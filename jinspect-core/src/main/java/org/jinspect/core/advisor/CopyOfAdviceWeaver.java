package org.jinspect.core.advisor;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

import org.jinspect.core.advisor.util.StringUtils;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.commons.JSRInlinerAdapter;
import org.objectweb.asm.commons.Method;

/**
 * transform class bytecode(植入增强代码)
 * 
 * @author david
 * @since 2017年11月02日
 */
public class CopyOfAdviceWeaver extends ClassVisitor implements Opcodes, AsmMethods {

    private final AdviceListener adviceListener;
    private final Class<?> targetClass;
    private final ClassLoader classLoader;
    private final String className;
    private final int adviceId;
    private final String transferName;

    private static final Map<Integer, AdviceListener> LISTENER_MAP = new ConcurrentHashMap<Integer, AdviceListener>();

    private static final Type ADVICE_WEAVER_TYPE = Type.getType(CopyOfAdviceWeaver.class);
    private static final Type CLASSLOADER_TYPE = Type.getType(ClassLoader.class);
    private static final Type CLASS_TYPE = Type.getType(Class.class);
    private static final Type ADVICE_LISTENER_TYPE = Type.getType(AdviceListener.class);
    private static final Type STRING_TYPE = Type.getType(String.class);
    private static final Type THROWABLE_TYPE = Type.getType(Throwable.class);
    private static final Type OBJECT_TYPE = Type.getType(Object.class);

    private static final String FIELD_NAME_CLASSLOADER = "classLoader";
    private static final String FIELD_NAME_TARGET_CLASS = "targetClass";
    private static final String FIELD_NAME_CLASSNAME = "className";
    private static final String FIELD_NAME_ADVICE_LISTENER = "adviceListener";

    public CopyOfAdviceWeaver(int adviceId, AdviceListener adviceListener, String transferName, Class<?> targetClass,
        ClassVisitor cv) {
        super(ASM7, cv);
        this.adviceId = adviceId;
        this.adviceListener = adviceListener;
        this.targetClass = targetClass;
        this.className = targetClass.getName();
        this.transferName = transferName;
        this.classLoader = targetClass.getClassLoader();
        LISTENER_MAP.put(adviceId, adviceListener);
    }

    private static final ThreadLocal<Boolean> selfCalled = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    private static final ThreadLocal<Stack<Stack<Object>>> threadFrameStack = new ThreadLocal<Stack<Stack<Object>>>() {
        @Override
        protected Stack<Stack<Object>> initialValue() {
            return new Stack<Stack<Object>>();
        }
    };

    public static void methodOnBegin(int adviceId, ClassLoader loader, String className, String methodName,
        String methodDesc, Object target, Object[] args) {

        if (selfCalled.get()) {
            return;
        } else {
            selfCalled.set(true);
        }
        try {
            AdviceListener listener = LISTENER_MAP.get(adviceId);
            if (listener == null) {
                throw new RuntimeException("no listener for:" + adviceId);
            }
            // 为了方便returning/throwing方法,先保护现场入栈
            Stack<Object> beginMethodFrame = new Stack<Object>();
            beginMethodFrame.push(listener);
            beginMethodFrame.push(loader);
            beginMethodFrame.push(className);
            beginMethodFrame.push(methodName);
            beginMethodFrame.push(methodDesc);
            beginMethodFrame.push(target);
            beginMethodFrame.push(args);

            threadFrameStack.get().push(beginMethodFrame);

            // 执行before listener
            doBefore(listener, loader, className, methodName, methodDesc, target, args);
        } finally {
            selfCalled.set(false);
        }

    }

    public static void methodOnReturning(Object returnVal) {
        methodOnEnd(false, returnVal);
    }

    public static void methodOnThrowing(Throwable throwable) {
        methodOnEnd(true, throwable);
    }

    public static void methodOnEnd(boolean isThrowing, Object returnTarget) {
        if (selfCalled.get()) {
            return;
        } else {
            selfCalled.set(true);
        }

        // 从栈中恢复执行现场
        try {
            Stack<Object> beginMethodFrame = threadFrameStack.get().pop();

            Object[] args = (Object[])beginMethodFrame.pop();
            Object target = beginMethodFrame.pop();
            String methodDesc = (String)beginMethodFrame.pop();
            String methodName = (String)beginMethodFrame.pop();
            String className = (String)beginMethodFrame.pop();
            ClassLoader classLoader = (ClassLoader)beginMethodFrame.pop();
            AdviceListener adviceListener = (AdviceListener)beginMethodFrame.pop();

            if (isThrowing) {
                // 执行
                doAfterThrowing(adviceListener, classLoader, className, methodName, methodDesc, target, args,
                    (Throwable)returnTarget);
            } else {
                doAfterReturning(adviceListener, classLoader, className, methodName, methodDesc, target, args,
                    returnTarget);
            }

        } finally {
            selfCalled.set(false);
        }
    }

    private static void doBefore(AdviceListener adviceListener, ClassLoader loader, String className,
        String methodName, String methodDesc, Object target, Object[] args) {
        if (adviceListener != null) {
            try {
                adviceListener.before(loader, className, methodName, methodDesc, target, args);
            } catch (Throwable th) {
                // to log
                // 此处其实可以约定listener内部处理好异常,不允许抛出
            }
        }
    }

    private static void doAfterReturning(AdviceListener adviceListener, ClassLoader classLoader, String className,
        String methodName, String methodDesc, Object target, Object[] args, Object returnObj) {
        if (adviceListener != null) {
            try {
                adviceListener.afterReturning(classLoader, className, methodName, methodDesc, target, args, returnObj);
            } catch (Throwable th) {
                // to log
                // 此处其实可以约定listener内部处理好异常,不允许抛出
            }
        }
    }

    private static void doAfterThrowing(AdviceListener adviceListener, ClassLoader loader, String className,
        String methodName, String methodDesc, Object target, Object[] args, Throwable throwable) {
        if (adviceListener != null) {
            try {
                adviceListener.afterThrowing(loader, className, methodName, methodDesc, target, args, throwable);
            } catch (Throwable th) {
                // to log
                // 此处其实可以约定listener内部处理好异常,不允许抛出
            }
        }
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, final String name, final String desc, String signature,
        String[] exceptions) {
        final MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

        if (!"execute".equals(name) && !"pp".equals(name) && !"start".equals(name)) {
            return mv;
        }
        if (isIgnore(mv, access, name)) {
            return mv;
        }

        return new AdviceAdapter(Opcodes.ASM7, new JSRInlinerAdapter(mv, access, name, desc, signature, exceptions),
            access, name, desc) {

            // -- Label for try...catch block
            private final Label beginLabel = new Label();
            private final Label endLabel = new Label();

            // -- KEY of advice --
            private final int KEY_ARTHAS_ADVICE_BEFORE_METHOD = 0;
            private final int KEY_ARTHAS_ADVICE_RETURN_METHOD = 1;
            private final int KEY_ARTHAS_ADVICE_THROWS_METHOD = 2;
            private final int KEY_ARTHAS_ADVICE_BEFORE_INVOKING_METHOD = 3;
            private final int KEY_ARTHAS_ADVICE_AFTER_INVOKING_METHOD = 4;
            private final int KEY_ARTHAS_ADVICE_THROW_INVOKING_METHOD = 5;

            // -- KEY of ASM_TYPE or ASM_METHOD --
            private final Type ASM_TYPE_SPY = Type.getType("Lorg/jinspect/probe/Probe;");
            private final Type ASM_TYPE_OBJECT = Type.getType(Object.class);
            private final Type ASM_TYPE_OBJECT_ARRAY = Type.getType(Object[].class);
            private final Type ASM_TYPE_CLASS = Type.getType(Class.class);
            private final Type ASM_TYPE_INTEGER = Type.getType(Integer.class);
            private final Type ASM_TYPE_CLASS_LOADER = Type.getType(ClassLoader.class);
            private final Type ASM_TYPE_STRING = Type.getType(String.class);
            private final Type ASM_TYPE_THROWABLE = Type.getType(Throwable.class);
            private final Type ASM_TYPE_INT = Type.getType(int.class);
            private final Type ASM_TYPE_METHOD = Type.getType(java.lang.reflect.Method.class);
            private final Method ASM_METHOD_METHOD_INVOKE = Method.getMethod("Object invoke(Object,Object[])");

            // 代码锁
            private final CodeLock codeLockForTracing = new TracingAsmCodeLock(this);

            private int lineNumber;

            /**
             * 加载通知方法
             * 
             * @param keyOfMethod
             *            通知方法KEY
             */
            private void loadAdviceMethod(int keyOfMethod) {

                switch (keyOfMethod) {

                    case KEY_ARTHAS_ADVICE_BEFORE_METHOD: {
                        getStatic(ASM_TYPE_SPY, "ON_BEFORE_METHOD", ASM_TYPE_METHOD);
                        break;
                    }

                    case KEY_ARTHAS_ADVICE_RETURN_METHOD: {
                        getStatic(ASM_TYPE_SPY, "ON_RETURN_METHOD", ASM_TYPE_METHOD);
                        break;
                    }

                    case KEY_ARTHAS_ADVICE_THROWS_METHOD: {
                        getStatic(ASM_TYPE_SPY, "ON_THROWS_METHOD", ASM_TYPE_METHOD);
                        break;
                    }

                    case KEY_ARTHAS_ADVICE_BEFORE_INVOKING_METHOD: {
                        getStatic(ASM_TYPE_SPY, "BEFORE_INVOKING_METHOD", ASM_TYPE_METHOD);
                        break;
                    }

                    case KEY_ARTHAS_ADVICE_AFTER_INVOKING_METHOD: {
                        getStatic(ASM_TYPE_SPY, "AFTER_INVOKING_METHOD", ASM_TYPE_METHOD);
                        break;
                    }

                    case KEY_ARTHAS_ADVICE_THROW_INVOKING_METHOD: {
                        getStatic(ASM_TYPE_SPY, "THROW_INVOKING_METHOD", ASM_TYPE_METHOD);
                        break;
                    }

                    default: {
                        throw new IllegalArgumentException("illegal keyOfMethod=" + keyOfMethod);
                    }

                }

            }

            /**
             * 加载ClassLoader<br/>
             * 这里分开静态方法中ClassLoader的获取以及普通方法中ClassLoader的获取 主要是性能上的考虑
             */
            private void loadClassLoader() {

                if (this.isStaticMethod()) {
                    visitLdcInsn(StringUtils.normalizeClassName(className));
                    invokeStatic(ASM_TYPE_CLASS, Method.getMethod("Class forName(String)"));
                    invokeVirtual(ASM_TYPE_CLASS, Method.getMethod("ClassLoader getClassLoader()"));

                } else {
                    loadThis();
                    invokeVirtual(ASM_TYPE_OBJECT, Method.getMethod("Class getClass()"));
                    invokeVirtual(ASM_TYPE_CLASS, Method.getMethod("ClassLoader getClassLoader()"));
                }

            }

            /**
             * 加载before通知参数数组
             */
            private void loadArrayForBefore() {
                push(7);
                newArray(ASM_TYPE_OBJECT);

                dup();
                push(0);
                push(adviceId);
                box(ASM_TYPE_INT);
                arrayStore(ASM_TYPE_INTEGER);

                dup();
                push(1);
                loadClassLoader();
                arrayStore(ASM_TYPE_CLASS_LOADER);

                dup();
                push(2);
                push(className);
                arrayStore(ASM_TYPE_STRING);

                dup();
                push(3);
                push(name);
                arrayStore(ASM_TYPE_STRING);

                dup();
                push(4);
                push(desc);
                arrayStore(ASM_TYPE_STRING);

                dup();
                push(5);
                loadThisOrPushNullIfIsStatic();
                arrayStore(ASM_TYPE_OBJECT);

                dup();
                push(6);
                loadArgArray();
                arrayStore(ASM_TYPE_OBJECT_ARRAY);
            }

            @Override
            protected void onMethodEnter() {

                codeLockForTracing.lock(new CodeLock.Block() {
                    @Override
                    public void code() {
                        // 加载before方法
                        loadAdviceMethod(KEY_ARTHAS_ADVICE_BEFORE_METHOD);
                        // 推入Method.invoke()的第一个参数
                        pushNull();
                        // 方法参数
                        loadArrayForBefore();
                        // 调用方法
                        invokeVirtual(ASM_TYPE_METHOD, ASM_METHOD_METHOD_INVOKE);
                        pop();

                    }
                });

                mark(beginLabel);

            }

            /*
             * 加载return通知参数数组
             */
            private void loadReturnArgs() {
                dup2X1();
                pop2();
                push(1);
                newArray(ASM_TYPE_OBJECT);
                dup();
                dup2X1();
                pop2();
                push(0);
                swap();
                arrayStore(ASM_TYPE_OBJECT);
            }

            @Override
            protected void onMethodExit(final int opcode) {

                if (!isThrow(opcode)) {
                    codeLockForTracing.lock(new CodeLock.Block() {
                        @Override
                        public void code() {

                            final StringBuilder append = new StringBuilder();

                            // 加载返回对象
                            loadReturn(opcode);

                            // 加载returning方法
                            loadAdviceMethod(KEY_ARTHAS_ADVICE_RETURN_METHOD);

                            // 推入Method.invoke()的第一个参数
                            pushNull();

                            // 加载return通知参数数组
                            loadReturnArgs();

                            invokeVirtual(ASM_TYPE_METHOD, ASM_METHOD_METHOD_INVOKE);
                            pop();

                        }
                    });
                }

            }

            /*
             * 创建throwing通知参数本地变量
             */
            private void loadThrowArgs() {
                dup2X1();
                pop2();
                push(1);
                newArray(ASM_TYPE_OBJECT);
                dup();
                dup2X1();
                pop2();
                push(0);
                swap();
                arrayStore(ASM_TYPE_THROWABLE);
            }

            @Override
            public void visitMaxs(int maxStack, int maxLocals) {

                mark(endLabel);
                // catchException(beginLabel, endLabel, ASM_TYPE_THROWABLE);
                visitTryCatchBlock(beginLabel, endLabel, mark(), ASM_TYPE_THROWABLE.getInternalName());

                codeLockForTracing.lock(new CodeLock.Block() {
                    @Override
                    public void code() {

                        final StringBuilder append = new StringBuilder();

                        // 加载异常
                        loadThrow();

                        // 加载throwing方法
                        loadAdviceMethod(KEY_ARTHAS_ADVICE_THROWS_METHOD);

                        // 推入Method.invoke()的第一个参数
                        pushNull();

                        // 加载throw通知参数数组
                        loadThrowArgs();

                        // 调用方法
                        invokeVirtual(ASM_TYPE_METHOD, ASM_METHOD_METHOD_INVOKE);
                        pop();

                    }
                });

                throwException();

                super.visitMaxs(maxStack, maxLocals);
            }

            @Override
            public void visitLineNumber(int line, Label start) {
                super.visitLineNumber(line, start);
                lineNumber = line;
            }

            /**
             * 是否静态方法
             * 
             * @return true:静态方法 / false:非静态方法
             */
            private boolean isStaticMethod() {
                return (methodAccess & ACC_STATIC) != 0;
            }

            /**
             * 是否抛出异常返回(通过字节码判断)
             * 
             * @param opcode
             *            操作码
             * @return true:以抛异常形式返回 / false:非抛异常形式返回(return)
             */
            private boolean isThrow(int opcode) {
                return opcode == ATHROW;
            }

            /**
             * 将NULL推入堆栈
             */
            private void pushNull() {
                push((Type)null);
            }

            /**
             * 加载this/null
             */
            private void loadThisOrPushNullIfIsStatic() {
                if (isStaticMethod()) {
                    pushNull();
                } else {
                    loadThis();
                }
            }

            /**
             * 加载返回值
             * 
             * @param opcode
             *            操作吗
             */
            private void loadReturn(int opcode) {
                switch (opcode) {

                    case RETURN: {
                        pushNull();
                        break;
                    }

                    case ARETURN: {
                        dup();
                        break;
                    }

                    case LRETURN:
                    case DRETURN: {
                        dup2();
                        box(Type.getReturnType(methodDesc));
                        break;
                    }

                    default: {
                        dup();
                        box(Type.getReturnType(methodDesc));
                        break;
                    }

                }
            }

            /**
             * 加载异常
             */
            private void loadThrow() {
                dup();
            }

            /**
             * 加载方法调用跟踪通知所需参数数组
             */
            private void loadArrayForInvokeTracing(String owner, String name, String desc, int lineNumber) {
                push(5);
                newArray(ASM_TYPE_OBJECT);

                dup();
                push(0);
                push(adviceId);
                box(ASM_TYPE_INT);
                arrayStore(ASM_TYPE_INTEGER);

                dup();
                push(1);
                push(owner);
                arrayStore(ASM_TYPE_STRING);

                dup();
                push(2);
                push(name);
                arrayStore(ASM_TYPE_STRING);

                dup();
                push(3);
                push(desc);
                arrayStore(ASM_TYPE_STRING);

                dup();
                push(4);
                push(lineNumber);
                box(ASM_TYPE_INT);
                arrayStore(ASM_TYPE_INTEGER);
            }

            @Override
            public void visitInsn(int opcode) {
                super.visitInsn(opcode);
                codeLockForTracing.code(opcode);
            }

            /*
             * 跟踪代码
             */
            private void tracing(final int tracingType, final String owner, final String name, final String desc,
                final int lineNumber) {

                final String label;
                switch (tracingType) {
                    case KEY_ARTHAS_ADVICE_BEFORE_INVOKING_METHOD: {
                        label = "beforeInvoking";
                        break;
                    }
                    case KEY_ARTHAS_ADVICE_AFTER_INVOKING_METHOD: {
                        label = "afterInvoking";
                        break;
                    }
                    case KEY_ARTHAS_ADVICE_THROW_INVOKING_METHOD: {
                        label = "throwInvoking";
                        break;
                    }
                    default: {
                        throw new IllegalStateException("illegal tracing type: " + tracingType);
                    }
                }

                codeLockForTracing.lock(new CodeLock.Block() {
                    @Override
                    public void code() {

                        final StringBuilder append = new StringBuilder();

                        loadAdviceMethod(tracingType);

                        pushNull();
                        loadArrayForInvokeTracing(owner, name, desc, lineNumber);

                        invokeVirtual(ASM_TYPE_METHOD, ASM_METHOD_METHOD_INVOKE);
                        pop();

                    }
                });

            }

            @Override
            public void visitMethodInsn(int opcode, final String owner, final String name, final String desc,
                boolean itf) {

                // 方法调用前通知
                tracing(KEY_ARTHAS_ADVICE_BEFORE_INVOKING_METHOD, owner, name, desc, lineNumber);

                final Label beginLabel = new Label();
                final Label endLabel = new Label();
                final Label finallyLabel = new Label();

                // try
                // {

                mark(beginLabel);
                super.visitMethodInsn(opcode, owner, name, desc, itf);
                mark(endLabel);

                // 方法调用后通知
                tracing(KEY_ARTHAS_ADVICE_AFTER_INVOKING_METHOD, owner, name, desc, lineNumber);
                goTo(finallyLabel);

                // }
                // catch
                // {

                catchException(beginLabel, endLabel, ASM_TYPE_THROWABLE);
                tracing(KEY_ARTHAS_ADVICE_THROW_INVOKING_METHOD, owner, name, desc, lineNumber);

                throwException();

                // }
                // finally
                // {
                mark(finallyLabel);
                // }
            }
        };
    }

    private boolean isIgnore(MethodVisitor mv, int access, String methodName) {
        return null == mv || isAbstract(access) || isFinalMethod(access) || "<clinit>".equals(methodName)
            || "<init>".equals(methodName);
    }

    private boolean isAbstract(int access) {
        return (ACC_ABSTRACT & access) == ACC_ABSTRACT;
    }

    private boolean isFinalMethod(int methodAccess) {
        return (ACC_FINAL & methodAccess) == ACC_FINAL;
    }

}

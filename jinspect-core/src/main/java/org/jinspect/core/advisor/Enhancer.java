package org.jinspect.core.advisor;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.concurrent.atomic.AtomicInteger;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class Enhancer implements ClassFileTransformer {

    private Class<?> targetClass;

    private AdviceListener adviceListener;

    private int adviceId;

    private String transferName;

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    public void setAdviceListener(AdviceListener adviceListener) {
        this.adviceListener = adviceListener;
        this.adviceId = ID_GENERATOR.getAndIncrement();
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public String genTransferClassName() {
        if (transferName == null) {
            this.transferName = this.targetClass.getName().replace("/", ".");
        }

        return this.transferName;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
        ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        System.out.println(className);
        if (className.contains("java/lang/Thread") || className.contains("Foo")) {
            System.out.println("fuck-- # " + className);
            try {
                ClassReader cr = new ClassReader(className);
                ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
                adviceId = ID_GENERATOR.getAndIncrement();
                cr.accept(new AdviceWeaver(adviceId, adviceListener, genTransferClassName(), targetClass, cw),
                    ClassReader.EXPAND_FRAMES);
                byte[] bb = cw.toByteArray();
                return bb;
            } catch (Exception e) {
                System.out.println("fuck error..." + e.getMessage());
                e.printStackTrace();
            } finally {
                System.out.println("fuck finally");
            }
        }
        return classfileBuffer;
    }

}

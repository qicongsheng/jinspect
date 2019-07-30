package org.jinspect.core.advisor;

import org.objectweb.asm.commons.Method;

/**
 * Asm methods 常量
 * 
 * @author davie
 * @since 2017年11月02日
 */
public interface AsmMethods {

    /**
     * @link {cn.david.scrat.profiler.enhance.AdviceWeaver#methodOnBegin(cn.david.scrat.profiler.enhance.AdviceListener,
     *       java.lang.ClassLoader, java.lang.String, java.lang.String, java.lang.String, java.lang.Object,
     *       java.lang.Object[])}
     */
    Method AdviceWeaver_methodOnBegin = MethodFinder.getAsmMethod(AdviceWeaver.class, "methodOnBegin", int.class,
        ClassLoader.class, String.class, String.class, String.class, Object.class, Object[].class);

    /**
     * @link {cn.david.scrat.profiler.enhance.AdviceWeaver#methodOnReturning(java.lang.Object)}
     */
    Method AdviceWeaver_methodOnReturning = MethodFinder.getAsmMethod(AdviceWeaver.class, "methodOnReturning",
        Object.class);

    /**
     * @link {cn.david.scrat.profiler.enhance.AdviceWeaver#methodOnThrowing(java.lang.Throwable)}
     */
    Method AdviceWeaver_methodOnThrowing = MethodFinder.getAsmMethod(AdviceWeaver.class, "methodOnThrowing",
        Throwable.class);

    Method Class_forName = MethodFinder.getAsmMethod(Class.class, "forName", String.class);

    Method OBJECT_getClass = MethodFinder.getAsmMethod(Object.class, "getClass");

    Method Class_getClassLoader = MethodFinder.getAsmMethod(Class.class, "getClassLoader");

}

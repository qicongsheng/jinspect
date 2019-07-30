package org.jinspect.core.advisor;

import org.objectweb.asm.commons.Method;

public class MethodFinder {

    private MethodFinder() {

    }

    public static Method getAsmMethod(final Class<?> clazz, final String methodName, final Class<?>... parameterTypes) {
        return Method.getMethod(getJavaMethodUnsafe(clazz, methodName, parameterTypes));
    }

    public static java.lang.reflect.Method getJavaMethodUnsafe(final Class<?> clazz, final String methodName,
        final Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}

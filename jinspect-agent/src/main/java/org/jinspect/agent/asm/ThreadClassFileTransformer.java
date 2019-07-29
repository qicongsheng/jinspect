package org.jinspect.agent.asm;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class ThreadClassFileTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
        ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println(className);
        if (className.contains("java/lang/Thread") || className.contains("Foo")) {
            System.out.println("fuck-- # " + className);
            try {
                ClassReader cr = new ClassReader(className);
                System.out.println("fuck 1");
                ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
                System.out.println("fuck 2");
                ClassVisitor cv = new MethodChangeClassAdapter(cw);
                cr.accept(cv, Opcodes.ASM7);
                System.out.println("fuck 3");
                return cw.toByteArray();
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

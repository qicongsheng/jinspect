package org.jinspect.agent.asm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class AsmAopExample extends ClassLoader implements Opcodes {

    public static void main(String[] args) throws IOException, IllegalArgumentException, SecurityException,
        IllegalAccessException, InvocationTargetException {

        ClassReader cr = new ClassReader(Foo.class.getName());
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new MethodChangeClassAdapter(cw);
        cr.accept(cv, Opcodes.ASM7);

        // gets the bytecode of the Example class, and loads it dynamically
        byte[] code = cw.toByteArray();

        // AsmAopExample loader = new AsmAopExample();
        // Class<?> exampleClass = loader.defineClass(Foo.class.getName(), code, 0, code.length);
        // // Class<?> exampleClass = Foo.class;
        //
        // for (Method method : exampleClass.getMethods()) {
        // System.out.println(method);
        // }
        //
        // exampleClass.getMethods()[0].invoke(null, null);

        // gets the bytecode of the Example class, and loads it dynamically

        FileOutputStream fos = new FileOutputStream("E://Foo.class");
        fos.write(code);
        fos.close();
    }

}

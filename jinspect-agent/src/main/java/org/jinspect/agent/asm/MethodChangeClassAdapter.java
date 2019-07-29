package org.jinspect.agent.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodChangeClassAdapter extends ClassVisitor implements Opcodes {

    public MethodChangeClassAdapter(final ClassVisitor cv) {
        super(Opcodes.ASM7, cv);
        System.out.println("MethodChangeClassAdapter---");
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("-----:name : " + name);
        if ("execute".equals(name) || "pp".equals(name) || "start".equals(name)) // 此处的execute即为需要修改的方法 ，修改方法內容
        {
            System.out.println("开始编制：" + name);
            MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);// 先得到原始的方法
            MethodVisitor newMethod = null;
            newMethod = new AsmMethodVisit(mv); // 访问需要修改的方法
            System.out.println("编制结束：" + name);
            return newMethod;
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

}

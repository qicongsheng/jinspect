package org.jinspect.agent.asm;

public class Foo {
    public static void execute() {
        System.out.println("test changed execute name");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void pp() {
        System.out.println("test changed pp name");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

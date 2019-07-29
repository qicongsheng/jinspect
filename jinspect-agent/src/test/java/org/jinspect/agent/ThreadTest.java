package org.jinspect.agent;

import org.jinspect.agent.asm.Foo;

public class ThreadTest {

    public static void ee() {
        new Foo().execute();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    ee();
                    System.out.println(Thread.currentThread().getId() + " : " + Thread.currentThread().getName());
                }
            };
            Thread.sleep(1000 * 3);
            t.start();
        }
    }
}

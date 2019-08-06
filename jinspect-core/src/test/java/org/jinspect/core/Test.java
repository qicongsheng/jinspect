package org.jinspect.core;

import org.jinspect.core.jvmti.JVMTICaller;
import org.jinspect.core.jvmti.JVMTIListenerHandler;
import org.jinspect.core.jvmti.ThreadStartupAndEndListener;

public class Test {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        JVMTICaller caller = JVMTICaller.getCaller();
        caller.loadNativeLib("/root/call.so");

        JVMTIListenerHandler handler = JVMTIListenerHandler.getHandler();
        handler.addThreadStartAndEndListener(new ThreadStartupAndEndListener() {
            @Override
            public void onThreadStart(Thread thread) {
                System.out.println(thread.getName() + " started. #" + thread.getId());
            }

            @Override
            public void onThreadEnd(Thread thread) {
                System.out.println(thread.getName() + " end. #" + thread.getId());
            }
        });
        caller.bindJVMTIListenerHandler(handler);

        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    System.out.println("I am " + this.getName());
                }
            };
            t.start();
            Thread.sleep(3000);
        }
    }

}

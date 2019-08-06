package org.jinspect.core;

import org.jinspect.core.jvmti.JVMTICaller;
import org.jinspect.core.jvmti.JVMTIListenerHandler;
import org.jinspect.core.jvmti.ThreadStartupListener;

public class Test {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        JVMTIListenerHandler handler = JVMTIListenerHandler.getHandler();
        handler.addThreadStartupListener(new ThreadStartupListener() {
            @Override
            public void onThreadStart(Thread thread) {
                System.out.println(thread.getName() + " started. #" + thread.getId());
            }

            @Override
            public void onThreadEnd(Thread thread) {
                System.out.println(thread.getName() + " end. #" + thread.getId());
            }
        });
        JVMTICaller caller = JVMTICaller.getCaller();
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

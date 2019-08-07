package org.jinspect.agent;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getId() + " : " + Thread.currentThread().getName());
                }
            };
            Thread.sleep(1000 * 3);
            try {
                t.start();
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000 * 3);
            }
        }
    }
}

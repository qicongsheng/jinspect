import java.util.ArrayList;
import java.util.List;

public class NativeDemo {
    {
        System.load("/root/nd.so");
    }

    public static void m1(final List<People> list) {
        Thread t = new Thread() {

            @Override
            public void run() {
                People p1 = new People();
                People p2 = new People();
                list.add(p1);
                list.add(p2);
                while (true) {
                    System.out.println("list size" + list.size());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };
        t.start();
    }

    public native int sayHello(Object obj);

    public static void main(String[] args) throws InterruptedException {
        List<People> list = new ArrayList<People>();
        m1(list);
        int a = new NativeDemo().sayHello(list);
        System.out.println(a);
    }
}

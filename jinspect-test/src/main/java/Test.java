import java.util.ArrayList;
import java.util.List;

public class Test {

    public static List<String> getList() {
        List<String> m = new ArrayList<String>();
        m.add("aadd");
        m.add("www");
        return m;
    }

    public static void main(String[] args) {
        String a = "";
        String b = a + "ww";
        getList().add(b);
        System.out.println(22);
    }

}
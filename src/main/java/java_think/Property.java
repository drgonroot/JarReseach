package java_think;

import java.util.Date;
import java.util.Properties;

/**
 * Created by useheart on 2018/11/5
 */
public class Property {
    public static void main(String[] args) {
        System.out.println(new Date());
        Properties p = System.getProperties();
        p.list(System.out);
        System.out.println("--- Memory Usage");
        Runtime rt = Runtime.getRuntime();
        System.out.println("Total Memory = " + rt.totalMemory()
                + "Free Memory = "
                + rt.freeMemory());
    }
}

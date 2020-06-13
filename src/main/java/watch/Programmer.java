package watch;

/**
 * Created by useheart on 2019-04-18
 *
 * @author useheart
 */
abstract class Writer {
    public static void write() {
        System.out.println("writing..");
    }
}

class Author extends Writer {
    public static void write() {
        System.out.println("Writing book");
    }
}

public class Programmer extends Writer {
    public static void write() {
        System.out.println("Writing code");
    }

    public static void main(String[] args) {
        Writer writer = new Programmer();
        writer.write();
    }
}

package security;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by useheart on 2020/5/15
 *
 * @author useheart
 */

public class FileReader {
    public void read(String fileName) throws Exception {
        System.out.println(fileName);
        InputStream in = new FileInputStream(fileName);
    }
}

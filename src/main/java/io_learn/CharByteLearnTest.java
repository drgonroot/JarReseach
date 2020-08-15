package io_learn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by useheart on 2019-06-02
 *
 * @author useheart
 */
public class CharByteLearnTest {
    public static void main(String[] args) throws IOException {
        byte byte1 = 1;
        System.out.println("byte:" + byte1);
        System.out.println("char:" + (char) (byte1));
        char char1 = 'A';
        char char2 = '总';
        //char char3 = 'A总';
        //char char4 = "A总";
        System.out.println("char1:" + char1);
        System.out.println("char2:" + char2);
        System.out.println("char2:" + char2);
        System.out.println(System.getProperty("user.dir"));
        System.out.println(File.separator);
        System.out.println(File.separatorChar);
        ///Users/useheart/Applications/IDEA/JarResearch/src/main/java/learnstream/alice30.txt
        //learnstream/alice30.txt
        InputStream inputStream = new FileInputStream("src/main/java/learnstream/alice30.txt");
        inputStream.close();
    }
}

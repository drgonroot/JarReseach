package io_learn;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by useheart on 2019-06-08
 *
 * @author useheart
 */
public class ByteBufferTest {
    public static void main(String[] args) throws IOException {
        // Declaring the capacity of the the ByteBuffer
        int capacity = 4;
        // Creating the ByteBuffer
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);

            /**
             * putting the int to byte  typecast value
             * in bytebuffer using putInt() method
             * */
            byteBuffer.put((byte) 20);
            byteBuffer.put((byte) 30);
            byteBuffer.put((byte) 40);
            byteBuffer.put((byte) 50);
            //byteBuffer.put((byte)60);
            System.out.println("Original ByteBuffer: " + Arrays.toString(byteBuffer.array()));
            byteBuffer.rewind();
            // print ByteBuffer
            System.out.println("Original ByteBuffer: " + Arrays.toString(byteBuffer.array()));

            FileChannel fileChannel = FileChannel.open(Paths.get("employee.dat"));
            FileLock fileLock = fileChannel.tryLock();
            fileLock.isShared();
            fileLock.release();
            fileChannel.close();
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentExc eption catched");
        } catch (ReadOnlyBufferException e) {
            System.out.println("ReadOnlyBufferException catched");
        }

        System.out.println();
    }
}

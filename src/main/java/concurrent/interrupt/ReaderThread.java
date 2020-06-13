package concurrent.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by useheart on 2020-03-22
 *
 * @author useheart
 */
public class ReaderThread extends Thread {

    private final Socket socket;
    private final InputStream in;
    private static final int BUFSZ = 1024;

    public ReaderThread(Socket socket, InputStream in) {
        this.socket = socket;
        this.in = in;
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException e) {

        } finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[BUFSZ];
            while (true) {
                int count = in.read(buf);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    processBuffer(buf, count);
                }
            }
        } catch (IOException e) {
            // 允许线程退出
        }
    }

    private void processBuffer(byte[] buf, int count) {
    }
}

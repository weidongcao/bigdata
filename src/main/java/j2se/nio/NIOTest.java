package j2se.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016/10/16.  E://Workspaces//resource//TestData//people.txt
 */
public class NIOTest {
    public static void main(String[] args) throws IOException{
        String input = "E://Workspaces//resource//TestData//people.txt";

        FileInputStream fin = new FileInputStream(input);

        //RandomAccessFile aFile = new RandomAccessFile(input,"rw");

        FileChannel fcin = fin.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(1024);


        while (fcin.read(buf) != -1) {
            buf.flip();

            while (buf.hasRemaining()) {
                System.out.println("Read => " + (char)buf.get());
            }

            buf.clear();
        }


    }
}

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class IOHandler {
    public void run(SelectionKey selectionKey) throws IOException {
        if(selectionKey.isReadable()){
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int len = 0;
            while ((len = (socketChannel.read(byteBuffer))) > 0) {
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array(), 0, len));
                byteBuffer.clear();
            }

        }
    }
}

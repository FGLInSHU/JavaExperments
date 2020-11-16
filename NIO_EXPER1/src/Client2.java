import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client2 {
    public static void main(String[] args) throws IOException {
        new Client2().start();
    }
    public void start() throws IOException {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            //连接服务端socket
            SocketAddress socketAddress = new InetSocketAddress("localhost", 8888);
            try {
                socketChannel.connect(socketAddress);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int sendCount = 0;

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            //这里最好使用selector处理   这里只是为了写的简单
            while (sendCount < 20) {
                buffer.clear();
                //向服务端发送消息
                buffer.put(("client2 index : " + sendCount).getBytes());
                //读取模式
                buffer.flip();
                try {
                    socketChannel.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                buffer.clear();

                //从服务端读取消息
//                int readLenth = socketChannel.read(buffer);
//                //读取模式
//                buffer.flip();
//                byte[] bytes = new byte[readLenth];
//                buffer.get(bytes);
//                System.out.println(new String(bytes, "UTF-8"));
//                buffer.clear();


                sendCount++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



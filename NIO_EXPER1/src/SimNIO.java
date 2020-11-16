import java.io.IOException;
import java.nio.channels.SelectionKey;

/*
    一个mainReactor负责监听连接事件，每次接受连接后分发给subReactor监听IO事件
 */
public class SimNIO {
    public static void main(String[] args)throws IOException {
        MainReactor mainReactor = new MainReactor(8888);
        while(true){
            mainReactor.startListen(mainReactor.selector, SelectionKey.OP_ACCEPT);
        }

    }
}

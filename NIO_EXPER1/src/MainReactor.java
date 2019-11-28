import com.sun.org.apache.xerces.internal.xs.ItemPSVI;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainReactor extends Reactor {
    private Selector selector;
    private final int port;
    //serverSocketChannel管理selector
    private ServerSocketChannel serverSocketChannel;

    public MainReactor(int port) throws IOException {
        this.port = port;
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

//    public boolean addSubReactor(SubReactor subReactor){
//        return subReactorList.add(subReactor);
//    }





}

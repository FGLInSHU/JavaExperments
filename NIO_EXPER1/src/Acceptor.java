import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class Acceptor {
    private static List<SubReactor> subReactorList = new ArrayList<>();

    private SelectionKey sk;
    private static int index = 0;
    private static int cores = 2;//Runtime.getRuntime().availableProcessors();;

    static{
        for(int i = 0; i < cores; i++){
            Selector selector = null;
            try {
                selector = Selector.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SubReactor subReactor = new SubReactor();
            subReactor.addSelector(selector);
            subReactorList.add(subReactor);
        }
    }
    public Acceptor(SelectionKey sk) {
        this.sk = sk;
    }


    public  static int dispatch(SelectionKey sk){
        try {
            //将监听的通道注册到对应的selector上，并指定确定监听的事件
            ServerSocketChannel ssc = (ServerSocketChannel) sk.channel();
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);
            SubReactor subReactor = subReactorList.get(index);
            sc.register(subReactor.getSelector(), SelectionKey.OP_READ);
            subReactor.operation = SelectionKey.OP_READ;
            //阻塞地监听
            new Thread(subReactor).start();
//            subReactor.startListen(subReactor.getSelector(), SelectionKey.OP_READ);
            index = (index + 1) % cores;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

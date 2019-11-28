import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.List;

public class Acceptor {
    private static List<SubReactor> subReactorList;

    private SelectionKey sk;

    public Acceptor(SelectionKey sk) {
        this.sk = sk;
    }

    public boolean addSubReactor(SubReactor subReactor){
        return  subReactorList.add(subReactor);
    }

    public  int dispatch(SelectionKey sk){
        try {
            subReactorList.get(0).startListen(sk);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

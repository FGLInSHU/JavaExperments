import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class Reactor implements Runnable{

    public Selector selector;
    public Integer operation;
    @Override
    public void run() {
        try {
            startListen(selector, operation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void startListen(Selector selector, int oper) throws IOException {
        synchronized (selector) {
            System.out.println("开始监听");
            while (true) {
                //阻塞地监听事件
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                SelectionKey tmpKey;
                while (iterator.hasNext()) {
                    tmpKey = iterator.next();
                    if ((tmpKey.isReadable() && oper == SelectionKey.OP_READ)
                            || (tmpKey.isAcceptable() && oper == SelectionKey.OP_ACCEPT)) {
                        iterator.remove();
                        if (oper == SelectionKey.OP_ACCEPT)
                            dispatch(tmpKey);
                        else
                            dispatch2IOHandler(tmpKey);  //监听到读写事件则交给IOHandler去处理
                    }
                }

            }
        }
    }
    private void dispatch(SelectionKey selectionKey){
        Acceptor.dispatch(selectionKey);
    }
    private void dispatch2IOHandler(SelectionKey selectionKey) throws IOException {
        new IOHandler().run(selectionKey);
    }
}

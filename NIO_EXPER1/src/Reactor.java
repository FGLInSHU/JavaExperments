import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class Reactor {
    public void startListen(Selector selector, int oper) throws IOException {
        System.out.println("开始监听");
        while(true){
            //阻塞地监听事件
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            SelectionKey tmpKey ;
            while (iterator.hasNext()){
                tmpKey = iterator.next();
                iterator.remove();
                if(oper == SelectionKey.OP_ACCEPT)
                    dispatch(tmpKey);
                else
                    dispatch2IOHandler(tmpKey);
            }

        }
    }
    private void dispatch(SelectionKey selectionKey){
        new Acceptor(selectionKey).dispatch(selectionKey);
    }
    private void dispatch2IOHandler(SelectionKey selectionKey){
        new Acceptor(selectionKey).dispatch(selectionKey);
    }
}

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SubReactor extends Reactor{
    private Selector selector;
    public void addSelector(Selector selector){
        this.selector = selector;
    }
    public Selector getSelector(){
        return  selector;
    }


}

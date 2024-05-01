package test;
import java.util.LinkedList;
import java.util.HashMap;

public class LRU implements CacheReplacementPolicy {

    private HashMap<String, String> data;
    private LinkedList<String> order;

    public LRU() {
        this.data = new HashMap<String, String>();
        this.order = new LinkedList<>();
    }

    @Override
    public void add(String word) {
        
        if (order.contains(word)) {
            order.remove(order.indexOf(word));
        }
        
        order.addFirst(word);
        data.put(word, word);
    }

    @Override
    public String remove() {
        
        String laString = order.removeLast();
        data.remove(laString);

        return laString;
    }


}

package test;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Map;

class Pair {

    String value;
    int frequency;
 

    public Pair(String value, int frequency) {

        this.value = value;

        this.frequency = frequency;

    }
}

public class LFU implements CacheReplacementPolicy {

    private Map<String, Pair> cache;
    private PriorityQueue<Pair> minHeap;

    public LFU() {
        this.cache = new HashMap<>();
        this.minHeap = new PriorityQueue<>((a,b) -> a.frequency - b.frequency);
    }

    public void increase(String value) {
        if (this.cache.containsKey(value)) {
            Pair pair = this.cache.get(value);
            pair.frequency++;
            minHeap.remove(pair);
            minHeap.offer(pair);
        }
    }

    @Override
    public void add(String word) {
        
        if (!this.cache.containsKey(word)){
            Pair newPair = new Pair(word, 1);
            this.cache.put(word, newPair);
            this.minHeap.offer(newPair);
        }

        else {
            increase(word);
        }
         
    }

    @Override
    public String remove() {
        
        Pair lfuPair = minHeap.poll();
        this.cache.remove(lfuPair.value);

        return lfuPair.value;
    }
    
}

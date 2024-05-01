package test;
import java.util.HashSet;

public class CacheManager {

    protected HashSet<String> wordsInCache;
    protected int capacity;
    protected CacheReplacementPolicy crp;

    public CacheManager(int size, CacheReplacementPolicy crp) {
        this.capacity = size;
        this.wordsInCache = new HashSet<String>();
        this.crp = crp;
    }

    public void add(String string) {
        
        crp.add(string);
        wordsInCache.add(string);

        if (wordsInCache.size() > capacity) {
            wordsInCache.remove(crp.remove());
        }
        
    }

    public boolean query(String string) {
        
        if (wordsInCache.contains(string)) {
            return true;
        }

        return false;
    }
	
	

}

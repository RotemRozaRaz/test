package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {

    public CacheManager WordsInBooks;
    public CacheManager WordsNotInBooks;
    public BloomFilter bf;
    public String[] Books;
   

    public Dictionary(String... fileNames) {

        this.Books = fileNames;

        LRU lru = new LRU();
        this.WordsInBooks = new CacheManager(400, lru);

        LFU lfu = new LFU();
        this.WordsNotInBooks = new CacheManager(100, lfu);

        this.bf = new BloomFilter(256, "MD5", "SHA1");
        initBloomFilter();

        
    }

    private void initBloomFilter() {

        for (String fileName: Books) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
                String line;

                while ((line = br.readLine()) != null) {
                    String[] lineWords = line.split("\\W+");
                    for (String word : lineWords) {
                        if (!word.isEmpty()) {
                            bf.add(word);
                        }
                    }
                }

                br.close();

            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        }
    }
    public boolean query(String string) {
        
        if (this.WordsInBooks.query(string)) {
            return true;
        }
        else if (this.WordsNotInBooks.query(string)) {
            return false;
        }
        else {
            if(bf.contains(string)) {
                this.WordsInBooks.add(string);
                return true;
            }
            else {
                this.WordsNotInBooks.add(string);
                return false;
            }
        }
    }

    public boolean challenge(String string) {

        if (IOSearcher.search(string, Books)) {
            this.WordsInBooks.add(string);
            return true;
        }

        else {
            this.WordsNotInBooks.add(string);
            return false;
        }
    }

}

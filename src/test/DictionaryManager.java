package test;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class DictionaryManager {

    private HashMap<String, Dictionary> BooksDictionary;
    private static DictionaryManager DictionaryManageringletone = null;

    private DictionaryManager() {
        BooksDictionary  = new HashMap<>();
    }

    public static DictionaryManager get() {
        if (DictionaryManageringletone == null) {
            DictionaryManageringletone = new DictionaryManager();
        }

        return DictionaryManageringletone;
    }

    private boolean querySingleBook(String BookName, String query) {

        if (BooksDictionary.get(BookName) == null) {
            BooksDictionary.put(BookName, new Dictionary(BookName));
        }

        Dictionary dict = BooksDictionary.get(BookName);

        if (dict.query(query)) {
            return true;
        }

        return false;
    }

    public boolean query(String... args) {
        
        String textToQuery = args[args.length - 1];
        List<Boolean> isQuery = new ArrayList<>();

        for (int i = 0; i < args.length - 1; i++) {
            if (querySingleBook(args[i], textToQuery)) {
                isQuery.add(true);
            }

            else {
                isQuery.add(false);
            }
        }

        return isQuery.stream().anyMatch(Boolean::valueOf);
    }

    private boolean challengeSingleBook(String BookName, String challengeString) {
        if (BooksDictionary.get(BookName) == null) {
            BooksDictionary.put(BookName, new Dictionary(BookName));
        }

        Dictionary dict = BooksDictionary.get(BookName);

        if (dict.challenge(challengeString)) {
            return true;
        }

        return false;
    }

    public boolean challenge(String... args) {
        
        String textToChallnge = args[args.length - 1];
        List<Boolean> isChallenge = new ArrayList<>();

        for (int i = 0; i < args.length - 1; i++) {
            if (challengeSingleBook(args[i], textToChallnge)) {
                isChallenge.add(true);
            }

            else {
                isChallenge.add(false);
            }
        }

        return isChallenge.stream().anyMatch(Boolean::valueOf);

    }

    public int getSize() {
        return BooksDictionary.size();
    }

}

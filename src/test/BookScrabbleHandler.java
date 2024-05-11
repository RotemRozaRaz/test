package test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class BookScrabbleHandler implements ClientHandler {

    @Override
    public void close() {
        
    }

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient){
        Scanner scanner = new Scanner(inFromclient);
        try {

            String line = scanner.nextLine();
            System.out.println("server got: " + line);

            String[] words = line.split(",");
            String task = words[0];
            String[] BooksnWord = createListOfBooksnWord(words);

            DictionaryManager DM = DictionaryManager.get();
            String result = "\n";

            if (task.equals("Q")) 
            {
                if (DM.query(BooksnWord)) 
                {
                    result = "true\n";
                }

                else {
                    result = "false\n";
                }
            }
            else if (task.equals("C"))
            {
                if (DM.challenge(BooksnWord))
                {
                    result = "true\n";
                }
                else
                {
                    result = "false\n";
                }
            }
            System.out.println("server sends: " + result);
            outToClient.write(result.getBytes());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            scanner.close();
        }
    }

    private String[] createListOfBooksnWord(String[] words) {
        String[] BooksnWord = new String[words.length-1];

        for (int i = 1; i < words.length; i++) {
            BooksnWord[i-1] = words[i];
        }

        return BooksnWord;
    }

}

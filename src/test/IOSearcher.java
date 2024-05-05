package test;

import java.io.BufferedReader;
import java.io.FileReader;

public class IOSearcher {

    public static boolean search(String word, String... fileNames) {
        boolean wordFound = false;

        for (String fileName: fileNames) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains(word)) {
                        wordFound = true;
                    }
                }
                br.close();
                
            } catch (Exception e) {
                System.err.println("Error reading file: " + fileName + ": " + e.getMessage());
            }
        }
        
        return wordFound;
    }
}

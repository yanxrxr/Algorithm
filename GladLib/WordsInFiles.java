
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;
import edu.duke.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordMap;
    
    public WordsInFiles() {
        wordMap = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for (String s: fr.words()) {
            //s = s.toLowerCase();
            ArrayList<String> fileList;
            if(!wordMap.containsKey(s)) {
                fileList = new ArrayList<String>();
            }
            else {
                fileList = wordMap.get(s);
            }
            if(!fileList.contains(f.getName())) {
                fileList.add(f.getName());
            }
            wordMap.put(s, fileList);
        }
    }
    
    public void buildWordFileMap() {
        wordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber() {
        int max = 0;
        for(ArrayList<String> l: wordMap.values()) {
            int size = l.size();
            if (size > max) {
                max = size;
            }
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordList = new ArrayList<String>();
        for(String s: wordMap.keySet()) {
            ArrayList<String> l = wordMap.get(s);
            if(l.size() == number) {
                wordList.add(s);
            }
        }
        return wordList;
    }
    
    public void printFilesIn(String word) {
        ArrayList<String> l = wordMap.get(word);
        for(int i = 0; i < l.size(); i ++) {
            System.out.println(l.get(i));
        }
    }
    
    public void tester() {
        buildWordFileMap();
        System.out.println(maxNumber());
        System.out.println(wordMap.size());
        int ct = 0;
        for (String s: wordMap.keySet()) {
            if (wordMap.get(s).size() == 5) {
                ct++;
            }
        }
        System.out.println(ct);
    }

}

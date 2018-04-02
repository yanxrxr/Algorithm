
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String s: fr.words()) {
            int index = myWords.indexOf(s.toLowerCase());
            if (index == -1) {
                myWords.add(s.toLowerCase());
                myFreqs.add(1);
            }
            else {
                int ct = myFreqs.get(index);
                myFreqs.set(index, ct + 1);
            }
        }
    }
    
    public int findIndexOfMax() {
        int max = 0;
        int maxInd = 0;
        for (int i = 0; i < myFreqs.size(); i ++) {
            if (myFreqs.get(i) > max) {
                max = myFreqs.get(i);
                maxInd = i;
            }
        }
        return maxInd;
    }
    
    public void tester() {
        findUnique();
        for (int i = 0; i < myWords.size(); i ++) {
            System.out.println(myWords.get(i) + "\t" + myFreqs.get(i));
        }
        int maxInd = findIndexOfMax();
        System.out.println(myWords.size() + "\tunique words");
        System.out.println("The word that occurs most ofetn and its count are: " + myWords.get(maxInd) + " " + myFreqs.get(maxInd));
    }

}

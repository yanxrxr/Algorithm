
/**
 * Write a description of CountCodon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class CountCodon {
    private HashMap<String, Integer> dnaMap;
    
    public CountCodon() {
        dnaMap = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna) {
        for (int i = start; i < dna.length()-3; i += 3) {
            String codon = dna.substring(i, i+3);
            if (!dnaMap.containsKey(codon)) {
                dnaMap.put(codon, 1);
            }
            else {
                dnaMap.put(codon, dnaMap.get(codon) + 1);
            }
        }
    }
    
    public String getMostCommonCodon() {
        int maxVal = 0;
        String mostCommon = "";
        for (String s: dnaMap.keySet()) {
            if (dnaMap.get(s) > maxVal) {
                maxVal = dnaMap.get(s);
                mostCommon = s;
            }
        }
        return mostCommon;
    }
    
    public void printCodonCounts(int start, int end) {
        for (String s: dnaMap.keySet()) {
            if (dnaMap.get(s) >= start && dnaMap.get(s) <= end) {
                System.out.println(s + " " + dnaMap.get(s));
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        buildCodonMap(1, dna);
        String mostCommon = getMostCommonCodon();
        System.out.println("Most common codon is " + mostCommon + " with count " + dnaMap.get(mostCommon));
        System.out.println(dnaMap.size());
        printCodonCounts(6,6);
    }

}

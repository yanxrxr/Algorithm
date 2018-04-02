
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        for(String word: resource.words()) {
            word = word.toLowerCase();
            int l = word.length();
            if (Character.isLetter(word.charAt(0)) == false) {
                l -= 1;
            }
            if (Character.isLetter(word.charAt(l-1)) == false) {
                l -= 1;
            }
            if (l > counts.length) {
                l = counts.length;
            }
            counts[l] ++;
        }
    }
    
    public int indexOfMax(int[] values) {
        int max = 0;
        int maxInd = 0;
        for (int i = 0; i < values.length; i ++) {
            if(values[i] > max) {
                max = values[i];
                maxInd = i;
            }
        }
        return maxInd;
    }
    
    public void testCountWordLengths() {
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource, counts);
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                System.out.println(i + ": " + counts[i]);
            }
        }
        System.out.println(indexOfMax(counts));
    }
    
}

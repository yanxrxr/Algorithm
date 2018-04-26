
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public int indexOf(String[] words, WordGram target, int start) {
        for (int i = start; i < words.length - target.length(); i ++) {
            WordGram wg = new WordGram(words, i, target.length());
            if (wg.equals(target)) {
                return i;
            }
        }
        return -1;
    }
    
    public ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;
        while (indexOf(myText, kGram, start) != -1) {
            int idx = indexOf(myText, kGram, start);
            follows.add(myText[idx+kGram.length()]);
            start = idx + 1;
        }
        return follows;
    }
        
    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key);
        sb.append(" ");
        for(int k = 0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }
    
    public void tester() {
        String source = "this is a test this is a test this is a test of words";
        myText = source.split("\\s+");
        String[] words = new String[3];
        words[0] = "is";
        words[1] = "a";
        words[2] = "test";
        WordGram wg = new WordGram(words, 0, 3);
        System.out.println(getFollows(wg));
    }
}
 
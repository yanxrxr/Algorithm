
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int order;

    public MarkovModel(int num) {
        myRandom = new Random();
        order = num;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - order);
        String key = myText.substring(index, index + order);
        sb.append(key);
        for(int k = 0; k < numChars - order; k++){
            ArrayList<String> follows = getFollows(key);
            index = myRandom.nextInt(follows.size());
            key = key.substring(1) + follows.get(index);
            sb.append(follows.get(index));
        }
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int where = 0;
        while (myText.indexOf(key, where) != -1) {
            int idx = myText.indexOf(key, where);
            if (idx < myText.length() - key.length()) {
                follows.add(myText.substring(idx+key.length(),idx+key.length()+1));              
            }
            where = idx + 1;
        }
        return follows;
    }
}

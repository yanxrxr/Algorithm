
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;

    public MarkovOne() {
        myRandom = new Random();
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
        int index = myRandom.nextInt(myText.length() - 1);
        String key = myText.substring(index, index + 1);
        sb.append(key);
        for(int k = 0; k < numChars - 1; k++){
            ArrayList<String> follows = getFollows(key);
            index = myRandom.nextInt(follows.size());
            key = follows.get(index);
            sb.append(key);
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

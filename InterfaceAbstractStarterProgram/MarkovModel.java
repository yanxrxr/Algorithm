
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel extends AbstractMarkovModel {
    private int order;

    public MarkovModel(int num) {
        myRandom = new Random();
        order = num;
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
    
    public String toString() {
        return "MarkovModel of order" + order;
    }
}

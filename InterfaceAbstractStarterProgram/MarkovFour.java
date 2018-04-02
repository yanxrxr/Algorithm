
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovFour extends AbstractMarkovModel {

    public MarkovFour() {
        myRandom = new Random();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 4);
        String key = myText.substring(index, index + 4);
        sb.append(key);
        for(int k = 0; k < numChars - 4; k++){
            ArrayList<String> follows = getFollows(key);
            index = myRandom.nextInt(follows.size());
            key = key.substring(1) + follows.get(index);
            sb.append(follows.get(index));
        }
        return sb.toString();
    }
    
    public String toString() {
        return "MarkovModel of order 4";
    }
}

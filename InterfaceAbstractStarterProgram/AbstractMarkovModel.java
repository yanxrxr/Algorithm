
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key) {
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
    
    abstract public String toString();

}

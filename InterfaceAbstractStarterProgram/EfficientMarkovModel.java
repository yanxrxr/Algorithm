
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int order;
    private HashMap<String, ArrayList<String>> keyMap;
    
    public EfficientMarkovModel(int num) {
        myRandom = new Random();
        order = num;
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        buildMap();
        printHashMapInfo(keyMap);
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - order);
        String key = myText.substring(index, index + order);
        sb.append(key);
        for(int k = 0; k < numChars - order; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows == null) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            key = key.substring(1) + follows.get(index);
            sb.append(follows.get(index));
        }
        return sb.toString();
    }
    
    public void buildMap() {
        keyMap = new HashMap<String, ArrayList<String>>();
        for (int i = 0; i < myText.length() - order; i ++) {
            String key = myText.substring(i, i + order);
            String ch = myText.substring(i + order, i + order + 1);
            if (keyMap.containsKey(key)) {
                ArrayList<String> follows = keyMap.get(key);
                follows.add(ch);
                keyMap.put(key, follows);
            }
            else {
                ArrayList<String> follows = new ArrayList<String>();
                follows.add(ch);
                keyMap.put(key, follows);
            }
        }
    }
    
    public ArrayList<String> getFollows(String key) {
        return keyMap.get(key);
    }
    
    public String toString() {
        return "MarkovModel of order" + order;
    }
    
    public void printHashMapInfo(HashMap<String, ArrayList<String>> keyMap) {
        int max = 0;
        for (String key: keyMap.keySet()) {
            int num = keyMap.get(key).size();
            if (num > max) {
                max = num;
            }
            System.out.println(key + ": " + keyMap.get(key));
        }
        System.out.println(max);
        for (String key: keyMap.keySet()) {
            if (keyMap.get(key).size() == max) {
                System.out.println(key);
            }
        }
        System.out.println(keyMap.size());
    }
}

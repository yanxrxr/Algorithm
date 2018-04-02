
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        characters = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    public void update(String person) {
        int ind = characters.indexOf(person);
        if (ind == -1) {
            characters.add(person);
            counts.add(1);
        }
        else {
            int ct = counts.get(ind);
            counts.set(ind, ct + 1);
        }
    }
    
    public void findAllCharacters() {
        FileResource fr = new FileResource();
        for (String line: fr.lines()) {
            for (int i = 0; i < line.length(); i ++) {
                if (!Character.isLetter(line.charAt(i)) && !Character.isWhitespace(line.charAt(i))) {
                    if(line.charAt(i) == '.') {
                        update(line.substring(0,i));
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < characters.size(); i ++) {
            if (counts.get(i) >= num1 && counts.get(i) <= num2) {
                System.out.println(characters.get(i) + " " + counts.get(i));
            }
        }
    }
    
    public void tester() {
        findAllCharacters();
        int max = 0;
        int maxInd = 0;
        for(int i = 0; i < characters.size(); i ++) {
            if (counts.get(i) > max) {
                max = counts.get(i);
                maxInd = i;
            }
        }
        System.out.println(characters.get(maxInd) + " " + counts.get(maxInd));
        charactersWithNumParts(10, 15);
    }
}

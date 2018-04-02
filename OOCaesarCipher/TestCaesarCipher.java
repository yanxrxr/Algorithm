
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {
    private int INDEX_OF_E = 4;
    
    public void countLetters(String message, int[] counts) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < message.length(); i ++) {
            if(Character.isLetter(message.charAt(i))) {
                int ind = alphabet.indexOf(Character.toLowerCase(message.charAt(i)));
                counts[ind] ++;
            }
        }
    }
    
    public int maxIndex(int[] values) {
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
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String contents = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(contents);
        System.out.println(encrypted);
        String decrypted = breakCaesarCipher(encrypted);
        System.out.println(decrypted);
    }
    
    public String breakCaesarCipher(String input) {
        int[] counts = new int[26];
        countLetters(input, counts);
        int maxInd = maxIndex(counts);
        int key = maxInd - INDEX_OF_E;
        if (key < 0) {
            key += 26;
        }
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(input);
    }
    
}


/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    
    public void countLetters(String message, int[] counts) {
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
    
    public String halfOfString(String message, int start) {
        String halfMessage = "";
        for (int i = start; i < message.length(); i += 2) {
            halfMessage += message.charAt(i);
        }
        return halfMessage;
    }
    
    public void SimpleTest() {
        FileResource fr = new FileResource();
        String contents = fr.asString();
        CaesarCipherTwo cc2 = new CaesarCipherTwo(17, 3);
        String encrypted = cc2.encrypt(contents);
        System.out.println(encrypted);
        String decrypted = breakCaesarCipher(encrypted);
        System.out.println(decrypted);
    }
    
    public String breakCaesarCipher(String input) {
        String evenHalf = halfOfString(input, 0);
        String oddHalf = halfOfString(input, 1);
        int[] counts1 = new int[26];
        int[] counts2 = new int[26];
        countLetters(evenHalf, counts1);
        countLetters(oddHalf, counts2);
        int maxInd1 = maxIndex(counts1);
        int maxInd2 = maxIndex(counts2);
        int key1 = maxInd1 - 4;
        int key2 = maxInd2 - 4;
        if (key1 < 0) {
            key1 += 26;
        }
        if (key2 < 0) {
            key2 += 26;
        }
        CaesarCipher cc1 = new CaesarCipher(key1);
        CaesarCipher cc2 = new CaesarCipher(key2);
        String decryptedEvenHalf = cc1.decrypt(evenHalf);
        String decryptedOddHalf = cc2.decrypt(oddHalf);
        String decrypted = "";
        for (int i = 0; i < input.length() / 2 + 1; i ++) {
            if (i < decryptedEvenHalf.length()) {
                decrypted += decryptedEvenHalf.charAt(i);
            }
            if (i < decryptedOddHalf.length()) {
                decrypted += decryptedOddHalf.charAt(i);
            }
        }
        return decrypted;
    }

}

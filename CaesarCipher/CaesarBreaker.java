
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    
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
    
    public String decrypt(String encrypted, int[] counts) {
        CaesarCipher cc = new CaesarCipher();
        int ind_e = alphabet.indexOf('e');
        int key = maxIndex(counts) - ind_e;
        if (key < 0) {
            key += 26;
        }
        String message = cc.encrypt(encrypted, 26 - key);
        return message;
    }
    
    public String halfOfString(String message, int start) {
        String halfMessage = "";
        for (int i = start; i < message.length(); i += 2) {
            halfMessage += message.charAt(i);
        }
        return halfMessage;
    }
    
    public int getKey(String s) {
        int[] counts = new int[26];
        countLetters(s, counts);
        int maxInd = maxIndex(counts);
        int ind_e = alphabet.indexOf('e');
        int key = maxInd - ind_e;
        if (key < 0) {
            key += 26;
        }
        return key;
    }
    
    public String decryptTwoKeys(String encrypted) {
        String evenHalf = halfOfString(encrypted, 0);
        String oddHalf = halfOfString(encrypted, 1);
        int key1 = getKey(evenHalf);
        int key2 = getKey(oddHalf);
        System.out.println("Key1 is " + key1 + ", Key2 is " + key2);
        CaesarCipher cc = new CaesarCipher();
        String decryptedEvenHalf = cc.encrypt(evenHalf, 26 - key1);
        String decryptedOddHalf = cc.encrypt(oddHalf, 26 - key2);
        String decrypted = "";
        for (int i = 0; i < encrypted.length() / 2 + 1; i ++) {
            if (i < decryptedEvenHalf.length()) {
                decrypted += decryptedEvenHalf.charAt(i);
            }
            if (i < decryptedOddHalf.length()) {
                decrypted += decryptedOddHalf.charAt(i);
            }
        }
        return decrypted;
    }
    
    public void test() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println(decryptTwoKeys(message));
        //System.out.println(halfOfString("Qbkm Zgis", 0));
        //System.out.println(halfOfString("Qbkm Zgis", 1));
    }

}

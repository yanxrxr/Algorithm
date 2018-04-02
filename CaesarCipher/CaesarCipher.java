
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder sb = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String encrypted = alphabet.substring(key) + alphabet.substring(0, key);
        alphabet += alphabet.toLowerCase();
        encrypted += encrypted.toLowerCase();
        for (int i = 0; i < input.length(); i ++) {
            char currChar = sb.charAt(i);
            if (encrypted.indexOf(currChar) != -1) {
                sb.setCharAt(i, encrypted.charAt(alphabet.indexOf(currChar)));
            }
        }
        return sb.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder sb = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String encrypted1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String encrypted2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        alphabet += alphabet.toLowerCase();
        encrypted1 += encrypted1.toLowerCase();
        encrypted2 += encrypted2.toLowerCase();
        for(int i = 0; i < input.length(); i += 2) {
            char currChar = sb.charAt(i);
            if (encrypted1.indexOf(currChar) != -1) {
                sb.setCharAt(i, encrypted1.charAt(alphabet.indexOf(currChar)));
            }
        }
        for(int i = 1; i < input.length(); i += 2) {
            char currChar = sb.charAt(i);
            if (encrypted2.indexOf(currChar) != -1) {
                sb.setCharAt(i, encrypted2.charAt(alphabet.indexOf(currChar)));
            }
        }
        return sb.toString();
    }
    
    public void testCaesar() {
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        int key = 17;
        String encrypted = encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 24, 6);
        System.out.println("key is " + key + "\n" + encrypted);
    }

}

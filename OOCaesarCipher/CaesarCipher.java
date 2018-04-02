
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }

    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i ++) {
            char upper = Character.toUpperCase(sb.charAt(i));
            int ind = alphabet.indexOf(upper);
            if (ind != -1) {
                if (upper != sb.charAt(i)) {
                    sb.setCharAt(i, Character.toLowerCase(shiftedAlphabet.charAt(ind)));
                }
                else {
                    sb.setCharAt(i, shiftedAlphabet.charAt(ind));
                }
            }
        }
        return sb.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}

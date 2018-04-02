
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i ++) {
            char upper = Character.toUpperCase(sb.charAt(i));
            int ind = alphabet.indexOf(upper);
            if (ind != -1) {
                if ( i % 2 == 0) {
                    if (upper != sb.charAt(i)) {
                        sb.setCharAt(i, Character.toLowerCase(shiftedAlphabet1.charAt(ind)));
                    }
                    else {
                        sb.setCharAt(i, shiftedAlphabet1.charAt(ind));
                    }
                }
                else {
                    if (upper != sb.charAt(i)) {
                        sb.setCharAt(i, Character.toLowerCase(shiftedAlphabet2.charAt(ind)));
                    }
                    else {
                        sb.setCharAt(i, shiftedAlphabet2.charAt(ind));
                    }
                }
            }
        }
        return sb.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipherTwo cc2 = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cc2.encrypt(input);
    }

}

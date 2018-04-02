
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class WordPlay {
    public boolean isVowel(char ch) {
        String vowel = "aeiouAEIOU";
        if (vowel.indexOf(ch) == -1) {
            return false;
        }
        return true;
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0;  i < phrase.length(); i ++) {
            if(isVowel(sb.charAt(i))){
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        char ch1;
        if (Character.isUpperCase(ch)) {
            ch1 = Character.toLowerCase(ch);
        }
        else {
            ch1 = Character.toUpperCase(ch);
        }
        
        for (int i = 0; i < phrase.length(); i ++) {
            if (sb.charAt(i) == ch || sb.charAt(i) == ch1) {
                if (i % 2 == 0) { 
                    sb.setCharAt(i, '*');
                }
                else {
                    sb.setCharAt(i, '+');
                }
            }
        }
        return sb.toString();
    }
    
    public void test() {
        //System.out.println(isVowel('F'));
        //System.out.println(isVowel('a'));
        //System.out.println(replaceVowels("Hello World", '*'));
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }

}

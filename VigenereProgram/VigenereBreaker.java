import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        for (int i = 0; i < klength; i ++) {
            String slice = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(slice);
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dic = new HashSet<String>();
        for (String word: fr.lines()) {
            dic.add(word.toLowerCase());
        }
        return dic;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        String[] words = message.split("\\W+");
        int ct = 0;
        for (int i = 0; i < words.length; i++) {
            if (dictionary.contains(words[i].toLowerCase())) {
                ct++;
            }
        }
        return ct;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int max = 0;
        String answer = "";
        int keyLength = 0;
        char mostCommon = mostCommonCharln(dictionary);
        for (int i = 1; i < 100; i ++) {
            int[] key = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int ct = countWords(decrypted, dictionary);
            if (ct > max) {
                max = ct;
                answer = decrypted;
                keyLength = key.length;
            }
        }
        return answer;
    }
    
    public char mostCommonCharln(HashSet<String> dictionary) {
        HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
        for (String word: dictionary) {
            word = word.toLowerCase();
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!charMap.containsKey(ch)) {
                    charMap.put(ch, 1);
                }
                else {
                    charMap.put(ch, charMap.get(ch) + 1);
                }
            }
        }
        int max = 0;
        char mostChar = ' ';
        for (char ch: charMap.keySet()) {
            if (charMap.get(ch) > max) {
                max = charMap.get(ch);
                mostChar = ch;
            }
        }
        return mostChar;
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int max = 0;
        String realDecrypted = "";
        String realLan = "";
        for (String lan: languages.keySet()) { 
            HashSet<String> dictionary = languages.get(lan);
            String decrypted = breakForLanguage(encrypted, dictionary);
            int ct = countWords(encrypted, dictionary);
            if (ct > max) {
                max = ct;
                realDecrypted = decrypted;
                realLan = lan;
            }
        }
        System.out.println(realLan);
        System.out.println(realDecrypted);
        return realDecrypted;
    }
    
    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String message = fr.asString();
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        for (File f: dr.selectedFiles()) {
            FileResource dic = new FileResource(f);
            HashSet<String> dictionary = readDictionary(dic);
            languages.put(f.getName(), dictionary);
        }
        String decrypted = breakForAllLangs(message, languages);
        System.out.println(decrypted.substring(0,200));
    }
    
}

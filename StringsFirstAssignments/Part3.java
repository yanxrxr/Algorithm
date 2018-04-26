
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int index1 = stringb.indexOf(stringa);
        if(index1 == -1) {
            return false;
        }
        int index2 = stringb.indexOf(stringa, index1 + stringa.length());
        if(index2 == -1) {
            return false;
        }
        return true;
    }
    
    public String lastPart(String stringa, String stringb) {
        int index = stringb.indexOf(stringa);
        if(index == -1) {
            return stringb;
        }
        return stringb.substring(index + stringa.length());
    }
    
    public void testing() {
        System.out.println(twoOccurrences("atg", "ctgtatgta"));
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("an", "banana"));
        System.out.println(lastPart("an", "banana")); 
        System.out.println(lastPart("zoo", "forest"));
    }
    
    public static void main (String[] args) {
        Part3 pr = new Part3();
        pr.testing();
    }
}

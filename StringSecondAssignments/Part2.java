
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int startIndex = 0;
        int i = 0;
        while(true) {
            int index = stringb.indexOf(stringa, startIndex);
            if (index != -1) {
                i++;
            }else{
                break;
            }
            startIndex = index + stringa.length();
        }
        return i;
    }
    
    public void testHowMany() {
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println(howMany("AA", "ATAAAA"));
        System.out.println(howMany("AA", "JDIJFOIEJ"));
    }

}

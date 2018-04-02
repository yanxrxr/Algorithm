
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part4 {
    public void findLinks() {
        URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String s: url.lines()) {
            String sLower = s.toLowerCase();
            int ind = sLower.indexOf("youtube.com");
            if(ind != -1) {
                int i1 = s.lastIndexOf("\"", ind);
                int i2 = s.indexOf("\"", ind);
                String link = s.substring(i1 + 1, i2);
                System.out.println(link);
            }
        }
    }
    
    public static void main (String[] args) {
        Part4 pr = new Part4();
        pr.findLinks();
    }

}

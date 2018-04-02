
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class Tester {
    public void testGetFollows() {
        MarkovOne mo = new MarkovOne();
        mo.setTraining("this is a test yes this is a test");
        System.out.println(mo.getFollows("t"));
        System.out.println(mo.getFollows("e"));
        System.out.println(mo.getFollows("es"));
    }
    
    public void testGetFolloewsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("th");
        System.out.println(follows.size());
    }

}

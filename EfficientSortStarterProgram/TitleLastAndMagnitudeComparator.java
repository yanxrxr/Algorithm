
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String[] w1 = q1.getInfo().split("\\W+");
        String[] w2 = q2.getInfo().split("\\W+");
        if (w1[w1.length-1].equals(w2[w2.length-1])) {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return w1[w1.length-1].compareTo(w2[w2.length-1]);
    }

}

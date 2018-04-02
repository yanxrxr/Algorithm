
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        //int maxInd = indexOfLargest(list);
        //System.out.println(maxInd);
        //System.out.println(list.get(maxInd).getMagnitude());
        ArrayList<QuakeEntry> largest = getLargest(list, 5);
        for (int i = 0; i < largest.size(); i ++) {
            System.out.println(largest.get(i));
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int maxInd = 0;
        for (int i = 1; i < data.size(); i ++) {
            if (data.get(i).getMagnitude() > data.get(maxInd).getMagnitude()) {
                maxInd = i;
            }
        }
        return maxInd;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        for (int i = 0; i < howMany; i ++) {
            int maxInd = indexOfLargest(copy);
            answer.add(copy.get(maxInd));
            copy.remove(maxInd);
        }
        return answer;
    }

}

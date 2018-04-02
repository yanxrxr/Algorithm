
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location loc;
    private int maxDist;
    
    public DistanceFilter(Location city, int dist) {
        loc = city;
        maxDist = dist;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return (loc.distanceTo(qe.getLocation()) < maxDist);
    }
    
    public String getName() {
        return "Distance";
    }
}

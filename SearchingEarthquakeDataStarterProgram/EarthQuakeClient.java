import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe: quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe: quakeData) {
            Location to = qe.getLocation();
            //System.out.println(from.distanceTo(to));
            if (from.distanceTo(to) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe: quakeData) {
            double depth = qe.getDepth();
            if (depth > minDepth && depth < maxDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe: quakeData) {
            String title = qe.getInfo();
            if (where.equals("start")) {
                if (title.startsWith(phrase)) {
                    answer.add(qe);
                }
            }
            else if (where.equals("end")) {
                if (title.endsWith(phrase)) {
                    answer.add(qe);
                }
            }
            else if (where.equals("any")) {
                if (title.indexOf(phrase) != -1) {
                    answer.add(qe);
                }
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> filtered = filterByMagnitude(list, 5.0);
        for (int i = 0; i < filtered.size(); i ++) {
            System.out.println(filtered.get(i));
        }
        System.out.println("Found " + filtered.size() + " quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> filtered =  filterByDistanceFrom(list, 1000000, city);
        for (int i = 0; i < filtered.size(); i ++) {
            QuakeEntry qe = filtered.get(i);
            System.out.print(qe.getLocation().distanceTo(city));
            System.out.println(" " + qe.getInfo());
        }
        System.out.println("Found " + filtered.size() + " quakes that match that criteria");
    }
    
    public void quaksOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        double minDepth = -8000.0;
        double maxDepth = -5000.0;
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        ArrayList<QuakeEntry> filtered =  filterByDepth(list, minDepth, maxDepth);
        for (int i = 0; i < filtered.size(); i ++) {
            System.out.println(filtered.get(i));
        }
        System.out.println("Found " + filtered.size() + " quakes that match that criteria");
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> filtered =  filterByPhrase(list, "any", "Creek");
        for (int i = 0; i < filtered.size(); i ++) {
            System.out.println(filtered.get(i));
        }
        System.out.println("Found " + filtered.size() + " quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}

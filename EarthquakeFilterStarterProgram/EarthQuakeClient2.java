import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        }
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MinMagFilter(4.0); 
        //ArrayList<QuakeEntry> m7  = filter(list, f); 
        //for (QuakeEntry qe: m7) { 
        //    System.out.println(qe);
        //}
        
        // Filter magF = new MagnitudeFilter(4.0, 5.0);
        // ArrayList<QuakeEntry> results = filter(list, magF);
        // Filter depF = new DepthFilter(-35000.0, -12000.0);
        // results = filter(results, depF);
        // for (QuakeEntry qe: results) { 
            // System.out.println(qe);
        // }
        
        Location Tokyo = new Location(35.42, 139.43);
        Filter distF = new DistanceFilter(Tokyo, 10000000);
        Filter phraF = new PhraseFilter("end", "Japan");
        ArrayList<QuakeEntry> results = filter(list, distF);
        results = filter(results, phraF);
        for (QuakeEntry qe: results) { 
            System.out.println(qe);
        }
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0, 2.0);
        Filter f2 = new DepthFilter(-100000.0, -10000.0);
        Filter f3 = new PhraseFilter("any", "a");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> results = filter(list, maf);
        for (QuakeEntry qe: results) { 
            System.out.println(qe);
        }
        System.out.println(results.size());
        System.out.println(maf.getName());
    }
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        Location Tulsa = new Location(36.1314, -95.9372);
        Location Tokyo = new Location(35.42, 139.43);
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0, 3.0);
        //Filter f2 = new DepthFilter(-35000.0, -12000.0);
        Filter f2 = new DistanceFilter(Tulsa, 10000000);
        Filter f3 = new PhraseFilter("any", "Ca");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> results = filter(list, maf);
        for (QuakeEntry qe: results) { 
            System.out.println(qe);
        }
        System.out.println(results.size());
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}

/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        // complete constructor
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        // complete method
        FileResource fr = new FileResource(filename);
        for (String line: fr.lines()) {
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le: records) {
            String ip = le.getIpAddress();
            if(!uniqueIPs.contains(ip)) {
                uniqueIPs.add(ip);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num) {
        for (LogEntry le: records) {
            int status = le.getStatusCode();
            if (status > num) {
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le: records) {
            String time = le.getAccessTime().toString();
            String ip = le.getIpAddress();
            if (time.indexOf(someday) != -1) {
                if (!uniqueIPs.contains(ip)) {
                    uniqueIPs.add(le.getIpAddress());
                }
            }
        }
        return uniqueIPs;
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le: records) {
            int status = le.getStatusCode();
            String ip = le.getIpAddress();
            if (status >= low && status <= high) {
                if (!uniqueIPs.contains(ip)) {
                    uniqueIPs.add(le.getIpAddress());
                }
            }
        }
        return uniqueIPs.size();
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le: records) {
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            }
            else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int max = 0;
        for (int ct: counts.values()) {
            if (ct > max) {
                max = ct;
            }
        }
        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        ArrayList<String> IPs = new ArrayList<String>();
        int maxVisits = mostNumberVisitsByIP(counts);
        for (String ip: counts.keySet()) {
            if (counts.get(ip) == maxVisits) {
                IPs.add(ip);
            }
        }
        return IPs;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> IpDays = new HashMap<String, ArrayList<String>>();
        for (LogEntry le: records) {
            String day = le.getAccessTime().toString().substring(4, 10);
            if (!IpDays.containsKey(day)) {
                ArrayList<String> IPs = new ArrayList<String>();
                IPs.add(le.getIpAddress());
                IpDays.put(day, IPs);
            }
            else {
                ArrayList<String> IPs = IpDays.get(day);
                IPs.add(le.getIpAddress());
                IpDays.put(day,IPs);
            }
        }
        return IpDays;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> IpDays) {
        int max = 0;
        String maxDay = "";
        for (String day: IpDays.keySet()) {
            if (IpDays.get(day).size() > max) {
                max = IpDays.get(day).size();
                maxDay = day;
            }
        }
        return maxDay;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> IpDays, String day) {
        ArrayList<String> ips  = IpDays.get(day);
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (String ip: ips) {
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            }
            else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return iPsMostVisits(counts);
    }
    
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

}

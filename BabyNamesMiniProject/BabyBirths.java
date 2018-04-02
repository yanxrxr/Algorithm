
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void totalBirths() {
        FileResource fr = new FileResource();
        int totalNames = 0;
        int girlNames = 0;
        int boyNames = 0;
        for (CSVRecord record: fr.getCSVParser(false)) {
            totalNames ++;
            if (record.get(1).equals("F")) {
                girlNames ++;
            }
            else {
                boyNames ++;
            }
        }
        System.out.println("The total number of names = " + totalNames);
        System.out.println("The number of girls names = " + girlNames);
        System.out.println("The number of boys names = " + boyNames);
    }
    
    public int getRank(int year, String name, String gender) {
        int rank = 0;
        int flag = 0;
        FileResource fr = new FileResource();
        for (CSVRecord record: fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                rank ++;
                if (record.get(0).equals(name)) {
                    flag = 1;
                    break;
                }
            }
        }
        if (flag == 0) {
            return -1;
        }
        return rank;
    }
    
    public String getName(int year, int rank, String gender) {
        int ct = 0;
        FileResource fr = new FileResource();
        for (CSVRecord record: fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                ct ++;
                if (ct == rank) {
                    return record.get(0);
                }
            }
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        if (gender == "M") {
            gender = "he";
        }else {
            gender = "she";
        }
        System.out.println(name + " born in " + year + " would be " + newName + " if " + gender + " was born in " + newYear);
    }
    
    public void test() {
        //System.out.println(getName(2012, 100, "M"));
        whatIsNameInYear("Isabella", 2012, 2014, "F");
    }

}

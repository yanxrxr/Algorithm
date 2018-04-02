
/**
 * Write a description of ParseWeather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ParseWeather {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord minRecord = null;
        for (CSVRecord record: parser) {
            if (minRecord == null) {
                minRecord = record;
            }
            else {
                double coldestTemp = Double.parseDouble(minRecord.get("TemperatureF"));
                if (Double.parseDouble(record.get("TemperatureF")) < coldestTemp) {
                    minRecord = record;
                }
            }
        }
        return minRecord;
    }
    
    public String fileWithColdestTemperature() {
        DirectoryResource dr  = new DirectoryResource();
        CSVRecord coldestRecord = null;
        File coldestFile = null;
        for(File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            if (coldestRecord == null) {
                coldestRecord = current;
                coldestFile = f;
            }
            else {
                double coldestTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));
                double currentTemp = Double.parseDouble(current.get("TemperatureF"));
                if(currentTemp < coldestTemp && currentTemp != -9999) {
                    coldestRecord = current;
                    coldestFile = f;
                }
            }
        }
        return coldestFile.getName();
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord minRecord = null;
        for (CSVRecord record: parser) {
            if (minRecord == null) {
                minRecord = record;
            }
            else {
                if (record.get("Humidity").matches("N/A") == false) {
                    double lowestHum = Double.parseDouble(minRecord.get("Humidity"));
                    if (Integer.parseInt(record.get("Humidity")) < lowestHum) {
                        minRecord = record;
                    }
                }
            }
        }
        return minRecord;
    }
    
    public String fileWithLowestHumidity() {
        DirectoryResource dr  = new DirectoryResource();
        CSVRecord lowestRecord = null;
        File coldestFile = null;
        for(File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            if (lowestRecord == null) {
                lowestRecord = current;
                coldestFile = f;
            }
            else {
                double coldestHum = Integer.parseInt(lowestRecord.get("Humidity"));
                if (current.get("Humidity").matches("N/A")) {
                    break;
                }
                double currentHum = Integer.parseInt(current.get("Humidity"));
                if(currentHum < coldestHum) {
                    lowestRecord = current;
                    coldestFile = f;
                }
            }
        }
        return coldestFile.getName();
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        int num = 0;
        double sum = 0;
        for (CSVRecord record: parser) {
            sum += Double.parseDouble(record.get("TemperatureF"));
            num ++;
        }
        return sum/num;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        int num = 0;
        double sum = 0;
        for (CSVRecord record: parser) {
            int humi = Integer.parseInt(record.get("Humidity"));
            if (humi >= value) {
                sum += Double.parseDouble(record.get("TemperatureF"));
                num ++;
            }
        }
        if (num == 0) {
            return -1;
        }else {
            return sum/num;
        }
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double ave = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (ave == -1) {
            System.out.println("No temperatures with that humidity");
        }
        else {
            System.out.println("Average Temp when high Humidity is " + ave);
        }
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temprature in file is " + averageTemperatureInFile(parser));
    }
    
    public void testFileWithLowestHumidity() {
        String file = fileWithLowestHumidity();
        System.out.println("Lowest day was in file " + file);
        FileResource fr = new FileResource("nc_weather/2014/" + file);
        CSVRecord lowest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest humidity on that day was " + lowest.get("Humidity"));
        System.out.println("Time: " + lowest.get("DateUTC"));
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public void testFileWithColdestTemperature() {
         String file = fileWithColdestTemperature();
         System.out.println("Coldest day was in file " + file);
         FileResource fr = new FileResource("nc_weather/2014/" + file);
         CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
         System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
         System.out.println("All the Temperature on the coldest day were:");
         for (CSVRecord record: fr.getCSVParser()) {
             System.out.print(record.get("DateUTC") + ": ");
             System.out.println(record.get("TemperatureF"));
         }
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest: " + coldest.get("TemperatureF"));
    }

}

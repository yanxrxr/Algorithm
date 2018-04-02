
/**
 * Write a description of WhichCountriesExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Nauru"));
        //listExportersTwoProducts(parser, "gold", "diamonds");
        bigExporters(parser, "$999,999,999,999");
        //int i = 0;
        //for (CSVRecord record: parser) {
            //if (record.get("Exports").contains("gold")) {
                //i++;
            //}
        //}
        //System.out.println(i);
        
    }
    
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record: parser) {
            if (record.get("Country").matches(country)) {
                return (country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)"));
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record: parser) {
            if (record.get("Exports").contains(exportItem1) 
                && record.get("Exports").contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record: parser) {
            if (record.get("Value (dollars)").length() > amount.length()) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }

}

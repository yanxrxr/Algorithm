
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String endCodon) {
        String dna1 = dna.toUpperCase();
        int startIndex = dna1.indexOf(startCodon);
        if (startIndex == -1) {
            return "";
        }
        int endIndex = dna1.indexOf(endCodon, startIndex + 3);
        if (endIndex == -1) {
            return "";
        }
        String gene = dna.substring(startIndex, endIndex + 3);
        return gene;
    }
    
    public void testSimpleString() {
        String dna = "ATATGCAGTAA";
        String gene = findSimpleGene(dna,"TAT", "GTA");
        System.out.println("Gene: " + gene);
        dna = "atgtatctgta";
        gene = findSimpleGene(dna, "TAT", "GTA");
        System.out.println("Gene: " + gene);
        dna = "ATTAACGATAA";
        gene = findSimpleGene(dna, "TAT", "GTA");
        System.out.println("Gene: " + gene);
    }
    
    public static void main (String[] args) {
        Part2 pr = new Part2();
        pr.testSimpleString();
    }

}

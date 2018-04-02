
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int endIndex = dna.indexOf("TAA", startIndex + 3);
        if (endIndex == -1) {
            return "";
        }
        String gene = dna.substring(startIndex, endIndex + 3);
        return gene;
    }
    
    public void testSimpleString() {
        String dna = "ATATGCAGTAA";
        String gene = findSimpleGene(dna);
        System.out.println("Gene: " + gene);
        dna = "ATATGCAGTAG";
        gene = findSimpleGene(dna);
        System.out.println("Gene: " + gene);
        dna = "ATTAACGATAA";
        gene = findSimpleGene(dna);
        System.out.println("Gene: " + gene);
    }
    
    public static void main (String[] args) {
        Part1 pr = new Part1();
        pr.testSimpleString();
    }

}

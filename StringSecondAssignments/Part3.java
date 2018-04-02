
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int firstIndex = dna.indexOf(stopCodon, startIndex);
        if (firstIndex != -1) {
            if ((firstIndex - startIndex) % 3 == 0) {
                return firstIndex;
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna, int startIndex) {
        if(startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, tagIndex);
        minIndex = Math.min(minIndex, tgaIndex);
        if(minIndex == dna.length()) {
            return "";
        }else {
            return dna.substring(startIndex, minIndex + 3);
        } 
    }
    
    public void testFindGene() {
        String dna = "AATGCTAACTAGCTGACTAAT";
        printAllGenes(dna);
        //System.out.println("Gene is: " + findGene(dna, dna.indexOf("ATG")));
    }
    
    public void printAllGenes(String dna) {
        int startIndex = dna.indexOf("ATG");
        while(true) {
            String gene = findGene(dna, startIndex);
            if(gene.isEmpty()) {
                break;
            }
            System.out.println("Gene: " + gene);
            startIndex = dna.indexOf("ATG", dna.indexOf(gene) + gene.length());
        }
    }
    
    public int countGenes(String dna) {
        int startIndex = dna.indexOf("ATG");
        int i = 0;
        while(true) {
            String gene = findGene(dna, startIndex);
            if(gene.isEmpty()) {
                break;
            }
            i++;
            startIndex = dna.indexOf("ATG", dna.indexOf(gene) + gene.length());
        }
        return i;
    }
    
    public void testCountGenes() {
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
    }
}

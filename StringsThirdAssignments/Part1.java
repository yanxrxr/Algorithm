
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part1 {
    FileResource fr = new FileResource("brca1line.fa");
    String dna = fr.asString().toUpperCase();
    
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex);
        //System.out.println(stopIndex);
        while(stopIndex != -1) {
            if ((stopIndex - startIndex) % 3 == 0) {
                return stopIndex;
            }else {
                stopIndex = dna.indexOf(stopCodon, stopIndex+3);
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
        //System.out.println(taaIndex + " " + tagIndex + " " + tgaIndex);
        int minIndex = Math.min(taaIndex, tagIndex);
        minIndex = Math.min(minIndex, tgaIndex);
        if(minIndex == dna.length()) {
            return "";
        }else {
            return dna.substring(startIndex, minIndex + 3);
        }
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
    
    public StorageResource getAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        int startIndex = dna.indexOf("ATG");
        while(true) {
            String gene = findGene(dna, startIndex);
            if(gene.isEmpty()) {
                break;
            }
            sr.add(gene);
            startIndex = dna.indexOf("ATG", dna.indexOf(gene) + gene.length());
            System.out.println("Start Index: " + startIndex);
        }
        return sr;
    }
    
    public void testFindStopCodon() {
        //String dna = "CTATGCGTAATATAG";
        System.out.println(findStopCodon(dna, dna.indexOf("ATG"), "TAA"));
        System.out.println(findStopCodon(dna, dna.indexOf("ATG"), "TAG"));
    }
    
    public void testFindGene() {
        //String dna = "TGATGATTTAACTAGCCATGTAA";
        printAllGenes(dna);
        //System.out.println("Gene is: " + findGene(dna, dna.indexOf("ATG")));
    }
    
    public void testGetAllGenes() {
        String dna = "TGATGATTTAACTAGCCATGTAA";
        StorageResource sr = getAllGenes(dna);
        for(String s: sr.data()) {
            System.out.println(s);
        }
    }
    
    public int howMany(String stringa, String stringb) {
        int startIndex = 0;
        int i = 0;
        while(true) {
            int index = stringb.indexOf(stringa, startIndex);
            if (index != -1) {
                i++;
            }else{
                break;
            }
            startIndex = index + stringa.length();
        }
        return i;
    }
    
    public double cgRatio(String dna) {
        int cct = howMany("C", dna);
        int gct = howMany("G", dna);
        double r = (float)(cct + gct) / dna.length();
        return r;
    }
    
    public void testCgRatio() {
        String dna = "ATGCCATAG";
        System.out.println(cgRatio(dna));
    }
    
    public int countCTG(String dna) {
        int ct = 0;
        int index = dna.indexOf("CTG");
        while (index != -1) {
            index = dna.indexOf("CTG", index+3);
            ct ++;
        }
        return ct;
    }
    
    public void testCountCTG() {
        String dna = "CTGCTCT";
        System.out.println(countCTG(dna));
    }
    
    public void processGenes(StorageResource sr) {
        int lct = 0;
        int rct = 0;
        int maxL = 0;
        String maxS = "";
        for (String s: sr.data()) {
            int l = s.length();
            double r = cgRatio(s);
            if (l > 60) {
                System.out.println("Strings longer than 960: " + s);
                lct ++;
            }
            if (r > 0.35) {
                System.out.println("Strings C-G-ratio higher than 0.35: " + s);
                rct ++;
            }
            if (l > maxL) {
                maxL = l;
                maxS = s;
            }
        }
        System.out.println("Number of Stings longer than 60: " + lct);
        System.out.println("Number of Stings C-G-ratio higher than 0.35: " + rct);
        System.out.println("Longest gene: " + maxS);
    }
    
    public void testProcesGenes() {
        //String gene = findGene(dna, 0);
        //System.out.println(gene);
        //System.out.println(findStopCodon(dna, dna.indexOf("ATG"), "TAG"));
        //System.out.println(dna.indexOf("TAG", 42));
        StorageResource sr = new StorageResource();
        sr = getAllGenes(dna);
        System.out.println(sr.size());
        for (String S: sr.data()) {
            System.out.println(S.length());
        }
        processGenes(sr);
    }

}


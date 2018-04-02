
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String req;
    private String phrase;
    
    public PhraseFilter(String where, String word) {
        req = where;
        phrase = word;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if (req.equals("start")) {
            return qe.getInfo().startsWith(phrase);
        }
        else if (req.equals("end")) {
            return qe.getInfo().endsWith(phrase);
        }
        else if (req.equals("any")) {
            return (qe.getInfo().indexOf(phrase) != -1);
        }
        return false;
    }
    
    public String getName() {
        return "Phrase";
    }
}

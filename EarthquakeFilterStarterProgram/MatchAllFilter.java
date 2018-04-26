
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    
    public MatchAllFilter() {
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f) {
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe) {
        for (Filter f: filters) {
            if (!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }
    
    public String getName() {
        StringBuilder sb = new StringBuilder();
        for (Filter f: filters) {
            sb.append(f.getName() + " ");
        }
        return sb.toString();
    }
}

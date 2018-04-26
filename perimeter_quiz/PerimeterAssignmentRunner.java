import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int n = getNumPoints(s);
        System.out.println("Number of Points = " + n);
        double ave = getAverageLength(s);
        System.out.println("Average Length = " + ave);
        double largest = getLargestSide(s);
        System.out.println("Largest side = " + largest);
        double largestX = getLargestX(s);
        System.out.println("Largest X = " + largestX);
    }
    
    public int getNumPoints (Shape s) {
        // Put code here
        int n = 0;
        for(Point p: s.getPoints()) {
            n++;
        }
        return n;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        int n = getNumPoints(s);
        double p = getPerimeter(s);
        double ave = p / n;
        return ave;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largest = 0.0;
        Point prevPt = s.getLastPoint();
        for(Point currPt: s.getPoints()) {
            double length = currPt.distance(prevPt);
            if(length > largest) {
                largest = length;
            }
        }
        return largest;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0.0;
        for(Point currPt: s.getPoints()) {
            double x = currPt.getX();
            if (x > largestX) {
                largestX = x;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestP = 0.0;
        for(File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double p = getPerimeter(s);
            System.out.println(p);
            if (p > largestP) { 
                largestP = p;
            }
        }
        return largestP;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        File temp = null;    // replace this code    
        double largestP = 0.0;
        for(File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double p = getPerimeter(s);
            if (p > largestP) { 
                largestP = p; 
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestP = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter = " + largestP);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String f = getFileWithLargestPerimeter();
        System.out.println("File with Largest Perimeter: " + f); 
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}

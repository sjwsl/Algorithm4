import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

public class FastCollinearPoints {
    LineSegment[] segments;


    private ArrayList<LineSegment> collinear(Point[] points, int index) {
        ArrayList<LineSegment> segments = new ArrayList<LineSegment>();

    }

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
        for (int i = 0; i < points.length; i++) {
            segments.addAll(collinear(points, i));
        }
    }

    // the number of line segments
    public int numberOfSegments() {

        return segments.length;
    }

    // the number of line segments
    public LineSegment[] segments() {

        return segments;
    }

    public static void main(String[] args) {

    }

}
import java.util.Arrays;
import java.util.ArrayList;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

public class BruteCollinearPoints {

    private LineSegment[] segments;

    private boolean collinear(Point a, Point b, Point c) {
        return a.slopeTo(b) == a.slopeTo(c);
    }

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++)
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].toString() == points[j].toString()) throw new IllegalArgumentException();
            }

        Arrays.sort(points);
        ArrayList<LineSegment> segmentsList=new ArrayList<LineSegment>();


        for (int i = 0; i < points.length; i++)
            for (int j = i + 1; j < points.length; j++)
                for (int k = j + 1; k < points.length; k++)
                    for (int l = k + 1; l < points.length; l++) {
                        if (collinear(points[i], points[j], points[k]) && collinear(points[i], points[k], points[l])) {
                            segmentsList.add(new LineSegment(points[i], points[l]));
                        }
                    }
        segments=segmentsList.toArray(new LineSegment[segmentsList.size()]);
    }

    // the number of line segments
    public int numberOfSegments() {
        LineSegment[] copySegments=segments;
        return copySegments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] copySegments=segments;
        return copySegments;
    }

    public static void main(String[] args) {

        // read the n points from a file
        int n = StdIn.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        StdOut.println(points[0].slopeTo(points[4]));

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
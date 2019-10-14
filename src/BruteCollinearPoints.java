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
                if (points[i].slopeTo(points[j])==Double.NEGATIVE_INFINITY) throw new IllegalArgumentException();
            }


        Point[] copyPoints= Arrays.copyOf(points,points.length);
        Arrays.sort(copyPoints);

        ArrayList<LineSegment> segmentsList=new ArrayList<LineSegment>();


        for (int i = 0; i < copyPoints.length; i++)
            for (int j = i + 1; j < copyPoints.length; j++)
                for (int k = j + 1; k < copyPoints.length; k++)
                    for (int l = k + 1; l < copyPoints.length; l++) {
                        if (collinear(copyPoints[i], copyPoints[j], copyPoints[k]) && collinear(copyPoints[i], copyPoints[k], copyPoints[l])) {
                            segmentsList.add(new LineSegment(copyPoints[i],copyPoints[l]));
                        }
                    }
        segments=segmentsList.toArray(new LineSegment[segmentsList.size()]);
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(segments,segments.length);
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
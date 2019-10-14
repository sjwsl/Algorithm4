import java.util.Arrays;
import java.util.ArrayList;
import java.util.Objects;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

public class FastCollinearPoints {
    private LineSegment[] segments;
    private ArrayList<String> visited = new ArrayList<String>();

    private ArrayList<LineSegment> collinear(Point[] ppoints, int center) {
        ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
        Point[] points=new Point[ppoints.length-center-1];
        for(int i=center+1;i<ppoints.length;i++){
            points[i-center-1]=ppoints[i];
        }

        Arrays.sort(points, ppoints[center].slopeOrder());
        for (int i = 0; i < points.length; i++) {
            double nowSlope = points[i].slopeTo(ppoints[center]);
            int last = i;
            while (last + 1 < points.length && points[last + 1].slopeTo(ppoints[center]) == nowSlope) ++last;
            if (last - i + 1 >= 3) {
                ArrayList<Point> sameSlopePoints = new ArrayList<Point>();
                sameSlopePoints.add(ppoints[center]);
                for (int j = i; j <= last; j++) sameSlopePoints.add(points[j]);
                Point[] sameSlopePointsArray = sameSlopePoints.toArray(new Point[sameSlopePoints.size()]);
                Arrays.sort(sameSlopePointsArray);
                LineSegment now=new LineSegment(sameSlopePointsArray[0], sameSlopePointsArray[sameSlopePointsArray.length - 1]);
                if(!visited.contains(now.toString())){
                    segments.add(now);
                    visited.add(now.toString());
                }
            }
            i = last;
        }
        return segments;
    }

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {

        if (points == null) throw new IllegalArgumentException();

        for (int i = 0; i + 3 < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++)
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].toString().equals(points[j].toString())) throw new IllegalArgumentException();
            }

        ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
        for (int i = 0; i < points.length; i++) {
            segments.addAll(collinear(points, i));
        }


        this.segments = segments.toArray(new LineSegment[segments.size()]);

    }

    // the number of line segments
    public int numberOfSegments() {
        LineSegment[] copySegments=segments.clone();
        return copySegments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] copySegments=segments.clone();
        return copySegments;
    }
    public static void main(String[] args) {
        String a=new String("aa");
        String b=new String("bb");
        StdOut.println(a==b);


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

        //StdOut.println(points[0].slopeTo(points[4]));

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }

        StdDraw.show();
    }

}
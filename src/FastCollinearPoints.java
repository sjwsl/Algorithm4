import java.util.Arrays;
import java.util.ArrayList;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

public class FastCollinearPoints {
    private LineSegment[] segments;
    //private ArrayList<String> visited = new ArrayList<String>();

    private ArrayList<LineSegment> collinear(Point[] points, int center,ArrayList<Point> pointa,ArrayList<Point> pointb) {
        ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
        Point[] copypoints = Arrays.copyOf(points,points.length);

        Arrays.sort(copypoints, points[center].slopeOrder());
        for (int i = 0; i < copypoints.length; i++) {
            double nowSlope = copypoints[i].slopeTo(points[center]);
            int last = i;
            while (last + 1 < copypoints.length && copypoints[last + 1].slopeTo(points[center]) == nowSlope) ++last;
            if (last - i + 1 >= 3) {
                ArrayList<Point> sameSlopePoints = new ArrayList<Point>();
                sameSlopePoints.add(points[center]);
                for (int j = i; j <= last; j++) sameSlopePoints.add(copypoints[j]);
                Point[] sameSlopePointsArray = sameSlopePoints.toArray(new Point[sameSlopePoints.size()]);
                Arrays.sort(sameSlopePointsArray);

                pointa.add(sameSlopePointsArray[0]);
                pointb.add(sameSlopePointsArray[sameSlopePointsArray.length-1]);

                //LineSegment now=new LineSegment(sameSlopePointsArray[0], sameSlopePointsArray[sameSlopePointsArray.length - 1]);
                //if(!visited.contains(now.toString())){
                //    segments.add(now);
                //    visited.add(now.toString());
                //}

            }
            i = last;
        }
        return segments;
    }

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {

        if (points == null) throw new IllegalArgumentException();

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++)
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].slopeTo(points[j])==Double.NEGATIVE_INFINITY) throw new IllegalArgumentException();
            }

        ArrayList<LineSegment> segments = new ArrayList<LineSegment>();

        ArrayList<Point> pointa=new ArrayList<Point>();
        ArrayList<Point> pointb=new ArrayList<Point>();

        for (int i = 0; i < points.length; i++) {
            collinear(points, i, pointa, pointb);
        }

        Point[] pointaArray=pointa.toArray(new Point[pointa.size()]);
        Point[] pointbArray=pointb.toArray(new Point[pointb.size()]);


        for(int i=0;i<pointa.size();i++){
            boolean visited=false;
            for(int j=0;j<i;j++){
                if(pointaArray[i].slopeTo(pointaArray[j])==Double.NEGATIVE_INFINITY&&pointbArray[i].slopeTo(pointbArray[j])==Double.NEGATIVE_INFINITY){
                    visited=true;
                    break;
                }
            }
            if(!visited)segments.add(new LineSegment(pointaArray[i],pointbArray[i]));
        }



        this.segments = segments.toArray(new LineSegment[segments.size()]);

    }

    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(segments,segments.length);
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
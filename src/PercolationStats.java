import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

    private final double[] results;

    private final int trials;

    private static double dev, mean;

    private double result(Percolation percolation, int n) {
        while (!percolation.percolates()) {
            int openRow = StdRandom.uniform(1, n + 1);
            int openCol = StdRandom.uniform(1, n + 1);
            percolation.open(openRow, openCol);
            //StdOut.println(openRow);
        }
        return (double) percolation.numberOfOpenSites() / (double) (n * n);
    }

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        this.trials = trials;
        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            results[i] = result(percolation, n);
        }
        dev = StdStats.stddev(results);
        mean = StdStats.mean(results);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return dev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * dev / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * dev / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        StdOut.println("mean = " + stats.mean());
        StdOut.println("standard deviation = " + stats.stddev());
        StdOut.println("95% confidence interval = " + stats.confidenceLo() + " , " + stats.confidenceHi());
    }

}
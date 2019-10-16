package Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

    private WeightedQuickUnionUF UF;

    private WeightedQuickUnionUF UF2;

    private boolean[][] percolation;

    private final int n, totalDirection = 4;

    private int top, bottom, numberOfOpenSites = 0;

    private final int[] directionX = {1, -1, 0, 0};
    private final int[] directionY = {0, 0, 1, -1};

    private int id(int row, int col) {
        return (row - 1) * n + col - 1;
    }

    private boolean illegal(int row, int col) {
        if (row > n || row <= 0) return true;
        if (col > n || col <= 0) return true;
        return false;
    }

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }

        this.n = n;

        percolation = new boolean[n + 1][n + 1];

        top = n * n;

        bottom = n * n + 1;

        UF = new WeightedQuickUnionUF(n * n + 2);

        UF2 = new WeightedQuickUnionUF(n * n + 1);

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; j++) {
                percolation[i][j] = false;
            }
        }


    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (illegal(row, col)) throw new IllegalArgumentException();

        if (percolation[row][col] == true) return;

        percolation[row][col] = true;

        numberOfOpenSites++;

        if (row == 1) {
            UF.union(id(row, col), top);
            UF2.union(id(row, col), top);
        }

        if (row == n) UF.union(id(row, col), bottom);

        for (int i = 0; i < totalDirection; ++i) {
            int newRow = row + directionX[i];
            int newCol = col + directionY[i];
            if (illegal(newRow, newCol)) continue;
            if (percolation[newRow][newCol]) {
                UF.union(id(row, col), id(newRow, newCol));
                UF2.union(id(row, col), id(newRow, newCol));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (illegal(row, col)) throw new IllegalArgumentException();

        return percolation[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (illegal(row, col)) throw new IllegalArgumentException();

        return UF2.connected(id(row, col), top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return UF.connected(top, bottom);
    }


    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(2);
        percolation.open(2, 2);
        percolation.open(1, 2);
        StdOut.println(percolation.percolates());
    }
}
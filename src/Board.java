import edu.princeton.cs.algs4.StdOut;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;

public class Board {

    private int n;
    private int[][] tiles;
    private String stringFormat;
    private int hamming = 0;
    private int manhattan = 0;


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        n = tiles.length;
        this.tiles = tiles;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(n);
        stringBuffer.append('\n');
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] > 0) {
                    hamming += tiles[i][j] == (i * n) + j + 1 ? 0 : 1;
                    manhattan += Math.abs((tiles[i][j] - 1) / n - i) + Math.abs((tiles[i][j] - 1) % n - j);
                }
                String tile = Integer.toString(tiles[i][j]);
                while (tile.length() < 3) tile = " " + tile;
                stringBuffer.append(tile);
            }
            stringBuffer.append('\n');
        }
        stringFormat = stringBuffer.toString();

    }

    // string representation of this board
    public String toString() {
        return stringFormat;
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;
        if (!(y instanceof Board)) return false;
        return this.toString() == y.toString();
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] a = {{3, 2, 1}, {6, 4, 5}, {7, 8, 0}};
        Board board = new Board(a);
        StdOut.println(board.toString());
        StdOut.println(board.hamming());
        StdOut.println(board.manhattan());
    }

}
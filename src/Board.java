import edu.princeton.cs.algs4.StdOut;

public class Board {

    private int n;
    private int[][] tiles;
    private String stringFormat;
    private int hamming = 0;
    private int manhattan = 0;


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        n = tiles[0].length;
        this.tiles = tiles;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(n);
        stringBuffer.append('\n');
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] > 0) {
                    hamming += tiles[i][j] == (i * n) + j + 1 ? 1 : 0;
                    manhattan += tiles[i][j] > (i * n) + j ? tiles[i][j] - ((i * n) + j) : (i * n + j) - tiles[i][j];
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
        return
    }

    // does this board equal y?
    public boolean equals(Object y) {

    }

    // all neighboring boards
    public Iterable<Board> neighbors() {

    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }

}
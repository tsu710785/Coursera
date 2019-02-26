/* *****************************************************************************
 *  Name: Benson Chang
 *  Date: 2019.02.25
 *  Description: algo-hw1
 **************************************************************************** */
//
// import edu.princeton.cs.algs4.StdRandom;
// import edu.princeton.cs.algs4.StdStats;
// import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF grid;
    private int n, openCount;
    private boolean[] block;

    public Percolation(int n) // create n-by-n grid, with all sites blocked
    {
        this.n = n;
        openCount = 0;
        grid = new WeightedQuickUnionUF(n * n + 2);
        block = new boolean[n * n];
        // for (int i = 0; i < n; i++) {
        //     grid.union(i + 1, 0);
        // }
        // for (int i = n * n - n; i < n * n; i++) {
        //     grid.union(i + 1, n * n + 1);
        // }
    }

    public void open(int row, int col) {

        if (this.isOpen(row, col)) return;
        if (row == 1) {
            grid.union(n * (row - 1) + col, 0);
        }
        else if (row == n) {
            grid.union(n * (row - 1) + col, n * n + 1);
        }
        if (col != 1 && this.isOpen(row, col - 1)) {
            // conn left
            grid.union(n * (row - 1) + col, n * (row - 1) + col - 1);
        }
        if (col != n && this.isOpen(row, col + 1)) {
            // conn right
            grid.union(n * (row - 1) + col, n * (row - 1) + col + 1);
        }
        if (row != 1 && this.isOpen(row - 1, col)) {
            // conn up
            grid.union(n * (row - 1) + col, n * (row - 2) + col);
        }
        if (row != n && this.isOpen(row + 1, col)) {
            // conn down
            grid.union(n * (row - 1) + col, row * n + col);
        }
        block[n * (row - 1) + col - 1] = true;
        openCount++;
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return block[row * n + col - n - 1];
    }

    public void validate(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException("index out of range");
        }
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return grid.connected(row * n - n + col + 1, 0);
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    public boolean percolates() {
        return grid.connected(0, n * n + 1);
    }

    public void print() {
        for (int i = 0; i < n * n; i++) {
            System.out.print(block[i]);
            if ((i + 1) % n == 0) {
                System.out.print("\n");
            }
            else {
                System.out.print(" ");
            }
        }
        System.out.println("----");
    }

    public static void main(String[] args) {

        // int n = 3;
        // Percolation g = new Percolation(n);

        //
        // g.print();
        // g.open(1, 1);
        // g.print();
        // System.out.println("0,1 " + g.grid.connected(0, 1));
        // g.open(2, 1);
        // g.print();
        // System.out.println("1 parent " + g.grid.find(1));
        // System.out.println("4 parent " + g.grid.find(4));
        // System.out.println("0,4 " + g.grid.connected(0, 4));
        // g.open(3, 2);
        // System.out.println("0,7 " + g.grid.connected(0, 7));
        // g.print();
        // g.open(2, 2);
        // g.print();
        // g.open(3, 1);
        // g.print();
        // System.out.println(g.isFull(3, 1));
        // System.out.println(g.percolates());
        // g.print();
        // g.block[0] = true;
        // System.out.println(g.isOpen(1, 1));
        // g.block[2] = true;
        // g.print();
        // System.out.println(g.isOpen(1, 3));
    }


}

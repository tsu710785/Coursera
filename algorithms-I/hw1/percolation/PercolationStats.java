/* *****************************************************************************
 *  Name: Benson Chang
 *  Date: 2019.02.26
 *  Description: algo-hw1
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double confidenceLow;
    private final double confidenceHigh;
    private static final double CONFIDENCE_95 = 1.96;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("invalid arguments");
        }
        double[] testResult = new double[trials];
        for (int i = 0; i < trials; i++) {
            testResult[i] = simulation(n);
        }
        this.mean = StdStats.mean(testResult);
        this.stddev = StdStats.stddev(testResult);
        this.confidenceLow = this.mean - CONFIDENCE_95 * this.stddev / Math.sqrt(trials);
        this.confidenceHigh = this.mean + CONFIDENCE_95 * this.stddev / Math.sqrt(trials);

    }    // perform trials independent experiments on an n-by-n grid

    private double simulation(int n) {
        Percolation p = new Percolation(n);
        double count = 0;
        while (!p.percolates()) {
            int randomRow = StdRandom.uniform(n) + 1;
            int randomCol = StdRandom.uniform(n) + 1;
            if (!p.isOpen(randomRow, randomCol)) {
                p.open(randomRow, randomCol);
                count++;
            }
        }
        double possibility = count / (double) (n * n);
        return possibility;
    }

    public double mean() {
        return this.mean;
    }                    // sample mean of percolation threshold

    public double stddev() {
        return this.stddev;
    }      // sample standard deviation of percolation threshold

    public double confidenceLo() {
        return this.confidenceLow;
    }               // low  endpoint of 95% confidence interval

    public double confidenceHi() {
        return this.confidenceHigh;
    }             // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trial = Integer.parseInt(args[1]);
        if (n <= 0 || trial <= 0) {
            throw new IllegalArgumentException("invalid arguments");
        }

        PercolationStats s = new PercolationStats(n, trial);
        System.out.println(s.mean());
        System.out.println(s.stddev());
        System.out.println(s.confidenceLo());
        System.out.println(s.confidenceHi());
    }     // test client (described below)

}

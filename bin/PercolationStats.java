/*-------------------------------------------------------------------------
 * Author:        Mark Johnson
 * Written:       8/22/2012
 * Last Updated:  8/22/2012
 *
 * Compilation:   javac PercolationStats.java
 * Execution:     java PercolationStats
 *
 * Generates a Monte Carlo simulation, stores percolation thresholds,
 * and provides mean and standard deviation methods to analyze the
 * generated data.
 * 
 *************************************************************************/

public class PercolationStats {

    static int[] tableOfCounts; // PRIVATE, SHOULD THIS BE STATIC? store thresholds
    int randomFirst, randomSecond; // PRIVATE two random numbers
    Percolation experiment; // PRIVATE experiment object
    int T; // PRIVATE, bad naming! number of repititions
    int N; // PRIVATE, bad naming! dimension of grid

    /*******************************************************************
     * Initializes and runs each experiment, populating a table of 
     * percolation thresholds.
     *******************************************************************/
    public PercolationStats(int N, int T) {
        if ((N <= 0) || (T <= 0))
            throw new java.lang.IllegalArgumentException("index out of bounds");

        this.N = N; // ? why
        this.T = T; // ? why
        tableOfCounts = new int[T];

        for (int i = 1; i <= T; i++) {
            experiment = new Percolation(N);

            while (!experiment.percolates()) {
                randomFirst = StdRandom.uniform(1, N + 1);
                randomSecond = StdRandom.uniform(1, N + 1);

                if (!experiment.isOpen(randomFirst, randomSecond)) {
                    experiment.open(randomFirst, randomSecond);
                }
            }

            tableOfCounts[i-1] = experiment.count;
        }
    }

    /***************************************************************************
     * Returns the mean from the table of percolation thresholds
     ***************************************************************************/
    public double mean() {
        double gridSize = ((double) (N * N));
        return (StdStats.mean(tableOfCounts) / gridSize);
    }

    /****************************************************************************
     * Returns the sample standard deviation from the table of percolation thresholds.
     ****************************************************************************/
    public double stddev() {
        double gridSize = ((double) (N * N));
        return (StdStats.stddev(tableOfCounts) / gridSize);
    }

    public static void main(String[] args) {
                                             // You do need to deal with N = 1
                                             // If T equals 1, return Double.NaN
        int N = Integer.parseInt(args[0]); // specifies grid size
        int T = Integer.parseInt(args[1]); // how many experiments
        int randomFirst, randomSecond;
        double lowerConfidence, upperConfidence; // lower and upper confidence intervals

        PercolationStats myStats = new PercolationStats(N, T);
        StdOut.println("mean\t\t\t" + "= " + myStats.mean());
        StdOut.println("stddev\t\t\t" + "= " + myStats.stddev());
        lowerConfidence = myStats.mean() - ((1.96 * myStats.stddev())/Math.sqrt((double) T));
        upperConfidence = myStats.mean() + ((1.96 * myStats.stddev())/Math.sqrt((double) T));
        StdOut.println("95% confident interval = " + lowerConfidence + ", " + upperConfidence);
    }
}
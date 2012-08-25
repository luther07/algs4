/*-------------------------------------------------------------------------
 * Author:        Mark Johnson
 * Written:       8/22/2012
 * Last Updated:  8/24/2012
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

    private int[] tableOfCounts;           // store thresholds
    private int randomFirst, randomSecond; // two random numbers
    private Percolation experiment;        // experiment object
    private int numberOfSimulations;       // number of repititions
    private int dimension;                 // dimension of grid
    private int threshold;                 // running percolation threshold

    /*******************************************************************
     * Initializes and runs each experiment, populating a table of 
     * percolation thresholds.
     *******************************************************************/
    public PercolationStats(int N, int T) {
        if ((N <= 0) || (T <= 0))
            throw new java.lang.IllegalArgumentException("index out of bounds");
        dimension = N;
        numberOfSimulations = T;
        tableOfCounts = new int[numberOfSimulations];
        
        for (int i = 1; i <= numberOfSimulations; i++) {
            experiment = new Percolation(dimension);
            threshold = 0;

            while (!experiment.percolates()) {
                randomFirst = StdRandom.uniform(1, dimension + 1);
                randomSecond = StdRandom.uniform(1, dimension + 1);

                if (!experiment.isOpen(randomFirst, randomSecond)) {
                    experiment.open(randomFirst, randomSecond);
                    threshold++;
                }
            }
            tableOfCounts[i-1] = threshold;
        }
        
    }

    /***************************************************************************
     * Returns the mean from the table of percolation thresholds
     * Mean is a function of tableOfCounts and gridSize.
     * Array tableOfCounts depends on variable threshold.
     * Variable gridSize is a function of dimension.
     * Variable dimension is safe.
     ***************************************************************************/
    public double mean() {
        double gridSize = ((double) (dimension * dimension));
        return (StdStats.mean(tableOfCounts) / gridSize);
    }

    /****************************************************************************
     * Returns the sample standard deviation from the thresholds store.
     * Standard deviation is a function of tableOfCounts and gridSize.
     * Array tableOfCounts depends on variable threshold.
     * Variable gridSize is a function of dimension.
     * Variable dimension is safe.
     ****************************************************************************/
    public double stddev() {
        double gridSize = ((double) (dimension * dimension));
        return (StdStats.stddev(tableOfCounts) / gridSize);
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);       // grid size
        int T = Integer.parseInt(args[1]);       // number of simulations 
        int randomFirst, randomSecond;           // two random numbers
        double lowerConfidence, upperConfidence; // confidence intervals

        PercolationStats sample = new PercolationStats(N, T);
        StdOut.println("mean\t\t\t" + "= " + sample.mean());
        StdOut.println("stddev\t\t\t" + "= " + sample.stddev());
        lowerConfidence = 
            sample.mean() - ((1.96 * sample.stddev())/Math.sqrt((double) T));
        upperConfidence = 
            sample.mean() + ((1.96 * sample.stddev())/Math.sqrt((double) T));
        StdOut.print("95% confidence interval =");
        StdOut.print(" " + lowerConfidence + ", " + upperConfidence + "\n");
    }
}
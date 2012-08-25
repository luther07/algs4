/*-------------------------------------------------------------------------
 * Author:        Mark Johnson
 * Written:       8/22/2012
 * Last Updated:  8/22/2012
 *
 * Compilation:   javac Percolation.java
 * Execution:     java Percolation
 *
 * Defines a Percolation type, which has a grid, and methods that
 * allow us to measure when the grid percolates.
 * 
 *************************************************************************/

public class Percolation {
    private WeightedQuickUnionUF grid; // grid of sites
    private int dimension;             // grid dimensions
    private boolean[] siteStatus;      // open/blocked sites

    /***********************************************************************
     * Class constructor method which sets all sites to blocked (false)
     * and connects the sites in the virtual bottom to eachother
     * and connects the sites in the virtual top to eachother. 
     ***********************************************************************/
    public Percolation(int N) { // create N-by-N grid, with all sites blocked
                                // What do we do if N equals 1?
        if (N < 1)
            throw new java.lang.IllegalArgumentException("index out of bounds");
        dimension     = N;
        grid          = new WeightedQuickUnionUF(dimension * dimension);
        siteStatus    = new boolean[dimension * dimension];

        // initialize grid sites to blocked
        for (int i = 0; i < (dimension * dimension); i++) {
            siteStatus[i] = false;
        }

        // initialize virtual top
        for (int i = 1; i < dimension; i++) {
            grid.union(0, i);
        }

        // initialize virtual bottom
        int lastRowFirstColumn = xyTo1D(dimension, 1); // variable simplifies formula
        for (int i = 1; i < dimension; i++) {
            grid.union(lastRowFirstColumn, lastRowFirstColumn + i); // ??
        }
    }

    /*********************************************************************
     * Method that opens a given site and connects to open neighbors,
     * if the given site is not currently open.
     *********************************************************************/
    public void open(int i, int j) { // open site (row i, column j)
        if ((i < 1) || (i > dimension) || (j < 1) || (j > dimension))
            throw new java.lang.IndexOutOfBoundsException("index out of bounds");
        int gridIndex = xyTo1D(i, j);
        if (!siteStatus[gridIndex]) {
            siteStatus[gridIndex] = true;
            connectToNeighbors(i, j);
        }
    }

    /**********************************************************************
     * Method that says if a given site is open.
     **********************************************************************/
    public boolean isOpen(int i, int j) {  // is site (row i, column j) open?
        if ((i < 1) || (i > dimension) || (j < 1) || (j > dimension))
            throw new java.lang.IndexOutOfBoundsException("index out of bounds");
        int gridIndex = xyTo1D(i, j);
        return siteStatus[gridIndex];
    }

    /**********************************************************************
     * Method that says if a given site is connected to the top.
     **********************************************************************/
    public boolean isFull(int i, int j) {
        if ((i < 1) || (i > dimension) || (j < 1) || (j > dimension))
            throw new java.lang.IndexOutOfBoundsException("index out of bounds");
        int gridIndex = xyTo1D(i, j);
        if (isOpen(i, j)) {
            return grid.connected(0, gridIndex);
        } else {
            return false;
        }
    }

    /**********************************************************************
     * Method that says whether the system currently percolates.
     **********************************************************************/
    public boolean percolates() {
        return grid.connected((dimension * dimension) - 1, 0);
    }

    /**********************************************************************
     * Helper method: converts site location from (row,column) to a 1D index.
     **********************************************************************/
    private int xyTo1D(int i, int j) {
        if ((i < 1) || (i > dimension) || (j < 1) || (j > dimension))
            throw new java.lang.IndexOutOfBoundsException("index out of bounds");
        return dimension*(i - 1) + (j-1);
    }

    /************************************************************************
     * Helper method that connects (row i, column j) to all open neighbors.
     ************************************************************************/
    private void connectToNeighbors(int row, int column) {
        if ((row < 1) || (row > dimension) || (column < 1) || (column > dimension))
            throw new java.lang.IndexOutOfBoundsException("index out of bounds");
        int location = xyTo1D(row, column);

        if (row != 1 && isOpen(row - 1, column)) {
            grid.union(location, location - dimension);
        }
        if (row != dimension && isOpen(row + 1, column)) {
            grid.union(location, location + dimension);
        }
        if (column != 1 && isOpen(row, column - 1)) {
            grid.union(location, location -1);
        }
        if (column != dimension && isOpen(row, column + 1)) {
            grid.union(location, location + 1);
        }
    }
}
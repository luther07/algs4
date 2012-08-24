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
    WeightedQuickUnionUF grid;  // grid of sites, PRIVATE
    int dimension;              // grid dimensions, PRIVATE
    boolean[] openOrBlocked;    // stores grid open/blocked values, PRIVATE
    int count;                  // count of open sites?, PRIVATE

    /***********************************************************************
     * Class constructor method which sets all sites to blocked (false)
     * and connects the sites in the virtual bottom to eachother
     * and connects the sites in the virtual top to eachother. 
     ***********************************************************************/
    public Percolation(int N) { // create N-by-N grid, with all sites blocked
                                // takes time proportional to N^2
        dimension     = N;
        grid          = new WeightedQuickUnionUF(dimension * dimension);
        openOrBlocked = new boolean[dimension * dimension];

        // initialize grid sites to blocked
        for (int i = 0; i < (dimension * dimension); i++) {
            openOrBlocked[i] = false;
        }

        // initialize virtual top
        for (int i = 1; i < dimension; i++) {
            grid.union(0, i);
        }

        // initialize virtual bottom
        for (int i = 1; i < dimension; i++) {
            int lastRowFirstColumn = xyTo1D(dimension, 1);
            grid.union(lastRowFirstColumn, lastRowFirstColumn + i);
        }
    }

    /*********************************************************************
     * Method that opens a given site and connects to open neighbors,
     * if the given site is not currently open.
     *********************************************************************/
    public void open(int i, int j) { // open site (row i, column j)
        if ((i < 1) || (i > dimension) || (j < 1) || (j > dimension))
            throw new java.lang.IndexOutOfBoundsException();
        int gridIndex = xyTo1D(i, j);
        if (!openOrBlocked[gridIndex]) {
            openOrBlocked[gridIndex] = true;
            count++;
            connectToNeighbors(i, j);
        }
    }

    /**********************************************************************
     * Method that says if a given site is open.
     **********************************************************************/
    public boolean isOpen(int i, int j) {  // is site (row i, column j) open?
        if ((i < 1) || (i > dimension) || (j < 1) || (j > dimension))
            throw new java.lang.IndexOutOfBoundsException();
        int gridIndex = xyTo1D(i, j);
        return openOrBlocked[gridIndex];
    }

    /**********************************************************************
     * Method that says if a given site is connected to the top.
     **********************************************************************/
    public boolean isFull(int i, int j) {
        if ((i < 1) || (i > dimension) || (j < 1) || (j > dimension))
            throw new java.lang.IndexOutOfBoundsException();
        int gridIndex = xyTo1D(i, j);
        return grid.connected(0, gridIndex);
    }

    /**********************************************************************
     * Method that says whether the system currently percolates.
     **********************************************************************/
    public boolean percolates() {
        return grid.connected((dimension * dimension) - 1, 0);
    }

    /**********************************************************************
     * Helper method that converts a site location from 2D (row,column)
     * to a 1D index.
     **********************************************************************/
    private int xyTo1D(int i, int j) {
        if ((i < 1) || (i > dimension) || (j < 1) || (j > dimension))
            throw new java.lang.IndexOutOfBoundsException();
        return dimension * (i - 1) + (j - 1);
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
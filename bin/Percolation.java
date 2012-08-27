/*-----------------------------------------------------------------------------
 * Author:        Mark Johnson
 * Written:       8/22/2012
 * Last Updated:  8/26/2012
 *
 * Compilation:   javac Percolation.java
 * Execution:     java Percolation
 *
 * Defines a Percolation type, which has a grid, and methods that
 * allow us to measure when the grid percolates.
 *
 *---------------------------------------------------------------------------*/

public class Percolation {
    private WeightedQuickUnionUF grid; // grid of sites
    private int dimension;             // grid dimensions
    private boolean[] siteStatus;      // open/blocked sites
    private int virtualTopIndex;       // array index of virtual top
    private int virtualBottomIndex;    // array index of virtual bottom   

    /**************************************************************************
     * Class constructor method which sets all sites to blocked (false),
     * connects the sites in the virtual bottom to eachother,
     * and connects the sites in the virtual top to eachother.
     *
     * Updated virtuals, don't need to initialize old virtual top or bottom.
     *************************************************************************/
    public Percolation(int N) { // create N-by-N grid, with all sites blocked
                                // What do we do if N equals 1?
        if (N < 1)
            throw new java.lang.IllegalArgumentException("index out of bounds");
        dimension     = N;
        grid          = new WeightedQuickUnionUF((dimension*dimension) + 2);
        siteStatus    = new boolean[dimension * dimension];
        virtualTopIndex = dimension * dimension;
        virtualBottomIndex = (dimension * dimension) + 1;

        // initialize grid sites to blocked
        for (int i = 0; i < (dimension*dimension); i++) {
            siteStatus[i] = false;
        }

        // initialize virtual top
        //for (int i = 1; i < dimension; i++) {
        //    grid.union(0, i);
        //}

        // initialize virtual bottom
        //int lastRowFirstColumn = xyTo1D(dimension, 1);
        //for (int i = 1; i < dimension; i++) {
        //    grid.union(lastRowFirstColumn, lastRowFirstColumn + i); // ??
        //}
    }

    /**************************************************************************
     * Method that opens a given site and connects to open neighbors.
     * No changes necessary when making row N+1 into virtual top?
     *
     * If you connect a site in row 1, you must connect it to virtual top site.
     * Virtual top site will be index N.
     * If you connect a site in row N, you must connect it to virtual bottom
     * site. Virtual bottom site will be index N*N.
     *
     * I believe when a site in the bottome row is added, it doesn't get 
     * connected to the virtual bottom.
     *
     * Need to debug this open function. It is not working correctly.
     *************************************************************************/
    public void open(int i, int j) {
        if ((i < 1) || (i > dimension) || (j < 1) || (j > dimension))
            throw new java.lang.IndexOutOfBoundsException("index out of bounds");
        int gridIndex = xyTo1D(i, j);
        if (!siteStatus[gridIndex]) {
            siteStatus[gridIndex] = true;
            connectToNeighbors(i, j);
            if (i == 1) {                                   // join site to
                grid.union(gridIndex, virtualTopIndex);     // virtual top 
            } else if (i == dimension) {                    // and bottom
                grid.union(gridIndex, virtualBottomIndex);
            }
        }
    }

    /**************************************************************************
     * Method that says if a given site is open.
     * No changes necessary.
     *************************************************************************/
    public boolean isOpen(int i, int j) {
        if ((i < 1) || (i > dimension) || (j < 1) || (j > dimension))
            throw new java.lang.IndexOutOfBoundsException("invalid index");
        int gridIndex = xyTo1D(i, j);
        return siteStatus[gridIndex];
    }

    /**************************************************************************
     * Method that says if a given site is connected to the top.
     * Change to check if gridIndex is connected to new virtual top.
     *************************************************************************/
    public boolean isFull(int i, int j) {
        if ((i < 1) || (i > dimension) || (j < 1) || (j > dimension))
            throw new java.lang.IndexOutOfBoundsException("invalid index");
        int gridIndex = xyTo1D(i, j);
        if (isOpen(i, j)) {
            return grid.connected(gridIndex, virtualTopIndex); // virt top 
        } else {
            return false;
        }
    }

    /**************************************************************************
     * Method that says whether the system currently percolates.
     * Change to check if the virtual bottom is connected to the virtual top.
     * What the fuck is this shit? 
     *************************************************************************/
    public boolean percolates() {
        if (dimension == 1) {
            return isOpen(1, 1);
        } else {
            return grid.connected(virtualBottomIndex, virtualTopIndex);
        }
    }

    /**************************************************************************
     * Helper method: converts site location from (row,column) to a 1D index.
     *************************************************************************/
    private int xyTo1D(int i, int j) {
        if ((i < 1) || (i > dimension) || (j < 1) || (j > dimension))
            throw new java.lang.IndexOutOfBoundsException("index out of bounds");
        return dimension*(i - 1) + (j-1);
    }

    /**************************************************************************
     * Helper method that connects (row i, column j) to all open neighbors.
     *************************************************************************/
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

    public static void main(String[] args) {
        Percolation x = new Percolation(10);
        Percolation y = new Percolation(2);
        Percolation z = new Percolation(4);
        if (x.percolates())
            StdOut.println("percolates too soon");
        
        // test isOpen
        StdOut.println("TEST ISOPEN:");
        x.open(10, 2);
        x.open(1, 1);
        x.open(10, 1);
        if (x.isOpen(10, 2) && x.isOpen(1, 1) && x.isOpen(10, 1))
            StdOut.println("\tisOpen passed");

        // test isFull
        StdOut.println("TEST ISFULL:");
        x.open(2, 1);
        if (x.isFull(1, 1) && x.isFull(2, 1))
            StdOut.println("\tisFull passed");

        // test percolates
        StdOut.println("TEST PERCOLATES:");
        if (x.percolates())
            StdOut.println("hi loser");
        x.open(1, 8);
        x.open(2, 8);
        x.open(3, 8);
        x.open(4, 8);
        x.open(5, 8);
        x.open(6, 8);
        x.open(6, 9);
        x.open(7, 9);
        x.open(7, 10);
        x.open(8, 10);
        x.open(9, 10);
        if (x.percolates())
            StdOut.println("\tpercolates failed");
        x.open(10, 10);
        if (x.percolates())
            StdOut.println("\tpercolates passed");

        y.open(1, 1);
            if (y.isOpen(1, 1))
            StdOut.println("1,1 is open");
        y.open(2, 2);
        if (y.isOpen(2, 2))
            StdOut.println("2,2 is open");
        y.open(1, 2);
        if (y.isOpen(1, 2))
            StdOut.println("1,2 is open");
        if (y.percolates())
            StdOut.println("y percolates");

        z.open(1, 1);
        z.open(2, 1);
        z.open(3, 1);
        z.open(4, 1);
        if (z.percolates())
            StdOut.println("z percolates");        
    }
}


package ProjectC482.Model;

/**
 * @author
 * Armando Ramirez
 * Student ID: 010095862
 */

/** Contains the Parts. */
public abstract class Parts {
    private int partID, stock, min, max;
    private String name;
    private double partCost;


    public Parts(int partID, int stock, int min, int max, String name, double partCost) {
        this.partID = partID;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.name = name;
        this.partCost = partCost;
    }
    /** FUTURE ENHANCEMENT (Gets ID of Part)
     *  Being able to drag and drop Parts from separate windows of the same application. */
    public int getPartID() {
        return partID;
    }
   /** Returns the available stock of a part. */
    public int getStock() {
        return stock;
    }
    /** Returns the Minimum */
    public int getMin() {
        return min;
    }
    /** Returns the Maximum */
    public int getMax() {
        return max;
    }
    /** Returns the Name */
    public String getName() {
        return name;
    }
    /** Returns the cost of a Part. */
    public double getPartCost() {
        return partCost;
    }

    /** Sets the ID of a Part */
    public void setPartID(int partID) {
        this.partID = partID;
    }
   /** Sets the Stock in the application. */
    public void setStock(int stock) {
        this.stock = stock;
    }

 /** Sets the minimum. */
    public void setMin(int min) {
        this.min = min;
    }
    /** Sets the Maximum */
    public void setMax(int max) {
        this.max = max;
    }

 /** Sets the name */
    public void setName(String name) {
        this.name = name;
    }
    /** Sets the Cost of a Part */
    public void setPartCost(double partCost) {
        this.partCost = partCost;
    }
}

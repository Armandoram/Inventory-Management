package ProjectC482.Model;

/**
 * @author
 * Armando Ramirez
 * Student ID: 010095862
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** Creates a Product class for the Product object with no inheritance */
public class Products {
    private ObservableList<Parts> associatedParts = FXCollections.observableArrayList();
    private int productID, stock, min, max;
    private String name;
    private double productPrice;

    public Products(int productID, int stock, int min, int max, String name, double productPrice) {
        this.productID = productID;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.name = name;
        this.productPrice = productPrice;
    }

    public Products() {
        this(0, 0, 0, 0, null, 0.00);
    }
    /**
     * Returns the proudctID of an object. */
    public int getProductID() {
        return productID;
    }
    /** FUTURE ENHANCEMENT (Product ID is set for use by the Product object)
     * Being able to copy and paste products and being able to modify them, or be given a new ID when it gets pasted into the application. */
    public void setProductID(int productID) {
        this.productID = productID;
    }
    /** Returns the stock amount of the Product object */
    public int getStock() {
        return stock;
    }
    /** Sets the stock of a product */
    public void setStock(int stock) {
        this.stock = stock;
    }
    /** Returns the minimum amount of stock to be held of the Product object */
    public int getMin() {
        return min;
    }
    /** Sets the minimum amount of stock to be held of the Product object */
    public void setMin(int min) {
        this.min = min;
    }
    /** Returns the maximum amount of stock to be held of the Product object */
    public int getMax() {
        return max;
    }
    /** Sets  the maximum amount of stock set to the Product object */
    public void setMax(int max) {
        this.max = max;
    }
 /** Returns the Name of a Product. */
    public String getName() {
        return name;
    }
 /** Sets the name of a Product */
    public void setName(String name) {
        this.name = name;
    }
    /** Returns the Product Price */
    public double getProductPrice() {
        return productPrice;
    }
    /** Sets the Product Price */
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    /** Adds the Associated Part */
    public void addAssociatedPart(ObservableList<Parts> part){
        this.associatedParts.addAll(part);
    }
    /** Returns all Assoiciated Parts. */
    public ObservableList<Parts> getAllAssociatedParts(){
        return associatedParts;
    }
}

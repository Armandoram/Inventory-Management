package ProjectC482.Model;
/**
 * @author
 * Armando Ramirez
 * Student ID: 010095862
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Creates the Inventory for the class which contains all items in the system and also be able to manipulate them.*/
public class Inventory { private static ObservableList<Parts> allParts = FXCollections.observableArrayList();
    /**Initializes two objects of type ObservableList*/
    private static ObservableList<Products> allProducts = FXCollections.observableArrayList();

   /** Adds a new Part. */
    public static void addPart(Parts newPart){
        allParts.add(newPart);
    }

    /** Gets all the Parts. */
    public static ObservableList<Parts> getAllParts() {
        return allParts;
    }

    /** Adds a New Product */
    public static void addProduct(Products newProduct){
        allProducts.add(newProduct);
    }

    /** Gets all the Products */
    public static ObservableList<Products> getAllProducts() {
        return allProducts;
    }

    /** Looks up the Part ID */
    public static Parts lookupPartID(int partID) {
        Parts temp = null;
        for (Parts parts : allParts){
            if (partID == parts.getPartID()){
                temp = parts;
            }
        }
        return temp;
    }

    /** Looks up the Product ID */
    public static Products lookupProductID(int productID) {
        Products temp = null;
        for (Products products : allProducts){
            if (productID == products.getProductID()){
                temp = products;
            }
        }
        return temp;
    }

    //+ lookupPart(partName:String):ObservableList<Part>

    /** RUNTIME ERROR (Looks up the Part)
     * Java Exception due to a misplacing of the FXCollections, was fixed shortly after. */
    public static ObservableList lookupPart(String searchPartName){
        ObservableList<Parts> foundParts = FXCollections.observableArrayList();

        if(searchPartName.length() == 0) {
            foundParts = allParts;
        }
        else {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getName().toLowerCase().contains(searchPartName.toLowerCase())) {
                    foundParts.add(allParts.get(i));
                }
            }
        }

        return foundParts;
    }

    /** Looks up the Product */
    public static ObservableList lookupProduct(String searchProductName){
        ObservableList<Products> foundProducts = FXCollections.observableArrayList();

        if(searchProductName.length() == 0) {
            foundProducts = allProducts;
        }
        else {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getName().toLowerCase().contains(searchProductName.toLowerCase())) {
                    foundProducts.add(allProducts.get(i));
                }
            }
        }

        return foundProducts;
    }

    /** Updates the Part. */
    public static void updatePart(int index, Parts selectedPart){
        allParts.set(index, selectedPart);
    }

    /** Updates the Product. */
    public static void updateProduct(int index, Products newProduct){
        allProducts.set(index, newProduct);
    }

    /** Deletes the Part. */
    public static boolean deletePart(Parts selectedPart){
        return allParts.remove(selectedPart);
    }

    /** Deletes the Product. */
    public static boolean deleteProduct(Products selectedProduct){
        return allProducts.remove(selectedProduct);
    }
}


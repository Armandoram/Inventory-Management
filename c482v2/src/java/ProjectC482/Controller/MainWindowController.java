package ProjectC482.Controller;
/**
 * @author
 * Armando Ramirez
 * Student ID: 010095862
 */


import ProjectC482.Model.Inventory;
import ProjectC482.Model.Parts;
import ProjectC482.Model.Products;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
/** Creates an MainScreenController class for MainScreen FXML Scene */
public class MainWindowController implements Initializable {
    /**Initializing the XML fields*/

    // Parts Table
    @FXML private TableView<Parts> partsTableView;
    @FXML private TableColumn<Parts, Integer> partIDColumn;
    @FXML private TableColumn<Parts, String> partNameColumn;
    @FXML private TableColumn<Parts, Integer> partInvLevelColumn;
    @FXML private TableColumn<Parts, Double> partCostColumn;
    @FXML private TextField partSearchArea;

    //Products Table
    @FXML private TableView<Products> productsTableView;
    @FXML private TableColumn<Products, Integer> productIDColumn;
    @FXML private TableColumn<Products, String> productNameColumn;
    @FXML private TableColumn<Products, Integer> productInvLevelColumn;
    @FXML private TableColumn<Products, Double> productCostColumn;
    @FXML private TextField productSearchArea;

    //Buttons and Fields
    private Parent scene;

    /** When the button is pushed it adds the part. */
    public void addpartbuttonpushed(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        stage.setTitle("Add Part");
        stage.setScene(new Scene(scene));
        stage.show();
    }
 /** It allows you to modify Part when pushed */
    public void modifypartbuttonpushed(ActionEvent event){
        try {
            Parts selectedPart = partsTableView.getSelectionModel().getSelectedItem();
            if (selectedPart == null) {
                return;
            }
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProjectC482/Controller/ModifyPart.fxml"));
            scene = loader.load();
            ModifyPartController controller = loader.getController();
            controller.setParts(selectedPart);
            stage.setTitle("Modify Part");
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
/** It allows you to modify the Product. */
    public void modifyproductbuttonpushed(ActionEvent event){
        try {
            Products selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
            if (selectedProduct == null) {
                return;
            }
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProjectC482/Controller/ModifyProduct.fxml"));
            scene = loader.load();
            ModifyProductController controller = loader.getController();
            controller.setProduct(selectedProduct);
            stage.setTitle("Modify Product");
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
 /** When pushed the Part is searched */
    public void searchPartButton(ActionEvent event) {
        String term = partSearchArea.getText();
        ObservableList foundParts = Inventory.lookupPart(term);
        if(foundParts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No Match");
            alert.setHeaderText("Unable to locate a part name with: " + term);
            alert.showAndWait();
        } else {
            partsTableView.setItems(foundParts);
        }
    }
/** When pushed it searches the product */
    public void searchProductButton(ActionEvent event) {
        String term = productSearchArea.getText();
        ObservableList foundProducts = Inventory.lookupProduct(term);
        if(foundProducts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No Match");
            alert.setHeaderText("Unable to locate a product name with: " + term);
            alert.showAndWait();
        } else {
            productsTableView.setItems(foundProducts);
        }
    }

    /** FUTURE ENHANCEMENT (When pressed it deletes the Part)
     * Being able to undo a deletion could come in handy in a future update. */
    public void deletePartButton(ActionEvent event) {
        if (partsTableView.getSelectionModel().isEmpty()){
            infoDialog("Warning!", "No Part Selected","Please choose part from the above list");
            return;
        }
        if (confirmDialog("Warning!", "Would you like to delete this part?")){
            int selectedPart = partsTableView.getSelectionModel().getSelectedIndex();
            partsTableView.getItems().remove(selectedPart);
        }
    }
 /** Deletes the product when button is pushed */
    public void deleteProductButton(ActionEvent event) {
        if (productsTableView.getSelectionModel().isEmpty()){
            infoDialog("Warning!", "No Product Selected","Please choose product from the above list");
            return;
        }
        else {
            if (productsTableView.getSelectionModel().getSelectedItem().getAllAssociatedParts().size()>0)
            {
                infoDialog("Warning!", "Product cannot be deleted","Associated Parts exist. Please remove parts.");
                return;
            }
            else {
                if (confirmDialog("Warning!", "Would you like to delete this product?")){
                    int selectedPart = productsTableView.getSelectionModel().getSelectedIndex();
                    productsTableView.getItems().remove(selectedPart);


                }
            }
        }

    }

    static boolean confirmDialog(String title, String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText("Confirm");
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        }
        else {
            return false;
        }
    }

    static void infoDialog(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
/** When the button is pushed the Product is added */
    @FXML public void addProductButtonPushed(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        stage.setTitle("Add Product");
        stage.setScene(new Scene(scene));
        stage.show();
    }
/** When pushed it exits you out of the program. */
    @FXML public void exitButton(ActionEvent event) throws IOException{
        confirmDialog("Exit", "Are you sure you would like to exit the program?");
        {
            System.exit(0);
        }
    }
 /** Initializes the information and values for both Parts and Products with URL location and resources included. */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partsTableView.setItems(Inventory.getAllParts());
        partIDColumn.setCellValueFactory(new PropertyValueFactory<Parts, Integer>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Parts, String>("name"));
        partInvLevelColumn.setCellValueFactory(new PropertyValueFactory<Parts, Integer>("stock"));
        partCostColumn.setCellValueFactory(new PropertyValueFactory<Parts, Double>("partCost"));

        productsTableView.setItems((Inventory.getAllProducts()));
        productIDColumn.setCellValueFactory(new PropertyValueFactory<Products, Integer>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("name"));
        productInvLevelColumn.setCellValueFactory(new PropertyValueFactory<Products, Integer>("stock"));
        productCostColumn.setCellValueFactory(new PropertyValueFactory<Products, Double>("productPrice"));


    }

}

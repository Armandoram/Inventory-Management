package ProjectC482.Controller;
/**
 * @author
 * Armando Ramirez
 * Student ID: 010095862
 */
import ProjectC482.Model.Parts;
import ProjectC482.Model.Products;
import javafx.collections.FXCollections;
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
import java.util.ResourceBundle;
import javax.swing.JTextField;
/** Creates an ModifyProductController class for the ModifyProduct XML Scene */
public class ModifyProductController implements Initializable {

    private Stage stage;
    private Object scene;

    //Un Associated Parts Table
    @FXML private TableView<Parts> PartsTableView;
    @FXML private TableColumn<Parts, Integer> PartsIDColumn;
    @FXML private TableColumn<Parts, String> PartsNameColumn;
    @FXML private TableColumn<Parts, Integer> PartsInventoryColumn;
    @FXML private TableColumn<Parts, Double> PartsCostColumn;

    //Associated Parts Table
    @FXML private TableView<Parts> AssociatedPartsTableView;
    @FXML private TableColumn<Products, Integer> AssociatedPartsIDColumn;
    @FXML private TableColumn<Products, String> AssociatedPartsNameColumn;
    @FXML private TableColumn<Products, Integer> AssociatedPartsInventoryColumn;
    @FXML private TableColumn<Products, Double> AssociatedPartsCostColumn;

    //Modify Fields
    @FXML private TextField Name;
    @FXML private TextField Inventory;
    @FXML private TextField Price;
    @FXML private TextField Maximum;
    @FXML private TextField Minimum;
    @FXML private TextField ID;

    //Other Buttons/Fields
    @FXML private TextField SearchField;
    private Products selectedProduct;
    private Products modProduct;
    private ObservableList<Parts> associatedParts = FXCollections.observableArrayList();
    private int productID;

    /** FUTURE ENHANCEMENT (Sets the Product and all of its information.)
     * Being able to add more options to describe the product, making your own option, for example "Year Made".
     */
    public void setProduct(Products selectedProduct) {
        this.selectedProduct = selectedProduct;
        productID = ProjectC482.Model.Inventory.getAllProducts().indexOf(selectedProduct);
        ID.setText(Integer.toString(selectedProduct.getProductID()));
        Name.setText(selectedProduct.getName());
        Inventory.setText(Integer.toString(selectedProduct.getStock()));
        Price.setText(Double.toString(selectedProduct.getProductPrice()));
        Maximum.setText(Integer.toString(selectedProduct.getMax()));
        Minimum.setText(Integer.toString(selectedProduct.getMin()));
        associatedParts.addAll(selectedProduct.getAllAssociatedParts());
    }
/** Allows you to search for a Part. */
    @FXML public void searchPartButton(ActionEvent event) {
        String term = SearchField.getText();
        ObservableList foundParts = ProjectC482.Model.Inventory.lookupPart(term);
        if(foundParts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("NO MATCH");
            alert.setHeaderText("Unable to locate a Part name with: " + term);
            alert.showAndWait();
        } else {
            PartsTableView.setItems(foundParts);
        }
    }
/** Allows you to decide if you want to cancel a product. */
    @FXML public void modifyProductCancel(ActionEvent event) throws IOException {
        if (MainWindowController.confirmDialog("Cancel?", "Are you sure?")) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            stage.setTitle("Inventory Management System");
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }
    }

    @FXML void modifyProductSave(ActionEvent event) throws IOException {
        String field= "";
        String msg= "";
        JTextField jt = new JTextField(40);
        try{
            field= "Inventory";
            msg= "Inventory should be numeric";
            int productInventory = Integer.parseInt(Inventory.getText());
            field= "minimum";
            msg= "Minimum should be numeric";
            int productMinimum = Integer.parseInt(Minimum.getText());
            field= "maximum";
            msg= "Maximum should be numeric";
            int productMaximum = Integer.parseInt(Maximum.getText());
            field= "Name";
            msg= "Name should not be empty";
            if (Name.getText().trim().length() == 0) {
                throw new Exception(field+" "+msg);}
            field= "Price";
            msg= " Price should be Numeric";
            if(Price.getText()== toString());

            if(MainWindowController.confirmDialog("Save?", "Would you like to save this part?"))
                if(productMaximum < productMinimum) {
                    MainWindowController.infoDialog("Input Error", "Error in min and max field", "Check Min and Max value." );
                }
                else if(productInventory < productMinimum || productInventory > productMaximum) {
                    MainWindowController.infoDialog("Input Error", "Error in inventory field", "Inventory must be between Minimum and Maximum" );
                }
                else {
                    this.modProduct = selectedProduct;
                    { jt.setEditable(false);
                    selectedProduct.setProductID(Integer.parseInt(ID.getText()));}
                    selectedProduct.setName(Name.getText());
                    selectedProduct.setStock(Integer.parseInt(Inventory.getText()));
                    selectedProduct.setProductPrice(Double.parseDouble(Price.getText()));
                    selectedProduct.setMax(Integer.parseInt(Maximum.getText()));
                    selectedProduct.setMin(Integer.parseInt(Minimum.getText()));
                    selectedProduct.getAllAssociatedParts().clear();
                    selectedProduct.addAssociatedPart(associatedParts);
                    ProjectC482.Model.Inventory.updateProduct(productID, selectedProduct);

                    //Back to Main Screen
                    stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
                    stage.setTitle("Inventory Management System");
                    stage.setScene(new Scene((Parent) scene));
                    stage.show();
                }
        }
       catch (Exception e){
           MainWindowController.infoDialog("Input Error", field, msg );
       }
    }

    /** This adds a part to the product list */
    @FXML void addPartToProduct(ActionEvent event) {
        Parts selectedPart = PartsTableView.getSelectionModel().getSelectedItem();

        if(selectedPart != null) {
            associatedParts.add(selectedPart);
            updateAssociatedPartTable();
        }

        else {
            MainWindowController.infoDialog("Select a part","Select a part", "Select a part to add to the Product");
        }
    }

    @FXML
    void deletePartFromProduct(ActionEvent event) {
        Parts selectedPart = AssociatedPartsTableView.getSelectionModel().getSelectedItem();

        if(selectedPart != null) {
            MainWindowController.confirmDialog("Deleting Parts","Are you sure you want to delete " + selectedPart.getName() + " from the Product?");
            associatedParts.remove(selectedPart);
            updateAssociatedPartTable();
        }

        else {
            MainWindowController.infoDialog("No Selection","No Selection", "Please choose something to remove");
        }
    }
 /** Updates the Parts table */
    public void updatePartTable() {
        PartsTableView.setItems(ProjectC482.Model.Inventory.getAllParts());
    }

    private void updateAssociatedPartTable() {
        AssociatedPartsTableView.setItems(associatedParts);
    }

    /**Initializes the Add Product Scene with the Column, Fields, and Table View*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Columns and Table for un-associated parts.
        PartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        PartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartsCostColumn.setCellValueFactory(new PropertyValueFactory<>("partCost"));
        PartsTableView.setItems(ProjectC482.Model.Inventory.getAllParts());

        //Columns and Table for associated parts
        AssociatedPartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        AssociatedPartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedPartsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPartsCostColumn.setCellValueFactory(new PropertyValueFactory<>("partCost"));
        AssociatedPartsTableView.setItems(associatedParts);

        updatePartTable();
        updateAssociatedPartTable();
    }
}

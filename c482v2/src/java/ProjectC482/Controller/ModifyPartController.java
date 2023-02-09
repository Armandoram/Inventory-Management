package ProjectC482.Controller;
/**
 * @author
 * Armando Ramirez
 * Student ID: 010095862
 */

import ProjectC482.Model.InHouse;
import ProjectC482.Model.OutSourced;
import ProjectC482.Model.Parts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/** Creates an ModifyPartController class for the AddPart XML Scene */
public class ModifyPartController implements Initializable {

    @FXML private RadioButton outsourced;
    @FXML private RadioButton inHouse;
    @FXML private Label inhouseoroutsourced;
    @FXML private TextField ID;
    @FXML private TextField Name;
    @FXML private TextField Inventory;
    @FXML private TextField Price;
    @FXML private TextField Maximum;
    @FXML private TextField Minimum;
    @FXML private TextField companyORmachineID;

    private Stage stage;
    private Object scene;
    public Parts selectedPart;
    private int partID;

    /** Decides to wether or not use radioadd */
    public void radioadd()
    {
        if (outsourced.isSelected())
            this.inhouseoroutsourced.setText("Company Name");
        else
            this.inhouseoroutsourced.setText("Machine ID");
    }

    /** Sets the information and being able to modify the Parts. */
    public void setParts(Parts selectedPart) {
        this.selectedPart = selectedPart;
        partID = ProjectC482.Model.Inventory.getAllParts().indexOf(selectedPart);
        ID.setText(Integer.toString(selectedPart.getPartID()));
        Name.setText(selectedPart.getName());
        Inventory.setText(Integer.toString(selectedPart.getStock()));
        Price.setText(Double.toString(selectedPart.getPartCost()));
        Maximum.setText(Integer.toString(selectedPart.getMax()));
        Minimum.setText(Integer.toString(selectedPart.getMin()));
        if(selectedPart instanceof InHouse){
            InHouse ih = (InHouse) selectedPart;
            inHouse.setSelected(true);
            this.inhouseoroutsourced.setText("Machine ID");
            companyORmachineID.setText(Integer.toString(ih.getMachineID()));
        }
        else{
            OutSourced os = (OutSourced) selectedPart;
            outsourced.setSelected(true);
            this.inhouseoroutsourced.setText("Company Name");
            companyORmachineID.setText(os.getCompanyName());
        }
    }


    @FXML void onActionSave(ActionEvent event) throws IOException {
        String field = "";
        String msg = "";
        try {
            field= "Inventory";
            msg="Inventory needs to be numeric.";
            int partInventory = Integer.parseInt(Inventory.getText());
            field= "minimum";
            msg= "Minimum should be numeric";
            int partMin = Integer.parseInt(Minimum.getText());
            field= "maximum";
            msg= "Maximum should be numeric";
            int partMax = Integer.parseInt(Maximum.getText());
            field= "Name";
            msg= "Name should not be empty";
            if (Name.getText().trim().length() == 0) {
                throw new Exception(field+" "+msg);}

            field= "Price";
            msg= " Price should be Numeric";
            if(Price.getText()== toString());
            if (outsourced.isSelected()&& companyORmachineID.getText().trim().length()==0){
                field= "Company Name";
                msg= "Company Name cannot be blank";
                throw new Exception("Company Name cannot be blank");}

            if (MainWindowController.confirmDialog("Save?", "Would you like to save this part?"))
                if (partMax < partMin) {
                    MainWindowController.infoDialog("Input Error", "Error in min and max field", "Check Min and Max value.");
                } else if (partInventory < partMin || partInventory > partMax) {
                    MainWindowController.infoDialog("Input Error", "Error in inventory field", "Inventory must be between Minimum and Maximum");
                } else {
                    int id = Integer.parseInt(ID.getText());
                    String name = Name.getText();
                    double price = Double.parseDouble(Price.getText());
                    int stock = Integer.parseInt(Inventory.getText());
                    int min = Integer.parseInt(Minimum.getText());
                    int max = Integer.parseInt(Maximum.getText());
                    if (inHouse.isSelected()) {
                        try {
                            int machineID = Integer.parseInt(companyORmachineID.getText());
                            InHouse temp = new InHouse(id, stock, min, max, name, price, machineID);
                            ProjectC482.Model.Inventory.updatePart(partID, temp);
                            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                            scene = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
                            stage.setTitle("Inventory Management System");
                            stage.setScene(new Scene((Parent) scene));
                            stage.show();
                        } catch (NumberFormatException e) {
                            MainWindowController.infoDialog("Input Error", "Check Machine ID ", "Machine ID can only be numeric");
                        }
                    } else {
                        String companyName = companyORmachineID.getText();
                        OutSourced temp = new OutSourced(id, stock, min, max, name, price, companyName);
                        ProjectC482.Model.Inventory.updatePart(partID, temp);
                        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
                        stage.setTitle("Inventory Management System");
                        stage.setScene(new Scene((Parent) scene));
                        stage.show();
                    }
                }
        }
                     catch(Exception e){
                        MainWindowController.infoDialog("Input Error", field, msg);
                    }

                }


    /** RUNTIME ERROR ( Cancels the action)
     *  Java exception occurred because the FXML loader was not formatted properly. */
    @FXML public void onActionCancel(ActionEvent event) throws IOException {
        if(MainWindowController.confirmDialog("Cancel?", "Are you sure?")) {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            stage.setTitle("Inventory Management System");
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }
    }
  /** Initializes the location of URL and the resources */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}

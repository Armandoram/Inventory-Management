package ProjectC482.Controller;

import ProjectC482.Model.InHouse;
import ProjectC482.Model.OutSourced;
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

import static ProjectC482.Model.Inventory.getAllParts;
/**
 * @author
 * Armando Ramirez
 * Student ID: 010095862
 */

/** Creates an AddPartController class for the AddPart XML Scene */
public class AddPartController implements Initializable {
    @FXML private RadioButton outsourced;
    @FXML private RadioButton inHouse;
    @FXML private Label inhouseoroutsourced;
    @FXML private TextField Name;
    @FXML private TextField Inventory;
    @FXML private TextField Price;
    @FXML private TextField Maximum;
    @FXML private TextField Minimum;
    @FXML private TextField companyORmachineID;
    private Stage stage;
    private Object scene;

    /**radioadd is not used*/
    public void radioadd()
    {
        if (outsourced.isSelected())
            this.inhouseoroutsourced.setText("Company Name");
        else
            this.inhouseoroutsourced.setText("Machine ID");
    }
    /** On action the event is canceled*/
    @FXML public void onActionCancel(ActionEvent event) throws IOException {
        if(MainWindowController.confirmDialog("Cancel?", "Are you sure?")) {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            stage.setTitle("Inventory Management System");
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }
    }

    /** FUTURE ENHANCEMENT (On action the event is saved)
     * Having an auto save feature could save time within the application. */
    @FXML public void onActionSave(ActionEvent event) {
        String field= "";
        String msg= "";

        try {
           field= "Inventory";
           msg="Inventory needs to be numeric.";
           boolean flag= true;
            int partInventory = Integer.parseInt(Inventory.getText());
            field="Price";
            msg="Price should be numeric";
            int partPrice = Integer.parseInt(Price.getText());
            field= "minimum";
            msg= "Minimum should be numeric";
            int partMin = Integer.parseInt(Minimum.getText());
            field= "maximum";
            msg= "Maximum should be numeric";
            int partMax = Integer.parseInt(Maximum.getText());
            System.out.println(outsourced.isSelected());
            if (outsourced.isSelected() && companyORmachineID.getText().length() < 1){
                field= "Company Name";
                msg= "Company Name cannot be blank";
                throw new Exception("Company Name cannot be blank");
            }
            if (inHouse.isSelected()){
                try {
                    int partMachineID = Integer.parseInt(companyORmachineID.getText());

                } catch(NumberFormatException e)
                { field= "Machine ID";
                msg= "Machine ID should be numeric";
                }

            }

            if(MainWindowController.confirmDialog("Save?", "Would you like to save this part?"))
                if(partMax < partMin) {
                    MainWindowController.infoDialog("Input Error", "Error in min and max field", "Check Min and Max value." );
                    flag = false;
                }

            if(partInventory < partMin || partInventory> partMax) {
                    MainWindowController.infoDialog("Input Error", "Error in inventory field", "Inventory must be between Minimum and Maximum" );
                flag = false;
                }
            if (Name.getText().trim().length()==0){
                MainWindowController.infoDialog("Input Error", "Error in name field", "Name cannot be blank");
                flag = false;
            }
                if (flag == true){
                    int newID = getNewID();
                    String name = Name.getText();
                    int stock = partInventory;
                    double price = Double.parseDouble(Price.getText());
                    int min = partMin;
                    int max = partMax;
                    if (outsourced.isSelected()) {
                        String companyName = companyORmachineID.getText();
                        OutSourced temp = new OutSourced(newID, stock, min, max, name, price, companyName);
                        ProjectC482.Model.Inventory.addPart(temp);
                    } else {
                        int machineID = Integer.parseInt(companyORmachineID.getText());
                        InHouse temp = new InHouse(newID, stock, min, max, name, price, machineID);
                        ProjectC482.Model.Inventory.addPart(temp);
                    }
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
 /** Returns a new ID that has been made */
    public static int getNewID(){
        int newID = 1;
        for (int i = 0; i < getAllParts().size(); i++) {
            newID++;
        }
        return newID;
    }
    /** Initializes the URL Location and resources */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}


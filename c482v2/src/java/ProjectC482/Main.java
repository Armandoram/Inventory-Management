package ProjectC482;

/**
 * @author
 * Armando Ramirez
 * Student ID: 010095862
 */

import ProjectC482.Model.InHouse;
import ProjectC482.Model.Inventory;
import ProjectC482.Model.OutSourced;
import ProjectC482.Model.Products;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

    /** Contains the Main Screen and inventory of the Inventor Management system */
public class Main extends Application {
    /** Starts the application */
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Add Parts InHouse (BMX BIKES)
        Inventory.addPart(new InHouse(1, 23, 1, 50, "SE Big Flyer", 759.99, 19));
        Inventory.addPart(new InHouse(2, 5, 1, 50, "SE Big Ripper", 859.99, 12));
        Inventory.addPart(new InHouse(3, 13, 1, 50, "SE PK Ripper", 999.99, 82));
        Inventory.addPart(new InHouse(4, 7, 1, 50, "SE Fat Quad", 1299.99, 99));

        //Add Parts OutSourced
        Inventory.addPart(new OutSourced(5, 5, 1, 50, "Stealth Bike", 269.99, "Elite BMX"));
        Inventory.addPart(new OutSourced(6, 25, 1, 50, "Destroy Bike", 299.00, "Elite BMX"));
        Inventory.addPart(new OutSourced(7, 10, 1, 50, "Shocker Fixie Bike", 289.00, "Golden Cycles"));
        Inventory.addPart(new OutSourced(8, 35, 1, 50, "Shredder Pro", 389.99, "Haro"));
        Inventory.addPart(new OutSourced(9, 12, 1, 50, "Black Label V2", 999.99, "State Bicycle"));
        Inventory.addPart(new OutSourced(10, 30, 1, 50, "Quick Cx 4 Commuter", 749.99, "Cannondale"));
        Inventory.addPart(new OutSourced(11, 45, 1, 50, "Treadwell 3 Commuter", 49.99, "Cannondale"));

        //Add Products
        Inventory.addProduct(new Products(1, 3, 1, 50, "SE Racing Combo", 1229.99));
        Inventory.addProduct(new Products(2, 4, 1, 50, "Elite BMX Starter Pack", 599.99));
        Inventory.addProduct(new Products(3, 2, 1, 50, "Cannondale Commuter Brake Pack", 250.00));

/** RUNTIME ERROR
 * Exception in Application start method java.lang.reflect. Added VM Option to locate Java FX
 */

        Parent root = FXMLLoader.load(getClass().getResource("Controller/MainWindow.fxml"));
        Scene scene = new Scene(root, 1280, 580);
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    /** Javadoc submitted as separate file
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

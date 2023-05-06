package ooploverz.tubes2_oop;

// App
import javafx.application.Application;

// event
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

// scene
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Box;

// stage
import javafx.stage.Stage;
import javafx.stage.Screen;

// Geometry
import javafx.geometry.Rectangle2D;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class HalamanUtama extends Application{
    /* Set screen size constant */
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private final double WINDOW_HEIGHT = primaryScreenBounds.getHeight() * 0.97;
    private final double WINDOW_WIDTH  = primaryScreenBounds.getWidth();

    @Override
    public void start(Stage primaryStage) throws Exception {
        /* Root Node */
        StackPane root = new StackPane();

        /* Navbar Panel */
        HBox navbar = new HBox();
        navbar.getStyleClass().add("navbar");

        // Navbar buttons
        Button home = new Button();
        home.getStyleClass().add("nav-button");
        home.setId("home-button");

        Button membership = new Button("Membership");
        membership.getStyleClass().add("nav-button");

        Button cart = new Button("Cart");
        cart.getStyleClass().add("nav-button");

        Button payment = new Button("Payment");
        payment.getStyleClass().add("nav-button");

        Button inventory = new Button("Inventory");
        inventory.getStyleClass().add("nav-button");

        Button history = new Button("History");
        history.getStyleClass().add("nav-button");

        Button settings = new Button("Settings");
        settings.getStyleClass().add("nav-button");
//        home.setId("settings-button");


        navbar.getChildren().addAll(home, membership, cart, payment, inventory, history, settings);

        /* Logo */

        /* Digital Clock */

        /* Developers */

        /* Footer */






        // Setup Akhir Scene
        root.getChildren().addAll(navbar);
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("Yonkou Mart");
        primaryStage.setScene(scene);
        scene.getStylesheets().add
                (HalamanUtama.class.getResource("mainWindow.css").toExternalForm());
        primaryStage.show();
    }

    public static void main (String[] args)
    {
        launch(args);
    }

}
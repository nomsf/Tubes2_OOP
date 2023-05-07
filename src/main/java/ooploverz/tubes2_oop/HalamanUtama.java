package ooploverz.tubes2_oop;

// Net
import  java.net.URL;

// App
import javafx.application.Application;

// Event

// scene
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

// Stage
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.Screen;

// Geometry
import javafx.geometry.Rectangle2D;

// Animation
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

// Util
import javafx.util.Duration;
import ooploverz.tubes2_oop.util.DateTime;

public class HalamanUtama extends Application{
    /* Set screen size constant */

    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private final double WINDOW_HEIGHT = primaryScreenBounds.getHeight() * 0.97;
    private final double WINDOW_WIDTH  = primaryScreenBounds.getWidth();
    private TabPane tabPane;
    private Label clockDate; // Label untuk hari dan tanggal
    private Label clockTime; // Label untuk jam dan menit

    private void addTab(String title, Node content) {
        System.out.println("Masuk");
        Tab tab = new Tab(title);
        tab.setContent(content);
        tabPane.getTabs().add(tab);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Panel
        tabPane = new TabPane(); // Panel untuk tampung semua tab

        // First Tab
        Tab tab1 = new Tab("Home");
        tab1.setClosable(false);

        /* Root Node */
        VBox root = new VBox();
        root.getStyleClass().add("content-wrapper");


        /* Menubar Panel */

        // create a menubar
        MenuBar menuBar = new MenuBar();
        menuBar.getStyleClass().add("menubar");

        // create menus

        // Membership
        Menu membershipMenu = new Menu("Membership");
        membershipMenu.getStyleClass().add("menu");

        MenuItem memberRegistration = new MenuItem("Member Registration");
        memberRegistration.setOnAction(event -> {
            VBox newRoot = new VBox();
            addTab("Member Registration", newRoot);
        });

        MenuItem membershipUpdate = new MenuItem("Update Membership");
        membershipUpdate.setOnAction(event -> {
            VBox newRoot = new VBox();
            addTab("Update Membership", newRoot);
        });

        MenuItem membershipDeactivate = new MenuItem("Deactivate Membership");
        membershipDeactivate.setOnAction(event -> {
            VBox newRoot = new VBox();
            addTab("Deactivate Membership", newRoot);
        });

        // add menu items to menu
        membershipMenu.getItems().addAll(memberRegistration, membershipUpdate, membershipDeactivate);

        // Cart
        Menu cartMenu = new Menu("Cart");
        MenuItem cart = new MenuItem("Cart");
        cartMenu.getItems().add(cart);
        cart.setOnAction(event -> {
                    VBox newRoot = new VBox();
                    addTab("Cart", newRoot);
                }
        );

        // Payment
        Menu paymentMenu = new Menu("Payment");
        MenuItem payment = new MenuItem("Payment");
        paymentMenu.getItems().add(payment);
        payment.setOnAction(event -> {
                    VBox newRoot = new VBox();
                    addTab("Payment", newRoot);
                }
        );

        // Inventory
        Menu inventoryMenu = new Menu("Inventory");
        MenuItem inventory = new MenuItem("Inventory");
        inventoryMenu.getItems().add(inventory);
        inventory.setOnAction(event -> {
            VBox newRoot = new VBox();
            addTab("Inventory", newRoot);
        });

        // History
        Menu historyMenu = new Menu("History");
        MenuItem history = new MenuItem("History");
        historyMenu.getItems().add(history);
        history.setOnAction(event -> {
            VBox newRoot = new VBox();
            addTab("History", newRoot);
        });

        // Settings
        Menu settingMenu =  new Menu("Settings");
        MenuItem settings = new MenuItem("Settings");
        settingMenu.getItems().add(settings);
        settings.setOnAction(event -> {
            VBox newRoot = new VBox();
            addTab("Settings", newRoot);
        });

        // add menus to menubar
        menuBar.getMenus().addAll( membershipMenu, cartMenu, paymentMenu, inventoryMenu, historyMenu, settingMenu);


        /* Logo */

        Circle logoWrapper = new Circle(150);
        logoWrapper.setId("logo-wrapper");
        Image logo = new Image(HalamanUtama.class.getResource("logo.png").toExternalForm());
        ImageView logoView = new ImageView(logo);
        StackPane logoPanel = new StackPane(logoWrapper,logoView);
        logoPanel.getStyleClass().add("logo-panel");


        /* Digital Clock */

        Rectangle clockWrapper = new Rectangle(600, 145);
        clockWrapper.setId("clock-wrapper");

        VBox clockContainer = new VBox();
        clockContainer.setId("clock-container");
        // Label Hari dan Tanggal
        clockDate = new Label();
        clockDate.setId("clock-date");
        // Label Jam dan Menit
        clockTime = new Label();
        clockTime.setId("clock-time");

        clockContainer.getChildren().addAll(clockDate, clockTime);
        StackPane clockPanel = new StackPane(clockWrapper, clockContainer);
        clockPanel.getStyleClass().add("clock-panel");


        /* Developers */
        Rectangle developerWrapper = new Rectangle(500, 200);
        developerWrapper.setId("developer-wrapper");

        VBox developerContainer = new VBox();

        // Header
        Label developerHeader = new Label("Developers");
        developerHeader.setId("developer-header");

        // Developer
        Label developer1 = new Label("13521050      Naufal Syifa Firdaus");
        Label developer2 = new Label("13521058      Ghazi Akmal Fauzan");
        Label developer3 = new Label("13521066      Muhammad Fadhil Amri");
        Label developer4 = new Label("13521070      Akmal Mahardika Nurwahyu P");
        Label developer5 = new Label("13521168      Satria Oktavianus Nababan");

        developer1.getStyleClass().add("developer");
        developer2.getStyleClass().add("developer");
        developer3.getStyleClass().add("developer");
        developer4.getStyleClass().add("developer");
        developer5.getStyleClass().add("developer");

        developerContainer.getChildren().addAll(developerHeader, developer1, developer2, developer3, developer4, developer5);
        developerContainer.setId("developer-container");
        StackPane developerPanel = new StackPane(developerWrapper, developerContainer);
        developerPanel.getStyleClass().add("developer-panel");

        // Add to root
        root.getChildren().addAll(menuBar, logoPanel, clockPanel, developerPanel);

        // add to tab1
        tab1.setContent(root);
        tab1.getStyleClass().add("tab");

        // Setup Akhir Scene
        tabPane.getTabs().add(tab1);
        Scene scene = new Scene(tabPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("Yonkou Mart");
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        scene.getStylesheets().add
                (HalamanUtama.class.getResource("mainWindow.css").toExternalForm());
        primaryStage.show();
        
        Thread digitalClock = new Thread(new UpdateDigitalClock());
        digitalClock.setDaemon(true); // Background Thread
        digitalClock.start();
    }
}

    private class UpdateDigitalClock implements  Runnable {
        @Override
        public void run(){
            while (true){
                // Update the clock date and clock time label on the JavaFX Application Thread
                Platform.runLater(() -> {
                        DateTime time = new DateTime();
                        clockDate.setText(time.getDayName() + ", " + time.getDay()+ " " + time.getMonthName()+ " " + time.getYear());
                        clockTime.setText(time.getTime());
                }
                );

                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main (String[] args)
    {
        launch(args);
    }

}
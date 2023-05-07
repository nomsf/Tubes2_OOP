package ooploverz.tubes2_oop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import lombok.Getter;
import ooploverz.tubes2_oop.Bill.Bill;
import ooploverz.tubes2_oop.Bill.FixedBill;
import ooploverz.tubes2_oop.inventory.Inventory;
import ooploverz.tubes2_oop.inventory.Item;

import java.util.Objects;

public class CartPage {
    @Getter private VBox cartPageContainer;
    @Getter private Bill savedBill;
    private VBox cartContainer;
    private VBox itemListContainer;
    private  VBox itemInCart;
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private final double WINDOW_HEIGHT = primaryScreenBounds.getHeight() * 0.97;
    private final double WINDOW_WIDTH  = primaryScreenBounds.getWidth();

    public CartPage(){
        this.savedBill = new Bill();
        cartPageContainer = new VBox();
        cartPageContainer.setAlignment(Pos.CENTER);

        cartContainer = new VBox();
        cartContainer.setAlignment(Pos.CENTER);
        cartContainer.setSpacing(20);

        itemListContainer = new VBox();

        // Name Field
        GridPane nameField = new GridPane();
        nameField.setHgap(10);
        nameField.setAlignment(Pos.CENTER);
        Label labelName = new Label("Costumer Name:");
        TextField textFieldName = new TextField();
        nameField.add(labelName, 0,0);
        nameField.add(textFieldName, 1,0);
        cartContainer.getChildren().add(nameField);

        // Item in Cart
        this.itemInCart = new VBox();
        itemInCart.setAlignment(Pos.CENTER);
        itemInCart.setSpacing(10);
        addItemToCartGUI(new Item(1,"temp",1,1,"none","none"), false);
        cartContainer.getChildren().add(this.itemInCart);

        // All Item List
        allItem(tes());
        // Buttons
        GridPane buttons = new GridPane();
        buttons.setAlignment(Pos.CENTER);
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> { handleSave(); });
        Button checkoutButton = new Button("Checkout");
        checkoutButton.setOnAction(event -> { handleCheckout();});
        buttons.add(saveButton, 0,0);
        buttons.add(checkoutButton, 1,0);
        cartContainer.getChildren().add(buttons);

        cartPageContainer.getChildren().add(cartContainer);
        cartPageContainer.getChildren().add(itemListContainer);
    }

    public void addItemToCartGUI(Item addedItem, boolean item){
        String name;
        String price;

        if(item){
            name = addedItem.getName();
            price = Integer.toString(addedItem.getPrice()) ;
        }
        else {
            name = "Item in Cart";
            price = "Price";
        }

        GridPane soleItem = new GridPane();
        soleItem.setAlignment(Pos.CENTER);

        StackPane itemCont = new StackPane();
        itemCont.setPrefSize(600, 40);
        if(item) {
            itemCont.setStyle("-fx-background-color: #FFF;");
        }
        Label labelItem = new Label(name);
        itemCont.getChildren().add(labelItem);
        soleItem.add(itemCont, 0,0);

        StackPane priceCont = new StackPane();
        priceCont.setPrefSize(300, 40);
        if(item) {
            priceCont.setStyle("-fx-background-color: #FFF;");
        }
        Label labelPrice = new Label(price);
        priceCont.getChildren().add(labelPrice);
        soleItem.add(priceCont, 1,0);

        this.itemInCart.getChildren().add(soleItem);
    }

    public void handleSave(){
        // TODO : assign a costumer to the bill
    }

    public void handleCheckout(){
        handleSave();
        // TODO: turn bill into fixed bill
        FixedBill checkoutBill = new FixedBill(this.savedBill);
        checkoutBill.saveToDataStore();
    }

    public void allItem(Inventory inventory){

        ScrollPane scrollPane = new ScrollPane();

        // Set scroll pane properties
        scrollPane.setPrefWidth(this.itemInCart.getPrefWidth());
        scrollPane.setMaxWidth(this.itemInCart.getPrefWidth());
        scrollPane.setPrefHeight(this.WINDOW_HEIGHT);
        scrollPane.setMaxHeight(this.WINDOW_HEIGHT);
        scrollPane.setStyle("-fx-border-color: #9BCDFB; -fx-background: #9BCDFB;");

        // GridPane Properties
        GridPane itemGrid = new GridPane();
        itemGrid.setHgap(10);
        itemGrid.setAlignment(Pos.TOP_LEFT);
        itemGrid.setStyle("-fx-background-color: #9BCDFB;");

        int rowIndex = 0;
        int columnIndex = 0;
        for(Item item : inventory.getListItem()){
            ImageView image;
            // Image
            if (item.getImage() == null || item.getImage().equals("")) {
                image = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("no_image.jpg")).toExternalForm()));
            }
            else if (item.getImage().startsWith("https") || item.getImage().startsWith("http")) {
                image = new ImageView(new Image(item.getImage()));
            }
            else {
                image = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(item.getImage())).toExternalForm()));
            }

            image.setFitWidth((this.WINDOW_WIDTH / 5) - (itemGrid.getHgap() * 1.6));
            image.setFitHeight(image.getFitWidth());
            image.setOnMouseClicked(event -> {
                System.out.println("clicked");
                addItemToCartGUI(item, true);
            });
            itemGrid.add(image, columnIndex, rowIndex);

            // Name label
            Label nameLabel = new Label(item.getName() + " (" + item.getStock() + ")");
            nameLabel.setStyle("-fx-alignment: center; -fx-font-size: 15px;");
            nameLabel.setMaxWidth(image.getFitWidth());
            nameLabel.setAlignment(Pos.CENTER);
            itemGrid.add(nameLabel, columnIndex, rowIndex + 1);

            // Category label
            Label categoryLabel = new Label(item.getCategory() + "\n");
            categoryLabel.setStyle("-fx-alignment: center; -fx-font-size: 15px;");
            categoryLabel.setMaxWidth(image.getFitWidth());
            categoryLabel.setAlignment(Pos.CENTER);
            itemGrid.add(categoryLabel, columnIndex, rowIndex + 2);

            columnIndex++;

            // Handle if column index is more than 10
            if (columnIndex > 4) {
                columnIndex = 0;
                rowIndex += 3;
            }

            // add item to the bill
            this.savedBill.addItem(item);
            //System.out.println(savedBill.toString());
        }
        scrollPane.setContent(itemGrid);

        this.itemListContainer.getChildren().add(scrollPane);
    }


    public Inventory tes() {
        Inventory inventoryTest = new Inventory();
        inventoryTest.addItem(100, "Kacamata", 100, 100, "Kacamata", "https://image.uniqlo.com/UQ/ST3/AsianCommon/imagesgoods/447018/item/goods_80_447018.jpg?width=750");
        inventoryTest.addItem(100, "Kaos Kaki", 100, 100, "Kaos Kaki", "");
        inventoryTest.addItem(100, "Kemeja", 100, 100, "Kemeja", "");
        inventoryTest.addItem(100, "Jaket", 100, 100, "Jaket", "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2021/5/19/3c088906-1a62-40b7-bfda-7eee75b47655.jpg");
        inventoryTest.addItem(100, "Sandal", 100, 100, "Sandal", "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2021/5/19/3c088906-1a62-40b7-bfda-7eee75b47655.jpg");
        inventoryTest.addItem(100, "Tas", 100, 100, "Tas", "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2021/5/19/3c088906-1a62-40b7-bfda-7eee75b47655.jpg");
        inventoryTest.addItem(100, "Dompet", 100, 100, "Dompet", "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2021/5/19/3c088906-1a62-40b7-bfda-7eee75b47655.jpg");
        inventoryTest.addItem(100, "Koper", 100, 100, "Koper", "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2021/5/19/3c088906-1a62-40b7-bfda-7eee75b47655.jpg");
        inventoryTest.addItem(100, "Kaos", 100, 100, "Kaos", "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2021/5/19/3c088906-1a62-40b7-bfda-7eee75b47655.jpg");
        inventoryTest.addItem(100, "Kemeja", 100, 100, "Kemeja", "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2021/5/19/3c088906-1a62-40b7-bfda-7eee75b47655.jpg");
        inventoryTest.addItem(100, "Jaket", 100, 100, "Jaket", "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2021/5/19/3c088906-1a62-40b7-bfda-7eee75b47655.jpg");
        return inventoryTest;
    }
}

package ooploverz.tubes2_oop;

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

import ooploverz.tubes2_oop.bill.Bill;
import ooploverz.tubes2_oop.bill.FixedBill;
import ooploverz.tubes2_oop.bill.BillList;
import ooploverz.tubes2_oop.bill.FixedBillList;
import ooploverz.tubes2_oop.dataStore.DataInventory;
import ooploverz.tubes2_oop.customer.Customer;
import ooploverz.tubes2_oop.customer.ListOfMember;
import ooploverz.tubes2_oop.inventory.Inventory;
import ooploverz.tubes2_oop.inventory.Item;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Objects;

public class CartPage {
    @Getter private VBox cartPageContainer;
    @Getter private Bill savedBill;

    @Getter private FixedBill savedFixedBill;
    @Getter private boolean fixed;

    private final VBox cartContainer;
    private final VBox itemListContainer;
    private final VBox itemInCart;
    private final TextField nameInput;
    private static final BillList billList = new BillList();
    private static final FixedBillList fixedBillList = new FixedBillList();
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private final double WINDOW_HEIGHT = primaryScreenBounds.getHeight() * 0.97;
    private final double WINDOW_WIDTH  = primaryScreenBounds.getWidth();


    public CartPage(){
        fixed = false;
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
        this.nameInput = new TextField();
        nameField.add(labelName, 0,0);
        nameField.add(this.nameInput, 1,0);
        cartContainer.getChildren().add(nameField);

        // Item in Cart
        this.itemInCart = new VBox();
        itemInCart.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        itemInCart.setAlignment(Pos.TOP_CENTER);
        itemInCart.setSpacing(10);
        addItemToCartGUI(new Item(1,"temp",1,1,"none","none"), false);

        // Add itemInCart to ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxHeight(WINDOW_HEIGHT * 0.75);
        scrollPane.setPrefHeight(WINDOW_HEIGHT * 0.75);
        scrollPane.setPrefWidth(WINDOW_WIDTH * 0.5);
        scrollPane.setPrefWidth(WINDOW_WIDTH * 0.5);
        scrollPane.setStyle("-fx-background: #9BCDFB; -fx-border-color: #9BCDFB;");
        scrollPane.setContent(itemInCart);

        cartContainer.getChildren().add(scrollPane);

        // Get inventory data
        JSONArray data = DataInventory.getData();
        Inventory inventory = new Inventory();
        try {
            for (int i = 0; i < data.length(); i++) {
                inventory.addItem(data.getJSONObject(i).getInt("stock"), data.getJSONObject(i).getString("name"), data.getJSONObject(i).getInt("price"), data.getJSONObject(i).getInt("buyPrice"), data.getJSONObject(i).getString("category"), data.getJSONObject(i).getString("image"));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        // All Item List
        allItem(inventory);

        // Buttons
        GridPane buttons = new GridPane();
        buttons.setAlignment(Pos.CENTER);
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> handleSave());
        Button checkoutButton = new Button("Checkout");
        checkoutButton.setOnAction(event -> handleCheckout());
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
        boolean isMember = false;
        if(!this.nameInput.getText().isEmpty()){

                ListOfMember memberList = new ListOfMember();
                int id = memberList.searchMember(this.nameInput.getText());
                if(id != -1){
                    this.savedBill.setBuyerId(id);
                    isMember = true;
                }
        }

        if(!isMember){
            Customer newCostumer = new Customer();
            this.savedBill.setBuyerId(newCostumer.getCustomerId());
        }

        // TODO : synchronize costumer database with bill

    }

    public void handlePay(){
        // TODO : save fixed bill and change to payed.
        savedFixedBill.pay();
        fixedBillList.addBill(this.savedFixedBill);
        fixedBillList.saveData();
    }

    public void handleCheckout(){
        fixed = true;

        // add bill to billList
        billList.addBill(this.savedBill);

        // Convert Bill to Fixed Bill and add to fixedBillList
        this.savedFixedBill = new FixedBill(this.savedBill);

        // saved to database
        billList.saveData();

        Node delete = null;
        for(Node children : this.cartContainer.getChildren()){
            System.out.println(children);
            if(children instanceof GridPane && ((GridPane) children).getChildren().get(0) instanceof Button){
                delete = children;
            }
        }
        if(delete != null){
            this.cartContainer.getChildren().remove(delete);
        }

        Button payButton = new Button("Pay");
        payButton.setOnAction(actionEvent -> handlePay());

        this.cartContainer.getChildren().add(payButton);

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
                if(!fixed){
                    System.out.println(this.savedBill);
                    addItemToCartGUI(item, true);
                    this.savedBill.addItem(item);
                }

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

        }
        scrollPane.setContent(itemGrid);

        this.itemListContainer.getChildren().add(scrollPane);
    }
}

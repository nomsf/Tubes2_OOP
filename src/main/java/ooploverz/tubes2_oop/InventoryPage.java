package ooploverz.tubes2_oop;

import ooploverz.tubes2_oop.inventory.*;

import javafx.scene.control.*;
import javafx.scene.Cursor;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;

import java.util.Objects;

public class InventoryPage {
    private final HBox root = new HBox();
    private final TextField searchBox = new TextField();
    private final ScrollPane scrollPane = new ScrollPane();
    private final GridPane gridPane = new GridPane();
    private final TextField stockBox = new TextField();
    private final TextField nameBox = new TextField();
    private final TextField priceBox = new TextField();
    private final TextField buyPriceBox = new TextField();
    private final TextField categoryBox = new TextField();
    private final TextField imageBox = new TextField();
    private final Button saveButton = new Button("Save");
    private final Button deleteButton = new Button("Delete");
    private final TextArea messageBox = new TextArea("Message Box:");

    private String selectedItem = "";

    public InventoryPage(Inventory inventory) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double WINDOW_HEIGHT = primaryScreenBounds.getHeight() * 0.97;
        double WINDOW_WIDTH = primaryScreenBounds.getWidth();

        /*
        * LEFT SIDE PANEL
        */
        // Set vertical box properties
        VBox leftVBOX = new VBox();
        leftVBOX.setPrefWidth(WINDOW_WIDTH * 0.7);
        leftVBOX.setMaxWidth(WINDOW_WIDTH * 0.7);
        leftVBOX.setStyle("-fx-border-color: #000000; -fx-padding: 20px;");

        // Set scroll pane properties
        scrollPane.setPrefWidth(leftVBOX.getPrefWidth());
        scrollPane.setMaxWidth(leftVBOX.getPrefWidth());
        scrollPane.setPrefHeight(WINDOW_HEIGHT * 0.85);
        scrollPane.setMaxHeight(WINDOW_HEIGHT * 0.85);
        scrollPane.setStyle("-fx-border-color: #9BCDFB; -fx-background: #9BCDFB;");

        // Set search box properties
        searchBox.setPromptText("Search");
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");

        // Handle search box event
        searchBox.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                Inventory filteredInventory = inventory.filterInventory(searchBox.getText());
                messageBox.setText("Message Box:\n" + "Filtered " + filteredInventory.getTotalItem() + " item(s) out of " + inventory.getTotalItem() + " item(s).");
                updateInventory(filteredInventory);
            }
        });

        // Set text header properties
        Label header = new Label("Item List:");
        header.setPadding(new Insets(30, 0, 0, 0));
        header.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        // Set grid pane properties
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setStyle("-fx-background-color: #9BCDFB;");

        // Add inventory to grid pane
        updateInventory(inventory);

        // Insert grid pane to scroll pane
        scrollPane.setContent(gridPane);

        // Insert search box, header, and scroll pane to vertical box
        leftVBOX.getChildren().addAll(searchBox, header, scrollPane);


        /*
        * RIGHT SIDE PANEL
         */
        // Set vertical box properties
        VBox rightVBOX = new VBox();
        rightVBOX.setPrefWidth(WINDOW_WIDTH * 0.3);
        rightVBOX.setSpacing(20);
        rightVBOX.setStyle("-fx-border-color: #000000; -fx-padding: 20px;");

        // Set text field properties
        stockBox.setPromptText("Stock");
        stockBox.setAlignment(Pos.CENTER);
        stockBox.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");

        nameBox.setPromptText("Name");
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");

        priceBox.setPromptText("Price");
        priceBox.setAlignment(Pos.CENTER);
        priceBox.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");

        buyPriceBox.setPromptText("Buy Price");
        buyPriceBox.setAlignment(Pos.CENTER);
        buyPriceBox.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");

        categoryBox.setPromptText("Category");
        categoryBox.setAlignment(Pos.CENTER);
        categoryBox.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");

        imageBox.setPromptText("Image");
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");

        // Set button properties
        saveButton.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");
        // Handle cursor
        saveButton.setOnMouseEntered(event -> saveButton.setCursor(Cursor.HAND));
        saveButton.setOnMouseExited(event -> saveButton.setCursor(Cursor.DEFAULT));
        // Handle save button event
        saveButton.setOnAction(e -> {
            if (Objects.equals(saveButton.getText(), "Add")) {
                // check if all fields are filled
                if (stockBox.getText().isEmpty() || nameBox.getText().isEmpty() || priceBox.getText().isEmpty()) {
                    messageBox.setText("Message Box:\n" + "Please fill the mandatory fields (Stock, Name, Price).");
                }
                else {
                    try {
                        int stock = Integer.parseInt(stockBox.getText());
                        String name = nameBox.getText();
                        int price = Integer.parseInt(priceBox.getText());

                        int buyPrice;
                        if (buyPriceBox.getText().isEmpty())
                            buyPrice = 0;
                        else
                            buyPrice = Integer.parseInt(buyPriceBox.getText());

                        String category = categoryBox.getText();

                        String image = imageBox.getText();
                        ImageView imageData;

                        try {
                            if (image == null || image.equals("")) {
                                imageData = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("no_image.jpg")).toExternalForm()));
                            } else if (image.startsWith("https") || image.startsWith("http")) {
                                imageData = new ImageView(new Image(image));
                            } else if (image.endsWith(".jpg") || image.endsWith(".png")) {
                                imageData = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(image)).toExternalForm()));
                            } else {
                                throw new Exception();
                            }
                        } catch (Exception exception) {
                            messageBox.setText("Message Box:\n" + "Image not found.");
                            return;
                        }

                        if (imageData.getImage().getWidth() == 0 || imageData.getImage().getHeight() == 0) {
                            messageBox.setText("Message Box:\n" + "Image not found.");
                            return;
                        }

                        if (price < 0 || stock < 0 || buyPrice < 0) throw new NumberFormatException();

                        inventory.addItem(stock, name, price, buyPrice, category, image);
                        messageBox.setText("Message Box:\n" + name + " added successfully.");
                        updateInventory(inventory);
                    } catch (NumberFormatException exception) {
                        messageBox.setText("Message Box:\n" + "Invalid input.");
                    }
                }
            }
            else {
                if (Objects.equals(selectedItem, "")) {
                    messageBox.setText("Message Box:\n" + "Please select an item.");
                }
                else {
                    try {
                        int stock = Integer.parseInt(stockBox.getText());
                        String newName = nameBox.getText();
                        int price = Integer.parseInt(priceBox.getText());
                        int buyPrice = Integer.parseInt(buyPriceBox.getText());
                        String category = categoryBox.getText();
                        String image = imageBox.getText();
                        inventory.editItem(selectedItem, stock, newName, price, buyPrice, category, image);
                        messageBox.setText("Message Box:\n" + selectedItem + " edited successfully.");
                        updateInventory(inventory);
                    } catch (NumberFormatException exception) {
                        messageBox.setText("Message Box:\n" + "Invalid input.");
                    }
                }
            }
        });

        deleteButton.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");
        // Handle cursor
        deleteButton.setOnMouseEntered(event -> deleteButton.setCursor(Cursor.HAND));
        deleteButton.setOnMouseExited(event -> deleteButton.setCursor(Cursor.DEFAULT));

        // Handle delete button event
        deleteButton.setOnAction(e -> {
            if (Objects.equals(deleteButton.getText(), "Delete")) {
                if (!Objects.equals(selectedItem, "")) {
                    inventory.removeItem(selectedItem);
                    messageBox.setText("Message Box:\n" + selectedItem + " deleted successfully.");
                    updateInventory(inventory);
                }
                else
                    messageBox.setText("Message Box:\n" + "No item selected.");
            }
            else {
                stockBox.clear();
                nameBox.clear();
                priceBox.clear();
                buyPriceBox.clear();
                categoryBox.clear();
                imageBox.clear();
                messageBox.setText("Message Box:\n" + "Item cleared successfully.");
            }
        });

        // Set message box properties
        messageBox.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");
        messageBox.setEditable(false);

        // Add text fields and buttons to vertical box
        rightVBOX.getChildren().addAll(stockBox, nameBox, priceBox, buyPriceBox, categoryBox, imageBox, saveButton, deleteButton, messageBox);

        // Insert vertical box to root
        root.getChildren().addAll(leftVBOX, rightVBOX);
    }

    public void updateInventory(Inventory inventory) {
        // Clear grid pane
        gridPane.getChildren().clear();

        // Add item rows
        int rowIndex = 0;
        int columnIndex = 0;

        // Add new item image
        ImageView newItemImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("new_item.png")).toExternalForm()));
        newItemImage.setFitWidth((scrollPane.getPrefWidth() / 5) - (gridPane.getHgap() * 1.6));
        newItemImage.setFitHeight(newItemImage.getFitWidth());

        // Handle new item image on mouse clicked
        newItemImage.setOnMouseClicked(event -> {
            stockBox.clear();
            nameBox.clear();
            priceBox.clear();
            buyPriceBox.clear();
            categoryBox.clear();
            imageBox.clear();
            messageBox.setText("Message Box:");
            saveButton.setText("Add");
            deleteButton.setText("Cancel");
            for (int i = 1; i < gridPane.getChildren().size(); i++) {
                if (gridPane.getChildren().get(i) instanceof ImageView) {
                    gridPane.getChildren().get(i).setOpacity(0.6);
                }
            }
        });

        // Handle new item image on mouse entered and exited
        newItemImage.setOnMouseEntered(event -> newItemImage.setCursor(Cursor.HAND));
        newItemImage.setOnMouseExited(event -> newItemImage.setCursor(Cursor.DEFAULT));

        // Add new item image to grid pane
        gridPane.add(newItemImage, columnIndex, rowIndex);

        // Add new item label
        Label newItemLabel = new Label("Add New Item");
        newItemLabel.setStyle("-fx-alignment: center; -fx-font-size: 15px;");
        newItemLabel.setMaxWidth(newItemImage.getFitWidth());
        newItemLabel.setAlignment(Pos.CENTER);
        gridPane.add(newItemLabel, columnIndex, rowIndex + 1);

        columnIndex++;

        for (Item item : inventory.getListItem()) {
            ImageView image;
            // Image
            if (item.getImage() == null || item.getImage().equals("")) {
                image = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("no_image.jpg")).toExternalForm()));
            } else if (item.getImage().startsWith("https") || item.getImage().startsWith("http")) {
                image = new ImageView(new Image(item.getImage()));
            } else {
                image = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(item.getImage())).toExternalForm()));
            }

            image.setFitWidth((scrollPane.getPrefWidth() / 5) - (gridPane.getHgap() * 1.6));
            image.setFitHeight(image.getFitWidth());
            image.setOpacity(0.6);

            // Handle image on mouse clicked
            image.setOnMouseClicked(event -> {
                selectedItem = item.getName();
                nameBox.setText(item.getName());
                stockBox.setText(Integer.toString(item.getStock()));
                priceBox.setText(Integer.toString(item.getPrice()));
                buyPriceBox.setText(Integer.toString(item.getBuyPrice()));
                categoryBox.setText(item.getCategory());
                imageBox.setText(item.getImage());
                messageBox.setText("Message Box:\n" + item.getName() + " is selected.");
                for (int i = 1; i < gridPane.getChildren().size(); i++) {
                    if (gridPane.getChildren().get(i) instanceof ImageView) {
                        gridPane.getChildren().get(i).setOpacity(0.6);
                    }
                }
                image.setOpacity(1);
                saveButton.setText("Save");
                deleteButton.setText("Delete");
                if (item.getImage() == null || item.getImage().equals("")) {
                    imageBox.setAlignment(Pos.CENTER);
                } else if (item.getImage().startsWith("https") || item.getImage().startsWith("http")) {
                    imageBox.setAlignment(Pos.CENTER_LEFT);
                } else if (item.getImage().endsWith(".jpg") || item.getImage().endsWith(".png")) {
                    imageBox.setAlignment(Pos.CENTER);
                }
            });

            // Handle image on mouse entered and exited
            image.setOnMouseEntered(event -> {
                image.setCursor(Cursor.HAND);
                if (!(image.getOpacity() == 1)) {
                    image.setOpacity(0.99);
                }
            });
            image.setOnMouseExited(event -> {
                image.setCursor(Cursor.DEFAULT);
                if (!(image.getOpacity() == 1)) {
                    image.setOpacity(0.6);
                }
            });

            // Add image to grid pane
            gridPane.add(image, columnIndex, rowIndex);

            // Name label
            Label nameLabel = new Label(item.getName() + " (" + item.getStock() + ")");
            nameLabel.setStyle("-fx-alignment: center; -fx-font-size: 15px;");
            nameLabel.setMaxWidth(image.getFitWidth());
            nameLabel.setAlignment(Pos.CENTER);
            gridPane.add(nameLabel, columnIndex, rowIndex + 1);

            // Category label
            Label categoryLabel = new Label(item.getCategory() + "\n\t");
            categoryLabel.setStyle("-fx-alignment: center; -fx-font-size: 15px;");
            categoryLabel.setMaxWidth(image.getFitWidth());
            categoryLabel.setAlignment(Pos.CENTER);
            gridPane.add(categoryLabel, columnIndex, rowIndex + 2);

            columnIndex++;

            // Handle if column index is more than 4
            if (columnIndex > 4) {
                columnIndex = 0;
                rowIndex += 3;
            }
        }
    }

    public HBox getRoot() {
        return root;
    }
}

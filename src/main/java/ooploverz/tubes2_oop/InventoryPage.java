package ooploverz.tubes2_oop;

import ooploverz.tubes2_oop.inventory.*;

import javafx.geometry.Rectangle2D;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.geometry.Insets;

import java.util.Objects;

import lombok.Getter;

@Getter
public class InventoryPage {
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private final double WINDOW_HEIGHT = primaryScreenBounds.getHeight() * 0.97;
    private final double WINDOW_WIDTH  = primaryScreenBounds.getWidth();
    private final HBox root;

    public InventoryPage(Inventory inventory) {
        // Create root
        root = new HBox();

        /*
         *   VERTICAL BOX
         */
        VBox vBox = new VBox();

        // Set vertical box properties
        vBox.setPrefWidth(WINDOW_WIDTH * 0.7);
        vBox.setMaxWidth(WINDOW_WIDTH * 0.7);
        vBox.setStyle("-fx-border-color: #000000; -fx-padding: 20px;");

        /*
         *   SCROLL PANE
         */
        ScrollPane scrollPane = new ScrollPane();

        // Set scroll pane properties
        scrollPane.setPrefWidth(vBox.getPrefWidth());
        scrollPane.setMaxWidth(vBox.getPrefWidth());
        scrollPane.setPrefHeight(WINDOW_HEIGHT * 0.85);
        scrollPane.setMaxHeight(WINDOW_HEIGHT * 0.85);
        scrollPane.setStyle("-fx-border-color: #9BCDFB; -fx-background: #9BCDFB;");

        /*
         *   TEXT FIELD SEARCH BOX
         */
        TextField searchBox = new TextField();

        // Set search box properties
        searchBox.setPromptText("Search");
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");

        /*
         *   LABEL TEXT HEADER
         */
        Label header = new Label("Item List:");

        // Set text header properties
        header.setPadding(new Insets(30, 0, 0, 0));
        header.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        /*
        *   GRID PANE
        */
        GridPane gridPane = new GridPane();

        // Set grid pane properties
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setStyle("-fx-background-color: #9BCDFB;");

        // Add item rows
        int rowIndex = 0;
        int columnIndex = 0;
        for (Item item : inventory.getListItem()) {
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

            image.setFitWidth((scrollPane.getPrefWidth() / 5) - (gridPane.getHgap() * 1.6));
            image.setFitHeight(image.getFitWidth());
            gridPane.add(image, columnIndex, rowIndex);

            // Name label
            Label nameLabel = new Label(item.getName() + " (" + item.getStock() + ")");
            nameLabel.setStyle("-fx-alignment: center; -fx-font-size: 15px;");
            nameLabel.setMaxWidth(image.getFitWidth());
            nameLabel.setAlignment(Pos.CENTER);
            gridPane.add(nameLabel, columnIndex, rowIndex + 1);

            // Category label
            Label categoryLabel = new Label(item.getCategory() + "\n");
            categoryLabel.setStyle("-fx-alignment: center; -fx-font-size: 15px;");
            categoryLabel.setMaxWidth(image.getFitWidth());
            categoryLabel.setAlignment(Pos.CENTER);
            gridPane.add(categoryLabel, columnIndex, rowIndex + 2);

            columnIndex++;

            // Handle if column index is more than 10
            if (columnIndex > 4) {
                columnIndex = 0;
                rowIndex += 3;
            }
        }

        // Insert grid pane to scroll pane
        scrollPane.setContent(gridPane);

        // Insert search box, header, and scroll pane to vertical box
        vBox.getChildren().addAll(searchBox, header, scrollPane);

        // Insert vertical box to root
        root.getChildren().add(vBox);
    }
}

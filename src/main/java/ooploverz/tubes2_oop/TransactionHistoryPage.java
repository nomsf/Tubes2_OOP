package ooploverz.tubes2_oop;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import lombok.Getter;

import ooploverz.tubes2_oop.inventory.Item;
import ooploverz.tubes2_oop.report.SalesReport;
import ooploverz.tubes2_oop.bill.FixedBillList;

import org.json.JSONArray;

import java.util.Map;

@Getter
public class TransactionHistoryPage {
    private final HBox root = new HBox();
    private final TextField searchBox = new TextField();
    private final VBox historyContainer = new VBox();
    private final ScrollPane scrollPane = new ScrollPane();
    private final Button salesReportButton = new Button("Print Sales Report");
    private final Button BillReport = new Button("Print Fixed Bill");

    public TransactionHistoryPage() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double WINDOW_HEIGHT = primaryScreenBounds.getHeight() * 0.97;
        double WINDOW_WIDTH = primaryScreenBounds.getWidth();

        // Get data from fixed bill list
        FixedBillList fixedBillList = new FixedBillList();

        /*
         * LEFT SIDE PANEL
         */
        // Set left vertical box properties
        VBox leftVBOX = new VBox();
        leftVBOX.setPrefWidth(WINDOW_WIDTH * 0.8);
        leftVBOX.setMaxWidth(WINDOW_WIDTH * 0.8);
        leftVBOX.setStyle("-fx-border-color: #000000; -fx-padding: 20px;");

        // Set history container vertical box properties
        historyContainer.setSpacing(20);
        historyContainer.setPrefWidth(leftVBOX.getPrefWidth() - 65);
        historyContainer.setMaxWidth(leftVBOX.getPrefWidth() - 65);
        historyContainer.setAlignment(Pos.CENTER);
        historyContainer.setStyle("-fx-padding: 20px;");

        VBox rightVBOX = new VBox();
        rightVBOX.setPrefWidth(WINDOW_WIDTH * 0.2);
        rightVBOX.setMaxWidth(WINDOW_WIDTH * 0.2);
        rightVBOX.setSpacing(100);
        rightVBOX.setStyle("-fx-border-color: #000000; -fx-padding: 20px;");

        // Set button properties
        salesReportButton.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");
        // Set button properties
        BillReport.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");
        // handle cursor SalesReport
        salesReportButton.setOnMouseEntered(event -> getSalesReportButton().setCursor(Cursor.HAND));
        salesReportButton.setOnMouseExited(event -> salesReportButton.setCursor(Cursor.DEFAULT));
        // handle cursor BillReport
        BillReport.setOnMouseEntered(event -> getBillReport().setCursor(Cursor.HAND));
        BillReport.setOnMouseExited(event -> getBillReport().setCursor(Cursor.HAND));

        // Set scroll pane properties
        scrollPane.setPrefWidth(leftVBOX.getPrefWidth());
        scrollPane.setMaxWidth(leftVBOX.getPrefWidth());
        scrollPane.setPrefHeight(WINDOW_HEIGHT * 0.9);
        scrollPane.setMaxHeight(WINDOW_HEIGHT * 0.9);
        scrollPane.setStyle("-fx-border-color: #000000; -fx-background: #9BCDFB;");
        scrollPane.setContent(historyContainer);

        // Set search box properties
        searchBox.setPromptText("Search ID");
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");

        searchBox.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")){
                if (searchBox.getText().equals("")) {
                    updateHistoryContainer(fixedBillList);
                }
                else {
                    int id;
                    try {
                        id = Integer.parseInt(searchBox.getText());
                    } catch (NumberFormatException exception) {
                        return;
                    }
                    FixedBillList filteredFixedBillList = new FixedBillList(id);
                    updateHistoryContainer(filteredFixedBillList);
                }
            }
        });

        // handle button event
        salesReportButton.setOnAction(e ->{
                JSONArray data = fixedBillList.getJsonArray();
                SalesReport.printPDF("transaction_report.pdf", data);
                }
        );

        updateHistoryContainer(fixedBillList);

        // set vertical box
        leftVBOX.getChildren().addAll(searchBox, scrollPane);
        rightVBOX.getChildren().addAll(salesReportButton,BillReport);

        // insert vertical box to root
        root.getChildren().addAll(leftVBOX);
        root.getChildren().addAll(rightVBOX);
    }

    public void updateHistoryContainer(FixedBillList fixedBillList) {
        historyContainer.getChildren().clear();
        for (int i = 0; i < fixedBillList.getFixedBillList().size(); i++) {
            VBox historyBox = new VBox();
            Label buyerId = new Label("Buyer ID: ");
            Label total = new Label("Total: ");
            Label dateNow = new Label("Date: ");
            VBox boughtItems = new VBox();

            buyerId.setText(buyerId.getText() + fixedBillList.getFixedBillList().get(i).getBuyerId());
            total.setText(total.getText() + fixedBillList.getFixedBillList().get(i).getTotal());
            dateNow.setText(dateNow.getText() + fixedBillList.getFixedBillList().get(i).getDateNow());
            boughtItems.getChildren().add(new Label("\nBought Items: "));
            for (Map.Entry<Item, Integer> entry : fixedBillList.getFixedBillList().get(i).getItemMap().entrySet()) {
                Item key = entry.getKey();
                Integer amount = entry.getValue();
                Label boughtItem = new Label(key.getName() + " x " + amount.toString());
                boughtItems.getChildren().add(boughtItem);
            }

            historyBox.getChildren().addAll(buyerId, total, dateNow, boughtItems);
            historyBox.setStyle("-fx-border-color: #000000; -fx-padding: 20px; -fx-background-color: #ffffff; -fx-border-width: 2px; -fx-font-size: 25px;");
            historyContainer.getChildren().add(historyBox);
        }
    }
}

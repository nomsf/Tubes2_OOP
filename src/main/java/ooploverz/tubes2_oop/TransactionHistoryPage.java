package ooploverz.tubes2_oop;
//package ooploverz.tubes2_oop.util.DateTime;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import lombok.Getter;
import ooploverz.tubes2_oop.report.SalesReport;

import static javafx.application.Application.launch;

@Getter
public class TransactionHistoryPage {
    private final HBox root = new HBox();
    private final TextField searchBox = new TextField();
    private final ScrollPane scrollPane = new ScrollPane();
    private final VBox  verticalBox = new VBox();
    private final Button salesReportButton = new Button("Print Sales Report");
    private final Button BillReport = new Button("Print Fixed Bill");
    public TransactionHistoryPage() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double WINDOW_HEIGHT = primaryScreenBounds.getHeight() * 0.97;
        double WINDOW_WIDTH = primaryScreenBounds.getWidth();

        // Get inventory data
//        JSONArray data = DataBill.getData();
//        History transactions = new History();
//        try {
//            for (int i = 0; i < data.length(); i++) {
//                transactions.addTransaction(data.getJSONObject(i).getInt("id"), data.getJSONObject(i).getString("Name"), data.getJSONObject(i).getInt("Price"), data.getJSONObject(i).getInt("Buy Price"), data.getJSONObject(i).getString("Category"), data.getJSONObject(i).getString("Image"));
//            }
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
        /*
         * LEFT SIDE PANEL
         */
        // Set vertical box properties
        VBox leftVBOX = new VBox();
        leftVBOX.setPrefWidth(WINDOW_WIDTH * 0.8);
        leftVBOX.setMaxWidth(WINDOW_WIDTH * 0.8);
        leftVBOX.setStyle("-fx-border-color: #000000; -fx-padding: 20px;");

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
        scrollPane.setStyle("-fx-border-color: #9BCDFB; -fx-background: #9BCDFB;");

        // Set search box properties
        searchBox.setPromptText("Search ID");
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");

//        searchBox.setOnKeyPressed(e -> {
//            if (e.getCode().toString().equals("ENTER")){
//                History filteredHistory = History.filterTra
//            }
//        });

//         handle button event
        salesReportButton.setOnAction(e ->{
                JSONArray data = new JSONArray();
                SalesReport.printPDF("transaction_report.pdf", data);
                }
        );

        // set vertical box
        leftVBOX.getChildren().addAll(searchBox, scrollPane);
        rightVBOX.getChildren().addAll(salesReportButton,BillReport);


        // insert vertical box to root
        root.getChildren().addAll(leftVBOX);
        root.getChildren().addAll(rightVBOX);
    }


}

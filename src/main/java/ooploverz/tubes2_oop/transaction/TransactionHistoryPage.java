package ooploverz.tubes2_oop.transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import lombok.Getter;

import static javafx.application.Application.launch;

@Getter
public class TransactionHistoryPage  {
    private GridPane root;

    private ListView<String> listView;
    private ObservableList<String> data;

    public TransactionHistoryPage (Transaction[] ListTransaction){
        root = new GridPane();
        ObservableList<String> data = FXCollections.observableArrayList(
                "Apple", "Banana", "Cherry", "Grape", "Orange", "Peach", "Strawberry"
        );
        TextField searchField = new TextField();
        searchField.setPromptText("Search");

        ListView<Object> listView = new ListView<>();

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            search(newValue);
        });


        Label first_name=new Label("First Name");
            Label last_name=new Label("Last Name");
            TextField tf1=new TextField();
            TextField tf2=new TextField();
            Button Submit=new Button ("Submit");
//            GridPane root=new GridPane();
//            Scene scene = new Scene(root,400,200);
            root.addRow(0, first_name,tf1);
            root.addRow(1, last_name,tf2);
            root.addRow(2, Submit);
//            primaryStage.setScene(scene);
//            primaryStage.show();
    }

    private void search(String newValue) {
        String keyword = null;
        if (keyword.isEmpty()) {
            listView.setItems(data); // Menampilkan semua item jika keyword kosong
        } else {
            ObservableList<String> searchResults = FXCollections.observableArrayList();
            for (String item : data) {
                if (item.toLowerCase().contains(keyword.toLowerCase())) {
                    searchResults.add(item);
                }
            }
            listView.setItems(searchResults); // Menampilkan hasil pencarian
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}

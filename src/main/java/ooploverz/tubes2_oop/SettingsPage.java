package ooploverz.tubes2_oop;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.*;
import ooploverz.tubes2_oop.SettingsData.SettingsData;

import java.io.File;

@Getter
@Setter
public class SettingsPage {

    private VBox root;
    private TextField directoryField;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;

    public SettingsPage(Stage primaryStage){
        TextField directoryField = new TextField();
        directoryField.setPromptText("Enter Directory");
        directoryField.textProperty().addListener((o, oldValue, newValue) -> {
            SettingsData.directoryPath = newValue;
        });

        Button directoryButton = new Button("Select Directory");
        directoryButton.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            if (selectedDirectory != null) {
                directoryField.setText(selectedDirectory.getAbsolutePath());
            }
        });


        button1 = new RadioButton("json");
        button1.setSelected(true);
        button2 = new RadioButton("xml");
        button3 = new RadioButton("obj");

        ToggleGroup group = new ToggleGroup();
        button1.setToggleGroup(group);
        button2.setToggleGroup(group);
        button3.setToggleGroup(group);

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedRadioButton = (RadioButton) newValue;
                SettingsData.dataFormat = String.valueOf(selectedRadioButton);
            }
        });

//        Button check = new Button("check");
//        check.setOnAction(actionEvent -> {
//            System.out.println(SettingsData.dataFormat);
//            System.out.println(SettingsData.directoryPath);
//        });

        root = new VBox( 20,directoryField,directoryButton, button1, button2, button3);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.TOP_LEFT);
    }

}

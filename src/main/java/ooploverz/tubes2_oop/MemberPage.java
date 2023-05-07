package ooploverz.tubes2_oop;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ooploverz.tubes2_oop.customer.ListOfMember;
import ooploverz.tubes2_oop.customer.Member;
import ooploverz.tubes2_oop.customer.MemberVIP;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class MemberPage implements IPageRoot{
    private StackPane stackRoot;
    private final int currentId = 0;

    private ListOfMember allMembers;

    public MemberPage() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds() ;
        double WINDOW_HEIGHT = primaryScreenBounds.getHeight() * 0.97;
        double WINDOW_WIDTH = primaryScreenBounds.getWidth();

        try {
            this.allMembers = new ListOfMember();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.stackRoot = new StackPane();
        this.stackRoot.setPrefWidth(WINDOW_WIDTH);
        this.stackRoot.setMaxWidth(WINDOW_WIDTH);
        this.stackRoot.setPrefHeight(WINDOW_HEIGHT);
        this.stackRoot.setMaxHeight(WINDOW_HEIGHT);

        /* Container for sideBar and Edit */
        HBox containerBox = new HBox();
        containerBox.setPrefWidth(WINDOW_WIDTH);
        containerBox.setMaxWidth(WINDOW_WIDTH);
        containerBox.setPrefHeight(WINDOW_HEIGHT);
        containerBox.setMaxHeight(WINDOW_HEIGHT);


        /* Side Bar List Member and MemberVIP */
        VBox memberSection = new VBox();
        memberSection.setPrefWidth(containerBox.getPrefWidth() * 0.6);
        memberSection.setMaxWidth(containerBox.getMaxWidth() * 0.6);
        memberSection.setPrefHeight(containerBox.getPrefHeight());
        memberSection.setMaxHeight(containerBox.getMaxHeight());
        memberSection.setPadding(new Insets(20,20,20,20));
        memberSection.setStyle("-fx-border-color: #000000;");

        // Title Member List
        HBox titleBadge = new HBox();
        titleBadge.setPrefWidth(memberSection.getPrefWidth());
        titleBadge.setMaxWidth(memberSection.getMaxWidth());
        titleBadge.setMaxHeight(memberSection.getMaxHeight() / 11);
        titleBadge.setPrefHeight(memberSection.getPrefHeight() / 11);
        titleBadge.setPadding(new Insets(10,0,10,0));
        titleBadge.setStyle("-fx-border-color: #000000;");

        Label titleMember =  new Label("Member List");
        titleMember.setMaxWidth(titleBadge.getMaxWidth());
        titleMember.setPrefWidth(titleBadge.getPrefWidth());
        titleMember.setStyle("-fx-border-color: #000000; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 20px; -fx-background-color: #FFFFFF;");
        titleMember.setAlignment(Pos.CENTER);

        titleBadge.getChildren().add(titleMember);


        // Member List
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefWidth(memberSection.getPrefWidth());
        scrollPane.setMaxWidth(memberSection.getMaxWidth());
        scrollPane.setPrefHeight(memberSection.getPrefHeight() - titleBadge.getPrefHeight());
        scrollPane.setMaxHeight(memberSection.getMaxHeight() - titleBadge.getMaxHeight());
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: #000000;");

        VBox memberList = new VBox();
        memberList.setMaxWidth(scrollPane.getMaxWidth());
        memberList.setPrefWidth(scrollPane.getPrefWidth());
        memberList.setSpacing(10);
        memberList.setStyle("-fx-background-color: transparent;");

            // Member Badge : Member List
        if (this.allMembers.){}
        for (Member member : this.allMembers.getMemberList()) {
            HBox memberBadge = new HBox();
            memberBadge.setMaxWidth(memberList.getPrefWidth());
            memberBadge.setPrefWidth(memberList.getPrefWidth());
            memberBadge.setMaxHeight(memberList.getPrefHeight() / 7);
            memberBadge.setPrefHeight(memberList.getPrefHeight() / 7);
            memberBadge.setPadding(new Insets(5, 5, 5, 5));
            memberBadge.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20px;");


            Member members1 = members[i];
            Label memberName = new Label(members1.getName());
            memberName.setMaxWidth(memberBadge.getPrefWidth());
            memberName.setPrefWidth(memberBadge.getPrefWidth());
            memberName.setAlignment(Pos.CENTER_LEFT);

            Label pointsString = new Label("Points:");
            pointsString.setMaxWidth(memberBadge.getPrefWidth() / 2);
            pointsString.setPrefWidth(memberBadge.getPrefWidth() / 2);
            pointsString.setAlignment(Pos.CENTER_RIGHT);

            Label points = new Label(String.valueOf(members1.getPoints()));
            points.setMaxWidth(memberBadge.getPrefWidth() / 2);
            points.setPrefWidth(memberBadge.getPrefWidth() / 2);
            points.setAlignment(Pos.CENTER_RIGHT);

            memberBadge.getChildren().addAll(memberName, pointsString, points);

            memberList.getChildren().add(memberBadge);
        }

        scrollPane.setContent(memberList);

        memberSection.getChildren().addAll(titleBadge, scrollPane);
        
        containerBox.getChildren().add(memberSection);
        /* END : Side Bar List Member and MemberVIP */

        /* Create, Deactivate, Edit, Section */
        VBox CDESection = new VBox();
        CDESection.setPrefWidth(WINDOW_WIDTH * 0.4);
        CDESection.setMaxWidth(WINDOW_WIDTH * 0.4);
        CDESection.setSpacing(20);
        CDESection.setStyle("-fx-border-color: #000000; -fx-padding: 20px;");

        TextField nameFill = new TextField();
        nameFill.setPromptText("Name");
        nameFill.setAlignment(Pos.CENTER);
        nameFill.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");

        TextField phoneNumberFill = new TextField();
        phoneNumberFill.setPromptText("Phone Number");
        phoneNumberFill.setAlignment(Pos.CENTER);
        phoneNumberFill.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");

        // Button for register member and memberVIP called registerButton
        Button registerButton = new Button("Register");
        registerButton.setPrefWidth(CDESection.getPrefWidth());
        registerButton.setMaxWidth(CDESection.getPrefWidth());
        registerButton.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");

        // onAction for registerButton
        registerButton.setOnAction(e -> {
            if (nameFill.getText().isEmpty() || phoneNumberFill.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error Empty Field");
                alert.setContentText("Please fill all the fields");

                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

                alert.showAndWait();
            }
            else {
                boolean isDuplicate = Arrays.stream(this.allMembers)
                        .anyMatch(member -> member.getName().equals(nameFill.getText()));

                if (isDuplicate) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error Duplicate Name");
                    alert.setContentText("Name already exist");
                }
                else {
                    Member newMember = new Member(this.currentId, nameFill.getText(), phoneNumberFill.getText(), 0, true);
                    // add newMember to All Members

                    this.allMembers.add(newMember);

                    Collections.sort(this.allMembers, Comparator.comparing(Member::getCustomerId));

                    nameFill.clear();
                    phoneNumberFill.clear();
                }
            }
        });





        CDESection.getChildren().addAll(nameFill, phoneNumberFill, registerButton);

        containerBox.getChildren().add(CDESection);

        this.stackRoot.getChildren().addAll(containerBox);
    }
    public Node getRoot() {
        return this.stackRoot;
    }
}

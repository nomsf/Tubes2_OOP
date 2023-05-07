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

public class MemberPage implements IPageRoot{
    private final StackPane stackRoot;
    private final int currentId = 0;

    private final ListOfMember allMembers;

    public MemberPage() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds() ;
        double WINDOW_HEIGHT = primaryScreenBounds.getHeight() * 0.97;
        double WINDOW_WIDTH = primaryScreenBounds.getWidth();

        this.allMembers = new ListOfMember();

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

        // Title Member List
        HBox titleMemberBadge = new HBox();
        titleMemberBadge.setPrefWidth(memberSection.getPrefWidth());
        titleMemberBadge.setMaxWidth(memberSection.getMaxWidth());
        titleMemberBadge.setMaxHeight(memberSection.getMaxHeight() / 11);
        titleMemberBadge.setPrefHeight(memberSection.getPrefHeight() / 11);
        titleMemberBadge.setPadding(new Insets(10,0,10,0));

        Label titleMember =  new Label("Member List");
        titleMember.setMaxWidth(titleMemberBadge.getMaxWidth());
        titleMember.setPrefWidth(titleMemberBadge.getPrefWidth());
        titleMember.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 20px; -fx-background-color: #FFFFFF;");
        titleMember.setAlignment(Pos.CENTER);

        titleMemberBadge.getChildren().add(titleMember);


        // Member List
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefWidth(memberSection.getPrefWidth());
        scrollPane.setMaxWidth(memberSection.getMaxWidth());
        scrollPane.setPrefHeight(memberSection.getPrefHeight() - titleMemberBadge.getPrefHeight());
        scrollPane.setMaxHeight(memberSection.getMaxHeight() - titleMemberBadge.getMaxHeight());
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");

        VBox memberList = new VBox();
        memberList.setMaxWidth(scrollPane.getMaxWidth());
        memberList.setPrefWidth(scrollPane.getPrefWidth());
        memberList.setMaxHeight(scrollPane.getMaxHeight());
        memberList.setPrefHeight(scrollPane.getPrefHeight());
        memberList.setSpacing(10);
        memberList.setStyle("-fx-background-color: #9BCDFB;");

            // Member Badge : Member List
        if (this.allMembers.getMemberList().size() == 0){
            Label noMember = new Label("No Member");
            noMember.setMaxWidth(memberList.getPrefWidth());
            noMember.setPrefWidth(memberList.getPrefWidth());
            noMember.setAlignment(Pos.CENTER);
            noMember.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 20");
            memberList.getChildren().add(noMember);
        }
        else {
            for (Member member : this.allMembers.getMemberList()) {
                HBox memberBadge = new HBox();
                memberBadge.setMaxWidth(memberList.getPrefWidth());
                memberBadge.setPrefWidth(memberList.getPrefWidth());
                memberBadge.setMaxHeight(memberList.getPrefHeight() / 7);
                memberBadge.setPrefHeight(memberList.getPrefHeight() / 7);
                memberBadge.setPadding(new Insets(5, 5, 5, 5));
                memberBadge.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 20px;");

                Label memberName = new Label(member.getName());
                memberName.setMaxWidth(memberBadge.getPrefWidth());
                memberName.setPrefWidth(memberBadge.getPrefWidth());
                memberName.setAlignment(Pos.CENTER_LEFT);

                Label pointsString = new Label("Points:");
                pointsString.setMaxWidth(memberBadge.getPrefWidth() / 2);
                pointsString.setPrefWidth(memberBadge.getPrefWidth() / 2);
                pointsString.setAlignment(Pos.CENTER_RIGHT);

                Label points = new Label(String.valueOf(member.getPoints()));
                points.setMaxWidth(memberBadge.getPrefWidth() / 2);
                points.setPrefWidth(memberBadge.getPrefWidth() / 2);
                points.setAlignment(Pos.CENTER_RIGHT);

                memberBadge.getChildren().addAll(memberName, pointsString, points);

                memberList.getChildren().add(memberBadge);
            }
        }

        scrollPane.setContent(memberList);

        memberSection.getChildren().addAll(titleMemberBadge, scrollPane);
        
        containerBox.getChildren().add(memberSection);
        /* END : Side Bar List Member and MemberVIP */

        /* Create, Deactivate, Edit, Section */
        VBox CDESection = new VBox();
        CDESection.setPrefWidth(WINDOW_WIDTH * 0.4);
        CDESection.setMaxWidth(WINDOW_WIDTH * 0.4);
        CDESection.setSpacing(20);
        CDESection.setStyle("-fx-padding: 20px;");

        HBox titleCDEBadge = new HBox();
        titleCDEBadge.setPrefWidth(memberSection.getPrefWidth());
        titleCDEBadge.setMaxWidth(memberSection.getMaxWidth());
        titleCDEBadge.setMaxHeight(memberSection.getMaxHeight() / 11);
        titleCDEBadge.setPrefHeight(memberSection.getPrefHeight() / 11);
        titleCDEBadge.setPadding(new Insets(10,0,10,0));

        Label titleCDE =  new Label("Member Status");
        titleCDE.setMaxWidth(titleCDEBadge.getMaxWidth());
        titleCDE.setPrefWidth(titleCDEBadge.getPrefWidth());
        titleCDE.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 20px; -fx-background-color: #FFFFFF;");
        titleCDE.setAlignment(Pos.CENTER);

        titleCDEBadge.getChildren().add(titleCDE);

        TextField nameFill = new TextField();
        nameFill.setPromptText("Name");
        nameFill.setAlignment(Pos.CENTER);
        nameFill.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-width: 2px;");

        TextField phoneNumberFill = new TextField();
        phoneNumberFill.setPromptText("Phone Number");
        phoneNumberFill.setAlignment(Pos.CENTER);
        phoneNumberFill.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-width: 2px;");

        // Button for register member and memberVIP called registerButton
        Button registerButton = new Button("Register");
        registerButton.setPrefWidth(CDESection.getPrefWidth());
        registerButton.setMaxWidth(CDESection.getPrefWidth());
        registerButton.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-width: 2px;");

        // onAction for registerButton
        registerButton.setOnAction(e -> {
            if (nameFill.getText().isEmpty() || phoneNumberFill.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error Empty Field");
                alert.setContentText("Please fill all the fields");

                alert.showAndWait();
            }
            else {
                boolean isDuplicate = this.allMembers.getMemberList().stream()
                        .anyMatch(member -> member.getName().equals(nameFill.getText()));

                if (isDuplicate) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error Duplicate Name");
                    alert.setContentText("Name already exist");

                    alert.showAndWait();
                }
                else {
                    Member newMember = new Member(this.currentId, nameFill.getText(), phoneNumberFill.getText(), 0, true);

                    this.allMembers.addMember(newMember);

                    nameFill.clear();
                    phoneNumberFill.clear();
                }
            }
        });

        // Points show
        HBox pointsSection = new HBox();
        pointsSection.setPrefWidth(CDESection.getPrefWidth());
        pointsSection.setMaxWidth(CDESection.getPrefWidth());
        pointsSection.setSpacing(10);

        Label pointsString = new Label("Points:");
        pointsString.setPrefWidth(CDESection.getPrefWidth());
        pointsString.setMaxWidth(CDESection.getPrefWidth());
        pointsString.setAlignment(Pos.CENTER_LEFT);

        Label points = new Label("0");
        points.setPrefWidth(CDESection.getPrefWidth());
        points.setMaxWidth(CDESection.getPrefWidth());
        points.setAlignment(Pos.CENTER_RIGHT);

        pointsSection.getChildren().addAll(pointsString, points);


        CDESection.getChildren().addAll(titleCDEBadge, nameFill, phoneNumberFill, registerButton, pointsSection);

        containerBox.getChildren().add(CDESection);

        this.stackRoot.getChildren().addAll(containerBox);
    }
    public Node getRoot() {
        return this.stackRoot;
    }
}

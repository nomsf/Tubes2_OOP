package ooploverz.tubes2_oop;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import ooploverz.tubes2_oop.bill.FixedBill;
import ooploverz.tubes2_oop.bill.FixedBillList;
import ooploverz.tubes2_oop.customer.ListOfMember;
import ooploverz.tubes2_oop.customer.Member;
import ooploverz.tubes2_oop.customer.MemberVIP;
import ooploverz.tubes2_oop.dataStore.DataMember;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class MemberPage implements IPageRoot{
    private final StackPane stackRoot;
    private final VBox memberList = new VBox();
    private final ScrollPane scrollPane = new ScrollPane();
    private final VBox statusMemberSection = new VBox();
    private int currentId;
    private int selectionId;
    private final VBox CDESection = new VBox();
    private final VBox memberSection = new VBox();
    private final ListOfMember allMembers = new ListOfMember();
    private final TextField nameFill = new TextField();
    private final TextField phoneNumberFill = new TextField();
    private final Label statusVIP = new Label();
    private final Label statusMember = new Label();
    private final Label points = new Label();
    private final Button registerButton = new Button("Register");
    private final Button editButton = new Button("Edit");

    public MemberPage() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds() ;
        double WINDOW_HEIGHT = primaryScreenBounds.getHeight() * 0.97;
        double WINDOW_WIDTH = primaryScreenBounds.getWidth();



        this.stackRoot = new StackPane();
        this.stackRoot.setPrefWidth(WINDOW_WIDTH);
        this.stackRoot.setMaxWidth(WINDOW_WIDTH);
        this.stackRoot.setPrefHeight(WINDOW_HEIGHT);
        this.stackRoot.setMaxHeight(WINDOW_HEIGHT);

        FixedBillList fixedBillList = new FixedBillList();
        currentId = 1;
        LocalDateTime latestDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("  d/M/yyyy H:mm:ss");
        for (FixedBill r : fixedBillList.getFixedBillList()) {
            LocalDateTime billDate = LocalDateTime.parse(r.getDateNow().replaceAll("[^0-9/ :]",""), formatter);
            if (latestDate == null || billDate.isAfter(latestDate)) {
                currentId = r.getBuyerId();
                latestDate = billDate;
            }
        }

        /* END : SETUP Object */

        stackRoot.setPrefWidth(WINDOW_WIDTH);
        stackRoot.setMaxWidth(WINDOW_WIDTH);
        stackRoot.setPrefHeight(WINDOW_HEIGHT);
        stackRoot.setMaxHeight(WINDOW_HEIGHT);

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

        scrollPane.setPrefWidth(memberSection.getPrefWidth());
        scrollPane.setMaxWidth(memberSection.getMaxWidth());
        scrollPane.setPrefHeight(memberSection.getPrefHeight() - titleMemberBadge.getPrefHeight());
        scrollPane.setMaxHeight(memberSection.getMaxHeight() - titleMemberBadge.getMaxHeight());
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");

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


                memberBadge.setOnMouseClicked(e -> {
                    selectionId = member.getCustomerId();
                    nameFill.setText(member.getName());
                    phoneNumberFill.setText(member.getPhoneNumber());
                    points.setText(String.valueOf(member.getPoints()));
                    statusMember.setText(String.valueOf(member.isActive()));
                    statusVIP.setText(member instanceof MemberVIP ? "VIP" : "Regular");

                    // get Id register button from CDE section, change ro edit button
                    int index = CDESection.getChildren().indexOf(registerButton);
                    CDESection.getChildren().remove(registerButton);
                    CDESection.getChildren().add(index, editButton);
                });

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
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
                registerButton.setText("Register");
            }));

            if (nameFill.getText().isEmpty() || phoneNumberFill.getText().isEmpty()) {
                registerButton.setText("Please fill all the fields");
            }
            else {
                boolean isDuplicate = allMembers.getMemberList().stream()
                        .anyMatch(member -> member.getName().equals(nameFill.getText()) && member.getPhoneNumber().equals(phoneNumberFill.getText()));


                if (isDuplicate) {
                    registerButton.setText("Member already registered");
                }
                else {
                    if (currentId != -1) {
                        Member newMember = new Member(currentId, nameFill.getText(), phoneNumberFill.getText(), 0, true);

                        allMembers.addMember(newMember);
                        registerButton.setText("New Member Registered!");
                    }
                    else {
                        registerButton.setText("There is no current Transaction");
                    }
                }
            }

            timeline.play();
            nameFill.clear();
            phoneNumberFill.clear();
        });

        // Edit Button
        editButton.setPrefWidth(CDESection.getPrefWidth());
        editButton.setMaxWidth(CDESection.getPrefWidth());
        editButton.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-width: 2px;");
        editButton.setVisible(false);

        // onAction for editButton
        editButton.setOnAction(e -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
                int index = CDESection.getChildren().indexOf(editButton);
                CDESection.getChildren().remove(editButton);
                CDESection.getChildren().add(index, registerButton);
            }));

            if (nameFill.getText().isEmpty() || phoneNumberFill.getText().isEmpty()) {
                editButton.setText("Please fill all the fields");

                nameFill.clear();
                phoneNumberFill.clear();
            }
            else {
                boolean isDuplicate = allMembers.getMemberList().stream()
                        .anyMatch(member -> member.getName().equals(nameFill.getText()) && member.getPhoneNumber().equals(phoneNumberFill.getText()));

                if (isDuplicate) {
                    editButton.setText("Member already registered");
                }
                else {
                    Member newMember = new Member(currentId, nameFill.getText(), phoneNumberFill.getText(), 0, true);

                    allMembers.updateMember(newMember);
                    editButton.setText("Member Updated!");
                    updateListMember();
                }

                timeline.play();
                nameFill.clear();
                phoneNumberFill.clear();
                points.setText("0");
                statusVIP.setText("Reguler");
                statusMember.setText("Active");
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


        Label statusString = new Label("Status Member:");
        statusString.setPrefWidth(CDESection.getPrefWidth());
        statusString.setMaxWidth(CDESection.getPrefWidth());
        statusString.setAlignment(Pos.CENTER_LEFT);

        statusMember.setText("Active");
        statusMember.setPrefWidth(CDESection.getPrefWidth());
        statusMember.setMaxWidth(CDESection.getPrefWidth());
        statusMember.setAlignment(Pos.CENTER_RIGHT);

        statusMemberSection.getChildren().addAll(statusString, statusMember);

        // button for deactivate member
        Button memberStatusButton = new Button("Deactivate Member Status");
        memberStatusButton.setPrefWidth(CDESection.getPrefWidth());
        memberStatusButton.setMaxWidth(CDESection.getPrefWidth());
        memberStatusButton.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-width: 2px;");

        // onAction for deactivateButton
        memberStatusButton.setOnAction(e ->{
            // cek empty field
            String Text = memberStatusButton.getText();
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
                if (Text.equals("Deactivate Member Status")) {
                    memberStatusButton.setText("Activate Member Status");
                    statusMember.setText("Deactive");

                }
                else {
                    memberStatusButton.setText("Deactivate Member Status");
                    statusMember.setText("Active");
                }
            }));

            if (nameFill.getText().isEmpty() || phoneNumberFill.getText().isEmpty()) {
                memberStatusButton.setText("Please fill all the fields");
            }
            else {
                boolean isDuplicate = allMembers.getMemberList().stream()
                        .anyMatch(member -> member.getName().equals(nameFill.getText()) && member.getPhoneNumber().equals(phoneNumberFill.getText()));

                if (isDuplicate) {
                    Member newMember = new Member(currentId, nameFill.getText(), phoneNumberFill.getText(), 0, true);

                    allMembers.updateMember(newMember);
                    memberStatusButton.setText("Member Updated!");
                    updateListMember();
                }
                else {
                    memberStatusButton.setText("Member not registered");
                }
            }

            timeline.play();
        });

        // status VIP Member show : VIP or Regular
        HBox statusVIPSection = new HBox();
        statusVIPSection.setPrefWidth(CDESection.getPrefWidth());
        statusVIPSection.setMaxWidth(CDESection.getPrefWidth());
        statusVIPSection.setSpacing(10);
        
        Label statusVIPString = new Label("Status VIP:");
        statusVIPString.setPrefWidth(CDESection.getPrefWidth());
        statusVIPString.setMaxWidth(CDESection.getPrefWidth());
        statusVIPString.setAlignment(Pos.CENTER_LEFT);
        
        statusVIP.setText("Regular");
        statusVIP.setPrefWidth(CDESection.getPrefWidth());
        statusVIP.setMaxWidth(CDESection.getPrefWidth());
        statusVIP.setAlignment(Pos.CENTER_RIGHT);
        
        statusVIPSection.getChildren().addAll(statusVIPString, statusVIP);
        
        // Upgrade to MemberVIP
        Button VIPStatusButton = new Button("Upgrade to VIP Member");
        VIPStatusButton.setPrefWidth(CDESection.getPrefWidth());
        VIPStatusButton.setMaxWidth(CDESection.getPrefWidth());
        VIPStatusButton.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff; -fx-border-width: 2px;");
        
        // onAction for upgradeButton
        VIPStatusButton.setOnAction(e ->{
            // cek empty field
            String Text = VIPStatusButton.getText();
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
                if (Text.equals("Upgrade to VIP Member")) {
                    VIPStatusButton.setText("Downgrade to Regular Member");
                    statusVIP.setText("VIP");

                }
                else {
                    VIPStatusButton.setText("Upgrade to VIP Member");
                    statusVIP.setText("Regular");
                }
            }));

            if (nameFill.getText().isEmpty() || phoneNumberFill.getText().isEmpty()) {
                VIPStatusButton.setText("Please fill all the fields");
            }
            else {
                boolean isDuplicate = allMembers.getMemberList().stream()
                        .anyMatch(member -> member.getName().equals(nameFill.getText()) && member.getPhoneNumber().equals(phoneNumberFill.getText()));

                if (isDuplicate) {
                    Member newMember = new Member(currentId, nameFill.getText(), phoneNumberFill.getText(), 0, true);

                    allMembers.updateMember(newMember);
                    VIPStatusButton.setText("Statuus VIP Member Change!");
                    updateListMember();
                }
                else {
                    VIPStatusButton.setText("Member not registered");
                }
            }

            timeline.play();
        });


        CDESection.getChildren().addAll(titleCDEBadge, nameFill, phoneNumberFill, pointsSection, statusMemberSection, statusVIPSection, registerButton, memberStatusButton, VIPStatusButton);

        containerBox.getChildren().add(CDESection);

        stackRoot.getChildren().addAll(containerBox);
    }

    private void updateListMember() {
        // write to data base
        DataMember.updateData(allMembers.toJSONArray());

        if (allMembers.getMemberList().size() == 0){
            Label noMember = new Label("No Member");
            noMember.setMaxWidth(memberList.getPrefWidth());
            noMember.setPrefWidth(memberList.getPrefWidth());
            noMember.setAlignment(Pos.CENTER);
            noMember.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 20; -fx-font-weight: bold; -fx-background-radius: 20px;");
            memberList.getChildren().add(noMember);
        }
        else {
            for (Member member : allMembers.getMemberList()) {
                HBox memberBadge = new HBox();
                memberBadge.setMaxWidth(memberList.getPrefWidth());
                memberBadge.setPrefWidth(memberList.getPrefWidth());
                memberBadge.setMaxHeight(memberList.getPrefHeight() / 7);
                memberBadge.setPrefHeight(memberList.getPrefHeight() / 7);
                memberBadge.setPadding(new Insets(5, 5, 5, 5));
                memberBadge.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 20px;");

                memberBadge.setOnMouseClicked(e -> {
                    selectionId = member.getCustomerId();
                    nameFill.setText(member.getName());
                    phoneNumberFill.setText(member.getPhoneNumber());
                    points.setText(String.valueOf(member.getPoints()));
                    statusMember.setText(String.valueOf(member.isActive()));
                    statusVIP.setText(member instanceof MemberVIP ? "VIP" : "Regular");

                    // get Id register button from CDE section, change ro edit button
                    int index = CDESection.getChildren().indexOf(registerButton);
                    CDESection.getChildren().remove(registerButton);
                    CDESection.getChildren().add(index, editButton);
                });

                Label memberName = new Label(member.getName());
                memberName.setMaxWidth(memberBadge.getPrefWidth()/2);
                memberName.setPrefWidth(memberBadge.getPrefWidth()/2);
                memberName.setAlignment(Pos.CENTER_LEFT);

                Label phone = new Label(member.getPhoneNumber());
                phone.setMaxWidth(memberBadge.getPrefWidth()/2);
                phone.setPrefWidth(memberBadge.getPrefWidth()/2);
                phone.setAlignment(Pos.CENTER_LEFT);

                Label pointsString = new Label("Points:");
                pointsString.setMaxWidth(memberBadge.getPrefWidth() / 2);
                pointsString.setPrefWidth(memberBadge.getPrefWidth() / 2);
                pointsString.setAlignment(Pos.CENTER_RIGHT);

                Label points = new Label(String.valueOf(member.getPoints()));
                points.setMaxWidth(memberBadge.getPrefWidth() / 2);
                points.setPrefWidth(memberBadge.getPrefWidth() / 2);
                points.setAlignment(Pos.CENTER_RIGHT);


                memberBadge.getChildren().addAll(memberName, phone, pointsString, points);
                memberList.getChildren().clear();
                memberList.getChildren().add(memberBadge);
            }
        }
    }

    private Node AlertBox() {
        VBox alertBox = new VBox();
        alertBox.setSpacing(10);
        alertBox.setPadding(new Insets(10));
        alertBox.setStyle("-fx-background-color: #ffffff; -fx-border-width: 2px;");

        Label messageLabel = new Label("Warning: The file is too large to upload.");
        messageLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label detailsLabel = new Label("Please reduce the file size before uploading.");
        detailsLabel.setStyle("-fx-font-size: 14px;");

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(10));

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color: #dddddd; -fx-text-fill: black; -fx-font-size: 14px;");

        Button uploadButton = new Button("Upload Anyway");
        uploadButton.setStyle("-fx-background-color: #dd3333; -fx-text-fill: white; -fx-font-size: 14px;");

        buttonBox.getChildren().addAll(cancelButton, uploadButton);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.add(messageLabel, 0, 0);
        gridPane.add(detailsLabel, 0, 1);
        gridPane.add(buttonBox, 0, 2);

        alertBox.getChildren().add(gridPane);

        return alertBox;
    }

    private Node AlertBox(String message, String detail, String canceledButton , String approveButton) {
        VBox alertBox = new VBox();
        alertBox.setSpacing(10);
        alertBox.setPadding(new Insets(10));
        alertBox.setStyle("-fx-background-color: #ffffff; -fx-border-width: 2px;");

        Label messageLabel = new Label(message);
        messageLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label detailsLabel = new Label(detail);
        detailsLabel.setStyle("-fx-font-size: 14px;");

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(10));

        Button cancelButton = new Button(canceledButton);
        cancelButton.setStyle("-fx-background-color: #dddddd; -fx-text-fill: black; -fx-font-size: 14px;");

        Button uploadButton = new Button(approveButton);
        uploadButton.setStyle("-fx-background-color: #dd3333; -fx-text-fill: white; -fx-font-size: 14px;");

        buttonBox.getChildren().addAll(cancelButton, uploadButton);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.add(messageLabel, 0, 0);
        gridPane.add(detailsLabel, 0, 1);
        gridPane.add(buttonBox, 0, 2);

        alertBox.getChildren().add(gridPane);

        return alertBox;
    }
    public Node getRoot() {
        return this.stackRoot;
    }


}

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
import ooploverz.tubes2_oop.customer.Member;
import ooploverz.tubes2_oop.customer.MemberVIP;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class MemberPage implements IPageRoot{
    private final StackPane stackRoot;

    public MemberPage() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds() ;
        double WINDOW_HEIGHT = primaryScreenBounds.getHeight() * 0.97;
        double WINDOW_WIDTH = primaryScreenBounds.getWidth();

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

        // TODO : Ngambil Id dari data base (transaksi terakhir) dan ngambil Id dari data base semua member
        //        this.curentId = 0;

        Member[] members = new Member[10];
        MemberVIP[] membersVIP = new MemberVIP[10];

        IntStream.range(0, 10).forEach(i -> {
            int id = i + 1;
            String name = "Member " + id;
            String phoneNum = "62-8" + String.format("%08d", (int) (Math.random() * 99999999 + 1));
            int points = (int) (Math.random() * 10000 + 1);
            boolean isActive = Math.random() < 0.5;
            members[i] = new Member(id, name, phoneNum, points, isActive);
            membersVIP[i] = new MemberVIP(id + 10, name, phoneNum, points, isActive);
        });

        Member[] allMembers = Arrays.copyOf(members, members.length + membersVIP.length);
        System.arraycopy(membersVIP, 0, allMembers, members.length, membersVIP.length);

        Arrays.sort(allMembers, Comparator.comparing(Member::getCustomerId));

        Arrays.stream(allMembers).forEach(member -> System.out.println(member.getName() + " " + member.getPhoneNumber() + " " + member.getPoints() + " " + member.isActive() + " " + member.getClass().getSimpleName()));

            // Member Badge : Member List
        for(int i = 0; i < members.length + membersVIP.length; i++) {
            HBox memberBadge = new HBox();
            memberBadge.setMaxWidth(memberList.getPrefWidth());
            memberBadge.setPrefWidth(memberList.getPrefWidth());
            memberBadge.setMaxHeight(memberList.getPrefHeight() / 7);
            memberBadge.setPrefHeight(memberList.getPrefHeight() / 7);
            memberBadge.setPadding(new Insets(5, 5, 5, 5));
            memberBadge.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20px;");

            if (i < members.length) {
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
            } else {
                MemberVIP members1 = membersVIP[i - members.length];
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
            }

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

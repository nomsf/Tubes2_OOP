package ooploverz.tubes2_oop;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import ooploverz.tubes2_oop.customer.Member;

public class MemberPage implements IPageRoot{
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private final double WINDOW_HEIGHT = primaryScreenBounds.getHeight() * 0.97;
    private final double WINDOW_WIDTH  = primaryScreenBounds.getWidth();
    private final StackPane stackRoot;
//  private int curentId;

    public MemberPage() {
        this.stackRoot = new StackPane();
        this.stackRoot.setPrefWidth(WINDOW_WIDTH);
        this.stackRoot.setMaxWidth(WINDOW_WIDTH);
        this.stackRoot.setPrefHeight(WINDOW_HEIGHT);
        this.stackRoot.setMaxHeight(WINDOW_HEIGHT);
        // TODO : Ngambil Id dari data base (transaksi terakhir)
//        this.curentId = 0;
        /* Container for sideBar and Edit */
        HBox containerBox = new HBox();


        /* Side Bar List Member and MemberVIP */
        VBox memberSection = new VBox();

        memberSection.setPrefWidth(WINDOW_WIDTH * 0.6);
        memberSection.setMaxWidth(WINDOW_WIDTH);
        memberSection.setPadding(new Insets(20,20,20,20));
        memberSection.setStyle("-fx-border-color: #000000;");

        // Title Member List
        HBox titleBadge = new HBox();
        titleBadge.setMaxWidth(memberSection.getMaxWidth());
        titleBadge.setPrefWidth(memberSection.getPrefWidth());
        titleBadge.setStyle("-fx-border-color: #000000; -fx-padding: 15px; -fx-background-color: #c03939;");

        Label titleMember =  new Label("Member List");
        titleMember.setMaxWidth(titleBadge.getMaxWidth());
        titleMember.setPrefWidth(titleBadge.getPrefWidth());
        titleMember.setStyle("-fx-border-color: #000000; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 20px; -fx-background-color: #FFFFFF;");
        titleMember.setAlignment(Pos.CENTER);

        titleBadge.getChildren().add(titleMember);


        // Member List
        VBox memberList = new VBox();

        memberSection.getChildren().addAll(titleBadge, memberList);

        containerBox.getChildren().add(memberSection);

        this.stackRoot.getChildren().addAll(containerBox);
    }
    public Node getRoot() {

        return this.stackRoot;
    }



}

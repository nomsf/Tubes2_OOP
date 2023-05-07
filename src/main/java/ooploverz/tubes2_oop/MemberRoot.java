package ooploverz.tubes2_oop;

import javafx.scene.layout.GridPane;

public class MemberRoot {
    private GridPane rootPane;
    private int curentId;

    MemberRoot() {
        this.rootPane = new GridPane();
        // TODO : Ngambil Id dari data base (transaksi terakhir)
        this.curentId = 0;
    }
    GridPane getRoot() {

        return this.rootPane;
    }
}

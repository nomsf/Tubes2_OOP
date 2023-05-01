module ooploverz.tubes2_oop {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens ooploverz.tubes2_oop to javafx.fxml;
    exports ooploverz.tubes2_oop;
}
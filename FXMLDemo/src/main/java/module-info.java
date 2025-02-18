module org.example.fxmldemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.fxmldemo to javafx.fxml;
    exports org.example.fxmldemo;
}
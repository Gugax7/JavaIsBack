package org.example.fxmldemo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Okay that's pretty neat");
    }
    @FXML
    protected void myOwnFunction() {
        welcomeText.setText("Okay now i use my own function");
    }
    @FXML
    protected void onMouseEnteredButton(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("you've entered with your mouse on my button");
        alert.setTitle("Mouse on button");
        alert.setContentText("Yo, your mouse is on my button");
        alert.show();
    }
}
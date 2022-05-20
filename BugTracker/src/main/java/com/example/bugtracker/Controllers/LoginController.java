package com.example.bugtracker.Controllers;

import com.example.bugtracker.Main;
import com.example.bugtracker.Service.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class LoginController {
    @Setter
    @Getter
    private Service service;

    @Getter
    @Setter
    private Stage stage;

    @FXML
    public TextField usernameField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public Button loginButton;

    @FXML
    public void onLoginClick() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        service.tryLogIn(username, password);
        if(service.tryLogIn(username, password)) {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            MainController mainController = fxmlLoader.getController();
            mainController.setService(service);
            stage.setScene(scene);
            stage.show();
            this.stage.hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid username or password");
            alert.show();
        }
    }
}
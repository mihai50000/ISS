package com.example.bugtracker.Controllers;

import com.example.bugtracker.Model.Severity;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;

public class MainTesterController extends MainController {

    @FXML
    public TextField bugTitleBox;

    @FXML
    public TextArea bugDescriptionBox;

    @FXML
    public ComboBox<Severity> severityComboBox;

    @FXML
    public void initialize() {
        severityComboBox.getItems().addAll(Severity.values());
    }

    public void createBug() {
        if (bugTitleBox.getText().isEmpty() || bugDescriptionBox.getText().isEmpty() || severityComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid parameters");
            alert.show();
            return;
        }

        String title = bugTitleBox.getText();
        String description = bugDescriptionBox.getText();
        Severity severity = severityComboBox.getValue();
        LocalDateTime creationDate = LocalDateTime.now();

        service.saveBug(title, creationDate, severity, description);
    }
}

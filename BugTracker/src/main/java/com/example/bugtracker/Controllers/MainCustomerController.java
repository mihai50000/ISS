package com.example.bugtracker.Controllers;

import com.example.bugtracker.Model.Severity;
import com.example.bugtracker.Service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.Getter;

import java.time.LocalDateTime;

public class MainCustomerController extends MainController {
    @FXML
    public Label loggedUserLabel;

    @FXML
    public TextField titleBox;

    @FXML
    public TextArea descriptionBox;

    @FXML
    public DatePicker datePicker;

    @FXML
    public ComboBox<Severity> severityComboBox;

    @FXML
    public void initialize() {
        severityComboBox.getItems().addAll(Severity.values());
    }

    public void sendComplain() {
        if (titleBox.getText().isEmpty() || descriptionBox.getText().isEmpty() || severityComboBox.getValue() == null || datePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid parameters");
            alert.show();
            return;
        }

        String title = titleBox.getText();
        String description = descriptionBox.getText();
        Severity severity = severityComboBox.getValue();
        LocalDateTime dateTime = datePicker.getValue().atStartOfDay();

        service.saveComplain(title, dateTime, severity, description);
    }
}

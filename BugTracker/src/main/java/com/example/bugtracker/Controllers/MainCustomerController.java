package com.example.bugtracker.Controllers;

import com.example.bugtracker.Service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lombok.Getter;

public class MainCustomerController {
    @Getter
    private Service service;

    @FXML
    public Label loggedUserLabel;

    public void setService(Service service) {
        this.service = service;
        loggedUserLabel.setText("Hello " + service.getLoggedUser().getFullName());
    }

    @FXML
    public TextField titleBox;

    @FXML
    public TextArea descriptionBox;

    @FXML
    public DatePicker datePicker;

    @FXML
    public ComboBox severity;

    public void sendComplain() {
        ///TODO CREATE COMPLAIN
    }
}

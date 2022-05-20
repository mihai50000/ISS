package com.example.bugtracker.Controllers;

import com.example.bugtracker.Service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lombok.Getter;

public class MainController {
    @Getter
    private Service service;

    @FXML
    public Label loggedUserLabel;

    public void setService(Service service) {
        this.service = service;
        loggedUserLabel.setText("Hello " + service.getLoggedUser().getFullName());
    }
}

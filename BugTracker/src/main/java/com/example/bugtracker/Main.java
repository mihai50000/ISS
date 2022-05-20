package com.example.bugtracker;

import com.example.bugtracker.Controllers.LoginController;
import com.example.bugtracker.Repository.Bugs.BugsDbRepository;
import com.example.bugtracker.Repository.Bugs.BugsRepository;
import com.example.bugtracker.Repository.Complains.ComplainsDbRepository;
import com.example.bugtracker.Repository.Complains.ComplainsRepository;
import com.example.bugtracker.Repository.Credentials.CredentialsDbRepository;
import com.example.bugtracker.Repository.Credentials.CredentialsRepository;
import com.example.bugtracker.Service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Properties properties = new Properties();

        try {
            properties.load(new FileReader("db.properties"));
        } catch (IOException ioException) {
            System.out.println("Eroare la parsarea proprietatilor");
            System.exit(1);
        }

        CredentialsRepository credentialsRepository = new CredentialsDbRepository(properties);
        ComplainsRepository complainsRepository = new ComplainsDbRepository(properties);
        BugsRepository bugsRepository = new BugsDbRepository(properties);
        Service service = new Service(credentialsRepository, complainsRepository, bugsRepository);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Parent rootLogin = fxmlLoader.load();

        LoginController loginController = fxmlLoader.getController();
        loginController.setService(service);
        loginController.setStage(stage);

        Scene scene = new Scene(rootLogin);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
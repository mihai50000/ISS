open module com.example.bugtracker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires org.apache.logging.log4j;
    requires org.hibernate.commons.annotations;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.persistence;
    requires java.sql;

    exports com.example.bugtracker;
    exports com.example.bugtracker.Controllers;
    exports com.example.bugtracker.Service;
    exports com.example.bugtracker.Repository;
    exports com.example.bugtracker.Model;
    exports com.example.bugtracker.Repository.Credentials;
}
module SimpleDBClient {
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires gradle.wrapper;
    requires controlsfx;
    requires mysql.connector.java;
    requires java.sql;

    opens sample;
}
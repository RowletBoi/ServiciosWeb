module org.example.serviciosweb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires java.sql;




    opens org.example.serviciosweb to javafx.fxml;
    exports org.example.serviciosweb;

    exports org.example.serviciosweb.model;
    opens org.example.serviciosweb.model to javafx.fxml;

    exports org.example.serviciosweb.controller;
    opens org.example.serviciosweb.controller to javafx.fxml;

    exports org.example.serviciosweb.service;
    opens org.example.serviciosweb.service to javafx.base;



}
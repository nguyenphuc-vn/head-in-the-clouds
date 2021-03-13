module personal {
    requires javafx.controls;
    requires javafx.media;
    requires java.net.http;
    requires com.google.gson;
    requires javafx.fxml;



    opens personal to javafx.fxml;
    exports personal;
    opens personal.model to com.google.gson;
    exports personal.model;
    opens personal.controller to javafx.fxml;
    exports personal.controller;


}
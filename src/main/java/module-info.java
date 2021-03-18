module personal {
    requires javafx.controls;
    requires javafx.media;
    requires java.net.http;
    requires com.google.gson;
    requires javafx.fxml;



    opens personal to javafx.fxml;
    exports personal;
    opens personal.quote.model to com.google.gson;
    exports personal.quote.model;
    opens personal.quote.controller to javafx.fxml;
    exports personal.quote.controller;
    opens personal.maze.controller to javafx.fxml;
    exports personal.maze.controller;




}
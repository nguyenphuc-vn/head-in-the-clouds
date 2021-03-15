module personal {
    requires javafx.controls;
    requires javafx.media;
    requires java.net.http;
    requires javafx.fxml;
    requires com.google.gson;



    opens personal to javafx.fxml;
    exports personal;
    opens personal.controller.quote to javafx.fxml;
    exports personal.controller.quote;
    opens personal.controller.maze to javafx.fxml;
    exports personal.controller.maze;
    opens personal.model.quote to com.google.gson;
    exports personal.model.quote;


}
module personal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    requires java.net.http;


    opens personal to javafx.fxml;
    exports personal;
}
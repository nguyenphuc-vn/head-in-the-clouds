module personal {
    requires javafx.controls;
    requires javafx.fxml;

    opens personal to javafx.fxml;
    exports personal;
}
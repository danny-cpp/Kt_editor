module ca.ualberta {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens ca.ualberta to javafx.fxml;
    exports ca.ualberta;
}
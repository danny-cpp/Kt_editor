module ca.ualberta {
    requires javafx.controls;
    requires javafx.fxml;

    opens ca.ualberta to javafx.fxml;
    exports ca.ualberta;
}
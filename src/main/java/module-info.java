module ca.ualberta {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    opens ca.ualberta to javafx.fxml;
    exports ca.ualberta;
}
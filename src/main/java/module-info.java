module com.example.hashcafehouse {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.base;


    opens com.example.hashcafehouse to javafx.fxml,javafx.base;
    exports com.example.hashcafehouse;
}
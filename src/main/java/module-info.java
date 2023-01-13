module com.svalero.practicas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.svalero.practicas to javafx.fxml;
    exports com.svalero.practicas;
}
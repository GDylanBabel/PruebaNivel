module com.babel.bootcamp.pruebanivel {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.babel.bootcamp.pruebanivel to javafx.fxml;
    exports com.babel.bootcamp.pruebanivel;
}
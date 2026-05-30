module ru.qurati.metalsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.desktop;
    requires javafx.swing;
    opens ru.qurati.metalsapp to javafx.fxml;
    opens ru.qurati.metalsapp.model to org.hibernate.orm.core;
    exports ru.qurati.metalsapp;
    exports ru.qurati.metalsapp.controller.client;
    opens ru.qurati.metalsapp.controller.client to javafx.fxml;
    exports ru.qurati.metalsapp.controller.metalcategory;
    opens ru.qurati.metalsapp.controller.metalcategory to javafx.fxml;
    exports ru.qurati.metalsapp.controller.transaction;
    opens ru.qurati.metalsapp.controller.transaction to javafx.fxml;
}
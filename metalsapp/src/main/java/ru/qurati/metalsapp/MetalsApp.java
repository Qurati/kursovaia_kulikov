package ru.qurati.metalsapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MetalsApp extends Application {
    public static Stage primaryStage;
    public static Scene clients;
    public static Scene metalCategories;
    public static Scene transactions;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        clients = createScene("client-view.fxml");
        transactions = createScene("transaction-view.fxml");
        metalCategories = createScene("metal-categories-view.fxml");
        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(675);
        primaryStage.setTitle("Обменный пункт драгоценных металлов");
        clients.getStylesheets().add("base-styles.css");
        metalCategories.getStylesheets().add("base-styles.css");
        transactions.getStylesheets().add("base-styles.css");
        primaryStage.setScene(clients);
        primaryStage.show();
    }

    private Scene createScene(String name) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MetalsApp.class.getResource(name));
        return new Scene(fxmlLoader.load());
    }

    public static void main(String[] args) {
        launch();
    }
}
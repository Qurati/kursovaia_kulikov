package ru.qurati.metalsapp.controller.metalcategory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.qurati.metalsapp.MetalsApp;
import ru.qurati.metalsapp.model.MetalCategory;
import ru.qurati.metalsapp.service.MetalCategoryService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MetalCategoryController {
    private List<MetalCategory> metalCategories;
    private ObservableList<MetalCategoryTableItem> categoriesObservable;

    @FXML
    private TableView<MetalCategoryTableItem> metalCategoriesTable;
    @FXML
    private TableColumn<MetalCategory, String> nameColumn;
    @FXML
    private TableColumn<MetalCategory, String> purityColumn;
    @FXML
    private TableColumn<MetalCategory, String> priceColumn;

    @FXML
    void btnAddCategory(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(MetalsApp.class.getResource("add-edit-metal-category-dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(MetalsApp.primaryStage);
            dialogStage.setMinWidth(400);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.setTitle("Добавить категорию металла");
            AddEditMetalCategoryDialog controller = loader.getController();
            controller.setAddDialogStage(dialogStage);
            dialogStage.showAndWait();
            updateList();
        } catch (IOException e) {
            System.out.println("Ошибка открытия окна: " + e.getMessage());
        }
    }

    @FXML
    void btnDeleteCategory(ActionEvent event) {
        MetalCategoryTableItem currentItem = metalCategoriesTable.getSelectionModel().getSelectedItem();
        int currentItemId = metalCategoriesTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText("Удаление записи");
            alert.setContentText("Вы действительно хотите удалить \"" + currentItem.getName() + " " + currentItem.getPurity() + "\"?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new MetalCategoryService().delete(currentItem.getMetalCategory());
                metalCategoriesTable.getItems().remove(currentItemId);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение");
            alert.setContentText("Выберите запись в таблице для удаления");
            alert.showAndWait();
        }
    }

    @FXML
    void btnEditCategory(ActionEvent event) {
        MetalCategoryTableItem currentItem = metalCategoriesTable.getSelectionModel().getSelectedItem();
        int currentItemId = metalCategoriesTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            try {
                FXMLLoader loader = new FXMLLoader(MetalsApp.class.getResource("add-edit-metal-category-dialog.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(MetalsApp.primaryStage);
                dialogStage.setMinWidth(400);
                dialogStage.setScene(new Scene(loader.load()));
                dialogStage.setTitle("Редактировать категорию металла");
                AddEditMetalCategoryDialog controller = loader.getController();
                controller.setEditDialogStage(dialogStage, currentItem.getMetalCategory());
                dialogStage.showAndWait();
                updateList();
            } catch (IOException e) {
                System.out.println("Ошибка открытия окна: " + e.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение");
            alert.setContentText("Выберите запись в таблице для редактирования");
            alert.showAndWait();
        }
    }

    @FXML
    void btnOff(ActionEvent event) {
        MetalsApp.primaryStage.close();
    }

    @FXML
    void btnUpdateCategories(ActionEvent event) {
        updateList();
    }

    public void updateList() {
        metalCategories = new MetalCategoryService().findAll();
        categoriesObservable = FXCollections.observableArrayList();
        for (MetalCategory category : metalCategories) {
            categoriesObservable.add(new MetalCategoryTableItem(category));
        }
        metalCategoriesTable.setItems(categoriesObservable);
    }

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        purityColumn.setCellValueFactory(new PropertyValueFactory<>("purity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerGram"));
        updateList();
    }

    public void btnClientsOnAction(ActionEvent actionEvent) {
        MetalsApp.primaryStage.setScene(MetalsApp.clients);
    }

    public void btnTransactionsOnAction(ActionEvent actionEvent) {
        MetalsApp.primaryStage.setScene(MetalsApp.transactions);
    }
}
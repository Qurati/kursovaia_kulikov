package ru.qurati.metalsapp.controller.transaction;

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
import ru.qurati.metalsapp.model.Client;
import ru.qurati.metalsapp.model.MetalCategory;
import ru.qurati.metalsapp.model.Transaction;
import ru.qurati.metalsapp.service.ClientService;
import ru.qurati.metalsapp.service.MetalCategoryService;
import ru.qurati.metalsapp.service.TransactionService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TransactionController {
    private List<Transaction> transactions;
    private ObservableList<TransactionTableItem> transactionsObservable;
    private Map<Integer, String> categoryMap;
    private Map<Integer, String> clientMap;

    @FXML
    private TableView<TransactionTableItem> transactionsTable;
    @FXML
    private TableColumn<TransactionTableItem, String> categoryColumn;
    @FXML
    private TableColumn<TransactionTableItem, String> clientColumn;
    @FXML
    private TableColumn<TransactionTableItem, String> weightColumn;
    @FXML
    private TableColumn<TransactionTableItem, String> dateColumn;

    @FXML
    void btnAddTransaction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(MetalsApp.class.getResource("add-edit-transaction-dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(MetalsApp.primaryStage);
            dialogStage.setMinWidth(400);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.setTitle("Добавить операцию");
            AddEditTransactionDialog controller = loader.getController();
            controller.setAddDialogStage(dialogStage);
            dialogStage.showAndWait();
            updateList();
        } catch (IOException e) {
            System.out.println("Ошибка открытия окна: " + e.getMessage());
        }
    }

    @FXML
    void btnEditTransaction(ActionEvent event) {
        TransactionTableItem currentItem = transactionsTable.getSelectionModel().getSelectedItem();
        if (currentItem != null) {
            try {
                FXMLLoader loader = new FXMLLoader(MetalsApp.class.getResource("add-edit-transaction-dialog.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(MetalsApp.primaryStage);
                dialogStage.setMinWidth(400);
                dialogStage.setScene(new Scene(loader.load()));
                dialogStage.setTitle("Редактировать операцию");
                AddEditTransactionDialog controller = loader.getController();
                controller.setEditDialogStage(dialogStage, currentItem.getTransaction());
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
    void btnDeleteTransaction(ActionEvent event) {
        TransactionTableItem currentItem = transactionsTable.getSelectionModel().getSelectedItem();
        if (currentItem != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText("Удаление записи");
            alert.setContentText("Вы действительно хотите удалить операцию с металлом \"" + currentItem.getCategoryName() + "\"?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new TransactionService().delete(currentItem.getTransaction());
                transactionsTable.getItems().remove(currentItem);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение");
            alert.setContentText("Выберите запись в таблице для удаления");
            alert.showAndWait();
        }
    }

    @FXML
    void btnUpdateTransactions(ActionEvent event) {
        updateList();
    }

    @FXML
    void btnClients(ActionEvent event) {
        MetalsApp.primaryStage.setScene(MetalsApp.clients);
    }

    @FXML
    void btnMetalCategories(ActionEvent event) {
        MetalsApp.primaryStage.setScene(MetalsApp.metalCategories);
    }

    @FXML
    void btnOff(ActionEvent event) {
        MetalsApp.primaryStage.close();
    }

    private void updateList() {
        transactions = new TransactionService().findAll();
        categoryMap = new HashMap<>();
        clientMap = new HashMap<>();

        for (MetalCategory category : new MetalCategoryService().findAll()) {
            categoryMap.put(category.getMetalCategoryId(), category.getName() + " (" + category.getPurity() + " проба)");
        }

        for (Client client : new ClientService().findAll()) {
            clientMap.put(client.getClientId(), client.getFullName());
        }

        transactionsObservable = FXCollections.observableArrayList();
        for (Transaction transaction : transactions) {
            String categoryName = categoryMap.getOrDefault(transaction.getMetalCategoryId(), "Неизвестно");
            String clientName = clientMap.getOrDefault(transaction.getClientId(), "Неизвестно");
            transactionsObservable.add(new TransactionTableItem(transaction, categoryName, clientName));
        }
        transactionsTable.setItems(transactionsObservable);
    }

    @FXML
    public void initialize() {
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
        updateList();
    }
}
package ru.qurati.metalsapp.controller.client;

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
import ru.qurati.metalsapp.service.ClientService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ClientController {
    private List<Client> clients;
    private ObservableList<ClientTableItem> clientsObservable;

    @FXML
    private TableView<ClientTableItem> clientsTable;
    @FXML
    private TableColumn<Client, String> nameColumn;
    @FXML
    private TableColumn<Client, String> passportColumn;
    @FXML
    private TableColumn<Client, String> phoneColumn;
    @FXML
    private TableColumn<Client, String> addressColumn;

    @FXML
    void btnAddClient(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(MetalsApp.class.getResource("add-edit-client-dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(MetalsApp.primaryStage);
            dialogStage.setMinWidth(400);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.setTitle("Добавить клиента");
            AddEditClientDialog controller = loader.getController();
            controller.setAddDialogStage(dialogStage);
            dialogStage.showAndWait();
            updateList();
        } catch (IOException e) {
            System.out.println("Ошибка открытия окна: " + e.getMessage());
        }
    }

    private void updateList() {
        clients = new ClientService().findAll();
        clientsObservable = FXCollections.observableArrayList();
        for (Client client : clients) {
            clientsObservable.add(new ClientTableItem(client));
        }
        clientsTable.setItems(clientsObservable);
    }

    @FXML
    void btnDeleteClient(ActionEvent event) {
        ClientTableItem currentItem = clientsTable.getSelectionModel().getSelectedItem();
        int currentItemId = clientsTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText("Удаление записи");
            alert.setContentText("Вы действительно хотите удалить клиента \"" + currentItem.getFullName() + "\"?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new ClientService().delete(currentItem.getClient());
                clientsTable.getItems().remove(currentItemId);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение");
            alert.setContentText("Выберите запись в таблице для удаления");
            alert.showAndWait();
        }
    }

    @FXML
    void btnEditClient(ActionEvent event) {
        ClientTableItem currentItem = clientsTable.getSelectionModel().getSelectedItem();
        int currentItemId = clientsTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            try {
                FXMLLoader loader = new FXMLLoader(MetalsApp.class.getResource("add-edit-client-dialog.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(MetalsApp.primaryStage);
                dialogStage.setMinWidth(400);
                dialogStage.setScene(new Scene(loader.load()));
                dialogStage.setTitle("Редактировать клиента");
                AddEditClientDialog controller = loader.getController();
                controller.setEditDialogStage(dialogStage, currentItem.getClient());
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
    void btnOffOnAction(ActionEvent event) {
        MetalsApp.primaryStage.close();
    }

    @FXML
    void btnUpdateClients(ActionEvent event) {
        updateList();
    }

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        passportColumn.setCellValueFactory(new PropertyValueFactory<>("passport"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        updateList();
    }

    public void btnTransactionsOnAction(ActionEvent actionEvent) {
        MetalsApp.primaryStage.setScene(MetalsApp.transactions);
    }

    public void btnMetalCategoriesOnAction(ActionEvent actionEvent) {
        MetalsApp.primaryStage.setScene(MetalsApp.metalCategories);
    }
}
package ru.qurati.metalsapp.controller.transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.qurati.metalsapp.model.Client;
import ru.qurati.metalsapp.model.MetalCategory;
import ru.qurati.metalsapp.model.Transaction;
import ru.qurati.metalsapp.service.ClientService;
import ru.qurati.metalsapp.service.MetalCategoryService;
import ru.qurati.metalsapp.service.TransactionService;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AddEditTransactionDialog implements Initializable {
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private ComboBox<String> userComboBox;
    @FXML
    private TextField lengthField;
    @FXML
    private Button okButton;
    @FXML
    private Label errorLabel;

    private Stage dialogStage;
    private Transaction transaction;
    private ObservableList<String> categories;
    private ObservableList<String> clients;
    private Map<String, Integer> categoryIdMap;
    private Map<String, Integer> clientIdMap;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categories = FXCollections.observableArrayList();
        clients = FXCollections.observableArrayList();
        categoryIdMap = new HashMap<>();
        clientIdMap = new HashMap<>();

        for (MetalCategory category : new MetalCategoryService().findAll()) {
            String displayName = category.getName() + " (" + category.getPurity() + " проба) - " + category.getPricePerGram() + " руб/г";
            categories.add(displayName);
            categoryIdMap.put(displayName, category.getMetalCategoryId());
        }

        for (Client client : new ClientService().findAll()) {
            clients.add(client.getFullName());
            clientIdMap.put(client.getFullName(), client.getClientId());
        }

        categoryComboBox.setItems(categories);
        userComboBox.setItems(clients);
    }

    private void add() {
        try {
            String selectedCategory = categoryComboBox.getSelectionModel().getSelectedItem();
            String selectedClient = userComboBox.getSelectionModel().getSelectedItem();
            String weightText = lengthField.getText();

            if (selectedCategory == null) {
                throw new IllegalArgumentException("Выберите категорию металла!");
            }
            if (selectedClient == null) {
                throw new IllegalArgumentException("Выберите клиента!");
            }
            if (weightText == null || weightText.trim().isEmpty()) {
                throw new IllegalArgumentException("Введите вес!");
            }

            Transaction newTransaction = new Transaction();
            newTransaction.setMetalCategoryId(categoryIdMap.get(selectedCategory));
            newTransaction.setClientId(clientIdMap.get(selectedClient));
            newTransaction.setWeight(weightText);
            newTransaction.setTransactionDate(LocalDateTime.now());

            new TransactionService().save(newTransaction);
            dialogStage.close();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    private void edit() {
        try {
            String selectedCategory = categoryComboBox.getSelectionModel().getSelectedItem();
            String selectedClient = userComboBox.getSelectionModel().getSelectedItem();
            String weightText = lengthField.getText();

            if (selectedCategory == null) {
                throw new IllegalArgumentException("Выберите категорию металла!");
            }
            if (selectedClient == null) {
                throw new IllegalArgumentException("Выберите клиента!");
            }
            if (weightText == null || weightText.trim().isEmpty()) {
                throw new IllegalArgumentException("Введите вес!");
            }

            transaction.setMetalCategoryId(categoryIdMap.get(selectedCategory));
            transaction.setClientId(clientIdMap.get(selectedClient));
            transaction.setWeight(weightText);

            new TransactionService().update(transaction);
            dialogStage.close();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void setAddDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        lengthField.setPromptText("Вес в граммах (например: 5.5)");
        okButton.setOnAction((www) -> add());
    }

    public void setEditDialogStage(Stage dialogStage, Transaction transaction) {
        this.transaction = transaction;
        this.dialogStage = dialogStage;

        for (Map.Entry<String, Integer> entry : categoryIdMap.entrySet()) {
            if (entry.getValue().equals(transaction.getMetalCategoryId())) {
                categoryComboBox.getSelectionModel().select(entry.getKey());
                break;
            }
        }

        for (Map.Entry<String, Integer> entry : clientIdMap.entrySet()) {
            if (entry.getValue().equals(transaction.getClientId())) {
                userComboBox.getSelectionModel().select(entry.getKey());
                break;
            }
        }

        lengthField.setText(transaction.getWeight().toString());
        okButton.setOnAction((www) -> edit());
    }
}
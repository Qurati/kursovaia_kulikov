package ru.qurati.metalsapp.controller.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.qurati.metalsapp.model.Client;
import ru.qurati.metalsapp.service.ClientService;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditClientDialog implements Initializable {
    @FXML
    private TextField addressField;
    @FXML
    private TextField emailField;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField kindPropertyField;
    @FXML
    private TextField nameField;
    @FXML
    private Button okButton;
    @FXML
    private TextField phoneField;

    private TextField passportField;
    private TextField phoneNumberField;
    private TextField addressTextField;

    private Stage dialogStage;
    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passportField = emailField;
        phoneNumberField = kindPropertyField;
        addressTextField = phoneField;
    }

    private void add() {
        try {
            Client client = new Client();
            client.setFullName(nameField.getText());
            client.setPassport(passportField.getText());
            client.setPhone(phoneNumberField.getText());
            client.setAddress(addressTextField.getText());
            new ClientService().save(client);
            dialogStage.close();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    void edit() {
        try {
            client.setFullName(nameField.getText());
            client.setPassport(passportField.getText());
            client.setPhone(phoneNumberField.getText());
            client.setAddress(addressTextField.getText());
            new ClientService().update(client);
            dialogStage.close();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void setAddDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

        nameField.setPromptText("ФИО");
        passportField.setPromptText("Паспорт");
        phoneNumberField.setPromptText("Телефон");
        addressTextField.setPromptText("Адрес");

        okButton.setOnAction((www) -> add());
    }

    public void setEditDialogStage(Stage dialogStage, Client client) {
        this.client = client;
        this.dialogStage = dialogStage;
        nameField.setText(client.getFullName());
        passportField.setText(client.getPassport());
        phoneNumberField.setText(client.getPhone());
        addressTextField.setText(client.getAddress());
        okButton.setOnAction((www) -> edit());
    }
}
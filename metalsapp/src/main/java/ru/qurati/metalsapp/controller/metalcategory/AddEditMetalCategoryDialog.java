package ru.qurati.metalsapp.controller.metalcategory;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.qurati.metalsapp.model.MetalCategory;
import ru.qurati.metalsapp.service.MetalCategoryService;

public class AddEditMetalCategoryDialog {
    @FXML
    private TextField conditionField;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField nameField;
    @FXML
    private Button okButton;
    @FXML
    private TextField rateField;
    @FXML
    private TextField termField;

    private Stage dialogStage;
    private MetalCategory metalCategory;

    void add() {
        try {
            MetalCategory metalCategory = new MetalCategory();
            metalCategory.setName(nameField.getText());
            metalCategory.setPurity(conditionField.getText());
            metalCategory.setPricePerGram(rateField.getText());
            new MetalCategoryService().save(metalCategory);
            dialogStage.close();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void setAddDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        conditionField.setPromptText("Проба (например: 585, 750, 925)");
        rateField.setPromptText("Цена за грамм");
        okButton.setOnAction((www) -> add());
    }

    void edit() {
        try {
            metalCategory.setName(nameField.getText());
            metalCategory.setPurity(conditionField.getText());
            metalCategory.setPricePerGram(rateField.getText());
            new MetalCategoryService().update(metalCategory);
            dialogStage.close();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void setEditDialogStage(Stage dialogStage, MetalCategory metalCategory) {
        this.metalCategory = metalCategory;
        this.dialogStage = dialogStage;
        nameField.setText(metalCategory.getName());
        conditionField.setText(metalCategory.getPurity());
        rateField.setText(metalCategory.getPricePerGram() != null ? metalCategory.getPricePerGram().toString() : "");
        okButton.setOnAction((www) -> edit());
    }
}
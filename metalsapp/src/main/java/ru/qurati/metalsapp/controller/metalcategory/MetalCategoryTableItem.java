package ru.qurati.metalsapp.controller.metalcategory;

import javafx.beans.property.SimpleStringProperty;
import ru.qurati.metalsapp.model.MetalCategory;

public class MetalCategoryTableItem {
    private SimpleStringProperty name;
    private SimpleStringProperty purity;
    private SimpleStringProperty pricePerGram;
    private MetalCategory metalCategory;

    public MetalCategoryTableItem(MetalCategory metalCategory) {
        this.name = new SimpleStringProperty(metalCategory.getName());
        this.purity = new SimpleStringProperty(metalCategory.getPurity());
        this.pricePerGram = new SimpleStringProperty(
                metalCategory.getPricePerGram() != null ? metalCategory.getPricePerGram().toString() : ""
        );
        this.metalCategory = metalCategory;
    }

    public String getName() { return name.get(); }
    public SimpleStringProperty nameProperty() { return name; }
    public void setName(String name) { this.name.set(name); }

    public String getPurity() { return purity.get(); }
    public SimpleStringProperty purityProperty() { return purity; }
    public void setPurity(String purity) { this.purity.set(purity); }

    public String getPricePerGram() { return pricePerGram.get(); }
    public SimpleStringProperty pricePerGramProperty() { return pricePerGram; }
    public void setPricePerGram(String pricePerGram) { this.pricePerGram.set(pricePerGram); }

    public MetalCategory getMetalCategory() { return metalCategory; }
    public void setMetalCategory(MetalCategory metalCategory) { this.metalCategory = metalCategory; }
}
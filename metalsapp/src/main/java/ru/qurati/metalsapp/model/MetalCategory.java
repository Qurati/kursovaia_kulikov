package ru.qurati.metalsapp.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "metal_categories")
public class MetalCategory {
    @Id
    @Column(name = "metal_categories_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer metalCategoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "purity")
    private String purity;

    @Column(name = "price_per_gram")
    private BigDecimal pricePerGram;

    public Integer getMetalCategoryId() {
        return metalCategoryId;
    }

    public void setMetalCategoryId(Integer metalCategoryId) {
        this.metalCategoryId = metalCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Название металла не должно быть пустым");
        }
    }

    public String getPurity() {
        return purity;
    }

    public void setPurity(String purity) {
        if (purity != null && !purity.isEmpty()) {
            this.purity = purity;
        } else {
            throw new IllegalArgumentException("Проба не должна быть пустой");
        }
    }

    public BigDecimal getPricePerGram() {
        return pricePerGram;
    }

    public void setPricePerGram(String priceText) {
        if (priceText != null && !priceText.isEmpty()) {
            try {
                this.pricePerGram = new BigDecimal(priceText);
                if (this.pricePerGram.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("Цена должна быть положительной!");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Цена должна быть числом!");
            }
        } else {
            throw new IllegalArgumentException("Цена не должна быть пустой!");
        }
    }

    @Override
    public String toString() {
        return name + " (" + purity + " проба) - " + pricePerGram + " руб/г";
    }
}
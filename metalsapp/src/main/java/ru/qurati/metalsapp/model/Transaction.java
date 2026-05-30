package ru.qurati.metalsapp.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @Column(name = "metal_category_id")
    private Integer metalCategoryId;

    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getMetalCategoryId() {
        return metalCategoryId;
    }

    public void setMetalCategoryId(Integer metalCategoryId) {
        this.metalCategoryId = metalCategoryId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(String weightText) {
        if (weightText != null && !weightText.isEmpty()) {
            try {
                this.weight = new BigDecimal(weightText);
                if (this.weight.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("Вес должен быть положительным числом!");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Вес должен быть числом!");
            }
        } else {
            throw new IllegalArgumentException("Вес не должен быть пустым!");
        }
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction #" + transactionId;
    }
}
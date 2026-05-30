package ru.qurati.metalsapp.controller.transaction;

import javafx.beans.property.SimpleStringProperty;
import ru.qurati.metalsapp.model.Transaction;
import java.time.format.DateTimeFormatter;

public class TransactionTableItem {
    private final SimpleStringProperty categoryName;
    private final SimpleStringProperty clientName;
    private final SimpleStringProperty weight;
    private final SimpleStringProperty transactionDate;
    private final Transaction transaction;

    public TransactionTableItem(Transaction transaction, String categoryName, String clientName) {
        this.categoryName = new SimpleStringProperty(categoryName);
        this.clientName = new SimpleStringProperty(clientName);
        this.weight = new SimpleStringProperty(transaction.getWeight().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.transactionDate = new SimpleStringProperty(
                transaction.getTransactionDate() != null ? transaction.getTransactionDate().format(formatter) : ""
        );
        this.transaction = transaction;
    }

    public String getCategoryName() { return categoryName.get(); }
    public SimpleStringProperty categoryNameProperty() { return categoryName; }

    public String getClientName() { return clientName.get(); }
    public SimpleStringProperty clientNameProperty() { return clientName; }

    public String getWeight() { return weight.get(); }
    public SimpleStringProperty weightProperty() { return weight; }

    public String getTransactionDate() { return transactionDate.get(); }
    public SimpleStringProperty transactionDateProperty() { return transactionDate; }

    public Transaction getTransaction() { return transaction; }
}
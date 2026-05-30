package ru.qurati.metalsapp.repository;

import ru.qurati.metalsapp.model.Transaction;

public class TransactionDao extends BaseDao<Transaction> {
    public TransactionDao() {
        super(Transaction.class);
    }
}
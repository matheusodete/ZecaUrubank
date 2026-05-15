package com.example.zecaurubank.Infrastructure.Persistence.Repositories;

import com.example.zecaurubank.Domain.Accounts.Entities.BankAccount;
import com.example.zecaurubank.Domain.Accounts.Repositories.IBankAccountRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryBankAccountRepository
        implements IBankAccountRepository {

    private final Map<String, BankAccount> database = new HashMap<>();

    @Override
    public BankAccount findById(String id) {
        return database.get(id);
    }

    @Override
    public void save(BankAccount account) {
        database.put(account.getId(), account);
    }
}
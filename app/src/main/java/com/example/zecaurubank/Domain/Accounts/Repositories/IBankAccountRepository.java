package com.example.zecaurubank.Domain.Accounts.Repositories;

import com.example.zecaurubank.Domain.Accounts.Entities.BankAccount;

public interface IBankAccountRepository {

    BankAccount findById(String id);

    void save(BankAccount account);
}
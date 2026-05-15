package com.example.zecaurubank.Domain.Accounts.Factories;

import com.example.zecaurubank.Domain.Accounts.Entities.BankAccount;
import com.example.zecaurubank.Domain.Accounts.ValueObjects.Money;

import java.util.UUID;

public class BankAccountFactory {

    public BankAccount create(String ownerName) {
        return new BankAccount(
                UUID.randomUUID().toString(),
                ownerName,
                new Money(0, "BRL")
        );
    }
}
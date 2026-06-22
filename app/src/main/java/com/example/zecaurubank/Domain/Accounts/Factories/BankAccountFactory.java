package com.example.zecaurubank.Domain.Accounts.Factories;

import com.example.zecaurubank.Domain.Accounts.Entities.BankAccount;
import com.example.zecaurubank.Domain.Accounts.ValueObjects.Money;
import com.example.zecaurubank.Domain.Customers.Entities.Customer;

import java.util.UUID;

public class BankAccountFactory {

    public BankAccount create(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Cliente obrigatório para abertura de conta.");
        }
        return new BankAccount(
                UUID.randomUUID().toString(),
                customer.getId(),
                customer.getName(),
                Money.zero("BRL")
        );
    }

    public BankAccount create(String ownerName) {
        return new BankAccount(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                ownerName,
                Money.zero("BRL")
        );
    }
}

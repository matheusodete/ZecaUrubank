package com.example.zecaurubank.Domain.Accounts.Entities;

import com.example.zecaurubank.Domain.Accounts.ValueObjects.Money;
import com.example.zecaurubank.Domain.Shared.DomainException;

public class BankAccount {

    private final String id;
    private final String ownerName;
    private Money balance;

    public BankAccount(String id, String ownerName, Money balance) {
        this.id = id;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public void deposit(Money value) {
        balance = balance.add(value);
    }

    public void withdraw(Money value) {
        if (balance.getAmount() < value.getAmount()) {
            throw new DomainException("Saldo insuficiente.");
        }

        balance = new Money(
                balance.getAmount() - value.getAmount(),
                balance.getCurrency()
        );
    }

    public String getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public Money getBalance() {
        return balance;
    }
}
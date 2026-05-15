package com.example.zecaurubank.Domain.Accounts.ValueObjects;

import com.example.zecaurubank.Domain.Shared.DomainException;

public class Money {

    private final double amount;
    private final String currency;

    public Money(double amount, String currency) {
        if (amount < 0) {
            throw new DomainException("O valor não pode ser negativo.");
        }

        if (currency == null || currency.trim().isEmpty()) {
            throw new DomainException("Moeda obrigatória.");
        }

        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public Money add(Money other) {
        if (!currency.equals(other.currency)) {
            throw new DomainException("Não é possível somar moedas diferentes.");
        }

        return new Money(amount + other.amount, currency);
    }
}
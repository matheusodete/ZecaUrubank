package com.example.zecaurubank.Domain.Accounts.ValueObjects;

import com.example.zecaurubank.Domain.Shared.DomainException;

import java.util.Objects;

public final class Money {

    private final double amount;
    private final String currency;

    public Money(double amount, String currency) {
        if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new DomainException("O valor monetário precisa ser válido.");
        }

        if (amount < 0) {
            throw new DomainException("O valor não pode ser negativo.");
        }

        if (currency == null || currency.trim().isEmpty()) {
            throw new DomainException("Moeda obrigatória.");
        }

        this.amount = round(amount);
        this.currency = currency.trim().toUpperCase();
    }

    public static Money zero(String currency) {
        return new Money(0, currency);
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public boolean isZero() {
        return amount == 0;
    }

    public boolean isGreaterThan(Money other) {
        assertSameCurrency(other);
        return amount > other.amount;
    }

    public Money add(Money other) {
        assertSameCurrency(other);
        return new Money(amount + other.amount, currency);
    }

    public Money subtract(Money other) {
        assertSameCurrency(other);
        if (other.amount > amount) {
            throw new DomainException("O resultado da operação não pode gerar valor negativo.");
        }
        return new Money(amount - other.amount, currency);
    }

    private void assertSameCurrency(Money other) {
        if (other == null) {
            throw new DomainException("Valor monetário obrigatório.");
        }
        if (!currency.equals(other.currency)) {
            throw new DomainException("Não é possível operar moedas diferentes.");
        }
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return Double.compare(money.amount, amount) == 0 && currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
}

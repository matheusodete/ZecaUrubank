package com.example.zecaurubank.Domain.Accounts.Entities;

import com.example.zecaurubank.Domain.Accounts.Enums.AccountStatus;
import com.example.zecaurubank.Domain.Accounts.ValueObjects.Money;
import com.example.zecaurubank.Domain.Shared.DomainException;

public class BankAccount {

    private final String id;
    private final String customerId;
    private final String ownerName;
    private Money balance;
    private AccountStatus status;

    public BankAccount(String id, String customerId, String ownerName, Money balance) {
        if (id == null || id.trim().isEmpty()) {
            throw new DomainException("Identificador da conta obrigatório.");
        }
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new DomainException("Identificador do cliente obrigatório.");
        }
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new DomainException("Nome do titular obrigatório.");
        }
        if (balance == null) {
            throw new DomainException("Saldo inicial obrigatório.");
        }

        this.id = id;
        this.customerId = customerId;
        this.ownerName = ownerName.trim();
        this.balance = balance;
        this.status = AccountStatus.ACTIVE;
    }

    public void deposit(Money value) {
        assertActive();
        assertPositiveValue(value);
        balance = balance.add(value);
    }

    public void withdraw(Money value) {
        assertActive();
        assertPositiveValue(value);
        if (value.isGreaterThan(balance)) {
            throw new DomainException("Saldo insuficiente.");
        }
        balance = balance.subtract(value);
    }

    public void block() {
        if (status == AccountStatus.CLOSED) {
            throw new DomainException("Conta encerrada não pode ser bloqueada.");
        }
        status = AccountStatus.BLOCKED;
    }

    public void unblock() {
        if (status == AccountStatus.CLOSED) {
            throw new DomainException("Conta encerrada não pode ser reativada.");
        }
        status = AccountStatus.ACTIVE;
    }

    public void close() {
        if (!balance.isZero()) {
            throw new DomainException("Conta só pode ser encerrada com saldo zerado.");
        }
        status = AccountStatus.CLOSED;
    }

    private void assertActive() {
        if (status != AccountStatus.ACTIVE) {
            throw new DomainException("Operação permitida apenas para conta ativa.");
        }
    }

    private void assertPositiveValue(Money value) {
        if (value == null || value.isZero()) {
            throw new DomainException("O valor da operação precisa ser maior que zero.");
        }
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public Money getBalance() {
        return balance;
    }

    public AccountStatus getStatus() {
        return status;
    }
}

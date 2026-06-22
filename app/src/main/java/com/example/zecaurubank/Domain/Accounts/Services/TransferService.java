package com.example.zecaurubank.Domain.Accounts.Services;

import com.example.zecaurubank.Domain.Accounts.Entities.BankAccount;
import com.example.zecaurubank.Domain.Accounts.ValueObjects.Money;
import com.example.zecaurubank.Domain.Shared.DomainException;

public class TransferService {

    public void transfer(BankAccount sender, BankAccount receiver, Money amount) {
        if (sender == null || receiver == null) {
            throw new DomainException("Contas de origem e destino são obrigatórias.");
        }
        if (sender.getId().equals(receiver.getId())) {
            throw new DomainException("Não é possível transferir para a mesma conta.");
        }
        sender.withdraw(amount);
        receiver.deposit(amount);
    }
}

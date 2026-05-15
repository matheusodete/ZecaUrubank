package com.example.zecaurubank.Domain.Accounts.Services;

import com.example.zecaurubank.Domain.Accounts.Entities.BankAccount;
import com.example.zecaurubank.Domain.Accounts.ValueObjects.Money;

public class TransferService {

    public void transfer(BankAccount sender,
                         BankAccount receiver,
                         Money amount) {

        sender.withdraw(amount);
        receiver.deposit(amount);
    }
}
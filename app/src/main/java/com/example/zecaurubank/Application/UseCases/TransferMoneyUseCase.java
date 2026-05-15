package com.example.zecaurubank.Application.UseCases;

import com.example.zecaurubank.Application.DTOs.TransferDTO;
import com.example.zecaurubank.Domain.Accounts.Entities.BankAccount;
import com.example.zecaurubank.Domain.Accounts.Repositories.IBankAccountRepository;
import com.example.zecaurubank.Domain.Accounts.Services.TransferService;
import com.example.zecaurubank.Domain.Accounts.ValueObjects.Money;

public class TransferMoneyUseCase {

    private final IBankAccountRepository repository;
    private final TransferService transferService;

    public TransferMoneyUseCase(
            IBankAccountRepository repository,
            TransferService transferService
    ) {
        this.repository = repository;
        this.transferService = transferService;
    }

    public void execute(TransferDTO dto) {

        BankAccount sender = repository.findById(dto.senderId);
        BankAccount receiver = repository.findById(dto.receiverId);

        transferService.transfer(
                sender,
                receiver,
                new Money(dto.amount, "BRL")
        );

        repository.save(sender);
        repository.save(receiver);
    }
}
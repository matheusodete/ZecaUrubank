package com.example.zecaurubank.Application.UseCases;

import com.example.zecaurubank.Application.DTOs.TransferDTO;
import com.example.zecaurubank.Domain.Accounts.Entities.BankAccount;
import com.example.zecaurubank.Domain.Accounts.Repositories.IBankAccountRepository;
import com.example.zecaurubank.Domain.Accounts.Services.TransferService;
import com.example.zecaurubank.Domain.Accounts.ValueObjects.Money;
import com.example.zecaurubank.Domain.Shared.DomainException;

public class TransferMoneyUseCase {

    private final IBankAccountRepository repository;
    private final TransferService transferService;

    public TransferMoneyUseCase(IBankAccountRepository repository, TransferService transferService) {
        this.repository = repository;
        this.transferService = transferService;
    }

    public void execute(TransferDTO dto) {
        if (dto == null) {
            throw new DomainException("Dados da transferência são obrigatórios.");
        }
        BankAccount sender = repository.findById(dto.senderId);
        BankAccount receiver = repository.findById(dto.receiverId);
        if (sender == null || receiver == null) {
            throw new DomainException("Conta de origem ou destino não encontrada.");
        }
        transferService.transfer(sender, receiver, new Money(dto.amount, "BRL"));
        repository.save(sender);
        repository.save(receiver);
    }
}

package com.example.zecaurubank.Application.UseCases;

import com.example.zecaurubank.Application.DTOs.RegisterCustomerDTO;
import com.example.zecaurubank.Domain.Accounts.Entities.BankAccount;
import com.example.zecaurubank.Domain.Accounts.Factories.BankAccountFactory;
import com.example.zecaurubank.Domain.Accounts.Repositories.IBankAccountRepository;
import com.example.zecaurubank.Domain.Customers.Entities.Customer;
import com.example.zecaurubank.Domain.Customers.Factories.CustomerFactory;
import com.example.zecaurubank.Domain.Customers.Repositories.ICustomerRepository;
import com.example.zecaurubank.Domain.Customers.ValueObjects.Cpf;
import com.example.zecaurubank.Domain.Customers.ValueObjects.Email;
import com.example.zecaurubank.Domain.Shared.DomainException;

public class RegisterCustomerUseCase {
    private final ICustomerRepository customerRepository;
    private final IBankAccountRepository accountRepository;
    private final CustomerFactory customerFactory;
    private final BankAccountFactory accountFactory;

    public RegisterCustomerUseCase(ICustomerRepository customerRepository,
                                   IBankAccountRepository accountRepository,
                                   CustomerFactory customerFactory,
                                   BankAccountFactory accountFactory) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.customerFactory = customerFactory;
        this.accountFactory = accountFactory;
    }

    public BankAccount execute(RegisterCustomerDTO dto) {
        Cpf cpf = new Cpf(dto.cpf);
        Email email = new Email(dto.email);
        if (customerRepository.findByCpf(cpf) != null) {
            throw new DomainException("Já existe cliente cadastrado com este CPF.");
        }
        if (customerRepository.findByEmail(email) != null) {
            throw new DomainException("Já existe cliente cadastrado com este e-mail.");
        }
        Customer customer = customerFactory.create(dto.name, dto.cpf, dto.email, dto.age, dto.address);
        BankAccount account = accountFactory.create(customer);
        customerRepository.save(customer);
        accountRepository.save(account);
        return account;
    }
}

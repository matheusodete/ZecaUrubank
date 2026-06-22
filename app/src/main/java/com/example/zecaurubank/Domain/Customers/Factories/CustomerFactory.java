package com.example.zecaurubank.Domain.Customers.Factories;

import com.example.zecaurubank.Domain.Customers.Entities.Customer;
import com.example.zecaurubank.Domain.Customers.ValueObjects.Cpf;
import com.example.zecaurubank.Domain.Customers.ValueObjects.Email;

import java.util.UUID;

public class CustomerFactory {
    public Customer create(String name, String cpf, String email, int age, String address) {
        return new Customer(
                UUID.randomUUID().toString(),
                name,
                new Cpf(cpf),
                new Email(email),
                age,
                address
        );
    }
}

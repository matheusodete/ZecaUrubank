package com.example.zecaurubank.Domain.Customers.Repositories;

import com.example.zecaurubank.Domain.Customers.Entities.Customer;
import com.example.zecaurubank.Domain.Customers.ValueObjects.Cpf;
import com.example.zecaurubank.Domain.Customers.ValueObjects.Email;

public interface ICustomerRepository {
    Customer findById(String id);
    Customer findByCpf(Cpf cpf);
    Customer findByEmail(Email email);
    void save(Customer customer);
}

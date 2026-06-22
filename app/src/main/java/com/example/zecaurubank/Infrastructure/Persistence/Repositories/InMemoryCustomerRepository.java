package com.example.zecaurubank.Infrastructure.Persistence.Repositories;

import com.example.zecaurubank.Domain.Customers.Entities.Customer;
import com.example.zecaurubank.Domain.Customers.Repositories.ICustomerRepository;
import com.example.zecaurubank.Domain.Customers.ValueObjects.Cpf;
import com.example.zecaurubank.Domain.Customers.ValueObjects.Email;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCustomerRepository implements ICustomerRepository {
    private final Map<String, Customer> customers = new HashMap<>();

    @Override
    public Customer findById(String id) {
        return customers.get(id);
    }

    @Override
    public Customer findByCpf(Cpf cpf) {
        for (Customer customer : customers.values()) {
            if (customer.getCpf().equals(cpf)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public Customer findByEmail(Email email) {
        for (Customer customer : customers.values()) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public void save(Customer customer) {
        customers.put(customer.getId(), customer);
    }
}

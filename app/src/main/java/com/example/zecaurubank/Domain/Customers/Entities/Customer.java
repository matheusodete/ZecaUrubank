package com.example.zecaurubank.Domain.Customers.Entities;

import com.example.zecaurubank.Domain.Customers.ValueObjects.Cpf;
import com.example.zecaurubank.Domain.Customers.ValueObjects.Email;
import com.example.zecaurubank.Domain.Shared.DomainException;

public class Customer {
    private final String id;
    private final Cpf cpf;
    private Email email;
    private String name;
    private int age;
    private String address;

    public Customer(String id, String name, Cpf cpf, Email email, int age, String address) {
        if (id == null || id.trim().isEmpty()) {
            throw new DomainException("Identificador do cliente obrigatório.");
        }
        this.id = id;
        this.cpf = cpf;
        updateProfile(name, age, address);
        changeEmail(email);
    }

    public void updateProfile(String name, int age, String address) {
        if (name == null || name.trim().length() < 3) {
            throw new DomainException("Nome do cliente deve possuir pelo menos 3 caracteres.");
        }
        if (age < 18) {
            throw new DomainException("Cliente precisa ser maior de idade para abrir conta.");
        }
        if (address == null || address.trim().isEmpty()) {
            throw new DomainException("Endereço obrigatório.");
        }
        this.name = name.trim();
        this.age = age;
        this.address = address.trim();
    }

    public void changeEmail(Email email) {
        if (email == null) {
            throw new DomainException("E-mail obrigatório.");
        }
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public Email getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
}

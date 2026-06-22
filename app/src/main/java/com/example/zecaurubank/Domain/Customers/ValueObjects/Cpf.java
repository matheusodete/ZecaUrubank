package com.example.zecaurubank.Domain.Customers.ValueObjects;

import com.example.zecaurubank.Domain.Shared.DomainException;

import java.util.Objects;

public final class Cpf {
    private final String value;

    public Cpf(String value) {
        if (value == null) {
            throw new DomainException("CPF obrigatório.");
        }
        String onlyDigits = value.replaceAll("\\D", "");
        if (onlyDigits.length() != 11) {
            throw new DomainException("CPF deve possuir 11 dígitos.");
        }
        if (onlyDigits.matches("(\\d)\\1{10}")) {
            throw new DomainException("CPF inválido.");
        }
        this.value = onlyDigits;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cpf)) return false;
        Cpf cpf = (Cpf) o;
        return value.equals(cpf.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

package com.example.zecaurubank.Domain.Shared;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
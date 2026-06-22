package com.example.zecaurubank;

import com.example.zecaurubank.Domain.Accounts.Entities.BankAccount;
import com.example.zecaurubank.Domain.Accounts.Services.TransferService;
import com.example.zecaurubank.Domain.Accounts.ValueObjects.Money;
import com.example.zecaurubank.Domain.Customers.ValueObjects.Cpf;
import com.example.zecaurubank.Domain.Customers.ValueObjects.Email;
import com.example.zecaurubank.Domain.Shared.DomainException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DomainRulesTest {
    @Test(expected = DomainException.class)
    public void moneyShouldNotAcceptNegativeAmount() {
        new Money(-1, "BRL");
    }

    @Test
    public void moneyEqualityShouldBeBasedOnValue() {
        assertEquals(new Money(10, "brl"), new Money(10.001, "BRL"));
    }

    @Test(expected = DomainException.class)
    public void accountShouldNotAllowWithdrawGreaterThanBalance() {
        BankAccount account = new BankAccount("acc-1", "cus-1", "Matheus", Money.zero("BRL"));
        account.withdraw(new Money(100, "BRL"));
    }

    @Test
    public void transferShouldMoveBalanceBetweenAccounts() {
        BankAccount sender = new BankAccount("acc-1", "cus-1", "Matheus", new Money(100, "BRL"));
        BankAccount receiver = new BankAccount("acc-2", "cus-2", "Cliente", Money.zero("BRL"));
        new TransferService().transfer(sender, receiver, new Money(35, "BRL"));
        assertEquals(new Money(65, "BRL"), sender.getBalance());
        assertEquals(new Money(35, "BRL"), receiver.getBalance());
    }

    @Test(expected = DomainException.class)
    public void transferShouldNotAllowSameAccount() {
        BankAccount account = new BankAccount("acc-1", "cus-1", "Matheus", new Money(100, "BRL"));
        new TransferService().transfer(account, account, new Money(10, "BRL"));
    }

    @Test
    public void cpfAndEmailShouldNormalizeValues() {
        assertEquals("12345678909", new Cpf("123.456.789-09").getValue());
        assertEquals("teste@email.com", new Email("  TESTE@EMAIL.COM ").getValue());
        assertTrue(new Email("teste@email.com").equals(new Email("TESTE@email.com")));
    }
}

package com.bta.axondemo.exposition.controlers.accounts.services;

import com.bta.axondemo.exposition.controlers.accounts.dto.AccountCreateDTO;
import com.bta.axondemo.exposition.controlers.accounts.dto.MoneyCreditDTO;
import com.bta.axondemo.exposition.controlers.accounts.dto.MoneyDebitDTO;

import java.util.concurrent.CompletableFuture;

public interface AccountCommandService {

    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO);
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO);
}

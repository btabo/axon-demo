package com.bta.axondemo.axondemoexpo.exposition;

import com.bta.axondemo.axondemoexpo.exposition.dto.AccountCreateDTO;
import com.bta.axondemo.axondemoexpo.exposition.dto.MoneyCreditDTO;
import com.bta.axondemo.axondemoexpo.exposition.dto.MoneyDebitDTO;

import java.util.concurrent.CompletableFuture;

public interface AccountCommandService {

    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO);
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO);
}

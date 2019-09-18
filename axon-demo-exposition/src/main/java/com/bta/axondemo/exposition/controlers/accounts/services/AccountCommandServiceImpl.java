package com.bta.axondemo.exposition.controlers.accounts.services;

import com.bta.axondemo.application.account.commands.CreateAccountCommand;
import com.bta.axondemo.application.account.commands.CreditMoneyCommand;
import com.bta.axondemo.application.account.commands.DebitMoneyCommand;
import com.bta.axondemo.exposition.controlers.accounts.dto.AccountCreateDTO;
import com.bta.axondemo.exposition.controlers.accounts.dto.MoneyCreditDTO;
import com.bta.axondemo.exposition.controlers.accounts.dto.MoneyDebitDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {

    private final CommandGateway commandGateway;

    public AccountCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO) {
        return commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(), accountCreateDTO.getStartingBalance(), accountCreateDTO.getCurrency()));
    }

    @Override
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO) {
        return commandGateway.send(new CreditMoneyCommand(accountNumber, moneyCreditDTO.getCreditAmount(), moneyCreditDTO.getCurrency()));
    }

    @Override
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO) {
        return commandGateway.send(new DebitMoneyCommand(accountNumber, moneyDebitDTO.getDebitAmount(), moneyDebitDTO.getCurrency()));
    }
}

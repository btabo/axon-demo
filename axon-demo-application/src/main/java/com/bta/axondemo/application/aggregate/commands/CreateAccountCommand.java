package com.bta.axondemo.application.aggregate.commands;


import com.bta.axondemo.application.commons.BaseCommand;

public class CreateAccountCommand extends BaseCommand<String> {
    public final double accountBalance;
    public final String currency;
    public CreateAccountCommand(String id, double accountBalance, String currency) {
        super(id);
        this.accountBalance = accountBalance;
        this.currency = currency;
    }
}

package com.bta.axondemo.application.account.commands;

import com.bta.axondemo.application.commons.BaseCommand;

public class CreditMoneyCommand extends BaseCommand<String> {
    public final double creditAmount;
    public final String currency;
    public CreditMoneyCommand(String id, double creditAmount, String currency) {
        super(id);
        this.creditAmount = creditAmount;
        this.currency = currency;
    }
}

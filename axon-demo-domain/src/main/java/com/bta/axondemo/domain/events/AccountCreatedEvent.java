package com.bta.axondemo.domain.events;

import com.bta.axondemo.domain.commons.BaseEvent;

public class AccountCreatedEvent extends BaseEvent<String> {
    public final double accountBalance;
    public final String currency;
    public AccountCreatedEvent(String id, double accountBalance, String currency) {
        super(id);
        this.accountBalance = accountBalance;
        this.currency = currency;
    }
}

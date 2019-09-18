package com.bta.axondemo.domain.account.events;

import com.bta.axondemo.domain.commons.BaseEvent;

public class MoneyCreditedEvent extends BaseEvent<String> {
    public final double creditAmount;
    public final String currency;
    public MoneyCreditedEvent(String id, double creditAmount, String currency) {
        super(id);
        this.creditAmount = creditAmount;
        this.currency = currency;
    }
}

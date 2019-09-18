package com.bta.axondemo.domain.plr;

import com.bta.axondemo.domain.commons.BaseEvent;

import java.math.BigDecimal;

public class PlrCreatedEvent extends BaseEvent<String> {

    public BigDecimal loanAmount;
    public Integer loanTerm;

    public PlrCreatedEvent(String id, BigDecimal loanAmount, Integer loanTerm) {
        super(id);
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
    }
}

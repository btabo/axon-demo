package com.bta.axondemo.domain.plr.events;

import com.bta.axondemo.domain.commons.BaseEvent;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
public class PlrCreatedEvent extends BaseEvent<String> {

    public final BigDecimal loanAmount;
    public final Integer loanTerm;

    public PlrCreatedEvent(String id, BigDecimal loanAmount, Integer loanTerm) {
        super(id);
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
    }
}

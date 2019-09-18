package com.bta.axondemo.domain.plr;

import com.bta.axondemo.domain.commons.BaseEvent;

import java.math.BigDecimal;

public class SituationUpdatedEvent extends BaseEvent<String> {

    public BigDecimal revenues;

    public SituationUpdatedEvent(String id, BigDecimal revenues) {
        super(id);
        this.revenues = revenues;
    }
}

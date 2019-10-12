package com.bta.axondemo.domain.plr.events;

import com.bta.axondemo.domain.commons.BaseEvent;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
public class SituationUpdatedEvent extends BaseEvent<String> {

    public final BigDecimal revenues;

    public SituationUpdatedEvent(String id, BigDecimal revenues) {
        super(id);
        this.revenues = revenues;
    }
}

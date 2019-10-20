package com.bta.axondemo.domain.plr.events.v2;

import com.bta.axondemo.domain.commons.BaseEvent;
import lombok.ToString;
import org.axonframework.serialization.Revision;

import java.math.BigDecimal;

@ToString
@Revision("2.0")
public class SituationUpdatedEvent extends BaseEvent<String> {

    public final BigDecimal revenues;

    public final BigDecimal charges;

    public SituationUpdatedEvent(String id, BigDecimal revenues, BigDecimal charges) {
        super(id);
        this.revenues = revenues;
        this.charges = charges;
    }
}

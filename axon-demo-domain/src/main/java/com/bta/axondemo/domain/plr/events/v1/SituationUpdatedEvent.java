package com.bta.axondemo.domain.plr.events.v1;

import com.bta.axondemo.domain.commons.BaseEvent;
import lombok.ToString;
import org.axonframework.serialization.Revision;

import java.math.BigDecimal;

@ToString
@Revision("1.0")
public class SituationUpdatedEvent extends BaseEvent<String> {

    public final BigDecimal revenues;

    public SituationUpdatedEvent(String id, BigDecimal revenues) {
        super(id);
        this.revenues = revenues;
    }
}

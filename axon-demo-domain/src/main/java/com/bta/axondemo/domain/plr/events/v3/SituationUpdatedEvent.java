package com.bta.axondemo.domain.plr.events.v3;

import com.bta.axondemo.domain.commons.BaseEvent;
import lombok.ToString;
import org.axonframework.serialization.Revision;

import java.math.BigDecimal;

@ToString
@Revision("3.0")
public class SituationUpdatedEvent extends BaseEvent<String> {

    public final BigDecimal revenues;

    public final BigDecimal charges;

    public final BigDecimal personalContribution;

    public SituationUpdatedEvent(String id, BigDecimal revenues, BigDecimal charges, BigDecimal personalContribution) {
        super(id);
        this.revenues = revenues;
        this.charges = charges;
        this.personalContribution = personalContribution;
    }
}

package com.bta.axondemo.domain.plr.events.v1;

import com.bta.axondemo.domain.commons.BaseEvent;
import lombok.ToString;
import org.axonframework.serialization.Revision;

import java.math.BigDecimal;

@ToString
@Revision("1.0")
public class PlrCreatedEvent extends BaseEvent<String> {

    public final BigDecimal loanAmount;
    public final Integer loanTerm;

    public PlrCreatedEvent(String id, BigDecimal loanAmount, Integer loanTerm) {
        super(id);
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
    }
}

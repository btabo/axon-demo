package com.bta.axondemo.domain.plr.events.v2;

import com.bta.axondemo.domain.commons.BaseEvent;
import lombok.ToString;
import org.axonframework.serialization.Revision;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ToString
@Revision("2.0")
public class PlrCreatedEvent extends BaseEvent<String> {

    public final BigDecimal loanAmount;
    public final Integer loanTerm;
    public final LocalDateTime creationDate;


    public PlrCreatedEvent(String id, BigDecimal loanAmount, Integer loanTerm, LocalDateTime creationDate) {
        super(id);
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
        this.creationDate = creationDate;
    }
}

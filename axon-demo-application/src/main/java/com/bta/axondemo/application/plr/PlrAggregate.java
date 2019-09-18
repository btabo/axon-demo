package com.bta.axondemo.application.plr;

import com.bta.axondemo.application.plr.commands.CreatePlrCommand;
import com.bta.axondemo.domain.plr.PlrCreatedEvent;
import com.bta.axondemo.domain.plr.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.List;

@Aggregate
@Builder @AllArgsConstructor @NoArgsConstructor
public class PlrAggregate {

    @AggregateIdentifier
    private String plrId;

    private BigDecimal loanAmount;
    private Integer loanTerm;
    private List<Profile> profiles;

    @CommandHandler
    public PlrAggregate(CreatePlrCommand createPlrCommand) {
        this.plrId = createPlrCommand.id;
        this.loanAmount = createPlrCommand.plr.loanAmount;
        this.loanTerm = createPlrCommand.plr.loanTerm;
        AggregateLifecycle.apply(new PlrCreatedEvent(createPlrCommand.id, createPlrCommand.plr.loanAmount, createPlrCommand.plr.loanTerm));
    }
}

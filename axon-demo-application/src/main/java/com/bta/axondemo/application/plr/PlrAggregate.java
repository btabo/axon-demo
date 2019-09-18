package com.bta.axondemo.application.plr;

import com.bta.axondemo.application.plr.commands.AddSituationCommand;
import com.bta.axondemo.application.plr.commands.CreatePlrCommand;
import com.bta.axondemo.domain.plr.PlrCreatedEvent;
import com.bta.axondemo.domain.plr.PlrProfileAddedEvent;
import com.bta.axondemo.domain.plr.SituationUpdatedEvent;
import com.bta.axondemo.domain.plr.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Aggregate
@Builder @AllArgsConstructor @NoArgsConstructor
public class PlrAggregate {

    @AggregateIdentifier
    private String plrId;

    private BigDecimal loanAmount;
    private Integer loanTerm;
    private List<Profile> profiles = new ArrayList<>();

    private  BigDecimal revenues;

    @CommandHandler
    public PlrAggregate(CreatePlrCommand createPlrCommand) {
        AggregateLifecycle.apply(new PlrCreatedEvent(createPlrCommand.id, createPlrCommand.plr.loanAmount, createPlrCommand.plr.loanTerm));
        createPlrCommand.plr.profiles.forEach(p -> {
            AggregateLifecycle.apply(new PlrProfileAddedEvent(createPlrCommand.id, p));
        });
    }

    @EventSourcingHandler
    protected void on(PlrCreatedEvent plrCreatedEvent) {
        this.plrId = plrCreatedEvent.id;
        this.loanAmount = plrCreatedEvent.loanAmount;
        this.loanTerm = plrCreatedEvent.loanTerm;
    }

    @EventSourcingHandler
    protected void on(PlrProfileAddedEvent plrProfileAddedEvent) {
        this.profiles.add(Profile.builder()
                .customerId(plrProfileAddedEvent.customerId)
                .insuranceFormula(plrProfileAddedEvent.insuranceFormula)
                .insuranceType(plrProfileAddedEvent.insuranceType)
                .build());
    }

    @CommandHandler
    public void on(AddSituationCommand addSituationCommand) {
        AggregateLifecycle.apply(new SituationUpdatedEvent(addSituationCommand.id, addSituationCommand.revenues));
    }

    @EventSourcingHandler
    protected void on(SituationUpdatedEvent situationUpdatedEvent) {
        this.revenues = situationUpdatedEvent.revenues;
    }
}

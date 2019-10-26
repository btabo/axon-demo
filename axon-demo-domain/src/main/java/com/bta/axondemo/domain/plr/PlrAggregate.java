package com.bta.axondemo.domain.plr;

import com.bta.axondemo.domain.plr.commands.AddSituationCommand;
import com.bta.axondemo.domain.plr.commands.CreatePlrCommand;
import com.bta.axondemo.domain.plr.events.v1.PlrProfileAddedEvent;
import com.bta.axondemo.domain.plr.events.v2.PlrCreatedEvent;
import com.bta.axondemo.domain.plr.events.v3.SituationUpdatedEvent;
import com.bta.axondemo.domain.plr.model.Profile;
import com.bta.axondemo.domain.plr.model.Profiles;
import com.bta.axondemo.domain.plr.model.TransactionDescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Aggregate
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class PlrAggregate {

    /**
     * PLR ID
     **/
    @AggregateIdentifier
    private String plrId;

    /**
     * Montant cummulé des revenus mensuels des emprunteurs
     **/
    private BigDecimal revenues;

    /**
     * Montant cummulé des revenus mensuels des emprunteurs
     **/
    private BigDecimal charges;

    /**
     * Apport Personnel
     **/
    private BigDecimal personalContribution;

    /**
     * Liste des profiles emprunteurs
     **/
    @AggregateMember
    private Profiles profiles;

    /**
     * Description de l'opération financière
     **/
    @AggregateMember
    private TransactionDescription transactionDescription;

    public static class PlrAggregateBuilder {
        public PlrAggregateBuilder fromPlr(PlrAggregate plr) {
            this.plrId = plr.plrId;
            this.transactionDescription = plr.transactionDescription;
            this.revenues = plr.revenues;
            this.profiles = plr.profiles;
            return this;
        }
    }

    @CommandHandler
    public PlrAggregate(CreatePlrCommand command) {
        log.debug("Processing Command = " + command.toString());
        AggregateLifecycle.apply(new PlrCreatedEvent(command.id, command.plr.transactionDescription.getLoanAmount(), command.plr.transactionDescription.getLoanTerm(), LocalDateTime.now()));
        command.plr.profiles.getProfiles().forEach(p -> AggregateLifecycle.apply(new PlrProfileAddedEvent(command.id, p)));
    }

    @EventSourcingHandler
    protected void on(PlrCreatedEvent event) {
        log.debug("Applying Event = " + event.toString());
        this.plrId = event.id;
        this.transactionDescription = TransactionDescription.builder().fromTransactionDescription(this.transactionDescription)
                .loanAmount(event.loanAmount)
                .loanTerm(event.loanTerm)
                .build();
    }

    @EventSourcingHandler
    protected void on(com.bta.axondemo.domain.plr.events.v1.PlrCreatedEvent event) {
        log.debug("Applying Event = " + event.toString());
        this.plrId = event.id;
        this.transactionDescription = TransactionDescription.builder().fromTransactionDescription(this.transactionDescription)
                .loanAmount(event.loanAmount)
                .loanTerm(event.loanTerm)
                .build();
    }

    @EventSourcingHandler
    protected void on(PlrProfileAddedEvent event) {
        log.debug("Applying Event = " + event.toString());
        this.profiles = Profiles.builder()
                .fromProfiles(this.profiles)
                .profile(Profile.builder()
                        .customerId(event.customerId)
                        .insuranceFormula(event.insuranceFormula)
                        .insuranceType(event.insuranceType)
                        .build())
                .build();
    }

    @CommandHandler
    public void on(AddSituationCommand command) {
        log.debug("Processing Command = " + command.toString());
        AggregateLifecycle.apply(new SituationUpdatedEvent(command.id, command.revenues, command.charges, BigDecimal.ZERO));
    }

    @EventSourcingHandler
    protected void on(SituationUpdatedEvent event) {
        log.debug("Applying Event = " + event.toString());
        this.revenues = event.revenues;
        this.charges = event.charges;
        this.personalContribution = event.personalContribution;
    }
}

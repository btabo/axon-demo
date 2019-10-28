package com.bta.axondemo.application.plr.services.queries.plr;

import com.bta.axondemo.domain.plr.PlrAggregate;
import com.bta.axondemo.domain.plr.events.v1.PlrProfileAddedEvent;
import com.bta.axondemo.domain.plr.events.v2.PlrCreatedEvent;
import com.bta.axondemo.domain.plr.events.v3.SituationUpdatedEvent;
import com.bta.axondemo.domain.plr.model.Profile;
import com.bta.axondemo.domain.plr.model.Profiles;
import com.bta.axondemo.domain.plr.model.TransactionDescription;
import com.bta.axondemo.domain.projections.PlrRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@ProcessingGroup("amqpEvents")
public class PlrQueryServiceImpl implements PlrQueryService {

    private final EventStore eventStore;

    private final PlrRepository plrRepository;

    @Override
    public List<Object> getPlrEvents(String plrId) {
        return eventStore.readEvents(plrId).asStream().map(Message::getPayload).collect(Collectors.toList());
    }

    @Override
    public Optional<PlrAggregate> getPlr(String plrId) {
        return plrRepository.getById(plrId);
    }

    @EventHandler
    public void on(PlrCreatedEvent event) {
        log.debug("Updating Projection = " + event.toString());
        plrRepository.save(PlrAggregate.builder()
                .plrId(event.id)
                .transactionDescription(TransactionDescription.builder()
                        .loanAmount(event.loanAmount)
                        .loanTerm(event.loanTerm)
                        .build())
                .build());
    }

    @EventHandler
    public void on(PlrProfileAddedEvent event) {
        log.debug("Updating Projection = " + event.toString());
        plrRepository.getById(event.id).ifPresent(plr -> plrRepository.save(PlrAggregate.builder()
                .fromPlr(plr)
                .profiles(Profiles.builder()
                        .fromProfiles(plr.getProfiles())
                        .profile(Profile.builder()
                                .customerId(event.customerId)
                                .insuranceType(event.insuranceType)
                                .insuranceFormula(event.insuranceFormula)
                                .build())
                        .build())
                .build()));
    }

    @EventHandler
    public void on(SituationUpdatedEvent event) {
        log.debug("Updating Projection = " + event.toString());
        plrRepository.getById(event.id).ifPresent(plr -> plrRepository.save(PlrAggregate.builder()
                .fromPlr(plr)
                .plrId(event.id)
                .revenues(event.revenues)
                .charges(event.charges)
                .build()));
    }

}
package com.bta.axondemo.application.plr.services.commands.admin;

import com.bta.axondemo.domain.admin.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.EventStreamUtils;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.jpa.DomainEventEntry;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.upcasting.event.EventUpcasterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@Service
@Slf4j
public class EventStoreAdminServices {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    Serializer serializer;
    @Autowired
    EventUpcasterChain eventUpcasterChain;

    public void broadcastAggregateEventsForUpgrade() {

        AtomicReference<String> currentAggregateId = new AtomicReference<>();
        List<DomainEventEntry> aggregateEvents = new ArrayList<>();

        Stream<DomainEventEntry> events = adminRepository.streamAllDomainEventEntries();
        events.forEach(e -> {
            if (e.getAggregateIdentifier().equals(currentAggregateId.get()) || null == currentAggregateId.get()) {
                aggregateEvents.add(e);
            } else {
                // Process aggregate list of events :
                upgradeAggregateDomainEvents(aggregateEvents);
                // Prepare next aggregate migration :
                aggregateEvents.clear();
                aggregateEvents.add(e);
            }
            currentAggregateId.set(e.getAggregateIdentifier());
        });

        // Process last aggregate list of events :
        upgradeAggregateDomainEvents(aggregateEvents);
    }

    private void upgradeAggregateDomainEvents(List<DomainEventEntry> eventList) {
        log.debug("-- new List --");
        DomainEventStream stream = EventStreamUtils.upcastAndDeserializeDomainEvents(eventList.stream(), serializer, eventUpcasterChain);
        stream.forEachRemaining(e -> {
            log.debug("Aggregate Id = " + e.getAggregateIdentifier() + " - type = " + e.getType());
        });
    }
}
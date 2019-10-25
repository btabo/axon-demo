package com.bta.axondemo.application.plr.services.commands.admin;

import com.bta.axondemo.domain.admin.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.eventstore.jpa.DomainEventEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@Service
@Slf4j
public class EventStoreAdminServices {

//    @Autowired
//    EventStore eventStore;

    @Autowired
    AdminRepository adminRepository;

    public void broadcastAggregateEventsForUpgrade() {

        AtomicReference<String> currentAggregateId = null;
        List<DomainEventEntry> aggregateEvents = new ArrayList<>();

        Stream<DomainEventEntry> events = adminRepository.streamAllDomainEventEntries();
        events.forEach(e -> {
            if (e.getAggregateIdentifier().equals(currentAggregateId) || null == currentAggregateId) {
                aggregateEvents.add(e);
            } else {
                // Process aggregate list of events
                // TODO

                // Prepare next aggregate migration
                aggregateEvents.clear();
                aggregateEvents.add(e);
            }
            currentAggregateId.set(e.getAggregateIdentifier());
        });

        // Process last aggregate list of events
        // TODO
    }

    private void upgradeAggregateDomainEvents(List<DomainEventEntry> eventList) {



    }
}
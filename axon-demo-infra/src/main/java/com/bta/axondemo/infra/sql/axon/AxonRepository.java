package com.bta.axondemo.infra.sql.axon;

import com.bta.axondemo.domain.admin.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.eventstore.jpa.DomainEventEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.stream.Stream;

@Component
@Slf4j
public class AxonRepository implements AdminRepository {

    @Autowired
    EntityManager em;

    public Stream<DomainEventEntry> streamAllDomainEventEntries() {
        return em.createQuery("SELECT e FROM DomainEventEntry e order by aggregateIdentifier, sequenceNumber", DomainEventEntry.class).getResultStream();
    }
}

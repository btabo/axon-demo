package com.bta.axondemo.infra.sql.axon;

import com.bta.axondemo.domain.admin.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.eventstore.jpa.DomainEventEntry;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@Slf4j
public class AxonRepository implements AdminRepository {

//    @Autowired
//    EntityManager em;

    public Stream<DomainEventEntry> streamAllDomainEventEntries() {
        return null;
        //return em.createQuery("SELECT e FROM DomainEventEntry e order by aggregateIdentifier, sequenceNumber", DomainEventEntry.class).getResultStream();
    }
}

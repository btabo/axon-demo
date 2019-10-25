package com.bta.axondemo.domain.admin;

import org.axonframework.eventsourcing.eventstore.jpa.DomainEventEntry;

import java.util.stream.Stream;

public interface AdminRepository {

    Stream<DomainEventEntry> streamAllDomainEventEntries();

}

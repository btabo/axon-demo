package com.bta.axondemo.exposition.controlers.plr.services;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlrQueryServiceImpl implements PlrQueryService {

    private final EventStore eventStore;

    public PlrQueryServiceImpl(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public List<Object> getPlrEvents(String plrId) {
        return eventStore.readEvents(plrId).asStream().map( s -> s.getPayload()).collect(Collectors.toList());
    }
}
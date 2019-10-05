package com.bta.axondemo.application.plr.services.queries;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
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
        return eventStore.readEvents(plrId).asStream().map(Message::getPayload).collect(Collectors.toList());
    }
}
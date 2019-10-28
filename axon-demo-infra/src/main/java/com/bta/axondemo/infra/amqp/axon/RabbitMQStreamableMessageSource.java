package com.bta.axondemo.infra.amqp.axon;

import org.axonframework.common.stream.BlockingStream;
import org.axonframework.eventhandling.TrackingToken;
import org.axonframework.messaging.StreamableMessageSource;

import java.time.Duration;
import java.time.Instant;

public class RabbitMQStreamableMessageSource implements StreamableMessageSource {

    @Override
    public BlockingStream openStream(TrackingToken trackingToken) {
        return null;
    }

    @Override
    public TrackingToken createTailToken() {
        return null;
    }

    @Override
    public TrackingToken createHeadToken() {
        return null;
    }

    @Override
    public TrackingToken createTokenAt(Instant dateTime) {
        return null;
    }

    @Override
    public TrackingToken createTokenSince(Duration duration) {
        return null;
    }
}

package com.bta.axondemo.infra.sql.axon.upcasters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.axonframework.serialization.SimpleSerializedType;
import org.axonframework.serialization.upcasting.event.IntermediateEventRepresentation;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PlrCreatedEventUpcaster extends SingleEventUpcaster {

    private static SimpleSerializedType inputType =
            new SimpleSerializedType(com.bta.axondemo.domain.plr.events.v1.PlrCreatedEvent.class.getTypeName(), "1.0");
    private static SimpleSerializedType targetType =
            new SimpleSerializedType(com.bta.axondemo.domain.plr.events.v2.PlrCreatedEvent.class.getTypeName(), "2.0");

    @Override
    protected boolean canUpcast(IntermediateEventRepresentation
                                        intermediateRepresentation) {
        return intermediateRepresentation.getType().equals(inputType);
    }

    @Override
    protected IntermediateEventRepresentation doUpcast(
            IntermediateEventRepresentation intermediateRepresentation) {
        return intermediateRepresentation.upcastPayload(
                targetType,
                JsonNode.class,
                node -> ((ObjectNode) node)
                        .put("creationDate", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
        );
    }
}

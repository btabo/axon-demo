package com.bta.axondemo.domain.plr.events.upcasters;

import com.bta.axondemo.domain.plr.events.v1.PlrCreatedEvent;
import org.axonframework.serialization.SimpleSerializedType;
import org.axonframework.serialization.upcasting.event.IntermediateEventRepresentation;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PlrCreatedEventUpcaster extends SingleEventUpcaster {

    private static SimpleSerializedType targetType =
            new SimpleSerializedType(PlrCreatedEvent.class.getTypeName(), "1.0");

    @Override
    protected boolean canUpcast(IntermediateEventRepresentation
                                        intermediateRepresentation) {
        return intermediateRepresentation.getType().equals(targetType);
    }

    @Override
    protected IntermediateEventRepresentation doUpcast(
            IntermediateEventRepresentation intermediateRepresentation) {
        return intermediateRepresentation.upcastPayload(
                new SimpleSerializedType(targetType.getName(), "2.0"),
                org.dom4j.Document.class,
                document -> {
                    document.getRootElement()
                            .addElement("creationDate")
                            .addText(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
                    return document;
                }
        );
    }
}

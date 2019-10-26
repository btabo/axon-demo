package com.bta.axondemo.infra.sql.axon.upcasters;

import org.axonframework.serialization.SimpleSerializedType;
import org.axonframework.serialization.upcasting.event.IntermediateEventRepresentation;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SituationUpdatedEventUpcaster extends SingleEventUpcaster {


    private static SimpleSerializedType inputType =
            new SimpleSerializedType(com.bta.axondemo.domain.plr.events.v2.SituationUpdatedEvent.class.getTypeName(), "2.0");
    private static SimpleSerializedType targetType =
            new SimpleSerializedType(com.bta.axondemo.domain.plr.events.v3.SituationUpdatedEvent.class.getTypeName(), "3.0");

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
                org.dom4j.Document.class,
                document -> {
                    document.getRootElement()
                            .addElement("personalContribution")
                            .addText(BigDecimal.ZERO.toString());
                    return document;
                }
        );
    }
}

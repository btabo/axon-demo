package com.bta.axondemo.exposition.rest.rebuilds;

import io.swagger.annotations.Api;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.eventhandling.EventProcessor;
import org.axonframework.eventhandling.EventTrackerStatus;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@RestController(value = "Rebuild projections")
@RequestMapping(value = "/api/rebuild")
@Api(value = "Rebuild projections", description = "Rebuild projections", tags = "Rebuild projections")
public class RebuildsController {

    private final EventProcessingConfiguration eventProcessingConfiguration;

    @Autowired
    public RebuildsController(EventProcessingConfiguration eventProcessingConfiguration) {
        this.eventProcessingConfiguration = eventProcessingConfiguration;
    }

    @GetMapping("rebuild-status")
    public Map<String, Map<Integer, EventTrackerStatus>> rebuildStatus() {
        Map<String, EventProcessor> eventProcessors = eventProcessingConfiguration.eventProcessors();
        return eventProcessors.values().stream()
                .filter(processor -> processor instanceof TrackingEventProcessor)
                .collect(Collectors.toMap(EventProcessor::getName,
                        processor -> ((TrackingEventProcessor) processor).processingStatus()
                ));
    }

    @GetMapping("rebuild")
    public ResponseEntity<String> rebuild(@RequestParam(name = "instant") Instant instant) {
        if (null != instant) {
            eventProcessingConfiguration.eventProcessors().values().stream()
                    .filter(processor -> processor instanceof TrackingEventProcessor)
                    .forEach(processor -> {
                        ((TrackingEventProcessor) processor).shutDown();
                        ((TrackingEventProcessor) processor).resetTokens(streamableMessageSource -> streamableMessageSource.createTokenAt(instant));
                        ((TrackingEventProcessor) processor).start();
                    });
        } else {
            eventProcessingConfiguration.eventProcessors().values().stream()
                    .filter(processor -> processor instanceof TrackingEventProcessor)
                    .forEach(processor -> {
                        ((TrackingEventProcessor) processor).shutDown();
                        ((TrackingEventProcessor) processor).resetTokens();
                        ((TrackingEventProcessor) processor).start();
                    });
        }
        return ResponseEntity.ok("Rebuild in progess");
    }

}

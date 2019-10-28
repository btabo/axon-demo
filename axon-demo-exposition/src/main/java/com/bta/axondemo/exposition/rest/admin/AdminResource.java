package com.bta.axondemo.exposition.rest.admin;

import com.bta.axondemo.application.plr.services.commands.admin.EventStoreAdminServices;
import io.swagger.annotations.Api;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.eventhandling.EventProcessor;
import org.axonframework.eventhandling.EventTrackerStatus;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@RestController(value = "Rebuild projections")
@RequestMapping(value = "/api/admin")
@Api(value = "Admin resource", description = "Rebuild projections & upgrade Event Store", tags = "Admin resource")
public class AdminResource {

    private final EventProcessingConfiguration eventProcessingConfiguration;
    private final EventStoreAdminServices eventStoreAdminServices;

    @Autowired
    public AdminResource(EventProcessingConfiguration eventProcessingConfiguration, EventStoreAdminServices eventStoreAdminServices) {
        this.eventProcessingConfiguration = eventProcessingConfiguration;
        this.eventStoreAdminServices = eventStoreAdminServices;
    }

    @GetMapping("read-model/rebuild-status")
    public Map<String, Map<Integer, EventTrackerStatus>> readModelRebuildStatus() {
        Map<String, EventProcessor> eventProcessors = eventProcessingConfiguration.eventProcessors();
        return eventProcessors.values().stream()
                .filter(processor -> processor instanceof TrackingEventProcessor)
                .collect(Collectors.toMap(EventProcessor::getName,
                        processor -> ((TrackingEventProcessor) processor).processingStatus()
                ));
    }

    @GetMapping("read-model")
    public ResponseEntity<String> readModelRebuild(@RequestParam(name = "fromDate", required = false) LocalDate fromDate) {
        Instant instant = Optional.ofNullable(fromDate).map(date -> Instant.from(date)).orElse(Instant.MIN.plus(1000, DAYS));
        eventProcessingConfiguration.eventProcessors().values().stream()
                .filter(processor -> processor instanceof TrackingEventProcessor)
                .forEach(processor -> {
                    processor.shutDown();
                    ((TrackingEventProcessor) processor).resetTokens(streamableMessageSource -> streamableMessageSource.createTokenAt(instant));
                    processor.start();
                });
        return ResponseEntity.ok("Rebuild in progess");
    }

    @PostMapping(name = "event-store/start-upgrade")
    public ResponseEntity<String> eventStoreStartUpgrade(@RequestParam(name = "fromDate", required = false) LocalDate fromDate) {
        eventStoreAdminServices.broadcastAggregateEventsForUpgrade();
        return ResponseEntity.ok("Event Store 'copy & transform' process has started");
    }

    @GetMapping("event-store/source-upgrade-status")
    public ResponseEntity<String> eventStoreSourceUpgradeStatus(@RequestParam(name = "fromDate", required = false) LocalDate fromDate) {
        return ResponseEntity.ok("Not yet implemented");
    }

    @GetMapping("event-store/target-upgrade-status")
    public ResponseEntity<String> eventStoreTargetUpgradeStatus(@RequestParam(name = "fromDate", required = false) LocalDate fromDate) {
        return ResponseEntity.ok("Not yet implemented");
    }

}

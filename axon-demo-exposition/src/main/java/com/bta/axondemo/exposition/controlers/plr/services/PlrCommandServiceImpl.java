package com.bta.axondemo.exposition.controlers.plr.services;

import com.bta.axondemo.application.plr.PlrAggregate;
import com.bta.axondemo.domain.plr.model.Profile;
import com.bta.axondemo.application.plr.commands.CreatePlrCommand;
import com.bta.axondemo.exposition.controlers.plr.dto.PlrDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class PlrCommandServiceImpl implements PlrCommandService {

    private final CommandGateway commandGateway;

    public PlrCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }


    @Override
    public CompletableFuture<String> createPlr(PlrDTO plrDTO) {
        return commandGateway.send(new CreatePlrCommand(UUID.randomUUID().toString(), toDomain(plrDTO)));
    }

    private static PlrAggregate toDomain(PlrDTO dto) {
        return PlrAggregate.builder()
                .loanAmount(dto.getLoanAmount())
                .profiles(dto.getProfiles().stream()
                        .map(p -> Profile.builder()
                                .customerId(p.getCustomerId())
                                .insuranceFormula(p.getInsuranceFormula())
                                .insuranceType(p.getInsuranceType())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}

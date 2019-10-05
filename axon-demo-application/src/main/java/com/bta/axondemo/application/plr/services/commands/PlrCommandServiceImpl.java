package com.bta.axondemo.application.plr.services.commands;

import com.bta.axondemo.domain.plr.PlrAggregate;
import com.bta.axondemo.domain.plr.commands.AddSituationCommand;
import com.bta.axondemo.domain.plr.commands.CreatePlrCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class PlrCommandServiceImpl implements PlrCommandService {

    private final CommandGateway commandGateway;

    public PlrCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }


    @Override
    public CompletableFuture<String> createPlr(PlrAggregate plr) {
        return commandGateway.send(new CreatePlrCommand(UUID.randomUUID().toString(), plr));
    }

    @Override
    public CompletableFuture<String> putSituation(String plrId, BigDecimal revenues) {
        return commandGateway.send(new AddSituationCommand(plrId, revenues));
    }


}

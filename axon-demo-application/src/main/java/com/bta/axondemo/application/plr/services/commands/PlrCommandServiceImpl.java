package com.bta.axondemo.application.plr.services.commands;

import com.bta.axondemo.domain.plr.PlrAggregate;
import com.bta.axondemo.domain.plr.commands.AddSituationCommand;
import com.bta.axondemo.domain.plr.commands.CreatePlrCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class PlrCommandServiceImpl implements PlrCommandService {

    private final CommandGateway commandGateway;

    public PlrCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createPlr(PlrAggregate plr) {
        CreatePlrCommand cmd = new CreatePlrCommand(UUID.randomUUID().toString(), plr);
        log.debug("Sending command = " + cmd.toString());
        return commandGateway.send(cmd);
    }

    @Override
    public CompletableFuture<String> putSituation(String plrId, BigDecimal revenues, BigDecimal charges) {
        AddSituationCommand cmd = new AddSituationCommand(plrId, revenues, charges);
        log.debug("Sending command = " + cmd.toString());
        return commandGateway.send(cmd);
    }


}

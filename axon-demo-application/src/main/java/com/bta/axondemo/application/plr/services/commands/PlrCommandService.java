package com.bta.axondemo.application.plr.services.commands;

import com.bta.axondemo.domain.plr.PlrAggregate;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

public interface PlrCommandService {

    CompletableFuture<String> createPlr(PlrAggregate plr);

    CompletableFuture<String> putSituation(String plrId, BigDecimal revenues);

}

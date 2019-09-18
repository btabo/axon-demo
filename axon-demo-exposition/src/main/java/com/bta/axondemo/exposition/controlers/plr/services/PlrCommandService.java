package com.bta.axondemo.exposition.controlers.plr.services;

import com.bta.axondemo.exposition.controlers.plr.dto.PlrDTO;
import com.bta.axondemo.exposition.controlers.plr.dto.SituationDTO;

import java.util.concurrent.CompletableFuture;

public interface PlrCommandService {

    public CompletableFuture<String> createPlr(PlrDTO plrDTO);

    public CompletableFuture<String> putSituation(String plrId, SituationDTO situationDTO);

}

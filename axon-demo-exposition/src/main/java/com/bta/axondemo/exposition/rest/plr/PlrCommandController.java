package com.bta.axondemo.exposition.rest.plr;

import com.bta.axondemo.application.plr.services.commands.plr.PlrCommandService;
import com.bta.axondemo.exposition.rest.plr.dto.PlrDTO;
import com.bta.axondemo.exposition.rest.plr.dto.SituationDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

import static com.bta.axondemo.exposition.rest.plr.mapper.PlrMapper.toDomain;

@RestController(value = "PLR Commands")
@RequestMapping(value = "/api/plr")
@Api(value = "Property Loan Request Commands", description = "PLR Commands Related Endpoints", tags = "PLR Commands")
public class PlrCommandController {

    @Autowired
    private PlrCommandService plrCommandService;

    @PostMapping
    public CompletableFuture<String> createPlr(@RequestBody PlrDTO plrDTO) {
        return plrCommandService.createPlr(toDomain(plrDTO));
    }

    @PutMapping(value = "/{plrId}/situation")
    public CompletableFuture<String> putSituation(@PathVariable(value = "plrId") String plrId, @RequestBody SituationDTO situationDTO) {
        return plrCommandService.putSituation(plrId, situationDTO.getRevenues(), situationDTO.getCharges());
    }

}

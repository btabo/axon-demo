package com.bta.axondemo.exposition.controlers.plr;

import com.bta.axondemo.exposition.controlers.plr.dto.PlrDTO;
import com.bta.axondemo.exposition.controlers.plr.dto.SituationDTO;
import com.bta.axondemo.exposition.controlers.plr.services.PlrCommandService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController(value = "PLR Commands")
@RequestMapping(value = "/api/plr")
@Api(value = "Property Loan Request Commands", description = "PLR Commands Related Endpoints", tags = "PLR Commands")
public class PlrCommandController {

    @Autowired
    private PlrCommandService plrCommandService;

    @PostMapping
    public CompletableFuture<String> createPlr(@RequestBody PlrDTO plrDTO){
        return plrCommandService.createPlr(plrDTO);
    }

    @PutMapping(value = "/{plrId}/situation")
    public CompletableFuture<String> putSituation(@PathVariable(value = "plrId") String plrId, @RequestBody SituationDTO situationDTO){
        return plrCommandService.putSituation(plrId, situationDTO);
    }

}

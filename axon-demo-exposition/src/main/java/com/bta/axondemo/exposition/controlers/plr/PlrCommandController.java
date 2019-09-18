package com.bta.axondemo.exposition.controlers.plr;

import com.bta.axondemo.exposition.controlers.accounts.dto.AccountCreateDTO;
import com.bta.axondemo.exposition.controlers.plr.dto.PlrDTO;
import com.bta.axondemo.exposition.controlers.plr.services.PlrCommandService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

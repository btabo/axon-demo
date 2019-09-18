package com.bta.axondemo.exposition.controlers.plr;

import com.bta.axondemo.exposition.controlers.plr.services.PlrQueryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "PLR Queries")
@RequestMapping(value = "/api/plr")
@Api(value = "Property Loan Request Queries", description = "PLR Query Events Endpoint", tags = "PLR Queries")
public class PlrQueryController {

    @Autowired
    private PlrQueryService plrQueryService;

    @GetMapping("/{plrId}")
    public List<Object> getPlr(@PathVariable(value = "plrId") String plrId){
        return plrQueryService.getPlrEvents(plrId);
    }
}

package com.bta.axondemo.exposition.rest.plr;

import com.bta.axondemo.application.plr.services.queries.PlrQueryService;
import com.bta.axondemo.exposition.rest.plr.dto.PlrDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{plrId}/events")
    public List<Object> getPlrEvents(@PathVariable(value = "plrId") String plrId) {
        return plrQueryService.getPlrEvents(plrId);
    }

    @GetMapping("/{plrId}")
    public ResponseEntity<PlrDTO> getPlr(@PathVariable(value = "plrId") String plrId) {
        return plrQueryService.getPlr(plrId).map(plr -> new ResponseEntity(plr, HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
}

package com.bta.axondemo.application.plr.services.queries;

import com.bta.axondemo.domain.plr.PlrAggregate;

import java.util.List;
import java.util.Optional;

public interface PlrQueryService {
    List<Object> getPlrEvents(String plrId);

    Optional<PlrAggregate> getPlr(String plrId);
}

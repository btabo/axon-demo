package com.bta.axondemo.domain.projections;

import com.bta.axondemo.domain.plr.PlrAggregate;

import java.util.Optional;

public interface PlrRepository {

    Optional<PlrAggregate> getById(String plrId);

    void save(PlrAggregate plr);

}

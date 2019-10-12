package com.bta.axondemo.infra.sql.plr;

import com.bta.axondemo.infra.sql.plr.model.PlrEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlrJpaRepository extends CrudRepository<PlrEntity, String> {


}

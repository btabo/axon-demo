package com.bta.axondemo.infra.sql;

import com.bta.axondemo.domain.plr.PlrAggregate;
import com.bta.axondemo.domain.plr.model.Profile;
import com.bta.axondemo.domain.plr.model.Profiles;
import com.bta.axondemo.domain.plr.model.TransactionDescription;
import com.bta.axondemo.domain.projections.PlrRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PlrRepositoryTest extends AbstractInfraTest {

    @Autowired
    private PlrRepository plrRepository;

    @Test
    public void testContext() {
        PlrAggregate plrToBeSaved = buildSimplePlr();
        plrRepository.save(plrToBeSaved);
        Optional<PlrAggregate> plrReturned = plrRepository.getById(plrToBeSaved.getPlrId());
        assertThat(plrReturned.get().getPlrId()).isEqualTo(plrToBeSaved.getPlrId());

    }

    private static PlrAggregate buildSimplePlr() {
        return PlrAggregate.builder()
                .transactionDescription(TransactionDescription.builder()
                        .loanAmount(new BigDecimal(100000))
                        .loanTerm(120)
                        .build())
                .profiles(Profiles.builder()
                        .profiles(Arrays.asList(Profile.builder()
                                .customerId("1234")
                                .insuranceFormula("insuranceFormula")
                                .insuranceType("insuranceType")
                                .build()))
                        .build())
                .plrId("plrId")
                .revenues(new BigDecimal(5000))
                .build();
    }
}

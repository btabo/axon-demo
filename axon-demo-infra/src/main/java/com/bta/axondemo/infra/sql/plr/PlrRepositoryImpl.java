package com.bta.axondemo.infra.sql.plr;

import com.bta.axondemo.domain.plr.PlrAggregate;
import com.bta.axondemo.domain.plr.model.Profile;
import com.bta.axondemo.domain.plr.model.Profiles;
import com.bta.axondemo.domain.plr.model.TransactionDescription;
import com.bta.axondemo.domain.projections.PlrRepository;
import com.bta.axondemo.infra.sql.plr.model.PlrEntity;
import com.bta.axondemo.infra.sql.plr.model.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
public class PlrRepositoryImpl implements PlrRepository {

    @Autowired
    private PlrJpaRepository jpaRepo;

    @Override
    public Optional<PlrAggregate> getById(String plrId) {
        return toDomain(jpaRepo.findById(plrId));
    }

    @Override
    public void save(PlrAggregate plr) {
        jpaRepo.save(toJpa(ofNullable(plr)).orElseThrow(() -> new IllegalArgumentException("Plr can't be null")));
    }

    private Optional<PlrEntity> toJpa(Optional<PlrAggregate> plr) {
        return plr.map(p -> PlrEntity.builder()
                .loanAmount(p.getTransactionDescription().getLoanAmount())
                .loanTerm(p.getTransactionDescription().getLoanTerm())
                .plrId(p.getPlrId())
                .profiles(Optional.ofNullable(p.getProfiles()).map(Profiles::getProfiles).orElse(new ArrayList<>()).stream()
                        .map(profile -> ProfileEntity.builder()
                                .id(UUID.randomUUID().toString())
                                .customerId(profile.getCustomerId())
                                .insuranceFormula(profile.getInsuranceFormula())
                                .insuranceType(profile.getInsuranceType())
                                .build())
                        .collect(Collectors.toSet()))
                .revenues(p.getRevenues())
                .build());
    }

    private Optional<PlrAggregate> toDomain(Optional<PlrEntity> plr) {
        return plr.map(p -> PlrAggregate.builder()
                .transactionDescription(TransactionDescription.builder()
                        .loanAmount(p.getLoanAmount())
                        .loanTerm(p.getLoanTerm())
                        .build())
                .plrId(p.getPlrId())
                .profiles(Profiles.builder()
                        .profiles(Optional.ofNullable(p.getProfiles()).orElse(new HashSet<>()).stream()
                                .map(profile -> Profile.builder()
                                        .customerId(profile.getCustomerId())
                                        .insuranceFormula(profile.getInsuranceFormula())
                                        .insuranceType(profile.getInsuranceType())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .revenues(p.getRevenues())
                .build());
    }
}

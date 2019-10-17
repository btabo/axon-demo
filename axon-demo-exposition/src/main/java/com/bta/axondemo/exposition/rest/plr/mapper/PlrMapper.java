package com.bta.axondemo.exposition.rest.plr.mapper;

import com.bta.axondemo.domain.plr.PlrAggregate;
import com.bta.axondemo.domain.plr.model.Profile;
import com.bta.axondemo.domain.plr.model.Profiles;
import com.bta.axondemo.domain.plr.model.TransactionDescription;
import com.bta.axondemo.exposition.rest.plr.dto.PlrDTO;
import com.bta.axondemo.exposition.rest.plr.dto.ProfileDTO;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlrMapper {

    public static PlrDTO toDto(PlrAggregate plr) {
        return PlrDTO.builder()
                .plrId(plr.getPlrId())
                .loanTerm(plr.getTransactionDescription().getLoanTerm())
                .loanAmount(plr.getTransactionDescription().getLoanAmount())
                .profiles(Optional.ofNullable(plr.getProfiles()).map(Profiles::getProfiles).orElse(new ArrayList<>()).stream()
                        .map(p -> ProfileDTO.builder()
                                .insuranceType(p.getInsuranceType())
                                .insuranceFormula(p.getInsuranceFormula())
                                .customerId(p.getCustomerId())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public static PlrAggregate toDomain(PlrDTO dto) {
        return PlrAggregate.builder()
                .transactionDescription(TransactionDescription.builder()
                        .loanAmount(dto.getLoanAmount())
                        .loanTerm(dto.getLoanTerm())
                        .build())
                .profiles(Profiles.builder()
                        .profiles(dto.getProfiles().stream()
                                .map(p -> Profile.builder()
                                        .customerId(p.getCustomerId())
                                        .insuranceFormula(p.getInsuranceFormula())
                                        .insuranceType(p.getInsuranceType())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .build();
    }
}

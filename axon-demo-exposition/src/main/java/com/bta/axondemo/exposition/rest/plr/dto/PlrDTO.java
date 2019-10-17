package com.bta.axondemo.exposition.rest.plr.dto;

import com.bta.axondemo.domain.plr.PlrAggregate;
import com.bta.axondemo.domain.plr.model.Profiles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlrDTO {

    private String plrId;

    private BigDecimal loanAmount;

    private Integer loanTerm;

    private List<ProfileDTO> profiles;


    public static PlrDTO toDTO(PlrAggregate plr) {
        return PlrDTO.builder()
                .loanAmount(plr.getTransactionDescription().getLoanAmount())
                .loanTerm(plr.getTransactionDescription().getLoanTerm())
                .plrId(plr.getPlrId())
                .profiles(Optional.ofNullable(plr.getProfiles()).map(Profiles::getProfiles).orElse(new ArrayList<>()).stream()
                        .map(p -> ProfileDTO.builder()
                                .customerId(p.getCustomerId())
                                .insuranceFormula(p.getInsuranceFormula())
                                .insuranceType(p.getInsuranceType())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

}

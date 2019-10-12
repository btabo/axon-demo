package com.bta.axondemo.exposition.rest.plr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ProfileDTO {
    private String customerId;
    private String insuranceType;
    private String insuranceFormula;
}

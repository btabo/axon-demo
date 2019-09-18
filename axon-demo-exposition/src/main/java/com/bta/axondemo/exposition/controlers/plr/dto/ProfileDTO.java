package com.bta.axondemo.exposition.controlers.plr.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProfileDTO {
    private String customerId;
    private String insuranceType;
    private String insuranceFormula;
}

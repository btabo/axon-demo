package com.bta.axondemo.exposition.rest.plr.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SituationDTO {
    private BigDecimal revenues;
    private BigDecimal charges;
}

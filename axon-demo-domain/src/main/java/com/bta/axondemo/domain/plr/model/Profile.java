package com.bta.axondemo.domain.plr.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Profile {

    private String customerId;
    private String insuranceType;
    private String insuranceFormula;

}

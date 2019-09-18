package com.bta.axondemo.domain.plr.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class Profile {

    private String customerId;
    private String insuranceType;
    private String insuranceFormula;

}

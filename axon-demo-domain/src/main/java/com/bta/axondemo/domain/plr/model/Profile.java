package com.bta.axondemo.domain.plr.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.EntityId;

@Builder
@Getter @Setter
public class Profile {

    @EntityId
    private String customerId;
    private String insuranceType;
    private String insuranceFormula;


}

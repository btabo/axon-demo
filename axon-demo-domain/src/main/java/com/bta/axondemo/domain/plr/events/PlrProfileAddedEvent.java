package com.bta.axondemo.domain.plr.events;

import com.bta.axondemo.domain.commons.BaseEvent;
import com.bta.axondemo.domain.plr.model.Profile;
import lombok.ToString;

@ToString
public class PlrProfileAddedEvent extends BaseEvent<String> {

    public final String customerId;
    public final String insuranceType;
    public final String insuranceFormula;

    public PlrProfileAddedEvent(String id, Profile profile) {
        super(id);
        this.customerId = profile.getCustomerId();
        this.insuranceFormula = profile.getInsuranceFormula();
        this.insuranceType = profile.getInsuranceType();
    }
}

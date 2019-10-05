package com.bta.axondemo.domain.plr.events;

import com.bta.axondemo.domain.commons.BaseEvent;
import com.bta.axondemo.domain.plr.model.Profile;

public class PlrProfileAddedEvent extends BaseEvent<String> {

    public String customerId;
    public String insuranceType;
    public String insuranceFormula;

    public PlrProfileAddedEvent(String id, Profile profile) {
        super(id);
        this.customerId = profile.getCustomerId();
        this.insuranceFormula = profile.getInsuranceFormula();
        this.insuranceType = profile.getInsuranceType();
    }
}

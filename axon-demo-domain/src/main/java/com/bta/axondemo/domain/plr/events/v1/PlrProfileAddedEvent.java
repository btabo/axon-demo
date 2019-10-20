package com.bta.axondemo.domain.plr.events.v1;

import com.bta.axondemo.domain.commons.BaseEvent;
import com.bta.axondemo.domain.plr.model.Profile;
import lombok.ToString;
import org.axonframework.serialization.Revision;

import java.util.Optional;

@ToString
@Revision("1.0")
public class PlrProfileAddedEvent extends BaseEvent<String> {

    public String customerId;
    public String insuranceType;
    public String insuranceFormula;

    public PlrProfileAddedEvent(String id, Profile profile) {
        super(id);
        Optional.ofNullable(profile).ifPresent(p -> {
            this.customerId = p.getCustomerId();
            this.insuranceFormula = p.getInsuranceFormula();
            this.insuranceType = p.getInsuranceType();
        });
    }
}

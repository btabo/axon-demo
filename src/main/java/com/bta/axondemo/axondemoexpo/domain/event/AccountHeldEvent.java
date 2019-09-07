package com.bta.axondemo.axondemoexpo.domain.event;

import com.bta.axondemo.axondemoexpo.domain.BaseEvent;
import com.bta.axondemo.axondemoexpo.domain.aggregate.Status;

public class AccountHeldEvent extends BaseEvent<String> {
    public final Status status;
    public AccountHeldEvent(String id, Status status) {
        super(id);
        this.status = status;
    }
}

package com.bta.axondemo.domain.account.events;

import com.bta.axondemo.domain.commons.BaseEvent;
import com.bta.axondemo.domain.commons.Status;

public class AccountActivatedEvent extends BaseEvent<String> {
    public final Status status;
    public AccountActivatedEvent(String id, Status status) {
        super(id);
        this.status = status;
    }
}

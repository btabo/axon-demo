package com.bta.axondemo.application.plr.commands;


import com.bta.axondemo.application.commons.BaseCommand;

import java.math.BigDecimal;

public class AddSituationCommand extends BaseCommand<String> {

    public final BigDecimal revenues;

    public AddSituationCommand(String plrId, BigDecimal revenues) {
        super(plrId);
        this.revenues = revenues;
    }
}

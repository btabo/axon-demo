package com.bta.axondemo.domain.plr.commands;


import com.bta.axondemo.domain.commons.BaseCommand;
import lombok.ToString;

import java.math.BigDecimal;


@ToString
public class AddSituationCommand extends BaseCommand<String> {

    public final BigDecimal revenues;
    public final BigDecimal charges;

    public AddSituationCommand(String plrId, BigDecimal revenues, BigDecimal charges) {
        super(plrId);
        this.revenues = revenues;
        this.charges = charges;
    }
}

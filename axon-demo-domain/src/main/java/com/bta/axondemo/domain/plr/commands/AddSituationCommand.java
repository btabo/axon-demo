package com.bta.axondemo.domain.plr.commands;


import com.bta.axondemo.domain.commons.BaseCommand;
import lombok.ToString;

import java.math.BigDecimal;


@ToString
public class AddSituationCommand extends BaseCommand<String> {

    public final BigDecimal revenues;

    public AddSituationCommand(String plrId, BigDecimal revenues) {
        super(plrId);
        this.revenues = revenues;
    }
}

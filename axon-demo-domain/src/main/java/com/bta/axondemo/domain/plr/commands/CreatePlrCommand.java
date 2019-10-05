package com.bta.axondemo.domain.plr.commands;


import com.bta.axondemo.domain.commons.BaseCommand;
import com.bta.axondemo.domain.plr.PlrAggregate;

public class CreatePlrCommand extends BaseCommand<String> {

    public final PlrAggregate plr;

    public CreatePlrCommand(String plrId, PlrAggregate plr) {
        super(plrId);
        this.plr = plr;
    }
}

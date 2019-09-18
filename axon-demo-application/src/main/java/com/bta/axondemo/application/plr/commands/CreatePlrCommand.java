package com.bta.axondemo.application.plr.commands;


import com.bta.axondemo.application.commons.BaseCommand;
import com.bta.axondemo.application.plr.PlrAggregate;

public class CreatePlrCommand extends BaseCommand<String> {

    public final PlrAggregate plr;

    public CreatePlrCommand(String plrId, PlrAggregate plr) {
        super(plrId);
        this.plr = plr;
    }
}

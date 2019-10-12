package com.bta.axondemo.domain.plr.commands;


import com.bta.axondemo.domain.commons.BaseCommand;
import com.bta.axondemo.domain.plr.PlrAggregate;
import lombok.ToString;

@ToString
public class CreatePlrCommand extends BaseCommand<String> {

    public final PlrAggregate plr;

    public CreatePlrCommand(String plrId, PlrAggregate plr) {
        super(plrId);
        this.plr = plr;
    }
}

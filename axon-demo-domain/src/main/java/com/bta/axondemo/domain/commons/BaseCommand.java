package com.bta.axondemo.domain.commons;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<T> {
    @TargetAggregateIdentifier
    public final T id;
    public BaseCommand(T id) {
        this.id = id;
    }
}
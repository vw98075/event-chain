package com.example.eventchain;

import org.springframework.context.ApplicationEvent;

public class NewDailyDataEvent extends ApplicationEvent {

    private final String symbol;

    public NewDailyDataEvent(Object source, String symbol) {
        super(source);
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

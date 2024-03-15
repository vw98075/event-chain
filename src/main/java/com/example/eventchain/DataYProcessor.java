package com.example.eventchain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service
@Component
public class DataYProcessor {

    private final Logger log = LoggerFactory.getLogger(DataYProcessor.class);

    @Async
    @EventListener
    public void process(NewCalculatedDataEvent event){
        log.debug("Data Y processor for " + event.symbol());
    }
}

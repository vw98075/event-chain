package com.example.eventchain;

import org.apache.catalina.core.ApplicationPushBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private final Logger log = LoggerFactory.getLogger(EventController.class);

    private final ApplicationEventPublisher publisher;

    public EventController(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @GetMapping("/event/{symbol}")
    public String newEvent(@PathVariable("symbol") String symbol){
        publisher.publishEvent(new NewDailyDataEvent(this, symbol));
        return "A new event is published for " + symbol;
    }
}

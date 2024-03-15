package com.example.eventchain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DataACalculationService {

    private final Logger log = LoggerFactory.getLogger(DataACalculationService.class);

    public void calculateData(String symbol){
        log.debug("Data A calculation for {}", symbol);
    }
}

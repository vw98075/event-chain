package com.example.eventchain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DataBCalculationService {

    private final Logger log = LoggerFactory.getLogger(DataBCalculationService.class);

    public void calculateData(String symbol){
        log.debug("Data B calculation for {}", symbol);
    }
}

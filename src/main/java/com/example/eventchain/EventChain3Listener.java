package com.example.eventchain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EventChain3Listener {

    private final Logger log = LoggerFactory.getLogger(EventChain3Listener.class);

    private final DataACalculationService dataACalculationService;

    private final DataBCalculationService dataBCalculationService;

    private final ApplicationEventPublisher publisher;

    public EventChain3Listener(DataACalculationService dataACalculationService, DataBCalculationService dataBCalculationService, ApplicationEventPublisher publisher) {
        this.dataACalculationService = dataACalculationService;
        this.dataBCalculationService = dataBCalculationService;
        this.publisher = publisher;
    }

    @Async
    @EventListener
    public CompletableFuture<Void> handleDataEntered(NewDailyDataEvent event) {
        String name = event.getSymbol();
        log.debug("Receiving an event for {}", name);

        // Perform calculations asynchronously for the entity identified by the name
//        CompletableFuture<Void> calculationA = CompletableFuture.runAsync(() -> dataACalculationService.calculateData(name));
//        CompletableFuture<Void> calculationB = CompletableFuture.runAsync(() -> dataBCalculationService.calculateData(name));

        // Wait for both calculations to complete
//        CompletableFuture<Void> allCalculations = CompletableFuture.allOf(calculationA, calculationB);

//        CompletableFuture<Void> independentCalculations = CompletableFuture.runAsync(() -> {
//            // Execute independent calculation tasks
//            // (e.g., dataACalculationService.calculateData(name))
//        });
//
//        // Execute dependent calculations synchronously after independent calculations are completed
//        CompletableFuture<Void> dependentCalculations = independentCalculations.thenRun(() -> {
//            // Execute dependent calculation tasks
//            // (e.g., dataDCalculationService.calculateData(name))
//        });

        // Perform calculations asynchronously for the entity identified by the name
        CompletableFuture<Void> calculationA = CompletableFuture.runAsync(() -> dataACalculationService.calculateData(name));
        CompletableFuture<Void> calculationB = CompletableFuture.runAsync(() -> dataBCalculationService.calculateData(name));

        // Wait for both calculations to complete
        CompletableFuture<Void> allCalculations = CompletableFuture.allOf(calculationA, calculationB);

        // When all calculations are completed, publish the event for DataXProcessor
        allCalculations.thenRun(() -> {
            publisher.publishEvent(new NewCalculatedDataEvent(name));
            log.debug("Published NewCalculatedDataEvent for {}", name);
        });

        // Return a CompletableFuture representing the completion of all calculations
        return allCalculations;
    }
}

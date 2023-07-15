package com.amrut.prabhu;

import io.micrometer.core.annotation.Timed;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/service")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private RestTemplate restTemplate;


    @Value("${spring.application.name}")
    private String applicationName;

    private final ObservationRegistry observationRegistry;

    public Controller(RestTemplate restTemplate, ObservationRegistry observationRegistry) {
        this.restTemplate = restTemplate;
        this.observationRegistry = observationRegistry;
    }

    @Observed(
            name = "file.creation",
            contextualName = "observed annotation showcase",
            lowCardinalityKeyValues = {"class.name", "ObservedFileCreationService"}
    )
    @GetMapping("/path1")
    @Timed(value = "greeting.time", description = "Time taken to return greeting")
    public ResponseEntity path1() {
        observationRegistry.getCurrentObservation().event(Observation.Event.of("Pruebas"));
        logger.info("Incoming request at {} for request /path1 ", applicationName);
        logger.info("ERROR ........... {} ", applicationName);
        String response = restTemplate.getForObject("http://localhost:8091/service/path2", String.class);
        return ResponseEntity.ok("response from /path1 + " + response);
    }

    @GetMapping("/path2")
    public ResponseEntity path2() {
        logger.info("Incoming request at {} at /path2", applicationName);
        return ResponseEntity.ok("response from /path2 ");
    }
}
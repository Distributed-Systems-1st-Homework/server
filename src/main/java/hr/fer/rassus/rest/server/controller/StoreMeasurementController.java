package hr.fer.rassus.rest.server.controller;

import hr.fer.rassus.rest.server.ServerApplication;
import hr.fer.rassus.rest.server.model.Measurement;
import hr.fer.rassus.rest.server.service.StoreMeasurementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreMeasurementController {
    private StoreMeasurementService storeMeasurementService;

    public StoreMeasurementController(StoreMeasurementService storeMeasurementService) {
        this.storeMeasurementService = storeMeasurementService;
    }

    @PostMapping("/measurements")
    public ResponseEntity<String> storeMeasures(@RequestBody final Measurement measurement) {
        ServerApplication.logger.info("Received request for storing measurements for sensor \""
                + measurement.getUsername() + "\"");

        boolean stored = storeMeasurementService.storeMeasurement(measurement);

        return stored ?
                ResponseEntity.ok("Measurement stored!")
                :
                ResponseEntity.status(500).body("Could not store measurement!");
    }
}

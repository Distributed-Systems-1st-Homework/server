package hr.fer.rassus.rest.server.controller;

import hr.fer.rassus.rest.server.ServerApplication;
import hr.fer.rassus.rest.server.model.SensorDescription;
import hr.fer.rassus.rest.server.service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerSensor(@RequestBody final SensorDescription sensorObject) {
        ServerApplication.logger.info("Received request for sensor \"" + sensorObject.getUsername()
                + "\" registration");

        boolean flag = registerService.register(sensorObject);
        return flag ?
                ResponseEntity.status(200).build()
                :
                ResponseEntity.status(400).body("Username already exists!");
    }
}

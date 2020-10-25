package hr.fer.rassus.rest.server.controller;

import hr.fer.rassus.rest.server.ServerApplication;
import hr.fer.rassus.rest.server.model.UserAddress;
import hr.fer.rassus.rest.server.request.RegisterUsernameDto;
import hr.fer.rassus.rest.server.service.ClosestNeighbourService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClosestNeighbourController {
    private ClosestNeighbourService closestNeighbourService;

    public ClosestNeighbourController(ClosestNeighbourService closestNeighbourService) {
        this.closestNeighbourService = closestNeighbourService;
    }

    @PostMapping("/closest")
    public ResponseEntity<?> registerSensor(@RequestBody final RegisterUsernameDto username) {
        ServerApplication.logger.info("Received request for searching the closest neighbour of sensor \""
                + username.getUsername());

        UserAddress closestNeighbour = closestNeighbourService.searchNeighbour(username.getUsername());
        return closestNeighbour != null ?
                ResponseEntity.status(200).body(closestNeighbour)
                :
                ResponseEntity.badRequest().body("No other active sensor found!");
    }
}

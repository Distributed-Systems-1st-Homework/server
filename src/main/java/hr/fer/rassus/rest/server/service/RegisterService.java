package hr.fer.rassus.rest.server.service;

import hr.fer.rassus.rest.server.model.SensorDescription;

public interface RegisterService {
    boolean register(SensorDescription description);
}

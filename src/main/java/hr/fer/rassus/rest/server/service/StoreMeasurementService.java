package hr.fer.rassus.rest.server.service;

import hr.fer.rassus.rest.server.model.Measurement;

public interface StoreMeasurementService {
    boolean storeMeasurement(Measurement measurement);
}

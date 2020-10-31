package hr.fer.rassus.rest.server.service.implementation;

import hr.fer.rassus.rest.server.ServerApplication;
import hr.fer.rassus.rest.server.model.Measurement;
import hr.fer.rassus.rest.server.model.SensorDescription;
import hr.fer.rassus.rest.server.service.StoreMeasurementService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class StoreMeasurementServiceImplementation implements StoreMeasurementService {
    private Map<String, Map<String, Float>> measurementsMap = new TreeMap<>();

    @Override
    public boolean storeMeasurement(Measurement measurement) {
        String username = measurement.getUsername();
        String parameter = measurement.getParameter();
        float averageValue = measurement.getAverageValue();
        Map<String, SensorDescription> activeSensors = RegisterServiceImplementation.sensorsMap;

        if(activeSensors.get(measurement.getUsername()) == null) {
            ServerApplication.logger.error("Sensor \"" + username + "\" is currently not active (not registered)");
            return false;
        }

        ServerApplication.logger.info("Storing measurements for sensor \"" + measurement.getUsername() + "\"");
        if (measurementsMap.get(username) != null) {
            measurementsMap.get(username).put(parameter, averageValue);
            return Float.compare(measurementsMap.get(username).get(parameter), averageValue) == 0;
        } else {
            measurementsMap.put(username, new TreeMap<>());
            measurementsMap.get(username).put(parameter, averageValue);
            return Float.compare(measurementsMap.get(username).get(parameter), averageValue) == 0;
        }
    }
}
package hr.fer.rassus.rest.server.service.implementation;

import hr.fer.rassus.rest.server.ServerApplication;
import hr.fer.rassus.rest.server.model.SensorDescription;
import hr.fer.rassus.rest.server.model.UserAddress;
import hr.fer.rassus.rest.server.service.ClosestNeighbourService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ClosestNeighbourServiceImplementation implements ClosestNeighbourService {
    private static final int EARTH_RADIUS = 6371;
    private SensorDescription searchingSensor;

    @Override
    public UserAddress searchNeighbour(String username) {
        Map<String, SensorDescription> activeSensors = RegisterServiceImplementation.sensorsMap;
        searchingSensor = activeSensors.get(username);
        double counter = 1;
        double distance;
        String closestSensor = null;
        double minimumDistance = 0;

        if(activeSensors.size() == 1) {
            ServerApplication.logger.error("Only server \"" + username + "\" is currently active (zero neighbours)");
            return null;
        }

        if(activeSensors.get(username) == null) {
            ServerApplication.logger.error("Sensor \"" + username + "\" is currently not active (not registered)");
            return null;
        }

        ServerApplication.logger.info("Calculating the closest neighbour for sensor \"" + username + "\"");
        for (Map.Entry<String, SensorDescription> entry : activeSensors.entrySet()) {
            if (entry.getKey().equals(username)) continue;

            if(counter-- > 0) {
                minimumDistance = calculate(entry);
            }

            distance = calculate(entry);
            if(distance <= minimumDistance) {
                minimumDistance = distance;
                closestSensor = entry.getKey();
            }
        }

        SensorDescription foundSensor = activeSensors.get(closestSensor);
        ServerApplication.logger.info("Closest neighbour of sensor \"" + username + "\" is sensor " +
                foundSensor + " and their distance is \"" + minimumDistance + "\"");
        return new UserAddress(foundSensor.getPort(), foundSensor.getIpAddress());
    }

    private double calculate(Map.Entry<String, SensorDescription> entry) {
        double dLongitude;
        double dLatitude;
        double a;
        double c;

        dLongitude = Math.abs(entry.getValue().getLongitude() - searchingSensor.getLongitude());
        dLatitude = Math.abs(entry.getValue().getLatitude() - searchingSensor.getLatitude());
        a = Math.pow(Math.sin(Math.toRadians(dLatitude / 2)), 2) +
                Math.cos(Math.toRadians(searchingSensor.getLatitude())) *
                        Math.cos(Math.toRadians(entry.getValue().getLatitude())) *
                        Math.pow(Math.toRadians(Math.sin(dLongitude / 2)), 2);
        c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return EARTH_RADIUS * c;
    }
}

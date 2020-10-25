package hr.fer.rassus.rest.server.service.implementation;

import hr.fer.rassus.rest.server.ServerApplication;
import hr.fer.rassus.rest.server.model.SensorDescription;
import hr.fer.rassus.rest.server.service.RegisterService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class RegisterServiceImplementation implements RegisterService {
    public static Map<String, SensorDescription> sensorsMap = new TreeMap<>();

    public RegisterServiceImplementation() {}

    @Override
    public boolean register(SensorDescription description) {
        if(!sensorsMap.containsKey(description.getUsername())) {
            sensorsMap.put(description.getUsername(), description);
            ServerApplication.logger.info("Registering sensor \""+ description.getUsername() + "\"");
            return true;
        } else {
            ServerApplication.logger.error("Sensor \""+ description.getUsername() + "\" is already registered");
            return false;
        }
    }
}

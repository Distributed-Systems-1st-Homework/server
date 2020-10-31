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
    public int register(SensorDescription description) {
        if(!this.sensorsMap.containsKey(description.getUsername())) {
            this.sensorsMap.put(description.getUsername(), description);
            ServerApplication.logger.info("Registering sensor \""+ description.getUsername() + "\"");
            return description.getUsername().equals(this.sensorsMap.get(description.getUsername()).getUsername()) ?
                    0 : -1;
        } else {
            ServerApplication.logger.error("Sensor \""+ description.getUsername() + "\" is already registered");
            return 1;
        }
    }
}

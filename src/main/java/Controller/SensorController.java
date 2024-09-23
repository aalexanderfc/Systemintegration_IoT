package Controller;

import Data.SensorData;
import Service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class SensorController {

    private final SensorDataService service;

    @Autowired
    public SensorController(SensorDataService sensorService) {
        this.service = sensorService;
    }

    @PostMapping("/sensor_data")
    public SensorData saveSensorData(@RequestParam double temperature, @RequestParam double humidity) {
        return service.saveSensorData(temperature, humidity);
    }
}


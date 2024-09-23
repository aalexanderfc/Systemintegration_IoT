package com.example.systemintegrationiot.Service;

import com.example.systemintegrationiot.Data.SensorData;
import com.example.systemintegrationiot.Repos.SensorDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class SensorDataService {

    @Autowired
    private SensorDataRepository repository;

    // Method to save sensor data
    public SensorData saveSensorData(double temperature, double humidity) {
        SensorData data = new SensorData();
        data.setTemperature(temperature);
        data.setHumidity(humidity);
        data.setTimestamp(LocalDateTime.now());
        return repository.save(data);
    }

    // Method to retrieve all sensor data
    public List<SensorData> getAllSensorData() {
        return repository.findAll();  // Fetch all sensor data from the repository
    }
}

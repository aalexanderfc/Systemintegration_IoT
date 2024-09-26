package com.example.systemintegrationiot.controllers;

import com.example.systemintegrationiot.models.Sensor;
import com.example.systemintegrationiot.models.SensorData;
import com.example.systemintegrationiot.repos.SensorDataRepo;
import com.example.systemintegrationiot.repos.SensorRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// API Controller for ESP32
@RestController
@RequestMapping("/SensorData")
public class SensorDataApiController {

    private final SensorDataRepo sensorDataRepo;
    private final SensorRepo sensorRepo;

    public SensorDataApiController(SensorDataRepo sensorDataRepo, SensorRepo sensorRepo) {
        this.sensorDataRepo = sensorDataRepo;
        this.sensorRepo = sensorRepo;
    }

    @GetMapping("/addData")
    public ResponseEntity<String> addData(@RequestParam Long sensor_id,
                                          @RequestParam String unit,
                                          @RequestParam double value) {
        // Log incoming data
        System.out.println("Received data: Sensor ID = " + sensor_id + ", Unit = " + unit + ", Value = " + value);

        // Find the sensor by ID
        Sensor sensor = sensorRepo.findById(sensor_id)
                .orElseThrow(() -> new IllegalArgumentException("Sensor with ID " + sensor_id + " not found"));

        // Create a new SensorData object and save it
        SensorData newSensorData = new SensorData();
        newSensorData.setSensor(sensor);
        newSensorData.setValue(value);  // You can add the unit information if needed
        sensorDataRepo.save(newSensorData);

        return ResponseEntity.ok("Data saved successfully!");
    }
}

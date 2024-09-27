package com.example.systemintegrationiot.controllers;

import com.example.systemintegrationiot.models.Sensor;
import com.example.systemintegrationiot.models.SensorData;
import com.example.systemintegrationiot.repos.SensorDataRepo;
import com.example.systemintegrationiot.repos.SensorRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/SensorData")
public class SensorDataController {

    private final SensorDataRepo sensorDataRepo;
    private final SensorRepo sensorRepo;

    public SensorDataController(SensorDataRepo sensorDataRepo, SensorRepo sensorRepo) {
        this.sensorDataRepo = sensorDataRepo;
        this.sensorRepo = sensorRepo;
    }

    // Main index route to load the base webpage
    @GetMapping("/")
    public String showIndexPage() {
        return "index";  // Render index.html
    }

    // Fetch sensor history and return to the view
    @GetMapping("/history/{id}")
    public String getSensorHistory(@PathVariable Long id, Model model) {
        Sensor sensor = sensorRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sensor med ID " + id + " finns inte"));

        List<SensorData> sensorDataList = sensorDataRepo.findBySensor(sensor);

        model.addAttribute("sensorId", sensor.getSensor_id());
        model.addAttribute("sensorName", sensor.getName());
        model.addAttribute("sensorType", sensor.getType());
        model.addAttribute("sensorDataList", sensorDataList);
        model.addAttribute("sensorTitle", " History Data");

        return "sensorhistory";
    }

    // Fetch the latest sensor data and return to the view
    @GetMapping("/lastdata")
    public String getLastSensorDataForMultipleSensors(Model model) {
        // Fetch sensor with ID 2 (temperature)
        Sensor sensorTemperature = sensorRepo.findById(2L)
                .orElseThrow(() -> new IllegalArgumentException("Sensor with ID 2 (Temperature) does not exist"));

        List<SensorData> sensorTemperatureDataList = sensorDataRepo.findBySensor(sensorTemperature);
        if (sensorTemperatureDataList.isEmpty()) {
            model.addAttribute("messageTemperature", "No data available for sensor ID 2 (Temperature)");
        } else {
            SensorData latestTemperatureData = sensorTemperatureDataList.stream()
                    .max(Comparator.comparing(SensorData::getCreated))
                    .orElse(null);
            model.addAttribute("temperatureSensorId", sensorTemperature.getSensor_id());
            model.addAttribute("temperatureSensorName", sensorTemperature.getName());
            model.addAttribute("temperatureSensorType", sensorTemperature.getType());
            model.addAttribute("latestTemperatureData", latestTemperatureData.getValue());
        }

        // Fetch sensor with ID 3 (humidity)
        Sensor sensorHumidity = sensorRepo.findById(3L)
                .orElseThrow(() -> new IllegalArgumentException("Sensor with ID 3 (Humidity) does not exist"));

        List<SensorData> sensorHumidityDataList = sensorDataRepo.findBySensor(sensorHumidity);
        if (sensorHumidityDataList.isEmpty()) {
            model.addAttribute("messageHumidity", "No data available for sensor ID 3 (Humidity)");
        } else {
            SensorData latestHumidityData = sensorHumidityDataList.stream()
                    .max(Comparator.comparing(SensorData::getCreated))
                    .orElse(null);
            model.addAttribute("humiditySensorId", sensorHumidity.getSensor_id());
            model.addAttribute("humiditySensorName", sensorHumidity.getName());
            model.addAttribute("humiditySensorType", sensorHumidity.getType());
            model.addAttribute("latestHumidityData", latestHumidityData.getValue());
        }

        return "lastSensorData";  // Return the Thymeleaf template to display both sensor data
    }

    // Create Sensor Functionality
    // Display the create sensor form
    @GetMapping("/createSensor")
    public String createSensorForm(Model model) {
        model.addAttribute("sensor", new Sensor());  // Create an empty Sensor object
        return "createSensor";
    }

    // Handle the form submission and create a new sensor
    @PostMapping("/createSensor")
    public String saveSensor(@ModelAttribute Sensor sensor, Model model) {
        sensorRepo.save(sensor);  // Save the sensor to the database

        model.addAttribute("message", "The sensor data has been saved successfully!");
        return  "TheDataIsSaved";

    }

}

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
    @GetMapping("/lastdata/{id}")
    public String getLastSensorData(@PathVariable Long id, Model model) {
        Sensor sensor = sensorRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sensor med ID " + id + " finns inte"));

        List<SensorData> sensorDataList = sensorDataRepo.findBySensor(sensor);

        if (sensorDataList.isEmpty()) {
            model.addAttribute("Error Message", "No Sensor data available for sensor-ID " + id);
            return "noSensorData";
        }

        SensorData latestSensorData = sensorDataList.stream()
                .max(Comparator.comparing(SensorData::getCreated))
                .orElseThrow(() -> new IllegalArgumentException("No Sensor data available for sensor ID " + id));

        model.addAttribute("sensorId", sensor.getSensor_id());
        model.addAttribute("sensorName", sensor.getName());
        model.addAttribute("sensorType", sensor.getType());
        model.addAttribute("latestSensorData", latestSensorData.getValue());
        model.addAttribute("sensorTitle", " Latest Sensor Data");

        return "lastSensorData";
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

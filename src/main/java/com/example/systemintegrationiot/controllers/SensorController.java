package com.example.systemintegrationiot.controllers;

import com.example.systemintegrationiot.models.Sensor;
import com.example.systemintegrationiot.repos.SensorRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping(path="/sensores")
public class SensorController {

    private final SensorRepo sensorRepo;

    public SensorController(SensorRepo sensorRepo) {
        this.sensorRepo = sensorRepo;
    }

    @RequestMapping("/addSensorByForm")
    public String createByForm(Model model) {
        return "createSensor";
    }

    @PostMapping("/add")
    public String addSensor(@RequestParam String name,
                            @RequestParam String type,
                            @RequestParam String unit,
                            Model model){
        Sensor savedSensor = sensorRepo.save(new Sensor(name, type, unit));

        model.addAttribute("sensorId", savedSensor.getSensor_id());
        model.addAttribute("sensorName", savedSensor.getName());
        model.addAttribute("sensorType", savedSensor.getType());
        model.addAttribute("sensorUnit", savedSensor.getUnit());
        model.addAttribute("sensorTitle", "New Sensor Created");

        return "showNewSensor";
    }

    @GetMapping("/details/{id}")
    public String getSensorDetails(@PathVariable Long id, Model model) {
        Sensor sensor = sensorRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sensor with ID " + id + " does not exist"));

        model.addAttribute("sensorId", sensor.getSensor_id());
        model.addAttribute("sensorName", sensor.getName());
        model.addAttribute("sensorType", sensor.getType());
        model.addAttribute("sensorUnit", sensor.getUnit());

        return "sensorDetails";
    }

    // New method to fetch all sensors and display in the sensorHistory.html
    @GetMapping("/sensorHistory")
    public String getAllSensors(Model model) {
        // Fetch all sensors from the database
        List<Sensor> sensors = sensorRepo.findAll();

        // Add the list of sensors to the model
        model.addAttribute("sensors", sensors);

        // Return the Thymeleaf template sensorHistory.html
        return "sensorHistory";
    }

    @GetMapping("/")
    public String showIndexPage(Model model) {
        // Fetch the most recent sensor or a specific sensor, for example the first one
        List<Sensor> sensors = sensorRepo.findAll();

        // Assuming we want to show the first sensor in the list
        if (!sensors.isEmpty()) {
            Sensor sensor = sensors.get(0);  // Get the first sensor in the list
            model.addAttribute("sensorId", sensor.getSensor_id());
            model.addAttribute("sensorName", sensor.getName());
            model.addAttribute("sensorType", sensor.getType());
            model.addAttribute("sensorUnit", sensor.getUnit());
        } else {
            model.addAttribute("message", "No sensors available.");
        }

        return "index";  // Return the Thymeleaf template for index.html
    }

}

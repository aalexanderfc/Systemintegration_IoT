package com.example.systemintegrationiot.controllers;

import com.example.systemintegrationiot.models.Sensor;
import com.example.systemintegrationiot.models.SensorData;
import com.example.systemintegrationiot.repos.SensorDataRepo;
import com.example.systemintegrationiot.repos.SensorRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;

@Controller
public class HomeController {

    private final SensorRepo sensorRepo;
    private final SensorDataRepo sensorDataRepo;

    public HomeController(SensorRepo sensorRepo, SensorDataRepo sensorDataRepo) {
        this.sensorRepo = sensorRepo;
        this.sensorDataRepo = sensorDataRepo;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Assuming sensor ID 2 is connected to the ESP32 (adjust as needed)
        Long esp32SensorId = 2L;

        // Fetch the connected sensor
        Sensor connectedSensor = sensorRepo.findById(esp32SensorId)
                .orElse(null);

        model.addAttribute("connectedSensor", connectedSensor);

        if (connectedSensor != null) {
            // Fetch the latest data for the connected sensor
            SensorData latestData = sensorDataRepo.findTopBySensorOrderByCreatedDesc(connectedSensor);
            model.addAttribute("latestData", latestData);
        }

        return "index";  // Render index.html
    }
}


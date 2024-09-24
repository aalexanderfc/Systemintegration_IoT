package com.example.systemintegrationiot.Controller;

import com.example.systemintegrationiot.Data.SensorData;
import com.example.systemintegrationiot.Service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller  // Change to @Controller to serve the Thymeleaf view
public class SensorController {

    private final SensorDataService service;

    @Autowired
    public SensorController(SensorDataService sensorService) {
        this.service = sensorService;
    }

    // Endpoint for saving sensor data (POST request)
    @PostMapping("/data")
    public SensorData saveSensorData(@RequestParam double temperature, @RequestParam double humidity) {
        return service.saveSensorData(temperature, humidity);
    }

    // Endpoint to display sensor data (GET request)
    @GetMapping("/data")  // Here is your /data GET request for fetching data
    public String showSensorData(Model model) {
        List<SensorData> sensorDataList = service.getAllSensorData();
        model.addAttribute("sensorDataList", sensorDataList);
        return "index";  // Renders the Thymeleaf template (index.html)
    }
}

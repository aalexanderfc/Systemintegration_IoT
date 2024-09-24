package com.example.systemintegrationiot.controllers;


import com.example.systemintegrationiot.models.Sensor;
import com.example.systemintegrationiot.repos.SensorRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/sensores")
public class SensorController {

    private final SensorRepo sensorRepo;

    SensorController(SensorRepo senRepo){
        this.sensorRepo=senRepo;

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
       Sensor savedSensor= sensorRepo.save(new Sensor(name, type, unit));

        model.addAttribute("sensorId", savedSensor.getSensor_id());
        model.addAttribute("sensorName", savedSensor.getName());
        model.addAttribute("sensorType", savedSensor.getType());
        model.addAttribute("sensorUnit", savedSensor.getUnit());
        model.addAttribute("sensorTitle", "new sensor created");

        return "showNewSensor";

    }


    @GetMapping("/details/{id}")
    public String getSensorDetails(@PathVariable Long id, Model model) {
        Sensor sensor = sensorRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sensor med ID " + id + " finns inte"));


        model.addAttribute("sensorId", sensor.getSensor_id());
        model.addAttribute("sensorName", sensor.getName());
        model.addAttribute("sensorType", sensor.getType());


        return "sensorDetails";
    }













}

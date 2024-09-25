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
@RequestMapping("SensorData")
public class SensorDataController {

    private final SensorDataRepo sensorDataRepo;
    private final SensorRepo sensorRepo;

    public SensorDataController(SensorDataRepo sensorDataRepo, SensorRepo sensorRepo) {
        this.sensorDataRepo = sensorDataRepo;
        this.sensorRepo = sensorRepo;
    }



    //http://localhost:8080/SensorData/addData?sensor_id=2&value=12.0

    @RequestMapping("/addData")
    public String addData(@RequestParam Long sensor_id,
                          @RequestParam double value){
        Sensor sensor = sensorRepo.findById(sensor_id)
                .orElseThrow(() -> new IllegalArgumentException("Sensor med ID " + sensor_id + " finns inte"));

        SensorData newSensorData = new SensorData(value, sensor);

        sensorDataRepo.save(newSensorData);


        return "TheDataIsSaved";

    }




    @GetMapping("/history/{id}")
    public String getSensorHistory(@PathVariable Long id, Model model) {

        Sensor sensor = sensorRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sensor med ID " + id + " finns inte"));

        List<SensorData> sensorDataList = sensorDataRepo.findBySensor(sensor);

        System.out.println("Sensor ID: " + sensor.getSensor_id());
        System.out.println("Sensor Namn: " + sensor.getName());
        System.out.println("Sensor Typ: " + sensor.getType());


        model.addAttribute("sensorId", sensor.getSensor_id());
        model.addAttribute("sensorName", sensor.getName());
        model.addAttribute("sensorType", sensor.getType());
        model.addAttribute("sensorDataList", sensorDataList);
        model.addAttribute("sensorTitle", " History Data");



        return "sensorhistory";
    }


    @GetMapping("/lastdata/{id}")
    public String getLastSensorData(@PathVariable Long id, Model model) {

        Sensor sensor = sensorRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sensor med ID " + id + " finns inte"));

        List<SensorData> SensorDataList = sensorDataRepo.findBySensor(sensor);

        SensorData latestSensorData = SensorDataList.stream()
                .max(Comparator.comparing(SensorData::getCreated))
                .orElseThrow(() -> new IllegalArgumentException("Ingen sensor data tillgänglig för sensor med ID " + id));

        ;



        model.addAttribute("sensorId", sensor.getSensor_id());
        model.addAttribute("sensorName", sensor.getName());
        model.addAttribute("sensorType", sensor.getType());
        model.addAttribute("latestSensorData", latestSensorData.getValue());
        model.addAttribute("Date",latestSensorData.getCreated());
        model.addAttribute("sensorTitle", " History Data");



        return "lastSensorData";
    }




















}

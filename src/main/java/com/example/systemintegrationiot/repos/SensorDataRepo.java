package com.example.systemintegrationiot.repos;

import com.example.systemintegrationiot.models.Sensor;
import com.example.systemintegrationiot.models.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorDataRepo extends JpaRepository<SensorData, Long> {

    // Existing method to fetch all data by a sensor
    List<SensorData> findBySensor(Sensor sensor);

    // New method to find the latest sensor data for a given sensor
    SensorData findTopBySensorOrderByCreatedDesc(Sensor sensor);
}


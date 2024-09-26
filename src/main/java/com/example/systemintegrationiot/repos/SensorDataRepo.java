package com.example.systemintegrationiot.repos;

import com.example.systemintegrationiot.models.Sensor;
import com.example.systemintegrationiot.models.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorDataRepo extends JpaRepository<SensorData, Long> {
    List<SensorData> findBySensor(Sensor sensor);


}



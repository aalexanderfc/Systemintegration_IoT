package com.example.systemintegrationiot.repos;

import com.example.systemintegrationiot.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorRepo extends JpaRepository<Sensor, Long> {
    Optional<Sensor> findByName(String name);
}


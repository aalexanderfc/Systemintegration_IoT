package com.example.systemintegrationiot.repos;

import com.example.systemintegrationiot.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SensorRepo extends JpaRepository<Sensor, Long> {

}

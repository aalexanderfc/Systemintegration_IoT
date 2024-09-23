package com.example.systemintegrationiot.Repos;

import com.example.systemintegrationiot.Data.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
}

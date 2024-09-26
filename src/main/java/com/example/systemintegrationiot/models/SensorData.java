package com.example.systemintegrationiot.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double value;

    @Column(updatable = false)
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    @JsonIgnore  // Ignorera cirkulära referenser
    private Sensor sensor;

    public SensorData(double value, Sensor sensor){
        this.value = value;
        this.sensor = sensor;
    }

    @PrePersist
    protected void onCreate() {
        this.created = LocalDateTime.now();
    }
}

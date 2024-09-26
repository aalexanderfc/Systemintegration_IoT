package com.example.systemintegrationiot.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sensor_id;
    private String name;
    private String type;
    private String unit;


    @Column(updatable = false)
    private LocalDateTime created;

    private LocalDateTime updated;



    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
        updated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = LocalDateTime.now();
    }

    @OneToMany(mappedBy ="sensor")
    private List<SensorData> sensorsData;

    public Sensor (  String name, String type, String unit ){
        this.name = name;
        this.type = type;
        this.unit = unit;


    }


}

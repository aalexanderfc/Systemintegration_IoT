package com.example.systemintegrationiot;

import com.example.systemintegrationiot.models.Sensor;
import com.example.systemintegrationiot.repos.SensorRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SystemintegrationIoTApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemintegrationIoTApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(SensorRepo repository) {
        return (args) -> {
            repository.save(new Sensor("DHT100", "temp", "Celsius"));
            repository.save(new Sensor("BMP180", "pressure", "hPa"));
            repository.save(new Sensor("MQ135", "gas", "ppm"));
            repository.save(new Sensor("HCSR04", "distance", "cm"));
            repository.save(new Sensor("DHT101", "temp", "Celsius"));
            repository.save(new Sensor("MHZ19", "CO2", "ppm"));
            repository.save(new Sensor("LDR102", "light", "Lux"));
            repository.save(new Sensor("DS18B20", "temp", "Celsius"));
            repository.save(new Sensor("SHT31", "humidity", "Percentage"));
            repository.save(new Sensor("BME280", "pressure", "hPa"));



        };

    }






}

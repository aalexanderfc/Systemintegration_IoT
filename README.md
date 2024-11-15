# IoT System Integration Project

This repository contains an IoT system integration project developed by Alexander Flores, Ali Al-Zobaydee, and Kazem Hassan. The goal of the project was to create an integrated IoT solution that connects hardware components, a backend service, and a web-based frontend to provide a seamless system for monitoring environmental data.

## Overview

### Hardware

We used an **ESP32 microcontroller** connected to a **DHT11 sensor** to measure temperature and humidity. The microcontroller is programmed with Arduino IDE to collect real-time environmental data.

### Backend

The backend was built using **Spring Boot**, with data storage managed by **MySQL** via **Hibernate**. The system is designed to handle data from multiple sensors, storing the data efficiently. We developed **RESTful APIs** to receive sensor data from the ESP32 and to facilitate data storage and retrieval.

### Frontend

The frontend was implemented using **Thymeleaf** within the Spring Boot framework, providing a user-friendly interface for viewing sensor data. Users can access both real-time and historical sensor data stored in the database.

## Key Features

- **Sensor Integration**: ESP32 microcontroller integrated with DHT11 sensor for temperature and humidity data collection.
- **Data Transmission**: Sensor data is sent from the ESP32 to the backend via HTTP requests.
- **Data Storage**: Collected sensor data is stored in a MySQL relational database using Spring Boot and Hibernate.
- **Web Interface**: A web-based interface built with Thymeleaf allows users to view real-time and historical sensor data.

## Requirements

- **Hardware**: ESP32 microcontroller, DHT11 sensor
- **Software**:
  - Arduino IDE (for programming the ESP32)
  - Spring Boot (backend)
  - MySQL (database)
  - Thymeleaf (frontend)

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/aalexanderfc/Systemintegration_IoT.git
   cd Systemintegration_IoT
   ```

2. **Hardware Setup**:
   - Connect the DHT11 sensor to the ESP32 microcontroller.
   - Ensure the sensor is connected properly to the correct GPIO pins for reading data.

3. **Programming the ESP32**:
   - Open the `.ino` file in Arduino IDE.
   - Compile and upload the code to the ESP32.

4. **Backend Setup**:
   - Open the Spring Boot project in your IDE (e.g., IntelliJ, VS Code).
   - Configure the database connection settings in `application.properties`.
   - Run the Spring Boot application to start the backend server.

5. **Frontend Setup**:
   - Access the web interface by navigating to the provided URL (default is usually `http://localhost:8080`).
   - View real-time and historical sensor data via the Thymeleaf web pages.

## How It Works

- **Data Collection**: The ESP32 reads temperature and humidity data from the DHT11 sensor at regular intervals.
- **Data Transmission**: The ESP32 sends the sensor data to the backend server using HTTP POST requests.
- **Data Storage**: The backend, built with Spring Boot, stores the incoming data in a MySQL database using Hibernate for ORM (Object-Relational Mapping).
- **Data Visualization**: The web-based frontend, built with Thymeleaf, allows users to view the data through a user-friendly interface.

## Example Code

Here is an example snippet of the ESP32 code:

```cpp
void setup() {
    // Setup code for initializing ESP32 and DHT11 sensor
}

void loop() {
    // Main loop code for reading sensor data and sending it to the backend
}
```

For detailed code, please refer to the specific `.ino` file and Java classes in the repository.

## Limitations

- The system currently handles only temperature and humidity data from a single type of sensor (DHT11).
- The web interface is basic and could be extended with additional features, such as chart visualizations or more detailed sensor configuration options.

## Authors

Developed by Alexander Flores, Ali Al-Zobaydee, and Kazem Hassan. Feel free to reach out for more information or collaboration opportunities.


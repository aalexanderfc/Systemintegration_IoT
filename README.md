# GruppArbeteSystemintegration

IoT Systemintegrationsprojekt

Detta projekt utvecklades av Alexander Flores, Ali Al-Zobaydee och Kazem Hassan. Målet med projektet var att bygga ett IoT-system som integrerar hårdvara, en backend-tjänst och en webbaserad frontend.

 Översikt
 
- Hårdvara: Vi använde en ESP32 mikrokontroller kopplad till en DHT11-sensor för att mäta fuktighet och temperatur, programmerad med Arduino IDE. ESP32 samlar in realtidsdata om temperatur och fuktighet från sensorn.
  
- Backend: Backend byggdes med Spring Boot, och datan sparas i en MySQL-databas via Hibernate. Systemet kan hantera data från olika sensorer och lagra den effektivt. Vi utvecklade RESTful API:er för att ta emot sensordata från ESP32 och hantera datalagring.

- Frontend: Frontend implementerades med Thymeleaf inom ramen för Spring Boot, vilket ger ett användarvänligt gränssnitt för att visa sensordata. Användare kan visa både realtidsdata och historisk data som lagras i databasen.

 Nyckelfunktioner

- Integration av ESP32 med DHT11-sensor för att samla in temperatur- och fuktighetsdata.
- Datatransmission från ESP32 till backend via HTTP-förfrågningar.
- En relationsdatabas implementerad med MySQL och hanterad med Spring Boot och Hibernate.
- Webbgränssnitt byggt med Thymeleaf för att visa realtidsdata och historisk sensordata.

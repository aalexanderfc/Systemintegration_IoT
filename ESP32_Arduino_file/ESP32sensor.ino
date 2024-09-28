#include <WiFi.h>
#include <HTTPClient.h>
#include <DHT.h>

#define DHTPIN 26         // GPIO-pin connected to DHT11
#define DHTTYPE DHT11     // DHT11 sensor type
DHT dht(DHTPIN, DHTTYPE);

// Your WiFi network credentials
const char* ssid = "Alexander_iPhone";
const char* password = "iphonese21";

// Server address (replace with your computer's IP address)
const char* serverName = "http://172.20.10.4:8080/SensorData/addData";

void setup() {
  Serial.begin(115200);

  dht.begin();

  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to Wi-Fi...");
  }
  Serial.println("Connected to Wi-Fi!");

  // Print the ESP32's IP address
  Serial.print("ESP32 IP Address: ");
  Serial.println(WiFi.localIP());
}

// Create a function to build the HTTP path with dynamic sensor data
String makePath(long sensorId, String sensorUnit, float sensorValue){
  // Corrected concatenation for the sensorUnit and sensorValue
  String path = String(serverName) + "?sensor_id=" + String(sensorId) + "&unit=" + sensorUnit + "&value=" + String(sensorValue);
  return path;
}

// Function to send HTTP requests to the server
void httpBegin(String path){
  if (WiFi.status() == WL_CONNECTED){
    HTTPClient http;

    http.begin(path);
    int httpResponseCode = http.GET();

    if (httpResponseCode > 0) {
      String response = http.getString();
      Serial.println("HTTP Response code: " + String(httpResponseCode));
      Serial.println("Server response: " + response);
    } else {
      Serial.print("Error on sending GET: ");
      Serial.println(httpResponseCode);
    }

    http.end();
  } else {
    Serial.println("Wi-Fi Disconnected");
  }
}

void loop() {
  // Read temperature and humidity from DHT11
  float temperature = dht.readTemperature();
  float humidity = dht.readHumidity();

  if (isnan(temperature) || isnan(humidity)) {
    Serial.println("Error: Failed to read from DHT sensor");
    return;
  }

  Serial.print("Temperature: ");
  Serial.println(temperature);
  Serial.print("Humidity: ");
  Serial.println(humidity);

  // Create dynamic paths for both temperature and humidity
  String tempPath = makePath(2, "Celsius", temperature);  // Sensor ID 2, Unit "Celsius"
  String humPath = makePath(3, "Percentage", humidity);      // Sensor ID 3, Unit "Percent"

  // Send the data to the server
  httpBegin(tempPath);
  httpBegin(humPath);

  delay(10000);  // Send data every 10 seconds
}

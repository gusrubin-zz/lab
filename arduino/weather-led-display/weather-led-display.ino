/* 
 * Reads weather temperature and turn on LED when it's exceeds preset limit
 */

// System Libraries
#include <FS.h>
#include <LittleFS.h>

// Sensor Libraries
#include <BME280I2C.h>
#include <Wire.h>

// WiFi Libraries
#include <ESP8266WiFi.h> //https://github.com/esp8266/Arduino/tree/master/libraries/ESP8266WiFi
#include <DNSServer.h> //https://github.com/esp8266/Arduino/tree/master/libraries/DNSServer
#include <ESP8266WebServer.h> //https://github.com/esp8266/Arduino/tree/master/libraries/ESP8266WebServer
#include <WiFiManager.h> //https://github.com/tzapu/WiFiManager

// MQTT Libraries
#include <PubSubClient.h> //https://github.com/knolleary/pubsubclient
#include <ArduinoJson.h> //https://github.com/bblanchon/ArduinoJson

// General properties
char deviceSerialNumber[30] = "weather";
const char *configFileName = "/config.json";
File finalFile;
long continousNotifyPeriod = 1000;

// MQTT Properties
char mqttServerEndpoint[17] = "192.168.0.106";
char mqttServerPort[6] = "1883";
char mqttServerUser[30] = "adpcontroller";
char mqttServerPassword[30] = "adpadp";
long lastReconnectAttempt = 0;
// Topics
char mqttBaseTopicName[12] = "adp/device/";
char mqttEventsSubTopicName[8] = "/events";
char mqttEventsTopic[45] = "";
char mqttActionRequestSubTopicName[16] = "/inbound";
char mqttActionRequestTopic[53] = "";
char mqttActionReplySubTopicName[14] = "/outbound";
char mqttActionReplyTopic[51] = "";

WiFiClient espClient;
PubSubClient mqttClient(espClient);


// BME280I2C configuration;
// Default : forced mode, standby time = 1000 ms, Oversampling = pressure ×1,
// temperature ×1, humidity ×1, filter off

BME280I2C bme;

float tem(NAN), hum(NAN), pre(NAN), alt(NAN);

BME280::TempUnit tempUnit(BME280::TempUnit_Celsius);
BME280::PresUnit presUnit(BME280::PresUnit_hPa);

void setup() {
  Serial.begin(115200);
  Serial.println("Booting...");

  Serial.println("Setup GPIOs");
  pinMode(LED_BUILTIN, OUTPUT); // Initialize the BUILTIN_LED pin as an output
  digitalWrite(LED_BUILTIN, HIGH);
  
  while (!Serial) {} // Wait for serial comunication with the BMx280 sensor

  Wire.begin(0, 2);
  while (!bme.begin())
  {
    Serial.println("Could not find BME280 sensor!");
    delay(1000);
  }

  switch (bme.chipModel())
  {
    case BME280::ChipModel_BME280:
      Serial.println("BME280 detected");
      digitalWrite(LED_BUILTIN, LOW);
      break;
    case BME280::ChipModel_BMP280:
      Serial.println("Found BMP280 sensor! No Humidity available.");
      digitalWrite(LED_BUILTIN, LOW);
      break;
    default:
      Serial.println("Found UNKNOWN sensor! Error!");
  }

}

void loop() {
  printBME280Data(&Serial);
  delay(500);

}

void printBME280Data
(
   Stream* client
)
{
   float temp(NAN), hum(NAN), pres(NAN);

   BME280::TempUnit tempUnit(BME280::TempUnit_Celsius);
   BME280::PresUnit presUnit(BME280::PresUnit_Pa);

   bme.read(pres, temp, hum, tempUnit, presUnit);

   client->print("Temp: ");
   client->print(temp);
   client->print("°"+ String(tempUnit == BME280::TempUnit_Celsius ? 'C' :'F'));
   client->print("\t\tHumidity: ");
   client->print(hum);
   client->print("% RH");
   client->print("\t\tPressure: ");
   client->print(pres);
   client->println("Pa");

//   if (temp > 23) {
//      digitalWrite(LED_BUILTIN, HIGH);
//   } else {
//      digitalWrite(LED_BUILTIN, LOW);
//   }

   delay(1000);
}

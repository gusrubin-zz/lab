void setup_wifi() {
  loadConfig();

  WiFiManagerParameter customMqttConfigText("<br>Device Configurations<br>");
  WiFiManagerParameter customMqttServerEndpoint("MQTT Server Endpoint", "endpoint", mqttServerEndpoint, 17);
  WiFiManagerParameter customMqttServerPort("MQTT Server Port", "port", mqttServerPort, 6);
  WiFiManagerParameter customMqttServerUser("MQTT Server User", "user", mqttServerUser, 30);
  WiFiManagerParameter customMqttServerPassword("MQTT Server Password", "password", mqttServerPassword, 30);

  WiFiManager wifiManager;

  //set config save notify callback
  wifiManager.setSaveConfigCallback(saveConfigCallback);

  //Custom project parameters  
  wifiManager.addParameter(&customMqttConfigText);
  wifiManager.addParameter(&customMqttServerEndpoint);
  wifiManager.addParameter(&customMqttServerPort);
  wifiManager.addParameter(&customMqttServerUser);
  wifiManager.addParameter(&customMqttServerPassword);

  //tries to connect to last known settings
  //if it does not connect it starts an access point with the specified name
  //here "Digital Lock [Serial Number]" with password "operador"
  //and goes into a blocking loop awaiting configuration
  if (!wifiManager.autoConnect(deviceSerialNumber, "operador")) {
    Serial.println("failed to connect, we should reset as see if it connects");
    delay(3000);
    ESP.reset();
    delay(5000);
  }

  //if you get here you have connected to the WiFi
  Serial.print("\nWiFi connected - IP address: "); Serial.println(WiFi.localIP());

  // Get MQTT parameters values
  strcpy(mqttServerEndpoint, customMqttServerEndpoint.getValue());
  Serial.print("MQTT Server Endpoint: "); Serial.println(mqttServerEndpoint);  
  strcpy(mqttServerPort, customMqttServerPort.getValue());
  Serial.print("MQTT Server Port: "); Serial.println(mqttServerPort);  
  strcpy(mqttServerUser, customMqttServerUser.getValue());
  Serial.print("MQTT Server User: "); Serial.println(mqttServerUser);  
  strcpy(mqttServerPassword, customMqttServerPassword.getValue());

  strcpy(mqttEventsTopic, mqttBaseTopicName);
  strcat(mqttEventsTopic, deviceSerialNumber);
  strcat(mqttEventsTopic, mqttEventsSubTopicName);
  Serial.print("MQTT Events topic: "); Serial.println(mqttEventsTopic);

  strcpy(mqttActionRequestTopic, mqttBaseTopicName);
  strcat(mqttActionRequestTopic, deviceSerialNumber);
  strcat(mqttActionRequestTopic, mqttActionRequestSubTopicName);
  Serial.print("MQTT Action Request topic: "); Serial.println(mqttActionRequestTopic);

  strcpy(mqttActionReplyTopic, mqttBaseTopicName);
  strcat(mqttActionReplyTopic, deviceSerialNumber);
  strcat(mqttActionReplyTopic, mqttActionReplySubTopicName);
  Serial.print("MQTT Action Reply topic: "); Serial.println(mqttActionReplyTopic);
 
  if (shouldSaveConfig) {
    Serial.println("saving config");
    saveInitialConfig(configFileName);
    updateConfigRequired = true;
  }
}

void reset_wifi() {
  Serial.println("Reset button was pressed. Resetting WiFi config...");
  if(SPIFFS.remove(configFileName)){
    Serial.println("Old file succesfully deleted");
  }else{
    Serial.println("Couldn't delete file");
  }  
  WiFi.disconnect(true);
  delay(5000);
  ESP.reset();
  delay(5000);
}

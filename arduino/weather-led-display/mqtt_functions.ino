void setup_mqtt() {
  Serial.print("\nRunning Setup MQTT...");
  mqttClient.setServer(mqttServerEndpoint, atoi(mqttServerPort));
  mqttClient.setCallback(mqtt_callback);
  Serial.println("done");
}

void mqtt_topics_subscription() {
  mqttClient.subscribe(mqttActionRequestTopic);
}

bool mqtt_connect() {
  digitalWrite(LED_BUILTIN, LOW);
  Serial.print("Attempting MQTT connection...");
  if (mqttClient.connect(deviceSerialNumber, mqttServerUser, mqttServerPassword)) {
    mqtt_topics_subscription();
    digitalWrite(LED_BUILTIN, HIGH);
    Serial.println("connected.");
  }
  return mqttClient.connected();
}

void mqtt_callback(char* topic, byte* payload, unsigned int length) {
  char str_payload[length];
  for (int i = 0; i < length; i++) {
    str_payload[i] = (char)payload[i];
  }

  // Action Request Handler
  if (strcmp(topic, mqttActionRequestTopic) == 0) {
    Serial.print("Action request received from server...");
    const size_t capacity = JSON_OBJECT_SIZE(3) + length;
    DynamicJsonDocument doc(capacity);
    const char* json = str_payload;
    Serial.println(json);
    DeserializationError error =  deserializeJson(doc, json);
    if (error) {
      Serial.print(F("deserializeJson() failed: "));
      Serial.println(error.c_str());
      return;
    }

    String requestId = doc["requestId"];
    const char* action = doc["subject"];
    JsonObject content = doc["content"];

    // System Reset action handler
    if (strcmp(action, "SR") == 0) {
      Serial.println("System Reset action received from server");
      systemReset();
    }

    // Get Values action handler
    if (strcmp(action, "GV") == 0) {
      Serial.println("Get Values (GV) action received from server");
      publishSensorData(requestId);
    }

    // Get Status action handler
    if (strcmp(action, "GS") == 0) {
      Serial.println("Get Status (GS) action received from server");
      publishSensorStatus(requestId);
    }

    // Error Response Handler
    if (strcmp(action, "error") == 0) {
      const char* errorMessage = content["errorMessage"];
      if (!content["errorMessage"].isNull()) {
        const char* errorMessage = content["errorMessage"];
        int errorCode = content["errorCode"];
        Serial.print("Controller response: \""); Serial.print(errorMessage);
        Serial.print("\" (Error Code: "); Serial.print(errorCode); Serial.println(")");
      }
      if (!content["message"].isNull()) {
        const char* message = content["message"];
        Serial.print("Received message from controller: \""); Serial.print(message);
        Serial.println("\"");
      }
    }
  }
}

String sendEvent(const char* event, JsonObject content) {
  String eventId = String(random(10000, 90000), DEC);
  Serial.print("Sending event with id "); Serial.println(eventId);
  StaticJsonDocument<256> doc;
  doc["requestId"] = eventId;
  doc["subject"] = event;
  doc["content"] = content;
  char buffer[256];
  serializeJson(doc, buffer);
  mqttClient.publish(mqttActionReplyTopic, buffer, false);
  return eventId;
}

String sendEvent(String reqId, const char* event, JsonObject content) {
  Serial.print("Sending event with id "); Serial.println(reqId);
  StaticJsonDocument<256> doc;
  doc["requestId"] = reqId;
  doc["subject"] = event;
  doc["content"] = content;
  char buffer[256];
  serializeJson(doc, buffer);
  mqttClient.publish(mqttActionReplyTopic, buffer, false);
  return reqId;
}

String sendActionReply(String requestId, JsonObject content) {
  Serial.print("Sending sendActionReply with id "); Serial.println(requestId);
  StaticJsonDocument<256> doc;
  doc["requestId"] = requestId;
  doc["subject"] = "action-reply";
  doc["content"] = content;
  char buffer[256];
  serializeJson(doc, buffer);
  mqttClient.publish(mqttActionReplyTopic, buffer, false);
  return requestId;
}

String sendEvent(const char* context, const char* event, const char* contentValue) {
  String eventId = String(random(10000, 90000), DEC);
  Serial.print("Sending event with id "); Serial.println(eventId);
  StaticJsonDocument<256> doc;
  doc["requestId"] = eventId;
  //  doc["context"] = context;
  doc["subject"] = event;

  const size_t CAPACITY = JSON_OBJECT_SIZE(1);
  StaticJsonDocument<CAPACITY> docContent;
  JsonObject object = docContent.to<JsonObject>();
  object["event"] = contentValue;

  doc["content"] = object;
  char buffer[256];
  serializeJson(doc, buffer);
  mqttClient.publish(mqttActionReplyTopic, buffer, false);
  return eventId;
}

void sendActionReply(const char* requestId, const char* context,
                     const char* responseStatus, JsonObject content) {
  Serial.print("Sending action reply with requestId "); Serial.print(requestId);
  StaticJsonDocument<256> doc;
  doc["requestId"] = requestId;
  //  doc["context"] = context;
  doc["content"] = responseStatus;
  doc["content"] = content;
  char buffer[256];
  serializeJson(doc, buffer);
  mqttClient.publish(mqttActionReplyTopic, buffer, false);
}

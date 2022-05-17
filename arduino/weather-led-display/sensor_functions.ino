void publishSensorData(String reqId) {
  bme.read(pre, tem, hum, tempUnit, presUnit);
  alt = 44330 * (1 - pow(pre / 1013.25, 0.190294957));

  const int capacity = JSON_OBJECT_SIZE(100);
  StaticJsonDocument<capacity> jsonSensorData;
  JsonObject jsonSensorDataObj = jsonSensorData.to<JsonObject>();

  jsonSensorData["temperature"] = roundFloat(tem);
  jsonSensorData["humidity"] = roundFloat(hum);
  jsonSensorData["pression"] = roundFloat(pre);
  jsonSensorData["altitude"] = roundFloat(alt);

  //  char jsonOutput[100];
  //  serializeJson(jsonSensorData, jsonOutput);

  //  Serial.println(jsonOutput);

  sendActionReply(reqId, jsonSensorDataObj);

  // mqttClient.publish(topicWeather, jsonOutput);
}

float roundFloat(float var) {
  // 37.66666 * 100 =3766.66
  // 3766.66 + .5 =3767.16    for rounding off value
  // then type cast to int so value is 3767
  // then divided by 100 so the value converted into 37.67
  float value = (int)(var * 100 + .5);
  return (float)value / 100;
}

void publishSensorStatus(String reqId) {
  String sensorModel = "";

  switch (bme.chipModel())
  {
    case BME280::ChipModel_BME280:
      //      sendEvent("sensors", "config-notify", "BME280 detected");
      //      mqttClient.publish(mqttControllerEventNotifyTopic, "BME280 detected");
      sensorModel = "BME280";
      break;
    case BME280::ChipModel_BMP280:
      //      sendEvent("sensors", "config-notify", "BMP280 detected");
      //      mqttClient.publish(mqttControllerEventNotifyTopic, "BMP280 detected");
      sensorModel = "BMP280";
      break;
    default:
      //      sendEvent("sensors", "config-notify", "Unknow sensor detected");
      //      mqttClient.publish(mqttControllerEventNotifyTopic, "Unknow sensor detected" );
      sensorModel = "Unknow";
  }

  const int capacity = JSON_OBJECT_SIZE(4) + 80;
  StaticJsonDocument<capacity> jsonSensorStatus;
  JsonObject jsonSensorStatusObj = jsonSensorStatus.to<JsonObject>();

  jsonSensorStatus["sensorModel"] = sensorModel;

  sendActionReply(reqId, jsonSensorStatusObj);

}

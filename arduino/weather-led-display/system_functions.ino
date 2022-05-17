// System reset
void systemReset() {
  Serial.println("systemReset action");
  digitalWrite(BUILTIN_LED, LOW); // Turn the LED on (Note that LOW is the voltage level
  // but actually the LED is on; this is because
  // it is active low on the ESP-01)
  delay(2000);
  digitalWrite(BUILTIN_LED, HIGH);
  ESP.restart();
}

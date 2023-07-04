#include <SoftwareSerial.h>
SoftwareSerial BT1(0,1); // 1=RX, 2=Tx

const int pinHall = A0;
#define PINLED 4

int F_LecHall(int pinSensor){
  return digitalRead(pinSensor);  
}

void F_LED(int Estado, int pinLed) {
  digitalWrite(pinLed, Estado);
}

void setup() {
  pinMode(pinHall, INPUT);
  //Serial.begin(9600);
  BT1.begin(9600);
  pinMode(PINLED, OUTPUT);
}

void loop() {
  int ValSensor = F_LecHall(pinHall);
  F_LED(ValSensor, PINLED);
  //media de 10 medidas para filtrar ruido
  
  long measure = 0;
  for(int i = 0; i < 10; i++){
      int value = 
      measure += analogRead(pinHall);
  }
  measure /= 10;
  
  //calculo del voltaje en mV
  float outputV = measure * 5000.0 / 1023;
  //Serial.print("Output Voltaje = ");
  //Serial.print(outputV);
  //Serial.print(" mV   ");
  
  //interpolacion a densidad de flujo magnético
  float magneticFlux =  outputV * 53.33 - 133.3;
  //Serial.print("Magnetic Flux Density = ");
  //Serial.print(" ");
  //Serial.println(magneticFlux);
  BT1.print("1");
  BT1.println(magneticFlux);
  //Serial.print(" mT");
  
  delay(500);
}

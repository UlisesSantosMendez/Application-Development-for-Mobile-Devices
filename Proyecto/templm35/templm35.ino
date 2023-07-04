#include <SoftwareSerial.h>
SoftwareSerial BT1(0,1); // 1=RX, 2=Tx

const int entrada=A5;
float valor=0;
int temp=0;

void setup() {
BT1.begin(9600);
analogReference(entrada);
}

void loop() {
valor=analogRead(entrada);// 0 hasta 1023  2.5v= 560 0v=0  5v=1023
temp=(1.1*valor)/(1024*0.01); // 1°C/10mv  5v/1024  -55 hasta 150 =1.5v
BT1.print("3");
BT1.println(temp);
delay(1000);
}

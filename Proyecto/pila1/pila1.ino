#include <SoftwareSerial.h>
SoftwareSerial BT1(0,1); // 1=RX, 2=Tx

// Pines para los LEDs
#define LEDVERDE 8
#define LEDAMARILLO 9
#define LEDROJO 10


// Variables
int analogValor = 0;
float voltaje = 0;
int ledDelay = 800;
const int analogPin = A0;
int value;      //variable que almacena la lectura analógica raw

// Umbrales
float maximo = 1.6;
float medio = 1.4;
float minimo = 0.3;

void setup() {
  // Iniciamos el monitor serie
  //Serial.begin(9600);
  BT1.begin(9600);

  // Los pines de LED en modo salida
  pinMode(LEDVERDE, OUTPUT);
  pinMode(LEDAMARILLO, OUTPUT);
  pinMode(LEDROJO, OUTPUT);

}

void loop() {
  // Leemos valor de la entrada analógica
  analogValor = analogRead(analogPin);

  // Obtenemos el voltaje
  voltaje = 0.0048 * analogValor;
  Serial.print("Voltaje: ");
  //Serial.println(voltaje);
  BT1.print("2");
  BT1.println(voltaje);

  // Dependiendo del voltaje mostramos un LED u otro
  if (voltaje >= maximo)
  {
    digitalWrite(LEDVERDE, HIGH);
    delay(ledDelay);
    digitalWrite(LEDVERDE, LOW);
  }
  else if (voltaje < maximo && voltaje > medio)
  {
    digitalWrite(LEDAMARILLO, HIGH);
    delay(ledDelay);
    digitalWrite(LEDAMARILLO, LOW);
  }
  else if (voltaje < medio && voltaje > minimo)
  {
    digitalWrite(LEDROJO, HIGH);
    delay(ledDelay);
    digitalWrite(LEDROJO, LOW);
  }

  // Apagamos todos los LEDs
  digitalWrite(LEDVERDE, LOW);
  digitalWrite(LEDAMARILLO, LOW);
  digitalWrite(LEDROJO, LOW);
}

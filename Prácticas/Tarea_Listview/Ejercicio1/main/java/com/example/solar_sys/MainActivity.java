package com.example.solar_sys;

import android.app.*;
import android.os.*;
import java.util.ArrayList;
import android.view.View;
import android.view.View.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.*;
import android.content.*;

public class MainActivity extends Activity {
    private ListView lv;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.listado);
        //jbn1=(Button)findViewById(R.id.xbtn1);
        ArrayList<ListaEntrada> al = new ArrayList<ListaEntrada>();
        al.add(new ListaEntrada(R.drawable.mercurio, "MERCURIO", "Mercurio fue nombrado de " +
                "esta manera en honor a Hermes, el mensajero de los dioses griegos del Olimpo. " +
                "Más tarde los romanos lo bautizaron, tal y como ocurre con el resto de los " +
                "planetas, con el equivalente en su mitología para los dioses griegos, en este caso" +
                " Mercurio. Y con ese nombre ha llegado hasta nuestros días.\n" +
                "\n" +
                "Con un tamaño tan solo un poco mayor al de nuestra luna, es el planeta más pequeño" +
                "del sistema solar y el más cercano al Sol. Se trata del menor de los planetas " +
                "rocosos del sistema solar y de manera similar a la Luna, presenta una superficie " +
                "plagada de cráteres, en parte, debido a la finísima y casi ausente atmósfera " +
                "(exosfera) que le rodea. Con una velocidad de 170.5030 kilómetros por hora, " +
                "también se trata del planeta que viaja más rápido a través del espacio -de aquí su" +
                " nombre- ya que la velocidad de un planeta incrementa con su cercanía a la " +
                "estrella que orbita."));
        al.add(new ListaEntrada(R.drawable.venus, "VENUS", "Solo tras el Sol y la Luna, " +
                "Venus es el objeto más brillante que se puede ver en el firmamento desde la Tierra." +
                " Esto propició, ya desde la antigüedad, que por su brillo y belleza los romanos" +
                " asociaran el planeta con la diosa de la belleza y el amor, Venus, de la cual" +
                " recibe su nombre.\n" +
                "\n" +
                "Parte de la intensidad del brillo de Venus es debido a la cercanía con la Tierra, " +
                "ya que se trata del planeta que más próximo se encuentra de nosotros. También es" +
                " el segundo planeta más cercano al Sol. Se trata de otro de los 4 planetas " +
                "rocosos del sistema solar y debido a la gran similitud tanto en tamaño como en" +
                "densidad con nuestro planeta, en ocasiones es denominado como el planeta gemelo" +
                " de la Tierra.Al igual que la Tierra, Venus posee un núcleo de hierro envuelto " +
                "por un manto de roca caliente y una corteza rocosa. En cuanto a su superficie " +
                "presenta un color oxidado y está salpicada de montañas y miles de grandes " +
                "volcanes, algunos de los cuales los científicos creen que aún siguen activos."));
        al.add(new ListaEntrada(R.drawable.tierra, "TIERRA", "En la Tierra se suceden una" +
                " gran cantidad de características que hacen posible la vida. Por ejemplo, la" +
                " atmósfera de la Tierra, la cual también nos protege de la radiación procedente" +
                " del Sol y del espacio, está compuesta por un 78 % de nitrógeno, un 21 % de" +
                " oxígeno y un 1 % de otros ingredientes: el equilibrio perfecto para respirar" +
                " y vivir. Los vastos océanos de la Tierra proporcionaron un lugar conveniente" +
                " para que comenzara la vida hace unos 3.800 millones de años. La Tierra es del" +
                " mismo modo el único planeta del sistema solar con una sola luna, la cual es, en" +
                " muchos sentidos, responsable de hacer de la Tierra un hogar habitable al regir" +
                " las mareas o estabilizar la oscilación de nuestro planeta, lo que ha hecho que" +
                " el clima sea menos variable durante miles de años."));
        al.add(new ListaEntrada(R.drawable.marte, "MARTE", "Marte, también conocido como el " +
                "planeta Rojo, es el más alejado del Sol de los cuatro planetas rocosos del sistema" +
                " solar, y el segundo más cercano a la Tierra, aunque cuenta aproximadamente con" +
                " solo la mitad de su tamaño. Se trata de un planeta sencillo de identificar en" +
                " el firmamento nocturno debido al brillo rojizo que hace honor a su nombre.\n" +
                "\n" +
                "Pese a lo que pudiera parecer por su rojo color, Marte no es planeta cálido." +
                " Muy al contrario, en la actualidad se trata de un planeta seco, rocoso," +
                " inhóspito y frío, algo que no exime al planeta de haber albergado unas" +
                " condiciones pasadas en las que la vida hubiera sido posible. Así, se cree que" +
                " en el pasado Marte fue un mundo más cálido cubierto de agua, motivo por el que" +
                " los científicos no cesan en su empeño de encontrar signos de vida pretérita." +
                " De hecho, en Marte podemos encontrar agua a día de hoy, no obstante la atmósfera" +
                " marciana es demasiado delgada para que exista agua líquida por mucho tiempo en" +
                " la superficie, por lo que la mayor parte del agua marciana se encuentra en" +
                " forma de hielo debajo de la superficie, en las regiones polares."));
        al.add(new ListaEntrada(R.drawable.jupiter, "JUPITER", "Como no podía ser de otra" +
                " manera, Júpiter, con más del doble de la masa que el resto de planetas juntos," +
                " es el planeta más grande del sistema solar por lo que recibe su nombre del dios" +
                " entre los dioses del Olimpo, Zeus; Júpiter en la mitología romana.Y es que todo" +
                " en Júpiter adquiere magnitudes extraordinarias. Para hacernos una idea, en" +
                " torno a la línea del ecuador de Júpiter cabrían hasta 11 planetas Tierra " +
                "puestos en fila. La velocidad a la que gira este coloso del sistema solar" +
                " también es endiablada, pues no solo hay que tener en cuenta que el día en" +
                " Júpiter dura apenas unas escasas 10 horas mientras que en la Tierra un día" +
                " dura 24 horas, sino también que su radio es 10 veces mayor que el de nuestro" +
                " planeta. Esta también es la causa de los fuertes vientos que se producen en la" +
                " atmósfera de Júpiter, los cuales en el ecuador del planeta pueden alcanzar hasta" +
                " los 540 kilómetros por hora, dando lugar a las tormentas más impresionantes del" +
                " sistema solar, como es el caso de la Gran Mancha Roja, una tormenta que según" +
                " los científicos lleva activa más de 300 años y en la cual cabrían 2 planetas" +
                " como la Tierra."));
        al.add(new ListaEntrada(R.drawable.saturno, "SATURNO", "Saturno es otro de los" +
                " planetas más grandes del sistema solar, por lo que al igual que Júpiter, recibe" +
                " su nombre de uno de los dioses más importantes de la mitología para griegos y" +
                " romanos, quienes respectivamente se referían a él como Cronos y Saturno, y en" +
                " sendas mitologías fueron padres de Zeus y Júpiter. Cronos y Saturno eran, de" +
                " hecho, los dioses del tiempo y la agricultura. Saturno es el planeta que a lo" +
                " largo del año se puede observar durante más tiempo en el firmamento, por lo que" +
                " la elección de ambas culturas para nombrar a este gigante gaseoso no es una" +
                " casualidad.\n" +
                "\n" +
                "Saturno es el sexto planeta en distancia al sol del sistema solar. También se" +
                " trata del segundo planeta más grande (9 planetas Tierra en fila cabrían a lo" +
                " largo de la línea de su ecuador) y el otro de los gigantes gaseosos de nuestro" +
                " vecindario cósmico, aunque al igual que sucede con Júpiter, los científicos" +
                " creen que podría albergar una núcleo sólido en su interior."));
        al.add(new ListaEntrada(R.drawable.urano, "URANO", "Urano es el séptimo planeta" +
                " en distancia al sol, el tercero más grande, el cuarto más masivo y el segundo" +
                " menos denso del sistema solar. Se trata del primero de los gigantes helados de" +
                " nuestro sistema planetario y está compuesto por una especie de \"hielo\" fluido" +
                " de agua, amoníaco y metano. De hecho, el manto helado de Urano no está compuesto" +
                " de hielo en el sentido convencional, sino que es un fluido caliente y denso" +
                " formado por agua, amoníaco y otros volátiles.\n" +
                "\n" +
                "Urano es un planeta muy diferente a todos los que hemos visto con anterioridad," +
                " aunque comparte una característica especialmente excepcional con el planeta" +
                " Venus, y es que rota de oeste a este. Con la salvedad, eso si, algo que lo hace" +
                " mucho más especial, de que gira en un ángulo de casi 90 grados desde el plano" +
                " de su órbita, lo cual hace que Urano parezca girar de costado, orbitando " +
                "alrededor del Sol como una bola rodante.\n" +
                "\n" +
                "El gigante de hielo está rodeado por 13 anillos tenues, los cuales forman" +
                " el segundo sistema anular que fue descubierto en el sistema solar tras el de" +
                " Saturno. También cuenta con 27 satélites naturales conocidos, los cuales" +
                " reciben su nombre en honor de los personajes de las obras de William" +
                " Shakespeare y Alexander Pope."));
        al.add(new ListaEntrada(R.drawable.neptuno, "NEPTUNO", "El nombre del planeta" +
                " Neptuno, dios de los océanos para los romanos, en un proceso parecido al que" +
                " tuvo lugar con Urano, fue adoptado por consenso dentro de la comunidad de" +
                " astrónomos. Neptuno fue el primer planeta descubierto mediante cálculos" +
                " matemáticos, ya que los antiguos, al igual que ocurrió con Urano, pensaban" +
                " que se trataba de una estrella.\n" +
                "\n" +
                "Oscuro, frío y regido por vientos supersónicos, el gigante de hielo Neptuno" +
                " es el octavo y más distante planeta de nuestro sistema solar. Está tan lejos" +
                " que es el único de los planetas que no es visible desde la Tierra a simple" +
                " vista.\n" +
                "\n" +
                "Neptuno guarda muchas similitudes con su vecino Urano. Así, Neptuno se" +
                " clasifica como un gigante de hielo, y la mayor parte de su masa corresponde con" +
                " una especie de \"hielo\" fluido de agua, amoníaco y metano que se asienta sobre" +
                " un pequeño núcleo rocoso. Al igual que sucede en Urano, la atmósfera de Neptuno" +
                " se compone principalmente de hidrógeno molecular, helio atómico y metano, aunque" +
                " algunas pequeñas diferencias en esta hace que los dos planetas se vean de" +
                " colores azules diferentes.\n" +
                "\n" +
                "Neptuno tiene 14 lunas conocidas que llevan el nombre de dioses del mar y ninfas" +
                " en la mitología griega y también un sistema de anillos que cuenta con cinco" +
                " anillos principales y cuatro arcos de anillos más, que son cúmulos de polvo y" +
                " escombros probablemente formados por la gravedad de una luna cercana.\n" +
                "\n"));
        al.add(new ListaEntrada(R.drawable.pluton, "PLUTON", "Este planeta enano da una" +
                " vuelta alrededor del Sol cada 247,7 años y lo hace recorriendo una distancia" +
                " media de 5.900 millones de kilómetros. La masa de Plutón es la equivalente a" +
                " 0,0021 veces la de la Tierra o la quinta parte de la masa de nuestra luna." +
                " Esto lo hace ser muy pequeño como para ser considerado un planeta.\n" +
                "\n" +
                "Sí es cierto que durante 75 años ha sido un planeta por la Unión Astronómica" +
                " Internacional. En 1930 fue nombrado con el nombre del dios romano del" +
                " inframundo.\n" +
                "Gracias al descubrimiento de este planeta, se han podido realizar grandes" +
                " descubrimientos posteriores como el Cinturón de Kuiper. Es considerado el" +
                " planeta enano más grande y por detrás suyo Eris. Está formado principalmente" +
                " por algunos tipos de hielo. Nos encontramos con hielo hecho de metano congelado," +
                " otro de agua y otro de roca.\n" +
                "\n" +
                "La información sobre Plutón ha sido muy reducida ya que la tecnología desde" +
                " 1930 no era muy avanzada como para aportar grandes descubrimiento de un" +
                " cuerpo tan lejano de la Tierra. Hasta entonces era el único planeta que no" +
                " había sido visitado por una nave espacial.\n" +
                "\n" +
                "En julio de 2015, gracias a una nueva misión espacial que partió de nuestro" +
                " planeta en 2006, pudo llegar al planeta enano, obteniendo gran cantidad de" +
                " información. La información tardó un año en llegar a nuestro planeta."));
        lv = (ListView)findViewById(R.id.ListView_listado);
        lv.setAdapter(new ListaAdapter(this, R.layout.activity_main, al) {
            public void onEntrada(Object o, View v){
                if(o!=null){
                    TextView texto_superior_entrada = (TextView)v.findViewById(R.id.textView_superior);
                    TextView texto_inferior_entrada = (TextView)v.findViewById(R.id.textView_inferior);
                    ImageView imagen_entrada = (ImageView) v.findViewById(R.id.imageView_imagen);
                    if(texto_superior_entrada != null){
                        texto_superior_entrada.setText(((ListaEntrada) o).get_textoEncima());
                    }
                    if(texto_inferior_entrada != null){
                        texto_inferior_entrada.setText(((ListaEntrada) o).get_textoDebajo());
                    }
                    if (imagen_entrada != null) {
                        imagen_entrada.setImageResource(((ListaEntrada) o).get_idImagen());
                    }
                }
            }
        });
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                ListaEntrada le = (ListaEntrada) av.getItemAtPosition(i);
                CharSequence cs = "Seleccionado: " + le.get_textoDebajo();
                Toast t = Toast.makeText(MainActivity.this, cs, Toast.LENGTH_SHORT);
                t.show();
            }
        });
    }
}
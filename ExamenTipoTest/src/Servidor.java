import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String [] args){

        String [] preguntasExamen = {"¿Qué es el nenúfar?:","¿Cuál es la onomatopeya del pájaro?","¿Quién fue el escultor del " +
                                "Discóbolo?","¿En qué año comenzó la Guerra de Sucesión en España?",
                                "¿Cómo se representa el número 2993 en números romanos?",
                                "¿De los siguientes cuál es el planeta más caliente?",
                                "¿En qué año entró el Euro como moneda única europea?",
                                "¿Cuál de las siguientes ciudades se encuentra en África?",
                                "¿Con qué nombre ha pasado Fernando VII a la Historia?",
                                "¿A qué período pertenece Benjamin Franklin?",
                                "¿Cuándo comenzó la Economía de Mercado?",
                                "¿Cuántos pulmones tiene el cuerpo humano?",
                                "¿A qué estilo arquitectónico pertenece la Torre de Pissa?",
                                "¿Qué emperador Romano hizo senador a su caballo?",
                                "La onomatopeya 'muac' ¿que representa?",
                                };
        String  [] correctas = {"Una planta","Pio","Miron","1700","MMCMXCIII","Venus","2002","Ceuta","El deseado",
                                "Siglo de las luces","Edad moderna","2","Romantico","Caligula","Beso"};

        String [] respuestas = {"Una planta","Un arbol","Una enfermedad","Un animal","Miau","Guau","Pio","Mu",
                                "Geppeto","Miguel Angel","Miron","Neron","1800","1750","1700","1650",
                                "MMCMXCIII","MMMCMXCIII","MXMXCIII","MMMXCIII","Jupiter","Marte","Venus","Mercurio",
                                "2002","2003","2004","2001","Ceuta","Huelva","Almeria","Cadiz",
                                "El esperado","El deseado","El desesperado","El admirado","Siglo de Oro","Siglo de las luces","Generacion del 27","Generacion del 28",
                                "Edad moderna","Edad antigua","Edad contemporanea","Edad media","1","3","2","0",
                                "Neoclasico","Gotico","Barroco","Romantico","Neron","Marco Antonio","Caligula","Julio Cesar",
                                "Golpe","Gato","Beso","Oveja",
                                };

        Preguntas [] preguntas = new Preguntas[15];
        ClienteHilo [] clientes = new ClienteHilo[10];
        int cont = 0;


        for (int i = 0; i < preguntas.length; i++) {
            preguntas[i] = new Preguntas(preguntasExamen[i],respuestas[(i*4)],respuestas[(i*4)+1],respuestas[(i*4)+2],
                    respuestas[(i*4)+3],correctas[i]);
        }

        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            Socket socket;
            while (true) {
                socket = serverSocket.accept();
                System.out.println("Servicio establecido..");
                clientes[cont] = new ClienteHilo(socket,preguntas);
                clientes[cont].start();
                cont++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

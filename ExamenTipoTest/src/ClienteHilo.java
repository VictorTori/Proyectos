import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteHilo extends Thread {

    Socket cliente;
    Preguntas [] preguntas;

    ClienteHilo(Socket cliente, Preguntas [] preguntas){

        this.cliente = cliente;
        this.preguntas = preguntas;
    }

    public void run(){

        try {
            ObjectOutputStream ous = new ObjectOutputStream(cliente.getOutputStream());
            ous.writeObject(preguntas);
            System.out.println("Preguntas enviadas");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

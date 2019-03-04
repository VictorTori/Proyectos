import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Random;

public class Cliente {

    public static void main(String [] args){

        Preguntas [] pregunta;
        int numPregunta = 0;
        int [] preguntasRealizadas = new int [10];
        try {
            Random r = new Random();
            Socket cliente = new Socket(args[0],6666);
            System.out.println("Conectado");
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
            Vista vista = new Vista();
            vista.frame.setVisible(false);
            vista.confirmar.addActionListener(vista);
            vista.siguiente.addActionListener(vista);

            pregunta = (Preguntas[]) ois.readObject();

            for (int i = 0; i < 10; i++) {
                do {
                    numPregunta = r.nextInt(pregunta.length - 1);
                } while(preguntasRealizadas.equals(numPregunta));

                vista.cambiarPregunta(pregunta[numPregunta]);
                preguntasRealizadas[i] = numPregunta;
                vista.pregunta.setText(pregunta[numPregunta].getPregunta());
                vista.respuesta[0].setText(pregunta[numPregunta].respuesta1);
                vista.respuesta[1].setText(pregunta[numPregunta].respuesta2);
                vista.respuesta[2].setText(pregunta[numPregunta].respuesta3);
                vista.respuesta[3].setText(pregunta[numPregunta].respuesta4);
                vista.frame.setVisible(true);
                while (vista.respuestas == i){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            JOptionPane.showMessageDialog(null,"Tu nota es: " + vista.correctasNum.getText() + "/" + vista.respuestas);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

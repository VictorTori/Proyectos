import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vista implements ActionListener{

    JRadioButton [] respuesta = new JRadioButton[4];
    Preguntas preguntaC;

    public ButtonGroup posiblesRespuestas;

    JLabel pregunta;
    JLabel correctas;
    JLabel correctasNum;

    JButton confirmar;
    JButton siguiente;

    JFrame frame;

    public int contadorCorrectas = 0;
    public int respuestas = 0;

    Vista(){

        frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(600,350);
        frame.setTitle("Examen tipo test");

        respuesta[0] = new JRadioButton("");
        respuesta[1] = new JRadioButton("");
        respuesta[2] = new JRadioButton("");
        respuesta[3] = new JRadioButton("");

        posiblesRespuestas = new ButtonGroup();

        posiblesRespuestas.add(respuesta[0]);
        posiblesRespuestas.add(respuesta[1]);
        posiblesRespuestas.add(respuesta[2]);
        posiblesRespuestas.add(respuesta[3]);

        pregunta = new JLabel("dasdsa");
        correctasNum = new JLabel("");
        correctas = new JLabel("Correctas: ");
        confirmar = new JButton("CONFIRMAR");
        siguiente = new JButton("SIGUIENTE");

        respuesta[0].setBounds(150,100,500,30);
        respuesta[1].setBounds(150,130,500,30);
        respuesta[2].setBounds(150,160,500,30);
        respuesta[3].setBounds(150,190,500,30);
        correctas.setBounds(500,50,500,30);
        correctasNum.setBounds(565,50,500,30);
        confirmar.setBounds(430,250,120,30);
        pregunta.setBounds(150,50,500,30);

        respuesta[0].setActionCommand("respuesta1");
        respuesta[1].setActionCommand("respuesta2");
        respuesta[2].setActionCommand("respuesta3");
        respuesta[3].setActionCommand("respuesta4");

        siguiente.setEnabled(false);

        frame.add(respuesta[0]);
        frame.add(respuesta[1]);
        frame.add(respuesta[2]);
        frame.add(respuesta[3]);
        frame.add(pregunta);
        frame.add(confirmar);
        frame.add(correctas);
        frame.add(correctasNum);
        frame.add(siguiente);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void cambiarPregunta(Preguntas preguntaC){

        this.preguntaC = preguntaC;
    }

    public static void main(String [] args){

        new Vista();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == confirmar){
            if(respuesta[0].isSelected() || respuesta[1].isSelected() || respuesta[2].isSelected() || respuesta[3].isSelected()) {
                for (int i = 0; i < respuesta.length ; i++) {
                    if(respuesta[i].isSelected() && preguntaC.getCorrecta().equals(respuesta[i].getText())){
                        contadorCorrectas++;
                        correctasNum.setText(Integer.toString(contadorCorrectas));
                    }
                }
                respuestas++;
                posiblesRespuestas.clearSelection();
            }
        }
    }
}

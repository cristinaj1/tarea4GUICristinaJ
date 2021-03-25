/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Cris
 */
public class PanelPrincipal extends JPanel implements ActionListener {

    // Atributos de la clase (privados)
    private PanelBotones botonera;
    private JTextArea areaTexto;
    private int tipoOperacion;

    // Constructor
    public PanelPrincipal() {
        initComponents();
        tipoOperacion = -1; // No hay operaciones en la calculadora
    }

    // Se inicializan los componentes gráficos y se colocan en el panel
    private void initComponents() {
        // Creamos el panel de botones
        botonera = new PanelBotones();
        // Creamos el área de texto
        areaTexto = new JTextArea(10, 50);
        areaTexto.setEditable(false);
        areaTexto.setBackground(Color.white);

        //Establecemos layout del panel principal
        this.setLayout(new BorderLayout());
        // Colocamos la botonera y el área texto
        this.add(areaTexto, BorderLayout.NORTH);
        this.add(botonera, BorderLayout.SOUTH);
        for (JButton boton : this.botonera.getgrupoBotones()) {
            boton.addActionListener(this);
        }

    }

    public boolean controlExcepcion(String boton, JTextArea areaTexto) {
        try {
            Integer.parseInt(areaTexto.getText());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Se obtiene el objeto que desencadena el evento
        Object o = ae.getSource();

        String numeroA = "";
        String numeroB = "";
        Boolean intercambiar = true;
        String operador = "";
        int resultado;
        String resultadoString;
        StringBuilder sb = new StringBuilder();
        String[] numeros = new String[2];
        String operacion="";
        // Si es un botón
        if (o instanceof JButton) {
            //Está en este orden porque si no da error.
            JButton prueba = (JButton) o;
            System.out.println(prueba.getText());
            //Para que nos muestre los numeros por pantalla controlando excepciones
            if (!controlExcepcion(prueba.getText(), areaTexto)) {
                areaTexto.setText(areaTexto.getText() + prueba.getText());
            } else {
                areaTexto.setText(areaTexto.getText() + prueba.getText());
            }

            if (prueba.getText().equals("+") || prueba.getText().equals("-") || prueba.getText().equals("*") || prueba.getText().equals("/")) {
                for (int i = 10; i < this.botonera.getgrupoBotones().length - 1; i++) {
                    //Para que se deshabilite los botones cuando le da a los operadores
                    this.botonera.getgrupoBotones()[i].setEnabled(false);
                }
            }
            for (int i = 0; i < areaTexto.getText().length() - 1; i++) {
                switch (areaTexto.getText().charAt(i)) {
                            case '+':
                                operador = "+";
                                break;
                            case '-':
                                operador = "-";
                                break;
                            case '*':
                                operador = "*";
                                break;
                            case '/':
                                operador = "/";
                                break;

                        }
            }
            for (int i = 0; i < areaTexto.getText().length() - 1; i++) {
                sb.append(areaTexto.getText().charAt(i));
            }
            operacion = sb.toString();
            System.out.println(operacion);
            System.out.println(operador);
            int numeroAInt = 1/*Integer.parseInt(numeroA)*/;
            int numeroBInt = 2;
            //Vuelve a activar los botones después de deshabilitarlos
            for (int i = 0; i < 10; i++) {
                if (prueba.getText().equals(Integer.toString(i)) || prueba.getText().equals("C")) {
                    for (int j = 10; j < this.botonera.getgrupoBotones().length - 1; j++) {
                        //Para que en el resto de botones sí puedan seguir siendo ejecutable
                        //Vale también para hace numeros de mas de 1 dígito
                        this.botonera.getgrupoBotones()[j].setEnabled(true);
                    }
                }
            }
            if (prueba.getText() == "C") {
                areaTexto.setText("");
            }

            if (prueba.getText() == "=") {
                System.out.println(areaTexto.getText());
                switch (operador) {
                    case "+":
                        numeros = areaTexto.getText().split(operador);
                        resultado = numeroAInt + numeroBInt;
                        resultadoString = String.valueOf(resultado);
                        areaTexto.setText(resultadoString);
                        break;
                    case "-":
                        numeros = areaTexto.getText().split(operador);
                        resultado = numeroAInt - numeroBInt;
                        resultadoString = String.valueOf(resultado);
                        areaTexto.setText(resultadoString);
                        break;
                    case "*":
                        numeros = areaTexto.getText().split(operador);
                        resultado = numeroAInt * numeroBInt;
                        resultadoString = String.valueOf(resultado);
                        areaTexto.setText(resultadoString);
                        break;
                    case "/":
                        numeros = areaTexto.getText().split(operador);
                        resultado = numeroAInt / numeroBInt;
                        resultadoString = String.valueOf(resultado);
                        areaTexto.setText(resultadoString);
                        break;
                        
                }

            }

            switch (prueba.getText()) {
                case "0":
                    areaTexto.setText(areaTexto.getText());

                    break;
                case "1":
                    areaTexto.setText(areaTexto.getText());
                    break;
                case "2":
                    areaTexto.setText(areaTexto.getText());
                    break;
                case "3":
                    areaTexto.setText(areaTexto.getText());
                    break;
                case "4":
                    areaTexto.setText(areaTexto.getText());
                    break;
                case "5":
                    areaTexto.setText(areaTexto.getText());
                    break;
                case "6":
                    areaTexto.setText(areaTexto.getText());
                    break;
                case "7":
                    areaTexto.setText(areaTexto.getText());
                    break;
                case "8":
                    areaTexto.setText(areaTexto.getText());
                    break;
                case "9":
                    areaTexto.setText(areaTexto.getText());
                    break;
            }

        }
    }
}

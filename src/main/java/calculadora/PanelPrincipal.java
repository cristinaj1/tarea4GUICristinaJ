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
        int numeroAInt;
        int numeroBInt = 0;
        String numeroB = "";
        Boolean intercambiar = true;
        String operador = "";
        int lugar;
        int resultado = 0;
        String aux;
        String resultadoString = null;
        StringBuilder sb = new StringBuilder();
        String[] numeros = new String[2];
        String igual;
        String operacion = "";
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
            //Para que reconozca como operador a cada símbolo
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
            //Lee el area de texto por diferenete
            for (int i = 0; i < areaTexto.getText().length() - 1; i++) {
                sb.append(areaTexto.getText().charAt(i));
            }
            operacion = sb.toString();
            System.out.println(operacion);
            System.out.println(operador);
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
            //Cuando sea c se borra lo que hay en el area de Texto
            if (prueba.getText() == "C") {
                areaTexto.setText("");
            }
            //Cuando se da al igual se harán todos los cálculos
            if (((JButton) o).getText() == "=") {
                igual = areaTexto.getText().substring(numeroB.length(), areaTexto.getText().length());
                System.out.println(areaTexto.getText());
                switch (operador) {
                    //Te obliga que las operaciones tengan un primer número de una unidad pero funciona
                    case "+":
                        numeroA = areaTexto.getText().substring(0, 1);
                        numeroB = areaTexto.getText().substring(operador.length() + 1, areaTexto.getText().length() - 1);
                        numeroAInt = Integer.parseInt(numeroA);
                        numeroBInt = Integer.parseInt(numeroB);
                        resultado = numeroAInt + numeroBInt;
                        resultadoString = String.valueOf(resultado);
                        areaTexto.setText(resultadoString);
                        break;
                    case "-":
                        numeroA = areaTexto.getText().substring(0, 1);
                        numeroB = areaTexto.getText().substring(operador.length() + 1, areaTexto.getText().length() - 1);
                        numeroAInt = Integer.parseInt(numeroA);
                        numeroBInt = Integer.parseInt(numeroB);
                        resultado = numeroAInt - numeroBInt;
                        resultadoString = String.valueOf(resultado);
                        areaTexto.setText(resultadoString);
                        break;
                    case "*":
                        numeroA = areaTexto.getText().substring(0, 1);
                        numeroB = areaTexto.getText().substring(operador.length() + 1, areaTexto.getText().length() - 1);
                        numeroAInt = Integer.parseInt(numeroA);
                        numeroBInt = Integer.parseInt(numeroB);
                        resultado = numeroAInt * numeroBInt;
                        resultadoString = String.valueOf(resultado);
                        areaTexto.setText(resultadoString);
                        break;
                    case "/":
                        numeroA = areaTexto.getText().substring(0, 1);
                        numeroB = areaTexto.getText().substring(operador.length() + 1, areaTexto.getText().length() - 1);
                        numeroAInt = Integer.parseInt(numeroA);
                        numeroBInt = Integer.parseInt(numeroB);
                        resultado = numeroAInt / numeroBInt;
                        resultadoString = String.valueOf(resultado);
                        areaTexto.setText(resultadoString);
                        break;
                    case "=":
                        igual = areaTexto.getText().substring((numeroB.length() + 1) + 1, areaTexto.getText().length());
                        System.out.println(areaTexto.getText());
                        break;

                }
            }

            //Para que lea cada número y lo muestre en el area de texto
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

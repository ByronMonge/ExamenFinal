package main;

import controlador.ControladorMedicamento;
import modelo.ModeloMedicamento;
import vista.VistaMedicamento;

public class inicio {

    public static void main(String[] args) {

        VistaMedicamento vista = new VistaMedicamento();
        ModeloMedicamento modelo = new ModeloMedicamento();

        ControladorMedicamento control = new ControladorMedicamento(modelo, vista);
        control.iniciarControl();
    }
}

package controlador;

import modelo.ModeloMedicamento;
import vista.VistaMedicamento;

public class ControladorMedicamento {

    ModeloMedicamento modelo;
    VistaMedicamento vista;

    public ControladorMedicamento(ModeloMedicamento modelo, VistaMedicamento vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
    }
    
    public void iniciarControl(){
        
    }
    
    
}

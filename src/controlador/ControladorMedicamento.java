package controlador;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import modelo.Medicamento;
import modelo.ModeloMedicamento;
import vista.VistaMedicamento;

public class ControladorMedicamento {

    ModeloMedicamento modelo;
    VistaMedicamento vista;

    private JFileChooser jfc; //Objeto de tipo JFileChooser

    public ControladorMedicamento(ModeloMedicamento modelo, VistaMedicamento vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
    }

    public void iniciarControl() {

        cargarMedicamentosTabla();

        vista.getBtnCrear().addActionListener(l -> abrirJDlCrear());
        vista.getBtnExaminar().addActionListener(l -> seleccionarFoto());
        vista.getBtnGuardar().addActionListener(l -> crearEditarMedicamento());
        vista.getBtnActualizar().addActionListener(l -> cargarMedicamentosTabla());
    }

    public void abrirJDlCrear() {

        vista.getjDlgMedicamento().setVisible(true);
        vista.getjDlgMedicamento().setSize(599, 470);
        vista.getjDlgMedicamento().setLocationRelativeTo(null);
        vista.getjDlgMedicamento().setName("Ingresar medicamento");
        vista.getjDlgMedicamento().setTitle("Ingresar medicamento");

    }

    public void cargarMedicamentosTabla() {
        vista.getTblMedicamento().setDefaultRenderer(Object.class, new ImagenTabla());//La manera de renderizar la tabla.
        vista.getTblMedicamento().setRowHeight(100);

        //Enlazar el modelo de tabla con mi controlador.
        DefaultTableModel tblModel;
        tblModel = (DefaultTableModel) vista.getTblMedicamento().getModel();
        tblModel.setNumRows(0);//limpio filas de la tabla.

        List<Medicamento> listap = modelo.listaMedicamentosTabla();//Enlazo al Modelo y obtengo los datos
        Holder<Integer> i = new Holder<>(0);//Contador para las filas. 'i' funciona dentro de una expresion lambda

        listap.stream().forEach(pe -> {

            tblModel.addRow(new Object[9]);//Creo una fila vacia
            vista.getTblMedicamento().setValueAt(pe.getMed_codigo(), i.value, 0);
            vista.getTblMedicamento().setValueAt(pe.getMed_nomcom(), i.value, 1);
            vista.getTblMedicamento().setValueAt(pe.getMed_nomgen(), i.value, 2);
            vista.getTblMedicamento().setValueAt(pe.getMed_fechaela(), i.value, 3);
            vista.getTblMedicamento().setValueAt(pe.getMed_fechaexp(), i.value, 4);
            vista.getTblMedicamento().setValueAt(pe.getMed_costo(), i.value, 5);
            vista.getTblMedicamento().setValueAt(pe.getMed_pvp(), i.value, 6);

            Image foto = pe.getImagen();
            if (foto != null) {

                Image nimg = foto.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon icono = new ImageIcon(nimg);
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setIcon(icono);
                vista.getTblMedicamento().setValueAt(new JLabel(icono), i.value, 7);

            } else {
                vista.getTblMedicamento().setValueAt(null, i.value, 7);
            }

            i.value++;
        });
    }

    public void seleccionarFoto() {

        vista.getLblFoto().setIcon(null);
        jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int estado = jfc.showOpenDialog(null);

        if (estado == JFileChooser.APPROVE_OPTION) {
            try {
                Image imagen = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(vista.getLblFoto().getWidth(), vista.getLblFoto().getHeight(), Image.SCALE_DEFAULT);
                vista.getLblFoto().setIcon(new ImageIcon(imagen));
                vista.getLblFoto().updateUI();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(vista, "Error: " + ex);
            }
        }
    }

    private void crearEditarMedicamento() {
        if ("Ingresar medicamento".equals(vista.getjDlgMedicamento().getName())) {

            //INSERTAR
            ModeloMedicamento medicamento = new ModeloMedicamento();

            medicamento.setMed_nomcom(vista.getTxtNomCom().getText());
            medicamento.setMed_nomgen(vista.getTxtNomGen().getText());

            java.sql.Date fechaEl = new java.sql.Date(vista.getFechaElaboracion().getDate().getTime());//Paso de util.Date a sql.Date
            medicamento.setMed_fechaela(fechaEl);

            java.sql.Date fechaEx = new java.sql.Date(vista.getFechaExpiracion().getDate().getTime());//Paso de util.Date a sql.Date
            medicamento.setMed_fechaexp(fechaEx);

            medicamento.setMed_costo(Double.parseDouble(vista.getSpinnerCosto().getValue().toString()));
            medicamento.setMed_pvp(Double.parseDouble(vista.getSpinnerPVP().getValue().toString()));

            //Foto
            try {

                FileInputStream foto = new FileInputStream(jfc.getSelectedFile());
                int longitud = (int) jfc.getSelectedFile().length();

                medicamento.setFoto(foto);
                medicamento.setLongitud(longitud);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (medicamento.crearMedicamento()) {

                JOptionPane.showMessageDialog(null, "Medicamento creado satisfactoriamente");
                vista.getjDlgMedicamento().setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo crear el medicamento");
            }

        } else {

//            //EDITAR
//            String cedula = vista.getTxtIdentificacion().getText();
//            String nombres = vista.getTxtNombres().getText();
//            String apellidos = vista.getTxtApellidos().getText();
//
//            String sexo;
//            if (vista.getRbMasculino().isSelected()) {
//                sexo = "Masculino";
//            } else {
//                if (vista.getRbFemenino().isSelected()) {
//                    sexo = "Femenino";
//                } else {
//                    sexo = "null";
//                }
//            }
//
//            String telefono = vista.getTxtTelefono().getText();
//            Date fecha = vista.getjDateFecha().getDate();
//            double sueldo = Double.parseDouble(vista.getSpinnerSueldo().getValue().toString());
//            int cupo = Integer.parseInt(vista.getSpinnerCupo().getValue().toString());
//            String correo = vista.getTxtCorreo().getText();
//
//            ModeloPersona persona = new ModeloPersona();
//            persona.setIdPersona(cedula);
//            persona.setNombre(nombres);
//            persona.setApellido(apellidos);
//            persona.setSexo(sexo);
//            persona.setTelefono(telefono);
//
//            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());//Paso de util.Date a sql.Date
//            persona.setFechaDeNacimiento(fechaSQL);
//            persona.setSueldo(sueldo);
//            persona.setCupo(cupo);
//            persona.setCorreo(correo);
//
//            if (vista.getLabelFoto().getIcon() == null) {
//                if (persona.modificarPersonaSinFoto()) {
//
//                    vista.getDlgPersona().setVisible(false);
//                    JOptionPane.showMessageDialog(vista, "Persona Modificada Satisfactoriamente");
//                } else {
//                    JOptionPane.showMessageDialog(vista, "No se pudo modificar la persona");
//                }
//            } else {
//
//                //Foto
//                try {
//
//                    FileInputStream img = new FileInputStream(jfc.getSelectedFile());
//                    int longitud = (int) jfc.getSelectedFile().length();
//                    persona.setFoto(img);
//                    persona.setLongitud(longitud);
//                } catch (FileNotFoundException | NullPointerException ex) {
//                    Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                if (persona.modificarPersonaFoto()) {
//
//                    vista.getDlgPersona().setVisible(false);
//                    JOptionPane.showMessageDialog(vista, "Persona Modificada Satisfactoriamente");
//                } else {
//                    JOptionPane.showMessageDialog(vista, "No se pudo modificar la persona");
//                }
//            }
        }

        cargarMedicamentosTabla();
    }
}

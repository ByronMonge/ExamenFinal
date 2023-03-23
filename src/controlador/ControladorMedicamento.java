package controlador;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import modelo.ConectPG;
import modelo.Medicamento;
import modelo.ModeloMedicamento;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
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
        buscar();

        vista.getBtnCrear().addActionListener(l -> abrirJDlCrear());
        vista.getBtnExaminar().addActionListener(l -> seleccionarFoto());
        vista.getBtnGuardar().addActionListener(l -> crearEditarMedicamento());
        vista.getBtnActualizar().addActionListener(l -> cargarMedicamentosTabla());
        vista.getBtnModificar().addActionListener(l -> abrirYCargarDatosEnElDialog());
        vista.getBtnEliminar().addActionListener(l -> eliminar());
        vista.getBtnImprimir().addActionListener(l -> imprimir());
    }

    public void imprimir() {

        ConectPG cpg = new ConectPG();//Instanciar la conexion con esto abrimos la conexion a la BD
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/reporte/CursoReporte.jasper"));

            //Hacer una vista previa
            //JasperPrint jp = JasperFillManager.fillReport(jr, null, cpg.getCon());//JasperFillManager.fillReport: Carga los datos de la BD.//JasperPrint: Hace la impresion del reporte. Puede ir 'null' si en el jasper no existen parametros caso contrario se envian los parametros necesarios
            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put("RutaImagen", "src/imagenesJasper/curso.png"); //En donde esta 'titulo' tienen que ser igual al nombre que esta en el parametro del jasper

            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cpg.getCon());//'parametros' es el Map recien creado que contiene los parametros que iran al jasper

            JasperViewer jv = new JasperViewer(jp, false); //Se pasa false para que no se cierre el sistema 
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControladorMedicamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abrirJDlCrear() {

        vista.getjDlgMedicamento().setVisible(true);
        vista.getjDlgMedicamento().setSize(620, 470);
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
            modelo.setMed_nomcom(vista.getTxtNomCom().getText());
            modelo.setMed_nomgen(vista.getTxtNomGen().getText());

            java.sql.Date fechaEl = new java.sql.Date(vista.getFechaElaboracion().getDate().getTime());
            modelo.setMed_fechaela(fechaEl);

            java.sql.Date fechaEx = new java.sql.Date(vista.getFechaExpiracion().getDate().getTime());
            modelo.setMed_fechaexp(fechaEx);

            modelo.setMed_costo(Double.parseDouble(vista.getSpinnerCosto().getValue().toString()));
            modelo.setMed_pvp(Double.parseDouble(vista.getSpinnerPVP().getValue().toString()));

            //Foto
            try {

                FileInputStream foto = new FileInputStream(jfc.getSelectedFile());
                int longitud = (int) jfc.getSelectedFile().length();

                modelo.setFoto(foto);
                modelo.setLongitud(longitud);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (modelo.crearMedicamento()) {

                JOptionPane.showMessageDialog(null, "Medicamento creado satisfactoriamente");
                vista.getjDlgMedicamento().setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo crear el medicamento");
            }

        } else {

            //EDITAR
            modelo.setMed_codigo(Integer.parseInt(vista.getTxtCodigo().getText()));
            modelo.setMed_nomcom(vista.getTxtNomCom().getText());
            modelo.setMed_nomgen(vista.getTxtNomGen().getText());

            java.sql.Date fechaEl = new java.sql.Date(vista.getFechaElaboracion().getDate().getTime());
            modelo.setMed_fechaela(fechaEl);

            java.sql.Date fechaEx = new java.sql.Date(vista.getFechaExpiracion().getDate().getTime());
            modelo.setMed_fechaexp(fechaEx);

            modelo.setMed_costo(Double.parseDouble(vista.getSpinnerCosto().getValue().toString()));
            modelo.setMed_pvp(Double.parseDouble(vista.getSpinnerPVP().getValue().toString()));
            //Foto
            try {

                FileInputStream foto = new FileInputStream(jfc.getSelectedFile());
                int longitud = (int) jfc.getSelectedFile().length();

                modelo.setFoto(foto);
                modelo.setLongitud(longitud);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (modelo.modificarMedicamento()) {

                JOptionPane.showMessageDialog(null, "Medicamento modificado satisfactoriamente");
                vista.getjDlgMedicamento().setVisible(false);
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo modificar el medicamento");
            }

        }

        cargarMedicamentosTabla();
    }

    public void abrirYCargarDatosEnElDialog() {

        int seleccion = vista.getTblMedicamento().getSelectedRow();

        if (seleccion == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int codigo = Integer.parseInt(vista.getTblMedicamento().getValueAt(seleccion, 0).toString());
            modelo.listaMedicamentosTabla().forEach((m) -> {
                if (m.getMed_codigo() == codigo) {

                    //Abre el jDialog y carga los datos en el jDialog
                    vista.getjDlgMedicamento().setVisible(true);
                    vista.getjDlgMedicamento().setSize(620, 470);
                    vista.getjDlgMedicamento().setLocationRelativeTo(null);
                    vista.getjDlgMedicamento().setName("Editar");
                    vista.getjDlgMedicamento().setTitle("Editar");

                    vista.getTxtCodigo().setText(String.valueOf(codigo));
                    vista.getTxtNomCom().setText(m.getMed_nomcom());
                    vista.getTxtNomGen().setText(m.getMed_nomgen());
                    vista.getFechaElaboracion().setDate(m.getMed_fechaela());
                    vista.getFechaExpiracion().setDate(m.getMed_fechaexp());
                    vista.getSpinnerCosto().setValue(m.getMed_costo());
                    vista.getSpinnerPVP().setValue(m.getMed_pvp());
                    vista.getLblFoto().setIcon(modelo.ConsultarFoto(codigo)); //Llamo al metodo 'ConsultarFoto' del modelo
                }
            });
        }
    }

    public void eliminar() {

        int fila = vista.getTblMedicamento().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                int codigo;
                codigo = Integer.parseInt(vista.getTblMedicamento().getValueAt(fila, 0).toString());

                if (modelo.eliminarMedicamento(codigo)) {
                    JOptionPane.showMessageDialog(null, "El medicamento se elimino satisfactoriamente");
                    cargarMedicamentosTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "El medicamento no se pudo eliminar");
                }
            }
        }
    }

    public void buscar() {

        KeyListener eventoTeclado = new KeyListener() {//Crear un objeto de tipo keyListener(Es una interface) por lo tanto se debe implementar sus metodos abstractos

            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {

                vista.getTblMedicamento().setDefaultRenderer(Object.class, new ImagenTabla());//La manera de renderizar la tabla.
                vista.getTblMedicamento().setRowHeight(100);

                //Enlazar el modelo de tabla con mi controlador.
                DefaultTableModel tblModel;
                tblModel = (DefaultTableModel) vista.getTblMedicamento().getModel();
                tblModel.setNumRows(0);//limpio filas de la tabla.

                int codigo = 0;
                if (!vista.getTxtBuscar().getText().equals("")) {
                    codigo = Integer.parseInt(vista.getTxtBuscar().getText());
                }

                List<Medicamento> listap = modelo.buscar(codigo);//Enlazo al Modelo y obtengo los datos
                Holder<Integer> i = new Holder<>(0);//contador para el no. fila
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
        };

        vista.getTxtBuscar().addKeyListener(eventoTeclado); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }
}

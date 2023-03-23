package vista;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

public class VistaMedicamento extends javax.swing.JFrame {

    public VistaMedicamento() {
        initComponents();
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public void setBtnActualizar(JButton btnActualizar) {
        this.btnActualizar = btnActualizar;
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }

    public void setBtnCrear(JButton btnCrear) {
        this.btnCrear = btnCrear;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(JButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public JTable getTblMedicamento() {
        return tblMedicamento;
    }

    public void setTblMedicamento(JTable tblMedicamento) {
        this.tblMedicamento = tblMedicamento;
    }

    //Cragar datos
    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JButton getBtnExaminar() {
        return btnExaminar;
    }

    public void setBtnExaminar(JButton btnExaminar) {
        this.btnExaminar = btnExaminar;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public JDateChooser getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(JDateChooser fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public JDateChooser getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(JDateChooser fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public JDialog getjDlgMedicamento() {
        return jDlgMedicamento;
    }

    public void setjDlgMedicamento(JDialog jDlgMedicamento) {
        this.jDlgMedicamento = jDlgMedicamento;
    }

    public JLabel getLblFoto() {
        return lblFoto;
    }

    public void setLblFoto(JLabel lblFoto) {
        this.lblFoto = lblFoto;
    }

    public JSpinner getSpinnerCosto() {
        return spinnerCosto;
    }

    public void setSpinnerCosto(JSpinner spinnerCosto) {
        this.spinnerCosto = spinnerCosto;
    }

    public JSpinner getSpinnerPVP() {
        return spinnerPVP;
    }

    public void setSpinnerPVP(JSpinner spinnerPVP) {
        this.spinnerPVP = spinnerPVP;
    }

    public JTextField getTxtNomCom() {
        return txtNomCom;
    }

    public void setTxtNomCom(JTextField txtNomCom) {
        this.txtNomCom = txtNomCom;
    }

    public JTextField getTxtNomGen() {
        return txtNomGen;
    }

    public void setTxtNomGen(JTextField txtNomGen) {
        this.txtNomGen = txtNomGen;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JButton getBtnImprimir() {
        return btnImprimir;
    }

    public void setBtnImprimir(JButton btnImprimir) {
        this.btnImprimir = btnImprimir;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDlgMedicamento = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        txtNomCom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNomGen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        fechaElaboracion = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        fechaExpiracion = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        spinnerCosto = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        spinnerPVP = new javax.swing.JSpinner();
        lblFoto = new javax.swing.JLabel();
        btnExaminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnCrear = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMedicamento = new javax.swing.JTable();

        jDlgMedicamento.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Nombre comercial:");
        jDlgMedicamento.getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 40, -1, -1));
        jDlgMedicamento.getContentPane().add(txtNomCom, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 37, 148, -1));

        jLabel4.setText("Nombre generico:");
        jDlgMedicamento.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 93, -1, -1));
        jDlgMedicamento.getContentPane().add(txtNomGen, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 93, 148, -1));

        jLabel5.setText("Fecha de elaboracion:");
        jDlgMedicamento.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 146, -1, -1));
        jDlgMedicamento.getContentPane().add(fechaElaboracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 143, 148, -1));

        jLabel6.setText("Fecha de expiracion:");
        jDlgMedicamento.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 204, -1, -1));
        jDlgMedicamento.getContentPane().add(fechaExpiracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 201, 149, -1));

        jLabel7.setText("Costo:");
        jDlgMedicamento.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 260, -1, -1));
        jDlgMedicamento.getContentPane().add(spinnerCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 260, 149, -1));

        jLabel8.setText("PVP:");
        jDlgMedicamento.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 317, -1, -1));
        jDlgMedicamento.getContentPane().add(spinnerPVP, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 316, 149, -1));

        lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jDlgMedicamento.getContentPane().add(lblFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 126, 146));

        btnExaminar.setText("Examinar");
        jDlgMedicamento.getContentPane().add(btnExaminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, -1, -1));

        btnCancelar.setText("Cancelar");
        jDlgMedicamento.getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 388, -1, -1));

        btnGuardar.setText("Guardar");
        jDlgMedicamento.getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 388, -1, -1));
        jDlgMedicamento.getContentPane().add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 40, -1));

        jLabel2.setText("Código:");
        jDlgMedicamento.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, -1, -1));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCrear.setText("Crear");

        btnActualizar.setText("Actualizar");

        btnEliminar.setText("Eliminar");

        btnModificar.setText("Modificar");

        jLabel1.setText("Buscar");

        btnImprimir.setText("Imprimir");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCrear)
                .addGap(52, 52, 52)
                .addComponent(btnModificar)
                .addGap(35, 35, 35)
                .addComponent(btnActualizar)
                .addGap(31, 31, 31)
                .addComponent(btnEliminar)
                .addGap(39, 39, 39)
                .addComponent(btnImprimir)
                .addGap(76, 76, 76))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnActualizar)
                    .addComponent(btnModificar)
                    .addComponent(btnCrear)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnImprimir))
                .addGap(37, 37, 37))
        );

        tblMedicamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre comercial", "Nombre generico", "Fecha de elaboracion", "Fecha de expiracion", "Costo", "PVP", "Imagen"
            }
        ));
        jScrollPane1.setViewportView(tblMedicamento);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnExaminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnModificar;
    private com.toedter.calendar.JDateChooser fechaElaboracion;
    private com.toedter.calendar.JDateChooser fechaExpiracion;
    private javax.swing.JDialog jDlgMedicamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JSpinner spinnerCosto;
    private javax.swing.JSpinner spinnerPVP;
    private javax.swing.JTable tblMedicamento;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNomCom;
    private javax.swing.JTextField txtNomGen;
    // End of variables declaration//GEN-END:variables
}

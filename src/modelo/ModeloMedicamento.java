package modelo;

import java.awt.Image;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloMedicamento extends Medicamento {

    ConectPG conpg = new ConectPG();

    public ModeloMedicamento() {
    }

    public ModeloMedicamento(int med_codigo, String med_nomcom, String med_nomgen, java.sql.Date med_fechaela, java.sql.Date med_fechaexp, double med_costo, double med_pvp, FileInputStream foto, int longitud, Image imagen) {
        super(med_codigo, med_nomcom, med_nomgen, med_fechaela, med_fechaexp, med_costo, med_pvp, foto, longitud, imagen);
    }

    public boolean crearMedicamento() {
        try {
            String sql;

            sql = "INSERT INTO medicamento (med_nomcom, med_nomgen, med_fechaela, med_fechaexp, med_costo, med_pvp, med_imagen)";
            sql += "VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conpg.getCon().prepareStatement(sql);
            ps.setString(1, getMed_nomcom());
            ps.setString(2, getMed_nomgen());
            ps.setDate(3, getMed_fechaela());
            ps.setDate(4, getMed_fechaexp());
            ps.setDouble(5, getMed_costo());
            ps.setDouble(6, getMed_pvp());
            ps.setBinaryStream(9, getFoto(), getLongitud());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}

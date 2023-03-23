package modelo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

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
            sql += "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = conpg.getCon().prepareStatement(sql);
            ps.setString(1, getMed_nomcom());
            ps.setString(2, getMed_nomgen());
            ps.setDate(3, getMed_fechaela());
            ps.setDate(4, getMed_fechaexp());
            ps.setDouble(5, getMed_costo());
            ps.setDouble(6, getMed_pvp());
            ps.setBinaryStream(7, getFoto(), getLongitud());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Medicamento> listaMedicamentosTabla() {
        try {

            List<Medicamento> lista = new ArrayList<>();

            String sql = "select * from medicamento";

            ResultSet rs = conpg.consulta(sql);
            byte[] bytea;

            while (rs.next()) {

                Medicamento medicamento = new Medicamento();

                medicamento.setMed_codigo(rs.getInt("med_codigo"));
                medicamento.setMed_nomcom(rs.getString("med_nomcom"));
                medicamento.setMed_nomgen(rs.getString("med_nomgen"));
                medicamento.setMed_fechaela(rs.getDate("med_fechaela"));
                medicamento.setMed_fechaexp(rs.getDate("med_fechaexp"));
                medicamento.setMed_costo(rs.getDouble("med_costo"));
                medicamento.setMed_pvp(rs.getDouble("med_pvp"));
                bytea = rs.getBytes("med_imagen");

                if (bytea != null) {

                    try {
                        medicamento.setImagen(obtenerImagen(bytea));
                    } catch (IOException ex) {
                        Logger.getLogger(ModeloMedicamento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                lista.add(medicamento); //Agrego los datos a la lista
            }

            //Cierro la conexion a la BD
            rs.close();
            //Retorno la lista
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ModeloMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private Image obtenerImagen(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator it = ImageIO.getImageReadersByFormatName("png");
        ImageReader reader = (ImageReader) it.next();
        Object source = bis;
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        param.setSourceSubsampling(1, 1, 0, 0);
        return reader.read(0, param);
    }

    public boolean modificarMedicamento() {
        try {
            String sql;

            sql = "UPDATE medicamento SET med_nomcom=?,med_nomgen=?,med_fechaela=?,med_fechaexp=?,med_costo=?,med_pvp=?,med_imagen=? Where med_codigo=?";
            PreparedStatement ps = conpg.getCon().prepareStatement(sql);
            ps.setString(1, getMed_nomcom());
            ps.setString(2, getMed_nomgen());
            ps.setDate(3, getMed_fechaela());
            ps.setDate(4, getMed_fechaexp());
            ps.setDouble(5, getMed_costo());
            ps.setDouble(6, getMed_pvp());
            ps.setBinaryStream(7, getFoto(), getLongitud());
            ps.setInt(8, getMed_codigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ImageIcon ConsultarFoto(int codigo) {
        conpg.getCon();
        String sql = "select med_imagen from \"medicamento\" where med_codigo = " + codigo + ";";
        ImageIcon foto;
        InputStream is;

        try {
            ResultSet rs = conpg.consulta(sql);
            while (rs.next()) {

                is = rs.getBinaryStream(1);

                BufferedImage bi = ImageIO.read(is);
                foto = new ImageIcon(bi);

                Image img = foto.getImage();
                Image newimg = img.getScaledInstance(118, 139, java.awt.Image.SCALE_SMOOTH);

                ImageIcon newicon = new ImageIcon(newimg);

                return newicon;
            }
        } catch (Exception ex) {

            return null;
        }

        return null;
    }

    public boolean eliminarMedicamento(int codigo) {

        String sql = "DELETE FROM medicamento WHERE med_codigo = " + codigo + ";";

        return conpg.accion(sql);
    }

    public List<Medicamento> buscar(int codigo) {

        try {

            List<Medicamento> lista = new ArrayList<>();

            String sql = "select * from medicamento where med_codigo = " + codigo + ";";

            ResultSet rs = conpg.consulta(sql);
            byte[] bytea;

            while (rs.next()) {

                Medicamento medicamento = new Medicamento();

                medicamento.setMed_codigo(rs.getInt("med_codigo"));
                medicamento.setMed_nomcom(rs.getString("med_nomcom"));
                medicamento.setMed_nomgen(rs.getString("med_nomgen"));
                medicamento.setMed_fechaela(rs.getDate("med_fechaela"));
                medicamento.setMed_fechaexp(rs.getDate("med_fechaexp"));
                medicamento.setMed_costo(rs.getDouble("med_costo"));
                medicamento.setMed_pvp(rs.getDouble("med_pvp"));
                bytea = rs.getBytes("med_imagen");

                if (bytea != null) {

                    try {
                        medicamento.setImagen(obtenerImagen(bytea));
                    } catch (IOException ex) {
                        Logger.getLogger(ModeloMedicamento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                lista.add(medicamento); //Agrego los datos a la lista
            }

            //Cierro la conexion a la BD
            rs.close();
            //Retorno la lista
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ModeloMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}

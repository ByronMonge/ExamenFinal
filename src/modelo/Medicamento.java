package modelo;

import java.awt.Image;
import java.io.FileInputStream;
import java.sql.Date;


public class Medicamento {

    private int med_codigo;
    private String med_nomcom;
    private String med_nomgen;
    private Date med_fechaela;
    private Date med_fechaexp;
    private double med_costo;
    private double med_pvp;

    //Guardar foto
    private FileInputStream foto;
    private int longitud;

    //Recuperar imagen
    private Image imagen;

    public Medicamento() {
    }

    public Medicamento(int med_codigo, String med_nomcom, String med_nomgen, Date med_fechaela, Date med_fechaexp, double med_costo, double med_pvp, FileInputStream foto, int longitud, Image imagen) {
        this.med_codigo = med_codigo;
        this.med_nomcom = med_nomcom;
        this.med_nomgen = med_nomgen;
        this.med_fechaela = med_fechaela;
        this.med_fechaexp = med_fechaexp;
        this.med_costo = med_costo;
        this.med_pvp = med_pvp;
        this.foto = foto;
        this.longitud = longitud;
        this.imagen = imagen;
    }

    public int getMed_codigo() {
        return med_codigo;
    }

    public void setMed_codigo(int med_codigo) {
        this.med_codigo = med_codigo;
    }

    public String getMed_nomcom() {
        return med_nomcom;
    }

    public void setMed_nomcom(String med_nomcom) {
        this.med_nomcom = med_nomcom;
    }

    public String getMed_nomgen() {
        return med_nomgen;
    }

    public void setMed_nomgen(String med_nomgen) {
        this.med_nomgen = med_nomgen;
    }

    public Date getMed_fechaela() {
        return med_fechaela;
    }

    public void setMed_fechaela(Date med_fechaela) {
        this.med_fechaela = med_fechaela;
    }

    public Date getMed_fechaexp() {
        return med_fechaexp;
    }

    public void setMed_fechaexp(Date med_fechaexp) {
        this.med_fechaexp = med_fechaexp;
    }

    public double getMed_costo() {
        return med_costo;
    }

    public void setMed_costo(double med_costo) {
        this.med_costo = med_costo;
    }

    public double getMed_pvp() {
        return med_pvp;
    }

    public void setMed_pvp(double med_pvp) {
        this.med_pvp = med_pvp;
    }

    public FileInputStream getFoto() {
        return foto;
    }

    public void setFoto(FileInputStream foto) {
        this.foto = foto;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
}

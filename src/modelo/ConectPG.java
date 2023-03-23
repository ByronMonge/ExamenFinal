package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectPG {

    Connection con;

    String cadenaConexion = "jdbc:postgresql://localhost:5432/PV. Examen_Final"; // conexion String o cadena de conexion
    String usuarioPG = "postgres";
    String passPG = "bfml1123"; //contrasenia de postgres

    public ConectPG() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConectPG.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            con = DriverManager.getConnection(cadenaConexion, usuarioPG, passPG);
        } catch (SQLException ex) {
            Logger.getLogger(ConectPG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodos genericos para realizar las transacciones en la base de datos
    /*Metodo generico para cuando me devuelve datos*/
    public ResultSet consulta(String sql) {

        try {
            Statement st = con.createStatement(); //recive como parametro la consulta
            return st.executeQuery(sql);//Ejecuto la consulta y me devuelve un 'Resultset'

        } catch (SQLException ex) {
            Logger.getLogger(ConectPG.class.getName()).log(Level.SEVERE, null, ex);
            return null; //Si se da la excepcion me retorna un null
        }
    }

    /*Metodo generico cuando no devuelve datos. FORMA 1*/
    public boolean accion(String sql) {
        boolean correcto;
        try {
            Statement st = con.createStatement();
            st.execute(sql);
            st.close();
            correcto = true;
        } catch (SQLException ex) {
            Logger.getLogger(ConectPG.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            correcto = false;
        }

        return correcto;
    }

    public Connection getCon() {
        return con;
    }
}

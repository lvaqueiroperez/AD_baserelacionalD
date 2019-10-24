package ex21_baserelacionald;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ex21_baserelacionalD {

    Connection conn;

    public void Conexion() throws SQLException {

        String driver = "jdbc:oracle:thin:";
        String host = "localhost.localdomain"; // tambien puede ser una ip como "192.168.1.14"
        String porto = "1521";
        String sid = "orcl";
        String usuario = "hr";
        String password = "hr";
        String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;

        conn = DriverManager.getConnection(url);

    }

    public void Cerrar() throws SQLException {

        conn.close();

    }

    public void mostrarMetadata() {

        try {
            PreparedStatement pst = conn.prepareStatement("select produtos.* from produtos");

            ResultSetMetaData rsmd = pst.getMetaData();

            int numcolums = rsmd.getColumnCount();

            for (int i = 1; i <= numcolums; i++) {

                System.out.println(rsmd.getColumnName(i) + "," + rsmd.getColumnTypeName(i) + "," + rsmd.getColumnDisplaySize(i));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Ex21_baserelacionalD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {

        Ex21_baserelacionalD obj = new Ex21_baserelacionalD();

        try {
            obj.Conexion();

            obj.mostrarMetadata();

            obj.Cerrar();

        } catch (SQLException ex) {
            Logger.getLogger(Ex21_baserelacionalD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

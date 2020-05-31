
package Conection;

import Additionals.Texto;
import Processing.DuchaInfo;
import java.sql.*;
import javax.swing.JOptionPane;

public class DataBase {
    
    private static final String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10344429";
    private static final String USER = "sql10344429";
    private static final String PASS = "RZuaFb2mwy";
    
    static Connection conect = null;
    public static Connection conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conect = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return conect; 
    }
    
    public static boolean Exist(String fecha){
        boolean error = false;
        try {
            PreparedStatement statement = conect.prepareStatement("SELECT * FROM Datos_ducha WHERE Fecha='"+fecha+"'");
            ResultSet res = statement.executeQuery();
            return !res.next();
        } catch (SQLException ex) {
            error = true;
        }
        if (error) {
            JOptionPane.showMessageDialog(null, Texto.AV8);
        }
        return true;
    }
    
    public static void Insert(){
        boolean error = false;
        for (int i = 0; i < DuchaInfo.duchas.size(); i++) {
            if (DataBase.Exist(DuchaInfo.duchas.get(i).getFecha())) {
                try {
                    PreparedStatement statement = conect.prepareStatement(
                    "INSERT INTO Datos_ducha(Fecha, Gasto, Tiempo, Costo) VALUES ('"+DuchaInfo.duchas.get(i).getFecha()+
                            "',"+DuchaInfo.duchas.get(i).getGasto()+",'"+DuchaInfo.duchas.get(i).getTiempoFormat()+
                            "',"+DuchaInfo.duchas.get(i).getCosto()+")");
                    statement.execute();
                } catch (SQLException e) {
                    System.out.println(e);
                    error = true;
                }
            }
        }
        if (error) {
            JOptionPane.showMessageDialog(null, Texto.AV7);
        }
    }
}

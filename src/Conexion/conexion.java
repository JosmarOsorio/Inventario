package Conexion;//Creamos el package conexion para albergar la conexion con la base de datos

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {//Creamos la clase conexion para realizar la conexion con la base de datos
    Connection cx=null;

    public Connection conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            cx=DriverManager.getConnection("jdbc:sqlite:Computadoras.db");
            System.out.println("Conectado");//Realizamos la conexion y pedimos que se emita un mensaje por consola paar confirmar la coenxion
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return cx;
    }

    public void desconectar() { //Creamos un metodo para desconectar con la base de datos
        try {
            cx.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main (String[] arg) {
        conexion cx=new conexion();
        cx.conectar();
    }
}
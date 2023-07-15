package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.conexion;
import Modelo.Computadora;

public class daoComputadora { //Creamos la clase daoComputadora en donde desarrollaremos los metodos

    conexion cx;

    public daoComputadora() {
        cx= new conexion(); //Creamos la variable cx para realizar la conexion con la base de datos

    }

    public boolean insertarComputadora(Computadora compu) { //Creamos el metodo para insertar datos en la base de datos
        PreparedStatement ps=null;
        try {
            ps=cx.conectar().prepareStatement("INSERT INTO Computadoras VALUES(?,?,?)");
            ps.setString(1, compu.getMarca());
            ps.setString(2, compu.getProcesador());
            ps.setString(3, compu.getSistemaoperativo());
            ps.execute();
            cx.desconectar();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList <Computadora> consultaComputadoras(){ //Creamos el metod para consultar datos por pantalla
        ArrayList<Computadora>lista= new ArrayList<Computadora>();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=cx.conectar().prepareStatement("SELECT * From Computadoras");
            rs=ps.executeQuery();
            while(rs.next()) {
                Computadora compu=new Computadora();
                compu.setMarca(rs.getString("Marca"));
                compu.setProcesador(rs.getString("Procesador"));
                compu.setSistemaoperativo(rs.getString("Sistema Operativo"));
                lista.add(compu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean eliminarComputadora(String Marca) { //Creamos el metodo para eliminar datos de la base de datos
        PreparedStatement ps=null;
        try {
            ps=cx.conectar().prepareStatement("DELETE FROM Computadoras WHERE Marca=?");
            ps.setString(1, Marca);
            ps.execute();
            cx.desconectar();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
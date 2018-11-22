/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itesm.fullbox.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Asho
 */
public class Conexion {
    public static Connection getConexion() throws SQLException{
        String cadena = "jdbc:mysql://localhost/fullbox?user=root&serverTimezone=America/Mexico_City";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return DriverManager.getConnection(cadena);
    }
    
    public static void main(String[] args) throws SQLException {
        if(Conexion.getConexion() == null)
            System.out.println("No se conectó");
        else
            System.out.println("Se conectó");
    }
}
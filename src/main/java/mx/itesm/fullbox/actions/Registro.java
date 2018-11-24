/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itesm.fullbox.actions;

import com.mysql.cj.protocol.Resultset;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.itesm.fullbox.utils.Conexion;

/**
 *
 * @author tanya
 */
public class Registro extends ActionSupport {

    private String name, email, pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public String regresar()
    {
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        System.out.print("ENTRE AL EXECUTE");
        if (!email.isEmpty() && !name.isEmpty() && !pass.isEmpty()) {
            System.out.print("ENTRE AL IF");
            try {
                Connection conn = Conexion.getConexion();
                String emailsql = "SELECT * FROM Cuenta WHERE email = ?";
                PreparedStatement pss = conn.prepareStatement(emailsql);
                pss.setString(1, email);
                ResultSet rs = pss.executeQuery();
                if (rs.next()) {
                    System.out.print("ENTRE AL ERRORRRRR");
                    addActionError("Email already taken.");
                    return ERROR;
                } else {
                    String sql = "INSERT INTO Usuarios(nombre) VALUES(?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, name);
                    ps.execute();

                    String sql2 = "SELECT idUsuarios FROM Usuarios WHERE nombre = ?";
                    ps = conn.prepareStatement(sql2);
                    ps.setString(1, name);
                    rs = ps.executeQuery();
                    int idUsuario = 0;
                    while (rs.next()) {
                        idUsuario = rs.getInt("idUsuarios");
                    }
                    String sql3 = "INSERT INTO Cuenta(email, password, fk_idusuarios) VALUES(?,?,?)";
                    ps = conn.prepareStatement(sql3);
                    ps.setString(1, email);
                    ps.setString(2, pass);
                    ps.setInt(3, idUsuario);
                    ps.execute();
                    //el binaryStream almacena bytes, con java obtienes el inputstream.
                    return SUCCESS;
                }
            } catch (Exception ex) {
                Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            addActionError("Fill all the gaps.");
            return ERROR;
        }
        return ERROR;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itesm.fullbox.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mx.itesm.fullbox.utils.Conexion;

/**
 *
 * @author tanya
 */
public class Login extends ActionSupport {
    String your_email, your_pass;
    Map param;

    public Map getParam() {
        return param;
    }

    public void setParam(Map param) {
        this.param = param;
    }
    
    public String getYour_email() {
        return your_email;
    }

    public void setYour_email(String your_email) {
        this.your_email = your_email;
    }

    public String getYour_pass() {
        return your_pass;
    }

    public void setYour_pass(String your_pass) {
        this.your_pass = your_pass;
    }

    @Override
    public String execute() throws Exception {
        String sql = "SELECT * FROM Cuenta WHERE email = ? and password = ?";
        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ValueStack vs = ActionContext.getContext().getValueStack();
            param = new HashMap();
            param.put("ymail", your_email);
            vs.push(param);
            ps.setString(1, your_email);
            ps.setString(2, your_pass);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return SUCCESS;
            }
            else
            {
                addActionError("Email or password incorrect.");
                return ERROR;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ERROR;
    }
    
    
}

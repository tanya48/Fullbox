/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itesm.fullbox.actions;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
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
import mx.itesm.fullbox.utils.Conexion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author tanya
 */
public class GetFiles extends ActionSupport { 
    private List<FileUpload> list = new ArrayList<FileUpload>();  
    private String your_email;

    public String getYour_email() {
        return your_email;
    }

    public void setYour_email(String your_email) {
        this.your_email = your_email;
    }
    
    public List<FileUpload> getList() {
        return list;
    }

    public void setList(List<FileUpload> list) {
        this.list = list;
    }
    @Override
    public String execute() throws Exception {
        try {
            Connection conn = Conexion.getConexion();
            String sql = "SELECT nombre FROM archivo";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ValueStack stack = ActionContext.getContext().getValueStack();
            Map<String, Object> context = new HashMap<String, Object>();
            
            while (rs.next()) {
                FileUpload rc = new FileUpload();
                rc.setFileFileName(rs.getString("nombre"));
                list.add(rc);
            }
            return SUCCESS;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ERROR; //To change body of generated methods, choose Tools | Templates.
    }
    
}

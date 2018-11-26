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
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.itesm.fullbox.actions.Login;
import mx.itesm.fullbox.utils.Conexion;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author tanya
 */
public class FileUpload extends ActionSupport {

    private File file;
    private String fileContentType;
    private String fileFileName;
    private String your_email;

    public String getYour_email() {
        return your_email;
    }

    public void setYour_email(String your_email) {
        this.your_email = your_email;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    @Override
    public String execute() throws Exception {
        
        try {
            Connection conn = Conexion.getConexion();
            String emailsql = "SELECT idcuenta FROM Cuenta WHERE email = ?";
            PreparedStatement pss = conn.prepareStatement(emailsql);
            pss.setString(1, your_email);
            ResultSet rs = pss.executeQuery();
            if (rs.next()){

                int id = rs.getInt("idcuenta");
                String sql = "INSERT INTO archivo(nombre, tipo, extension, tamaño, contenido, link, fk_idcuenta) VALUES(?,?,?,?,?,SHA(?),?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, fileFileName);
                ps.setString(2, fileContentType);
                ps.setString(3, FilenameUtils.getExtension(fileFileName));
                ps.setLong(4, file.length());
                FileInputStream is = new FileInputStream(file);
                ps.setBinaryStream(5, is);
                ps.setString(6, Long.toString(file.length()));
                ps.setInt(7, id);
                ps.execute();
                return SUCCESS;   
            }
        } catch (Exception ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ERROR;
    }
}

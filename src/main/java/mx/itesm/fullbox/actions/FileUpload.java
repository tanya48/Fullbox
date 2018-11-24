/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itesm.fullbox.actions;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;
import mx.itesm.fullbox.utils.Conexion;

/**
 *
 * @author tanya
 */
public class FileUpload extends ActionSupport {

      private File file;
      private String fileContentType;
      private String fileFileName;
      private String fileFileSize;
      private String fileextension;
      private InputStream fileFileData;

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

    public String getFileFileSize() {
        return fileFileSize;
    }

    public void setFileFileSize(String fileFileSize) {
        this.fileFileSize = fileFileSize;
    }

    public String getFileextension() {
        return fileextension;
    }

    public void setFileextension(String fileextension) {
        this.fileextension = fileextension;
    }

    public InputStream getFileFileData() {
        return fileFileData;
    }

    public void setFileFileData(InputStream fileFileData) {
        this.fileFileData = fileFileData;
    }
      
      
      
  
    @Override
    public String execute() throws Exception {
        System.out.print("ENTRE AL EXXXX");
        try {
            
                Connection conn = Conexion.getConexion();
                String emailsql = "SELECT idcuenta FROM Cuenta WHERE email = ?";
                PreparedStatement pss = conn.prepareStatement(emailsql);
                pss.setString(1, "A0122091@itesm.mx");
                ResultSet rs = pss.executeQuery();
                System.out.print("ENTRE AL IDDDD");
                if (rs.next()) {
                    System.out.print("ENTRE AL FFFF");
                    System.out.print(fileFileName);
                    System.out.print("ENTRE AL FFFF");
                    int id = rs.getInt("idcuenta");
                    String sql = "INSERT INTO archivo(nombre, tipo, extension, tamaño, contenido, fk_idfolder, fk_idcuenta) VALUES(?,?,?,?,?,?,?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, fileFileName);
                    ps.setString(2, fileContentType);
                    ps.setString(3, "png");
                    ps.setString(4, fileFileSize);
                    ps.setBinaryStream(5, fileFileData);
                    ps.setInt(6, 1);
                    ps.setInt(7, id);
                    ps.execute();
                    //el binaryStream almacena bytes, con java obtienes el inputstream.
                    return SUCCESS;
                } else {
                   
                    return ERROR;
                }
            } catch (Exception ex) {
                Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
            }
        return ERROR; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacaciones.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import vacaciones.DiasFestivos;

/**
 *
 * @author mxuya
 */
public class DiasFestivosDAO {

    private String LOAD = "exec dbo.listarDiasFestivos ?, ?;";

    private String INSERT_DAY = "EXEC agregarDiasFestivos ?, ?";
    private String UPDATE_DAY = "EXEC modificarColaboradores ?, ?, ?, ?, ?, ?,?,?, ?, ?, ?,?,?,?";

    private String FIND_DAY = "exec diasDisponibles ?;";

    private String DELETE_DAY = "exec colaboradorInactivo ?";
    private String dia;
    private String comentario;

    public void lVars() {
        dia = null;
        comentario = null;
    }

    public void AgregarDia(DiasFestivos diasfestivos, Connection connection) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_DAY)) {
            ps.setString(1, diasfestivos.getDia());
            ps.setString(2, diasfestivos.getComentario());
            ps.execute();
        }
        connection.close();
    }

    public void BuscarDias(Connection connection) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(LOAD)) {
            ps.setString(1, null);
            ps.setString(2, null);
            ps.execute();
        }
        connection.close();
    }
    
        public List<DiasFestivos> ListaDiasFestivos(Connection connection) throws Exception {
        lVars();
        try (PreparedStatement ps = connection.prepareStatement(LOAD)){
            ps.setString(1, null);
            ps.setString(2, null);
            ps.execute();
           
        List<DiasFestivos> diasCol = new ArrayList<DiasFestivos>();
        try (ResultSet rs = ps.executeQuery()) {
            DiasFestivos dias=null;
            diasCol = new ArrayList<DiasFestivos>();
            while (rs.next()) {
                dia = rs.getString("Dia");
                comentario = rs.getString("Comentario");
              
                dias = new DiasFestivos(dia, comentario);
                diasCol.add(dias);
            }
        }
         connection.close();
        return diasCol;            
            
        }
        
        

    }
    
}

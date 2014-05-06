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
import vacaciones.ColaboradoresDisponibles;

/**
 *
 * @author mxuya
 */
public class colaboradoresDisponiblesDAO {
    private String LOAD_ALL = "exec buscarColaborador null;";
    private String LOAD_SOME = "exec buscarColaborador ?;";
    private String LOAD_BY_NAME = "exec buscarColaboradorNombre ?;";
    private String codigo;
    private String nombre;
    private String apellido;
    private String puesto;
    private String clasificacionPuesto;
    private String clasificacionCodigo;
    private String porcentajeDedicacion;
    private String sitio;
    private String proyecto;
    private String subproyecto;
    private String subproyecto2;
    private String observaciones;
    private String otroProyecto;
    private String disponible;

    public void lVars() {
        codigo = null;
        nombre = null;
        apellido = null;
        puesto = null;
        clasificacionPuesto = null;
        clasificacionCodigo = null;
        porcentajeDedicacion = null;
        sitio = null;
        proyecto = null;
        subproyecto = null;
        subproyecto2 = null;
        observaciones = null;
        otroProyecto = null;
        disponible = null;

    }







 

    public List<ColaboradoresDisponibles> listadoDeColaboradores(Connection connection) throws Exception {
        lVars();
        PreparedStatement ps = connection.prepareStatement(LOAD_ALL);
        
        List<ColaboradoresDisponibles> colaboradores = new ArrayList<ColaboradoresDisponibles>();
        try (ResultSet rs = ps.executeQuery()) {
            ColaboradoresDisponibles colaborador=null;
            colaboradores = new ArrayList<ColaboradoresDisponibles>();
            while (rs.next()) {
                codigo = rs.getString("codigo");
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                puesto = rs.getString("puesto");
                clasificacionPuesto = rs.getString("clasificacionPuesto");
                clasificacionCodigo = rs.getString("clasificacionCodigo");
                porcentajeDedicacion = rs.getString("porcentajeDedicacion");
                sitio = rs.getString("sitio");
                proyecto = rs.getString("proyecto");
                subproyecto = rs.getString("subproyecto");
                subproyecto2 = rs.getString("subproyecto2");
                observaciones = rs.getString("observaciones");
                otroProyecto = rs.getString("otroProyecto");
                disponible = rs.getString("disponible");
                colaborador = new ColaboradoresDisponibles(codigo, nombre, apellido, puesto, clasificacionPuesto, clasificacionCodigo, porcentajeDedicacion, sitio, proyecto, subproyecto, subproyecto2, observaciones,otroProyecto,disponible);
                colaboradores.add(colaborador);
            }
        }
        return colaboradores;
    }

    public List<ColaboradoresDisponibles> listarColaboradorNombre(String nombreb, Connection connection) throws Exception {
        lVars();
        ColaboradoresDisponibles colaborador = null;
        PreparedStatement ps = connection.prepareStatement(LOAD_BY_NAME);
        ps.setString(1, nombreb);        
        List<ColaboradoresDisponibles> colaboradores;
        try (ResultSet rs = ps.executeQuery()) {
            colaboradores = new ArrayList<ColaboradoresDisponibles>();
            while (rs.next()) {
                codigo = rs.getString("codigo");
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                puesto = rs.getString("puesto");
                clasificacionPuesto = rs.getString("clasificacionPuesto");
                clasificacionCodigo = rs.getString("clasificacionCodigo");
                porcentajeDedicacion = rs.getString("porcentajeDedicacion");
                sitio = rs.getString("sitio");
                proyecto = rs.getString("proyecto");
                subproyecto = rs.getString("subproyecto");
                subproyecto2 = rs.getString("subproyecto2");
                observaciones = rs.getString("observaciones");
                otroProyecto = rs.getString("otroProyecto");
                disponible = rs.getString("disponible");
                colaborador = new ColaboradoresDisponibles(codigo, nombre, apellido, puesto, clasificacionPuesto, clasificacionCodigo, porcentajeDedicacion, sitio, proyecto, subproyecto, subproyecto2, observaciones,otroProyecto,disponible);
                colaboradores.add(colaborador);
            }
            int rowcount = rs.getRow();
            if (codigo == null) {
                JOptionPane.showMessageDialog(null, "No se han encontrado resultados para su busqueda, verifique el nombre e intente de nuevo.", "ADVERTENCIA", 2);
            }
        }
        return colaboradores;
    }

    public List<ColaboradoresDisponibles> listarColaboradorCodigo(String codigoB, Connection connection) throws Exception {
        lVars();
        ColaboradoresDisponibles colaborador = null;
        //PreparedStatement ps = connection.prepareStatement("select * from colaboradores WHERE codigo = ?;");
        PreparedStatement ps = connection.prepareStatement(LOAD_SOME);
        ps.setString(1, codigoB);
        List<ColaboradoresDisponibles> colaboradores;
        try (ResultSet rs = ps.executeQuery()) {
            colaboradores = new ArrayList<ColaboradoresDisponibles>();
            while (rs.next()) {
                codigo = rs.getString("codigo");
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                puesto = rs.getString("puesto");
                clasificacionPuesto = rs.getString("clasificacionPuesto");
                clasificacionCodigo = rs.getString("clasificacionCodigo");
                porcentajeDedicacion = rs.getString("porcentajeDedicacion");
                sitio = rs.getString("sitio");
                proyecto = rs.getString("proyecto");
                subproyecto = rs.getString("subproyecto");
                subproyecto2 = rs.getString("subproyecto2");
                observaciones = rs.getString("observaciones");
                otroProyecto = rs.getString("otroProyecto");
                disponible = rs.getString("disponible");
                colaborador = new ColaboradoresDisponibles(codigo, nombre, apellido, puesto, clasificacionPuesto, clasificacionCodigo, porcentajeDedicacion, sitio, proyecto, subproyecto, subproyecto2, observaciones,otroProyecto,disponible);
                colaboradores.add(colaborador);
            }
            int rowcount = rs.getRow();
            if (codigo == null) {
                JOptionPane.showMessageDialog(null, "No se han encontrado resultados para su busqueda, verifique el código e intente de nuevo.", "ADVERTENCIA", 2);

            }
        }
        return colaboradores;
    }

  
}

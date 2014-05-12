/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vacaciones.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import vacaciones.Colaboradores;

/**
 *
 * @author mx
 */
public class colaboradoresDAO {

    private String LOAD_ALL = "exec buscarColaborador null;";
    private String LOAD_SOME = "exec buscarColaborador ?;";
    private String SIGN = "exec dbo.ColaboradoresFirman null";
    private String INSERT_PERSON = "EXEC agregarColaboradores ?, ?, ?, ?, ?,?,?, ?, ?, ?,?,?,?,?,?,?";
    private String UPDATE_PERSON = "EXEC modificarColaboradores ?, ?, ?, ?, ?, ?,?,?, ?, ?, ?,?,?,?,?,?,?";
    private String FIND_PERSON_VACATION = "exec RegistroVacaciones ?;";
    private String FIND_PERSON_AVAILABLE_DAY = "exec diasDisponibles ?;";
    private String LOAD_BY_NAME = "exec buscarColaboradorNombre ?;";
    private String DELETE_PERSON = "exec colaboradorInactivo ?";
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
    private String fechaInicioLaboral;
    private String fechaFinalLaboral;
    private String firmaAccion;

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
        fechaInicioLaboral = null;
        fechaFinalLaboral = null;
        firmaAccion = null;

    }

    public void AgregarColaboradores(Colaboradores colaborador, Connection connection) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_PERSON)) {
            ps.setString(1, colaborador.getCodigo());
            ps.setString(2, colaborador.getNombre());
            ps.setString(3, colaborador.getApellido());
            ps.setString(4, colaborador.getPuesto());
            ps.setString(5, colaborador.getClasificacionPuesto());
            ps.setString(6, colaborador.getClasificacionCodigo());
            ps.setString(7, colaborador.getPorcentajeDedicacion());
            ps.setString(8, colaborador.getSitio());
            ps.setString(9, colaborador.getProyecto());
            ps.setString(10, colaborador.getSubproyecto());
            ps.setString(11, colaborador.getSubproyecto2());
            ps.setString(12, colaborador.getObservaciones());
            ps.setString(13, colaborador.getOtroProyecto());
            ps.setString(14, colaborador.getFechaInicioLaboral());
            ps.setString(15, colaborador.getFechaFinalLaboral());
            ps.setString(16, colaborador.getFirmaAccion());
            ps.execute();
        }
        connection.close();
    }

    public void ModificarColaboradores(Colaboradores colaborador, String codigoOld, Connection connection) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PERSON)) {
            ps.setString(1, colaborador.getCodigo());
            ps.setString(2, codigoOld);
            ps.setString(3, colaborador.getNombre());
            ps.setString(4, colaborador.getApellido());
            ps.setString(5, colaborador.getPuesto());
            ps.setString(6, colaborador.getClasificacionPuesto());
            ps.setString(7, colaborador.getClasificacionCodigo());
            ps.setString(8, colaborador.getPorcentajeDedicacion());
            ps.setString(9, colaborador.getSitio());
            ps.setString(10, colaborador.getProyecto());
            ps.setString(11, colaborador.getSubproyecto());
            ps.setString(12, colaborador.getSubproyecto2());
            ps.setString(13, colaborador.getObservaciones());
            ps.setString(14, colaborador.getOtroProyecto());
            ps.setString(15, colaborador.getFechaInicioLaboral());
            ps.setString(16, colaborador.getFechaFinalLaboral());
            ps.setString(17, colaborador.getFirmaAccion());
            ps.execute();
        }
        connection.close();
    }

    public void BuscarColaboradoresAgregarVacaciones(String codigo, Connection connection) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(FIND_PERSON_VACATION)) {
            ps.setString(1, codigo);
            ps.execute();
        }
        connection.close();
    }

    public int BuscarColaboradoresDiasDisponibles(String codigo, Connection connection) throws Exception {
        int diasDisponibles;
        try (PreparedStatement ps = connection.prepareStatement(FIND_PERSON_AVAILABLE_DAY)) {
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            diasDisponibles = 0;
            while (rs.next()) {
                diasDisponibles = rs.getInt("diasDisponibles");
            }
        }
        connection.close();
        return diasDisponibles;
    }

    public List<Colaboradores> listadoDeColaboradores(Connection connection) throws Exception {
        lVars();
        PreparedStatement ps = connection.prepareStatement(LOAD_ALL);

        List<Colaboradores> colaboradores = new ArrayList<Colaboradores>();
        try (ResultSet rs = ps.executeQuery()) {
            Colaboradores colaborador = null;
            colaboradores = new ArrayList<Colaboradores>();
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
                fechaInicioLaboral = rs.getString("fechaInicioLaboral");
                fechaFinalLaboral = rs.getString("fechaFinalLaboral");
                firmaAccion = rs.getString("firmaAccion");
                //disponible = rs.getString("disponible");
                colaborador = new Colaboradores(codigo, nombre, apellido, puesto, clasificacionPuesto, clasificacionCodigo, porcentajeDedicacion, sitio, proyecto, subproyecto, subproyecto2, observaciones, otroProyecto, fechaInicioLaboral, fechaFinalLaboral, firmaAccion);
                colaboradores.add(colaborador);
            }
        }
        return colaboradores;
    }

     public List<Colaboradores> listadoDeFirmantes(Connection connection) throws Exception {
        lVars();
        PreparedStatement ps = connection.prepareStatement(SIGN);

        List<Colaboradores> colaboradores = new ArrayList<Colaboradores>();
        try (ResultSet rs = ps.executeQuery()) {
            Colaboradores colaborador = null;
            colaboradores = new ArrayList<Colaboradores>();
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
                fechaInicioLaboral = rs.getString("fechaInicioLaboral");
                fechaFinalLaboral = rs.getString("fechaFinalLaboral");
                firmaAccion = rs.getString("firmaAccion");
                //disponible = rs.getString("disponible");
                colaborador = new Colaboradores(codigo, nombre, apellido, puesto, clasificacionPuesto, clasificacionCodigo, porcentajeDedicacion, sitio, proyecto, subproyecto, subproyecto2, observaciones, otroProyecto, fechaInicioLaboral, fechaFinalLaboral, firmaAccion);
                colaboradores.add(colaborador);
            }
        }
        return colaboradores;
    }    

    public List<Colaboradores> listarColaboradorNombre(String nombreb, Connection connection) throws Exception {
        lVars();
        Colaboradores colaborador = null;
        PreparedStatement ps = connection.prepareStatement(LOAD_BY_NAME);
        ps.setString(1, nombreb);
        List<Colaboradores> colaboradores;
        try (ResultSet rs = ps.executeQuery()) {
            colaboradores = new ArrayList<Colaboradores>();
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
                fechaInicioLaboral = rs.getString("fechaInicioLaboral");
                fechaFinalLaboral = rs.getString("fechaFinalLaboral");
                firmaAccion = rs.getString("firmaAccion");
                //disponible = rs.getString("disponible");
                colaborador = new Colaboradores(codigo, nombre, apellido, puesto, clasificacionPuesto, clasificacionCodigo, porcentajeDedicacion, sitio, proyecto, subproyecto, subproyecto2, observaciones, otroProyecto, fechaInicioLaboral, fechaFinalLaboral, firmaAccion);
                colaboradores.add(colaborador);
            }
            int rowcount = rs.getRow();
            if (codigo == null) {
                JOptionPane.showMessageDialog(null, "No se han encontrado resultados para su busqueda, verifique el nombre e intente de nuevo.", "ADVERTENCIA", 2);
            }
        }
        return colaboradores;
    }

    public List<Colaboradores> listarColaboradorCodigo(String codigoB, Connection connection) throws Exception {
        lVars();
        Colaboradores colaborador = null;
        //PreparedStatement ps = connection.prepareStatement("select * from colaboradores WHERE codigo = ?;");
        PreparedStatement ps = connection.prepareStatement(LOAD_SOME);
        ps.setString(1, codigoB);
        List<Colaboradores> colaboradores;
        try (ResultSet rs = ps.executeQuery()) {
            colaboradores = new ArrayList<Colaboradores>();
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
                fechaInicioLaboral = rs.getString("fechaInicioLaboral");
                fechaFinalLaboral = rs.getString("fechaFinalLaboral");
                firmaAccion = rs.getString("firmaAccion");
                //disponible = rs.getString("disponible");
                colaborador = new Colaboradores(codigo, nombre, apellido, puesto, clasificacionPuesto, clasificacionCodigo, porcentajeDedicacion, sitio, proyecto, subproyecto, subproyecto2, observaciones, otroProyecto, fechaInicioLaboral, fechaFinalLaboral, firmaAccion);
                colaboradores.add(colaborador);
            }
            int rowcount = rs.getRow();
            if (codigo == null) {
                JOptionPane.showMessageDialog(null, "No se han encontrado resultados para su busqueda, verifique el código e intente de nuevo.", "ADVERTENCIA", 2);

            }
        }
        return colaboradores;
    }

    public void eliminarColaborador(int codigo, Connection connection) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_PERSON)) {
            ps.setInt(1, codigo);
            ps.execute();
        }
        connection.close();
    }
}

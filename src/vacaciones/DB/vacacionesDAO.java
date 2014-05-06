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
import vacaciones.Vacaciones;

/**
 *
 * @author mx
 */
public class vacacionesDAO {

    private String INSERT_ACTION = "EXEC agregarAccionPersonal ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?;";
    private String LOAD_SOME = "select * from accionPersonalPlanilla where CodigoEmpleado = ?;";// and fechaEmision = ? and vacacionesAl = ?;";
    private String UPDATE = "update accionPersonalPlanilla where CodigoEmpleado = ? and fechaEmision = ? and fechaEfectivo = ? and vacacionesAl = ?;";
    private String fechaEmision;
    private String fechaEfectivo;
    private String emitidoPor;
    private int codigoEmpleado;
    private String gradoEscolar;
    private String nit;
    private String igss;
    private String area;
    private String facultad;
    private String departamento;
    private String nombreNuevoPuesto;
    private String nombreNuevaArea;
    private String nombreNuevaFacultad;
    private String nombreNuevoDepartamento;
    private float sueldoMensual;
    private float bonificacion;
    private float totalPagar;
    private String partidaPresupuestaria;
    private float sueldoModificado;
    private float bonificacionModificada;
    private float totalPagarModificado;
    private String partidaPresupuestariaModificada;
    private String horario;
    private String diasLaboradosSemana5dias;
    private String diasLaboradosSabado;
    private String codigoCursos;
    private String nombreCurso;
    private String periodosImpartidos;
    private String tipoContratacion;
    private String valorCurso;
    private String valorCursoTotal;
    private String vacacionesDel;
    private String vacacionesAl;
    private String vacacionesDel2;
    private String vacacionesAl2;
    private int díasVacaciones;
    private String regresaLaborar;
    private int retiro;
    private String observaciones;
    private String proyectoEncargado;
    private String proyectoDirector;
    private String dap;
    private int tipoColaborador;
    private int tipoDocumento;
    private int tipoCampus;

    public void AgregarColaboradores(Vacaciones vacacion, Connection connection) throws Exception {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_ACTION)) {



            ps.setString(1, vacacion.getFechaEmision());

            ps.setString(2, vacacion.getFechaEfectivo());


            ps.setString(3, vacacion.getEmitidoPor());
            ps.setInt(4, vacacion.getCodigoEmpleado());
            ps.setString(5, vacacion.getGradoEscolar());
            ps.setString(6, vacacion.getNit());
            ps.setString(7, vacacion.getIgss());
            ps.setString(8, vacacion.getArea());
            ps.setString(9, vacacion.getFacultad());
            ps.setString(10, vacacion.getDepartamento());
            ps.setString(11, vacacion.getNombreNuevoPuesto());
            ps.setString(12, vacacion.getNombreNuevaArea());
            ps.setString(13, vacacion.getNombreNuevaFacultad());
            ps.setString(14, vacacion.getNombreNuevoDepartamento());
            ps.setFloat(15, vacacion.getSueldoMensual());
            ps.setFloat(16, vacacion.getBonificacion());
            ps.setFloat(17, vacacion.getTotalPagar());
            ps.setString(18, vacacion.getPartidaPresupuestaria());
            ps.setFloat(19, vacacion.getSueldoModificado());
            ps.setFloat(20, vacacion.getBonificacionModificada());
            ps.setFloat(21, vacacion.getTotalPagarModificado());
            ps.setString(22, vacacion.getPartidaPresupuestariaModificada());
            ps.setString(23, vacacion.getHorario());
            ps.setString(24, vacacion.getDiasLaboradosSemana5dias());
            ps.setString(25, vacacion.getDiasLaboradosSabado());
            ps.setString(26, vacacion.getCodigoCursos());
            ps.setString(27, vacacion.getNombreCurso());
            ps.setString(28, vacacion.getPeriodosImpartidos());
            ps.setString(29, vacacion.getTipoContratacion());
            ps.setString(30, vacacion.getValorCurso());
            ps.setString(31, vacacion.getValorCursoTotal());
            
            
            ps.setString(32, vacacion.getVacacionesDel());
            

            ps.setString(33, vacacion.getVacacionesAl());
            
            String noString = null;
            if (vacacion.getVacacionesDel2() == null) {
                ps.setString(34, noString);
            } else {

                ps.setString(34, vacacion.getVacacionesDel2());
            }

            if (vacacion.getVacacionesAl2() == null) {
                ps.setString(35, noString);
            } else {

                ps.setString(35, vacacion.getVacacionesAl2());
            }




            ps.setInt(36, vacacion.getDíasVacaciones());
            
            

            
            ps.setString(37, vacacion.getRegresaLaborar());
            
            
            ps.setInt(38, vacacion.getRetiro());
            ps.setString(39, vacacion.getObservaciones());
            ps.setString(40, vacacion.getProyectoEncargado());
            ps.setString(41, vacacion.getProyectoDirector());
            ps.setString(42, vacacion.getDap());
            ps.setInt(43, vacacion.getTipoColaborador());
            ps.setInt(44, vacacion.getTipoDocumento());
            ps.setInt(45, vacacion.getTipoCampus());
            ps.execute();
        }
        connection.close();
    }

    //public List<Vacaciones> listarAccionCodigo(String codigoB,String emision, String vacAl, Connection connection) throws Exception {
    public List<Vacaciones> listarAccionCodigo(String codigoB, Connection connection) throws Exception {


        //PreparedStatement ps = connection.prepareStatement("select * from colaboradores WHERE codigo = ?;");
        PreparedStatement ps = connection.prepareStatement(LOAD_SOME);
        ps.setString(1, codigoB);
        //ps.setString(2,emision);
        //ps.setString(3,vacAl);
        List<Vacaciones> vacaciones;
        try (ResultSet rs = ps.executeQuery()) {
            Vacaciones vacacion = null;
            vacaciones = new ArrayList<Vacaciones>();
            while (rs.next()) {
                fechaEmision = rs.getString("fechaEmision");
                fechaEfectivo = rs.getString("fechaEfectivo");
                emitidoPor = rs.getString("emitidoPor");
                codigoEmpleado = rs.getInt("codigoEmpleado");
                gradoEscolar = rs.getString("gradoEscolar");
                nit = rs.getString("nit");
                igss = rs.getString("igss");
                area = rs.getString("area");
                facultad = rs.getString("facultad");
                departamento = rs.getString("departamento");
                nombreNuevoPuesto = rs.getString("nombreNuevoPuesto");
                nombreNuevaArea = rs.getString("nombreNuevaArea");
                nombreNuevaFacultad = rs.getString("nombreNuevaFacultad");
                nombreNuevoDepartamento = rs.getString("nombreNuevoDepartamento");
                sueldoMensual = rs.getFloat("sueldoMensual");
                bonificacion = rs.getFloat("bonificacion");
                totalPagar = rs.getFloat("totalPagar");
                partidaPresupuestaria = rs.getString("partidaPresupuestaria");
                sueldoModificado = rs.getFloat("sueldoModificado");
                bonificacionModificada = rs.getFloat("bonificacionModificada");
                totalPagarModificado = rs.getFloat("totalPagarModificado");
                partidaPresupuestariaModificada = rs.getString("partidaPresupuestariaModificada");
                horario = rs.getString("horario");
                diasLaboradosSemana5dias = rs.getString("diasLaboradosSemana5dias");
                diasLaboradosSabado = rs.getString("diasLaboradosSabado");
                codigoCursos = rs.getString("codigoCursos");
                nombreCurso = rs.getString("nombreCurso");
                periodosImpartidos = rs.getString("periodosImpartidos");
                tipoContratacion = rs.getString("tipoContratacion");
                valorCurso = rs.getString("valorCurso");
                valorCursoTotal = rs.getString("valorCursoTotal");
                vacacionesDel = rs.getString("vacacionesDel");
                vacacionesAl = rs.getString("vacacionesAl");
                vacacionesDel2 = rs.getString("vacacionesDel2");
                vacacionesAl2 = rs.getString("vacacionesAl2");
                díasVacaciones = rs.getInt("díasVacaciones");
                regresaLaborar = rs.getString("regresaLaborar");
                retiro = rs.getInt("retiro");
                observaciones = rs.getString("observaciones");
                proyectoEncargado = rs.getString("proyectoEncargado");
                proyectoDirector = rs.getString("proyectoDirector");
                dap = rs.getString("dap");
                tipoColaborador = rs.getInt("tipoColaborador");
                tipoDocumento = rs.getInt("tipoDocumento");
                tipoCampus = rs.getInt("tipoCampus");

                vacacion = new Vacaciones(fechaEmision, fechaEfectivo, emitidoPor, codigoEmpleado, gradoEscolar, nit, igss, area, facultad, departamento, nombreNuevoPuesto, nombreNuevaArea, nombreNuevaFacultad, nombreNuevoDepartamento, sueldoMensual, bonificacion, totalPagar, partidaPresupuestaria, sueldoModificado, bonificacionModificada, totalPagarModificado, partidaPresupuestariaModificada, horario, diasLaboradosSemana5dias, diasLaboradosSabado, codigoCursos, nombreCurso, periodosImpartidos, tipoContratacion, valorCurso, valorCursoTotal, vacacionesDel, vacacionesAl, vacacionesDel2, vacacionesAl2, díasVacaciones, regresaLaborar, retiro, observaciones, proyectoEncargado, proyectoDirector, dap, tipoColaborador, tipoDocumento, tipoCampus);
                vacaciones.add(vacacion);
            }
        }
        return vacaciones;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vacaciones;



/**
 *
 * @author mx
 */
public class Vacaciones {

    String fechaEmision;
    String fechaEfectivo;
    String emitidoPor;
    int codigoEmpleado;
    String gradoEscolar;
    String nit;
    String igss;
    String area;
    String facultad;
    String departamento;
    String nombreNuevoPuesto;
    String nombreNuevaArea;
    String nombreNuevaFacultad;
    String nombreNuevoDepartamento;
    float sueldoMensual;
    float bonificacion;
    float totalPagar;
    String partidaPresupuestaria;
    float sueldoModificado;
    float bonificacionModificada;
    float totalPagarModificado;
    String partidaPresupuestariaModificada;
    String horario;
    String diasLaboradosSemana5dias;
    String diasLaboradosSabado;
    String codigoCursos;
    String nombreCurso;
    String periodosImpartidos;
    String tipoContratacion;
    String valorCurso;
    String valorCursoTotal;
    String vacacionesDel;
    String vacacionesAl;
    String vacacionesDel2;
    String vacacionesAl2;
    int díasVacaciones;
    String regresaLaborar;
    int retiro;
    String observaciones;
    String proyectoEncargado;
    String proyectoDirector;
    String dap;
    int tipoColaborador;
    int tipoDocumento;
    int tipoCampus;

    public Vacaciones(
            String fechaEmision,
            String fechaEfectivo,
            String emitidoPor,
            int codigoEmpleado,
            String gradoEscolar,
            String nit,
            String igss,
            String area,
            String facultad,
            String departamento,
            String nombreNuevoPuesto,
            String nombreNuevaArea,
            String nombreNuevaFacultad,
            String nombreNuevoDepartamento,
            float sueldoMensual,
            float bonificacion,
            float totalPagar,
            String partidaPresupuestaria,
            float sueldoModificado,
            float bonificacionModificada,
            float totalPagarModificado,
            String partidaPresupuestariaModificada,
            String horario,
            String diasLaboradosSemana5dias,
            String diasLaboradosSabado,
            String codigoCursos,
            String nombreCurso,
            String periodosImpartidos,
            String tipoContratacion,
            String valorCurso,
            String valorCursoTotal,
            String vacacionesDel,
            String vacacionesAl,
            String vacacionesDel2,
            String vacacionesAl2,
            int díasVacaciones,
            String regresaLaborar,
            int retiro,
            String observaciones,
            String proyectoEncargado,
            String proyectoDirector,
            String dap,
            int tipoColaborador,
            int tipoDocumento,
            int tipoCampus) {
        this.fechaEmision = fechaEmision;
        this.fechaEfectivo = fechaEfectivo;
        this.emitidoPor = emitidoPor;
        this.codigoEmpleado = codigoEmpleado;
        this.gradoEscolar = gradoEscolar;
        this.nit = nit;
        this.igss = igss;
        this.area = area;
        this.facultad = facultad;
        this.departamento = departamento;
        this.nombreNuevoPuesto = nombreNuevoPuesto;
        this.nombreNuevaArea = nombreNuevaArea;
        this.nombreNuevaFacultad = nombreNuevaFacultad;
        this.nombreNuevoDepartamento = nombreNuevoDepartamento;
        this.sueldoMensual = sueldoMensual;
        this.bonificacion = bonificacion;
        this.totalPagar = totalPagar;
        this.partidaPresupuestaria = partidaPresupuestaria;
        this.sueldoModificado = sueldoModificado;
        this.bonificacionModificada = bonificacionModificada;
        this.totalPagarModificado = totalPagarModificado;
        this.partidaPresupuestariaModificada = partidaPresupuestariaModificada;
        this.horario = horario;
        this.diasLaboradosSemana5dias = diasLaboradosSemana5dias;
        this.diasLaboradosSabado = diasLaboradosSabado;
        this.codigoCursos = codigoCursos;
        this.nombreCurso = nombreCurso;
        this.periodosImpartidos = periodosImpartidos;
        this.tipoContratacion = tipoContratacion;
        this.valorCurso = valorCurso;
        this.valorCursoTotal = valorCursoTotal;
        this.vacacionesDel = vacacionesDel;
        this.vacacionesAl = vacacionesAl;
        this.vacacionesDel2 = vacacionesDel2;
        this.vacacionesAl2 = vacacionesAl2;
        this.díasVacaciones = díasVacaciones;
        this.regresaLaborar = regresaLaborar;
        this.retiro = retiro;
        this.observaciones = observaciones;
        this.proyectoEncargado = proyectoEncargado;
        this.proyectoDirector = proyectoDirector;
        this.dap = dap;
        this.tipoColaborador = tipoColaborador;
        this.tipoDocumento = tipoDocumento;
        this.tipoCampus = tipoCampus;

    }
      
    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaEfectivo() {
        return fechaEfectivo;
    }

    public void setFechaEfectivo(String fechaEfectivo) {
        this.fechaEfectivo = fechaEfectivo;
    }

    public String getEmitidoPor() {
        return emitidoPor;
    }

    public void setEmitidoPor(String emitidoPor) {
        this.emitidoPor = emitidoPor;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getGradoEscolar() {
        return gradoEscolar;
    }

    public void setGradoEscolar(String gradoEscolar) {
        this.gradoEscolar = gradoEscolar;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIgss() {
        return igss;
    }

    public void setIgss(String igss) {
        this.igss = igss;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getNombreNuevoPuesto() {
        return nombreNuevoPuesto;
    }

    public void setNombreNuevoPuesto(String nombreNuevoPuesto) {
        this.nombreNuevoPuesto = nombreNuevoPuesto;
    }

    public String getNombreNuevaArea() {
        return nombreNuevaArea;
    }

    public void setNombreNuevaArea(String nombreNuevaArea) {
        this.nombreNuevaArea = nombreNuevaArea;
    }

    public String getNombreNuevaFacultad() {
        return nombreNuevaFacultad;
    }

    public void setNombreNuevaFacultad(String nombreNuevaFacultad) {
        this.nombreNuevaFacultad = nombreNuevaFacultad;
    }

    public String getNombreNuevoDepartamento() {
        return nombreNuevoDepartamento;
    }

    public void setNombreNuevoDepartamento(String nombreNuevoDepartamento) {
        this.nombreNuevoDepartamento = nombreNuevoDepartamento;
    }

    public float getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(float sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    public float getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(float bonificacion) {
        this.bonificacion = bonificacion;
    }

    public float getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(float totalPagar) {
        this.totalPagar = totalPagar;
    }

    public String getPartidaPresupuestaria() {
        return partidaPresupuestaria;
    }

    public void setPartidaPresupuestaria(String partidaPresupuestaria) {
        this.partidaPresupuestaria = partidaPresupuestaria;
    }

    public float getSueldoModificado() {
        return sueldoModificado;
    }

    public void setSueldoModificado(float sueldoModificado) {
        this.sueldoModificado = sueldoModificado;
    }

    public float getBonificacionModificada() {
        return bonificacionModificada;
    }

    public void setBonificacionModificada(float bonificacionModificada) {
        this.bonificacionModificada = bonificacionModificada;
    }

    public float getTotalPagarModificado() {
        return totalPagarModificado;
    }

    public void setTotalPagarModificado(float totalPagarModificado) {
        this.totalPagarModificado = totalPagarModificado;
    }

    public String getPartidaPresupuestariaModificada() {
        return partidaPresupuestariaModificada;
    }

    public void setPartidaPresupuestariaModificada(String partidaPresupuestariaModificada) {
        this.partidaPresupuestariaModificada = partidaPresupuestariaModificada;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDiasLaboradosSemana5dias() {
        return diasLaboradosSemana5dias;
    }

    public void setDiasLaboradosSemana5dias(String diasLaboradosSemana5dias) {
        this.diasLaboradosSemana5dias = diasLaboradosSemana5dias;
    }

    public String getDiasLaboradosSabado() {
        return diasLaboradosSabado;
    }

    public void setDiasLaboradosSabado(String diasLaboradosSabado) {
        this.diasLaboradosSabado = diasLaboradosSabado;
    }

    public String getCodigoCursos() {
        return codigoCursos;
    }

    public void setCodigoCursos(String codigoCursos) {
        this.codigoCursos = codigoCursos;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getPeriodosImpartidos() {
        return periodosImpartidos;
    }

    public void setPeriodosImpartidos(String periodosImpartidos) {
        this.periodosImpartidos = periodosImpartidos;
    }

    public String getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public String getValorCurso() {
        return valorCurso;
    }

    public void setValorCurso(String valorCurso) {
        this.valorCurso = valorCurso;
    }

    public String getValorCursoTotal() {
        return valorCursoTotal;
    }

    public void setValorCursoTotal(String valorCursoTotal) {
        this.valorCursoTotal = valorCursoTotal;
    }

    public String getVacacionesDel() {
        return vacacionesDel;
    }

    public void setVacacionesDel(String vacacionesDel) {
        this.vacacionesDel = vacacionesDel;
    }

    public String getVacacionesAl() {
        return vacacionesAl;
    }

    public void setVacacionesAl(String vacacionesAl) {
        this.vacacionesAl = vacacionesAl;
    }

    public String getVacacionesDel2() {
        return vacacionesDel2;
    }

    public void setVacacionesDel2(String vacacionesDel2) {
        this.vacacionesDel2 = vacacionesDel2;
    }

    public String getVacacionesAl2() {
        return vacacionesAl2;
    }

    public void setVacacionesAl2(String vacacionesAl2) {
        this.vacacionesAl2 = vacacionesAl2;
    }

    public int getDíasVacaciones() {
        return díasVacaciones;
    }

    public void setDíasVacaciones(int díasVacaciones) {
        this.díasVacaciones = díasVacaciones;
    }

    public String getRegresaLaborar() {
        return regresaLaborar;
    }

    public void setRegresaLaborar(String regresaLaborar) {
        this.regresaLaborar = regresaLaborar;
    }

    public int getRetiro() {
        return retiro;
    }

    public void setRetiro(int retiro) {
        this.retiro = retiro;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getProyectoEncargado() {
        return proyectoEncargado;
    }

    public void setProyectoEncargado(String proyectoEncargado) {
        this.proyectoEncargado = proyectoEncargado;
    }

    public String getProyectoDirector() {
        return proyectoDirector;
    }

    public void setProyectoDirector(String proyectoDirector) {
        this.proyectoDirector = proyectoDirector;
    }

    public String getDap() {
        return dap;
    }

    public void setDap(String dap) {
        this.dap = dap;
    }

    public int getTipoColaborador() {
        return tipoColaborador;
    }

    public void setTipoColaborador(int tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getTipoCampus() {
        return tipoCampus;
    }

    public void setTipoCampus(int tipoCampus) {
        this.tipoCampus = tipoCampus;
    }

}
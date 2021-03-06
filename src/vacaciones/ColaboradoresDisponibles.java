/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacaciones;

/**
 *
 * @author mxuya
 */
public class ColaboradoresDisponibles {

    String codigo;
    String nombre;
    String apellido;
    String puesto;
    String clasificacionPuesto;
    String clasificacionCodigo;
    String porcentajeDedicacion;
    String sitio;
    String proyecto;
    String subproyecto;
    String subproyecto2;
    String observaciones;
    String otroProyecto;
    String disponible;
    String fechaInicioLaboral;
    String fechaFinalLaboral;
    String firmaAccion;

    public ColaboradoresDisponibles(String codigo, String nombre, String apellido, String puesto, String clasificacionPuesto, String clasificacionCodigo, String porcentajeDedicacion, String sitio, String proyecto, String subproyecto, String subproyecto2, String observaciones, String otroProyecto, String disponible,String fechaInicioLaboral, String fechaFinalLaboral,String firmaAccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.clasificacionPuesto = clasificacionPuesto;
        this.clasificacionCodigo = clasificacionCodigo;
        this.porcentajeDedicacion = porcentajeDedicacion;
        this.sitio = sitio;
        this.proyecto = proyecto;
        this.subproyecto = subproyecto;
        this.subproyecto2 = subproyecto2;
        this.observaciones = observaciones;
        this.otroProyecto = otroProyecto;
        this.disponible = disponible;
        this.fechaInicioLaboral = fechaInicioLaboral;
        this.fechaFinalLaboral = fechaFinalLaboral;
        this.firmaAccion = firmaAccion;
    }

    public ColaboradoresDisponibles(String nombre, String apellido, String puesto, String clasificacionPuesto, String clasificacionCodigo, String porcentajeDedicacion, String sitio, String proyecto, String subproyecto, String subproyecto2, String observaciones, String otroProyecto,String disponible,String fechaInicioLaboral, String fechaFinalLaboral,String firmaAccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.clasificacionPuesto = clasificacionPuesto;
        this.clasificacionCodigo = clasificacionCodigo;
        this.porcentajeDedicacion = porcentajeDedicacion;
        this.sitio = sitio;
        this.proyecto = proyecto;
        this.subproyecto = subproyecto;
        this.subproyecto2 = subproyecto2;
        this.observaciones = observaciones;
        this.otroProyecto = otroProyecto;
        this.disponible = disponible;
        this.fechaInicioLaboral = fechaInicioLaboral;
        this.fechaFinalLaboral = fechaFinalLaboral;
        this.firmaAccion = firmaAccion;

    }

    public String getFechaInicioLaboral() {
        return fechaInicioLaboral;
    }

    public void setFechaInicioLaboral(String fechaInicioLaboral) {
        this.fechaInicioLaboral = fechaInicioLaboral;
    }

    public String getFechaFinalLaboral() {
        return fechaFinalLaboral;
    }

    public void setFechaFinalLaboral(String fechaFinalLaboral) {
        this.fechaFinalLaboral = fechaFinalLaboral;
    }

    public String getFirmaAccion() {
        return firmaAccion;
    }

    public void setFirmaAccion(String firmaAccion) {
        this.firmaAccion = firmaAccion;
    }
       
    public String getDisponible() {
        return disponible;
    }

    public String getOtroProyecto() {
        return otroProyecto;
    }

    public void setOtroProyecto(String otroProyecto) {
        this.otroProyecto = otroProyecto;
    }

    public String getClasificacionCodigo() {
        return clasificacionCodigo;
    }

    public void setClasificacionCodigo(String clasificacionCodigo) {
        this.clasificacionCodigo = clasificacionCodigo;
    }

    public String getSubproyecto2() {
        return subproyecto2;
    }

    public void setSubproyecto2(String subproyecto2) {
        this.subproyecto2 = subproyecto2;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getClasificacionPuesto() {
        return clasificacionPuesto;
    }

    public void setClasificacionPuesto(String clasificacionPuesto) {
        this.clasificacionPuesto = clasificacionPuesto;
    }

    public String getPorcentajeDedicacion() {
        return porcentajeDedicacion;
    }

    public void setPorcentajeDedicacion(String porcentajeDedicacion) {
        this.porcentajeDedicacion = porcentajeDedicacion;
    }

    public String getSitio() {
        return sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getSubproyecto() {
        return subproyecto;
    }

    public void setSubproyecto(String subproyecto) {
        this.subproyecto = subproyecto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

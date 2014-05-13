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
public class VacacionesMod {
      
    
    
    int codigoEmpleadoMod;    
    String fechaEmisionMod;
    String vacacionesAlMod;
    
    String fechaEmision;
    int díasVacaciones;
    String observaciones;
    String vacacionesDel;
    String vacacionesAl;
    String vacacionesDel2;
    String vacacionesAl2;
    int tipoCampus;
    int tipoColaborador;
    int tipoDocumento;    
    String emitidoPor;    
    String regresaLaborar;

    public VacacionesMod(

    int codigoEmpleadoMod,
    String fechaEmisionMod,
    String vacacionesAlMod,
    
    String fechaEmision,
    int díasVacaciones,
    String observaciones,
    String vacacionesDel,
    String vacacionesAl,
    String vacacionesDel2,
    String vacacionesAl2,
    int tipoCampus,
    int tipoColaborador,
    int tipoDocumento,  
    String emitidoPor,    
    String regresaLaborar
            
) {
        
        this.codigoEmpleadoMod = codigoEmpleadoMod;
        this.fechaEmisionMod = fechaEmisionMod;
        this.vacacionesAlMod = vacacionesAlMod;

        this.fechaEmision = fechaEmision;       
        this.emitidoPor = emitidoPor;       
        this.vacacionesDel = vacacionesDel;
        this.vacacionesAl = vacacionesAl;
        this.vacacionesDel2 = vacacionesDel2;
        this.vacacionesAl2 = vacacionesAl2;
        this.díasVacaciones = díasVacaciones;
        this.regresaLaborar = regresaLaborar;       
        this.observaciones = observaciones;       
        this.tipoColaborador = tipoColaborador;
        this.tipoDocumento = tipoDocumento;
        this.tipoCampus = tipoCampus;

    }    

    public int getCodigoEmpleadoMod() {
        return codigoEmpleadoMod;
    }

    public void setCodigoEmpleadoMod(int codigoEmpleadoMod) {
        this.codigoEmpleadoMod = codigoEmpleadoMod;
    }

    public String getFechaEmisionMod() {
        return fechaEmisionMod;
    }

    public void setFechaEmisionMod(String fechaEmisionMod) {
        this.fechaEmisionMod = fechaEmisionMod;
    }

    public String getVacacionesAlMod() {
        return vacacionesAlMod;
    }

    public void setVacacionesAlMod(String vacacionesAlMod) {
        this.vacacionesAlMod = vacacionesAlMod;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public int getDíasVacaciones() {
        return díasVacaciones;
    }

    public void setDíasVacaciones(int díasVacaciones) {
        this.díasVacaciones = díasVacaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public int getTipoCampus() {
        return tipoCampus;
    }

    public void setTipoCampus(int tipoCampus) {
        this.tipoCampus = tipoCampus;
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

    public String getEmitidoPor() {
        return emitidoPor;
    }

    public void setEmitidoPor(String emitidoPor) {
        this.emitidoPor = emitidoPor;
    }

    public String getRegresaLaborar() {
        return regresaLaborar;
    }

    public void setRegresaLaborar(String regresaLaborar) {
        this.regresaLaborar = regresaLaborar;
    }
    
    
}

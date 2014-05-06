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
public class DiasFestivos {
    String dia;
    String comentario;

    

    
    

    public DiasFestivos(String dia, String comentario) {
        this.dia = dia;
        this.comentario = comentario;

    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    

    

}

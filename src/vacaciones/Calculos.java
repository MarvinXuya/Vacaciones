/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vacaciones;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

/**
 *
 * @author mx
 */
public class Calculos {

    /**
     *
     * @param s
     * @return
     */
    public Calculos() {
    }

    /**
     *
     * @param s
     * @return
     */
    public static boolean isInt(String s) {
        try {
            int i = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException er) {
            return false;
        }

    }

    /**
     *
     * @param inicio
     * @param ultimo
     * @return
     */
    public static int EntreFechas(java.util.Date inicio, java.util.Date ultimo) {

//        JOptionPane.showMessageDialog(null, f1);
//        JOptionPane.showMessageDialog(null, f2);
        long diff1 = ultimo.getTime() - inicio.getTime();
        int diasDiff = (int) ((TimeUnit.DAYS.convert(diff1, TimeUnit.MILLISECONDS)) + 1);

        // Day of the week        
        GregorianCalendar gcal1 = new GregorianCalendar();
        gcal1.setTime(inicio);
        int quitarFinSemana = 0;
        while (gcal1.getTime().before(ultimo)) {
            // Sabado = 6; Domingo = 0
            if (gcal1.getTime().getDay() == 6 || gcal1.getTime().getDay() == 0) {
                quitarFinSemana++;
            }
            gcal1.add(Calendar.DAY_OF_YEAR, 1);
        }
        int diasVacaciones = diasDiff - quitarFinSemana;
        //jLabel11.setText(Integer.toString(diasVacaciones));
        return diasVacaciones;
    }

    /**
     *
     * @param inicio
     * @param ultimo
     * @return
     */
    public static boolean verificarQueHayaFecha(java.util.Date inicio, java.util.Date ultimo) {

        if (inicio != null & ultimo != null) {


            if (inicio.before(ultimo) || inicio.equals(ultimo)) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Verifique su intervalo, la fecha inicial debe ser menor que la final");
                return false;
            }
        }
        return false;
    }

    public static java.util.Date regresaLaborar(Date regresa) {

        GregorianCalendar gcal1 = new GregorianCalendar();
        gcal1.setTime(regresa);
        gcal1.add(Calendar.DAY_OF_YEAR, 1);
        // Sabado = 6; Domingo = 0
        if (gcal1.getTime().getDay() == 6) {
            gcal1.add(Calendar.DAY_OF_YEAR, 2);
        }
        if (gcal1.getTime().getDay() == 0) {
            gcal1.add(Calendar.DAY_OF_YEAR, 1);
        }
        
        return gcal1.getTime();

    }
}

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
        
        
        //Se agrego esta porción de código porque se contaba sabado como un día de trabajo
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(ultimo);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DATE, 1);  // number of days to add

        while (gcal1.getTime().before(c.getTime())) {
            // Sabado = 6; Domingo = 0
            //JOptionPane.showMessageDialog(null, c.getTime());
            //JOptionPane.showMessageDialog(null, gcal1.getTime().getDay());
            //JOptionPane.showMessageDialog(null,gcal1.getTime().before(c.getTime()));
            if (gcal1.getTime().getDay() == 6 | gcal1.getTime().getDay() == 0) {
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

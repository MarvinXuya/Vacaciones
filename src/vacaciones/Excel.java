package vacaciones;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;// .HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import vacaciones.DB.colaboradoresDAO;
import vacaciones.DB.vacacionesDAO;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//

/**
 *
 * @author mx
 */
public class Excel {

    colaboradoresDAO col = new colaboradoresDAO();
    vacacionesDAO v = new vacacionesDAO();

    public static boolean verificarexcel() {
        String excelMaestro = System.getProperty("user.dir") + "\\DRH7.xls";
        try {
            FileOutputStream testOut = new FileOutputStream(excelMaestro,
                    true);

            testOut.close();
            return true;
        } catch (Exception e) {
            //e.printStackTrace();  
            JOptionPane.showMessageDialog(null, "El archivo maestro: "
                    + excelMaestro
                    + " se encuentra abierto. Por favor cierrelo antes de continuar.");
            return false;
        }

    }

    public static boolean verificarexcelHonorarios() {
        String excelMaestro = System.getProperty("user.dir") + "\\DRH8.xls";
        try {
            FileOutputStream testOut = new FileOutputStream(excelMaestro,
                    true);

            testOut.close();
            return true;
        } catch (Exception e) {
            //e.printStackTrace();  
            JOptionPane.showMessageDialog(null, "El archivo maestro: "
                    + excelMaestro
                    + " se encuentra abierto. Por favor cierrelo antes de continuar.");
            return false;
        }

    }

public String modificar(int codMod, Date fechaEmisionMod, Date VacacionesAlMod,Date f1, Date f2, Date f3, Date f4, String codigo, int Dias, String rangoFechas, Date regresoLaborar, Date incioVacaciones, String elaboradoPor, Date fechaEmision, int tipoColaborador, int tipoDocumento, int tipoCampus, String firma) throws Exception {

        if (verificarexcel() == true) {

            GregorianCalendar fInicio = new GregorianCalendar();
            DateFormat base = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat display = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");           

            String rLaboral = display.format(regresoLaborar);
            String rLaboralBase = base.format(regresoLaborar);
            String fEmision = base.format(fechaEmision);

            String iVacaciones = base.format(incioVacaciones);
            String if1 = base.format(f1);
            String if2 = base.format(f2);
            String if3 = null;
            String if4 = null;
            String ifechaEmisionMod = base.format(fechaEmisionMod);
            String iVacacionesAlMod = base.format(VacacionesAlMod);

            String paso = null;
            float pasoFloat = 0;
            int pasoInt = 0;
            if (f3 == null) {
                f3 = null;
            } else {
                if3 = base.format(f3);
            }
            if (f4 == null) {
                f4 = null;
            } else {
                if4 = base.format(f4);
            }

            fInicio.setTime(incioVacaciones);
            int inicioDia = fInicio.get(GregorianCalendar.DAY_OF_MONTH);
            int inicioMes = fInicio.get(GregorianCalendar.MONTH) + 1;
            int inicioAnio = fInicio.get(GregorianCalendar.YEAR);

            fInicio.setTime(fechaEmision);
            int emisionDia = fInicio.get(GregorianCalendar.DAY_OF_MONTH);
            int emisionMes = fInicio.get(GregorianCalendar.MONTH) + 1;
            int emisionAnio = fInicio.get(GregorianCalendar.YEAR);

            VacacionesMod accion = new VacacionesMod(codMod,ifechaEmisionMod,iVacacionesAlMod,fEmision,Dias,"Estaré tomando " + Integer.toString(Dias) + " día(s) de vacaciones 2014",if1,if2,if3,if4,tipoCampus,tipoColaborador, tipoDocumento,elaboradoPor,rLaboralBase);
            v.ModificarAccion(accion, Conexion.getConnection());
        }//Fin de excel
        return null;
    }
    
    public String agregar(Date f1, Date f2, Date f3, Date f4, String codigo, int Dias, String rangoFechas, Date regresoLaborar, Date incioVacaciones, String elaboradoPor, Date fechaEmision, int tipoColaborador, int tipoDocumento, int tipoCampus, String firma) throws Exception {

        if (verificarexcel() == true) {

            GregorianCalendar fInicio = new GregorianCalendar();
            DateFormat base = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat display = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");           

            String rLaboral = display.format(regresoLaborar);
            String rLaboralBase = base.format(regresoLaborar);
            String fEmision = base.format(fechaEmision);

            String iVacaciones = base.format(incioVacaciones);
            String if1 = base.format(f1);
            String if2 = base.format(f2);
            String if3 = null;
            String if4 = null;

            String paso = null;
            float pasoFloat = 0;
            int pasoInt = 0;
            if (f3 == null) {
                f3 = null;
            } else {
                if3 = base.format(f3);
            }
            if (f4 == null) {
                f4 = null;
            } else {
                if4 = base.format(f4);
            }

            fInicio.setTime(incioVacaciones);
            int inicioDia = fInicio.get(GregorianCalendar.DAY_OF_MONTH);
            int inicioMes = fInicio.get(GregorianCalendar.MONTH) + 1;
            int inicioAnio = fInicio.get(GregorianCalendar.YEAR);

            fInicio.setTime(fechaEmision);
            int emisionDia = fInicio.get(GregorianCalendar.DAY_OF_MONTH);
            int emisionMes = fInicio.get(GregorianCalendar.MONTH) + 1;
            int emisionAnio = fInicio.get(GregorianCalendar.YEAR);

            Vacaciones accion = new Vacaciones(fEmision, iVacaciones, elaboradoPor, Integer.parseInt(codigo), paso, paso, paso, "Centro de estudios en salud", "Instituto de investigaciones", "EIR-UEIE año 1", paso, paso, paso, paso, pasoFloat, pasoFloat, pasoFloat, paso, pasoFloat, pasoFloat, pasoFloat, paso, paso, paso, paso, paso, paso, paso, paso, paso, paso, if1, if2, if3, if4, Dias, rLaboralBase, pasoInt, "Estaré tomando " + Integer.toString(Dias) + " día(s) de vacaciones 2014", paso, paso, paso, tipoColaborador, tipoDocumento, tipoCampus);
            v.AgregarColaboradores(accion, Conexion.getConnection());
        }//Fin de excel
        return null;
    }

    public String generar(Date f1, Date f2, Date f3, Date f4, String codigo, int Dias, String rangoFechas, Date regresoLaborar, Date incioVacaciones, String elaboradoPor, Date fechaEmision, int tipoColaborador, int tipoDocumento, int tipoCampus, String firma) throws Exception {
        //JOptionPane.showMessageDialog(null, firma);
        GregorianCalendar fInicio = new GregorianCalendar();
        DateFormat base = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat display = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");           

        String rLaboral = display.format(regresoLaborar);
        String rLaboralBase = base.format(regresoLaborar);
        String fEmision = base.format(fechaEmision);

        String iVacaciones = base.format(incioVacaciones);
        String if1 = base.format(f1);
        String if2 = base.format(f2);
        String if3 = null;
        String if4 = null;

        String paso = null;
        float pasoFloat = 0;
        int pasoInt = 0;
        if (f3 == null) {
            f3 = null;
        } else {
            if3 = base.format(f3);
        }
        if (f4 == null) {
            f4 = null;
        } else {
            if4 = base.format(f4);
        }

        fInicio.setTime(incioVacaciones);
        int inicioDia = fInicio.get(GregorianCalendar.DAY_OF_MONTH);
        int inicioMes = fInicio.get(GregorianCalendar.MONTH) + 1;
        int inicioAnio = fInicio.get(GregorianCalendar.YEAR);

        fInicio.setTime(fechaEmision);
        int emisionDia = fInicio.get(GregorianCalendar.DAY_OF_MONTH);
        int emisionMes = fInicio.get(GregorianCalendar.MONTH) + 1;
        int emisionAnio = fInicio.get(GregorianCalendar.YEAR);

        List<Colaboradores> colaboradores;
        colaboradores = col.listarColaboradorCodigo(codigo, Conexion.getConnection());
        for (Colaboradores c : colaboradores) {

            String NombreCompleto = c.getNombre() + " " + c.getApellido();
            //public void actualizar(){
            try {
                HSSFWorkbook workbook;
                //JOptionPane.showMessageDialog(null, tipoColaborador); //--Para ver el tipo de documento que se generará
                if (tipoColaborador == 0) {//factura = 1 -- planilla = 0
                    if (verificarexcel() == true) {
                        try (FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir") + "\\DRH7.xls"))) {
                            workbook = new HSSFWorkbook(file);
                            HSSFSheet sheet = workbook.getSheetAt(0);
                            HSSFCell cell = null;

                            switch (tipoColaborador) {
                                case 0:
                                    cell = sheet.getRow(1).getCell(25);
                                    cell.setCellValue("DRH-07"); //Planilla
                                    cell = sheet.getRow(9).getCell(2);
                                    cell.setCellValue("Vacaciones"); //Planilla
                                    cell = sheet.getRow(9).getCell(5);//Planilla
                                    cell.setCellValue(""); //Planilla
                                    break;
                                case 1:
                                    cell = sheet.getRow(1).getCell(25);
                                    cell.setCellValue("DRH-08"); //factura
                                    cell = sheet.getRow(9).getCell(2);
                                    cell.setCellValue("Permisos"); //factura
                                    cell = sheet.getRow(9).getCell(5);//factura
                                    cell.setCellValue("(1,2,4,10,13,18)"); //factura
                                    break;
                            }
                            cell = sheet.getRow(2).getCell(26);
                            cell.setCellValue("2013");
                            cell = sheet.getRow(4).getCell(2);
                            cell.setCellValue("○");
                            cell = sheet.getRow(4).getCell(8);
                            cell.setCellValue("○");
                            cell = sheet.getRow(4).getCell(15);
                            cell.setCellValue("○");
                            cell = sheet.getRow(4).getCell(20);
                            cell.setCellValue("○");
                            switch (tipoCampus) {
                                case 0:
                                    cell = sheet.getRow(4).getCell(2);
                                    break;
                                case 1:
                                    cell = sheet.getRow(4).getCell(8);
                                    break;
                                case 2:
                                    cell = sheet.getRow(4).getCell(15);
                                    break;
                                case 3:
                                    cell = sheet.getRow(4).getCell(20);
                                    break;

                            }
                            cell.setCellValue("●");

                            cell = sheet.getRow(14).getCell(9);
                            cell.setCellValue(inicioDia);
                            cell = sheet.getRow(14).getCell(11);
                            cell.setCellValue(inicioMes);
                            cell = sheet.getRow(14).getCell(13);
                            cell.setCellValue(inicioAnio);
                            cell = sheet.getRow(14).getCell(2);
                            cell.setCellValue(emisionDia);
                            cell = sheet.getRow(14).getCell(4);
                            cell.setCellValue(emisionMes);
                            cell = sheet.getRow(14).getCell(6);
                            cell.setCellValue(emisionAnio);
                            cell = sheet.getRow(14).getCell(17);
                            cell.setCellValue(elaboradoPor);
                            cell = sheet.getRow(16).getCell(7);
                            cell.setCellValue(NombreCompleto);
                            cell = sheet.getRow(16).getCell(25);
                            cell.setCellValue(c.getCodigo());
                            cell = sheet.getRow(20).getCell(2);
                            cell.setCellValue(c.getPuesto());
                            cell = sheet.getRow(21).getCell(23);
                            cell.setCellValue(c.getOtroProyecto());
                            cell = sheet.getRow(36).getCell(6);
                            cell.setCellValue(Dias);
                            cell = sheet.getRow(34).getCell(6);
                            cell.setCellValue(rangoFechas);
                            cell = sheet.getRow(36).getCell(10);
                            cell.setCellValue(rLaboral);
                            cell = sheet.getRow(44).getCell(2);
                            cell.setCellValue("Estaré tomando " + Dias + " día(s) de vacaciones 2014");
                            cell = sheet.getRow(54).getCell(2);
                            cell.setCellValue(firma);
                        }
                        try (FileOutputStream outFile = new FileOutputStream(new File(System.getProperty("user.dir") + "\\DRH7.xls"))) {
                            workbook.write(outFile);
                        }
                    }//Fin de validación de excel
                }// Fin excel planilla

                if (tipoColaborador == 1) {//factura = 1 -- planilla = 0
                    if (verificarexcelHonorarios() == true) {

                        try (FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir") + "\\DRH8.xls"))) {
                            workbook = new HSSFWorkbook(file);
                            HSSFSheet sheet = workbook.getSheetAt(0);
                            HSSFCell cell = null;

                            cell = sheet.getRow(1).getCell(25);
                            cell.setCellValue("DRH-08"); //factura
                            cell = sheet.getRow(9).getCell(2);
                            cell.setCellValue("Permisos"); //factura
                            cell = sheet.getRow(9).getCell(5);//factura
                            cell.setCellValue("(1,2,4,10,13,18)"); //factura

                            cell = sheet.getRow(2).getCell(26);
                            cell.setCellValue("2013");
                            cell = sheet.getRow(4).getCell(2);
                            cell.setCellValue("○");
                            cell = sheet.getRow(4).getCell(8);
                            cell.setCellValue("○");
                            cell = sheet.getRow(4).getCell(15);
                            cell.setCellValue("○");
                            cell = sheet.getRow(4).getCell(20);
                            cell.setCellValue("○");
                            switch (tipoCampus) {
                                case 0:
                                    cell = sheet.getRow(4).getCell(2);
                                    break;
                                case 1:
                                    cell = sheet.getRow(4).getCell(8);
                                    break;
                                case 2:
                                    cell = sheet.getRow(4).getCell(15);
                                    break;
                                case 3:
                                    cell = sheet.getRow(4).getCell(20);
                                    break;

                            }
                            cell.setCellValue("●");

                            cell = sheet.getRow(14).getCell(9);
                            cell.setCellValue(inicioDia);
                            cell = sheet.getRow(14).getCell(11);
                            cell.setCellValue(inicioMes);
                            cell = sheet.getRow(14).getCell(13);
                            cell.setCellValue(inicioAnio);
                            cell = sheet.getRow(14).getCell(2);
                            cell.setCellValue(emisionDia);
                            cell = sheet.getRow(14).getCell(4);
                            cell.setCellValue(emisionMes);
                            cell = sheet.getRow(14).getCell(6);
                            cell.setCellValue(emisionAnio);
                            cell = sheet.getRow(14).getCell(17);
                            cell.setCellValue(elaboradoPor);
                            cell = sheet.getRow(16).getCell(7);
                            cell.setCellValue(NombreCompleto);
                            cell = sheet.getRow(16).getCell(25);
                            cell.setCellValue(c.getCodigo());
                            cell = sheet.getRow(20).getCell(2);
                            cell.setCellValue(c.getPuesto());
                            cell = sheet.getRow(21).getCell(23);
                            cell.setCellValue(c.getOtroProyecto());
                            cell = sheet.getRow(35).getCell(2);
                            cell.setCellValue(Dias);

                            if (rangoFechas.contains(".")) {                                
                                String part1 = rangoFechas.substring(0,rangoFechas.indexOf('.'));
                                //System.out.println(part1);
                                String part2 = rangoFechas.substring((rangoFechas.indexOf('.')+2),rangoFechas.length());
                                //System.out.println(part2);
                                cell = sheet.getRow(36).getCell(2);
                                cell.setCellValue(part1);
                                cell = sheet.getRow(37).getCell(2);
                                cell.setCellValue(part2);
                            } else {
                                cell = sheet.getRow(36).getCell(2);
                                cell.setCellValue(rangoFechas);
                                cell = sheet.getRow(37).getCell(2);
                                cell.setCellValue("");
                            }
                            cell = sheet.getRow(36).getCell(16);
                            cell.setCellValue(rLaboral);
                            cell = sheet.getRow(44).getCell(2);
                            cell.setCellValue("Estaré tomando " + Dias + " día(s) de vacaciones 2014");
                            cell = sheet.getRow(54).getCell(2);
                            cell.setCellValue(firma);
                        }
                        try (FileOutputStream outFile = new FileOutputStream(new File(System.getProperty("user.dir") + "\\DRH8.xls"))) {
                            workbook.write(outFile);
                        }
                    }//Fin de validación de excel
                }// Fin excel planilla                    

                String valor = "'" + fEmision + "'" + "," + "'" + iVacaciones + "'" + "," + "'" + elaboradoPor + "'" + "," + "'" + codigo + "'" + "," + paso + "," + paso + "," + paso + "," + "'Centro de estudios en salud'" + "," + "'Instituto de investigaciones'" + "," + "'EIR-UEIE año 1'" + "," + paso + "," + paso + "," + paso + "," + paso + "," + paso + "," + paso + "," + paso + "," + paso + "," + paso + "," + paso + "," + paso + "," + paso + "," + paso + "," + paso + "," + paso + "," + paso + "," + paso + "," + paso + "," + paso + "," + paso + "," + paso + "," + "'" + f1 + "'" + "," + "'" + f2 + "'" + "," + f3 + "," + f4 + "," + Integer.toString(Dias) + "," + "'" + regresoLaborar + "'" + "," + paso + "," + "'Estaré tomando " + Integer.toString(Dias) + " día(s) de vacaciones 2014'" + "," + paso + "," + paso + "," + paso + "," + tipoColaborador + "," + tipoDocumento + "," + tipoCampus;
                //JOptionPane.showMessageDialog(null,valor);
                return valor;

            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }//final de catch

        }//final de for

        return null;
    }
}

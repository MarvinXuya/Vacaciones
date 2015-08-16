/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vacaciones.visual;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import vacaciones.Conexion;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import vacaciones.DB.colaboradoresDAO;
import vacaciones.DB.colaboradoresDisponiblesDAO;
import vacaciones.Colaboradores;
import vacaciones.ColaboradoresDisponibles;
import vacaciones.Calculos;
import vacaciones.Excel;
/**
 *
 * @author mx
 */
public class home extends javax.swing.JFrame {

    private boolean agregar = false;
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
    private String fechaInicioLaboral;
    private String fechaFinalLaboral;
    private String firmaAccion;
    String imagePath = "sol.png";
    colaboradoresDAO dao = new colaboradoresDAO();
    colaboradoresDisponiblesDAO daoCD = new colaboradoresDisponiblesDAO();
    JTable tabla;

    public void Limpiar() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
        textField7.setText("");
        textField8.setText("");
        textField9.setText("");
        textField10.setText("");
        textField11.setText("");
        textField12.setText("");
        textArea1.setText("");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jCheckBox1.setSelected(false);
    }

    private void Listar(String codigo) {

        boolean isIntCod = Calculos.isInt(codigo);
        if (isIntCod == true) {
            try {
                List<Colaboradores> colaboradores = dao.listarColaboradorCodigo(codigo, Conexion.getConnection());
                for (Colaboradores c : colaboradores) {
                    textField1.setText(c.getCodigo());
                    jLabel13.setText(c.getCodigo());
                    textField2.setText(c.getNombre());
                    textField3.setText(c.getApellido());
                    textField4.setText(c.getPuesto());
                    textField5.setText(c.getClasificacionPuesto());
                    textField10.setText(c.getClasificacionCodigo());
                    textField6.setText(c.getPorcentajeDedicacion());
                    textField7.setText(c.getSitio());
                    textField8.setText(c.getProyecto());
                    textField9.setText(c.getSubproyecto());
                    textField11.setText(c.getSubproyecto2());
                    textField12.setText(c.getOtroProyecto());
                    textArea1.setText(c.getObservaciones());

                    if (c.getFechaInicioLaboral() == null) {
                        jDateChooser1.setDate(null);
                    } else {
                        String paso1 = c.getFechaInicioLaboral().toString();
                        Date inicio = new SimpleDateFormat("yyyy-MM-dd").parse(paso1);
                        jDateChooser1.setDate(inicio);
                    }

                    if (c.getFechaFinalLaboral() == null) {

                        jDateChooser2.setDate(null);
                    } else {
                        String paso2 = c.getFechaFinalLaboral().toString();
                        Date ffinal = new SimpleDateFormat("yyyy-MM-dd").parse(paso2);
                        jDateChooser2.setDate(ffinal);
                    }
                    if (c.getFirmaAccion() == null) {
                        jCheckBox1.setSelected(false);
                    } else {
                        if (c.getFirmaAccion().equals("1")) {
                            jCheckBox1.setSelected(true);
                        } else {


                            if (c.getFirmaAccion().equals("0")) {
                                jCheckBox1.setSelected(false);
                            }
                        }
                    }
                }


            } catch (Exception ex) {
                Logger.getLogger(vacaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar un valor numérico");
        }

    }

    public void actualizarTabla() throws Exception {
        //TableModel Model = null;
        this.jTable1.setModel(Model);
        Model.setRowCount(0);
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            Model.removeRow(i);
        }
        List<ColaboradoresDisponibles> colaboradores = daoCD.listadoDeColaboradores(Conexion.getConnection());
        for (ColaboradoresDisponibles c : colaboradores) {
            //Model.addRow(new Object[]{c.getCodigo(), c.getNombre(), c.getApellido(), c.getPuesto(), c.getClasificacionPuesto(), c.getClasificacionCodigo(), c.getPorcentajeDedicacion(), c.getSitio(), c.getProyecto(), c.getSubproyecto(), c.getSubproyecto2(), c.getObservaciones()});
            Model.addRow(new Object[]{c.getCodigo(), c.getNombre(), c.getApellido(), c.getPuesto(), c.getDisponible()});
        }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(140);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(140);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(160);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);
        tabla = jTable1;
        
    }

    public void busquedaActualizaTabla() throws Exception {
        String nombre1 = JOptionPane.showInputDialog(null, "Por favor ingrese el nombre del colaborador.", "Busqueda", JOptionPane.QUESTION_MESSAGE);

        this.jTable1.setModel(Model);
        Model.setRowCount(0);
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            Model.removeRow(i);
        }
        List<ColaboradoresDisponibles> colaboradores = daoCD.listarColaboradorNombre(nombre1, Conexion.getConnection());
        for (ColaboradoresDisponibles c : colaboradores) {
            //Model.addRow(new Object[]{c.getCodigo(), c.getNombre(), c.getApellido(), c.getPuesto(), c.getClasificacionPuesto(), c.getClasificacionCodigo(), c.getPorcentajeDedicacion(), c.getSitio(), c.getProyecto(), c.getSubproyecto(), c.getSubproyecto2(), c.getObservaciones()});
            Model.addRow(new Object[]{c.getCodigo(), c.getNombre(), c.getApellido(), c.getPuesto(), c.getDisponible()});
        }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(140);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(140);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(160);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);
        tabla = jTable1;
    }

    public void VerificarAgregarColaboradores() {
        codigo = textField1.getText();
        nombre = textField2.getText();
        apellido = textField3.getText();
        puesto = textField4.getText();
        clasificacionPuesto = textField5.getText();
        clasificacionCodigo = textField10.getText();
        porcentajeDedicacion = textField6.getText();
        sitio = textField7.getText();
        proyecto = textField8.getText();
        subproyecto = textField9.getText();
        subproyecto2 = textField11.getText();
        observaciones = textArea1.getText();
        otroProyecto = textField12.getText();
        DateFormat base = new SimpleDateFormat("yyyy-MM-dd");
        if (jDateChooser1.getDate() == null) {
            fechaInicioLaboral = null;
        } else {
            String inicio = base.format(jDateChooser1.getDate());
            fechaInicioLaboral = inicio;
        }
        if (jDateChooser2.getDate() == null) {
            fechaFinalLaboral = null;
        } else {
            String ffinal = base.format(jDateChooser2.getDate());
            fechaFinalLaboral = ffinal;
        }
        if (jCheckBox1.isSelected() == true) {
            firmaAccion = "1";
        } else {
            firmaAccion = "0";
        }


        Calculos c = new Calculos();
        if (Calculos.isInt(codigo) == true & Calculos.isInt(porcentajeDedicacion) == true) {
            if (codigo == null || codigo.equals("") || nombre == null || nombre.equals("") || apellido == null || apellido.equals("")
                    || puesto == null || puesto.equals("") || porcentajeDedicacion == null || porcentajeDedicacion.equals("")
                    || sitio == null || sitio.equals("") || proyecto == null || proyecto.equals("")) {
                JOptionPane.showMessageDialog(null, "Verificar, existen campos vacios");
                agregar = false;
            } else {
                agregar = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "El código y porcentaje de dedicación debe ser numérico");
            agregar = false;
        }
    }
    DefaultTableModel Model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };

    public home() {
        Model.addColumn("Codigo");
        Model.addColumn("Nombre");
        Model.addColumn("Apellido");
        Model.addColumn("Puesto");
        Model.addColumn("Disponible");
        // Model.addColumn("Clasificación");
        // Model.addColumn("Clasificación Código");
        // Model.addColumn("Porcentaje Dedicación");
        // Model.addColumn("Sitio");
        // Model.addColumn("Proyecto");
        // Model.addColumn("Subproyecto");
        // Model.addColumn("Subproyecto2");
        // Model.addColumn("Observaciones");


        initComponents();
        setIcon();
        jPanel1.setVisible(false);
        jPanel2.setVisible(false);
        jLabel13.setVisible(false);
        Limpiar();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        jButton8.setEnabled(false);
        //this.setIconImage(Toolkit.getDefaultToolkit().getImage("sol.png")); 

    }

    public void fColaboradores() {
        jPanel1.setVisible(true);
        jPanel2.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        textField1 = new java.awt.TextField();
        textField2 = new java.awt.TextField();
        textField3 = new java.awt.TextField();
        textField4 = new java.awt.TextField();
        textField5 = new java.awt.TextField();
        textField6 = new java.awt.TextField();
        textField7 = new java.awt.TextField();
        textField8 = new java.awt.TextField();
        textField9 = new java.awt.TextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        textField10 = new java.awt.TextField();
        textField11 = new java.awt.TextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        textArea1 = new java.awt.TextArea();
        jButton6 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        textField12 = new java.awt.TextField();
        jLabel14 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton7 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        jButton1.setText("jButton1");

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JMX");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Colaboradores"));

        jLabel1.setText("Código");
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 14));

        jLabel2.setText("Nombre");
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 14));

        jLabel3.setText("Nombre del Puesto");
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 14));

        jLabel4.setText("Porcentaje de dedicación");

        jLabel5.setText("Sitio");
        jLabel5.setPreferredSize(new java.awt.Dimension(120, 14));

        jLabel6.setText("Sub-proyecto");

        jLabel7.setText("Clasificación del puesto");
        jLabel7.setPreferredSize(new java.awt.Dimension(120, 14));

        jLabel9.setText("Apellido");
        jLabel9.setPreferredSize(new java.awt.Dimension(120, 14));

        textField1.setText("textField1");

        textField2.setText("textField2");

        textField3.setText("textField3");

        textField4.setText("textField4");

        textField5.setText("textField5");

        textField6.setText("textField6");
        textField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField6ActionPerformed(evt);
            }
        });

        textField7.setText("textField7");

        textField8.setText("textField8");

        textField9.setText("textField9");

        jLabel10.setText("Proyecto");
        jLabel10.setPreferredSize(new java.awt.Dimension(120, 14));

        jLabel11.setText("Clasificación código");
        jLabel11.setPreferredSize(new java.awt.Dimension(120, 14));

        textField10.setMinimumSize(new java.awt.Dimension(60, 20));
        textField10.setText("textField10");

        textField11.setMaximumSize(new java.awt.Dimension(60, 20));
        textField11.setMinimumSize(new java.awt.Dimension(60, 20));
        textField11.setText("textField11");

        jLabel12.setText("Sub-proyecto");

        jButton6.setText("Limpiar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel8.setText("Observaciones");

        textField12.setText("textField12");

        jLabel14.setText("Otro proyecto");

        jLabel15.setText("Fecha Inicio Laboral");

        jLabel16.setText("Fecha Finalizacion Laboral");

        jLabel17.setText("Firma acciones");

        jCheckBox1.setText("Sí, firmará acciones de personal");

        jButton7.setText("Agregar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton5.setText("Actualizar");
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setText("Eliminar");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(256, 256, 256))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(textField6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(textField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7)))
                .addGap(77, 77, 77)
                .addComponent(jLabel13)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Colaboradores"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setColumnSelectionAllowed(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Todos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton8.setText("Exportar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );

        jMenu1.setText("Archivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Cerrar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Modulos");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Colaboradores");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Vacaciones");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Opciones");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem4.setText("Acerca de");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(1);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            actualizarTabla();
        } catch (Exception ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
jButton8.setEnabled(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            busquedaActualizaTabla();
        } catch (Exception ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
jButton8.setEnabled(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        fColaboradores();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed


        //super("Another GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(false);
        vacaciones v = new vacaciones();
        v.setVisible(true);

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (!(jTable1.getSelectedRowCount() > 0)) {


            JOptionPane.showMessageDialog(null, "Para ejecutar esta opción debe selecciónar a un colaborador", "ADVERTENCIA", 2);

        } else {
            Object sele = jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0);
            Object nombre1 = jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 1);
            Object apellido1 = jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 2);

            int seleccion = Integer.parseInt(sele.toString());
            try {


                if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar al colaborador " + nombre1 + " " + apellido1 + " con el codigo de empleado " + seleccion + "?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    dao.eliminarColaborador(seleccion, Conexion.getConnection());
                    Limpiar();
                    jButton4.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Cancelado");
                }



            } catch (Exception ex) {
                Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                actualizarTabla();
            } catch (SQLException ex) {
                Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            }        // TODO add your handling code here:
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (!(jTable1.getSelectedRowCount() > 0)) {


            JOptionPane.showMessageDialog(null, "Para ejecutar esta opción debe selecciónar a un colaborador", "ADVERTENCIA", 2);

        } else {
            VerificarAgregarColaboradores();
            if (agregar = true) {


                if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de modificar al colaborador " + textField2.getText() + " " + textField3.getText() + " con el codigo de empleado " + textField1.getText() + "?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    colaboradoresDAO col = new colaboradoresDAO();
                    try {
                        Colaboradores personas = new Colaboradores(codigo, nombre, apellido, puesto, clasificacionPuesto, clasificacionCodigo, porcentajeDedicacion, sitio, proyecto, subproyecto, subproyecto2, observaciones, otroProyecto, fechaInicioLaboral, fechaFinalLaboral, firmaAccion);
                        //textArea1.setText(codigo+","+ nombre+","+ apellido+","+ puesto+","+ clasificacionPuesto+","+ clasificacionCodigo+","+ porcentajeDedicacion+","+ sitio+","+ proyecto+","+ subproyecto+","+ subproyecto2+","+ observaciones+","+ otroProyecto);
                        //6439, Ingrid Lorena,Contreras Roldan,Investigadora,Investigador 1,G-08/P-08/I1,25,UVG,EiR-CoAg,G-08/P-08/I1,null,,SMS UC-1
                        col.ModificarColaboradores(personas, jLabel13.getText(), Conexion.getConnection());
                        Limpiar();
                        jButton5.setEnabled(false);
                        try {
                            actualizarTabla();
                        } catch (SQLException ex) {
                            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                        }        // TODO add your handling code here:


                    } catch (Exception ex) {
                        Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Parese que hubo un problema, comuniquese con el administrador del sistema");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Cancelado", "Warning",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (!(jTable1.getSelectedRowCount() > 0)) {


            JOptionPane.showMessageDialog(null, "Para ejecutar esta opción debe selecciónar a un colaborador", "ADVERTENCIA", 2);
            jButton5.setEnabled(false);
        } else {
            Object sele = jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0);


            String paso = sele.toString();
            Integer.parseInt(sele.toString());
            try {
                Listar(paso);
                jButton5.setEnabled(true);
                jButton4.setEnabled(true);

            } catch (Exception ex) {
                Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        AcercaDe d;
        try {
            d = new AcercaDe();
            d.setVisible(true);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            d.setLocation(dim.width / 2 - d.getSize().width / 2, dim.height / 2 - d.getSize().height / 2);


        } catch (IOException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }





    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Limpiar();
        jButton5.setEnabled(false);
        jButton4.setEnabled(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void textField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        VerificarAgregarColaboradores();
        if (agregar == true) {
            colaboradoresDAO col = new colaboradoresDAO();
            try {
                if (subproyecto.equals("")) {
                    subproyecto = null; //else subproyecto = "'"+subproyecto+"'";
                }
                if (observaciones.equals("")) {
                    observaciones = null; //else observaciones = "'"+observaciones+"'";
                }
                Colaboradores personas = new Colaboradores(codigo, nombre, apellido, puesto, clasificacionPuesto, clasificacionCodigo, porcentajeDedicacion, sitio, proyecto, subproyecto, subproyecto2, observaciones, otroProyecto, fechaInicioLaboral, fechaFinalLaboral, firmaAccion);
                col.AgregarColaboradores(personas, Conexion.getConnection());
                Limpiar();
            } catch (Exception ex) {
                Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Parese que hubo un problema, comuniquese con el administrador del sistema");
            }
        }
        jButton5.setEnabled(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
            Excel ex = new Excel();
        try {
            ex.exportTable(tabla);
        } catch (IOException ex1) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex1);
        }
        JOptionPane.showMessageDialog(null, "Archivo: Listado.xls", "Archivo actualizado ahora disponible", JOptionPane.INFORMATION_MESSAGE);
        jButton8.setEnabled(false);
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new home().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.TextArea textArea1;
    private java.awt.TextField textField1;
    private java.awt.TextField textField10;
    private java.awt.TextField textField11;
    private java.awt.TextField textField12;
    private java.awt.TextField textField2;
    private java.awt.TextField textField3;
    private java.awt.TextField textField4;
    private java.awt.TextField textField5;
    private java.awt.TextField textField6;
    private java.awt.TextField textField7;
    private java.awt.TextField textField8;
    private java.awt.TextField textField9;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("sol.gif")));

    }
}

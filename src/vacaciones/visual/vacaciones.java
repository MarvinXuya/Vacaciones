/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vacaciones.visual;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
//import java.sql.Date;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vacaciones.ColaboradoresDisponibles;
import vacaciones.Colaboradores;
import vacaciones.Conexion;
import vacaciones.Calculos;

import javax.swing.table.DefaultTableModel;

import vacaciones.DB.colaboradoresDisponiblesDAO;
import vacaciones.DB.colaboradoresDAO;
import vacaciones.DB.vacacionesDAO;
import vacaciones.Excel;
import vacaciones.Vacaciones;

/**
 *
 * @author mx
 */
public class vacaciones extends javax.swing.JFrame {

    colaboradoresDisponiblesDAO colCD = new colaboradoresDisponiblesDAO();
    colaboradoresDAO col = new colaboradoresDAO();
    private String codigo;
    private String nombre;
    private String apellido;
    private String puesto;
    private String clasificacionPuesto;
    private String porcentajeDedicacion;
    private String sitio;
    private String proyecto;
    private String subproyecto;
    private String observaciones;
    private String elaboradoPor = null;
    private Integer tipoColaborador = null;
    private Integer tipoDocumento = null;
    private Integer tipoCampus = null;
    private String firma = null;
    vacacionesDAO dao = new vacacionesDAO();

    public void actualizarTabla(String codigo) throws Exception {

        this.jTable1.setModel(Model);
        Model.setRowCount(0);
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            Model.removeRow(i);
        }
        List<Vacaciones> vacaciones = dao.listarAccionCodigo(codigo, Conexion.getConnection());
        for (Vacaciones v : vacaciones) {
            //Model.addRow(new Object[]{c.getCodigo(), c.getNombre(), c.getApellido(), c.getPuesto(), c.getClasificacionPuesto(), c.getClasificacionCodigo(), c.getPorcentajeDedicacion(), c.getSitio(), c.getProyecto(), c.getSubproyecto(), c.getSubproyecto2(), c.getObservaciones()});
            Model.addRow(new Object[]{v.getFechaEmision(), v.getVacacionesDel(), v.getVacacionesAl(), v.getVacacionesDel2(), v.getVacacionesAl2(), v.getDíasVacaciones()});
        }

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
    }

    private void buscarColaborador() {
        String verificador = null;
        if (jButton2.getText().equals("Buscar")) {
            String codigoBuscar = jTextField1.getText();
            boolean isIntCod = Calculos.isInt(codigoBuscar);
            if (isIntCod == true) {
                try {
                    List<ColaboradoresDisponibles> colaboradores = colCD.listarColaboradorCodigo(codigoBuscar, Conexion.getConnection());
                    for (ColaboradoresDisponibles c : colaboradores) {
                        jLabel1.setText(c.getNombre());
                        jLabel2.setText(c.getApellido());
                        jLabel12.setText(c.getDisponible());
                        verificador = c.getNombre();
                        //colaboradoresDAO col = new colaboradoresDAO();
                        // -----> se agregan 20 dias por año actual <------//
                        col.BuscarColaboradoresAgregarVacaciones(codigoBuscar, Conexion.getConnection());
//                        int disp = col.BuscarColaboradoresDiasDisponibles(codigo, Conexion.getConnection());
                        //jLabel12.setText(Integer.toString(col.BuscarColaboradoresDiasDisponibles(codigoBuscar, Conexion.getConnection())));
                    }
                    if (!(verificador == null)) {
                        jButton2.setText("Cancelar");
                        jTextField1.setEnabled(false);
                        try {
                            actualizarTabla(jTextField1.getText());
                        } catch (Exception ex) {
                            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        jTextField1.setText(null);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(vacaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor numérico");
            }
        } else {
            if (jButton2.getText().equals("Cancelar")) {
                jButton2.setText("Buscar");
                jTextField1.setEnabled(true);
                jTextField1.setText(null);
                jLabel1.setText(null);
                jLabel2.setText(null);
                jDateChooser1.setDate(null);
                jDateChooser2.setDate(null);
                jDateChooser3.setDate(null);
                jDateChooser4.setDate(null);
                jTextField1.setFocusable(true);
                Calendar calendar = Calendar.getInstance();
                java.util.Date currentDate = calendar.getTime();
                java.sql.Date now = new java.sql.Date(currentDate.getTime());
                Format formato = new SimpleDateFormat("dd-MM-yyyy");
                String s = formato.format(now);
                jTextField2.setText(s);
                jTextField2.setEnabled(false);
                jButton3.setEnabled(true);
            }
        }
    }
    DefaultTableModel Model = new DefaultTableModel();

    public vacaciones() {
        initComponents();
        setIcon();
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Date now = new java.sql.Date(currentDate.getTime());
        Format formato = new SimpleDateFormat("dd-MM-yyyy");
        String s = formato.format(now);
        jTextField2.setText(s);
        jTextField2.setEnabled(false);
        jDateChooser3.setEnabled(false);
        jDateChooser4.setEnabled(false);

        Model.addColumn("Emisión");
        Model.addColumn("Del");
        Model.addColumn("Al");
        Model.addColumn("Del 2do");
        Model.addColumn("Al  2do");
        Model.addColumn("Días Vacaciones");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);




    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SMU - Acción de personal");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Nombre: ");

        jLabel4.setText("Apellido:");

        jLabel5.setText("Código");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Rangos de fechas"));

        jLabel7.setText("De:");

        jLabel8.setText("Hasta:");

        jLabel9.setText("De:");

        jLabel10.setText("Hasta:");

        jCheckBox1.setText("Dos intervalos");
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseReleased(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addGap(90, 90, 90))
        );

        jLabel6.setText("Fecha de emisión:");

        jButton3.setText("Cambiar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("Procesar");
        jButton1.setToolTipText("Guarda y Genera documento de excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel13.setText("Elaborado por:");

        jLabel11.setText("Disponibles:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Detalle"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setColumnSelectionAllowed(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addContainerGap())
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Planilla", "Factura" }));

        jLabel15.setText("Tipo Documento:");

        jCheckBox2.setSelected(true);
        jCheckBox2.setText("Borrador");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Campus Central", "Campus Altiplano", "Campus Sur", "CAS" }));

        jLabel16.setText("Campus:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "John McCracken", "Licda. Beatriz Lopez", "Licda. Maria Renee Lopez", "Licda. Celia Cordón", "Licda. Silvia Sosa" }));

        jLabel19.setText("Firma:");

        jButton4.setText("Generar");
        jButton4.setToolTipText("Genera documento de excel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

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

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Días Festivos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Opciones");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setText("Acerca de");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel16))
                        .addContainerGap(691, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox2)
                                .addGap(34, 34, 34))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel19)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(93, 93, 93)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton4)
                                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel5))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField1)
                                            .addComponent(jTextField2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)))
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel13, jLabel15, jLabel16, jLabel3, jLabel4, jLabel5, jLabel6});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jCheckBox2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jButton3))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton4))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void AbrirHome() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(false);
        home h = new home();
        h.setVisible(true);

    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        buscarColaborador();


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jDateChooser1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jDateChooser1AncestorAdded
    }//GEN-LAST:event_jDateChooser1AncestorAdded

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        firma = (String) jComboBox3.getSelectedItem();
        tipoColaborador = jComboBox1.getSelectedIndex();
        tipoCampus = jComboBox2.getSelectedIndex();
        //JOptionPane.showMessageDialog(null, tipoCampus);
        //factura = 1 -- planilla = 0
        String f1 = null;
        String f2 = null;
        String f3 = null;
        String f4 = null;
        if (jCheckBox2.isSelected() == true) {
            tipoDocumento = 1;//Borrador
        } else {
            tipoDocumento = 0;//DocumentoDirecto
        }

        boolean generar = false;
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        Format formatterDisplay = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formatterBase = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formatterDisplayf = new SimpleDateFormat("dd-MM-yyyy");
        int diasVacaciones = 0;

        String rangosFechas = null;

        Date ultimo = null;
        Date primero = null;
        Date fechaEmision = null;
        try {
            Date emision = formatterDisplayf.parse(jTextField2.getText());
            fechaEmision = (emision);


        } catch (ParseException ex) {
            Logger.getLogger(vacaciones.class.getName()).log(Level.SEVERE, null, ex);
        }


        //fecha 1
        Date d1 = jDateChooser1.getDate();
        Date d2 = jDateChooser2.getDate();
        if (Calculos.verificarQueHayaFecha(d1, d2) == true) {
            diasVacaciones = Calculos.EntreFechas(d1, d2);
            //jLabel11.setText("Días: " + Integer.toString(diasVacaciones));

            f1 = formatterDisplay.format(d1);
            f2 = formatterDisplay.format(d2);

            rangosFechas = "Del: " + f1 + " al " + f2;
            ultimo = d2;
            primero = d1;
            generar = true;
        }
        //JOptionPane.showMessageDialog(null, "Dias:"+ Integer.toString(dayDiff1)+" Dias fin de semana:"+Integer.toString(quitarFinSemana));
//fecha 2    
        Date d3 = null;
        Date d4 = null;
        if (jCheckBox1.isSelected() == true) {
            generar = false;
            d3 = jDateChooser3.getDate();
            d4 = jDateChooser4.getDate();
            if (Calculos.verificarQueHayaFecha(d2, d3) == true & Calculos.verificarQueHayaFecha(d3, d4) == true) {
                int diasVacaciones2 = Calculos.EntreFechas(d3, d4);
                //jLabel12.setText("Días: " + Integer.toString(diasVacaciones2));
                diasVacaciones = diasVacaciones + diasVacaciones2;
                f3 = formatterDisplayf.format(d3);
                f4 = formatterDisplayf.format(d4);
                rangosFechas = rangosFechas + ". Del " + f3 + " al " + f4;
                ultimo = d4;
                generar = true;
//            x.actualizar();
            }



            //JOptionPane.showMessageDialog(null, "Dias:"+ Integer.toString(dayDiff2)+" Dias fin de semana:"+Integer.toString(quitarFinSemana2));
        }
        if (generar == true) {



            Excel x = new Excel();
            try {
                ultimo = Calculos.regresaLaborar(ultimo);

                String fechaRetorno = formatter.format(ultimo);
                String fechaEmision2 = formatter.format(fechaEmision);


                x.actualizar(d1, d2, d3, d4, jTextField1.getText(), diasVacaciones, rangosFechas, ultimo, primero, elaboradoPor, fechaEmision, tipoColaborador, tipoDocumento, tipoCampus, firma);
                x.generar(d1, d2, d3, d4, jTextField1.getText(), diasVacaciones, rangosFechas, ultimo, primero, elaboradoPor, fechaEmision, tipoColaborador, tipoDocumento, tipoCampus, firma);
                actualizarTabla(jTextField1.getText());
                buscarColaborador();
            } catch (Exception ex) {
                Logger.getLogger(vacaciones.class.getName()).log(Level.SEVERE, null, ex);
            }



        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jDateChooser1HierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jDateChooser1HierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1HierarchyChanged

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(1);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(false);
        home h = new home();
        h.setVisible(true);
        h.fColaboradores();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jDateChooser3HierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jDateChooser3HierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser3HierarchyChanged

    private void jDateChooser3AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jDateChooser3AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser3AncestorAdded

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected() == true) {
            jDateChooser3.setEnabled(true);
            jDateChooser4.setEnabled(true);
        }
        if (jCheckBox1.isSelected() == false) {
            jDateChooser3.setEnabled(false);
            jDateChooser4.setEnabled(false);
            jDateChooser3.setDate(null);
            jDateChooser4.setDate(null);
        }        //JOptionPane.showMessageDialog(null, jCheckBox1.isSelected());

    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1MouseReleased

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void jDateChooser2InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jDateChooser2InputMethodTextChanged
    }//GEN-LAST:event_jDateChooser2InputMethodTextChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jTextField2.setText("yyyy-MM-dd");
        jTextField2.setEnabled(true);
        jTextField2.setFocusable(true);
        jButton3.setEnabled(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Boolean continuar = false;


        while (continuar == false) {
            String codigoElaboradorString = JOptionPane.showInputDialog(null, "Por favor ingrese su número de colaborador", "Indentifiquese", JOptionPane.QUESTION_MESSAGE);

            if (Calculos.isInt(codigoElaboradorString) == true) {
                try {
                    List<Colaboradores> colaboradores = col.listarColaboradorCodigo(codigoElaboradorString, Conexion.getConnection());
                    for (Colaboradores c : colaboradores) {
                        jLabel14.setText(c.getNombre() + " " + c.getApellido());
                        elaboradoPor = jLabel14.getText();
                        continuar = true;
                    }

                } catch (Exception ex) {
                    Logger.getLogger(vacaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (codigoElaboradorString == null) {


                continuar = true;
                AbrirHome();
                //h.fColaboradores();


            }


        }
    }//GEN-LAST:event_formWindowOpened

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        int key = evt.getKeyCode();
        if (evt.getSource() == jTextField1) {
            if (key == KeyEvent.VK_ENTER) {
                buscarColaborador();        // TODO add your handling code here:
            }
        }

    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        firma = (String) jComboBox3.getSelectedItem();
        tipoColaborador = jComboBox1.getSelectedIndex();
        tipoCampus = jComboBox2.getSelectedIndex();
        //JOptionPane.showMessageDialog(null, tipoCampus);
        //factura = 1 -- planilla = 0
        String f1 = null;
        String f2 = null;
        String f3 = null;
        String f4 = null;
        if (jCheckBox2.isSelected() == true) {
            tipoDocumento = 1;//Borrador
        } else {
            tipoDocumento = 0;//DocumentoDirecto
        }

        boolean generar = false;
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        Format formatterDisplay = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formatterBase = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formatterDisplayf = new SimpleDateFormat("dd-MM-yyyy");
        int diasVacaciones = 0;

        String rangosFechas = null;

        Date ultimo = null;
        Date primero = null;
        Date fechaEmision = null;
        try {
            Date emision = formatterDisplayf.parse(jTextField2.getText());
            fechaEmision = (emision);


        } catch (ParseException ex) {
            Logger.getLogger(vacaciones.class.getName()).log(Level.SEVERE, null, ex);
        }


        //fecha 1
        Date d1 = jDateChooser1.getDate();
        Date d2 = jDateChooser2.getDate();
        if (Calculos.verificarQueHayaFecha(d1, d2) == true) {
            diasVacaciones = Calculos.EntreFechas(d1, d2);
            //jLabel11.setText("Días: " + Integer.toString(diasVacaciones));

            f1 = formatterDisplay.format(d1);
            f2 = formatterDisplay.format(d2);

            rangosFechas = "Del: " + f1 + " al " + f2;
            ultimo = d2;
            primero = d1;
            generar = true;
        }
        //JOptionPane.showMessageDialog(null, "Dias:"+ Integer.toString(dayDiff1)+" Dias fin de semana:"+Integer.toString(quitarFinSemana));
//fecha 2    
        Date d3 = null;
        Date d4 = null;
        if (jCheckBox1.isSelected() == true) {
            generar = false;
            d3 = jDateChooser3.getDate();
            d4 = jDateChooser4.getDate();
            if (Calculos.verificarQueHayaFecha(d2, d3) == true & Calculos.verificarQueHayaFecha(d3, d4) == true) {
                int diasVacaciones2 = Calculos.EntreFechas(d3, d4);
                //jLabel12.setText("Días: " + Integer.toString(diasVacaciones2));
                diasVacaciones = diasVacaciones + diasVacaciones2;
                f3 = formatterDisplay.format(d3);
                f4 = formatterDisplay.format(d4);
                rangosFechas = rangosFechas + ". Del " + f3 + " al " + f4;
                ultimo = d4;
                generar = true;
//            x.actualizar();
            }



            //JOptionPane.showMessageDialog(null, "Dias:"+ Integer.toString(dayDiff2)+" Dias fin de semana:"+Integer.toString(quitarFinSemana2));
        }
        if (generar == true) {



            Excel x = new Excel();
            try {
                ultimo = Calculos.regresaLaborar(ultimo);

                String fechaRetorno = formatter.format(ultimo);
                String fechaEmision2 = formatter.format(fechaEmision);

                //codigo, Dias, rangoFechas, regresoLaborar, incioVacaciones, elaboradoPor, fechaEmision
                x.generar(d1, d2, d3, d4, jTextField1.getText(), diasVacaciones, rangosFechas, ultimo, primero, elaboradoPor, fechaEmision, tipoColaborador, tipoDocumento, tipoCampus, firma);
                //JOptionPane.showMessageDialog(null,asd+"asd");
                //jTextField3.setText(asd);
            } catch (Exception ex) {
                Logger.getLogger(vacaciones.class.getName()).log(Level.SEVERE, null, ex);
            }



        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed


    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (!(jTable1.getSelectedRowCount() > 0)) {
            JOptionPane.showMessageDialog(null, "Para ejecutar esta opción debe selecciónar a un colaborador", "ADVERTENCIA", 2);

        } else {
            Object sele0 = jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0);
            Object sele1 = jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 1);
            Object sele2 = jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 2);
            Object sele3 = jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 3);
            Object sele4 = jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 4);


            String paso0 = sele0.toString();
            String paso1 = sele1.toString();
            String paso2 = sele2.toString();




            SimpleDateFormat display = new SimpleDateFormat("dd-MM-yyyy");

            try {
                Date emision = new SimpleDateFormat("yyyy-MM-dd").parse(paso0);
                //System.out.println(emision);
                String emision1 = display.format(emision);
                jTextField2.setText(null);
                jTextField2.setText(emision1);

                Date DEL = new SimpleDateFormat("yyyy-MM-dd").parse(paso1);
                //System.out.println(DEL);
                jDateChooser1.setDate(null);
                jDateChooser1.setDate(DEL);

                Date AL = new SimpleDateFormat("yyyy-MM-dd").parse(paso2);
                //System.out.println(AL);
                jDateChooser2.setDate(null);
                jDateChooser2.setDate(AL);

                if (sele3 == null) {
                    jDateChooser3.setDate(null);
                    jDateChooser4.setDate(null);
                    jDateChooser3.setEnabled(false);
                    jDateChooser4.setEnabled(false);
                    jCheckBox1.setSelected(false);
                } else {
                    String paso3 = sele3.toString();
                    String paso4 = sele4.toString();
                    jDateChooser3.setDate(null);
                    jDateChooser4.setDate(null);
                    jDateChooser3.setEnabled(true);
                    jDateChooser4.setEnabled(true);
                    jCheckBox1.setSelected(true);
                    Date DEL2 = new SimpleDateFormat("yyyy-MM-dd").parse(paso3);
                    //System.out.println(DEL2);            
                    jDateChooser3.setDate(DEL2);

                    Date AL2 = new SimpleDateFormat("yyyy-MM-dd").parse(paso4);
                    //System.out.println(AL2);            
                    jDateChooser4.setDate(AL2);

                }

            } catch (ParseException ex) {
                Logger.getLogger(vacaciones.class.getName()).log(Level.SEVERE, null, ex);
            }



            //JOptionPane.showMessageDialog(null, "emisión: " + paso0);            


            //jTextField2.setText(paso0); //emision
            //JOptionPane.showMessageDialog(null, "vacAl" + sele2);
            //JOptionPane.showMessageDialog(null, "vacAl" + sele2);
//            Integer.parseInt(sele.toString());
//            try {
//                Listar(paso);
//                jButton5.setEnabled(true);
//                jButton4.setEnabled(true);
//
//            } catch (Exception ex) {
//                Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            //}
//
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        AcercaDe d;
        try {
            d = new AcercaDe();
            d.setVisible(true);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            d.setLocation(dim.width / 2 - d.getSize().width / 2, dim.height / 2 - d.getSize().height / 2);

        } catch (IOException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        DiasFestivosVS d;
        try {
            d = new DiasFestivosVS();
            d.setVisible(true);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            d.setLocation(dim.width / 2 - d.getSize().width / 2, dim.height / 2 - d.getSize().height / 2);
        
        } catch (Exception ex) {
            Logger.getLogger(vacaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
            java.util.logging.Logger.getLogger(vacaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vacaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vacaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vacaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new vacaciones().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("sol.gif")));
    }
}

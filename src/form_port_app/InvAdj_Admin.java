/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form_port_app;

import com.mysql.jdbc.Connection;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author SSwisher & WStevens
 * 
 ♜♞♝♚♛♝♞♜
 ♟♟♟♟♟♟♟♟
 ▓░▓░▓░▓░
 ░▓░▓░▓░▓
 ▓░▓░▓░▓░
 ░▓░▓░▓░▓
 ♙♙♙♙♙♙♙♙
 ♖♘♗♔♕♗♘♖
 * 
 */

// this class provides a list of all the forms create in each status
// the user can click on the form name and open the existing form in the first 3 lists
// the ready for export list is for IC use only and it creates the .csv file for inventory adjustment compatible files with Retail pro
public class InvAdj_Admin extends javax.swing.JFrame {

    public static DefaultListModel listpending = new DefaultListModel();
    public static DefaultListModel listapproved = new DefaultListModel();
    public static DefaultListModel listinprocess = new DefaultListModel();
    public static DefaultListModel listreadyforexport = new DefaultListModel();
    public static String frmNm = null;
    public static int aprBtSt = 0;
    public static FileWriter fWriter;
    public static FileWriter fileWriter;
    public static FileWriter tWriter;
    public static BufferedWriter writer;
    public static BufferedWriter hWriter;
    public static BufferedWriter cwriter;
    private static Component frame;
    public static String wrnty = null;
    public static Object sel = null;
    public static String strNm = null;
    public static String StrCode = null;
    public static String GetStr = null;
    public static String ExFormNm = null;
    public static String frmDate = null;
    protected String store = null;
    private String str = null;
    protected String sku = null;
    protected String qty = null;
    protected String skudesc2 = null;
    protected String reason = null;
    protected String dsc_dmg = null;
    protected String new_used = null;
    protected String ln_date = null;
    protected String sskuvendor = null;
    protected String sskuvpnum = null;
    protected String ord_c = null;
    protected String manuf_inspec = null;
    protected String frmNmI = null;
    protected String frmNmP = null;
    protected String frmNmA = null;
    protected String ebasfrmNm = null;
    protected ArrayList<String> formArray = new ArrayList<>();
    protected ArrayList<String> inPArray = new ArrayList<>();
    protected ArrayList<String> pendArray = new ArrayList<>();
    protected ArrayList<String> apprArray = new ArrayList<>();
    protected ArrayList<String> ExformNmArray = new ArrayList<>();    
    protected ArrayList<String> strArray = new ArrayList<>();
    protected ArrayList<String> skuArray = new ArrayList<>();
    protected ArrayList<String> qtyArray = new ArrayList<>();
    protected ArrayList<String> skdesc2Array = new ArrayList<>();
    protected ArrayList<String> reasnArray = new ArrayList<>(); 
    protected ArrayList<String> dscdmgArray = new ArrayList<>();
    protected ArrayList<String> newusedArray = new ArrayList<>();
    protected ArrayList<String> lndateArray = new ArrayList<>();
    protected ArrayList<String> sskuvendrArray = new ArrayList<>();
    protected ArrayList<String> sskuvpnumArray = new ArrayList<>();
    protected ArrayList<String> ordcArray = new ArrayList<>();
    protected ArrayList<String> ManufInspecArray = new ArrayList<>();
    protected ArrayList<String> strconvertArray = new ArrayList<>();
    protected ArrayList<String> strcodeArray = new ArrayList<>();
    Connection connect;
    
    /**
     * Creates new form (GUI)
     */
    
    public InvAdj_Admin() {
        initComponents();
        listpending.clear();
        listapproved.clear();
        listinprocess.clear();
        listreadyforexport.clear();
        if (DBConnect.TestEnviron == true) {
            Test_Label.setVisible(true);
        } else if (DBConnect.TestEnviron == false){
            Test_Label.setVisible(false);
        }
        VersionLabel.setText("Version " + DBConnect.Version);
        try {
            GetForms_InvAdj.ChkAllForms();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InvAdj_Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (GetForms_InvAdj.usrType.equals("ic")) {
            createExportButton.setVisible(true); 
            All_Stores_Report_Btn.setVisible(true);
            All_Completed_Report_Btn.setVisible(true);
            RTV_Export_Btn.setVisible(true);
            Deleted_Report_Btn.setVisible(true);
        } else {
            createExportButton.setVisible(false);
            All_Stores_Report_Btn.setVisible(false);
            All_Completed_Report_Btn.setVisible(false);
            RTV_Export_Btn.setVisible(false);
            Deleted_Report_Btn.setVisible(false);
        }
         // sort(listpending);
    }    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inprocList = new javax.swing.JList(listinprocess);
        jScrollPane2 = new javax.swing.JScrollPane();
        readyList = new javax.swing.JList(listreadyforexport);
        pendBtn = new javax.swing.JButton();
        apprBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        closeBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        pendList = new javax.swing.JList(listpending);
        inprocBtn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        apprList = new javax.swing.JList(listapproved);
        jLabel2 = new javax.swing.JLabel();
        createExportButton = new javax.swing.JButton();
        All_Stores_Report_Btn = new javax.swing.JButton();
        All_Completed_Report_Btn = new javax.swing.JButton();
        RTV_Export_Btn = new javax.swing.JButton();
        Test_Label = new javax.swing.JLabel();
        VersionLabel = new javax.swing.JLabel();
        Deleted_Report_Btn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inventory Adjustment App Admin Panel");
        setBounds(new java.awt.Rectangle(50, 20, 0, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createTitledBorder("STORE FORMS (IN-PROCESS)"));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(280, 420));

        inprocList.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        inprocList.setModel(listinprocess);
        inprocList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inprocListMouseClicked(evt);
            }
        });
        inprocList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inprocListKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(inprocList);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(" READY FOR EXPORT (IC)"));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(280, 420));

        readyList.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        readyList.setModel(listreadyforexport);
        jScrollPane2.setViewportView(readyList);

        pendBtn.setBackground(new java.awt.Color(255, 255, 0));
        pendBtn.setText("View All");
        pendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendBtnActionPerformed(evt);
            }
        });

        apprBtn.setBackground(new java.awt.Color(255, 255, 0));
        apprBtn.setText("View All");
        apprBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apprBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Inventory Adjustment - Admin Panel (Existing Forms)");

        closeBtn.setBackground(new java.awt.Color(255, 0, 0));
        closeBtn.setText("Close");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setViewportBorder(javax.swing.BorderFactory.createTitledBorder("DM APPROVAL (PENDING)"));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(280, 420));

        pendList.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pendList.setModel(listpending);
        pendList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pendListMouseClicked(evt);
            }
        });
        pendList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pendListKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(pendList);

        inprocBtn.setBackground(new java.awt.Color(255, 255, 0));
        inprocBtn.setText("View All");
        inprocBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inprocBtnActionPerformed(evt);
            }
        });

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setViewportBorder(javax.swing.BorderFactory.createTitledBorder("NEEDS IC APPROVAL (DM APPROVED) "));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(280, 420));

        apprList.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        apprList.setModel(listapproved);
        apprList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                apprListMouseClicked(evt);
            }
        });
        apprList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                apprListKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(apprList);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cglogo3.jpg"))); // NOI18N

        createExportButton.setBackground(new java.awt.Color(0, 255, 0));
        createExportButton.setText("Create Export");
        createExportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createExportButtonActionPerformed(evt);
            }
        });

        All_Stores_Report_Btn.setBackground(new java.awt.Color(0, 0, 255));
        All_Stores_Report_Btn.setForeground(new java.awt.Color(255, 255, 255));
        All_Stores_Report_Btn.setText("All Stores Report");
        All_Stores_Report_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                All_Stores_Report_BtnActionPerformed(evt);
            }
        });

        All_Completed_Report_Btn.setBackground(new java.awt.Color(0, 0, 255));
        All_Completed_Report_Btn.setForeground(new java.awt.Color(255, 255, 255));
        All_Completed_Report_Btn.setText("All Completed Report");
        All_Completed_Report_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                All_Completed_Report_BtnActionPerformed(evt);
            }
        });

        RTV_Export_Btn.setBackground(new java.awt.Color(204, 204, 204));
        RTV_Export_Btn.setText("RTV Export");
        RTV_Export_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RTV_Export_BtnActionPerformed(evt);
            }
        });

        Test_Label.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        Test_Label.setText("(Test Environment)");

        VersionLabel.setText("Version 1.109");

        Deleted_Report_Btn.setBackground(new java.awt.Color(204, 0, 0));
        Deleted_Report_Btn.setText("Deleted Report");
        Deleted_Report_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Deleted_Report_BtnActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("** If DL is logged in, Closing this window will exit IAN (this application) **");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(inprocBtn)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pendBtn)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel1))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(168, 168, 168)
                                        .addComponent(Test_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(VersionLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(apprBtn)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(All_Stores_Report_Btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(All_Completed_Report_Btn)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Deleted_Report_Btn)
                                .addGap(88, 88, 88)
                                .addComponent(RTV_Export_Btn))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(createExportButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(closeBtn))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane1, jScrollPane2, jScrollPane3, jScrollPane4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Test_Label)
                            .addComponent(VersionLabel))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(All_Stores_Report_Btn)
                            .addComponent(All_Completed_Report_Btn)
                            .addComponent(RTV_Export_Btn)
                            .addComponent(Deleted_Report_Btn)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(apprBtn)
                                .addComponent(createExportButton))
                            .addComponent(closeBtn)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pendBtn)
                            .addComponent(inprocBtn))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane1, jScrollPane2, jScrollPane3, jScrollPane4});

        jScrollPane5.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        if (GetForms_InvAdj.usrType.equals("dl")) {
            GetForms.FrmExt();
        } else {        
            this.dispose();
        }
    }//GEN-LAST:event_closeBtnActionPerformed

    // if form is selected in admin panel list, open the corresponding form
    private void inprocListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inprocListKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            aprBtSt = 1;
            String[] args = null;
            String getValue = inprocList.getSelectedValuesList().toString();
            frmNm = getValue.substring(1, getValue.length() - 1);
            if (frmNm.startsWith("ias")) {
                IAS2.main(args);
            } else if (frmNm.startsWith("ebas")) {
                EBAS.main(args);
            } else if (frmNm.startsWith("nebas")) {
                NEBAS.main(args);
            }
        }
    }//GEN-LAST:event_inprocListKeyPressed
    // shows all pending line data when view all button is pressed 
    private void pendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendBtnActionPerformed
        String[] args = null;
        ViewPending_EBAS.main(args);
        //JOptionPane.showMessageDialog(frame, "If you do not see line data press the appropriate button on the right to view data");
    }//GEN-LAST:event_pendBtnActionPerformed
// shows all approved line data when view all button is pressed 
    private void apprBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apprBtnActionPerformed
        String[] args = null;
        ViewApproved_EBAS.main(args);
        // JOptionPane.showMessageDialog(frame, "If you do not see line data press the appropriate button on the right to view data");
    }//GEN-LAST:event_apprBtnActionPerformed
    // opening the existing form in the pending list
    private void pendListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pendListKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String[] args = null;
            String getValue = pendList.getSelectedValuesList().toString();
            frmNm = getValue.substring(1, getValue.length() - 1);
        if (GetForms_InvAdj.usrType.equals("ic") || GetForms_InvAdj.usrType.equals("dl")) {
            if (frmNm.startsWith("ias")) {
                IAS2.main(args);
            } else if (frmNm.startsWith("ebas")) {
                EBAS.main(args);
            } else if (frmNm.startsWith("nebas")) {
                NEBAS.main(args);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "You must be DL or IC to view these forms\nPress 'View All' button on bottom to view line data");
        }
        }
    }//GEN-LAST:event_pendListKeyPressed
     // shows all in process line data when view all button is pressed 
    private void inprocBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inprocBtnActionPerformed
        String[] args = null;
        ViewInProcess_EBAS.main(args);
       // JOptionPane.showMessageDialog(frame, "If you do not see line data press the appropriate button on the right to view data");
    }//GEN-LAST:event_inprocBtnActionPerformed
    // opening the existing form in the in process list
    private void inprocListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inprocListMouseClicked
        if (evt.getClickCount() == 2) {
            aprBtSt = 1;
            String[] args = null;
            String getValue = inprocList.getSelectedValuesList().toString();
            frmNm = getValue.substring(1, getValue.length() - 1);
            if (frmNm.startsWith("ias")) {
                IAS2.main(args);
            } else if (frmNm.startsWith("ebas")) {
                EBAS.main(args);
            } else if (frmNm.startsWith("nebas")) {
                NEBAS.main(args);
            }
        }
    }//GEN-LAST:event_inprocListMouseClicked
        // opening the existing form in the pending list
    private void pendListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pendListMouseClicked
    if (evt.getClickCount() == 2) {
            String[] args = null;
            String getValue = pendList.getSelectedValuesList().toString();
            frmNm = getValue.substring(1, getValue.length() - 1);
        if (GetForms_InvAdj.usrType.equals("ic") || GetForms_InvAdj.usrType.equals("dl")) {
            if (frmNm.startsWith("ias")) {
                IAS2.main(args);
            } else if (frmNm.startsWith("ebas")) {
                EBAS.main(args);
            } else if (frmNm.startsWith("nebas")) {
                NEBAS.main(args);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "You must be DL or IC to view these forms\nPress 'View All' button on bottom to view line data");
        }
    }
    }//GEN-LAST:event_pendListMouseClicked
        // opening the existing form in the approved list
    private void apprListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_apprListMouseClicked
        if (evt.getClickCount() == 2) {
            String[] args = null;
            String getValue = apprList.getSelectedValuesList().toString();
            frmNm = getValue.substring(1, getValue.length() - 1);
        if (GetForms_InvAdj.usrType.equals("ic")) {
            if (frmNm.startsWith("ias")) {
                IAS2.main(args);
            } else if (frmNm.startsWith("ebas")) {
                EBAS.main(args);
            } else if (frmNm.startsWith("nebas")) {
                NEBAS.main(args);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "You must be IC to view these forms\nPress 'View All' button on bottom to view line data");
        }
        }
    }//GEN-LAST:event_apprListMouseClicked
        // opening the existing form in the pending list
    private void apprListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apprListKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String[] args = null;
            String getValue = apprList.getSelectedValuesList().toString();
            frmNm = getValue.substring(1, getValue.length() - 1);
        if (GetForms_InvAdj.usrType.equals("ic")) {
            if (frmNm.startsWith("ias")) {
                IAS2.main(args);
            } else if (frmNm.startsWith("ebas")) { 
                EBAS.main(args);
            } else if (frmNm.startsWith("nebas")) {
                NEBAS.main(args);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "You must be IC to view these forms\nPress 'View All' button on bottom to view line data");
        }
        }
    }//GEN-LAST:event_apprListKeyPressed
    // creates exports and changes status in database
    private void createExportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createExportButtonActionPerformed
    int response = JOptionPane.showConfirmDialog(null, "Do you want to Create Export?", "Confirm Export",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");      
    } else if (response == JOptionPane.YES_OPTION) {
     // System.out.println("Yes button clicked");
        try {
                // change status of form to completed
                FormCompelted();
                } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(InvAdj_Admin.class.getName()).log(Level.SEVERE, null, ex);
                }   
                // transfer export
                try {
                formTransfer();
                } catch (ClassNotFoundException | SQLException | IOException ex) {
                Logger.getLogger(InvAdj_Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
                // copy of 
                try {
                formTransferCopy();
                } catch (ClassNotFoundException | SQLException | IOException ex) {
                Logger.getLogger(InvAdj_Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
                // adjustment export
                try {   
                formAdjustments();
                } catch (ClassNotFoundException | SQLException | IOException ex) {
                Logger.getLogger(InvAdj_Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
                // copy sent to warren's tech toolbox on I:\
                try {
                formAdjustmentsCopy();
                } catch (ClassNotFoundException | SQLException | IOException ex) {
                Logger.getLogger(InvAdj_Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                removeTempFormsAfterCompletion();
                } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(InvAdj_Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
                InvAdj_Admin.Rpnt();
                JOptionPane.showMessageDialog(frame, "All Data from Selected Forms has been Exported");
            } else if (response == JOptionPane.CLOSED_OPTION) {
           // this.dispose();
            System.out.println("JOptionPane closed");
    }
    }//GEN-LAST:event_createExportButtonActionPerformed

    private void All_Stores_Report_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_All_Stores_Report_BtnActionPerformed
        String[] args = null;
        JOptionPane.showMessageDialog(frame, "Pulling Records");
        try {                                        
                 Statement s = DBConnect.connection.createStatement();
                 
                 String DeletePrevRecords = "DELETE FROM unprocessed";
                 s.execute(DeletePrevRecords);
                 
                 // pulls all form names and puts into array list
                /* String selFrmIStatus = "SELECT Form_Name As frmnme FROM existing_forms WHERE Status = 'In-Process'"; // AND Store = '" + GtStore.store + "'";
                 s.execute(selFrmIStatus);
                 try (ResultSet rs02 = s.getResultSet()) { 
                     while (rs02.next()) {
                         String frmnm = rs02.getString("frmnme");
                         frmNmI = frmnm;
                         inPArray.add(frmNmI);
                    }
                     // uses form name strings to insert each line of data one at a time into unprocessed table
                     for (String inPArray1 : inPArray) {
                        String insrtInpro = "INSERT IGNORE INTO unprocessed (SELECT Form_Name, Line, Sku, Qty, Description1, Orig_Sku, Description2, Attribute2, Size2, Orig_Retail, Reason, "
                        + "Desc_Damage, Cust_Satisf, Ln_Date, New_Sku, Second_Cost, Second_Sku_Vendor, Second_Sku_VPNum, "
                        + "First_DCS, Second_DCS FROM " + inPArray1 + " WHERE Sku IS NOT NULL AND Form_Name = '" + inPArray1 + "')";
                        s.execute(insrtInpro);
                    } 
                }
                 */
                 // pulls all form names and puts into array list
                  String selFrmPStatus = "SELECT Form_Name As frmnme FROM existing_forms WHERE Status = 'Pending'"; // AND Store = '" + GtStore.store + "'";
                 s.execute(selFrmPStatus);
                 try (ResultSet rs02 = s.getResultSet()) {
                     while (rs02.next()) {
                         String frmnm = rs02.getString("frmnme");
                         frmNmP = frmnm;
                         pendArray.add(frmNmP);
                     }
                     // uses form name strings to insert each line of data one at a time into unprocessed table
                     for (String pendArray1 : pendArray) {
                         String insrtPend = "INSERT IGNORE INTO unprocessed (SELECT Form_Name, Line, Sku, Qty, Description1, Orig_Sku, Description2, Attribute2, Size2, Orig_Retail, Reason, "
                        + "Desc_Damage, Cust_Satisf, Ln_Date, New_Sku, Second_Cost, Second_Sku_Vendor, Second_Sku_VPNum, "
                        + "First_DCS, Second_DCS FROM " + pendArray1 + " WHERE Sku IS NOT NULL AND Form_Name = '" + pendArray1 + "')";
                        s.execute(insrtPend);
                    }     
                }
                 
                  // pulls all form names and puts into array list
                  String selFrmAStatus = "SELECT Form_Name As frmnme FROM existing_forms WHERE Status = 'Approved'"; // AND Store = '" + GtStore.store + "'";
                 s.execute(selFrmAStatus);
                 try (ResultSet rs02 = s.getResultSet()) {
                     while (rs02.next()) {
                         String frmnm = rs02.getString("frmnme");
                         frmNmA = frmnm;
                        apprArray.add(frmNmA);
                    }
                     // uses form name strings to insert each line of data one at a time into unprocessed table
                     for (String apprArray1 : apprArray) {
                         String insrtAppr = "INSERT IGNORE INTO unprocessed (SELECT Form_Name, Line, Sku, Qty, Description1, Orig_Sku, Description2, Attribute2, Size2, Orig_Retail, Reason, "
                        + "Desc_Damage, Cust_Satisf, Ln_Date, New_Sku, Second_Cost, Second_Sku_Vendor, Second_Sku_VPNum, "
                        + "First_DCS, Second_DCS FROM " + apprArray1 + " WHERE Sku IS NOT NULL AND Form_Name = '" + apprArray1 + "')";
                         s.execute(insrtAppr);
                     }  
                 }             
                 Reports.main(args);
             }       catch (SQLException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_All_Stores_Report_BtnActionPerformed

    private void All_Completed_Report_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_All_Completed_Report_BtnActionPerformed
        String[] args = null;
        All_Completed.main(args);
    }//GEN-LAST:event_All_Completed_Report_BtnActionPerformed

    private void RTV_Export_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RTV_Export_BtnActionPerformed
    int response = JOptionPane.showConfirmDialog(null, "Do you want to Create RTV Export?", "Confirm RTV Export",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
     // System.out.println("Yes button clicked");    
        try {
            Calendar currentDate = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
            String datenow = formatter.format(currentDate.getTime());
            
            try (Statement s = DBConnect.connection.createStatement()) {
            
                String DeletePrevRecords = "TRUNCATE invadj.rtv";
                s.execute(DeletePrevRecords);
                
                getRtvForms();
                /*
                String getFrmNm = "SELECT Form_Name As frmNme FROM completed_forms WHERE Form_Name LIKE 'ebas%' AND Status = 'Completed'";
                s.execute(getFrmNm);
                try (ResultSet rs1 = s.getResultSet()) {          
                while (rs1.next()) {
                    String frmNme = rs1.getString("frmNme");
                    ebasfrmNm = frmNme;
                    formArray.add(ebasfrmNm);
                    }
                }
                */
                
                insertRtvForms();
                /*
                for (String formArray1 : formArray) {
                    String insertFrms = "INSERT INTO rtv (SELECT * FROM completed_lines WHERE Form_Name = '" + formArray1 + "' AND Warranty = 'true')";
                    s.execute(insertFrms);
                }
                */
                
                getRtvStr();
                /*
                // **** This loop takes each store CG # and converts it to the Store 3 letter designation ****
                for (String formArray2 : formArray) {
                    // this statement pulls store from temp table for each form name
                String getStr = "SELECT Store As strcd FROM rtv WHERE Form_Name = '" + formArray2 + "'";
                s.execute(getStr);
                try (ResultSet rs01 = s.getResultSet()) {
                while (rs01.next()) {
                    GetStr = rs01.getString("StrCd");
                    strconvertArray.add(GetStr);
                    }
                }
                }
                */
                
                // takes collected store value and searches the store database table
                // ***** NEEDS LOOP SO THAT MULTIPLE STORES DO NOT GET THE SAME 3 LETTER STORE MNEMONIC *****
                for (String strconvertArray1 : strconvertArray) {
               // strNm = GetStr;
                String strConvert = "SELECT Store_Code As StrCd FROM stores WHERE CG_Store = '" + strconvertArray1 + "'";
                s.execute(strConvert);
                try (ResultSet rs01 = s.getResultSet()) {
                while (rs01.next()) {
                    StrCode = rs01.getString("StrCd");
                    strcodeArray.add(StrCode);
                    }
                    }
                }
                
                for (String strcodeArray1 : strcodeArray) {
                // this changes the store number to the store letters for each form name in this loop
                    String UpdateStore = "UPDATE rtv SET Store = '"+ strcodeArray1 + "' WHERE Form_Name LIKE 'ebas_" + strNm + "_%'";
                    s.execute(UpdateStore);
                    s.closeOnCompletion();
                }
            } catch (SQLException ex) {
                Logger.getLogger(InvAdj_Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try (Statement s1 = DBConnect.connection.createStatement()) {
                
                getRTVLines(); 
                /*
                String getRtv = "SELECT Store As str, Sku As sku, Qty As qty, Description2 As skudesc2, "
                    + "Reason As reasn, Desc_Damage As DscDmg, New_Used As nwUsd, Ln_Date As lnDte, Second_Sku_Vendor As sskuvendr, Second_Sku_VPNum As sskuvpnum, Ord_C$ As ordC, Manuf_Inspection As manufInspec "
                    + "FROM rtv WHERE Sku IS NOT NULL";
            s1.execute(getRtv);
            try (ResultSet rs1 = s1.getResultSet()) {
                while (rs1.next()) {
                    String stre = rs1.getString("str");
                    String sk = rs1.getString("sku");
                    String qt = rs1.getString("qty");
                    String skdsc2 = rs1.getString("skudesc2");
                    String reasn = rs1.getString("reasn");
                    String dscdmg = rs1.getString("DscDmg");
                    String newused = rs1.getString("nwUsd");
                    String lndate = rs1.getString("lnDte");
                    String sskuvendr = rs1.getString("sskuvendr");
                    String sskuvpnm = rs1.getString("sskuvpnum");
                    String ordC$ = rs1.getString("ordC");
                    String manufinspec = rs1.getString("manufInspec");
                    str = stre;
                    sku = sk;
                    qty = qt;
                    skudesc2 = skdsc2;
                    reason = reasn;
                    dsc_dmg = dscdmg;
                    new_used = newused;
                    ln_date = lndate;
                    sskuvendor = sskuvendr;
                    sskuvpnum = sskuvpnm;
                    ord_c = ordC$;
                    manuf_inspec = manufinspec;
                    strArray.add(str);
                    skuArray.add(sku);
                    qtyArray.add(qty);
                    skdesc2Array.add(skudesc2);
                    reasnArray.add(reason);
                    dscdmgArray.add(dsc_dmg);
                    newusedArray.add(new_used);
                    lndateArray.add(ln_date);
                    sskuvendrArray.add(sskuvendor);
                    sskuvpnumArray.add(sskuvpnum);
                    ordcArray.add(ord_c);
                    ManufInspecArray.add(manuf_inspec);
                    }              
                }
                */
            
                if (str == null) {
                    JOptionPane.showMessageDialog(frame, "No Warranty Records to Export");
                } else {
            
                fileWriter = new FileWriter("//sscfil03/groups/Inventory/Inventory Control/IAN/RTV Reports/RTV_" + datenow + ".csv");
                hWriter = new BufferedWriter(fileWriter);
                String header = "STORE,VENDOR,QTY,SKU,NEW OR USED,VENDOR PART #,VENDOR INVOICE #,ITEM DESC,REASON FOR RETURN,UNIT COST,EXT COST,RTV #,INSPECTION CODE";
                hWriter.write(header);
                hWriter.newLine();
                
                for (int count = 0; count < skuArray.size(); count++ ) {
                String line = strArray.get(count) + "," + sskuvendrArray.get(count) + "," + qtyArray.get(count) + "," + skuArray.get(count) + "," + newusedArray.get(count) + "," + sskuvpnumArray.get(count) + "," + "" + "," + skdesc2Array.get(count) + "," + reasnArray.get(count) +" "+ dscdmgArray.get(count) + "," + ordcArray.get(count) + "," + "" + "," + "" + ","+ ManufInspecArray.get(count);

                    hWriter.write(line);
                    hWriter.newLine();                    
                }     
                
                hWriter.close();
                
                String getFrms = "SELECT Form_Name As FrmNm FROM rtv WHERE Sku IS NOT NULL";
                s1.execute(getFrms);
                try (ResultSet rs1 = s1.getResultSet()) {
                    while (rs1.next()) {
                    ExFormNm = rs1.getString("FrmNm");
                    ExformNmArray.add(ExFormNm);
                    }
                }
                
                    for (String ExformNmArray1 : ExformNmArray) {
                        String upTable = "UPDATE completed_forms "
                                + "SET Status = 'RTV_Exported', Last_Updated = '" + datenow + "' WHERE Form_Name = '" + ExformNmArray1 + "'";
                        s1.execute(upTable);
                    }                
                JOptionPane.showMessageDialog(frame, "RTV Export Created");
                
            }
            } catch (SQLException ex) {
                Logger.getLogger(InvAdj_Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(InvAdj_Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        } else if (response == JOptionPane.CLOSED_OPTION) {
           // this.dispose();
      System.out.println("JOptionPane closed");
    }
    }//GEN-LAST:event_RTV_Export_BtnActionPerformed

    private void Deleted_Report_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Deleted_Report_BtnActionPerformed
        String[] args = null;
        DeleteReport.main(args);
    }//GEN-LAST:event_Deleted_Report_BtnActionPerformed
    // changes status to completed
     public static void FormCompelted() throws ClassNotFoundException, SQLException {
        try (Statement s = DBConnect.connection.createStatement()) {
            int[] selectedIx = readyList.getSelectedIndices();
            for (int i = 0; i < selectedIx.length; i++) {
            String upTable = "UPDATE completed_forms "
                    + "SET Status = 'Completed'"
                    + " WHERE Form_Name = '"+ readyList.getModel().getElementAt(selectedIx[i]) +"'";
            s.execute(upTable);
           // readyList.remove(selectedIx[i]);
            }
        }
    }
     // changes store number (110, 24) to store 3 letter designation (HAY, SD, PAC)
     
     // not working or being used yet // meant to fix form created date in header tables
     /*
     public static void frmdate() throws SQLException {
        Statement s = DBConnect.connection.createStatement();
        String frmdte = "SELECT Ln_Date As frmdate FROM '"+ frmNm +"'";
        s.execute(frmdte);
        try (ResultSet rs02 = s.getResultSet()) {
            while (rs02.next()) {
                String formdate = rs02.getString("frmdate");
                frmDate = formdate.substring(11);
            }                      
        }        
    } */
     
     // *** creates export for inventory transfers
    @SuppressWarnings("DeadBranch")
    public static void formTransfer() throws IOException, SQLException, ClassNotFoundException {
        // *** gets current time for export file name
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String datenow = formatter.format(currentDate.getTime());
        
        // loop that will only create export files for EBAS forms with warranty
        int[] selectedIx = readyList.getSelectedIndices(); 
        for (int i = 0; i < selectedIx.length; i++) {
          //  while (i < selectedIx.length) {
              sel = readyList.getModel().getElementAt(selectedIx[i]);
        if (sel.toString().substring(0,4).contentEquals("ebas")) { 
            // *** creates export file name and headers
        
        /*    
        if (DBConnect.TestEnviron = (true)) {
            fWriter = new FileWriter("G:/CGD/Cyclegear.com/Scott/Inventory Adjustment App/App_Exports/Transfers/TEST_TO_" + GtStore.store +"_"+ datenow + "_TEST.csv");
            writer = new BufferedWriter(fWriter);            
        } else {  */
            fWriter = new FileWriter("//sscfil03/groups/Inventory/Inventory Control/IAN/Transfers/TO_" + GtStore.store +"_"+ datenow + ".csv");
            writer = new BufferedWriter(fWriter);
      //  }        
            // String header = "slip_number,slip_type,out_store,in_store,item_number,quantity,price,comment,to_num";
            // writer.write(header);
            // writer.newLine();
               
        // *** loop for multiple form selection from list
        Statement s1 = DBConnect.connection.createStatement();
        selectedIx = readyList.getSelectedIndices(); 
        for (int j = 0; j < selectedIx.length; j++) {
         //  while (i < selectedIx.length) {
              sel = readyList.getModel().getElementAt(selectedIx[j]);
         // pulls data from database one at a time per form selection from list
        String selTable01 = "SELECT Sku As s, Qty As q, Orig_Retail As prce, Orig_Sku As orgsk " //Store As strcd "
                    + "FROM completed_lines WHERE Form_Name = '" + sel + "' AND Warranty = 'true'";
        // *** changes store mnemonic for export files
        
      //   private static void strConversion() throws SQLException, ClassNotFoundException {    
      //   Statement s = DBConnect.connection.createStatement();
         String getStr = "SELECT Store As strcd FROM completed_lines WHERE Form_Name = '" + sel + "'";
         s1.execute(getStr);
         try (ResultSet rs01 = s1.getResultSet()) {             
                while (rs01.next()) {
                    GetStr = rs01.getString("StrCd");
                }   
           }
        
         strNm = GetStr;
         String  strConvert = "SELECT Store_Code As StrCd FROM stores WHERE CG_Store = '"+ strNm +"'";
         s1.execute(strConvert);
         try (ResultSet rs01 = s1.getResultSet()) {             
                while (rs01.next()) {
                    StrCode = rs01.getString("StrCd");
                }   
           }
           
            // *** fills export file with data 
            s1.execute(selTable01);
            try (ResultSet rs01 = s1.getResultSet()) {             
                while (rs01.next()) {                   
                    String sku = rs01.getString("s");
                    String qty = rs01.getString("q");
                    String price = rs01.getString("prce");                   
                    String line = "" + "," + "out" + "," + StrCode + "," + "VTX" + "," + sku + "," + qty + ", " + price + "," + "EBW";
                    // *** Original line of code
                    // String line = "" + "," + "out" + "," + StrCode + "," + "RTV" + "," + sku + "," + qty + ", " + price + "," + "EBW";
                    
                    writer.write(line);
                    writer.newLine();
                }
            }                 
      }
      writer.close();
      }
    }
    }
    
    @SuppressWarnings("DeadBranch")
    public static void formTransferCopy() throws IOException, SQLException, ClassNotFoundException {
        // *** gets current time for export file name
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String datenow = formatter.format(currentDate.getTime());
        
        // loop that will only create export files for EBAS forms with warranty
        int[] selectedIx = readyList.getSelectedIndices(); 
        for (int i = 0; i < selectedIx.length; i++) {
          //  while (i < selectedIx.length) {
              sel = readyList.getModel().getElementAt(selectedIx[i]);
        if (sel.toString().substring(0,4).contentEquals("ebas")) { 
            // *** creates export file name and headers
        
        /*    
        if (DBConnect.TestEnviron = (true)) {
            fWriter = new FileWriter("G:/CGD/Cyclegear.com/Scott/Inventory Adjustment App/App_Exports/Transfers/TEST_TO_" + GtStore.store +"_"+ datenow + "_TEST.csv");
            writer = new BufferedWriter(fWriter);            
        } else {  */
            tWriter = new FileWriter("//sscfil03/groups/Merch/Purchasing/Warren/IAN transfers Copy/TO_" + GtStore.store + "_" + datenow + "_copy.csv", false);
            cwriter = new BufferedWriter(tWriter);
      //  }        
            // String header = "slip_number,slip_type,out_store,in_store,item_number,quantity,price,comment,to_num";
            // writer.write(header);
            // writer.newLine();
               
        // *** loop for multiple form selection from list
        Statement s1 = DBConnect.connection.createStatement();
        selectedIx = readyList.getSelectedIndices();
        for (int j = 0; j < selectedIx.length; j++) {
         //  while (i < selectedIx.length) {
              sel = readyList.getModel().getElementAt(selectedIx[j]);
         // pulls data from database one at a time per form selection from list
        String selTable01 = "SELECT Sku As s, Qty As q, Orig_Retail As prce, Orig_Sku As orgsk " //Store As strcd "
                    + "FROM completed_lines WHERE Form_Name = '" + sel + "' AND Warranty = 'true'";
        // *** changes store mnemonic for export files
        
      //   private static void strConversion() throws SQLException, ClassNotFoundException {    
      //   Statement s = DBConnect.connection.createStatement();
         String getStr = "SELECT Store As strcd FROM completed_lines WHERE Form_Name = '" + sel + "'";
         s1.execute(getStr);
         try (ResultSet rs01 = s1.getResultSet()) {             
                while (rs01.next()) {
                    GetStr = rs01.getString("StrCd");
                }   
           }
        
         strNm = GetStr;
         String  strConvert = "SELECT Store_Code As StrCd FROM stores WHERE CG_Store = '"+ strNm +"'";
         s1.execute(strConvert);
         try (ResultSet rs01 = s1.getResultSet()) {             
                while (rs01.next()) {
                    StrCode = rs01.getString("StrCd");
                }   
           }
           
            // *** fills export file with data 
            s1.execute(selTable01);
            try (ResultSet rs01 = s1.getResultSet()) {             
                while (rs01.next()) {                   
                    String sku = rs01.getString("s");
                    String qty = rs01.getString("q");
                    String price = rs01.getString("prce");                   
                    String line = "" + "," + "out" + "," + StrCode + "," + "VTX" + "," + sku + "," + qty + ", " + price + "," + "EBW";
                    // *** Original line of code
                    // String line = "" + "," + "out" + "," + StrCode + "," + "RTV" + "," + sku + "," + qty + ", " + price + "," + "EBW";
                    
                    cwriter.write(line);
                    cwriter.newLine();
                }
            }                 
      }
      cwriter.close();
      }
    }
    }
    
    
    // creates export for inventory adjustments
    @SuppressWarnings("DeadBranch")
    public static void formAdjustments() throws IOException, SQLException, ClassNotFoundException {
        // gets the current time for export file name
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String datenow = formatter.format(currentDate.getTime());
                // creates export file name and headers
            
            /*  
            if (DBConnect.TestEnviron = (true)) {
                fWriter = new FileWriter("G:/CGD/Cyclegear.com/Scott/Inventory Adjustment App/App_Exports/Adjustments/" + frmNm.split("_")[1] + "/" + frmNm.replace(":", "_") + "TEST_ADJ_" + GtStore.store + "_" + datenow + "_TEST.csv", false);
                writer = new BufferedWriter(fWriter);
            } else { */
                fWriter = new FileWriter("//sscfil03/groups/Inventory/Inventory Control/IAN/Adjustments/ADJ_" + GtStore.store + "_" + datenow + ".csv", false);
                writer = new BufferedWriter(fWriter);
                
          //  }  
              // String header = "adj_num,store_code,item_number,quantity,adj_reason";
              // writer.write(header);
              // writer.newLine();
        
            Statement s1 = DBConnect.connection.createStatement();
            // loop for multiple form selections from list
          int[] selectedIx = readyList.getSelectedIndices();
          for (int i = 0; i < selectedIx.length; i++) {
          //  while (i < selectedIx.length) {
               Object select = readyList.getModel().getElementAt(selectedIx[i]);         
        // pulls data from database for each selection
        String selTable01 = "SELECT Sku As s, Qty As q, New_Sku As nwsku, "/* Store As sc, */ + "Qty_In As qtyI "
                    + "FROM completed_lines WHERE Form_Name = '" + select + "' AND Cust_Satisf = 'true'";
        
        String getStr = "SELECT Store As strcd FROM completed_lines WHERE Form_Name = '" + select + "'";
         s1.execute(getStr);
         try (ResultSet rs01 = s1.getResultSet()) {             
                while (rs01.next()) {
                    GetStr = rs01.getString("StrCd");
                }   
           }
        
         strNm = GetStr;
         String  strConvert = "SELECT Store_Code As StrCd FROM stores WHERE CG_Store = '"+ strNm +"'";
         s1.execute(strConvert);
         try (ResultSet rs01 = s1.getResultSet()) {             
                while (rs01.next()) {
                    StrCode = rs01.getString("StrCd");
                }   
           }
        
        //    strConversion();
            // fills data into export file
            s1.execute(selTable01);
            try (ResultSet rs01 = s1.getResultSet()) {
                while (rs01.next()) { 
                    if (select.toString().contains("ias")){
                    String sku = rs01.getString("s");
                    String qty = rs01.getString("q");
                 // String strcd = rs01.getString("sc");
                    String newsku = rs01.getString("nwsku");
                    String qtyin = rs01.getString("qtyI");
                    String line = "" + "," + StrCode + "," + sku + "," + "-" + qty + "," + "STR FAX";
                    if (sku.equals("0")) {
                        System.out.println("No Sku to Export");
                    } else {
                    writer.write(line);
                    writer.newLine();
                    }
                    if (newsku.equals("0")) {
                        System.out.println("No New Sku to Export");
                    } else {
                    line = "" + "," + StrCode + "," + newsku + "," + qtyin + "," + "STR FAX";
                    writer.write(line);
                    writer.newLine(); 
                    }
                    }
                    if (select.toString().contains("nebas")){
                    String sku = rs01.getString("s");
                    String qty = rs01.getString("q");
                //  String strcd = rs01.getString("sc");
                //  System.out.println(frmNm.split("_")[1] + qty + "," + sku + "," + desc1 + "," + reas + "," + descdmg + "," + orgR + "," + nwsku + "," + desc2 + "," + qtyI);
                    String line = "" + "," + StrCode + "," + sku + "," + "-" + qty + "," + "NEBMOS";
                    writer.write(line);
                    writer.newLine();                
                    }
                    if (select.toString().substring(0,4).contentEquals("ebas")){
                    String sku = rs01.getString("s");
                    String qty = rs01.getString("q");
                //  String strcd = rs01.getString("sc");
                    String line = "" + "," + StrCode + "," + sku + "," + "-" + qty + "," + "EBMOSNW";
                    writer.write(line);
                    writer.newLine();
                    }
                }
                }    
        }
        writer.close();
        }
    
    // ** 
    @SuppressWarnings("DeadBranch")
    public static void formAdjustmentsCopy() throws IOException, SQLException, ClassNotFoundException {
        // gets the current time for export file name
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String datenow = formatter.format(currentDate.getTime());
                // creates export file name and headers
            
            /*  
            if (DBConnect.TestEnviron = (true)) {
                fWriter = new FileWriter("G:/CGD/Cyclegear.com/Scott/Inventory Adjustment App/App_Exports/Adjustments/" + frmNm.split("_")[1] + "/" + frmNm.replace(":", "_") + "TEST_ADJ_" + GtStore.store + "_" + datenow + "_TEST.csv", false);
                writer = new BufferedWriter(fWriter);
            } else { */
            tWriter = new FileWriter("//sscfil03/groups/Merch/Purchasing/Warren/IAN Adjustments Copy/ADJ_" + GtStore.store + "_" + datenow + "_copy.csv", false);
            cwriter = new BufferedWriter(tWriter);
                
               
          //  }  
              // String header = "adj_num,store_code,item_number,quantity,adj_reason";
              // writer.write(header);
              // writer.newLine();
        
            Statement s1 = DBConnect.connection.createStatement();
            // loop for multiple form selections from list
          int[] selectedIx = readyList.getSelectedIndices();
          for (int i = 0; i < selectedIx.length; i++) {
          //  while (i < selectedIx.length) {
               Object select = readyList.getModel().getElementAt(selectedIx[i]);         
        // pulls data from database for each selection
        String selTable01 = "SELECT Sku As s, Qty As q, New_Sku As nwsku, "/* Store As sc, */ + "Qty_In As qtyI "
                    + "FROM completed_lines WHERE Form_Name = '" + select + "' AND Cust_Satisf = 'true'";
        
        String getStr = "SELECT Store As strcd FROM completed_lines WHERE Form_Name = '" + select + "'";
         s1.execute(getStr);
         try (ResultSet rs01 = s1.getResultSet()) {             
                while (rs01.next()) {
                    GetStr = rs01.getString("StrCd");
                }   
           }
        
         strNm = GetStr;
         String  strConvert = "SELECT Store_Code As StrCd FROM stores WHERE CG_Store = '"+ strNm +"'";
         s1.execute(strConvert);
         try (ResultSet rs01 = s1.getResultSet()) {             
                while (rs01.next()) {
                    StrCode = rs01.getString("StrCd");
                }   
           }
        
        //    strConversion();
            // fills data into export file
            s1.execute(selTable01);
            try (ResultSet rs01 = s1.getResultSet()) {
                while (rs01.next()) { 
                    if (select.toString().contains("ias")){
                    String sku = rs01.getString("s");
                    String qty = rs01.getString("q");
                 // String strcd = rs01.getString("sc");
                    String newsku = rs01.getString("nwsku");
                    String qtyin = rs01.getString("qtyI");
                    String line = "" + "," + StrCode + "," + sku + "," + "-" + qty + "," + "STR FAX";
                    if (sku.equals("0")) {
                        System.out.println("No Sku to Export");
                    } else {
                    cwriter.write(line);
                    cwriter.newLine();
                    }
                    if (newsku.equals("0")) {
                        System.out.println("No New Sku to Export");
                    } else {
                    line = "" + "," + StrCode + "," + newsku + "," + qtyin + "," + "STR FAX";
                    cwriter.write(line);
                    cwriter.newLine(); 
                    }
                    }
                    if (select.toString().contains("nebas")){
                    String sku = rs01.getString("s");
                    String qty = rs01.getString("q");
                //  String strcd = rs01.getString("sc");
                //  System.out.println(frmNm.split("_")[1] + qty + "," + sku + "," + desc1 + "," + reas + "," + descdmg + "," + orgR + "," + nwsku + "," + desc2 + "," + qtyI);
                    String line = "" + "," + StrCode + "," + sku + "," + "-" + qty + "," + "NEBMOS";
                    cwriter.write(line);
                    cwriter.newLine();                
                    }
                    if (select.toString().substring(0,4).contentEquals("ebas")){
                    String sku = rs01.getString("s");
                    String qty = rs01.getString("q");
                //  String strcd = rs01.getString("sc");
                    String line = "" + "," + StrCode + "," + sku + "," + "-" + qty + "," + "EBMOSNW";
                    cwriter.write(line);
                    cwriter.newLine();
                    }
                }
                }    
        }
        cwriter.close();
        }   
        
    
    public void removeTempFormsAfterCompletion() throws ClassNotFoundException, SQLException {
    Statement rmv = DBConnect.connection.createStatement();
    int[] selectedIx = readyList.getSelectedIndices();
          for (int i = 0; i < selectedIx.length; i++) {
          //  while (i < selectedIx.length) {
               Object select = readyList.getModel().getElementAt(selectedIx[i]);
       String rmvtemptables = "DELETE FROM existing_forms WHERE Form_Name = '" + select + "'";
       rmv.execute(rmvtemptables);
       
       String dTable = "DROP TABLE " + select;            
       rmv.execute(dTable);   
       }
}
    /*  
    //drop table
    public static void drTable() throws ClassNotFoundException, SQLException {
        String[] args = null;
        GtDates.main(args);
        try (Statement s = DBConnect.connection.createStatement()) {
            String dTable = "DROP TABLE " + InvAdj_Admin.frmNm;
            
            s.execute(dTable);
        }
    }
    */
        
    public void getRtvForms() throws SQLException {
        Statement s = DBConnect.connection.createStatement();
        String getFrmNm = "SELECT Form_Name As frmNme FROM completed_forms WHERE Form_Name LIKE 'ebas%' AND Status = 'Completed'";
                s.execute(getFrmNm);
                try (ResultSet rs1 = s.getResultSet()) {          
                while (rs1.next()) {
                    String frmNme = rs1.getString("frmNme");
                    ebasfrmNm = frmNme;
                    formArray.add(ebasfrmNm);
                    }
                }
    }
    
    public void insertRtvForms() throws SQLException {
        Statement s = DBConnect.connection.createStatement();
        for (String formArray1 : formArray) {
                    String insertFrms = "INSERT INTO rtv (SELECT * FROM completed_lines WHERE Form_Name = '" + formArray1 + "' AND Warranty = 'true')";
                    s.execute(insertFrms);
                }
    }
    
    public void getRtvStr() throws SQLException{
        Statement s = DBConnect.connection.createStatement();
    for (String formArray2 : formArray) {
                    // this statement pulls store from temp table for each form name
                String getStr = "SELECT Store As strcd FROM rtv WHERE Form_Name = '" + formArray2 + "'";
                s.execute(getStr);
                try (ResultSet rs01 = s.getResultSet()) {             
                while (rs01.next()) {
                    GetStr = rs01.getString("StrCd");
                    strconvertArray.add(GetStr);
                    }
                }
                }
    }
    
    
   public void getRTVLines() throws SQLException {
       try (Statement s1 = DBConnect.connection.createStatement()) {
       String getRtv = "SELECT Store As str, Sku As sku, Qty As qty, Description2 As skudesc2, "
                    + "Reason As reasn, Desc_Damage As DscDmg, New_Used As nwUsd, Ln_Date As lnDte, Second_Sku_Vendor As sskuvendr, Second_Sku_VPNum As sskuvpnum, Ord_C$ As ordC, Manuf_Inspection As manufInspec "
                    + "FROM rtv WHERE Sku IS NOT NULL";
            s1.execute(getRtv);
            try (ResultSet rs1 = s1.getResultSet()) {
                while (rs1.next()) {
                    String stre = rs1.getString("str");
                    String sk = rs1.getString("sku");
                    String qt = rs1.getString("qty");
                    String skdsc2 = rs1.getString("skudesc2");
                    String reasn = rs1.getString("reasn");
                    String dscdmg = rs1.getString("DscDmg");
                    String newused = rs1.getString("nwUsd");
                    String lndate = rs1.getString("lnDte");
                    String sskuvendr = rs1.getString("sskuvendr");
                    String sskuvpnm = rs1.getString("sskuvpnum");
                    String ordC$ = rs1.getString("ordC");
                    String manufinspec = rs1.getString("manufInspec");
                    str = stre;
                    sku = sk;
                    qty = qt;
                    skudesc2 = skdsc2;
                    reason = reasn;
                    dsc_dmg = dscdmg;
                    new_used = newused;
                    ln_date = lndate;
                    sskuvendor = sskuvendr;
                    sskuvpnum = sskuvpnm;
                    ord_c = ordC$;
                    manuf_inspec = manufinspec;
                    strArray.add(str);
                    skuArray.add(sku);
                    qtyArray.add(qty);
                    skdesc2Array.add(skudesc2);
                    reasnArray.add(reason);
                    dscdmgArray.add(dsc_dmg);
                    newusedArray.add(new_used);
                    lndateArray.add(ln_date);
                    sskuvendrArray.add(sskuvendor);
                    sskuvpnumArray.add(sskuvpnum);
                    ordcArray.add(ord_c);
                    ManufInspecArray.add(manuf_inspec);
                    }              
                }
       }
   }
    
   
   
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InvAdj_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InvAdj_Admin().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton All_Completed_Report_Btn;
    private javax.swing.JButton All_Stores_Report_Btn;
    private javax.swing.JButton Deleted_Report_Btn;
    private javax.swing.JButton RTV_Export_Btn;
    private javax.swing.JLabel Test_Label;
    private javax.swing.JLabel VersionLabel;
    private javax.swing.JButton apprBtn;
    public static javax.swing.JList apprList;
    private javax.swing.JButton closeBtn;
    private javax.swing.JButton createExportButton;
    private javax.swing.JButton inprocBtn;
    public static javax.swing.JList inprocList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton pendBtn;
    public static javax.swing.JList pendList;
    public static javax.swing.JList readyList;
    // End of variables declaration//GEN-END:variables

    public static void Rpnt() {
        listinprocess.clear();
        listpending.clear();
        listapproved.clear();
        listreadyforexport.clear();
        try {
            GetForms_InvAdj.ChkAllForms();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InvAdj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}

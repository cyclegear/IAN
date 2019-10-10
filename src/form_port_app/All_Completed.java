/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package form_port_app;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author WStevens
 */

// This class pulls all data from completed_lines table in the database and inserts it into a JTable 
// with the ability for IC users to view the data and print physical copy from all stores data that was completed
public class All_Completed extends javax.swing.JFrame {

    private Component frame;
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    protected static String StartDate;
    protected static String EndDate;
    public static FileWriter fileWriter;
    public static BufferedWriter Writer;
    protected static String store;
    protected static String form_name;
    protected static String lne;
    protected static String sku;    
    protected static String qty;
    protected static String sku_desc1;
    protected static String attr;
    protected static String size;
    protected static String orig_retail;
    protected static String reason;
    protected static String new_used;
    protected static String desc_damage;
    protected static String org_sku;
    protected static String new_sku;
    protected static String sku_desc2;
    protected static String attr2;
    protected static String size2;
    protected static String cust_satisf;
    protected static String warranty;
    protected static String manuf_inspec;
    protected static String qty_in;
    protected static String fCost;
    protected static String sCost;
    protected static String ln_date;
    protected static String fskuvendor;
    protected static String fskuvpnum;
    protected static String fdcs;
    protected static String sskuvendor;
    protected static String sskuvpnum;
    protected static String sdcs;
    protected static String ord_c;
    public ArrayList<String> strArray = new ArrayList<>();
    public ArrayList<String> formNameArray = new ArrayList<>();
    public ArrayList<String> lineArray = new ArrayList<>();
    public ArrayList<String> skuArray = new ArrayList<>();
    public ArrayList<String> qtyArray = new ArrayList<>();
    public ArrayList<String> skuDesc1Array = new ArrayList<>();
    public ArrayList<String> attrArray = new ArrayList<>();
    public ArrayList<String> sizeArray = new ArrayList<>();
    public ArrayList<String> origRetailArray = new ArrayList<>();
    public ArrayList<String> reasonArray = new ArrayList<>();
    public ArrayList<String> newUsedArray = new ArrayList<>();
    public ArrayList<String> descDamageArray = new ArrayList<>();
    public ArrayList<String> origSkuArray = new ArrayList<>();
    public ArrayList<String> newSkuArray = new ArrayList<>();
    public ArrayList<String> skuDesc2Array = new ArrayList<>();
    public ArrayList<String> attr2Array = new ArrayList<>();
    public ArrayList<String> size2Array = new ArrayList<>();
    public ArrayList<String> custSatisfArray = new ArrayList<>();
    public ArrayList<String> warrantyArray = new ArrayList<>();
    public ArrayList<String> manufInspecArray = new ArrayList<>();
    public ArrayList<String> qtyInArray = new ArrayList<>();
    public ArrayList<String> fcostArray = new ArrayList<>();
    public ArrayList<String> scostArray = new ArrayList<>();
    public ArrayList<String> lnDateArray = new ArrayList<>();
    public ArrayList<String> fskuvendorArray = new ArrayList<>();
    public ArrayList<String> fskuvpnumArray = new ArrayList<>();
    public ArrayList<String> fdcsArray = new ArrayList<>();
    public ArrayList<String> sskuvendorArray = new ArrayList<>();
    public ArrayList<String> sskuvpnumArray = new ArrayList<>();
    public ArrayList<String> sdcsArray = new ArrayList<>();
    public ArrayList<String> ordCArray = new ArrayList<>();

    protected static String FrmNm;
    protected ArrayList<String> FrmNmRprtArray = new ArrayList<>();
    
  //  protected static String last_updated;
  //  protected ArrayList<String> LastUpdatedArray = new ArrayList<>();
    
    
    
    /**
     * Creates new form All_Completed
     */
    public All_Completed() {
       // Test_Label.setVisible(false);
      //  Print_Button.setVisible(false);
     
        initComponents();
        conn=DBConnect.connection;
      /*  GetAllComp();
        All_Comp_Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        All_Comp_Table.getColumnModel().getColumn(0).setPreferredWidth(80);
        All_Comp_Table.getColumnModel().getColumn(1).setPreferredWidth(230);
        All_Comp_Table.getColumnModel().getColumn(2).setPreferredWidth(40);
        All_Comp_Table.getColumnModel().getColumn(3).setPreferredWidth(60);
        All_Comp_Table.getColumnModel().getColumn(4).setPreferredWidth(30);
        All_Comp_Table.getColumnModel().getColumn(5).setPreferredWidth(200);
        All_Comp_Table.getColumnModel().getColumn(6).setPreferredWidth(60);
        All_Comp_Table.getColumnModel().getColumn(7).setPreferredWidth(40);
        All_Comp_Table.getColumnModel().getColumn(8).setPreferredWidth(90);
        All_Comp_Table.getColumnModel().getColumn(9).setPreferredWidth(60);
        All_Comp_Table.getColumnModel().getColumn(10).setPreferredWidth(90);
        All_Comp_Table.getColumnModel().getColumn(11).setPreferredWidth(100);
        All_Comp_Table.getColumnModel().getColumn(12).setPreferredWidth(80);
        All_Comp_Table.getColumnModel().getColumn(13).setPreferredWidth(80);
        All_Comp_Table.getColumnModel().getColumn(14).setPreferredWidth(220);
        All_Comp_Table.getColumnModel().getColumn(15).setPreferredWidth(60);
        All_Comp_Table.getColumnModel().getColumn(16).setPreferredWidth(50);
        All_Comp_Table.getColumnModel().getColumn(17).setPreferredWidth(70);
        All_Comp_Table.getColumnModel().getColumn(18).setPreferredWidth(70);
        All_Comp_Table.getColumnModel().getColumn(19).setPreferredWidth(70);
        All_Comp_Table.getColumnModel().getColumn(20).setPreferredWidth(50);
        All_Comp_Table.getColumnModel().getColumn(21).setPreferredWidth(50);
        All_Comp_Table.getColumnModel().getColumn(22).setPreferredWidth(120); */
        // This was necessary because auto resize was making column headers too small or too big horizontally       
    }

    // gets all data from Completed lines table in database and inserts them into JTable
    /*
    private void GetAllComp() {
         try  {
    String sql = "SELECT * FROM completed_lines";
    pst = conn.prepareStatement(sql);
    rs=pst.executeQuery();
    All_Comp_Table.setModel(DbUtils.resultSetToTableModel(rs));
} catch (SQLException e) {
    JOptionPane.showMessageDialog(null, e);
}
    } */
    
    private void GetDateRange() throws SQLException {
        
        try  {
            Statement s = DBConnect.connection.createStatement();
            
            try {
                String DeletePrevRecords = "DELETE FROM complete_range";
                    s.execute(DeletePrevRecords);
            } catch (SQLException ex) {
                    Logger.getLogger(All_Completed.class.getName()).log(Level.SEVERE, null, ex);
            }    
            /*
            try {
                String DeletePrevRecords = "DELETE FROM complete_date_adjusted";
                    s.execute(DeletePrevRecords);
            } catch (SQLException ex) {
                    Logger.getLogger(All_Completed.class.getName()).log(Level.SEVERE, null, ex);
            }
            */          
            All_Comp_Table.clearSelection();
            
           // if (StartDateField.getText().matches("[0-9]+") && EndDateField.getText().matches("[0-9+]")) {
            StartDate = StartDateField.getText();
            EndDate = EndDateField.getText();
    
            // ** ADD CHECK OF STATUS IN COMPLETED FORMS 
            String GetDates = "SELECT Form_Name As FrmNm FROM completed_forms WHERE Last_Updated >= '"+ StartDate + "'"
            + " AND Last_Updated <= '" + EndDate + "' AND (Status = 'Completed' OR Status = 'RTV_Exported')";
            s.execute(GetDates);
            try (ResultSet rs1 = s.getResultSet()) {
                        while (rs1.next()) {
                            String frmnm = rs1.getString("frmNm");
                            FrmNm = frmnm;
                            FrmNmRprtArray.add(FrmNm);
                        }
            }
            
            for (int count = 0; count < FrmNmRprtArray.size(); count++ ) {
            String InsertRange = "INSERT IGNORE INTO complete_range (SELECT * FROM completed_lines WHERE Form_Name = '" + FrmNmRprtArray.get(count) + "')";
            s.execute(InsertRange);
            }
            /*
            for (int count = 0; count < FrmNmRprtArray.size(); count++ ) {
            String InsertDateAdjusted = "INSERT IGNORE INTO complete_date_adjusted (SELECT Form_Name, Last_Updated FROM completed_forms WHERE Form_Name = '" + FrmNmRprtArray.get(count) + "')";
            s.execute(InsertDateAdjusted);
            }
            */
            /*
            // Create Select statement that will get last updated for all completed lines //
            for (int count = 0; count < FrmNmRprtArray.size(); count++ ) {
            String getAdjustmentDate = "SELECT Last_Updated As lastUpdated FROM completed_forms WHERE Form_Name = '"+ FrmNmRprtArray.get(count) +"'";
            s.execute(getAdjustmentDate);
            try (ResultSet rs1 = s.getResultSet()) {
                        while (rs1.next()) {
                        String lastupdated = rs1.getString("lastUpdated");
                        last_updated = lastupdated;
                        LastUpdatedArray.add(last_updated);
                        }
                    }            
            }            
            
              // insert last updated into completed range //
            for (int count = 0; count < LastUpdatedArray.size(); count++ ) {
            String updatecomprenge = "UPDATE complete_range "
                // + "SET Date_Adjusted = '" + LastUpdatedArray + "'" + ","
                + "SET Date_Adjusted = '" + LastUpdatedArray.get(count) + "'"
                + " WHERE Sku IS NOT NULL AND Form_Name = '" + FrmNmRprtArray.get(count) + "'";
            s.execute(updatecomprenge); 
            }
            */
    /*
    String insertRange = "INSERT INTO complete_range (SELECT * FROM completed_lines WHERE Ln_Date >= '" + StartDate + "01:00:00'"
     + " AND Ln_Date <= '" + EndDate + " 23:59:59')";
    s.execute(insertRange); */
            
        } catch (SQLException e) {
    JOptionPane.showMessageDialog(null, e);
    } 
    }
    
    private void GetCompDateRange() throws SQLException {
    String pullRange = "SELECT * FROM complete_range";
    pst = conn.prepareStatement(pullRange);
    rs = pst.executeQuery();
    All_Comp_Table.setModel(DbUtils.resultSetToTableModel(rs)); 
  //  }
      
        All_Comp_Table.getColumnModel().getColumn(0).setPreferredWidth(50);
        All_Comp_Table.getColumnModel().getColumn(1).setPreferredWidth(200);
        All_Comp_Table.getColumnModel().getColumn(2).setPreferredWidth(40);
        All_Comp_Table.getColumnModel().getColumn(3).setPreferredWidth(60);
        All_Comp_Table.getColumnModel().getColumn(4).setPreferredWidth(30);
        All_Comp_Table.getColumnModel().getColumn(5).setPreferredWidth(200);
        All_Comp_Table.getColumnModel().getColumn(6).setPreferredWidth(60);
        All_Comp_Table.getColumnModel().getColumn(7).setPreferredWidth(40);
        All_Comp_Table.getColumnModel().getColumn(8).setPreferredWidth(90);
        All_Comp_Table.getColumnModel().getColumn(9).setPreferredWidth(60);
        All_Comp_Table.getColumnModel().getColumn(10).setPreferredWidth(90);
        All_Comp_Table.getColumnModel().getColumn(11).setPreferredWidth(100);
        All_Comp_Table.getColumnModel().getColumn(12).setPreferredWidth(80);
        All_Comp_Table.getColumnModel().getColumn(13).setPreferredWidth(80);
        All_Comp_Table.getColumnModel().getColumn(14).setPreferredWidth(220);
        All_Comp_Table.getColumnModel().getColumn(15).setPreferredWidth(60);
        All_Comp_Table.getColumnModel().getColumn(16).setPreferredWidth(50);
        All_Comp_Table.getColumnModel().getColumn(17).setPreferredWidth(70);
        All_Comp_Table.getColumnModel().getColumn(18).setPreferredWidth(70);
        All_Comp_Table.getColumnModel().getColumn(19).setPreferredWidth(70);
        All_Comp_Table.getColumnModel().getColumn(20).setPreferredWidth(50);
        All_Comp_Table.getColumnModel().getColumn(21).setPreferredWidth(50);
        All_Comp_Table.getColumnModel().getColumn(22).setPreferredWidth(120);
        All_Comp_Table.getColumnModel().getColumn(23).setPreferredWidth(70);
        All_Comp_Table.getColumnModel().getColumn(24).setPreferredWidth(70);
        All_Comp_Table.getColumnModel().getColumn(25).setPreferredWidth(70);
        All_Comp_Table.getColumnModel().getColumn(26).setPreferredWidth(70);
        All_Comp_Table.getColumnModel().getColumn(27).setPreferredWidth(70);
        All_Comp_Table.getColumnModel().getColumn(28).setPreferredWidth(70);
        All_Comp_Table.getColumnModel().getColumn(29).setPreferredWidth(70);
        All_Comp_Table.getColumnModel().getColumn(30).setPreferredWidth(70);
        
        // JOptionPane.showMessageDialog(frame, "Records sucessfuly pulled within date range");        
        }    
    /*
    private void GetCompDateAdj() throws SQLException {
    String pullRange = "SELECT * FROM complete_date_adjusted";
    pst = conn.prepareStatement(pullRange);
    rs = pst.executeQuery();    
    Date_Adjusted_Table.setModel(DbUtils.resultSetToTableModel(rs));
    Date_Adjusted_Table.getColumnModel().getColumn(0).setPreferredWidth(195);
    Date_Adjusted_Table.getColumnModel().getColumn(1).setPreferredWidth(80);
    }
    */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        All_Comp_Table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Close_Button = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Get_Date_Range_Button = new javax.swing.JButton();
        StartDateField = new javax.swing.JTextField();
        EndDateField = new javax.swing.JTextField();
        Save_To_Excel_Button = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        All_Comp_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        All_Comp_Table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        All_Comp_Table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        All_Comp_Table.setDropMode(javax.swing.DropMode.INSERT_COLS);
        jScrollPane1.setViewportView(All_Comp_Table);

        jLabel1.setText("All Completed");

        Close_Button.setBackground(new java.awt.Color(255, 0, 0));
        Close_Button.setText("Close");
        Close_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Close_ButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Date Range From:");

        jLabel3.setText("To:");

        Get_Date_Range_Button.setText("Get Date Range");
        Get_Date_Range_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Get_Date_Range_ButtonActionPerformed(evt);
            }
        });

        StartDateField.setText("2014-09-17");

        EndDateField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EndDateFieldKeyPressed(evt);
            }
        });

        Save_To_Excel_Button.setBackground(new java.awt.Color(0, 255, 0));
        Save_To_Excel_Button.setText("Save To Excel");
        Save_To_Excel_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_To_Excel_ButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("(Date format: Year-Month-Day)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(545, 545, 545)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(StartDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(EndDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Get_Date_Range_Button)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Save_To_Excel_Button)
                                        .addGap(46, 46, 46)
                                        .addComponent(Close_Button))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1085, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(StartDateField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(EndDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Get_Date_Range_Button))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Close_Button)
                        .addComponent(Save_To_Excel_Button)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Close_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Close_ButtonActionPerformed
      this.dispose();
    }//GEN-LAST:event_Close_ButtonActionPerformed

    private void Get_Date_Range_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Get_Date_Range_ButtonActionPerformed
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(frame, "Pulling Records");
        try {
            GetDateRange();
            GetCompDateRange();
        //    GetCompDateAdj();
        } catch (SQLException ex) {
            Logger.getLogger(All_Completed.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.getRootFrame().dispose();
    }//GEN-LAST:event_Get_Date_Range_ButtonActionPerformed

    // not working properly yet
    private void Save_To_Excel_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_To_Excel_ButtonActionPerformed
        try {                                                   
            Statement s = DBConnect.connection.createStatement();
                    
            Calendar currentDate = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("MM_dd_yy_HH_mm_ss");
            String datenow = formatter.format(currentDate.getTime());
            
                    try {
                        /*
                        try {
                            String GetCompRange = "SELECT * FROM complete_range";
                            pst = conn.prepareStatement(GetCompRange);
                            pst.executeQuery();
                        } catch (SQLException ex) {
                            Logger.getLogger(All_Completed.class.getName()).log(Level.SEVERE, null, ex);
                        } */
                            fileWriter = new FileWriter("//sscfil03/groups/Inventory/Inventory Control/IAN/Completed Reports/Completed_" + GtStore.store +"_"+ datenow + ".csv");
                            Writer = new BufferedWriter(fileWriter);
                       // }
                        String header = "STORE,FORM NAME,LINE,SKU,QTY,DESCRIPTION1,ATTRIBUTE,SIZE,ORIG_RETAIL,REASON,NEW USED,DESC DAMAGE,ORIG SKU,NEW SKU,DESCRIPTION 2,"
                                + "ATTRIBUTE 2,SIZE 2,CUST SATISF,WARRANTY,MANUF INSPECTION,QTY IN,FIRST COST,LN DATE,ORD C$,SECOND COST,FIRST SKU VENDOR,FIRST SKU VPNUM,"
                              + "FIRST DCS,SECOND SKU VENDOR,SECOND SKU VPNUM,SECOND DCS";
                        Writer.write(header);
                        Writer.newLine();                       
                        
                        String getRangeData = "SELECT Store As str, Form_Name As frmNm, Line As lne, Sku As sku, Qty As qty, Description1 As skudesc1, Attribute As attr, Size As size, " 
                        + "Orig_Retail As orgRetail, Reason As reasn, New_Used As nwUsd, Desc_Damage As descDmge, Orig_Sku As orgSku, New_Sku As newSku, Description2 As skudesc2, "
                        + "Attribute2 As attr2, Size2 As size2, Cust_Satisf As cstSatisf, Warranty As wrrnty, Manuf_Inspection As manufInspec, Qty_In As qtyIn, First_Cost As fcst, " 
                        + "Ln_Date As lnDte, Ord_C$ As ordC, Second_Cost As scst, First_Sku_Vendor As firstSkuVendor, First_Sku_VPNum As firstSkuVpnum, First_DCS As fDCS, " 
                        + "Second_Sku_Vendor As secondSkuVendor, Second_Sku_VPNum As secondSkuVpnum, Second_DCS As sDCS "
                        + "FROM complete_range WHERE Sku IS NOT NULL";
                        s.execute(getRangeData);
                    try (ResultSet rs1 = s.getResultSet()) {
                        while (rs1.next()) {
                        String stre = rs1.getString("str");
                        String frmnm = rs1.getString("frmNm");
                        String skuline = rs1.getString("lne");
                        String sk = rs1.getString("sku");
                        String qt = rs1.getString("qty");
                        String skdesc1 = rs1.getString("skudesc1");
                        String atr = rs1.getString("attr");
                        String sze = rs1.getString("size");
                        String orgretail = rs1.getString("orgRetail");
                        String reasn = rs1.getString("reasn");
                        String newused = rs1.getString("nwUsd");
                        String descdamage = rs1.getString("descDmge");
                        String orgsk = rs1.getString("orgSku");
                        String newsku = rs1.getString("newSku");
                        String skdsc2 = rs1.getString("skudesc2");
                        String atr2 = rs1.getString("attr2");
                        String sze2 = rs1.getString("size2");
                        String custsatisf = rs1.getString("cstSatisf");
                        String wrrnty = rs1.getString("wrrnty");
                        String manufinpsec = rs1.getString("manufInspec");
                        String qtyin = rs1.getString("qtyIn");
                        String fcst = rs1.getString("fcst");
                        String lndate = rs1.getString("lnDte");
                        String ordC$ = rs1.getString("ordC");
                        String scst = rs1.getString("scst");
                        String fskuvendr = rs1.getString("firstSkuVendor");
                        String fskuvpnm = rs1.getString("firstSkuVpnum");
                        String fDcs = rs1.getString("fDCS");
                        String sskuvendr = rs1.getString("secondSkuVendor");
                        String sskuvpnm = rs1.getString("secondSkuVpnum");
                        String sDcs = rs1.getString("sDCS");
                        store = stre;
                        form_name = frmnm;
                        lne = skuline;
                        sku = sk;
                        qty = qt;
                        sku_desc1 = skdesc1;
                        attr = atr;
                        size = sze; 
                        orig_retail = orgretail;
                        reason = reasn;
                        new_used = newused;
                        desc_damage = descdamage;                        
                        org_sku = orgsk;
                        new_sku = newsku;
                        sku_desc2 = skdsc2;
                        attr2 = atr2;
                        size2 = sze2;
                        cust_satisf = custsatisf;
                        warranty = wrrnty;
                        manuf_inspec = manufinpsec;
                        qty_in = qtyin;
                        fCost = fcst;
                        ln_date = lndate;                     
                        ord_c = ordC$;
                        sCost = scst;
                        fskuvendor = fskuvendr;
                        fskuvpnum = fskuvpnm;
                        fdcs = fDcs;
                        sskuvendor = sskuvendr;
                        sskuvpnum = sskuvpnm;
                        sdcs = sDcs;
                        strArray.add(store);
                        formNameArray.add(form_name);
                        lineArray.add(lne);
                        skuArray.add(sku);
                        qtyArray.add(qty);
                        skuDesc1Array.add(sku_desc1);
                        attrArray.add(attr);
                        sizeArray.add(size);
                        origRetailArray.add(orig_retail);
                        reasonArray.add(reason);
                        newUsedArray.add(new_used);
                        descDamageArray.add(desc_damage);
                        origSkuArray.add(org_sku);
                        newSkuArray.add(new_sku);
                        skuDesc2Array.add(sku_desc2);
                        attr2Array.add(attr2);
                        size2Array.add(size2);
                        custSatisfArray.add(cust_satisf);
                        warrantyArray.add(warranty);
                        manufInspecArray.add(manuf_inspec);
                        qtyInArray.add(qty_in);
                        fcostArray.add(fCost);
                        lnDateArray.add(ln_date);                        
                        ordCArray.add(ord_c);
                        scostArray.add(sCost);
                        fskuvendorArray.add(fskuvendor);
                        fskuvpnumArray.add(fskuvpnum);
                        fdcsArray.add(fdcs);
                        sskuvendorArray.add(sskuvendor);
                        sskuvpnumArray.add(sskuvpnum);
                        sdcsArray.add(sdcs);
                        }
                        
                for (int count = 0; count < skuArray.size(); count++ ) {
                String line = strArray.get(count) + "," + formNameArray.get(count) + "," + lineArray.get(count) + "," + skuArray.get(count) + "," + qtyArray.get(count) + "," +
                    skuDesc1Array.get(count)+ "," + attrArray.get(count) + "," + sizeArray.get(count) + "," + origRetailArray.get(count) + "," + reasonArray.get(count) + "," +
                    newUsedArray.get(count) + "," + descDamageArray.get(count) + "," + origSkuArray.get(count) + "," + newSkuArray.get(count) + "," + skuDesc2Array.get(count) + "," + 
                    attr2Array.get(count) + "," + size2Array.get(count) + "," + custSatisfArray.get(count) + "," + warrantyArray.get(count) + "," + manufInspecArray.get(count) + "," + 
                    qtyInArray.get(count) + "," + fcostArray.get(count) + "," + lnDateArray.get(count) + "," + ordCArray.get(count) + "," + scostArray.get(count) + ","+ fskuvendorArray.get(count) + "," + 
                    fskuvpnumArray.get(count) + "," + fdcsArray.get(count) + "," + sskuvendorArray.get(count) + "," + sskuvpnumArray.get(count) + "," + sdcsArray.get(count) +  "\t";
                    Writer.write(line);
                    Writer.newLine();
                }
                    Writer.close();
                }
                        
                    try {
                        String DeletePrevRecords = "DELETE FROM complete_range";
                        s.execute(DeletePrevRecords);
                        } catch (SQLException ex) {
                            Logger.getLogger(All_Completed.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    } catch (IOException ex) {
                        Logger.getLogger(All_Completed.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
        } catch (SQLException ex) {
            Logger.getLogger(All_Completed.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(frame, "Created Completed Report File");
        
    }//GEN-LAST:event_Save_To_Excel_ButtonActionPerformed

    private void EndDateFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EndDateFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
        JOptionPane.showMessageDialog(frame, "Pulling Records");
        try {
            GetDateRange();
            GetCompDateRange();
         //   GetCompDateAdj();
        } catch (SQLException ex) {
            Logger.getLogger(All_Completed.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_EndDateFieldKeyPressed

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
            java.util.logging.Logger.getLogger(All_Completed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new All_Completed().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected static javax.swing.JTable All_Comp_Table;
    private javax.swing.JButton Close_Button;
    private javax.swing.JTextField EndDateField;
    private javax.swing.JButton Get_Date_Range_Button;
    private javax.swing.JButton Save_To_Excel_Button;
    private javax.swing.JTextField StartDateField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

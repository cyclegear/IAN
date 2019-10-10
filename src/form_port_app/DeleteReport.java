/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form_port_app;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author WStevens
 */
public class DeleteReport extends javax.swing.JFrame {

    private Component frame;
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    protected static String storeLines;
    protected static String pullStr;
    protected static String delFrm;
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

    protected static String delFrmNm;
    protected ArrayList<String> delFrmNmArray = new ArrayList<>();
    protected ArrayList<String> delFrmNmRprtArray = new ArrayList<>();
    
    /**
     * Creates new form DeleteReport
     */
    public DeleteReport() {
        initComponents();
        conn=DBConnect.connection;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Deleted_Table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Close_Button = new javax.swing.JButton();
        Print_Button = new javax.swing.JButton();
        Save_To_Excel_Button = new javax.swing.JButton();
        StartDateField = new javax.swing.JTextField();
        EndDateField = new javax.swing.JTextField();
        Get_Date_Range_Button = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Deleted_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Deleted_Table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        Deleted_Table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Deleted_Table.setDropMode(javax.swing.DropMode.INSERT_COLS);
        jScrollPane1.setViewportView(Deleted_Table);

        jLabel1.setText("Deleted Lines Report");

        Close_Button.setBackground(new java.awt.Color(255, 0, 0));
        Close_Button.setText("Close");
        Close_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Close_ButtonActionPerformed(evt);
            }
        });

        Print_Button.setBackground(new java.awt.Color(255, 255, 0));
        Print_Button.setText("Print");
        Print_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Print_ButtonActionPerformed(evt);
            }
        });

        Save_To_Excel_Button.setBackground(new java.awt.Color(0, 255, 0));
        Save_To_Excel_Button.setText("Save To Excel");
        Save_To_Excel_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_To_Excel_ButtonActionPerformed(evt);
            }
        });

        StartDateField.setText("2014-09-17");

        EndDateField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EndDateFieldKeyPressed(evt);
            }
        });

        Get_Date_Range_Button.setBackground(new java.awt.Color(255, 153, 51));
        Get_Date_Range_Button.setText("Get Date Range");
        Get_Date_Range_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Get_Date_Range_ButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Date Range From:");

        jLabel3.setText("To:");

        jLabel4.setText("(Date format: Year-Month-Day)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(540, 540, 540)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 463, Short.MAX_VALUE)
                                .addComponent(Save_To_Excel_Button)
                                .addGap(18, 18, 18)
                                .addComponent(Print_Button)
                                .addGap(18, 18, 18)
                                .addComponent(Close_Button)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Save_To_Excel_Button)
                    .addComponent(Print_Button)
                    .addComponent(Close_Button)
                    .addComponent(Get_Date_Range_Button)
                    .addComponent(EndDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(StartDateField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(19, 19, 19))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Close_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Close_ButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_Close_ButtonActionPerformed

    private void Print_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Print_ButtonActionPerformed
        MessageFormat header = new MessageFormat("Deleted Report");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try {
            // fit width was prefered over normal because normal format would print 3 pages with all the column headers
            // spread across instead of on one page
            Deleted_Table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (java.awt.print.PrinterException e) {
            System.err.format("Failed to Print", e.getMessage());
        }
    }//GEN-LAST:event_Print_ButtonActionPerformed

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
                fileWriter = new FileWriter("//cg.com/groups/Inventory/Inventory Control/IAN/Deleted Reports/Deleted_" + GtStore.store +"_"+ datenow + ".csv");
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
            + "FROM deleted_report WHERE Sku IS NOT NULL";
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
                    qtyInArray.get(count) + "," + fcostArray.get(count) + "," + lnDateArray.get(count) + "," + ordCArray.get(count) + "," + scostArray.get(count) + "," + fskuvendorArray.get(count) + "," +
                    fskuvpnumArray.get(count) + "," + fdcsArray.get(count) + "," + sskuvendorArray.get(count) + "," + sskuvpnumArray.get(count) + "," + sdcsArray.get(count) +  "\t";
                    Writer.write(line);
                    Writer.newLine();
                }
                Writer.close();
            }
            
            } catch (IOException ex) {
                Logger.getLogger(DeleteReport.class.getName()).log(Level.SEVERE, null, ex);
            }
            } catch (SQLException ex) {
                Logger.getLogger(DeleteReport.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(frame, "Created Deleted Report File");
    }//GEN-LAST:event_Save_To_Excel_ButtonActionPerformed

    private void EndDateFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EndDateFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JOptionPane.showMessageDialog(frame, "Pulling Records");
            try {
                GetDateRange();
            } catch (SQLException ex) {
                Logger.getLogger(All_Completed.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_EndDateFieldKeyPressed

    private void Get_Date_Range_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Get_Date_Range_ButtonActionPerformed
        JOptionPane.showMessageDialog(frame, "Pulling Records");
        try {
            GetDateRange();
        } catch (SQLException ex) {
            Logger.getLogger(All_Completed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Get_Date_Range_ButtonActionPerformed

    private void GetDateRange() throws SQLException {
        
    try {
        
        String DeletePrevRecords = "DELETE FROM deleted_report";
        pst = conn.prepareStatement(DeletePrevRecords);
        } catch (SQLException ex) {
            Logger.getLogger(All_Completed.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    try  {
        getDeletedStatus();
        getDteRangeDel();
       // delReport();
    
    String pullDateRange = "SELECT * FROM deleted_report";
    pst = conn.prepareStatement(pullDateRange);
    rs = pst.executeQuery();
    Deleted_Table.setModel(DbUtils.resultSetToTableModel(rs));
    
  //  }
} catch (SQLException e) {
    JOptionPane.showMessageDialog(null, e);
}       
        Deleted_Table.getColumnModel().getColumn(0).setPreferredWidth(80);
        Deleted_Table.getColumnModel().getColumn(1).setPreferredWidth(230);
        Deleted_Table.getColumnModel().getColumn(2).setPreferredWidth(40);
        Deleted_Table.getColumnModel().getColumn(3).setPreferredWidth(60);
        Deleted_Table.getColumnModel().getColumn(4).setPreferredWidth(30);
        Deleted_Table.getColumnModel().getColumn(5).setPreferredWidth(200);
        Deleted_Table.getColumnModel().getColumn(6).setPreferredWidth(60);
        Deleted_Table.getColumnModel().getColumn(7).setPreferredWidth(40);
        Deleted_Table.getColumnModel().getColumn(8).setPreferredWidth(90);
        Deleted_Table.getColumnModel().getColumn(9).setPreferredWidth(60);
        Deleted_Table.getColumnModel().getColumn(10).setPreferredWidth(90);
        Deleted_Table.getColumnModel().getColumn(11).setPreferredWidth(100);
        Deleted_Table.getColumnModel().getColumn(12).setPreferredWidth(80);
        Deleted_Table.getColumnModel().getColumn(13).setPreferredWidth(80);
        Deleted_Table.getColumnModel().getColumn(14).setPreferredWidth(220);
        Deleted_Table.getColumnModel().getColumn(15).setPreferredWidth(60);
        Deleted_Table.getColumnModel().getColumn(16).setPreferredWidth(50);
        Deleted_Table.getColumnModel().getColumn(17).setPreferredWidth(70);
        Deleted_Table.getColumnModel().getColumn(18).setPreferredWidth(70);
        Deleted_Table.getColumnModel().getColumn(19).setPreferredWidth(70);
        Deleted_Table.getColumnModel().getColumn(20).setPreferredWidth(50);
        Deleted_Table.getColumnModel().getColumn(21).setPreferredWidth(50);
        Deleted_Table.getColumnModel().getColumn(22).setPreferredWidth(120);
        Deleted_Table.getColumnModel().getColumn(23).setPreferredWidth(70);
        Deleted_Table.getColumnModel().getColumn(24).setPreferredWidth(70);
        Deleted_Table.getColumnModel().getColumn(25).setPreferredWidth(70);        
        Deleted_Table.getColumnModel().getColumn(26).setPreferredWidth(70);        
        Deleted_Table.getColumnModel().getColumn(27).setPreferredWidth(70);        
        Deleted_Table.getColumnModel().getColumn(28).setPreferredWidth(70);        
        Deleted_Table.getColumnModel().getColumn(29).setPreferredWidth(70);
        Deleted_Table.getColumnModel().getColumn(30).setPreferredWidth(70);
        
        JOptionPane.showMessageDialog(frame, "Records sucessfuly pulled within date range");        
        }
    // ** not in use at the moment **
    /*
    public void getStores() throws SQLException {
        Statement s1 = DBConnect.connection.createStatement();        
        
        String pullStore = "SELECT Store As Str FROM completed_forms WHERE Store = '" + storeLines + "' AND Status = 'Deleted'";
        s1.execute(pullStore);
            try (ResultSet rs3 = s1.getResultSet()) {
                if (rs3.next()) {
                    pullStr = rs3.getString("Str");                
        }
        }        
    }
    */
    
    public void getDeletedStatus() throws SQLException {
        Statement s1 = DBConnect.connection.createStatement();
        String pullStore = "SELECT Form_Name As delFmNm FROM completed_forms WHERE Status = 'Deleted'";
        s1.execute(pullStore);

        try (ResultSet rs3 = s1.getResultSet()) {
                while (rs3.next()) {
                    String delFr = rs3.getString("delFmNm");
                    delFrmNm = delFr;
                    delFrmNmArray.add(delFrmNm);
        }
        }    
    }
    
    public void getDteRangeDel() throws SQLException {
        
        Statement s1 = DBConnect.connection.createStatement();
        
        StartDate = StartDateField.getText();
            EndDate = EndDateField.getText();
            
        for (int count = 0; count < delFrmNmArray.size(); count++ ) {        
        String pullDateRange = "SELECT Form_Name As FrmNm FROM completed_forms WHERE Last_Updated >= '"+ StartDate + "'"
        + " AND Last_Updated <= '" + EndDate + "' AND Form_Name = '" + delFrmNmArray.get(count) + "' AND Status = 'Deleted'";
        s1.execute(pullDateRange);
        try (ResultSet rs1 = s1.getResultSet()) {
                while (rs1.next()) {
                    String frmnm = rs1.getString("frmNm");
                    delFrm = frmnm;
                    delFrmNmRprtArray.add(delFrm);
                }
            }
        }
        for (int count = 0; count < delFrmNmRprtArray.size(); count++ ) {
            String InsertRange = "INSERT IGNORE INTO deleted_report (SELECT * FROM completed_lines WHERE Form_Name = '" + delFrmNmRprtArray.get(count) + "')";
            s1.execute(InsertRange);
            }    
      //  }
    }
        /*
        public void delReport() throws SQLException {
        Statement s1 = DBConnect.connection.createStatement();
            for (int count = 0; count < delFrmNmRprtArray.size(); count++ ) {
            String InsertRange = "INSERT IGNORE INTO deleted_report (SELECT * FROM completed_lines WHERE Form_Name = '" + delFrmNmRprtArray.get(count) + "')";
            s1.execute(InsertRange);
            }    
        } 
        */
    
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
            java.util.logging.Logger.getLogger(DeleteReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeleteReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeleteReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeleteReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeleteReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton Close_Button;
    protected static javax.swing.JTable Deleted_Table;
    protected javax.swing.JTextField EndDateField;
    protected javax.swing.JButton Get_Date_Range_Button;
    protected javax.swing.JButton Print_Button;
    protected javax.swing.JButton Save_To_Excel_Button;
    protected javax.swing.JTextField StartDateField;
    protected javax.swing.JLabel jLabel1;
    protected javax.swing.JLabel jLabel2;
    protected javax.swing.JLabel jLabel3;
    protected javax.swing.JLabel jLabel4;
    protected javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
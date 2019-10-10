/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form_port_app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SSwisher & WStevens
 */

// EBASdao = Exclusive Brand Adjustment Sheet data access object class
// the main function of this class is to gather data from and send to the EBAS class via user input
// then data is sent to or gathered from the database invadj automatically by this class
public class EBASdao {

    private static int z = 0;
    private static int cnt = 0;
    private static int cntRec = 0;
    // public static String dbPath = "db/InvAdj.mdb";
    private static String skuInput = null;
    private static String skuInput2 = null;
    public static String rproSKU = null;
    public static String prodName = null;
    public static String prodName2 = null;
    public static String prodAttr = null;
    public static String prodSize = null;
    public static String prodPr = null;
    public static String prodCost = null;
    public static String vendor = null;
    public static String VPnum = null;
    public static String ord_c = null;
    public static String DCS = null;
    public static String dteCreate = null;
    public static String[] recLine = new String[50];
    public static String skuRslt = null;
    public static String skuRslt2 = null;
    public static String Field1 = null;
    public static String Field2 = null;
    public static String Field3 = null;
    public static String Field4 = null;
    public static String Field5 = null;
    public static String Field6 = null;
    public static String Field7 = null;
    public static String Field8 = null;
    public static String Field9 = null;
    public static String Field10 = null;
    public static String Field11 = null;
    public static String Field12 = null;
    public static String Field13 = null;
    public static String Field14 = null;
    public static String Field15 = null;
    public static String rline = null;
    public static String nwFrm = null;
    public static String StFrmNm = null;
    public static String StFrmSt = null;
    public static int lnFlg = 0;
    public static String strNm = null;
    public static String StrCode = null;
    public static String dateCreated = null;

    // first sku validation in main method
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Statement s = DBConnect.connection.createStatement();
            String selTable1 = "SELECT Item AS rslt, Desc1 As rslt2 FROM item_master WHERE Item = '" + getSkuInput() + "'";
            s.execute(selTable1);
            ResultSet rs1 = s.getResultSet();
                if (rs1.next() && Integer.parseInt(rs1.getString("rslt")) >= 702630 && Integer.parseInt(rs1.getString("rslt")) <= 702672) {
                    rproSKU = rs1.getString("rslt");
                    prodName = rs1.getString("rslt2");
                  //  prodCost = rs1.getString("rslt3");
                    skuRslt = "good";
                } else {
                    skuRslt = "bad";
                    prodName = "No Name Available";
                }
            }
    
    // second sku validation
    public static void skuInput2() throws ClassNotFoundException, SQLException {
        Statement s = DBConnect.connection.createStatement();
            String selTable2 = "SELECT Item AS rslt, Desc1 As rslt2, Attr As rslt3, Size As rslt4, VC As rslt5, Desc2 As rslt6, P$_RT_TAG As rslt7, Ord_C$ As rslt8, C$ As rslt9, DCS As rslt10 FROM item_master WHERE Item = '" + getSkuInput2() + "' AND Brand != '' AND DCS != 'SO'";
            s.execute(selTable2);
            ResultSet rs2 = s.getResultSet();
                if (rs2.next() && (Integer.parseInt(rs2.getString("rslt")) < 702630 || Integer.parseInt(rs2.getString("rslt")) > 702672))  {                        
                    rproSKU = rs2.getString("rslt");
                    prodName2 = rs2.getString("rslt2");
                    prodAttr = rs2.getString("rslt3");
                    prodSize = rs2.getString("rslt4");                    
                    vendor = rs2.getString("rslt5");
                    VPnum = rs2.getString("rslt6");
                    prodPr = rs2.getString("rslt7");
                    ord_c = rs2.getString("rslt8");
                    prodCost = rs2.getString("rslt9");
                    DCS = rs2.getString("rslt10");
                    skuRslt2 = "good";
                } else {
                    skuRslt2 = "bad";
                    prodName2 = "No Name Available";
        }
    }

    // Check For Existing Form for store use
    public static void ChkForm() throws ClassNotFoundException, SQLException {
        try (Statement s = DBConnect.connection.createStatement()) {
            String selTable3 = "SELECT Store As str, Form_Name As frm, Date_Created As dte, Status As st FROM existing_forms WHERE Status = 'In-Process' AND Form_Name LIKE 'ebas_" + GtStore.store + "_0%' OR 'ebas_" + GtStore.store + "_1%'";
            s.execute(selTable3);
            try (ResultSet rs3 = s.getResultSet()) {
                if (rs3.next()) {
                    //rs3.next();
                    EBAS.Date_Label.setText("Date Started: " + rs3.getString("dte"));
                    nwFrm = "no";
                    StFrmNm = rs3.getString("frm");
                    StFrmSt = rs3.getString("st");
                    gtDB();
                } else {
                    nwFrm = "yes";
                }
            }
        }
    }
    
    // Check For Existing Form for DL and IC use
    public static void ChkForm2() throws ClassNotFoundException, SQLException {
        Statement s = DBConnect.connection.createStatement();
            String selTable3 = "SELECT Store As str, Form_Name As frm, Date_Created As dte, Status As st FROM existing_forms WHERE Form_Name = '" + InvAdj_Admin.frmNm + "'";
            s.execute(selTable3);
            try (ResultSet rs3 = s.getResultSet()) {
                if (rs3.next()) {
                    //rs3.next();
                    EBAS.Date_Label.setText("Date Started: " + rs3.getString("dte"));
                    nwFrm = "no";
                    StFrmNm = rs3.getString("frm");
                    StFrmSt = rs3.getString("st");
                    gtDB();
                } else {
                    nwFrm = "yes";
            }
        }
    }

    // inserts header data into database 
    public static void RcForm() throws ClassNotFoundException, SQLException {
        Statement s = DBConnect.connection.createStatement();
           String chkDte = "SELECT Date_Created As dateCreated FROM existing_forms WHERE Date_Created IS NOT NULL AND Form_Name = '"+ StFrmNm + "'";
            s.execute(chkDte);
            try (ResultSet rs1 = s.getResultSet()) {
               if  (rs1.next()) {
                dteCreate = rs1.getString("dateCreated");
               }
            }
                if (dteCreate == null) {
            String insertTable = "INSERT INTO existing_forms"
                    + "(Store, Form_Name, Date_Created, Last_Updated, Status) "
                    + "VALUES("
                    + "'" + GtStore.store.toLowerCase() + "'" + ","
                    + "'" + StFrmNm + "'" + ","
                    + "'" + GtDates.tdate + "'" + ","
                    + "'" + GtDates.tdate + "'" + ","
                    + "'" + "In-Process" + "'"
                    + ")";
                    s.execute(insertTable);                              
                            
            //String crtTable = "SELECT * INTO " + StFrmNm + " FROM ias WHERE 1=2";
            String crtTable = "CREATE TABLE " + StFrmNm + " SELECT * FROM ebas";
            s.execute(crtTable); 
                }
                
        insLns();
     // lastUpdate();
    }
    
    public static void lastUpdate() throws SQLException {
        Statement s = DBConnect.connection.createStatement();
        String updateHeader = "UPDATE existing_forms "
                    + "Set Form_Name = '"+ StFrmNm +"', Last_Updated = '" + GtDates.tdate + "' WHERE Form_Name = '"+ StFrmNm +"'";
                    s.execute(updateHeader);        
    }

    // inserts the all lines of data into temporary table in database
    // this inserts 10 lines of data regardless of user data input, if there is no sku input 
    // all remaining rows are filled with null values 
    public static void insLns() throws ClassNotFoundException, SQLException {
        Statement s = DBConnect.connection.createStatement();
            for (int w = 1; w < 11; w++) {
                String insLines = "INSERT INTO " + StFrmNm
                        + "(Line) "
                        + "VALUES("
                        + "'" + w + "'"
                        + ")";
                s.execute(insLines);
        }
    }
    
    public static void StrConversion() throws SQLException {
        strNm = GtStore.store;
        Statement s1 = DBConnect.connection.createStatement();
        String  StrConvert = "SELECT Store_Code As StrCd FROM stores WHERE CG_Store = '"+ strNm +"'";
        s1.execute(StrConvert);
        try (ResultSet rs01 = s1.getResultSet()) {
                while (rs01.next()) {
                    StrCode = rs01.getString("StrCd");
                }   
        }
    }

    // if any user type unchecks the checkbox to make changes to a line with previous existing data
    // this will update any chagnes in the temporary table 
    public static void upDB() throws ClassNotFoundException, SQLException {
        Statement s = DBConnect.connection.createStatement();
            String upTable = "UPDATE " + StFrmNm
                    + " Set Sku = '" + Field1 + "',"
                    + " Qty = '" + Field2 + "',"
                    + " Description1 = '" + Field3 + "',"
                    + " Orig_Sku = '" + Field4 + "',"
                    + " Description2 = '" + Field5 + "',"
                    + " Attribute2 = '" + Field6 + "',"
                    + " Size2 = '" + Field7 + "',"
                    + " Orig_Retail = '" + Field8 + "',"
                    + " Manuf_Inspection = '" + Field9 + "',"
                    + " New_Used = '" + Field10 + "',"
                    + " Reason = '" + Field11 + "',"
                    + " Desc_Damage = '" + Field12 + "',"
                    + " Cust_Satisf = '" + Field13 + "',"
                    + " Warranty = '" + Field14 + "',"
                    + " Second_Sku_Vendor = '" + vendor + "',"
                    + " Ln_Date = '" + GtDates.ldate + "',"                                      
                    + " Form_Name = '" + StFrmNm + "',"  
                    + " Second_Cost = '" + prodCost + "',"
                    + " Second_Sku_VPNum = '" + VPnum + "',"
                    + " First_DCS = null, "
                    + " Second_DCS = '" + DCS + "',"
                    + " Ord_C$ = '" + ord_c + "'"
                    + " WHERE Line = " + rline;
            /*
            + "(Line, Qty, SKU, Description1, Reason, Desc_Damage, Orig_Retail, New_SKU, Description2, Qty_In, Ln_Date) "
            + "VALUES("
            + "'" + rline + "'" + ","
            + "'" + Field1 + "'" + ","
            + "'" + Field2 + "'" + ","
            + "'" + Field3 + "'" + ","
            + "'" + Field4 + "'" + ","
            + "'" + Field5 + "'" + ","
            + "'" + Field6 + "'" + ","
            + "'" + Field7 + "'" + ","
            + "'" + Field8 + "'" + ","
            + "'" + Field9 + "'" + ","
            + "'" + Field10 + "'" + ","
            + "'" + GtDates.ldate + "'"
            + ")";
            */
            s.execute(upTable);
        
    }

    // this initially gathers the data from the temporary table for an existing form all 10 lines but one line at a time 
    // but inserts all lines as the same time from a user viewpoint
    // if values are null the lines are left blank in the form
    public static void gtDB() throws ClassNotFoundException, SQLException {
        Statement s1 = DBConnect.connection.createStatement();
            String selTable1 = "SELECT COUNT(*) AS total FROM " + StFrmNm;
            s1.execute(selTable1);
            ResultSet rs1 = s1.getResultSet();
                rs1.next();
                setCnt(rs1.getInt("total"));
            
            for (int w = 1; w < getCnt() + 1; w++) {
                setCntRec(getCntRec() + 1);
                String selTable3 = "SELECT Sku AS rslt, Qty As rslt2, Description1 As rslt3, "
                        + "Orig_Sku As rslt4, Description2 As rslt5, Attribute2 As rslt6, Size2 As rslt7, Orig_Retail As rslt8, "
                        + "Manuf_Inspection As rslt9, New_Used As rslt10, Reason As rslt11, Desc_Damage As rslt12, "
                        + "Cust_Satisf As rslt13, Warranty As rslt14, Ln_Date As rslt15, Second_Cost As rslt16, Form_Name As rslt17, Second_Sku_Vendor As rslt18, "
                        + "Second_Sku_VPNum As rslt19, Ord_C$ As rslt20, First_DCS As rslt21, Second_DCS As rslt22 "
                        + "FROM " + StFrmNm + " WHERE Line = " + w;
                s1.execute(selTable3);
                String sku;
                String qty;
                String descr1;
                String orgSku;
                String descr2;
                String attr;
                String size;
                String orgRt;
                String mnInsp;
                String nwusd;
                String reas;
                String ddamg;
                String custSat;
                String wrrnty;
                String lndate;
                String scost;                
                String frmnme;
                String svendr;
                String svpn;
                String ordc;
                String fdcs;
                String sdcs;
                ResultSet rs3 = s1.getResultSet();
                    rs3.next();
                    sku = rs3.getString("rslt");
                    qty = rs3.getString("rslt2");
                    descr1 = rs3.getString("rslt3");
                    orgSku = rs3.getString("rslt4");
                    descr2 = rs3.getString("rslt5");
                    attr = rs3.getString("rslt6");
                    size = rs3.getString("rslt7");
                    orgRt = rs3.getString("rslt8");
                    mnInsp = rs3.getString("rslt9");
                    nwusd = rs3.getString("rslt10");
                    reas = rs3.getString("rslt11");
                    ddamg = rs3.getString("rslt12");
                    custSat = rs3.getString("rslt13");
                    wrrnty = rs3.getString("rslt14");
                    lndate = rs3.getString("rslt15");
                    scost = rs3.getString("rslt16");                    
                    frmnme = rs3.getString("rslt17");
                    svendr = rs3.getString("rslt18");
                    svpn = rs3.getString("rslt19");
                    ordc = rs3.getString("rslt20");
                    fdcs = rs3.getString("rslt21");     
                    sdcs = rs3.getString("rslt22");
                recLine[w] = sku + ";" + qty + "; " + descr1 + ";" + orgSku + ";" + descr2 + ";" + attr + ";" + size + ";" + orgRt + ";" + mnInsp + ";" +
                        nwusd + ";" + reas + ";" + ddamg + ";"  + custSat + ";" + wrrnty + ";" + lndate+ ";" + scost + ";" + frmnme + ";" + svendr + ";" + 
                        svpn + ";" + ordc + ";" + fdcs + ";" + sdcs;
                
                //System.out.println(sku + ";" + qty + "; " + nwusd + ";" + descr1 + ";" + reas + ";" + ddamg + ";" + orgSku + ";" + mnInsp + ";" + custSat + ";" + wrrnty + ";" + lndate);
            }
        }
    
    public static void DeleteForm() throws SQLException {
        
        saveDeletedLines();
        
        setDeletedForms();
         
        deleteTable();
            
        deleteFormHeader();
            
        InvAdj_Admin.Rpnt();
    }
    
    public static void saveDeletedLines() throws SQLException {
        Statement s1 = DBConnect.connection.createStatement();
            String delLines = "INSERT INTO completed_lines "
                + "(Line, Sku, Qty, Description1, Orig_Sku, Description2, Attribute2, Size2, Orig_Retail, Manuf_Inspection, New_Used, Reason, "
                + "Desc_Damage, Cust_Satisf, Warranty, Second_Cost, Form_Name, Ln_Date, Second_Sku_Vendor, Second_Sku_VPNum, Ord_C$, New_Sku, "
                + "First_DCS, Second_DCS) SELECT * FROM " + StFrmNm + " WHERE Sku IS NOT NULL";
            s1.execute(delLines);
    
            String updateStrFmNm = "UPDATE completed_lines "
                // + "SET Form_Name = '" + InvAdj_Admin.frmNm + "'" + ","
                + "SET Store = '" + InvAdj_Admin.frmNm.split("_")[1] + "'"
                + " WHERE Sku IS NOT NULL AND Form_Name = '"+InvAdj_Admin.frmNm+"'";
            s1.execute(updateStrFmNm);
    }        
    
    public static void setDeletedForms() throws SQLException {
        Statement s1 = DBConnect.connection.createStatement();
        String delTable = "INSERT INTO completed_forms"
                    + "(Store, Form_Name, Date_Created, Last_Updated, Status) "
                    + "VALUES("
                    + "'" + InvAdj_Admin.frmNm.split("_")[1] + "'" + ","
                    + "'" + InvAdj_Admin.frmNm + "'" + ","
                    + "'" + GtDates.tdate + "'" + ","
                    + "'" + GtDates.tdate + "'" + ","
                    + "'" + "Deleted" + "'"
                    + ")";
            s1.execute(delTable);
    }
        
    public static void deleteTable() throws SQLException {
        Statement s1 = DBConnect.connection.createStatement();
        String delTable = "DROP TABLE " + StFrmNm;
            s1.execute(delTable);
    }
    
    public static void deleteFormHeader() throws SQLException {
        Statement s1 = DBConnect.connection.createStatement();
        String delTableHeader = "DELETE FROM existing_forms WHERE Form_Name = '"+ StFrmNm +"'";
            s1.execute(delTableHeader);
    }
    

    // Record Line Processing
    // this makes all fields for this line disabled from possible data changes and updates the database table
    public static void Ln1() {
        EBAS.L1_Timestamp.setText(GtDates.ldate);

        EBAS.L1_First_Sku.setEnabled(false);
        EBAS.L1_Qty_Out.setEnabled(false);
        EBAS.L1_First_Desc.setEnabled(false);
        EBAS.L1_Orig_Sku.setEnabled(false);
        EBAS.L1_Orig_Desc.setEnabled(false);
        EBAS.L1_Orig_Attr.setEnabled(false);
        EBAS.L1_Orig_Size.setEnabled(false);
        EBAS.L1_Orig_Retail.setEnabled(false);
        EBAS.L1_Manuf_Inspec.setEnabled(false);
        EBAS.L1_New_Used.setEnabled(false);
        EBAS.L1_Reason_DropDown.setEnabled(false);
        EBAS.L1_Desc_Damage.setEnabled(false);
        EBAS.L1_Cust_Satisf_ChkBox.setEnabled(false);
        EBAS.L1_Warranty_ChkBox.setEnabled(false);
        
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Record Line Processing
    // this makes all fields for this line disabled from possible data changes and updates the database table
    public static void Ln2() {
        EBAS.L2_Timestamp.setText(GtDates.ldate);
        
        EBAS.L2_First_Sku.setEnabled(false);
        EBAS.L2_Qty_Out.setEnabled(false);
        EBAS.L2_First_Desc.setEnabled(false);
        EBAS.L2_Orig_Sku.setEnabled(false);
        EBAS.L2_Orig_Desc.setEnabled(false);
        EBAS.L2_Orig_Attr.setEnabled(false);
        EBAS.L2_Orig_Size.setEnabled(false);
        EBAS.L2_Orig_Retail.setEnabled(false);
        EBAS.L2_Manuf_Inspec.setEnabled(false);
        EBAS.L2_New_Used.setEnabled(false);
        EBAS.L2_Reason_DropDown.setEnabled(false);
        EBAS.L2_Desc_Damage.setEnabled(false);
        EBAS.L2_Cust_Satisf_ChkBox.setEnabled(false);
        EBAS.L2_Warranty_ChkBox.setEnabled(false);
        
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      // Record Line Processing
      // this makes all fields for this line disabled from possible data changes and updates the database table
      public static void Ln3() {
        EBAS.L3_Timestamp.setText(GtDates.ldate);
        
        EBAS.L3_First_Sku.setEnabled(false);
        EBAS.L3_Qty_Out.setEnabled(false);
        EBAS.L3_First_Desc.setEnabled(false);
        EBAS.L3_Orig_Sku.setEnabled(false);
        EBAS.L3_Orig_Desc.setEnabled(false);
        EBAS.L3_Orig_Attr.setEnabled(false);
        EBAS.L3_Orig_Size.setEnabled(false);
        EBAS.L3_Orig_Retail.setEnabled(false);
        EBAS.L3_Manuf_Inspec.setEnabled(false);
        EBAS.L3_New_Used.setEnabled(false);
        EBAS.L3_Reason_DropDown.setEnabled(false);
        EBAS.L3_Desc_Damage.setEnabled(false);
        EBAS.L3_Cust_Satisf_ChkBox.setEnabled(false);
        EBAS.L3_Warranty_ChkBox.setEnabled(false);
        
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        // Record Line Processing
        // this makes all fields for this line disabled from possible data changes and updates the database table
        public static void Ln4() {
        EBAS.L4_Timestamp.setText(GtDates.ldate);
        
        EBAS.L4_First_Sku.setEnabled(false);
        EBAS.L4_Qty_Out.setEnabled(false);
        EBAS.L4_First_Desc.setEnabled(false);
        EBAS.L4_Orig_Sku.setEnabled(false);
        EBAS.L4_Orig_Desc.setEnabled(false);
        EBAS.L4_Orig_Attr.setEnabled(false);
        EBAS.L4_Orig_Size.setEnabled(false);
        EBAS.L4_Orig_Retail.setEnabled(false);
        EBAS.L4_Manuf_Inspec.setEnabled(false);
        EBAS.L4_New_Used.setEnabled(false);
        EBAS.L4_Reason_DropDown.setEnabled(false);
        EBAS.L4_Desc_Damage.setEnabled(false);
        EBAS.L4_Cust_Satisf_ChkBox.setEnabled(false);
        EBAS.L4_Warranty_ChkBox.setEnabled(false);
        
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        // Record Line Processing
        // this makes all fields for this line disabled from possible data changes and updates the database table
        public static void Ln5() {
        EBAS.L5_Timestamp.setText(GtDates.ldate);
        
        EBAS.L5_First_Sku.setEnabled(false);
        EBAS.L5_Qty_Out.setEnabled(false);
        EBAS.L5_First_Desc.setEnabled(false);
        EBAS.L5_Orig_Sku.setEnabled(false);
        EBAS.L5_Orig_Desc.setEnabled(false);
        EBAS.L5_Orig_Attr.setEnabled(false);
        EBAS.L5_Orig_Size.setEnabled(false);
        EBAS.L5_Orig_Retail.setEnabled(false);
        EBAS.L5_Manuf_Inspec.setEnabled(false);
        EBAS.L5_New_Used.setEnabled(false);
        EBAS.L5_Reason_DropDown.setEnabled(false);
        EBAS.L5_Desc_Damage.setEnabled(false);
        EBAS.L5_Cust_Satisf_ChkBox.setEnabled(false);
        EBAS.L5_Warranty_ChkBox.setEnabled(false);
        
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
        // Record Line Processing
        // this makes all fields for this line disabled from possible data changes and updates the database table
        public static void Ln6() {
        EBAS.L6_Timestamp.setText(GtDates.ldate);
        
        EBAS.L6_First_Sku.setEnabled(false);
        EBAS.L6_Qty_Out.setEnabled(false);
        EBAS.L6_First_Desc.setEnabled(false);
        EBAS.L6_Orig_Sku.setEnabled(false);
        EBAS.L6_Orig_Desc.setEnabled(false);
        EBAS.L6_Orig_Attr.setEnabled(false);
        EBAS.L6_Orig_Size.setEnabled(false);
        EBAS.L6_Orig_Retail.setEnabled(false);
        EBAS.L6_Manuf_Inspec.setEnabled(false);
        EBAS.L6_New_Used.setEnabled(false);
        EBAS.L6_Reason_DropDown.setEnabled(false);
        EBAS.L6_Desc_Damage.setEnabled(false);
        EBAS.L6_Cust_Satisf_ChkBox.setEnabled(false);
        EBAS.L6_Warranty_ChkBox.setEnabled(false);
        
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
        // Record Line Processing
        // this makes all fields for this line disabled from possible data changes and updates the database table
        public static void Ln7() {
        EBAS.L7_Timestamp.setText(GtDates.ldate);
        
        EBAS.L7_First_Sku.setEnabled(false);
        EBAS.L7_Qty_Out.setEnabled(false);
        EBAS.L7_First_Desc.setEnabled(false);
        EBAS.L7_Orig_Sku.setEnabled(false);
        EBAS.L7_Orig_Desc.setEnabled(false);
        EBAS.L7_Orig_Attr.setEnabled(false);
        EBAS.L7_Orig_Size.setEnabled(false);
        EBAS.L7_Orig_Retail.setEnabled(false);
        EBAS.L7_Manuf_Inspec.setEnabled(false);
        EBAS.L7_New_Used.setEnabled(false);
        EBAS.L7_Reason_DropDown.setEnabled(false);
        EBAS.L7_Desc_Damage.setEnabled(false);
        EBAS.L7_Cust_Satisf_ChkBox.setEnabled(false);
        EBAS.L7_Warranty_ChkBox.setEnabled(false);
        
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
        // Record Line Processing
        // this makes all fields for this line disabled from possible data changes and updates the database table
        public static void Ln8() {
        EBAS.L8_Timestamp.setText(GtDates.ldate);
        
        EBAS.L8_First_Sku.setEnabled(false);
        EBAS.L8_Qty_Out.setEnabled(false);
        EBAS.L8_First_Desc.setEnabled(false);
        EBAS.L8_Orig_Sku.setEnabled(false);
        EBAS.L8_Orig_Desc.setEnabled(false);
        EBAS.L8_Orig_Attr.setEnabled(false);
        EBAS.L8_Orig_Size.setEnabled(false);
        EBAS.L8_Orig_Retail.setEnabled(false);
        EBAS.L8_Manuf_Inspec.setEnabled(false);
        EBAS.L8_New_Used.setEnabled(false);
        EBAS.L8_Reason_DropDown.setEnabled(false);
        EBAS.L8_Desc_Damage.setEnabled(false);
        EBAS.L8_Cust_Satisf_ChkBox.setEnabled(false);
        EBAS.L8_Warranty_ChkBox.setEnabled(false);
        
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
        // Record Line Processing
        // this makes all fields for this line disabled from possible data changes and updates the database table
        public static void Ln9() {
        EBAS.L9_Timestamp.setText(GtDates.ldate);
        
        EBAS.L9_First_Sku.setEnabled(false);
        EBAS.L9_Qty_Out.setEnabled(false);
        EBAS.L9_First_Desc.setEnabled(false);
        EBAS.L9_Orig_Sku.setEnabled(false);
        EBAS.L9_Orig_Desc.setEnabled(false);
        EBAS.L9_Orig_Attr.setEnabled(false);
        EBAS.L9_Orig_Size.setEnabled(false);
        EBAS.L9_Orig_Retail.setEnabled(false);
        EBAS.L9_Manuf_Inspec.setEnabled(false);
        EBAS.L9_New_Used.setEnabled(false);
        EBAS.L9_Reason_DropDown.setEnabled(false);
        EBAS.L9_Desc_Damage.setEnabled(false);
        EBAS.L9_Cust_Satisf_ChkBox.setEnabled(false);
        EBAS.L9_Warranty_ChkBox.setEnabled(false);
        
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        // Record Line Processing
        // this makes all fields for this line disabled from possible data changes and updates the database table
        public static void Ln10() {
        EBAS.L10_Timestamp.setText(GtDates.ldate);
        
        EBAS.L10_First_Sku.setEnabled(false);
        EBAS.L10_Qty_Out.setEnabled(false);
        EBAS.L10_First_Desc.setEnabled(false);
        EBAS.L10_Orig_Sku.setEnabled(false);
        EBAS.L10_Orig_Desc.setEnabled(false);
        EBAS.L10_Orig_Attr.setEnabled(false);
        EBAS.L10_Orig_Size.setEnabled(false);
        EBAS.L10_Orig_Retail.setEnabled(false);
        EBAS.L10_Manuf_Inspec.setEnabled(false);
        EBAS.L10_New_Used.setEnabled(false);
        EBAS.L10_Reason_DropDown.setEnabled(false);
        EBAS.L10_Desc_Damage.setEnabled(false);
        EBAS.L10_Cust_Satisf_ChkBox.setEnabled(false);
        EBAS.L10_Warranty_ChkBox.setEnabled(false);
        
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
       // this clears all the data in the corresponding line from the temporary table in the database
       public static void ClearLine1() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, Manuf_Inspection = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Warranty = null, Second_Cost = null, "
                       + "Form_Name = null, Second_Sku_Vendor = null, Ln_Date = null, Second_Sku_VPNum = null, Ord_C$ = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + EBAS.L1.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database   
       public static void ClearLine2() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, Manuf_Inspection = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Warranty = null, Second_Cost = null, "
                       + "Form_Name = null, Second_Sku_Vendor = null, Ln_Date = null, Second_Sku_VPNum = null, Ord_C$ = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + EBAS.L2.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database
       public static void ClearLine3() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, Manuf_Inspection = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Warranty = null, Second_Cost = null, "
                       + "Form_Name = null, Second_Sku_Vendor = null, Ln_Date = null, Second_Sku_VPNum = null, Ord_C$ = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + EBAS.L3.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database
       public static void ClearLine4() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, Manuf_Inspection = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Warranty = null, Second_Cost = null, "
                       + "Form_Name = null, Second_Sku_Vendor = null, Ln_Date = null, Second_Sku_VPNum = null, Ord_C$ = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + EBAS.L4.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database
       public static void ClearLine5() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, Manuf_Inspection = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Warranty = null, Second_Cost = null, "
                       + "Form_Name = null, Second_Sku_Vendor = null, Ln_Date = null, Second_Sku_VPNum = null, Ord_C$ = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + EBAS.L5.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database
       public static void ClearLine6() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, Manuf_Inspection = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Warranty = null, Second_Cost = null, "
                       + "Form_Name = null, Second_Sku_Vendor = null, Ln_Date = null, Second_Sku_VPNum = null, Ord_C$ = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + EBAS.L6.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database
       public static void ClearLine7() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, Manuf_Inspection = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Warranty = null, Second_Cost = null, "
                       + "Form_Name = null, Second_Sku_Vendor = null, Ln_Date = null, Second_Sku_VPNum = null, Ord_C$ = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + EBAS.L7.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database
       public static void ClearLine8() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, Manuf_Inspection = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Warranty = null, Second_Cost = null, "
                       + "Form_Name = null, Second_Sku_Vendor = null, Ln_Date = null, Second_Sku_VPNum = null, Ord_C$ = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + EBAS.L8.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database   
       public static void ClearLine9() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, Manuf_Inspection = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Warranty = null, Second_Cost = null, "
                       + "Form_Name = null, Second_Sku_Vendor = null, Ln_Date = null, Second_Sku_VPNum = null, Ord_C$ = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + EBAS.L9.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database
       public static void ClearLine10() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, Manuf_Inspection = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Warranty = null, Second_Cost = null, "
                       + "Form_Name = null, Second_Sku_Vendor = null, Ln_Date = null, Second_Sku_VPNum = null, Ord_C$ = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + EBAS.L10.getText() + "'";
               s.execute(clearLine);
           }
       }   
       
    // updates status of Form to Pending
    public static void FormPending() throws ClassNotFoundException, SQLException {
        try (Statement s = DBConnect.connection.createStatement()) {
            String upTable = "UPDATE existing_forms "
                    + "SET Status = 'Pending', Last_Updated = '" + GtDates.tdate + "'"
                    + "WHERE Form_Name = '" + StFrmNm + "'";
            s.execute(upTable);
        }
    }
    
    // updates status of Form to Approved
    public static void FormApproved() throws ClassNotFoundException, SQLException {
        try (Statement s = DBConnect.connection.createStatement()) {
            String upTable = "UPDATE existing_forms "
                    + "SET Status = 'Approved', Last_Updated = '" + GtDates.tdate + "'"
                    + "WHERE Form_Name = '" + InvAdj_Admin.frmNm + "'";
            s.execute(upTable);
        }
    }
    
     // this deletes the temporary table and inserts into completed forms table for permanant storage 
     public static void FormReadyForExport() throws ClassNotFoundException, SQLException {
       Statement s = DBConnect.connection.createStatement();
       String getCreatedDate = "SELECT Date_Created As dateCreated FROM existing_forms WHERE Date_Created IS NOT NULL AND Form_Name = '"+ InvAdj_Admin.frmNm + "'";
       s.execute(getCreatedDate); 
       try (ResultSet rs3 = s.getResultSet()) {
            while (rs3.next()) {
                dateCreated = rs3.getString("dateCreated");
                }
        }   
       
            String updateExisting = "UPDATE existing_forms "
                    + "SET Status = 'Ready_for_Export', Last_Updated = '" + GtDates.tdate + "'"
                    + "WHERE Form_Name = '" + InvAdj_Admin.frmNm + "'";
       
            String insTable = "INSERT INTO completed_forms"
                    + "(Store, Form_Name, Date_Created, Last_Updated, Status) "
                    + "VALUES("
                    + "'" + InvAdj_Admin.frmNm.split("_")[1] + "'" + ","
                    + "'" + InvAdj_Admin.frmNm + "'" + ","
                 // + "'" + InvAdj_Admin.frmDate + "'" + ","
                    + "'" + dateCreated + "'" + ","
                    + "'" + GtDates.tdate + "'" + ","
                    + "'" + "Ready_for_Export" + "'"
                    + ")";
                       
            
            s.execute(updateExisting);                  
            s.execute(insTable);
         
    }
    
     // this inserts all the line data into database table that archives all line data from all forms
     // the line data in the database is identifiable by form name and line number
    public static void LineArchive() throws ClassNotFoundException, SQLException {
        Statement s1 = DBConnect.connection.createStatement();
     String writeLine = "INSERT INTO completed_lines "
            + "(Line, Sku, Qty, Description1, Orig_Sku, Description2, Attribute2, Size2, Orig_Retail, Manuf_Inspection, New_Used, Reason, "
            + "Desc_Damage, Cust_Satisf, Warranty, Second_Cost, Form_Name, Ln_Date, Second_Sku_Vendor, Second_Sku_VPNum, Ord_C$, New_Sku, "
            + "First_DCS, Second_DCS) SELECT * FROM "  + InvAdj_Admin.frmNm + " WHERE Sku IS NOT NULL";
                
                // This inserts store and form name because I could not figure out how to include it in the insert statement above
                String setStrFmNm = "UPDATE completed_lines "
                   // + "SET Form_Name = '" + InvAdj_Admin.frmNm + "'" + ","
                    + "SET Store = '" + InvAdj_Admin.frmNm.split("_")[1] + "' "
                    + "WHERE Sku IS NOT NULL AND Form_Name = '"+ InvAdj_Admin.frmNm +"'";
                s1.execute(writeLine); 
                s1.execute(setStrFmNm);
    }
     
    /* not in use
    //Form Export 
    public static void FormExport() throws ClassNotFoundException, SQLException, IOException {
        String[] args = null;
        GtDates.main(args);
        try (Statement s = DBConnect.connection.createStatement()) {
            String rmForm = "DELETE FROM existing_forms "
                    + " WHERE Form_Name = '" + InvAdj_Admin.frmNm + "'";
            
            String upTable = "INSERT INTO completed_forms"
                    + "(Store, Form_Name, Date_Created, Last_Updated, Status) "
                    + "VALUES("
                    + "'" + InvAdj_Admin.frmNm.split("_")[1] + "'" + ","f
                    + "'" + InvAdj_Admin.frmNm + "'" + ","
                    + "'" + GtDates.tdate + "'" + ","
                    + "'" + GtDates.tdate + "'" + ","
                    + "'" + "Completed" + "'"
                    + ")";
            
            s.execute(rmForm);
            s.execute(upTable);
        }
    } not in use */
     
    /*
    // drop table
    public static void drTable() throws ClassNotFoundException, SQLException {
        String[] args = null;
        GtDates.main(args);
        try (Statement s = DBConnect.connection.createStatement()) {
            String dTable = "DROP TABLE " + InvAdj_Admin.frmNm;
            
            s.execute(dTable);
        }
    }
    */
    //Commit Line
    public static void CmtLn() {
        if (nwFrm.equals("yes")) {
            StFrmNm = "ebas_" + GtStore.store.toLowerCase() + "_" + GtDates.fdate;
            nwFrm = "no";
            try {
                RcForm();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(EBASdao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String[] args = null;
        GtDates.main(args);
        switch (lnFlg) {
            case 1:
                Ln1();
                break;
            case 2:
                Ln2();
                break;
            case 3:
                Ln3();
                break;
            case 4:
                Ln4();
                break;
            case 5:
                Ln5();
                break;
            case 6:
                Ln6();
                break;
            case 7:
                Ln7();
                break;
            case 8:
                Ln8();
                break;
            case 9:
                Ln9();
                break;
            case 10:
                Ln10();
                break;
        }
    }

    /**
     * @return the z
     */
    public static int getZ() {
        return z;
    }

    /**
     * @param aZ the z to set
     */
    public static void setZ(int aZ) {
        z = aZ;
    }

    /**
     * @return the cnt
     */
    public static int getCnt() {
        return cnt;
    }

    /**
     * @param aCnt the cnt to set
     */
    public static void setCnt(int aCnt) {
        cnt = aCnt;
    }

    /**
     * @return the cntRec
     */
    public static int getCntRec() {
        return cntRec;
    }

    /**
     * @param aCntRec the cntRec to set
     */
    public static void setCntRec(int aCntRec) {
        cntRec = aCntRec;
    }

    /**
     * @return the skuInput
     */
    public static String getSkuInput() {
        return skuInput;
    }

    /**
     * @param aSkuInput the skuInput to set
     */
    public static void setSkuInput(String aSkuInput) {
        skuInput = aSkuInput;
    }

    /**
     * @return the skuInput2
     */
    public static String getSkuInput2() {
        return skuInput2;
    }

    /**
     * @param aSkuInput2 the skuInput2 to set
     */
    public static void setSkuInput2(String aSkuInput2) {
        skuInput2 = aSkuInput2;
    }
}

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

// NEBASdao = Non-Exclusive Brand Adjustment Sheet data access object class
// the main function of this class is to gather data from and send to the NEBAS class via user input
// then data is sent to or gathered from the database invadj automatically by this class
public class NEBASdao {

    public static int z = 0;
    public static int cnt = 0;
    public static int cntRec = 0;
    // public static String dbPath = "db/InvAdj.mdb";
    public static String skuInput = null;
    public static String skuInput2 = null;
    public static String rproSKU = null;
    public static String prodName = null;
    public static String prodName2 = null;
    public static String prodPr = null;
    public static String prodAttr2 = null;
    public static String prodSize2 = null;
    public static String prodCost = null;
    public static String ord_c = null;
    public static String vendor = null;
    public static String VPnum = null;
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
    public static String rline = null;
    public static String nwFrm = null;
    public static String StFrmNm = null;
    public static String StFrmSt = null;
    public static int lnFlg = 0;
    public static String strNm = null;
    public static String StrCode = null;
    public static String dateCreated = null;

    // main class that checks the first sku input from NEBAS class
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try (Statement s = DBConnect.connection.createStatement()) {
            String selTable1 = "SELECT Item AS rslt, Desc1 As rslt2 FROM item_master WHERE Item = '" + skuInput + "'";
            s.execute(selTable1);
            try (ResultSet rs1 = s.getResultSet()) {
                if (rs1.next() && Integer.parseInt(rs1.getString("rslt")) > 14635 && Integer.parseInt(rs1.getString("rslt")) < 14679){
                    rproSKU = rs1.getString("rslt");
                    prodName = rs1.getString("rslt2");                      
                    skuRslt = "good";
                } else {
                    skuRslt = "bad";
                    prodName = "No Name Available";
                }
            }
        }
    }

    // second sku input from nebas check
   public static void skuInput2() throws ClassNotFoundException, SQLException {
        try (Statement s = DBConnect.connection.createStatement()) {
            String selTable2 = "SELECT Item AS rslt, Desc1 As rslt2, Attr As rslt3, Size As rslt4, P$_RT_TAG As rslt5, C$ As rslt6, Ord_C$ As rslt7, VC As rslt8, Desc2 As rslt9, DCS As rslt10 FROM item_master WHERE Item = '" + skuInput2 + "' AND DCS != 'SO' AND Brand = ''";
            s.execute(selTable2);
            try (ResultSet rs2 = s.getResultSet()) {
                if (rs2.next() && (Integer.parseInt(rs2.getString("rslt")) < 14636 || Integer.parseInt(rs2.getString("rslt")) > 14678 )){
                    rproSKU = rs2.getString("rslt");
                    prodName2 = rs2.getString("rslt2");
                    prodAttr2 = rs2.getString("rslt3");
                    prodSize2 = rs2.getString("rslt4");
                    prodPr = rs2.getString("rslt5");              
                    prodCost = rs2.getString("rslt6");
                    ord_c = rs2.getString("rslt7");
                    vendor = rs2.getString("rslt8");
                    VPnum = rs2.getString("rslt9");
                    DCS = rs2.getString("rslt10");
                    skuRslt2 = "good";
                } else {
                    skuRslt2 = "bad";
                    prodName2 = "No Name Available";
                }
            }
        }
    }
    
    // Check For Existing Form for store use
    public static void ChkForm() throws ClassNotFoundException, SQLException {
        try (Statement s = DBConnect.connection.createStatement()) {
            String selTable3 = "SELECT Store As str, Form_Name As frm, Date_Created As dte, Status As st FROM existing_forms WHERE Status = 'In-Process' AND Form_Name LIKE 'nebas_" + GtStore.store + "_0%' OR 'nebas_" + GtStore.store + "_1%'";
            s.execute(selTable3);
            try (ResultSet rs3 = s.getResultSet()) {
                if (rs3.next()) {
                    //rs3.next();
                    NEBAS.Date_Label.setText("Date Started: " + rs3.getString("dte"));
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
        try (Statement s = DBConnect.connection.createStatement()) {
            String selTable3 = "SELECT Store As str, Form_Name As frm, Date_Created As dte, Status As st FROM existing_forms WHERE Form_Name LIKE '" + InvAdj_Admin.frmNm + "'";
            s.execute(selTable3);
            try (ResultSet rs3 = s.getResultSet()) {
                if (rs3.next()) {
                    //rs3.next();
                    NEBAS.Date_Label.setText("Date Started: " + rs3.getString("dte"));
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

    // Creates table header data
    public static void RcForm() throws ClassNotFoundException, SQLException {
        try (Statement s = DBConnect.connection.createStatement()) {
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
            String crtTable = "CREATE TABLE " + StFrmNm + " SELECT * FROM nebas";
            s.execute(crtTable); 
                }
        insLns();
     // lastUpdate();
        }
    }
    
    public static void lastUpdate() throws SQLException {
        Statement s = DBConnect.connection.createStatement();
        String updateHeader = "UPDATE existing_forms "
                    + "Set Form_Name = '"+ StFrmNm +"', Last_Updated = '" + GtDates.tdate + "' WHERE Form_Name = '"+ StFrmNm +"'";
                    s.execute(updateHeader);        
    }

    // inserts 10 lines into temporary database table and inserts null values in table where there is no sku input
    public static void insLns() throws ClassNotFoundException, SQLException {
        try (Statement s = DBConnect.connection.createStatement()) {
            for (int w = 1; w < 11; w++) {
                String insLines = "INSERT INTO " + StFrmNm
                        + "(Line) "
                        + "VALUES("
                        + "'" + w + "'"
                        + ")";
                s.execute(insLines);
            }
        }
    }
    
    public static void StrConversion() throws SQLException {
        strNm = GtStore.store;
        Statement s1 = DBConnect.connection.createStatement();
        String  StrConvert = "SELECT Store_Code As StrCd FROM stores WHERE CG_Store = '"+ strNm +"'";
        s1.execute(StrConvert);
        try (ResultSet rs01 = s1.getResultSet()) {             
                if (rs01.next()) {
                    StrCode = rs01.getString("StrCd");
                }   
        }
    }

    // if any user type unchecks the checkbox to make changes to a line with previous existing data
    // this will update any chagnes in the temporary table
    public static void upDB() throws ClassNotFoundException, SQLException {
        try (Statement s = DBConnect.connection.createStatement()) {
            String upTable = "UPDATE " + StFrmNm
                    + " Set Sku = '" + Field1 + "',"
                    + " Qty = '" + Field2 + "',"
                    + " Description1 = '" + Field3 + "',"
                    + " Orig_Sku = '" + Field4 + "',"
                    + " Description2 = '" + Field5 + "',"
                    + " Attribute2 = '" + Field6 + "',"
                    + " Size2 = '" + Field7 + "',"
                    + " Orig_Retail = '" + Field8 + "',"
                    + " New_Used = '" + Field9 + "',"
                    + " Reason = '" + Field10 + "',"
                    + " Desc_Damage = '" + Field11 + "',"                    
                    + " Cust_Satisf = 'true',"
                    + " Ln_Date = '" + GtDates.ldate + "',"
                    + " Second_Cost = '" + prodCost + "',"
                    + " Ord_C$ = '" + ord_c + "',"
                    + " Form_Name = '" + StFrmNm + "',"
                    + " Second_Sku_Vendor = '" + vendor + "',"
                    + " Second_Sku_VPNum = '" + VPnum + "',"
                    + " First_DCS = null,"
                    + " Second_DCS = '" + DCS + "'"
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
    }

    // this initially gathers the data from the temporary table for an existing form all 10 lines but one line at a time 
    // but inserts all lines as the same time from a user viewpoint
    // if values are null the lines are left blank in the form
    public static void gtDB() throws ClassNotFoundException, SQLException {
        try (Statement s1 = DBConnect.connection.createStatement()) {
            String selTable1 = "SELECT COUNT(*) AS total FROM " + StFrmNm;
            s1.execute(selTable1);
            try (ResultSet rs1 = s1.getResultSet()) {
                rs1.next();
                cnt = rs1.getInt("total");
            }
            
            for (int w = 1; w < cnt + 1; w++) {
                cntRec += 1;
                String selTable3 = "SELECT Sku AS rslt, Qty As rslt2, Description1 As rslt3, Orig_Sku As rslt4, Description2 As rslt5, Attribute2 As rslt6, "
                        + "Size2 As rslt7, Orig_Retail As rslt8, New_Used As rslt9, Reason As rslt10, Desc_Damage As rslt11, "
                        + "Ln_Date As rslt12, Second_Cost As rslt13, Form_Name As rslt14, Second_Sku_Vendor As rslt15, Second_Sku_VPNum As rslt16, First_DCS As rslt17, Second_DCS As rslt18 "
                        + "FROM " + StFrmNm + " WHERE Line = " + w;
                s1.execute(selTable3);
                String sku;
                String qty;
                String descr1;
                String orgSku;
                String descr2;
                String attr2;
                String size2;
                String orgrt;
                String nwUsd;                
                String ddamg;
                String reas;
                String lndate;
                String cost;
                String frmnme;
                String vendr;
                String vendrpnum;
                String fdcs;
                String sdcs;
                try (ResultSet rs3 = s1.getResultSet()) {
                    rs3.next();
                    sku = rs3.getString("rslt");
                    qty = rs3.getString("rslt2");
                    descr1 = rs3.getString("rslt3");
                    orgSku = rs3.getString("rslt4");
                    descr2 = rs3.getString("rslt5");
                    attr2 = rs3.getString("rslt6");
                    size2 = rs3.getString("rslt7");
                    orgrt = rs3.getString("rslt8");
                    nwUsd = rs3.getString("rslt9");
                    reas = rs3.getString("rslt10");
                    ddamg = rs3.getString("rslt11");                    
                    lndate = rs3.getString("rslt12");
                    cost = rs3.getString("rslt13");
                    frmnme = rs3.getString("rslt14");
                    vendr = rs3.getString("rslt15");
                    vendrpnum = rs3.getString("rslt16");
                    fdcs = rs3.getString("rslt17");
                    sdcs = rs3.getString("rslt18");
                }                
                recLine[w] = sku + ";" + qty + ";" + descr1 + ";" + orgSku + ";" + descr2 + ";" + attr2 + ";" + size2 + ";" + orgrt + ";" + nwUsd + ";" +
                        reas + ";" + ddamg + ";" + lndate + ";" + cost+ ";"+ frmnme + ";"+ vendr + ";"+ vendrpnum + ";" + fdcs + ";" + sdcs;
                //System.out.println(sku + ";" + qty + "; "+ descr1 + ";" + reas + ";" + ddamg + ";" + orgSku + ";" + orgrt + ";" + lndate);
            }
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
                + "(Line, Sku, Qty, Description1, Orig_Sku, Description2, Attribute2, Size2, Orig_Retail, New_Used, "
                + "Reason, Desc_Damage, Cust_Satisf, Form_Name, Ln_Date, New_Sku, Second_Cost, Second_Sku_Vendor, Second_Sku_VPNum, "
                + "Ord_C$, First_DCS, Second_DCS) SELECT * FROM "  + StFrmNm + " WHERE Sku IS NOT NULL";
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
    public static void Ln1() {
        NEBAS.L1_Timestamp.setText(GtDates.ldate);

        NEBAS.L1_First_Sku.setEnabled(false);
        NEBAS.L1_Qty_Out.setEnabled(false);
        NEBAS.L1_First_Desc.setEnabled(false);
        NEBAS.L1_Orig_Sku.setEnabled(false);
        NEBAS.L1_Orig_Desc.setEnabled(false); 
        NEBAS.L1_Orig_Attr.setEnabled(false);
        NEBAS.L1_Orig_Size.setEnabled(false);
        NEBAS.L1_Orig_Retail.setEnabled(false);
        NEBAS.L1_New_Used.setEnabled(false);
        NEBAS.L1_Reason_DropDown.setEnabled(false);
        NEBAS.L1_Desc_Damage.setEnabled(false);
        
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Ln2() {
        NEBAS.L2_Timestamp.setText(GtDates.ldate);

        NEBAS.L2_First_Sku.setEnabled(false);
        NEBAS.L2_Qty_Out.setEnabled(false);
        NEBAS.L2_First_Desc.setEnabled(false);
        NEBAS.L2_Orig_Sku.setEnabled(false);
        NEBAS.L2_Orig_Desc.setEnabled(false); 
        NEBAS.L2_Orig_Attr.setEnabled(false);
        NEBAS.L2_Orig_Size.setEnabled(false);
        NEBAS.L2_Orig_Retail.setEnabled(false);
        NEBAS.L2_New_Used.setEnabled(false);
        NEBAS.L2_Reason_DropDown.setEnabled(false);
        NEBAS.L2_Desc_Damage.setEnabled(false);
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Ln3() {
        NEBAS.L3_Timestamp.setText(GtDates.ldate);

        NEBAS.L3_First_Sku.setEnabled(false);
        NEBAS.L3_Qty_Out.setEnabled(false);
        NEBAS.L3_First_Desc.setEnabled(false);
        NEBAS.L3_Orig_Sku.setEnabled(false);
        NEBAS.L3_Orig_Desc.setEnabled(false); 
        NEBAS.L3_Orig_Attr.setEnabled(false);
        NEBAS.L3_Orig_Size.setEnabled(false);
        NEBAS.L3_Orig_Retail.setEnabled(false);
        NEBAS.L3_New_Used.setEnabled(false);
        NEBAS.L3_Reason_DropDown.setEnabled(false);
        NEBAS.L3_Desc_Damage.setEnabled(false);
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static void Ln4() {
        NEBAS.L4_Timestamp.setText(GtDates.ldate);

        NEBAS.L4_First_Sku.setEnabled(false);
        NEBAS.L4_Qty_Out.setEnabled(false);
        NEBAS.L4_First_Desc.setEnabled(false);
        NEBAS.L4_Orig_Sku.setEnabled(false);
        NEBAS.L4_Orig_Desc.setEnabled(false); 
        NEBAS.L4_Orig_Attr.setEnabled(false);
        NEBAS.L4_Orig_Size.setEnabled(false);
        NEBAS.L4_Orig_Retail.setEnabled(false);
        NEBAS.L4_New_Used.setEnabled(false);
        NEBAS.L4_Reason_DropDown.setEnabled(false);
        NEBAS.L4_Desc_Damage.setEnabled(false);
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
      public static void Ln5() {
        NEBAS.L5_Timestamp.setText(GtDates.ldate);

        NEBAS.L5_First_Sku.setEnabled(false);
        NEBAS.L5_Qty_Out.setEnabled(false);
        NEBAS.L5_First_Desc.setEnabled(false);
        NEBAS.L5_Orig_Sku.setEnabled(false);
        NEBAS.L5_Orig_Desc.setEnabled(false); 
        NEBAS.L5_Orig_Attr.setEnabled(false);
        NEBAS.L5_Orig_Size.setEnabled(false);
        NEBAS.L5_Orig_Retail.setEnabled(false);
        NEBAS.L5_New_Used.setEnabled(false);
        NEBAS.L5_Reason_DropDown.setEnabled(false);
        NEBAS.L5_Desc_Damage.setEnabled(false);
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
      public static void Ln6() {
        NEBAS.L6_Timestamp.setText(GtDates.ldate);

        NEBAS.L6_First_Sku.setEnabled(false);
        NEBAS.L6_Qty_Out.setEnabled(false);
        NEBAS.L6_First_Desc.setEnabled(false);
        NEBAS.L6_Orig_Sku.setEnabled(false);
        NEBAS.L6_Orig_Desc.setEnabled(false); 
        NEBAS.L6_Orig_Attr.setEnabled(false);
        NEBAS.L6_Orig_Size.setEnabled(false);
        NEBAS.L6_Orig_Retail.setEnabled(false);
        NEBAS.L6_New_Used.setEnabled(false);
        NEBAS.L6_Reason_DropDown.setEnabled(false);
        NEBAS.L6_Desc_Damage.setEnabled(false);
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
     public static void Ln7() {
        NEBAS.L7_Timestamp.setText(GtDates.ldate);

        NEBAS.L7_First_Sku.setEnabled(false);
        NEBAS.L7_Qty_Out.setEnabled(false);
        NEBAS.L7_First_Desc.setEnabled(false);
        NEBAS.L7_Orig_Sku.setEnabled(false);
        NEBAS.L7_Orig_Desc.setEnabled(false); 
        NEBAS.L7_Orig_Attr.setEnabled(false);
        NEBAS.L7_Orig_Size.setEnabled(false);
        NEBAS.L7_Orig_Retail.setEnabled(false);
        NEBAS.L7_New_Used.setEnabled(false);
        NEBAS.L7_Reason_DropDown.setEnabled(false);
        NEBAS.L7_Desc_Damage.setEnabled(false);
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
      public static void Ln8() {
        NEBAS.L8_Timestamp.setText(GtDates.ldate);

        NEBAS.L8_First_Sku.setEnabled(false);
        NEBAS.L8_Qty_Out.setEnabled(false);
        NEBAS.L8_First_Desc.setEnabled(false);
        NEBAS.L8_Orig_Sku.setEnabled(false);
        NEBAS.L8_Orig_Desc.setEnabled(false); 
        NEBAS.L8_Orig_Attr.setEnabled(false);
        NEBAS.L8_Orig_Size.setEnabled(false);
        NEBAS.L8_Orig_Retail.setEnabled(false);
        NEBAS.L8_New_Used.setEnabled(false);
        NEBAS.L8_Reason_DropDown.setEnabled(false);
        NEBAS.L8_Desc_Damage.setEnabled(false);
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public static void Ln9() {
        NEBAS.L9_Timestamp.setText(GtDates.ldate);

        NEBAS.L9_First_Sku.setEnabled(false);
        NEBAS.L9_Qty_Out.setEnabled(false);
        NEBAS.L9_First_Desc.setEnabled(false);
        NEBAS.L9_Orig_Sku.setEnabled(false);
        NEBAS.L9_Orig_Desc.setEnabled(false); 
        NEBAS.L9_Orig_Attr.setEnabled(false);
        NEBAS.L9_Orig_Size.setEnabled(false);
        NEBAS.L9_Orig_Retail.setEnabled(false);
        NEBAS.L9_New_Used.setEnabled(false);
        NEBAS.L9_Reason_DropDown.setEnabled(false);
        NEBAS.L9_Desc_Damage.setEnabled(false);
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
      public static void Ln10() {
        NEBAS.L10_Timestamp.setText(GtDates.ldate);

        NEBAS.L10_First_Sku.setEnabled(false);
        NEBAS.L10_Qty_Out.setEnabled(false);
        NEBAS.L10_First_Desc.setEnabled(false);
        NEBAS.L10_Orig_Sku.setEnabled(false);
        NEBAS.L10_Orig_Desc.setEnabled(false); 
        NEBAS.L10_Orig_Attr.setEnabled(false);
        NEBAS.L10_Orig_Size.setEnabled(false);
        NEBAS.L10_Orig_Retail.setEnabled(false);
        NEBAS.L10_New_Used.setEnabled(false);
        NEBAS.L10_Reason_DropDown.setEnabled(false);
        NEBAS.L10_Desc_Damage.setEnabled(false);
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
      // this clears all the data in the corresponding line from the temporary table in the database
       public static void ClearLine1() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Second_Sku_Vendor = null, Second_Sku_VPNum = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + NEBAS.L1.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database   
       public static void ClearLine2() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Second_Sku_Vendor = null, Second_Sku_VPNum = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + NEBAS.L2.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database
       public static void ClearLine3() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Second_Sku_Vendor = null, Second_Sku_VPNum = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + NEBAS.L3.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database 
       public static void ClearLine4() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Second_Sku_Vendor = null, Second_Sku_VPNum = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + NEBAS.L4.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database
       public static void ClearLine5() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Second_Sku_Vendor = null, Second_Sku_VPNum = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + NEBAS.L5.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database
       public static void ClearLine6() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Second_Sku_Vendor = null, Second_Sku_VPNum = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + NEBAS.L6.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database
       public static void ClearLine7() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Second_Sku_Vendor = null, Second_Sku_VPNum = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + NEBAS.L7.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database
       public static void ClearLine8() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Second_Sku_Vendor = null, Second_Sku_VPNum = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + NEBAS.L8.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database   
       public static void ClearLine9() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Second_Sku_Vendor = null, Second_Sku_VPNum = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + NEBAS.L9.getText() + "'";
               s.execute(clearLine);
           }
       }   
       // this clears all the data in the corresponding line from the temporary table in the database
       public static void ClearLine10() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Orig_Sku = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Orig_Retail = null, "
                       + "New_Used = null, Reason = null, Desc_Damage = null, Cust_Satisf = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Second_Sku_Vendor = null, Second_Sku_VPNum = null, First_DCS = null, Second_DCS = null "
                       + "WHERE Line = '" + NEBAS.L10.getText() + "'";
               s.execute(clearLine);
           }
       }   
            
    //Form Pending
    public static void FormPending() throws ClassNotFoundException, SQLException {
        try (Statement s = DBConnect.connection.createStatement()) {
            String upTable = "UPDATE existing_forms "
                    + "SET Status = 'Pending', Last_Updated = '" + GtDates.tdate + "'"
                    + "WHERE Form_Name = '" + StFrmNm + "'";
            s.execute(upTable);
        }
    }
    
    //Form Approved
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
                    + "(Line, Sku, Qty, Description1, Orig_Sku, Description2, Attribute2, Size2, Orig_Retail, New_Used, "
                    + "Reason, Desc_Damage, Cust_Satisf, Form_Name, Ln_Date, New_Sku, Second_Cost, Second_Sku_Vendor, Second_Sku_VPNum, "
                    + "Ord_C$, First_DCS, Second_DCS) SELECT * FROM "  + InvAdj_Admin.frmNm + " WHERE Sku IS NOT NULL";
                
                // This inserts store and form name because I could not figure out how to include it in the insert statement above
                String setStrFmNm = "UPDATE completed_lines "
                  //  + "SET Form_Name = '" + InvAdj_Admin.frmNm + "'" + ","
                    + "SET Store = '" + InvAdj_Admin.frmNm.split("_")[1] + "'"
                    + " WHERE Sku IS NOT NULL AND Form_Name = '"+InvAdj_Admin.frmNm+"'";
                                
                s1.execute(writeLine); 
                s1.execute(setStrFmNm);
                                
    }
    
    /* Form Export (not currently being used)
    public static void FormExport() throws ClassNotFoundException, SQLException, IOException {
        String[] args = null;
        GtDates.main(args);
        try (Statement s = DBConnect.connection.createStatement()) {
            String rmForm = "DELETE FROM existing_forms "
                    + " WHERE Form_Name = '" + InvAdj_Admin.frmNm + "'";
            
            String upTable = "INSERT INTO completed_forms"
                    + "(Store, Form_Name, Date_Created, Last_Updated, Status) "
                    + "VALUES("
                    + "'" + InvAdj_Admin.frmNm.split("_")[1] + "'" + ","
                    + "'" + InvAdj_Admin.frmNm + "'" + ","
                    + "'" + GtDates.tdate + "'" + ","
                    + "'" + GtDates.tdate + "'" + ","
                    + "'" + "Completed" + "'"
                    + ")";
            
            s.execute(rmForm);
            s.execute(upTable);
        }

    } */

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
    * */
    
    //Commit Line
    public static void CmtLn() {
        if (nwFrm.equals("yes")) {
            StFrmNm = "nebas_" + GtStore.store.toLowerCase() + "_" + GtDates.fdate;
            nwFrm = "no";
            try {
                RcForm();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(NEBASdao.class.getName()).log(Level.SEVERE, null, ex);
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
}

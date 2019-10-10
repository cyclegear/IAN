/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form_port_app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SSwisher & WStevens
 */

// This is the data access object class for IAS2
// All data from IAS2 is sent to or received from the database through this class
public class IASdao {

    public static int z = 0;
    public static int cnt = 0;
    public static int cntRec = 0;
  //  public static String dbPath = "db/InvAdj.mdb";
    public static String firstSkuInput = null;
    public static String secondSkuInput = null;
    public static String firstRproSku = null;
    public static String secondRproSku = null;
    public static String firstSkuDesc = null;
    public static String secondSkuDesc = null;
    public static String firstSkuPr = null;
    public static String secondSkuPr = null;
    public static String firstSkuAttr = null;
    public static String secondSkuAttr = null;
    public static String firstSkuSize = null;
    public static String secondSkuSize = null;
    public static String firstSkuCost = "0.00";
    public static String secondSkuCost = "0.00";
    public static String firstSkuVendor = null;
    public static String firstSkuVPNum = null;
    public static String secondSkuVendor = null;
    public static String secondSkuVPNum = null;
    public static String firstDCS = null;
    public static String secondDCS = null;
    public static String dteCreate = null;
    public static String[] recLine = new String[60];
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
    public static String rline = null;
    public static String nwFrm = null;
    public static String StFrmNm = null;
    public static String StFrmSt = null;
    public static int lnFlg = 0;
    public static String strNm = null;
    public static String StrCode = null;
    public static String dateCreated = null;

    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        skuCheck();
    }
        // checks the sku input in the database from IAS2 and returns all values if sku == good
        public static void skuCheck() throws SQLException, ClassNotFoundException {
        try (Statement s = DBConnect.connection.createStatement()) {
            String selTable1 = "SELECT Item As rslt, Desc1 As rslt2,  Attr As rslt3, Size As rslt4, "
                    + "P$_RT_TAG As rslt5, C$ As rslt6, VC As rslt7, Desc2 As rslt8, DCS As rslt9 FROM item_master WHERE Item = '" + firstSkuInput + "'";
          //  try {
            s.execute(selTable1);
            try (ResultSet rs1 = s.getResultSet()) {
                if (rs1.next() && Integer.parseInt(rs1.getString("rslt")) > 1 && Integer.parseInt(rs1.getString("rslt")) < 999999) {
                    firstRproSku = rs1.getString("rslt");
                    firstSkuDesc = rs1.getString("rslt2");
                    firstSkuAttr = rs1.getString("rslt3");
                    firstSkuSize = rs1.getString("rslt4");
                    firstSkuPr = rs1.getString("rslt5");
                    firstSkuCost = rs1.getString("rslt6");
                    firstSkuVendor = rs1.getString("rslt7");
                    firstSkuVPNum = rs1.getString("rslt8");
                    firstDCS = rs1.getString("rslt9");
                    skuRslt = "good";
                } else {
                    skuRslt = "bad";
                    firstSkuDesc = "No Desc Available";
                }
                }
          //  } finally {    
          //  s.close();
          //  }        
        }
    }

// for second sku input
public static void skuCheck2() throws SQLException, ClassNotFoundException {
        try (Statement s = DBConnect.connection.createStatement()) {
            String selTable1 = "SELECT Item As rslt, Desc1 As rslt2, Attr As rslt3, Size As rslt4, "
                    + "P$_RT_TAG As rslt5, C$ As rslt6, VC As rslt7, Desc2 As rslt8, DCS As rslt9 FROM item_master WHERE Item = '" + secondSkuInput + "'";
            s.execute(selTable1);
            try (ResultSet rs2 = s.getResultSet()) {
                if (rs2.next() && Integer.parseInt(rs2.getString("rslt")) > 1 && Integer.parseInt(rs2.getString("rslt")) < 999999) {
                    secondRproSku = rs2.getString("rslt");
                    secondSkuDesc = rs2.getString("rslt2");
                    secondSkuAttr = rs2.getString("rslt3");
                    secondSkuSize = rs2.getString("rslt4");
                    secondSkuPr = rs2.getString("rslt5");
                    secondSkuCost = rs2.getString("rslt6");
                    secondSkuVendor = rs2.getString("rslt7");
                    secondSkuVPNum = rs2.getString("rslt8");
                    secondDCS = rs2.getString("rslt9");
                    skuRslt2 = "good";
                } else {
                    skuRslt2 = "Bad";
                    secondSkuDesc = "No Desc Available";
                }
            }
        }
    }

    // Check For Existing Form for store use
    public static void ChkForm() throws ClassNotFoundException, SQLException {
        try (Statement s = DBConnect.connection.createStatement()) {
            String selTable3 = "SELECT Store As str, Form_Name As frm, Date_Created As dte, Status As st FROM existing_forms WHERE Status = 'In-Process' AND Form_Name LIKE 'ias_" + GtStore.store + "_0%' OR 'ias_" + GtStore.store + "_1%'";
         //   try {
            s.execute(selTable3);
            try (ResultSet rs3 = s.getResultSet()) {
                if (rs3.next()) {                    
                    IAS2.Date_Label.setText("Date Started: " + rs3.getString("dte"));
                    nwFrm = "no";
                    StFrmNm = rs3.getString("frm");
                    StFrmSt = rs3.getString("st");
                    gtDB();
                  //  rs3.close();
                } else {
                    nwFrm = "yes";
                   // IAS2.clearIfNew();
                   // rs3.close();
                }
                }  
          //  } finally {
          //      s.close();
          //  }
        }
    }

    // Check For Existing Form for DL and IC use
    public static void ChkForm2() throws ClassNotFoundException, SQLException {
        try (Statement s = DBConnect.connection.createStatement()) {
            String selTable3 = "SELECT Store As str, Form_Name As frm, Date_Created As dte, Status As st FROM existing_forms WHERE Form_Name = '" + InvAdj_Admin.frmNm + "'";
          //  try {
            s.execute(selTable3);
            try (ResultSet rs3 = s.getResultSet()) {
                if (rs3.next()) {
                    //rs3.next();
                    IAS2.Date_Label.setText("Date Started: " + rs3.getString("dte"));
                    nwFrm = "no";
                    StFrmNm = rs3.getString("frm");
                    StFrmSt = rs3.getString("st");
                    gtDB();
                   // rs3.close();
                } else {
                    nwFrm = "yes";
                   // rs3.close();
                }
            } 
          //  } finally {
          //      s.close();
          //  }
        }
    }   
    
    
    // inserts data into existing_forms header table and creates temp IAS form with data
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
                    // Last_Updated,
                    + "(Store, Form_Name, Date_Created, Status) "
                    + "VALUES("
                    + "'" + GtStore.store.toLowerCase() + "'" + ","
                    + "'" + StFrmNm + "'" + ","
                    + "'" + GtDates.tdate + "'" + ","
                   // + "'" + GtDates.tdate + "'" + ","
                    + "'" + "In-Process" + "'"
                    + ")";
                    s.execute(insertTable);                              
                            
            //String crtTable = "SELECT * INTO " + StFrmNm + " FROM ias WHERE 1=2";
            String crtTable = "CREATE TABLE " + StFrmNm + " SELECT * FROM ias";
            s.execute(crtTable);
            }
        }
        insLns();
     //   lastUpdate();
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
    
    public static void lastUpdate() throws SQLException {
        Statement s = DBConnect.connection.createStatement();
        String updateHeader = "UPDATE existing_forms "
                    + "Set Form_Name = '"+ StFrmNm +"', Last_Updated = '" + GtDates.tdate + "' WHERE Form_Name = '"+ StFrmNm +"'";
                    s.execute(updateHeader);        
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
    
    /* ** this section was an attempt to insert all information into temp table for reports but retain for each 
       ** Form creation. It only does it for one form at a time which was not my attention
    
    public static void createUnprocesseed() throws ClassNotFoundException, SQLException {
        Statement s = DBConnect.connection.createStatement();
        String crtUnp = "CREATE TABLE IF NOT EXISTS unprocessed SELECT * FROM ias";
        s.execute(crtUnp);
        insUnp();
    }
     
    public static void insUnp() throws ClassNotFoundException, SQLException {
     try (Statement s = DBConnect.connection.createStatement()) {
            for (int w = 1; w < 11; w++) {
                String insUnpLns = "INSERT INTO unprocessed "
                        + "(Line) "
                        + "VALUES("
                        + "'" + w + "'"
                        + ")";
              s.execute(insUnpLns);
            }
        }
    }
            
       // meant to store data for all unprocessed forms
    public static void insUnprocessed() throws ClassNotFoundException, SQLException {
        Statement s = DBConnect.connection.createStatement();
        String InsUnp = "UPDATE unprocessed"
                + " Set Sku = '" + Field1 + "',"
                + " Qty = '" + Field2 + "',"
                + " Description1 = '" + Field3 + "',"
                + " Attribute = '" + Field4 + "',"
                + " Size = '" + Field5 + "',"
                + " Orig_Retail = '" + Field6 + "',"
                + " Reason = '" + Field7 + "',"
                + " Desc_Damage = '" + Field8 + "',"
                + " New_SKU = '" + Field9 + "',"
                + " Description2 = '" + Field10 + "',"
                + " Attribute2 = '" + Field11 + "',"
                + " Size2 = '" + Field12 + "',"
                + " Qty_In = '" + Field13 + "',"
                + " Cust_Satisf = 'true',"
                + " Ln_Date = '" + GtDates.ldate + "',"
                + " Cost = '" + prodCost + "'"
                + " WHERE Line = " + rline;
        s.execute(InsUnp);
        } */
    
    
   // if any user type unchecks the checkbox to make changes to a line with previous existing data
    // this will update any chagnes in the temporary table
    public static void upDB() throws ClassNotFoundException, SQLException {
        try (Statement s = DBConnect.connection.createStatement()) {
            try {
            String upTable = "UPDATE " + StFrmNm
                    + " Set Sku = '" + Field1 + "',"
                    + " Qty = '" + Field2 + "',"
                    + " Description1 = '" + Field3 + "',"
                    + " Attribute = '" + Field4 + "',"
                    + " Size = '" + Field5 + "',"
                    + " Orig_Retail = '" + Field6 + "',"
                    + " Reason = '" + Field7 + "',"
                    + " Desc_Damage = '" + Field8 + "',"
                    + " New_SKU = '" + Field9 + "',"
                    + " Description2 = '" + Field10 + "',"
                    + " Attribute2 = '" + Field11 + "',"
                    + " Size2 = '" + Field12 + "',"
                    + " Qty_In = '" + Field13 + "',"
                    + " Cust_Satisf = 'true',"
                    + " Ln_Date = '" + GtDates.ldate + "',"
                    + " First_Cost = '" + firstSkuCost + "',"
                    + " Second_Cost = '" + secondSkuCost + "',"
                    + " Form_Name = '" + StFrmNm + "',"
                    + " First_Sku_Vendor = '" + firstSkuVendor + "',"
                    + " First_Sku_VPNum = '" + firstSkuVPNum + "',"
                    + " First_DCS = '" + firstDCS + "',"
                    + " Second_Sku_Vendor = '" + secondSkuVendor + "',"
                    + " Second_Sku_VPNum = '" + secondSkuVPNum + "',"
                    + " Second_DCS = '" + secondDCS + "'"
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
            } finally {
                s.close();
            }
        }
    }
    
    // Gets info from database and puts it into the appropriate reclines in sequential order
    public static void gtDB() throws ClassNotFoundException, SQLException {
        try (Statement s1 = DBConnect.connection.createStatement()) {
            String selTable1 = "SELECT COUNT(*) AS total FROM " + StFrmNm;
            s1.execute(selTable1);
            try (ResultSet rs1 = s1.getResultSet()) {
                rs1.next();
                cnt = rs1.getInt("total");
            }          
            try {
            for (int w = 1; w < cnt + 1; w++) {
                cntRec += 1;
                String selTable3 = "SELECT SKU As rslt, Qty As rslt2, Description1 As rslt3, Attribute As rslt4, "
                        + "Size As rslt5, Orig_Retail As rslt6, Reason As rslt7, Desc_Damage As rslt8, "
                        + "New_SKU As rslt9, Description2 As rslt10, Attribute2 As rslt11, Size2 As rslt12, Qty_In As rslt13, Ln_Date As rslt14, "
                        + "First_Cost As rslt15, Second_Cost As rslt16, Form_Name As rslt17, First_Sku_Vendor As rslt18, First_Sku_VPNum As rslt19, "
                        + "First_DCS As rslt20, Second_Sku_Vendor As rslt21, Second_Sku_VPNum As rslt22, Second_DCS As rslt23 "
                        + "FROM " + StFrmNm + " WHERE Line = " + w;
                s1.execute(selTable3);
                ResultSet rs3 = s1.getResultSet();
                rs3.next();
                String sku = rs3.getString("rslt");
                String qty = rs3.getString("rslt2");
                String descr1 = rs3.getString("rslt3");
                String attr = rs3.getString("rslt4");
                String size = rs3.getString("rslt5");
                String orgrt = rs3.getString("rslt6");
                String reas = rs3.getString("rslt7");
                String ddamg = rs3.getString("rslt8");
                String nwSku = rs3.getString("rslt9");
                String descr2 = rs3.getString("rslt10");
                String attr2 = rs3.getString("rslt11");
                String size2 = rs3.getString("rslt12");
                String qty2 = rs3.getString("rslt13");
                String lndate = rs3.getString("rslt14");
                String firstcost = rs3.getString("rslt15");
                String secondcost = rs3.getString("rslt16");
                String frmnme = rs3.getString("rslt17");
                String firstskuvendor = rs3.getString("rslt18");
                String firstskuvpnum = rs3.getString("rslt19");
                String firstdcs = rs3.getString("rslt20");
                String secondskuvendor = rs3.getString("rslt21");
                String secondskuvpnum = rs3.getString("rslt22");
                String seconddcs = rs3.getString("rslt23");
                recLine[w] = sku + ";" + qty + ";" + descr1 + ";" + attr + ";" + size + ";" + orgrt + ";" + reas + ";" + ddamg + ";" + nwSku + ";" + descr2 + ";" + attr2 + ";"
                        + size2 + ";" + qty2 + ";" + lndate + ";" + firstcost + ";" + secondcost + ";" + frmnme + ";" + firstskuvendor + ";" + firstskuvpnum  + ";" + firstdcs + ";" +
                        secondskuvendor + ";" + secondskuvpnum + ";" + seconddcs;
                //System.out.println(qty + "," + sku + "," + vend + "," + descr1 + "," + reas + "," + ddamg + "," + orgrt + "," + nwSku + "," + descr2 + "," + qty2 + "," + lndate);
            }
            } finally {
            s1.close();
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
                    + "(Line, Sku, Qty, Description1, Attribute, Size, Orig_Retail, Reason, "
                    + "Desc_Damage, New_Sku, Description2, Attribute2, Size2, Qty_In, Cust_Satisf, First_Cost, Second_Cost, Form_Name, Ln_Date, "
                    + "Orig_Sku, First_Sku_Vendor, First_Sku_VPNum, First_DCS, Second_Sku_Vendor, Second_Sku_VPNum, Second_DCS) "
                    + "SELECT * FROM " + InvAdj_Admin.frmNm + " WHERE Sku IS NOT NULL";
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
        IAS2.L1_Timestamp.setText(GtDates.ldate);

        IAS2.L1_First_Sku.setEnabled(false);
        IAS2.L1_Qty_Out.setEnabled(false);
        IAS2.L1_First_Desc.setEnabled(false);
        IAS2.L1_First_Attr.setEnabled(false);
        IAS2.L1_First_Size.setEnabled(false);
        IAS2.L1_Orig_Retail.setEnabled(false);
        IAS2.L1_Reason_DropDown.setEnabled(false);
        IAS2.L1_Desc_Damage.setEnabled(false);
        IAS2.L1_New_Sku.setEnabled(false);
        IAS2.L1_New_Desc.setEnabled(false);
        IAS2.L1_New_Attr.setEnabled(false);
        IAS2.L1_New_Size.setEnabled(false);
        IAS2.L1_Qty_In.setEnabled(false);
        
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Ln2() {
        IAS2.L2_Timestamp.setText(GtDates.ldate);

        IAS2.L2_First_Sku.setEnabled(false);
        IAS2.L2_Qty_Out.setEnabled(false);
        IAS2.L2_First_Desc.setEnabled(false);
        IAS2.L2_First_Attr.setEnabled(false);
        IAS2.L2_First_Size.setEnabled(false);
        IAS2.L2_Orig_Retail.setEnabled(false);
        IAS2.L2_Reason_DropDown.setEnabled(false);
        IAS2.L2_Desc_Damage.setEnabled(false);
        IAS2.L2_New_Sku.setEnabled(false);
        IAS2.L2_New_Desc.setEnabled(false);
        IAS2.L2_New_Attr.setEnabled(false);
        IAS2.L2_New_Size.setEnabled(false);
        IAS2.L2_Qty_In.setEnabled(false);
        
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static void Ln3() {
        IAS2.L3_Timestamp.setText(GtDates.ldate);

        IAS2.L3_First_Sku.setEnabled(false);
        IAS2.L3_Qty_Out.setEnabled(false);
        IAS2.L3_First_Desc.setEnabled(false);
        IAS2.L3_First_Attr.setEnabled(false);
        IAS2.L3_First_Size.setEnabled(false);
        IAS2.L3_Orig_Retail.setEnabled(false);
        IAS2.L3_Reason_DropDown.setEnabled(false);
        IAS2.L3_Desc_Damage.setEnabled(false);
        IAS2.L3_New_Sku.setEnabled(false);
        IAS2.L3_New_Desc.setEnabled(false);
        IAS2.L3_New_Attr.setEnabled(false);
        IAS2.L3_New_Size.setEnabled(false);
        IAS2.L3_Qty_In.setEnabled(false);
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
      public static void Ln4() {
        IAS2.L4_Timestamp.setText(GtDates.ldate);

        IAS2.L4_First_Sku.setEnabled(false);
        IAS2.L4_Qty_Out.setEnabled(false);
        IAS2.L4_First_Desc.setEnabled(false);
        IAS2.L4_First_Attr.setEnabled(false);
        IAS2.L4_First_Size.setEnabled(false);
        IAS2.L4_Orig_Retail.setEnabled(false);
        IAS2.L4_Reason_DropDown.setEnabled(false);
        IAS2.L4_Desc_Damage.setEnabled(false);
        IAS2.L4_New_Sku.setEnabled(false);
        IAS2.L4_New_Desc.setEnabled(false);       
        IAS2.L4_New_Attr.setEnabled(false);
        IAS2.L4_New_Size.setEnabled(false);
        IAS2.L4_Qty_In.setEnabled(false);
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
 public static void Ln5() {
        IAS2.L5_Timestamp.setText(GtDates.ldate);

        IAS2.L5_First_Sku.setEnabled(false);
        IAS2.L5_Qty_Out.setEnabled(false);
        IAS2.L5_First_Desc.setEnabled(false);
        IAS2.L5_First_Attr.setEnabled(false);
        IAS2.L5_First_Size.setEnabled(false);
        IAS2.L5_Orig_Retail.setEnabled(false);
        IAS2.L5_Reason_DropDown.setEnabled(false);
        IAS2.L5_Desc_Damage.setEnabled(false);
        IAS2.L5_New_Sku.setEnabled(false);
        IAS2.L5_New_Desc.setEnabled(false);
        IAS2.L5_New_Attr.setEnabled(false);
        IAS2.L5_New_Size.setEnabled(false);
        IAS2.L5_Qty_In.setEnabled(false);
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
       public static void Ln6() {
        IAS2.L6_Timestamp.setText(GtDates.ldate);

        IAS2.L6_First_Sku.setEnabled(false);
        IAS2.L6_Qty_Out.setEnabled(false);
        IAS2.L6_First_Desc.setEnabled(false);
        IAS2.L6_First_Attr.setEnabled(false);
        IAS2.L6_First_Size.setEnabled(false);
        IAS2.L6_Orig_Retail.setEnabled(false);
        IAS2.L6_Reason_DropDown.setEnabled(false);
        IAS2.L6_Desc_Damage.setEnabled(false);
        IAS2.L6_New_Sku.setEnabled(false);
        IAS2.L6_New_Desc.setEnabled(false);
        IAS2.L6_New_Attr.setEnabled(false);
        IAS2.L6_New_Size.setEnabled(false);
        IAS2.L6_Qty_In.setEnabled(false);
        
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
       public static void Ln7() {
        IAS2.L7_Timestamp.setText(GtDates.ldate);
        
        IAS2.L7_First_Sku.setEnabled(false);
        IAS2.L7_Qty_Out.setEnabled(false);
        IAS2.L7_First_Desc.setEnabled(false);
        IAS2.L7_First_Attr.setEnabled(false);
        IAS2.L7_First_Size.setEnabled(false);
        IAS2.L7_Orig_Retail.setEnabled(false);
        IAS2.L7_Reason_DropDown.setEnabled(false);
        IAS2.L7_Desc_Damage.setEnabled(false);
        IAS2.L7_New_Sku.setEnabled(false);
        IAS2.L7_New_Desc.setEnabled(false);
        IAS2.L7_New_Attr.setEnabled(false);
        IAS2.L7_New_Size.setEnabled(false);
        IAS2.L7_Qty_In.setEnabled(false);
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
       public static void Ln8() {
        IAS2.L8_Timestamp.setText(GtDates.ldate);

        IAS2.L8_First_Sku.setEnabled(false);
        IAS2.L8_Qty_Out.setEnabled(false);
        IAS2.L8_First_Desc.setEnabled(false);
        IAS2.L8_First_Attr.setEnabled(false);
        IAS2.L8_First_Size.setEnabled(false);
        IAS2.L8_Orig_Retail.setEnabled(false);
        IAS2.L8_Reason_DropDown.setEnabled(false);
        IAS2.L8_Desc_Damage.setEnabled(false);
        IAS2.L8_New_Sku.setEnabled(false);
        IAS2.L8_New_Desc.setEnabled(false);
        IAS2.L8_New_Attr.setEnabled(false);
        IAS2.L8_New_Size.setEnabled(false);
        IAS2.L8_Qty_In.setEnabled(false);
        
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
        public static void Ln9() {
        IAS2.L9_Timestamp.setText(GtDates.ldate);

        IAS2.L9_First_Sku.setEnabled(false);
        IAS2.L9_Qty_Out.setEnabled(false);
        IAS2.L9_First_Desc.setEnabled(false);
        IAS2.L9_First_Attr.setEnabled(false);
        IAS2.L9_First_Size.setEnabled(false);
        IAS2.L9_Orig_Retail.setEnabled(false);
        IAS2.L9_Reason_DropDown.setEnabled(false);
        IAS2.L9_Desc_Damage.setEnabled(false);
        IAS2.L9_New_Sku.setEnabled(false);
        IAS2.L9_New_Desc.setEnabled(false);
        IAS2.L9_New_Attr.setEnabled(false);
        IAS2.L9_New_Size.setEnabled(false);
        IAS2.L9_Qty_In.setEnabled(false);
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
         public static void Ln10() {
        IAS2.L10_Timestamp.setText(GtDates.ldate);

        IAS2.L10_First_Sku.setEnabled(false);
        IAS2.L10_Qty_Out.setEnabled(false);
        IAS2.L10_First_Desc.setEnabled(false);
        IAS2.L10_First_Attr.setEnabled(false);
        IAS2.L10_First_Size.setEnabled(false);
        IAS2.L10_Orig_Retail.setEnabled(false);
        IAS2.L10_Reason_DropDown.setEnabled(false);
        IAS2.L10_Desc_Damage.setEnabled(false);
        IAS2.L10_New_Sku.setEnabled(false);
        IAS2.L10_New_Desc.setEnabled(false);
        IAS2.L10_New_Attr.setEnabled(false);
        IAS2.L10_New_Size.setEnabled(false);
        IAS2.L10_Qty_In.setEnabled(false);
        try {
            upDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          // allows users to clear a line in the database in IAS2
          // these functions clear the specific line data from the temp table in the database
          public static void ClearLine1() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Attribute = null, "
                       + "Size = null, Orig_Retail = null, Reason = null, Desc_Damage = null, New_SKU = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Qty_In = null, Cust_Satisf = null, First_Cost = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Orig_Sku = null, First_Sku_Vendor = null, First_Sku_VPNum = null, First_DCS = null, "
                       + "Second_Sku_Vendor = null, Second_Sku_VPNum = null, Second_DCS = null "
                       + "WHERE Line = '" + IAS2.L1.getText() + "'";
               s.execute(clearLine);
           }
       }   
          
       public static void ClearLine2() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Attribute = null, "
                       + "Size = null, Orig_Retail = null, Reason = null, Desc_Damage = null, New_SKU = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Qty_In = null, Cust_Satisf = null, First_Cost = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Orig_Sku = null, First_Sku_Vendor = null, First_Sku_VPNum = null, First_DCS = null, "
                       + "Second_Sku_Vendor = null, Second_Sku_VPNum = null, Second_DCS = null "
                       + "WHERE Line = '" + IAS2.L2.getText() + "'";
               s.execute(clearLine);
           }
       }   
       
       public static void ClearLine3() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Attribute = null, "
                       + "Size = null, Orig_Retail = null, Reason = null, Desc_Damage = null, New_SKU = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Qty_In = null, Cust_Satisf = null, First_Cost = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Orig_Sku = null, First_Sku_Vendor = null, First_Sku_VPNum = null, First_DCS = null, "
                       + "Second_Sku_Vendor = null, Second_Sku_VPNum = null, Second_DCS = null "
                       + "WHERE Line = '" + IAS2.L3.getText() + "'";
               s.execute(clearLine);
           }
       }   
       
       public static void ClearLine4() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
              String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Attribute = null, "
                       + "Size = null, Orig_Retail = null, Reason = null, Desc_Damage = null, New_SKU = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Qty_In = null, Cust_Satisf = null, First_Cost = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Orig_Sku = null, First_Sku_Vendor = null, First_Sku_VPNum = null, First_DCS = null, "
                       + "Second_Sku_Vendor = null, Second_Sku_VPNum = null, Second_DCS = null "
                       + "WHERE Line = '" + IAS2.L4.getText() + "'";
               s.execute(clearLine);
           }
       }   
       
       public static void ClearLine5() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
             String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Attribute = null, "
                       + "Size = null, Orig_Retail = null, Reason = null, Desc_Damage = null, New_SKU = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Qty_In = null, Cust_Satisf = null, First_Cost = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Orig_Sku = null, First_Sku_Vendor = null, First_Sku_VPNum = null, First_DCS = null, "
                       + "Second_Sku_Vendor = null, Second_Sku_VPNum = null, Second_DCS = null "
                       + "WHERE Line = '" + IAS2.L5.getText() + "'";
               s.execute(clearLine);
           }
       }   
       
       public static void ClearLine6() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
              String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Attribute = null, "
                       + "Size = null, Orig_Retail = null, Reason = null, Desc_Damage = null, New_SKU = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Qty_In = null, Cust_Satisf = null, First_Cost = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Orig_Sku = null, First_Sku_Vendor = null, First_Sku_VPNum = null, First_DCS = null, "
                       + "Second_Sku_Vendor = null, Second_Sku_VPNum = null, Second_DCS = null "
                       + "WHERE Line = '" + IAS2.L6.getText() + "'";
               s.execute(clearLine);
           }
       }   
       
       public static void ClearLine7() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
              String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Attribute = null, "
                       + "Size = null, Orig_Retail = null, Reason = null, Desc_Damage = null, New_SKU = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Qty_In = null, Cust_Satisf = null, First_Cost = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Orig_Sku = null, First_Sku_Vendor = null, First_Sku_VPNum = null, First_DCS = null, "
                       + "Second_Sku_Vendor = null, Second_Sku_VPNum = null, Second_DCS = null "
                       + "WHERE Line = '" + IAS2.L7.getText() + "'";
               s.execute(clearLine);
           }
       }   
       
       public static void ClearLine8() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
              String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Attribute = null, "
                       + "Size = null, Orig_Retail = null, Reason = null, Desc_Damage = null, New_SKU = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Qty_In = null, Cust_Satisf = null, First_Cost = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Orig_Sku = null, First_Sku_Vendor = null, First_Sku_VPNum = null, First_DCS = null, "
                       + "Second_Sku_Vendor = null, Second_Sku_VPNum = null, Second_DCS = null "
                       + "WHERE Line = '" + IAS2.L8.getText() + "'";
               s.execute(clearLine);
           }
       }   
          
       public static void ClearLine9() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
             String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Attribute = null, "
                       + "Size = null, Orig_Retail = null, Reason = null, Desc_Damage = null, New_SKU = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Qty_In = null, Cust_Satisf = null, First_Cost = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Orig_Sku = null, First_Sku_Vendor = null, First_Sku_VPNum = null, First_DCS = null, "
                       + "Second_Sku_Vendor = null, Second_Sku_VPNum = null, Second_DCS = null "
                       + "WHERE Line = '" + IAS2.L9.getText() + "'";
               s.execute(clearLine);
           }
       }   
       
       public static void ClearLine10() throws SQLException {
           try (Statement s = DBConnect.connection.createStatement()) {
               String clearLine = "Update " +StFrmNm+ " Set Sku = null, Qty = null, Description1 = null, Attribute = null, "
                       + "Size = null, Orig_Retail = null, Reason = null, Desc_Damage = null, New_SKU = null, "
                       + "Description2 = null, Attribute2 = null, Size2 = null, Qty_In = null, Cust_Satisf = null, First_Cost = null, Second_Cost = null, "
                       + "Form_Name = null, Ln_Date = null, Orig_Sku = null, First_Sku_Vendor = null, First_Sku_VPNum = null, First_DCS = null, "
                       + "Second_Sku_Vendor = null, Second_Sku_VPNum = null, Second_DCS = null "
                       + "WHERE Line = '" + IAS2.L10.getText() + "'";
               s.execute(clearLine);
           }
       }         
      
    //Form Pending
    public static void FormPending() throws ClassNotFoundException, SQLException {
            String upTable = "UPDATE existing_forms "
                    + "SET Status = 'Pending', Last_Updated = '" + GtDates.tdate + "'"
                    + " WHERE Form_Name = '" + StFrmNm + "'";
            PreparedStatement ps = DBConnect.connection.prepareStatement(upTable);
            ps.execute(upTable);        
    } 
    
    //Form Approved
    public static void FormApproved() throws ClassNotFoundException, SQLException {       
            String upTable = "UPDATE existing_forms "
                    + "SET Status = 'Approved', Last_Updated = '" + GtDates.tdate + "'"
                    + " WHERE Form_Name = '" +  StFrmNm + "'";
            PreparedStatement ps = DBConnect.connection.prepareStatement(upTable);
            ps.execute(upTable);
    }
    
     //Form Ready For Export deletes header data from Existing_Forms table and into completed forms table
     // at "ready for export" button in IAS2
    public static void FormReadyForExport() throws ClassNotFoundException, SQLException {
       Statement s = DBConnect.connection.createStatement();
        String getCreatedDate = "SELECT Date_Created As dateCreated FROM existing_forms WHERE Date_Created IS NOT NULL AND Form_Name = '"+ InvAdj_Admin.frmNm + "'";
       s.execute(getCreatedDate); 
         try (ResultSet rs3 = s.getResultSet()) {
            while (rs3.next()) {
                dateCreated = rs3.getString("dateCreated");
                }   
        }
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
            
            String rmForm = "DELETE FROM existing_forms "
                    +"WHERE Form_Name = '" + InvAdj_Admin.frmNm + "'";
        
            s.execute(insTable);
            s.execute(rmForm);
    }
         /*
            String insTable =  "INSERT INTO completed_forms (SELECT * FROM existing_forms WHERE Form_Name = '"+ InvAdj_Admin.frmNm +"')"; /// ***** CHANGE TO UPDATE TO AVOID INSERTING NULL VALUE ******
                  //  + "(Store, Form_Name, Last_Updated, Status) "
                 //   + "VALUES("
               //     + " Set Store = '" + InvAdj_Admin.frmNm.split("_")[1] + "'" + ","
                //    + " Form_Name = '" + InvAdj_Admin.frmNm + "'" + ","
                //  + "'" + GtDates.tdate + "'" + ","
                //  + "'" + frmDate + "'" + ","                    
                //    + " Last_Updated = '" + GtDates.tdate + "'" + ","
                 //   + " Status = '" + "Ready_for_Export" + "'";
                 //   + " WHERE Form_Name = '" + InvAdj_Admin.frmNm + "'";
                 //   + ")";
            String changeStatus = "UPDATE completed_forms Set Status = 'Ready_for_Export', Last_Updated = '"+ dateCreated +"' WHERE Form_Name = '" + InvAdj_Admin.frmNm +"'";
            s.execute(insTable);  
            s.execute(changeStatus);
            String rmForm = "DELETE FROM existing_forms "
                    +"WHERE Form_Name = '" + InvAdj_Admin.frmNm + "'";  
            s.execute(rmForm); 
                       
    }  */
    
    // Inserts lines into archive table at ready for export button in IAS2
    public static void LineArchive() throws ClassNotFoundException, SQLException {
        Statement s1 = DBConnect.connection.createStatement();  
        
         // This code was borrowed from other fucntions to test and figure out how to insert all lines into database. This does not work correctly
        /* String selTable1 = "SELECT COUNT(*) AS total FROM " + InvAdj_Admin.frmNm;
                s1.execute(selTable1);
                try (ResultSet rs1 = s1.getResultSet()) {
                    rs1.next();
                    cnt = rs1.getInt("total");
                }
                
                for (int w = 1; w < cnt + 1; w++) { 
               String getLine = "SELECT * FROM " + InvAdj_Admin.frmNm;  +  " WHERE Line = " + w;*/ 
        
                // This inputs line data into database
                String writeLine = "INSERT INTO completed_lines "
                    + "(Line, Sku, Qty, Description1, Attribute, Size, Orig_Retail, Reason, "
                    + "Desc_Damage, New_Sku, Description2, Attribute2, Size2, Qty_In, Cust_Satisf, First_Cost, Second_Cost, Form_Name, Ln_Date, "
                    + "Orig_Sku, First_Sku_Vendor, First_Sku_VPNum, First_DCS, Second_Sku_Vendor, Second_Sku_VPNum, Second_DCS) "
                    + "SELECT * FROM " + InvAdj_Admin.frmNm + " WHERE Sku IS NOT NULL";
                s1.execute(writeLine); 
                // This inserts store and form name because I could not figure out how to include it in the insert statement above
                String setStrFmNm = "UPDATE completed_lines "
                   // + "SET Form_Name = '" + InvAdj_Admin.frmNm + "'" + ","
                    + "SET Store = '" + InvAdj_Admin.frmNm.split("_")[1] + "'"
                    + " WHERE Sku IS NOT NULL AND Form_Name = '"+InvAdj_Admin.frmNm+"'"; 
                
                    // Code that is meant to be part of the for loop with counter ++ and insert mysql statement
                    // but this only inserted the same row of data 10 times in the archive table
                    /*+ " VALUES (" 
                    + "'" + InvAdj_Admin.frmNm + "'" + ","
                    + "'" + InvAdj_Admin.frmNm.split("_")[1] + "'" + ","
                    + "'" + Field1 + "',"
                    + "'" + Field2 + "',"
                    + "'" + Field3 + "',"
                    + "'0',"   
                    + "'" + Field9 + "',"
                    + "'" + Field4 + "',"
                    + "'" + Field5 + "',"
                    + "'" + Field6 + "',"
                    + "'" + Field7 + "',"
                    + "'NULL',"       
                    + "'" + Field8 + "',"
                    + "'" + Field10 + "',"
                    + "'" + Field11 + "',"
                    + "'" + Field12 + "',"
                    + "'NULL',"       
                    + "'NULL',"       
                    + "'NULL',"       
                    + "'" + Field13 + "',"
                    + "'" + skuCost + "',"
                    + "'" + GtDates.ldate + "',"
                    + "'" + w + "'"
                    + ")" */
               // s1.execute(getLine);
                
               s1.execute(setStrFmNm);
               drTable();  
    }
    
    /* Export Form (not currently in use)
    public static void FormExport() throws ClassNotFoundException, SQLException {
        String[] args = null;
        GtDates.main(args);
        Statement s = DBConnect.connection.createStatement();
                    String rmForm = "DELETE FROM existing_forms"
                    + " WHERE Form_Name = '" + InvAdj_Admin.frmNm + "'";
            
            String insTable = "INSERT INTO completed_forms"
                    + "(Store, Form_Name, Date_Created, Last_Updated, Status) "
                    + "VALUES("
                    + "'" + InvAdj_Admin.frmNm.split("_")[1] + "'" + ","
                    + "'" + InvAdj_Admin.frmNm + "'" + ","
                    + "'" + GtDates.tdate + "'" + ","
                    + "'" + GtDates.tdate + "'" + ","
                    + "'" + "Completed" + "'"
                    + ")"; 
            s.execute(rmForm);
            s.execute(insTable);
    } */
    
    // drops temp table from database
    public static void drTable() throws ClassNotFoundException, SQLException{
        String[] args = null;
        GtDates.main(args);
        try (Statement s = DBConnect.connection.createStatement()) {
            String dTable = "DROP TABLE " + InvAdj_Admin.frmNm;
            s.execute(dTable);
        }
    }
    
    //Commit Line
    public static void CmtLn() {
        if (nwFrm.equals("yes")) {
            StFrmNm = "ias_" + GtStore.store.toLowerCase() + "_" + GtDates.fdate;
            nwFrm = "no";
            try {
                RcForm();
               // createUnprocesseed();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(IASdao.class.getName()).log(Level.SEVERE, null, ex);
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
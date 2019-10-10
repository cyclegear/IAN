/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form_port_app;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Seven_User & Wstevens
 */

// NEBAS = Non_Exclusive Brand Adjustment Sheet 
// create user interface for this form with data validation 
public class NEBAS extends javax.swing.JFrame {

    private Component frame;
    public static String proc;
    public static String frmNm;
    public static FileWriter fWriter;
    public static BufferedWriter writer;
   // protected static String formType;
    public static String[] Reasons = new String[GetForms.cntRes];
    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();

    /**
     * Creates new form NEBAS
     */
    public NEBAS() throws SQLException {
        initComponents();
        GtStore.GtStore();
        String[] args = null;
        GtDates.main(args);
        if (DBConnect.TestEnviron == true) {
            Test_Label.setVisible(true);
        } else if (DBConnect.TestEnviron == false) {
            Test_Label.setVisible(false);
        }
        NEBASdao.StrConversion();
        Store_Letter_Label.setText("("+NEBASdao.StrCode+")");        
        VersionLabel.setText("Version " + DBConnect.Version);
        DL_Print_Btn.setVisible(false);
        Delete_Form_Btn.setVisible(false);
        
        System.out.println("Opened NEBAS");
         subBtn.setEnabled(false);
         // checks if the user has DL or IC authority as well as chekcing forms and changes the button accordingly
        switch (GetForms_InvAdj.usrType) {
            case "dl":              
                try {
                    NEBASdao.ChkForm2();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (NEBASdao.StFrmSt.equals("Pending")) { 
                    ApproveBtn.setText("Approve");
                    ApproveBtn.setVisible(true);
                    RdyforExportBtn.setVisible(false);
                    DL_Print_Btn.setVisible(true);
                } else {
                    ApproveBtn.setVisible(false);
                    DL_Print_Btn.setVisible(false);
                    RdyforExportBtn.setVisible(false);
                    L1_Done_ChkBox.setEnabled(false);
                    L2_Done_ChkBox.setEnabled(false);
                    L3_Done_ChkBox.setEnabled(false);
                    L4_Done_ChkBox.setEnabled(false);
                    L5_Done_ChkBox.setEnabled(false);
                    L6_Done_ChkBox.setEnabled(false);
                    L7_Done_ChkBox.setEnabled(false);
                    L8_Done_ChkBox.setEnabled(false);
                    L9_Done_ChkBox.setEnabled(false);
                    L10_Done_ChkBox.setEnabled(false);
                }
                subBtn.setEnabled(false);
                RdyforExportBtn.setVisible(false);
                break;        
            case "ic":                
                try {
                    NEBASdao.ChkForm2();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                subBtn.setEnabled(false);
                if (NEBASdao.StFrmSt.equals("In-Process")) { 
                    RdyforExportBtn.setVisible(false);
                    ApproveBtn.setVisible(false);
                    subBtn.setEnabled(true);
                    Delete_Form_Btn.setVisible(true);
                } else {
                    subBtn.setEnabled(false);
                }
                if (NEBASdao.StFrmSt.equals("Pending")) { 
                    RdyforExportBtn.setVisible(false);
                    ApproveBtn.setVisible(true);
                    subBtn.setEnabled(false);
                    Delete_Form_Btn.setVisible(true);
                    DL_Print_Btn.setVisible(true);
                } else {
                  ApproveBtn.setVisible(false);
                }
                if (NEBASdao.StFrmSt.equals("Approved")) { 
                    RdyforExportBtn.setVisible(true);
                    ApproveBtn.setVisible(false);
                    subBtn.setEnabled(false);
                    Delete_Form_Btn.setVisible(true);
                    DL_Print_Btn.setVisible(true);
                } else {
                    RdyforExportBtn.setVisible(false);
                }
                break;                
            default:
               // try {
               // NEBASdao.ChkForm();
               // } catch (ClassNotFoundException | SQLException ex) {
               //     Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
               // }
                Close_Btn.setVisible(false);              
                NEBASdao.nwFrm = "yes";
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
                if (L1_Done_ChkBox.isSelected()==true || L2_Done_ChkBox.isSelected()==true || L3_Done_ChkBox.isSelected()==true || L4_Done_ChkBox.isSelected()==true || L5_Done_ChkBox.isSelected()==true
                || L6_Done_ChkBox.isSelected()==true || L7_Done_ChkBox.isSelected()==true || L8_Done_ChkBox.isSelected()==true || L9_Done_ChkBox.isSelected()==true || L10_Done_ChkBox.isSelected()==true){
                subBtn.setEnabled(true); 
                }
                break;
        }

        Store_Num_Label.setText(GtStore.store);
        Date_Label.setText(GtDates.tdate);
        frmNm = NEBASdao.StFrmNm;
        Form_Name_Label.setText(frmNm);
        
        // Set Adjustment Reasons (combobox)
        try {
            GtRes();
        } catch (SQLException ex) {
            Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
        }

        L1_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(Reasons));
        L2_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(Reasons));
        L3_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(Reasons));
        L4_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(Reasons));
        L5_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(Reasons));
        L6_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(Reasons));
        L7_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(Reasons));
        L8_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(Reasons));
        L9_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(Reasons));
        L10_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(Reasons));
        

        // Fill Lines w/Existing Data from NEBASdao class
     if (NEBASdao.cntRec > 0 && Character.isDigit(NEBASdao.recLine[1].split(";")[0].charAt(0))) {
            L1_First_Sku.setText(NEBASdao.recLine[1].split(";")[0]);
            L1_Qty_Out.setText(NEBASdao.recLine[1].split(";")[1]);
            L1_First_Desc.setText(NEBASdao.recLine[1].split(";")[2]);
            L1_Orig_Sku.setText(NEBASdao.recLine[1].split(";")[3]);
            L1_Orig_Desc.setText(NEBASdao.recLine[1].split(";")[4]);
            L1_Orig_Attr.setText(NEBASdao.recLine[1].split(";")[5]);
            L1_Orig_Size.setText(NEBASdao.recLine[1].split(";")[6]);
            L1_Orig_Retail.setText(NEBASdao.recLine[1].split(";")[7]);
            L1_New_Used.setSelectedItem(NEBASdao.recLine[1].split(";")[8]);
            L1_Reason_DropDown.setSelectedItem(NEBASdao.recLine[1].split(";")[9]);
            L1_Desc_Damage.setText(NEBASdao.recLine[1].split(";")[10]);
            L1_Timestamp.setText(NEBASdao.recLine[1].split(";")[11]);
            L1_Done_ChkBox.setSelected(true);

            L1_First_Sku.setEnabled(false);
            L1_Qty_Out.setEnabled(false);
            L1_First_Desc.setEnabled(false);
            L1_Orig_Sku.setEnabled(false);
            L1_Orig_Desc.setEnabled(false);
            L1_Orig_Attr.setEnabled(false);
            L1_Orig_Size.setEnabled(false);
            L1_Orig_Retail.setEnabled(false);
            L1_New_Used.setEnabled(false);
            L1_Reason_DropDown.setEnabled(false);
            L1_Desc_Damage.setEnabled(false);
            L1_Timestamp.setEnabled(false); 
        }
         // Fill Lines w/Existing Data from NEBASdao class
     if (NEBASdao.cntRec > 1 && Character.isDigit(NEBASdao.recLine[2].split(";")[0].charAt(0))) {
            L2_First_Sku.setText(NEBASdao.recLine[2].split(";")[0]);
            L2_Qty_Out.setText(NEBASdao.recLine[2].split(";")[1]);
            L2_First_Desc.setText(NEBASdao.recLine[2].split(";")[2]);
            L2_Orig_Sku.setText(NEBASdao.recLine[2].split(";")[3]);
            L2_Orig_Desc.setText(NEBASdao.recLine[2].split(";")[4]);
            L2_Orig_Attr.setText(NEBASdao.recLine[2].split(";")[5]);
            L2_Orig_Size.setText(NEBASdao.recLine[2].split(";")[6]);
            L2_Orig_Retail.setText(NEBASdao.recLine[2].split(";")[7]);
            L2_New_Used.setSelectedItem(NEBASdao.recLine[2].split(";")[8]);
            L2_Reason_DropDown.setSelectedItem(NEBASdao.recLine[2].split(";")[9]);
            L2_Desc_Damage.setText(NEBASdao.recLine[2].split(";")[10]);
            L2_Timestamp.setText(NEBASdao.recLine[2].split(";")[11]);
            L2_Done_ChkBox.setSelected(true);

            L2_First_Sku.setEnabled(false);
            L2_Qty_Out.setEnabled(false);
            L2_First_Desc.setEnabled(false);
            L2_Orig_Sku.setEnabled(false);
            L2_Orig_Desc.setEnabled(false);
            L2_Orig_Attr.setEnabled(false);
            L2_Orig_Size.setEnabled(false);
            L2_Orig_Retail.setEnabled(false);
            L2_New_Used.setEnabled(false);
            L2_Reason_DropDown.setEnabled(false);
            L2_Desc_Damage.setEnabled(false);
            L2_Timestamp.setEnabled(false);
        }
         // Fill Lines w/Existing Data from NEBASdao class
     if (NEBASdao.cntRec > 2 && Character.isDigit(NEBASdao.recLine[3].split(";")[0].charAt(0))) {
            L3_First_Sku.setText(NEBASdao.recLine[3].split(";")[0]);
            L3_Qty_Out.setText(NEBASdao.recLine[3].split(";")[1]);
            L3_First_Desc.setText(NEBASdao.recLine[3].split(";")[2]);
            L3_Orig_Sku.setText(NEBASdao.recLine[3].split(";")[3]);
            L3_Orig_Desc.setText(NEBASdao.recLine[3].split(";")[4]);
            L3_Orig_Attr.setText(NEBASdao.recLine[3].split(";")[5]);
            L3_Orig_Size.setText(NEBASdao.recLine[3].split(";")[6]);
            L3_Orig_Retail.setText(NEBASdao.recLine[3].split(";")[7]);
            L3_New_Used.setSelectedItem(NEBASdao.recLine[3].split(";")[8]);
            L3_Reason_DropDown.setSelectedItem(NEBASdao.recLine[3].split(";")[9]);
            L3_Desc_Damage.setText(NEBASdao.recLine[3].split(";")[10]);
            L3_Timestamp.setText(NEBASdao.recLine[3].split(";")[11]);
            L3_Done_ChkBox.setSelected(true);

            L3_First_Sku.setEnabled(false);
            L3_Qty_Out.setEnabled(false);
            L3_First_Desc.setEnabled(false);
            L3_Orig_Sku.setEnabled(false);
            L3_Orig_Desc.setEnabled(false);
            L3_Orig_Attr.setEnabled(false);
            L3_Orig_Size.setEnabled(false);
            L3_Orig_Retail.setEnabled(false);
            L3_New_Used.setEnabled(false);
            L3_Reason_DropDown.setEnabled(false);
            L3_Desc_Damage.setEnabled(false);
            L3_Timestamp.setEnabled(false);
        }
         // Fill Lines w/Existing Data from NEBASdao class
     if (NEBASdao.cntRec > 3 && Character.isDigit(NEBASdao.recLine[4].split(";")[0].charAt(0))) {
            L4_First_Sku.setText(NEBASdao.recLine[4].split(";")[0]);
            L4_Qty_Out.setText(NEBASdao.recLine[4].split(";")[1]);
            L4_First_Desc.setText(NEBASdao.recLine[4].split(";")[2]);
            L4_Orig_Sku.setText(NEBASdao.recLine[4].split(";")[3]);
            L4_Orig_Desc.setText(NEBASdao.recLine[4].split(";")[4]);
            L4_Orig_Attr.setText(NEBASdao.recLine[4].split(";")[5]);
            L4_Orig_Size.setText(NEBASdao.recLine[4].split(";")[6]);
            L4_Orig_Retail.setText(NEBASdao.recLine[4].split(";")[7]);
            L4_New_Used.setSelectedItem(NEBASdao.recLine[4].split(";")[8]);
            L4_Reason_DropDown.setSelectedItem(NEBASdao.recLine[4].split(";")[9]);
            L4_Desc_Damage.setText(NEBASdao.recLine[4].split(";")[10]);
            L4_Timestamp.setText(NEBASdao.recLine[4].split(";")[11]);
            L4_Done_ChkBox.setSelected(true);

            L4_First_Sku.setEnabled(false);
            L4_Qty_Out.setEnabled(false);
            L4_First_Desc.setEnabled(false);
            L4_Orig_Sku.setEnabled(false);
            L4_Orig_Desc.setEnabled(false);
            L4_Orig_Attr.setEnabled(false);
            L4_Orig_Size.setEnabled(false);
            L4_Orig_Retail.setEnabled(false);
            L4_New_Used.setEnabled(false);
            L4_Reason_DropDown.setEnabled(false);
            L4_Desc_Damage.setEnabled(false);
            L4_Timestamp.setEnabled(false);
        }
          // Fill Lines w/Existing Data from NEBASdao class
      if (NEBASdao.cntRec > 4 && Character.isDigit(NEBASdao.recLine[5].split(";")[0].charAt(0))) {
            L5_First_Sku.setText(NEBASdao.recLine[5].split(";")[0]);
            L5_Qty_Out.setText(NEBASdao.recLine[5].split(";")[1]);
            L5_First_Desc.setText(NEBASdao.recLine[5].split(";")[2]);
            L5_Orig_Sku.setText(NEBASdao.recLine[5].split(";")[3]);
            L5_Orig_Desc.setText(NEBASdao.recLine[5].split(";")[4]);
            L5_Orig_Attr.setText(NEBASdao.recLine[5].split(";")[5]);
            L5_Orig_Size.setText(NEBASdao.recLine[5].split(";")[6]);
            L5_Orig_Retail.setText(NEBASdao.recLine[5].split(";")[7]);
            L5_New_Used.setSelectedItem(NEBASdao.recLine[5].split(";")[8]);
            L5_Reason_DropDown.setSelectedItem(NEBASdao.recLine[5].split(";")[9]);
            L5_Desc_Damage.setText(NEBASdao.recLine[5].split(";")[10]);
            L5_Timestamp.setText(NEBASdao.recLine[5].split(";")[11]);
            L5_Done_ChkBox.setSelected(true);

            L5_First_Sku.setEnabled(false);
            L5_Qty_Out.setEnabled(false);
            L5_First_Desc.setEnabled(false);
            L5_Orig_Sku.setEnabled(false);
            L5_Orig_Desc.setEnabled(false);
            L5_Orig_Attr.setEnabled(false);
            L5_Orig_Size.setEnabled(false);
            L5_Orig_Retail.setEnabled(false);
            L5_New_Used.setEnabled(false);
            L5_Reason_DropDown.setEnabled(false);
            L5_Desc_Damage.setEnabled(false);
            L5_Timestamp.setEnabled(false);
        }
           // Fill Lines w/Existing Data from NEBASdao class
      if (NEBASdao.cntRec > 5 && Character.isDigit(NEBASdao.recLine[6].split(";")[0].charAt(0))) {
            L6_First_Sku.setText(NEBASdao.recLine[6].split(";")[0]);
            L6_Qty_Out.setText(NEBASdao.recLine[6].split(";")[1]);
            L6_First_Desc.setText(NEBASdao.recLine[6].split(";")[2]);
            L6_Orig_Sku.setText(NEBASdao.recLine[6].split(";")[3]);
            L6_Orig_Desc.setText(NEBASdao.recLine[6].split(";")[4]);
            L6_Orig_Attr.setText(NEBASdao.recLine[6].split(";")[5]);
            L6_Orig_Size.setText(NEBASdao.recLine[6].split(";")[6]);
            L6_Orig_Retail.setText(NEBASdao.recLine[6].split(";")[7]);
            L6_New_Used.setSelectedItem(NEBASdao.recLine[6].split(";")[8]);
            L6_Reason_DropDown.setSelectedItem(NEBASdao.recLine[6].split(";")[9]);
            L6_Desc_Damage.setText(NEBASdao.recLine[6].split(";")[10]);
            L6_Timestamp.setText(NEBASdao.recLine[6].split(";")[11]);
            L6_Done_ChkBox.setSelected(true);

            L6_First_Sku.setEnabled(false);
            L6_Qty_Out.setEnabled(false);
            L6_First_Desc.setEnabled(false);
            L6_Orig_Sku.setEnabled(false);
            L6_Orig_Desc.setEnabled(false);
            L6_Orig_Attr.setEnabled(false);
            L6_Orig_Size.setEnabled(false);
            L6_Orig_Retail.setEnabled(false);
            L6_New_Used.setEnabled(false);
            L6_Reason_DropDown.setEnabled(false);
            L6_Desc_Damage.setEnabled(false);
            L6_Timestamp.setEnabled(false);
        }
           // Fill Lines w/Existing Data from NEBASdao class
       if (NEBASdao.cntRec > 6 && Character.isDigit(NEBASdao.recLine[7].split(";")[0].charAt(0))) {
            L7_First_Sku.setText(NEBASdao.recLine[7].split(";")[0]);
            L7_Qty_Out.setText(NEBASdao.recLine[7].split(";")[1]);
            L7_First_Desc.setText(NEBASdao.recLine[7].split(";")[2]);
            L7_Orig_Sku.setText(NEBASdao.recLine[7].split(";")[3]);
            L7_Orig_Desc.setText(NEBASdao.recLine[7].split(";")[4]);
            L7_Orig_Attr.setText(NEBASdao.recLine[7].split(";")[5]);
            L7_Orig_Size.setText(NEBASdao.recLine[7].split(";")[6]);
            L7_Orig_Retail.setText(NEBASdao.recLine[7].split(";")[7]);
            L7_New_Used.setSelectedItem(NEBASdao.recLine[7].split(";")[8]);
            L7_Reason_DropDown.setSelectedItem(NEBASdao.recLine[7].split(";")[9]);
            L7_Desc_Damage.setText(NEBASdao.recLine[7].split(";")[10]);
            L7_Timestamp.setText(NEBASdao.recLine[7].split(";")[11]);
            L7_Done_ChkBox.setSelected(true);

            L7_First_Sku.setEnabled(false);
            L7_Qty_Out.setEnabled(false);
            L7_First_Desc.setEnabled(false);
            L7_Orig_Sku.setEnabled(false);
            L7_Orig_Desc.setEnabled(false);
            L7_Orig_Attr.setEnabled(false);
            L7_Orig_Size.setEnabled(false);
            L7_Orig_Retail.setEnabled(false);
            L7_New_Used.setEnabled(false);
            L7_Reason_DropDown.setEnabled(false);
            L7_Desc_Damage.setEnabled(false);
            L7_Timestamp.setEnabled(false);
        }
             // Fill Lines w/Existing Data from NEBASdao class
      if (NEBASdao.cntRec > 7 && Character.isDigit(NEBASdao.recLine[8].split(";")[0].charAt(0))) {
            L8_First_Sku.setText(NEBASdao.recLine[8].split(";")[0]);
            L8_Qty_Out.setText(NEBASdao.recLine[8].split(";")[1]);
            L8_First_Desc.setText(NEBASdao.recLine[8].split(";")[2]);
            L8_Orig_Sku.setText(NEBASdao.recLine[8].split(";")[3]);
            L8_Orig_Desc.setText(NEBASdao.recLine[8].split(";")[4]);
            L8_Orig_Attr.setText(NEBASdao.recLine[8].split(";")[5]);
            L8_Orig_Size.setText(NEBASdao.recLine[8].split(";")[6]);
            L8_Orig_Retail.setText(NEBASdao.recLine[8].split(";")[7]);
            L8_New_Used.setSelectedItem(NEBASdao.recLine[8].split(";")[8]);
            L8_Reason_DropDown.setSelectedItem(NEBASdao.recLine[8].split(";")[9]);
            L8_Desc_Damage.setText(NEBASdao.recLine[8].split(";")[10]);
            L8_Timestamp.setText(NEBASdao.recLine[8].split(";")[11]);
            L8_Done_ChkBox.setSelected(true);

            L8_First_Sku.setEnabled(false);
            L8_Qty_Out.setEnabled(false);
            L8_First_Desc.setEnabled(false);
            L8_Orig_Sku.setEnabled(false);
            L8_Orig_Desc.setEnabled(false);
            L8_Orig_Attr.setEnabled(false);
            L8_Orig_Size.setEnabled(false);
            L8_Orig_Retail.setEnabled(false);
            L8_New_Used.setEnabled(false);
            L8_Reason_DropDown.setEnabled(false);
            L8_Desc_Damage.setEnabled(false);
            L8_Timestamp.setEnabled(false);
        }
              // Fill Lines w/Existing Data from NEBASdao class
     if (NEBASdao.cntRec > 8 && Character.isDigit(NEBASdao.recLine[9].split(";")[0].charAt(0))) {
            L9_First_Sku.setText(NEBASdao.recLine[9].split(";")[0]);
            L9_Qty_Out.setText(NEBASdao.recLine[9].split(";")[1]);
            L9_First_Desc.setText(NEBASdao.recLine[9].split(";")[2]);
            L9_Orig_Sku.setText(NEBASdao.recLine[9].split(";")[3]);
            L9_Orig_Desc.setText(NEBASdao.recLine[9].split(";")[4]);
            L9_Orig_Attr.setText(NEBASdao.recLine[9].split(";")[5]);
            L9_Orig_Size.setText(NEBASdao.recLine[9].split(";")[6]);
            L9_Orig_Retail.setText(NEBASdao.recLine[9].split(";")[7]);
            L9_New_Used.setSelectedItem(NEBASdao.recLine[9].split(";")[8]);
            L9_Reason_DropDown.setSelectedItem(NEBASdao.recLine[9].split(";")[9]);
            L9_Desc_Damage.setText(NEBASdao.recLine[9].split(";")[10]);
            L9_Timestamp.setText(NEBASdao.recLine[9].split(";")[11]);
            L9_Done_ChkBox.setSelected(true);

            L9_First_Sku.setEnabled(false);
            L9_Qty_Out.setEnabled(false);
            L9_First_Desc.setEnabled(false);
            L9_Orig_Sku.setEnabled(false);
            L9_Orig_Desc.setEnabled(false);
            L9_Orig_Attr.setEnabled(false);
            L9_Orig_Size.setEnabled(false);
            L9_Orig_Retail.setEnabled(false);
            L9_New_Used.setEnabled(false);
            L9_Reason_DropDown.setEnabled(false);
            L9_Desc_Damage.setEnabled(false);
            L9_Timestamp.setEnabled(false);
        }
            // Fill Lines w/Existing Data from NEBASdao class
      if (NEBASdao.cntRec > 9 && Character.isDigit(NEBASdao.recLine[10].split(";")[0].charAt(0))) {
            L10_First_Sku.setText(NEBASdao.recLine[10].split(";")[0]);
            L10_Qty_Out.setText(NEBASdao.recLine[10].split(";")[1]);
            L10_First_Desc.setText(NEBASdao.recLine[10].split(";")[2]);
            L10_Orig_Sku.setText(NEBASdao.recLine[10].split(";")[3]);
            L10_Orig_Desc.setText(NEBASdao.recLine[10].split(";")[4]);
            L10_Orig_Attr.setText(NEBASdao.recLine[10].split(";")[5]);
            L10_Orig_Size.setText(NEBASdao.recLine[10].split(";")[6]);
            L10_Orig_Retail.setText(NEBASdao.recLine[10].split(";")[7]);
            L10_New_Used.setSelectedItem(NEBASdao.recLine[10].split(";")[8]);
            L10_Reason_DropDown.setSelectedItem(NEBASdao.recLine[10].split(";")[9]);
            L10_Desc_Damage.setText(NEBASdao.recLine[10].split(";")[10]);
            L10_Timestamp.setText(NEBASdao.recLine[10].split(";")[11]);
            L10_Done_ChkBox.setSelected(true);

            L10_First_Sku.setEnabled(false);
            L10_Qty_Out.setEnabled(false);
            L10_First_Desc.setEnabled(false);
            L10_Orig_Sku.setEnabled(false);
            L10_Orig_Desc.setEnabled(false);
            L10_Orig_Attr.setEnabled(false);
            L10_Orig_Size.setEnabled(false);
            L10_Orig_Retail.setEnabled(false);
            L10_New_Used.setEnabled(false);
            L10_Reason_DropDown.setEnabled(false);
            L10_Desc_Damage.setEnabled(false);
            L10_Timestamp.setEnabled(false);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Store_Location_Label = new javax.swing.JLabel();
        Store_Logged_In_Label = new javax.swing.JLabel();
        dtStLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        L1 = new javax.swing.JLabel();
        L1_Orig_Sku = new javax.swing.JTextField();
        L1_Qty_Out = new javax.swing.JTextField();
        L1_First_Desc = new javax.swing.JTextField();
        L1_Reason_DropDown = new javax.swing.JComboBox();
        L1_Orig_Desc = new javax.swing.JTextField();
        L1_First_Sku = new javax.swing.JTextField();
        L1_Orig_Retail = new javax.swing.JTextField();
        L1_Done_ChkBox = new javax.swing.JCheckBox();
        L1_Timestamp = new javax.swing.JTextField();
        L2 = new javax.swing.JLabel();
        L2_Orig_Sku = new javax.swing.JTextField();
        L2_Qty_Out = new javax.swing.JTextField();
        L2_First_Desc = new javax.swing.JTextField();
        L2_Reason_DropDown = new javax.swing.JComboBox();
        L2_Orig_Desc = new javax.swing.JTextField();
        L2_First_Sku = new javax.swing.JTextField();
        L2_Orig_Retail = new javax.swing.JTextField();
        L2_Done_ChkBox = new javax.swing.JCheckBox();
        L2_Timestamp = new javax.swing.JTextField();
        L3 = new javax.swing.JLabel();
        L3_Orig_Sku = new javax.swing.JTextField();
        L3_Qty_Out = new javax.swing.JTextField();
        L3_First_Desc = new javax.swing.JTextField();
        L3_Reason_DropDown = new javax.swing.JComboBox();
        L3_Orig_Desc = new javax.swing.JTextField();
        L3_First_Sku = new javax.swing.JTextField();
        L3_Orig_Retail = new javax.swing.JTextField();
        L3_Done_ChkBox = new javax.swing.JCheckBox();
        L3_Timestamp = new javax.swing.JTextField();
        L4 = new javax.swing.JLabel();
        L4_Orig_Sku = new javax.swing.JTextField();
        L4_Qty_Out = new javax.swing.JTextField();
        L4_First_Desc = new javax.swing.JTextField();
        L4_Reason_DropDown = new javax.swing.JComboBox();
        L4_Orig_Desc = new javax.swing.JTextField();
        L4_First_Sku = new javax.swing.JTextField();
        L4_Orig_Retail = new javax.swing.JTextField();
        L4_Done_ChkBox = new javax.swing.JCheckBox();
        L4_Timestamp = new javax.swing.JTextField();
        L5 = new javax.swing.JLabel();
        L5_Orig_Sku = new javax.swing.JTextField();
        L5_Qty_Out = new javax.swing.JTextField();
        L5_First_Desc = new javax.swing.JTextField();
        L5_Reason_DropDown = new javax.swing.JComboBox();
        L5_Orig_Desc = new javax.swing.JTextField();
        L5_First_Sku = new javax.swing.JTextField();
        L5_Orig_Retail = new javax.swing.JTextField();
        L5_Done_ChkBox = new javax.swing.JCheckBox();
        L5_Timestamp = new javax.swing.JTextField();
        L6 = new javax.swing.JLabel();
        L6_Orig_Sku = new javax.swing.JTextField();
        L6_Qty_Out = new javax.swing.JTextField();
        L6_First_Desc = new javax.swing.JTextField();
        L6_Reason_DropDown = new javax.swing.JComboBox();
        L6_Orig_Desc = new javax.swing.JTextField();
        L6_First_Sku = new javax.swing.JTextField();
        L6_Orig_Retail = new javax.swing.JTextField();
        L6_Done_ChkBox = new javax.swing.JCheckBox();
        L6_Timestamp = new javax.swing.JTextField();
        L7 = new javax.swing.JLabel();
        L7_Orig_Sku = new javax.swing.JTextField();
        L7_Qty_Out = new javax.swing.JTextField();
        L7_First_Desc = new javax.swing.JTextField();
        L7_Reason_DropDown = new javax.swing.JComboBox();
        L7_Orig_Desc = new javax.swing.JTextField();
        L7_First_Sku = new javax.swing.JTextField();
        L7_Orig_Retail = new javax.swing.JTextField();
        L7_Done_ChkBox = new javax.swing.JCheckBox();
        L7_Timestamp = new javax.swing.JTextField();
        L8 = new javax.swing.JLabel();
        L8_Orig_Sku = new javax.swing.JTextField();
        L8_Qty_Out = new javax.swing.JTextField();
        L8_First_Desc = new javax.swing.JTextField();
        L8_Reason_DropDown = new javax.swing.JComboBox();
        L8_Orig_Desc = new javax.swing.JTextField();
        L8_First_Sku = new javax.swing.JTextField();
        L8_Orig_Retail = new javax.swing.JTextField();
        L8_Done_ChkBox = new javax.swing.JCheckBox();
        L8_Timestamp = new javax.swing.JTextField();
        L9 = new javax.swing.JLabel();
        L9_Orig_Sku = new javax.swing.JTextField();
        L9_Qty_Out = new javax.swing.JTextField();
        L9_First_Desc = new javax.swing.JTextField();
        L9_Reason_DropDown = new javax.swing.JComboBox();
        L9_Orig_Desc = new javax.swing.JTextField();
        L9_First_Sku = new javax.swing.JTextField();
        L9_Orig_Retail = new javax.swing.JTextField();
        L9_Done_ChkBox = new javax.swing.JCheckBox();
        L9_Timestamp = new javax.swing.JTextField();
        L10 = new javax.swing.JLabel();
        L10_Orig_Sku = new javax.swing.JTextField();
        L10_Qty_Out = new javax.swing.JTextField();
        L10_First_Desc = new javax.swing.JTextField();
        L10_Reason_DropDown = new javax.swing.JComboBox();
        L10_Orig_Desc = new javax.swing.JTextField();
        L10_First_Sku = new javax.swing.JTextField();
        L10_Orig_Retail = new javax.swing.JTextField();
        L10_Done_ChkBox = new javax.swing.JCheckBox();
        L10_Timestamp = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        subBtn = new javax.swing.JButton();
        Close_Btn = new javax.swing.JButton();
        ApproveBtn = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        L1_Desc_Damage = new javax.swing.JTextField();
        L2_Desc_Damage = new javax.swing.JTextField();
        L3_Desc_Damage = new javax.swing.JTextField();
        L4_Desc_Damage = new javax.swing.JTextField();
        L5_Desc_Damage = new javax.swing.JTextField();
        L6_Desc_Damage = new javax.swing.JTextField();
        L7_Desc_Damage = new javax.swing.JTextField();
        L8_Desc_Damage = new javax.swing.JTextField();
        L9_Desc_Damage = new javax.swing.JTextField();
        L10_Desc_Damage = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        L1_New_Used = new javax.swing.JComboBox();
        jTextField9 = new javax.swing.JTextField();
        L2_New_Used = new javax.swing.JComboBox();
        L3_New_Used = new javax.swing.JComboBox();
        L4_New_Used = new javax.swing.JComboBox();
        L5_New_Used = new javax.swing.JComboBox();
        L6_New_Used = new javax.swing.JComboBox();
        L7_New_Used = new javax.swing.JComboBox();
        L8_New_Used = new javax.swing.JComboBox();
        L9_New_Used = new javax.swing.JComboBox();
        L10_New_Used = new javax.swing.JComboBox();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        L1_Orig_Attr = new javax.swing.JTextField();
        L2_Orig_Attr = new javax.swing.JTextField();
        L3_Orig_Attr = new javax.swing.JTextField();
        L1_Orig_Size = new javax.swing.JTextField();
        L4_Orig_Attr = new javax.swing.JTextField();
        L5_Orig_Attr = new javax.swing.JTextField();
        L6_Orig_Attr = new javax.swing.JTextField();
        L7_Orig_Attr = new javax.swing.JTextField();
        L8_Orig_Attr = new javax.swing.JTextField();
        L9_Orig_Attr = new javax.swing.JTextField();
        L10_Orig_Attr = new javax.swing.JTextField();
        L2_Orig_Size = new javax.swing.JTextField();
        L3_Orig_Size = new javax.swing.JTextField();
        L4_Orig_Size = new javax.swing.JTextField();
        L5_Orig_Size = new javax.swing.JTextField();
        L6_Orig_Size = new javax.swing.JTextField();
        L7_Orig_Size = new javax.swing.JTextField();
        L8_Orig_Size = new javax.swing.JTextField();
        L9_Orig_Size = new javax.swing.JTextField();
        L10_Orig_Size = new javax.swing.JTextField();
        RdyforExportBtn = new javax.swing.JButton();
        Form_Name_Tag = new javax.swing.JLabel();
        Test_Label = new javax.swing.JLabel();
        VersionLabel = new javax.swing.JLabel();
        DL_Print_Btn = new javax.swing.JButton();
        CLine_Label = new javax.swing.JLabel();
        CLine_textfield = new javax.swing.JTextField();
        CLine_Button = new javax.swing.JButton();
        Delete_Form_Btn = new javax.swing.JButton();
        Store_Letter_Label = new javax.swing.JLabel();
        Store_Num_Label = new javax.swing.JLabel();
        Date_Label = new javax.swing.JLabel();
        Form_Name_Label = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Inventory Adjustment Application NEBAS Form");
        setMinimumSize(new java.awt.Dimension(10, 10));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                clsFrm(evt);
            }
        });

        jScrollPane2.setPreferredSize(new java.awt.Dimension(1320, 590));

        jPanel6.setBackground(new java.awt.Color(153, 180, 209));
        jPanel6.setPreferredSize(new java.awt.Dimension(1390, 609));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1209, 560));

        Store_Location_Label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Store_Location_Label.setText("Store Location:");

        Store_Logged_In_Label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Store_Logged_In_Label.setText("Store #:");

        dtStLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dtStLabel.setText("Today's Date:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("INVENTORY ADJUSTMENTS - (Non-Exclusive Brand)");

        L1.setText("1");

        L1_Orig_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L1_Orig_SkuFocusLost(evt);
            }
        });
        L1_Orig_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_Orig_SkuKeyPressed(evt);
            }
        });

        L1_Qty_Out.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        L1_Qty_Out.setDoubleBuffered(true);
        L1_Qty_Out.setDragEnabled(true);
        L1_Qty_Out.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L1_Qty_OutFocusLost(evt);
            }
        });
        L1_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_Qty_OutKeyPressed(evt);
            }
        });

        L1_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L1_First_Desc.setFocusable(false);

        L1_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  "Item is Defective", "Not What I Ordered", "Not As Described", "Other" }));
        L1_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_Reason_DropDownKeyPressed(evt);
            }
        });

        L1_Orig_Desc.setAutoscrolls(false);
        L1_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L1_Orig_Desc.setFocusable(false);

        L1_First_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L1_First_SkuFocusLost(evt);
            }
        });
        L1_First_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_First_SkuKeyPressed(evt);
            }
        });

        L1_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L1_Orig_Retail.setFocusable(false);

        L1_Done_ChkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L1_Done_ChkBoxActionPerformed(evt);
            }
        });
        L1_Done_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_Done_ChkBoxKeyPressed(evt);
            }
        });

        L1_Timestamp.setBorder(null);
        L1_Timestamp.setFocusable(false);

        L2.setText("2");

        L2_Orig_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L2_Orig_SkuFocusLost(evt);
            }
        });
        L2_Orig_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_Orig_SkuKeyPressed(evt);
            }
        });

        L2_Qty_Out.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L2_Qty_OutFocusLost(evt);
            }
        });
        L2_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_Qty_OutKeyPressed(evt);
            }
        });

        L2_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L2_First_Desc.setFocusable(false);

        L2_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  "Item is Defective", "Not What I Ordered", "Not As Described", "Other" }));
        L2_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_Reason_DropDownKeyPressed(evt);
            }
        });

        L2_Orig_Desc.setAutoscrolls(false);
        L2_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L2_Orig_Desc.setFocusable(false);

        L2_First_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L2_First_SkuFocusLost(evt);
            }
        });
        L2_First_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_First_SkuKeyPressed(evt);
            }
        });

        L2_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L2_Orig_Retail.setFocusable(false);

        L2_Done_ChkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L2_Done_ChkBoxActionPerformed(evt);
            }
        });
        L2_Done_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_Done_ChkBoxKeyPressed(evt);
            }
        });

        L2_Timestamp.setBorder(null);
        L2_Timestamp.setFocusable(false);

        L3.setText("3");

        L3_Orig_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L3_Orig_SkuFocusLost(evt);
            }
        });
        L3_Orig_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_Orig_SkuKeyPressed(evt);
            }
        });

        L3_Qty_Out.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L3_Qty_OutFocusLost(evt);
            }
        });
        L3_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_Qty_OutKeyPressed(evt);
            }
        });

        L3_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L3_First_Desc.setFocusable(false);

        L3_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  "Item is Defective", "Not What I Ordered", "Not As Described", "Other" }));
        L3_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_Reason_DropDownKeyPressed(evt);
            }
        });

        L3_Orig_Desc.setAutoscrolls(false);
        L3_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L3_Orig_Desc.setFocusable(false);

        L3_First_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L3_First_SkuFocusLost(evt);
            }
        });
        L3_First_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_First_SkuKeyPressed(evt);
            }
        });

        L3_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L3_Orig_Retail.setFocusable(false);

        L3_Done_ChkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L3_Done_ChkBoxActionPerformed(evt);
            }
        });
        L3_Done_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_Done_ChkBoxKeyPressed(evt);
            }
        });

        L3_Timestamp.setBorder(null);
        L3_Timestamp.setFocusable(false);

        L4.setText("4");

        L4_Orig_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L4_Orig_SkuFocusLost(evt);
            }
        });
        L4_Orig_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_Orig_SkuKeyPressed(evt);
            }
        });

        L4_Qty_Out.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L4_Qty_OutFocusLost(evt);
            }
        });
        L4_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_Qty_OutKeyPressed(evt);
            }
        });

        L4_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L4_First_Desc.setFocusable(false);

        L4_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  "Item is Defective", "Not What I Ordered", "Not As Described", "Other" }));
        L4_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_Reason_DropDownKeyPressed(evt);
            }
        });

        L4_Orig_Desc.setAutoscrolls(false);
        L4_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L4_Orig_Desc.setFocusable(false);

        L4_First_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L4_First_SkuFocusLost(evt);
            }
        });
        L4_First_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_First_SkuKeyPressed(evt);
            }
        });

        L4_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L4_Orig_Retail.setFocusable(false);

        L4_Done_ChkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L4_Done_ChkBoxActionPerformed(evt);
            }
        });
        L4_Done_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_Done_ChkBoxKeyPressed(evt);
            }
        });

        L4_Timestamp.setBorder(null);
        L4_Timestamp.setFocusable(false);

        L5.setText("5");

        L5_Orig_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L5_Orig_SkuFocusLost(evt);
            }
        });
        L5_Orig_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_Orig_SkuKeyPressed(evt);
            }
        });

        L5_Qty_Out.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L5_Qty_OutFocusLost(evt);
            }
        });
        L5_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_Qty_OutKeyPressed(evt);
            }
        });

        L5_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L5_First_Desc.setFocusable(false);

        L5_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  "Item is Defective", "Not What I Ordered", "Not As Described", "Other" }));
        L5_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_Reason_DropDownKeyPressed(evt);
            }
        });

        L5_Orig_Desc.setAutoscrolls(false);
        L5_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L5_Orig_Desc.setFocusable(false);

        L5_First_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L5_First_SkuFocusLost(evt);
            }
        });
        L5_First_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_First_SkuKeyPressed(evt);
            }
        });

        L5_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L5_Orig_Retail.setFocusable(false);

        L5_Done_ChkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L5_Done_ChkBoxActionPerformed(evt);
            }
        });
        L5_Done_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_Done_ChkBoxKeyPressed(evt);
            }
        });

        L5_Timestamp.setBorder(null);
        L5_Timestamp.setFocusable(false);

        L6.setText("6");

        L6_Orig_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L6_Orig_SkuFocusLost(evt);
            }
        });
        L6_Orig_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_Orig_SkuKeyPressed(evt);
            }
        });

        L6_Qty_Out.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L6_Qty_OutFocusLost(evt);
            }
        });
        L6_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_Qty_OutKeyPressed(evt);
            }
        });

        L6_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L6_First_Desc.setFocusable(false);

        L6_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  "Item is Defective", "Not What I Ordered", "Not As Described", "Other" }));
        L6_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_Reason_DropDownKeyPressed(evt);
            }
        });

        L6_Orig_Desc.setAutoscrolls(false);
        L6_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L6_Orig_Desc.setFocusable(false);

        L6_First_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L6_First_SkuFocusLost(evt);
            }
        });
        L6_First_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_First_SkuKeyPressed(evt);
            }
        });

        L6_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L6_Orig_Retail.setFocusable(false);

        L6_Done_ChkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L6_Done_ChkBoxActionPerformed(evt);
            }
        });
        L6_Done_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_Done_ChkBoxKeyPressed(evt);
            }
        });

        L6_Timestamp.setBorder(null);
        L6_Timestamp.setFocusable(false);

        L7.setText("7");

        L7_Orig_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L7_Orig_SkuFocusLost(evt);
            }
        });
        L7_Orig_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_Orig_SkuKeyPressed(evt);
            }
        });

        L7_Qty_Out.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L7_Qty_OutFocusLost(evt);
            }
        });
        L7_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_Qty_OutKeyPressed(evt);
            }
        });

        L7_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L7_First_Desc.setFocusable(false);

        L7_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  "Item is Defective", "Not What I Ordered", "Not As Described", "Other" }));
        L7_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_Reason_DropDownKeyPressed(evt);
            }
        });

        L7_Orig_Desc.setAutoscrolls(false);
        L7_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L7_Orig_Desc.setFocusable(false);

        L7_First_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L7_First_SkuFocusLost(evt);
            }
        });
        L7_First_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_First_SkuKeyPressed(evt);
            }
        });

        L7_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L7_Orig_Retail.setFocusable(false);

        L7_Done_ChkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L7_Done_ChkBoxActionPerformed(evt);
            }
        });
        L7_Done_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_Done_ChkBoxKeyPressed(evt);
            }
        });

        L7_Timestamp.setBorder(null);
        L7_Timestamp.setFocusable(false);

        L8.setText("8");

        L8_Orig_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L8_Orig_SkuFocusLost(evt);
            }
        });
        L8_Orig_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_Orig_SkuKeyPressed(evt);
            }
        });

        L8_Qty_Out.setAutoscrolls(false);
        L8_Qty_Out.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L8_Qty_OutFocusLost(evt);
            }
        });
        L8_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_Qty_OutKeyPressed(evt);
            }
        });

        L8_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L8_First_Desc.setFocusable(false);

        L8_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  "Item is Defective", "Not What I Ordered", "Not As Described", "Other" }));
        L8_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_Reason_DropDownKeyPressed(evt);
            }
        });

        L8_Orig_Desc.setAutoscrolls(false);
        L8_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L8_Orig_Desc.setFocusable(false);

        L8_First_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L8_First_SkuFocusLost(evt);
            }
        });
        L8_First_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_First_SkuKeyPressed(evt);
            }
        });

        L8_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L8_Orig_Retail.setFocusable(false);

        L8_Done_ChkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L8_Done_ChkBoxActionPerformed(evt);
            }
        });
        L8_Done_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_Done_ChkBoxKeyPressed(evt);
            }
        });

        L8_Timestamp.setBorder(null);
        L8_Timestamp.setFocusable(false);

        L9.setText("9");

        L9_Orig_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L9_Orig_SkuFocusLost(evt);
            }
        });
        L9_Orig_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_Orig_SkuKeyPressed(evt);
            }
        });

        L9_Qty_Out.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L9_Qty_OutFocusLost(evt);
            }
        });
        L9_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_Qty_OutKeyPressed(evt);
            }
        });

        L9_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L9_First_Desc.setFocusable(false);

        L9_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  "Item is Defective", "Not What I Ordered", "Not As Described", "Other" }));
        L9_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_Reason_DropDownKeyPressed(evt);
            }
        });

        L9_Orig_Desc.setAutoscrolls(false);
        L9_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L9_Orig_Desc.setFocusable(false);

        L9_First_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L9_First_SkuFocusLost(evt);
            }
        });
        L9_First_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_First_SkuKeyPressed(evt);
            }
        });

        L9_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L9_Orig_Retail.setFocusable(false);

        L9_Done_ChkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L9_Done_ChkBoxActionPerformed(evt);
            }
        });
        L9_Done_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_Done_ChkBoxKeyPressed(evt);
            }
        });

        L9_Timestamp.setBorder(null);
        L9_Timestamp.setFocusable(false);

        L10.setText("10");

        L10_Orig_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L10_Orig_SkuFocusLost(evt);
            }
        });
        L10_Orig_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_Orig_SkuKeyPressed(evt);
            }
        });

        L10_Qty_Out.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L10_Qty_OutFocusLost(evt);
            }
        });
        L10_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_Qty_OutKeyPressed(evt);
            }
        });

        L10_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L10_First_Desc.setFocusable(false);

        L10_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  "Item is Defective", "Not What I Ordered", "Not As Described", "Other" }));
        L10_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_Reason_DropDownKeyPressed(evt);
            }
        });

        L10_Orig_Desc.setAutoscrolls(false);
        L10_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L10_Orig_Desc.setFocusable(false);

        L10_First_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L10_First_SkuFocusLost(evt);
            }
        });
        L10_First_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_First_SkuKeyPressed(evt);
            }
        });

        L10_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L10_Orig_Retail.setFocusable(false);

        L10_Done_ChkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L10_Done_ChkBoxActionPerformed(evt);
            }
        });
        L10_Done_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_Done_ChkBoxKeyPressed(evt);
            }
        });

        L10_Timestamp.setBorder(null);
        L10_Timestamp.setFocusable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel2.setPreferredSize(new java.awt.Dimension(100, 55));

        jLabel1.setText("MaxClear ReturnSku");
        jLabel1.setAutoscrolls(true);
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel4.setText("(14636 - 14678)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 11, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("  Qty-Out");
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField1.setFocusable(false);

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setText("      Product Description");
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField2.setFocusable(false);

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setText("    Damage or Defect");
        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField3.setFocusable(false);

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setText("   Damage Description");
        jTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField4.setFocusable(false);

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setText("  Original SKU");
        jTextField5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField5.setFocusable(false);

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(255, 255, 255));
        jTextField6.setForeground(new java.awt.Color(0, 204, 0));
        jTextField6.setText("  Done/Save");
        jTextField6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField6.setFocusable(false);

        subBtn.setBackground(new java.awt.Color(0, 255, 0));
        subBtn.setText("Submit");
        subBtn.setAutoscrolls(true);
        subBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBtnActionPerformed(evt);
            }
        });

        Close_Btn.setBackground(new java.awt.Color(255, 0, 0));
        Close_Btn.setText("Close");
        Close_Btn.setAutoscrolls(true);
        Close_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Close_BtnActionPerformed(evt);
            }
        });

        ApproveBtn.setBackground(new java.awt.Color(0, 153, 255));
        ApproveBtn.setText("Approve");
        ApproveBtn.setFocusable(false);
        ApproveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApproveBtnActionPerformed(evt);
            }
        });

        jTextField7.setEditable(false);
        jTextField7.setBackground(new java.awt.Color(255, 255, 255));
        jTextField7.setText(" Orig Retail");
        jTextField7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField7.setFocusable(false);

        L1_Desc_Damage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L1_Desc_DamageFocusLost(evt);
            }
        });
        L1_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_Desc_DamageKeyPressed(evt);
            }
        });

        L2_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_Desc_DamageKeyPressed(evt);
            }
        });

        L3_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_Desc_DamageKeyPressed(evt);
            }
        });

        L4_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_Desc_DamageKeyPressed(evt);
            }
        });

        L5_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_Desc_DamageKeyPressed(evt);
            }
        });

        L6_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_Desc_DamageKeyPressed(evt);
            }
        });

        L7_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_Desc_DamageKeyPressed(evt);
            }
        });

        L8_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_Desc_DamageKeyPressed(evt);
            }
        });

        L9_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_Desc_DamageKeyPressed(evt);
            }
        });

        L10_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_Desc_DamageKeyPressed(evt);
            }
        });

        jTextField8.setEditable(false);
        jTextField8.setBackground(new java.awt.Color(255, 255, 255));
        jTextField8.setText("    Original Product Description");
        jTextField8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField8.setFocusable(false);

        L1_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L1_New_Used.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L1_New_UsedFocusGained(evt);
            }
        });
        L1_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_New_UsedKeyPressed(evt);
            }
        });

        jTextField9.setEditable(false);
        jTextField9.setBackground(new java.awt.Color(255, 255, 255));
        jTextField9.setText("  New/Used");
        jTextField9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField9.setFocusable(false);

        L2_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L2_New_Used.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L2_New_UsedFocusGained(evt);
            }
        });
        L2_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_New_UsedKeyPressed(evt);
            }
        });

        L3_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L3_New_Used.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L3_New_UsedFocusGained(evt);
            }
        });
        L3_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_New_UsedKeyPressed(evt);
            }
        });

        L4_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L4_New_Used.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L4_New_UsedFocusGained(evt);
            }
        });
        L4_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_New_UsedKeyPressed(evt);
            }
        });

        L5_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L5_New_Used.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L5_New_UsedFocusGained(evt);
            }
        });
        L5_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_New_UsedKeyPressed(evt);
            }
        });

        L6_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L6_New_Used.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L6_New_UsedFocusGained(evt);
            }
        });
        L6_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_New_UsedKeyPressed(evt);
            }
        });

        L7_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L7_New_Used.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L7_New_UsedFocusGained(evt);
            }
        });
        L7_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_New_UsedKeyPressed(evt);
            }
        });

        L8_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L8_New_Used.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L8_New_UsedFocusGained(evt);
            }
        });
        L8_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_New_UsedKeyPressed(evt);
            }
        });

        L9_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L9_New_Used.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L9_New_UsedFocusGained(evt);
            }
        });
        L9_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_New_UsedKeyPressed(evt);
            }
        });

        L10_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L10_New_Used.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L10_New_UsedFocusGained(evt);
            }
        });
        L10_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_New_UsedKeyPressed(evt);
            }
        });

        jTextField10.setEditable(false);
        jTextField10.setBackground(new java.awt.Color(255, 255, 255));
        jTextField10.setText("   Attr");
        jTextField10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField10.setFocusable(false);

        jTextField11.setEditable(false);
        jTextField11.setBackground(new java.awt.Color(255, 255, 255));
        jTextField11.setText("   Size");
        jTextField11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField11.setFocusable(false);

        L1_Orig_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L1_Orig_Attr.setFocusable(false);

        L2_Orig_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L2_Orig_Attr.setFocusable(false);

        L3_Orig_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L3_Orig_Attr.setFocusable(false);

        L1_Orig_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L1_Orig_Size.setFocusable(false);

        L4_Orig_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L4_Orig_Attr.setFocusable(false);

        L5_Orig_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L5_Orig_Attr.setFocusable(false);

        L6_Orig_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L6_Orig_Attr.setFocusable(false);

        L7_Orig_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L7_Orig_Attr.setFocusable(false);

        L8_Orig_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L8_Orig_Attr.setFocusable(false);

        L9_Orig_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L9_Orig_Attr.setFocusable(false);

        L10_Orig_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L10_Orig_Attr.setFocusable(false);

        L2_Orig_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L2_Orig_Size.setFocusable(false);

        L3_Orig_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L3_Orig_Size.setFocusable(false);

        L4_Orig_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L4_Orig_Size.setFocusable(false);

        L5_Orig_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L5_Orig_Size.setFocusable(false);

        L6_Orig_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L6_Orig_Size.setFocusable(false);

        L7_Orig_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L7_Orig_Size.setFocusable(false);

        L8_Orig_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L8_Orig_Size.setFocusable(false);

        L9_Orig_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L9_Orig_Size.setFocusable(false);

        L10_Orig_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L10_Orig_Size.setFocusable(false);

        RdyforExportBtn.setBackground(new java.awt.Color(0, 0, 255));
        RdyforExportBtn.setForeground(new java.awt.Color(255, 255, 255));
        RdyforExportBtn.setText("Ready for Export");
        RdyforExportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RdyforExportBtnActionPerformed(evt);
            }
        });

        Form_Name_Tag.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Form_Name_Tag.setText("Form Name: ");

        Test_Label.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        Test_Label.setText("(Test Environment)");

        VersionLabel.setText("Version 1.109");

        DL_Print_Btn.setBackground(new java.awt.Color(255, 255, 0));
        DL_Print_Btn.setText("Print");
        DL_Print_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DL_Print_BtnActionPerformed(evt);
            }
        });

        CLine_Label.setText("Line");

        CLine_Button.setBackground(new java.awt.Color(204, 0, 0));
        CLine_Button.setText("Clear Line");
        CLine_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLine_ButtonActionPerformed(evt);
            }
        });

        Delete_Form_Btn.setBackground(new java.awt.Color(204, 0, 204));
        Delete_Form_Btn.setText("Delete Form");
        Delete_Form_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_Form_BtnActionPerformed(evt);
            }
        });

        Store_Letter_Label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Store_Letter_Label.setForeground(new java.awt.Color(255, 0, 0));
        Store_Letter_Label.setText("PAC");

        Store_Num_Label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Store_Num_Label.setForeground(new java.awt.Color(0, 0, 255));
        Store_Num_Label.setText("52");

        Date_Label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Date_Label.setForeground(new java.awt.Color(0, 204, 204));
        Date_Label.setText("2015-04-30");

        Form_Name_Label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Form_Name_Label.setForeground(new java.awt.Color(0, 204, 0));
        Form_Name_Label.setText("nebas_999_4_30_14_12_30_45");

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("**Make sure to click on done/save before submitting forms**");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("** NEBAS forms are for Non-Exclusive Brand Skus UNDER $100 ONLY!!! **");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(L1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(L1_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(573, 573, 573)
                                .addComponent(L2_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L2_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L2_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(184, 184, 184)
                                        .addComponent(L2_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(297, 297, 297)
                                        .addComponent(L10_Done_ChkBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(L10_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(subBtn)
                                    .addGap(36, 36, 36)
                                    .addComponent(Close_Btn)
                                    .addGap(50, 50, 50)
                                    .addComponent(ApproveBtn)
                                    .addGap(36, 36, 36)
                                    .addComponent(RdyforExportBtn)
                                    .addGap(314, 314, 314))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(483, 483, 483)
                                            .addComponent(L9_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(L9_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel6)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(Test_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(VersionLabel)
                                                    .addGap(9, 9, 9))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(L3)
                                .addGap(10, 10, 10)
                                .addComponent(L3_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(L3_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L3_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L3_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(L8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(L8_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L8_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L8_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(L7)
                                        .addGap(10, 10, 10)
                                        .addComponent(L7_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(L7_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L7_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(L7_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L8_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(L4)
                                        .addGap(10, 10, 10)
                                        .addComponent(L4_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(L4_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L4_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(L5)
                                        .addGap(10, 10, 10)
                                        .addComponent(L5_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(L5_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L5_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(L6)
                                        .addGap(10, 10, 10)
                                        .addComponent(L6_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(L6_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L6_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(L6_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L5_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L4_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(L6_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L6_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L6_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L6_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L6_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L6_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L6_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(L6_Done_ChkBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(L6_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(L5_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L4_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(L5_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L5_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(L4_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L4_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(L4_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L4_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L4_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L4_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(L4_Done_ChkBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(L4_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(L5_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L5_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L5_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L5_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(L5_Done_ChkBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(L5_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(L7_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L8_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(L8_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(L8_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(L7_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L7_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(L7_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L7_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L7_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L7_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(L7_Done_ChkBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(L7_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(L8_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L8_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L8_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L8_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(L8_Done_ChkBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(L8_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(L3_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L3_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L3_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L3_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L3_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(150, 150, 150)
                                        .addComponent(L2_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(L2_Done_ChkBox))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L3_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L3_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(L3_Done_ChkBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(L3_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(L9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(L9_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L9_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L9_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L9_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L9_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(L9_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L9_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L9_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L9_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(L9_Done_ChkBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(L9_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(L10_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L10_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L10_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L10_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Store_Location_Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Store_Letter_Label)
                        .addGap(21, 21, 21)
                        .addComponent(Store_Logged_In_Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Store_Num_Label)
                        .addGap(27, 27, 27)
                        .addComponent(dtStLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Date_Label)
                        .addGap(18, 18, 18)
                        .addComponent(Form_Name_Tag)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Form_Name_Label)
                        .addGap(28, 28, 28)
                        .addComponent(DL_Print_Btn)
                        .addGap(26, 26, 26)
                        .addComponent(Delete_Form_Btn)
                        .addGap(256, 256, 256)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(L2)
                                .addGap(10, 10, 10)
                                .addComponent(L2_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(L2_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(L1_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(L1_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L1_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L1_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(L2_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L2_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L2_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(L1_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L1_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L1_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L1_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L1_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L1_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(L1_Done_ChkBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(L1_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(L2_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L2_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(L10)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(CLine_Label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CLine_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CLine_Button))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(L10_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(L10_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L10_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L10_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L10_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L10_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L10_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(548, 548, 548)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {L1_First_Sku, L2_First_Sku, L3_First_Sku, L4_First_Sku, L5_First_Sku});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {L1_Qty_Out, L2_Qty_Out, L3_Qty_Out, L4_Qty_Out, L5_Qty_Out, jTextField1});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {L1_Reason_DropDown, L2_Reason_DropDown, L3_Reason_DropDown, L4_Reason_DropDown, L5_Reason_DropDown, L6_Reason_DropDown});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {L3_Orig_Desc, L4_Orig_Desc, L6_Orig_Desc});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {L1_Orig_Retail, L2_Orig_Retail, L3_Orig_Retail, L4_Orig_Retail, L5_Orig_Retail, jTextField7});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DL_Print_Btn)
                        .addComponent(Delete_Form_Btn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Form_Name_Tag)
                        .addComponent(Form_Name_Label))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Store_Logged_In_Label)
                        .addComponent(Store_Num_Label)
                        .addComponent(Store_Letter_Label)
                        .addComponent(Store_Location_Label)
                        .addComponent(dtStLabel)
                        .addComponent(Date_Label)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(VersionLabel)
                            .addComponent(Test_Label)
                            .addComponent(jLabel6))
                        .addGap(4, 4, 4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L1_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(L1_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L1_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L2_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L2_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L2_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L2_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L2_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L2_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L2_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L2_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L2_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L2_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(L2_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L3_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L3_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L3_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L3_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L3_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L3_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L3_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L3_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L3_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L3_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L3_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(L3_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L3_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L4_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L4_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L4_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L4_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L4_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L4_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L4_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L4_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L4_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L4_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L4_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(L4_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L4_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L5_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L5_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L5_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L5_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L5_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L5_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L5_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L5_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L5_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L5_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L5_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(L5_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L5_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L6_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L6_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L6_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L6_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L6_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L6_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L6_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L6_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L6_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L6_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L6_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(L6_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L6_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L7_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L7_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L7_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L7_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L7_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L7_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L7_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L7_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L7_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L7_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L7_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(L7_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L7_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L8_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L8_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L8_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L8_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L8_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L8_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L8_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L8_Orig_Desc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L8_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L8_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L8_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(L8_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L8_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L9_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L9_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L9_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L9_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L9_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L9_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L9_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L9_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L9_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L9_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L9_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(L9_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L9_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L10_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L10_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L10_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L10_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L10_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(L10_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L10_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CLine_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CLine_Label)
                    .addComponent(CLine_Button)
                    .addComponent(Close_Btn)
                    .addComponent(ApproveBtn)
                    .addComponent(RdyforExportBtn)
                    .addComponent(subBtn))
                .addGap(112, 112, 112))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField1, jTextField2, jTextField3, jTextField4, jTextField5, jTextField6, jTextField7});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {L10_Done_ChkBox, L1_Done_ChkBox, L1_Orig_Desc, L1_Orig_Retail, L1_Orig_Sku, L2_Done_ChkBox, L3_Done_ChkBox, L4_Done_ChkBox, L5_Done_ChkBox, L6_Done_ChkBox, L7_Done_ChkBox, L8_Done_ChkBox, L9_Done_ChkBox});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {L10_Timestamp, L1_Timestamp, L2_Timestamp, L3_Timestamp, L4_Timestamp, L5_Timestamp, L6_Timestamp, L7_Timestamp, L8_Timestamp, L9_Timestamp});

        L1_Qty_Out.getAccessibleContext().setAccessibleName("");
        L1_First_Desc.getAccessibleContext().setAccessibleName("");
        L1_First_Sku.getAccessibleContext().setAccessibleName("");
        L2_Qty_Out.getAccessibleContext().setAccessibleName("");
        L2_First_Desc.getAccessibleContext().setAccessibleName("");
        L2_First_Sku.getAccessibleContext().setAccessibleName("");
        L3_Qty_Out.getAccessibleContext().setAccessibleName("");
        L3_First_Desc.getAccessibleContext().setAccessibleName("");
        L3_First_Sku.getAccessibleContext().setAccessibleName("");
        L4_Qty_Out.getAccessibleContext().setAccessibleName("");
        L4_First_Desc.getAccessibleContext().setAccessibleName("");
        L4_First_Sku.getAccessibleContext().setAccessibleName("");
        L5_Qty_Out.getAccessibleContext().setAccessibleName("");
        L5_First_Desc.getAccessibleContext().setAccessibleName("");
        L5_First_Sku.getAccessibleContext().setAccessibleName("");
        L6_Qty_Out.getAccessibleContext().setAccessibleName("");
        L6_First_Desc.getAccessibleContext().setAccessibleName("");
        L6_First_Sku.getAccessibleContext().setAccessibleName("");
        L7_Qty_Out.getAccessibleContext().setAccessibleName("");
        L7_First_Desc.getAccessibleContext().setAccessibleName("");
        L7_First_Sku.getAccessibleContext().setAccessibleName("");
        L8_Qty_Out.getAccessibleContext().setAccessibleName("");
        L8_First_Desc.getAccessibleContext().setAccessibleName("");
        L8_First_Sku.getAccessibleContext().setAccessibleName("");
        L9_Qty_Out.getAccessibleContext().setAccessibleName("");
        L9_First_Desc.getAccessibleContext().setAccessibleName("");
        L9_First_Sku.getAccessibleContext().setAccessibleName("");
        L10_Qty_Out.getAccessibleContext().setAccessibleName("");
        L10_First_Desc.getAccessibleContext().setAccessibleName("");
        L10_First_Sku.getAccessibleContext().setAccessibleName("");
        jPanel2.getAccessibleContext().setAccessibleName("");
        jTextField1.getAccessibleContext().setAccessibleName("");
        jTextField2.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1410, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Close_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Close_BtnActionPerformed
        InvAdj.Rpnt();
        this.dispose();
    }//GEN-LAST:event_Close_BtnActionPerformed

    // Function calls when submit button is pressed
    private void subBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBtnActionPerformed
    // if (L1_Done_ChkBox.isSelected()==true || L2_Done_ChkBox.isSelected()==true || L3_Done_ChkBox.isSelected()==true || L4_Done_ChkBox.isSelected()==true || L5_Done_ChkBox.isSelected()==true
     //   || L6_Done_ChkBox.isSelected()==true || L7_Done_ChkBox.isSelected()==true || L8_Done_ChkBox.isSelected()==true || L9_Done_ChkBox.isSelected()==true || L10_Done_ChkBox.isSelected()==true){
        int response = JOptionPane.showConfirmDialog(null, "Do you want to Submit? \n** NOTE: Any lines with data must have done/save checked before submitting or you will lose data **", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");      
    } else if (response == JOptionPane.YES_OPTION) {
      System.out.println("Yes button clicked");
      String[] args = null;
        try {
            NEBASdao.FormPending();
            PrintForms.formType = ("NEBAS");
            PrintForms.main(args);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(frame, "Form Has Been Submitted for Approval");
        InvAdj.Rpnt();
        NEBASdao.nwFrm = "yes";
     // commented out because this is old code...the export is executed in InvAdj_Admin class
     //   try {
     //       UsrExport();
     //  } catch (SQLException | IOException ex) {
     //     Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
     //   }
        this.dispose();
        } else if (response == JOptionPane.CLOSED_OPTION) {           
      System.out.println("JOptionPane closed");
    }
  //  } else {
  //      JOptionPane.showMessageDialog(frame, "You must input at least one sku and check done/save before submitting \nYou cannot close without submitting", "Submit Only Error", JOptionPane.ERROR_MESSAGE);  
  //  } 
    }//GEN-LAST:event_subBtnActionPerformed

     // multiple field check for correct data when "done" button is clicked
    private void L1_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L1_Done_ChkBoxActionPerformed
       rChkLn1();
        if (L1_Done_ChkBox.isSelected() == true) {    
            String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L1_First_Sku.getText();
            NEBASdao.skuInput2 = L1_Orig_Sku.getText();
            NEBASdao.lnFlg = 1;
            /*
            if (L1_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine1();
                    L1_Qty_Out.setText("");
                    L1_First_Desc.setText("");
                    L1_Orig_Sku.setText("");
                    L1_Orig_Desc.setText("");
                    L1_Orig_Attr.setText("");
                    L1_Orig_Size.setText("");
                    L1_Orig_Retail.setText("");
                    L1_New_Used.setSelectedItem("New");
                    L1_Reason_DropDown.setSelectedItem("");
                    L1_Desc_Damage.setText("");                    
                    L1_Done_ChkBox.setSelected(false);
                    L1_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L1_First_Sku.getText().matches("[0-9]+") && L1_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L1_First_Sku.setBackground(Color.WHITE);
                L1_First_Desc.setText(NEBASdao.prodName);
                
                if (L1_Qty_Out.getText().matches("[1-9]+") && L1_Qty_Out.getText().length() < 2 ) {
                    L1_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L1_Orig_Sku.getText().matches("[0-9]+") && L1_Orig_Sku.getText().length() < 7 ) {
                L1_Orig_Sku.setBackground(Color.WHITE); 
                L1_Orig_Desc.setText(NEBASdao.prodName2);
                L1_Orig_Attr.setText(NEBASdao.prodAttr2);
                L1_Orig_Size.setText(NEBASdao.prodSize2);
                L1_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L1_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L1_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L1_Desc_Damage.setBackground(Color.WHITE);
                    
             //   if((Integer.parseInt(L1_First_Sku.getText()) < 799610 || Integer.parseInt(L1_First_Sku.getText()) > 790407)
             //   && ((L1_Desc_Damage.getText().equals("DW03") || L1_Desc_Damage.getText().equals("dw03")) || (L1_Desc_Damage.getText().equals("DW04") || L1_Desc_Damage.getText().equals("dw04")))) {

                NEBASdao.Field1 = L1_First_Sku.getText();
                NEBASdao.Field2 = L1_Qty_Out.getText();
                NEBASdao.Field3 = L1_First_Desc.getText();
                NEBASdao.Field4 = L1_Orig_Sku.getText();
                NEBASdao.Field5 = L1_Orig_Desc.getText();
                NEBASdao.Field6 = L1_Orig_Attr.getText();
                NEBASdao.Field7 = L1_Orig_Size.getText();
                NEBASdao.Field8 = L1_Orig_Retail.getText();
                NEBASdao.Field9 = L1_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L1_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L1_Desc_Damage.getText();
                
                NEBASdao.rline = L1.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L1.getBounds(null));
                
          //  } else {
          //     JOptionPane.showMessageDialog(frame, "Wrong input, This bluetooth helmet requires either DW03 or DW04 to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
          //      L1_Done_ChkBox.setSelected(false);
          //      L1_Desc_Damage.requestFocus();
          //      L1_Desc_Damage.setBackground(Color.yellow);
          //  }    
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                L1_Done_ChkBox.setSelected(false);
                L1_Desc_Damage.requestFocus();
                L1_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L1_Done_ChkBox.setSelected(false);
                L1_Reason_DropDown.requestFocus();
                }
            } else {
                L1_Done_ChkBox.setSelected(false);
                L1_Orig_Sku.setBackground(Color.yellow);
                L1_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                 JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L1_Done_ChkBox.setSelected(false);
                L1_Qty_Out.setBackground(Color.yellow);
                L1_Qty_Out.requestFocus();
            }
            } else {
                L1_Done_ChkBox.setSelected(false);
                L1_First_Sku.setBackground(Color.yellow);
                L1_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L1_Done_ChkBox.setSelected(false);
                L1_First_Sku.requestFocus();
                L1_First_Sku.setBackground(Color.yellow);
            }
          //  }
        } else {
            L1_First_Sku.setEnabled(true);
            L1_Qty_Out.setEnabled(true);
            L1_First_Desc.setEnabled(true);
            L1_Orig_Sku.setEnabled(true);
            L1_Orig_Desc.setEnabled(true);
            L1_Orig_Attr.setEnabled(true);
            L1_Orig_Size.setEnabled(true);
            L1_Orig_Retail.setEnabled(true);
            L1_New_Used.setEnabled(true);
            L1_Reason_DropDown.setEnabled(true);
            L1_Desc_Damage.setEnabled(true);
            L1_Done_ChkBox.setEnabled(true);
            L1_Timestamp.setEnabled(true);
        }
    }//GEN-LAST:event_L1_Done_ChkBoxActionPerformed

    // closes the form
    private void clsFrm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_clsFrm
        switch (GetForms_InvAdj.usrType) {
        // Store user is default
        default:
            Toolkit.getDefaultToolkit().beep();
          //  if (L1_Done_ChkBox.isSelected()==true || L2_Done_ChkBox.isSelected()==true || L3_Done_ChkBox.isSelected()==true || L4_Done_ChkBox.isSelected()==true || L5_Done_ChkBox.isSelected()==true
          //      || L6_Done_ChkBox.isSelected()==true || L7_Done_ChkBox.isSelected()==true || L8_Done_ChkBox.isSelected()==true || L9_Done_ChkBox.isSelected()==true || L10_Done_ChkBox.isSelected()==true){
        // this ensures that when the store closes the window it will not allow them until they submit their line data to prevent data loss
        int response = JOptionPane.showConfirmDialog(null, "Do you want to Submit? \n** NOTE: Any lines with data must have done/save checked before submitting or you will lose data **", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
        System.out.println("Yes button clicked");
                
            String[] args = null;
        //  if (GetForms_InvAdj.usrType.equals("store")) {
            try {
                NEBASdao.FormPending();
                PrintForms.formType = ("NEBAS");
                PrintForms.main(args);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
                JOptionPane.showMessageDialog(frame, "Form Has Been Submitted for Approval");        
                InvAdj.Rpnt();        
                this.dispose();                
           // } else {
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("NEBAS Window closed");
            }
          //  } else {
          //      JOptionPane.showMessageDialog(frame, "You must input at least one sku and check done/save before submitting \nYou cannot close without submitting first", "Submit Only Error", JOptionPane.ERROR_MESSAGE);  
          //  }
            // DL and IC do not need to submit because it is a store specific action
            break;
            case "dl":
                InvAdj.Rpnt();        
                this.dispose();
            break;
            case "ic":
                InvAdj.Rpnt();        
                this.dispose();            
            }    
    }//GEN-LAST:event_clsFrm

     // multiple field check for correct data when "done" button is clicked
    private void L2_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L2_Done_ChkBoxActionPerformed
        rChkLn2();
        if (L2_Done_ChkBox.isSelected() == true) {
             String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L2_First_Sku.getText();
            NEBASdao.skuInput2 = L2_Orig_Sku.getText();
            NEBASdao.lnFlg = 2;
            /*
             if (L2_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine2();
                    L2_Qty_Out.setText("");
                    L2_First_Desc.setText("");
                    L2_Orig_Sku.setText("");
                    L2_Orig_Desc.setText("");
                    L2_Orig_Attr.setText("");
                    L2_Orig_Size.setText("");
                    L2_Orig_Retail.setText("");
                    L2_New_Used.setSelectedItem("New");
                    L2_Reason_DropDown.setSelectedItem("");
                    L2_Desc_Damage.setText("");                    
                    L2_Done_ChkBox.setSelected(false);
                    L2_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L2_First_Sku.getText().matches("[0-9]+") && L2_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L2_First_Sku.setBackground(Color.WHITE);
                L2_First_Desc.setText(NEBASdao.prodName);
                
                if (L2_Qty_Out.getText().matches("[1-9]+") && L2_Qty_Out.getText().length() < 2 ) {
                    L2_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L2_Orig_Sku.getText().matches("[0-9]+") && L2_Orig_Sku.getText().length() < 7 ) {
                    L2_Orig_Sku.setBackground(Color.WHITE); 
                    L2_Orig_Desc.setText(NEBASdao.prodName2);
                    L2_Orig_Attr.setText(NEBASdao.prodAttr2);
                    L2_Orig_Size.setText(NEBASdao.prodSize2);
                    L2_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L2_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L2_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L2_Desc_Damage.setBackground(Color.WHITE);

                NEBASdao.Field1 = L2_First_Sku.getText();
                NEBASdao.Field2 = L2_Qty_Out.getText();
                NEBASdao.Field3 = L2_First_Desc.getText();
                NEBASdao.Field4 = L2_Orig_Sku.getText();
                NEBASdao.Field5 = L2_Orig_Desc.getText();
                NEBASdao.Field6 = L2_Orig_Attr.getText();
                NEBASdao.Field7 = L2_Orig_Size.getText();
                NEBASdao.Field8 = L2_Orig_Retail.getText();
                NEBASdao.Field9 = L2_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L2_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L2_Desc_Damage.getText();
                
                NEBASdao.rline = L2.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L2.getBounds(null));
                
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                L2_Done_ChkBox.setSelected(false);
                L2_Desc_Damage.requestFocus();
                L2_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L2_Done_ChkBox.setSelected(false);
                L2_Reason_DropDown.requestFocus();
            }
            } else {
                L2_Done_ChkBox.setSelected(false);
                L2_Orig_Sku.setBackground(Color.yellow);
                L2_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L2_Done_ChkBox.setSelected(false);
                L2_Qty_Out.setBackground(Color.yellow);
                L2_Qty_Out.requestFocus();
            }
            } else {
                L2_Done_ChkBox.setSelected(false);
                L2_First_Sku.setBackground(Color.yellow);
                L2_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L2_Done_ChkBox.setSelected(false);
                L2_First_Sku.requestFocus();
                L2_First_Sku.setBackground(Color.yellow);
            }
        //  }
        } else {
            L2_First_Sku.setEnabled(true);
            L2_Qty_Out.setEnabled(true);
            L2_First_Desc.setEnabled(true);
            L2_Orig_Sku.setEnabled(true);
            L2_Orig_Desc.setEnabled(true);
            L2_Orig_Attr.setEnabled(true);
            L2_Orig_Size.setEnabled(true);
            L2_Orig_Retail.setEnabled(true);
            L2_New_Used.setEnabled(true);
            L2_Reason_DropDown.setEnabled(true);
            L2_Desc_Damage.setEnabled(true);
            L2_Done_ChkBox.setEnabled(true);
            L2_Timestamp.setEnabled(true);
        }
    }//GEN-LAST:event_L2_Done_ChkBoxActionPerformed
   
    // if IC is the user then it calls different functions 
    private void ApproveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApproveBtnActionPerformed
    int response = JOptionPane.showConfirmDialog(null, "Do you want to Approve?", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
      System.out.println("Yes button clicked");	   
        try {
                NEBASdao.FormApproved();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(frame, "Form Has Been Approved & Submitted" + '\n' + "to Inventory Control");
            InvAdj_Admin.Rpnt();
        this.dispose();
    } else if (response == JOptionPane.CLOSED_OPTION) {           
      System.out.println("JOptionPane closed");
    } 
    }//GEN-LAST:event_ApproveBtnActionPerformed

    // check validity of sku input by user and autofills appropriate data and moves cursor to next field
    private void L1_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_First_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String[] args = null;
            NEBASdao.skuInput = L1_First_Sku.getText();
            NEBASdao.lnFlg = 1;
            if (L1_First_Sku.getText().matches("[0-9]+") && L1_First_Sku.getText().length() < 7 ) {
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L1_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L1_First_Desc.setText(NEBASdao.prodName);
                L1_Qty_Out.requestFocus();
            } else {
                L1_First_Sku.setBackground(Color.yellow);
                L1_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L1_First_Sku.setBackground(Color.yellow);
                L1_First_Sku.requestFocus();
            }
        }
        
    }//GEN-LAST:event_L1_First_SkuKeyPressed

    // check validity of sku input by user and autofills appropriate data and moves cursor to next field
    private void L2_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_First_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String[] args = null;
            NEBASdao.skuInput = L2_First_Sku.getText();
            NEBASdao.lnFlg = 2;
            if (L2_First_Sku.getText().matches("[0-9]+") && L2_First_Sku.getText().length() < 7 ) {
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L2_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L2_First_Desc.setText(NEBASdao.prodName);
                L2_Qty_Out.requestFocus();
            } else {
                L2_First_Sku.setBackground(Color.yellow);
                L2_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L2_First_Sku.setBackground(Color.yellow);
                L2_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "You can only enter 6 digits");
            }
        }
    }//GEN-LAST:event_L2_First_SkuKeyPressed

     // multiple field check for correct data when enter button is pressed when highlighting done checkbox
    private void L1_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Done_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L1_Done_ChkBox.setSelected(true);
            rChkLn1();
            if (L1_Done_ChkBox.isSelected() == true) {
                 String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L1_First_Sku.getText();
            NEBASdao.skuInput2 = L1_Orig_Sku.getText();
            NEBASdao.lnFlg = 1;
            /*
            if (L1_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine1();
                    L1_Qty_Out.setText("");
                    L1_First_Desc.setText("");
                    L1_Orig_Sku.setText("");
                    L1_Orig_Desc.setText("");
                    L1_Orig_Attr.setText("");
                    L1_Orig_Size.setText("");
                    L1_Orig_Retail.setText("");
                    L1_New_Used.setSelectedItem("New");
                    L1_Reason_DropDown.setSelectedItem("");
                    L1_Desc_Damage.setText("");    
                    L1_Done_ChkBox.setSelected(false);
                    L1_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {  */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L1_First_Sku.getText().matches("[0-9]+") && L1_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L1_First_Sku.setBackground(Color.WHITE);
                L1_First_Desc.setText(NEBASdao.prodName);
                
                if (L1_Qty_Out.getText().matches("[1-9]+") && L1_Qty_Out.getText().length() < 2 ) {
                L1_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L1_Orig_Sku.getText().matches("[0-9]+") && L1_Orig_Sku.getText().length() < 7 ) {
                L1_Orig_Sku.setBackground(Color.WHITE); 
                L1_Orig_Desc.setText(NEBASdao.prodName2);
                L1_Orig_Attr.setText(NEBASdao.prodAttr2);
                L1_Orig_Size.setText(NEBASdao.prodSize2);
                L1_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L1_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L1_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L1_Desc_Damage.setBackground(Color.WHITE);

                NEBASdao.Field1 = L1_First_Sku.getText();
                NEBASdao.Field2 = L1_Qty_Out.getText();
                NEBASdao.Field3 = L1_First_Desc.getText();
                NEBASdao.Field4 = L1_Orig_Sku.getText();
                NEBASdao.Field5 = L1_Orig_Desc.getText();
                NEBASdao.Field6 = L1_Orig_Attr.getText();
                NEBASdao.Field7 = L1_Orig_Size.getText();
                NEBASdao.Field8 = L1_Orig_Retail.getText();
                NEBASdao.Field9 = L1_New_Used.getSelectedItem().toString();   
                NEBASdao.Field10 = L1_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L1_Desc_Damage.getText();             	
                
                NEBASdao.rline = L1.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L1.getBounds(null));
                
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                L1_Done_ChkBox.setSelected(false);
                L1_Desc_Damage.requestFocus();
                L1_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L1_Reason_DropDown.requestFocus();
                L1_Done_ChkBox.setSelected(false);
            }
            } else {
                L1_Done_ChkBox.setSelected(false);
                L1_Orig_Sku.setBackground(Color.yellow);
                L1_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L1_Done_ChkBox.setSelected(false);
                L1_Qty_Out.setBackground(Color.yellow);
                L1_Qty_Out.requestFocus();
            }
            } else {
                L1_Done_ChkBox.setSelected(false);
                L1_First_Sku.setBackground(Color.yellow);
                L1_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L1_Done_ChkBox.setSelected(false);
                L1_First_Sku.requestFocus();
                L1_First_Sku.setBackground(Color.yellow);
            }
            // }
        } else {
            L1_First_Sku.setEnabled(true);
            L1_Qty_Out.setEnabled(true);
            L1_First_Desc.setEnabled(true);
            L1_Orig_Sku.setEnabled(true);
            L1_Orig_Desc.setEnabled(true);
            L1_Orig_Attr.setEnabled(true);
            L1_Orig_Size.setEnabled(true);
            L1_Orig_Retail.setEnabled(true);
            L1_New_Used.setEnabled(true);
            L1_Reason_DropDown.setEnabled(true);
            L1_Desc_Damage.setEnabled(true);
            L1_Done_ChkBox.setEnabled(true);
            L1_Timestamp.setEnabled(true);
            }
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L1_Done_ChkBoxKeyPressed

     // multiple field check for correct data when enter button is pressed when highlighting done checkbox
    private void L2_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Done_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L2_Done_ChkBox.setSelected(true);
            rChkLn2();
            if (L2_Done_ChkBox.isSelected() == true) {
                 String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L2_First_Sku.getText();
            NEBASdao.skuInput2 = L2_Orig_Sku.getText();
            NEBASdao.lnFlg = 2;
            /*
            if (L2_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine2();
                    L2_Qty_Out.setText("");
                    L2_First_Desc.setText("");
                    L2_Orig_Sku.setText("");
                    L2_Orig_Desc.setText("");
                    L2_Orig_Attr.setText("");
                    L2_Orig_Size.setText("");
                    L2_Orig_Retail.setText("");
                    L2_New_Used.setSelectedItem("New");
                    L2_Reason_DropDown.setSelectedItem("");
                    L2_Desc_Damage.setText("");
                    L2_Done_ChkBox.setSelected(false);
                    L2_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L2_First_Sku.getText().matches("[0-9]+") && L2_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L2_First_Sku.setBackground(Color.WHITE);
                L2_First_Desc.setText(NEBASdao.prodName);
                
                if (L2_Qty_Out.getText().matches("[1-9]+") && L2_Qty_Out.getText().length() < 2 ) {
                  L2_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L2_Orig_Sku.getText().matches("[0-9]+") && L2_Orig_Sku.getText().length() < 7 ) {
                L2_Orig_Sku.setBackground(Color.WHITE); 
                L2_Orig_Desc.setText(NEBASdao.prodName2);
                L2_Orig_Attr.setText(NEBASdao.prodAttr2);
                L2_Orig_Size.setText(NEBASdao.prodSize2);
                L2_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L2_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L2_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L2_Desc_Damage.setBackground(Color.WHITE);

                NEBASdao.Field1 = L2_First_Sku.getText();
                NEBASdao.Field2 = L2_Qty_Out.getText();
                NEBASdao.Field3 = L2_First_Desc.getText();
                NEBASdao.Field4 = L2_Orig_Sku.getText();
                NEBASdao.Field5 = L2_Orig_Desc.getText();
                NEBASdao.Field6 = L2_Orig_Attr.getText();
                NEBASdao.Field7 = L2_Orig_Size.getText();
                NEBASdao.Field8 = L2_Orig_Retail.getText();
                NEBASdao.Field9 = L2_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L2_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L2_Desc_Damage.getText();                
                
                NEBASdao.rline = L2.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L2.getBounds(null));
                
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L2_Done_ChkBox.setSelected(false);
                L2_Desc_Damage.requestFocus();
        	L2_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L2_Done_ChkBox.setSelected(false);
                L2_Reason_DropDown.requestFocus();
            }
            } else {
                L2_Done_ChkBox.setSelected(false);
                L2_Orig_Sku.setBackground(Color.yellow);
                L2_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                 JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L2_Done_ChkBox.setSelected(false);
                L2_Qty_Out.setBackground(Color.yellow);
                L2_Qty_Out.requestFocus();
            }
            } else {
                L2_Done_ChkBox.setSelected(false);
                L2_First_Sku.setBackground(Color.yellow);
                L2_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L2_Done_ChkBox.setSelected(false);
                L2_First_Sku.requestFocus();
                L2_First_Sku.setBackground(Color.yellow);
            }
           // }
        } else {
            L2_First_Sku.setEnabled(true);
            L2_Qty_Out.setEnabled(true);
            L2_First_Desc.setEnabled(true);
            L2_Orig_Sku.setEnabled(true);
            L2_Orig_Desc.setEnabled(true);
            L2_Orig_Attr.setEnabled(true);
            L2_Orig_Size.setEnabled(true);
            L2_Orig_Retail.setEnabled(true);
            L2_New_Used.setEnabled(true);
            L2_Reason_DropDown.setEnabled(true);
            L2_Desc_Damage.setEnabled(true);
            L2_Done_ChkBox.setEnabled(true);
            L2_Timestamp.setEnabled(true);
            }
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L2_Done_ChkBoxKeyPressed
    
    // check validity of sku input by user and autofills appropriate data and moves cursor to next field
    private void L3_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_First_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String[] args = null;
            NEBASdao.skuInput = L3_First_Sku.getText();
            NEBASdao.lnFlg = 3;
            if (L3_First_Sku.getText().matches("[0-9]+") && L3_First_Sku.getText().length() < 7 ) {
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L3_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L3_First_Desc.setText(NEBASdao.prodName);
                L3_Qty_Out.requestFocus();
            } else {
                L3_First_Sku.setBackground(Color.yellow);
                L3_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L3_First_Sku.setBackground(Color.yellow);
                L3_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "You can only enter 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L3_First_SkuKeyPressed

    // check validity of sku input by user and autofills appropriate data and moves cursor to next field
    private void L4_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_First_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String[] args = null;
            NEBASdao.skuInput = L4_First_Sku.getText();
            NEBASdao.lnFlg = 4;
            if (L4_First_Sku.getText().matches("[0-9]+") && L4_First_Sku.getText().length() < 7 ) {
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L4_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L4_First_Desc.setText(NEBASdao.prodName);
                L4_Qty_Out.requestFocus();
            } else {
                L4_First_Sku.setBackground(Color.yellow);
                L4_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L4_First_Sku.setBackground(Color.yellow);
                L4_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "You can only enter 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L4_First_SkuKeyPressed

     // multiple field check for correct data when enter button is pressed when highlighting done checkbox
    private void L3_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Done_ChkBoxKeyPressed
           if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L3_Done_ChkBox.setSelected(true);
            rChkLn3();
            if (L3_Done_ChkBox.isSelected() == true) {
                String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L3_First_Sku.getText();
            NEBASdao.skuInput2 = L3_Orig_Sku.getText();
            NEBASdao.lnFlg = 3;
            /*
            if (L3_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine3();
                    L3_Qty_Out.setText("");
                    L3_First_Desc.setText("");
                    L3_Orig_Sku.setText("");
                    L3_Orig_Desc.setText("");
                    L3_Orig_Attr.setText("");
                    L3_Orig_Size.setText("");
                    L3_Orig_Retail.setText("");
                    L3_New_Used.setSelectedItem("New");
                    L3_Reason_DropDown.setSelectedItem("");
                    L3_Desc_Damage.setText("");                    
                    L3_Done_ChkBox.setSelected(false);
                    L3_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L3_First_Sku.getText().matches("[0-9]+") && L3_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L3_First_Sku.setBackground(Color.WHITE);
                L3_First_Desc.setText(NEBASdao.prodName);
                
                if (L3_Qty_Out.getText().matches("[1-9]+") && L3_Qty_Out.getText().length() < 2 ) {
                    L3_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L3_Orig_Sku.getText().matches("[0-9]+") && L3_Orig_Sku.getText().length() < 7 ) {
                L3_Orig_Sku.setBackground(Color.WHITE); 
                L3_Orig_Desc.setText(NEBASdao.prodName2);
                L3_Orig_Attr.setText(NEBASdao.prodAttr2);
                L3_Orig_Size.setText(NEBASdao.prodSize2);
                L3_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L3_Reason_DropDown.getSelectedItem().equals("")) {
                
                if (L3_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L3_Desc_Damage.setBackground(Color.WHITE);

                NEBASdao.Field1 = L3_First_Sku.getText();
                NEBASdao.Field2 = L3_Qty_Out.getText();
                NEBASdao.Field3 = L3_First_Desc.getText();
                NEBASdao.Field4 = L3_Orig_Sku.getText();
                NEBASdao.Field5 = L3_Orig_Desc.getText();
                NEBASdao.Field6 = L3_Orig_Attr.getText();
                NEBASdao.Field7 = L3_Orig_Size.getText();
                NEBASdao.Field8 = L3_Orig_Retail.getText();
                NEBASdao.Field9 = L3_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L3_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L3_Desc_Damage.getText();
                
                NEBASdao.rline = L3.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L3.getBounds(null));
               
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L3_Done_ChkBox.setSelected(false);
                L3_Desc_Damage.requestFocus();
        	L3_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
                L3_Reason_DropDown.requestFocus();
            }
            } else {
                L3_Done_ChkBox.setSelected(false);
                L3_Orig_Sku.setBackground(Color.yellow);
                L3_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
                L3_Qty_Out.setBackground(Color.yellow);
                L3_Qty_Out.requestFocus();
            }
            } else {
                L3_Done_ChkBox.setSelected(false);
                L3_First_Sku.setBackground(Color.yellow);
                L3_Qty_Out.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
                L3_First_Sku.requestFocus();
                L3_First_Sku.setBackground(Color.yellow);
            }
         //   }
        } else {
            L3_First_Sku.setEnabled(true);
            L3_Qty_Out.setEnabled(true);
            L3_First_Desc.setEnabled(true);
            L3_Orig_Sku.setEnabled(true);
            L3_Orig_Desc.setEnabled(true);
            L3_Orig_Attr.setEnabled(true);
            L3_Orig_Size.setEnabled(true);
            L3_Orig_Retail.setEnabled(true);
            L3_New_Used.setEnabled(true);
            L3_Reason_DropDown.setEnabled(true);
            L3_Desc_Damage.setEnabled(true);
            L3_Done_ChkBox.setEnabled(true);
            L3_Timestamp.setEnabled(true);
        }
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L3_Done_ChkBoxKeyPressed

     // multiple field check for correct data when "done" button is pressed
    private void L3_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L3_Done_ChkBoxActionPerformed
        rChkLn3();
        if (L3_Done_ChkBox.isSelected() == true) {
             String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L3_First_Sku.getText();
            NEBASdao.skuInput2 = L3_Orig_Sku.getText();
            NEBASdao.lnFlg = 3;
            /*
            if (L3_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine3();
                    L3_Qty_Out.setText("");
                    L3_First_Desc.setText("");
                    L3_Orig_Sku.setText("");
                    L3_Orig_Desc.setText("");
                    L3_Orig_Attr.setText("");
                    L3_Orig_Size.setText("");
                    L3_Orig_Retail.setText("");
                    L3_New_Used.setSelectedItem("New");
                    L3_Desc_Damage.setText("");
                    L3_Reason_DropDown.setSelectedItem("");
                    L3_Done_ChkBox.setSelected(false);
                    L3_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L3_First_Sku.getText().matches("[0-9]+") && L3_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L3_First_Sku.setBackground(Color.WHITE);
                L3_First_Desc.setText(NEBASdao.prodName);
                
                if (L3_Qty_Out.getText().matches("[1-9]+") && L3_Qty_Out.getText().length() < 2 ) {
                    L3_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L3_Orig_Sku.getText().matches("[0-9]+") && L3_Orig_Sku.getText().length() < 7 ) {
                L3_Orig_Sku.setBackground(Color.WHITE); 
                L3_Orig_Desc.setText(NEBASdao.prodName2);
                L3_Orig_Attr.setText(NEBASdao.prodAttr2);
                L3_Orig_Size.setText(NEBASdao.prodSize2);
                L3_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L3_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L3_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L3_Desc_Damage.setBackground(Color.WHITE);
                
                NEBASdao.Field1 = L3_First_Sku.getText();
                NEBASdao.Field2 = L3_Qty_Out.getText();
                NEBASdao.Field3 = L3_First_Desc.getText();
                NEBASdao.Field4 = L3_Orig_Sku.getText();
                NEBASdao.Field5 = L3_Orig_Desc.getText();
                NEBASdao.Field6 = L3_Orig_Attr.getText();
                NEBASdao.Field7 = L3_Orig_Size.getText();
                NEBASdao.Field8 = L3_Orig_Retail.getText();
                NEBASdao.Field9 = L3_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L3_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L3_Desc_Damage.getText();
                
                NEBASdao.rline = L3.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L3.getBounds(null));
                
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L3_Done_ChkBox.setSelected(false);
                L3_Desc_Damage.requestFocus();
        	L3_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
                L3_Reason_DropDown.requestFocus();
            }
            } else {
                L3_Done_ChkBox.setSelected(false);
                L3_Orig_Sku.setBackground(Color.yellow);
                L3_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                 JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
                L3_Qty_Out.setBackground(Color.yellow);
                L3_Qty_Out.requestFocus();
            }
            } else {
                L3_Done_ChkBox.setSelected(false);
                L3_First_Sku.setBackground(Color.yellow);
                L3_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
                L3_First_Sku.requestFocus();
                L3_First_Sku.setBackground(Color.yellow);
            }
           // }
        } else {
            L3_First_Sku.setEnabled(true);
            L3_Qty_Out.setEnabled(true);
            L3_First_Desc.setEnabled(true);
            L3_Orig_Sku.setEnabled(true);
            L3_Orig_Desc.setEnabled(true);
            L3_Orig_Attr.setEnabled(true);
            L3_Orig_Size.setEnabled(true);
            L3_Orig_Retail.setEnabled(true);
            L3_New_Used.setEnabled(true);
            L3_Reason_DropDown.setEnabled(true);
            L3_Desc_Damage.setEnabled(true);
            L3_Done_ChkBox.setEnabled(true);
            L3_Timestamp.setEnabled(true);
        }
    }//GEN-LAST:event_L3_Done_ChkBoxActionPerformed

    // checks validity of sku input by user and autofills appropriate data and moves cursor to next field
    private void L5_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_First_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String[] args = null;
            NEBASdao.skuInput = L5_First_Sku.getText();
            NEBASdao.lnFlg = 5;
            if (L5_First_Sku.getText().matches("[0-9]+") && L5_First_Sku.getText().length() < 7 ) {
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L5_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L5_First_Desc.setText(NEBASdao.prodName);
                L5_Qty_Out.requestFocus();
            } else {
                L5_First_Sku.setBackground(Color.yellow);
                L5_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
       } else {
                L5_First_Sku.setBackground(Color.yellow);
                L5_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "You can only enter 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
       } 
       }
    }//GEN-LAST:event_L5_First_SkuKeyPressed

    // checks validity of sku input by user and autofills appropriate data and moves cursor to next field
    private void L6_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_First_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String[] args = null;
            NEBASdao.skuInput = L6_First_Sku.getText();
            NEBASdao.lnFlg = 6;
            if (L6_First_Sku.getText().matches("[0-9]+") && L6_First_Sku.getText().length() < 7 ) {
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L6_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L6_First_Desc.setText(NEBASdao.prodName);
                L6_Qty_Out.requestFocus();
            } else {
                L6_First_Sku.setBackground(Color.yellow);
                L6_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L6_First_Sku.setBackground(Color.yellow);
                L6_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "You can only enter 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L6_First_SkuKeyPressed

    // checks validity of sku input by user and autofills appropriate data and moves cursor to next field
    private void L7_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_First_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String[] args = null;
            NEBASdao.skuInput = L7_First_Sku.getText();
            NEBASdao.lnFlg = 7;
            if (L7_First_Sku.getText().matches("[0-9]+") && L7_First_Sku.getText().length() < 7 ) {
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L7_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L7_First_Desc.setText(NEBASdao.prodName);
                L7_Qty_Out.requestFocus();
            } else {
                L7_First_Sku.setBackground(Color.yellow);
                L7_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L7_First_Sku.setBackground(Color.yellow);
                L7_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "You can only enter 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L7_First_SkuKeyPressed

    // checks validity of sku input by user and autofills appropriate data and moves cursor to next field
    private void L8_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_First_SkuKeyPressed
     if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String[] args = null;
            NEBASdao.skuInput = L8_First_Sku.getText();
            NEBASdao.lnFlg = 8;
            if (L8_First_Sku.getText().matches("[0-9]+") && L8_First_Sku.getText().length() < 7 ) {
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L8_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L8_First_Desc.setText(NEBASdao.prodName);
                L8_Qty_Out.requestFocus();
            } else {
                L8_First_Sku.setBackground(Color.yellow);
                L8_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L8_First_Sku.setBackground(Color.yellow);
                L8_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "You can only enter 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L8_First_SkuKeyPressed

    // checks validity of sku input by user and autofills appropriate data and moves cursor to next field
    private void L9_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_First_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String[] args = null;
            NEBASdao.skuInput = L9_First_Sku.getText();
            NEBASdao.lnFlg = 9;
            if (L9_First_Sku.getText().matches("[0-9]+") && L9_First_Sku.getText().length() < 7 ) {
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L9_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L9_First_Desc.setText(NEBASdao.prodName);
                L9_Qty_Out.requestFocus();
            } else {
                L9_First_Sku.setBackground(Color.yellow);
                L9_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L9_First_Sku.setBackground(Color.yellow);
                L9_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "You can only enter 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L9_First_SkuKeyPressed

    // checks validity of sku input by user and autofills appropriate data and moves cursor to next field
    private void L10_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_First_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String[] args = null;
            NEBASdao.skuInput = L10_First_Sku.getText();
            NEBASdao.lnFlg = 10;
            if (L10_First_Sku.getText().matches("[0-9]+") && L10_First_Sku.getText().length() < 7 ) {
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L10_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L10_First_Desc.setText(NEBASdao.prodName);
                L10_Qty_Out.requestFocus();
            } else {
                L10_First_Sku.setBackground(Color.yellow);
                L10_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L10_First_Sku.setBackground(Color.yellow);
                L10_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "You can only enter 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L10_First_SkuKeyPressed

     // multiple field check for correct data when enter button is pressed when highlighting done checkbox
    private void L4_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Done_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L4_Done_ChkBox.setSelected(true);
            rChkLn4();
            if (L4_Done_ChkBox.isSelected() == true) {
                String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L4_First_Sku.getText();
            NEBASdao.skuInput2 = L4_Orig_Sku.getText();
            NEBASdao.lnFlg = 4;
            /*
            if (L4_First_Sku.getText().equals("")) {
                try {
                   NEBASdao.ClearLine4();
                    L4_Qty_Out.setText("");
                    L4_First_Desc.setText("");
                    L4_Orig_Sku.setText("");
                    L4_Orig_Desc.setText("");
                    L4_Orig_Attr.setText("");
                    L4_Orig_Size.setText("");
                    L4_Orig_Retail.setText("");
                    L4_New_Used.setSelectedItem("New");
                    L4_Reason_DropDown.setSelectedItem("");
                    L4_Desc_Damage.setText("");                    
                    L4_Done_ChkBox.setSelected(false);
                    L4_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L4_First_Sku.getText().matches("[0-9]+") && L4_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L4_First_Sku.setBackground(Color.WHITE);
                L4_First_Desc.setText(NEBASdao.prodName);
                
                if (L4_Qty_Out.getText().matches("[1-9]+") && L4_Qty_Out.getText().length() < 2 ) {
                    L4_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L4_Orig_Sku.getText().matches("[0-9]+") && L4_Orig_Sku.getText().length() < 7 ) {
                L4_Orig_Sku.setBackground(Color.WHITE); 
                L4_Orig_Desc.setText(NEBASdao.prodName2);
                L4_Orig_Attr.setText(NEBASdao.prodAttr2);
                L4_Orig_Size.setText(NEBASdao.prodSize2);
                L4_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L4_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L4_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L4_Desc_Damage.setBackground(Color.WHITE);
              
                NEBASdao.Field1 = L4_First_Sku.getText();
                NEBASdao.Field2 = L4_Qty_Out.getText();
                NEBASdao.Field3 = L4_First_Desc.getText();
                NEBASdao.Field4 = L4_Orig_Sku.getText();
                NEBASdao.Field5 = L4_Orig_Desc.getText();
                NEBASdao.Field6 = L4_Orig_Attr.getText();
                NEBASdao.Field7 = L4_Orig_Size.getText();
                NEBASdao.Field8 = L4_Orig_Retail.getText();
                NEBASdao.Field9 = L4_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L4_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L4_Desc_Damage.getText();
                
                NEBASdao.rline = L4.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L4.getBounds(null));
                
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L4_Done_ChkBox.setSelected(false);
                L4_Desc_Damage.requestFocus();
        	L4_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L4_Done_ChkBox.setSelected(false);
                L4_Reason_DropDown.requestFocus();
            }
            } else {
                L4_Done_ChkBox.setSelected(false);
                L4_Orig_Sku.setBackground(Color.yellow);
                L4_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L4_Done_ChkBox.setSelected(false);
                L4_Qty_Out.setBackground(Color.yellow);
                L4_Qty_Out.requestFocus();
            }
            } else {
                L4_Done_ChkBox.setSelected(false);
                L4_First_Sku.setBackground(Color.yellow);
                L4_First_Sku.requestFocus(); 
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L4_Done_ChkBox.setSelected(false);
                L4_First_Sku.requestFocus();
                L4_First_Sku.setBackground(Color.yellow);
            }
           // }
        } else {
            L4_First_Sku.setEnabled(true);
            L4_Qty_Out.setEnabled(true);
            L4_First_Desc.setEnabled(true);
            L4_Orig_Sku.setEnabled(true);
            L4_Orig_Desc.setEnabled(true);
            L4_Orig_Attr.setEnabled(true);
            L4_Orig_Size.setEnabled(true);
            L4_Orig_Retail.setEnabled(true);
            L4_New_Used.setEnabled(true);
            L4_Reason_DropDown.setEnabled(true);
            L4_Desc_Damage.setEnabled(true);
            L4_Done_ChkBox.setEnabled(true);
            L4_Timestamp.setEnabled(true);
            }
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L4_Done_ChkBoxKeyPressed

     // multiple field check for correct data when enter button is pressed when highlighting done checkbox
    private void L5_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Done_ChkBoxKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L5_Done_ChkBox.setSelected(true);
            rChkLn5();
            if (L5_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L5_First_Sku.getText();
            NEBASdao.skuInput2 = L5_Orig_Sku.getText();
            NEBASdao.lnFlg = 5;
            /*
            if (L5_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine5();
                    L5_Qty_Out.setText("");
                    L5_First_Desc.setText("");
                    L5_Orig_Sku.setText("");
                    L5_Orig_Desc.setText("");
                    L5_Orig_Attr.setText("");
                    L5_Orig_Size.setText("");
                    L5_Orig_Retail.setText("");
                    L5_New_Used.setSelectedItem("New");
                    L5_Reason_DropDown.setSelectedItem("");
                    L5_Desc_Damage.setText("");                    
                    L5_Done_ChkBox.setSelected(false);
                    L5_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L5_First_Sku.getText().matches("[0-9]+") && L5_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L5_First_Sku.setBackground(Color.WHITE);
                L5_First_Desc.setText(NEBASdao.prodName);
                
                if (L5_Qty_Out.getText().matches("[1-9]+") && L5_Qty_Out.getText().length() < 2 ) {
                    L5_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L5_Orig_Sku.getText().matches("[0-9]+") && L5_Orig_Sku.getText().length() < 7 ) {
                L5_Orig_Sku.setBackground(Color.WHITE); 
                L5_Orig_Desc.setText(NEBASdao.prodName2);
                L5_Orig_Attr.setText(NEBASdao.prodAttr2);
                L5_Orig_Size.setText(NEBASdao.prodSize2);
                L5_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L5_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L5_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L5_Desc_Damage.setBackground(Color.WHITE);

                NEBASdao.Field1 = L5_First_Sku.getText();
                NEBASdao.Field2 = L5_Qty_Out.getText();
                NEBASdao.Field3 = L5_First_Desc.getText();
                NEBASdao.Field4 = L5_Orig_Sku.getText();
                NEBASdao.Field5 = L5_Orig_Desc.getText();
                NEBASdao.Field6 = L5_Orig_Attr.getText();
                NEBASdao.Field7 = L5_Orig_Size.getText();
                NEBASdao.Field8 = L5_Orig_Retail.getText();
                NEBASdao.Field9 = L5_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L5_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L5_Desc_Damage.getText();
                
                NEBASdao.rline = L5.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L5.getBounds(null));
                
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
        	L5_Desc_Damage.requestFocus();
        	L5_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
                L5_Reason_DropDown.requestFocus();
            }
            } else {
                L5_Done_ChkBox.setSelected(false);
                L5_Orig_Sku.setBackground(Color.yellow);
                L5_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
                L5_Qty_Out.setBackground(Color.yellow);
                L5_Qty_Out.requestFocus();
            }
            } else {
                L5_Done_ChkBox.setSelected(false);
                L5_First_Sku.setBackground(Color.yellow);
                L5_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
                L5_First_Sku.requestFocus();
                L5_First_Sku.setBackground(Color.yellow);
            }
         //   }
        } else {
            L5_First_Sku.setEnabled(true);
            L5_Qty_Out.setEnabled(true);
            L5_First_Desc.setEnabled(true);
            L5_Orig_Sku.setEnabled(true);
            L5_Orig_Desc.setEnabled(true);
            L5_Orig_Attr.setEnabled(true);
            L5_Orig_Size.setEnabled(true);
            L5_Orig_Retail.setEnabled(true);
            L5_New_Used.setEnabled(true);
            L5_Reason_DropDown.setEnabled(true);
            L5_Desc_Damage.setEnabled(true);
            L5_Done_ChkBox.setEnabled(true);
            L5_Timestamp.setEnabled(true);
            }
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L5_Done_ChkBoxKeyPressed

     // multiple field check for correct data when "done" button is pressed
    private void L4_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L4_Done_ChkBoxActionPerformed
        rChkLn4();
        if (L4_Done_ChkBox.isSelected() == true) {
             String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L4_First_Sku.getText();
            NEBASdao.skuInput2 = L4_Orig_Sku.getText();
            NEBASdao.lnFlg = 4;
            /*
            if (L4_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine4();
                    L4_Qty_Out.setText("");
                    L4_First_Desc.setText("");
                    L4_Orig_Sku.setText("");
                    L4_Orig_Desc.setText("");
                    L4_Orig_Attr.setText("");
                    L4_Orig_Size.setText("");
                    L4_Orig_Retail.setText("");
                    L4_New_Used.setSelectedItem("New");
                    L4_Reason_DropDown.setSelectedItem("");
                    L4_Desc_Damage.setText("");
                    L4_Done_ChkBox.setSelected(false);
                    L4_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L4_First_Sku.getText().matches("[0-9]+") && L4_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L4_First_Sku.setBackground(Color.WHITE);
                L4_First_Desc.setText(NEBASdao.prodName);
                
                if (L4_Qty_Out.getText().matches("[1-9]+") && L4_Qty_Out.getText().length() < 2 ) {
                    L4_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L4_Orig_Sku.getText().matches("[0-9]+") && L4_Orig_Sku.getText().length() < 7 ) {
                L4_Orig_Sku.setBackground(Color.WHITE); 
                L4_Orig_Desc.setText(NEBASdao.prodName2);
                L4_Orig_Attr.setText(NEBASdao.prodAttr2);
                L4_Orig_Size.setText(NEBASdao.prodSize2);
                L4_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L4_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L4_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L4_Desc_Damage.setBackground(Color.WHITE);

                NEBASdao.Field1 = L4_First_Sku.getText();
                NEBASdao.Field2 = L4_Qty_Out.getText();
                NEBASdao.Field3 = L4_First_Desc.getText();
                NEBASdao.Field4 = L4_Orig_Sku.getText();
                NEBASdao.Field5 = L4_Orig_Desc.getText();
                NEBASdao.Field6 = L4_Orig_Attr.getText();
                NEBASdao.Field7 = L4_Orig_Size.getText();
                NEBASdao.Field8 = L4_Orig_Retail.getText();
                NEBASdao.Field9 = L4_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L4_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L4_Desc_Damage.getText();
                
                NEBASdao.rline = L4.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L4.getBounds(null));
                
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L4_Done_ChkBox.setSelected(false);
		L4_Desc_Damage.requestFocus();
        	L4_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L4_Done_ChkBox.setSelected(false);
                L4_Reason_DropDown.requestFocus();
            }
            } else {
                L4_Done_ChkBox.setSelected(false);
                L4_Orig_Sku.setBackground(Color.yellow);
                L4_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                 JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L4_Done_ChkBox.setSelected(false);
                L4_Qty_Out.setBackground(Color.yellow);
                L4_Qty_Out.requestFocus();
            }
            } else {
                L4_Done_ChkBox.setSelected(false);
                L4_First_Sku.setBackground(Color.yellow);
                L4_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L4_Done_ChkBox.setSelected(false);
                L4_First_Sku.requestFocus();
                L4_First_Sku.setBackground(Color.yellow);
            }
         //   }
        } else {
            L4_First_Sku.setEnabled(true);
            L4_Qty_Out.setEnabled(true);
            L4_First_Desc.setEnabled(true);
            L4_Orig_Sku.setEnabled(true);
            L4_Orig_Desc.setEnabled(true);
            L4_Orig_Attr.setEnabled(true);
            L4_Orig_Size.setEnabled(true);
            L4_Orig_Retail.setEnabled(true);
            L4_New_Used.setEnabled(true);
            L4_Reason_DropDown.setEnabled(true);
            L4_Desc_Damage.setEnabled(true);
            L4_Done_ChkBox.setEnabled(true);
            L4_Timestamp.setEnabled(true);
        }
    }//GEN-LAST:event_L4_Done_ChkBoxActionPerformed

     // multiple field check for correct data when "done" button is pressed
    private void L5_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L5_Done_ChkBoxActionPerformed
        rChkLn5();
        if (L5_Done_ChkBox.isSelected() == true) {
             String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L5_First_Sku.getText();
            NEBASdao.skuInput2 = L5_Orig_Sku.getText();
            NEBASdao.lnFlg = 5;
            /*
            if (L5_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine5();
                    L5_Qty_Out.setText("");
                    L5_First_Desc.setText("");
                    L5_Orig_Sku.setText("");
                    L5_Orig_Desc.setText("");
                    L5_Orig_Attr.setText("");
                    L5_Orig_Size.setText("");
                    L5_Orig_Retail.setText("");
                    L5_New_Used.setSelectedItem("New");
                    L5_Reason_DropDown.setSelectedItem("");
                    L5_Desc_Damage.setText("");                    
                    L5_Done_ChkBox.setSelected(false);
                    L5_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L5_First_Sku.getText().matches("[0-9]+") && L5_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L5_First_Sku.setBackground(Color.WHITE);
                L5_First_Desc.setText(NEBASdao.prodName);
                
                if (L5_Qty_Out.getText().matches("[1-9]+") && L5_Qty_Out.getText().length() < 2 ) {
                    L5_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L5_Orig_Sku.getText().matches("[0-9]+") && L5_Orig_Sku.getText().length() < 7 ) {
                L5_Orig_Sku.setBackground(Color.WHITE); 
                L5_Orig_Desc.setText(NEBASdao.prodName2);
                L5_Orig_Attr.setText(NEBASdao.prodAttr2);
                L5_Orig_Size.setText(NEBASdao.prodSize2);
                L5_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L5_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L5_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L5_Desc_Damage.setBackground(Color.WHITE);

                NEBASdao.Field1 = L5_First_Sku.getText();
                NEBASdao.Field2 = L5_Qty_Out.getText();
                NEBASdao.Field3 = L5_First_Desc.getText();
                NEBASdao.Field4 = L5_Orig_Sku.getText();
                NEBASdao.Field5 = L5_Orig_Desc.getText();
                NEBASdao.Field6 = L5_Orig_Attr.getText();
                NEBASdao.Field7 = L5_Orig_Size.getText();
                NEBASdao.Field8 = L5_Orig_Retail.getText();
                NEBASdao.Field9 = L5_New_Used.getSelectedItem().toString();
                NEBASdao.Field11 = L5_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field10 = L5_Desc_Damage.getText();
                
                NEBASdao.rline = L5.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L5.getBounds(null));
                
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L5_Done_ChkBox.setSelected(false);
		L5_Desc_Damage.requestFocus();
        	L5_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
                L5_Reason_DropDown.requestFocus();
            }
            } else {
                L5_Done_ChkBox.setSelected(false);
                L5_Orig_Sku.setBackground(Color.yellow);
                L5_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
                L5_Qty_Out.setBackground(Color.yellow);
                L5_Qty_Out.requestFocus();
            }
            } else {
                L5_Done_ChkBox.setSelected(false);
                L5_First_Sku.setBackground(Color.yellow);
                L5_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
                L5_First_Sku.requestFocus();
                L5_First_Sku.setBackground(Color.yellow);
            }
          //  }
        } else {
            L5_First_Sku.setEnabled(true);
            L5_Qty_Out.setEnabled(true);
            L5_First_Desc.setEnabled(true);
            L5_Orig_Sku.setEnabled(true);
            L5_Orig_Desc.setEnabled(true);
            L5_Orig_Attr.setEnabled(true);
            L5_Orig_Size.setEnabled(true);
            L5_Orig_Retail.setEnabled(true);
            L5_New_Used.setEnabled(true);
            L5_Reason_DropDown.setEnabled(true);
            L5_Desc_Damage.setEnabled(true);
            L5_Done_ChkBox.setEnabled(true);
            L5_Timestamp.setEnabled(true);
        }
    }//GEN-LAST:event_L5_Done_ChkBoxActionPerformed

     // multiple field check for correct data when enter button is pressed when highlighting done checkbox
    private void L6_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Done_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L6_Done_ChkBox.setSelected(true);
            rChkLn6();
            if (L6_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L6_First_Sku.getText();
            NEBASdao.skuInput2 = L6_Orig_Sku.getText();
            NEBASdao.lnFlg = 6;
            /*
            if (L6_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine6();
                    L6_Qty_Out.setText("");
                    L6_First_Desc.setText("");
                    L6_Orig_Sku.setText("");
                    L6_Orig_Desc.setText("");
                    L6_Orig_Attr.setText("");
                    L6_Orig_Size.setText("");
                    L6_Orig_Retail.setText("");
                    L6_New_Used.setSelectedItem("New");
                    L6_Reason_DropDown.setSelectedItem("");
                    L6_Desc_Damage.setText("");
                    L6_Done_ChkBox.setSelected(false);
                    L6_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L6_First_Sku.getText().matches("[0-9]+") && L6_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L6_First_Sku.setBackground(Color.WHITE);
                L6_First_Desc.setText(NEBASdao.prodName);
                
                if (L6_Qty_Out.getText().matches("[1-9]+") && L6_Qty_Out.getText().length() < 2 ) {
                    L6_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L6_Orig_Sku.getText().matches("[0-9]+") && L6_Orig_Sku.getText().length() < 7 ) {
                L6_Orig_Sku.setBackground(Color.WHITE); 
                L6_Orig_Desc.setText(NEBASdao.prodName2);
                L6_Orig_Attr.setText(NEBASdao.prodAttr2);
                L6_Orig_Size.setText(NEBASdao.prodSize2);
                L6_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L6_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L6_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L6_Desc_Damage.setBackground(Color.WHITE);

                NEBASdao.Field1 = L6_First_Sku.getText();
                NEBASdao.Field2 = L6_Qty_Out.getText();
                NEBASdao.Field3 = L6_First_Desc.getText();
                NEBASdao.Field4 = L6_Orig_Sku.getText();
                NEBASdao.Field5 = L6_Orig_Desc.getText();
                NEBASdao.Field6 = L6_Orig_Attr.getText();
                NEBASdao.Field7 = L6_Orig_Size.getText();
                NEBASdao.Field8 = L6_Orig_Retail.getText();
                NEBASdao.Field9 = L6_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L6_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L6_Desc_Damage.getText();
                
                NEBASdao.rline = L6.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L6.getBounds(null));
                
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L6_Done_ChkBox.setSelected(false);
		L6_Desc_Damage.requestFocus();
        	L6_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L6_Done_ChkBox.setSelected(false);
                L6_Reason_DropDown.requestFocus();
            }
            } else {
                L6_Done_ChkBox.setSelected(false);
                L6_Orig_Sku.setBackground(Color.yellow);
                L6_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                 JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L6_Done_ChkBox.setSelected(false);
                L6_Qty_Out.setBackground(Color.yellow);
                L6_Qty_Out.requestFocus();
            }
            } else {
                L6_Done_ChkBox.setSelected(false);
                L6_First_Sku.setBackground(Color.yellow);
                L6_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L6_Done_ChkBox.setSelected(false);
                L6_First_Sku.requestFocus();
                L6_First_Sku.setBackground(Color.yellow);
            }
          //  }
        } else {
            L6_First_Sku.setEnabled(true);
            L6_Qty_Out.setEnabled(true);
            L6_First_Desc.setEnabled(true);
            L6_Orig_Sku.setEnabled(true);
            L6_Orig_Desc.setEnabled(true);
            L6_Orig_Attr.setEnabled(true);
            L6_Orig_Size.setEnabled(true);
            L6_Orig_Retail.setEnabled(true);
            L6_New_Used.setEnabled(true);
            L6_Reason_DropDown.setEnabled(true);
            L6_Desc_Damage.setEnabled(true);
            L6_Done_ChkBox.setEnabled(true);
            L6_Timestamp.setEnabled(true);
            }
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L6_Done_ChkBoxKeyPressed

     // multiple field check for correct data when enter button is pressed when highlighting done checkbox
    private void L7_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Done_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L7_Done_ChkBox.setSelected(true);
            rChkLn7();
            if (L7_Done_ChkBox.isSelected() == true) {
                 String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L7_First_Sku.getText();
            NEBASdao.skuInput2 = L7_Orig_Sku.getText();
            NEBASdao.lnFlg = 7;
            /*
            if (L7_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine7();
                    L7_Qty_Out.setText("");
                    L7_First_Desc.setText("");
                    L7_Orig_Sku.setText("");
                    L7_Orig_Desc.setText("");
                    L7_Orig_Attr.setText("");
                    L7_Orig_Size.setText("");
                    L7_Orig_Retail.setText("");
                    L7_New_Used.setSelectedItem("New");
                    L7_Reason_DropDown.setSelectedItem("");
                    L7_Desc_Damage.setText("");                    
                    L7_Done_ChkBox.setSelected(false);
                    L7_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L7_First_Sku.getText().matches("[0-9]+") && L7_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L7_First_Sku.setBackground(Color.WHITE);
                L7_First_Desc.setText(NEBASdao.prodName);
                
                if (L7_Qty_Out.getText().matches("[1-9]+") && L7_Qty_Out.getText().length() < 2 ) {
                    L7_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L7_Orig_Sku.getText().matches("[0-9]+") && L7_Orig_Sku.getText().length() < 7 ) {
                L7_Orig_Sku.setBackground(Color.WHITE); 
                L7_Orig_Desc.setText(NEBASdao.prodName2);
                L7_Orig_Attr.setText(NEBASdao.prodAttr2);
                L7_Orig_Size.setText(NEBASdao.prodSize2);
                L7_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L7_Reason_DropDown.getSelectedItem().equals("")) {
                        
                if (L7_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L7_Desc_Damage.setBackground(Color.WHITE);

                NEBASdao.Field1 = L7_First_Sku.getText();
                NEBASdao.Field2 = L7_Qty_Out.getText();
                NEBASdao.Field3 = L7_First_Desc.getText();
                NEBASdao.Field4 = L7_Orig_Sku.getText();
                NEBASdao.Field5 = L7_Orig_Desc.getText();
                NEBASdao.Field6 = L7_Orig_Attr.getText();
                NEBASdao.Field7 = L7_Orig_Size.getText();
                NEBASdao.Field8 = L7_Orig_Retail.getText();
                NEBASdao.Field9 = L7_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L7_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L7_Desc_Damage.getText();
                
                NEBASdao.rline = L7.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L7.getBounds(null));
                
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L7_Done_ChkBox.setSelected(false);
		L7_Desc_Damage.requestFocus();
        	L7_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L7_Done_ChkBox.setSelected(false);
                L7_Reason_DropDown.requestFocus();
            }
            } else {
                L7_Done_ChkBox.setSelected(false);
                L7_Orig_Sku.setBackground(Color.yellow);
                L7_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L7_Done_ChkBox.setSelected(false);
                L7_Qty_Out.setBackground(Color.yellow);
                L7_Qty_Out.requestFocus();
            }
            } else {
                L7_Done_ChkBox.setSelected(false);
                L7_First_Sku.setBackground(Color.yellow);
                L7_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L7_Done_ChkBox.setSelected(false);
                L7_First_Sku.requestFocus();
                L7_First_Sku.setBackground(Color.yellow);
            }
          //  }
        } else {
            L7_First_Sku.setEnabled(true);
            L7_Qty_Out.setEnabled(true);
            L7_First_Desc.setEnabled(true);
            L7_Orig_Sku.setEnabled(true);
            L7_Orig_Desc.setEnabled(true);
            L7_Orig_Attr.setEnabled(true);
            L7_Orig_Size.setEnabled(true);
            L7_Orig_Retail.setEnabled(true);
            L7_New_Used.setEnabled(true);
            L7_Reason_DropDown.setEnabled(true);
            L7_Desc_Damage.setEnabled(true);
            L7_Done_ChkBox.setEnabled(true);
            L7_Timestamp.setEnabled(true);
        }
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L7_Done_ChkBoxKeyPressed

     // multiple field check for correct data when enter button is pressed when highlighting done checkbox to avoid null or incorrect inputs by user
    private void L8_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Done_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L8_Done_ChkBox.setSelected(true);
            rChkLn8();
            if (L8_Done_ChkBox.isSelected() == true) {
                String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L8_First_Sku.getText();
            NEBASdao.skuInput2 = L8_Orig_Sku.getText();
            NEBASdao.lnFlg = 8;
            /*
            if (L8_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine8();
                    L8_Qty_Out.setText("");
                    L8_First_Desc.setText("");
                    L8_Orig_Sku.setText("");
                    L8_Orig_Desc.setText("");
                    L8_Orig_Attr.setText("");
                    L8_Orig_Size.setText("");
                    L8_Orig_Retail.setText("");
                    L8_New_Used.setSelectedItem("New");
                    L8_Reason_DropDown.setSelectedItem("");
                    L8_Desc_Damage.setText("");                    
                    L8_Done_ChkBox.setSelected(false);
                    L8_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L8_First_Sku.getText().matches("[0-9]+") && L8_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L8_First_Sku.setBackground(Color.WHITE);
                L8_First_Desc.setText(NEBASdao.prodName);
                
                if (L8_Qty_Out.getText().matches("[1-9]+") && L8_Qty_Out.getText().length() < 2 ) {
                    L8_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L8_Orig_Sku.getText().matches("[0-9]+") && L8_Orig_Sku.getText().length() < 7 ) {
                L8_Orig_Sku.setBackground(Color.WHITE); 
                L8_Orig_Desc.setText(NEBASdao.prodName2);
                L8_Orig_Attr.setText(NEBASdao.prodAttr2);
                L8_Orig_Size.setText(NEBASdao.prodSize2);
                L8_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L8_Reason_DropDown.getSelectedItem().equals("")) {
                 
                if (L8_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L8_Desc_Damage.setBackground(Color.WHITE);

                NEBASdao.Field1 = L8_First_Sku.getText();
                NEBASdao.Field2 = L8_Qty_Out.getText();
                NEBASdao.Field3 = L8_First_Desc.getText();
                NEBASdao.Field4 = L8_Orig_Sku.getText();
                NEBASdao.Field5 = L8_Orig_Desc.getText();
                NEBASdao.Field6 = L8_Orig_Attr.getText();
                NEBASdao.Field7 = L8_Orig_Size.getText();
                NEBASdao.Field8 = L8_Orig_Retail.getText();
                NEBASdao.Field9 = L8_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L8_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L8_Desc_Damage.getText();             	
                
                NEBASdao.rline = L8.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L8.getBounds(null));
                
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only inputletters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L8_Done_ChkBox.setSelected(false);
		L8_Desc_Damage.requestFocus();
        	L8_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L8_Done_ChkBox.setSelected(false);
                L8_Reason_DropDown.requestFocus();
            }
            } else {
                L8_Done_ChkBox.setSelected(false);
                L8_Orig_Sku.setBackground(Color.yellow);
                L8_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L8_Done_ChkBox.setSelected(false);
                L8_Qty_Out.setBackground(Color.yellow);
                L8_Qty_Out.requestFocus();
            }
            } else {
                L8_Done_ChkBox.setSelected(false);
                L8_First_Sku.setBackground(Color.yellow);
                L8_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L8_Done_ChkBox.setSelected(false);
                L8_First_Sku.requestFocus();
                L8_First_Sku.setBackground(Color.yellow);
            }
          //  }
        } else {
            L8_First_Sku.setEnabled(true);
            L8_Qty_Out.setEnabled(true);
            L8_First_Desc.setEnabled(true);
            L8_Orig_Sku.setEnabled(true);
            L8_Orig_Desc.setEnabled(true);
            L8_Orig_Attr.setEnabled(true);
            L8_Orig_Size.setEnabled(true);
            L8_Orig_Retail.setEnabled(true);
            L8_New_Used.setEnabled(true);
            L8_Reason_DropDown.setEnabled(true);
            L8_Desc_Damage.setEnabled(true);
            L8_Done_ChkBox.setEnabled(true);
            L8_Timestamp.setEnabled(true);
            }
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L8_Done_ChkBoxKeyPressed

     // multiple field check for correct data when enter button is pressed when highlighting done checkbox to avoid null or incorrect inputs by user
    private void L9_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Done_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L9_Done_ChkBox.setSelected(true);
            rChkLn9();
            if (L9_Done_ChkBox.isSelected() == true) {
                 String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L9_First_Sku.getText();
            NEBASdao.skuInput2 = L9_Orig_Sku.getText();
            NEBASdao.lnFlg = 9;
            /* 
            if (L9_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine9();
                    L9_Qty_Out.setText("");
                    L9_First_Desc.setText("");
                    L9_Orig_Sku.setText("");
                    L9_Orig_Desc.setText("");
                    L9_Orig_Attr.setText("");
                    L9_Orig_Size.setText("");
                    L9_Orig_Retail.setText("");
                    L9_New_Used.setSelectedItem("New");
                    L9_Reason_DropDown.setSelectedItem("");
                    L9_Desc_Damage.setText("");                    
                    L9_Done_ChkBox.setSelected(false);
                    L9_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L9_First_Sku.getText().matches("[0-9]+") && L9_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L9_First_Sku.setBackground(Color.WHITE);
                L9_First_Desc.setText(NEBASdao.prodName);
                
                if (L9_Qty_Out.getText().matches("[1-9]+") && L9_Qty_Out.getText().length() < 2 ) {
                    L9_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L9_Orig_Sku.getText().matches("[0-9]+") && L9_Orig_Sku.getText().length() < 7 ) {
                L9_Orig_Sku.setBackground(Color.WHITE); 
                L9_Orig_Desc.setText(NEBASdao.prodName2);
                L9_Orig_Attr.setText(NEBASdao.prodAttr2);
                L9_Orig_Size.setText(NEBASdao.prodSize2);
                L9_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L9_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L9_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L9_Desc_Damage.setBackground(Color.WHITE);

                NEBASdao.Field1 = L9_First_Sku.getText();
                NEBASdao.Field2 = L9_Qty_Out.getText();
                NEBASdao.Field3 = L9_First_Desc.getText();
                NEBASdao.Field4 = L9_Orig_Sku.getText();
                NEBASdao.Field5 = L9_Orig_Desc.getText();
                NEBASdao.Field6 = L9_Orig_Attr.getText();
                NEBASdao.Field7 = L9_Orig_Size.getText();
                NEBASdao.Field8 = L9_Orig_Retail.getText();
                NEBASdao.Field9 = L9_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L9_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L9_Desc_Damage.getText();
                
                NEBASdao.rline = L9.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L9.getBounds(null));
                
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L9_Done_ChkBox.setSelected(false);
		L9_Desc_Damage.requestFocus();
        	L9_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L9_Done_ChkBox.setSelected(false);
                L9_Reason_DropDown.requestFocus();
            }
            } else {
                L9_Done_ChkBox.setSelected(false);
                L9_Orig_Sku.setBackground(Color.yellow);
                L9_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L9_Done_ChkBox.setSelected(false);
                L9_Qty_Out.setBackground(Color.yellow);
                L9_Qty_Out.requestFocus();
            }
            } else {
                L9_Done_ChkBox.setSelected(false);
                L9_First_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L9_Done_ChkBox.setSelected(false);
                L9_First_Sku.requestFocus();
                L9_First_Sku.setBackground(Color.yellow);
            }
           // }
        } else {
            L9_First_Sku.setEnabled(true);
            L9_Qty_Out.setEnabled(true);
            L9_First_Desc.setEnabled(true);
            L9_Orig_Sku.setEnabled(true);
            L9_Orig_Desc.setEnabled(true);
            L9_Orig_Attr.setEnabled(true);
            L9_Orig_Size.setEnabled(true);
            L9_Orig_Retail.setEnabled(true);
            L9_New_Used.setEnabled(true);
            L9_Reason_DropDown.setEnabled(true);
            L9_Desc_Damage.setEnabled(true);
            L9_Done_ChkBox.setEnabled(true);
            L9_Timestamp.setEnabled(true);
            }
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L9_Done_ChkBoxKeyPressed

     // multiple field check for correct data when enter button is pressed when highlighting done checkbox to avoid null or incorrect inputs by user
    private void L10_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Done_ChkBoxKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L10_Done_ChkBox.setSelected(true);
            rChkLn10();
            if (L10_Done_ChkBox.isSelected() == true) {
                 String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L10_First_Sku.getText();
            NEBASdao.skuInput2 = L10_Orig_Sku.getText();
            NEBASdao.lnFlg = 10;
            /*
            if (L10_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine10();
                    L10_Qty_Out.setText("");
                    L10_First_Desc.setText("");
                    L10_Orig_Sku.setText("");
                    L10_Orig_Desc.setText("");
                    L10_Orig_Attr.setText("");
                    L10_Orig_Size.setText("");
                    L10_Orig_Retail.setText("");
                    L10_New_Used.setSelectedItem("New");
                    L10_Reason_DropDown.setSelectedItem("");
                    L10_Desc_Damage.setText("");                    
                    L10_Done_ChkBox.setSelected(false);
                    L10_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L10_First_Sku.getText().matches("[0-9]+") && L10_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L10_First_Sku.setBackground(Color.WHITE);
                L10_First_Desc.setText(NEBASdao.prodName);
                
                if (L10_Qty_Out.getText().matches("[1-9]+") && L10_Qty_Out.getText().length() < 2 ) {
                    L10_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L10_Orig_Sku.getText().matches("[0-9]+") && L10_Orig_Sku.getText().length() < 7 ) {
                L10_Orig_Sku.setBackground(Color.WHITE); 
                L10_Orig_Desc.setText(NEBASdao.prodName2);
                L10_Orig_Attr.setText(NEBASdao.prodAttr2);
                L10_Orig_Size.setText(NEBASdao.prodSize2);
                L10_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L10_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L10_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L10_Desc_Damage.setBackground(Color.WHITE);

                NEBASdao.Field1 = L10_First_Sku.getText();
                NEBASdao.Field2 = L10_Qty_Out.getText();
                NEBASdao.Field3 = L10_First_Desc.getText();
                NEBASdao.Field4 = L10_Orig_Sku.getText();
                NEBASdao.Field5 = L10_Orig_Desc.getText();
                NEBASdao.Field6 = L10_Orig_Attr.getText();
                NEBASdao.Field7 = L10_Orig_Size.getText();
                NEBASdao.Field8 = L10_Orig_Retail.getText();
                NEBASdao.Field9 = L10_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L10_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L10_Desc_Damage.getText();
                
                NEBASdao.rline = L10.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L10.getBounds(null));
                
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L10_Done_ChkBox.setSelected(false);
		L10_Desc_Damage.requestFocus();
        	L10_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L10_Done_ChkBox.setSelected(false);
                L10_Reason_DropDown.requestFocus();
            }
            } else {
                L10_Done_ChkBox.setSelected(false);
                L10_Orig_Sku.setBackground(Color.yellow);
                L10_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L10_Done_ChkBox.setSelected(false);
                L10_Qty_Out.setBackground(Color.yellow);
                L10_Qty_Out.requestFocus();
            }
            } else {
                L10_Done_ChkBox.setSelected(false);
                L10_First_Sku.setBackground(Color.yellow);
                L10_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L10_Done_ChkBox.setSelected(false);
                L10_First_Sku.requestFocus();
                L10_First_Sku.setBackground(Color.yellow);
            }
          //  }
        } else {
            L10_First_Sku.setEnabled(true);
            L10_Qty_Out.setEnabled(true);
            L10_First_Desc.setEnabled(true);
            L10_Orig_Sku.setEnabled(true);
            L10_Orig_Desc.setEnabled(true);
            L10_Orig_Attr.setEnabled(true);
            L10_Orig_Size.setEnabled(true);
            L10_Orig_Retail.setEnabled(true);
            L10_New_Used.setEnabled(true);
            L10_Reason_DropDown.setEnabled(true);
            L10_Desc_Damage.setEnabled(true);
            L10_Done_ChkBox.setEnabled(true);
            L10_Timestamp.setEnabled(true);
            }
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L10_Done_ChkBoxKeyPressed

    // multiple field check for correct data when "done" button is pressed to avoid null or incorrect inputs by user
    private void L6_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L6_Done_ChkBoxActionPerformed
            rChkLn6(); 
        if (L6_Done_ChkBox.isSelected() == true) {
             String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L6_First_Sku.getText();
            NEBASdao.skuInput2 = L6_Orig_Sku.getText();
            NEBASdao.lnFlg = 6;
            /*
            if (L6_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine6();
                    L6_Qty_Out.setText("");
                    L6_First_Desc.setText("");
                    L6_Orig_Sku.setText("");
                    L6_Orig_Desc.setText("");
                    L6_Orig_Attr.setText("");
                    L6_Orig_Size.setText("");
                    L6_Orig_Retail.setText("");
                    L6_New_Used.setSelectedItem("New");
                    L6_Reason_DropDown.setSelectedItem("");
                    L6_Desc_Damage.setText("");                    
                    L6_Done_ChkBox.setSelected(false);
                    L6_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L6_First_Sku.getText().matches("[0-9]+") && L6_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L6_First_Sku.setBackground(Color.WHITE);
                L6_First_Desc.setText(NEBASdao.prodName);
                
                if (L6_Qty_Out.getText().matches("[1-9]+") && L6_Qty_Out.getText().length() < 2 ) {
                    L6_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L6_Orig_Sku.getText().matches("[0-9]+") && L6_Orig_Sku.getText().length() < 7 ) {
                L6_Orig_Sku.setBackground(Color.WHITE); 
                L6_Orig_Desc.setText(NEBASdao.prodName2);
                L6_Orig_Attr.setText(NEBASdao.prodAttr2);
                L6_Orig_Size.setText(NEBASdao.prodSize2);
                L6_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L6_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L6_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L6_Desc_Damage.setBackground(Color.WHITE);

                NEBASdao.Field1 = L6_First_Sku.getText();
                NEBASdao.Field2 = L6_Qty_Out.getText();
                NEBASdao.Field3 = L6_First_Desc.getText();
                NEBASdao.Field4 = L6_Orig_Sku.getText();
                NEBASdao.Field5 = L6_Orig_Desc.getText();
                NEBASdao.Field6 = L6_Orig_Attr.getText();
                NEBASdao.Field7 = L6_Orig_Size.getText();
                NEBASdao.Field8 = L6_Orig_Retail.getText();
                NEBASdao.Field9 = L6_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L6_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L6_Desc_Damage.getText();
                
                NEBASdao.rline = L6.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L6.getBounds(null));
                
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L6_Done_ChkBox.setSelected(false);
		L6_Desc_Damage.requestFocus();
        	L6_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L6_Done_ChkBox.setSelected(false);
                L6_Reason_DropDown.requestFocus();
            }
            } else {
                L6_Done_ChkBox.setSelected(false);
                L6_Orig_Sku.setBackground(Color.yellow);
                L6_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L6_Done_ChkBox.setSelected(false);
                L6_Qty_Out.setBackground(Color.yellow);
                L6_Qty_Out.requestFocus();
            }
            } else {
                L6_Done_ChkBox.setSelected(false);
                L6_First_Sku.setBackground(Color.yellow);
                L6_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L6_Done_ChkBox.setSelected(false);
                L6_First_Sku.requestFocus();
                L6_First_Sku.setBackground(Color.yellow);
            }
          //  }
        } else {
            L6_First_Sku.setEnabled(true);
            L6_Qty_Out.setEnabled(true);
            L6_First_Desc.setEnabled(true);
            L6_Orig_Sku.setEnabled(true);
            L6_Orig_Desc.setEnabled(true);
            L6_Orig_Attr.setEnabled(true);
            L6_Orig_Size.setEnabled(true);
            L6_Orig_Retail.setEnabled(true);
            L6_New_Used.setEnabled(true);
            L6_Reason_DropDown.setEnabled(true);
            L6_Desc_Damage.setEnabled(true);
            L6_Done_ChkBox.setEnabled(true);
            L6_Timestamp.setEnabled(true);
        }
    }//GEN-LAST:event_L6_Done_ChkBoxActionPerformed

    // multiple field check for correct data when "done" button is pressed
    private void L7_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L7_Done_ChkBoxActionPerformed
            rChkLn7();
        if (L7_Done_ChkBox.isSelected() == true) {
             String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L7_First_Sku.getText();
            NEBASdao.skuInput2 = L7_Orig_Sku.getText();
            NEBASdao.lnFlg = 7;
            /*
            if (L7_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine7();
                    L7_Qty_Out.setText("");
                    L7_First_Desc.setText("");
                    L7_Orig_Sku.setText("");
                    L7_Orig_Desc.setText("");
                    L7_Orig_Attr.setText("");
                    L7_Orig_Size.setText("");
                    L7_Orig_Retail.setText("");
                    L7_New_Used.setSelectedItem("New");
                    L7_Reason_DropDown.setSelectedItem("");
                    L7_Desc_Damage.setText("");                    
                    L7_Done_ChkBox.setSelected(false);
                    L7_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L7_First_Sku.getText().matches("[0-9]+") && L7_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L7_First_Sku.setBackground(Color.WHITE);
                L7_First_Desc.setText(NEBASdao.prodName);
                
                if (L7_Qty_Out.getText().matches("[1-9]+") && L7_Qty_Out.getText().length() < 2 ) {
                    L7_Qty_Out.setBackground(Color.WHITE);
                    
                if (NEBASdao.skuRslt2.equals("good") && L7_Orig_Sku.getText().matches("[0-9]+") && L7_Orig_Sku.getText().length() < 7 ) {
                L7_Orig_Sku.setBackground(Color.WHITE); 
                L7_Orig_Desc.setText(NEBASdao.prodName2);
                L7_Orig_Attr.setText(NEBASdao.prodAttr2);
                L7_Orig_Size.setText(NEBASdao.prodSize2);
                L7_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L7_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L7_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L7_Desc_Damage.setBackground(Color.WHITE);

                NEBASdao.Field1 = L7_First_Sku.getText();
                NEBASdao.Field2 = L7_Qty_Out.getText();
                NEBASdao.Field3 = L7_First_Desc.getText();
                NEBASdao.Field4 = L7_Orig_Sku.getText();
                NEBASdao.Field5 = L7_Orig_Desc.getText();
                NEBASdao.Field6 = L7_Orig_Attr.getText();
                NEBASdao.Field7 = L7_Orig_Size.getText();
                NEBASdao.Field8 = L7_Orig_Retail.getText();
                NEBASdao.Field9 = L7_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L7_Desc_Damage.getText();
                NEBASdao.Field11 = L7_Reason_DropDown.getSelectedItem().toString();
                
                NEBASdao.rline = L7.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L7.getBounds(null));
                
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L7_Done_ChkBox.setSelected(false);
		L7_Desc_Damage.requestFocus();
        	L7_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L7_Done_ChkBox.setSelected(false);
                L7_Reason_DropDown.requestFocus();
            }
            } else {
                L7_Done_ChkBox.setSelected(false);
                L7_Orig_Sku.setBackground(Color.yellow);
                L7_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                 JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L7_Done_ChkBox.setSelected(false);
                L7_Qty_Out.setBackground(Color.yellow);
                L7_Qty_Out.requestFocus();
            }
            } else {
                L7_Done_ChkBox.setSelected(false);
                L7_First_Sku.setBackground(Color.yellow);
                L7_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L7_Done_ChkBox.setSelected(false);
                L7_First_Sku.requestFocus();
                L7_First_Sku.setBackground(Color.yellow);
            }
          //  }
        } else {
            L7_First_Sku.setEnabled(true);
            L7_Qty_Out.setEnabled(true);
            L7_First_Desc.setEnabled(true);
            L7_Orig_Sku.setEnabled(true);
            L7_Orig_Desc.setEnabled(true);
            L7_Orig_Attr.setEnabled(true);
            L7_Orig_Size.setEnabled(true);
            L7_Orig_Retail.setEnabled(true);
            L7_New_Used.setEnabled(true);
            L7_Reason_DropDown.setEnabled(true);
            L7_Desc_Damage.setEnabled(true);
            L7_Done_ChkBox.setEnabled(true);
            L7_Timestamp.setEnabled(true);
        }
    }//GEN-LAST:event_L7_Done_ChkBoxActionPerformed

    // multiple field check for correct data when "done" button is pressed
    private void L8_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L8_Done_ChkBoxActionPerformed
            rChkLn8();
        if (L8_Done_ChkBox.isSelected() == true) {
             String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L8_First_Sku.getText();
            NEBASdao.skuInput2 = L8_Orig_Sku.getText();
            NEBASdao.lnFlg = 8;
            /*
            if (L8_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine8();
                    L8_Qty_Out.setText("");
                    L8_First_Desc.setText("");
                    L8_Orig_Sku.setText("");
                    L8_Orig_Desc.setText("");
                    L8_Orig_Attr.setText("");
                    L8_Orig_Size.setText("");
                    L8_Orig_Retail.setText("");
                    L8_New_Used.setSelectedItem("New");
                    L8_Reason_DropDown.setSelectedItem("");                    
                    L8_Desc_Damage.setText(""); 
                    L8_Done_ChkBox.setSelected(false);
                    L8_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L8_First_Sku.getText().matches("[0-9]+") && L8_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L8_First_Sku.setBackground(Color.WHITE);
                L8_First_Desc.setText(NEBASdao.prodName);
                
                if (L8_Qty_Out.getText().matches("[1-9]+") && L8_Qty_Out.getText().length() < 2 ) {
                    L8_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L8_Orig_Sku.getText().matches("[0-9]+") && L8_Orig_Sku.getText().length() < 7 ) {
                L8_Orig_Sku.setBackground(Color.WHITE); 
                L8_Orig_Desc.setText(NEBASdao.prodName2);
                L8_Orig_Attr.setText(NEBASdao.prodAttr2);
                L8_Orig_Size.setText(NEBASdao.prodSize2);
                L8_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L8_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L8_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L8_Desc_Damage.setBackground(Color.WHITE);

                NEBASdao.Field1 = L8_First_Sku.getText();
                NEBASdao.Field2 = L8_Qty_Out.getText();
                NEBASdao.Field3 = L8_First_Desc.getText();
                NEBASdao.Field4 = L8_Orig_Sku.getText();
                NEBASdao.Field5 = L8_Orig_Desc.getText();
                NEBASdao.Field6 = L8_Orig_Attr.getText();
                NEBASdao.Field7 = L8_Orig_Size.getText();
                NEBASdao.Field8 = L8_Orig_Retail.getText();
                NEBASdao.Field9 = L8_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L8_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L8_Desc_Damage.getText();
                
                NEBASdao.rline = L8.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L8.getBounds(null));
                
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L8_Done_ChkBox.setSelected(false);
		L8_Desc_Damage.requestFocus();
        	L8_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L8_Done_ChkBox.setSelected(false);
                L8_Reason_DropDown.requestFocus();
            }
            } else {
                L8_Done_ChkBox.setSelected(false);
                L8_Orig_Sku.setBackground(Color.yellow);
                L8_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L8_Done_ChkBox.setSelected(false);
                L8_Qty_Out.setBackground(Color.yellow);
                L8_Qty_Out.requestFocus();
            }
            } else {
                L8_Done_ChkBox.setSelected(false);
                L8_First_Sku.setBackground(Color.yellow);
                L8_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L8_Done_ChkBox.setSelected(false);
                L8_First_Sku.requestFocus();
                L8_First_Sku.setBackground(Color.yellow);
            }
          //  }
        } else {
            L8_First_Sku.setEnabled(true);
            L8_Qty_Out.setEnabled(true);
            L8_First_Desc.setEnabled(true);
            L8_Orig_Sku.setEnabled(true);
            L8_Orig_Desc.setEnabled(true);
            L8_Orig_Attr.setEnabled(true);
            L8_Orig_Size.setEnabled(true);
            L8_Orig_Retail.setEnabled(true);
            L8_New_Used.setEnabled(true);
            L8_Reason_DropDown.setEnabled(true);
            L8_Desc_Damage.setEnabled(true);
            L8_Done_ChkBox.setEnabled(true);
            L8_Timestamp.setEnabled(true);
        }
    }//GEN-LAST:event_L8_Done_ChkBoxActionPerformed

    // multiple field check for correct data input when "done" checkbox is pressed
    private void L9_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L9_Done_ChkBoxActionPerformed
            rChkLn9();
        if (L9_Done_ChkBox.isSelected() == true) {
             String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L9_First_Sku.getText();
            NEBASdao.skuInput2 = L9_Orig_Sku.getText();
            NEBASdao.lnFlg = 9;
            /*
            if (L9_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine9();
                    L9_Qty_Out.setText("");
                    L9_First_Desc.setText("");
                    L9_Orig_Sku.setText("");
                    L9_Orig_Desc.setText("");
                    L9_Orig_Attr.setText("");
                    L9_Orig_Size.setText("");
                    L9_Orig_Retail.setText("");
                    L9_New_Used.setSelectedItem("New");
                    L9_Reason_DropDown.setSelectedItem("");
                    L9_Desc_Damage.setText("");                    
                    L9_Done_ChkBox.setSelected(false);
                    L9_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L9_First_Sku.getText().matches("[0-9]+") && L9_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L9_First_Sku.setBackground(Color.WHITE);
                L9_First_Desc.setText(NEBASdao.prodName);
                
                if (L9_Qty_Out.getText().matches("[1-9]+") && L9_Qty_Out.getText().length() < 2 ) {
                    L9_Qty_Out.setBackground(Color.WHITE);
                
                if (NEBASdao.skuRslt2.equals("good") && L9_Orig_Sku.getText().matches("[0-9]+") && L9_Orig_Sku.getText().length() < 7 ) {
                L9_Orig_Sku.setBackground(Color.WHITE); 
                L9_Orig_Desc.setText(NEBASdao.prodName2);
                L9_Orig_Attr.setText(NEBASdao.prodAttr2);
                L9_Orig_Size.setText(NEBASdao.prodSize2);
                L9_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L9_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L9_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L9_Desc_Damage.setBackground(Color.WHITE);

                NEBASdao.Field1 = L9_First_Sku.getText();
                NEBASdao.Field2 = L9_Qty_Out.getText();
                NEBASdao.Field3 = L9_First_Desc.getText();
                NEBASdao.Field4 = L9_Orig_Sku.getText();
                NEBASdao.Field5 = L9_Orig_Desc.getText();
                NEBASdao.Field6 = L9_Orig_Attr.getText();
                NEBASdao.Field7 = L9_Orig_Size.getText();
                NEBASdao.Field8 = L9_Orig_Retail.getText();
                NEBASdao.Field9 = L9_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L9_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L9_Desc_Damage.getText();
                
                NEBASdao.rline = L9.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L9.getBounds(null));

            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L9_Done_ChkBox.setSelected(false);
		L9_Desc_Damage.requestFocus();
        	L9_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L9_Done_ChkBox.setSelected(false);
                L9_Reason_DropDown.requestFocus();
            }
            } else {
                L9_Done_ChkBox.setSelected(false);
                L9_Orig_Sku.setBackground(Color.yellow);
                L9_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L9_Done_ChkBox.setSelected(false);
                L9_Qty_Out.setBackground(Color.yellow);
                L9_Qty_Out.requestFocus();
            }
            } else {
                L9_Done_ChkBox.setSelected(false);
                L9_First_Sku.setBackground(Color.yellow);
                L9_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L9_Done_ChkBox.setSelected(false);
                L9_First_Sku.requestFocus();
                L9_First_Sku.setBackground(Color.yellow);
            }
          //  }
        } else {
            L9_First_Sku.setEnabled(true);
            L9_Qty_Out.setEnabled(true);
            L9_First_Desc.setEnabled(true);
            L9_Orig_Sku.setEnabled(true);
            L9_Orig_Desc.setEnabled(true);
            L9_Orig_Attr.setEnabled(true);
            L9_Orig_Size.setEnabled(true);
            L9_Orig_Retail.setEnabled(true);
            L9_New_Used.setEnabled(true);
            L9_Reason_DropDown.setEnabled(true);
            L9_Desc_Damage.setEnabled(true);
            L9_Done_ChkBox.setEnabled(true);
            L9_Timestamp.setEnabled(true);
        }
    }//GEN-LAST:event_L9_Done_ChkBoxActionPerformed

    // multiple field check for correct data when "done" button is pressed
    private void L10_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L10_Done_ChkBoxActionPerformed
            rChkLn10();
        if (L10_Done_ChkBox.isSelected() == true) {
             String[] args = null;
            GtDates.main(args);
            NEBASdao.skuInput = L10_First_Sku.getText();
            NEBASdao.skuInput2 = L10_Orig_Sku.getText();
            NEBASdao.lnFlg = 10;
            /*
            if (L10_First_Sku.getText().equals("")) {
                try {
                    NEBASdao.ClearLine10();
                    L10_Qty_Out.setText("");
                    L10_First_Desc.setText("");
                    L10_Orig_Sku.setText("");
                    L10_Orig_Desc.setText("");
                    L10_Orig_Attr.setText("");
                    L10_Orig_Size.setText("");
                    L10_Orig_Retail.setText("");
                    L10_New_Used.setSelectedItem("New");
                    L10_Reason_DropDown.setSelectedItem("");
                    L10_Desc_Damage.setText("");                    
                    L10_Done_ChkBox.setSelected(false);
                    L10_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                NEBASdao.main(args);
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L10_First_Sku.getText().matches("[0-9]+") && L10_First_Sku.getText().length() < 7 ) {
            
            if (NEBASdao.skuRslt.equals("good")) {
                L10_First_Sku.setBackground(Color.WHITE);
                L10_First_Desc.setText(NEBASdao.prodName);
                
                if (L10_Qty_Out.getText().matches("[1-9]+") && L10_Qty_Out.getText().length() < 2 ) {
                    L10_Qty_Out.setBackground(Color.WHITE);
                    
                if (NEBASdao.skuRslt2.equals("good") && L10_Orig_Sku.getText().matches("[0-9]+") && L10_Orig_Sku.getText().length() < 7 ) {
                L10_Orig_Sku.setBackground(Color.WHITE); 
                L10_Orig_Desc.setText(NEBASdao.prodName2);
                L10_Orig_Attr.setText(NEBASdao.prodAttr2);
                L10_Orig_Size.setText(NEBASdao.prodSize2);
                L10_Orig_Retail.setText(NEBASdao.prodPr);
                
                if (!L10_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L10_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L10_Desc_Damage.setBackground(Color.WHITE);

                NEBASdao.Field1 = L10_First_Sku.getText();
                NEBASdao.Field2 = L10_Qty_Out.getText();
                NEBASdao.Field3 = L10_First_Desc.getText();
                NEBASdao.Field4 = L10_Orig_Sku.getText();
                NEBASdao.Field5 = L10_Orig_Desc.getText();
                NEBASdao.Field6 = L10_Orig_Attr.getText();
                NEBASdao.Field7 = L10_Orig_Size.getText();
                NEBASdao.Field8 = L10_Orig_Retail.getText();
                NEBASdao.Field9 = L10_New_Used.getSelectedItem().toString();
                NEBASdao.Field10 = L10_Reason_DropDown.getSelectedItem().toString();
                NEBASdao.Field11 = L10_Desc_Damage.getText();
                
                NEBASdao.rline = L10.getText().replace(")", "");

                NEBASdao.CmtLn();
                
                jPanel6.scrollRectToVisible(L10.getBounds(null));
                
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L10_Done_ChkBox.setSelected(false);
		L10_Desc_Damage.requestFocus();
        	L10_Desc_Damage.setBackground(Color.yellow);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L10_Done_ChkBox.setSelected(false);
                L10_Reason_DropDown.requestFocus();
            }
            } else {
                L10_Done_ChkBox.setSelected(false);
                L10_Orig_Sku.setBackground(Color.yellow);
                L10_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L10_Done_ChkBox.setSelected(false);
                L10_Qty_Out.setBackground(Color.yellow);
                L10_Qty_Out.requestFocus();
            }
            } else {
                L10_Done_ChkBox.setSelected(false);
                L10_First_Sku.setBackground(Color.yellow);
                L10_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L10_Done_ChkBox.setSelected(false);
                L10_First_Sku.requestFocus();
                L10_First_Sku.setBackground(Color.yellow);
            }
           // }
        } else {
            L10_First_Sku.setEnabled(true);
            L10_Qty_Out.setEnabled(true);
            L10_First_Desc.setEnabled(true);
            L10_Orig_Sku.setEnabled(true);
            L10_Orig_Desc.setEnabled(true);
            L10_Orig_Attr.setEnabled(true);
            L10_Orig_Size.setEnabled(true);
            L10_Orig_Retail.setEnabled(true);
            L10_New_Used.setEnabled(true);
            L10_Reason_DropDown.setEnabled(true);
            L10_Desc_Damage.setEnabled(true);
            L10_Done_ChkBox.setEnabled(true);
            L10_Timestamp.setEnabled(true);
        }
    }//GEN-LAST:event_L10_Done_ChkBoxActionPerformed

    // sends input and recieves data from NEBASdao.java and inserts data into corresponding fields if correct
    private void L2_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Orig_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            NEBASdao.skuInput2 = L2_Orig_Sku.getText();
            if (!L2_Orig_Sku.getText().equals(L2_First_Sku.getText())) {
                if (L2_Orig_Sku.getText().matches("[0-9]+") && L2_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt2.equals("good")) {
                L2_Orig_Sku.setBackground(Color.WHITE);
                L2_Orig_Desc.setText(NEBASdao.prodName2);
                L2_Orig_Attr.setText(NEBASdao.prodAttr2);
                L2_Orig_Size.setText(NEBASdao.prodSize2);
                L2_Orig_Retail.setText(NEBASdao.prodPr);
                L2_New_Used.requestFocus();
                
            } else {
                L2_Orig_Sku.requestFocus();
                L2_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L2_Orig_Sku.requestFocus();
                L2_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L2_Orig_Sku.requestFocus();
                L2_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L2_Orig_SkuKeyPressed

    // sends input to NEBASdao.java and inserts data into corresponding fields if correct
    private void L3_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Orig_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            NEBASdao.skuInput2 = L3_Orig_Sku.getText();
            if (!L3_Orig_Sku.getText().equals(L3_First_Sku.getText())) {
                if (L3_Orig_Sku.getText().matches("[0-9]+") && L3_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt2.equals("good")) {
                L3_Orig_Sku.setBackground(Color.WHITE);
                L3_Orig_Desc.setText(NEBASdao.prodName2);
                L3_Orig_Attr.setText(NEBASdao.prodAttr2);
                L3_Orig_Size.setText(NEBASdao.prodSize2);
                L3_Orig_Retail.setText(NEBASdao.prodPr);
                L3_New_Used.requestFocus();
                
            } else {
                L3_Orig_Sku.requestFocus();
                L3_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L3_Orig_Sku.requestFocus();
                L3_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L3_Orig_Sku.requestFocus();
                L3_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L3_Orig_SkuKeyPressed

    // sends input to NEBASdao.java and inserts data into corresponding fields if correct
    private void L4_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Orig_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            NEBASdao.skuInput2 = L4_Orig_Sku.getText();
            if (!L4_Orig_Sku.getText().equals(L4_First_Sku.getText())) {
                if (L4_Orig_Sku.getText().matches("[0-9]+") && L4_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
                if (NEBASdao.skuRslt2.equals("good")) {
                    L4_Orig_Sku.setBackground(Color.WHITE);
                    L4_Orig_Desc.setText(NEBASdao.prodName2);
                    L4_Orig_Attr.setText(NEBASdao.prodAttr2);
                    L4_Orig_Size.setText(NEBASdao.prodSize2);
                    L4_Orig_Retail.setText(NEBASdao.prodPr);
                    L4_New_Used.requestFocus();
                } else {
                    L4_Orig_Sku.requestFocus();
                    L4_Orig_Sku.setBackground(Color.yellow);
                    JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                }
                } else {
                    L4_Orig_Sku.requestFocus();
                    L4_Orig_Sku.setBackground(Color.yellow);
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                }
                } else {
                    L4_Orig_Sku.requestFocus();
                    L4_Orig_Sku.setBackground(Color.yellow);
                    JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                }
        }
    }//GEN-LAST:event_L4_Orig_SkuKeyPressed

    // sends input to NEBASdao.java and inserts data into corresponding fields if correct
    private void L5_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Orig_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            NEBASdao.skuInput2 = L5_Orig_Sku.getText();
            if (!L5_Orig_Sku.getText().equals(L5_First_Sku.getText())) {
                if (L5_Orig_Sku.getText().matches("[0-9]+") && L5_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
                if (NEBASdao.skuRslt2.equals("good")) {
                    L5_Orig_Sku.setBackground(Color.WHITE);
                    L5_Orig_Desc.setText(NEBASdao.prodName2);
                    L5_Orig_Attr.setText(NEBASdao.prodAttr2);
                    L5_Orig_Size.setText(NEBASdao.prodSize2);
                    L5_Orig_Retail.setText(NEBASdao.prodPr);
                    L5_New_Used.requestFocus();
                } else {
                    L5_Orig_Sku.requestFocus();
                    L5_Orig_Sku.setBackground(Color.yellow);
                    JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                }
                } else {
                    L5_Orig_Sku.requestFocus();
                    L5_Orig_Sku.setBackground(Color.yellow);
                     JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                }
                } else {
                    L5_Orig_Sku.requestFocus();
                    L5_Orig_Sku.setBackground(Color.yellow);
                    JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                }
        }
    }//GEN-LAST:event_L5_Orig_SkuKeyPressed

    // sends input to NEBASdao.java and inserts data into corresponding fields if correct
    private void L6_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Orig_SkuKeyPressed
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
          NEBASdao.skuInput2 = L6_Orig_Sku.getText();
          if (!L6_Orig_Sku.getText().equals(L6_First_Sku.getText())) {
              if (L6_Orig_Sku.getText().matches("[0-9]+") && L6_Orig_Sku.getText().length() < 7 ) {
          // String[] args = null;
            try {
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt2.equals("good")) {
                L6_Orig_Sku.setBackground(Color.WHITE);
                L6_Orig_Desc.setText(NEBASdao.prodName2);
                L6_Orig_Attr.setText(NEBASdao.prodAttr2);
                L6_Orig_Size.setText(NEBASdao.prodSize2);
                L6_Orig_Retail.setText(NEBASdao.prodPr);
                L6_New_Used.requestFocus();
            } else {
                L6_Orig_Sku.requestFocus();
                L6_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L6_Orig_Sku.requestFocus();
                L6_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                L6_Orig_Sku.requestFocus();
                L6_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L6_Orig_SkuKeyPressed

    // sends input to NEBASdao.java and inserts data into corresponding fields if correct
    private void L7_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Orig_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           NEBASdao.skuInput2 = L7_Orig_Sku.getText();
           if (!L7_Orig_Sku.getText().equals(L7_First_Sku.getText())) {
               if (L7_Orig_Sku.getText().matches("[0-9]+") && L7_Orig_Sku.getText().length() < 7 ) {
           // String[] args = null; 
            try {
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt2.equals("good")) {
                L7_Orig_Sku.setBackground(Color.WHITE);
                L7_Orig_Desc.setText(NEBASdao.prodName2);
                L7_Orig_Attr.setText(NEBASdao.prodAttr2);
                L7_Orig_Size.setText(NEBASdao.prodSize2);
                L7_Orig_Retail.setText(NEBASdao.prodPr);
                L7_New_Used.requestFocus();
            } else {
                L7_Orig_Sku.requestFocus();
                L7_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L7_Orig_Sku.requestFocus();
                L7_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L7_Orig_Sku.requestFocus();
                L7_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L7_Orig_SkuKeyPressed

    // sends input to NEBASdao.java and inserts data into corresponding fields if correct
    private void L8_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Orig_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           NEBASdao.skuInput2 = L8_Orig_Sku.getText();
           if (!L8_Orig_Sku.getText().equals(L8_First_Sku.getText())) {
               if (L8_Orig_Sku.getText().matches("[0-9]+") && L8_Orig_Sku.getText().length() < 7 ) {
           // String[] args = null;
          try {
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt2.equals("good")) {
                L8_Orig_Sku.setBackground(Color.WHITE);
                L8_Orig_Desc.setText(NEBASdao.prodName2);
                L8_Orig_Attr.setText(NEBASdao.prodAttr2);
                L8_Orig_Size.setText(NEBASdao.prodSize2);
                L8_Orig_Retail.setText(NEBASdao.prodPr);
                L8_New_Used.requestFocus();
            } else {
                L8_Orig_Sku.requestFocus();
                L8_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L8_Orig_Sku.requestFocus();
                L8_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L8_Orig_Sku.requestFocus();
                L8_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L8_Orig_SkuKeyPressed

    // sends input to NEBASdao.java and inserts data into corresponding fields if correct
    private void L9_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Orig_SkuKeyPressed
     if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            NEBASdao.skuInput2 = L9_Orig_Sku.getText();
            if (!L9_Orig_Sku.getText().equals(L9_First_Sku.getText())) {
                if (L9_Orig_Sku.getText().matches("[0-9]+") && L9_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt2.equals("good")) {
                L9_Orig_Sku.setBackground(Color.WHITE);
                L9_Orig_Desc.setText(NEBASdao.prodName2);
                L9_Orig_Attr.setText(NEBASdao.prodAttr2);
                L9_Orig_Size.setText(NEBASdao.prodSize2);
                L9_Orig_Retail.setText(NEBASdao.prodPr);
                L9_New_Used.requestFocus();
            } else {
                L9_Orig_Sku.requestFocus();
                L9_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L9_Orig_Sku.requestFocus();
                L9_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L9_Orig_Sku.requestFocus();
                L9_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L9_Orig_SkuKeyPressed

    // sends input to NEBASdao.java and inserts data into corresponding fields if correct
    private void L10_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Orig_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            NEBASdao.skuInput2 = L10_Orig_Sku.getText();
            if (!L10_Orig_Sku.getText().equals(L10_First_Sku.getText())) {
                if (L10_Orig_Sku.getText().matches("[0-9]+") && L10_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                NEBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt2.equals("good")) {
                L10_Orig_Sku.setBackground(Color.WHITE);
                L10_Orig_Desc.setText(NEBASdao.prodName2);
                L10_Orig_Attr.setText(NEBASdao.prodAttr2);
                L10_Orig_Size.setText(NEBASdao.prodSize2);
                L10_Orig_Retail.setText(NEBASdao.prodPr);
                L10_New_Used.requestFocus();
            } else {
                L10_Orig_Sku.setBackground(Color.yellow);
                L10_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L10_Orig_Sku.requestFocus();
                L10_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L10_Orig_Sku.requestFocus();
                L10_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L10_Orig_SkuKeyPressed

    // validates a single numerical input
    private void L1_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Qty_OutKeyPressed
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (L1_Qty_Out.getText().matches("[1-9]+") && L1_Qty_Out.getText().length() < 2 ) {
             L1_Orig_Sku.requestFocus();
             L1_Qty_Out.setBackground(Color.white);
        } else {
         JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
         L1_Qty_Out.setBackground(Color.yellow);
        }
    }  
    }//GEN-LAST:event_L1_Qty_OutKeyPressed

    // sends input to NEBASdao.java and inserts data into corresponding fields if correct
    private void L1_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Orig_SkuKeyPressed
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
          NEBASdao.skuInput2 = L1_Orig_Sku.getText();
          if (!L1_Orig_Sku.getText().equals(L1_First_Sku.getText())) {
              if (L1_Orig_Sku.getText().matches("[0-9]+") && L1_Orig_Sku.getText().length() < 7 ) {
          // String[] args = null;
          try {
              NEBASdao.skuInput2();
          }   catch (SQLException | ClassNotFoundException ex) {
              Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
          }
          if (NEBASdao.skuRslt2.equals("good")) {
                L1_Orig_Sku.setBackground(Color.WHITE);
                L1_Orig_Desc.setText(NEBASdao.prodName2);
                L1_Orig_Attr.setText(NEBASdao.prodAttr2);
                L1_Orig_Size.setText(NEBASdao.prodSize2);
                L1_Orig_Retail.setText(NEBASdao.prodPr);
                L1_New_Used.requestFocus();
            }  else {
                L1_Orig_Sku.requestFocus();
                L1_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Non-Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L1_Orig_Sku.requestFocus();
                L1_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L1_Orig_Sku.requestFocus();
                L1_Orig_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
          }
        }
    }//GEN-LAST:event_L1_Orig_SkuKeyPressed

    // validates a single numerical input
    private void L2_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Qty_OutKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (L2_Qty_Out.getText().matches("[1-9]+") && L2_Qty_Out.getText().length() < 2 ) {
             L2_Orig_Sku.requestFocus();
             L2_Qty_Out.setBackground(Color.WHITE);
        } else {
         JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
            L2_Qty_Out.requestFocus();
            L2_Qty_Out.setBackground(Color.yellow);
        }
    }  
    }//GEN-LAST:event_L2_Qty_OutKeyPressed

// validates a single numerical input
    private void L3_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Qty_OutKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (L3_Qty_Out.getText().matches("[1-9]+") && L3_Qty_Out.getText().length() < 2 ) {
             L3_Orig_Sku.requestFocus();
             L3_Qty_Out.setBackground(Color.WHITE);
        } else {
            JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
            L3_Qty_Out.requestFocus();
            L3_Qty_Out.setBackground(Color.yellow);
        }
    }  
    }//GEN-LAST:event_L3_Qty_OutKeyPressed

    // validates a single numerical input
    private void L4_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Qty_OutKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (L4_Qty_Out.getText().matches("[1-9]+") && L4_Qty_Out.getText().length() < 2 ) {
             L4_Orig_Sku.requestFocus();
             L4_Qty_Out.setBackground(Color.WHITE);
        } else {
            JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
            L4_Qty_Out.requestFocus();
            L4_Qty_Out.setBackground(Color.yellow);
        }
    }  
    }//GEN-LAST:event_L4_Qty_OutKeyPressed

    // validates a single numerical input
    private void L5_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Qty_OutKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (L5_Qty_Out.getText().matches("[1-9]+") && L5_Qty_Out.getText().length() < 2 ) {
             L5_Orig_Sku.requestFocus();
             L5_Qty_Out.setBackground(Color.WHITE);
      } else {
         JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
         L5_Qty_Out.requestFocus();
         L5_Qty_Out.setBackground(Color.yellow);
      }
         }  
    }//GEN-LAST:event_L5_Qty_OutKeyPressed

    // validates a single numerical input
    private void L6_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Qty_OutKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (L6_Qty_Out.getText().matches("[1-9]+") && L6_Qty_Out.getText().length() < 2 ) {
             L6_Orig_Sku.requestFocus();
             L6_Qty_Out.setBackground(Color.WHITE);
      } else {
         JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
         L6_Qty_Out.requestFocus();
         L6_Qty_Out.setBackground(Color.yellow);
      }
         }  
    }//GEN-LAST:event_L6_Qty_OutKeyPressed

    // validates a single numerical input
    private void L7_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Qty_OutKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (L7_Qty_Out.getText().matches("[1-9]+") && L7_Qty_Out.getText().length() < 2 ) {
             L7_Orig_Sku.requestFocus();
             L7_Qty_Out.setBackground(Color.WHITE);
      } else {
         JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
         L7_Qty_Out.requestFocus();
         L7_Qty_Out.setBackground(Color.yellow);
      }
         }  
    }//GEN-LAST:event_L7_Qty_OutKeyPressed

    // validates a single numerical input
    private void L8_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Qty_OutKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (L8_Qty_Out.getText().matches("[1-9]+") && L8_Qty_Out.getText().length() < 2 ) {
             L8_Orig_Sku.requestFocus();
             L8_Qty_Out.setBackground(Color.WHITE);
      } else {
         JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
         L8_Qty_Out.requestFocus();
         L8_Qty_Out.setBackground(Color.yellow);
      }
         }  
    }//GEN-LAST:event_L8_Qty_OutKeyPressed

    // validates a single numerical input
    private void L9_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Qty_OutKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (L9_Qty_Out.getText().matches("[1-9]+") && L9_Qty_Out.getText().length() < 2 ) {
             L9_Orig_Sku.requestFocus();
             L9_Qty_Out.setBackground(Color.WHITE);
      } else {
         JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
         L9_Qty_Out.requestFocus();
         L9_Qty_Out.setBackground(Color.yellow);
      }
         }  
    }//GEN-LAST:event_L9_Qty_OutKeyPressed

    // validates a single numerical input
    private void L10_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Qty_OutKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (L10_Qty_Out.getText().matches("[1-9]+") && L10_Qty_Out.getText().length() < 2 ) {
             L10_Orig_Sku.requestFocus();
             L10_Qty_Out.setBackground(Color.WHITE);
      } else {
         JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
         L10_Qty_Out.requestFocus();
         L10_Qty_Out.setBackground(Color.yellow);
      }
         }  
    }//GEN-LAST:event_L10_Qty_OutKeyPressed

    private void L1_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_New_UsedKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           L1_Reason_DropDown.requestFocus();
       }
    }//GEN-LAST:event_L1_New_UsedKeyPressed

    private void L1_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Reason_DropDownKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           L1_Desc_Damage.requestFocus();
       }
    }//GEN-LAST:event_L1_Reason_DropDownKeyPressed

    private void L1_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Desc_DamageKeyPressed
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
          if (L1_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L1_Desc_Damage.setBackground(Color.WHITE);
                L1_Done_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage Error", JOptionPane.ERROR_MESSAGE);
        	L1_Desc_Damage.setBackground(Color.yellow);
        }           
       }
    }//GEN-LAST:event_L1_Desc_DamageKeyPressed

    private void L2_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_New_UsedKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           L2_Reason_DropDown.requestFocus();
       }
    }//GEN-LAST:event_L2_New_UsedKeyPressed

    private void L2_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Reason_DropDownKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           L2_Desc_Damage.requestFocus();
       }
    }//GEN-LAST:event_L2_Reason_DropDownKeyPressed

    private void L2_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Desc_DamageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L2_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L2_Desc_Damage.setBackground(Color.WHITE);
                L2_Done_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage Error", JOptionPane.ERROR_MESSAGE);
        	L2_Desc_Damage.setBackground(Color.yellow);
        }           
       }
    }//GEN-LAST:event_L2_Desc_DamageKeyPressed

    private void L3_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_New_UsedKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           L3_Reason_DropDown.requestFocus();
       }
    }//GEN-LAST:event_L3_New_UsedKeyPressed

    private void L3_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Reason_DropDownKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           L3_Desc_Damage.requestFocus();
       }
    }//GEN-LAST:event_L3_Reason_DropDownKeyPressed

    private void L3_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Desc_DamageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           if (L3_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L3_Desc_Damage.setBackground(Color.WHITE);
                L3_Done_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage Error", JOptionPane.ERROR_MESSAGE);
        	L3_Desc_Damage.setBackground(Color.yellow);
        }           
       }
    }//GEN-LAST:event_L3_Desc_DamageKeyPressed

    private void L4_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_New_UsedKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L4_Reason_DropDown.requestFocus();
        }
    }//GEN-LAST:event_L4_New_UsedKeyPressed

    private void L4_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Reason_DropDownKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L4_Desc_Damage.requestFocus();
        }
    }//GEN-LAST:event_L4_Reason_DropDownKeyPressed

    private void L4_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Desc_DamageKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           if (L4_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L4_Desc_Damage.setBackground(Color.WHITE);
                L4_Done_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage Error", JOptionPane.ERROR_MESSAGE);
        	L4_Desc_Damage.setBackground(Color.yellow);
        }           
       }
    }//GEN-LAST:event_L4_Desc_DamageKeyPressed

    private void L5_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_New_UsedKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L5_Reason_DropDown.requestFocus();
        }
    }//GEN-LAST:event_L5_New_UsedKeyPressed

    private void L5_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Reason_DropDownKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L5_Desc_Damage.requestFocus();
        }
    }//GEN-LAST:event_L5_Reason_DropDownKeyPressed

    private void L5_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Desc_DamageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {            
	if (L5_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L5_Desc_Damage.setBackground(Color.WHITE);
                L5_Done_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage Error", JOptionPane.ERROR_MESSAGE);
        	L5_Desc_Damage.setBackground(Color.yellow);
        }           
       }
    }//GEN-LAST:event_L5_Desc_DamageKeyPressed

    private void L6_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_New_UsedKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L6_Reason_DropDown.requestFocus();
        }
    }//GEN-LAST:event_L6_New_UsedKeyPressed

    private void L6_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Reason_DropDownKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L6_Desc_Damage.requestFocus();
        }
    }//GEN-LAST:event_L6_Reason_DropDownKeyPressed

    private void L6_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Desc_DamageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L6_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L6_Desc_Damage.setBackground(Color.WHITE);
                L6_Done_ChkBox.requestFocus();
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage Error", JOptionPane.ERROR_MESSAGE);
        	L6_Desc_Damage.setBackground(Color.yellow);
            }
        }
    }//GEN-LAST:event_L6_Desc_DamageKeyPressed

    private void L7_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_New_UsedKeyPressed
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           L7_Reason_DropDown.requestFocus();
       }
    }//GEN-LAST:event_L7_New_UsedKeyPressed

    private void L7_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Reason_DropDownKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           L7_Desc_Damage.requestFocus();
       }
    }//GEN-LAST:event_L7_Reason_DropDownKeyPressed

    private void L7_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Desc_DamageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           if (L7_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L7_Desc_Damage.setBackground(Color.WHITE);
                L7_Done_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage Error", JOptionPane.ERROR_MESSAGE);
        	L7_Desc_Damage.setBackground(Color.yellow);
        }           
    }
    }//GEN-LAST:event_L7_Desc_DamageKeyPressed

    private void L8_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_New_UsedKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           L8_Reason_DropDown.requestFocus();
       }
    }//GEN-LAST:event_L8_New_UsedKeyPressed

    private void L8_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Reason_DropDownKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           L8_Desc_Damage.requestFocus();
       }
    }//GEN-LAST:event_L8_Reason_DropDownKeyPressed

    private void L8_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Desc_DamageKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L8_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L8_Desc_Damage.setBackground(Color.WHITE);
                L8_Done_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage Error", JOptionPane.ERROR_MESSAGE);
        	L8_Desc_Damage.setBackground(Color.yellow);
        }           
    }
    }//GEN-LAST:event_L8_Desc_DamageKeyPressed

    private void L9_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_New_UsedKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           L9_Reason_DropDown.requestFocus();
       }
    }//GEN-LAST:event_L9_New_UsedKeyPressed

    private void L9_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Reason_DropDownKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L9_Desc_Damage.requestFocus();
        }
    }//GEN-LAST:event_L9_Reason_DropDownKeyPressed

    private void L9_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Desc_DamageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           if (L9_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L9_Desc_Damage.setBackground(Color.WHITE);
                L9_Done_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage Error", JOptionPane.ERROR_MESSAGE);
        	L9_Desc_Damage.setBackground(Color.yellow);
        }           
       }
    }//GEN-LAST:event_L9_Desc_DamageKeyPressed

    private void L10_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_New_UsedKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           L10_Reason_DropDown.requestFocus();
       }
    }//GEN-LAST:event_L10_New_UsedKeyPressed

    private void L10_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Reason_DropDownKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           L10_Desc_Damage.requestFocus();
        }
    }//GEN-LAST:event_L10_Reason_DropDownKeyPressed

    private void L10_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Desc_DamageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L10_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L10_Desc_Damage.setBackground(Color.WHITE);
                L10_Done_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage Error", JOptionPane.ERROR_MESSAGE);
        	L10_Desc_Damage.setBackground(Color.yellow);
        }           
       }
    }//GEN-LAST:event_L10_Desc_DamageKeyPressed

    // change status and save line data in database table archive
    private void RdyforExportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdyforExportBtnActionPerformed
    int response = JOptionPane.showConfirmDialog(null, "Do you want to Confirm?", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");      
    } else if (response == JOptionPane.YES_OPTION) {
      System.out.println("Yes button clicked");      
        try {
                NEBASdao.FormReadyForExport();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                NEBASdao.LineArchive();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(frame, "Form is Ready for Export");
            InvAdj_Admin.Rpnt();
            this.dispose();
    } else if (response == JOptionPane.CLOSED_OPTION) {
      System.out.println("JOptionPane closed");
    }
    }//GEN-LAST:event_RdyforExportBtnActionPerformed

    private void DL_Print_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DL_Print_BtnActionPerformed
        String[] args = null;
        PrintForms.formType = ("NEBAS");
        PrintForms.main(args);
    }//GEN-LAST:event_DL_Print_BtnActionPerformed

    private void L1_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L1_First_SkuFocusLost
        NEBASdao.skuInput = L1_First_Sku.getText();
            if (L1_First_Sku.getText().matches("[0-9]+") && L1_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L1_First_Sku.setBackground(Color.WHITE);
                L1_First_Desc.setText(NEBASdao.prodName);
             //   L1_Qty_Out.requestFocus();            
            }
            }
    }//GEN-LAST:event_L1_First_SkuFocusLost

    private void L1_Qty_OutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L1_Qty_OutFocusLost
        if (L1_Qty_Out.getText().matches("[1-9]+") && L1_Qty_Out.getText().length() < 2 ) {
                    L1_Qty_Out.setBackground(Color.WHITE);
                   // L1_Reason_DropDown.requestFocus();
                }
    }//GEN-LAST:event_L1_Qty_OutFocusLost

    private void L1_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L1_Orig_SkuFocusLost
        NEBASdao.skuInput2 = L1_Orig_Sku.getText();
        if (!L1_Orig_Sku.getText().equals(L1_First_Sku.getText())) {
              if (L1_Orig_Sku.getText().matches("[0-9]+") && L1_Orig_Sku.getText().length() < 7 ) {
          // String[] args = null;
            try {
              NEBASdao.skuInput2();
            }   catch (SQLException | ClassNotFoundException ex) {
              Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt2.equals("good")) {
                L1_Orig_Sku.setBackground(Color.WHITE);
                L1_Orig_Desc.setText(NEBASdao.prodName2);
                L1_Orig_Attr.setText(NEBASdao.prodAttr2);
                L1_Orig_Size.setText(NEBASdao.prodSize2);
                L1_Orig_Retail.setText(NEBASdao.prodPr);
                jPanel6.scrollRectToVisible(L1_Timestamp.getBounds(null));
            }       
        }
    }
    }//GEN-LAST:event_L1_Orig_SkuFocusLost

    private void L1_Desc_DamageFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L1_Desc_DamageFocusLost
        if (L1_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
            L1_Desc_Damage.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_L1_Desc_DamageFocusLost

    private void L2_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L2_First_SkuFocusLost
        NEBASdao.skuInput = L2_First_Sku.getText();
            if (L2_First_Sku.getText().matches("[0-9]+") && L2_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L2_First_Sku.setBackground(Color.WHITE);
                L2_First_Desc.setText(NEBASdao.prodName);
             //   L1_Qty_Out.requestFocus();            
            }
            }
    }//GEN-LAST:event_L2_First_SkuFocusLost

    private void L2_Qty_OutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L2_Qty_OutFocusLost
       if (L2_Qty_Out.getText().matches("[1-9]+") && L2_Qty_Out.getText().length() < 2 ) {
                    L2_Qty_Out.setBackground(Color.WHITE);
                   // L1_Reason_DropDown.requestFocus();
                }
    }//GEN-LAST:event_L2_Qty_OutFocusLost

    private void L2_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L2_Orig_SkuFocusLost
        NEBASdao.skuInput2 = L2_Orig_Sku.getText();
        if (!L2_Orig_Sku.getText().equals(L2_First_Sku.getText())) {
              if (L2_Orig_Sku.getText().matches("[0-9]+") && L2_Orig_Sku.getText().length() < 7 ) {
          // String[] args = null;
            try {
              NEBASdao.skuInput2();
            }   catch (SQLException | ClassNotFoundException ex) {
              Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt2.equals("good")) {
                L2_Orig_Sku.setBackground(Color.WHITE);
                L2_Orig_Desc.setText(NEBASdao.prodName2);
                L2_Orig_Attr.setText(NEBASdao.prodAttr2);
                L2_Orig_Size.setText(NEBASdao.prodSize2);
                L2_Orig_Retail.setText(NEBASdao.prodPr);
                jPanel6.scrollRectToVisible(L2_Timestamp.getBounds(null));
            }       
        }
    }
    }//GEN-LAST:event_L2_Orig_SkuFocusLost

    private void L3_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L3_First_SkuFocusLost
        NEBASdao.skuInput = L3_First_Sku.getText();
            if (L3_First_Sku.getText().matches("[0-9]+") && L3_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L3_First_Sku.setBackground(Color.WHITE);
                L3_First_Desc.setText(NEBASdao.prodName);
             //   L1_Qty_Out.requestFocus();            
            }
            }
    }//GEN-LAST:event_L3_First_SkuFocusLost

    private void L3_Qty_OutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L3_Qty_OutFocusLost
	if (L3_Qty_Out.getText().matches("[1-9]+") && L3_Qty_Out.getText().length() < 2 ) {
                    L3_Qty_Out.setBackground(Color.WHITE);
                   // L1_Reason_DropDown.requestFocus();
                }
    }//GEN-LAST:event_L3_Qty_OutFocusLost

    private void L3_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L3_Orig_SkuFocusLost
        NEBASdao.skuInput2 = L3_Orig_Sku.getText();
        if (!L3_Orig_Sku.getText().equals(L3_First_Sku.getText())) {
              if (L3_Orig_Sku.getText().matches("[0-9]+") && L3_Orig_Sku.getText().length() < 7 ) {
          // String[] args = null;
            try {
              NEBASdao.skuInput2();
            }   catch (SQLException | ClassNotFoundException ex) {
              Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt2.equals("good")) {
                L3_Orig_Sku.setBackground(Color.WHITE);
                L3_Orig_Desc.setText(NEBASdao.prodName2);
                L3_Orig_Attr.setText(NEBASdao.prodAttr2);
                L3_Orig_Size.setText(NEBASdao.prodSize2);
                L3_Orig_Retail.setText(NEBASdao.prodPr);
                jPanel6.scrollRectToVisible(L3_Timestamp.getBounds(null));
            }       
        }
    }
    }//GEN-LAST:event_L3_Orig_SkuFocusLost

    private void L4_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L4_First_SkuFocusLost
        NEBASdao.skuInput = L4_First_Sku.getText();
            if (L4_First_Sku.getText().matches("[0-9]+") && L4_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L4_First_Sku.setBackground(Color.WHITE);
                L4_First_Desc.setText(NEBASdao.prodName);
             //   L1_Qty_Out.requestFocus();            
            }
            }
    }//GEN-LAST:event_L4_First_SkuFocusLost

    private void L4_Qty_OutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L4_Qty_OutFocusLost
        if (L4_Qty_Out.getText().matches("[1-9]+") && L4_Qty_Out.getText().length() < 2 ) {
                    L4_Qty_Out.setBackground(Color.WHITE);
                   // L1_Reason_DropDown.requestFocus();
                }
    }//GEN-LAST:event_L4_Qty_OutFocusLost

    private void L4_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L4_Orig_SkuFocusLost
        NEBASdao.skuInput2 = L4_Orig_Sku.getText();
        if (!L4_Orig_Sku.getText().equals(L4_First_Sku.getText())) {
              if (L4_Orig_Sku.getText().matches("[0-9]+") && L4_Orig_Sku.getText().length() < 7 ) {
          // String[] args = null;
            try {
              NEBASdao.skuInput2();
            }   catch (SQLException | ClassNotFoundException ex) {
              Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt2.equals("good")) {
                L4_Orig_Sku.setBackground(Color.WHITE);
                L4_Orig_Desc.setText(NEBASdao.prodName2);
                L4_Orig_Attr.setText(NEBASdao.prodAttr2);
                L4_Orig_Size.setText(NEBASdao.prodSize2);
                L4_Orig_Retail.setText(NEBASdao.prodPr);
                jPanel6.scrollRectToVisible(L4_Timestamp.getBounds(null));
            }       
        }
    }
    }//GEN-LAST:event_L4_Orig_SkuFocusLost

    private void L5_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L5_First_SkuFocusLost
        NEBASdao.skuInput = L5_First_Sku.getText();
            if (L5_First_Sku.getText().matches("[0-9]+") && L5_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L5_First_Sku.setBackground(Color.WHITE);
                L5_First_Desc.setText(NEBASdao.prodName);
             //   L1_Qty_Out.requestFocus();            
            }
            }
    }//GEN-LAST:event_L5_First_SkuFocusLost

    private void L5_Qty_OutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L5_Qty_OutFocusLost
        if (L5_Qty_Out.getText().matches("[1-9]+") && L5_Qty_Out.getText().length() < 2 ) {
                    L5_Qty_Out.setBackground(Color.WHITE);
                   // L1_Reason_DropDown.requestFocus();
                }
    }//GEN-LAST:event_L5_Qty_OutFocusLost

    private void L5_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L5_Orig_SkuFocusLost
        NEBASdao.skuInput2 = L5_Orig_Sku.getText();
        if (!L5_Orig_Sku.getText().equals(L5_First_Sku.getText())) {
              if (L5_Orig_Sku.getText().matches("[0-9]+") && L5_Orig_Sku.getText().length() < 7 ) {
          // String[] args = null;
            try {
              NEBASdao.skuInput2();
            }   catch (SQLException | ClassNotFoundException ex) {
              Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt2.equals("good")) {
                L5_Orig_Sku.setBackground(Color.WHITE);
                L5_Orig_Desc.setText(NEBASdao.prodName2);
                L5_Orig_Attr.setText(NEBASdao.prodAttr2);
                L5_Orig_Size.setText(NEBASdao.prodSize2);
                L5_Orig_Retail.setText(NEBASdao.prodPr);
                jPanel6.scrollRectToVisible(L5_Timestamp.getBounds(null));
            }       
        }
    }
    }//GEN-LAST:event_L5_Orig_SkuFocusLost

    private void L6_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L6_First_SkuFocusLost
        NEBASdao.skuInput = L6_First_Sku.getText();
            if (L6_First_Sku.getText().matches("[0-9]+") && L6_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L6_First_Sku.setBackground(Color.WHITE);
                L6_First_Desc.setText(NEBASdao.prodName);
             //   L1_Qty_Out.requestFocus();            
            }
            }
    }//GEN-LAST:event_L6_First_SkuFocusLost

    private void L6_Qty_OutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L6_Qty_OutFocusLost
        if (L6_Qty_Out.getText().matches("[1-9]+") && L6_Qty_Out.getText().length() < 2 ) {
                    L6_Qty_Out.setBackground(Color.WHITE);
                   // L1_Reason_DropDown.requestFocus();
        }
    }//GEN-LAST:event_L6_Qty_OutFocusLost

    private void L6_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L6_Orig_SkuFocusLost
        NEBASdao.skuInput2 = L6_Orig_Sku.getText();
        if (!L6_Orig_Sku.getText().equals(L6_First_Sku.getText())) {
              if (L6_Orig_Sku.getText().matches("[0-9]+") && L6_Orig_Sku.getText().length() < 7 ) {
          // String[] args = null;
            try {
              NEBASdao.skuInput2();
            }   catch (SQLException | ClassNotFoundException ex) {
              Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt2.equals("good")) {
                L6_Orig_Sku.setBackground(Color.WHITE);
                L6_Orig_Desc.setText(NEBASdao.prodName2);
                L6_Orig_Attr.setText(NEBASdao.prodAttr2);
                L6_Orig_Size.setText(NEBASdao.prodSize2);
                L6_Orig_Retail.setText(NEBASdao.prodPr);
                jPanel6.scrollRectToVisible(L6_Timestamp.getBounds(null));
            }       
        }
    }
    }//GEN-LAST:event_L6_Orig_SkuFocusLost

    private void L7_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L7_First_SkuFocusLost
        NEBASdao.skuInput = L7_First_Sku.getText();
            if (L7_First_Sku.getText().matches("[0-9]+") && L7_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L7_First_Sku.setBackground(Color.WHITE);
                L7_First_Desc.setText(NEBASdao.prodName);
             //   L1_Qty_Out.requestFocus();            
            }
            }
    }//GEN-LAST:event_L7_First_SkuFocusLost

    private void L7_Qty_OutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L7_Qty_OutFocusLost
        if (L7_Qty_Out.getText().matches("[1-9]+") && L7_Qty_Out.getText().length() < 2 ) {
                    L7_Qty_Out.setBackground(Color.WHITE);
                   // L1_Reason_DropDown.requestFocus();
                }
    }//GEN-LAST:event_L7_Qty_OutFocusLost

    private void L7_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L7_Orig_SkuFocusLost
        NEBASdao.skuInput2 = L7_Orig_Sku.getText();
        if (!L7_Orig_Sku.getText().equals(L7_First_Sku.getText())) {
              if (L7_Orig_Sku.getText().matches("[0-9]+") && L7_Orig_Sku.getText().length() < 7 ) {
          // String[] args = null;
            try {
              NEBASdao.skuInput2();
            }   catch (SQLException | ClassNotFoundException ex) {
              Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt2.equals("good")) {
                L7_Orig_Sku.setBackground(Color.WHITE);
                L7_Orig_Desc.setText(NEBASdao.prodName2);
                L7_Orig_Attr.setText(NEBASdao.prodAttr2);
                L7_Orig_Size.setText(NEBASdao.prodSize2);
                L7_Orig_Retail.setText(NEBASdao.prodPr);
                jPanel6.scrollRectToVisible(L7_Timestamp.getBounds(null));
            }       
        }
    }
    }//GEN-LAST:event_L7_Orig_SkuFocusLost

    private void L8_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L8_First_SkuFocusLost
        NEBASdao.skuInput = L8_First_Sku.getText();
            if (L8_First_Sku.getText().matches("[0-9]+") && L8_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L8_First_Sku.setBackground(Color.WHITE);
                L8_First_Desc.setText(NEBASdao.prodName);
             //   L1_Qty_Out.requestFocus();            
            }
            }
    }//GEN-LAST:event_L8_First_SkuFocusLost

    private void L8_Qty_OutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L8_Qty_OutFocusLost
        if (L8_Qty_Out.getText().matches("[1-9]+") && L8_Qty_Out.getText().length() < 2 ) {
                    L8_Qty_Out.setBackground(Color.WHITE);
                   // L1_Reason_DropDown.requestFocus();
                } 
    }//GEN-LAST:event_L8_Qty_OutFocusLost

    private void L8_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L8_Orig_SkuFocusLost
        NEBASdao.skuInput2 = L8_Orig_Sku.getText();
        if (!L8_Orig_Sku.getText().equals(L8_First_Sku.getText())) {
              if (L8_Orig_Sku.getText().matches("[0-9]+") && L8_Orig_Sku.getText().length() < 7 ) {
          // String[] args = null;
            try {
              NEBASdao.skuInput2();
            }   catch (SQLException | ClassNotFoundException ex) {
              Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt2.equals("good")) {
                L8_Orig_Sku.setBackground(Color.WHITE);
                L8_Orig_Desc.setText(NEBASdao.prodName2);
                L8_Orig_Attr.setText(NEBASdao.prodAttr2);
                L8_Orig_Size.setText(NEBASdao.prodSize2);
                L8_Orig_Retail.setText(NEBASdao.prodPr);
                jPanel6.scrollRectToVisible(L8_Timestamp.getBounds(null));
            }       
        }
    }
    }//GEN-LAST:event_L8_Orig_SkuFocusLost

    private void L9_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L9_First_SkuFocusLost
        NEBASdao.skuInput = L9_First_Sku.getText();
            if (L9_First_Sku.getText().matches("[0-9]+") && L9_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L9_First_Sku.setBackground(Color.WHITE);
                L9_First_Desc.setText(NEBASdao.prodName);
             //   L1_Qty_Out.requestFocus();            
            }
            }
    }//GEN-LAST:event_L9_First_SkuFocusLost

    private void L9_Qty_OutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L9_Qty_OutFocusLost
        if (L9_Qty_Out.getText().matches("[1-9]+") && L9_Qty_Out.getText().length() < 2 ) {
                    L9_Qty_Out.setBackground(Color.WHITE);
                   // L1_Reason_DropDown.requestFocus();
                }
    }//GEN-LAST:event_L9_Qty_OutFocusLost

    private void L9_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L9_Orig_SkuFocusLost
        NEBASdao.skuInput2 = L9_Orig_Sku.getText();
        if (!L9_Orig_Sku.getText().equals(L9_First_Sku.getText())) {
              if (L9_Orig_Sku.getText().matches("[0-9]+") && L9_Orig_Sku.getText().length() < 7 ) {
          // String[] args = null;
            try {
              NEBASdao.skuInput2();
            }   catch (SQLException | ClassNotFoundException ex) {
              Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt2.equals("good")) {
                L9_Orig_Sku.setBackground(Color.WHITE);
                L9_Orig_Desc.setText(NEBASdao.prodName2);
                L9_Orig_Attr.setText(NEBASdao.prodAttr2);
                L9_Orig_Size.setText(NEBASdao.prodSize2);
                L9_Orig_Retail.setText(NEBASdao.prodPr);
                jPanel6.scrollRectToVisible(L9_Timestamp.getBounds(null));
            }       
        }
    }
    }//GEN-LAST:event_L9_Orig_SkuFocusLost

    private void L10_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L10_First_SkuFocusLost
        NEBASdao.skuInput = L10_First_Sku.getText();
            if (L10_First_Sku.getText().matches("[0-9]+") && L10_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                NEBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt.equals("good")) {
                L10_First_Sku.setBackground(Color.WHITE);
                L10_First_Desc.setText(NEBASdao.prodName);
             //   L1_Qty_Out.requestFocus();            
            }
            }
    }//GEN-LAST:event_L10_First_SkuFocusLost

    private void L10_Qty_OutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L10_Qty_OutFocusLost
        if (L10_Qty_Out.getText().matches("[1-9]+") && L10_Qty_Out.getText().length() < 2 ) {
                    L10_Qty_Out.setBackground(Color.WHITE);
                   // L1_Reason_DropDown.requestFocus();
                }
    }//GEN-LAST:event_L10_Qty_OutFocusLost

    private void L10_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L10_Orig_SkuFocusLost
        NEBASdao.skuInput2 = L10_Orig_Sku.getText();
        if (!L10_Orig_Sku.getText().equals(L10_First_Sku.getText())) {
              if (L10_Orig_Sku.getText().matches("[0-9]+") && L10_Orig_Sku.getText().length() < 7 ) {
          // String[] args = null;
            try {
              NEBASdao.skuInput2();
            }   catch (SQLException | ClassNotFoundException ex) {
              Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (NEBASdao.skuRslt2.equals("good")) {
                L10_Orig_Sku.setBackground(Color.WHITE);
                L10_Orig_Desc.setText(NEBASdao.prodName2);
                L10_Orig_Attr.setText(NEBASdao.prodAttr2);
                L10_Orig_Size.setText(NEBASdao.prodSize2);
                L10_Orig_Retail.setText(NEBASdao.prodPr);
                jPanel6.scrollRectToVisible(L10_Timestamp.getBounds(null));
            }       
        }
    }
    }//GEN-LAST:event_L10_Orig_SkuFocusLost

    private void CLine_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLine_ButtonActionPerformed
        Toolkit.getDefaultToolkit().beep();
        int response = JOptionPane.showConfirmDialog(null, "Do you want to Clear this Line?", "Confirm",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.NO_OPTION) {
                CLine_textfield.setText("");
                } else if (response == JOptionPane.YES_OPTION) {
                    System.out.println("Yes button clicked");
                    String[] args = null;
             switch (CLine_textfield.getText()) {
                 case "1":
                     try {
                        NEBASdao.ClearLine1();
                        L1_First_Sku.setText("");
                        L1_Qty_Out.setText("");
                        L1_First_Desc.setText("");
                        L1_Orig_Sku.setText("");
                        L1_Orig_Desc.setText("");
                        L1_Orig_Attr.setText("");
                        L1_Orig_Size.setText("");
                        L1_Orig_Retail.setText("");
                        L1_New_Used.setSelectedItem("New");
                        L1_Reason_DropDown.setSelectedItem("");                    
                        L1_Desc_Damage.setText(""); 
                        L1_Done_ChkBox.setSelected(false);
                        L1_Timestamp.setText("");
                        L1_First_Sku.setEnabled(true);
                        L1_Qty_Out.setEnabled(true);
                        L1_First_Desc.setEnabled(true);
                        L1_Orig_Sku.setEnabled(true);
                        L1_Orig_Desc.setEnabled(true);
                        L1_Orig_Attr.setEnabled(true);
                        L1_Orig_Size.setEnabled(true);
                        L1_Orig_Retail.setEnabled(true);
                        L1_New_Used.setEnabled(true);
                        L1_Reason_DropDown.setEnabled(true);
                        L1_Desc_Damage.setEnabled(true);
                        L1_Done_ChkBox.setEnabled(true);
                        L1_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     }    break;
                 case "2":
                       try {
                        NEBASdao.ClearLine2();
                        L2_First_Sku.setText("");
                        L2_Qty_Out.setText("");
                        L2_First_Desc.setText("");
                        L2_Orig_Sku.setText("");
                        L2_Orig_Desc.setText("");
                        L2_Orig_Attr.setText("");
                        L2_Orig_Size.setText("");
                        L2_Orig_Retail.setText("");
                        L2_New_Used.setSelectedItem("New");
                        L2_Reason_DropDown.setSelectedItem("");                    
                        L2_Desc_Damage.setText(""); 
                        L2_Done_ChkBox.setSelected(false);
                        L2_Timestamp.setText("");
                        L2_First_Sku.setEnabled(true);
                        L2_Qty_Out.setEnabled(true);
                        L2_First_Desc.setEnabled(true);
                        L2_Orig_Sku.setEnabled(true);
                        L2_Orig_Desc.setEnabled(true);
                        L2_Orig_Attr.setEnabled(true);
                        L2_Orig_Size.setEnabled(true);
                        L2_Orig_Retail.setEnabled(true);
                        L2_New_Used.setEnabled(true);
                        L2_Reason_DropDown.setEnabled(true);
                        L2_Desc_Damage.setEnabled(true);
                        L2_Done_ChkBox.setEnabled(true);
                        L2_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     } break;
                 case "3":
                     try {
                        NEBASdao.ClearLine3();
                        L3_First_Sku.setText("");
                        L3_Qty_Out.setText("");
                        L3_First_Desc.setText("");
                        L3_Orig_Sku.setText("");
                        L3_Orig_Desc.setText("");
                        L3_Orig_Attr.setText("");
                        L3_Orig_Size.setText("");
                        L3_Orig_Retail.setText("");
                        L3_New_Used.setSelectedItem("New");
                        L3_Reason_DropDown.setSelectedItem("");                    
                        L3_Desc_Damage.setText(""); 
                        L3_Done_ChkBox.setSelected(false);
                        L3_Timestamp.setText("");
                        L3_First_Sku.setEnabled(true);
                        L3_Qty_Out.setEnabled(true);
                        L3_First_Desc.setEnabled(true);
                        L3_Orig_Sku.setEnabled(true);
                        L3_Orig_Desc.setEnabled(true);
                        L3_Orig_Attr.setEnabled(true);
                        L3_Orig_Size.setEnabled(true);
                        L3_Orig_Retail.setEnabled(true);
                        L3_New_Used.setEnabled(true);
                        L3_Reason_DropDown.setEnabled(true);
                        L3_Desc_Damage.setEnabled(true);
                        L3_Done_ChkBox.setEnabled(true);
                        L3_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     }    break;
                 case "4":
                     try {
                        NEBASdao.ClearLine4();
                        L4_First_Sku.setText("");
                        L4_Qty_Out.setText("");
                        L4_First_Desc.setText("");
                        L4_Orig_Sku.setText("");
                        L4_Orig_Desc.setText("");
                        L4_Orig_Attr.setText("");
                        L4_Orig_Size.setText("");
                        L4_Orig_Retail.setText("");
                        L4_New_Used.setSelectedItem("New");
                        L4_Reason_DropDown.setSelectedItem("");                    
                        L4_Desc_Damage.setText(""); 
                        L4_Done_ChkBox.setSelected(false);
                        L4_Timestamp.setText("");
                        L4_First_Sku.setEnabled(true);
                        L4_Qty_Out.setEnabled(true);
                        L4_First_Desc.setEnabled(true);
                        L4_Orig_Sku.setEnabled(true);
                        L4_Orig_Desc.setEnabled(true);
                        L4_Orig_Attr.setEnabled(true);
                        L4_Orig_Size.setEnabled(true);
                        L4_Orig_Retail.setEnabled(true);
                        L4_New_Used.setEnabled(true);
                        L4_Reason_DropDown.setEnabled(true);
                        L4_Desc_Damage.setEnabled(true);
                        L4_Done_ChkBox.setEnabled(true);
                        L4_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     }    break;
                 case "5":
                     try {
                        NEBASdao.ClearLine5();
                        L5_First_Sku.setText("");
                        L5_Qty_Out.setText("");
                        L5_First_Desc.setText("");
                        L5_Orig_Sku.setText("");
                        L5_Orig_Desc.setText("");
                        L5_Orig_Attr.setText("");
                        L5_Orig_Size.setText("");
                        L5_Orig_Retail.setText("");
                        L5_New_Used.setSelectedItem("New");
                        L5_Reason_DropDown.setSelectedItem("");                    
                        L5_Desc_Damage.setText(""); 
                        L5_Done_ChkBox.setSelected(false);
                        L5_Timestamp.setText("");
                        L5_First_Sku.setEnabled(true);
                        L5_Qty_Out.setEnabled(true);
                        L5_First_Desc.setEnabled(true);
                        L5_Orig_Sku.setEnabled(true);
                        L5_Orig_Desc.setEnabled(true);
                        L5_Orig_Attr.setEnabled(true);
                        L5_Orig_Size.setEnabled(true);
                        L5_Orig_Retail.setEnabled(true);
                        L5_New_Used.setEnabled(true);
                        L5_Reason_DropDown.setEnabled(true);
                        L5_Desc_Damage.setEnabled(true);
                        L5_Done_ChkBox.setEnabled(true);
                        L5_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     }  break;
                 case "6":
                     try {
                        NEBASdao.ClearLine6();
                        L6_First_Sku.setText("");
                        L6_Qty_Out.setText("");
                        L6_First_Desc.setText("");
                        L6_Orig_Sku.setText("");
                        L6_Orig_Desc.setText("");
                        L6_Orig_Attr.setText("");
                        L6_Orig_Size.setText("");
                        L6_Orig_Retail.setText("");
                        L6_New_Used.setSelectedItem("New");
                        L6_Reason_DropDown.setSelectedItem("");                    
                        L6_Desc_Damage.setText(""); 
                        L6_Done_ChkBox.setSelected(false);
                        L6_Timestamp.setText("");
                        L6_First_Sku.setEnabled(true);
                        L6_Qty_Out.setEnabled(true);
                        L6_First_Desc.setEnabled(true);
                        L6_Orig_Sku.setEnabled(true);
                        L6_Orig_Desc.setEnabled(true);
                        L6_Orig_Attr.setEnabled(true);
                        L6_Orig_Size.setEnabled(true);
                        L6_Orig_Retail.setEnabled(true);
                        L6_New_Used.setEnabled(true);
                        L6_Reason_DropDown.setEnabled(true);
                        L6_Desc_Damage.setEnabled(true);
                        L6_Done_ChkBox.setEnabled(true);
                        L6_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     }   break;
                 case "7":
                     try {
                        NEBASdao.ClearLine7();
                        L7_First_Sku.setText("");
                        L7_Qty_Out.setText("");
                        L7_First_Desc.setText("");
                        L7_Orig_Sku.setText("");
                        L7_Orig_Desc.setText("");
                        L7_Orig_Attr.setText("");
                        L7_Orig_Size.setText("");
                        L7_Orig_Retail.setText("");
                        L7_New_Used.setSelectedItem("New");
                        L7_Reason_DropDown.setSelectedItem("");                    
                        L7_Desc_Damage.setText(""); 
                        L7_Done_ChkBox.setSelected(false);
                        L7_Timestamp.setText("");
                        L7_First_Sku.setEnabled(true);
                        L7_Qty_Out.setEnabled(true);
                        L7_First_Desc.setEnabled(true);
                        L7_Orig_Sku.setEnabled(true);
                        L7_Orig_Desc.setEnabled(true);
                        L7_Orig_Attr.setEnabled(true);
                        L7_Orig_Size.setEnabled(true);
                        L7_Orig_Retail.setEnabled(true);
                        L7_New_Used.setEnabled(true);
                        L7_Reason_DropDown.setEnabled(true);
                        L7_Desc_Damage.setEnabled(true);
                        L7_Done_ChkBox.setEnabled(true);
                        L7_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     }    break;
                 case "8":
                     try {
                        NEBASdao.ClearLine8();
                        L8_First_Sku.setText("");
                        L8_Qty_Out.setText("");
                        L8_First_Desc.setText("");
                        L8_Orig_Sku.setText("");
                        L8_Orig_Desc.setText("");
                        L8_Orig_Attr.setText("");
                        L8_Orig_Size.setText("");
                        L8_Orig_Retail.setText("");
                        L8_New_Used.setSelectedItem("New");
                        L8_Reason_DropDown.setSelectedItem("");                    
                        L8_Desc_Damage.setText(""); 
                        L8_Done_ChkBox.setSelected(false);
                        L8_Timestamp.setText("");
                        L8_First_Sku.setEnabled(true);
                        L8_Qty_Out.setEnabled(true);
                        L8_First_Desc.setEnabled(true);
                        L8_Orig_Sku.setEnabled(true);
                        L8_Orig_Desc.setEnabled(true);
                        L8_Orig_Attr.setEnabled(true);
                        L8_Orig_Size.setEnabled(true);
                        L8_Orig_Retail.setEnabled(true);
                        L8_New_Used.setEnabled(true);
                        L8_Reason_DropDown.setEnabled(true);
                        L8_Desc_Damage.setEnabled(true);
                        L8_Done_ChkBox.setEnabled(true);
                        L8_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     }  break;
                 case "9":
                     try {
                        NEBASdao.ClearLine9();
                        L9_First_Sku.setText("");
                        L9_Qty_Out.setText("");
                        L9_First_Desc.setText("");
                        L9_Orig_Sku.setText("");
                        L9_Orig_Desc.setText("");
                        L9_Orig_Attr.setText("");
                        L9_Orig_Size.setText("");
                        L9_Orig_Retail.setText("");
                        L9_New_Used.setSelectedItem("New");
                        L9_Reason_DropDown.setSelectedItem("");                    
                        L9_Desc_Damage.setText(""); 
                        L9_Done_ChkBox.setSelected(false);
                        L9_Timestamp.setText("");
                        L9_First_Sku.setEnabled(true);
                        L9_Qty_Out.setEnabled(true);
                        L9_First_Desc.setEnabled(true);
                        L9_Orig_Sku.setEnabled(true);
                        L9_Orig_Desc.setEnabled(true);
                        L9_Orig_Attr.setEnabled(true);
                        L9_Orig_Size.setEnabled(true);
                        L9_Orig_Retail.setEnabled(true);
                        L9_New_Used.setEnabled(true);
                        L9_Reason_DropDown.setEnabled(true);
                        L9_Desc_Damage.setEnabled(true);
                        L9_Done_ChkBox.setEnabled(true);
                        L9_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     }   break;
                 case "10":
                     try {
                        NEBASdao.ClearLine10();
                        L10_First_Sku.setText("");
                        L10_Qty_Out.setText("");
                        L10_First_Desc.setText("");
                        L10_Orig_Sku.setText("");
                        L10_Orig_Desc.setText("");
                        L10_Orig_Attr.setText("");
                        L10_Orig_Size.setText("");
                        L10_Orig_Retail.setText("");
                        L10_New_Used.setSelectedItem("New");
                        L10_Reason_DropDown.setSelectedItem("");                    
                        L10_Desc_Damage.setText(""); 
                        L10_Done_ChkBox.setSelected(false);
                        L10_Timestamp.setText("");
                        L10_First_Sku.setEnabled(true);
                        L10_Qty_Out.setEnabled(true);
                        L10_First_Desc.setEnabled(true);
                        L10_Orig_Sku.setEnabled(true);
                        L10_Orig_Desc.setEnabled(true);
                        L10_Orig_Attr.setEnabled(true);
                        L10_Orig_Size.setEnabled(true);
                        L10_Orig_Retail.setEnabled(true);
                        L10_New_Used.setEnabled(true);
                        L10_Reason_DropDown.setEnabled(true);
                        L10_Desc_Damage.setEnabled(true);
                        L10_Done_ChkBox.setEnabled(true);
                        L10_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     }   break;
                default:
                     CLine_textfield.setText("");
                     JOptionPane.showMessageDialog(frame, "You can only enter lines 1-10\nPlease try again", "Clear Line Error", JOptionPane.ERROR_MESSAGE);
                     break;
             }
        } else if (response == JOptionPane.CLOSED_OPTION) {
            System.out.println("JOptionPane closed");
        }
    }//GEN-LAST:event_CLine_ButtonActionPerformed

    private void L1_New_UsedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L1_New_UsedFocusGained
        jPanel6.scrollRectToVisible(L1_Timestamp.getBounds(null));
    }//GEN-LAST:event_L1_New_UsedFocusGained

    private void L2_New_UsedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L2_New_UsedFocusGained
        jPanel6.scrollRectToVisible(L2_Timestamp.getBounds(null));
    }//GEN-LAST:event_L2_New_UsedFocusGained

    private void L3_New_UsedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L3_New_UsedFocusGained
        jPanel6.scrollRectToVisible(L3_Timestamp.getBounds(null));
    }//GEN-LAST:event_L3_New_UsedFocusGained

    private void L4_New_UsedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L4_New_UsedFocusGained
        jPanel6.scrollRectToVisible(L4_Timestamp.getBounds(null));
    }//GEN-LAST:event_L4_New_UsedFocusGained

    private void L5_New_UsedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L5_New_UsedFocusGained
        jPanel6.scrollRectToVisible(L5_Timestamp.getBounds(null));
    }//GEN-LAST:event_L5_New_UsedFocusGained

    private void L6_New_UsedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L6_New_UsedFocusGained
        jPanel6.scrollRectToVisible(L6_Timestamp.getBounds(null));
    }//GEN-LAST:event_L6_New_UsedFocusGained

    private void L7_New_UsedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L7_New_UsedFocusGained
        jPanel6.scrollRectToVisible(L7_Timestamp.getBounds(null));
    }//GEN-LAST:event_L7_New_UsedFocusGained

    private void L8_New_UsedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L8_New_UsedFocusGained
        jPanel6.scrollRectToVisible(L8_Timestamp.getBounds(null));
    }//GEN-LAST:event_L8_New_UsedFocusGained

    private void L9_New_UsedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L9_New_UsedFocusGained
        jPanel6.scrollRectToVisible(L9_Timestamp.getBounds(null));
    }//GEN-LAST:event_L9_New_UsedFocusGained

    private void L10_New_UsedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L10_New_UsedFocusGained
        jPanel6.scrollRectToVisible(L10_Timestamp.getBounds(null));
    }//GEN-LAST:event_L10_New_UsedFocusGained

    private void Delete_Form_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_Form_BtnActionPerformed
        Toolkit.getDefaultToolkit().beep();
        int response = JOptionPane.showConfirmDialog(null, "Do you want to Delete Form?", "Confirm Delete",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            System.out.println("No button clicked");
        } else if (response == JOptionPane.YES_OPTION) {
            try {
                NEBASdao.DeleteForm();
            } catch (SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(frame, "Form Has Been Deleted");
            InvAdj.Rpnt();
            this.dispose();
        }
    }//GEN-LAST:event_Delete_Form_BtnActionPerformed

     // this makes approve button appear only after checkbox is checked and sku is not null to prevent accidental submission of data to archive
    public static void rChkLn1() {
       if (L1_Done_ChkBox.isSelected()== true) {
            if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(true);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        
        if ("ic".equals(GetForms_InvAdj.usrType)) {
         if (L1_Done_ChkBox.isSelected()== true) {
            if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(true);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
        if ("dl".equals(GetForms_InvAdj.usrType)) {
            if (L1_Done_ChkBox.isSelected()== true) {
                if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(true);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
    }
    
    public static void rChkLn2() {
        if (L2_Done_ChkBox.isSelected()== true) {
            if  ((L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(true);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        
        if ("ic".equals(GetForms_InvAdj.usrType)) {
         if (L2_Done_ChkBox.isSelected()== true) {
                if  ((L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(true);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
        if ("dl".equals(GetForms_InvAdj.usrType)) {
            if (L2_Done_ChkBox.isSelected()== true) {
                if  ((L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(true);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
    }
    
    public static void rChkLn3() {
        if (L3_Done_ChkBox.isSelected()== true) {
            if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(true);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        
        if ("ic".equals(GetForms_InvAdj.usrType)) {
         if (L3_Done_ChkBox.isSelected()== true) {
                if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(true);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
        if ("dl".equals(GetForms_InvAdj.usrType)) {
            if (L3_Done_ChkBox.isSelected()== true) {
            if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(true);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
    }
    
    public static void rChkLn4() {
         if (L4_Done_ChkBox.isSelected()== true) {
            if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(true);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        
        if ("ic".equals(GetForms_InvAdj.usrType)) {
         if (L4_Done_ChkBox.isSelected()== true) {
                if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(true);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
        if ("dl".equals(GetForms_InvAdj.usrType)) {
            if (L4_Done_ChkBox.isSelected()== true) {
                if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(true);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
    }
    
    public static void rChkLn5() {
         if (L5_Done_ChkBox.isSelected()== true) {
            if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(true);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        
        if ("ic".equals(GetForms_InvAdj.usrType)) {
         if (L5_Done_ChkBox.isSelected()== true) {
                if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(true);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
        if ("dl".equals(GetForms_InvAdj.usrType)) {
            if (L5_Done_ChkBox.isSelected()== true) {
            if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(true);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
    }
    
    public static void rChkLn6() {
        if (L6_Done_ChkBox.isSelected()== true) {
            if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(true);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        
        if ("ic".equals(GetForms_InvAdj.usrType)) {
         if (L6_Done_ChkBox.isSelected()== true) {
            if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(true);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
        if ("dl".equals(GetForms_InvAdj.usrType)) {
            if (L6_Done_ChkBox.isSelected()== true) {
            if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(true);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
    }
    
    public static void rChkLn7() {
         if (L7_Done_ChkBox.isSelected()== true) {
            if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(true);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        
        if ("ic".equals(GetForms_InvAdj.usrType)) {
         if (L7_Done_ChkBox.isSelected()== true) {
                if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(true);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
        if ("dl".equals(GetForms_InvAdj.usrType)) {
            if (L7_Done_ChkBox.isSelected()== true) {
                if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(true);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
    }
    
    public static void rChkLn8() {
        if (L8_Done_ChkBox.isSelected()== true) {
            if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(true);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        
        if ("ic".equals(GetForms_InvAdj.usrType)) {
         if (L8_Done_ChkBox.isSelected()== true) {
                if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(true);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
        if ("dl".equals(GetForms_InvAdj.usrType)) {
            if (L8_Done_ChkBox.isSelected()== true) {
                if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(true);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
    }
    
    public static void rChkLn9() {
         if (L9_Done_ChkBox.isSelected()== true) {
            if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(true);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        
        if ("ic".equals(GetForms_InvAdj.usrType)) {
         if (L9_Done_ChkBox.isSelected()== true) {
                if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(true);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
        if ("dl".equals(GetForms_InvAdj.usrType)) {
            if (L9_Done_ChkBox.isSelected()== true) {
                if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true)
            && (L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== false || !L10_First_Sku.getText().equals("") && L10_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(true);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
    }
    
    public static void rChkLn10() {
        if (L10_Done_ChkBox.isSelected()== true) {
            if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(true);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        
        if ("ic".equals(GetForms_InvAdj.usrType)) {
         if (L10_Done_ChkBox.isSelected()== true) {
                if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(false);
            RdyforExportBtn.setVisible(true);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
            }
        }
        if ("dl".equals(GetForms_InvAdj.usrType)) {
            if (L10_Done_ChkBox.isSelected()== true) {
                if  ((L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected() == true) && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) 
            && (L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== false || !L4_First_Sku.getText().equals("") && L4_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
            && (L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== false || !L6_First_Sku.getText().equals("") && L6_Done_ChkBox.isSelected()== true) && (L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== false || !L7_First_Sku.getText().equals("") && L7_Done_ChkBox.isSelected()== true)
            && (L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== false || !L8_First_Sku.getText().equals("") && L8_Done_ChkBox.isSelected()== true) && (L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== false || !L9_First_Sku.getText().equals("") && L9_Done_ChkBox.isSelected()== true)
            && (L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== true)) { 
            subBtn.setEnabled(false);
            ApproveBtn.setVisible(true);
            RdyforExportBtn.setVisible(false);
            }
            } else {
                subBtn.setEnabled(false);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
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
            java.util.logging.Logger.getLogger(NEBAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new NEBAS().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton ApproveBtn;
    private javax.swing.JButton CLine_Button;
    private javax.swing.JLabel CLine_Label;
    private javax.swing.JTextField CLine_textfield;
    private javax.swing.JButton Close_Btn;
    private javax.swing.JButton DL_Print_Btn;
    public static javax.swing.JLabel Date_Label;
    private javax.swing.JButton Delete_Form_Btn;
    private javax.swing.JLabel Form_Name_Label;
    private javax.swing.JLabel Form_Name_Tag;
    public static javax.swing.JLabel L1;
    public static javax.swing.JLabel L10;
    public static javax.swing.JTextField L10_Desc_Damage;
    public static javax.swing.JCheckBox L10_Done_ChkBox;
    public static javax.swing.JTextField L10_First_Desc;
    public static javax.swing.JTextField L10_First_Sku;
    public static javax.swing.JComboBox L10_New_Used;
    public static javax.swing.JTextField L10_Orig_Attr;
    public static javax.swing.JTextField L10_Orig_Desc;
    public static javax.swing.JTextField L10_Orig_Retail;
    public static javax.swing.JTextField L10_Orig_Size;
    public static javax.swing.JTextField L10_Orig_Sku;
    public static javax.swing.JTextField L10_Qty_Out;
    public static javax.swing.JComboBox L10_Reason_DropDown;
    public static javax.swing.JTextField L10_Timestamp;
    public static javax.swing.JTextField L1_Desc_Damage;
    public static javax.swing.JCheckBox L1_Done_ChkBox;
    public static javax.swing.JTextField L1_First_Desc;
    public static javax.swing.JTextField L1_First_Sku;
    public static javax.swing.JComboBox L1_New_Used;
    public static javax.swing.JTextField L1_Orig_Attr;
    public static javax.swing.JTextField L1_Orig_Desc;
    public static javax.swing.JTextField L1_Orig_Retail;
    public static javax.swing.JTextField L1_Orig_Size;
    public static javax.swing.JTextField L1_Orig_Sku;
    public static javax.swing.JTextField L1_Qty_Out;
    public static javax.swing.JComboBox L1_Reason_DropDown;
    public static javax.swing.JTextField L1_Timestamp;
    public static javax.swing.JLabel L2;
    public static javax.swing.JTextField L2_Desc_Damage;
    public static javax.swing.JCheckBox L2_Done_ChkBox;
    public static javax.swing.JTextField L2_First_Desc;
    public static javax.swing.JTextField L2_First_Sku;
    public static javax.swing.JComboBox L2_New_Used;
    public static javax.swing.JTextField L2_Orig_Attr;
    public static javax.swing.JTextField L2_Orig_Desc;
    public static javax.swing.JTextField L2_Orig_Retail;
    public static javax.swing.JTextField L2_Orig_Size;
    public static javax.swing.JTextField L2_Orig_Sku;
    public static javax.swing.JTextField L2_Qty_Out;
    public static javax.swing.JComboBox L2_Reason_DropDown;
    public static javax.swing.JTextField L2_Timestamp;
    public static javax.swing.JLabel L3;
    public static javax.swing.JTextField L3_Desc_Damage;
    public static javax.swing.JCheckBox L3_Done_ChkBox;
    public static javax.swing.JTextField L3_First_Desc;
    public static javax.swing.JTextField L3_First_Sku;
    public static javax.swing.JComboBox L3_New_Used;
    public static javax.swing.JTextField L3_Orig_Attr;
    public static javax.swing.JTextField L3_Orig_Desc;
    public static javax.swing.JTextField L3_Orig_Retail;
    public static javax.swing.JTextField L3_Orig_Size;
    public static javax.swing.JTextField L3_Orig_Sku;
    public static javax.swing.JTextField L3_Qty_Out;
    public static javax.swing.JComboBox L3_Reason_DropDown;
    public static javax.swing.JTextField L3_Timestamp;
    public static javax.swing.JLabel L4;
    public static javax.swing.JTextField L4_Desc_Damage;
    public static javax.swing.JCheckBox L4_Done_ChkBox;
    public static javax.swing.JTextField L4_First_Desc;
    public static javax.swing.JTextField L4_First_Sku;
    public static javax.swing.JComboBox L4_New_Used;
    public static javax.swing.JTextField L4_Orig_Attr;
    public static javax.swing.JTextField L4_Orig_Desc;
    public static javax.swing.JTextField L4_Orig_Retail;
    public static javax.swing.JTextField L4_Orig_Size;
    public static javax.swing.JTextField L4_Orig_Sku;
    public static javax.swing.JTextField L4_Qty_Out;
    public static javax.swing.JComboBox L4_Reason_DropDown;
    public static javax.swing.JTextField L4_Timestamp;
    public static javax.swing.JLabel L5;
    public static javax.swing.JTextField L5_Desc_Damage;
    public static javax.swing.JCheckBox L5_Done_ChkBox;
    public static javax.swing.JTextField L5_First_Desc;
    public static javax.swing.JTextField L5_First_Sku;
    public static javax.swing.JComboBox L5_New_Used;
    public static javax.swing.JTextField L5_Orig_Attr;
    public static javax.swing.JTextField L5_Orig_Desc;
    public static javax.swing.JTextField L5_Orig_Retail;
    public static javax.swing.JTextField L5_Orig_Size;
    public static javax.swing.JTextField L5_Orig_Sku;
    public static javax.swing.JTextField L5_Qty_Out;
    public static javax.swing.JComboBox L5_Reason_DropDown;
    public static javax.swing.JTextField L5_Timestamp;
    public static javax.swing.JLabel L6;
    public static javax.swing.JTextField L6_Desc_Damage;
    public static javax.swing.JCheckBox L6_Done_ChkBox;
    public static javax.swing.JTextField L6_First_Desc;
    public static javax.swing.JTextField L6_First_Sku;
    public static javax.swing.JComboBox L6_New_Used;
    public static javax.swing.JTextField L6_Orig_Attr;
    public static javax.swing.JTextField L6_Orig_Desc;
    public static javax.swing.JTextField L6_Orig_Retail;
    public static javax.swing.JTextField L6_Orig_Size;
    public static javax.swing.JTextField L6_Orig_Sku;
    public static javax.swing.JTextField L6_Qty_Out;
    public static javax.swing.JComboBox L6_Reason_DropDown;
    public static javax.swing.JTextField L6_Timestamp;
    public static javax.swing.JLabel L7;
    public static javax.swing.JTextField L7_Desc_Damage;
    public static javax.swing.JCheckBox L7_Done_ChkBox;
    public static javax.swing.JTextField L7_First_Desc;
    public static javax.swing.JTextField L7_First_Sku;
    public static javax.swing.JComboBox L7_New_Used;
    public static javax.swing.JTextField L7_Orig_Attr;
    public static javax.swing.JTextField L7_Orig_Desc;
    public static javax.swing.JTextField L7_Orig_Retail;
    public static javax.swing.JTextField L7_Orig_Size;
    public static javax.swing.JTextField L7_Orig_Sku;
    public static javax.swing.JTextField L7_Qty_Out;
    public static javax.swing.JComboBox L7_Reason_DropDown;
    public static javax.swing.JTextField L7_Timestamp;
    public static javax.swing.JLabel L8;
    public static javax.swing.JTextField L8_Desc_Damage;
    public static javax.swing.JCheckBox L8_Done_ChkBox;
    public static javax.swing.JTextField L8_First_Desc;
    public static javax.swing.JTextField L8_First_Sku;
    public static javax.swing.JComboBox L8_New_Used;
    public static javax.swing.JTextField L8_Orig_Attr;
    public static javax.swing.JTextField L8_Orig_Desc;
    public static javax.swing.JTextField L8_Orig_Retail;
    public static javax.swing.JTextField L8_Orig_Size;
    public static javax.swing.JTextField L8_Orig_Sku;
    public static javax.swing.JTextField L8_Qty_Out;
    public static javax.swing.JComboBox L8_Reason_DropDown;
    public static javax.swing.JTextField L8_Timestamp;
    public static javax.swing.JLabel L9;
    public static javax.swing.JTextField L9_Desc_Damage;
    public static javax.swing.JCheckBox L9_Done_ChkBox;
    public static javax.swing.JTextField L9_First_Desc;
    public static javax.swing.JTextField L9_First_Sku;
    public static javax.swing.JComboBox L9_New_Used;
    public static javax.swing.JTextField L9_Orig_Attr;
    public static javax.swing.JTextField L9_Orig_Desc;
    public static javax.swing.JTextField L9_Orig_Retail;
    public static javax.swing.JTextField L9_Orig_Size;
    public static javax.swing.JTextField L9_Orig_Sku;
    public static javax.swing.JTextField L9_Qty_Out;
    public static javax.swing.JComboBox L9_Reason_DropDown;
    public static javax.swing.JTextField L9_Timestamp;
    public static javax.swing.JButton RdyforExportBtn;
    private javax.swing.JLabel Store_Letter_Label;
    private javax.swing.JLabel Store_Location_Label;
    public static javax.swing.JLabel Store_Logged_In_Label;
    private javax.swing.JLabel Store_Num_Label;
    private javax.swing.JLabel Test_Label;
    private javax.swing.JLabel VersionLabel;
    public static javax.swing.JLabel dtStLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private static javax.swing.JButton subBtn;
    // End of variables declaration//GEN-END:variables

    /*
    public static void RProExp() throws ClassNotFoundException, SQLException, IOException {
        try (Statement s1 = DBConnect.connection.createStatement()) {
            String selTable01 = "SELECT Sku As s, Qty As q, Description1 As d1, Orig_SKU As orgS, "
                    + "Description2 As d2, Attribute2 As atr, Size2 As sze, Orig_Retail As orgR, New_Used As nUsd, Reason As r, Desc_Damage As dmg, "
                    + "FROM " + frmNm + " WHERE Sku IS NOT NULL";
            s1.execute(selTable01);
            try (ResultSet rs01 = s1.getResultSet()) {
                fWriter = new FileWriter("exports/" + frmNm.replace(":", "_") + ".csv", false);
                writer = new BufferedWriter(fWriter);
                String header = "store_code,item_number,quantity,description,original_sku,description2,attribute,size,original_retail,new_used,adj_reason,damage_descr";
                writer.write(header);
                writer.newLine();
                while (rs01.next()) {
                    String sku = rs01.getString("s");
                    String qty = rs01.getString("q");
                    String desc1 = rs01.getString("d1");
                    String orgS = rs01.getString("orgS");
                    String desc2 = rs01.getString("d2");
                    String attr2 = rs01.getString("atr2");
                    String size2 = rs01.getString("sze2");
                    String orgR = rs01.getString("orgR");
                    String nUsed = rs01.getString("nUsd");
                    String reas = rs01.getString("r");
                    String descdmg = rs01.getString("dmg");                   
                    //System.out.println(frmNm.split("_")[1] + qty + "," + sku + "," + desc1 + "," + reas + "," + descdmg + "," + orgR + "," + nwsku + "," + desc2 + "," + qtyI);
                    String line = frmNm.split("_")[1] + "," + sku + "," + qty + "," + desc1 + "," + orgS + "," + desc2 + "," + attr2 + "," + size2 + "," + orgR + "," + nUsed + ","+ reas + "," + descdmg;
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
        writer.close();
        GtForm();
        NEBASdao.drTable();
    }

    public static void UsrExport() throws SQLException, IOException {
        try (Statement s1 = DBConnect.connection.createStatement()) {
            String selTable01 = "SELECT Sku As s, Qty As q, Description1 As d1, Orig_SKU As orgS, Description2 As d2, Attribute2 As atr, Size2 As sze, "
                    + "Orig_Retail As orgR, New_Used As nUsd, Desc_Damage As dmg, Reason As r FROM " + frmNm
                    + " WHERE Sku IS NOT NULL";
            s1.execute(selTable01);
            try (ResultSet rs01 = s1.getResultSet()) {
                fWriter = new FileWriter("Stores/" + frmNm.split("_")[1] + "/" + frmNm.replace(":", "_") + ".csv", false);
                writer = new BufferedWriter(fWriter);
                String header = "store_code,item_number,quantity,description,original_sku,description2,attribute2,size2,original_retail,new_used,adj_reason,damage_descr";
                writer.write(header);
                writer.newLine();
                while (rs01.next()) {
                    String sku = rs01.getString("s");
                    String qty = rs01.getString("q");
                    String desc1 = rs01.getString("d1");
                    String orgS = rs01.getString("orgS");
                    String desc2 = rs01.getString("d2");
                    String attr2 = rs01.getString("atr2");
                    String size2 = rs01.getString("sze2");
                    String orgR = rs01.getString("orgR");
                    String nwUsd = rs01.getString("nUsd");
                    String reas = rs01.getString("r");
                    String descdmg = rs01.getString("dmg");
                    
                    //System.out.println(frmNm.split("_")[1] + qty + "," + sku + "," + desc1 + "," + reas + "," + descdmg + "," + orgR + "," + nwsku + "," + desc2 + "," + qtyI);
                    String line = frmNm.split("_")[1] + "," + sku + "," + qty + "," + desc1 + "," + orgS + "," + desc2 + "," + attr2 + "," + size2 + "," + orgR + "," + nwUsd + "," + reas + "," + descdmg;
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
        writer.close();
    }  */

    public static void GtForm() throws IOException {
        Desktop.getDesktop().open(new File("exports/" + frmNm.replace(":", "_") + ".csv"));
    }

    public static void GtRes() throws SQLException {
        int ctr = 0;
        try (Statement s1 = DBConnect.connection.createStatement()) {
            String selTable02 = "SELECT Reason AS rslt FROM adj_reasons";
            s1.execute(selTable02);
            try (ResultSet rs1 = s1.getResultSet()) {
                while (rs1.next()) {
                    Reasons[ctr] = rs1.getString("rslt");
                    //System.out.println(ctr + ") " + Reasons[ctr]);
                    ctr = ctr + 1;
                }
            }
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form_port_app;

import java.awt.Color;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Seven_User & WStevens
 */

// This class create the GUI for the Exclusive Brand Adjustment Sheet Form
// This class gets data from EBASdao Class to send and receive data from the database
// The buttons in this class can only be viewed by specific user types (Store, DL, IC)

@SuppressWarnings("serial")
public class EBAS extends javax.swing.JFrame {

    private Component frame;
    public static String proc;
    public static String frmNm;
    public static FileWriter fWriter;
    public static BufferedWriter writer;
    /*  *** not used ***
    protected String[] Special_Char_Array = new String[]{"," , "'" , "!" , "#" , "%" , "^" , "&" , "(" , ")" , ":" , ";" , "<" , ">" , "@" , "=" , "+" , "_" , "|" , "{" , "}" , "[" , "]" , "*" , "`"};
    List Special_Char_Array_List = Arrays.asList(Special_Char_Array);
    */
//  protected static String formType;
    public static String[] Reasons = new String[GetForms.cntRes];
    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();

        
    /**
     * Creates new form EBAS
     */
    public EBAS() throws SQLException {
        Form_Name_Label = null;
        initComponents();
        // gets store login name to create and access forms 
        GtStore.GtStore();
        String[] args = null;
        GtDates.main(args);
        if (DBConnect.TestEnviron == true) {
            Test_Label.setVisible(true);
        } else if (DBConnect.TestEnviron == false) {
            Test_Label.setVisible(false);
        }
        EBASdao.StrConversion();
        Store_Letter_Label.setText("(" + EBASdao.StrCode + ")");
        VersionLabel.setText("Version " + DBConnect.Version);
        
        DL_Print_Btn.setVisible(false);
        Delete_Form_Btn.setVisible(false);  
        
        System.out.println("Opened EBAS");
        subBtn.setEnabled(false);         
        // checks if the user has DL or IC authority as well as chekcing forms and changes the button accordingly
        switch (GetForms_InvAdj.usrType) {
            case "dl":
                try {
                    EBASdao.ChkForm2();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (EBASdao.StFrmSt.equals("Pending")) {
                    ApproveBtn.setText("Approve");
                    // should only be visable for DL(and IC) because stores should not be able to approve their own forms
                    ApproveBtn.setVisible(true);
                    DL_Print_Btn.setVisible(true);
                    // This button should only be viewable by IC
                    RdyforExportBtn.setVisible(false);
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
                RdyforExportBtn.setVisible(false);
                // DL cannot see because because only store should be able submit
                subBtn.setEnabled(false);
                break;
            case "ic":
                try {
                    EBASdao.ChkForm2();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                subBtn.setEnabled(false);
                 if (EBASdao.StFrmSt.equals("In-Process")) {
                    RdyforExportBtn.setVisible(false);
                    ApproveBtn.setVisible(false);
                    subBtn.setEnabled(true);              
                    Delete_Form_Btn.setVisible(true);
                } else {
                    subBtn.setEnabled(false);                    
                }
                if (EBASdao.StFrmSt.equals("Pending")) {
                    RdyforExportBtn.setVisible(false);
                    ApproveBtn.setVisible(true);
                    subBtn.setEnabled(false);
                    Delete_Form_Btn.setVisible(true);
                    DL_Print_Btn.setVisible(true);
                } else {
                    ApproveBtn.setVisible(false);
                }
                if (EBASdao.StFrmSt.equals("Approved")) {
                    RdyforExportBtn.setVisible(true);
                    ApproveBtn.setVisible(false);
                    subBtn.setEnabled(false);                    
                    Delete_Form_Btn.setVisible(true);
                    DL_Print_Btn.setVisible(true);
                } else {
                    RdyforExportBtn.setVisible(false);
                }
                break;
                // this is the store 
            default:
               // try {
               // EBASdao.ChkForm();
               // } catch (ClassNotFoundException | SQLException ex) {
               //     Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
               // }
                Close_Btn.setVisible(false);              
                EBASdao.nwFrm = "yes";
                //  not working at all
                //  JFrame.setUndecorated(true);
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
                if (L1_Done_ChkBox.isSelected()==true || L2_Done_ChkBox.isSelected()==true || L3_Done_ChkBox.isSelected()==true || L4_Done_ChkBox.isSelected()==true || L5_Done_ChkBox.isSelected()==true
                || L6_Done_ChkBox.isSelected()==true || L7_Done_ChkBox.isSelected()==true || L8_Done_ChkBox.isSelected()==true || L9_Done_ChkBox.isSelected()==true || L10_Done_ChkBox.isSelected()==true){
                subBtn.setEnabled(true);
                }
                break;
                }
        Store_Number_Label.setText("("+ GtStore.store +")");
        Date_Label.setText(GtDates.tdate);
       // dtStLabel.setText("Today's Date: " + GtDates.tdate);
        frmNm = EBASdao.StFrmNm;
        Form_Name_Label.setText(frmNm);

        // Set Adjustment Reasons (combobox)
        try {
            GtRes();
        } catch (SQLException ex) {
            Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
        }

        // gets reasons from GtRes() method that accesses from datbase table
        L1_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel<>(Reasons));
        L2_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel<>(Reasons));
        L3_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel<>(Reasons));
        L4_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel<>(Reasons));
        L5_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel<>(Reasons));
        L6_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel<>(Reasons));
        L7_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel<>(Reasons));
        L8_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel<>(Reasons));
        L9_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel<>(Reasons));
        L10_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel<>(Reasons));
        

        // Fill Lines w/Existing Data from temporary database table accessed by EBASdao.java
        if (EBASdao.getCntRec() > 0 && Character.isDigit(EBASdao.recLine[1].split(";")[0].charAt(0))) {
            L1_First_Sku.setText(EBASdao.recLine[1].split(";")[0]);
            L1_Qty_Out.setText(EBASdao.recLine[1].split(";")[1]);
            L1_First_Desc.setText(EBASdao.recLine[1].split(";")[2]);
            L1_Orig_Sku.setText(EBASdao.recLine[1].split(";")[3]);
            L1_Orig_Desc.setText(EBASdao.recLine[1].split(";")[4]);
            L1_Orig_Attr.setText(EBASdao.recLine[1].split(";")[5]);
            L1_Orig_Size.setText(EBASdao.recLine[1].split(";")[6]);
            L1_Orig_Retail.setText(EBASdao.recLine[1].split(";")[7]);
            L1_Manuf_Inspec.setText(EBASdao.recLine[1].split(";")[8]);
            L1_New_Used.setSelectedItem(EBASdao.recLine[1].split(";")[9]);
            L1_Reason_DropDown.setSelectedItem(EBASdao.recLine[1].split(";")[10]);
            L1_Desc_Damage.setText(EBASdao.recLine[1].split(";")[11]);          
            if (EBASdao.recLine[1].split(";")[12].equals("true")) {
                L1_Cust_Satisf_ChkBox.setSelected(true);
            }
            if (EBASdao.recLine[1].split(";")[13].equals("true")) {
                L1_Warranty_ChkBox.setSelected(true);
            }
            L1_Done_ChkBox.setSelected(true);
            L1_Timestamp.setText(EBASdao.recLine[1].split(";")[14]);            

            // this grays out all fields and checkboxes to prevent accidental changes for submitted lines
            L1_First_Sku.setEnabled(false);
            L1_Qty_Out.setEnabled(false);
            L1_First_Desc.setEnabled(false);
            L1_Orig_Sku.setEnabled(false);
            L1_Orig_Desc.setEnabled(false);
            L1_Orig_Attr.setEnabled(false);
            L1_Orig_Size.setEnabled(false);
            L1_Orig_Retail.setEnabled(false);
            L1_Manuf_Inspec.setEnabled(false);
            L1_New_Used.setEnabled(false);
            L1_Reason_DropDown.setEnabled(false);
            L1_Desc_Damage.setEnabled(false);
            L1_Cust_Satisf_ChkBox.setEnabled(false);
            L1_Warranty_ChkBox.setEnabled(false);
        }        
        
        // Fill Lines w/Existing Data from temporary database table accessed by EBASdao.java
        if (EBASdao.getCntRec() > 1 && Character.isDigit(EBASdao.recLine[2].split(";")[0].charAt(0))) {
            L2_First_Sku.setText(EBASdao.recLine[2].split(";")[0]);
            L2_Qty_Out.setText(EBASdao.recLine[2].split(";")[1]);
            L2_First_Desc.setText(EBASdao.recLine[2].split(";")[2]);
            L2_Orig_Sku.setText(EBASdao.recLine[2].split(";")[3]);
            L2_Orig_Desc.setText(EBASdao.recLine[2].split(";")[4]);
            L2_Orig_Attr.setText(EBASdao.recLine[2].split(";")[5]);
            L2_Orig_Size.setText(EBASdao.recLine[2].split(";")[6]);
            L2_Orig_Retail.setText(EBASdao.recLine[2].split(";")[7]);
            L2_Manuf_Inspec.setText(EBASdao.recLine[2].split(";")[8]);
            L2_New_Used.setSelectedItem(EBASdao.recLine[2].split(";")[9]);
            L2_Reason_DropDown.setSelectedItem(EBASdao.recLine[2].split(";")[10]);
            L2_Desc_Damage.setText(EBASdao.recLine[2].split(";")[11]);
            if (EBASdao.recLine[2].split(";")[12].equals("true")) {
                L2_Cust_Satisf_ChkBox.setSelected(true);
            }
            if (EBASdao.recLine[2].split(";")[13].equals("true")) {
                L2_Warranty_ChkBox.setSelected(true);
            }
            L2_Done_ChkBox.setSelected(true);            
            L2_Timestamp.setText(EBASdao.recLine[2].split(";")[14]);

            // this grays out all fields and checkboxes to prevent accidental changes for submitted lines
            L2_First_Sku.setEnabled(false);
            L2_Qty_Out.setEnabled(false);
            L2_First_Desc.setEnabled(false);
            L2_Orig_Sku.setEnabled(false);
            L2_Orig_Desc.setEnabled(false);
            L2_Orig_Attr.setEnabled(false);
            L2_Orig_Size.setEnabled(false);
            L2_Orig_Retail.setEnabled(false);
            L2_Manuf_Inspec.setEnabled(false);
            L2_New_Used.setEnabled(false);
            L2_Reason_DropDown.setEnabled(false);
            L2_Desc_Damage.setEnabled(false);
            L2_Cust_Satisf_ChkBox.setEnabled(false);
            L2_Warranty_ChkBox.setEnabled(false);
        }
    
    // Fill Lines w/Existing Data from temporary database table accessed by EBASdao.java
    if (EBASdao.getCntRec() > 2 && Character.isDigit(EBASdao.recLine[3].split(";")[0].charAt(0))) {
            L3_First_Sku.setText(EBASdao.recLine[3].split(";")[0]);
            L3_Qty_Out.setText(EBASdao.recLine[3].split(";")[1]);
            L3_First_Desc.setText(EBASdao.recLine[3].split(";")[2]);
            L3_Orig_Sku.setText(EBASdao.recLine[3].split(";")[3]);
            L3_Orig_Desc.setText(EBASdao.recLine[3].split(";")[4]);
            L3_Orig_Attr.setText(EBASdao.recLine[3].split(";")[5]);
            L3_Orig_Size.setText(EBASdao.recLine[3].split(";")[6]);
            L3_Orig_Retail.setText(EBASdao.recLine[3].split(";")[7]);
            L3_Manuf_Inspec.setText(EBASdao.recLine[3].split(";")[8]);
            L3_New_Used.setSelectedItem(EBASdao.recLine[3].split(";")[9]);
            L3_Reason_DropDown.setSelectedItem(EBASdao.recLine[3].split(";")[10]);
            L3_Desc_Damage.setText(EBASdao.recLine[3].split(";")[11]);
           
            if (EBASdao.recLine[3].split(";")[12].equals("true")) {
                L3_Cust_Satisf_ChkBox.setSelected(true);
            }
            if (EBASdao.recLine[3].split(";")[13].equals("true")) {
                L3_Warranty_ChkBox.setSelected(true);
            }
            L3_Done_ChkBox.setSelected(true);            
            L3_Timestamp.setText(EBASdao.recLine[3].split(";")[14]);

            // this grays out all fields and checkboxes to prevent accidental changes for submitted lines
            L3_First_Sku.setEnabled(false);
            L3_Qty_Out.setEnabled(false);
            L3_First_Desc.setEnabled(false);
            L3_Orig_Sku.setEnabled(false);
            L3_Orig_Desc.setEnabled(false);
            L3_Orig_Attr.setEnabled(false);
            L3_Orig_Size.setEnabled(false);
            L3_Orig_Retail.setEnabled(false);
            L3_Manuf_Inspec.setEnabled(false);
            L3_New_Used.setEnabled(false);
            L3_Reason_DropDown.setEnabled(false);
            L3_Desc_Damage.setEnabled(false);
            L3_Cust_Satisf_ChkBox.setEnabled(false);
            L3_Warranty_ChkBox.setEnabled(false);
        }
    
    // Fill Lines w/Existing Data from temporary database table accessed by EBASdao.java
    if (EBASdao.getCntRec() > 3 && Character.isDigit(EBASdao.recLine[4].split(";")[0].charAt(0))) {
            L4_First_Sku.setText(EBASdao.recLine[4].split(";")[0]);
            L4_Qty_Out.setText(EBASdao.recLine[4].split(";")[1]);
            L4_First_Desc.setText(EBASdao.recLine[4].split(";")[2]);
            L4_Orig_Sku.setText(EBASdao.recLine[4].split(";")[3]);
            L4_Orig_Desc.setText(EBASdao.recLine[4].split(";")[4]);
            L4_Orig_Attr.setText(EBASdao.recLine[4].split(";")[5]);
            L4_Orig_Size.setText(EBASdao.recLine[4].split(";")[6]);
            L4_Orig_Retail.setText(EBASdao.recLine[4].split(";")[7]);
            L4_Manuf_Inspec.setText(EBASdao.recLine[4].split(";")[8]);
            L4_New_Used.setSelectedItem(EBASdao.recLine[4].split(";")[9]);
            L4_Reason_DropDown.setSelectedItem(EBASdao.recLine[4].split(";")[10]);
            L4_Desc_Damage.setText(EBASdao.recLine[4].split(";")[11]);
           
            if (EBASdao.recLine[4].split(";")[12].equals("true")) {
                L4_Cust_Satisf_ChkBox.setSelected(true);
            }
            if (EBASdao.recLine[4].split(";")[13].equals("true")) {
                L4_Warranty_ChkBox.setSelected(true);
            }
            L4_Done_ChkBox.setSelected(true);            
            L4_Timestamp.setText(EBASdao.recLine[4].split(";")[14]);
            
            // this grays out all fields and checkboxes to prevent accidental changes for submitted lines
            L4_First_Sku.setEnabled(false);
            L4_Qty_Out.setEnabled(false);
            L4_First_Desc.setEnabled(false);
            L4_Orig_Sku.setEnabled(false);
            L4_Orig_Desc.setEnabled(false);
            L4_Orig_Attr.setEnabled(false);
            L4_Orig_Size.setEnabled(false);
            L4_Orig_Retail.setEnabled(false);
            L4_Manuf_Inspec.setEnabled(false);
            L4_New_Used.setEnabled(false);
            L4_Reason_DropDown.setEnabled(false);
            L4_Desc_Damage.setEnabled(false);
            L4_Cust_Satisf_ChkBox.setEnabled(false);
            L4_Warranty_ChkBox.setEnabled(false);
        }
    
    // Fill Lines w/Existing Data from temporary database table accessed by EBASdao.java
    if (EBASdao.getCntRec() > 4 && Character.isDigit(EBASdao.recLine[5].split(";")[0].charAt(0))) {
            L5_First_Sku.setText(EBASdao.recLine[5].split(";")[0]);
            L5_Qty_Out.setText(EBASdao.recLine[5].split(";")[1]);
            L5_First_Desc.setText(EBASdao.recLine[5].split(";")[2]);
            L5_Orig_Sku.setText(EBASdao.recLine[5].split(";")[3]);
            L5_Orig_Desc.setText(EBASdao.recLine[5].split(";")[4]);
            L5_Orig_Attr.setText(EBASdao.recLine[5].split(";")[5]);
            L5_Orig_Size.setText(EBASdao.recLine[5].split(";")[6]);
            L5_Orig_Retail.setText(EBASdao.recLine[5].split(";")[7]);
            L5_Manuf_Inspec.setText(EBASdao.recLine[5].split(";")[8]);
            L5_New_Used.setSelectedItem(EBASdao.recLine[5].split(";")[9]);
            L5_Reason_DropDown.setSelectedItem(EBASdao.recLine[5].split(";")[10]);
            L5_Desc_Damage.setText(EBASdao.recLine[5].split(";")[11]);
           
            if (EBASdao.recLine[5].split(";")[12].equals("true")) {
                L5_Cust_Satisf_ChkBox.setSelected(true);
            }
            if (EBASdao.recLine[5].split(";")[13].equals("true")) {
                L5_Warranty_ChkBox.setSelected(true);
            }
            L5_Done_ChkBox.setSelected(true);            
            L5_Timestamp.setText(EBASdao.recLine[5].split(";")[14]);

            // this grays out all fields and checkboxes to prevent accidental changes for submitted lines
            L5_First_Sku.setEnabled(false);
            L5_Qty_Out.setEnabled(false);
            L5_First_Desc.setEnabled(false);
            L5_Orig_Sku.setEnabled(false);
            L5_Orig_Desc.setEnabled(false);
            L5_Orig_Attr.setEnabled(false);
            L5_Orig_Size.setEnabled(false);
            L5_Orig_Retail.setEnabled(false);
            L5_Manuf_Inspec.setEnabled(false);
            L5_New_Used.setEnabled(false);
            L5_Reason_DropDown.setEnabled(false);
            L5_Desc_Damage.setEnabled(false);
            L5_Cust_Satisf_ChkBox.setEnabled(false);
            L5_Warranty_ChkBox.setEnabled(false);
        }
    
    // Fill Lines w/Existing Data from temporary database table accessed by EBASdao.java
    if (EBASdao.getCntRec() > 5 && Character.isDigit(EBASdao.recLine[6].split(";")[0].charAt(0))) {
            L6_First_Sku.setText(EBASdao.recLine[6].split(";")[0]);
            L6_Qty_Out.setText(EBASdao.recLine[6].split(";")[1]);
            L6_First_Desc.setText(EBASdao.recLine[6].split(";")[2]);
            L6_Orig_Sku.setText(EBASdao.recLine[6].split(";")[3]);
            L6_Orig_Desc.setText(EBASdao.recLine[6].split(";")[4]);
            L6_Orig_Attr.setText(EBASdao.recLine[6].split(";")[5]);
            L6_Orig_Size.setText(EBASdao.recLine[6].split(";")[6]);
            L6_Orig_Retail.setText(EBASdao.recLine[6].split(";")[7]);
            L6_Manuf_Inspec.setText(EBASdao.recLine[6].split(";")[8]);
            L6_New_Used.setSelectedItem(EBASdao.recLine[6].split(";")[9]);
            L6_Reason_DropDown.setSelectedItem(EBASdao.recLine[6].split(";")[10]);
            L6_Desc_Damage.setText(EBASdao.recLine[6].split(";")[11]);
            
            if (EBASdao.recLine[6].split(";")[12].equals("true")) {
                L6_Cust_Satisf_ChkBox.setSelected(true);
            }
            if (EBASdao.recLine[6].split(";")[13].equals("true")) {
                L6_Warranty_ChkBox.setSelected(true);
            }
            L6_Done_ChkBox.setSelected(true);
            L6_Timestamp.setText(EBASdao.recLine[6].split(";")[14]);

            // this grays out all fields and checkboxes to prevent accidental changes for submitted lines
            L6_First_Sku.setEnabled(false);
            L6_Qty_Out.setEnabled(false);
            L6_First_Desc.setEnabled(false);
            L6_Orig_Sku.setEnabled(false);
            L6_Orig_Desc.setEnabled(false);
            L6_Orig_Attr.setEnabled(false);
            L6_Orig_Size.setEnabled(false);
            L6_Orig_Retail.setEnabled(false);
            L6_Manuf_Inspec.setEnabled(false);
            L6_New_Used.setEnabled(false);
            L6_Reason_DropDown.setEnabled(false);
            L6_Desc_Damage.setEnabled(false);
            L6_Cust_Satisf_ChkBox.setEnabled(false);
            L6_Warranty_ChkBox.setEnabled(false);
        }
    
    // Fill Lines w/Existing Data from temporary database table accessed by EBASdao.java
    if (EBASdao.getCntRec() > 6 && Character.isDigit(EBASdao.recLine[7].split(";")[0].charAt(0))) {
            L7_First_Sku.setText(EBASdao.recLine[7].split(";")[0]);
            L7_Qty_Out.setText(EBASdao.recLine[7].split(";")[1]);
            L7_First_Desc.setText(EBASdao.recLine[7].split(";")[2]);
            L7_Orig_Sku.setText(EBASdao.recLine[7].split(";")[3]);
            L7_Orig_Desc.setText(EBASdao.recLine[7].split(";")[4]);
            L7_Orig_Attr.setText(EBASdao.recLine[7].split(";")[5]);
            L7_Orig_Size.setText(EBASdao.recLine[7].split(";")[6]);
            L7_Orig_Retail.setText(EBASdao.recLine[7].split(";")[7]);
            L7_Manuf_Inspec.setText(EBASdao.recLine[7].split(";")[8]);
            L7_New_Used.setSelectedItem(EBASdao.recLine[7].split(";")[9]);
            L7_Reason_DropDown.setSelectedItem(EBASdao.recLine[7].split(";")[10]);
            L7_Desc_Damage.setText(EBASdao.recLine[7].split(";")[11]);
           
            if (EBASdao.recLine[7].split(";")[12].equals("true")) {
                L7_Cust_Satisf_ChkBox.setSelected(true);
            }
            if (EBASdao.recLine[7].split(";")[13].equals("true")) {
                L7_Warranty_ChkBox.setSelected(true);
            }
            L7_Done_ChkBox.setSelected(true);            
            L7_Timestamp.setText(EBASdao.recLine[7].split(";")[14]);

            // this grays out all fields and checkboxes to prevent accidental changes for submitted lines
            L7_First_Sku.setEnabled(false);
            L7_Qty_Out.setEnabled(false);
            L7_First_Desc.setEnabled(false);
            L7_Orig_Sku.setEnabled(false);
            L7_Orig_Desc.setEnabled(false);
            L7_Orig_Attr.setEnabled(false);
            L7_Orig_Size.setEnabled(false);
            L7_Orig_Retail.setEnabled(false);
            L7_Manuf_Inspec.setEnabled(false);
            L7_New_Used.setEnabled(false);
            L7_Reason_DropDown.setEnabled(false);
            L7_Desc_Damage.setEnabled(false);
            L7_Cust_Satisf_ChkBox.setEnabled(false);
            L7_Warranty_ChkBox.setEnabled(false);
        }
    
    // Fill Lines w/Existing Data from temporary database table accessed by EBASdao.java
    if (EBASdao.getCntRec() > 7 && Character.isDigit(EBASdao.recLine[8].split(";")[0].charAt(0))) {
            L8_First_Sku.setText(EBASdao.recLine[8].split(";")[0]);
            L8_Qty_Out.setText(EBASdao.recLine[8].split(";")[1]);
            L8_First_Desc.setText(EBASdao.recLine[8].split(";")[2]);
            L8_Orig_Sku.setText(EBASdao.recLine[8].split(";")[3]);
            L8_Orig_Desc.setText(EBASdao.recLine[8].split(";")[4]);
            L8_Orig_Attr.setText(EBASdao.recLine[8].split(";")[5]);
            L8_Orig_Size.setText(EBASdao.recLine[8].split(";")[6]);
            L8_Orig_Retail.setText(EBASdao.recLine[8].split(";")[7]);
            L8_Manuf_Inspec.setText(EBASdao.recLine[8].split(";")[8]);
            L8_New_Used.setSelectedItem(EBASdao.recLine[8].split(";")[9]);
            L8_Reason_DropDown.setSelectedItem(EBASdao.recLine[8].split(";")[10]);
            L8_Desc_Damage.setText(EBASdao.recLine[8].split(";")[11]);
            
            if (EBASdao.recLine[8].split(";")[12].equals("true")) {
                L8_Cust_Satisf_ChkBox.setSelected(true);
            }
            if (EBASdao.recLine[8].split(";")[13].equals("true")) {
                L8_Warranty_ChkBox.setSelected(true);
            }
            L8_Done_ChkBox.setSelected(true);            
            L8_Timestamp.setText(EBASdao.recLine[8].split(";")[14]);

            // this grays out all fields and checkboxes to prevent accidental changes for submitted lines
            L8_First_Sku.setEnabled(false);
            L8_Qty_Out.setEnabled(false);
            L8_First_Desc.setEnabled(false);
            L8_Orig_Sku.setEnabled(false);
            L8_Orig_Desc.setEnabled(false);
            L8_Orig_Attr.setEnabled(false);
            L8_Orig_Size.setEnabled(false);
            L8_Orig_Retail.setEnabled(false);
            L8_Manuf_Inspec.setEnabled(false);
            L8_New_Used.setEnabled(false);
            L8_Reason_DropDown.setEnabled(false);
            L8_Desc_Damage.setEnabled(false);
            L8_Cust_Satisf_ChkBox.setEnabled(false);
            L8_Warranty_ChkBox.setEnabled(false);
        }

    // Fill Lines w/Existing Data from temporary database table accessed by EBASdao.java 
    if (EBASdao.getCntRec() > 8 && Character.isDigit(EBASdao.recLine[9].split(";")[0].charAt(0))) {
            L9_First_Sku.setText(EBASdao.recLine[9].split(";")[0]);
            L9_Qty_Out.setText(EBASdao.recLine[9].split(";")[1]);
            L9_First_Desc.setText(EBASdao.recLine[9].split(";")[2]);
            L9_Orig_Sku.setText(EBASdao.recLine[9].split(";")[3]);
            L9_Orig_Desc.setText(EBASdao.recLine[9].split(";")[4]);
            L9_Orig_Attr.setText(EBASdao.recLine[9].split(";")[5]);
            L9_Orig_Size.setText(EBASdao.recLine[9].split(";")[6]);
            L9_Orig_Retail.setText(EBASdao.recLine[9].split(";")[7]);
            L9_Manuf_Inspec.setText(EBASdao.recLine[9].split(";")[8]);
            L9_New_Used.setSelectedItem(EBASdao.recLine[9].split(";")[9]);
            L9_Reason_DropDown.setSelectedItem(EBASdao.recLine[9].split(";")[10]);
            L9_Desc_Damage.setText(EBASdao.recLine[9].split(";")[11]);
           
            if (EBASdao.recLine[9].split(";")[12].equals("true")) {
                L9_Cust_Satisf_ChkBox.setSelected(true);
            }
            if (EBASdao.recLine[9].split(";")[13].equals("true")) {
                L9_Warranty_ChkBox.setSelected(true);
            }
            L9_Done_ChkBox.setSelected(true);            
            L9_Timestamp.setText(EBASdao.recLine[9].split(";")[14]);

            // this grays out all fields and checkboxes to prevent accidental changes for submitted lines
            L9_First_Sku.setEnabled(false);
            L9_Qty_Out.setEnabled(false);
            L9_First_Desc.setEnabled(false);
            L9_Orig_Sku.setEnabled(false);
            L9_Orig_Desc.setEnabled(false);
            L9_Orig_Attr.setEnabled(false);
            L9_Orig_Size.setEnabled(false);
            L9_Orig_Retail.setEnabled(false);
            L9_Manuf_Inspec.setEnabled(false);
            L9_New_Used.setEnabled(false);
            L9_Reason_DropDown.setEnabled(false);
            L9_Desc_Damage.setEnabled(false);
            L9_Cust_Satisf_ChkBox.setEnabled(false);
            L9_Warranty_ChkBox.setEnabled(false);
        }
    
    // Fill Lines w/Existing Data from temporary database table accessed by EBASdao.java
    if (EBASdao.getCntRec() > 9 && Character.isDigit(EBASdao.recLine[10].split(";")[0].charAt(0))) {
            L10_First_Sku.setText(EBASdao.recLine[10].split(";")[0]);
            L10_Qty_Out.setText(EBASdao.recLine[10].split(";")[1]);
            L10_First_Desc.setText(EBASdao.recLine[10].split(";")[2]);
            L10_Orig_Sku.setText(EBASdao.recLine[10].split(";")[3]);
            L10_Orig_Desc.setText(EBASdao.recLine[10].split(";")[4]);
            L10_Orig_Attr.setText(EBASdao.recLine[10].split(";")[5]);
            L10_Orig_Size.setText(EBASdao.recLine[10].split(";")[6]);
            L10_Orig_Retail.setText(EBASdao.recLine[10].split(";")[7]);
            L10_Manuf_Inspec.setText(EBASdao.recLine[10].split(";")[8]);
            L10_New_Used.setSelectedItem(EBASdao.recLine[10].split(";")[9]);
            L10_Reason_DropDown.setSelectedItem(EBASdao.recLine[10].split(";")[10]);
            L10_Desc_Damage.setText(EBASdao.recLine[10].split(";")[11]);
           
            if (EBASdao.recLine[10].split(";")[12].equals("true")) {
                L10_Cust_Satisf_ChkBox.setSelected(true);
            }
            if (EBASdao.recLine[10].split(";")[13].equals("true")) {
                L10_Warranty_ChkBox.setSelected(true);
            }
            L10_Done_ChkBox.setSelected(true);            
            L10_Timestamp.setText(EBASdao.recLine[10].split(";")[14]);
            // this grays out all fields and checkboxes to prevent accidental changes for submitted lines
            L10_First_Sku.setEnabled(false);
            L10_Qty_Out.setEnabled(false);
            L10_First_Desc.setEnabled(false);
            L10_Orig_Sku.setEnabled(false);
            L10_Orig_Desc.setEnabled(false);
            L10_Orig_Attr.setEnabled(false);
            L10_Orig_Size.setEnabled(false);
            L10_Orig_Retail.setEnabled(false);
            L10_Manuf_Inspec.setEnabled(false);
            L10_New_Used.setEnabled(false);
            L10_Reason_DropDown.setEnabled(false);
            L10_Desc_Damage.setEnabled(false);
            L10_Cust_Satisf_ChkBox.setEnabled(false);
            L10_Warranty_ChkBox.setEnabled(false);
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
        jPanel_1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        L1_First_Sku = new javax.swing.JTextField();
        L1_Qty_Out = new javax.swing.JTextField();
        L1_New_Used = new javax.swing.JComboBox();
        L1_First_Desc = new javax.swing.JTextField();
        L1_Reason_DropDown = new javax.swing.JComboBox();
        L1_Desc_Damage = new javax.swing.JTextField();
        L1_Orig_Sku = new javax.swing.JTextField();
        L1_Manuf_Inspec = new javax.swing.JTextField();
        L1_Cust_Satisf_ChkBox = new javax.swing.JCheckBox();
        L1_Warranty_ChkBox = new javax.swing.JCheckBox();
        L1_Done_ChkBox = new javax.swing.JCheckBox();
        L1_Timestamp = new javax.swing.JTextField();
        L1 = new javax.swing.JLabel();
        L2_First_Sku = new javax.swing.JTextField();
        L2_Qty_Out = new javax.swing.JTextField();
        L2_New_Used = new javax.swing.JComboBox();
        L2_First_Desc = new javax.swing.JTextField();
        L2_Reason_DropDown = new javax.swing.JComboBox();
        L2_Desc_Damage = new javax.swing.JTextField();
        L2_Orig_Sku = new javax.swing.JTextField();
        L2_Manuf_Inspec = new javax.swing.JTextField();
        L2_Cust_Satisf_ChkBox = new javax.swing.JCheckBox();
        L2_Warranty_ChkBox = new javax.swing.JCheckBox();
        L2_Done_ChkBox = new javax.swing.JCheckBox();
        L2_Timestamp = new javax.swing.JTextField();
        L2 = new javax.swing.JLabel();
        L3_First_Sku = new javax.swing.JTextField();
        L4_First_Sku = new javax.swing.JTextField();
        L5_First_Sku = new javax.swing.JTextField();
        L6_First_Sku = new javax.swing.JTextField();
        L7_First_Sku = new javax.swing.JTextField();
        L8_First_Sku = new javax.swing.JTextField();
        L9_First_Sku = new javax.swing.JTextField();
        L3 = new javax.swing.JLabel();
        L4 = new javax.swing.JLabel();
        L5 = new javax.swing.JLabel();
        L6 = new javax.swing.JLabel();
        L7 = new javax.swing.JLabel();
        L8 = new javax.swing.JLabel();
        L9 = new javax.swing.JLabel();
        L3_Qty_Out = new javax.swing.JTextField();
        L4_Qty_Out = new javax.swing.JTextField();
        L5_Qty_Out = new javax.swing.JTextField();
        L6_Qty_Out = new javax.swing.JTextField();
        L7_Qty_Out = new javax.swing.JTextField();
        L8_Qty_Out = new javax.swing.JTextField();
        L9_Qty_Out = new javax.swing.JTextField();
        L10_First_Sku = new javax.swing.JTextField();
        L10_Qty_Out = new javax.swing.JTextField();
        L10 = new javax.swing.JLabel();
        L3_New_Used = new javax.swing.JComboBox();
        L4_New_Used = new javax.swing.JComboBox();
        L5_New_Used = new javax.swing.JComboBox();
        L6_New_Used = new javax.swing.JComboBox();
        L7_New_Used = new javax.swing.JComboBox();
        L8_New_Used = new javax.swing.JComboBox();
        L9_New_Used = new javax.swing.JComboBox();
        L10_New_Used = new javax.swing.JComboBox();
        L3_First_Desc = new javax.swing.JTextField();
        L4_First_Desc = new javax.swing.JTextField();
        L5_First_Desc = new javax.swing.JTextField();
        L9_First_Desc = new javax.swing.JTextField();
        L7_First_Desc = new javax.swing.JTextField();
        L8_First_Desc = new javax.swing.JTextField();
        L6_First_Desc = new javax.swing.JTextField();
        L10_First_Desc = new javax.swing.JTextField();
        L3_Reason_DropDown = new javax.swing.JComboBox();
        L4_Reason_DropDown = new javax.swing.JComboBox();
        L5_Reason_DropDown = new javax.swing.JComboBox();
        L6_Reason_DropDown = new javax.swing.JComboBox();
        L7_Reason_DropDown = new javax.swing.JComboBox();
        L8_Reason_DropDown = new javax.swing.JComboBox();
        L9_Reason_DropDown = new javax.swing.JComboBox();
        L10_Reason_DropDown = new javax.swing.JComboBox();
        L3_Desc_Damage = new javax.swing.JTextField();
        L4_Desc_Damage = new javax.swing.JTextField();
        L5_Desc_Damage = new javax.swing.JTextField();
        L6_Desc_Damage = new javax.swing.JTextField();
        L7_Desc_Damage = new javax.swing.JTextField();
        L8_Desc_Damage = new javax.swing.JTextField();
        L9_Desc_Damage = new javax.swing.JTextField();
        L10_Desc_Damage = new javax.swing.JTextField();
        L3_Orig_Sku = new javax.swing.JTextField();
        L4_Orig_Sku = new javax.swing.JTextField();
        L5_Orig_Sku = new javax.swing.JTextField();
        L6_Orig_Sku = new javax.swing.JTextField();
        L7_Orig_Sku = new javax.swing.JTextField();
        L8_Orig_Sku = new javax.swing.JTextField();
        L9_Orig_Sku = new javax.swing.JTextField();
        L10_Orig_Sku = new javax.swing.JTextField();
        L3_Manuf_Inspec = new javax.swing.JTextField();
        L4_Manuf_Inspec = new javax.swing.JTextField();
        L5_Manuf_Inspec = new javax.swing.JTextField();
        L6_Manuf_Inspec = new javax.swing.JTextField();
        L7_Manuf_Inspec = new javax.swing.JTextField();
        L8_Manuf_Inspec = new javax.swing.JTextField();
        L9_Manuf_Inspec = new javax.swing.JTextField();
        L10_Manuf_Inspec = new javax.swing.JTextField();
        L3_Cust_Satisf_ChkBox = new javax.swing.JCheckBox();
        L4_Cust_Satisf_ChkBox = new javax.swing.JCheckBox();
        L5_Cust_Satisf_ChkBox = new javax.swing.JCheckBox();
        L6_Cust_Satisf_ChkBox = new javax.swing.JCheckBox();
        L7_Cust_Satisf_ChkBox = new javax.swing.JCheckBox();
        L8_Cust_Satisf_ChkBox = new javax.swing.JCheckBox();
        L9_Cust_Satisf_ChkBox = new javax.swing.JCheckBox();
        L3_Warranty_ChkBox = new javax.swing.JCheckBox();
        L4_Warranty_ChkBox = new javax.swing.JCheckBox();
        L5_Warranty_ChkBox = new javax.swing.JCheckBox();
        L6_Warranty_ChkBox = new javax.swing.JCheckBox();
        L7_Warranty_ChkBox = new javax.swing.JCheckBox();
        L8_Warranty_ChkBox = new javax.swing.JCheckBox();
        L9_Warranty_ChkBox = new javax.swing.JCheckBox();
        L10_Warranty_ChkBox = new javax.swing.JCheckBox();
        L3_Done_ChkBox = new javax.swing.JCheckBox();
        L4_Done_ChkBox = new javax.swing.JCheckBox();
        L5_Done_ChkBox = new javax.swing.JCheckBox();
        L6_Done_ChkBox = new javax.swing.JCheckBox();
        L7_Done_ChkBox = new javax.swing.JCheckBox();
        L8_Done_ChkBox = new javax.swing.JCheckBox();
        L9_Done_ChkBox = new javax.swing.JCheckBox();
        L10_Done_ChkBox = new javax.swing.JCheckBox();
        L3_Timestamp = new javax.swing.JTextField();
        L4_Timestamp = new javax.swing.JTextField();
        L5_Timestamp = new javax.swing.JTextField();
        L6_Timestamp = new javax.swing.JTextField();
        L7_Timestamp = new javax.swing.JTextField();
        L8_Timestamp = new javax.swing.JTextField();
        L9_Timestamp = new javax.swing.JTextField();
        L10_Timestamp = new javax.swing.JTextField();
        L10_Cust_Satisf_ChkBox = new javax.swing.JCheckBox();
        Store_Location_Label = new javax.swing.JLabel();
        Store_logged_in_label = new javax.swing.JLabel();
        dtStLabel = new javax.swing.JLabel();
        Close_Btn = new javax.swing.JButton();
        subBtn = new javax.swing.JButton();
        ApproveBtn = new javax.swing.JButton();
        jTextField9 = new javax.swing.JTextField();
        L1_Orig_Desc = new javax.swing.JTextField();
        L2_Orig_Desc = new javax.swing.JTextField();
        L3_Orig_Desc = new javax.swing.JTextField();
        L4_Orig_Desc = new javax.swing.JTextField();
        L5_Orig_Desc = new javax.swing.JTextField();
        L6_Orig_Desc = new javax.swing.JTextField();
        L7_Orig_Desc = new javax.swing.JTextField();
        L8_Orig_Desc = new javax.swing.JTextField();
        L9_Orig_Desc = new javax.swing.JTextField();
        L10_Orig_Desc = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        L1_Orig_Retail = new javax.swing.JTextField();
        L2_Orig_Retail = new javax.swing.JTextField();
        L3_Orig_Retail = new javax.swing.JTextField();
        L4_Orig_Retail = new javax.swing.JTextField();
        L5_Orig_Retail = new javax.swing.JTextField();
        L6_Orig_Retail = new javax.swing.JTextField();
        L7_Orig_Retail = new javax.swing.JTextField();
        L8_Orig_Retail = new javax.swing.JTextField();
        L9_Orig_Retail = new javax.swing.JTextField();
        L10_Orig_Retail = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        L1_Orig_Attr = new javax.swing.JTextField();
        L1_Orig_Size = new javax.swing.JTextField();
        L2_Orig_Size = new javax.swing.JTextField();
        L3_Orig_Size = new javax.swing.JTextField();
        L4_Orig_Size = new javax.swing.JTextField();
        L5_Orig_Size = new javax.swing.JTextField();
        L6_Orig_Size = new javax.swing.JTextField();
        L7_Orig_Size = new javax.swing.JTextField();
        L8_Orig_Size = new javax.swing.JTextField();
        L10_Orig_Size = new javax.swing.JTextField();
        L9_Orig_Size = new javax.swing.JTextField();
        L2_Orig_Attr = new javax.swing.JTextField();
        L3_Orig_Attr = new javax.swing.JTextField();
        L4_Orig_Attr = new javax.swing.JTextField();
        L5_Orig_Attr = new javax.swing.JTextField();
        L6_Orig_Attr = new javax.swing.JTextField();
        L7_Orig_Attr = new javax.swing.JTextField();
        L8_Orig_Attr = new javax.swing.JTextField();
        L10_Orig_Attr = new javax.swing.JTextField();
        L9_Orig_Attr = new javax.swing.JTextField();
        RdyforExportBtn = new javax.swing.JButton();
        Form_Name_Tag = new javax.swing.JLabel();
        Test_Label = new javax.swing.JLabel();
        VersionLabel = new javax.swing.JLabel();
        DL_Print_Btn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        CLine_textfield = new javax.swing.JTextField();
        CLine_Button = new javax.swing.JButton();
        Delete_Form_Btn = new javax.swing.JButton();
        Store_Letter_Label = new javax.swing.JLabel();
        Store_Number_Label = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        Date_Label = new javax.swing.JLabel();
        Form_Name_Label = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Inventoy Adjustment Application EBAS Form");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(100, 100));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                clsFrm(evt);
            }
        });

        jScrollPane2.setPreferredSize(new java.awt.Dimension(1558, 568));

        jPanel6.setBackground(new java.awt.Color(153, 180, 209));
        jPanel6.setPreferredSize(new java.awt.Dimension(1550, 548));

        jPanel_1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_1.setPreferredSize(new java.awt.Dimension(1350, 530));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("INVENTORY ADJUSTMENTS - (Exclusive Brand)");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel2.setMaximumSize(new java.awt.Dimension(104, 58));

        jLabel1.setText("SKU #");

        jLabel4.setText("702630 - 702672");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jTextField3.setText("   New/Used");
        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField3.setFocusable(false);

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setText("     Description of Damage");
        jTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField4.setFocusable(false);

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setText("Orig. SKU");
        jTextField5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField5.setFocusable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel3.setFocusable(false);

        jLabel6.setText("Manufacturer");

        jLabel7.setText("Inspection");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(255, 255, 255));
        jTextField6.setText("    Damage or Defect");
        jTextField6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField6.setFocusable(false);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel4.setFocusable(false);

        jLabel8.setText("Customer");

        jLabel9.setText("Satisfaction");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        jTextField7.setEditable(false);
        jTextField7.setBackground(new java.awt.Color(255, 255, 255));
        jTextField7.setText("   Warranty");
        jTextField7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField7.setFocusable(false);

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

        L1_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_Qty_OutKeyPressed(evt);
            }
        });

        L1_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L1_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_New_UsedKeyPressed(evt);
            }
        });

        L1_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L1_First_Desc.setFocusable(false);

        L1_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is Defective", "Not as Described", "Not What I Ordered", "Other" }));
        L1_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_Reason_DropDownKeyPressed(evt);
            }
        });

        L1_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_Desc_DamageKeyPressed(evt);
            }
        });

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

        L1_Manuf_Inspec.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L1_Manuf_InspecFocusGained(evt);
            }
        });
        L1_Manuf_Inspec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_Manuf_InspecKeyPressed(evt);
            }
        });

        L1_Cust_Satisf_ChkBox.setText("   ");
        L1_Cust_Satisf_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L1_Cust_Satisf_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L1_Cust_Satisf_ChkBox.setPreferredSize(new java.awt.Dimension(30, 23));
        L1_Cust_Satisf_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_Cust_Satisf_ChkBoxKeyPressed(evt);
            }
        });

        L1_Warranty_ChkBox.setText("   ");
        L1_Warranty_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L1_Warranty_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L1_Warranty_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_Warranty_ChkBoxKeyPressed(evt);
            }
        });

        L1_Done_ChkBox.setBackground(new java.awt.Color(255, 255, 255));
        L1_Done_ChkBox.setText(" ");
        L1_Done_ChkBox.setAutoscrolls(true);
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

        L1_Timestamp.setEditable(false);
        L1_Timestamp.setBackground(new java.awt.Color(255, 255, 255));
        L1_Timestamp.setBorder(null);
        L1_Timestamp.setFocusable(false);

        L1.setText("1");

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

        L2_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_Qty_OutKeyPressed(evt);
            }
        });

        L2_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L2_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_New_UsedKeyPressed(evt);
            }
        });

        L2_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L2_First_Desc.setFocusable(false);

        L2_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is Defective", "Not as Described", "Not What I Ordered", "Other" }));
        L2_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_Reason_DropDownKeyPressed(evt);
            }
        });

        L2_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_Desc_DamageKeyPressed(evt);
            }
        });

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

        L2_Manuf_Inspec.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L2_Manuf_InspecFocusGained(evt);
            }
        });
        L2_Manuf_Inspec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_Manuf_InspecKeyPressed(evt);
            }
        });

        L2_Cust_Satisf_ChkBox.setText("   ");
        L2_Cust_Satisf_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L2_Cust_Satisf_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L2_Cust_Satisf_ChkBox.setPreferredSize(new java.awt.Dimension(30, 23));
        L2_Cust_Satisf_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_Cust_Satisf_ChkBoxKeyPressed(evt);
            }
        });

        L2_Warranty_ChkBox.setText("   ");
        L2_Warranty_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L2_Warranty_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L2_Warranty_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_Warranty_ChkBoxKeyPressed(evt);
            }
        });

        L2_Done_ChkBox.setText(" ");
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

        L2_Timestamp.setEditable(false);
        L2_Timestamp.setBackground(new java.awt.Color(255, 255, 255));
        L2_Timestamp.setBorder(null);
        L2_Timestamp.setFocusable(false);

        L2.setText("2");

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

        L3.setText("3");

        L4.setText("4");

        L5.setText("5");

        L6.setText("6");

        L7.setText("7");

        L8.setText("8");

        L9.setText("9");

        L3_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_Qty_OutKeyPressed(evt);
            }
        });

        L4_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_Qty_OutKeyPressed(evt);
            }
        });

        L5_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_Qty_OutKeyPressed(evt);
            }
        });

        L6_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_Qty_OutKeyPressed(evt);
            }
        });

        L7_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_Qty_OutKeyPressed(evt);
            }
        });

        L8_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_Qty_OutKeyPressed(evt);
            }
        });

        L9_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_Qty_OutKeyPressed(evt);
            }
        });

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

        L10_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_Qty_OutKeyPressed(evt);
            }
        });

        L10.setText("10");

        L3_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L3_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_New_UsedKeyPressed(evt);
            }
        });

        L4_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L4_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_New_UsedKeyPressed(evt);
            }
        });

        L5_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L5_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_New_UsedKeyPressed(evt);
            }
        });

        L6_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L6_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_New_UsedKeyPressed(evt);
            }
        });

        L7_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L7_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_New_UsedKeyPressed(evt);
            }
        });

        L8_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L8_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_New_UsedKeyPressed(evt);
            }
        });

        L9_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L9_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_New_UsedKeyPressed(evt);
            }
        });

        L10_New_Used.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New", "Used" }));
        L10_New_Used.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_New_UsedKeyPressed(evt);
            }
        });

        L3_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L3_First_Desc.setFocusable(false);

        L4_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L4_First_Desc.setFocusable(false);

        L5_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L5_First_Desc.setFocusable(false);

        L9_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L9_First_Desc.setFocusable(false);

        L7_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L7_First_Desc.setFocusable(false);

        L8_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L8_First_Desc.setFocusable(false);

        L6_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L6_First_Desc.setFocusable(false);

        L10_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L10_First_Desc.setFocusable(false);

        L3_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is Defective", "Not as Described", "Not What I Ordered", "Other" }));
        L3_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_Reason_DropDownKeyPressed(evt);
            }
        });

        L4_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is Defective", "Not as Described", "Not What I Ordered", "Other" }));
        L4_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_Reason_DropDownKeyPressed(evt);
            }
        });

        L5_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is Defective", "Not as Described", "Not What I Ordered", "Other" }));
        L5_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_Reason_DropDownKeyPressed(evt);
            }
        });

        L6_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is Defective", "Not as Described", "Not What I Ordered", "Other" }));
        L6_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_Reason_DropDownKeyPressed(evt);
            }
        });

        L7_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is Defective", "Not as Described", "Not What I Ordered", "Other" }));
        L7_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_Reason_DropDownKeyPressed(evt);
            }
        });

        L8_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is Defective", "Not as Described", "Not What I Ordered", "Other" }));
        L8_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_Reason_DropDownKeyPressed(evt);
            }
        });

        L9_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is Defective", "Not as Described", "Not What I Ordered", "Other" }));
        L9_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_Reason_DropDownKeyPressed(evt);
            }
        });

        L10_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is Defective", "Not as Described", "Not What I Ordered", "Other" }));
        L10_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_Reason_DropDownKeyPressed(evt);
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

        L3_Manuf_Inspec.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L3_Manuf_InspecFocusGained(evt);
            }
        });
        L3_Manuf_Inspec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_Manuf_InspecKeyPressed(evt);
            }
        });

        L4_Manuf_Inspec.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L4_Manuf_InspecFocusGained(evt);
            }
        });
        L4_Manuf_Inspec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_Manuf_InspecKeyPressed(evt);
            }
        });

        L5_Manuf_Inspec.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L5_Manuf_InspecFocusGained(evt);
            }
        });
        L5_Manuf_Inspec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_Manuf_InspecKeyPressed(evt);
            }
        });

        L6_Manuf_Inspec.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L6_Manuf_InspecFocusGained(evt);
            }
        });
        L6_Manuf_Inspec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_Manuf_InspecKeyPressed(evt);
            }
        });

        L7_Manuf_Inspec.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L7_Manuf_InspecFocusGained(evt);
            }
        });
        L7_Manuf_Inspec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_Manuf_InspecKeyPressed(evt);
            }
        });

        L8_Manuf_Inspec.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L8_Manuf_InspecFocusGained(evt);
            }
        });
        L8_Manuf_Inspec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_Manuf_InspecKeyPressed(evt);
            }
        });

        L9_Manuf_Inspec.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L9_Manuf_InspecFocusGained(evt);
            }
        });
        L9_Manuf_Inspec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_Manuf_InspecKeyPressed(evt);
            }
        });

        L10_Manuf_Inspec.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L10_Manuf_InspecFocusGained(evt);
            }
        });
        L10_Manuf_Inspec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_Manuf_InspecKeyPressed(evt);
            }
        });

        L3_Cust_Satisf_ChkBox.setText("   ");
        L3_Cust_Satisf_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L3_Cust_Satisf_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L3_Cust_Satisf_ChkBox.setPreferredSize(new java.awt.Dimension(30, 23));
        L3_Cust_Satisf_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_Cust_Satisf_ChkBoxKeyPressed(evt);
            }
        });

        L4_Cust_Satisf_ChkBox.setText("   ");
        L4_Cust_Satisf_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L4_Cust_Satisf_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L4_Cust_Satisf_ChkBox.setPreferredSize(new java.awt.Dimension(30, 23));
        L4_Cust_Satisf_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_Cust_Satisf_ChkBoxKeyPressed(evt);
            }
        });

        L5_Cust_Satisf_ChkBox.setText("   ");
        L5_Cust_Satisf_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L5_Cust_Satisf_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L5_Cust_Satisf_ChkBox.setPreferredSize(new java.awt.Dimension(30, 23));
        L5_Cust_Satisf_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_Cust_Satisf_ChkBoxKeyPressed(evt);
            }
        });

        L6_Cust_Satisf_ChkBox.setText("   ");
        L6_Cust_Satisf_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L6_Cust_Satisf_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L6_Cust_Satisf_ChkBox.setPreferredSize(new java.awt.Dimension(30, 23));
        L6_Cust_Satisf_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_Cust_Satisf_ChkBoxKeyPressed(evt);
            }
        });

        L7_Cust_Satisf_ChkBox.setText("   ");
        L7_Cust_Satisf_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L7_Cust_Satisf_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L7_Cust_Satisf_ChkBox.setPreferredSize(new java.awt.Dimension(30, 23));
        L7_Cust_Satisf_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_Cust_Satisf_ChkBoxKeyPressed(evt);
            }
        });

        L8_Cust_Satisf_ChkBox.setText("   ");
        L8_Cust_Satisf_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L8_Cust_Satisf_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L8_Cust_Satisf_ChkBox.setPreferredSize(new java.awt.Dimension(30, 23));
        L8_Cust_Satisf_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_Cust_Satisf_ChkBoxKeyPressed(evt);
            }
        });

        L9_Cust_Satisf_ChkBox.setText("   ");
        L9_Cust_Satisf_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L9_Cust_Satisf_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L9_Cust_Satisf_ChkBox.setPreferredSize(new java.awt.Dimension(30, 23));
        L9_Cust_Satisf_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_Cust_Satisf_ChkBoxKeyPressed(evt);
            }
        });

        L3_Warranty_ChkBox.setText("   ");
        L3_Warranty_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L3_Warranty_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L3_Warranty_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_Warranty_ChkBoxKeyPressed(evt);
            }
        });

        L4_Warranty_ChkBox.setText("   ");
        L4_Warranty_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L4_Warranty_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L4_Warranty_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_Warranty_ChkBoxKeyPressed(evt);
            }
        });

        L5_Warranty_ChkBox.setText("   ");
        L5_Warranty_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L5_Warranty_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L5_Warranty_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_Warranty_ChkBoxKeyPressed(evt);
            }
        });

        L6_Warranty_ChkBox.setText("   ");
        L6_Warranty_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L6_Warranty_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L6_Warranty_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_Warranty_ChkBoxKeyPressed(evt);
            }
        });

        L7_Warranty_ChkBox.setText("   ");
        L7_Warranty_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L7_Warranty_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L7_Warranty_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_Warranty_ChkBoxKeyPressed(evt);
            }
        });

        L8_Warranty_ChkBox.setText("   ");
        L8_Warranty_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L8_Warranty_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L8_Warranty_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_Warranty_ChkBoxKeyPressed(evt);
            }
        });

        L9_Warranty_ChkBox.setText("   ");
        L9_Warranty_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L9_Warranty_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L9_Warranty_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_Warranty_ChkBoxKeyPressed(evt);
            }
        });

        L10_Warranty_ChkBox.setText("   ");
        L10_Warranty_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L10_Warranty_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L10_Warranty_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_Warranty_ChkBoxKeyPressed(evt);
            }
        });

        L3_Done_ChkBox.setText(" ");
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

        L4_Done_ChkBox.setText(" ");
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

        L5_Done_ChkBox.setText(" ");
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

        L6_Done_ChkBox.setText(" ");
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

        L7_Done_ChkBox.setText(" ");
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

        L8_Done_ChkBox.setText(" ");
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

        L9_Done_ChkBox.setText(" ");
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

        L10_Done_ChkBox.setText(" ");
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

        L3_Timestamp.setEditable(false);
        L3_Timestamp.setBackground(new java.awt.Color(255, 255, 255));
        L3_Timestamp.setBorder(null);
        L3_Timestamp.setFocusable(false);

        L4_Timestamp.setEditable(false);
        L4_Timestamp.setBackground(new java.awt.Color(255, 255, 255));
        L4_Timestamp.setBorder(null);
        L4_Timestamp.setFocusable(false);

        L5_Timestamp.setEditable(false);
        L5_Timestamp.setBackground(new java.awt.Color(255, 255, 255));
        L5_Timestamp.setBorder(null);
        L5_Timestamp.setFocusable(false);

        L6_Timestamp.setEditable(false);
        L6_Timestamp.setBackground(new java.awt.Color(255, 255, 255));
        L6_Timestamp.setBorder(null);
        L6_Timestamp.setFocusable(false);

        L7_Timestamp.setEditable(false);
        L7_Timestamp.setBackground(new java.awt.Color(255, 255, 255));
        L7_Timestamp.setBorder(null);
        L7_Timestamp.setFocusable(false);

        L8_Timestamp.setEditable(false);
        L8_Timestamp.setBackground(new java.awt.Color(255, 255, 255));
        L8_Timestamp.setBorder(null);
        L8_Timestamp.setFocusable(false);

        L9_Timestamp.setEditable(false);
        L9_Timestamp.setBackground(new java.awt.Color(255, 255, 255));
        L9_Timestamp.setBorder(null);
        L9_Timestamp.setFocusable(false);

        L10_Timestamp.setEditable(false);
        L10_Timestamp.setBackground(new java.awt.Color(255, 255, 255));
        L10_Timestamp.setBorder(null);
        L10_Timestamp.setFocusable(false);

        L10_Cust_Satisf_ChkBox.setText("   ");
        L10_Cust_Satisf_ChkBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L10_Cust_Satisf_ChkBox.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        L10_Cust_Satisf_ChkBox.setPreferredSize(new java.awt.Dimension(30, 23));
        L10_Cust_Satisf_ChkBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_Cust_Satisf_ChkBoxKeyPressed(evt);
            }
        });

        Store_Location_Label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Store_Location_Label.setText("Store Location:");

        Store_logged_in_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Store_logged_in_label.setText("Store #:");

        dtStLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dtStLabel.setText("Today's Date:");

        Close_Btn.setBackground(new java.awt.Color(255, 0, 0));
        Close_Btn.setText("Close");
        Close_Btn.setAutoscrolls(true);
        Close_Btn.setHideActionText(true);
        Close_Btn.setSelected(true);
        Close_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Close_BtnActionPerformed(evt);
            }
        });

        subBtn.setBackground(new java.awt.Color(0, 255, 0));
        subBtn.setText("Submit");
        subBtn.setAutoscrolls(true);
        subBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBtnActionPerformed(evt);
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

        jTextField9.setEditable(false);
        jTextField9.setBackground(new java.awt.Color(255, 255, 255));
        jTextField9.setText("      Original Sku Description");
        jTextField9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField9.setFocusable(false);

        L1_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L1_Orig_Desc.setFocusable(false);

        L2_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L2_Orig_Desc.setFocusable(false);

        L3_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L3_Orig_Desc.setFocusable(false);

        L4_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L4_Orig_Desc.setFocusable(false);

        L5_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L5_Orig_Desc.setFocusable(false);

        L6_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L6_Orig_Desc.setFocusable(false);

        L7_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L7_Orig_Desc.setFocusable(false);

        L8_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L8_Orig_Desc.setFocusable(false);

        L9_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L9_Orig_Desc.setFocusable(false);

        L10_Orig_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L10_Orig_Desc.setFocusable(false);

        jTextField10.setEditable(false);
        jTextField10.setBackground(new java.awt.Color(255, 255, 255));
        jTextField10.setText("  Orig. Retail");
        jTextField10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField10.setFocusable(false);

        L1_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L1_Orig_Retail.setFocusable(false);

        L2_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L2_Orig_Retail.setFocusable(false);

        L3_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L3_Orig_Retail.setFocusable(false);

        L4_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L4_Orig_Retail.setFocusable(false);

        L5_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L5_Orig_Retail.setFocusable(false);

        L6_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L6_Orig_Retail.setFocusable(false);

        L7_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L7_Orig_Retail.setFocusable(false);

        L8_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L8_Orig_Retail.setFocusable(false);

        L9_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L9_Orig_Retail.setFocusable(false);

        L10_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L10_Orig_Retail.setFocusable(false);

        jTextField11.setEditable(false);
        jTextField11.setBackground(new java.awt.Color(255, 255, 255));
        jTextField11.setText("  Attr");
        jTextField11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField11.setFocusable(false);

        jTextField12.setEditable(false);
        jTextField12.setBackground(new java.awt.Color(255, 255, 255));
        jTextField12.setText("  Size");
        jTextField12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextField12.setFocusable(false);

        L1_Orig_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L1_Orig_Attr.setFocusable(false);

        L1_Orig_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L1_Orig_Size.setFocusable(false);

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

        L10_Orig_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L10_Orig_Size.setFocusable(false);

        L9_Orig_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L9_Orig_Size.setFocusable(false);

        L2_Orig_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L2_Orig_Attr.setFocusable(false);

        L3_Orig_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L3_Orig_Attr.setFocusable(false);

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

        L10_Orig_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L10_Orig_Attr.setFocusable(false);

        L9_Orig_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L9_Orig_Attr.setFocusable(false);

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

        jLabel2.setText("Line");

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

        Store_Number_Label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Store_Number_Label.setForeground(new java.awt.Color(0, 0, 204));
        Store_Number_Label.setText("52");

        jTextField8.setEditable(false);
        jTextField8.setForeground(new java.awt.Color(0, 204, 0));
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setText("Done/Save");
        jTextField8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField8.setFocusable(false);

        Date_Label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Date_Label.setForeground(new java.awt.Color(0, 204, 204));
        Date_Label.setText("4/30/2014");

        Form_Name_Label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Form_Name_Label.setForeground(new java.awt.Color(0, 204, 0));
        Form_Name_Label.setText("ebas_999_04_30_14_12_30_45");

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("**Make sure to click on done/save before submitting forms**");

        javax.swing.GroupLayout jPanel_1Layout = new javax.swing.GroupLayout(jPanel_1);
        jPanel_1.setLayout(jPanel_1Layout);
        jPanel_1Layout.setHorizontalGroup(
            jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_1Layout.createSequentialGroup()
                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel_1Layout.createSequentialGroup()
                                                    .addGap(26, 26, 26)
                                                    .addComponent(L1))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_1Layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(L2)))
                                            .addComponent(L3, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addComponent(L4, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(L5, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addComponent(L6, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(L7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addComponent(L8, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(L9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(L10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_1Layout.createSequentialGroup()
                        .addComponent(Store_Location_Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Store_Letter_Label)
                        .addGap(20, 20, 20)
                        .addComponent(Store_logged_in_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Store_Number_Label)
                        .addGap(18, 18, 18)
                        .addComponent(dtStLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Date_Label)
                        .addContainerGap())
                    .addGroup(jPanel_1Layout.createSequentialGroup()
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_1Layout.createSequentialGroup()
                                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(L2_First_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L3_First_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L4_First_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L5_First_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L6_First_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L7_First_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L8_First_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L9_First_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L10_First_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L1_First_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(L9_Qty_Out, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L8_Qty_Out, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L7_Qty_Out, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L6_Qty_Out, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L5_Qty_Out, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L4_Qty_Out, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L3_Qty_Out, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L2_Qty_Out, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L10_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L1_Qty_Out, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(L9_First_Desc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L8_First_Desc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L7_First_Desc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L6_First_Desc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L5_First_Desc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L4_First_Desc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L3_First_Desc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L2_First_Desc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L1_First_Desc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L10_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(L9_Orig_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L8_Orig_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L7_Orig_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L6_Orig_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L5_Orig_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L4_Orig_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L3_Orig_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L2_Orig_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L10_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L1_Orig_Sku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5)
                            .addGroup(jPanel_1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CLine_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CLine_Button)))
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(L1_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L2_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L3_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L4_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L5_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L6_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L7_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L8_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L9_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L10_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel_1Layout.createSequentialGroup()
                                                .addComponent(L1_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(L1_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel_1Layout.createSequentialGroup()
                                                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(L2_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L3_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L4_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L5_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L6_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L7_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L8_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L10_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L9_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(L2_Orig_Size, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L3_Orig_Size, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L4_Orig_Size, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L5_Orig_Size, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L6_Orig_Size, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L7_Orig_Size, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L8_Orig_Size, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L9_Orig_Size, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L10_Orig_Size, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(L2_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L3_Orig_Retail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L4_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L5_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L6_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L7_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L8_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L9_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L10_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(L1_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField10)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel_1Layout.createSequentialGroup()
                                                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                                        .addComponent(L4_Manuf_Inspec, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(L4_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                                        .addComponent(L6_Manuf_Inspec, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(L6_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(L8_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(L7_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(L9_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(L7_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(L8_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(L9_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                                        .addComponent(L10_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(L10_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(6, 6, 6))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_1Layout.createSequentialGroup()
                                                .addComponent(subBtn)
                                                .addGap(40, 40, 40)))
                                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_1Layout.createSequentialGroup()
                                                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_1Layout.createSequentialGroup()
                                                        .addComponent(L4_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(L4_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_1Layout.createSequentialGroup()
                                                        .addComponent(L6_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(L6_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_1Layout.createSequentialGroup()
                                                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_1Layout.createSequentialGroup()
                                                        .addComponent(L9_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(L9_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(L8_Reason_DropDown, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_1Layout.createSequentialGroup()
                                                        .addComponent(L7_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(L7_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_1Layout.createSequentialGroup()
                                                        .addComponent(L10_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(L10_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_1Layout.createSequentialGroup()
                                                        .addGap(11, 11, 11)
                                                        .addComponent(Close_Btn)
                                                        .addGap(41, 41, 41)
                                                        .addComponent(ApproveBtn)
                                                        .addGap(43, 43, 43)
                                                        .addComponent(RdyforExportBtn)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel_1Layout.createSequentialGroup()
                                                .addComponent(L1_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(L1_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(L1_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(L1_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(L1_Cust_Satisf_ChkBox, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                                            .addGroup(jPanel_1Layout.createSequentialGroup()
                                                .addComponent(L2_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(L2_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(L2_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(L2_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(L2_Cust_Satisf_ChkBox, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_1Layout.createSequentialGroup()
                                                .addComponent(L5_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(L5_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(L5_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(L5_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(L5_Cust_Satisf_ChkBox, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_1Layout.createSequentialGroup()
                                                .addComponent(L3_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(L3_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(L3_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(L3_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(L3_Cust_Satisf_ChkBox, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(L4_Cust_Satisf_ChkBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                                    .addComponent(L9_Cust_Satisf_ChkBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                                    .addComponent(L10_Cust_Satisf_ChkBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                                    .addComponent(L6_Cust_Satisf_ChkBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                                    .addComponent(L7_Cust_Satisf_ChkBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_1Layout.createSequentialGroup()
                                                        .addComponent(L8_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(L8_Cust_Satisf_ChkBox, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))))
                                            .addGroup(jPanel_1Layout.createSequentialGroup()
                                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                        .addComponent(L2_Warranty_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(L2_Done_ChkBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L2_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                        .addComponent(L3_Warranty_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(L3_Done_ChkBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L3_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                        .addComponent(L4_Warranty_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(L4_Done_ChkBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L4_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                        .addComponent(L5_Warranty_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(L5_Done_ChkBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L5_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                        .addComponent(L6_Warranty_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(L6_Done_ChkBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L6_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                        .addComponent(L7_Warranty_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(L7_Done_ChkBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L7_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                        .addComponent(L8_Warranty_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(L8_Done_ChkBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L8_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                        .addComponent(L9_Warranty_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(L9_Done_ChkBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L9_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                        .addComponent(L10_Warranty_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(L10_Done_ChkBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L10_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                        .addComponent(L1_Warranty_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(L1_Done_ChkBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(L1_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(154, 154, 154))
                            .addGroup(jPanel_1Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                        .addGap(211, 211, 211)
                                        .addComponent(Form_Name_Tag)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Form_Name_Label))
                                    .addGroup(jPanel_1Layout.createSequentialGroup()
                                        .addComponent(Test_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(VersionLabel)
                                        .addGap(50, 50, 50)
                                        .addComponent(DL_Print_Btn)
                                        .addGap(80, 80, 80)
                                        .addComponent(Delete_Form_Btn)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(648, 648, 648))
        );

        jPanel_1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {L10_First_Sku, L2_First_Sku, L3_First_Sku, L4_First_Sku, L5_First_Sku, L6_First_Sku, L7_First_Sku, L8_First_Sku, L9_First_Sku, jPanel2});

        jPanel_1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {L1_Reason_DropDown, jTextField6});

        jPanel_1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {L10_Manuf_Inspec, L1_Manuf_Inspec, L4_Manuf_Inspec, L6_Manuf_Inspec, jPanel3});

        jPanel_1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {L10_Warranty_ChkBox, L1_Warranty_ChkBox, L3_Warranty_ChkBox, L4_Warranty_ChkBox, L5_Warranty_ChkBox, L6_Warranty_ChkBox, L7_Warranty_ChkBox, L8_Warranty_ChkBox, L9_Warranty_ChkBox});

        jPanel_1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {L10_New_Used, L1_New_Used, L2_New_Used, L3_New_Used, L4_New_Used, L5_New_Used, L6_New_Used, L7_New_Used, L8_New_Used, L9_New_Used, jTextField3});

        jPanel_1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {L10_Cust_Satisf_ChkBox, L1_Cust_Satisf_ChkBox, L2_Cust_Satisf_ChkBox, L3_Cust_Satisf_ChkBox, L4_Cust_Satisf_ChkBox, L5_Cust_Satisf_ChkBox, L6_Cust_Satisf_ChkBox, L7_Cust_Satisf_ChkBox, L8_Cust_Satisf_ChkBox, L9_Cust_Satisf_ChkBox});

        jPanel_1Layout.setVerticalGroup(
            jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Store_Location_Label)
                    .addComponent(dtStLabel)
                    .addComponent(Form_Name_Tag)
                    .addComponent(Store_logged_in_label, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Store_Letter_Label)
                    .addComponent(Store_Number_Label)
                    .addComponent(Date_Label)
                    .addComponent(Form_Name_Label))
                .addGap(11, 11, 11)
                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Test_Label)
                    .addComponent(DL_Print_Btn)
                    .addComponent(Delete_Form_Btn)
                    .addComponent(VersionLabel))
                .addGap(21, 21, 21)
                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, Short.MAX_VALUE))
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField8))
                .addGap(29, 29, 29)
                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L1_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L1_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_Warranty_ChkBox)
                        .addComponent(L1_Done_ChkBox)
                        .addComponent(L1_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1)
                        .addComponent(L1_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_Cust_Satisf_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L1_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(L2_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Cust_Satisf_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Warranty_ChkBox)
                    .addComponent(L2)
                    .addComponent(L2_Done_ChkBox)
                    .addComponent(L2_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_1Layout.createSequentialGroup()
                        .addComponent(L3_Cust_Satisf_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(L4_Warranty_ChkBox)
                            .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(L4_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L4)
                                .addComponent(L4_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L4_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L4_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L4_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L4_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L4_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L4_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L4_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L4_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L4_Cust_Satisf_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L4_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L4_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L4_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel_1Layout.createSequentialGroup()
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L3_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L3)
                            .addComponent(L3_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L3_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L3_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L3_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L3_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L3_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L3_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L3_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L3_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L3_Warranty_ChkBox)
                            .addComponent(L3_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L3_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L3_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L3_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L4_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_1Layout.createSequentialGroup()
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L5_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5)
                            .addComponent(L5_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L6_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6)
                            .addComponent(L6_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L7_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L7)
                            .addComponent(L7_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L7_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L7_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L7_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L7_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L7_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L7_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L8_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L8)
                            .addComponent(L8_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L8_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L8_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L8_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L9_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L9)
                            .addComponent(L9_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L9_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L9_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L9_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_1Layout.createSequentialGroup()
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L5_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_Cust_Satisf_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_Warranty_ChkBox)
                            .addComponent(L5_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L6_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_Cust_Satisf_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_Warranty_ChkBox)
                            .addComponent(L6_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L7_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L7_Cust_Satisf_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L7_Warranty_ChkBox)
                            .addComponent(L7_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L7_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L7_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L7_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L7_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(L8_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L8_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L8_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L8_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L8_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L8_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(L8_Cust_Satisf_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L8_Warranty_ChkBox)
                                .addComponent(L8_Done_ChkBox)
                                .addComponent(L8_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L8_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(L9_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(L9_Warranty_ChkBox)
                                .addComponent(L9_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_Cust_Satisf_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L10_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10)
                        .addComponent(L10_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_Orig_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_Orig_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_Orig_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_Orig_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L10_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_New_Used, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_Cust_Satisf_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_Manuf_Inspec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_Warranty_ChkBox)
                        .addComponent(L10_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L10_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(CLine_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CLine_Button)))
                    .addGroup(jPanel_1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Close_Btn)
                            .addComponent(subBtn)
                            .addComponent(ApproveBtn)
                            .addComponent(RdyforExportBtn))))
                .addGap(104, 104, 104))
        );

        jPanel_1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel2, jPanel3, jPanel4, jTextField1, jTextField2, jTextField3, jTextField4, jTextField5, jTextField6, jTextField7});

        jPanel_1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {L1_First_Desc, L1_New_Used, L1_Qty_Out, L1_Reason_DropDown});

        jPanel_1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {L10_Reason_DropDown, L2_First_Desc, L2_Reason_DropDown, L3_Reason_DropDown, L4_Reason_DropDown, L5_Reason_DropDown, L6_Reason_DropDown, L7_Reason_DropDown, L8_Reason_DropDown, L9_Reason_DropDown});

        jPanel_1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {L3_New_Used, L3_Qty_Out, L4_New_Used, L4_Qty_Out});

        jPanel_1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {L10_New_Used, L10_Qty_Out, L7_New_Used, L7_Qty_Out, L8_New_Used, L8_Qty_Out, L9_New_Used, L9_Qty_Out});

        jPanel_1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {L2_Orig_Sku, L3_Orig_Sku, L4_Orig_Sku});

        jPanel_1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {L10_Manuf_Inspec, L1_Manuf_Inspec, L5_Manuf_Inspec, L6_Manuf_Inspec, L7_Manuf_Inspec, L8_Manuf_Inspec, L9_Manuf_Inspec});

        jPanel_1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {L2_New_Used, L2_Qty_Out});

        jPanel_1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {L5_New_Used, L5_Qty_Out});

        jPanel_1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {L6_New_Used, L6_Qty_Out});

        jTextField1.getAccessibleContext().setAccessibleName("");
        jTextField2.getAccessibleContext().setAccessibleName("");
        L1_Qty_Out.getAccessibleContext().setAccessibleName("");
        L1_First_Desc.getAccessibleContext().setAccessibleName("");
        L2_Qty_Out.getAccessibleContext().setAccessibleName("");
        L2_First_Desc.getAccessibleContext().setAccessibleName("");
        L3_Qty_Out.getAccessibleContext().setAccessibleName("");
        L4_Qty_Out.getAccessibleContext().setAccessibleName("");
        L5_Qty_Out.getAccessibleContext().setAccessibleName("");
        L6_Qty_Out.getAccessibleContext().setAccessibleName("");
        L7_Qty_Out.getAccessibleContext().setAccessibleName("");
        L8_Qty_Out.getAccessibleContext().setAccessibleName("");
        L9_Qty_Out.getAccessibleContext().setAccessibleName("");
        L10_Qty_Out.getAccessibleContext().setAccessibleName("");
        L3_First_Desc.getAccessibleContext().setAccessibleName("");
        L4_First_Desc.getAccessibleContext().setAccessibleName("");
        L5_First_Desc.getAccessibleContext().setAccessibleName("");
        L9_First_Desc.getAccessibleContext().setAccessibleName("");
        L7_First_Desc.getAccessibleContext().setAccessibleName("");
        L8_First_Desc.getAccessibleContext().setAccessibleName("");
        L6_First_Desc.getAccessibleContext().setAccessibleName("");
        L10_First_Desc.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_1, javax.swing.GroupLayout.PREFERRED_SIZE, 1582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_1, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1601, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Close_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Close_BtnActionPerformed
        InvAdj.Rpnt();
        this.dispose();
    }//GEN-LAST:event_Close_BtnActionPerformed

    // changes status of In-Process form to Pending, this status requires DL Approval
    private void subBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBtnActionPerformed
  //  if (L1_Done_ChkBox.isSelected()==true || L2_Done_ChkBox.isSelected()==true || L3_Done_ChkBox.isSelected()==true || L4_Done_ChkBox.isSelected()==true || L5_Done_ChkBox.isSelected()==true
   //     || L6_Done_ChkBox.isSelected()==true || L7_Done_ChkBox.isSelected()==true || L8_Done_ChkBox.isSelected()==true || L9_Done_ChkBox.isSelected()==true || L10_Done_ChkBox.isSelected()==true){
        
    int response = JOptionPane.showConfirmDialog(null, "Do you want to Submit?", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
      System.out.println("Yes button clicked");
	String[] args = null;
        try {
            EBASdao.FormPending();
            PrintForms.formType = ("EBAS");
            PrintForms.main(args);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(frame, "Form Has Been Submitted for Approval");
        InvAdj.Rpnt();
        
     //   EBASdao.nwFrm = "yes";
       // try {
       //     UsrExport();
      //  } catch (SQLException | IOException ex) {
       //     Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
      //  }
        this.dispose();
    } else if (response == JOptionPane.CLOSED_OPTION) {
      System.out.println("JOptionPane closed");
    }
  //  } else {
  //      JOptionPane.showMessageDialog(frame, "You must input at least one sku and check done/save before submitting \nYou cannot close without submitting", "Submit Only Error", JOptionPane.ERROR_MESSAGE);  
  //  }    
    }//GEN-LAST:event_subBtnActionPerformed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L1_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L1_Done_ChkBoxActionPerformed
      // function shown toward the bottom of class code
        rChkLn1();
        if (L1_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            // gets text from fields for validation
            EBASdao.setSkuInput(L1_First_Sku.getText());
            EBASdao.setSkuInput2(L1_Orig_Sku.getText());
            EBASdao.lnFlg = 1;
            // if the first field is empty this clears the fields for the Line 1 and deletes that line from 
            // the temp table in the database after the checkbox is checked (L1_15.isSelected() == true)
            // *** this is now separated from each line and now sits at the bottom of the form
            /*
            if (L1_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine1();
                    L1_Qty_Out.setText("");
                    L1_First_Desc.setText("");
                    L1_Orig_Sku.setText("");
                    L1_Orig_Desc.setText("");
                    L1_Orig_Attr.setText("");
                    L1_Orig_Size.setText("");
                    L1_Orig_Retail.setText("");
                    L1_Manuf_Inspec.setText("");
                    L1_New_Used.setSelectedItem("New");
                    L1_Reason_DropDown.setSelectedItem("");
                    L1_Desc_Damage.setText("");
                    L1_Cust_Satisf_ChkBox.setSelected(false);
                    L1_Warranty_ChkBox.setSelected(false);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
            try {
                // this checks both sku inputs in database table item_master
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L1_First_Sku.getText().matches("[0-9]+") && L1_First_Sku.getText().length() < 7 ) {
            
            // first sku validation and related data insertion
            if (EBASdao.skuRslt.equals("good")) {
                L1_First_Sku.setBackground(Color.WHITE);
                L1_First_Desc.setText(EBASdao.prodName);
             // checks for single numerical value in range
                
             if (L1_Qty_Out.getText().matches("[1-9]+") && L1_Qty_Out.getText().length() < 2 ) {
                 L1_Qty_Out.setBackground(Color.WHITE);
                 
              // second sku validation and related data insertion 
              if (EBASdao.skuRslt2.equals("good") && L1_Orig_Sku.getText().matches("[0-9]+") && L1_Orig_Sku.getText().length() < 7) {
                L1_Orig_Sku.setBackground(Color.WHITE);
                L1_Orig_Desc.setText(EBASdao.prodName2);
                L1_Orig_Attr.setText(EBASdao.prodAttr);
                L1_Orig_Size.setText(EBASdao.prodSize);
                L1_Orig_Retail.setText(EBASdao.prodPr);
                
                if (L1_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L1_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                || L1_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L1_Manuf_Inspec.getText().matches("[0-9 ]+") || L1_Manuf_Inspec.getText().equals("")) {
                // (L1_Manuf_Inspec.getText().matches("[1-8 ]+") && L1_Manuf_Inspec.getText().length() <2 || L1_Manuf_Inspec.getText().equals("")) {
		L1_Manuf_Inspec.setBackground(Color.WHITE);
                
             /* ** Limit sku range to ensure specif text was entered for bluetooth headset versions ** */
             //   if (((Integer.parseInt(L1_Orig_Sku.getText()) < 799610 && Integer.parseInt(L1_Orig_Sku.getText()) > 799591) && (L1_Manuf_Inspec.getText().equals("DW03") 
             //   || L1_Manuf_Inspec.getText().equals("dw03") || L1_Manuf_Inspec.getText().equals("DW04") || L1_Manuf_Inspec.getText().equals("dw04")))
             //   || (((Integer.parseInt(L1_Orig_Sku.getText())) > 799609 && (Integer.parseInt(L1_Orig_Sku.getText())) < 799592) && L1_Manuf_Inspec.getText().equals(""))) {
                
              //  if (((Integer.parseInt(L1_Orig_Sku.getText()) < 799610 && Integer.parseInt(L1_Orig_Sku.getText()) > 799591) ||
              //      (Integer.parseInt(L1_Orig_Sku.getText()) < 795700 && Integer.parseInt(L1_Orig_Sku.getText()) > 795681) ||
              //      (Integer.parseInt(L1_Orig_Sku.getText()) < 790432 && Integer.parseInt(L1_Orig_Sku.getText()) > 790407))
              //  && ((L1_Manuf_Inspec.getText().equals("DW03") || L1_Manuf_Inspec.getText().equals("dw03")) || (L1_Manuf_Inspec.getText().equals("DW04") || L1_Manuf_Inspec.getText().equals("dw04")))) {
              //      L1_Manuf_Inspec.setBackground(Color.WHITE);           
              //  }
                
                if (!L1_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L1_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L1_Desc_Damage.setBackground(Color.WHITE);
                                
                // checks for both selected or neither selected (one must be selected in for program functionality)
                if (L1_Cust_Satisf_ChkBox.isSelected() != L1_Warranty_ChkBox.isSelected()){  
                    if (!L1_Cust_Satisf_ChkBox.isSelected() || !L1_Warranty_ChkBox.isSelected()) {                        
                
                // gets data from form and sends to EBASdao class to store in database
                EBASdao.Field1 = L1_First_Sku.getText();
                EBASdao.Field2 = L1_Qty_Out.getText();
                EBASdao.Field3 = L1_First_Desc.getText();
                EBASdao.Field4 = L1_Orig_Sku.getText();
                EBASdao.Field5 = L1_Orig_Desc.getText();
                EBASdao.Field6 = L1_Orig_Attr.getText();
                EBASdao.Field7 = L1_Orig_Size.getText();
                EBASdao.Field8 = L1_Orig_Retail.getText();                         
                EBASdao.Field9 = L1_Manuf_Inspec.getText();                               
                EBASdao.Field10 = L1_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L1_Reason_DropDown.getSelectedItem().toString();
                EBASdao.Field12 = L1_Desc_Damage.getText();
                
                if (L1_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L1_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                                
                EBASdao.rline = L1.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L1.getBounds(null));
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    subBtn.setEnabled(false);
                }                     
                } else {
                    JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    subBtn.setEnabled(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Desc_Damage.setBackground(Color.yellow);
                    L1_Desc_Damage.requestFocus();
                    subBtn.setEnabled(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Reason_DropDown.requestFocus();
                    subBtn.setEnabled(false);
                }
                /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
                */
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                    L1_Manuf_Inspec.requestFocus();
                    subBtn.setEnabled(false);
                }
            } else {
                L1_Done_ChkBox.setSelected(false);
                L1_Orig_Sku.setBackground(Color.yellow);
                L1_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L1.getBounds(null));
                subBtn.setEnabled(false);
            }            
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L1_Done_ChkBox.setSelected(false);
                L1_Qty_Out.setBackground(Color.yellow);
                L1_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L1.getBounds(null));
                subBtn.setEnabled(false);
            }
            } else {
                L1_Done_ChkBox.setSelected(false);
                L1_First_Sku.setBackground(Color.yellow);
                L1_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L1.getBounds(null));
                subBtn.setEnabled(false);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L1_Done_ChkBox.setSelected(false);
                L1_First_Sku.requestFocus();
                L1_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L1.getBounds(null));
                subBtn.setEnabled(false);
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
            L1_Manuf_Inspec.setEnabled(true);
            L1_New_Used.setEnabled(true);
            L1_Reason_DropDown.setEnabled(true);
            L1_Desc_Damage.setEnabled(true);           
            L1_Cust_Satisf_ChkBox.setEnabled(true);
            L1_Warranty_ChkBox.setEnabled(true);
            L1_Done_ChkBox.setEnabled(true);
            L1_Timestamp.setEnabled(true);
        }
            manager.focusNextComponent();            
    }//GEN-LAST:event_L1_Done_ChkBoxActionPerformed

    private void clsFrm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_clsFrm
    switch (GetForms_InvAdj.usrType) {
        // Store user is default
        default:
            Toolkit.getDefaultToolkit().beep();
          //  if (L1_Done_ChkBox.isSelected()==true || L2_Done_ChkBox.isSelected()==true || L3_Done_ChkBox.isSelected()==true || L4_Done_ChkBox.isSelected()==true || L5_Done_ChkBox.isSelected()==true
          //      || L6_Done_ChkBox.isSelected()==true || L7_Done_ChkBox.isSelected()==true || L8_Done_ChkBox.isSelected()==true || L9_Done_ChkBox.isSelected()==true || L10_Done_ChkBox.isSelected()==true){
        // this ensures that when the store closes the window it will not allow them until they submit their line data to prevent data loss
        int response = JOptionPane.showConfirmDialog(null, "You are trying to exit WITHOUT SAVING your information. \nIf you want TO SAVE the lines you have entered you" +
        "must CLICK NO and then click the Done/Save boxes for any items you want to keep. \nIf you DO NOT WANT TO SAVE any items" +
        "then click yes to exit.", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
        System.out.println("Yes button clicked");
                
            String[] args = null;
        //  if (GetForms_InvAdj.usrType.equals("store")) {
            try {
                EBASdao.FormPending();
                PrintForms.formType = ("EBAS");
                PrintForms.main(args);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
              //  JOptionPane.showMessageDialog(frame, "Form Has Been Submitted for Approval");        
                InvAdj.Rpnt();        
                this.dispose();                
           // } else {
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("EBAS Window closed");
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
        // ********* This partially works i.e. line 1 works but not sure about the others
        /* 
        switch (GetForms_InvAdj.usrType) {
        // Store user is default
        default:
            Toolkit.getDefaultToolkit().beep();
            if (L1_Done_ChkBox.isSelected() == true) {
                rChkLn1();
                int response = JOptionPane.showConfirmDialog(null, "Do you want to Submit? \n** NOTE: Any lines with data must have done/save checked before submitting or you will lose data **", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
        System.out.println("Yes button clicked");
        
            String[] args = null;
        //  if (GetForms_InvAdj.usrType.equals("store")) {
            try {
                EBASdao.FormPending();
                PrintForms.formType = ("EBAS");
                PrintForms.main(args);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
                JOptionPane.showMessageDialog(frame, "Form Has Been Submitted for Approval");        
                InvAdj.Rpnt();        
                this.dispose();                
           // } else {
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("EBAS Window closed");
            }
            } else if(L2_Done_ChkBox.isSelected() == true) {
                rChkLn2();
                int response = JOptionPane.showConfirmDialog(null, "Do you want to Submit? \n** NOTE: Any lines with data must have done/save checked before submitting or you will lose data **", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
        System.out.println("Yes button clicked");
        
            String[] args = null;
        //  if (GetForms_InvAdj.usrType.equals("store")) {
            try {
                EBASdao.FormPending();
                PrintForms.formType = ("EBAS");
                PrintForms.main(args);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
                JOptionPane.showMessageDialog(frame, "Form Has Been Submitted for Approval");        
                InvAdj.Rpnt();        
                this.dispose();                
           // } else {
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("EBAS Window closed");
            }
            } else if(L3_Done_ChkBox.isSelected() == true) {
                rChkLn3();
                int response = JOptionPane.showConfirmDialog(null, "Do you want to Submit? \n** NOTE: Any lines with data must have done/save checked before submitting or you will lose data **", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
        System.out.println("Yes button clicked");
        
            String[] args = null;
        //  if (GetForms_InvAdj.usrType.equals("store")) {
            try {
                EBASdao.FormPending();
                PrintForms.formType = ("EBAS");
                PrintForms.main(args);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
                JOptionPane.showMessageDialog(frame, "Form Has Been Submitted for Approval");        
                InvAdj.Rpnt();        
                this.dispose();                
           // } else {
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("EBAS Window closed");
            }
            } else if(L4_Done_ChkBox.isSelected() == true) {
                rChkLn4();
                int response = JOptionPane.showConfirmDialog(null, "Do you want to Submit? \n** NOTE: Any lines with data must have done/save checked before submitting or you will lose data **", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
        System.out.println("Yes button clicked");
        
            String[] args = null;
        //  if (GetForms_InvAdj.usrType.equals("store")) {
            try {
                EBASdao.FormPending();
                PrintForms.formType = ("EBAS");
                PrintForms.main(args);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
                JOptionPane.showMessageDialog(frame, "Form Has Been Submitted for Approval");        
                InvAdj.Rpnt();        
                this.dispose();                
           // } else {
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("EBAS Window closed");
            }
            } else if(L5_Done_ChkBox.isSelected() == true) {
                rChkLn5();
                int response = JOptionPane.showConfirmDialog(null, "Do you want to Submit? \n** NOTE: Any lines with data must have done/save checked before submitting or you will lose data **", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
        System.out.println("Yes button clicked");
        
            String[] args = null;
        //  if (GetForms_InvAdj.usrType.equals("store")) {
            try {
                EBASdao.FormPending();
                PrintForms.formType = ("EBAS");
                PrintForms.main(args);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
                JOptionPane.showMessageDialog(frame, "Form Has Been Submitted for Approval");        
                InvAdj.Rpnt();        
                this.dispose();                
           // } else {
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("EBAS Window closed");
            }
            } else if(L6_Done_ChkBox.isSelected() == true) {
                rChkLn6();
                int response = JOptionPane.showConfirmDialog(null, "Do you want to Submit? \n** NOTE: Any lines with data must have done/save checked before submitting or you will lose data **", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
        System.out.println("Yes button clicked");
        
            String[] args = null;
        //  if (GetForms_InvAdj.usrType.equals("store")) {
            try {
                EBASdao.FormPending();
                PrintForms.formType = ("EBAS");
                PrintForms.main(args);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
                JOptionPane.showMessageDialog(frame, "Form Has Been Submitted for Approval");        
                InvAdj.Rpnt();        
                this.dispose();                
           // } else {
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("EBAS Window closed");
            }
            } else if(L7_Done_ChkBox.isSelected() == true) {
                rChkLn7();
                int response = JOptionPane.showConfirmDialog(null, "Do you want to Submit? \n** NOTE: Any lines with data must have done/save checked before submitting or you will lose data **", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
        System.out.println("Yes button clicked");
        
            String[] args = null;
        //  if (GetForms_InvAdj.usrType.equals("store")) {
            try {
                EBASdao.FormPending();
                PrintForms.formType = ("EBAS");
                PrintForms.main(args);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
                JOptionPane.showMessageDialog(frame, "Form Has Been Submitted for Approval");        
                InvAdj.Rpnt();        
                this.dispose();                
           // } else {
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("EBAS Window closed");
            }
            } else if(L8_Done_ChkBox.isSelected() == true) {
                rChkLn8();
                int response = JOptionPane.showConfirmDialog(null, "Do you want to Submit? \n** NOTE: Any lines with data must have done/save checked before submitting or you will lose data **", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
        System.out.println("Yes button clicked");
        
            String[] args = null;
        //  if (GetForms_InvAdj.usrType.equals("store")) {
            try {
                EBASdao.FormPending();
                PrintForms.formType = ("EBAS");
                PrintForms.main(args);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
                JOptionPane.showMessageDialog(frame, "Form Has Been Submitted for Approval");        
                InvAdj.Rpnt();        
                this.dispose();                
           // } else {
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("EBAS Window closed");
            }
            } else if(L9_Done_ChkBox.isSelected() == true) {
                rChkLn9();
                int response = JOptionPane.showConfirmDialog(null, "Do you want to Submit? \n** NOTE: Any lines with data must have done/save checked before submitting or you will lose data **", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
        System.out.println("Yes button clicked");
        
            String[] args = null;
        //  if (GetForms_InvAdj.usrType.equals("store")) {
            try {
                EBASdao.FormPending();
                PrintForms.formType = ("EBAS");
                PrintForms.main(args);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
                JOptionPane.showMessageDialog(frame, "Form Has Been Submitted for Approval");        
                InvAdj.Rpnt();        
                this.dispose();                
           // } else {
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("EBAS Window closed");
            }
            } else if(L10_Done_ChkBox.isSelected() == true) {
                rChkLn10();
                int response = JOptionPane.showConfirmDialog(null, "Do you want to Submit? \n** NOTE: Any lines with data must have done/save checked before submitting or you will lose data **", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
        System.out.println("Yes button clicked");
        
            String[] args = null;
        //  if (GetForms_InvAdj.usrType.equals("store")) {
            try {
                EBASdao.FormPending();
                PrintForms.formType = ("EBAS");
                PrintForms.main(args);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
                JOptionPane.showMessageDialog(frame, "Form Has Been Submitted for Approval");        
                InvAdj.Rpnt();        
                this.dispose();                
           // } else {
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("EBAS Window closed");
            }
            // || L2_Done_ChkBox.isSelected()==true || L3_Done_ChkBox.isSelected()==true || L4_Done_ChkBox.isSelected()==true || L5_Done_ChkBox.isSelected()==true
           //     || L6_Done_ChkBox.isSelected()==true || L7_Done_ChkBox.isSelected()==true || L8_Done_ChkBox.isSelected()==true || L9_Done_ChkBox.isSelected()==true || L10_Done_ChkBox.isSelected()==true){
   
    
        // this ensures that when the store closes the window it will not allow them until they submit their line data to prevent data loss
    /*  
    int response = JOptionPane.showConfirmDialog(null, "Do you want to Submit? \n** NOTE: Any lines with data must have done/save checked before submitting or you will lose data **", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
        System.out.println("Yes button clicked");
        
            String[] args = null;
        //  if (GetForms_InvAdj.usrType.equals("store")) {
            try {
                EBASdao.FormPending();
                PrintForms.formType = ("EBAS");
                PrintForms.main(args);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
                JOptionPane.showMessageDialog(frame, "Form Has Been Submitted for Approval");        
                InvAdj.Rpnt();        
                this.dispose();                
           // } else {
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("EBAS Window closed");
            }
         //   } else {
         //       JOptionPane.showMessageDialog(frame, "You must input at least one sku and check done/save before submitting \nYou cannot close without submitting first", "Submit Only Error", JOptionPane.ERROR_MESSAGE);  
         //   }
            // DL and IC do not need to submit because it is a store specific action
            
            }
            
            break;
            case "dl":
                InvAdj.Rpnt();        
                this.dispose();
            break;
            case "ic":
                InvAdj.Rpnt();        
                this.dispose();            
            }      
            */
    }//GEN-LAST:event_clsFrm

         // Validates all the necessary fields have the correct data and are not empty
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L2_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L2_Done_ChkBoxActionPerformed
        rChkLn2();
        if (L2_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L2_First_Sku.getText());
            EBASdao.setSkuInput2(L2_Orig_Sku.getText());
            EBASdao.lnFlg = 2;
            /*
            if (L2_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine2();
                    L2_Qty_Out.setText("");
                    L2_First_Desc.setText("");
                    L2_Orig_Sku.setText("");
                    L2_Orig_Desc.setText("");
                    L2_Orig_Attr.setText("");
                    L2_Orig_Size.setText("");
                    L2_Orig_Retail.setText("");
                    L2_Manuf_Inspec.setText("");
                    L2_New_Used.setSelectedItem("New");
                    L2_Reason_DropDown.setSelectedItem("");
                    L2_Desc_Damage.setText("");
                    L2_Cust_Satisf_ChkBox.setSelected(false);
                    L2_Warranty_ChkBox.setSelected(false);
                    L2_Done_ChkBox.setSelected(false);
                    L2_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                } 
            } else {  */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L2_First_Sku.getText().matches("[0-9]+") && L2_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L2_First_Sku.setBackground(Color.WHITE);
                L2_First_Desc.setText(EBASdao.prodName);
                
            if (L2_Qty_Out.getText().matches("[1-9]+") && L2_Qty_Out.getText().length() < 2 ) {
                 L2_Qty_Out.setBackground(Color.WHITE);
                
            if (EBASdao.skuRslt2.equals("good") && L2_Orig_Sku.getText().matches("[0-9]+") && L2_Orig_Sku.getText().length() < 7) {
                L2_Orig_Sku.setBackground(Color.WHITE);
                L2_Orig_Desc.setText(EBASdao.prodName2);
                L2_Orig_Attr.setText(EBASdao.prodAttr);
                L2_Orig_Size.setText(EBASdao.prodSize);
                L2_Orig_Retail.setText(EBASdao.prodPr);
                
            if (L2_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L2_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L2_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L2_Manuf_Inspec.getText().matches("[0-9 ]+") || L2_Manuf_Inspec.getText().equals("")) {
               // (L2_Manuf_Inspec.getText().matches("[1-8 ]+") && L2_Manuf_Inspec.getText().length() <2 || L2_Manuf_Inspec.getText().equals("")) {
		L2_Manuf_Inspec.setBackground(Color.WHITE);
                
           // if((Integer.parseInt(L2_Orig_Sku.getText()) < 799610 || Integer.parseInt(L2_Orig_Sku.getText()) > 790407)
            //    && ((L2_Manuf_Inspec.getText().equals("DW03") || L2_Manuf_Inspec.getText().equals("dw03")) || (L2_Manuf_Inspec.getText().equals("DW04") || L2_Manuf_Inspec.getText().equals("dw04")))) {
            //    L2_Manuf_Inspec.setBackground(Color.WHITE);
                
            if (!L2_Reason_DropDown.getSelectedItem().equals("")) {
                    
            if (L2_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L2_Desc_Damage.setBackground(Color.WHITE);
             
            if (L2_Cust_Satisf_ChkBox.isSelected() != L2_Warranty_ChkBox.isSelected()){  
                    if (!L2_Cust_Satisf_ChkBox.isSelected() || !L2_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L2_First_Sku.getText();
                EBASdao.Field2 = L2_Qty_Out.getText();
                EBASdao.Field3 = L2_First_Desc.getText();
                EBASdao.Field4 = L2_Orig_Sku.getText();
                EBASdao.Field5 = L2_Orig_Desc.getText();
                EBASdao.Field6 = L2_Orig_Attr.getText();
                EBASdao.Field7 = L2_Orig_Size.getText();
                EBASdao.Field8 = L2_Orig_Retail.getText();              
                EBASdao.Field9 = L2_Manuf_Inspec.getText();                
                EBASdao.Field10 = L2_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L2_Reason_DropDown.getSelectedItem().toString();
                EBASdao.Field12 = L2_Desc_Damage.getText();                
                
                if (L2_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L2_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L2.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L2.getBounds(null));
                    
                } else {
                     JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                     L2_Done_ChkBox.setSelected(false);
                }                   
                } else {
                    JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L2_Done_ChkBox.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                    L2_Done_ChkBox.setSelected(false);
                    L2_Desc_Damage.setBackground(Color.yellow);
                    L2_Desc_Damage.requestFocus();
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                    L2_Done_ChkBox.setSelected(false);
                    L2_Reason_DropDown.requestFocus();
                }
                /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
                */
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
                    L2_Done_ChkBox.setSelected(false);
                    L2_Manuf_Inspec.setBackground(Color.yellow);
                    L2_Manuf_Inspec.requestFocus();
                }
                } else {
                    L2_Done_ChkBox.setSelected(false);
                    L2_Orig_Sku.setBackground(Color.yellow);
                    L2_Orig_Sku.requestFocus();
                    JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                    jPanel6.scrollRectToVisible(L2.getBounds(null));
                }            
            } else {
                 JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L2_Done_ChkBox.setSelected(false);
                L2_Qty_Out.setBackground(Color.yellow);
                L2_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L2.getBounds(null));
            }            
            } else {
                L2_Done_ChkBox.setSelected(false);
                L2_First_Sku.setBackground(Color.yellow);
                L2_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L2.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L2_Done_ChkBox.setSelected(false);
                L2_First_Sku.requestFocus();
                L2_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L2.getBounds(null));
            } 
          //  }
        } else {
            // this ensures that the fields are enabled for input if the checkbox is not selected (false)
            L2_First_Sku.setEnabled(true);
            L2_Qty_Out.setEnabled(true);
            L2_First_Desc.setEnabled(true);
            L2_Orig_Sku.setEnabled(true);
            L2_Orig_Desc.setEnabled(true);
            L2_Orig_Attr.setEnabled(true);
            L2_Orig_Size.setEnabled(true);
            L2_Orig_Retail.setEnabled(true);
            L2_Manuf_Inspec.setEnabled(true);
            L2_New_Used.setEnabled(true);
            L2_Reason_DropDown.setEnabled(true);
            L2_Desc_Damage.setEnabled(true);           
            L2_Cust_Satisf_ChkBox.setEnabled(true);
            L2_Warranty_ChkBox.setEnabled(true);
            L2_Done_ChkBox.setEnabled(true);
            L2_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
    }//GEN-LAST:event_L2_Done_ChkBoxActionPerformed

    // Approve button is only viewable by DL and IC
    // When the DL presses this button this changes the status from Pending to Approved that now requires IC Approval
    private void ApproveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApproveBtnActionPerformed
    int response = JOptionPane.showConfirmDialog(null, "Do you want to Approve?", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");      
    } else if (response == JOptionPane.YES_OPTION) {
      System.out.println("Yes button clicked");	
        try {
                EBASdao.FormApproved();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(frame, "Form Has Been Approved & Submitted" + '\n' + "to Inventory Control");
            InvAdj_Admin.Rpnt();        
            this.dispose();
    } else if (response == JOptionPane.CLOSED_OPTION) {
      System.out.println("JOptionPane closed");
    }
    }//GEN-LAST:event_ApproveBtnActionPerformed

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L1_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_First_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput(L1_First_Sku.getText());
            String[] args = null;
            if (L1_First_Sku.getText().matches("[0-9]+") && L1_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L1_First_Sku.setBackground(Color.WHITE);
                L1_First_Desc.setText(EBASdao.prodName);
                L1_Qty_Out.requestFocus();
            } else {
                // this shows the user the field where the input is needed or is wrong
                L1_First_Sku.setBackground(Color.yellow);
                L1_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU");
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter 6 digits");                    
                L1_First_Sku.setBackground(Color.yellow);
                L1_First_Sku.requestFocus();
            }            
        }
    }//GEN-LAST:event_L1_First_SkuKeyPressed

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L2_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_First_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput(L2_First_Sku.getText());
            String[] args = null;
            if (L2_First_Sku.getText().matches("[0-9]+") && L2_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L2_First_Sku.setBackground(Color.WHITE);
                L2_First_Desc.setText(EBASdao.prodName);
                L2_Qty_Out.requestFocus();
            } else {
                L2_First_Sku.setBackground(Color.yellow);
                L2_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU");
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter 6 digits");
                L2_First_Sku.setBackground(Color.yellow);
                L2_First_Sku.requestFocus();
            }
        } 
    }//GEN-LAST:event_L2_First_SkuKeyPressed
  
     // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L1_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Done_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L1_Done_ChkBox.setSelected(true);
            rChkLn1();
               if (L1_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L1_First_Sku.getText());
            EBASdao.setSkuInput2(L1_Orig_Sku.getText());
            EBASdao.lnFlg = 1;
            /*
            if (L1_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine1();
                    L1_Qty_Out.setText("");
                    L1_First_Desc.setText("");
                    L1_Orig_Sku.setText("");
                    L1_Orig_Desc.setText("");
                    L1_Orig_Attr.setText("");
                    L1_Orig_Size.setText("");
                    L1_Orig_Retail.setText("");
                    L1_Manuf_Inspec.setText("");
                    L1_New_Used.setSelectedItem("New");
                    L1_Reason_DropDown.setSelectedItem("");
                    L1_Desc_Damage.setText("");
                    L1_Cust_Satisf_ChkBox.setSelected(false);
                    L1_Warranty_ChkBox.setSelected(false);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L1_First_Sku.getText().matches("[0-9]+") && L1_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L1_First_Sku.setBackground(Color.WHITE);
                L1_First_Desc.setText(EBASdao.prodName);
                
             if (L1_Qty_Out.getText().matches("[1-9]+") && L1_Qty_Out.getText().length() < 2 ) {
                 L1_Qty_Out.setBackground(Color.WHITE);
                
            if (EBASdao.skuRslt2.equals("good") && L1_Orig_Sku.getText().matches("[0-9]+") && L1_Orig_Sku.getText().length() < 7 ) {
                L1_Orig_Sku.setBackground(Color.WHITE);
                L1_Orig_Desc.setText(EBASdao.prodName2);
                L1_Orig_Attr.setText(EBASdao.prodAttr);
                L1_Orig_Size.setText(EBASdao.prodSize);
                L1_Orig_Retail.setText(EBASdao.prodPr);
                
            if (L1_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L1_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L1_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L1_Manuf_Inspec.getText().matches("[0-9 ]+") || L1_Manuf_Inspec.getText().equals("")) {
               // (L1_Manuf_Inspec.getText().matches("[1-8 ]+") && L1_Manuf_Inspec.getText().length() <2 || L1_Manuf_Inspec.getText().equals("")) {
		L1_Manuf_Inspec.setBackground(Color.WHITE);
                
            // if((Integer.parseInt(L1_First_Sku.getText()) < 799610 || Integer.parseInt(L1_First_Sku.getText()) > 790407)
            //     && ((L1_Manuf_Inspec.getText().equals("DW03") || L1_Manuf_Inspec.getText().equals("dw03")) || (L1_Manuf_Inspec.getText().equals("DW04") || L1_Manuf_Inspec.getText().equals("dw04")))) {
            //     L1_Manuf_Inspec.setBackground(Color.WHITE);
                
            if (!L1_Reason_DropDown.getSelectedItem().equals("")) {
                    
            if (L1_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L1_Desc_Damage.setBackground(Color.WHITE);
             
                if (L1_Cust_Satisf_ChkBox.isSelected() != L1_Warranty_ChkBox.isSelected()){  
                    if (!L1_Cust_Satisf_ChkBox.isSelected() || !L1_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L1_First_Sku.getText();
                EBASdao.Field2 = L1_Qty_Out.getText();
                EBASdao.Field3 = L1_First_Desc.getText();
                EBASdao.Field4 = L1_Orig_Sku.getText();
                EBASdao.Field5 = L1_Orig_Desc.getText();
                EBASdao.Field6 = L1_Orig_Attr.getText();
                EBASdao.Field7 = L1_Orig_Size.getText();
                EBASdao.Field8 = L1_Orig_Retail.getText();                
                EBASdao.Field9 = L1_Manuf_Inspec.getText();
                EBASdao.Field10 = L1_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L1_Reason_DropDown.getSelectedItem().toString();                
                EBASdao.Field12 = L1_Desc_Damage.getText();
                
                if (L1_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L1_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L1.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L1.getBounds(null));
                    
            } else {
                JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                L1_Done_ChkBox.setSelected(false);
                subBtn.setEnabled(false);
            }                    
            } else {
                JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                L1_Done_ChkBox.setSelected(false);
                subBtn.setEnabled(false);
            }
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L1_Done_ChkBox.setSelected(false);
		L1_Desc_Damage.setBackground(Color.yellow);
                L1_Desc_Damage.requestFocus();
                subBtn.setEnabled(false);
            }            
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L1_Done_ChkBox.setSelected(false);
                L1_Reason_DropDown.requestFocus();
                subBtn.setEnabled(false);
            }
            /*
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                L1_Done_ChkBox.setSelected(false);
                L1_Manuf_Inspec.requestFocus();
                L1_Manuf_Inspec.setBackground(Color.yellow);
            }
            */
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
        	L1_Done_ChkBox.setSelected(false);
		L1_Manuf_Inspec.setBackground(Color.yellow);
                L1_Manuf_Inspec.requestFocus();
                subBtn.setEnabled(false);
            }
            } else {
                L1_Done_ChkBox.setSelected(false);
                L1_Orig_Sku.setBackground(Color.yellow);
                L1_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L1.getBounds(null));
                subBtn.setEnabled(false);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L1_Done_ChkBox.setSelected(false);
                L1_Qty_Out.setBackground(Color.yellow);
                L1_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L1.getBounds(null));
                subBtn.setEnabled(false);
            }
            } else {
                L1_Done_ChkBox.setSelected(false);
                L1_First_Sku.setBackground(Color.yellow);
                L1_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L1.getBounds(null));
                subBtn.setEnabled(false);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L1_Done_ChkBox.setSelected(false);
                L1_First_Sku.requestFocus();
                L1_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L1.getBounds(null));
                subBtn.setEnabled(false);
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
            L1_Manuf_Inspec.setEnabled(true);
            L1_New_Used.setEnabled(true);
            L1_Reason_DropDown.setEnabled(true);
            L1_Desc_Damage.setEnabled(true);           
            L1_Cust_Satisf_ChkBox.setEnabled(true);
            L1_Warranty_ChkBox.setEnabled(true);
            L1_Done_ChkBox.setEnabled(true);
            L1_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
            }
    }//GEN-LAST:event_L1_Done_ChkBoxKeyPressed

     // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L2_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Done_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L2_Done_ChkBox.setSelected(true);
            rChkLn2();
            if (L2_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L2_First_Sku.getText());
            EBASdao.setSkuInput2(L2_Orig_Sku.getText());
            EBASdao.lnFlg = 2;
            /*
            if (L2_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine2();
                    L2_Qty_Out.setText("");
                    L2_First_Desc.setText("");
                    L2_Orig_Sku.setText("");
                    L2_Orig_Desc.setText("");
                    L2_Orig_Attr.setText("");
                    L2_Orig_Size.setText("");
                    L2_Orig_Retail.setText("");
                    L2_Manuf_Inspec.setText("");
                    L2_New_Used.setSelectedItem("New");
                    L2_Reason_DropDown.setSelectedItem("");
                    L2_Desc_Damage.setText("");
                    L2_Cust_Satisf_ChkBox.setSelected(false);
                    L2_Warranty_ChkBox.setSelected(false);
                    L2_Done_ChkBox.setSelected(false);
                    L2_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L2_First_Sku.getText().matches("[0-9]+") && L2_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L2_First_Sku.setBackground(Color.WHITE);
                L2_First_Desc.setText(EBASdao.prodName);
                
             if (L2_Qty_Out.getText().matches("[1-9]+") && L2_Qty_Out.getText().length() < 2 ) {
                 L2_Qty_Out.setBackground(Color.WHITE);
                
              if (EBASdao.skuRslt2.equals("good") && L2_Orig_Sku.getText().matches("[0-9]+") && L2_Orig_Sku.getText().length() < 7 ) {
                L2_Orig_Sku.setBackground(Color.WHITE);
                L2_Orig_Desc.setText(EBASdao.prodName2);
                L2_Orig_Attr.setText(EBASdao.prodAttr);
                L2_Orig_Size.setText(EBASdao.prodSize);
                L2_Orig_Retail.setText(EBASdao.prodPr);
                
                if (L2_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L2_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L2_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L2_Manuf_Inspec.getText().matches("[0-9 ]+") || L2_Manuf_Inspec.getText().equals("")) {
                // (L2_Manuf_Inspec.getText().matches("[1-8 ]+") && L2_Manuf_Inspec.getText().length() <2 || L2_Manuf_Inspec.getText().equals("")) {
		L2_Manuf_Inspec.setBackground(Color.WHITE);
                
             //   if((Integer.parseInt(L2_First_Sku.getText()) < 799610 || Integer.parseInt(L2_First_Sku.getText()) > 790407)
             //   && ((L2_Manuf_Inspec.getText().equals("DW03") || L2_Manuf_Inspec.getText().equals("dw03")) || (L2_Manuf_Inspec.getText().equals("DW04") || L2_Manuf_Inspec.getText().equals("dw04")))) {
             //   L2_Manuf_Inspec.setBackground(Color.WHITE);
                
                if (!L2_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L2_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L2_Desc_Damage.setBackground(Color.WHITE);
             
                if (L2_Cust_Satisf_ChkBox.isSelected() != L2_Warranty_ChkBox.isSelected()){  
                    if (!L2_Cust_Satisf_ChkBox.isSelected() || !L2_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L2_First_Sku.getText();
                EBASdao.Field2 = L2_Qty_Out.getText();
                EBASdao.Field3 = L2_First_Desc.getText();
                EBASdao.Field4 = L2_Orig_Sku.getText();
                EBASdao.Field5 = L2_Orig_Desc.getText();
                EBASdao.Field6 = L2_Orig_Attr.getText();
                EBASdao.Field7 = L2_Orig_Size.getText();
                EBASdao.Field8 = L2_Orig_Retail.getText();                
                EBASdao.Field9 = L2_Manuf_Inspec.getText();                
                EBASdao.Field10 = L2_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L2_Reason_DropDown.getSelectedItem().toString();
                EBASdao.Field12 = L2_Desc_Damage.getText();
                                
                if (L2_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L2_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L2.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L2.getBounds(null));
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L2_Done_ChkBox.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L2_Done_ChkBox.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                    L2_Done_ChkBox.setSelected(false);
                    L2_Desc_Damage.setBackground(Color.yellow);
                    L2_Desc_Damage.requestFocus();
                }                
                } else {
                    JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                    L2_Done_ChkBox.setSelected(false);
                    L2_Reason_DropDown.requestFocus();
                }
                /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
                */
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
                    L2_Done_ChkBox.setSelected(false);
                    L2_Manuf_Inspec.setBackground(Color.yellow);
                    L2_Manuf_Inspec.requestFocus();
                }
             } else {
                L2_Done_ChkBox.setSelected(false);
                L2_Orig_Sku.setBackground(Color.yellow);
                L2_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L2.getBounds(null));
            }
            } else {
                 JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L2_Done_ChkBox.setSelected(false);
                L2_Qty_Out.setBackground(Color.yellow);
                L2_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L2.getBounds(null));
            }
            } else {
                L2_Done_ChkBox.setSelected(false);
                L2_First_Sku.setBackground(Color.yellow);
                L2_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L2.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L2_Done_ChkBox.setSelected(false);
                L2_First_Sku.requestFocus();
                L2_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L2.getBounds(null));
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
            L2_Manuf_Inspec.setEnabled(true);
            L2_New_Used.setEnabled(true);
            L2_Reason_DropDown.setEnabled(true);
            L2_Desc_Damage.setEnabled(true);           
            L2_Cust_Satisf_ChkBox.setEnabled(true);
            L2_Warranty_ChkBox.setEnabled(true);
            L2_Done_ChkBox.setEnabled(true);
            L2_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
        }       
    }//GEN-LAST:event_L2_Done_ChkBoxKeyPressed

    // advances to the next field
    private void L1_Cust_Satisf_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Cust_Satisf_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L1_Cust_Satisf_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L1_Cust_Satisf_ChkBoxKeyPressed

    // advances to the next field
    private void L1_Warranty_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Warranty_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L1_Warranty_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L1_Warranty_ChkBoxKeyPressed

    // advances to the next field
    private void L2_Cust_Satisf_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Cust_Satisf_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L2_Cust_Satisf_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L2_Cust_Satisf_ChkBoxKeyPressed

    // advances to the next field
    private void L2_Warranty_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Warranty_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L2_Warranty_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L2_Warranty_ChkBoxKeyPressed
    
    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L3_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_First_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput(L3_First_Sku.getText());
            String[] args = null;
            if (L3_First_Sku.getText().matches("[0-9]+") && L3_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L3_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L3_First_Desc.setText(EBASdao.prodName);
                L3_Qty_Out.requestFocus();
            } else {
                L3_First_Sku.setBackground(Color.yellow);
                L3_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU");
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter 6 digits");
                L3_First_Sku.setBackground(Color.yellow);
                L3_First_Sku.requestFocus();
            }
        }                           
    }//GEN-LAST:event_L3_First_SkuKeyPressed

    // advances to the next field
    private void L3_Cust_Satisf_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Cust_Satisf_ChkBoxKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L3_Cust_Satisf_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L3_Cust_Satisf_ChkBoxKeyPressed

    // advances to the next field
    private void L3_Warranty_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Warranty_ChkBoxKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L3_Warranty_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L3_Warranty_ChkBoxKeyPressed
   
     // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L3_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Done_ChkBoxKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L3_Done_ChkBox.setSelected(true);
            rChkLn3();
           if (L3_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L3_First_Sku.getText());
            EBASdao.setSkuInput2(L3_Orig_Sku.getText());
            EBASdao.lnFlg = 3;
            /*
            if (L3_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine3();
                    L3_Qty_Out.setText("");
                    L3_First_Desc.setText("");
                    L3_Orig_Sku.setText("");
                    L3_Orig_Desc.setText("");
                    L3_Orig_Attr.setText("");
                    L3_Orig_Size.setText("");
                    L3_Orig_Retail.setText("");
                    L3_Manuf_Inspec.setText("");
                    L3_New_Used.setSelectedItem("New");
                    L3_Reason_DropDown.setSelectedItem("");
                    L3_Desc_Damage.setText("");
                    L3_Cust_Satisf_ChkBox.setSelected(false);
                    L3_Warranty_ChkBox.setSelected(false);
                    L3_Done_ChkBox.setSelected(false);
                    L3_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L3_First_Sku.getText().matches("[0-9]+") && L3_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L3_First_Sku.setBackground(Color.WHITE);
                L3_First_Desc.setText(EBASdao.prodName);
                
             if (L3_Qty_Out.getText().matches("[1-9]+") && L3_Qty_Out.getText().length() < 2 ) {
                 L3_Qty_Out.setBackground(Color.WHITE);
                
              if (EBASdao.skuRslt2.equals("good") && L3_Orig_Sku.getText().matches("[0-9]+") && L3_Orig_Sku.getText().length() < 7 ) {
                L3_Orig_Sku.setBackground(Color.WHITE);
                L3_Orig_Desc.setText(EBASdao.prodName2);
                L3_Orig_Attr.setText(EBASdao.prodAttr);
                L3_Orig_Size.setText(EBASdao.prodSize);
                L3_Orig_Retail.setText(EBASdao.prodPr);
                
                if (L3_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L3_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L3_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L3_Manuf_Inspec.getText().matches("[0-9 ]+") || L3_Manuf_Inspec.getText().equals("")) {
                //  (L3_Manuf_Inspec.getText().matches("[1-8 ]+") && L3_Manuf_Inspec.getText().length() <2 {
		L3_Manuf_Inspec.setBackground(Color.WHITE);
                
              //  if((Integer.parseInt(L3_First_Sku.getText()) < 799610 || Integer.parseInt(L3_First_Sku.getText()) > 790407)
              //  && ((L3_Manuf_Inspec.getText().equals("DW03") || L3_Manuf_Inspec.getText().equals("dw03")) || (L3_Manuf_Inspec.getText().equals("DW04") || L3_Manuf_Inspec.getText().equals("dw04")))) {
              //  L3_Manuf_Inspec.setBackground(Color.WHITE);
                
                if (!L3_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L3_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L3_Desc_Damage.setBackground(Color.WHITE);
             
                if (L3_Cust_Satisf_ChkBox.isSelected() != L3_Warranty_ChkBox.isSelected()){  
                    if (!L3_Cust_Satisf_ChkBox.isSelected() || !L3_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L3_First_Sku.getText();
                EBASdao.Field2 = L3_Qty_Out.getText();
                EBASdao.Field3 = L3_First_Desc.getText();
                EBASdao.Field4 = L3_Orig_Sku.getText();
                EBASdao.Field5 = L3_Orig_Desc.getText();
                EBASdao.Field6 = L3_Orig_Attr.getText();
                EBASdao.Field7 = L3_Orig_Size.getText();
                EBASdao.Field8 = L3_Orig_Retail.getText();
                EBASdao.Field9 = L3_Manuf_Inspec.getText();
                EBASdao.Field10 = L3_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L3_Reason_DropDown.getSelectedItem().toString();
                EBASdao.Field12 = L3_Desc_Damage.getText();                
                
                if (L3_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L3_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L3.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L3.getBounds(null));
                    
            } else {
                JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
            }
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L3_Done_ChkBox.setSelected(false);
		L3_Desc_Damage.setBackground(Color.yellow);
                L3_Desc_Damage.requestFocus();
            }            
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
                L3_Reason_DropDown.requestFocus();
            }
            /*
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                L1_Done_ChkBox.setSelected(false);
                L1_Manuf_Inspec.requestFocus();
                L1_Manuf_Inspec.setBackground(Color.yellow);
            }
            */
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
        	L3_Done_ChkBox.setSelected(false);
		L3_Manuf_Inspec.setBackground(Color.yellow);
                L3_Manuf_Inspec.requestFocus();
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
                L3_Orig_Sku.setBackground(Color.yellow);
                L3_Orig_Sku.requestFocus(); 
                jPanel6.scrollRectToVisible(L3.getBounds(null));
            }
            } else {
                 JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
                L3_Qty_Out.setBackground(Color.yellow);
                L3_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L3.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
                L3_First_Sku.setBackground(Color.yellow);
                L3_First_Sku.requestFocus();  
                jPanel6.scrollRectToVisible(L3.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
                L3_First_Sku.requestFocus();
                L3_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L3.getBounds(null));
            }
          //  }
        } else {
            L3_First_Sku.setEnabled(true);
            L3_Qty_Out.setEnabled(true);
            L3_First_Desc.setEnabled(true);
            L3_Orig_Sku.setEnabled(true);
            L3_Orig_Desc.setEnabled(true);
            L3_Orig_Attr.setEnabled(true);
            L3_Orig_Size.setEnabled(true);
            L3_Orig_Retail.setEnabled(true);
            L3_Manuf_Inspec.setEnabled(true);
            L3_New_Used.setEnabled(true);
            L3_Reason_DropDown.setEnabled(true);
            L3_Desc_Damage.setEnabled(true);           
            L3_Cust_Satisf_ChkBox.setEnabled(true);
            L3_Warranty_ChkBox.setEnabled(true);
            L3_Done_ChkBox.setEnabled(true);
            L3_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
        }
    }//GEN-LAST:event_L3_Done_ChkBoxKeyPressed

     // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L3_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L3_Done_ChkBoxActionPerformed
         rChkLn3();
        if (L3_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L3_First_Sku.getText());
            EBASdao.setSkuInput2(L3_Orig_Sku.getText());
            EBASdao.lnFlg = 3;
            /*
            if (L3_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine3();
                    L3_Qty_Out.setText("");
                    L3_First_Desc.setText("");
                    L3_Orig_Sku.setText("");
                    L3_Orig_Desc.setText("");
                    L3_Orig_Attr.setText("");
                    L3_Orig_Size.setText("");
                    L3_Orig_Retail.setText("");
                    L3_Manuf_Inspec.setText("");
                    L3_New_Used.setSelectedItem("New");
                    L3_Reason_DropDown.setSelectedItem("");
                    L3_Desc_Damage.setText("");
                    L3_Cust_Satisf_ChkBox.setSelected(false);
                    L3_Warranty_ChkBox.setSelected(false);
                    L3_Done_ChkBox.setSelected(false);
                    L3_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L3_First_Sku.getText().matches("[0-9]+") && L3_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L3_First_Sku.setBackground(Color.WHITE);
                L3_First_Desc.setText(EBASdao.prodName);
                
             if (L3_Qty_Out.getText().matches("[1-9]+") && L3_Qty_Out.getText().length() < 2 ) {
                 L3_Qty_Out.setBackground(Color.WHITE);
                
              if (EBASdao.skuRslt2.equals("good") && L3_Orig_Sku.getText().matches("[0-9]+") && L3_Orig_Sku.getText().length() < 7 ) {
                L3_Orig_Sku.setBackground(Color.WHITE);
                L3_Orig_Desc.setText(EBASdao.prodName2);
                L3_Orig_Attr.setText(EBASdao.prodAttr);
                L3_Orig_Size.setText(EBASdao.prodSize);
                L3_Orig_Retail.setText(EBASdao.prodPr);
                
                if (L3_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L3_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L3_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L3_Manuf_Inspec.getText().matches("[0-9 ]+") || L3_Manuf_Inspec.getText().equals("")) {
                // (L3_Manuf_Inspec.getText().matches("[1-8 ]+") && L3_Manuf_Inspec.getText().length() <2  {
		L3_Manuf_Inspec.setBackground(Color.WHITE);
                
             //   if((Integer.parseInt(L3_First_Sku.getText()) < 799610 || Integer.parseInt(L3_First_Sku.getText()) > 790407)
             //   && ((L3_Manuf_Inspec.getText().equals("DW03") || L3_Manuf_Inspec.getText().equals("dw03")) || (L3_Manuf_Inspec.getText().equals("DW04") || L3_Manuf_Inspec.getText().equals("dw04")))) {
             //   L3_Manuf_Inspec.setBackground(Color.WHITE);
                
                if (!L3_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L3_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L3_Desc_Damage.setBackground(Color.WHITE);
             
                if (L3_Cust_Satisf_ChkBox.isSelected() != L3_Warranty_ChkBox.isSelected()){  
                    if (!L3_Cust_Satisf_ChkBox.isSelected() || !L3_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L3_First_Sku.getText();
                EBASdao.Field2 = L3_Qty_Out.getText();
                EBASdao.Field3 = L3_First_Desc.getText();
                EBASdao.Field4 = L3_Orig_Sku.getText();
                EBASdao.Field5 = L3_Orig_Desc.getText();
                EBASdao.Field6 = L3_Orig_Attr.getText();
                EBASdao.Field7 = L3_Orig_Size.getText();
                EBASdao.Field8 = L3_Orig_Retail.getText();                
                EBASdao.Field9 = L3_Manuf_Inspec.getText();                
                EBASdao.Field10 = L3_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L3_Reason_DropDown.getSelectedItem().toString();                
                EBASdao.Field12 = L3_Desc_Damage.getText();                
                
                if (L3_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L3_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L3.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L3.getBounds(null));
                    
            } else {
                JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
            }
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L3_Done_ChkBox.setSelected(false);
		L3_Desc_Damage.setBackground(Color.yellow);
                L3_Desc_Damage.requestFocus();
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
                L3_Reason_DropDown.requestFocus();
            }
            /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
            */
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
        	L3_Done_ChkBox.setSelected(false);
		L3_Manuf_Inspec.setBackground(Color.yellow);
                L3_Manuf_Inspec.requestFocus();
            }            
            } else {
                L3_Done_ChkBox.setSelected(false);
                L3_Orig_Sku.setBackground(Color.yellow);
                L3_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L4.getBounds(null));
            }
            } else {
                 JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
                L3_Qty_Out.setBackground(Color.yellow);
                L3_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L4.getBounds(null));
            }
            } else {
                L3_Done_ChkBox.setSelected(false);
                L3_First_Sku.setBackground(Color.yellow);
                L3_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L4.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
                L3_First_Sku.requestFocus();
                L3_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L4.getBounds(null));
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
            L3_Manuf_Inspec.setEnabled(true);
            L3_New_Used.setEnabled(true);
            L3_Reason_DropDown.setEnabled(true);
            L3_Desc_Damage.setEnabled(true);           
            L3_Cust_Satisf_ChkBox.setEnabled(true);
            L3_Warranty_ChkBox.setEnabled(true);
            L3_Done_ChkBox.setEnabled(true);
            L3_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
    }//GEN-LAST:event_L3_Done_ChkBoxActionPerformed

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L4_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_First_SkuKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput(L4_First_Sku.getText());
            String[] args = null;
            if (L4_First_Sku.getText().matches("[0-9]+") && L4_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L4_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L4_First_Desc.setText(EBASdao.prodName);
                L4_Qty_Out.requestFocus();
            } else {
                L4_First_Sku.setBackground(Color.yellow);
                L4_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L4_First_Sku.setBackground(Color.yellow);
                L4_First_Sku.requestFocus();
            }
        }
    }//GEN-LAST:event_L4_First_SkuKeyPressed

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L5_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_First_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput(L5_First_Sku.getText());
            String[] args = null;
            if (L5_First_Sku.getText().matches("[0-9]+") && L5_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L5_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L5_First_Desc.setText(EBASdao.prodName);
                L5_Qty_Out.requestFocus();
            } else {
                L5_First_Sku.setBackground(Color.yellow);
                L5_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L5_First_Sku.setBackground(Color.yellow);
                L5_First_Sku.requestFocus();
            }
        }
    }//GEN-LAST:event_L5_First_SkuKeyPressed

    // advances to the next field
    private void L4_Cust_Satisf_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Cust_Satisf_ChkBoxKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L4_Cust_Satisf_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L4_Cust_Satisf_ChkBoxKeyPressed

    // advances to the next field
    private void L4_Warranty_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Warranty_ChkBoxKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L4_Warranty_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L4_Warranty_ChkBoxKeyPressed

     // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L4_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Done_ChkBoxKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L4_Done_ChkBox.setSelected(true);
            rChkLn4();
           if (L4_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L4_First_Sku.getText());
            EBASdao.setSkuInput2(L4_Orig_Sku.getText());
            EBASdao.lnFlg = 4;
            /*
            if (L4_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine4();
                    L4_Qty_Out.setText("");
                    L4_First_Desc.setText("");
                    L4_Orig_Sku.setText("");
                    L4_Orig_Desc.setText("");
                    L4_Orig_Attr.setText("");
                    L4_Orig_Size.setText("");
                    L4_Orig_Retail.setText("");
                    L4_Manuf_Inspec.setText("");
                    L4_New_Used.setSelectedItem("New");
                    L4_Reason_DropDown.setSelectedItem("");
                    L4_Desc_Damage.setText("");
                    L4_Cust_Satisf_ChkBox.setSelected(false);
                    L4_Warranty_ChkBox.setSelected(false);
                    L4_Done_ChkBox.setSelected(false);
                    L4_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L4_First_Sku.getText().matches("[0-9]+") && L4_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L4_First_Sku.setBackground(Color.WHITE);
                L4_First_Desc.setText(EBASdao.prodName);
                
             if (L4_Qty_Out.getText().matches("[1-9]+") && L4_Qty_Out.getText().length() < 2 ) {
                L4_Qty_Out.setBackground(Color.WHITE);
                
              if (EBASdao.skuRslt2.equals("good") && L4_Orig_Sku.getText().matches("[0-9]+") && L4_Orig_Sku.getText().length() < 7 ) {
                L4_Orig_Sku.setBackground(Color.WHITE);
                L4_Orig_Desc.setText(EBASdao.prodName2);
                L4_Orig_Attr.setText(EBASdao.prodAttr);
                L4_Orig_Size.setText(EBASdao.prodSize);
                L4_Orig_Retail.setText(EBASdao.prodPr);
                
                if (L4_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L4_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                || L4_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L4_Manuf_Inspec.getText().matches("[0-9 ]+") || L4_Manuf_Inspec.getText().equals("")) {
                // (L4_Manuf_Inspec.getText().matches("[1-8 ]+") && L4_Manuf_Inspec.getText().length() <2 {
		L4_Manuf_Inspec.setBackground(Color.WHITE);
                
             //   if((Integer.parseInt(L4_First_Sku.getText()) < 799610 || Integer.parseInt(L4_First_Sku.getText()) > 790407)
             //  && ((L4_Manuf_Inspec.getText().equals("DW03") || L4_Manuf_Inspec.getText().equals("dw03")) || (L4_Manuf_Inspec.getText().equals("DW04") || L4_Manuf_Inspec.getText().equals("dw04")))) {
             //   L4_Manuf_Inspec.setBackground(Color.WHITE);
                
                if (!L4_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L4_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L4_Desc_Damage.setBackground(Color.WHITE);
             
                if (L4_Cust_Satisf_ChkBox.isSelected() != L4_Warranty_ChkBox.isSelected()){  
                    if (!L4_Cust_Satisf_ChkBox.isSelected() || !L4_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L4_First_Sku.getText();
                EBASdao.Field2 = L4_Qty_Out.getText();
                EBASdao.Field3 = L4_First_Desc.getText();
                EBASdao.Field4 = L4_Orig_Sku.getText();
                EBASdao.Field5 = L4_Orig_Desc.getText();
                EBASdao.Field6 = L4_Orig_Attr.getText();
                EBASdao.Field7 = L4_Orig_Size.getText();
                EBASdao.Field8 = L4_Orig_Retail.getText();                
                EBASdao.Field9 = L4_Manuf_Inspec.getText();                
                EBASdao.Field10 = L4_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L4_Reason_DropDown.getSelectedItem().toString();                
                EBASdao.Field12 = L4_Desc_Damage.getText();
                                
                if (L4_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L4_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L4.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L4_First_Sku.getBounds(null));
                    
            } else {
                JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                L4_Done_ChkBox.setSelected(false);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                L4_Done_ChkBox.setSelected(false);
            }
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L4_Done_ChkBox.setSelected(false);
		L4_Desc_Damage.setBackground(Color.yellow);
                L4_Desc_Damage.requestFocus();
            }              
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L4_Done_ChkBox.setSelected(false);
                L4_Reason_DropDown.requestFocus();
            }
            /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
            */
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
        	L4_Done_ChkBox.setSelected(false);
		L4_Manuf_Inspec.setBackground(Color.yellow);
                L4_Manuf_Inspec.requestFocus();
            }
            } else {
                L4_Done_ChkBox.setSelected(false);
                L4_Orig_Sku.setBackground(Color.yellow);
                L4_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L4.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L4_Done_ChkBox.setSelected(false);
                L4_Qty_Out.setBackground(Color.yellow);
                L4_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L4.getBounds(null));
            }
            } else {
                L4_Done_ChkBox.setSelected(false);
                L4_First_Sku.setBackground(Color.yellow);
                L4_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L4.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L4_Done_ChkBox.setSelected(false);
                L4_First_Sku.requestFocus();
                L4_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L4.getBounds(null));
            }
          //  }
        } else {
            L4_First_Sku.setEnabled(true);
            L4_Qty_Out.setEnabled(true);
            L4_First_Desc.setEnabled(true);
            L4_Orig_Sku.setEnabled(true);
            L4_Orig_Desc.setEnabled(true);
            L4_Orig_Attr.setEnabled(true);
            L4_Orig_Size.setEnabled(true);
            L4_Orig_Retail.setEnabled(true);
            L4_Manuf_Inspec.setEnabled(true);
            L4_New_Used.setEnabled(true);
            L4_Reason_DropDown.setEnabled(true);
            L4_Desc_Damage.setEnabled(true);           
            L4_Cust_Satisf_ChkBox.setEnabled(true);
            L4_Warranty_ChkBox.setEnabled(true);
            L4_Done_ChkBox.setEnabled(true);
            L4_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
       }
    }//GEN-LAST:event_L4_Done_ChkBoxKeyPressed

     // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L4_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L4_Done_ChkBoxActionPerformed
        rChkLn4();
        if (L4_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L4_First_Sku.getText());
            EBASdao.setSkuInput2(L4_Orig_Sku.getText());
            EBASdao.lnFlg = 4;
            /*
            if (L4_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine4();
                    L4_Qty_Out.setText("");
                    L4_First_Desc.setText("");
                    L4_Orig_Sku.setText("");
                    L4_Orig_Desc.setText("");
                    L4_Orig_Attr.setText("");
                    L4_Orig_Size.setText("");
                    L4_Orig_Retail.setText("");
                    L4_Manuf_Inspec.setText("");
                    L4_New_Used.setSelectedItem("New");
                    L4_Reason_DropDown.setSelectedItem("");
                    L4_Desc_Damage.setText("");
                    L4_Cust_Satisf_ChkBox.setSelected(false);
                    L4_Warranty_ChkBox.setSelected(false);
                    L4_Done_ChkBox.setSelected(false);
                    L4_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L4_First_Sku.getText().matches("[0-9]+") && L4_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L4_First_Sku.setBackground(Color.WHITE);
                L4_First_Desc.setText(EBASdao.prodName);
                
            if (L4_Qty_Out.getText().matches("[1-9]+") && L4_Qty_Out.getText().length() < 2 ) {
                 L4_Qty_Out.setBackground(Color.WHITE);
                
            if (EBASdao.skuRslt2.equals("good") && L4_Orig_Sku.getText().matches("[0-9]+") && L4_Orig_Sku.getText().length() < 7 ) {
                L4_Orig_Sku.setBackground(Color.WHITE);
                L4_Orig_Desc.setText(EBASdao.prodName2);
                L4_Orig_Attr.setText(EBASdao.prodAttr);
                L4_Orig_Size.setText(EBASdao.prodSize);
                L4_Orig_Retail.setText(EBASdao.prodPr);
                
                
            if  (L4_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L4_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                || L4_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L4_Manuf_Inspec.getText().matches("[0-9 ]+") || L4_Manuf_Inspec.getText().equals("")) {
                /* (L4_Manuf_Inspec.getText().matches("[1-8 ]+") && L4_Manuf_Inspec.getText().length() <2 */ 
		L4_Manuf_Inspec.setBackground(Color.WHITE);
                
          //  if((Integer.parseInt(L4_First_Sku.getText()) < 799610 || Integer.parseInt(L4_First_Sku.getText()) > 790407)
          //      && ((L4_Manuf_Inspec.getText().equals("DW03") || L4_Manuf_Inspec.getText().equals("dw03")) || (L4_Manuf_Inspec.getText().equals("DW04") || L4_Manuf_Inspec.getText().equals("dw04")))) {
          //      L4_Manuf_Inspec.setBackground(Color.WHITE);    
                
            if (!L4_Reason_DropDown.getSelectedItem().equals("")) {
                    
            if (L4_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L4_Desc_Damage.setBackground(Color.WHITE);
                if (L4_Cust_Satisf_ChkBox.isSelected() != L4_Warranty_ChkBox.isSelected()){  
                if (!L4_Cust_Satisf_ChkBox.isSelected() || !L4_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L4_First_Sku.getText();
                EBASdao.Field2 = L4_Qty_Out.getText();
                EBASdao.Field3 = L4_First_Desc.getText();
                EBASdao.Field4 = L4_Orig_Sku.getText();
                EBASdao.Field5 = L4_Orig_Desc.getText();
                EBASdao.Field6 = L4_Orig_Attr.getText();
                EBASdao.Field7 = L4_Orig_Size.getText();
                EBASdao.Field8 = L4_Orig_Retail.getText();
                EBASdao.Field9 = L4_Manuf_Inspec.getText();
                EBASdao.Field10 = L4_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L4_Reason_DropDown.getSelectedItem().toString();
                EBASdao.Field12 = L4_Desc_Damage.getText();                
                
                if (L4_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L4_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L4.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L4.getBounds(null));
                    
            } else {
                JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                L4_Done_ChkBox.setSelected(false);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                L4_Done_ChkBox.setSelected(false);
            }
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L4_Done_ChkBox.setSelected(false);
		L4_Desc_Damage.setBackground(Color.yellow);
                L4_Desc_Damage.requestFocus();
            }               
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L4_Done_ChkBox.setSelected(false);
                L4_Reason_DropDown.requestFocus();
            }            
            /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
            */
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
        	L4_Done_ChkBox.setSelected(false);
		L4_Manuf_Inspec.setBackground(Color.yellow);
                L4_Manuf_Inspec.requestFocus();
            }
            } else {
                L4_Done_ChkBox.setSelected(false);
                L4_Orig_Sku.setBackground(Color.yellow);
                L4_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L4.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L4_Done_ChkBox.setSelected(false);
                L4_Qty_Out.setBackground(Color.yellow);
                L4_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L4.getBounds(null));
            }
            } else {
                L4_Done_ChkBox.setSelected(false);
                L4_First_Sku.setBackground(Color.yellow);
                L4_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L4.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L4_Done_ChkBox.setSelected(false);
                L4_First_Sku.requestFocus();
                L4_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L4.getBounds(null));
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
            L4_Manuf_Inspec.setEnabled(true);
            L4_New_Used.setEnabled(true);
            L4_Reason_DropDown.setEnabled(true);
            L4_Desc_Damage.setEnabled(true);           
            L4_Cust_Satisf_ChkBox.setEnabled(true);
            L4_Warranty_ChkBox.setEnabled(true);
            L4_Done_ChkBox.setEnabled(true);
            L4_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
    }//GEN-LAST:event_L4_Done_ChkBoxActionPerformed

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L1_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Orig_SkuKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput2(L1_Orig_Sku.getText());
            if (!L1_Orig_Sku.getText().equals(L1_First_Sku.getText())) {
                 if (L1_Orig_Sku.getText().matches("[0-9]+") && L1_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L1_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L1_Orig_Desc.setText(EBASdao.prodName2);
                L1_Orig_Attr.setText(EBASdao.prodAttr);
                L1_Orig_Size.setText(EBASdao.prodSize);
                L1_Orig_Retail.setText(EBASdao.prodPr);
                L1_Manuf_Inspec.requestFocus();
                jPanel6.scrollRectToVisible(L1_Timestamp.getBounds(null));
            } else {
                L1_Orig_Sku.setBackground(Color.yellow);
                L1_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L1_Orig_Sku.setBackground(Color.yellow);
                L1_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L1_Orig_Sku.setBackground(Color.yellow);
                L1_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
            }
         }
    }//GEN-LAST:event_L1_Orig_SkuKeyPressed

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L2_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Orig_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput2(L2_Orig_Sku.getText());
            if (!L2_Orig_Sku.getText().equals(L2_First_Sku.getText())) {
                if (L2_Orig_Sku.getText().matches("[0-9]+") && L2_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L2_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L2_Orig_Desc.setText(EBASdao.prodName2);
                L2_Orig_Attr.setText(EBASdao.prodAttr);
                L2_Orig_Size.setText(EBASdao.prodSize);
                L2_Orig_Retail.setText(EBASdao.prodPr);
                L2_Manuf_Inspec.requestFocus();
                jPanel6.scrollRectToVisible(L2_Timestamp.getBounds(null));
            } else {
                L2_Orig_Sku.setBackground(Color.yellow);
                L2_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L2_Orig_Sku.setBackground(Color.yellow);
                L2_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L2_Orig_Sku.setBackground(Color.yellow);
                L2_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L2_Orig_SkuKeyPressed

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L3_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Orig_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput2(L3_Orig_Sku.getText());
            if (!L3_Orig_Sku.getText().equals(L3_First_Sku.getText())) {
                if (L3_Orig_Sku.getText().matches("[0-9]+") && L3_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L3_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L3_Orig_Desc.setText(EBASdao.prodName2);
                L3_Orig_Attr.setText(EBASdao.prodAttr);
                L3_Orig_Size.setText(EBASdao.prodSize);
                L3_Orig_Retail.setText(EBASdao.prodPr);
                L3_Manuf_Inspec.requestFocus();
                jPanel6.scrollRectToVisible(L3_Timestamp.getBounds(null));
            } else {                
                L3_Orig_Sku.setBackground(Color.yellow);
                L3_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
                } else {
                    L3_Orig_Sku.setBackground(Color.yellow);
                    L3_Orig_Sku.requestFocus();
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                }
        } else {
                L3_Orig_Sku.setBackground(Color.yellow);
                L3_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L3_Orig_SkuKeyPressed

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L4_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Orig_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput2(L4_Orig_Sku.getText());
            if (!L4_Orig_Sku.getText().equals(L4_First_Sku.getText())) {
                if (L4_Orig_Sku.getText().matches("[0-9]+") && L4_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L4_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L4_Orig_Desc.setText(EBASdao.prodName2);
                L4_Orig_Attr.setText(EBASdao.prodAttr);
                L4_Orig_Size.setText(EBASdao.prodSize);
                L4_Orig_Retail.setText(EBASdao.prodPr);
                L4_Manuf_Inspec.requestFocus();
                jPanel6.scrollRectToVisible(L4_Timestamp.getBounds(null));
            } else {
                L4_Orig_Sku.setBackground(Color.yellow);
                L4_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
                } else {
                    L4_Orig_Sku.setBackground(Color.yellow);
                    L4_Orig_Sku.requestFocus();
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                L4_Orig_Sku.setBackground(Color.yellow);
                L4_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L4_Orig_SkuKeyPressed

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L5_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Orig_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput2(L5_Orig_Sku.getText());
            if (!L5_Orig_Sku.getText().equals(L5_First_Sku.getText())) {
                if (L5_Orig_Sku.getText().matches("[0-9]+") && L5_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L5_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L5_Orig_Desc.setText(EBASdao.prodName2);
                L5_Orig_Attr.setText(EBASdao.prodAttr);
                L5_Orig_Size.setText(EBASdao.prodSize);
                L5_Orig_Retail.setText(EBASdao.prodPr);
                L5_Manuf_Inspec.requestFocus();
                jPanel6.scrollRectToVisible(L5_Timestamp.getBounds(null));
            } else {
                L5_Orig_Sku.setBackground(Color.yellow);
                L5_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
                } else {
                    L5_Orig_Sku.setBackground(Color.yellow);
                    L5_Orig_Sku.requestFocus();
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                }
        } else {
                 L5_Orig_Sku.setBackground(Color.yellow);
                 L5_Orig_Sku.requestFocus();
                 JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
             }
       }
    }//GEN-LAST:event_L5_Orig_SkuKeyPressed

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L6_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Orig_SkuKeyPressed
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput2(L6_Orig_Sku.getText());
            if (!L6_Orig_Sku.getText().equals(L6_First_Sku.getText())) {
                if (L6_Orig_Sku.getText().matches("[0-9]+") && L6_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L6_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L6_Orig_Desc.setText(EBASdao.prodName2);
                L6_Orig_Attr.setText(EBASdao.prodAttr);
                L6_Orig_Size.setText(EBASdao.prodSize);
                L6_Orig_Retail.setText(EBASdao.prodPr);
                L6_Manuf_Inspec.requestFocus();
                jPanel6.scrollRectToVisible(L6_Timestamp.getBounds(null));
            } else {
                L6_Orig_Sku.setBackground(Color.yellow);
                L6_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L6_Orig_Sku.setBackground(Color.yellow);
                L6_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                L6_Orig_Sku.setBackground(Color.yellow);
                L6_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L6_Orig_SkuKeyPressed

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L7_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Orig_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput2(L7_Orig_Sku.getText());
            if (!L7_Orig_Sku.getText().equals(L7_First_Sku.getText())) {
                if (L7_Orig_Sku.getText().matches("[0-9]+") && L7_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L7_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L7_Orig_Desc.setText(EBASdao.prodName2);
                L7_Orig_Attr.setText(EBASdao.prodAttr);
                L7_Orig_Size.setText(EBASdao.prodSize);
                L7_Orig_Retail.setText(EBASdao.prodPr);
                L7_Manuf_Inspec.requestFocus();
                jPanel6.scrollRectToVisible(L7_Timestamp.getBounds(null));
            } else {
                L7_Orig_Sku.setBackground(Color.yellow);
                L7_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
                } else {
                L7_Orig_Sku.setBackground(Color.yellow);
                L7_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L7_Orig_Sku.setBackground(Color.yellow);
                L7_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L7_Orig_SkuKeyPressed

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L6_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_First_SkuKeyPressed
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput(L6_First_Sku.getText());
            String[] args = null;
            if (L6_First_Sku.getText().matches("[0-9]+") && L6_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L6_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L6_First_Desc.setText(EBASdao.prodName);
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

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L7_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_First_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput(L7_First_Sku.getText());
            String[] args = null;
            if (L7_First_Sku.getText().matches("[0-9]+") && L7_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L7_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L7_First_Desc.setText(EBASdao.prodName);
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

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L8_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_First_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput(L8_First_Sku.getText());
            String[] args = null;
            if (L8_First_Sku.getText().matches("[0-9]+") && L8_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L8_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L8_First_Desc.setText(EBASdao.prodName);
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

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L9_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_First_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput(L9_First_Sku.getText());
            String[] args = null;
            if (L9_First_Sku.getText().matches("[0-9]+") && L9_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L9_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L9_First_Desc.setText(EBASdao.prodName);
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

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L10_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_First_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput(L10_First_Sku.getText());
            String[] args = null;
            if (L10_First_Sku.getText().matches("[0-9]+") && L10_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L10_First_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L10_First_Desc.setText(EBASdao.prodName);
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

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L8_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Orig_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput2(L8_Orig_Sku.getText());
            if (!L8_Orig_Sku.getText().equals(L8_First_Sku.getText())) {
                if (L8_Orig_Sku.getText().matches("[0-9]+") && L8_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L8_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L8_Orig_Desc.setText(EBASdao.prodName2);
                L8_Orig_Attr.setText(EBASdao.prodAttr);
                L8_Orig_Size.setText(EBASdao.prodSize);
                L8_Orig_Retail.setText(EBASdao.prodPr);
                L8_Manuf_Inspec.requestFocus();
                jPanel6.scrollRectToVisible(L8_Timestamp.getBounds(null));
            } else {
                L8_Orig_Sku.setBackground(Color.yellow);
                L8_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L8_Orig_Sku.setBackground(Color.yellow);
                L8_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }            
            } else {
                L8_Orig_Sku.setBackground(Color.yellow);
                L8_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L8_Orig_SkuKeyPressed

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L9_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Orig_SkuKeyPressed
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput2(L9_Orig_Sku.getText());
            if (!L9_Orig_Sku.getText().equals(L9_First_Sku.getText())) {
            if (L9_Orig_Sku.getText().matches("[0-9]+") && L9_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L9_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L9_Orig_Desc.setText(EBASdao.prodName2);
                L9_Orig_Attr.setText(EBASdao.prodAttr);
                L9_Orig_Size.setText(EBASdao.prodSize);
                L9_Orig_Retail.setText(EBASdao.prodPr);
                L9_Manuf_Inspec.requestFocus();
                jPanel6.scrollRectToVisible(L9_Timestamp.getBounds(null));
            } else {
                L9_Orig_Sku.setBackground(Color.yellow);
                L9_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L9_Orig_Sku.setBackground(Color.yellow);
                L9_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L9_Orig_Sku.setBackground(Color.yellow);
                L9_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L9_Orig_SkuKeyPressed

    // checks and validates from EBASdao class if the sku is correct and inputs related sku data (like desc, size, attribute) 
    // in the fields to the right of the sku input field and returns an error if the sku is wrong
    private void L10_Orig_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Orig_SkuKeyPressed
     if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            EBASdao.setSkuInput2(L10_Orig_Sku.getText());
            if (!L10_Orig_Sku.getText().equals(L10_First_Sku.getText())) {
                if (L10_Orig_Sku.getText().matches("[0-9]+") && L10_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L10_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L10_Orig_Desc.setText(EBASdao.prodName2);
                L10_Orig_Attr.setText(EBASdao.prodAttr);
                L10_Orig_Size.setText(EBASdao.prodSize);
                L10_Orig_Retail.setText(EBASdao.prodPr);
                L10_Manuf_Inspec.requestFocus();
                jPanel6.scrollRectToVisible(L10_Timestamp.getBounds(null));
            } else {
                L10_Orig_Sku.setBackground(Color.yellow);
                L10_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L10_Orig_Sku.setBackground(Color.yellow);
                L10_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                L10_Orig_Sku.setBackground(Color.yellow);
                L10_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_L10_Orig_SkuKeyPressed

    // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L5_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L5_Done_ChkBoxActionPerformed
        rChkLn5();
        if (L5_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L5_First_Sku.getText());
            EBASdao.setSkuInput2(L5_Orig_Sku.getText());
            EBASdao.lnFlg = 5;
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
                    L5_Manuf_Inspec.setText("");
                    L5_New_Used.setSelectedItem("New");
                    L5_Reason_DropDown.setSelectedItem("");
                    L5_Desc_Damage.setText("");
                    L5_Cust_Satisf_ChkBox.setSelected(false);
                    L5_Warranty_ChkBox.setSelected(false);
                    L5_Done_ChkBox.setSelected(false);
                    L5_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L5_First_Sku.getText().matches("[0-9]+") && L5_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L5_First_Sku.setBackground(Color.WHITE);
                L5_First_Desc.setText(EBASdao.prodName);
                
             if (L5_Qty_Out.getText().matches("[1-9]+") && L5_Qty_Out.getText().length() < 2 ) {
                 L5_Qty_Out.setBackground(Color.WHITE);
                
              if (EBASdao.skuRslt2.equals("good") && L5_Orig_Sku.getText().matches("[0-9]+") && L5_Orig_Sku.getText().length() < 7) {
                L5_Orig_Sku.setBackground(Color.WHITE);
                L5_Orig_Desc.setText(EBASdao.prodName2);
                L5_Orig_Attr.setText(EBASdao.prodAttr);
                L5_Orig_Size.setText(EBASdao.prodSize);
                L5_Orig_Retail.setText(EBASdao.prodPr);
                
                if  (L5_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L5_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L5_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L5_Manuf_Inspec.getText().matches("[0-9 ]+")
                /* (L5_Manuf_Inspec.getText().matches("[1-8 ]+") && L5_Manuf_Inspec.getText().length() <2 */ || L5_Manuf_Inspec.getText().equals("")) {
		L5_Manuf_Inspec.setBackground(Color.WHITE);
                
              //  if((Integer.parseInt(L5_First_Sku.getText()) < 799610 || Integer.parseInt(L5_First_Sku.getText()) > 790407)
              //  && ((L5_Manuf_Inspec.getText().equals("DW03") || L5_Manuf_Inspec.getText().equals("dw03")) || (L5_Manuf_Inspec.getText().equals("DW04") || L5_Manuf_Inspec.getText().equals("dw04")))) {
              //  L5_Manuf_Inspec.setBackground(Color.WHITE);
                
                if (!L5_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L5_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L5_Desc_Damage.setBackground(Color.WHITE);
             
                if (L5_Cust_Satisf_ChkBox.isSelected() != L5_Warranty_ChkBox.isSelected()){  
                    if (!L5_Cust_Satisf_ChkBox.isSelected() || !L5_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L5_First_Sku.getText();
                EBASdao.Field2 = L5_Qty_Out.getText();
                EBASdao.Field3 = L5_First_Desc.getText();
                EBASdao.Field4 = L5_Orig_Sku.getText();
                EBASdao.Field5 = L5_Orig_Desc.getText();
                EBASdao.Field6 = L5_Orig_Attr.getText();
                EBASdao.Field7 = L5_Orig_Size.getText();
                EBASdao.Field8 = L5_Orig_Retail.getText();                
                EBASdao.Field9 = L5_Manuf_Inspec.getText();                
                EBASdao.Field10 = L5_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L5_Reason_DropDown.getSelectedItem().toString();
                EBASdao.Field12 = L5_Desc_Damage.getText();             	
                
                if (L5_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L5_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L5.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L5.getBounds(null));
                    
            } else {
                JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
            }
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L5_Done_ChkBox.setSelected(false);
		L5_Desc_Damage.setBackground(Color.yellow);
                L5_Desc_Damage.requestFocus();
            }             
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
                L5_Reason_DropDown.requestFocus();
            }
            /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
            */
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
        	L5_Done_ChkBox.setSelected(false);
		L5_Manuf_Inspec.setBackground(Color.yellow);
                L5_Manuf_Inspec.requestFocus();
            }
            } else {
                L5_Done_ChkBox.setSelected(false);
                L5_Orig_Sku.setBackground(Color.yellow);
                L5_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L5.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
                L5_Qty_Out.setBackground(Color.yellow);
                L5_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L5.getBounds(null));
            }
            } else {
                L5_Done_ChkBox.setSelected(false);
                L5_First_Sku.setBackground(Color.yellow);
                L5_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L5.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
                L5_First_Sku.requestFocus();
                L5_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L5.getBounds(null));
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
            L5_Manuf_Inspec.setEnabled(true);
            L5_New_Used.setEnabled(true);
            L5_Reason_DropDown.setEnabled(true);
            L5_Desc_Damage.setEnabled(true);           
            L5_Cust_Satisf_ChkBox.setEnabled(true);
            L5_Warranty_ChkBox.setEnabled(true);
            L5_Done_ChkBox.setEnabled(true);
            L5_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
    }//GEN-LAST:event_L5_Done_ChkBoxActionPerformed

     // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L5_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Done_ChkBoxKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L5_Done_ChkBox.setSelected(true);
            rChkLn5();
            if (L5_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L5_First_Sku.getText());
            EBASdao.setSkuInput2(L5_Orig_Sku.getText());
            EBASdao.lnFlg = 5;
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
                    L5_Manuf_Inspec.setText("");
                    L5_New_Used.setSelectedItem("New");
                    L5_Reason_DropDown.setSelectedItem("");
                    L5_Desc_Damage.setText("");
                    L5_Cust_Satisf_ChkBox.setSelected(false);
                    L5_Warranty_ChkBox.setSelected(false);
                    L5_Done_ChkBox.setSelected(false);
                    L5_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L5_First_Sku.getText().matches("[0-9]+") && L5_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L5_First_Sku.setBackground(Color.WHITE);
                L5_First_Desc.setText(EBASdao.prodName);
                
             if (L5_Qty_Out.getText().matches("[1-9]+") && L5_Qty_Out.getText().length() < 2 ) {
                 L5_Qty_Out.setBackground(Color.WHITE);
                
              if (EBASdao.skuRslt2.equals("good") && L5_Orig_Sku.getText().matches("[0-9]+") && L5_Orig_Sku.getText().length() < 7 ) {
                L5_Orig_Sku.setBackground(Color.WHITE);
                L5_Orig_Desc.setText(EBASdao.prodName2);
                L5_Orig_Attr.setText(EBASdao.prodAttr);
                L5_Orig_Size.setText(EBASdao.prodSize);
                L5_Orig_Retail.setText(EBASdao.prodPr);
                
                if (L5_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L5_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L5_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L5_Manuf_Inspec.getText().matches("[0-9 ]+") || L5_Manuf_Inspec.getText().equals("")) {
                //  (L5_Manuf_Inspec.getText().matches("[1-8 ]+") && L5_Manuf_Inspec.getText().length() <2 || L5_Manuf_Inspec.getText().equals("")) {
		L5_Manuf_Inspec.setBackground(Color.WHITE);
                
              //  if((Integer.parseInt(L5_First_Sku.getText()) < 799610 || Integer.parseInt(L5_First_Sku.getText()) > 790407)
              //  && ((L5_Manuf_Inspec.getText().equals("DW03") || L5_Manuf_Inspec.getText().equals("dw03")) || (L5_Manuf_Inspec.getText().equals("DW04") || L5_Manuf_Inspec.getText().equals("dw04")))) {
              //  L5_Manuf_Inspec.setBackground(Color.WHITE);
                
                if (!L5_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L5_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L5_Desc_Damage.setBackground(Color.WHITE);
             
                if (L5_Cust_Satisf_ChkBox.isSelected() != L5_Warranty_ChkBox.isSelected()){  
                    if (!L5_Cust_Satisf_ChkBox.isSelected() || !L5_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L5_First_Sku.getText();
                EBASdao.Field2 = L5_Qty_Out.getText();
                EBASdao.Field3 = L5_First_Desc.getText();
                EBASdao.Field4 = L5_Orig_Sku.getText();
                EBASdao.Field5 = L5_Orig_Desc.getText();
                EBASdao.Field6 = L5_Orig_Attr.getText();
                EBASdao.Field7 = L5_Orig_Size.getText();
                EBASdao.Field8 = L5_Orig_Retail.getText();
                EBASdao.Field9 = L5_Manuf_Inspec.getText();             	
                EBASdao.Field10 = L5_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L5_Reason_DropDown.getSelectedItem().toString();
                EBASdao.Field12 = L5_Desc_Damage.getText();
                
                if (L5_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L5_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L5.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L5.getBounds(null));
                    
            } else {
                JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
            }
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L5_Done_ChkBox.setSelected(false);
		L5_Desc_Damage.setBackground(Color.yellow);
                L5_Desc_Damage.requestFocus();
            }            
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
                L5_Reason_DropDown.requestFocus();
            }
            /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
            */
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
        	L5_Done_ChkBox.setSelected(false);
		L5_Manuf_Inspec.setBackground(Color.yellow);
                L1_Manuf_Inspec.requestFocus();
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
                L5_Orig_Sku.setBackground(Color.yellow);
                L5_Orig_Sku.requestFocus();     
                jPanel6.scrollRectToVisible(L5.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
                L5_Qty_Out.setBackground(Color.yellow);
                L5_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L5.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
                L5_First_Sku.setBackground(Color.yellow);
                L5_First_Sku.requestFocus();
                jPanel6.scrollRectToVisible(L5.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L5_Done_ChkBox.setSelected(false);
                L5_First_Sku.requestFocus();
                L5_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L5.getBounds(null));
            }
           // }
            } else {
            L5_First_Sku.setEnabled(true);
            L5_Qty_Out.setEnabled(true);
            L5_First_Desc.setEnabled(true);
            L5_Orig_Sku.setEnabled(true);
            L5_Orig_Desc.setEnabled(true);
            L5_Orig_Attr.setEnabled(true);
            L5_Orig_Size.setEnabled(true);
            L5_Orig_Retail.setEnabled(true);
            L5_Manuf_Inspec.setEnabled(true);
            L5_New_Used.setEnabled(true);
            L5_Reason_DropDown.setEnabled(true);
            L5_Desc_Damage.setEnabled(true);           
            L5_Cust_Satisf_ChkBox.setEnabled(true);
            L5_Warranty_ChkBox.setEnabled(true);
            L5_Done_ChkBox.setEnabled(true);
            L5_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
       }
    }//GEN-LAST:event_L5_Done_ChkBoxKeyPressed

     // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L6_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L6_Done_ChkBoxActionPerformed
        rChkLn6();
        if (L6_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L6_First_Sku.getText());
            EBASdao.setSkuInput2(L6_Orig_Sku.getText());
            EBASdao.lnFlg = 6;
            /*
            if (L6_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine6();
                    L6_Qty_Out.setText("");
                    L6_First_Desc.setText("");
                    L6_Orig_Sku.setText("");
                    L6_Orig_Desc.setText("");
                    L6_Orig_Attr.setText("");
                    L6_Orig_Size.setText("");
                    L6_Orig_Retail.setText("");
                    L6_Manuf_Inspec.setText("");
                    L6_New_Used.setSelectedItem("New");
                    L6_Reason_DropDown.setSelectedItem("");
                    L6_Desc_Damage.setText("");
                    L6_Cust_Satisf_ChkBox.setSelected(false);
                    L6_Warranty_ChkBox.setSelected(false);
                    L6_Done_ChkBox.setSelected(false);
                    L6_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L6_First_Sku.getText().matches("[0-9]+") && L6_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L6_First_Sku.setBackground(Color.WHITE);
                L6_First_Desc.setText(EBASdao.prodName);
                
             if (L6_Qty_Out.getText().matches("[1-9]+") && L6_Qty_Out.getText().length() < 2 ) {
                 L6_Qty_Out.setBackground(Color.WHITE);
                
              if (EBASdao.skuRslt2.equals("good") && L6_Orig_Sku.getText().matches("[0-9]+") && L6_Orig_Sku.getText().length() < 7 ) {
                L6_Orig_Sku.setBackground(Color.WHITE);
                L6_Orig_Desc.setText(EBASdao.prodName2);
                L6_Orig_Attr.setText(EBASdao.prodAttr);
                L6_Orig_Size.setText(EBASdao.prodSize);
                L6_Orig_Retail.setText(EBASdao.prodPr);
                
                if (L6_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L6_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                || L6_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L6_Manuf_Inspec.getText().matches("[0-9 ]+") || L6_Manuf_Inspec.getText().equals("")) {
                // (L6_Manuf_Inspec.getText().matches("[1-8 ]+") && L6_Manuf_Inspec.getText().length() <2 || L6_Manuf_Inspec.getText().equals("")) {
		L6_Manuf_Inspec.setBackground(Color.WHITE);
                
              //  if((Integer.parseInt(L6_First_Sku.getText()) < 799610 || Integer.parseInt(L6_First_Sku.getText()) > 790407)
              //  && ((L6_Manuf_Inspec.getText().equals("DW03") || L6_Manuf_Inspec.getText().equals("dw03")) || (L6_Manuf_Inspec.getText().equals("DW04") || L6_Manuf_Inspec.getText().equals("dw04")))) {
              //  L6_Manuf_Inspec.setBackground(Color.WHITE);
                
                if (!L6_Reason_DropDown.getSelectedItem().equals("")) {
             
                if (L6_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L6_Desc_Damage.setBackground(Color.WHITE);
                    
                if (L6_Cust_Satisf_ChkBox.isSelected() != L6_Warranty_ChkBox.isSelected()){  
                    if (!L6_Cust_Satisf_ChkBox.isSelected() || !L6_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L6_First_Sku.getText();
                EBASdao.Field2 = L6_Qty_Out.getText();
                EBASdao.Field3 = L6_First_Desc.getText();
                EBASdao.Field4 = L6_Orig_Sku.getText();
                EBASdao.Field5 = L6_Orig_Desc.getText();
                EBASdao.Field6 = L6_Orig_Attr.getText();
                EBASdao.Field7 = L6_Orig_Size.getText();
                EBASdao.Field8 = L6_Orig_Retail.getText();                
                EBASdao.Field9 = L6_Manuf_Inspec.getText();             	
                EBASdao.Field10 = L6_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L6_Reason_DropDown.getSelectedItem().toString();                
                EBASdao.Field12 = L6_Desc_Damage.getText();             	
                
                if (L6_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L6_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L6.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L6.getBounds(null));
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.","Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L6_Done_ChkBox.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L6_Done_ChkBox.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                    L6_Done_ChkBox.setSelected(false);
                    L6_Desc_Damage.setBackground(Color.yellow);
                    L6_Desc_Damage.requestFocus();
                }                
                } else {
                    JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                    L6_Done_ChkBox.setSelected(false);
                    L6_Reason_DropDown.requestFocus();
                }
                /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
                */
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
                    L6_Done_ChkBox.setSelected(false);
                    L6_Manuf_Inspec.setBackground(Color.yellow);
                    L6_Manuf_Inspec.requestFocus();
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                    L6_Done_ChkBox.setSelected(false);
                    L6_Orig_Sku.setBackground(Color.yellow);
                    L6_Orig_Sku.requestFocus();
                    jPanel6.scrollRectToVisible(L6.getBounds(null));
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                    L6_Done_ChkBox.setSelected(false);
                    L6_Qty_Out.setBackground(Color.yellow);
                    L6_Qty_Out.requestFocus();
                    jPanel6.scrollRectToVisible(L6.getBounds(null));
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                    L6_Done_ChkBox.setSelected(false);
                    L6_First_Sku.setBackground(Color.yellow);
                    L6_First_Sku.requestFocus();
                    jPanel6.scrollRectToVisible(L6.getBounds(null));
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L6_Done_ChkBox.setSelected(false);
                    L6_First_Sku.requestFocus();
                    L6_First_Sku.setBackground(Color.yellow);
                    jPanel6.scrollRectToVisible(L6.getBounds(null));
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
            L6_Manuf_Inspec.setEnabled(true);
            L6_New_Used.setEnabled(true);
            L6_Reason_DropDown.setEnabled(true);
            L6_Desc_Damage.setEnabled(true);           
            L6_Cust_Satisf_ChkBox.setEnabled(true);
            L6_Warranty_ChkBox.setEnabled(true);
            L6_Done_ChkBox.setEnabled(true);
            L6_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
    }//GEN-LAST:event_L6_Done_ChkBoxActionPerformed

     // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L6_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Done_ChkBoxKeyPressed
     if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L6_Done_ChkBox.setSelected(true);
            rChkLn6();
            if (L6_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L6_First_Sku.getText());
            EBASdao.setSkuInput2(L6_Orig_Sku.getText());
            EBASdao.lnFlg = 6;
            /*
            if (L6_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine6();
                    L6_Qty_Out.setText("");
                    L6_First_Desc.setText("");
                    L6_Orig_Sku.setText("");
                    L6_Orig_Desc.setText("");
                    L6_Orig_Attr.setText("");
                    L6_Orig_Size.setText("");
                    L6_Orig_Retail.setText("");
                    L6_Manuf_Inspec.setText("");
                    L6_New_Used.setSelectedItem("New");
                    L6_Reason_DropDown.setSelectedItem("");
                    L6_Desc_Damage.setText("");
                    L6_Cust_Satisf_ChkBox.setSelected(false);
                    L6_Warranty_ChkBox.setSelected(false);
                    L6_Done_ChkBox.setSelected(false);
                    L6_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L6_First_Sku.getText().matches("[0-9]+") && L6_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L6_First_Sku.setBackground(Color.WHITE);
                L6_First_Desc.setText(EBASdao.prodName);
                
             if (L6_Qty_Out.getText().matches("[1-9]+") && L6_Qty_Out.getText().length() < 2 ) {
                 L6_Qty_Out.setBackground(Color.WHITE);
                
              if (EBASdao.skuRslt2.equals("good") && L6_Orig_Sku.getText().matches("[0-9]+") && L6_Orig_Sku.getText().length() < 7 ){
                L6_Orig_Sku.setBackground(Color.WHITE);
                L6_Orig_Desc.setText(EBASdao.prodName2);
                L6_Orig_Attr.setText(EBASdao.prodAttr);
                L6_Orig_Size.setText(EBASdao.prodSize);
                L6_Orig_Retail.setText(EBASdao.prodPr);
                
                if (L6_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L6_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                || L6_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L6_Manuf_Inspec.getText().matches("[0-9 ]+") || L6_Manuf_Inspec.getText().equals("")) {
                //  (L6_Manuf_Inspec.getText().matches("[1-8 ]+") && L6_Manuf_Inspec.getText().length() <2 || L6_Manuf_Inspec.getText().equals("")) {
		L6_Manuf_Inspec.setBackground(Color.WHITE);
                
             //   if((Integer.parseInt(L6_First_Sku.getText()) < 799610 || Integer.parseInt(L6_First_Sku.getText()) > 790407)
             //   && ((L6_Manuf_Inspec.getText().equals("DW03") || L6_Manuf_Inspec.getText().equals("dw03")) || (L6_Manuf_Inspec.getText().equals("DW04") || L6_Manuf_Inspec.getText().equals("dw04")))) {
             //   L6_Manuf_Inspec.setBackground(Color.WHITE);
                
                if (!L6_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L6_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L6_Desc_Damage.setBackground(Color.WHITE);
             
                if (L6_Cust_Satisf_ChkBox.isSelected() != L6_Warranty_ChkBox.isSelected()){  
                    if (!L6_Cust_Satisf_ChkBox.isSelected() || !L6_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L6_First_Sku.getText();
                EBASdao.Field2 = L6_Qty_Out.getText();
                EBASdao.Field3 = L6_First_Desc.getText();
                EBASdao.Field4 = L6_Orig_Sku.getText();
                EBASdao.Field5 = L6_Orig_Desc.getText();
                EBASdao.Field6 = L6_Orig_Attr.getText();
                EBASdao.Field7 = L6_Orig_Size.getText();
                EBASdao.Field8 = L6_Orig_Retail.getText();
                EBASdao.Field9 = L6_Manuf_Inspec.getText();             	
                EBASdao.Field10 = L6_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L6_Reason_DropDown.getSelectedItem().toString();
                EBASdao.Field12 = L6_Desc_Damage.getText();
                
                if (L6_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L6_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L6.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L6.getBounds(null));
                    
            } else {
                JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                L6_Done_ChkBox.setSelected(false);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                L6_Done_ChkBox.setSelected(false);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                L6_Done_ChkBox.setSelected(false);
                L6_Desc_Damage.setBackground(Color.yellow);
                L6_Desc_Damage.requestFocus();
            }            
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L6_Done_ChkBox.setSelected(false);
                L6_Reason_DropDown.requestFocus();
            }
            /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
            */
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
                L6_Done_ChkBox.setSelected(false);
                L6_Manuf_Inspec.setBackground(Color.yellow);
                L6_Manuf_Inspec.requestFocus();
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                L6_Done_ChkBox.setSelected(false);
                L6_Orig_Sku.setBackground(Color.yellow);
                L6_Orig_Sku.requestFocus();
                jPanel6.scrollRectToVisible(L6.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L6_Done_ChkBox.setSelected(false);
                L6_Qty_Out.setBackground(Color.yellow);
                L6_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L6.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                L6_Done_ChkBox.setSelected(false);
                L6_First_Sku.setBackground(Color.yellow);
                L6_First_Sku.requestFocus();
                jPanel6.scrollRectToVisible(L6.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L6_Done_ChkBox.setSelected(false);
                L6_First_Sku.requestFocus();
                L6_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L6.getBounds(null));
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
            L6_Manuf_Inspec.setEnabled(true);
            L6_New_Used.setEnabled(true);
            L6_Reason_DropDown.setEnabled(true);
            L6_Desc_Damage.setEnabled(true);           
            L6_Cust_Satisf_ChkBox.setEnabled(true);
            L6_Warranty_ChkBox.setEnabled(true);
            L6_Done_ChkBox.setEnabled(true);
            L6_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
        }
    }//GEN-LAST:event_L6_Done_ChkBoxKeyPressed

     // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L7_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L7_Done_ChkBoxActionPerformed
         rChkLn7();
        if (L7_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L7_First_Sku.getText());
            EBASdao.setSkuInput2(L7_Orig_Sku.getText());
            EBASdao.lnFlg = 7;
            /*
            if (L7_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine7();
                    L7_Qty_Out.setText("");
                    L7_First_Desc.setText("");
                    L7_Orig_Sku.setText("");
                    L7_Orig_Desc.setText("");
                    L7_Orig_Attr.setText("");
                    L7_Orig_Size.setText("");
                    L7_Orig_Retail.setText("");
                    L7_Manuf_Inspec.setText("");
                    L7_New_Used.setSelectedItem("New");
                    L7_Reason_DropDown.setSelectedItem("");
                    L7_Desc_Damage.setText("");
                    L7_Cust_Satisf_ChkBox.setSelected(false);
                    L7_Warranty_ChkBox.setSelected(false);
                    L7_Done_ChkBox.setSelected(false);
                    L7_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L7_First_Sku.getText().matches("[0-9]+") && L7_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L7_First_Sku.setBackground(Color.WHITE);
                L7_First_Desc.setText(EBASdao.prodName);
                
             if (L7_Qty_Out.getText().matches("[1-9]+") && L7_Qty_Out.getText().length() < 2 ) {
                 L7_Qty_Out.setBackground(Color.WHITE);
                
              if (EBASdao.skuRslt2.equals("good") && L7_Orig_Sku.getText().matches("[0-9]+") && L7_Orig_Sku.getText().length() < 7 ) {
                L7_Orig_Sku.setBackground(Color.WHITE);
                L7_Orig_Desc.setText(EBASdao.prodName2);
                L7_Orig_Attr.setText(EBASdao.prodAttr);
                L7_Orig_Size.setText(EBASdao.prodSize);
                L7_Orig_Retail.setText(EBASdao.prodPr);
                
                if (L7_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L7_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                || L7_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L7_Manuf_Inspec.getText().matches("[0-9 ]+") || L7_Manuf_Inspec.getText().equals("")) {
                // (L7_Manuf_Inspec.getText().matches("[1-8 ]+") && L7_Manuf_Inspec.getText().length() <2 || L7_Manuf_Inspec.getText().equals("")) {
		L7_Manuf_Inspec.setBackground(Color.WHITE);
                
              //  if((Integer.parseInt(L7_First_Sku.getText()) < 799610 || Integer.parseInt(L7_First_Sku.getText()) > 790407)
              //  && ((L7_Manuf_Inspec.getText().equals("DW03") || L7_Manuf_Inspec.getText().equals("dw03")) || (L7_Manuf_Inspec.getText().equals("DW04") || L7_Manuf_Inspec.getText().equals("dw04")))) {
              //  L7_Manuf_Inspec.setBackground(Color.WHITE);
                
                if (!L7_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L7_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L7_Desc_Damage.setBackground(Color.WHITE);
                    
                if (L7_Cust_Satisf_ChkBox.isSelected() != L7_Warranty_ChkBox.isSelected()){  
                    if (!L7_Cust_Satisf_ChkBox.isSelected() || !L7_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L7_First_Sku.getText();
                EBASdao.Field2 = L7_Qty_Out.getText();
                EBASdao.Field3 = L7_First_Desc.getText();
                EBASdao.Field4 = L7_Orig_Sku.getText();
                EBASdao.Field5 = L7_Orig_Desc.getText();
                EBASdao.Field6 = L7_Orig_Attr.getText();
                EBASdao.Field7 = L7_Orig_Size.getText();
                EBASdao.Field8 = L7_Orig_Retail.getText();                
                EBASdao.Field9 = L7_Manuf_Inspec.getText();             	
                EBASdao.Field10 = L7_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L7_Reason_DropDown.getSelectedItem().toString();                
                EBASdao.Field12 = L7_Desc_Damage.getText();             	
                
                if (L7_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13= "false";
                }
                if (L7_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L7.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L7.getBounds(null));
                    
            } else {
                JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                L7_Done_ChkBox.setSelected(false);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                L7_Done_ChkBox.setSelected(false);
            }
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
		L7_Desc_Damage.setBackground(Color.yellow);
                L7_Desc_Damage.requestFocus();
            }               
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L7_Done_ChkBox.setSelected(false);
                L7_Reason_DropDown.requestFocus();
            }
            /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
            */
            } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
        	L7_Done_ChkBox.setSelected(false);
		L7_Manuf_Inspec.setBackground(Color.yellow);
                L7_Manuf_Inspec.requestFocus();
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                L7_Done_ChkBox.setSelected(false);
                L7_Orig_Sku.setBackground(Color.yellow);
                L7_Orig_Sku.requestFocus();
                jPanel6.scrollRectToVisible(L7.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L7_Done_ChkBox.setSelected(false);
                L7_Qty_Out.setBackground(Color.yellow);
                L7_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L7.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                L7_Done_ChkBox.setSelected(false);
                L7_First_Sku.setBackground(Color.yellow);
                L7_First_Sku.requestFocus();
                jPanel6.scrollRectToVisible(L7.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L7_Done_ChkBox.setSelected(false);
                L7_First_Sku.requestFocus();
                L7_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L7.getBounds(null));
            }
         //   }
        } else {
            L7_First_Sku.setEnabled(true);
            L7_Qty_Out.setEnabled(true);
            L7_First_Desc.setEnabled(true);
            L7_Orig_Sku.setEnabled(true);
            L7_Orig_Desc.setEnabled(true);
            L7_Orig_Attr.setEnabled(true);
            L7_Orig_Size.setEnabled(true);
            L7_Orig_Retail.setEnabled(true);
            L7_Manuf_Inspec.setEnabled(true);
            L7_New_Used.setEnabled(true);
            L7_Reason_DropDown.setEnabled(true);
            L7_Desc_Damage.setEnabled(true);           
            L7_Cust_Satisf_ChkBox.setEnabled(true);
            L7_Warranty_ChkBox.setEnabled(true);
            L7_Done_ChkBox.setEnabled(true);
            L7_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
    }//GEN-LAST:event_L7_Done_ChkBoxActionPerformed

     // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L7_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Done_ChkBoxKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L7_Done_ChkBox.setSelected(true);
            rChkLn7();
           if (L7_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L7_First_Sku.getText());
            EBASdao.setSkuInput2(L7_Orig_Sku.getText());
            EBASdao.lnFlg = 7;
            /*
            if (L7_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine7();
                    L7_Qty_Out.setText("");
                    L7_First_Desc.setText("");
                    L7_Orig_Sku.setText("");
                    L7_Orig_Desc.setText("");
                    L7_Orig_Attr.setText("");
                    L7_Orig_Size.setText("");
                    L7_Orig_Retail.setText("");
                    L7_Manuf_Inspec.setText("");
                    L7_New_Used.setSelectedItem("New");
                    L7_Reason_DropDown.setSelectedItem("");
                    L7_Desc_Damage.setText("");
                    L7_Cust_Satisf_ChkBox.setSelected(false);
                    L7_Warranty_ChkBox.setSelected(false);
                    L7_Done_ChkBox.setSelected(false);
                    L7_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L7_First_Sku.getText().matches("[0-9]+") && L7_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L7_First_Sku.setBackground(Color.WHITE);
                L7_First_Desc.setText(EBASdao.prodName);
                
             if (L7_Qty_Out.getText().matches("[1-9]+") && L7_Qty_Out.getText().length() < 2 ) {
                 L7_Qty_Out.setBackground(Color.WHITE);
                
              if (EBASdao.skuRslt2.equals("good") && L7_Orig_Sku.getText().matches("[0-9]+") && L7_Orig_Sku.getText().length() < 7 ) {
                L7_Orig_Sku.setBackground(Color.WHITE);
                L7_Orig_Desc.setText(EBASdao.prodName2);
                L7_Orig_Attr.setText(EBASdao.prodAttr);
                L7_Orig_Size.setText(EBASdao.prodSize);
                L7_Orig_Retail.setText(EBASdao.prodPr);
                
                if (L7_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L7_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                || L7_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L7_Manuf_Inspec.getText().matches("[0-9 ]+") || L7_Manuf_Inspec.getText().equals("")) {
                // (L7_Manuf_Inspec.getText().matches("[1-8 ]+") && L7_Manuf_Inspec.getText().length() <2 || L7_Manuf_Inspec.getText().equals("")) {
		L7_Manuf_Inspec.setBackground(Color.WHITE);
                
              //  if((Integer.parseInt(L7_First_Sku.getText()) < 799610 || Integer.parseInt(L7_First_Sku.getText()) > 790407)
              //  && ((L7_Manuf_Inspec.getText().equals("DW03") || L7_Manuf_Inspec.getText().equals("dw03")) || (L7_Manuf_Inspec.getText().equals("DW04") || L7_Manuf_Inspec.getText().equals("dw04")))) {
              //  L7_Manuf_Inspec.setBackground(Color.WHITE);
                
                if (!L7_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L7_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L7_Desc_Damage.setBackground(Color.WHITE);               
             
                if (L7_Cust_Satisf_ChkBox.isSelected() != L7_Warranty_ChkBox.isSelected()){  
                    if (!L7_Cust_Satisf_ChkBox.isSelected() || !L7_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L7_First_Sku.getText();
                EBASdao.Field2 = L7_Qty_Out.getText();
                EBASdao.Field3 = L7_First_Desc.getText();
                EBASdao.Field4 = L7_Orig_Sku.getText();
                EBASdao.Field5 = L7_Orig_Desc.getText();
                EBASdao.Field6 = L7_Orig_Attr.getText();
                EBASdao.Field7 = L7_Orig_Size.getText();
                EBASdao.Field8 = L7_Orig_Retail.getText();                
                EBASdao.Field9 = L7_Manuf_Inspec.getText();
                EBASdao.Field10 = L7_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L7_Reason_DropDown.getSelectedItem().toString();
                EBASdao.Field12 = L7_Desc_Damage.getText();
                
                if (L7_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13= "false";
                }
                if (L7_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L7.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L7.getBounds(null));
                    
                } else {
                     JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                     L7_Done_ChkBox.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L7_Done_ChkBox.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                    L7_Done_ChkBox.setSelected(false);
                    L7_Desc_Damage.setBackground(Color.yellow);
                    L7_Desc_Damage.requestFocus();
                }                
                } else {
                    JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                    L7_Done_ChkBox.setSelected(false);
                    L7_Reason_DropDown.requestFocus();
                }
                /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
                */
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
                    L7_Done_ChkBox.setSelected(false);
                    L7_Manuf_Inspec.setBackground(Color.yellow);
                    L7_Manuf_Inspec.requestFocus();
                }
             } else {
                L7_Done_ChkBox.setSelected(false);
                L7_Orig_Sku.setBackground(Color.yellow);
                L7_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L7.getBounds(null));
            }
            } else {
                 JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L7_Done_ChkBox.setSelected(false);
                L7_Qty_Out.setBackground(Color.yellow);
                L7_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L7.getBounds(null));
            }
            } else {
                L7_Done_ChkBox.setSelected(false);
                L7_First_Sku.setBackground(Color.yellow);
                L7_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L7.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L7_Done_ChkBox.setSelected(false);
                L7_First_Sku.requestFocus();
                L7_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L7.getBounds(null));
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
            L7_Manuf_Inspec.setEnabled(true);
            L7_New_Used.setEnabled(true);
            L7_Reason_DropDown.setEnabled(true);
            L7_Desc_Damage.setEnabled(true);           
            L7_Cust_Satisf_ChkBox.setEnabled(true);
            L7_Warranty_ChkBox.setEnabled(true);
            L7_Done_ChkBox.setEnabled(true);
            L7_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
       }
    }//GEN-LAST:event_L7_Done_ChkBoxKeyPressed

     // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L8_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L8_Done_ChkBoxActionPerformed
        rChkLn8();
        if (L8_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L8_First_Sku.getText());
            EBASdao.setSkuInput2(L8_Orig_Sku.getText());
            EBASdao.lnFlg = 8;
            /*
            if (L8_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine8();
                    L8_Qty_Out.setText("");
                    L8_First_Desc.setText("");
                    L8_Orig_Sku.setText("");
                    L8_Orig_Desc.setText("");
                    L8_Orig_Attr.setText("");
                    L8_Orig_Size.setText("");
                    L8_Orig_Retail.setText("");
                    L8_Manuf_Inspec.setText("");
                    L8_New_Used.setSelectedItem("New");
                    L8_Reason_DropDown.setSelectedItem("");
                    L8_Desc_Damage.setText("");
                    L8_Cust_Satisf_ChkBox.setSelected(false);
                    L8_Warranty_ChkBox.setSelected(false);
                    L8_Done_ChkBox.setSelected(false);
                    L8_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L8_First_Sku.getText().matches("[0-9]+") && L8_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L8_First_Sku.setBackground(Color.WHITE);
                L8_First_Desc.setText(EBASdao.prodName);
                
             if (L8_Qty_Out.getText().matches("[1-9]+") && L8_Qty_Out.getText().length() < 2 ) {
                 L8_Qty_Out.setBackground(Color.WHITE);
                
              if (EBASdao.skuRslt2.equals("good") && L8_Orig_Sku.getText().matches("[0-9]+") && L8_Orig_Sku.getText().length() < 7 ) {
                L8_Orig_Sku.setBackground(Color.WHITE);
                L8_Orig_Desc.setText(EBASdao.prodName2);
                L8_Orig_Attr.setText(EBASdao.prodAttr);
                L8_Orig_Size.setText(EBASdao.prodSize);
                L8_Orig_Retail.setText(EBASdao.prodPr);
                
                if (L8_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L8_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                || L8_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L8_Manuf_Inspec.getText().matches("[0-9 ]+") || L8_Manuf_Inspec.getText().equals("")) {
                // (L8_Manuf_Inspec.getText().matches("[1-8 ]+") && L8_Manuf_Inspec.getText().length() <2 || L8_Manuf_Inspec.getText().equals("")) {
		L8_Manuf_Inspec.setBackground(Color.WHITE);
                
             //   if((Integer.parseInt(L8_First_Sku.getText()) < 799610 || Integer.parseInt(L8_First_Sku.getText()) > 790407)
             //   && ((L8_Manuf_Inspec.getText().equals("DW03") || L8_Manuf_Inspec.getText().equals("dw03")) || (L8_Manuf_Inspec.getText().equals("DW04") || L8_Manuf_Inspec.getText().equals("dw04")))) {
             //   L8_Manuf_Inspec.setBackground(Color.WHITE);
                
                if (!L8_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L8_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L8_Desc_Damage.setBackground(Color.WHITE);
             
                if (L8_Cust_Satisf_ChkBox.isSelected() != L8_Warranty_ChkBox.isSelected()){  
                    if (!L8_Cust_Satisf_ChkBox.isSelected() || !L8_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L8_First_Sku.getText();
                EBASdao.Field2 = L8_Qty_Out.getText();
                EBASdao.Field3 = L8_First_Desc.getText();
                EBASdao.Field4 = L8_Orig_Sku.getText();
                EBASdao.Field5 = L8_Orig_Desc.getText();
                EBASdao.Field6 = L8_Orig_Attr.getText();
                EBASdao.Field7 = L8_Orig_Size.getText();
                EBASdao.Field8 = L8_Orig_Retail.getText();                
                EBASdao.Field9 = L8_Manuf_Inspec.getText();             	
                EBASdao.Field10 = L8_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L8_Reason_DropDown.getSelectedItem().toString();                
                EBASdao.Field12 = L8_Desc_Damage.getText();             	
                
                if (L8_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L8_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L8.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L8.getBounds(null));
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L8_Done_ChkBox.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L8_Done_ChkBox.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                    L8_Done_ChkBox.setSelected(false);
                    L8_Desc_Damage.setBackground(Color.yellow);
                    L8_Desc_Damage.requestFocus();
                }                
                } else {
                    JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                    L8_Done_ChkBox.setSelected(false);
                    L8_Reason_DropDown.requestFocus();
                }
                /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
                */
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
                    L8_Done_ChkBox.setSelected(false);
                    L8_Manuf_Inspec.setBackground(Color.yellow);
                    L8_Manuf_Inspec.requestFocus();
                }   
            } else {
                L8_Done_ChkBox.setSelected(false);
                L8_Orig_Sku.setBackground(Color.yellow);
                L8_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L8.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L8_Done_ChkBox.setSelected(false);
                L8_Qty_Out.setBackground(Color.yellow);
                L8_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L8.getBounds(null));
            }
            } else {
                L8_Done_ChkBox.setSelected(false);
                L8_First_Sku.setBackground(Color.yellow);
                L8_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L8.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L8_Done_ChkBox.setSelected(false);
                L8_First_Sku.requestFocus();
                L8_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L8.getBounds(null));
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
            L8_Manuf_Inspec.setEnabled(true);
            L8_New_Used.setEnabled(true);
            L8_Reason_DropDown.setEnabled(true);
            L8_Desc_Damage.setEnabled(true);           
            L8_Cust_Satisf_ChkBox.setEnabled(true);
            L8_Warranty_ChkBox.setEnabled(true);
            L8_Done_ChkBox.setEnabled(true);
            L8_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
    }//GEN-LAST:event_L8_Done_ChkBoxActionPerformed

     // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L8_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Done_ChkBoxKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L8_Done_ChkBox.setSelected(true);
            rChkLn8();
            if (L8_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L8_First_Sku.getText());
            EBASdao.setSkuInput2(L8_Orig_Sku.getText());
            EBASdao.lnFlg = 8;
            /*
            if (L8_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine8();
                    L8_Qty_Out.setText("");
                    L8_First_Desc.setText("");
                    L8_Orig_Sku.setText("");
                    L8_Orig_Desc.setText("");
                    L8_Orig_Attr.setText("");
                    L8_Orig_Size.setText("");
                    L8_Orig_Retail.setText("");
                    L8_Manuf_Inspec.setText("");
                    L8_New_Used.setSelectedItem("New");
                    L8_Reason_DropDown.setSelectedItem("");
                    L8_Desc_Damage.setText("");
                    L8_Cust_Satisf_ChkBox.setSelected(false);
                    L8_Warranty_ChkBox.setSelected(false);
                    L8_Done_ChkBox.setSelected(false);
                    L8_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L8_First_Sku.getText().matches("[0-9]+") && L8_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L8_First_Sku.setBackground(Color.WHITE);
                L8_First_Desc.setText(EBASdao.prodName);
                
             if (L8_Qty_Out.getText().matches("[1-9]+") && L8_Qty_Out.getText().length() < 2 ) {
                 L8_Qty_Out.setBackground(Color.WHITE);
                
              if (EBASdao.skuRslt2.equals("good") && L8_Orig_Sku.getText().matches("[0-9]+") && L8_Orig_Sku.getText().length() < 7 ) {
                L8_Orig_Sku.setBackground(Color.WHITE);
                L8_Orig_Desc.setText(EBASdao.prodName2);
                L8_Orig_Attr.setText(EBASdao.prodAttr);
                L8_Orig_Size.setText(EBASdao.prodSize);
                L8_Orig_Retail.setText(EBASdao.prodPr);
                
                if (L8_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L8_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                || L8_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L8_Manuf_Inspec.getText().matches("[0-9 ]+") || L8_Manuf_Inspec.getText().equals("")) {
                // (L8_Manuf_Inspec.getText().matches("[1-8 ]+") && L8_Manuf_Inspec.getText().length() <2 || L8_Manuf_Inspec.getText().equals("")) {
		L8_Manuf_Inspec.setBackground(Color.WHITE);
                
              //  if((Integer.parseInt(L8_First_Sku.getText()) < 799610 || Integer.parseInt(L8_First_Sku.getText()) > 790407)
              //  && ((L8_Manuf_Inspec.getText().equals("DW03") || L8_Manuf_Inspec.getText().equals("dw03")) || (L8_Manuf_Inspec.getText().equals("DW04") || L8_Manuf_Inspec.getText().equals("dw04")))) {
              //  L8_Manuf_Inspec.setBackground(Color.WHITE);
                
                if (!L8_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L8_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L8_Desc_Damage.setBackground(Color.WHITE);
                             
                if (L8_Cust_Satisf_ChkBox.isSelected() != L8_Warranty_ChkBox.isSelected()){  
                    if (!L8_Cust_Satisf_ChkBox.isSelected() || !L8_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L8_First_Sku.getText();
                EBASdao.Field2 = L8_Qty_Out.getText();
                EBASdao.Field3 = L8_First_Desc.getText();
                EBASdao.Field4 = L8_Orig_Sku.getText();
                EBASdao.Field5 = L8_Orig_Desc.getText();
                EBASdao.Field6 = L8_Orig_Attr.getText();
                EBASdao.Field7 = L8_Orig_Size.getText();
                EBASdao.Field8 = L8_Orig_Retail.getText();                
                EBASdao.Field9 = L8_Manuf_Inspec.getText();
                EBASdao.Field10 = L8_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L8_Reason_DropDown.getSelectedItem().toString();
                EBASdao.Field12 = L8_Desc_Damage.getText();
                
                if (L8_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L8_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L8.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L8.getBounds(null));
                    
                } else {
                     JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                     L8_Done_ChkBox.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L8_Done_ChkBox.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                    L8_Done_ChkBox.setSelected(false);
                    L8_Desc_Damage.setBackground(Color.yellow);
                    L8_Desc_Damage.requestFocus();
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                    L8_Done_ChkBox.setSelected(false);
                    L8_Reason_DropDown.requestFocus();
                }
                /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
                */
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
                    L8_Done_ChkBox.setSelected(false);
                    L8_Manuf_Inspec.setBackground(Color.yellow);
                    L8_Manuf_Inspec.requestFocus();
                }
             } else {
                L8_Done_ChkBox.setSelected(false);
                L8_Orig_Sku.setBackground(Color.yellow);
                L8_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L8.getBounds(null));
            }
            } else {
                 JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L8_Done_ChkBox.setSelected(false);
                L8_Qty_Out.setBackground(Color.yellow);
                L8_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L8.getBounds(null));
            }
            } else {
                L8_Done_ChkBox.setSelected(false);
                L8_First_Sku.setBackground(Color.yellow);
                L8_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L8.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L8_Done_ChkBox.setSelected(false);
                L8_First_Sku.requestFocus();
                L8_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L8.getBounds(null));
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
            L8_Manuf_Inspec.setEnabled(true);
            L8_New_Used.setEnabled(true);
            L8_Reason_DropDown.setEnabled(true);
            L8_Desc_Damage.setEnabled(true);           
            L8_Cust_Satisf_ChkBox.setEnabled(true);
            L8_Warranty_ChkBox.setEnabled(true);
            L8_Done_ChkBox.setEnabled(true);
            L8_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
       }
    }//GEN-LAST:event_L8_Done_ChkBoxKeyPressed

     // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L9_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L9_Done_ChkBoxActionPerformed
        rChkLn9();
        if (L9_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L9_First_Sku.getText());
            EBASdao.setSkuInput2(L9_Orig_Sku.getText());
            EBASdao.lnFlg = 9;
            /*
            if (L9_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine9();
                    L9_Qty_Out.setText("");
                    L9_First_Desc.setText("");
                    L9_Orig_Sku.setText("");
                    L9_Orig_Desc.setText("");
                    L9_Orig_Attr.setText("");
                    L9_Orig_Size.setText("");
                    L9_Orig_Retail.setText("");
                    L9_Manuf_Inspec.setText("");
                    L9_New_Used.setSelectedItem("New");
                    L9_Reason_DropDown.setSelectedItem("");
                    L9_Desc_Damage.setText("");
                    L9_Cust_Satisf_ChkBox.setSelected(false);
                    L9_Warranty_ChkBox.setSelected(false);
                    L9_Done_ChkBox.setSelected(false);
                    L9_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L9_First_Sku.getText().matches("[0-9]+") && L9_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L9_First_Sku.setBackground(Color.WHITE);
                L9_First_Desc.setText(EBASdao.prodName);
                
             if (L9_Qty_Out.getText().matches("[1-9]+") && L9_Qty_Out.getText().length() < 2 ) {
                 L9_Qty_Out.setBackground(Color.WHITE);
                
              if (EBASdao.skuRslt2.equals("good") && L9_Orig_Sku.getText().matches("[0-9]+") && L9_Orig_Sku.getText().length() < 7 ) {
                L9_Orig_Sku.setBackground(Color.WHITE);
                L9_Orig_Desc.setText(EBASdao.prodName2);
                L9_Orig_Attr.setText(EBASdao.prodAttr);
                L9_Orig_Size.setText(EBASdao.prodSize);
                L9_Orig_Retail.setText(EBASdao.prodPr);
                
                if (L9_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L9_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                || L9_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L9_Manuf_Inspec.getText().matches("[0-9 ]+") || L9_Manuf_Inspec.getText().equals("")) {
                //  (L9_Manuf_Inspec.getText().matches("[1-8 ]+") && L9_Manuf_Inspec.getText().length() <2 || L9_Manuf_Inspec.getText().equals("")) {
		L9_Manuf_Inspec.setBackground(Color.WHITE);
                
             //   if((Integer.parseInt(L9_First_Sku.getText()) < 799610 || Integer.parseInt(L9_First_Sku.getText()) > 790407)
             //   && ((L9_Manuf_Inspec.getText().equals("DW03") || L9_Manuf_Inspec.getText().equals("dw03")) || (L9_Manuf_Inspec.getText().equals("DW04") || L9_Manuf_Inspec.getText().equals("dw04")))) {
             //   L9_Manuf_Inspec.setBackground(Color.WHITE);
                
                if (!L9_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L9_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L9_Desc_Damage.setBackground(Color.WHITE);
            
                if (L9_Cust_Satisf_ChkBox.isSelected() != L9_Warranty_ChkBox.isSelected()){  
                    if (!L9_Cust_Satisf_ChkBox.isSelected() || !L9_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L9_First_Sku.getText();
                EBASdao.Field2 = L9_Qty_Out.getText();
                EBASdao.Field3 = L9_First_Desc.getText();
                EBASdao.Field4 = L9_Orig_Sku.getText();
                EBASdao.Field5 = L9_Orig_Desc.getText();
                EBASdao.Field6 = L9_Orig_Attr.getText();
                EBASdao.Field7 = L9_Orig_Size.getText();
                EBASdao.Field8 = L9_Orig_Retail.getText();                
                EBASdao.Field9 = L9_Manuf_Inspec.getText();
                EBASdao.Field10 = L9_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L9_Reason_DropDown.getSelectedItem().toString();                
                EBASdao.Field12 = L9_Desc_Damage.getText();
                
                if (L9_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L9_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L9.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L9.getBounds(null));
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L9_Done_ChkBox.setSelected(false);                     
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L9_Done_ChkBox.setSelected(false);        
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                    L9_Done_ChkBox.setSelected(false);
                    L9_Desc_Damage.setBackground(Color.yellow);
                    L9_Desc_Damage.requestFocus();
                }                
                } else {
                    JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                    L9_Done_ChkBox.setSelected(false);
                    L9_Reason_DropDown.requestFocus();
                }
                /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
                */
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
                    L9_Done_ChkBox.setSelected(false);
                    L9_Manuf_Inspec.setBackground(Color.yellow);
                    L9_Manuf_Inspec.requestFocus();
                }
             } else {
                L9_Done_ChkBox.setSelected(false);
                L9_Orig_Sku.setBackground(Color.yellow);
                L9_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L9.getBounds(null));
            }
            } else {
                 JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L9_Done_ChkBox.setSelected(false);
                L9_Qty_Out.setBackground(Color.yellow);
                L9_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L9.getBounds(null));
            }
            } else {
                L9_Done_ChkBox.setSelected(false);
                L9_First_Sku.setBackground(Color.yellow);
                L9_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L9.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L9_Done_ChkBox.setSelected(false);
                L9_First_Sku.requestFocus();
                L9_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L9.getBounds(null));
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
            L9_Manuf_Inspec.setEnabled(true);
            L9_New_Used.setEnabled(true);
            L9_Reason_DropDown.setEnabled(true);
            L9_Desc_Damage.setEnabled(true);           
            L9_Cust_Satisf_ChkBox.setEnabled(true);
            L9_Warranty_ChkBox.setEnabled(true);
            L9_Done_ChkBox.setEnabled(true);
            L9_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
    }//GEN-LAST:event_L9_Done_ChkBoxActionPerformed

     // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L9_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Done_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L9_Done_ChkBox.setSelected(true);
            rChkLn9();
            if (L9_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L9_First_Sku.getText());
            EBASdao.setSkuInput2(L9_Orig_Sku.getText());
            EBASdao.lnFlg = 9;
            /*
            if (L9_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine9();
                    L9_Qty_Out.setText("");
                    L9_First_Desc.setText("");
                    L9_Orig_Sku.setText("");
                    L9_Orig_Desc.setText("");
                    L9_Orig_Attr.setText("");
                    L9_Orig_Size.setText("");
                    L9_Orig_Retail.setText("");
                    L9_Manuf_Inspec.setText("");
                    L9_New_Used.setSelectedItem("New");
                    L9_Reason_DropDown.setSelectedItem("");
                    L9_Desc_Damage.setText("");
                    L9_Cust_Satisf_ChkBox.setSelected(false);
                    L9_Warranty_ChkBox.setSelected(false);
                    L9_Done_ChkBox.setSelected(false);
                    L9_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L9_First_Sku.getText().matches("[0-9]+") && L9_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L9_First_Sku.setBackground(Color.WHITE);
                L9_First_Desc.setText(EBASdao.prodName);
                
             if (L9_Qty_Out.getText().matches("[1-9]+") && L9_Qty_Out.getText().length() < 2 ) {
                 L9_Qty_Out.setBackground(Color.WHITE);
                
              if (EBASdao.skuRslt2.equals("good") && L9_Orig_Sku.getText().matches("[0-9]+") && L9_Orig_Sku.getText().length() < 7 ) {
                L9_Orig_Sku.setBackground(Color.WHITE);
                L9_Orig_Desc.setText(EBASdao.prodName2);
                L9_Orig_Attr.setText(EBASdao.prodAttr);
                L9_Orig_Size.setText(EBASdao.prodSize);
                L9_Orig_Retail.setText(EBASdao.prodPr);
                
                if (L9_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L9_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L9_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L9_Manuf_Inspec.getText().matches("[0-9 ]+") || L9_Manuf_Inspec.getText().equals("")) {
               // (L9_Manuf_Inspec.getText().matches("[1-8 ]+") && L9_Manuf_Inspec.getText().length() <2 || L9_Manuf_Inspec.getText().equals("")) {
		L9_Manuf_Inspec.setBackground(Color.WHITE);
                
             //   if((Integer.parseInt(L9_First_Sku.getText()) < 799610 || Integer.parseInt(L9_First_Sku.getText()) > 790407)
             //   && ((L9_Manuf_Inspec.getText().equals("DW03") || L9_Manuf_Inspec.getText().equals("dw03")) || (L9_Manuf_Inspec.getText().equals("DW04") || L9_Manuf_Inspec.getText().equals("dw04")))) {
             //   L9_Manuf_Inspec.setBackground(Color.WHITE);
                
                if (!L9_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L9_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L9_Desc_Damage.setBackground(Color.WHITE);
             
                if (L9_Cust_Satisf_ChkBox.isSelected() != L9_Warranty_ChkBox.isSelected()){  
                    if (!L9_Cust_Satisf_ChkBox.isSelected() || !L9_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L9_First_Sku.getText();
                EBASdao.Field2 = L9_Qty_Out.getText();
                EBASdao.Field3 = L9_First_Desc.getText();
                EBASdao.Field4 = L9_Orig_Sku.getText();
                EBASdao.Field5 = L9_Orig_Desc.getText();
                EBASdao.Field6 = L9_Orig_Attr.getText();
                EBASdao.Field7 = L9_Orig_Size.getText();
                EBASdao.Field8 = L9_Orig_Retail.getText();                
                EBASdao.Field9 = L9_Manuf_Inspec.getText();
                EBASdao.Field10 = L9_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L9_Reason_DropDown.getSelectedItem().toString();                
                EBASdao.Field12 = L9_Desc_Damage.getText();
                
                if (L9_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L9_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L9.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L9.getBounds(null));
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L9_Done_ChkBox.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L9_Done_ChkBox.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                    L9_Done_ChkBox.setSelected(false);
                    L9_Desc_Damage.setBackground(Color.yellow);
                    L9_Desc_Damage.requestFocus();
                }                
            } else {
                JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                L9_Done_ChkBox.setSelected(false);
                L9_Reason_DropDown.requestFocus();
            }
            /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
            */
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
                L9_Done_ChkBox.setSelected(false);
                L9_Manuf_Inspec.setBackground(Color.yellow);
                L9_Manuf_Inspec.requestFocus();
            }   
            } else {
                L9_Done_ChkBox.setSelected(false);
                L9_Orig_Sku.setBackground(Color.yellow);
                L9_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L9.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L9_Done_ChkBox.setSelected(false);
                L9_Qty_Out.setBackground(Color.yellow);
                L9_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L9.getBounds(null));
            }
            } else {
                L9_Done_ChkBox.setSelected(false);
                L9_First_Sku.setBackground(Color.yellow);
                L9_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L9.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L9_Done_ChkBox.setSelected(false);
                L9_First_Sku.requestFocus();
                L9_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L9.getBounds(null));
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
            L9_Manuf_Inspec.setEnabled(true);
            L9_New_Used.setEnabled(true);
            L9_Reason_DropDown.setEnabled(true);
            L9_Desc_Damage.setEnabled(true);           
            L9_Cust_Satisf_ChkBox.setEnabled(true);
            L9_Warranty_ChkBox.setEnabled(true);
            L9_Done_ChkBox.setEnabled(true);
            L9_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
        }
    }//GEN-LAST:event_L9_Done_ChkBoxKeyPressed

     // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L10_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L10_Done_ChkBoxActionPerformed
        rChkLn10();
        if (L10_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L10_First_Sku.getText());
            EBASdao.setSkuInput2(L10_Orig_Sku.getText());
            EBASdao.lnFlg = 10;
            /*
            if (L10_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine10();
                    L10_Qty_Out.setText("");
                    L10_First_Desc.setText("");
                    L10_Orig_Sku.setText("");
                    L10_Orig_Desc.setText("");
                    L10_Orig_Attr.setText("");
                    L10_Orig_Size.setText("");
                    L10_Orig_Retail.setText("");
                    L10_Manuf_Inspec.setText("");
                    L10_New_Used.setSelectedItem("New");
                    L10_Reason_DropDown.setSelectedItem("");
                    L10_Desc_Damage.setText("");
                    L10_Cust_Satisf_ChkBox.setSelected(false);
                    L10_Warranty_ChkBox.setSelected(false);
                    L10_Done_ChkBox.setSelected(false);
                    L10_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L10_First_Sku.getText().matches("[0-9]+") && L10_First_Sku.getText().length() < 7 ) {

            if (EBASdao.skuRslt.equals("good")) {
                L10_First_Sku.setBackground(Color.WHITE);
                L10_First_Desc.setText(EBASdao.prodName);
                
             if (L10_Qty_Out.getText().matches("[1-9]+") && L10_Qty_Out.getText().length() < 2 ) {
                 L10_Qty_Out.setBackground(Color.WHITE);
                
              if (EBASdao.skuRslt2.equals("good") && L10_Orig_Sku.getText().matches("[0-9]+") && L10_Orig_Sku.getText().length() < 7 ) {
                L10_Orig_Sku.setBackground(Color.WHITE);
                L10_Orig_Desc.setText(EBASdao.prodName2);
                L10_Orig_Attr.setText(EBASdao.prodAttr);
                L10_Orig_Size.setText(EBASdao.prodSize);
                L10_Orig_Retail.setText(EBASdao.prodPr);
                
                if (L10_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L10_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                || L10_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L10_Manuf_Inspec.getText().matches("[0-9 ]+") || L10_Manuf_Inspec.getText().equals("")) {
                // (L10_Manuf_Inspec.getText().matches("[1-8 ]+") && L10_Manuf_Inspec.getText().length() <2 || L10_Manuf_Inspec.getText().equals("")) {
		L10_Manuf_Inspec.setBackground(Color.WHITE);
                
            //    if((Integer.parseInt(L10_First_Sku.getText()) < 799610 || Integer.parseInt(L10_First_Sku.getText()) > 790407)
            //    && ((L10_Manuf_Inspec.getText().equals("DW03") || L10_Manuf_Inspec.getText().equals("dw03")) || (L10_Manuf_Inspec.getText().equals("DW04") || L10_Manuf_Inspec.getText().equals("dw04")))) {
            //    L10_Manuf_Inspec.setBackground(Color.WHITE);
                
                if (!L10_Reason_DropDown.getSelectedItem().equals("")) {
                    
                if (L10_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L10_Desc_Damage.setBackground(Color.WHITE);
             
                if (L10_Cust_Satisf_ChkBox.isSelected() != L10_Warranty_ChkBox.isSelected()){  
                    if (!L10_Cust_Satisf_ChkBox.isSelected() || !L10_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L10_First_Sku.getText();
                EBASdao.Field2 = L10_Qty_Out.getText();
                EBASdao.Field3 = L10_First_Desc.getText();
                EBASdao.Field4 = L10_Orig_Sku.getText();
                EBASdao.Field5 = L10_Orig_Desc.getText();
                EBASdao.Field6 = L10_Orig_Attr.getText();
                EBASdao.Field7 = L10_Orig_Size.getText();
                EBASdao.Field8 = L10_Orig_Retail.getText();                
                EBASdao.Field9 = L10_Manuf_Inspec.getText();
                EBASdao.Field10 = L10_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L10_Reason_DropDown.getSelectedItem().toString();
                EBASdao.Field12 = L10_Desc_Damage.getText();
                
                if (L10_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L10_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L10.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L10.getBounds(null));
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L10_Done_ChkBox.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L10_Done_ChkBox.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                    L10_Done_ChkBox.setSelected(false);
                    L10_Desc_Damage.setBackground(Color.yellow);
                    L10_Desc_Damage.requestFocus();
                }                
                } else {
                    JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                    L10_Done_ChkBox.setSelected(false);
                    L10_Reason_DropDown.requestFocus();
                }
                /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
                */
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
                    L10_Done_ChkBox.setSelected(false);
                    L10_Manuf_Inspec.setBackground(Color.yellow);
                    L10_Manuf_Inspec.requestFocus();
                }
             } else {
                L10_Done_ChkBox.setSelected(false);
                L10_Orig_Sku.setBackground(Color.yellow);
                L10_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L10.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L10_Done_ChkBox.setSelected(false);
                L10_Qty_Out.setBackground(Color.yellow);
                L10_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L10.getBounds(null));
            }
            } else {
                L10_Done_ChkBox.setSelected(false);
                L10_First_Sku.setBackground(Color.yellow);
                L10_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L10.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L10_Done_ChkBox.setSelected(false);
                L10_First_Sku.requestFocus();
                L10_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L10.getBounds(null));
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
            L10_Manuf_Inspec.setEnabled(true);
            L10_New_Used.setEnabled(true);
            L10_Reason_DropDown.setEnabled(true);
            L10_Desc_Damage.setEnabled(true);           
            L10_Cust_Satisf_ChkBox.setEnabled(true);
            L10_Warranty_ChkBox.setEnabled(true);
            L10_Done_ChkBox.setEnabled(true);
            L10_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
    }//GEN-LAST:event_L10_Done_ChkBoxActionPerformed

     // Validates all the necessary fields have the correct data and are not empty when the checkbox is checked
     // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L10_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Done_ChkBoxKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L10_Done_ChkBox.setSelected(true);
            rChkLn10();
            if (L10_Done_ChkBox.isSelected() == true) {
            String[] args = null;
            GtDates.main(args);
            EBASdao.setSkuInput(L10_First_Sku.getText());
            EBASdao.setSkuInput2(L10_Orig_Sku.getText());
            EBASdao.lnFlg = 10;
            /*
            if (L10_First_Sku.getText().equals("")) {
                try {
                    EBASdao.ClearLine10();
                    L10_Qty_Out.setText("");
                    L10_First_Desc.setText("");
                    L10_Orig_Sku.setText("");
                    L10_Orig_Desc.setText("");
                    L10_Orig_Attr.setText("");
                    L10_Orig_Size.setText("");
                    L10_Orig_Retail.setText("");
                    L10_Manuf_Inspec.setText("");
                    L10_New_Used.setSelectedItem("New");
                    L10_Reason_DropDown.setSelectedItem("");
                    L10_Desc_Damage.setText("");
                    L10_Cust_Satisf_ChkBox.setSelected(false);
                    L10_Warranty_ChkBox.setSelected(false);
                    L10_Done_ChkBox.setSelected(false);
                    L10_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else { */
            try {
                EBASdao.main(args);
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (L10_First_Sku.getText().matches("[0-9]+") && L10_First_Sku.getText().length() < 7 ) {
            
            if (EBASdao.skuRslt.equals("good")) {
                L10_First_Sku.setBackground(Color.WHITE);
                L10_First_Desc.setText(EBASdao.prodName);
                
             if (L10_Qty_Out.getText().matches("[1-9]+") && L10_Qty_Out.getText().length() < 2 ) {
                 L10_Qty_Out.setBackground(Color.WHITE);
                
              if (EBASdao.skuRslt2.equals("good") && L10_Orig_Sku.getText().matches("[0-9]+") && L10_Orig_Sku.getText().length() < 7 ) {
                L10_Orig_Sku.setBackground(Color.WHITE);
                L10_Orig_Desc.setText(EBASdao.prodName2);
                L10_Orig_Attr.setText(EBASdao.prodAttr);
                L10_Orig_Size.setText(EBASdao.prodSize);
                L10_Orig_Retail.setText(EBASdao.prodPr);
                
                if (L10_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L10_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                || L10_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L10_Manuf_Inspec.getText().matches("[0-9 ]+") || L10_Manuf_Inspec.getText().equals("")) { 
                // (L10_Manuf_Inspec.getText().matches("[1-8 ]+") && L10_Manuf_Inspec.getText().length() <2 || L10_Manuf_Inspec.getText().equals("")) {
		L10_Manuf_Inspec.setBackground(Color.WHITE);
                
              //  if((Integer.parseInt(L10_First_Sku.getText()) < 799610 || Integer.parseInt(L10_First_Sku.getText()) > 790407)
              //  && ((L10_Manuf_Inspec.getText().equals("DW03") || L10_Manuf_Inspec.getText().equals("dw03")) || (L10_Manuf_Inspec.getText().equals("DW04") || L10_Manuf_Inspec.getText().equals("dw04")))) {
              //  L10_Manuf_Inspec.setBackground(Color.WHITE);
                
                if (!L10_Reason_DropDown.getSelectedItem().equals("")) {
             
                if (L10_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L10_Desc_Damage.setBackground(Color.WHITE);
                    
                if (L10_Cust_Satisf_ChkBox.isSelected() != L10_Warranty_ChkBox.isSelected()){  
                    if (!L10_Cust_Satisf_ChkBox.isSelected() || !L10_Warranty_ChkBox.isSelected()) {

                EBASdao.Field1 = L10_First_Sku.getText();
                EBASdao.Field2 = L10_Qty_Out.getText();
                EBASdao.Field3 = L10_First_Desc.getText();
                EBASdao.Field4 = L10_Orig_Sku.getText();
                EBASdao.Field5 = L10_Orig_Desc.getText();
                EBASdao.Field6 = L10_Orig_Attr.getText();
                EBASdao.Field7 = L10_Orig_Size.getText();
                EBASdao.Field8 = L10_Orig_Retail.getText();
                EBASdao.Field9 = L10_Manuf_Inspec.getText();
                EBASdao.Field10 = L10_New_Used.getSelectedItem().toString();
                EBASdao.Field11 = L10_Reason_DropDown.getSelectedItem().toString();                
                EBASdao.Field12 = L10_Desc_Damage.getText();
                
                if (L10_Cust_Satisf_ChkBox.isSelected()) {
                    EBASdao.Field13 = "true";
                } else {
                    EBASdao.Field13 = "false";
                }
                if (L10_Warranty_ChkBox.isSelected()) {
                    EBASdao.Field14 = "true";
                } else {
                    EBASdao.Field14 = "false";
                }
                EBASdao.rline = L10.getText().replace(")", "");
                EBASdao.CmtLn();
                jPanel6.scrollRectToVisible(L10.getBounds(null));
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Both Warranty and Customer Satisfaction cannot be selected, pick one please.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L10_Done_ChkBox.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "You must Pick Warranty or Customer Satisfaction.", "Warranty / Cust Satisf Error", JOptionPane.ERROR_MESSAGE);
                    L10_Done_ChkBox.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                    L10_Done_ChkBox.setSelected(false);
                    L10_Desc_Damage.setBackground(Color.yellow);
                    L10_Desc_Damage.requestFocus();
                }                
                } else {
                    JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                    L10_Done_ChkBox.setSelected(false);                   
                    L10_Reason_DropDown.requestFocus();
                }
                /*
                } else {
                   JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_Manuf_Inspec.requestFocus();
                    L1_Manuf_Inspec.setBackground(Color.yellow);
                }
                */
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
                    L10_Done_ChkBox.setSelected(false);
                    L10_Manuf_Inspec.setBackground(Color.yellow);
                    L10_Manuf_Inspec.requestFocus();
                }
             } else {
                L10_Done_ChkBox.setSelected(false);
                L10_Orig_Sku.setBackground(Color.yellow);
                L10_Orig_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid Exclusive Brand SKU", "Orig Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L10.getBounds(null));
            }
            } else {
                 JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                L10_Done_ChkBox.setSelected(false);
                L10_Qty_Out.setBackground(Color.yellow);
                L10_Qty_Out.requestFocus();
                jPanel6.scrollRectToVisible(L10.getBounds(null));
            }
            } else {
                L10_Done_ChkBox.setSelected(false);
                L10_First_Sku.setBackground(Color.yellow);
                L10_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                jPanel6.scrollRectToVisible(L10.getBounds(null));
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L10_Done_ChkBox.setSelected(false);
                L10_First_Sku.requestFocus();
                L10_First_Sku.setBackground(Color.yellow);
                jPanel6.scrollRectToVisible(L10.getBounds(null));
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
            L10_Manuf_Inspec.setEnabled(true);
            L10_New_Used.setEnabled(true);
            L10_Reason_DropDown.setEnabled(true);
            L10_Desc_Damage.setEnabled(true);           
            L10_Cust_Satisf_ChkBox.setEnabled(true);
            L10_Warranty_ChkBox.setEnabled(true);
            L10_Done_ChkBox.setEnabled(true);
            L10_Timestamp.setEnabled(true);
        } 
         manager.focusNextComponent();
       }
    }//GEN-LAST:event_L10_Done_ChkBoxKeyPressed

    // validates if the number input is within the range 1-9 and advances to the next input field
    private void L1_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Qty_OutKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (L1_Qty_Out.getText().matches("[1-9]+") && L1_Qty_Out.getText().length() < 2 ) {
             L1_Orig_Sku.requestFocus();
             L1_Qty_Out.setBackground(Color.white);
        } else {
            L1_Qty_Out.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
        }
    }    
    }//GEN-LAST:event_L1_Qty_OutKeyPressed

    // validates if the number input is within the range 1-9 and advances to the next input field
    private void L2_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Qty_OutKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (L2_Qty_Out.getText().matches("[1-9]+") && L2_Qty_Out.getText().length() < 2 ) {
            L2_Orig_Sku.requestFocus();
            L2_Qty_Out.setBackground(Color.white);
        } else {
            L2_Qty_Out.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE); 
        }
    }    
    }//GEN-LAST:event_L2_Qty_OutKeyPressed

    // validates if the number input is within the range 1-9 and advances to the next input field
    private void L3_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Qty_OutKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (L3_Qty_Out.getText().matches("[1-9]+") && L3_Qty_Out.getText().length() < 2 ) {
             L3_Orig_Sku.requestFocus();
             L3_Qty_Out.setBackground(Color.white);
        } else {
            L3_Qty_Out.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);   
        }
    }    
    }//GEN-LAST:event_L3_Qty_OutKeyPressed

    // validates if the number input is within the range 1-9 and advances to the next input field
    private void L4_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Qty_OutKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (L4_Qty_Out.getText().matches("[1-9]+") && L4_Qty_Out.getText().length() < 2 ) {
            L4_Orig_Sku.requestFocus();
            L4_Qty_Out.setBackground(Color.white);
        } else {
            L4_Qty_Out.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE); 
        }
    }    
    }//GEN-LAST:event_L4_Qty_OutKeyPressed

    // validates if the number input is within the range 1-9 and advances to the next input field
    private void L5_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Qty_OutKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (L5_Qty_Out.getText().matches("[1-9]+") && L5_Qty_Out.getText().length() < 2 ) {
            L5_Orig_Sku.requestFocus();
            L5_Qty_Out.setBackground(Color.white);
        } else {
            L5_Qty_Out.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);   
        }
    }    
    }//GEN-LAST:event_L5_Qty_OutKeyPressed

    // validates if the number input is within the range 1-9 and advances to the next input field
    private void L6_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Qty_OutKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (L6_Qty_Out.getText().matches("[1-9]+") && L6_Qty_Out.getText().length() < 2 ) {
            L6_Orig_Sku.requestFocus();
            L6_Qty_Out.setBackground(Color.white);
        } else {
            L6_Qty_Out.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);  
        }
    }    
    }//GEN-LAST:event_L6_Qty_OutKeyPressed

    // validates if the number input is within the range 1-9 and advances to the next input field
    private void L7_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Qty_OutKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (L7_Qty_Out.getText().matches("[1-9]+") && L7_Qty_Out.getText().length() < 2 ) {
            L7_Orig_Sku.requestFocus();
            L7_Qty_Out.setBackground(Color.white);
        } else {
            L7_Qty_Out.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);   
        }
    }    
    }//GEN-LAST:event_L7_Qty_OutKeyPressed

    // validates if the number input is within the range 1-9 and advances to the next input field
    private void L8_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Qty_OutKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (L8_Qty_Out.getText().matches("[1-9]+") && L8_Qty_Out.getText().length() < 2 ) {
            L8_Orig_Sku.requestFocus();
            L8_Qty_Out.setBackground(Color.white);
        } else {
            L8_Qty_Out.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);  
        }
    }    
    }//GEN-LAST:event_L8_Qty_OutKeyPressed

    // validates if the number input is within the range 1-9 and advances to the next input field
    private void L9_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Qty_OutKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (L9_Qty_Out.getText().matches("[1-9]+") && L9_Qty_Out.getText().length() < 2 ) {
            L9_Orig_Sku.requestFocus();
            L9_Qty_Out.setBackground(Color.white);
        } else {
            L9_Qty_Out.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);  
        }
    }    
    }//GEN-LAST:event_L9_Qty_OutKeyPressed

    // validates if the number input is within the range 1-9 and advances to the next input field
    private void L10_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Qty_OutKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (L10_Qty_Out.getText().matches("[1-9]+") && L10_Qty_Out.getText().length() < 2 ) {
            L10_Orig_Sku.requestFocus();
            L10_Qty_Out.setBackground(Color.white);
        } else {
            JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
            L10_Qty_Out.setBackground(Color.yellow);
        }
    }    
    }//GEN-LAST:event_L10_Qty_OutKeyPressed

    // advances to the next field
    private void L1_Manuf_InspecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Manuf_InspecKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L1_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L1_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L1_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L1_Manuf_Inspec.getText().matches("[0-9 ]+") || L1_Manuf_Inspec.getText().equals("")) {
           // (L1_Manuf_Inspec.getText().matches("[1-8 ]+") && L1_Manuf_Inspec.getText().length() <2 || L1_Manuf_Inspec.getText().equals("")) {
		L1_Manuf_Inspec.setBackground(Color.WHITE);
                L1_New_Used.requestFocus();               
                
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
        	L1_Manuf_Inspec.setBackground(Color.yellow);
        }        
            /* if((Integer.parseInt(L1_First_Sku.getText()) < 799610 || Integer.parseInt(L1_First_Sku.getText()) > 790407)
                && ((L1_Manuf_Inspec.getText().equals("DW03") || L1_Manuf_Inspec.getText().equals("dw03")) || (L1_Manuf_Inspec.getText().equals("DW04") || L1_Manuf_Inspec.getText().equals("dw04")))) {
                L1_Manuf_Inspec.setBackground(Color.WHITE);
            
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                L1_Done_ChkBox.setSelected(false);
                L1_Manuf_Inspec.requestFocus();
                L1_Manuf_Inspec.setBackground(Color.yellow);
            } */        
        }
    }//GEN-LAST:event_L1_Manuf_InspecKeyPressed

    // advances to the next field and move the focus of the window to right side 
    private void L1_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_New_UsedKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L1_Reason_DropDown.requestFocus();            
        }
    }//GEN-LAST:event_L1_New_UsedKeyPressed

    // advances to the next field
    private void L1_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Reason_DropDownKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L1_Desc_Damage.requestFocus(); 
        }
    }//GEN-LAST:event_L1_Reason_DropDownKeyPressed

    // advances to the next field
    private void L1_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Desc_DamageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L1_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L1_Desc_Damage.setBackground(Color.WHITE);
                L1_Cust_Satisf_ChkBox.requestFocus();
        } else {
            JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
            L1_Desc_Damage.setBackground(Color.yellow);
        }            
        }
    }//GEN-LAST:event_L1_Desc_DamageKeyPressed

    // advances to the next field
    private void L2_Manuf_InspecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Manuf_InspecKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           if (L2_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L2_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L2_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L2_Manuf_Inspec.getText().matches("[0-9 ]+") || L2_Manuf_Inspec.getText().equals("")) {
           // (L2_Manuf_Inspec.getText().matches("[1-8 ]+") && L2_Manuf_Inspec.getText().length() <2 || L2_Manuf_Inspec.getText().equals("")) {
		L2_Manuf_Inspec.setBackground(Color.WHITE);
                L2_New_Used.requestFocus(); 
        } else {
            JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
            L2_Manuf_Inspec.setBackground(Color.yellow);
        }  
            /*
            if((Integer.parseInt(L2_First_Sku.getText()) < 799610 || Integer.parseInt(L2_First_Sku.getText()) > 790407)
                && ((L2_Manuf_Inspec.getText().equals("DW03") || L2_Manuf_Inspec.getText().equals("dw03")) || (L2_Manuf_Inspec.getText().equals("DW04") || L2_Manuf_Inspec.getText().equals("dw04")))) {
                L2_Manuf_Inspec.setBackground(Color.WHITE);
            
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                L2_Done_ChkBox.setSelected(false);
                L2_Manuf_Inspec.requestFocus();
                L2_Manuf_Inspec.setBackground(Color.yellow);
            }
             */
        }
    }//GEN-LAST:event_L2_Manuf_InspecKeyPressed

    // advances to the next field
    private void L2_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_New_UsedKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L2_Reason_DropDown.requestFocus();                       
        }
    }//GEN-LAST:event_L2_New_UsedKeyPressed

    // advances to the next field
    private void L2_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Reason_DropDownKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L2_Desc_Damage.requestFocus(); 
        }
    }//GEN-LAST:event_L2_Reason_DropDownKeyPressed

    // advances to the next field
    private void L2_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Desc_DamageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L2_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L2_Desc_Damage.setBackground(Color.WHITE);
                L2_Cust_Satisf_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L2_Desc_Damage.setBackground(Color.yellow);
        }
        }
    }//GEN-LAST:event_L2_Desc_DamageKeyPressed

    // advances to the next field
    private void L3_Manuf_InspecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Manuf_InspecKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L3_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L3_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L3_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L3_Manuf_Inspec.getText().matches("[0-9 ]+") || L3_Manuf_Inspec.getText().equals("")) {
          //  (L3_Manuf_Inspec.getText().matches("[1-8 ]+") && L3_Manuf_Inspec.getText().length() <2 || L3_Manuf_Inspec.getText().equals("")) {
		L3_Manuf_Inspec.setBackground(Color.WHITE);
                L3_New_Used.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
        	L3_Manuf_Inspec.setBackground(Color.yellow);
        }
        /*    
        if((Integer.parseInt(L3_First_Sku.getText()) < 799610 || Integer.parseInt(L3_First_Sku.getText()) > 790407)
                && ((L3_Manuf_Inspec.getText().equals("DW03") || L3_Manuf_Inspec.getText().equals("dw03")) || (L3_Manuf_Inspec.getText().equals("DW04") || L3_Manuf_Inspec.getText().equals("dw04")))) {
                L3_Manuf_Inspec.setBackground(Color.WHITE);
            
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                L3_Done_ChkBox.setSelected(false);
                L3_Manuf_Inspec.requestFocus();
                L3_Manuf_Inspec.setBackground(Color.yellow);
            }
            */         
        }
    }//GEN-LAST:event_L3_Manuf_InspecKeyPressed

    // advance to the next field
    private void L3_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_New_UsedKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L3_Reason_DropDown.requestFocus();
        }
    }//GEN-LAST:event_L3_New_UsedKeyPressed

    // advance to the next field
    private void L3_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Reason_DropDownKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L3_Desc_Damage.requestFocus(); 
        }
    }//GEN-LAST:event_L3_Reason_DropDownKeyPressed

    // advance to the next field
    private void L3_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Desc_DamageKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L3_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L3_Desc_Damage.setBackground(Color.WHITE);
                L3_Cust_Satisf_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L3_Desc_Damage.setBackground(Color.yellow);
        } 
        }
    }//GEN-LAST:event_L3_Desc_DamageKeyPressed

    // advance to the next field
    private void L4_Manuf_InspecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Manuf_InspecKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L4_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L4_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L4_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L4_Manuf_Inspec.getText().matches("[0-9 ]+") || L4_Manuf_Inspec.getText().equals("")) {
          //  (L4_Manuf_Inspec.getText().matches("[1-8 ]+")  && L4_Manuf_Inspec.getText().length() <2 || L4_Manuf_Inspec.getText().equals("")) {
		L4_Manuf_Inspec.setBackground(Color.WHITE);
                L4_New_Used.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
        	L4_Manuf_Inspec.setBackground(Color.yellow);
        }
        /*  
        if((Integer.parseInt(L4_First_Sku.getText()) < 799610 || Integer.parseInt(L4_First_Sku.getText()) > 790407)
                && ((L4_Manuf_Inspec.getText().equals("DW03") || L4_Manuf_Inspec.getText().equals("dw03")) || (L4_Manuf_Inspec.getText().equals("DW04") || L4_Manuf_Inspec.getText().equals("dw04")))) {
                L4_Manuf_Inspec.setBackground(Color.WHITE);
            
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
                L4_Done_ChkBox.setSelected(false);
                L4_Manuf_Inspec.requestFocus();
                L4_Manuf_Inspec.setBackground(Color.yellow);
            }
            */
        }
    }//GEN-LAST:event_L4_Manuf_InspecKeyPressed

    // advance to the next field
    private void L4_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_New_UsedKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L4_Reason_DropDown.requestFocus();            
        }
    }//GEN-LAST:event_L4_New_UsedKeyPressed

    // advance to the next field
    private void L4_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Reason_DropDownKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L4_Desc_Damage.requestFocus(); 
        }
    }//GEN-LAST:event_L4_Reason_DropDownKeyPressed

    // advance to the next field
    private void L4_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Desc_DamageKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L4_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L4_Desc_Damage.setBackground(Color.WHITE);
                L4_Cust_Satisf_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L4_Desc_Damage.setBackground(Color.yellow);
        }
        }
    }//GEN-LAST:event_L4_Desc_DamageKeyPressed

    // advance to the next field
    private void L5_Manuf_InspecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Manuf_InspecKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           if (L5_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L5_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L5_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L5_Manuf_Inspec.getText().matches("[0-9 ]+") || L5_Manuf_Inspec.getText().equals("")) {
           // (L5_Manuf_Inspec.getText().matches("[1-8 ]+") && L5_Manuf_Inspec.getText().length() <2 || L5_Manuf_Inspec.getText().equals("")) {
		L5_Manuf_Inspec.setBackground(Color.WHITE);
                L5_New_Used.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
        	L5_Manuf_Inspec.setBackground(Color.yellow);
        }
         /*  
        if((Integer.parseInt(L5_First_Sku.getText()) < 799610 || Integer.parseInt(L5_First_Sku.getText()) > 790407)
            && ((L5_Manuf_Inspec.getText().equals("DW03") || L5_Manuf_Inspec.getText().equals("dw03")) || (L5_Manuf_Inspec.getText().equals("DW04") || L5_Manuf_Inspec.getText().equals("dw04")))) {
            L5_Manuf_Inspec.setBackground(Color.WHITE);
            
        } else {
            JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
            L5_Done_ChkBox.setSelected(false);
            L5_Manuf_Inspec.requestFocus();
            L5_Manuf_Inspec.setBackground(Color.yellow);
        }
           */
        }
    }//GEN-LAST:event_L5_Manuf_InspecKeyPressed

    // advance to the next field and focus window to the right
    private void L5_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_New_UsedKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L5_Reason_DropDown.requestFocus();
        }
    }//GEN-LAST:event_L5_New_UsedKeyPressed

    // advance to the next field
    private void L5_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Reason_DropDownKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L5_Desc_Damage.requestFocus(); 
        }
    }//GEN-LAST:event_L5_Reason_DropDownKeyPressed

    // advance to the next field
    private void L5_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Desc_DamageKeyPressed
          if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L5_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L5_Desc_Damage.setBackground(Color.WHITE);
                L5_Cust_Satisf_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L5_Desc_Damage.setBackground(Color.yellow);
        }
        }
    }//GEN-LAST:event_L5_Desc_DamageKeyPressed

    // advance to the next field
    private void L6_Manuf_InspecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Manuf_InspecKeyPressed
          if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L6_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L6_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L6_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L6_Manuf_Inspec.getText().matches("[0-9 ]+") || L6_Manuf_Inspec.getText().equals("")) {
            // (L6_Manuf_Inspec.getText().matches("[1-8 ]+") && L6_Manuf_Inspec.getText().length() <2 || L6_Manuf_Inspec.getText().equals("")) {
		L6_Manuf_Inspec.setBackground(Color.WHITE);
                L6_New_Used.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
        	L6_Manuf_Inspec.setBackground(Color.yellow);
        }
        /*    
        if((Integer.parseInt(L6_First_Sku.getText()) < 799610 || Integer.parseInt(L6_First_Sku.getText()) > 790407)
                && ((L6_Manuf_Inspec.getText().equals("DW03") || L6_Manuf_Inspec.getText().equals("dw03")) || (L6_Manuf_Inspec.getText().equals("DW04") || L6_Manuf_Inspec.getText().equals("dw04")))) {
                L6_Manuf_Inspec.setBackground(Color.WHITE);
            
        } else {
            JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
            L6_Done_ChkBox.setSelected(false);
            L6_Manuf_Inspec.requestFocus();
            L6_Manuf_Inspec.setBackground(Color.yellow);
        }
            */
        }
    }//GEN-LAST:event_L6_Manuf_InspecKeyPressed

    // advance to the next field and focus window to the right
    private void L6_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_New_UsedKeyPressed
           if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L6_Reason_DropDown.requestFocus();
        }
    }//GEN-LAST:event_L6_New_UsedKeyPressed

    // advance to the next field
    private void L6_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Reason_DropDownKeyPressed
          if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L6_Desc_Damage.requestFocus(); 
        }
    }//GEN-LAST:event_L6_Reason_DropDownKeyPressed

    // advance to the next field
    private void L6_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Desc_DamageKeyPressed
           if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L6_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L6_Desc_Damage.setBackground(Color.WHITE);
                L6_Cust_Satisf_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L6_Desc_Damage.setBackground(Color.yellow);
        }
        }
    }//GEN-LAST:event_L6_Desc_DamageKeyPressed

    // advance to the next field
    private void L7_Manuf_InspecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Manuf_InspecKeyPressed
           if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L7_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L7_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L7_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L7_Manuf_Inspec.getText().matches("[0-9 ]+") || L7_Manuf_Inspec.getText().equals("")) {
           // (L7_Manuf_Inspec.getText().matches("[1-8 ]+") && L7_Manuf_Inspec.getText().length() <2 || L7_Manuf_Inspec.getText().equals("")) {
		L7_Manuf_Inspec.setBackground(Color.WHITE);
                L7_New_Used.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
        	L7_Manuf_Inspec.setBackground(Color.yellow);
        }
        /*    
        if((Integer.parseInt(L7_First_Sku.getText()) < 799610 || Integer.parseInt(L7_First_Sku.getText()) > 790407)
                && ((L7_Manuf_Inspec.getText().equals("DW03") || L7_Manuf_Inspec.getText().equals("dw03")) || (L7_Manuf_Inspec.getText().equals("DW04") || L7_Manuf_Inspec.getText().equals("dw04")))) {
                L7_Manuf_Inspec.setBackground(Color.WHITE);
            
        } else {
            JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
            L7_Done_ChkBox.setSelected(false);
            L7_Manuf_Inspec.requestFocus();
            L7_Manuf_Inspec.setBackground(Color.yellow);
        }
        */
        }
    }//GEN-LAST:event_L7_Manuf_InspecKeyPressed

    // advance to the next field and focus window to the right
    private void L7_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_New_UsedKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L7_Reason_DropDown.requestFocus();            
        }         
    }//GEN-LAST:event_L7_New_UsedKeyPressed

    // advance to the next field
    private void L7_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Reason_DropDownKeyPressed
           if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L7_Desc_Damage.requestFocus(); 
        }
    }//GEN-LAST:event_L7_Reason_DropDownKeyPressed

    // advance to the next field
    private void L7_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Desc_DamageKeyPressed
          if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L7_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L7_Desc_Damage.setBackground(Color.WHITE);
                L7_Cust_Satisf_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L7_Desc_Damage.setBackground(Color.yellow);
        }
        }
    }//GEN-LAST:event_L7_Desc_DamageKeyPressed

    // advance to the next field
    private void L8_Manuf_InspecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Manuf_InspecKeyPressed
           if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L8_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L8_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L8_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L8_Manuf_Inspec.getText().matches("[0-9 ]+") || L8_Manuf_Inspec.getText().equals("")) {
           // (L8_Manuf_Inspec.getText().matches("[1-8 ]+") && L8_Manuf_Inspec.getText().length() <2 || L8_Manuf_Inspec.getText().equals("")) {
		L8_Manuf_Inspec.setBackground(Color.WHITE);
                L8_New_Used.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
        	L8_Manuf_Inspec.setBackground(Color.yellow);
        }
         /*   
        if((Integer.parseInt(L8_First_Sku.getText()) < 799610 || Integer.parseInt(L8_First_Sku.getText()) > 790407)
                && ((L8_Manuf_Inspec.getText().equals("DW03") || L8_Manuf_Inspec.getText().equals("dw03")) || (L8_Manuf_Inspec.getText().equals("DW04") || L8_Manuf_Inspec.getText().equals("dw04")))) {
                L8_Manuf_Inspec.setBackground(Color.WHITE);
            
        } else {
            JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
            L8_Done_ChkBox.setSelected(false);
            L8_Manuf_Inspec.requestFocus();
            L8_Manuf_Inspec.setBackground(Color.yellow);
        }  
        */    
        }
    }//GEN-LAST:event_L8_Manuf_InspecKeyPressed

        // advance to the next field and focus window to the right
    private void L8_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_New_UsedKeyPressed
           if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L8_Reason_DropDown.requestFocus();
        }
    }//GEN-LAST:event_L8_New_UsedKeyPressed

        // advance to the next field
    private void L8_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Reason_DropDownKeyPressed
          if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L8_Desc_Damage.requestFocus(); 
        }
    }//GEN-LAST:event_L8_Reason_DropDownKeyPressed

    // advance to the next field
    private void L8_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Desc_DamageKeyPressed
           if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L8_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L8_Desc_Damage.setBackground(Color.WHITE);
                L8_Cust_Satisf_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L8_Desc_Damage.setBackground(Color.yellow);
        }
        }
    }//GEN-LAST:event_L8_Desc_DamageKeyPressed

    // advance to the next field
    private void L9_Manuf_InspecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Manuf_InspecKeyPressed
          if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L9_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L9_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L9_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L9_Manuf_Inspec.getText().matches("[0-9 ]+") || L9_Manuf_Inspec.getText().equals("")) {
            // (L9_Manuf_Inspec.getText().matches("[1-8 ]+") && L9_Manuf_Inspec.getText().length() <2 || L9_Manuf_Inspec.getText().equals("")) {
		L9_Manuf_Inspec.setBackground(Color.WHITE);
                L9_New_Used.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
        	L9_Manuf_Inspec.setBackground(Color.yellow);
        }
        /*    
        if((Integer.parseInt(L9_First_Sku.getText()) < 799610 || Integer.parseInt(L9_First_Sku.getText()) > 790407)
                && ((L9_Manuf_Inspec.getText().equals("DW03") || L9_Manuf_Inspec.getText().equals("dw03")) || (L9_Manuf_Inspec.getText().equals("DW04") || L9_Manuf_Inspec.getText().equals("dw04")))) {
                L9_Manuf_Inspec.setBackground(Color.WHITE);
            
        } else {
            JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
            L9_Done_ChkBox.setSelected(false);
            L9_Manuf_Inspec.requestFocus();
            L9_Manuf_Inspec.setBackground(Color.yellow);
        }
        */
        }
    }//GEN-LAST:event_L9_Manuf_InspecKeyPressed

    // advance to the next field and focus window to the right
    private void L9_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_New_UsedKeyPressed
          if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L9_Reason_DropDown.requestFocus();
          }
    }//GEN-LAST:event_L9_New_UsedKeyPressed

    // advance to the next field
    private void L9_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Reason_DropDownKeyPressed
          if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L9_Desc_Damage.requestFocus(); 
        }
    }//GEN-LAST:event_L9_Reason_DropDownKeyPressed

    // advance to the next field
    private void L9_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Desc_DamageKeyPressed
           if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L9_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L9_Desc_Damage.setBackground(Color.WHITE);
                L9_Cust_Satisf_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L9_Desc_Damage.setBackground(Color.yellow);
        }
        }
    }//GEN-LAST:event_L9_Desc_DamageKeyPressed

    // advance to the next field
    private void L10_Manuf_InspecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Manuf_InspecKeyPressed
           if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L10_Manuf_Inspec.getText().matches("[0-9 ]+[a-zA-Z ]+") || L10_Manuf_Inspec.getText().matches("[a-zA-Z ]+[0-9 ]+")
                 || L10_Manuf_Inspec.getText().matches("[a-zA-Z ]+") || L10_Manuf_Inspec.getText().matches("[0-9 ]+") || L10_Manuf_Inspec.getText().equals("")) {
            // (L10_Manuf_Inspec.getText().matches("[1-8 ]+") && L10_Manuf_Inspec.getText().length() <2 || L10_Manuf_Inspec.getText().equals("")) {
		L10_Manuf_Inspec.setBackground(Color.WHITE);
                L10_New_Used.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input numbers and letters in this field", "Manuf Inspec Error", JOptionPane.ERROR_MESSAGE);
        	L10_Manuf_Inspec.setBackground(Color.yellow);
        }
        /*    
        if((Integer.parseInt(L10_First_Sku.getText()) < 799610 || Integer.parseInt(L10_First_Sku.getText()) > 790407)
            && ((L10_Manuf_Inspec.getText().equals("DW03") || L10_Manuf_Inspec.getText().equals("dw03")) || (L10_Manuf_Inspec.getText().equals("DW04") || L10_Manuf_Inspec.getText().equals("dw04")))) {
            L10_Manuf_Inspec.setBackground(Color.WHITE);
            
        } else {
            JOptionPane.showMessageDialog(frame, "Wrong input, This helmet requires you put either DW03 or DW04 version of bluetooth in Manuf Inspec field to continue", "Must put in version of BT blank Error", JOptionPane.ERROR_MESSAGE);
            L10_Done_ChkBox.setSelected(false);
            L10_Manuf_Inspec.requestFocus();
            L10_Manuf_Inspec.setBackground(Color.yellow);
        }
        */
        }
    }//GEN-LAST:event_L10_Manuf_InspecKeyPressed

    // advance to the next field and focus window to the right
    private void L10_New_UsedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_New_UsedKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L10_Reason_DropDown.requestFocus();
        }
    }//GEN-LAST:event_L10_New_UsedKeyPressed

    // advance to the next field
    private void L10_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Reason_DropDownKeyPressed
           if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L10_Desc_Damage.requestFocus(); 
        }
    }//GEN-LAST:event_L10_Reason_DropDownKeyPressed

    // advance to the next field
    private void L10_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Desc_DamageKeyPressed
          if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L10_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
		L10_Desc_Damage.setBackground(Color.WHITE);
                L10_Cust_Satisf_ChkBox.requestFocus();
        } else {
        	JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field", "Desc of Damage cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        	L10_Desc_Damage.setBackground(Color.yellow);
        } 
        }
    }//GEN-LAST:event_L10_Desc_DamageKeyPressed

    // this allows IC to remove the temporary table from the database and inserts line data in one table and header data 
    // in another then it is placed in the next list in the admin panel that is now ready to be exported by IC
    private void RdyforExportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdyforExportBtnActionPerformed
    int response = JOptionPane.showConfirmDialog(null, "Do you want to Confirm?", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");      
    } else if (response == JOptionPane.YES_OPTION) {
      System.out.println("Yes button clicked");
        try {
                EBASdao.FormReadyForExport();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                EBASdao.LineArchive();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(frame, "Form is Ready for Export");
            InvAdj_Admin.Rpnt();
            this.dispose();
    } else if (response == JOptionPane.CLOSED_OPTION) {
      System.out.println("JOptionPane closed");
    } 
    }//GEN-LAST:event_RdyforExportBtnActionPerformed

    private void L5_Cust_Satisf_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Cust_Satisf_ChkBoxKeyPressed
           if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L5_Cust_Satisf_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L5_Cust_Satisf_ChkBoxKeyPressed

    private void L6_Cust_Satisf_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Cust_Satisf_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L6_Cust_Satisf_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L6_Cust_Satisf_ChkBoxKeyPressed

    private void L7_Cust_Satisf_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Cust_Satisf_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L7_Cust_Satisf_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L7_Cust_Satisf_ChkBoxKeyPressed

    private void L8_Cust_Satisf_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Cust_Satisf_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L8_Cust_Satisf_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L8_Cust_Satisf_ChkBoxKeyPressed

    private void L9_Cust_Satisf_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Cust_Satisf_ChkBoxKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L9_Cust_Satisf_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L9_Cust_Satisf_ChkBoxKeyPressed

    private void L10_Cust_Satisf_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Cust_Satisf_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L10_Cust_Satisf_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L10_Cust_Satisf_ChkBoxKeyPressed

    private void L5_Warranty_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Warranty_ChkBoxKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L5_Warranty_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L5_Warranty_ChkBoxKeyPressed

    private void L6_Warranty_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Warranty_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L6_Warranty_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L6_Warranty_ChkBoxKeyPressed

    private void L7_Warranty_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Warranty_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L7_Warranty_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L7_Warranty_ChkBoxKeyPressed

    private void L8_Warranty_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Warranty_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L8_Warranty_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L8_Warranty_ChkBoxKeyPressed

    private void L9_Warranty_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Warranty_ChkBoxKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L9_Warranty_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L9_Warranty_ChkBoxKeyPressed

    private void L10_Warranty_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Warranty_ChkBoxKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            L10_Warranty_ChkBox.setSelected(true);
            manager.focusNextComponent();
        }
    }//GEN-LAST:event_L10_Warranty_ChkBoxKeyPressed

    private void DL_Print_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DL_Print_BtnActionPerformed
        String[] args = null;
        PrintForms.formType = ("EBAS");
        PrintForms.main(args);
    }//GEN-LAST:event_DL_Print_BtnActionPerformed

    private void L1_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L1_First_SkuFocusLost
        EBASdao.setSkuInput(L1_First_Sku.getText());
            String[] args = null;
            if (L1_First_Sku.getText().matches("[0-9]+") && L1_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L1_First_Sku.setBackground(Color.WHITE);
                L1_First_Desc.setText(EBASdao.prodName);
            }
            }
    }//GEN-LAST:event_L1_First_SkuFocusLost

    private void L1_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L1_Orig_SkuFocusLost
        EBASdao.setSkuInput2(L1_Orig_Sku.getText());
            if (!L1_Orig_Sku.getText().equals(L1_First_Sku.getText())) {
                if (L1_Orig_Sku.getText().matches("[0-9]+") && L1_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L1_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L1_Orig_Desc.setText(EBASdao.prodName2);
                L1_Orig_Attr.setText(EBASdao.prodAttr);
                L1_Orig_Size.setText(EBASdao.prodSize);
                L1_Orig_Retail.setText(EBASdao.prodPr);
                jPanel6.scrollRectToVisible(L2_Timestamp.getBounds(null));
            }
        }
    }
    }//GEN-LAST:event_L1_Orig_SkuFocusLost

    private void L2_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L2_First_SkuFocusLost
        EBASdao.setSkuInput(L2_First_Sku.getText());
            String[] args = null;
            if (L2_First_Sku.getText().matches("[0-9]+") && L2_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L2_First_Sku.setBackground(Color.WHITE);
                L2_First_Desc.setText(EBASdao.prodName);
            }
	}
    }//GEN-LAST:event_L2_First_SkuFocusLost

    private void L2_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L2_Orig_SkuFocusLost
        EBASdao.setSkuInput2(L2_Orig_Sku.getText());
            if (!L2_Orig_Sku.getText().equals(L2_First_Sku.getText())) {
                if (L2_Orig_Sku.getText().matches("[0-9]+") && L2_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L2_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L2_Orig_Desc.setText(EBASdao.prodName2);
                L2_Orig_Attr.setText(EBASdao.prodAttr);
                L2_Orig_Size.setText(EBASdao.prodSize);
                L2_Orig_Retail.setText(EBASdao.prodPr);
                jPanel6.scrollRectToVisible(L2_Timestamp.getBounds(null));
            }
        }
    }
    }//GEN-LAST:event_L2_Orig_SkuFocusLost

    private void L3_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L3_First_SkuFocusLost
        EBASdao.setSkuInput(L3_First_Sku.getText());
            String[] args = null;
            if (L3_First_Sku.getText().matches("[0-9]+") && L3_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L3_First_Sku.setBackground(Color.WHITE);
                L3_First_Desc.setText(EBASdao.prodName);
            }
	}
    }//GEN-LAST:event_L3_First_SkuFocusLost

    private void L3_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L3_Orig_SkuFocusLost
        EBASdao.setSkuInput2(L3_Orig_Sku.getText());
            if (!L3_Orig_Sku.getText().equals(L3_First_Sku.getText())) {
                if (L3_Orig_Sku.getText().matches("[0-9]+") && L3_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L3_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L3_Orig_Desc.setText(EBASdao.prodName2);
                L3_Orig_Attr.setText(EBASdao.prodAttr);
                L3_Orig_Size.setText(EBASdao.prodSize);
                L3_Orig_Retail.setText(EBASdao.prodPr);
                jPanel6.scrollRectToVisible(L2_Timestamp.getBounds(null));
            }
        }
    }
    }//GEN-LAST:event_L3_Orig_SkuFocusLost

    private void L4_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L4_First_SkuFocusLost
        EBASdao.setSkuInput(L4_First_Sku.getText());
            String[] args = null;
            if (L4_First_Sku.getText().matches("[0-9]+") && L4_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L4_First_Sku.setBackground(Color.WHITE);
                L4_First_Desc.setText(EBASdao.prodName);
            }
	}
    }//GEN-LAST:event_L4_First_SkuFocusLost

    private void L4_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L4_Orig_SkuFocusLost
        EBASdao.setSkuInput2(L4_Orig_Sku.getText());
            if (!L4_Orig_Sku.getText().equals(L4_First_Sku.getText())) {
                if (L4_Orig_Sku.getText().matches("[0-9]+") && L4_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L4_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L4_Orig_Desc.setText(EBASdao.prodName2);
                L4_Orig_Attr.setText(EBASdao.prodAttr);
                L4_Orig_Size.setText(EBASdao.prodSize);
                L4_Orig_Retail.setText(EBASdao.prodPr);
                jPanel6.scrollRectToVisible(L2_Timestamp.getBounds(null));
            }
        }
    }
    }//GEN-LAST:event_L4_Orig_SkuFocusLost

    private void L5_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L5_First_SkuFocusLost
        EBASdao.setSkuInput(L5_First_Sku.getText());
            String[] args = null;
            if (L5_First_Sku.getText().matches("[0-9]+") && L5_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L5_First_Sku.setBackground(Color.WHITE);
                L5_First_Desc.setText(EBASdao.prodName);
            }
	}
    }//GEN-LAST:event_L5_First_SkuFocusLost

    private void L5_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L5_Orig_SkuFocusLost
        EBASdao.setSkuInput2(L5_Orig_Sku.getText());
            if (!L5_Orig_Sku.getText().equals(L5_First_Sku.getText())) {
                if (L1_Orig_Sku.getText().matches("[0-9]+") && L5_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L5_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L5_Orig_Desc.setText(EBASdao.prodName2);
                L5_Orig_Attr.setText(EBASdao.prodAttr);
                L5_Orig_Size.setText(EBASdao.prodSize);
                L5_Orig_Retail.setText(EBASdao.prodPr);
                jPanel6.scrollRectToVisible(L2_Timestamp.getBounds(null));
            }
        }
    }
    }//GEN-LAST:event_L5_Orig_SkuFocusLost

    private void L6_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L6_First_SkuFocusLost
        EBASdao.setSkuInput(L6_First_Sku.getText());
            String[] args = null;
            if (L6_First_Sku.getText().matches("[0-9]+") && L6_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L6_First_Sku.setBackground(Color.WHITE);
                L6_First_Desc.setText(EBASdao.prodName);
            }
	}
    }//GEN-LAST:event_L6_First_SkuFocusLost

    private void L6_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L6_Orig_SkuFocusLost
        EBASdao.setSkuInput2(L6_Orig_Sku.getText());
            if (!L6_Orig_Sku.getText().equals(L6_First_Sku.getText())) {
                if (L6_Orig_Sku.getText().matches("[0-9]+") && L6_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L6_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L6_Orig_Desc.setText(EBASdao.prodName2);
                L6_Orig_Attr.setText(EBASdao.prodAttr);
                L6_Orig_Size.setText(EBASdao.prodSize);
                L6_Orig_Retail.setText(EBASdao.prodPr);
                jPanel6.scrollRectToVisible(L2_Timestamp.getBounds(null));
            }
        }
    }
    }//GEN-LAST:event_L6_Orig_SkuFocusLost

    private void L7_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L7_First_SkuFocusLost
        EBASdao.setSkuInput(L7_First_Sku.getText());
            String[] args = null;
            if (L7_First_Sku.getText().matches("[0-9]+") && L7_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L7_First_Sku.setBackground(Color.WHITE);
                L7_First_Desc.setText(EBASdao.prodName);
            }
	}
    }//GEN-LAST:event_L7_First_SkuFocusLost

    private void L7_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L7_Orig_SkuFocusLost
        EBASdao.setSkuInput2(L7_Orig_Sku.getText());
            if (!L7_Orig_Sku.getText().equals(L7_First_Sku.getText())) {
                if (L7_Orig_Sku.getText().matches("[0-9]+") && L7_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L7_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L7_Orig_Desc.setText(EBASdao.prodName2);
                L7_Orig_Attr.setText(EBASdao.prodAttr);
                L7_Orig_Size.setText(EBASdao.prodSize);
                L7_Orig_Retail.setText(EBASdao.prodPr);
                jPanel6.scrollRectToVisible(L2_Timestamp.getBounds(null));
            }
        }
    }
    }//GEN-LAST:event_L7_Orig_SkuFocusLost

    private void L8_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L8_First_SkuFocusLost
        EBASdao.setSkuInput(L8_First_Sku.getText());
            String[] args = null;
            if (L8_First_Sku.getText().matches("[0-9]+") && L8_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L8_First_Sku.setBackground(Color.WHITE);
                L8_First_Desc.setText(EBASdao.prodName);
            }
	}
    }//GEN-LAST:event_L8_First_SkuFocusLost

    private void L8_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L8_Orig_SkuFocusLost
        EBASdao.setSkuInput2(L8_Orig_Sku.getText());
            if (!L8_Orig_Sku.getText().equals(L8_First_Sku.getText())) {
                if (L8_Orig_Sku.getText().matches("[0-9]+") && L8_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L8_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L8_Orig_Desc.setText(EBASdao.prodName2);
                L8_Orig_Attr.setText(EBASdao.prodAttr);
                L8_Orig_Size.setText(EBASdao.prodSize);
                L8_Orig_Retail.setText(EBASdao.prodPr);
                jPanel6.scrollRectToVisible(L2_Timestamp.getBounds(null));
            }
        }
    }
    }//GEN-LAST:event_L8_Orig_SkuFocusLost

    private void L9_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L9_First_SkuFocusLost
       EBASdao.setSkuInput(L9_First_Sku.getText());
            String[] args = null;
            if (L9_First_Sku.getText().matches("[0-9]+") && L9_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L9_First_Sku.setBackground(Color.WHITE);
                L9_First_Desc.setText(EBASdao.prodName);
            }
	}
    }//GEN-LAST:event_L9_First_SkuFocusLost

    private void L9_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L9_Orig_SkuFocusLost
        EBASdao.setSkuInput2(L9_Orig_Sku.getText());
            if (!L9_Orig_Sku.getText().equals(L9_First_Sku.getText())) {
                if (L9_Orig_Sku.getText().matches("[0-9]+") && L9_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L9_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L9_Orig_Desc.setText(EBASdao.prodName2);
                L9_Orig_Attr.setText(EBASdao.prodAttr);
                L9_Orig_Size.setText(EBASdao.prodSize);
                L9_Orig_Retail.setText(EBASdao.prodPr);
                jPanel6.scrollRectToVisible(L2_Timestamp.getBounds(null));
            }
        }
    }
    }//GEN-LAST:event_L9_Orig_SkuFocusLost

    private void L10_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L10_First_SkuFocusLost
        EBASdao.setSkuInput(L10_First_Sku.getText());
            String[] args = null;
            if (L10_First_Sku.getText().matches("[0-9]+") && L10_First_Sku.getText().length() < 7 ) {
            try {
                EBASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt.equals("good")) {
                L10_First_Sku.setBackground(Color.WHITE);
                L10_First_Desc.setText(EBASdao.prodName);
            }
	}
    }//GEN-LAST:event_L10_First_SkuFocusLost

    private void L10_Orig_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L10_Orig_SkuFocusLost
        EBASdao.setSkuInput2(L10_Orig_Sku.getText());
            if (!L10_Orig_Sku.getText().equals(L10_First_Sku.getText())) {
                if (L10_Orig_Sku.getText().matches("[0-9]+") && L10_Orig_Sku.getText().length() < 7 ) {
            // String[] args = null;
            try {
                EBASdao.skuInput2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (EBASdao.skuRslt2.equals("good")) {
                L10_Orig_Sku.setBackground(Color.WHITE);
                // Auto-fill from item_master Table
                L10_Orig_Desc.setText(EBASdao.prodName2);
                L10_Orig_Attr.setText(EBASdao.prodAttr);
                L10_Orig_Size.setText(EBASdao.prodSize);
                L10_Orig_Retail.setText(EBASdao.prodPr);
                jPanel6.scrollRectToVisible(L2_Timestamp.getBounds(null));
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
                        EBASdao.ClearLine1();
                        L1_First_Sku.setText("");
                        L1_Qty_Out.setText("");
                        L1_First_Desc.setText("");
                        L1_Orig_Sku.setText("");
                        L1_Orig_Desc.setText("");
                        L1_Orig_Attr.setText("");
                        L1_Orig_Size.setText("");
                        L1_Orig_Retail.setText("");
                        L1_Manuf_Inspec.setText("");
                        L1_New_Used.setSelectedItem("New");
                        L1_Reason_DropDown.setSelectedItem("");                    
                        L1_Desc_Damage.setText("");
                        L1_Cust_Satisf_ChkBox.setSelected(false);
                        L1_Warranty_ChkBox.setSelected(false);
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
                        L1_Manuf_Inspec.setEnabled(true);
                        L1_New_Used.setEnabled(true);
                        L1_Reason_DropDown.setEnabled(true);
                        L1_Desc_Damage.setEnabled(true);
                        L1_Cust_Satisf_ChkBox.setEnabled(true);
                        L1_Warranty_ChkBox.setEnabled(true);
                        L1_Done_ChkBox.setEnabled(true);
                        L1_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     }    break;
                 case "2":
                       try {
                        EBASdao.ClearLine2();
                        L2_First_Sku.setText("");
                        L2_Qty_Out.setText("");
                        L2_First_Desc.setText("");
                        L2_Orig_Sku.setText("");
                        L2_Orig_Desc.setText("");
                        L2_Orig_Attr.setText("");
                        L2_Orig_Size.setText("");
                        L2_Orig_Retail.setText("");
                        L2_Manuf_Inspec.setText("");
                        L2_New_Used.setSelectedItem("New");
                        L2_Reason_DropDown.setSelectedItem("");                    
                        L2_Desc_Damage.setText("");
                        L2_Cust_Satisf_ChkBox.setSelected(false);
                        L2_Warranty_ChkBox.setSelected(false);
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
                        L2_Manuf_Inspec.setEnabled(true);
                        L2_New_Used.setEnabled(true);
                        L2_Reason_DropDown.setEnabled(true);
                        L2_Desc_Damage.setEnabled(true);
                        L2_Cust_Satisf_ChkBox.setEnabled(true);
                        L2_Warranty_ChkBox.setEnabled(true);
                        L2_Done_ChkBox.setEnabled(true);
                        L2_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     } break;
                 case "3":
                     try {
                        EBASdao.ClearLine3();
                        L3_First_Sku.setText("");
                        L3_Qty_Out.setText("");
                        L3_First_Desc.setText("");
                        L3_Orig_Sku.setText("");
                        L3_Orig_Desc.setText("");
                        L3_Orig_Attr.setText("");
                        L3_Orig_Size.setText("");
                        L3_Orig_Retail.setText("");
                        L3_Manuf_Inspec.setText("");
                        L3_New_Used.setSelectedItem("New");
                        L3_Reason_DropDown.setSelectedItem("");                    
                        L3_Desc_Damage.setText("");
                        L3_Cust_Satisf_ChkBox.setSelected(false);
                        L3_Warranty_ChkBox.setSelected(false);
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
                        L3_Manuf_Inspec.setEnabled(true);
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
                        EBASdao.ClearLine4();
                        L4_First_Sku.setText("");
                        L4_Qty_Out.setText("");
                        L4_First_Desc.setText("");
                        L4_Orig_Sku.setText("");
                        L4_Orig_Desc.setText("");
                        L4_Orig_Attr.setText("");
                        L4_Orig_Size.setText("");
                        L4_Orig_Retail.setText("");
                        L4_Manuf_Inspec.setText("");
                        L4_New_Used.setSelectedItem("New");
                        L4_Reason_DropDown.setSelectedItem("");                    
                        L4_Desc_Damage.setText("");
                        L4_Cust_Satisf_ChkBox.setSelected(false);
                        L4_Warranty_ChkBox.setSelected(false);
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
                        L4_Manuf_Inspec.setEnabled(true);
                        L4_New_Used.setEnabled(true);
                        L4_Reason_DropDown.setEnabled(true);
                        L4_Desc_Damage.setEnabled(true);
                        L4_Cust_Satisf_ChkBox.setEnabled(true);
                        L4_Warranty_ChkBox.setEnabled(true);
                        L4_Done_ChkBox.setEnabled(true);
                        L4_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     }    break;
                 case "5":
                     try {
                        EBASdao.ClearLine5();
                        L5_First_Sku.setText("");
                        L5_Qty_Out.setText("");
                        L5_First_Desc.setText("");
                        L5_Orig_Sku.setText("");
                        L5_Orig_Desc.setText("");
                        L5_Orig_Attr.setText("");
                        L5_Orig_Size.setText("");
                        L5_Orig_Retail.setText("");
                        L5_Manuf_Inspec.setText("");
                        L5_New_Used.setSelectedItem("New");
                        L5_Reason_DropDown.setSelectedItem("");                    
                        L5_Desc_Damage.setText("");
                        L5_Cust_Satisf_ChkBox.setSelected(false);
                        L5_Warranty_ChkBox.setSelected(false);
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
                        L5_Manuf_Inspec.setEnabled(true);
                        L5_New_Used.setEnabled(true);
                        L5_Reason_DropDown.setEnabled(true);
                        L5_Desc_Damage.setEnabled(true);
                        L5_Cust_Satisf_ChkBox.setEnabled(true);
                        L5_Warranty_ChkBox.setEnabled(true);
                        L5_Done_ChkBox.setEnabled(true);
                        L5_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     }  break;
                 case "6":
                     try {
                        EBASdao.ClearLine6();
                        L6_First_Sku.setText("");
                        L6_Qty_Out.setText("");
                        L6_First_Desc.setText("");
                        L6_Orig_Sku.setText("");
                        L6_Orig_Desc.setText("");
                        L6_Orig_Attr.setText("");
                        L6_Orig_Size.setText("");
                        L6_Orig_Retail.setText("");
                        L6_Manuf_Inspec.setText("");
                        L6_New_Used.setSelectedItem("New");
                        L6_Reason_DropDown.setSelectedItem("");                    
                        L6_Desc_Damage.setText("");
                        L6_Cust_Satisf_ChkBox.setSelected(false);
                        L6_Warranty_ChkBox.setSelected(false);
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
                        L6_Manuf_Inspec.setEnabled(true);
                        L6_New_Used.setEnabled(true);
                        L6_Reason_DropDown.setEnabled(true);
                        L6_Desc_Damage.setEnabled(true);
                        L6_Cust_Satisf_ChkBox.setEnabled(true);
                        L6_Warranty_ChkBox.setEnabled(true);
                        L6_Done_ChkBox.setEnabled(true);
                        L6_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     }   break;
                 case "7":
                     try {
                        EBASdao.ClearLine7();
                        L7_First_Sku.setText("");
                        L7_Qty_Out.setText("");
                        L7_First_Desc.setText("");
                        L7_Orig_Sku.setText("");
                        L7_Orig_Desc.setText("");
                        L7_Orig_Attr.setText("");
                        L7_Orig_Size.setText("");
                        L7_Orig_Retail.setText("");
                        L7_Manuf_Inspec.setText("");
                        L7_New_Used.setSelectedItem("New");
                        L7_Reason_DropDown.setSelectedItem("");                    
                        L7_Desc_Damage.setText("");
                        L7_Cust_Satisf_ChkBox.setSelected(false);
                        L7_Warranty_ChkBox.setSelected(false);
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
                        L7_Manuf_Inspec.setEnabled(true);
                        L7_New_Used.setEnabled(true);
                        L7_Reason_DropDown.setEnabled(true);
                        L7_Desc_Damage.setEnabled(true);
                        L7_Cust_Satisf_ChkBox.setEnabled(true);
                        L7_Warranty_ChkBox.setEnabled(true);
                        L7_Done_ChkBox.setEnabled(true);
                        L7_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     }    break;
                 case "8":
                     try {
                        EBASdao.ClearLine8();
                        L8_First_Sku.setText("");
                        L8_Qty_Out.setText("");
                        L8_First_Desc.setText("");
                        L8_Orig_Sku.setText("");
                        L8_Orig_Desc.setText("");
                        L8_Orig_Attr.setText("");
                        L8_Orig_Size.setText("");
                        L8_Orig_Retail.setText("");
                        L8_Manuf_Inspec.setText("");
                        L8_New_Used.setSelectedItem("New");
                        L8_Reason_DropDown.setSelectedItem("");                    
                        L8_Desc_Damage.setText("");
                        L8_Cust_Satisf_ChkBox.setSelected(false);
                        L8_Warranty_ChkBox.setSelected(false);
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
                        L8_Manuf_Inspec.setEnabled(true);
                        L8_New_Used.setEnabled(true);
                        L8_Reason_DropDown.setEnabled(true);
                        L8_Desc_Damage.setEnabled(true);
                        L8_Cust_Satisf_ChkBox.setEnabled(true);
                        L8_Warranty_ChkBox.setEnabled(true);
                        L8_Done_ChkBox.setEnabled(true);
                        L8_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     }  break;
                 case "9":
                     try {
                        EBASdao.ClearLine9();
                        L9_First_Sku.setText("");
                        L9_Qty_Out.setText("");
                        L9_First_Desc.setText("");
                        L9_Orig_Sku.setText("");
                        L9_Orig_Desc.setText("");
                        L9_Orig_Attr.setText("");
                        L9_Orig_Size.setText("");
                        L9_Orig_Retail.setText("");
                        L9_Manuf_Inspec.setText("");
                        L9_New_Used.setSelectedItem("New");
                        L9_Reason_DropDown.setSelectedItem("");                    
                        L9_Desc_Damage.setText("");
                        L9_Cust_Satisf_ChkBox.setSelected(false);
                        L9_Warranty_ChkBox.setSelected(false);
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
                        L9_Manuf_Inspec.setEnabled(true);
                        L9_New_Used.setEnabled(true);
                        L9_Reason_DropDown.setEnabled(true);
                        L9_Desc_Damage.setEnabled(true);
                        L9_Cust_Satisf_ChkBox.setEnabled(true);
                        L9_Warranty_ChkBox.setEnabled(true);
                        L9_Done_ChkBox.setEnabled(true);
                        L9_Timestamp.setEnabled(true);
                        CLine_textfield.setText("");
                     } catch (SQLException ex) {
                        Logger.getLogger(NEBAS.class.getName()).log(Level.SEVERE, null, ex);
                     }   break;
                 case "10":
                     try {
                        EBASdao.ClearLine10();
                        L10_First_Sku.setText("");
                        L10_Qty_Out.setText("");
                        L10_First_Desc.setText("");
                        L10_Orig_Sku.setText("");
                        L10_Orig_Desc.setText("");
                        L10_Orig_Attr.setText("");
                        L10_Orig_Size.setText("");
                        L10_Orig_Retail.setText("");
                        L10_Manuf_Inspec.setText("");
                        L10_New_Used.setSelectedItem("New");
                        L10_Reason_DropDown.setSelectedItem("");                    
                        L10_Desc_Damage.setText("");
                        L10_Cust_Satisf_ChkBox.setSelected(false);
                        L10_Warranty_ChkBox.setSelected(false);
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
                        L10_Manuf_Inspec.setEnabled(true);
                        L10_New_Used.setEnabled(true);
                        L10_Reason_DropDown.setEnabled(true);
                        L10_Desc_Damage.setEnabled(true);
                        L10_Cust_Satisf_ChkBox.setEnabled(true);
                        L10_Warranty_ChkBox.setEnabled(true);
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

    private void L1_Manuf_InspecFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L1_Manuf_InspecFocusGained
        jPanel6.scrollRectToVisible(L1_Timestamp.getBounds(null));
    }//GEN-LAST:event_L1_Manuf_InspecFocusGained

    private void L2_Manuf_InspecFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L2_Manuf_InspecFocusGained
        jPanel6.scrollRectToVisible(L1_Timestamp.getBounds(null));
    }//GEN-LAST:event_L2_Manuf_InspecFocusGained

    private void L3_Manuf_InspecFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L3_Manuf_InspecFocusGained
        jPanel6.scrollRectToVisible(L1_Timestamp.getBounds(null));
    }//GEN-LAST:event_L3_Manuf_InspecFocusGained

    private void L4_Manuf_InspecFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L4_Manuf_InspecFocusGained
        jPanel6.scrollRectToVisible(L1_Timestamp.getBounds(null));
    }//GEN-LAST:event_L4_Manuf_InspecFocusGained

    private void L5_Manuf_InspecFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L5_Manuf_InspecFocusGained
        jPanel6.scrollRectToVisible(L1_Timestamp.getBounds(null));
    }//GEN-LAST:event_L5_Manuf_InspecFocusGained

    private void L6_Manuf_InspecFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L6_Manuf_InspecFocusGained
        jPanel6.scrollRectToVisible(L1_Timestamp.getBounds(null));
    }//GEN-LAST:event_L6_Manuf_InspecFocusGained

    private void L7_Manuf_InspecFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L7_Manuf_InspecFocusGained
        jPanel6.scrollRectToVisible(L1_Timestamp.getBounds(null));
    }//GEN-LAST:event_L7_Manuf_InspecFocusGained

    private void L8_Manuf_InspecFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L8_Manuf_InspecFocusGained
        jPanel6.scrollRectToVisible(L1_Timestamp.getBounds(null));
    }//GEN-LAST:event_L8_Manuf_InspecFocusGained

    private void L9_Manuf_InspecFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L9_Manuf_InspecFocusGained
        jPanel6.scrollRectToVisible(L1_Timestamp.getBounds(null));
    }//GEN-LAST:event_L9_Manuf_InspecFocusGained

    private void L10_Manuf_InspecFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L10_Manuf_InspecFocusGained
        jPanel6.scrollRectToVisible(L1_Timestamp.getBounds(null));
    }//GEN-LAST:event_L10_Manuf_InspecFocusGained

    private void Delete_Form_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_Form_BtnActionPerformed
        Toolkit.getDefaultToolkit().beep();
        int response = JOptionPane.showConfirmDialog(null, "Do you want to Delete Form?", "Confirm Delete",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
        try {
            EBASdao.DeleteForm();            
        } catch (SQLException ex) {
            Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(frame, "Form Has Been Deleted");
        InvAdj.Rpnt();
        this.dispose();
    }
    }//GEN-LAST:event_Delete_Form_BtnActionPerformed
   
    // this makes approve button appear only after checkbox is checked and sku is not null to prevent accidental submission 
    // of data to archive and prevent corrupted data
    public static void rChkLn1() {
        if (L1_Done_ChkBox.isSelected()== true && !L1_First_Sku.getText().equals("")) {
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
        
        if ("ic".equals(GetForms_InvAdj.usrType) && !L1_First_Sku.getText().equals("")) {
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
        if ("dl".equals(GetForms_InvAdj.usrType) && !L1_First_Sku.getText().equals("")) {
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
        if (L2_Done_ChkBox.isSelected()== true && !L2_First_Sku.getText().equals("")) {
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
        if (L2_Done_ChkBox.isSelected()== true && !L2_First_Sku.getText().equals("")) {
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
            if (L2_Done_ChkBox.isSelected()== true && !L2_First_Sku.getText().equals("")) {
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
        if (L3_Done_ChkBox.isSelected()== true && !L3_First_Sku.getText().equals("")) {
            if  ((L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected() == true) && (L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== true) 
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
        if (L3_Done_ChkBox.isSelected()== true && !L3_First_Sku.getText().equals("")) {
            if  ((L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected() == true) && (L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== true) 
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
       if("dl".equals(GetForms_InvAdj.usrType)) {
           if (L3_Done_ChkBox.isSelected()== true && !L3_First_Sku.getText().equals("")) {
            if  ((L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected() == true) && (L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== true) 
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
        if (L4_Done_ChkBox.isSelected()== true && !L4_First_Sku.getText().equals("")) {
                if  ((L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected() == true) && (L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== true) 
            && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
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
         if (L4_Done_ChkBox.isSelected()== true && !L4_First_Sku.getText().equals("")) {
                if  ((L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected() == true) && (L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== true) 
            && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
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
       if("dl".equals(GetForms_InvAdj.usrType)) {
           if (L4_Done_ChkBox.isSelected()== true && !L4_First_Sku.getText().equals("")) {
                if  ((L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected()== false || !L1_First_Sku.getText().equals("") && L1_Done_ChkBox.isSelected() == true) && (L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== false || !L2_First_Sku.getText().equals("") && L2_Done_ChkBox.isSelected()== true) 
            && (L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== false || !L3_First_Sku.getText().equals("") && L3_Done_ChkBox.isSelected()== true) && (L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== false || !L5_First_Sku.getText().equals("") && L5_Done_ChkBox.isSelected()== true)
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
        if (L5_Done_ChkBox.isSelected()== true && !L5_First_Sku.getText().equals("")) {
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
       if (L5_Done_ChkBox.isSelected()== true && !L5_First_Sku.getText().equals("")) {
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
       if("dl".equals(GetForms_InvAdj.usrType)) {
          if (L5_Done_ChkBox.isSelected()== true && !L5_First_Sku.getText().equals("")) {
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
        if (L6_Done_ChkBox.isSelected()== true && !L6_First_Sku.getText().equals("")) {
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
         if (L6_Done_ChkBox.isSelected()== true && !L6_First_Sku.getText().equals("")) {
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
       if("dl".equals(GetForms_InvAdj.usrType)) {
          if (L6_Done_ChkBox.isSelected()== true && !L6_First_Sku.getText().equals("")) {
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
        if (L7_Done_ChkBox.isSelected()== true && !L7_First_Sku.getText().equals("")) {
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
         if (L7_Done_ChkBox.isSelected()== true && !L7_First_Sku.getText().equals("")) {
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
       if("dl".equals(GetForms_InvAdj.usrType)) {
           if (L7_Done_ChkBox.isSelected()== true && !L7_First_Sku.getText().equals("")) {
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
        if (L8_Done_ChkBox.isSelected()== true && !L8_First_Sku.getText().equals("")) {
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
         if (L8_Done_ChkBox.isSelected()== true && !L8_First_Sku.getText().equals("")) {
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
       if("dl".equals(GetForms_InvAdj.usrType)) {
          if (L8_Done_ChkBox.isSelected()== true && !L8_First_Sku.getText().equals("")) {
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
        if (L9_Done_ChkBox.isSelected()== true && !L9_First_Sku.getText().equals("")) {
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
         if (L9_Done_ChkBox.isSelected()== true && !L9_First_Sku.getText().equals("")) {
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
       if("dl".equals(GetForms_InvAdj.usrType)) {
           if (L9_Done_ChkBox.isSelected()== true && !L9_First_Sku.getText().equals("")) {
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
        if (L10_Done_ChkBox.isSelected()== true && !L10_First_Sku.getText().equals("")) {
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
         if (L10_Done_ChkBox.isSelected()== true && !L10_First_Sku.getText().equals("")) {
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
       if("dl".equals(GetForms_InvAdj.usrType)) {
           if (L10_Done_ChkBox.isSelected()== true && !L10_First_Sku.getText().equals("")) {
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
            java.util.logging.Logger.getLogger(EBAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new EBAS().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton ApproveBtn;
    private javax.swing.JButton CLine_Button;
    private javax.swing.JTextField CLine_textfield;
    private javax.swing.JButton Close_Btn;
    private javax.swing.JButton DL_Print_Btn;
    public static javax.swing.JLabel Date_Label;
    private javax.swing.JButton Delete_Form_Btn;
    private javax.swing.JLabel Form_Name_Label;
    private javax.swing.JLabel Form_Name_Tag;
    public static javax.swing.JLabel L1;
    public static javax.swing.JLabel L10;
    public static javax.swing.JCheckBox L10_Cust_Satisf_ChkBox;
    public static javax.swing.JTextField L10_Desc_Damage;
    public static javax.swing.JCheckBox L10_Done_ChkBox;
    public static javax.swing.JTextField L10_First_Desc;
    public static javax.swing.JTextField L10_First_Sku;
    public static javax.swing.JTextField L10_Manuf_Inspec;
    public static javax.swing.JComboBox L10_New_Used;
    public static javax.swing.JTextField L10_Orig_Attr;
    public static javax.swing.JTextField L10_Orig_Desc;
    public static javax.swing.JTextField L10_Orig_Retail;
    public static javax.swing.JTextField L10_Orig_Size;
    public static javax.swing.JTextField L10_Orig_Sku;
    public static javax.swing.JTextField L10_Qty_Out;
    public static javax.swing.JComboBox L10_Reason_DropDown;
    public static javax.swing.JTextField L10_Timestamp;
    public static javax.swing.JCheckBox L10_Warranty_ChkBox;
    public static javax.swing.JCheckBox L1_Cust_Satisf_ChkBox;
    public static javax.swing.JTextField L1_Desc_Damage;
    public static javax.swing.JCheckBox L1_Done_ChkBox;
    public static javax.swing.JTextField L1_First_Desc;
    public static javax.swing.JTextField L1_First_Sku;
    public static javax.swing.JTextField L1_Manuf_Inspec;
    public static javax.swing.JComboBox L1_New_Used;
    public static javax.swing.JTextField L1_Orig_Attr;
    public static javax.swing.JTextField L1_Orig_Desc;
    public static javax.swing.JTextField L1_Orig_Retail;
    public static javax.swing.JTextField L1_Orig_Size;
    public static javax.swing.JTextField L1_Orig_Sku;
    public static javax.swing.JTextField L1_Qty_Out;
    public static javax.swing.JComboBox L1_Reason_DropDown;
    public static javax.swing.JTextField L1_Timestamp;
    public static javax.swing.JCheckBox L1_Warranty_ChkBox;
    public static javax.swing.JLabel L2;
    public static javax.swing.JCheckBox L2_Cust_Satisf_ChkBox;
    public static javax.swing.JTextField L2_Desc_Damage;
    public static javax.swing.JCheckBox L2_Done_ChkBox;
    public static javax.swing.JTextField L2_First_Desc;
    public static javax.swing.JTextField L2_First_Sku;
    public static javax.swing.JTextField L2_Manuf_Inspec;
    public static javax.swing.JComboBox L2_New_Used;
    public static javax.swing.JTextField L2_Orig_Attr;
    public static javax.swing.JTextField L2_Orig_Desc;
    public static javax.swing.JTextField L2_Orig_Retail;
    public static javax.swing.JTextField L2_Orig_Size;
    public static javax.swing.JTextField L2_Orig_Sku;
    public static javax.swing.JTextField L2_Qty_Out;
    public static javax.swing.JComboBox L2_Reason_DropDown;
    public static javax.swing.JTextField L2_Timestamp;
    public static javax.swing.JCheckBox L2_Warranty_ChkBox;
    public static javax.swing.JLabel L3;
    public static javax.swing.JCheckBox L3_Cust_Satisf_ChkBox;
    public static javax.swing.JTextField L3_Desc_Damage;
    public static javax.swing.JCheckBox L3_Done_ChkBox;
    public static javax.swing.JTextField L3_First_Desc;
    public static javax.swing.JTextField L3_First_Sku;
    public static javax.swing.JTextField L3_Manuf_Inspec;
    public static javax.swing.JComboBox L3_New_Used;
    public static javax.swing.JTextField L3_Orig_Attr;
    public static javax.swing.JTextField L3_Orig_Desc;
    public static javax.swing.JTextField L3_Orig_Retail;
    public static javax.swing.JTextField L3_Orig_Size;
    public static javax.swing.JTextField L3_Orig_Sku;
    public static javax.swing.JTextField L3_Qty_Out;
    public static javax.swing.JComboBox L3_Reason_DropDown;
    public static javax.swing.JTextField L3_Timestamp;
    public static javax.swing.JCheckBox L3_Warranty_ChkBox;
    public static javax.swing.JLabel L4;
    public static javax.swing.JCheckBox L4_Cust_Satisf_ChkBox;
    public static javax.swing.JTextField L4_Desc_Damage;
    public static javax.swing.JCheckBox L4_Done_ChkBox;
    public static javax.swing.JTextField L4_First_Desc;
    public static javax.swing.JTextField L4_First_Sku;
    public static javax.swing.JTextField L4_Manuf_Inspec;
    public static javax.swing.JComboBox L4_New_Used;
    public static javax.swing.JTextField L4_Orig_Attr;
    public static javax.swing.JTextField L4_Orig_Desc;
    public static javax.swing.JTextField L4_Orig_Retail;
    public static javax.swing.JTextField L4_Orig_Size;
    public static javax.swing.JTextField L4_Orig_Sku;
    public static javax.swing.JTextField L4_Qty_Out;
    public static javax.swing.JComboBox L4_Reason_DropDown;
    public static javax.swing.JTextField L4_Timestamp;
    public static javax.swing.JCheckBox L4_Warranty_ChkBox;
    public static javax.swing.JLabel L5;
    public static javax.swing.JCheckBox L5_Cust_Satisf_ChkBox;
    public static javax.swing.JTextField L5_Desc_Damage;
    public static javax.swing.JCheckBox L5_Done_ChkBox;
    public static javax.swing.JTextField L5_First_Desc;
    public static javax.swing.JTextField L5_First_Sku;
    public static javax.swing.JTextField L5_Manuf_Inspec;
    public static javax.swing.JComboBox L5_New_Used;
    public static javax.swing.JTextField L5_Orig_Attr;
    public static javax.swing.JTextField L5_Orig_Desc;
    public static javax.swing.JTextField L5_Orig_Retail;
    public static javax.swing.JTextField L5_Orig_Size;
    public static javax.swing.JTextField L5_Orig_Sku;
    public static javax.swing.JTextField L5_Qty_Out;
    public static javax.swing.JComboBox L5_Reason_DropDown;
    public static javax.swing.JTextField L5_Timestamp;
    public static javax.swing.JCheckBox L5_Warranty_ChkBox;
    public static javax.swing.JLabel L6;
    public static javax.swing.JCheckBox L6_Cust_Satisf_ChkBox;
    public static javax.swing.JTextField L6_Desc_Damage;
    public static javax.swing.JCheckBox L6_Done_ChkBox;
    public static javax.swing.JTextField L6_First_Desc;
    public static javax.swing.JTextField L6_First_Sku;
    public static javax.swing.JTextField L6_Manuf_Inspec;
    public static javax.swing.JComboBox L6_New_Used;
    public static javax.swing.JTextField L6_Orig_Attr;
    public static javax.swing.JTextField L6_Orig_Desc;
    public static javax.swing.JTextField L6_Orig_Retail;
    public static javax.swing.JTextField L6_Orig_Size;
    public static javax.swing.JTextField L6_Orig_Sku;
    public static javax.swing.JTextField L6_Qty_Out;
    public static javax.swing.JComboBox L6_Reason_DropDown;
    public static javax.swing.JTextField L6_Timestamp;
    public static javax.swing.JCheckBox L6_Warranty_ChkBox;
    public static javax.swing.JLabel L7;
    public static javax.swing.JCheckBox L7_Cust_Satisf_ChkBox;
    public static javax.swing.JTextField L7_Desc_Damage;
    public static javax.swing.JCheckBox L7_Done_ChkBox;
    public static javax.swing.JTextField L7_First_Desc;
    public static javax.swing.JTextField L7_First_Sku;
    public static javax.swing.JTextField L7_Manuf_Inspec;
    public static javax.swing.JComboBox L7_New_Used;
    public static javax.swing.JTextField L7_Orig_Attr;
    public static javax.swing.JTextField L7_Orig_Desc;
    public static javax.swing.JTextField L7_Orig_Retail;
    public static javax.swing.JTextField L7_Orig_Size;
    public static javax.swing.JTextField L7_Orig_Sku;
    public static javax.swing.JTextField L7_Qty_Out;
    public static javax.swing.JComboBox L7_Reason_DropDown;
    public static javax.swing.JTextField L7_Timestamp;
    public static javax.swing.JCheckBox L7_Warranty_ChkBox;
    public static javax.swing.JLabel L8;
    public static javax.swing.JCheckBox L8_Cust_Satisf_ChkBox;
    public static javax.swing.JTextField L8_Desc_Damage;
    public static javax.swing.JCheckBox L8_Done_ChkBox;
    public static javax.swing.JTextField L8_First_Desc;
    public static javax.swing.JTextField L8_First_Sku;
    public static javax.swing.JTextField L8_Manuf_Inspec;
    public static javax.swing.JComboBox L8_New_Used;
    public static javax.swing.JTextField L8_Orig_Attr;
    public static javax.swing.JTextField L8_Orig_Desc;
    public static javax.swing.JTextField L8_Orig_Retail;
    public static javax.swing.JTextField L8_Orig_Size;
    public static javax.swing.JTextField L8_Orig_Sku;
    public static javax.swing.JTextField L8_Qty_Out;
    public static javax.swing.JComboBox L8_Reason_DropDown;
    public static javax.swing.JTextField L8_Timestamp;
    public static javax.swing.JCheckBox L8_Warranty_ChkBox;
    public static javax.swing.JLabel L9;
    public static javax.swing.JCheckBox L9_Cust_Satisf_ChkBox;
    public static javax.swing.JTextField L9_Desc_Damage;
    public static javax.swing.JCheckBox L9_Done_ChkBox;
    public static javax.swing.JTextField L9_First_Desc;
    public static javax.swing.JTextField L9_First_Sku;
    public static javax.swing.JTextField L9_Manuf_Inspec;
    public static javax.swing.JComboBox L9_New_Used;
    public static javax.swing.JTextField L9_Orig_Attr;
    public static javax.swing.JTextField L9_Orig_Desc;
    public static javax.swing.JTextField L9_Orig_Retail;
    public static javax.swing.JTextField L9_Orig_Size;
    public static javax.swing.JTextField L9_Orig_Sku;
    public static javax.swing.JTextField L9_Qty_Out;
    public static javax.swing.JComboBox L9_Reason_DropDown;
    public static javax.swing.JTextField L9_Timestamp;
    public static javax.swing.JCheckBox L9_Warranty_ChkBox;
    public static javax.swing.JButton RdyforExportBtn;
    private javax.swing.JLabel Store_Letter_Label;
    private javax.swing.JLabel Store_Location_Label;
    private javax.swing.JLabel Store_Number_Label;
    public static javax.swing.JLabel Store_logged_in_label;
    private javax.swing.JLabel Test_Label;
    private javax.swing.JLabel VersionLabel;
    public static javax.swing.JLabel dtStLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel_1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
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

    /*  not currently in use because a better solution was created
    //  this function was not sufficient for the functionality of the program 
    public static void RProExp() throws ClassNotFoundException, SQLException, IOException {
        try (Statement s1 = DBConnect.connection.createStatement()) {
            String selTable01 = "SELECT Sku As s, Qty As q, New_Used As nwUsd, Description1 As d1, Reason As r, "
                    + "Desc_Damage As dmg, Orig_SKU As orgS, Manuf_Inspection As mnInsp, Cust_Satisf As cs, Warranty As w "
                    + "FROM " + frmNm + " WHERE Sku IS NOT NULL";
            s1.execute(selTable01);
            try (ResultSet rs01 = s1.getResultSet()) {
                fWriter = new FileWriter("exports/" + frmNm.replace(":", "_") + ".csv", false);
                writer = new BufferedWriter(fWriter);
                String header = "store_code,item_number,qty,new_used,description,adj_reason,damage,original_sku,man_Insp,cust_satf,warranty";
                writer.write(header);
                writer.newLine();
                while (rs01.next()) {
                    String sku = rs01.getString("s");
                    String qty = rs01.getString("q");
                    String nwused = rs01.getString("nwUsd");
                    String desc1 = rs01.getString("d1");
                    String reas = rs01.getString("r");
                    String descdmg = rs01.getString("dmg");
                    String orgS = rs01.getString("orgS");
                    String mnIns = rs01.getString("mnInsp");
                    String cstS = rs01.getString("cs");
                    String wrr = rs01.getString("w");
                    //System.out.println(frmNm.split("_")[1] + qty + "," + sku + "," + desc1 + "," + reas + "," + descdmg + "," + orgR + "," + nwsku + "," + desc2 + "," + qtyI);
                    String line = frmNm.split("_")[1] + "," + sku + "," + qty + "," + nwused + "," + desc1 + "," + reas + "," + descdmg + "," + orgS + "," + mnIns + "," + cstS + "," + wrr;
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
        writer.close();
        GtForm();
        EBASdao.drTable();
    }

    //  not currently in use because a better solution was created
    //  this function was not sufficient for the functionality of the program
    public static void UsrExport() throws SQLException, IOException {
           try (Statement s1 = DBConnect.connection.createStatement()) {
            String selTable01 = "SELECT Sku As s, Qty As q, Description1 As d1, Orig_SKU As orgSk, Description2 As d2,Attribute As atr, "
                    + "Size As sze, Orig_Retail As orgR, Manuf_Inspection As manfInspc, New_Used As nwUsd, Reason As r, Desc_Damage As dmg, "
                    + "Cust_Satisf As cstSatsf, Warranty As wrrnty FROM " + InvAdj_Admin.frmNm + " WHERE Sku IS NOT NULL";
            s1.execute(selTable01);
            try (ResultSet rs01 = s1.getResultSet()) {
                fWriter = new FileWriter("Stores/" + frmNm.split("_")[1] + "/" + frmNm.replace(":", "_") + ".csv", false);
                writer = new BufferedWriter(fWriter);
                String header = "store_code,item_number,quantity,description,orig_sku,description2,attribute,size,original_retail,manf_inspec,new_used,adj_reason,damage,cust_satisf,warranty";
                writer.write(header);
                writer.newLine();
                while (rs01.next()) {
                    String sku = rs01.getString("s");
                    String qty = rs01.getString("q");
                    String desc1 = rs01.getString("d1");
                    String orgSku = rs01.getString("orgSk");
                    String desc2 = rs01.getString("d2");
                    String attr = rs01.getString("atr");
                    String size = rs01.getString("sze");
                    String orgR = rs01.getString("orgR");
                    String manufInspec = rs01.getString("manfInspc");
                    String newUsed = rs01.getString("nwUsd");
                    String reas = rs01.getString("r");
                    String descdmg = rs01.getString("dmg");
                    String custSatisf = rs01.getString("cstSatsf");
                    String warranty = rs01.getString("wrrnty");
                    //System.out.println(frmNm.split("_")[1] + qty + "," + sku + ","+ desc1 + "," + reas + "," + descdmg + "," + orgR + "," + nwsku + "," + desc2 + "," + qtyI);
                    String line = frmNm.split("_")[1] + "," + sku + "," + qty + "," + desc1 + "," + orgSku + "," + desc2 + "," + attr + "," + size + "," + orgR + "," + manufInspec + "," + newUsed + "," + reas + "," + descdmg + "," + custSatisf + "," + warranty;
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
        writer.close();
    } 

    public static void GtForm() throws IOException {
        Desktop.getDesktop().open(new File("exports/" + frmNm.replace(":", "_") + ".csv"));
    } */

    // gets strings from database for damage reason drop down selection in form
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

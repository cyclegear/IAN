/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form_port_app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author SSwisher & WStevens
 */
public class GetForms_InvAdj {

    public static int z = 0;
    public static int cnt = 0;
    public static String dbPath = "db/InvAdj.mdb";
    public static String rline;
    public static String exFrm = null;
    public static String frmNm_inproc = null;
    public static String frmNm_pend = null;
    public static String frmNm_appr = null;
    public static String frmNm_ready = null;
    public static String frmNm_comp = null;
    public static String dlNm = null;
    public static String dlStrs = null;
    public static String icNm = null;
    public static String usrType = null;

    // Check For Existing Form
    public static void ChkForm() throws ClassNotFoundException, SQLException {
        GtStore.GtStore();
        Statement s = DBConnect.connection.createStatement();
            String selTable3 = "SELECT Form_Name As frm FROM existing_forms WHERE Store = '" + GtStore.store.toLowerCase() + "'" + " AND Status = 'In-Process'";
            s.execute(selTable3);
        try (ResultSet rs3 = s.getResultSet()) {
            while (rs3.next()) {
                InvAdj.listModel2.addElement(rs3.getString("frm"));
            }
        }
    }

    // Get All existing forms for admin panel
    public static void ChkAllForms() throws ClassNotFoundException, SQLException {
        GtStore.GtStore();
       
        Statement s = DBConnect.connection.createStatement();
        
        
        // assigns drop down selection to user type for admin panel access
        usrType = InvAdj.UserTypeBox.getSelectedItem().toString().toLowerCase();
        
        /* not being used because drop down user type is a better solution
         // Check if DL user
         String selTable2 = "SELECT DL As nm, Stores As strs FROM DL WHERE DL = '" + GtStore.store.toLowerCase() + "'";
         s.execute(selTable2);
         ResultSet rs2 = s.getResultSet();
         if (rs2.next()) {
         dlNm = rs2.getString("nm");
         dlStrs = rs2.getString("strs");
         usrType = "dl";
         gtDL();
         rs2.close();
         }

         // Check if IC user
         String selTable22 = "SELECT IC As nm FROM IC WHERE IC = '" + GtStore.store.toLowerCase() + "'";
         s.execute(selTable22);
         ResultSet rs22 = s.getResultSet();
         if (rs22.next()) {
         icNm = rs22.getString("nm");
         usrType = "ic";
         rs22.close();
         }
                */         
         
        // gets all forms from all stores for IC to view in admin panel
        if (usrType.equals("ic")) {
            // Get In-Process
            String selTable32 = "SELECT Form_Name As frm, Store As str FROM existing_forms WHERE Status = 'In-Process'";
            s.execute(selTable32);
            ResultSet rs32 = s.getResultSet();
                while (rs32.next()) {
                    frmNm_inproc = rs32.getString("frm");
                    InvAdj_Admin.listinprocess.addElement(frmNm_inproc);
            }

            // Get Pending
            String selTable3 = "SELECT Form_Name As frm, Store As str FROM existing_forms WHERE Status = 'Pending'";
            s.execute(selTable3);
            try (ResultSet rs3 = s.getResultSet()) {
                while (rs3.next()) {
                    frmNm_pend = rs3.getString("frm");
                    InvAdj_Admin.listpending.addElement(frmNm_pend);
                }
            }

            // Get Approved
            String selTable4 = "SELECT Form_Name As frm2, Store As str FROM existing_forms WHERE Status = 'Approved'";
            s.execute(selTable4);
            try (ResultSet rs4 = s.getResultSet()) {
                while (rs4.next()) {
                    frmNm_appr = rs4.getString("frm2");
                    InvAdj_Admin.listapproved.addElement(frmNm_appr);
                }
            }

            // Get Ready for Export
            String selTable5 = "SELECT Form_Name As frm2, Store As str FROM completed_forms WHERE Status = 'Ready_for_Export'";
            s.execute(selTable5);
            try (ResultSet rs5 = s.getResultSet()) {
                while (rs5.next()) {
                    frmNm_ready = rs5.getString("frm2");
                    InvAdj_Admin.listreadyforexport.addElement(frmNm_ready);
                }
            }
            
            // Removed Completed forms from "ready for export" list
            String selTable6 = "SELECT Form_Name As frm2, Store As str FROM completed_forms WHERE Status = 'Completed'";
            s.execute(selTable6);
            try (ResultSet rs6 = s.getResultSet()) {
                while (rs6.next()) {
                    frmNm_ready = rs6.getString("frm2");
                    InvAdj_Admin.listreadyforexport.removeElement(frmNm_ready);
                }
            }
            s.close();
        } else {
            gtDL();
        }
    }

    // Get only Store Forms that Store and DL can view in the admin panel
    public static void gtDL() throws ClassNotFoundException, SQLException {
        GtStore.GtStore();
        
        Statement s = DBConnect.connection.createStatement();
            String selTable32 = "SELECT Form_Name As frm, Store As str FROM existing_forms WHERE Status = 'In-Process' AND Store = '" + GtStore.store + "'";
            s.execute(selTable32);
        try (ResultSet rs32 = s.getResultSet()) {
            while (rs32.next()) {
                frmNm_inproc = rs32.getString("frm");
                InvAdj_Admin.listinprocess.addElement(frmNm_inproc);
            }
        }
    
            // Get Pending
            String selTable3 = "SELECT Form_Name As frm, Store As str FROM existing_forms WHERE Status = 'Pending' AND Store = '" + GtStore.store + "'";
            s.execute(selTable3);
        try (ResultSet rs3 = s.getResultSet()) {
            while (rs3.next()) {
                frmNm_pend = rs3.getString("frm");
              // if (dlStrs.contains(frmNm_pend.split("_")[1])) {
                    InvAdj_Admin.listpending.addElement(frmNm_pend);
               // }
            }
        }
            
            // Get Approved
            String selTable4 = "SELECT Form_Name As frm2, Store As str FROM existing_forms WHERE Status = 'Approved' AND Store = '" + GtStore.store + "'";
            s.execute(selTable4);
        try (ResultSet rs4 = s.getResultSet()) {
            while (rs4.next()) {
                frmNm_appr = rs4.getString("frm2");
              //  if (dlStrs.contains(frmNm_appr.split("_")[1])) {
                    InvAdj_Admin.listapproved.addElement(frmNm_appr);
              //  }
            }
        }
            
                // Get Ready for export
            String selTable5 = "SELECT Form_Name As frm2, Store As str FROM completed_forms WHERE Status = 'Ready_for_Export' AND Store = '" + GtStore.store + "'";
            s.execute(selTable5);
        try (ResultSet rs5 = s.getResultSet()) {
            while (rs5.next()) {
                frmNm_ready = rs5.getString("frm2");
                InvAdj_Admin.listreadyforexport.addElement(frmNm_ready);
            }
        }
        }
    
}

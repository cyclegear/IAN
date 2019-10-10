/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form_port_app;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SSwisher & WStevens
 */

// this class creates all the necessary components for the main user interface
// this class opens the database connection
public class GetForms extends javax.swing.JFrame {

    public static String AdminFormPath = "Admin/forms/";
    public static String StoreFormPath = "Stores/";
    public static String FormPath = "forms/";
    public static String formN;
    public static String imgdir;
    public static int noFrm = 0;
    public static int Fcount;
    public static int a;
    public static int fSwitch = 0;
    public static int cntRes;
    public static String[] forms = new String[100];

    private static void GetForms(String fname) {

        MainGUI.listModel.addElement("Inventory Adjustment System");

        File dir = new File(fname);
        String[] chld = dir.list();

        /*
         if (chld.length < 1 && noFrm < 1) {
         MainGUI.listModel.addElement("No Existing Forms For " + GtStore.store);
         }
         */
        for (a = 0; a < chld.length; a++) {
            String extStr = chld[a].substring(chld[a].length() - 3);

            Fcount = a;
            String fileName = chld[a];
            forms[a] = fileName;
            MainGUI.listModel.addElement(forms[a]);
        }

        if (fSwitch > 0) {
            MainGUI.listModel.addElement("GO BACK");
        }
    }
// main method calls for store number and main gui
    public static void main(String[] args) {
        try {
            DBConnect.opConn();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(GetForms.class.getName()).log(Level.SEVERE, null, ex);
        }
        GtStore.GtStore();
        MainGUI.main(args);
        GetForms("forms");
    }

    // cannot find usage within program 
    public static void mainSt(String[] args) throws ClassNotFoundException, SQLException {
        GetForms("Stores/" + GtStore.store + "/forms");
        // Check for existing Inventory Adjustment Form
        try (Statement s = DBConnect.connection.createStatement()) {
            String selTable3 = "SELECT Store As str, Form_Name As frm, Date_Created As dte, Status AS st FROM existing_forms WHERE Store = '" + GtStore.store.toLowerCase() + "'" + "AND Status = 'In-Process'";
            s.execute(selTable3);
            try (ResultSet rs3 = s.getResultSet()) {
                if (!rs3.next()) {
                    MainGUI.listModel.removeElement("Inventory Adjustment System");
                    MainGUI.listModel.addElement("No Existing Forms For " + GtStore.store);
                }
            }
        }
    }

    public static void mainAd(String[] args) {
        GetForms("Admin/forms");
    }

    public static void mainGbck(String[] args) {
        GetForms("forms");
    }

    public static void GtForm() {
        try {
            String[] cmdarray = new String[]{"cmd.exe", "/c", FormPath + formN};
            Runtime.getRuntime().exec(cmdarray);
        } catch (IOException e) {
        }
    }

    public static void GtStoreForm() {
        try {
            String[] cmdarray = new String[]{"cmd.exe", "/c", StoreFormPath + GtStore.store + formN};
            Runtime.getRuntime().exec(cmdarray);
        } catch (IOException e) {
        }
    }

    public static void GtAdminForm() {
        try {
            String[] cmdarray = new String[]{"cmd.exe", "/c", AdminFormPath + formN};
            Runtime.getRuntime().exec(cmdarray);
        } catch (IOException e) {
        }
    }

    public static void FrmExt() {
        try {
            DBConnect.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetForms.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Exiting!");
        System.exit(0);
    }

    public static void GtResCnt() throws SQLException {
        try (Statement s1 = DBConnect.connection.createStatement()) {
            String selTable02 = "SELECT COUNT(*) As tot FROM adj_reasons";
            s1.execute(selTable02);
            try (ResultSet rs1 = s1.getResultSet()) {
                rs1.next();
                cntRes = rs1.getInt("tot");
            }
        }
    }
}

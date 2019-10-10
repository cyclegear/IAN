/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form_port_app;

/**
 *
 * @author SSwisher & WStevens
 */
// gets store username from local computer system
public class GtStore extends GetForms {

    public static String store;
    public static String storeDir;
    public static String admin;
    public static String adminDir;
    public static String dl;
    public static String dlDir;

    public static void GtStore() {
        // gets store name with windows login username 
        store = System.getProperty("user.name").toLowerCase();
        // this code is for reference
        storeDir = "Stores/" + store + "/";
        admin = "sswisher" + "jlopez" +  "seswisher" + "seven_user" + "wstevens";
        adminDir = "Admin/";
        dl = "galexander";
        dlDir = "DL/";
    }
    
}


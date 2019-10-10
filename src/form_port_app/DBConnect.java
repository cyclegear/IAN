/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form_port_app;
import java.awt.Component;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**0
 *
 * @author SSwisher & Wstevens
 */

// this class is establishes the connection to the database referenced by a majority of classes within the applicaiton
public class DBConnect {
    
    public static Component frame;
    // These are the necessary database connection credentials to access the invadj database in oakland
     /*
        public static String url = "jdbc:mysql://10.1.110.11:3306/invadj"; // ** Prod DB *** 
        public static String username = "ServiceInvAdjApp"; // restricted to only invadj database schema access
        public static String password = "zg3DeATp8m";
        public static boolean TestEnviron = (false);
      */
        /*
        public static String url = "jdbc:mysql://10.1.110.11:3306/invadj_test"; // ** Prod DB test schema  **
        public static String username = "ServiceInvAdjApp";  // admin user for invadj_test database access
        public static String password = "zg3DeATp8m";
        public static boolean TestEnviron = (true);
       */
    // /*
        public static String url = "jdbc:mysql://192.168.200.11:3306/invadj"; // ** test DB **.
        public static String username = "wstevens";  // admin user for invadj_test database access
        public static String password = "4vfr$VFR";
        public static boolean TestEnviron = (true);
    // */
        
        /*`
        public static String url = "jdbc:mysql://localhost:3306/invadj_test"; // ** Local DB *** 
        public static String username = "root"; // restricted to only invadj database schema access
        public static String password = "toor";
        public static boolean TestEnviron = (true);
        */
        
        public static Connection connection = null;
        public static String Version = "3.1";

   
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
        Conn();
        
        } catch (SQLException ex){
            Logger.getLogger(ViewInProcess.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(frame, "Lost connection to Database, Please wait for Reconnection");
    }
    }

    // ************* not being used currently ************** //
    public static void Conn() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);
       // this does not seem necessary for the db connection 
       /*  try (Statement stmt = connection.createStatement()) {
        *  stmt.execute("SELECT DL AS dL FROM dl WHERE ID = 2");
        *  ResultSet rs = stmt.getResultSet();
        *   rs.next();
        *  System.out.println(rs.getString("dL"));
        }  */
        } catch (SQLException ex){
            Logger.getLogger(ViewInProcess.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(frame, "Lost connection to Database, Please wait for Reconnection");
    }
        connection.close();
        System.exit(0);
    }
    // **************************** //
    
    // this is the DB connection that is being called in GetForms.java 
    public static void opConn() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
    try {   
        Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);
    } catch (SQLException ex){
            Logger.getLogger(ViewInProcess.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(frame, "Lost connection to Database, Please wait for Reconnection");
 //        connection.close();
       System.exit(0);
    }
      //  connection.close();
    }
}

    
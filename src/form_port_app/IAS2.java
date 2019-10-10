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
import java.io.File;
import java.io.IOException;
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

/* 

*  this class is the Inventory Adjustment Sheet
*  this class creates the user interface for this sheet
*  the class contains two sku fields, the first one for taking inventory out of stock and the second for
*  entering the correct sku and putting it in inventory
*  Some of the line validations and events have mixed up order becaus this was the first form created from early development
*  and was not efficient use of time to change this order because it does not affect fucntionality
*
*/
@SuppressWarnings("serial")
public class IAS2 extends javax.swing.JFrame {

    /**
     * @return the L1_Cost
     */
    public static String getL1_Cost() {
        return L1_Cost;
    }

    /**
     * @param aL1_Cost the L1_Cost to set
     */
    public static void setL1_Cost(String aL1_Cost) {
        L1_Cost = aL1_Cost;
    }

    /**
     * @return the L2_Cost
     */
    public static String getL2_Cost() {
        return L2_Cost;
    }

    /**
     * @param aL2_Cost the L2_Cost to set
     */
    public static void setL2_Cost(String aL2_Cost) {
        L2_Cost = aL2_Cost;
    }

    /**
     * @return the L3_Cost
     */
    public static String getL3_Cost() {
        return L3_Cost;
    }

    /**
     * @param aL3_Cost the L3_Cost to set
     */
    public static void setL3_Cost(String aL3_Cost) {
        L3_Cost = aL3_Cost;
    }

    /**
     * @return the L4_Cost
     */
    public static String getL4_Cost() {
        return L4_Cost;
    }

    /**
     * @param aL4_Cost the L4_Cost to set
     */
    public static void setL4_Cost(String aL4_Cost) {
        L4_Cost = aL4_Cost;
    }

    /**
     * @return the L5_Cost
     */
    public static String getL5_Cost() {
        return L5_Cost;
    }

    /**
     * @param aL5_Cost the L5_Cost to set
     */
    public static void setL5_Cost(String aL5_Cost) {
        L5_Cost = aL5_Cost;
    }

    /**
     * @return the L6_Cost
     */
    public static String getL6_Cost() {
        return L6_Cost;
    }

    /**
     * @param aL6_Cost the L6_Cost to set
     */
    public static void setL6_Cost(String aL6_Cost) {
        L6_Cost = aL6_Cost;
    }

    /**
     * @return the L7_Cost
     */
    public static String getL7_Cost() {
        return L7_Cost;
    }

    /**
     * @param aL7_Cost the L7_Cost to set
     */
    public static void setL7_Cost(String aL7_Cost) {
        L7_Cost = aL7_Cost;
    }

    /**
     * @return the L8_Cost
     */
    public static String getL8_Cost() {
        return L8_Cost;
    }

    /**
     * @param aL8_Cost the L8_Cost to set
     */
    public static void setL8_Cost(String aL8_Cost) {
        L8_Cost = aL8_Cost;
    }

    /**
     * @return the L9_Cost
     */
    public static String getL9_Cost() {
        return L9_Cost;
    }

    /**
     * @param aL9_Cost the L9_Cost to set
     */
    public static void setL9_Cost(String aL9_Cost) {
        L9_Cost = aL9_Cost;
    }

    /**
     * @return the L10_Cost
     */
    public static String getL10_Cost() {
        return L10_Cost;
    }

    /**
     * @param aL10_Cost the L10_Cost to set
     */
    public static void setL10_Cost(String aL10_Cost) {
        L10_Cost = aL10_Cost;
    }

    /**
     * @return the fDcs
     */
    public static String getfDcs1() {
        return fDcs1;
    }

    /**
     * @param afDcs1 the fDcs to set
     */
    public static void setfDcs1(String afDcs1) {
        fDcs1 = afDcs1;
    }

    /**
     * @return the fVpn
     */
    public static String getfVpn1() {
        return fVpn1;
    }

    /**
     * @param afVpn1 the fVpn to set
     */
    public static void setfVpn1(String afVpn1) {
        fVpn1 = afVpn1;
    }

    /**
     * @return the fVen
     */
    public static String getfVen1() {
        return fVen1;
    }

    /**
     * @param afVen1 the fVen to set
     */
    public static void setfVen1(String afVen1) {
        fVen1 = afVen1;
    }

    /**
     * @return the fDcs2
     */
    public static String getfDcs2() {
        return fDcs2;
    }

    /**
     * @param afDcs2 the fDcs2 to set
     */
    public static void setfDcs2(String afDcs2) {
        fDcs2 = afDcs2;
    }

    /**
     * @return the fVpn2
     */
    public static String getfVpn2() {
        return fVpn2;
    }

    /**
     * @param afVpn2 the fVpn2 to set
     */
    public static void setfVpn2(String afVpn2) {
        fVpn2 = afVpn2;
    }

    /**
     * @return the fVen2
     */
    public static String getfVen2() {
        return fVen2;
    }

    /**
     * @param afVen2 the fVen2 to set
     */
    public static void setfVen2(String afVen2) {
        fVen2 = afVen2;
    }

    /**
     * @return the fDcs3
     */
    public static String getfDcs3() {
        return fDcs3;
    }

    /**
     * @param afDcs3 the fDcs3 to set
     */
    public static void setfDcs3(String afDcs3) {
        fDcs3 = afDcs3;
    }

    /**
     * @return the fVpn3
     */
    public static String getfVpn3() {
        return fVpn3;
    }

    /**
     * @param afVpn3 the fVpn3 to set
     */
    public static void setfVpn3(String afVpn3) {
        fVpn3 = afVpn3;
    }

    /**
     * @return the fVen3
     */
    public static String getfVen3() {
        return fVen3;
    }

    /**
     * @param afVen3 the fVen3 to set
     */
    public static void setfVen3(String afVen3) {
        fVen3 = afVen3;
    }

    /**
     * @return the fDcs4
     */
    public static String getfDcs4() {
        return fDcs4;
    }

    /**
     * @param afDcs4 the fDcs4 to set
     */
    public static void setfDcs4(String afDcs4) {
        fDcs4 = afDcs4;
    }

    /**
     * @return the fVpn4
     */
    public static String getfVpn4() {
        return fVpn4;
    }

    /**
     * @param afVpn4 the fVpn4 to set
     */
    public static void setfVpn4(String afVpn4) {
        fVpn4 = afVpn4;
    }

    /**
     * @return the fVen4
     */
    public static String getfVen4() {
        return fVen4;
    }

    /**
     * @param afVen4 the fVen4 to set
     */
    public static void setfVen4(String afVen4) {
        fVen4 = afVen4;
    }

    /**
     * @return the fDcs5
     */
    public static String getfDcs5() {
        return fDcs5;
    }

    /**
     * @param afDcs5 the fDcs5 to set
     */
    public static void setfDcs5(String afDcs5) {
        fDcs5 = afDcs5;
    }

    /**
     * @return the fVpn5
     */
    public static String getfVpn5() {
        return fVpn5;
    }

    /**
     * @param afVpn5 the fVpn5 to set
     */
    public static void setfVpn5(String afVpn5) {
        fVpn5 = afVpn5;
    }

    /**
     * @return the fVen5
     */
    public static String getfVen5() {
        return fVen5;
    }

    /**
     * @param afVen5 the fVen5 to set
     */
    public static void setfVen5(String afVen5) {
        fVen5 = afVen5;
    }

    /**
     * @return the fDcs6
     */
    public static String getfDcs6() {
        return fDcs6;
    }

    /**
     * @param afDcs6 the fDcs6 to set
     */
    public static void setfDcs6(String afDcs6) {
        fDcs6 = afDcs6;
    }

    /**
     * @return the fVpn6
     */
    public static String getfVpn6() {
        return fVpn6;
    }

    /**
     * @param afVpn6 the fVpn6 to set
     */
    public static void setfVpn6(String afVpn6) {
        fVpn6 = afVpn6;
    }

    /**
     * @return the fVen6
     */
    public static String getfVen6() {
        return fVen6;
    }

    /**
     * @param afVen6 the fVen6 to set
     */
    public static void setfVen6(String afVen6) {
        fVen6 = afVen6;
    }

    /**
     * @return the fDcs7
     */
    public static String getfDcs7() {
        return fDcs7;
    }

    /**
     * @param afDcs7 the fDcs7 to set
     */
    public static void setfDcs7(String afDcs7) {
        fDcs7 = afDcs7;
    }

    /**
     * @return the fVpn7
     */
    public static String getfVpn7() {
        return fVpn7;
    }

    /**
     * @param afVpn7 the fVpn7 to set
     */
    public static void setfVpn7(String afVpn7) {
        fVpn7 = afVpn7;
    }

    /**
     * @return the fVen7
     */
    public static String getfVen7() {
        return fVen7;
    }

    /**
     * @param afVen7 the fVen7 to set
     */
    public static void setfVen7(String afVen7) {
        fVen7 = afVen7;
    }

    /**
     * @return the fDcs8
     */
    public static String getfDcs8() {
        return fDcs8;
    }

    /**
     * @param afDcs8 the fDcs8 to set
     */
    public static void setfDcs8(String afDcs8) {
        fDcs8 = afDcs8;
    }

    /**
     * @return the fVpn8
     */
    public static String getfVpn8() {
        return fVpn8;
    }

    /**
     * @param afVpn8 the fVpn8 to set
     */
    public static void setfVpn8(String afVpn8) {
        fVpn8 = afVpn8;
    }

    /**
     * @return the fVen8
     */
    public static String getfVen8() {
        return fVen8;
    }

    /**
     * @param afVen8 the fVen8 to set
     */
    public static void setfVen8(String afVen8) {
        fVen8 = afVen8;
    }

    /**
     * @return the fDcs9
     */
    public static String getfDcs9() {
        return fDcs9;
    }

    /**
     * @param afDcs9 the fDcs9 to set
     */
    public static void setfDcs9(String afDcs9) {
        fDcs9 = afDcs9;
    }

    /**
     * @return the fVpn9
     */
    public static String getfVpn9() {
        return fVpn9;
    }

    /**
     * @param afVpn9 the fVpn9 to set
     */
    public static void setfVpn9(String afVpn9) {
        fVpn9 = afVpn9;
    }

    /**
     * @return the fVen9
     */
    public static String getfVen9() {
        return fVen9;
    }

    /**
     * @param afVen9 the fVen9 to set
     */
    public static void setfVen9(String afVen9) {
        fVen9 = afVen9;
    }

    /**
     * @return the fDcs10
     */
    public static String getfDcs10() {
        return fDcs10;
    }

    /**
     * @param afDcs10 the fDcs10 to set
     */
    public static void setfDcs10(String afDcs10) {
        fDcs10 = afDcs10;
    }

    /**
     * @return the fVpn10
     */
    public static String getfVpn10() {
        return fVpn10;
    }

    /**
     * @param afVpn10 the fVpn10 to set
     */
    public static void setfVpn10(String afVpn10) {
        fVpn10 = afVpn10;
    }

    /**
     * @return the fVen10
     */
    public static String getfVen10() {
        return fVen10;
    }

    /**
     * @param afVen10 the fVen10 to set
     */
    public static void setfVen10(String afVen10) {
        fVen10 = afVen10;
    }

    /**
     * @return the sDcs1
     */
    public static String getsDcs1() {
        return sDcs1;
    }

    /**
     * @param asDcs1 the sDcs1 to set
     */
    public static void setsDcs1(String asDcs1) {
        sDcs1 = asDcs1;
    }

    /**
     * @return the sVpn1
     */
    public static String getsVpn1() {
        return sVpn1;
    }

    /**
     * @param asVpn1 the sVpn1 to set
     */
    public static void setsVpn1(String asVpn1) {
        sVpn1 = asVpn1;
    }

    /**
     * @return the sVen1
     */
    public static String getsVen1() {
        return sVen1;
    }

    /**
     * @param asVen1 the sVen1 to set
     */
    public static void setsVen1(String asVen1) {
        sVen1 = asVen1;
    }

    /**
     * @return the sDcs2
     */
    public static String getsDcs2() {
        return sDcs2;
    }

    /**
     * @param asDcs2 the sDcs2 to set
     */
    public static void setsDcs2(String asDcs2) {
        sDcs2 = asDcs2;
    }

    /**
     * @return the sVpn2
     */
    public static String getsVpn2() {
        return sVpn2;
    }

    /**
     * @param asVpn2 the sVpn2 to set
     */
    public static void setsVpn2(String asVpn2) {
        sVpn2 = asVpn2;
    }

    /**
     * @return the sVen2
     */
    public static String getsVen2() {
        return sVen2;
    }

    /**
     * @param asVen2 the sVen2 to set
     */
    public static void setsVen2(String asVen2) {
        sVen2 = asVen2;
    }

    /**
     * @return the sDcs3
     */
    public static String getsDcs3() {
        return sDcs3;
    }

    /**
     * @param asDcs3 the sDcs3 to set
     */
    public static void setsDcs3(String asDcs3) {
        sDcs3 = asDcs3;
    }

    /**
     * @return the sVpn3
     */
    public static String getsVpn3() {
        return sVpn3;
    }

    /**
     * @param asVpn3 the sVpn3 to set
     */
    public static void setsVpn3(String asVpn3) {
        sVpn3 = asVpn3;
    }

    /**
     * @return the sVen3
     */
    public static String getsVen3() {
        return sVen3;
    }

    /**
     * @param asVen3 the sVen3 to set
     */
    public static void setsVen3(String asVen3) {
        sVen3 = asVen3;
    }

    /**
     * @return the sDcs4
     */
    public static String getsDcs4() {
        return sDcs4;
    }

    /**
     * @param asDcs4 the sDcs4 to set
     */
    public static void setsDcs4(String asDcs4) {
        sDcs4 = asDcs4;
    }

    /**
     * @return the sVpn4
     */
    public static String getsVpn4() {
        return sVpn4;
    }

    /**
     * @param asVpn4 the sVpn4 to set
     */
    public static void setsVpn4(String asVpn4) {
        sVpn4 = asVpn4;
    }

    /**
     * @return the sVen4
     */
    public static String getsVen4() {
        return sVen4;
    }

    /**
     * @param asVen4 the sVen4 to set
     */
    public static void setsVen4(String asVen4) {
        sVen4 = asVen4;
    }

    /**
     * @return the sDcs5
     */
    public static String getsDcs5() {
        return sDcs5;
    }

    /**
     * @param asDcs5 the sDcs5 to set
     */
    public static void setsDcs5(String asDcs5) {
        sDcs5 = asDcs5;
    }

    /**
     * @return the sVpn5
     */
    public static String getsVpn5() {
        return sVpn5;
    }

    /**
     * @param asVpn5 the sVpn5 to set
     */
    public static void setsVpn5(String asVpn5) {
        sVpn5 = asVpn5;
    }

    /**
     * @return the sVen5
     */
    public static String getsVen5() {
        return sVen5;
    }

    /**
     * @param asVen5 the sVen5 to set
     */
    public static void setsVen5(String asVen5) {
        sVen5 = asVen5;
    }

    /**
     * @return the sDcs6
     */
    public static String getsDcs6() {
        return sDcs6;
    }

    /**
     * @param asDcs6 the sDcs6 to set
     */
    public static void setsDcs6(String asDcs6) {
        sDcs6 = asDcs6;
    }

    /**
     * @return the sVpn6
     */
    public static String getsVpn6() {
        return sVpn6;
    }

    /**
     * @param asVpn6 the sVpn6 to set
     */
    public static void setsVpn6(String asVpn6) {
        sVpn6 = asVpn6;
    }

    /**
     * @return the sVen6
     */
    public static String getsVen6() {
        return sVen6;
    }

    /**
     * @param asVen6 the sVen6 to set
     */
    public static void setsVen6(String asVen6) {
        sVen6 = asVen6;
    }

    /**
     * @return the sDcs7
     */
    public static String getsDcs7() {
        return sDcs7;
    }

    /**
     * @param asDcs7 the sDcs7 to set
     */
    public static void setsDcs7(String asDcs7) {
        sDcs7 = asDcs7;
    }

    /**
     * @return the sVpn7
     */
    public static String getsVpn7() {
        return sVpn7;
    }

    /**
     * @param asVpn7 the sVpn7 to set
     */
    public static void setsVpn7(String asVpn7) {
        sVpn7 = asVpn7;
    }

    /**
     * @return the sVen7
     */
    public static String getsVen7() {
        return sVen7;
    }

    /**
     * @param asVen7 the sVen7 to set
     */
    public static void setsVen7(String asVen7) {
        sVen7 = asVen7;
    }

    /**
     * @return the sDcs8
     */
    public static String getsDcs8() {
        return sDcs8;
    }

    /**
     * @param asDcs8 the sDcs8 to set
     */
    public static void setsDcs8(String asDcs8) {
        sDcs8 = asDcs8;
    }

    /**
     * @return the sVpn8
     */
    public static String getsVpn8() {
        return sVpn8;
    }

    /**
     * @param asVpn8 the sVpn8 to set
     */
    public static void setsVpn8(String asVpn8) {
        sVpn8 = asVpn8;
    }

    /**
     * @return the sVen8
     */
    public static String getsVen8() {
        return sVen8;
    }

    /**
     * @param asVen8 the sVen8 to set
     */
    public static void setsVen8(String asVen8) {
        sVen8 = asVen8;
    }

    /**
     * @return the sDcs9
     */
    public static String getsDcs9() {
        return sDcs9;
    }

    /**
     * @param asDcs9 the sDcs9 to set
     */
    public static void setsDcs9(String asDcs9) {
        sDcs9 = asDcs9;
    }

    /**
     * @return the sVpn9
     */
    public static String getsVpn9() {
        return sVpn9;
    }

    /**
     * @param asVpn9 the sVpn9 to set
     */
    public static void setsVpn9(String asVpn9) {
        sVpn9 = asVpn9;
    }

    /**
     * @return the sVen9
     */
    public static String getsVen9() {
        return sVen9;
    }

    /**
     * @param asVen9 the sVen9 to set
     */
    public static void setsVen9(String asVen9) {
        sVen9 = asVen9;
    }

    /**
     * @return the sDcs10
     */
    public static String getsDcs10() {
        return sDcs10;
    }

    /**
     * @param asDcs10 the sDcs10 to set
     */
    public static void setsDcs10(String asDcs10) {
        sDcs10 = asDcs10;
    }

    /**
     * @return the sVpn10
     */
    public static String getsVpn10() {
        return sVpn10;
    }

    /**
     * @param asVpn10 the sVpn10 to set
     */
    public static void setsVpn10(String asVpn10) {
        sVpn10 = asVpn10;
    }

    /**
     * @return the sVen10
     */
    public static String getsVen10() {
        return sVen10;
    }

    /**
     * @param asVen10 the sVen10 to set
     */
    public static void setsVen10(String asVen10) {
        sVen10 = asVen10;
    }

    private Component frame;
    public static String proc = null;
    public static String frmNm = null;    
 //  protected static String noComma;
 //  public static FileWriter fWriter = null;
 //  public static BufferedWriter writer = null;
 //  protected static String formType;
    public static String Line_ChkBox = null;
    private static String L1_Cost = "0.00";
    private static String L2_Cost = "0.00";
    private static String L3_Cost = "0.00";
    private static String L4_Cost = "0.00";
    private static String L5_Cost = "0.00";
    private static String L6_Cost = "0.00";
    private static String L7_Cost = "0.00";
    private static String L8_Cost = "0.00";
    private static String L9_Cost = "0.00";
    private static String L10_Cost = "0.00";
    private static String fDcs1 = "";
    private static String fVpn1 = "";
    private static String fVen1 = "";
    private static String fDcs2 = "";
    private static String fVpn2 = "";
    private static String fVen2 = "";
    private static String fDcs3 = "";
    private static String fVpn3 = "";
    private static String fVen3 = "";
    private static String fDcs4 = "";
    private static String fVpn4 = "";
    private static String fVen4 = "";
    private static String fDcs5 = "";
    private static String fVpn5 = "";
    private static String fVen5 = "";
    private static String fDcs6 = "";
    private static String fVpn6 = "";
    private static String fVen6 = "";
    private static String fDcs7 = "";
    private static String fVpn7 = "";
    private static String fVen7 = "";
    private static String fDcs8 = "";
    private static String fVpn8 = "";
    private static String fVen8 = "";
    private static String fDcs9 = "";
    private static String fVpn9 = "";
    private static String fVen9 = "";
    private static String fDcs10 = "";
    private static String fVpn10 = "";
    private static String fVen10 = "";
    private static String sDcs1 = "";
    private static String sVpn1 = "";
    private static String sVen1 = "";
    private static String sDcs2 = "";
    private static String sVpn2 = "";
    private static String sVen2 = "";
    private static String sDcs3 = "";
    private static String sVpn3 = "";
    private static String sVen3 = "";
    private static String sDcs4 = "";
    private static String sVpn4 = "";
    private static String sVen4 = "";
    private static String sDcs5 = "";
    private static String sVpn5 = "";
    private static String sVen5 = "";
    private static String sDcs6 = "";
    private static String sVpn6 = "";
    private static String sVen6 = "";
    private static String sDcs7 = "";
    private static String sVpn7 = "";
    private static String sVen7 = "";
    private static String sDcs8 = "";
    private static String sVpn8 = "";
    private static String sVen8 = "";
    private static String sDcs9 = "";
    private static String sVpn9 = "";
    private static String sVen9 = "";
    private static String sDcs10 = "";
    private static String sVpn10 = "";
    private static String sVen10 = "";
    public static String[] Reasons = new String[GetForms.cntRes];
    /*
    public static JTextField L1_First_Sku = null;
    public static JTextField L1_First_Desc = null;
    public static JTextField L1_Qty_Out = null;
    public static JTextField L1_First_Attr = null;
    public static JTextField L1_First_Size = null;
    public static JTextField L1_Orig_Retail = null;
    public static JComboBox L1_Reason_DropDown = null;
    public static JTextField L1_Desc_Damage = null;
    public static JTextField L1_New_Sku = null;
    public static JTextField L1_New_Desc = null;
    public static JTextField L1_New_Attr = null;
    public static JTextField L1_New_Size = null;
    public static JTextField L1_Qty_In = null; 
    */
    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    
    /**
     * Creates new form IAS2
     */
    public IAS2() throws SQLException {
        initComponents();
        // ** Gets store number from local system ** 
        GtStore.GtStore();
        String[] args = null;
        // ** Gets today's date **
        GtDates.main(args);
       // clearIfNew();
        IASdao.StrConversion();
        Store_Letter_Label.setText("("+ IASdao.StrCode +")");
        Delete_Form_Btn.setVisible(false);
        IC_Print_Btn.setVisible(false);
        
        if (DBConnect.TestEnviron == true) {
            Test_Label.setVisible(true);
        } else if (DBConnect.TestEnviron == false) {
            Test_Label.setVisible(false);
        }
        VersionLabel.setText("Version " + DBConnect.Version);        
        System.out.println("Opened IAS");
        subBtn.setEnabled(false);
        
        
        // ** checks if the user has DL or IC authority as well as checking form status and changes the button accordingly **
        switch (GetForms_InvAdj.usrType) {
            case "dl":
                try {
                    // ** checks if forms exist in database **
                    IASdao.ChkForm2();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                // ** this is meant to limit access to buttons depending on the user authority **
                if (IASdao.StFrmSt.equals("Pending")) {
                    ApproveBtn.setText("Approve");
                    ApproveBtn.setVisible(true);
                    RdyforExportBtn.setVisible(false);
                } else {
                    ApproveBtn.setVisible(false);
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
                // ** DL is not meant to use the submit button **
                subBtn.setEnabled(false);
                System.out.println("DL Check Forms and Limit/Enable Functionality in form");
                break;
            case "ic":
                try {
                    // ** checks for existing forms **
                    IASdao.ChkForm2();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                // ** these if statments below are to allow access for IC users to view the form at any status and use "submit", "approve", and "ready for export" buttons ** 
                subBtn.setEnabled(false);
                if (IASdao.StFrmSt.equals("In-Process")) {
                    RdyforExportBtn.setVisible(false);
                    ApproveBtn.setVisible(false);
                    subBtn.setEnabled(true);
                    Delete_Form_Btn.setVisible(true);
                } else {
                    subBtn.setEnabled(false);
                }
                if (IASdao.StFrmSt.equals("Pending")) {                   
                    RdyforExportBtn.setVisible(false);
                    ApproveBtn.setVisible(true);
                    subBtn.setEnabled(false);
                    Delete_Form_Btn.setVisible(true);
                    IC_Print_Btn.setVisible(true);
                } else {
                   ApproveBtn.setVisible(false);
                }
                if (IASdao.StFrmSt.equals("Approved")) {
                    RdyforExportBtn.setVisible(true);
                    ApproveBtn.setVisible(false);
                    subBtn.setEnabled(false);
                    Delete_Form_Btn.setVisible(true);
                    IC_Print_Btn.setVisible(true);
                } else {
                    RdyforExportBtn.setVisible(false);
                }
                System.out.println("IC Check Forms and Limit/Enable IC Functionality in form");
                break;
            // ** This is what is executed when the store opens a new form ** // ** Only STORES can create new forms **
            default:
               // clearIfNew();
                // try {
               // IASdao.ChkForm();
               // } catch (ClassNotFoundException | SQLException ex) {
               //     Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
               // }
                Close_Btn.setVisible(false);              
                IASdao.nwFrm = "yes";
                // ** does not clear fields after checking form if form was just submitted **
                /*
                if (IASdao.nwFrm.equals("yes")) {
                    L1_First_Sku.setText("");
                    L1_First_Desc.setText("");
                    L1_Qty_Out.setText("");
                    L1_First_Attr.setText("");
                    L1_First_Size.setText("");
                    L1_Orig_Retail.setText("");
                    L1_Reason_DropDown.setSelectedItem("");
                    L1_Desc_Damage.setText("");
                    L1_New_Sku.setText("");
                    L1_New_Desc.setText("");
                    L1_New_Attr.setText("");
                    L1_New_Size.setText("");
                    L1_Qty_In.setText(""); 
                } */
                ApproveBtn.setVisible(false);
                RdyforExportBtn.setVisible(false);
                // ** This ensures that the submit button only shows up when at least one of the check boxes are successfuly checked and the boolean value equals true (checkbox == true) **
                if (L1_Done_ChkBox.isSelected()==true || L2_Done_ChkBox.isSelected()==true || L3_Done_ChkBox.isSelected()==true || L4_Done_ChkBox.isSelected()==true || L5_Done_ChkBox.isSelected()==true
                || L6_Done_ChkBox.isSelected()==true || L7_Done_ChkBox.isSelected()==true || L8_Done_ChkBox.isSelected()==true || L9_Done_ChkBox.isSelected()==true || L10_Done_ChkBox.isSelected()==true){
                subBtn.setEnabled(true); 
                }
                System.out.println("Store Check Forms and Limit/Enable Functionality in form");
                break;
        }

        Store_Num_Label.setText(GtStore.store);
        Date_Label.setText(GtDates.tdate);
        // this pulls the form name from DAO class from database after creation
        frmNm = IASdao.StFrmNm;
        // this is the form name that is displayed within the form for the user to see
        Form_Name_Label.setText(frmNm);
        
        // Set Adjustment Reasons (combobox)
        try {
            GtRes();
        } catch (SQLException ex) {
            Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        // pulls data from reasons for drop down combo box selection
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
        

        // Fill Lines w/Existing Data from IASdao that accesses the database
        if (IASdao.cntRec > 0 && Character.isDigit(IASdao.recLine[1].split(";")[1].charAt(0))) {
            L1_First_Sku.setText(IASdao.recLine[1].split(";")[0]);
            L1_Qty_Out.setText(IASdao.recLine[1].split(";")[1]); 
            L1_First_Desc.setText(IASdao.recLine[1].split(";")[2]);
            L1_First_Attr.setText(IASdao.recLine[1].split(";")[3]);
            L1_First_Size.setText(IASdao.recLine[1].split(";")[4]);
            L1_Orig_Retail.setText(IASdao.recLine[1].split(";")[5]);
            L1_Reason_DropDown.setSelectedItem(IASdao.recLine[1].split(";")[6]);
            L1_Desc_Damage.setText(IASdao.recLine[1].split(";")[7]);
            L1_New_Sku.setText(IASdao.recLine[1].split(";")[8]);
            L1_New_Desc.setText(IASdao.recLine[1].split(";")[9]);
            L1_New_Attr.setText(IASdao.recLine[1].split(";")[10]);
            L1_New_Size.setText(IASdao.recLine[1].split(";")[11]);
            L1_Qty_In.setText(IASdao.recLine[1].split(";")[12]);
            L1_Timestamp.setText(IASdao.recLine[1].split(";")[13]);
            L1_Done_ChkBox.setSelected(true);

            L1_First_Sku.setEnabled(false);
            L1_Qty_Out.setEnabled(false);
            L1_First_Desc.setEnabled(false);
            L1_First_Attr.setEnabled(false);
            L1_First_Size.setEnabled(false);
            L1_Orig_Retail.setEnabled(false);
            L1_Reason_DropDown.setEnabled(false);
            L1_Desc_Damage.setEnabled(false);
            L1_New_Sku.setEnabled(false);
            L1_New_Desc.setEnabled(false);
            L1_New_Attr.setEnabled(false);
            L1_New_Size.setEnabled(false);
            L1_Qty_In.setEnabled(false);
            L1_Timestamp.setEnabled(false);
        }
        // Fill Lines w/Existing Data from IASdao that accesses the database
        if (IASdao.cntRec > 1 && Character.isDigit(IASdao.recLine[2].split(";")[1].charAt(0))) {
            L2_First_Sku.setText(IASdao.recLine[2].split(";")[0]);
            L2_Qty_Out.setText(IASdao.recLine[2].split(";")[1]); 
            L2_First_Desc.setText(IASdao.recLine[2].split(";")[2]);
            L2_First_Attr.setText(IASdao.recLine[2].split(";")[3]);
            L2_First_Size.setText(IASdao.recLine[2].split(";")[4]);
            L2_Orig_Retail.setText(IASdao.recLine[2].split(";")[5]);
            L2_Reason_DropDown.setSelectedItem(IASdao.recLine[2].split(";")[6]);
            L2_Desc_Damage.setText(IASdao.recLine[2].split(";")[7]);
            L2_New_Sku.setText(IASdao.recLine[2].split(";")[8]);
            L2_New_Desc.setText(IASdao.recLine[2].split(";")[9]);
            L2_New_Attr.setText(IASdao.recLine[2].split(";")[10]);
            L2_New_Size.setText(IASdao.recLine[2].split(";")[11]);
            L2_Qty_In.setText(IASdao.recLine[2].split(";")[12]);
            L2_Timestamp.setText(IASdao.recLine[2].split(";")[13]);
            L2_Done_ChkBox.setSelected(true);

            L2_First_Sku.setEnabled(false);
            L2_Qty_Out.setEnabled(false);
            L2_First_Desc.setEnabled(false);
            L2_First_Attr.setEnabled(false);
            L2_First_Size.setEnabled(false);
            L2_Orig_Retail.setEnabled(false);
            L2_Reason_DropDown.setEnabled(false);
            L2_Desc_Damage.setEnabled(false);
            L2_New_Sku.setEnabled(false);
            L2_New_Desc.setEnabled(false);
            L2_New_Attr.setEnabled(false);
            L2_New_Size.setEnabled(false);
            L2_Qty_In.setEnabled(false);
            L2_Timestamp.setEnabled(false);
        }
        // Fill Lines w/Existing Data from IASdao that accesses the database
        if (IASdao.cntRec > 2 && Character.isDigit(IASdao.recLine[3].split(";")[1].charAt(0))) {
            L3_First_Sku.setText(IASdao.recLine[3].split(";")[0]);
            L3_Qty_Out.setText(IASdao.recLine[3].split(";")[1]); 
            L3_First_Desc.setText(IASdao.recLine[3].split(";")[2]);
            L3_First_Attr.setText(IASdao.recLine[3].split(";")[3]);
            L3_First_Size.setText(IASdao.recLine[3].split(";")[4]);
            L3_Orig_Retail.setText(IASdao.recLine[3].split(";")[5]);
            L3_Reason_DropDown.setSelectedItem(IASdao.recLine[3].split(";")[6]);
            L3_Desc_Damage.setText(IASdao.recLine[3].split(";")[7]);
            L3_New_Sku.setText(IASdao.recLine[3].split(";")[8]);
            L3_New_Desc.setText(IASdao.recLine[3].split(";")[9]);
            L3_New_Attr.setText(IASdao.recLine[3].split(";")[10]);
            L3_New_Size.setText(IASdao.recLine[3].split(";")[11]);
            L3_Qty_In.setText(IASdao.recLine[3].split(";")[12]);
            L3_Timestamp.setText(IASdao.recLine[3].split(";")[13]);
            L3_Done_ChkBox.setSelected(true);

            L3_First_Sku.setEnabled(false);
            L3_Qty_Out.setEnabled(false);
            L3_First_Desc.setEnabled(false);
            L3_First_Attr.setEnabled(false);
            L3_First_Size.setEnabled(false);
            L3_Orig_Retail.setEnabled(false);
            L3_Reason_DropDown.setEnabled(false);
            L3_Desc_Damage.setEnabled(false);
            L3_New_Sku.setEnabled(false);
            L3_New_Desc.setEnabled(false);
            L3_New_Attr.setEnabled(false);
            L3_New_Size.setEnabled(false);
            L3_Qty_In.setEnabled(false);
            L3_Timestamp.setEnabled(false);
        }
        // Fill Lines w/Existing Data from IASdao that accesses the database
        if (IASdao.cntRec > 3 && Character.isDigit(IASdao.recLine[4].split(";")[1].charAt(0))) {
            L4_First_Sku.setText(IASdao.recLine[4].split(";")[0]);
            L4_Qty_Out.setText(IASdao.recLine[4].split(";")[1]); 
            L4_First_Desc.setText(IASdao.recLine[4].split(";")[2]);
            L4_First_Attr.setText(IASdao.recLine[4].split(";")[3]);
            L4_First_Size.setText(IASdao.recLine[4].split(";")[4]);
            L4_Orig_Retail.setText(IASdao.recLine[4].split(";")[5]);
            L4_Reason_DropDown.setSelectedItem(IASdao.recLine[4].split(";")[6]);
            L4_Desc_Damage.setText(IASdao.recLine[4].split(";")[7]);
            L4_New_Sku.setText(IASdao.recLine[4].split(";")[8]);
            L4_New_Desc.setText(IASdao.recLine[4].split(";")[9]);
            L4_New_Attr.setText(IASdao.recLine[4].split(";")[10]);
            L4_New_Size.setText(IASdao.recLine[4].split(";")[11]);
            L4_Qty_In.setText(IASdao.recLine[4].split(";")[12]);
            L4_Timestamp.setText(IASdao.recLine[4].split(";")[13]);
            L4_Done_ChkBox.setSelected(true);

            L4_First_Sku.setEnabled(false);
            L4_Qty_Out.setEnabled(false);
            L4_First_Desc.setEnabled(false);
            L4_First_Attr.setEnabled(false);
            L4_First_Size.setEnabled(false);
            L4_Orig_Retail.setEnabled(false);
            L4_Reason_DropDown.setEnabled(false);
            L4_Desc_Damage.setEnabled(false);
            L4_New_Sku.setEnabled(false);
            L4_New_Desc.setEnabled(false);
            L4_New_Attr.setEnabled(false);
            L4_New_Size.setEnabled(false);
            L4_Qty_In.setEnabled(false);
            L4_Timestamp.setEnabled(false);
        }
        // Fill Lines w/Existing Data from IASdao that accesses the database
        if (IASdao.cntRec > 4 && Character.isDigit(IASdao.recLine[5].split(";")[1].charAt(0))) {
            L5_First_Sku.setText(IASdao.recLine[5].split(";")[0]);
            L5_Qty_Out.setText(IASdao.recLine[5].split(";")[1]); 
            L5_First_Desc.setText(IASdao.recLine[5].split(";")[2]);
            L5_First_Attr.setText(IASdao.recLine[5].split(";")[3]);
            L5_First_Size.setText(IASdao.recLine[5].split(";")[4]);
            L5_Orig_Retail.setText(IASdao.recLine[5].split(";")[5]);
            L5_Reason_DropDown.setSelectedItem(IASdao.recLine[5].split(";")[6]);
            L5_Desc_Damage.setText(IASdao.recLine[5].split(";")[7]);
            L5_New_Sku.setText(IASdao.recLine[5].split(";")[8]);
            L5_New_Desc.setText(IASdao.recLine[5].split(";")[9]);
            L5_New_Attr.setText(IASdao.recLine[5].split(";")[10]);
            L5_New_Size.setText(IASdao.recLine[5].split(";")[11]);
            L5_Qty_In.setText(IASdao.recLine[5].split(";")[12]);
            L5_Timestamp.setText(IASdao.recLine[5].split(";")[13]);
            L5_Done_ChkBox.setSelected(true);

            L5_First_Sku.setEnabled(false);
            L5_Qty_Out.setEnabled(false);
            L5_First_Desc.setEnabled(false);
            L5_First_Attr.setEnabled(false);
            L5_First_Size.setEnabled(false);
            L5_Orig_Retail.setEnabled(false);
            L5_Reason_DropDown.setEnabled(false);
            L5_Desc_Damage.setEnabled(false);
            L5_New_Sku.setEnabled(false);
            L5_New_Desc.setEnabled(false);
            L5_New_Attr.setEnabled(false);
            L5_New_Size.setEnabled(false);
            L5_Qty_In.setEnabled(false);
            L5_Timestamp.setEnabled(false);
        }
        // Fill Lines w/Existing Data from IASdao that accesses the database
       if (IASdao.cntRec > 5 && Character.isDigit(IASdao.recLine[6].split(";")[1].charAt(0))) {
            L6_First_Sku.setText(IASdao.recLine[6].split(";")[0]);
            L6_Qty_Out.setText(IASdao.recLine[6].split(";")[1]); 
            L6_First_Desc.setText(IASdao.recLine[6].split(";")[2]);
            L6_First_Attr.setText(IASdao.recLine[6].split(";")[3]);
            L6_First_Size.setText(IASdao.recLine[6].split(";")[4]);
            L6_Orig_Retail.setText(IASdao.recLine[6].split(";")[5]);
            L6_Reason_DropDown.setSelectedItem(IASdao.recLine[6].split(";")[6]);
            L6_Desc_Damage.setText(IASdao.recLine[6].split(";")[7]);
            L6_New_Sku.setText(IASdao.recLine[6].split(";")[8]);
            L6_New_Desc.setText(IASdao.recLine[6].split(";")[9]);
            L6_New_Attr.setText(IASdao.recLine[6].split(";")[10]);
            L6_New_Size.setText(IASdao.recLine[6].split(";")[11]);
            L6_Qty_In.setText(IASdao.recLine[6].split(";")[12]);
            L6_Timestamp.setText(IASdao.recLine[6].split(";")[13]);
            L6_Done_ChkBox.setSelected(true);

            L6_First_Sku.setEnabled(false);
            L6_Qty_Out.setEnabled(false);
            L6_First_Desc.setEnabled(false);
            L6_First_Attr.setEnabled(false);
            L6_First_Size.setEnabled(false);
            L6_Orig_Retail.setEnabled(false);
            L6_Reason_DropDown.setEnabled(false);
            L6_Desc_Damage.setEnabled(false);
            L6_New_Sku.setEnabled(false);
            L6_New_Desc.setEnabled(false);
            L6_New_Attr.setEnabled(false);
            L6_New_Size.setEnabled(false);
            L6_Qty_In.setEnabled(false);
            L6_Timestamp.setEnabled(false);
        } 
        // Fill Lines w/Existing Data from IASdao that accesses the database
        if (IASdao.cntRec > 6 && Character.isDigit(IASdao.recLine[7].split(";")[1].charAt(0))) {
            L7_First_Sku.setText(IASdao.recLine[7].split(";")[0]);
            L7_Qty_Out.setText(IASdao.recLine[7].split(";")[1]); 
            L7_First_Desc.setText(IASdao.recLine[7].split(";")[2]);
            L7_First_Attr.setText(IASdao.recLine[7].split(";")[3]);
            L7_First_Size.setText(IASdao.recLine[7].split(";")[4]);
            L7_Orig_Retail.setText(IASdao.recLine[7].split(";")[5]);
            L7_Reason_DropDown.setSelectedItem(IASdao.recLine[7].split(";")[6]);
            L7_Desc_Damage.setText(IASdao.recLine[7].split(";")[7]);
            L7_New_Sku.setText(IASdao.recLine[7].split(";")[8]);
            L7_New_Desc.setText(IASdao.recLine[7].split(";")[9]);
            L7_New_Attr.setText(IASdao.recLine[7].split(";")[10]);
            L7_New_Size.setText(IASdao.recLine[7].split(";")[11]);
            L7_Qty_In.setText(IASdao.recLine[7].split(";")[12]);
            L7_Timestamp.setText(IASdao.recLine[7].split(";")[13]);
            L7_Done_ChkBox.setSelected(true);

            L7_First_Sku.setEnabled(false);
            L7_Qty_Out.setEnabled(false);
            L7_First_Desc.setEnabled(false);
            L7_First_Attr.setEnabled(false);
            L7_First_Size.setEnabled(false);
            L7_Orig_Retail.setEnabled(false);
            L7_Reason_DropDown.setEnabled(false);
            L7_Desc_Damage.setEnabled(false);
            L7_New_Sku.setEnabled(false);
            L7_New_Desc.setEnabled(false);
            L7_New_Attr.setEnabled(false);
            L7_New_Size.setEnabled(false);
            L7_Qty_In.setEnabled(false);
            L7_Timestamp.setEnabled(false);
        }
        // Fill Lines w/Existing Data from IASdao that accesses the database
        if (IASdao.cntRec > 7 && Character.isDigit(IASdao.recLine[8].split(";")[1].charAt(0))) {
            L8_First_Sku.setText(IASdao.recLine[8].split(";")[0]);
            L8_Qty_Out.setText(IASdao.recLine[8].split(";")[1]); 
            L8_First_Desc.setText(IASdao.recLine[8].split(";")[2]);
            L8_First_Attr.setText(IASdao.recLine[8].split(";")[3]);
            L8_First_Size.setText(IASdao.recLine[8].split(";")[4]);
            L8_Orig_Retail.setText(IASdao.recLine[8].split(";")[5]);
            L8_Reason_DropDown.setSelectedItem(IASdao.recLine[8].split(";")[6]);
            L8_Desc_Damage.setText(IASdao.recLine[8].split(";")[7]);
            L8_New_Sku.setText(IASdao.recLine[8].split(";")[8]);
            L8_New_Desc.setText(IASdao.recLine[8].split(";")[9]);
            L8_New_Attr.setText(IASdao.recLine[8].split(";")[10]);
            L8_New_Size.setText(IASdao.recLine[8].split(";")[11]);
            L8_Qty_In.setText(IASdao.recLine[8].split(";")[12]);
            L8_Timestamp.setText(IASdao.recLine[8].split(";")[13]);
            L8_Done_ChkBox.setSelected(true);

            L8_First_Sku.setEnabled(false);
            L8_Qty_Out.setEnabled(false);
            L8_First_Desc.setEnabled(false);
            L8_First_Attr.setEnabled(false);
            L8_First_Size.setEnabled(false);
            L8_Orig_Retail.setEnabled(false);
            L8_Reason_DropDown.setEnabled(false);
            L8_Desc_Damage.setEnabled(false);
            L8_New_Sku.setEnabled(false);
            L8_New_Desc.setEnabled(false);
            L8_New_Attr.setEnabled(false);
            L8_New_Size.setEnabled(false);
            L8_Qty_In.setEnabled(false);
            L8_Timestamp.setEnabled(false);
        }
        // Fill Lines w/Existing Data from IASdao that accesses the database
        if (IASdao.cntRec > 8 && Character.isDigit(IASdao.recLine[9].split(";")[1].charAt(0))) {
            L9_First_Sku.setText(IASdao.recLine[9].split(";")[0]);
            L9_Qty_Out.setText(IASdao.recLine[9].split(";")[1]); 
            L9_First_Desc.setText(IASdao.recLine[9].split(";")[2]);
            L9_First_Attr.setText(IASdao.recLine[9].split(";")[3]);
            L9_First_Size.setText(IASdao.recLine[9].split(";")[4]);
            L9_Orig_Retail.setText(IASdao.recLine[9].split(";")[5]);
            L9_Reason_DropDown.setSelectedItem(IASdao.recLine[9].split(";")[6]);
            L9_Desc_Damage.setText(IASdao.recLine[9].split(";")[7]);
            L9_New_Sku.setText(IASdao.recLine[9].split(";")[8]);
            L9_New_Desc.setText(IASdao.recLine[9].split(";")[9]);
            L9_New_Attr.setText(IASdao.recLine[9].split(";")[10]);
            L9_New_Size.setText(IASdao.recLine[9].split(";")[11]);
            L9_Qty_In.setText(IASdao.recLine[9].split(";")[12]);
            L9_Timestamp.setText(IASdao.recLine[9].split(";")[13]);
            L9_Done_ChkBox.setSelected(true);

            L9_First_Sku.setEnabled(false);
            L9_Qty_Out.setEnabled(false);
            L9_First_Desc.setEnabled(false);
            L9_First_Attr.setEnabled(false);
            L9_First_Size.setEnabled(false);
            L9_Orig_Retail.setEnabled(false);
            L9_Reason_DropDown.setEnabled(false);
            L9_Desc_Damage.setEnabled(false);
            L9_New_Sku.setEnabled(false);
            L9_New_Desc.setEnabled(false);
            L9_New_Attr.setEnabled(false);
            L9_New_Size.setEnabled(false);
            L9_Qty_In.setEnabled(false);
            L9_Timestamp.setEnabled(false);
        }
        // Fill Lines w/Existing Data from IASdao that accesses the database
        if (IASdao.cntRec > 9 && Character.isDigit(IASdao.recLine[10].split(";")[1].charAt(0))) {
            L10_First_Sku.setText(IASdao.recLine[10].split(";")[0]);
            L10_Qty_Out.setText(IASdao.recLine[10].split(";")[1]); 
            L10_First_Desc.setText(IASdao.recLine[10].split(";")[2]);
            L10_First_Attr.setText(IASdao.recLine[10].split(";")[3]);
            L10_First_Size.setText(IASdao.recLine[10].split(";")[4]);
            L10_Orig_Retail.setText(IASdao.recLine[10].split(";")[5]);
            L10_Reason_DropDown.setSelectedItem(IASdao.recLine[10].split(";")[6]);
            L10_Desc_Damage.setText(IASdao.recLine[10].split(";")[7]);
            L10_New_Sku.setText(IASdao.recLine[10].split(";")[8]);
            L10_New_Desc.setText(IASdao.recLine[10].split(";")[9]);
            L10_New_Attr.setText(IASdao.recLine[10].split(";")[10]);
            L10_New_Size.setText(IASdao.recLine[10].split(";")[11]);
            L10_Qty_In.setText(IASdao.recLine[10].split(";")[12]);
            L10_Timestamp.setText(IASdao.recLine[10].split(";")[13]);
            L10_Done_ChkBox.setSelected(true);

            L10_First_Sku.setEnabled(false);
            L10_Qty_Out.setEnabled(false);
            L10_First_Desc.setEnabled(false);
            L10_First_Attr.setEnabled(false);
            L10_First_Size.setEnabled(false);
            L10_Orig_Retail.setEnabled(false);
            L10_Reason_DropDown.setEnabled(false);
            L10_Desc_Damage.setEnabled(false);
            L10_New_Sku.setEnabled(false);
            L10_New_Desc.setEnabled(false);
            L10_New_Attr.setEnabled(false);
            L10_New_Size.setEnabled(false);
            L10_Qty_In.setEnabled(false);
            L10_Timestamp.setEnabled(false);
        }
        System.out.println("Fill lines with existing data in form");
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
        L1_Qty_Out = new javax.swing.JTextField();
        L1_First_Sku = new javax.swing.JTextField();
        L1_First_Desc = new javax.swing.JTextField();
        L1_Reason_DropDown = new javax.swing.JComboBox();
        L1_Desc_Damage = new javax.swing.JTextField();
        L1_Orig_Retail = new javax.swing.JTextField();
        L1_New_Sku = new javax.swing.JTextField();
        L1_New_Desc = new javax.swing.JTextField();
        L1_Qty_In = new javax.swing.JTextField();
        jTextField118 = new javax.swing.JTextField();
        jTextField119 = new javax.swing.JTextField();
        jTextField121 = new javax.swing.JTextField();
        jTextField122 = new javax.swing.JTextField();
        jTextField123 = new javax.swing.JTextField();
        jTextField124 = new javax.swing.JTextField();
        jTextField125 = new javax.swing.JTextField();
        jTextField126 = new javax.swing.JTextField();
        jTextField127 = new javax.swing.JTextField();
        L1_Done_ChkBox = new javax.swing.JCheckBox();
        L1_Timestamp = new javax.swing.JLabel();
        Store_Location_Label = new javax.swing.JLabel();
        Store_Logged_In_Label = new javax.swing.JLabel();
        tdteLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        L2_Qty_Out = new javax.swing.JTextField();
        L2_First_Sku = new javax.swing.JTextField();
        L2_First_Desc = new javax.swing.JTextField();
        L2_Reason_DropDown = new javax.swing.JComboBox();
        L2_Desc_Damage = new javax.swing.JTextField();
        L2_New_Sku = new javax.swing.JTextField();
        L2_New_Desc = new javax.swing.JTextField();
        L2_Orig_Retail = new javax.swing.JTextField();
        L2_Qty_In = new javax.swing.JTextField();
        L2_Done_ChkBox = new javax.swing.JCheckBox();
        L2_Timestamp = new javax.swing.JLabel();
        L3_Qty_Out = new javax.swing.JTextField();
        L3_First_Sku = new javax.swing.JTextField();
        L3_First_Desc = new javax.swing.JTextField();
        L3_Reason_DropDown = new javax.swing.JComboBox();
        L3_Desc_Damage = new javax.swing.JTextField();
        L3_Orig_Retail = new javax.swing.JTextField();
        L3_New_Sku = new javax.swing.JTextField();
        L3_New_Desc = new javax.swing.JTextField();
        L3_Qty_In = new javax.swing.JTextField();
        L3_Done_ChkBox = new javax.swing.JCheckBox();
        L3_Timestamp = new javax.swing.JLabel();
        L4_Qty_Out = new javax.swing.JTextField();
        L4_First_Sku = new javax.swing.JTextField();
        L4_First_Desc = new javax.swing.JTextField();
        L4_Reason_DropDown = new javax.swing.JComboBox();
        L4_Desc_Damage = new javax.swing.JTextField();
        L4_Orig_Retail = new javax.swing.JTextField();
        L4_New_Sku = new javax.swing.JTextField();
        L4_New_Desc = new javax.swing.JTextField();
        L4_Qty_In = new javax.swing.JTextField();
        L4_Done_ChkBox = new javax.swing.JCheckBox();
        L4_Timestamp = new javax.swing.JLabel();
        L5_Qty_Out = new javax.swing.JTextField();
        L5_First_Sku = new javax.swing.JTextField();
        L5_First_Desc = new javax.swing.JTextField();
        L5_Reason_DropDown = new javax.swing.JComboBox();
        L5_Desc_Damage = new javax.swing.JTextField();
        L5_Orig_Retail = new javax.swing.JTextField();
        L5_New_Sku = new javax.swing.JTextField();
        L5_New_Desc = new javax.swing.JTextField();
        L5_Qty_In = new javax.swing.JTextField();
        L5_Done_ChkBox = new javax.swing.JCheckBox();
        L5_Timestamp = new javax.swing.JLabel();
        L6_Qty_Out = new javax.swing.JTextField();
        L6_First_Sku = new javax.swing.JTextField();
        L6_First_Desc = new javax.swing.JTextField();
        L6_Reason_DropDown = new javax.swing.JComboBox();
        L6_Desc_Damage = new javax.swing.JTextField();
        L6_Orig_Retail = new javax.swing.JTextField();
        L6_New_Sku = new javax.swing.JTextField();
        L6_New_Desc = new javax.swing.JTextField();
        L6_Qty_In = new javax.swing.JTextField();
        L6_Done_ChkBox = new javax.swing.JCheckBox();
        L6_Timestamp = new javax.swing.JLabel();
        L7_Qty_Out = new javax.swing.JTextField();
        L7_First_Sku = new javax.swing.JTextField();
        L7_First_Desc = new javax.swing.JTextField();
        L7_Reason_DropDown = new javax.swing.JComboBox();
        L7_Desc_Damage = new javax.swing.JTextField();
        L7_Orig_Retail = new javax.swing.JTextField();
        L7_New_Sku = new javax.swing.JTextField();
        L7_New_Desc = new javax.swing.JTextField();
        L7_Qty_In = new javax.swing.JTextField();
        L7_Done_ChkBox = new javax.swing.JCheckBox();
        L7_Timestamp = new javax.swing.JLabel();
        L8_Qty_Out = new javax.swing.JTextField();
        L8_First_Sku = new javax.swing.JTextField();
        L8_First_Desc = new javax.swing.JTextField();
        L8_Reason_DropDown = new javax.swing.JComboBox();
        L8_Desc_Damage = new javax.swing.JTextField();
        L8_Orig_Retail = new javax.swing.JTextField();
        L8_New_Sku = new javax.swing.JTextField();
        L8_New_Desc = new javax.swing.JTextField();
        L8_Qty_In = new javax.swing.JTextField();
        L8_Done_ChkBox = new javax.swing.JCheckBox();
        L8_Timestamp = new javax.swing.JLabel();
        L9_Qty_Out = new javax.swing.JTextField();
        L9_First_Sku = new javax.swing.JTextField();
        L9_First_Desc = new javax.swing.JTextField();
        L9_Reason_DropDown = new javax.swing.JComboBox();
        L9_Desc_Damage = new javax.swing.JTextField();
        L9_Orig_Retail = new javax.swing.JTextField();
        L9_New_Sku = new javax.swing.JTextField();
        L9_New_Desc = new javax.swing.JTextField();
        L9_Qty_In = new javax.swing.JTextField();
        L9_Done_ChkBox = new javax.swing.JCheckBox();
        L9_Timestamp = new javax.swing.JLabel();
        L10_Qty_Out = new javax.swing.JTextField();
        L10_First_Sku = new javax.swing.JTextField();
        L10_First_Desc = new javax.swing.JTextField();
        L10_Reason_DropDown = new javax.swing.JComboBox();
        L10_Desc_Damage = new javax.swing.JTextField();
        L10_Orig_Retail = new javax.swing.JTextField();
        L10_New_Sku = new javax.swing.JTextField();
        L10_Qty_In = new javax.swing.JTextField();
        L10_Done_ChkBox = new javax.swing.JCheckBox();
        L10_Timestamp = new javax.swing.JLabel();
        L1 = new javax.swing.JLabel();
        L2 = new javax.swing.JLabel();
        L3 = new javax.swing.JLabel();
        L4 = new javax.swing.JLabel();
        L5 = new javax.swing.JLabel();
        L6 = new javax.swing.JLabel();
        L7 = new javax.swing.JLabel();
        L8 = new javax.swing.JLabel();
        L9 = new javax.swing.JLabel();
        L10 = new javax.swing.JLabel();
        subBtn = new javax.swing.JButton();
        Close_Btn = new javax.swing.JButton();
        ApproveBtn = new javax.swing.JButton();
        jTextField191 = new javax.swing.JTextField();
        L10_New_Desc = new javax.swing.JTextField();
        jTextField128 = new javax.swing.JTextField();
        jTextField129 = new javax.swing.JTextField();
        L1_First_Attr = new javax.swing.JTextField();
        L2_First_Attr = new javax.swing.JTextField();
        L3_First_Attr = new javax.swing.JTextField();
        L4_First_Attr = new javax.swing.JTextField();
        L5_First_Attr = new javax.swing.JTextField();
        L6_First_Attr = new javax.swing.JTextField();
        L7_First_Attr = new javax.swing.JTextField();
        L8_First_Attr = new javax.swing.JTextField();
        L9_First_Attr = new javax.swing.JTextField();
        L10_First_Attr = new javax.swing.JTextField();
        L1_First_Size = new javax.swing.JTextField();
        L2_First_Size = new javax.swing.JTextField();
        L3_First_Size = new javax.swing.JTextField();
        L4_First_Size = new javax.swing.JTextField();
        L5_First_Size = new javax.swing.JTextField();
        L7_First_Size = new javax.swing.JTextField();
        L8_First_Size = new javax.swing.JTextField();
        L9_First_Size = new javax.swing.JTextField();
        L10_First_Size = new javax.swing.JTextField();
        L6_First_Size = new javax.swing.JTextField();
        Attr2 = new javax.swing.JTextField();
        jTextField131 = new javax.swing.JTextField();
        L1_New_Attr = new javax.swing.JTextField();
        L2_New_Attr = new javax.swing.JTextField();
        L3_New_Attr = new javax.swing.JTextField();
        L4_New_Attr = new javax.swing.JTextField();
        L5_New_Attr = new javax.swing.JTextField();
        L6_New_Attr = new javax.swing.JTextField();
        L7_New_Attr = new javax.swing.JTextField();
        L8_New_Attr = new javax.swing.JTextField();
        L9_New_Attr = new javax.swing.JTextField();
        L10_New_Attr = new javax.swing.JTextField();
        L1_New_Size = new javax.swing.JTextField();
        L2_New_Size = new javax.swing.JTextField();
        L3_New_Size = new javax.swing.JTextField();
        L4_New_Size = new javax.swing.JTextField();
        L5_New_Size = new javax.swing.JTextField();
        L6_New_Size = new javax.swing.JTextField();
        L7_New_Size = new javax.swing.JTextField();
        L8_New_Size = new javax.swing.JTextField();
        L9_New_Size = new javax.swing.JTextField();
        L10_New_Size = new javax.swing.JTextField();
        RdyforExportBtn = new javax.swing.JButton();
        Form_Name_Tag = new javax.swing.JLabel();
        Test_Label = new javax.swing.JLabel();
        VersionLabel = new javax.swing.JLabel();
        CLine_textfield = new javax.swing.JTextField();
        CLine_Button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Delete_Form_Btn = new javax.swing.JButton();
        Date_Label = new javax.swing.JLabel();
        Form_Name_Label = new javax.swing.JLabel();
        Store_Letter_Label = new javax.swing.JLabel();
        Store_Num_Label = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        IC_Print_Btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Inventory Adjustment Application IAS Form");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                clsFrm(evt);
            }
        });

        jScrollPane2.setPreferredSize(new java.awt.Dimension(1440, 670));

        jPanel6.setBackground(new java.awt.Color(153, 180, 209));
        jPanel6.setPreferredSize(new java.awt.Dimension(1420, 650));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1400, 640));
        jPanel1.setRequestFocusEnabled(false);

        L1_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_Qty_OutKeyPressed(evt);
            }
        });

        L1_First_Sku.setToolTipText("");
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

        L1_First_Desc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        L1_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L1_First_Desc.setFocusable(false);

        L1_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] {""}));
        L1_Reason_DropDown.setToolTipText("");
        L1_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_Reason_DropDownKeyPressed(evt);
            }
        });

        L1_Desc_Damage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L1_Desc_DamageFocusGained(evt);
            }
        });
        L1_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_Desc_DamageKeyPressed(evt);
            }
        });

        L1_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L1_Orig_Retail.setFocusable(false);

        L1_New_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L1_New_SkuFocusLost(evt);
            }
        });
        L1_New_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_New_SkuKeyPressed(evt);
            }
        });

        L1_New_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L1_New_Desc.setFocusable(false);

        L1_Qty_In.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L1_Qty_InKeyPressed(evt);
            }
        });

        jTextField118.setEditable(false);
        jTextField118.setBackground(new java.awt.Color(255, 255, 255));
        jTextField118.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField118.setText("SKU #");
        jTextField118.setToolTipText("");
        jTextField118.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField118.setFocusable(false);

        jTextField119.setEditable(false);
        jTextField119.setBackground(new java.awt.Color(255, 255, 255));
        jTextField119.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField119.setText("Qty-Out");
        jTextField119.setToolTipText("");
        jTextField119.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField119.setFocusable(false);

        jTextField121.setEditable(false);
        jTextField121.setBackground(new java.awt.Color(255, 255, 255));
        jTextField121.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField121.setText("Description");
        jTextField121.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField121.setFocusable(false);

        jTextField122.setEditable(false);
        jTextField122.setBackground(new java.awt.Color(255, 255, 255));
        jTextField122.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField122.setText("Reason");
        jTextField122.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField122.setFocusable(false);

        jTextField123.setEditable(false);
        jTextField123.setBackground(new java.awt.Color(255, 255, 255));
        jTextField123.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField123.setText("Orig.Retail");
        jTextField123.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField123.setFocusable(false);

        jTextField124.setEditable(false);
        jTextField124.setBackground(new java.awt.Color(255, 255, 255));
        jTextField124.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField124.setText("New SKU");
        jTextField124.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField124.setFocusable(false);

        jTextField125.setEditable(false);
        jTextField125.setBackground(new java.awt.Color(255, 255, 255));
        jTextField125.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField125.setText("Desc");
        jTextField125.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField125.setFocusable(false);

        jTextField126.setEditable(false);
        jTextField126.setBackground(new java.awt.Color(255, 255, 255));
        jTextField126.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField126.setText("Qty In");
        jTextField126.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField126.setFocusable(false);

        jTextField127.setEditable(false);
        jTextField127.setBackground(new java.awt.Color(255, 255, 255));
        jTextField127.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField127.setText("Desc / Explanation");
        jTextField127.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField127.setFocusable(false);

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

        Store_Location_Label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Store_Location_Label.setText("Store Location:");

        Store_Logged_In_Label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Store_Logged_In_Label.setText("Store #:");

        tdteLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tdteLabel.setText("Today's Date:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("INVENTORY ADJUSTMENTS");

        L2_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_Qty_OutKeyPressed(evt);
            }
        });

        L2_First_Sku.setToolTipText("");
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

        L2_First_Desc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        L2_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L2_First_Desc.setFocusable(false);

        L2_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is defective", "Not as described", "Not what I ordered", "Other" }));
        L2_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_Reason_DropDownKeyPressed(evt);
            }
        });

        L2_Desc_Damage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L2_Desc_DamageFocusGained(evt);
            }
        });
        L2_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_Desc_DamageKeyPressed(evt);
            }
        });

        L2_New_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L2_New_SkuFocusLost(evt);
            }
        });
        L2_New_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_New_SkuKeyPressed(evt);
            }
        });

        L2_New_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L2_New_Desc.setFocusable(false);

        L2_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L2_Orig_Retail.setFocusable(false);

        L2_Qty_In.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L2_Qty_InKeyPressed(evt);
            }
        });

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

        L3_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_Qty_OutKeyPressed(evt);
            }
        });

        L3_First_Sku.setToolTipText("");
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

        L3_First_Desc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        L3_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L3_First_Desc.setFocusable(false);

        L3_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is defective", "Not as described", "Not what I ordered", "Other" }));
        L3_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_Reason_DropDownKeyPressed(evt);
            }
        });

        L3_Desc_Damage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L3_Desc_DamageFocusGained(evt);
            }
        });
        L3_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_Desc_DamageKeyPressed(evt);
            }
        });

        L3_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L3_Orig_Retail.setFocusable(false);

        L3_New_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L3_New_SkuFocusLost(evt);
            }
        });
        L3_New_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_New_SkuKeyPressed(evt);
            }
        });

        L3_New_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L3_New_Desc.setFocusable(false);

        L3_Qty_In.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L3_Qty_InKeyPressed(evt);
            }
        });

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

        L4_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_Qty_OutKeyPressed(evt);
            }
        });

        L4_First_Sku.setToolTipText("");
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

        L4_First_Desc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        L4_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L4_First_Desc.setFocusable(false);

        L4_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is defective", "Not as described", "Not what I ordered", "Other" }));
        L4_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_Reason_DropDownKeyPressed(evt);
            }
        });

        L4_Desc_Damage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L4_Desc_DamageFocusGained(evt);
            }
        });
        L4_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_Desc_DamageKeyPressed(evt);
            }
        });

        L4_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L4_Orig_Retail.setFocusable(false);

        L4_New_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L4_New_SkuFocusLost(evt);
            }
        });
        L4_New_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_New_SkuKeyPressed(evt);
            }
        });

        L4_New_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L4_New_Desc.setFocusable(false);

        L4_Qty_In.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L4_Qty_InKeyPressed(evt);
            }
        });

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

        L5_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_Qty_OutKeyPressed(evt);
            }
        });

        L5_First_Sku.setToolTipText("");
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

        L5_First_Desc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        L5_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L5_First_Desc.setFocusable(false);

        L5_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is defective", "Not as described", "Not what I ordered", "Other" }));
        L5_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_Reason_DropDownKeyPressed(evt);
            }
        });

        L5_Desc_Damage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L5_Desc_DamageFocusGained(evt);
            }
        });
        L5_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_Desc_DamageKeyPressed(evt);
            }
        });

        L5_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L5_Orig_Retail.setFocusable(false);

        L5_New_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L5_New_SkuFocusLost(evt);
            }
        });
        L5_New_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_New_SkuKeyPressed(evt);
            }
        });

        L5_New_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L5_New_Desc.setFocusable(false);

        L5_Qty_In.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L5_Qty_InKeyPressed(evt);
            }
        });

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

        L6_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_Qty_OutKeyPressed(evt);
            }
        });

        L6_First_Sku.setToolTipText("");
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

        L6_First_Desc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        L6_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L6_First_Desc.setFocusable(false);

        L6_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is defective", "Not as described", "Not what I ordered", "Other" }));
        L6_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_Reason_DropDownKeyPressed(evt);
            }
        });

        L6_Desc_Damage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L6_Desc_DamageFocusGained(evt);
            }
        });
        L6_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_Desc_DamageKeyPressed(evt);
            }
        });

        L6_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L6_Orig_Retail.setFocusable(false);

        L6_New_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L6_New_SkuFocusLost(evt);
            }
        });
        L6_New_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_New_SkuKeyPressed(evt);
            }
        });

        L6_New_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L6_New_Desc.setFocusable(false);

        L6_Qty_In.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L6_Qty_InKeyPressed(evt);
            }
        });

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

        L7_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_Qty_OutKeyPressed(evt);
            }
        });

        L7_First_Sku.setToolTipText("");
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

        L7_First_Desc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        L7_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L7_First_Desc.setFocusable(false);

        L7_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is defective", "Not as described", "Not what I ordered", "Other" }));
        L7_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_Reason_DropDownKeyPressed(evt);
            }
        });

        L7_Desc_Damage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L7_Desc_DamageFocusGained(evt);
            }
        });
        L7_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_Desc_DamageKeyPressed(evt);
            }
        });

        L7_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L7_Orig_Retail.setFocusable(false);

        L7_New_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L7_New_SkuFocusLost(evt);
            }
        });
        L7_New_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_New_SkuKeyPressed(evt);
            }
        });

        L7_New_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L7_New_Desc.setFocusable(false);

        L7_Qty_In.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L7_Qty_InKeyPressed(evt);
            }
        });

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

        L8_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_Qty_OutKeyPressed(evt);
            }
        });

        L8_First_Sku.setToolTipText("");
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

        L8_First_Desc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        L8_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L8_First_Desc.setFocusable(false);

        L8_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is defective", "Not as described", "Not what I ordered", "Other" }));
        L8_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_Reason_DropDownKeyPressed(evt);
            }
        });

        L8_Desc_Damage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L8_Desc_DamageFocusGained(evt);
            }
        });
        L8_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_Desc_DamageKeyPressed(evt);
            }
        });

        L8_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L8_Orig_Retail.setFocusable(false);

        L8_New_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L8_New_SkuFocusLost(evt);
            }
        });
        L8_New_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_New_SkuKeyPressed(evt);
            }
        });

        L8_New_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L8_New_Desc.setFocusable(false);

        L8_Qty_In.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L8_Qty_InKeyPressed(evt);
            }
        });

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

        L9_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_Qty_OutKeyPressed(evt);
            }
        });

        L9_First_Sku.setToolTipText("");
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

        L9_First_Desc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        L9_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L9_First_Desc.setFocusable(false);

        L9_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is defective", "Not as described", "Not what I ordered", "Other" }));
        L9_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_Reason_DropDownKeyPressed(evt);
            }
        });

        L9_Desc_Damage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L9_Desc_DamageFocusGained(evt);
            }
        });
        L9_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_Desc_DamageKeyPressed(evt);
            }
        });

        L9_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L9_Orig_Retail.setFocusable(false);

        L9_New_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L9_New_SkuFocusLost(evt);
            }
        });
        L9_New_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_New_SkuKeyPressed(evt);
            }
        });

        L9_New_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L9_New_Desc.setFocusable(false);

        L9_Qty_In.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L9_Qty_InKeyPressed(evt);
            }
        });

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

        L10_Qty_Out.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_Qty_OutKeyPressed(evt);
            }
        });

        L10_First_Sku.setToolTipText("");
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

        L10_First_Desc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        L10_First_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L10_First_Desc.setFocusable(false);

        L10_Reason_DropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item is defective", "Not as described", "Not what I ordered", "Other" }));
        L10_Reason_DropDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_Reason_DropDownKeyPressed(evt);
            }
        });

        L10_Desc_Damage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                L10_Desc_DamageFocusGained(evt);
            }
        });
        L10_Desc_Damage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_Desc_DamageKeyPressed(evt);
            }
        });

        L10_Orig_Retail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L10_Orig_Retail.setFocusable(false);

        L10_New_Sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                L10_New_SkuFocusLost(evt);
            }
        });
        L10_New_Sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_New_SkuKeyPressed(evt);
            }
        });

        L10_Qty_In.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                L10_Qty_InKeyPressed(evt);
            }
        });

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

        L1.setText("1");
        L1.setFocusable(false);

        L2.setText("2");
        L2.setFocusable(false);

        L3.setText("3");

        L4.setText("4");

        L5.setText("5");

        L6.setText("6");

        L7.setText("7");

        L8.setText("8");

        L9.setText("9");

        L10.setText("10");

        subBtn.setBackground(new java.awt.Color(0, 255, 0));
        subBtn.setText("Submit");
        subBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBtnActionPerformed(evt);
            }
        });

        Close_Btn.setBackground(new java.awt.Color(255, 0, 0));
        Close_Btn.setText("Close");
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

        jTextField191.setEditable(false);
        jTextField191.setBackground(new java.awt.Color(255, 255, 255));
        jTextField191.setForeground(new java.awt.Color(0, 204, 0));
        jTextField191.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField191.setText("Done/Save");
        jTextField191.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField191.setFocusable(false);

        L10_New_Desc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L10_New_Desc.setFocusable(false);

        jTextField128.setEditable(false);
        jTextField128.setBackground(new java.awt.Color(255, 255, 255));
        jTextField128.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField128.setText("Attr");
        jTextField128.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField128.setFocusable(false);

        jTextField129.setEditable(false);
        jTextField129.setBackground(new java.awt.Color(255, 255, 255));
        jTextField129.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField129.setText("Size");
        jTextField129.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField129.setFocusable(false);

        L1_First_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L1_First_Attr.setFocusable(false);

        L2_First_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L2_First_Attr.setFocusable(false);

        L3_First_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L3_First_Attr.setFocusable(false);

        L4_First_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L4_First_Attr.setFocusable(false);

        L5_First_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L5_First_Attr.setFocusable(false);

        L6_First_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L6_First_Attr.setFocusable(false);

        L7_First_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L7_First_Attr.setFocusable(false);

        L8_First_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L8_First_Attr.setFocusable(false);

        L9_First_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L9_First_Attr.setFocusable(false);

        L10_First_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L10_First_Attr.setFocusable(false);

        L1_First_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L1_First_Size.setFocusable(false);

        L2_First_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L2_First_Size.setFocusable(false);

        L3_First_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L3_First_Size.setFocusable(false);

        L4_First_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L4_First_Size.setFocusable(false);

        L5_First_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L5_First_Size.setFocusable(false);

        L7_First_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L7_First_Size.setFocusable(false);

        L8_First_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L8_First_Size.setFocusable(false);

        L9_First_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L9_First_Size.setFocusable(false);

        L10_First_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L10_First_Size.setFocusable(false);

        L6_First_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L6_First_Size.setFocusable(false);

        Attr2.setEditable(false);
        Attr2.setBackground(new java.awt.Color(255, 255, 255));
        Attr2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Attr2.setText("Attr");
        Attr2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Attr2.setFocusable(false);

        jTextField131.setEditable(false);
        jTextField131.setBackground(new java.awt.Color(255, 255, 255));
        jTextField131.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField131.setText("Size");
        jTextField131.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextField131.setFocusable(false);

        L1_New_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L1_New_Attr.setFocusable(false);

        L2_New_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L2_New_Attr.setFocusable(false);

        L3_New_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L3_New_Attr.setFocusable(false);

        L4_New_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L4_New_Attr.setFocusable(false);

        L5_New_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L5_New_Attr.setFocusable(false);

        L6_New_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L6_New_Attr.setFocusable(false);

        L7_New_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L7_New_Attr.setFocusable(false);

        L8_New_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L8_New_Attr.setFocusable(false);

        L9_New_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L9_New_Attr.setFocusable(false);

        L10_New_Attr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L10_New_Attr.setFocusable(false);

        L1_New_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L1_New_Size.setFocusable(false);

        L2_New_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L2_New_Size.setFocusable(false);

        L3_New_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L3_New_Size.setFocusable(false);

        L4_New_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L4_New_Size.setFocusable(false);

        L5_New_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L5_New_Size.setFocusable(false);

        L6_New_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L6_New_Size.setFocusable(false);

        L7_New_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L7_New_Size.setFocusable(false);

        L8_New_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L8_New_Size.setFocusable(false);

        L9_New_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L9_New_Size.setFocusable(false);

        L10_New_Size.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        L10_New_Size.setFocusable(false);

        RdyforExportBtn.setBackground(new java.awt.Color(0, 0, 255));
        RdyforExportBtn.setForeground(new java.awt.Color(255, 255, 255));
        RdyforExportBtn.setText("Ready for Export");
        RdyforExportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RdyforExportBtnActionPerformed(evt);
            }
        });

        Form_Name_Tag.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Form_Name_Tag.setText("Form Name:");

        Test_Label.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        Test_Label.setText("(Test Environment)");

        VersionLabel.setText("Version 1.110");

        CLine_Button.setBackground(new java.awt.Color(204, 0, 0));
        CLine_Button.setText("Clear Line");
        CLine_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLine_ButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Line");

        Delete_Form_Btn.setBackground(new java.awt.Color(204, 0, 204));
        Delete_Form_Btn.setText("Delete Form");
        Delete_Form_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_Form_BtnActionPerformed(evt);
            }
        });

        Date_Label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Date_Label.setForeground(new java.awt.Color(0, 204, 204));
        Date_Label.setText("4-30-2015");

        Form_Name_Label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Form_Name_Label.setForeground(new java.awt.Color(51, 204, 0));
        Form_Name_Label.setText(" ias_999_04_30_14_12_30_45");

        Store_Letter_Label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Store_Letter_Label.setForeground(new java.awt.Color(255, 0, 0));
        Store_Letter_Label.setText("PAC");

        Store_Num_Label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Store_Num_Label.setForeground(new java.awt.Color(0, 0, 204));
        Store_Num_Label.setText("52");

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("*Desc is always required*");

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("**Make sure to click on done/save before submitting forms**");

        IC_Print_Btn.setBackground(new java.awt.Color(255, 255, 0));
        IC_Print_Btn.setText("Print");
        IC_Print_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IC_Print_BtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(L1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(L1_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L1_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L1_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L1_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L1_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L1_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L1_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L1_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L1_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L1_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L1_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L1_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L1_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(L1_Done_ChkBox)
                        .addGap(6, 6, 6)
                        .addComponent(L1_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(L2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(L2_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L2_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L2_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L2_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L2_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L2_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L2_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L2_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L2_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L2_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L2_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L2_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L2_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(L2_Done_ChkBox)
                        .addGap(6, 6, 6)
                        .addComponent(L2_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(L3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(L3_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L3_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L3_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L3_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L3_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L3_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L3_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L3_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L3_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L3_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L3_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L3_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L3_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(L3_Done_ChkBox)
                        .addGap(6, 6, 6)
                        .addComponent(L3_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(L4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(L4_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L4_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L4_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L4_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L4_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L4_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L4_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L4_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L4_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L4_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L4_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L4_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L4_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(L4_Done_ChkBox)
                        .addGap(6, 6, 6)
                        .addComponent(L4_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(L5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(L5_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L5_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L5_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L5_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L5_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L5_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L5_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L5_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L5_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L5_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L5_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L5_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L5_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(L5_Done_ChkBox)
                        .addGap(6, 6, 6)
                        .addComponent(L5_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(L6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(L6_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L6_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L6_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L6_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L6_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L6_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L6_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L6_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L6_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L6_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L6_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L6_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L6_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(L6_Done_ChkBox)
                        .addGap(6, 6, 6)
                        .addComponent(L6_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(L7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(L7_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L7_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L7_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L7_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L7_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L7_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L7_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L7_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L7_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L7_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L7_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L7_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(L7_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(L7_Done_ChkBox)
                        .addGap(6, 6, 6)
                        .addComponent(L7_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(L9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(L9_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L9_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L9_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L9_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L9_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L9_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L9_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L9_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L9_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L9_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L9_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L9_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L9_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(L8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(L8_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(L8_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(L8_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L8_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(L8_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(L8_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L8_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L8_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(L8_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(L8_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(L8_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(L8_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(L8_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(L8_Done_ChkBox)
                                .addGap(6, 6, 6)
                                .addComponent(L8_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jTextField118, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField119, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField121, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(jLabel5)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField128, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField129, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField123, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField122, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextField127, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField124, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(Test_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(VersionLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(IC_Print_Btn)
                                .addGap(35, 35, 35)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField125, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Attr2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField131, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField126, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField191, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(Delete_Form_Btn))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(Store_Location_Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Store_Letter_Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Store_Logged_In_Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Store_Num_Label)
                        .addGap(43, 43, 43)
                        .addComponent(tdteLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Date_Label)
                        .addGap(29, 29, 29)
                        .addComponent(Form_Name_Tag)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Form_Name_Label)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(L10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(L10_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L10_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L10_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L10_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L10_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L10_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CLine_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CLine_Button)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L10_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(subBtn)
                        .addGap(33, 33, 33)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(Close_Btn)
                        .addGap(42, 42, 42)
                        .addComponent(ApproveBtn)
                        .addGap(54, 54, 54)
                        .addComponent(RdyforExportBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(L10_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L10_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L10_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L10_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L10_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L10_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(L10_Done_ChkBox))
                            .addComponent(L9_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(L9_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L10_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(552, 552, 552))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Store_Location_Label)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Store_Logged_In_Label)
                        .addComponent(Store_Letter_Label)
                        .addComponent(Store_Num_Label))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tdteLabel)
                        .addComponent(Form_Name_Tag)
                        .addComponent(Date_Label)
                        .addComponent(Form_Name_Label)))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Test_Label)
                    .addComponent(Delete_Form_Btn)
                    .addComponent(VersionLabel)
                    .addComponent(IC_Print_Btn))
                .addGap(8, 8, 8)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField119, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField118, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField121, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField128, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField129, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField123, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField122, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField124, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField127, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField125, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Attr2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField131, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField126, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField191, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L1)
                    .addComponent(L1_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L1_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L1_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L1_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L1_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L1_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L1_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L1_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(L1_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L1_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L1_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L1_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L1_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L1_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L1_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L2)
                    .addComponent(L2_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(L2_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L2_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L2_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L2_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L2_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L2_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L2_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L3_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L3_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(L3_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L3_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L3_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L3_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L3_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L3_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(L3_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(L3_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L3_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(L3_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L3_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L3_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L3_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L4)
                    .addComponent(L4_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L4_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L4_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L4_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L4_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L4_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L4_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L4_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(L4_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L4_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(L4_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L4_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(L4_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L4_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L4_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L5)
                    .addComponent(L5_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L5_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L5_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L5_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L5_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L5_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L5_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L5_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(L5_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L5_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L6)
                    .addComponent(L6_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L6_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L6_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L6_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L6_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L6_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L6_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L6_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(L6_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L6_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(L7))
                    .addComponent(L7_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L7_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L7_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L7_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L7_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L7_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(L7_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(L7_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(L7_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L7_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L7_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L7_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L7_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L7_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L7_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L8)
                    .addComponent(L8_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L8_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L8_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L8_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L8_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L8_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L8_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L8_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(L8_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L8_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L8_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L8_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L8_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L8_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(L8_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(L9)
                                .addComponent(L9_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(L9_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(L9_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(L10_First_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(L10)
                                        .addComponent(L10_Qty_Out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(L10_First_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(L10_First_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(L10_First_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(L10_Orig_Retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(L10_Reason_DropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(L10_Desc_Damage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(L10_New_Sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(L10_New_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(L10_New_Attr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(L10_New_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(L10_Qty_In, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(L10_Timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(9, 9, 9)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(CLine_Button)
                                    .addComponent(CLine_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(subBtn)
                                    .addComponent(Close_Btn)
                                    .addComponent(ApproveBtn)
                                    .addComponent(RdyforExportBtn)))
                            .addComponent(L10_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(L9_Done_ChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(119, Short.MAX_VALUE))
        );

        L1_First_Size.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void clsFrm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_clsFrm
        switch (GetForms_InvAdj.usrType) {
        // Store user is default
        default:
            Toolkit.getDefaultToolkit().beep();
           // if (L1_Done_ChkBox.isSelected()==true || L2_Done_ChkBox.isSelected()==true || L3_Done_ChkBox.isSelected()==true || L4_Done_ChkBox.isSelected()==true || L5_Done_ChkBox.isSelected()==true
           //     || L6_Done_ChkBox.isSelected()==true || L7_Done_ChkBox.isSelected()==true || L8_Done_ChkBox.isSelected()==true || L9_Done_ChkBox.isSelected()==true || L10_Done_ChkBox.isSelected()==true){
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
                IASdao.FormApproved();
                PrintForms.formType = ("IAS");
                PrintForms.main(args);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
                JOptionPane.showMessageDialog(frame, "Form Has Been Submitted for Approval");        
                InvAdj.Rpnt();        
                this.dispose();                
         // } else {
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("IAS Window closed");
            }
           // } else {
           //     JOptionPane.showMessageDialog(frame, "You must input at least one sku and check done/save before submitting \nYou cannot close without submitting first", "Submit Only Error", JOptionPane.ERROR_MESSAGE);  
           // }
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
    // ** Not used because DL cannot access IAS forms because they are not required to have DL approval and are sent directly to IC users for approval **
    // ** The button is set to not visible in code at the top of this class **
    private void ApproveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApproveBtnActionPerformed
    int response = JOptionPane.showConfirmDialog(null, "Do you want to Approve?", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");
    } else if (response == JOptionPane.YES_OPTION) {
      System.out.println("Yes button clicked");	
        try {
                // changes status to approved in database
                IASdao.FormApproved();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(frame, "Form Has Been Approved & Submitted" + '\n' + "to Inventory Control");
            // clears form name from admin panel list
            InvAdj_Admin.Rpnt();
            // closes form
            this.dispose();
    } else if (response == JOptionPane.CLOSED_OPTION) {
      System.out.println("JOptionPane closed");
    }
    }//GEN-LAST:event_ApproveBtnActionPerformed

    private void Close_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Close_BtnActionPerformed
        InvAdj.Rpnt();
        System.out.println("IAS Closed");
        this.dispose();
    }//GEN-LAST:event_Close_BtnActionPerformed
    // ** Submits the form and displays a JTable that contains the information that was submitted and allows the user to print **
    // ** Once "Print" is pressed it automatically prints to the local printer ** 
    private void subBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBtnActionPerformed
    // if (L1_Done_ChkBox.isSelected()==true || L2_Done_ChkBox.isSelected()==true || L3_Done_ChkBox.isSelected()==true || L4_Done_ChkBox.isSelected()==true || L5_Done_ChkBox.isSelected()==true
    //    || L6_Done_ChkBox.isSelected()==true || L7_Done_ChkBox.isSelected()==true || L8_Done_ChkBox.isSelected()==true || L9_Done_ChkBox.isSelected()==true || L10_Done_ChkBox.isSelected()==true){
        int response = JOptionPane.showConfirmDialog(null, "Do you want to Submit?", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");      
    } else if (response == JOptionPane.YES_OPTION) {
      System.out.println("Yes button clicked");
      String[] args = null;
        try {
            // changes status to pending
            IASdao.FormApproved();
            PrintForms.formType = ("IAS");
            PrintForms.main(args);
            System.out.println("IAS Form Approved");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(frame, "Form Has Been Submitted for Approval");
        IASdao.nwFrm = "yes";
      //  clearIfNew();
        InvAdj.Rpnt();
        this.dispose();
    } else if (response == JOptionPane.CLOSED_OPTION) {
      System.out.println("JOptionPane closed");
    }
   // } else {
   //     JOptionPane.showMessageDialog(frame, "You must input at least one sku and check done/save before submitting \nYou cannot close without submitting", "Submit Only Error", JOptionPane.ERROR_MESSAGE);  
   // }
    }//GEN-LAST:event_subBtnActionPerformed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L2_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Done_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {    
        L2_Done_ChkBox.setSelected(true);
            rChkLn2();
            if (L2_Done_ChkBox.isSelected() == true) {
                String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L2_First_Sku.getText();
                IASdao.lnFlg = 2;
                // ** This clears all the data in the database and clears all the fields in the application when the first field is empty **
                /*
                
                if (L2_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine2();
                    L2_Qty_Out.setText("");
                    L2_First_Desc.setText("");
                    L2_First_Attr.setText("");
                    L2_First_Size.setText("");
                    L2_Orig_Retail.setText("");
                    L2_Reason_DropDown.setSelectedItem("");
                    L2_Desc_Damage.setText("");
                    L2_New_Sku.setText("");
                    L2_New_Desc.setText("");
                    L2_New_Attr.setText("");
                    L2_New_Size.setText("");
                    L2_Qty_In.setText("");
                    L2_Done_ChkBox.setSelected(false);
                    L2_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    // ** Calls main method which is also the first sku check to database **
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                // ** this checks first field for only numbers and no more than 6 **
                if ((L2_First_Sku.getText().matches("[0-9]+") && L2_First_Sku.getText().length() < 7) || L2_First_Sku.getText().equals("0") || L2_First_Sku.getText().equals("")) {
                    L2_First_Sku.setBackground(Color.white);
                
                // ** this checks sku input in first field in database from IASdao class **
                if (IASdao.skuRslt.equals("good") || (L2_First_Sku.getText().equals("") || (L2_First_Sku.getText().equals("0")))) {
                    L2_First_Sku.setBackground(Color.WHITE);
                    L2_First_Desc.setText(IASdao.firstSkuDesc);
                    L2_First_Attr.setText(IASdao.firstSkuAttr);
                    L2_First_Size.setText(IASdao.firstSkuSize);
                    L2_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L2_First_Sku.getText().equals("") || (L2_First_Sku.getText().equals("0"))) {
                    L2_First_Sku.setText("0");
                    L2_Qty_Out.setText("0");
                    L2_First_Desc.setText("");
                    L2_First_Attr.setText("");
                    L2_First_Size.setText("");
                    L2_Orig_Retail.setText("0.00");
                    L2_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL2_Cost();
                    IASdao.firstDCS = getfDcs2();
                    IASdao.firstSkuVendor = getfVen2();
                    IASdao.firstSkuVPNum = getfVpn2();
                } 
                    
                // ** Makes sure Qty is only number 1-9 and only one digit long ** 
                if (L2_Qty_Out.getText().matches("[1-9]+") && L2_Qty_Out.getText().length() < 2 || L2_Qty_Out.getText().matches("0") && L2_First_Sku.getText().matches("0")) {                      
                    L2_Qty_Out.setBackground(Color.WHITE);
                    
                // ** Validates the user selects value that is not default **
                if (!L2_Reason_DropDown.getSelectedItem().equals("")&& !L2_First_Sku.getText().equals("0") || L2_Reason_DropDown.getSelectedItem().equals("") && L2_First_Sku.getText().matches("0")) {                               
                                        
                // ** Validates that the field contains only letters **
                if ((L2_Desc_Damage.getText().matches("[a-zA-Z ]+"))) {
                    L2_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L2_New_Sku.getText();
                try {
                //  ** Validates second sku field data **
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    // ** validates the field to only allow numbers 0-9 with a max length of 6 digits ** && ** Allows blank or '0' to be input to skip input because it is not always necessary **
                    if (IASdao.skuRslt2.equals("good") && L2_New_Sku.getText().matches("[0-9]+") && L2_New_Sku.getText().length() < 7 || (L2_New_Sku.getText().matches("") || L2_New_Sku.getText().matches("0"))) {
                        L2_New_Sku.setBackground(Color.WHITE);
                        L2_New_Desc.setText(IASdao.secondSkuDesc);
                        L2_New_Attr.setText(IASdao.secondSkuAttr);
                        L2_New_Size.setText(IASdao.secondSkuSize);
                    // ** Fills in or clears data if sku field is empty or contains '0' value **
                    if (L2_New_Sku.getText().equals("") || L2_New_Sku.getText().equals("0")) {
                        L2_New_Sku.setBackground(Color.WHITE);
                        L2_New_Sku.setText("0");
                        L2_New_Desc.setText("");
                        L2_New_Attr.setText("");
                        L2_New_Size.setText("");
                        L2_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL2_Cost();
                        IASdao.secondDCS = getsDcs2();
                        IASdao.secondSkuVendor = getsVen2();
                        IASdao.secondSkuVPNum = getsVpn2();
                    }
                    
                    // ** Validates Qty to only be numbers 1-9 and max length of 1 digit ** && ** Allows only '0' in the field ** 
                if ((L2_Qty_In.getText().matches("[1-9]+") && L2_Qty_In.getText().length() < 2) || (L2_Qty_In.getText().matches("0")) && L2_New_Sku.getText().matches("0")) {
                    L2_Qty_In.setBackground(Color.WHITE);
                    
                    // ** Validates that the new sku is not the same as the first sku **
                if (!L2_New_Sku.getText().equals(L2_First_Sku.getText())) {
                    L2_New_Sku.setBackground(Color.WHITE);
                    
                if ((!L2_First_Sku.getText().equals("0") && L2_New_Sku.getText().equals("0")) || (L2_First_Sku.getText().equals("0") && !L2_New_Sku.getText().equals("0"))
                    || (!L2_First_Sku.getText().equals("0") && !L2_New_Sku.getText().equals("0"))) {
                    
                    // fills the fields on the screen with data from the database
                    IASdao.Field1 = L2_First_Sku.getText();
                    IASdao.Field2 = L2_Qty_Out.getText();
                    IASdao.Field3 = L2_First_Desc.getText();
                    IASdao.Field4 = L2_First_Attr.getText();
                    IASdao.Field5 = L2_First_Size.getText();
                    IASdao.Field6 = L2_Orig_Retail.getText();
                    IASdao.Field7 = L2_Reason_DropDown.getSelectedItem().toString();
                    IASdao.Field8 = L2_Desc_Damage.getText();
                    IASdao.Field9 = L2_New_Sku.getText();
                    IASdao.Field10 = L2_New_Desc.getText();
                    IASdao.Field11 = L2_New_Attr.getText();
                    IASdao.Field12 = L2_New_Size.getText();
                    IASdao.Field13 = L2_Qty_In.getText();
                    IASdao.rline = L2.getText().replace(")", "");
                    
                    IASdao.CmtLn(); 
                    
                    jPanel6.scrollRectToVisible(L2.getBounds(null));
                    
                    // ** Below are all the corresponding error messages to the above if statements **
                    } else {
                        JOptionPane.showMessageDialog(frame, "You must input at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L2_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L2_Done_ChkBox.setSelected(false);
                        L2_New_Sku.requestFocus();
                        L2_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L2_Done_ChkBox.setSelected(false);
                        L2_Qty_In.requestFocus();
                        L2_Qty_In.setBackground(Color.yellow);
                    }                    
                    } else {
                        L2_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L2_Done_ChkBox.setSelected(false);
                        L2_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L2_Done_ChkBox.setSelected(false);
                        L2_Desc_Damage.requestFocus();
                        L2_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n\n"
                                + "NOTE: This is only available for use with the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L2_Done_ChkBox.setSelected(false);
                        L2_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L2_Done_ChkBox.setSelected(false);
                        L2_Qty_Out.requestFocus();
                        L2_Qty_Out.setBackground(Color.yellow);
                    } 
                    } else {
                        L2_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L2_Done_ChkBox.setSelected(false);
                        L2_First_Sku.requestFocus();
                    }                  
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L2_Done_ChkBox.setSelected(false);
                    L2_First_Sku.requestFocus();
                    L2_First_Sku.setBackground(Color.yellow);
                }
              //  }
            } else {
                // ** allows all fields enabled if the checkbox does not equal true **
                L2_First_Sku.setEnabled(true);
                L2_Qty_Out.setEnabled(true);
                L2_First_Desc.setEnabled(true);
                L2_First_Attr.setEnabled(true);
                L2_First_Size.setEnabled(true);
                L2_Orig_Retail.setEnabled(true);
                L2_Reason_DropDown.setEnabled(true);
                L2_Desc_Damage.setEnabled(true);
                L2_New_Sku.setEnabled(true);
                L2_New_Desc.setEnabled(true);
                L2_New_Attr.setEnabled(true);
                L2_New_Size.setEnabled(true);
                L2_Qty_In.setEnabled(true);
            }
            // ** Focuses cursor to the next line **
            manager.focusNextComponent();
        }   
    }//GEN-LAST:event_L2_Done_ChkBoxKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L2_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L2_Done_ChkBoxActionPerformed
                rChkLn2();
                if (L2_Done_ChkBox.isSelected() == true) {
                String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L2_First_Sku.getText();
                IASdao.lnFlg = 2;
                // if first sku field is empty it will clear all data from all fields on that line
                /*
                if (L2_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine2();
                    L2_Qty_Out.setText("");
                    L2_First_Desc.setText("");
                    L2_First_Attr.setText("");
                    L2_First_Size.setText("");
                    L2_Orig_Retail.setText("");
                    L2_Reason_DropDown.setSelectedItem("");
                    L2_Desc_Damage.setText("");
                    L2_New_Sku.setText("");
                    L2_New_Desc.setText("");
                    L2_New_Attr.setText("");
                    L2_New_Size.setText("");
                    L2_Qty_In.setText("");
                    L2_Done_ChkBox.setSelected(false);
                    L2_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                // validates the first sku and ensures input is only numerical and has a max length of six
                if ((L2_First_Sku.getText().matches("[0-9]+") && L2_First_Sku.getText().length() < 7) || (L2_First_Sku.getText().equals("0") || L2_First_Sku.getText().equals(""))) {
                
                // validates the first sku in the database
                if (IASdao.skuRslt.equals("good") || (L2_First_Sku.getText().equals("") || (L2_First_Sku.getText().equals("0")))) {
                    L2_First_Sku.setBackground(Color.WHITE);
                    L2_First_Desc.setText(IASdao.firstSkuDesc);
                    L2_First_Attr.setText(IASdao.firstSkuAttr);
                    L2_First_Size.setText(IASdao.firstSkuSize);
                    L2_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L2_First_Sku.getText().equals("") || (L2_First_Sku.getText().equals("0"))) {
                    L2_First_Sku.setText("0");
                    L2_Qty_Out.setText("0");
                    L2_First_Desc.setText("");
                    L2_First_Attr.setText("");
                    L2_First_Size.setText("");
                    L2_Orig_Retail.setText("0.00");
                    L2_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL2_Cost();
                    IASdao.firstDCS = getfDcs2();
                    IASdao.firstSkuVendor = getfVen2();
                    IASdao.firstSkuVPNum = getfVpn2();
                }
                    
                // validates the first sku and ensures input is only numerical and has a max length of one
                if (L2_Qty_Out.getText().matches("[1-9]+") && L2_Qty_Out.getText().length() < 2 || L2_Qty_Out.getText().matches("0") && L2_First_Sku.getText().matches("0")) {
                    L2_Qty_Out.setBackground(Color.WHITE);
                    
                // validates the reason for return is not left the default value && !L2_First_Sku.getText().equals("0") || L2_Reason_DropDown.getSelectedItem().equals("") && L2_First_Sku.getText().matches("0")) {
                
                if (!L2_Reason_DropDown.getSelectedItem().equals("")&& !L2_First_Sku.getText().equals("0") || L2_Reason_DropDown.getSelectedItem().equals("") && L2_First_Sku.getText().matches("0")) {
                        
                // validates this field contains only alpha characters and spaces
                if (L2_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L2_Desc_Damage.setBackground(Color.WHITE);
                    
                // validates second sku   
                IASdao.secondSkuInput = L2_New_Sku.getText();
                try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if (IASdao.skuRslt2.equals("good") && L2_New_Sku.getText().matches("[0-9]+") && L2_New_Sku.getText().length() < 7 || (L2_New_Sku.getText().matches("") || L2_New_Sku.getText().matches("0"))) {
                        L2_New_Sku.setBackground(Color.WHITE);
                        L2_New_Desc.setText(IASdao.secondSkuDesc);
                        L2_New_Attr.setText(IASdao.secondSkuAttr);
                        L2_New_Size.setText(IASdao.secondSkuSize);
                         
                    if (L2_New_Sku.getText().equals("") || L2_New_Sku.getText().equals("0")) {                                                
                        L2_New_Sku.setText("0");
                        L2_New_Desc.setText("");
                        L2_New_Attr.setText("");
                        L2_New_Size.setText("");
                        L2_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL2_Cost();
                        IASdao.secondDCS = getsDcs2();
                        IASdao.secondSkuVendor = getsVen2();
                        IASdao.secondSkuVPNum = getsVpn2();
                    }
                
                if ((L2_Qty_In.getText().matches("[1-9]+") && L2_Qty_In.getText().length() < 2) || (L2_Qty_In.getText().matches("0"))&& L2_New_Sku.getText().matches("0")) {
                    L2_Qty_In.setBackground(Color.WHITE);
                    
                if (!L2_New_Sku.getText().equals(L2_First_Sku.getText())) {
                    L2_New_Sku.setBackground(Color.WHITE);
                
                if ((!L2_First_Sku.getText().equals("0") && L2_New_Sku.getText().equals("0")) || (L2_First_Sku.getText().equals("0") && !L2_New_Sku.getText().equals("0"))
                    || (!L2_First_Sku.getText().equals("0") && !L2_New_Sku.getText().equals("0"))) {
                    
                    IASdao.Field1 = L2_First_Sku.getText();
                    IASdao.Field2 = L2_Qty_Out.getText();
                    IASdao.Field3 = L2_First_Desc.getText();
                    IASdao.Field4 = L2_First_Attr.getText();
                    IASdao.Field5 = L2_First_Size.getText();
                    IASdao.Field6 = L2_Orig_Retail.getText();
                    IASdao.Field7 = L2_Reason_DropDown.getSelectedItem().toString();
                    IASdao.Field8 = L2_Desc_Damage.getText();                    
                    IASdao.Field9 = L2_New_Sku.getText();
                    IASdao.Field10 = L2_New_Desc.getText();
                    IASdao.Field11 = L2_New_Attr.getText();
                    IASdao.Field12 = L2_New_Size.getText();
                    IASdao.Field13 = L2_Qty_In.getText();
                    IASdao.rline = L2.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L2.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "You must input at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L2_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L2_Done_ChkBox.setSelected(false);
                        L2_New_Sku.requestFocus();
                        L2_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L2_Done_ChkBox.setSelected(false);
                        L2_Qty_In.requestFocus();
                        L2_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L2_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L2_Done_ChkBox.setSelected(false);
                        L2_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L2_Done_ChkBox.setSelected(false);
                        L2_Desc_Damage.requestFocus();
                        L2_Desc_Damage.setBackground(Color.yellow);
                    }                 
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n\n" 
                            + "NOTE: This is only available for use with the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L2_Done_ChkBox.setSelected(false);
                        L2_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L2_Done_ChkBox.setSelected(false);
                        L2_Qty_Out.requestFocus();
                        L2_Qty_Out.setBackground(Color.yellow);
                    } 
                    } else {
                        L2_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L2_Done_ChkBox.setSelected(false);
                        L2_First_Sku.requestFocus();
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
                    L2_First_Attr.setEnabled(true);
                    L2_First_Size.setEnabled(true);
                    L2_Orig_Retail.setEnabled(true);
                    L2_Reason_DropDown.setEnabled(true);
                    L2_Desc_Damage.setEnabled(true);
                    L2_New_Sku.setEnabled(true);
                    L2_New_Desc.setEnabled(true);
                    L2_New_Attr.setEnabled(true);
                    L2_New_Size.setEnabled(true);
                    L2_Qty_In.setEnabled(true);
                } 
    }//GEN-LAST:event_L2_Done_ChkBoxActionPerformed

    // sku validation for the first sku input field
    private void L2_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_First_SkuKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.firstSkuInput = L2_First_Sku.getText();
            if (L2_First_Sku.getText().matches("[0-9]+") && L2_First_Sku.getText().length() < 7 || (L2_First_Sku.getText().equals("") || (L2_First_Sku.getText().equals("0")))) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L2_First_Sku.setBackground(Color.WHITE);
                L2_First_Desc.setText(IASdao.firstSkuDesc);
                L2_First_Attr.setText(IASdao.firstSkuAttr);
                L2_First_Size.setText(IASdao.firstSkuSize);
                L2_Orig_Retail.setText(IASdao.firstSkuPr);
                L2_Qty_Out.requestFocus();
            } else if (L2_First_Sku.getText().equals("") || (L2_First_Sku.getText().equals("0"))) {
                L2_First_Sku.setBackground(Color.WHITE);
                L2_First_Sku.setText("0");
                L2_Qty_Out.setText("0");
                L2_First_Desc.setText("");
                L2_First_Attr.setText("");
                L2_First_Size.setText("");
                L2_Orig_Retail.setText("0.00");
                L2_Reason_DropDown.setSelectedItem("");
                L2_Desc_Damage.requestFocus();
                // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
              //  IASdao.firstSkuCost = getL2_Cost();
            } else {
                L2_First_Sku.setBackground(Color.yellow);
                L2_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L2_First_Sku.requestFocus();
                L2_First_Sku.setBackground(Color.yellow);
            }
        }
    }//GEN-LAST:event_L2_First_SkuKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L1_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Done_ChkBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {     
        L1_Done_ChkBox.setSelected(true);  
            rChkLn1();
            if (L1_Done_ChkBox.isSelected() == true) {
            String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L1_First_Sku.getText();
                IASdao.lnFlg = 1;
                /*
                if (L1_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine1();
                    L1_Qty_Out.setText("");
                    L1_First_Desc.setText("");
                    L1_First_Attr.setText("");
                    L1_First_Size.setText("");
                    L1_Orig_Retail.setText("");
                    L1_Reason_DropDown.setSelectedItem("");
                    L1_Desc_Damage.setText("");
                    L1_New_Sku.setText("");
                    L1_New_Desc.setText("");
                    L1_New_Attr.setText("");
                    L1_New_Size.setText("");
                    L1_Qty_In.setText("");
                    L1_Done_ChkBox.setSelected(false);
                    L1_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (L1_First_Sku.getText().matches("[0-9]+") && L1_First_Sku.getText().length() < 7 || (L1_First_Sku.getText().equals("0") || L1_First_Sku.getText().equals(""))) {
                    L1_First_Sku.setBackground(Color.WHITE);
                
                if (IASdao.skuRslt.equals("good") || (L1_First_Sku.getText().equals("") || (L1_First_Sku.getText().equals("0")))) {
                    L1_First_Sku.setBackground(Color.WHITE);
                    L1_First_Desc.setText(IASdao.firstSkuDesc);
                    L1_First_Attr.setText(IASdao.firstSkuAttr);
                    L1_First_Size.setText(IASdao.firstSkuSize);
                    L1_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L1_First_Sku.getText().equals("") || (L1_First_Sku.getText().equals("0"))) {
                    L1_First_Sku.setText("0");
                    L1_Qty_Out.setText("0");
                    L1_First_Desc.setText("");
                    L1_First_Attr.setText("");
                    L1_First_Size.setText("");
                    L1_Orig_Retail.setText("0");
                    L1_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL1_Cost();
                    IASdao.firstDCS = getfDcs1();
                    IASdao.firstSkuVendor = getfVen1();
                    IASdao.firstSkuVPNum = getfVpn1();
                }
                    
                if (L1_Qty_Out.getText().matches("[1-9]+") && L1_Qty_Out.getText().length() < 2 || L1_Qty_Out.getText().matches("0") && L1_First_Sku.getText().matches("0")) {                      
                    L1_Qty_Out.setBackground(Color.WHITE);
               
                    
                if (!L1_Reason_DropDown.getSelectedItem().equals("") && !L1_First_Sku.getText().equals("0") || L1_Reason_DropDown.getSelectedItem().equals("") && L1_First_Sku.getText().matches("0")) {
                    
                if  (L1_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L1_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L1_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if ((IASdao.skuRslt2.equals("good") && L1_New_Sku.getText().matches("[0-9]+") && L1_New_Sku.getText().length() < 7) || (L1_New_Sku.getText().matches("") || L1_New_Sku.getText().matches("0"))) {
                        L1_New_Sku.setBackground(Color.WHITE);
                        L1_New_Desc.setText(IASdao.secondSkuDesc);
                        L1_New_Attr.setText(IASdao.secondSkuAttr);
                        L1_New_Size.setText(IASdao.secondSkuSize); 
                    if (L1_New_Sku.getText().equals("") || L1_New_Sku.getText().equals("0")) {                                               
                        L1_New_Sku.setText("0");
                        L1_New_Desc.setText("");
                        L1_New_Attr.setText("");
                        L1_New_Size.setText("");
                        L1_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL1_Cost();
                        IASdao.secondDCS = getsDcs1();
                        IASdao.secondSkuVendor = getsVen1();
                        IASdao.secondSkuVPNum = getsVpn1();
                    }
                
                if ((L1_Qty_In.getText().matches("[1-9]+") && L1_Qty_In.getText().length() < 2) || L1_Qty_In.getText().matches("0") && L1_New_Sku.getText().matches("0")) {
                   L1_Qty_In.setBackground(Color.WHITE);
                    
                if (!L1_New_Sku.getText().equals(L1_First_Sku.getText())) {
                    L1_New_Sku.setBackground(Color.WHITE);
                   
                if ((!L1_First_Sku.getText().equals("0") && L1_New_Sku.getText().equals("0")) || (L1_First_Sku.getText().equals("0") && !L1_New_Sku.getText().equals("0"))
                    || (!L1_First_Sku.getText().equals("0") && !L1_New_Sku.getText().equals("0"))) {
                    
                    IASdao.Field1 = L1_First_Sku.getText();
                    IASdao.Field2 = L1_Qty_Out.getText();
                    IASdao.Field3 = L1_First_Desc.getText();
                    IASdao.Field4 = L1_First_Attr.getText();
                    IASdao.Field5 = L1_First_Size.getText();
                    IASdao.Field6 = L1_Orig_Retail.getText();
                    IASdao.Field7 = L1_Reason_DropDown.getSelectedItem().toString();
                    IASdao.Field8 = L1_Desc_Damage.getText();                                      
                    IASdao.Field9 = L1_New_Sku.getText();
                    IASdao.Field10 = L1_New_Desc.getText();
                    IASdao.Field11 = L1_New_Attr.getText();
                    IASdao.Field12 = L1_New_Size.getText();
                    IASdao.Field13 = L1_Qty_In.getText();
                    IASdao.rline = L1.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L1.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "You must input at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L1_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L1_Done_ChkBox.setSelected(false);
                        L1_New_Sku.requestFocus();
                        L1_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L1_Done_ChkBox.setSelected(false);
                        L1_Qty_In.requestFocus();
                        L1_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L1_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L1_Done_ChkBox.setSelected(false);
                        L1_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L1_Done_ChkBox.setSelected(false);
                        L1_Desc_Damage.requestFocus();
                        L1_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n\n" 
                               + "NOTE: This is only available for use with the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L1_Done_ChkBox.setSelected(false);
                        L1_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L1_Qty_Out.setBackground(Color.yellow);
                        L1_Done_ChkBox.setSelected(false);
                        L1_Qty_Out.requestFocus();
                    } 
                    } else {
                        L1_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L1_Done_ChkBox.setSelected(false);
                        L1_First_Sku.requestFocus();
                    }                
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_First_Sku.requestFocus();
                    L1_First_Sku.setBackground(Color.yellow);
                }
            //    }
            } else {
                L1_First_Sku.setEnabled(true);
                L1_Qty_Out.setEnabled(true);
                L1_First_Desc.setEnabled(true);
                L1_First_Attr.setEnabled(true);
                L1_First_Size.setEnabled(true);
                L1_Orig_Retail.setEnabled(true);
                L1_Reason_DropDown.setEnabled(true);
                L1_Desc_Damage.setEnabled(true);
                L1_New_Sku.setEnabled(true);
                L1_New_Desc.setEnabled(true);
                L1_New_Attr.setEnabled(true);
                L1_New_Size.setEnabled(true);
                L1_Qty_In.setEnabled(true);
            }
            manager.focusNextComponent();      
        }
    }//GEN-LAST:event_L1_Done_ChkBoxKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L1_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L1_Done_ChkBoxActionPerformed
           rChkLn1();
    if (L1_Done_ChkBox.isSelected() == true) {
       String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L1_First_Sku.getText();
                IASdao.lnFlg = 1;
                /* 
                if (L1_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine1();
                    L1_Qty_Out.setText("");
                    L1_First_Desc.setText("");
                    L1_First_Attr.setText("");
                    L1_First_Size.setText("");
                    L1_Orig_Retail.setText("");
                    L1_Reason_DropDown.setSelectedItem("");
                    L1_Desc_Damage.setText("");
                    L1_New_Sku.setText("");
                    L1_New_Desc.setText("");
                    L1_New_Attr.setText("");
                    L1_New_Size.setText("");
                    L1_Qty_In.setText("");
                    L1_Done_ChkBox.setSelected(false);
                    L1_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (L1_First_Sku.getText().matches("[0-9]+") && L1_First_Sku.getText().length() < 7 || (L1_First_Sku.getText().equals("0") || L1_First_Sku.getText().equals(""))) {
                    
                if (IASdao.skuRslt.equals("good")|| (L1_First_Sku.getText().equals("") || (L1_First_Sku.getText().equals("0")))) {
                    L1_First_Sku.setBackground(Color.WHITE);
                    L1_First_Desc.setText(IASdao.firstSkuDesc);
                    L1_First_Attr.setText(IASdao.firstSkuAttr);
                    L1_First_Size.setText(IASdao.firstSkuSize);
                    L1_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L1_First_Sku.getText().equals("") || (L1_First_Sku.getText().equals("0"))) {
                    L1_First_Sku.setText("0");
                    L1_Qty_Out.setText("0");
                    L1_First_Desc.setText("");
                    L1_First_Attr.setText("");
                    L1_First_Size.setText("");
                    L1_Orig_Retail.setText("0.00");
                    L1_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL1_Cost();
                    IASdao.firstDCS = getfDcs1();
                    IASdao.firstSkuVendor = getfVen1();
                    IASdao.firstSkuVPNum = getfVpn1();
                }
                    
                if (L1_Qty_Out.getText().matches("[1-9]+") && L1_Qty_Out.getText().length() < 2 || L1_Qty_Out.getText().matches("0") && L1_First_Sku.getText().matches("0")) {
                    L1_Qty_Out.setBackground(Color.WHITE);
                    
                if (!L1_Reason_DropDown.getSelectedItem().equals("") && !L1_First_Sku.getText().matches("0") || L1_Reason_DropDown.getSelectedItem().equals("") && L1_First_Sku.getText().matches("0")) {
                    
                if (L1_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L1_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L1_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if ((IASdao.skuRslt2.equals("good") && L1_New_Sku.getText().matches("[0-9]+") && L1_New_Sku.getText().length() < 7) || (L1_New_Sku.getText().matches("") || L1_New_Sku.getText().matches("0"))) {
                        L1_New_Sku.setBackground(Color.WHITE);
                        L1_New_Desc.setText(IASdao.secondSkuDesc);
                        L1_New_Attr.setText(IASdao.secondSkuAttr);
                        L1_New_Size.setText(IASdao.secondSkuSize);
                    if (L1_New_Sku.getText().matches("") || L1_New_Sku.getText().matches("0")) {
                        L1_New_Sku.setText("0");
                        L1_New_Desc.setText("");
                        L1_New_Attr.setText("");
                        L1_New_Size.setText("");
                        L1_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL1_Cost();
                        IASdao.secondDCS = getsDcs1();
                        IASdao.secondSkuVendor = getsVen1();
                        IASdao.secondSkuVPNum = getsVpn1();
                    }
                
                if ((L1_Qty_In.getText().matches("[1-9]+") && L1_Qty_In.getText().length() < 2 && !L1_New_Sku.getText().matches("0")) || (L1_Qty_In.getText().matches("0") && L1_New_Sku.getText().matches("0"))) {
                   L1_Qty_In.setBackground(Color.WHITE);                
                    
                if (!L1_New_Sku.getText().equals(L1_First_Sku.getText())) {
                    L1_New_Sku.setBackground(Color.WHITE);
                    
                if ((!L1_First_Sku.getText().equals("0") && L1_New_Sku.getText().equals("0")) || (L1_First_Sku.getText().equals("0") && !L1_New_Sku.getText().equals("0"))
                    || (!L1_First_Sku.getText().equals("0") && !L1_New_Sku.getText().equals("0"))) {
                    
                    IASdao.Field1 = L1_First_Sku.getText();
                    IASdao.Field2 = L1_Qty_Out.getText();
                    IASdao.Field3 = L1_First_Desc.getText();
                    IASdao.Field4 = L1_First_Attr.getText();
                    IASdao.Field5 = L1_First_Size.getText();
                    IASdao.Field6 = L1_Orig_Retail.getText();
                    IASdao.Field7 = L1_Reason_DropDown.getSelectedItem().toString();
                    IASdao.Field8 = L1_Desc_Damage.getText();
                    IASdao.Field9 = L1_New_Sku.getText();
                    IASdao.Field10 = L1_New_Desc.getText();
                    IASdao.Field11 = L1_New_Attr.getText();
                    IASdao.Field12 = L1_New_Size.getText();
                    IASdao.Field13 = L1_Qty_In.getText();
                    IASdao.rline = L1.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L1.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "You must input at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L1_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L1_Done_ChkBox.setSelected(false);
                        L1_New_Sku.requestFocus();
                        L1_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L1_Done_ChkBox.setSelected(false);
                        L1_Qty_In.requestFocus();
                        L1_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L1_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L1_Done_ChkBox.setSelected(false);
                        L1_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L1_Done_ChkBox.setSelected(false);
                        L1_Desc_Damage.requestFocus();
                        L1_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n\n"
                                + "NOTE: This is only available for use with the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L1_Done_ChkBox.setSelected(false);
                        L1_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L1_Qty_Out.setBackground(Color.yellow);
                        L1_Done_ChkBox.setSelected(false);
                        L1_Qty_Out.requestFocus();
                    } 
                    } else {
                        L1_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L1_Done_ChkBox.setSelected(false);
                        L1_First_Sku.requestFocus();
                    }  
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L1_Done_ChkBox.setSelected(false);
                    L1_First_Sku.requestFocus();
                    L1_First_Sku.setBackground(Color.yellow);
                }
              //  }
                manager.focusNextComponent();
        } else {
            L1_First_Sku.setEnabled(true);
            L1_Qty_Out.setEnabled(true);
            L1_First_Desc.setEnabled(true);
            L1_First_Attr.setEnabled(true);
            L1_First_Size.setEnabled(true);
            L1_Orig_Retail.setEnabled(true);
            L1_Reason_DropDown.setEnabled(true);
            L1_Desc_Damage.setEnabled(true);
            L1_New_Sku.setEnabled(true);
            L1_New_Desc.setEnabled(true);
            L1_New_Attr.setEnabled(true);
            L1_New_Size.setEnabled(true);
            L1_Qty_In.setEnabled(true);
        }    
    }//GEN-LAST:event_L1_Done_ChkBoxActionPerformed

    // sku validation for the first sku input field
    private void L1_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_First_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.firstSkuInput = L1_First_Sku.getText();
            if (L1_First_Sku.getText().matches("[0-9]+") && L1_First_Sku.getText().length() < 7 || (L1_First_Sku.getText().equals("") || (L1_First_Sku.getText().equals("0")))) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L1_First_Sku.setBackground(Color.WHITE);
                L1_First_Desc.setText(IASdao.firstSkuDesc);
                L1_First_Attr.setText(IASdao.firstSkuAttr);
                L1_First_Size.setText(IASdao.firstSkuSize);
                L1_Orig_Retail.setText(IASdao.firstSkuPr);
                L1_Qty_Out.requestFocus();
                L1_Qty_Out.setText("");
            } else if (L1_First_Sku.getText().equals("") || (L1_First_Sku.getText().equals("0"))) {
                L1_First_Sku.setText("0");
                L1_Qty_Out.setText("0");
                L1_First_Desc.setText("");
                L1_First_Attr.setText("");
                L1_First_Size.setText("");
                L1_Orig_Retail.setText("0.00");
                L1_Reason_DropDown.setSelectedItem("");
                L1_Desc_Damage.requestFocus();
                // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
              //  IASdao.firstSkuCost = getL1_Cost();
            } else {
                L1_First_Sku.setBackground(Color.yellow);
                L1_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L1_First_Sku.requestFocus();
                L1_First_Sku.setBackground(Color.yellow);
            }
        }
    }//GEN-LAST:event_L1_First_SkuKeyPressed

    // sku validation for the first sku input field
    private void L3_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_First_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.firstSkuInput = L3_First_Sku.getText();
            if (L3_First_Sku.getText().matches("[0-9]+") && L3_First_Sku.getText().length() < 7 || (L3_First_Sku.getText().equals("") || (L3_First_Sku.getText().equals("0")))) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L3_First_Sku.setBackground(Color.WHITE);
                L3_First_Desc.setText(IASdao.firstSkuDesc);
                L3_First_Attr.setText(IASdao.firstSkuAttr);
                L3_First_Size.setText(IASdao.firstSkuSize);
                L3_Orig_Retail.setText(IASdao.firstSkuPr);
                L3_Qty_Out.requestFocus();
                L3_Qty_Out.setText("");
            } else if (L3_First_Sku.getText().equals("") || (L3_First_Sku.getText().equals("0"))) {
                L3_First_Sku.setText("0");
                L3_Qty_Out.setText("0");
                L3_First_Desc.setText("");
                L3_First_Attr.setText("");
                L3_First_Size.setText("");
                L3_Orig_Retail.setText("0.00");
                L3_Reason_DropDown.setSelectedItem("");
                L3_Desc_Damage.requestFocus();
                // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
               // IASdao.firstSkuCost = getL3_Cost();
            } else {
                L3_First_Sku.setBackground(Color.yellow);
                L3_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L3_First_Sku.requestFocus();
                L3_First_Sku.setBackground(Color.yellow);
            }
        }
    }//GEN-LAST:event_L3_First_SkuKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L3_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L3_Done_ChkBoxActionPerformed
           rChkLn3();
            if (L3_Done_ChkBox.isSelected() == true) {
            String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L3_First_Sku.getText();
                IASdao.lnFlg = 3;
                /*   *** Replaced by stand alone method ***
                if (L3_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine3();
                    L3_Qty_Out.setText("");
                    L3_First_Desc.setText("");
                    L3_First_Attr.setText("");
                    L3_First_Size.setText("");
                    L3_Orig_Retail.setText("");
                    L3_Reason_DropDown.setSelectedItem("");
                    L3_Desc_Damage.setText("");
                    L3_New_Sku.setText("");
                    L3_New_Desc.setText("");
                    L3_New_Attr.setText("");
                    L3_New_Size.setText("");
                    L3_Qty_In.setText("");
                    L3_Done_ChkBox.setSelected(false);
                    L3_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (L3_First_Sku.getText().matches("[0-9]+") && L3_First_Sku.getText().length() < 7 || L3_First_Sku.getText().equals("0") || L3_First_Sku.getText().equals("")) {
                
                if (IASdao.skuRslt.equals("good") || (L3_First_Sku.getText().equals("") || (L3_First_Sku.getText().equals("0")))) {
                    L3_First_Sku.setBackground(Color.WHITE);
                    L3_First_Desc.setText(IASdao.firstSkuDesc);
                    L3_First_Attr.setText(IASdao.firstSkuAttr);
                    L3_First_Size.setText(IASdao.firstSkuSize);
                    L3_Orig_Retail.setText(IASdao.firstSkuPr);
                 if (L3_First_Sku.getText().equals("") || (L3_First_Sku.getText().equals("0"))) {
                    L3_First_Sku.setText("0");
                    L3_Qty_Out.setText("0");
                    L3_First_Desc.setText("");
                    L3_First_Attr.setText("");
                    L3_First_Size.setText("");
                    L3_Orig_Retail.setText("0.00");
                    L3_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL3_Cost();
                    IASdao.firstDCS = getfDcs3();
                    IASdao.firstSkuVendor = getfVen3();
                    IASdao.firstSkuVPNum = getfVpn3();
                }
                    
                if (L3_Qty_Out.getText().matches("[1-9]+") && L3_Qty_Out.getText().length() < 2 || L3_Qty_Out.getText().matches("0")) {
                    L3_Qty_Out.setBackground(Color.WHITE);
                    
                if (!L3_Reason_DropDown.getSelectedItem().equals("") && !L3_First_Sku.getText().equals("") || L3_Reason_DropDown.getSelectedItem().equals("") && L3_First_Sku.getText().matches("0")) {
                    
                if (L3_Desc_Damage.getText().matches("[a-zA-Z ]+")) {    
                    L3_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L3_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if (IASdao.skuRslt2.equals("good") && L3_New_Sku.getText().matches("[0-9]+") && L3_New_Sku.getText().length() < 7 || L3_New_Sku.getText().equals("0") || L3_New_Sku.getText().equals("")) {
                        L3_New_Sku.setBackground(Color.WHITE);
                        L3_New_Desc.setText(IASdao.secondSkuDesc);
                        L3_New_Attr.setText(IASdao.secondSkuAttr);
                        L3_New_Size.setText(IASdao.secondSkuSize);
                    if (L3_New_Sku.getText().equals("") || L3_New_Sku.getText().equals("0")) {                                                
                        L3_New_Sku.setText("0");
                        L3_New_Desc.setText("");
                        L3_New_Attr.setText("");
                        L3_New_Size.setText("");
                        L3_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL3_Cost();
                        IASdao.secondDCS = getsDcs3();
                        IASdao.secondSkuVendor = getsVen3();
                        IASdao.secondSkuVPNum = getsVpn3();
                    }
                
                if (L3_Qty_In.getText().matches("[1-9]+") && L3_Qty_In.getText().length() < 2 || L3_Qty_In.getText().matches("0")&& L3_New_Sku.getText().matches("0"))  {
                    L3_Qty_In.setBackground(Color.WHITE);
                    
                if (!L3_New_Sku.getText().equals(L3_First_Sku.getText())) {
                    
                if ((!L3_First_Sku.getText().equals("0") && L3_New_Sku.getText().equals("0")) || (L3_First_Sku.getText().equals("0") && !L3_New_Sku.getText().equals("0"))
                    || (!L3_First_Sku.getText().equals("0") && !L3_New_Sku.getText().equals("0"))) {
                    
                    IASdao.Field1 = L3_First_Sku.getText();
                    IASdao.Field2 = L3_Qty_Out.getText();
                    IASdao.Field3 = L3_First_Desc.getText();
                    IASdao.Field4 = L3_First_Attr.getText();
                    IASdao.Field5 = L3_First_Size.getText();
                    IASdao.Field6 = L3_Orig_Retail.getText();
                    IASdao.Field7 = L3_Reason_DropDown.getSelectedItem().toString();
                    IASdao.Field8 = L3_Desc_Damage.getText();
                    IASdao.Field9 = L3_New_Sku.getText();
                    IASdao.Field10 = L3_New_Desc.getText();
                    IASdao.Field11 = L3_New_Attr.getText();
                    IASdao.Field12 = L3_New_Size.getText();
                    IASdao.Field13 = L3_Qty_In.getText();
                    IASdao.rline = L3.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L3.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "You must input at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L3_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L3_Done_ChkBox.setSelected(false);
                        L3_New_Sku.requestFocus();
                        L3_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L3_Done_ChkBox.setSelected(false);
                        L3_Qty_In.requestFocus();
                        L3_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L3_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L3_Done_ChkBox.setSelected(false);
                        L3_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L3_Done_ChkBox.setSelected(false);
                        L3_Desc_Damage.requestFocus();
                        L3_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n\n"
                                + "NOTE: This is only available for use with the First Sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L3_Done_ChkBox.setSelected(false);
                        L3_Reason_DropDown.requestFocus();                        
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L3_Done_ChkBox.setSelected(false);
                        L3_Qty_Out.requestFocus();
                        L3_Qty_Out.setBackground(Color.yellow);
                    } 
                    } else {
                        L3_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L3_Done_ChkBox.setSelected(false);
                        L3_First_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                        L3_Done_ChkBox.setSelected(false);
                        L3_First_Sku.requestFocus();
                        L3_First_Sku.setBackground(Color.yellow);
                    }
              //  }
        } else {
            L3_First_Sku.setEnabled(true);
            L3_Qty_Out.setEnabled(true);
            L3_First_Desc.setEnabled(true);
            L3_First_Attr.setEnabled(true);
            L3_First_Size.setEnabled(true);
            L3_Orig_Retail.setEnabled(true);
            L3_Reason_DropDown.setEnabled(true);
            L3_Desc_Damage.setEnabled(true);
            L3_New_Sku.setEnabled(true);
            L3_New_Desc.setEnabled(true);
            L3_New_Attr.setEnabled(true);
            L3_New_Size.setEnabled(true);
            L3_Qty_In.setEnabled(true);
        }
       manager.focusNextComponent();
    }//GEN-LAST:event_L3_Done_ChkBoxActionPerformed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L3_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Done_ChkBoxKeyPressed
            L3_Done_ChkBox.setSelected(true);
            rChkLn3();
            if (L3_Done_ChkBox.isSelected() == true) {
               String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L3_First_Sku.getText();
                IASdao.lnFlg = 3;
                /*
                if (L3_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine3();
                    L3_Qty_Out.setText("");
                    L3_First_Desc.setText("");
                    L3_First_Attr.setText("");
                    L3_First_Size.setText("");
                    L3_Orig_Retail.setText("");
                    L3_Reason_DropDown.setSelectedItem("");
                    L3_Desc_Damage.setText("");
                    L3_New_Sku.setText("");
                    L3_New_Desc.setText("");
                    L3_New_Attr.setText("");
                    L3_New_Size.setText("");
                    L3_Qty_In.setText("");
                    L3_Done_ChkBox.setSelected(false);
                    L3_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (L3_First_Sku.getText().matches("[0-9]+") && L3_First_Sku.getText().length() < 7 || L3_First_Sku.getText().equals("0") || L3_First_Sku.getText().equals("")) {
                
                if (IASdao.skuRslt.equals("good") || (L3_First_Sku.getText().equals("") || (L3_First_Sku.getText().equals("0")))) {
                    L3_First_Sku.setBackground(Color.WHITE);
                    L3_First_Desc.setText(IASdao.firstSkuDesc);
                    L3_First_Attr.setText(IASdao.firstSkuAttr);
                    L3_First_Size.setText(IASdao.firstSkuSize);
                    L3_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L3_First_Sku.getText().equals("") || (L3_First_Sku.getText().equals("0"))) {
                    L3_First_Sku.setText("0");
                    L3_Qty_Out.setText("0");
                    L3_First_Desc.setText("");
                    L3_First_Attr.setText("");
                    L3_First_Size.setText("");
                    L3_Orig_Retail.setText("0.00");
                    L3_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL3_Cost();
                    IASdao.firstDCS = getfDcs3();
                    IASdao.firstSkuVendor = getfVen3();
                    IASdao.firstSkuVPNum = getfVpn3();
                }
                    
                if (L3_Qty_Out.getText().matches("[1-9]+") && L3_Qty_Out.getText().length() < 2 || L3_Qty_Out.getText().matches("0") && L3_First_Sku.getText().matches("0")) {
                    L3_Qty_Out.setBackground(Color.WHITE);
                    
                if (!L3_Reason_DropDown.getSelectedItem().equals("")&& !L3_First_Sku.getText().equals("") || L3_Reason_DropDown.getSelectedItem().equals("") && L3_First_Sku.getText().matches("0")) {
                    
                if (L3_Desc_Damage.getText().matches("[a-zA-Z ]+")) {    
                    L3_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L3_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if (IASdao.skuRslt2.equals("good") && L3_New_Sku.getText().matches("[0-9]+") && L3_New_Sku.getText().length() < 7 || L3_New_Sku.getText().equals("0") || L3_New_Sku.getText().equals("")) {
                        L3_New_Sku.setBackground(Color.WHITE);
                        L3_New_Desc.setText(IASdao.secondSkuDesc);
                        L3_New_Attr.setText(IASdao.secondSkuAttr);
                        L3_New_Size.setText(IASdao.secondSkuSize);
                       
                    if (L3_New_Sku.getText().equals("") || L3_New_Sku.getText().equals("0")) {                                                
                        L3_New_Sku.setText("0");
                        L3_New_Desc.setText("");
                        L3_New_Attr.setText("");
                        L3_New_Size.setText("");
                        L3_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL3_Cost();
                        IASdao.secondDCS = getsDcs3();
                        IASdao.secondSkuVendor = getsVen3();
                        IASdao.secondSkuVPNum = getsVpn3();
                    }
                
                if (L3_Qty_In.getText().matches("[1-9]+") && L3_Qty_In.getText().length() < 2 || L3_Qty_In.getText().matches("0") && L3_New_Sku.getText().matches("0"))  {
                    L3_Qty_In.setBackground(Color.WHITE);
                    
                if (!L3_New_Sku.getText().equals(L3_First_Sku.getText())) {
                    
                if ((!L3_First_Sku.getText().equals("") && L3_New_Sku.getText().equals("")) || (L3_First_Sku.getText().equals("") && !L3_New_Sku.getText().equals(""))
                    || (!L3_First_Sku.getText().equals("") && !L3_New_Sku.getText().equals(""))) {
                    
                    IASdao.Field1 = L3_First_Sku.getText();
                    IASdao.Field2 = L3_Qty_Out.getText();
                    IASdao.Field3 = L3_First_Desc.getText();
                    IASdao.Field4 = L3_First_Attr.getText();
                    IASdao.Field5 = L3_First_Size.getText();
                    IASdao.Field6 = L3_Orig_Retail.getText();
                    IASdao.Field7 = L3_Reason_DropDown.getSelectedItem().toString();
                    IASdao.Field8 = L3_Desc_Damage.getText();
                    IASdao.Field9 = L3_New_Sku.getText();
                    IASdao.Field10 = L3_New_Desc.getText();
                    IASdao.Field11 = L3_New_Attr.getText();
                    IASdao.Field12 = L3_New_Size.getText();
                    IASdao.Field13 = L3_Qty_In.getText();
                    IASdao.rline = L3.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L3.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "You must input at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L3_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L3_Done_ChkBox.setSelected(false);                            
                        L3_New_Sku.requestFocus();
                        L3_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L3_Done_ChkBox.setSelected(false);
                        L3_Qty_In.requestFocus();
                        L3_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L3_Done_ChkBox.setSelected(false);
                        L3_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L3_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);                       
                        L3_Done_ChkBox.setSelected(false);
                        L3_Desc_Damage.requestFocus();
                        L3_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n" 
                                + "NOTE: This is only available for the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L3_Done_ChkBox.setSelected(false);
                        L3_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L3_Done_ChkBox.setSelected(false);
                        L3_Qty_Out.requestFocus();
                        L3_Qty_Out.setBackground(Color.yellow);
                    } 
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L3_First_Sku.setBackground(Color.yellow);
                        L3_Done_ChkBox.setSelected(false);
                        L3_First_Sku.requestFocus();
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
                L3_First_Attr.setEnabled(true);
                L3_First_Size.setEnabled(true);
                L3_Orig_Retail.setEnabled(true);
                L3_Reason_DropDown.setEnabled(true);
                L3_Desc_Damage.setEnabled(true);
                L3_New_Sku.setEnabled(true);
                L3_New_Desc.setEnabled(true);
                L3_New_Attr.setEnabled(true);
                L3_New_Size.setEnabled(true);
                L3_Qty_In.setEnabled(true);
            }
            manager.focusNextComponent();                
    }//GEN-LAST:event_L3_Done_ChkBoxKeyPressed

        // sku validation for the second sku input field
    private void L1_New_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_New_SkuKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.secondSkuInput = L1_New_Sku.getText();
            if (!L1_New_Sku.getText().equals(L1_First_Sku.getText())) {
               
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L1_New_Sku.getText().matches("[0-9]+") && L1_New_Sku.getText().length() < 7 ) {
                    L1_New_Sku.setBackground(Color.WHITE);
                    L1_New_Desc.setText(IASdao.secondSkuDesc);
                    L1_New_Attr.setText(IASdao.secondSkuAttr);
                    L1_New_Size.setText(IASdao.secondSkuSize);
                    L1_Qty_In.requestFocus();
                    L1_Qty_In.setText("");
                } else {
                   JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L1_New_Sku.requestFocus();
                    L1_New_Sku.setBackground(Color.yellow);
                }
            } else if (L1_New_Sku.getText().equals("") || L1_New_Sku.getText().equals("0")) {
                L1_New_Sku.setText("0");
                L1_New_Desc.setText("");
                L1_New_Attr.setText("");
                L1_New_Size.setText("");
                L1_Qty_In.setText("0");
                L1_Done_ChkBox.requestFocus();
            } else {
                L1_New_Sku.setBackground(Color.yellow);
                L1_New_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
            }                 
            } else {
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                L1_New_Sku.requestFocus();
                L1_New_Sku.setBackground(Color.yellow);
            }
         }
    }//GEN-LAST:event_L1_New_SkuKeyPressed

        // sku validation for the first sku input field
    private void L4_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_First_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.firstSkuInput = L4_First_Sku.getText();
            if (L4_First_Sku.getText().matches("[0-9]+") && L4_First_Sku.getText().length() < 7 || (L4_First_Sku.getText().equals("") || (L4_First_Sku.getText().equals("0")))) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L4_First_Sku.setBackground(Color.WHITE);
                L4_First_Desc.setText(IASdao.firstSkuDesc);
                L4_First_Attr.setText(IASdao.firstSkuAttr);
                L4_First_Size.setText(IASdao.firstSkuSize);
                L4_Orig_Retail.setText(IASdao.firstSkuPr);
                L4_Qty_Out.requestFocus();
                L4_Qty_Out.setText("");
            } else if (L4_First_Sku.getText().equals("") || (L4_First_Sku.getText().equals("0"))) {
                L4_First_Sku.setText("0");
                L4_Qty_Out.setText("0");
                L4_First_Desc.setText("");
                L4_First_Attr.setText("");
                L4_First_Size.setText("");
                L4_Orig_Retail.setText("0.00");
                L4_Reason_DropDown.setSelectedItem("");
                L4_Desc_Damage.requestFocus();
               // IASdao.firstSkuCost = getL4_Cost();
            } else {
                L4_First_Sku.setBackground(Color.yellow);
                L4_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L4_First_Sku.requestFocus();
                L4_First_Sku.setBackground(Color.yellow);
            }
        }
    }//GEN-LAST:event_L4_First_SkuKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L4_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Done_ChkBoxKeyPressed
            L4_Done_ChkBox.setSelected(true);
            rChkLn4();
            if (L4_Done_ChkBox.isSelected() == true) {
                String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L4_First_Sku.getText();
                IASdao.lnFlg = 4;
                /*
                if (L4_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine4();
                    L4_Qty_Out.setText("");
                    L4_First_Desc.setText("");
                    L4_First_Attr.setText("");
                    L4_First_Size.setText("");
                    L4_Orig_Retail.setText("");
                    L4_Reason_DropDown.setSelectedItem("");
                    L4_Desc_Damage.setText("");
                    L4_New_Sku.setText("");
                    L4_New_Desc.setText("");
                    L4_New_Attr.setText("");
                    L4_New_Size.setText("");
                    L4_Qty_In.setText("");
                    L4_Done_ChkBox.setSelected(false);
                    L4_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (L4_First_Sku.getText().matches("[0-9]+") && L4_First_Sku.getText().length() < 7 || L4_First_Sku.getText().equals("0") || L4_First_Sku.getText().equals("")) {
                
                if (IASdao.skuRslt.equals("good") || (L4_First_Sku.getText().equals("") || (L4_First_Sku.getText().equals("0")))) {
                    L4_First_Sku.setBackground(Color.WHITE);
                    L4_First_Desc.setText(IASdao.firstSkuDesc);
                    L4_First_Attr.setText(IASdao.firstSkuAttr);
                    L4_First_Size.setText(IASdao.firstSkuSize);
                    L4_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L4_First_Sku.getText().equals("") || (L4_First_Sku.getText().equals("0"))) {
                    L4_First_Sku.setText("0");
                    L4_Qty_Out.setText("0");
                    L4_First_Desc.setText("");
                    L4_First_Attr.setText("");
                    L4_First_Size.setText("");
                    L4_Orig_Retail.setText("0.00");
                    L4_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL4_Cost();
                    IASdao.firstDCS = getfDcs4();
                    IASdao.firstSkuVendor = getfVen4();
                    IASdao.firstSkuVPNum = getfVpn4();
                }
                    
                if (L4_Qty_Out.getText().matches("[1-9]+") && L4_Qty_Out.getText().length() < 2 || L4_Qty_Out.getText().matches("0") && L4_First_Sku.getText().matches("0")) {
                    L4_Qty_Out.setBackground(Color.WHITE);
                    
                if (!L4_Reason_DropDown.getSelectedItem().equals("") && !L4_First_Sku.getText().equals("0") || L4_Reason_DropDown.getSelectedItem().equals("") && L4_First_Sku.getText().matches("0")) {
                    
                if (L4_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L4_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L4_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if (IASdao.skuRslt2.equals("good") && L4_New_Sku.getText().matches("[0-9]+") && L4_New_Sku.getText().length() < 7 || L4_New_Sku.getText().equals("0") || L4_New_Sku.getText().equals("")) {
                        L4_New_Sku.setBackground(Color.WHITE);
                        L4_New_Desc.setText(IASdao.secondSkuDesc);
                        L4_New_Attr.setText(IASdao.secondSkuAttr);
                        L4_New_Size.setText(IASdao.secondSkuSize);
                        
                    if (L4_New_Sku.getText().equals("") || L4_New_Sku.getText().equals("0")) {                                                
                        L4_New_Sku.setText("0");
                        L4_New_Desc.setText("");
                        L4_New_Attr.setText("");
                        L4_New_Size.setText("");
                        L4_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL4_Cost();
                        IASdao.secondDCS = getsDcs4();
                        IASdao.secondSkuVendor = getsVen4();
                        IASdao.secondSkuVPNum = getsVpn4();
                    }
                
                if (L4_Qty_In.getText().matches("[1-9]+") && L4_Qty_In.getText().length() < 2 || L4_Qty_In.getText().matches("0")&& L4_New_Sku.getText().matches("0")) {
                    L4_Qty_In.setBackground(Color.WHITE);
                    
                if (!L4_New_Sku.getText().equals(L4_First_Sku.getText())) {
                    
                if ((!L4_First_Sku.getText().equals("0") && L4_New_Sku.getText().equals("0")) || (L4_First_Sku.getText().equals("0") && !L4_New_Sku.getText().equals("0"))
                    || (!L4_First_Sku.getText().equals("0") && !L4_New_Sku.getText().equals("0"))) {
                    
                    IASdao.Field1 = L4_First_Sku.getText();
                    IASdao.Field2 = L4_Qty_Out.getText();
                    IASdao.Field3 = L4_First_Desc.getText();
                    IASdao.Field4 = L4_First_Attr.getText();
                    IASdao.Field5 = L4_First_Size.getText();
                    IASdao.Field6 = L4_Orig_Retail.getText();
                    IASdao.Field7 = L4_Reason_DropDown.getSelectedItem().toString();
                    IASdao.Field8 = L4_Desc_Damage.getText();
                    IASdao.Field9 = L4_New_Sku.getText();
                    IASdao.Field10 = L4_New_Desc.getText();
                    IASdao.Field11 = L4_New_Attr.getText();
                    IASdao.Field12 = L4_New_Size.getText();
                    IASdao.Field13 = L4_Qty_In.getText();
                    IASdao.rline = L4.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L4.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "You must input at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L4_Done_ChkBox.setSelected(false); 
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L4_Done_ChkBox.setSelected(false);                       
                        L4_New_Sku.requestFocus();
                        L4_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L4_Done_ChkBox.setSelected(false);
                        L4_Qty_In.requestFocus();
                        L4_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L4_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L4_Done_ChkBox.setSelected(false);
                        L4_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L4_Done_ChkBox.setSelected(false);
                        L4_Desc_Damage.requestFocus();
                        L4_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n\n" 
                                + "NOTE: This is only available for use with the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L4_Done_ChkBox.setSelected(false);
                        L4_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L4_Done_ChkBox.setSelected(false);
                        L4_Qty_Out.requestFocus();
                        L4_Qty_Out.setBackground(Color.yellow);
                    } 
                    } else {
                        L4_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L4_Done_ChkBox.setSelected(false);
                        L4_First_Sku.requestFocus();
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
                L4_First_Attr.setEnabled(true);
                L4_First_Size.setEnabled(true);
                L4_Orig_Retail.setEnabled(true);
                L4_Reason_DropDown.setEnabled(true);
                L4_Desc_Damage.setEnabled(true);
                L4_New_Sku.setEnabled(true);
                L4_New_Desc.setEnabled(true);
                L4_Qty_In.setEnabled(true);
            }
            manager.focusNextComponent();
    }//GEN-LAST:event_L4_Done_ChkBoxKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L4_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L4_Done_ChkBoxActionPerformed
           rChkLn4();
            if (L4_Done_ChkBox.isSelected() == true) {
            String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L4_First_Sku.getText();
                IASdao.lnFlg = 4;
                /*
                if (L4_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine4();
                    L4_Qty_Out.setText("");
                    L4_First_Desc.setText("");
                    L4_First_Attr.setText("");
                    L4_First_Size.setText("");
                    L4_Orig_Retail.setText("");
                    L4_Reason_DropDown.setSelectedItem("");
                    L4_Desc_Damage.setText("");
                    L4_New_Sku.setText("");
                    L4_New_Desc.setText("");
                    L4_New_Attr.setText("");
                    L4_New_Size.setText("");
                    L4_Qty_In.setText("");
                    L4_Done_ChkBox.setSelected(false);
                    L4_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (L4_First_Sku.getText().matches("[0-9]+") && L4_First_Sku.getText().length() < 7 || (L4_First_Sku.getText().equals("0") || L4_First_Sku.getText().equals(""))) {
                
                if (IASdao.skuRslt.equals("good") || (L4_First_Sku.getText().equals("") || (L4_First_Sku.getText().equals("0")))) {
                    L4_First_Sku.setBackground(Color.WHITE);
                    L4_First_Desc.setText(IASdao.firstSkuDesc);
                    L4_First_Attr.setText(IASdao.firstSkuAttr);
                    L4_First_Size.setText(IASdao.firstSkuSize);
                    L4_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L4_First_Sku.getText().equals("") || (L4_First_Sku.getText().equals("0"))) {
                    L4_First_Sku.setText("0");
                    L4_Qty_Out.setText("0");
                    L4_First_Desc.setText("");
                    L4_First_Attr.setText("");
                    L4_First_Size.setText("");
                    L4_Orig_Retail.setText("0.00");
                    L4_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL4_Cost();
                    IASdao.firstDCS = getfDcs4();
                    IASdao.firstSkuVendor = getfVen4();
                    IASdao.firstSkuVPNum = getfVpn4();
                }
                    
                if (L4_Qty_Out.getText().matches("[1-9]+") && L4_Qty_Out.getText().length() < 2 || L4_Qty_Out.getText().matches("0") && L4_First_Sku.getText().matches("0")) {
                    L4_Qty_Out.setBackground(Color.WHITE);
                    
                if (!L4_Reason_DropDown.getSelectedItem().equals("") && !L4_First_Sku.getText().equals("0") || L4_Reason_DropDown.getSelectedItem().equals("") && L4_First_Sku.getText().matches("0")) {
                    
                if (L4_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L4_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L4_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if (IASdao.skuRslt2.equals("good") && L4_New_Sku.getText().matches("[0-9]+") && L4_New_Sku.getText().length() < 7 || L4_New_Sku.getText().equals("0") || L4_New_Sku.getText().equals("")) {
                        L4_New_Sku.setBackground(Color.WHITE);
                        L4_New_Desc.setText(IASdao.secondSkuDesc);
                        L4_New_Attr.setText(IASdao.secondSkuAttr);
                        L4_New_Size.setText(IASdao.secondSkuSize);
                        
                    if (L4_New_Sku.getText().equals("") || L4_New_Sku.getText().equals("0")) {                                                
                        L4_New_Sku.setText("0");
                        L4_New_Desc.setText("");
                        L4_New_Attr.setText("");
                        L4_New_Size.setText("");
                        L4_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL4_Cost();
                        IASdao.secondDCS = getsDcs4();
                        IASdao.secondSkuVendor = getsVen4();
                        IASdao.secondSkuVPNum = getsVpn4();
                    }
                
                if (L4_Qty_In.getText().matches("[1-9]+") && L4_Qty_In.getText().length() < 2 || L4_Qty_In.getText().matches("0") && L4_New_Sku.getText().matches("0")) {
                    L4_Qty_In.setBackground(Color.WHITE);
                    
                if (!L4_New_Sku.getText().equals(L4_First_Sku.getText())) {
                    
                if ((!L4_First_Sku.getText().equals("0") && L4_New_Sku.getText().equals("0")) || (L4_First_Sku.getText().equals("0") && !L4_New_Sku.getText().equals("0"))
                    || (!L4_First_Sku.getText().equals("0") && !L4_New_Sku.getText().equals("0"))) {
                    
                    IASdao.Field1 = L4_First_Sku.getText();
                    IASdao.Field2 = L4_Qty_Out.getText();
                    IASdao.Field3 = L4_First_Desc.getText();
                    IASdao.Field4 = L4_First_Attr.getText();
                    IASdao.Field5 = L4_First_Size.getText();
                    IASdao.Field6 = L4_Orig_Retail.getText();
                    IASdao.Field7 = L4_Reason_DropDown.getSelectedItem().toString();
                    IASdao.Field8 = L4_Desc_Damage.getText();
                    IASdao.Field9 = L4_New_Sku.getText();
                    IASdao.Field10 = L4_New_Desc.getText();
                    IASdao.Field11 = L4_New_Attr.getText();
                    IASdao.Field12 = L4_New_Size.getText();
                    IASdao.Field13 = L4_Qty_In.getText();
                    IASdao.rline = L4.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L4.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "You must input at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L4_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L4_Done_ChkBox.setSelected(false);
                        L4_New_Sku.requestFocus();
                        L4_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L4_Done_ChkBox.setSelected(false);
                        L4_Qty_In.requestFocus();
                        L4_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L4_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L4_Done_ChkBox.setSelected(false);
                        L4_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L4_Done_ChkBox.setSelected(false);
                        L4_Desc_Damage.requestFocus();
                        L4_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n" 
                                + "NOTE: This is only available for use with the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L4_Done_ChkBox.setSelected(false);
                        L4_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L4_Done_ChkBox.setSelected(false);
                        L4_Qty_Out.requestFocus();
                        L4_Qty_Out.setBackground(Color.yellow);
                    } 
                    } else {
                        L4_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L4_Done_ChkBox.setSelected(false);
                        L4_First_Sku.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L4_Done_ChkBox.setSelected(false);
                    L4_First_Sku.requestFocus();
                    L4_First_Sku.setBackground(Color.yellow);
                }
              //  }
            manager.focusNextComponent();
        } else {
            L4_First_Sku.setEnabled(true);
            L4_Qty_Out.setEnabled(true);
            L4_First_Desc.setEnabled(true);
            L4_First_Attr.setEnabled(true);
            L4_First_Size.setEnabled(true);
            L4_Orig_Retail.setEnabled(true);
            L4_Reason_DropDown.setEnabled(true);
            L4_Desc_Damage.setEnabled(true);
            L4_New_Sku.setEnabled(true);
            L4_New_Desc.setEnabled(true);
            L4_New_Attr.setEnabled(true);
            L4_New_Size.setEnabled(true);
            L4_Qty_In.setEnabled(true);
        }
    }//GEN-LAST:event_L4_Done_ChkBoxActionPerformed

        // sku validation for the first sku input field
    private void L5_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_First_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.firstSkuInput = L5_First_Sku.getText();
            if (L5_First_Sku.getText().matches("[0-9]+") && L4_First_Sku.getText().length() < 7 || (L5_First_Sku.getText().equals("") || (L5_First_Sku.getText().equals("0")))) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L5_First_Sku.setBackground(Color.WHITE);
                L5_First_Desc.setText(IASdao.firstSkuDesc);
                L5_First_Attr.setText(IASdao.firstSkuAttr);
                L5_First_Size.setText(IASdao.firstSkuSize);
                L5_Orig_Retail.setText(IASdao.firstSkuPr);
                L5_Qty_Out.requestFocus();
                L5_Qty_Out.setText("");
            } else if (L5_First_Sku.getText().equals("") || (L5_First_Sku.getText().equals("0"))) {
                L5_First_Sku.setText("0");
                L5_Qty_Out.setText("0");
                L5_First_Desc.setText("");
                L5_First_Attr.setText("");
                L5_First_Size.setText("");
                L5_Orig_Retail.setText("0.00");
                L5_Reason_DropDown.setSelectedItem("");
                L5_Desc_Damage.requestFocus();
               // IASdao.firstSkuCost = getL5_Cost();
            } else {
                L5_First_Sku.setBackground(Color.yellow);
                L5_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L5_First_Sku.requestFocus();
                L5_First_Sku.setBackground(Color.yellow);
            }
        }
    }//GEN-LAST:event_L5_First_SkuKeyPressed

        // sku validation for the second sku input field
    private void L2_New_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_New_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.secondSkuInput = L2_New_Sku.getText();
            if (!L2_New_Sku.getText().equals(L2_First_Sku.getText())) {
                
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L2_New_Sku.getText().matches("[0-9]+") && L2_New_Sku.getText().length() < 7 ) {
                    L2_New_Sku.setBackground(Color.WHITE);
                    L2_New_Desc.setText(IASdao.secondSkuDesc);
                    L2_New_Attr.setText(IASdao.secondSkuAttr);
                    L2_New_Size.setText(IASdao.secondSkuSize);
                    L2_Qty_In.requestFocus();
                    L2_Qty_In.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L2_New_Sku.requestFocus();
                    L2_New_Sku.setBackground(Color.yellow);
                }
                } else if (L2_New_Sku.getText().equals("") || L2_New_Sku.getText().equals("0")) {
                    L2_New_Sku.setText("0");
                    L2_New_Desc.setText("");
                    L2_New_Attr.setText("");
                    L2_New_Size.setText("");
                    L2_Qty_In.setText("0");
                    L2_Done_ChkBox.requestFocus();
                } else {
                    L2_New_Sku.setBackground(Color.yellow);
                    L2_New_Sku.requestFocus();
                    JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                }                
                } else {
                    JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                    L2_New_Sku.requestFocus();
                    L2_New_Sku.setBackground(Color.yellow);
                }
            }
    }//GEN-LAST:event_L2_New_SkuKeyPressed

        // sku validation for the second sku input field
    private void L3_New_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_New_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.secondSkuInput = L3_New_Sku.getText();
            if (!L3_New_Sku.getText().equals(L3_First_Sku.getText())) {
                
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L3_New_Sku.getText().matches("[0-9]+") && L3_New_Sku.getText().length() < 7 ) {
                    L3_New_Sku.setBackground(Color.WHITE);
                    L3_New_Desc.setText(IASdao.secondSkuDesc);
                    L3_New_Attr.setText(IASdao.secondSkuAttr);
                    L3_New_Size.setText(IASdao.secondSkuSize);
                    L3_Qty_In.requestFocus();
                    L3_Qty_In.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L3_New_Sku.requestFocus();
                    L3_New_Sku.setBackground(Color.yellow);
                }
            } else if (L3_New_Sku.getText().equals("") || L3_New_Sku.getText().equals("0")) {
                L3_New_Sku.setText("0");
                L3_New_Desc.setText("");
                L3_New_Attr.setText("");
                L3_New_Size.setText("");
                L3_Qty_In.setText("0");
                L3_Done_ChkBox.requestFocus();
            } else {
                L3_New_Sku.setBackground(Color.yellow);
                L3_New_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
            }             
            } else {
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                L3_New_Sku.requestFocus();
                L3_New_Sku.setBackground(Color.yellow);
            }
        }
    }//GEN-LAST:event_L3_New_SkuKeyPressed

        // sku validation for the second sku input field
    private void L4_New_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_New_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.secondSkuInput = L4_New_Sku.getText();
            if (!L4_New_Sku.getText().equals(L4_First_Sku.getText())) {
                
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L4_New_Sku.getText().matches("[0-9]+") && L4_New_Sku.getText().length() < 7 ) {
                    L4_New_Sku.setBackground(Color.WHITE);
                    L4_New_Desc.setText(IASdao.secondSkuDesc);
                    L4_New_Attr.setText(IASdao.secondSkuAttr);
                    L4_New_Size.setText(IASdao.secondSkuSize);
                    L4_Qty_In.requestFocus();
                    L4_Qty_In.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L4_New_Sku.requestFocus();
                    L4_New_Sku.setBackground(Color.yellow);
                }
            } else if (L4_New_Sku.getText().equals("") || L4_New_Sku.getText().equals("0")) {
                L4_New_Sku.setText("0");
                L4_New_Desc.setText("");
                L4_New_Attr.setText("");
                L4_New_Size.setText("");
                L4_Qty_In.setText("0");
                L4_Done_ChkBox.requestFocus();
            } else {
                L4_New_Sku.setBackground(Color.yellow);
                L4_New_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
            }             
            } else {
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                L4_New_Sku.requestFocus();
                L4_New_Sku.setBackground(Color.yellow);
            }
       }
    }//GEN-LAST:event_L4_New_SkuKeyPressed

        // sku validation for the second sku input field
    private void L5_New_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_New_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.secondSkuInput = L5_New_Sku.getText();
            if (!L5_New_Sku.getText().equals(L5_First_Sku.getText())) {
                
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L5_New_Sku.getText().matches("[0-9]+") && L5_New_Sku.getText().length() < 7 ) {
                    L5_New_Sku.setBackground(Color.WHITE);
                    L5_New_Desc.setText(IASdao.secondSkuDesc);
                    L5_New_Attr.setText(IASdao.secondSkuAttr);
                    L5_New_Size.setText(IASdao.secondSkuSize);
                    L5_Qty_In.requestFocus();
                    L5_Qty_In.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L5_New_Sku.requestFocus();
                    L5_New_Sku.setBackground(Color.yellow);
                }
            } else if (L5_New_Sku.getText().equals("") || L5_New_Sku.getText().equals("0")) {
                L5_New_Sku.setText("0");
                L5_New_Desc.setText("");
                L5_New_Attr.setText("");
                L5_New_Size.setText("");
                L5_Qty_In.setText("0");
                L5_Done_ChkBox.requestFocus();
            } else {
                L5_New_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                L5_New_Sku.requestFocus();
            }                
            } else {
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                L5_New_Sku.requestFocus();
                L5_New_Sku.setBackground(Color.yellow);
            }
        }
    }//GEN-LAST:event_L5_New_SkuKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L5_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Done_ChkBoxKeyPressed
            L5_Done_ChkBox.setSelected(true);
            rChkLn5();
            if (L5_Done_ChkBox.isSelected() == true) {
                String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L5_First_Sku.getText();
                IASdao.lnFlg = 5;
                /*
                if (L5_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine5();
                    L5_Qty_Out.setText("");
                    L5_First_Desc.setText("");
                    L5_First_Attr.setText("");
                    L5_First_Size.setText("");
                    L5_Orig_Retail.setText("");
                    L5_Reason_DropDown.setSelectedItem("");
                    L5_Desc_Damage.setText("");
                    L5_New_Sku.setText("");
                    L5_New_Desc.setText("");
                    L5_New_Attr.setText("");
                    L5_New_Size.setText("");
                    L5_Qty_In.setText("");
                    L5_Done_ChkBox.setSelected(false);
                    L5_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (L5_First_Sku.getText().matches("[0-9]+") && L5_First_Sku.getText().length() < 7  || (L5_First_Sku.getText().equals("") || (L5_First_Sku.getText().equals("0")))) {
                
                if (IASdao.skuRslt.equals("good") || (L5_First_Sku.getText().equals("") || (L5_First_Sku.getText().equals("0")))) {
                    L5_First_Sku.setBackground(Color.WHITE);
                    L5_First_Desc.setText(IASdao.firstSkuDesc);
                    L5_First_Attr.setText(IASdao.firstSkuAttr);
                    L5_First_Size.setText(IASdao.firstSkuSize);
                    L5_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L5_First_Sku.getText().equals("") || (L5_First_Sku.getText().equals("0"))) {
                    L5_First_Sku.setText("0");
                    L5_Qty_Out.setText("0");
                    L5_First_Desc.setText("");
                    L5_First_Attr.setText("");
                    L5_First_Size.setText("");
                    L5_Orig_Retail.setText("0.00");
                    L5_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL5_Cost();
                    IASdao.firstDCS = getfDcs5();
                    IASdao.firstSkuVendor = getfVen5();
                    IASdao.firstSkuVPNum = getfVpn5();
                }
                    
                if (L5_Qty_Out.getText().matches("[1-9]+") && L5_Qty_Out.getText().length() < 2 || L5_Qty_Out.getText().matches("0") && L5_First_Sku.getText().matches("0")) {
                    L5_Qty_Out.setBackground(Color.WHITE);
                    
                if (!L5_Reason_DropDown.getSelectedItem().equals("") && !L5_First_Sku.getText().equals("0") || L5_Reason_DropDown.getSelectedItem().equals("") && L5_First_Sku.getText().matches("0")) {
                                        
                if (L5_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L5_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L5_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if (IASdao.skuRslt2.equals("good") && L5_New_Sku.getText().matches("[0-9]+") && L5_New_Sku.getText().length() < 7 || L5_New_Sku.getText().equals("0") || L5_New_Sku.getText().equals("")) {
                        L5_New_Sku.setBackground(Color.WHITE);
                        L5_New_Desc.setText(IASdao.secondSkuDesc);
                        L5_New_Attr.setText(IASdao.secondSkuAttr);
                        L5_New_Size.setText(IASdao.secondSkuSize);
                        
                    if (L5_New_Sku.getText().equals("") || L5_New_Sku.getText().equals("0")) {                                                
                        L5_New_Sku.setText("0");
                        L5_New_Desc.setText("");
                        L5_New_Attr.setText("");
                        L5_New_Size.setText("");
                        L5_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL5_Cost();
                        IASdao.secondDCS = getsDcs5();
                        IASdao.secondSkuVendor = getsVen5();
                        IASdao.secondSkuVPNum = getsVpn5();
                    }
                
                if (L5_Qty_In.getText().matches("[1-9]+") && L5_Qty_In.getText().length() < 2 || L5_Qty_In.getText().matches("0") && L5_New_Sku.getText().matches("0")) {
                    L5_Qty_In.setBackground(Color.WHITE);
                    
                if (!L5_New_Sku.getText().equals(L5_First_Sku.getText())) {
                    L5_New_Sku.setBackground(Color.WHITE); 
                    
                if ((!L5_First_Sku.getText().equals("0") && L5_New_Sku.getText().equals("0")) || (L5_First_Sku.getText().equals("0") && !L5_New_Sku.getText().equals("0"))
                    || (!L5_First_Sku.getText().equals("0") && !L5_New_Sku.getText().equals("0"))) {
                    
                    IASdao.Field1 = L5_First_Sku.getText();
                    IASdao.Field2 = L5_Qty_Out.getText();
                    IASdao.Field3 = L5_First_Desc.getText();
                    IASdao.Field4 = L5_First_Attr.getText();
                    IASdao.Field5 = L5_First_Size.getText();
                    IASdao.Field6 = L5_Orig_Retail.getText();
                    IASdao.Field7 = L5_Reason_DropDown.getSelectedItem().toString();
                    IASdao.Field8 = L5_Desc_Damage.getText();
                    IASdao.Field9 = L5_New_Sku.getText();
                    IASdao.Field10 = L5_New_Desc.getText();
                    IASdao.Field11 = L5_New_Attr.getText();
                    IASdao.Field12 = L5_New_Size.getText();
                    IASdao.Field13 = L5_Qty_In.getText();
                    IASdao.rline = L5.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L5.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L5_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L5_Done_ChkBox.setSelected(false);
                        L5_New_Sku.requestFocus();
                        L5_New_Sku.setBackground(Color.yellow);                        
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L5_Done_ChkBox.setSelected(false);
                        L5_Qty_In.requestFocus();
                        L5_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L5_Done_ChkBox.setSelected(false);
                        L5_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L5_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L5_Done_ChkBox.setSelected(false);
                        L5_Desc_Damage.requestFocus();
                        L5_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n\n" 
                                + "NOTE: This is mandatory", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L5_Done_ChkBox.setSelected(false);
                        L5_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L5_Done_ChkBox.setSelected(false);
                        L5_Qty_Out.requestFocus();
                        L5_Qty_Out.setBackground(Color.yellow);
                    } 
                    } else {
                        L5_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L5_Done_ChkBox.setSelected(false);
                        L5_First_Sku.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L5_Done_ChkBox.setSelected(false);
                    L5_First_Sku.requestFocus();
                    L5_First_Sku.setBackground(Color.yellow);
                }
            //    }
        } else {
            L5_First_Sku.setEnabled(true);
            L5_Qty_Out.setEnabled(true);
            L5_First_Desc.setEnabled(true);
            L5_First_Attr.setEnabled(true);
            L5_First_Size.setEnabled(true);
            L5_Orig_Retail.setEnabled(true);
            L5_Reason_DropDown.setEnabled(true);
            L5_Desc_Damage.setEnabled(true);
            L5_New_Sku.setEnabled(true);
            L5_New_Desc.setEnabled(true);
            L5_New_Attr.setEnabled(true);
            L5_New_Size.setEnabled(true);
            L5_Qty_In.setEnabled(true);
            }
            manager.focusNextComponent();
    }//GEN-LAST:event_L5_Done_ChkBoxKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L5_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L5_Done_ChkBoxActionPerformed
           rChkLn5();
            if (L5_Done_ChkBox.isSelected() == true) {
            String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L5_First_Sku.getText();
                IASdao.lnFlg = 5;
                /*
                if (L5_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine5();
                    L5_Qty_Out.setText("");
                    L5_First_Desc.setText("");
                    L5_First_Attr.setText("");
                    L5_First_Size.setText("");
                    L5_Orig_Retail.setText("");
                    L5_Reason_DropDown.setSelectedItem("");
                    L5_Desc_Damage.setText("");
                    L5_New_Sku.setText("");
                    L5_New_Desc.setText("");
                    L5_New_Attr.setText("");
                    L5_New_Size.setText("");
                    L5_Qty_In.setText("");
                    L5_Done_ChkBox.setSelected(false);
                    L5_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (L5_First_Sku.getText().matches("[0-9]+") && L5_First_Sku.getText().length() < 7 || (L5_First_Sku.getText().equals("0") || L5_First_Sku.getText().equals(""))) {
                
                if (IASdao.skuRslt.equals("good") || (L5_First_Sku.getText().equals("") || (L5_First_Sku.getText().equals("0")))) {
                    L5_First_Sku.setBackground(Color.WHITE);
                    L5_First_Desc.setText(IASdao.firstSkuDesc);
                    L5_First_Attr.setText(IASdao.firstSkuAttr);
                    L5_First_Size.setText(IASdao.firstSkuSize);
                    L5_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L5_First_Sku.getText().equals("") || (L5_First_Sku.getText().equals("0"))) {
                    L5_First_Sku.setText("0");
                    L5_Qty_Out.setText("0");
                    L5_First_Desc.setText("");
                    L5_First_Attr.setText("");
                    L5_First_Size.setText("");
                    L5_Orig_Retail.setText("0.00");
                    L5_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL5_Cost();
                    IASdao.firstDCS = getfDcs5();
                    IASdao.firstSkuVendor = getfVen5();
                    IASdao.firstSkuVPNum = getfVpn5();
                }
                    
                if (L5_Qty_Out.getText().matches("[1-9]+") && L5_Qty_Out.getText().length() < 2 || L5_Qty_Out.getText().matches("0") && L5_First_Sku.getText().matches("0")) {
                    L5_Qty_Out.setBackground(Color.WHITE);
                    
                if (!L5_Reason_DropDown.getSelectedItem().equals("") && !L5_First_Sku.getText().equals("0") || L5_Reason_DropDown.getSelectedItem().equals("") && L5_First_Sku.getText().matches("0")) {
                                            
                if (L5_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L5_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L5_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if (IASdao.skuRslt2.equals("good") && L5_New_Sku.getText().matches("[0-9]+") && L5_New_Sku.getText().length() < 7 || L5_New_Sku.getText().equals("0") || L5_New_Sku.getText().equals("")) {
                        L5_New_Sku.setBackground(Color.WHITE);
                        L5_New_Desc.setText(IASdao.secondSkuDesc);
                        L5_New_Attr.setText(IASdao.secondSkuAttr);
                        L5_New_Size.setText(IASdao.secondSkuSize);
                        
                    if (L5_New_Sku.getText().equals("") || L5_New_Sku.getText().equals("0")) {                                                
                        L5_New_Sku.setText("0");
                        L5_New_Desc.setText("");
                        L5_New_Attr.setText("");
                        L5_New_Size.setText("");
                        L5_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL5_Cost();
                        IASdao.secondDCS = getsDcs5();
                        IASdao.secondSkuVendor = getsVen5();
                        IASdao.secondSkuVPNum = getsVpn5();
                    }
                
                if (L5_Qty_In.getText().matches("[1-9]+") && L5_Qty_In.getText().length() < 2 || L5_Qty_In.getText().matches("0")&& L5_New_Sku.getText().matches("0")) {
                    L5_Qty_In.setBackground(Color.WHITE);
                    
                if (!L5_New_Sku.getText().equals(L5_First_Sku.getText())) {
                    
                if ((!L5_First_Sku.getText().equals("0") && L5_New_Sku.getText().equals("0")) || (L5_First_Sku.getText().equals("0") && !L5_New_Sku.getText().equals("0"))
                    || (!L5_First_Sku.getText().equals("0") && !L5_New_Sku.getText().equals("0"))) {
                    
                    IASdao.Field1 = L5_First_Sku.getText();
                    IASdao.Field2 = L5_Qty_Out.getText();
                    IASdao.Field3 = L5_First_Desc.getText();
                    IASdao.Field4 = L5_First_Attr.getText();
                    IASdao.Field5 = L5_First_Size.getText();
                    IASdao.Field6 = L5_Orig_Retail.getText();
                    IASdao.Field7 = L5_Reason_DropDown.getSelectedItem().toString();
                    IASdao.Field8 = L5_Desc_Damage.getText();
                    IASdao.Field9 = L5_New_Sku.getText();
                    IASdao.Field10 = L5_New_Desc.getText();
                    IASdao.Field11 = L5_New_Attr.getText();
                    IASdao.Field12 = L5_New_Size.getText();
                    IASdao.Field13 = L5_Qty_In.getText();
                    IASdao.rline = L5.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L5.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L5_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L5_Done_ChkBox.setSelected(false);
                        L5_New_Sku.requestFocus();
                        L5_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);

                        L5_Done_ChkBox.setSelected(false);
                        L5_Qty_In.requestFocus();
                        L5_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L5_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L5_Done_ChkBox.setSelected(false);
                        L5_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L5_Done_ChkBox.setSelected(false);
                        L5_Desc_Damage.requestFocus();
                        L5_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n\n"
                                + "NOTE: This is only available for use with the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L5_Done_ChkBox.setSelected(false);
                        L5_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L5_Done_ChkBox.setSelected(false);
                        L5_Qty_Out.requestFocus();
                        L5_Qty_Out.setBackground(Color.yellow);
                    } 
                    } else {
                        L5_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L5_Done_ChkBox.setSelected(false);
                        L5_First_Sku.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L5_Done_ChkBox.setSelected(false);
                    L5_First_Sku.requestFocus();
                    L5_First_Sku.setBackground(Color.yellow);
                }
               // }
                    manager.focusNextComponent();
        } else {
            L5_First_Sku.setEnabled(true);
            L5_Qty_Out.setEnabled(true);
            L5_First_Desc.setEnabled(true);
            L5_First_Attr.setEnabled(true);
            L5_First_Size.setEnabled(true);
            L5_Orig_Retail.setEnabled(true);
            L5_Reason_DropDown.setEnabled(true);
            L5_Desc_Damage.setEnabled(true);
            L5_New_Sku.setEnabled(true);
            L5_New_Desc.setEnabled(true);
            L5_New_Attr.setEnabled(true);
            L5_New_Size.setEnabled(true);
            L5_Qty_In.setEnabled(true);
        } 
    }//GEN-LAST:event_L5_Done_ChkBoxActionPerformed

    // sku validation for the first sku input field
    private void L6_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_First_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.firstSkuInput = L6_First_Sku.getText();
            if (L6_First_Sku.getText().matches("[0-9]+") && L6_First_Sku.getText().length() < 7 || (L6_First_Sku.getText().equals("") || (L6_First_Sku.getText().equals("0")))) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L6_First_Sku.setBackground(Color.WHITE);
                L6_First_Desc.setText(IASdao.firstSkuDesc);
                L6_First_Attr.setText(IASdao.firstSkuAttr);
                L6_First_Size.setText(IASdao.firstSkuSize);
                L6_Orig_Retail.setText(IASdao.firstSkuPr);
                L6_Qty_Out.requestFocus();
                L6_Qty_Out.setText("");
            } else if (L6_First_Sku.getText().equals("") || (L6_First_Sku.getText().equals("0"))) {
                L6_First_Sku.setText("0");
                L6_Qty_Out.setText("0");
                L6_First_Desc.setText("");
                L6_First_Attr.setText("");
                L6_First_Size.setText("");
                L6_Orig_Retail.setText("0.00");
                L6_Reason_DropDown.setSelectedItem("");
                L6_Desc_Damage.requestFocus();
              //  IASdao.firstSkuCost = getL6_Cost();
            } else {
                L6_First_Sku.setBackground(Color.yellow);
                L6_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L6_First_Sku.requestFocus();
                L6_First_Sku.setBackground(Color.yellow);
            }
        }
    }//GEN-LAST:event_L6_First_SkuKeyPressed

    // sku validation for the second sku input field
    private void L6_New_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_New_SkuKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.secondSkuInput = L6_New_Sku.getText();
            if (!L6_New_Sku.getText().equals(L6_First_Sku.getText())) {
                
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L6_New_Sku.getText().matches("[0-9]+") && L6_New_Sku.getText().length() < 7 ) {
                    L6_New_Sku.setBackground(Color.WHITE);
                    L6_New_Desc.setText(IASdao.secondSkuDesc);
                    L6_New_Attr.setText(IASdao.secondSkuAttr);
                    L6_New_Size.setText(IASdao.secondSkuSize);
                    L6_Qty_In.requestFocus();
                    L6_Qty_In.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L6_New_Sku.requestFocus();
                    L6_New_Sku.setBackground(Color.yellow);
                }
            } else if (L6_New_Sku.getText().equals("") || L6_New_Sku.getText().equals("0")) {
                L6_New_Sku.setText("0");
                L6_New_Desc.setText("");
                L6_New_Attr.setText("");
                L6_New_Size.setText("");
                L6_Qty_In.setText("0");
                L6_Done_ChkBox.requestFocus();
            } else {
                L6_New_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                L6_New_Sku.requestFocus();
            }            
        } else {
               JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
               L6_New_Sku.requestFocus();
               L6_New_Sku.setBackground(Color.yellow);
            }
         }
    }//GEN-LAST:event_L6_New_SkuKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L6_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Done_ChkBoxKeyPressed
            L6_Done_ChkBox.setSelected(true);
            rChkLn6();
            if (L6_Done_ChkBox.isSelected() == true) {
                String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L6_First_Sku.getText();
                IASdao.lnFlg = 6;
                /*
                if (L6_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine6();
                    L6_Qty_Out.setText("");
                    L6_First_Desc.setText("");
                    L6_First_Attr.setText("");
                    L6_First_Size.setText("");
                    L6_Orig_Retail.setText("");
                    L6_Reason_DropDown.setSelectedItem("");
                    L6_Desc_Damage.setText("");
                    L6_New_Sku.setText("");
                    L6_New_Desc.setText("");
                    L6_New_Attr.setText("");
                    L6_New_Size.setText("");
                    L6_Qty_In.setText("");
                    L6_Done_ChkBox.setSelected(false);
                    L6_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (L6_First_Sku.getText().matches("[0-9]+") && L6_First_Sku.getText().length() < 7 || (L6_First_Sku.getText().equals("0") || L6_First_Sku.getText().equals(""))) {
                    L6_First_Sku.setBackground(Color.WHITE);
                
                if (IASdao.skuRslt.equals("good") || (L6_First_Sku.getText().equals("") || (L6_First_Sku.getText().equals("0")))) {
                    L6_First_Sku.setBackground(Color.WHITE);
                    L6_First_Desc.setText(IASdao.firstSkuDesc);
                    L6_First_Attr.setText(IASdao.firstSkuAttr);
                    L6_First_Size.setText(IASdao.firstSkuSize);
                    L6_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L6_First_Sku.getText().equals("") || (L6_First_Sku.getText().equals("0"))) {
                    L6_First_Sku.setText("0");
                    L6_Qty_Out.setText("0");
                    L6_First_Desc.setText("");
                    L6_First_Attr.setText("");
                    L6_First_Size.setText("");
                    L6_Orig_Retail.setText("0.00");
                    L6_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL6_Cost();
                    IASdao.firstDCS = getfDcs6();
                    IASdao.firstSkuVendor = getfVen6();
                    IASdao.firstSkuVPNum = getfVpn6();
                }
                    
                if (L6_Qty_Out.getText().matches("[1-9]+") && L6_Qty_Out.getText().length() < 2 || L6_Qty_Out.getText().matches("0") && L6_First_Sku.getText().matches("0")) {
                    L6_Qty_Out.setBackground(Color.WHITE);
                    
                if (!L6_Reason_DropDown.getSelectedItem().equals("")&& !L6_First_Sku.getText().equals("0") || L6_Reason_DropDown.getSelectedItem().equals("") && L6_First_Sku.getText().matches("0")) {
                    
                if (L6_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L6_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L6_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if (IASdao.skuRslt2.equals("good") && L6_New_Sku.getText().matches("[0-9]+") && L6_New_Sku.getText().length() < 7 || L6_New_Sku.getText().equals("0") || L6_New_Sku.getText().equals("")) {
                        L6_New_Sku.setBackground(Color.WHITE);
                        L6_New_Desc.setText(IASdao.secondSkuDesc);
                        L6_New_Attr.setText(IASdao.secondSkuAttr);
                        L6_New_Size.setText(IASdao.secondSkuSize); 
                        
                    if (L6_New_Sku.getText().equals("") || L6_New_Sku.getText().equals("0")) {                                                
                        L6_New_Sku.setText("0");
                        L6_New_Desc.setText("");
                        L6_New_Attr.setText("");
                        L6_New_Size.setText("");
                        L6_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL6_Cost();
                        IASdao.secondDCS = getsDcs6();
                        IASdao.secondSkuVendor = getsVen6();
                        IASdao.secondSkuVPNum = getsVpn6();
                    }
                
                if (L6_Qty_In.getText().matches("[1-9]+") && L6_Qty_In.getText().length() < 2 || L6_Qty_In.getText().matches("0") && L6_New_Sku.getText().matches("0")) {
                    L6_Qty_In.setBackground(Color.WHITE);
                    
                if (!L6_New_Sku.getText().equals(L6_First_Sku.getText())) {
                    L6_New_Sku.setBackground(Color.WHITE);
                    
                if ((!L6_First_Sku.getText().equals("0") && L6_New_Sku.getText().equals("0")) || (L6_First_Sku.getText().equals("0") && !L6_New_Sku.getText().equals("0"))
                    || (!L6_First_Sku.getText().equals("0") && !L6_New_Sku.getText().equals("0"))) {
                    
                    IASdao.Field1 = L6_First_Sku.getText();
                    IASdao.Field2 = L6_Qty_Out.getText();
                    IASdao.Field3 = L6_First_Desc.getText();
                    IASdao.Field4 = L6_First_Attr.getText();
                    IASdao.Field5 = L6_First_Size.getText();
                    IASdao.Field6 = L6_Orig_Retail.getText();
                    IASdao.Field7 = L6_Reason_DropDown.getSelectedItem().toString();                    
                    IASdao.Field8 = L6_Desc_Damage.getText();                    
                    IASdao.Field9 = L6_New_Sku.getText();
                    IASdao.Field10 = L6_New_Desc.getText();
                    IASdao.Field11 = L6_New_Attr.getText();
                    IASdao.Field12 = L6_New_Size.getText();
                    IASdao.Field13 = L6_Qty_In.getText();
                    IASdao.rline = L6.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L6.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L6_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L6_Done_ChkBox.setSelected(false);
                        L6_New_Sku.requestFocus();
                        L6_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L6_Done_ChkBox.setSelected(false);
                        L6_Qty_In.requestFocus();
                        L6_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L6_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L6_Done_ChkBox.setSelected(false);
                        L6_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L6_Done_ChkBox.setSelected(false);
                        L6_Desc_Damage.requestFocus();
                        L6_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n\n" 
                                + "NOTE: This is only available for use with the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L6_Done_ChkBox.setSelected(false);
                        L6_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L6_Done_ChkBox.setSelected(false);
                        L6_Qty_Out.requestFocus();
                        L6_Qty_Out.setBackground(Color.yellow);
                    }
                    } else {
                        L6_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L6_Done_ChkBox.setSelected(false);
                        L6_First_Sku.requestFocus();
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
                L6_First_Attr.setEnabled(true);
                L6_First_Size.setEnabled(true);
                L6_Orig_Retail.setEnabled(true);
                L6_Reason_DropDown.setEnabled(true);
                L6_Desc_Damage.setEnabled(true);
                L6_New_Sku.setEnabled(true);
                L6_New_Desc.setEnabled(true);
                L6_New_Attr.setEnabled(true);
                L6_New_Size.setEnabled(true);
                L6_Qty_In.setEnabled(true);
            }
            manager.focusNextComponent();
    }//GEN-LAST:event_L6_Done_ChkBoxKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L6_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L6_Done_ChkBoxActionPerformed
           rChkLn6();
            if (L6_Done_ChkBox.isSelected() == true) {
            String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L6_First_Sku.getText();
                IASdao.lnFlg = 6;
                /*
                if (L6_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine6();
                    L6_Qty_Out.setText("");
                    L6_First_Desc.setText("");
                    L6_First_Attr.setText("");
                    L6_First_Size.setText("");
                    L6_Orig_Retail.setText("");
                    L6_Reason_DropDown.setSelectedItem("");
                    L6_Desc_Damage.setText("");
                    L6_New_Sku.setText("");
                    L6_New_Desc.setText("");
                    L6_New_Attr.setText("");
                    L6_New_Size.setText("");
                    L6_Qty_In.setText("");
                    L6_Done_ChkBox.setSelected(false);
                    L6_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (L6_First_Sku.getText().matches("[0-9]+") && L6_First_Sku.getText().length() < 7 || (L6_First_Sku.getText().equals("0") || L6_First_Sku.getText().equals(""))) {
                
                if (IASdao.skuRslt.equals("good") || (L6_First_Sku.getText().equals("") || (L6_First_Sku.getText().equals("0")))) {
                    L6_First_Sku.setBackground(Color.WHITE);
                    L6_First_Desc.setText(IASdao.firstSkuDesc);
                    L6_First_Attr.setText(IASdao.firstSkuAttr);
                    L6_First_Size.setText(IASdao.firstSkuSize);
                    L6_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L6_First_Sku.getText().equals("") || (L6_First_Sku.getText().equals("0"))) {
                    L6_First_Sku.setText("0");
                    L6_Qty_Out.setText("0");
                    L6_First_Desc.setText("");
                    L6_First_Attr.setText("");
                    L6_First_Size.setText("");
                    L6_Orig_Retail.setText("0.00");
                    L6_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL6_Cost();
                    IASdao.firstDCS = getfDcs6();
                    IASdao.firstSkuVendor = getfVen6();
                    IASdao.firstSkuVPNum = getfVpn6();
                }
                    
                if (L6_Qty_Out.getText().matches("[1-9]+") && L6_Qty_Out.getText().length() < 2 || L6_Qty_Out.getText().matches("0") && L6_First_Sku.getText().matches("0")) {
                    L6_Qty_Out.setBackground(Color.WHITE);
                    
                if (!L6_Reason_DropDown.getSelectedItem().equals("")&& !L6_First_Sku.getText().equals("0") || L6_Reason_DropDown.getSelectedItem().equals("") && L6_First_Sku.getText().matches("0")) {
                    
                if (L6_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L6_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L6_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if (IASdao.skuRslt2.equals("good") && L6_New_Sku.getText().matches("[0-9]+") && L6_New_Sku.getText().length() < 7 || L6_New_Sku.getText().equals("0") || L6_New_Sku.getText().equals("")) {
                        L6_New_Sku.setBackground(Color.WHITE);
                        L6_New_Desc.setText(IASdao.secondSkuDesc);
                        L6_New_Attr.setText(IASdao.secondSkuAttr);
                        L6_New_Size.setText(IASdao.secondSkuSize);
                         
                    if (L6_New_Sku.getText().equals("") || L6_New_Sku.getText().equals("0")) {                                                
                        L6_New_Sku.setText("0");
                        L6_New_Desc.setText("");
                        L6_New_Attr.setText("");
                        L6_New_Size.setText("");
                        L6_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL6_Cost();
                        IASdao.secondDCS = getsDcs6();
                        IASdao.secondSkuVendor = getsVen6();
                        IASdao.secondSkuVPNum = getsVpn6();
                    }
                
                if (L6_Qty_In.getText().matches("[1-9]+") && L6_Qty_In.getText().length() < 2 || L6_Qty_In.getText().matches("0") && L6_New_Sku.getText().matches("0")) {
                    L6_Qty_In.setBackground(Color.WHITE);
                    
                if (!L6_New_Sku.getText().equals(L6_First_Sku.getText())) {
                    
                if ((!L6_First_Sku.getText().equals("0") && L6_New_Sku.getText().equals("0")) || (L6_First_Sku.getText().equals("0") && !L6_New_Sku.getText().equals("0"))
                    || (!L6_First_Sku.getText().equals("0") && !L6_New_Sku.getText().equals("0"))) {
                    
                    IASdao.Field1 = L6_First_Sku.getText();
                    IASdao.Field2 = L6_Qty_Out.getText();
                    IASdao.Field3 = L6_First_Desc.getText();
                    IASdao.Field4 = L6_First_Attr.getText();
                    IASdao.Field5 = L6_First_Size.getText();
                    IASdao.Field6 = L6_Orig_Retail.getText();
                    IASdao.Field7 = L6_Reason_DropDown.getSelectedItem().toString();
                    IASdao.Field8 = L6_Desc_Damage.getText();
                    IASdao.Field9 = L6_New_Sku.getText();
                    IASdao.Field10 = L6_New_Desc.getText();
                    IASdao.Field11 = L6_New_Attr.getText();
                    IASdao.Field12 = L6_New_Size.getText();
                    IASdao.Field13 = L6_Qty_In.getText();
                    IASdao.rline = L6.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L6.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L6_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L6_Done_ChkBox.setSelected(false);
                        L6_New_Sku.requestFocus();
                        L6_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L6_Done_ChkBox.setSelected(false);
                        L6_Qty_In.requestFocus();
                        L6_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L6_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L6_Done_ChkBox.setSelected(false);
                        L6_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L6_Done_ChkBox.setSelected(false);
                        L6_Desc_Damage.requestFocus();
                        L6_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n\n" 
                                + "NOTE: This is only available for use with the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L6_Done_ChkBox.setSelected(false);
                        L6_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L6_Done_ChkBox.setSelected(false);
                        L6_Qty_Out.requestFocus();
                        L6_Qty_Out.setBackground(Color.yellow);
                    } 
                    } else {
                        L6_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L6_Done_ChkBox.setSelected(false);
                        L6_First_Sku.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L6_Done_ChkBox.setSelected(false);
                    L6_First_Sku.requestFocus();
                    L6_First_Sku.setBackground(Color.yellow);
                }
             //   }
        } else {
            L6_First_Sku.setEnabled(true);
            L6_Qty_Out.setEnabled(true);
            L6_First_Desc.setEnabled(true);
            L6_First_Attr.setEnabled(true);
            L6_First_Size.setEnabled(true);
            L6_Orig_Retail.setEnabled(true);
            L6_Reason_DropDown.setEnabled(true);
            L6_Desc_Damage.setEnabled(true);
            L6_New_Sku.setEnabled(true);
            L6_New_Desc.setEnabled(true);
            L6_New_Attr.setEnabled(true);
            L6_New_Size.setEnabled(true);
            L6_Qty_In.setEnabled(true);
        }
            manager.focusNextComponent();
    }//GEN-LAST:event_L6_Done_ChkBoxActionPerformed

    // sku validation for the first sku input field
    private void L7_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_First_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.firstSkuInput = L7_First_Sku.getText();
            if (L7_First_Sku.getText().matches("[0-9]+") && L7_First_Sku.getText().length() < 7 || (L7_First_Sku.getText().equals("") || (L7_First_Sku.getText().equals("0")))) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L7_First_Sku.setBackground(Color.WHITE);
                L7_First_Desc.setText(IASdao.firstSkuDesc);
                L7_First_Attr.setText(IASdao.firstSkuAttr);
                L7_First_Size.setText(IASdao.firstSkuSize);
                L7_Orig_Retail.setText(IASdao.firstSkuPr);
                L7_Qty_Out.requestFocus();
                L7_Qty_Out.setText("");
            } else if (L7_First_Sku.getText().equals("") || (L7_First_Sku.getText().equals("0"))) {
                L7_First_Sku.setText("0");
                L7_Qty_Out.setText("0");
                L7_First_Desc.setText("");
                L7_First_Attr.setText("");
                L7_First_Size.setText("");
                L7_Orig_Retail.setText("0.00");
                L7_Reason_DropDown.setSelectedItem("");
                L7_Desc_Damage.requestFocus();
              //  IASdao.firstSkuCost = getL7_Cost();
            } else {                
                L7_First_Sku.setBackground(Color.yellow);
                L7_First_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 numerical values", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L7_First_Sku.requestFocus();
                L7_First_Sku.setBackground(Color.yellow);
            }
        }
    }//GEN-LAST:event_L7_First_SkuKeyPressed

    // sku validation for the second sku input field
    private void L7_New_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_New_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.secondSkuInput = L7_New_Sku.getText();
            if (!L7_New_Sku.getText().equals(L7_First_Sku.getText())) {
                
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L7_New_Sku.getText().matches("[0-9]+") && L7_New_Sku.getText().length() < 7 ) {
                    L7_New_Sku.setBackground(Color.WHITE);
                    L7_New_Desc.setText(IASdao.secondSkuDesc);
                    L7_New_Attr.setText(IASdao.secondSkuAttr);
                    L7_New_Size.setText(IASdao.secondSkuSize);
                    L7_Qty_In.requestFocus();
                    L7_Qty_In.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 numerical values", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L7_New_Sku.setBackground(Color.yellow);
                 }
            } else if (L7_New_Sku.getText().equals("") || L7_New_Sku.getText().equals("0")) {
                L7_New_Sku.setText("0");
                L7_New_Desc.setText("");
                L7_New_Attr.setText("");
                L7_New_Size.setText("");
                L7_Qty_In.setText("0");
                L7_Done_ChkBox.requestFocus();
            } else {
                L7_New_Sku.setBackground(Color.yellow);
                L7_New_Sku.requestFocus();
               JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
            }            
        } else {
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                L7_New_Sku.requestFocus();
                L7_New_Sku.setBackground(Color.yellow);
            }
       }
    }//GEN-LAST:event_L7_New_SkuKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L7_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Done_ChkBoxKeyPressed
            L7_Done_ChkBox.setSelected(true);
            rChkLn7();
            if (L7_Done_ChkBox.isSelected() == true) {
                String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L7_First_Sku.getText();
                IASdao.lnFlg = 7;
                /*
                if (L7_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine7();
                    L7_Qty_Out.setText("");
                    L7_First_Desc.setText("");
                    L7_First_Attr.setText("");
                    L7_First_Size.setText("");
                    L7_Orig_Retail.setText("");
                    L7_Reason_DropDown.setSelectedItem("");
                    L7_Desc_Damage.setText("");
                    L7_New_Sku.setText("");
                    L7_New_Desc.setText("");
                    L7_New_Attr.setText("");
                    L7_New_Size.setText("");
                    L7_Qty_In.setText("");
                    L7_Done_ChkBox.setSelected(false);
                    L7_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                if (L7_First_Sku.getText().matches("[0-9]+") && L7_First_Sku.getText().length() < 7 || (L7_First_Sku.getText().equals("0") || L7_First_Sku.getText().equals(""))) {
                
                if (IASdao.skuRslt.equals("good") || (L7_First_Sku.getText().equals("") || (L7_First_Sku.getText().equals("0")))) {
                    L7_First_Sku.setBackground(Color.WHITE);
                    L7_First_Desc.setText(IASdao.firstSkuDesc);
                    L7_First_Attr.setText(IASdao.firstSkuAttr);
                    L7_First_Size.setText(IASdao.firstSkuSize);
                    L7_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L7_First_Sku.getText().equals("") || (L7_First_Sku.getText().equals("0"))) {
                    L7_First_Sku.setText("0");
                    L7_Qty_Out.setText("0");
                    L7_First_Desc.setText("");
                    L7_First_Attr.setText("");
                    L7_First_Size.setText("");
                    L7_Orig_Retail.setText("0.00");
                    L7_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL7_Cost();
                    IASdao.firstDCS = getfDcs7();
                    IASdao.firstSkuVendor = getfVen7();
                    IASdao.firstSkuVPNum = getfVpn7();
                }
                    
                if (L7_Qty_Out.getText().matches("[1-9]+") && L7_Qty_Out.getText().length() < 2 || L7_Qty_Out.getText().matches("0") && L7_First_Sku.getText().matches("0")) {
                    L7_Qty_Out.setBackground(Color.WHITE);
                    
                if (!L7_Reason_DropDown.getSelectedItem().equals("")&& !L7_First_Sku.getText().equals("0") || L7_Reason_DropDown.getSelectedItem().equals("") && L7_First_Sku.getText().matches("0")) {
                    
                if (L7_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L7_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L7_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if (IASdao.skuRslt2.equals("good") && L7_New_Sku.getText().matches("[0-9]+") && L7_New_Sku.getText().length() < 7 || L7_New_Sku.getText().equals("0") || L7_New_Sku.getText().equals("")) {
                        L7_New_Sku.setBackground(Color.WHITE);
                        L7_New_Desc.setText(IASdao.secondSkuDesc);
                        L7_New_Attr.setText(IASdao.secondSkuAttr);
                        L7_New_Size.setText(IASdao.secondSkuSize);
                         
                    if (L7_New_Sku.getText().equals("") || L7_New_Sku.getText().equals("0")) {                                                
                        L7_New_Sku.setText("0");
                        L7_New_Desc.setText("");
                        L7_New_Attr.setText("");
                        L7_New_Size.setText("");
                        L7_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL7_Cost();
                        IASdao.secondDCS = getsDcs7();
                        IASdao.secondSkuVendor = getsVen7();
                        IASdao.secondSkuVPNum = getsVpn7();
                    }
                
                if (L7_Qty_In.getText().matches("[1-9]+") && L7_Qty_In.getText().length() < 2 || L7_Qty_In.getText().matches("0")&& L7_New_Sku.getText().matches("0")) {
                    L7_Qty_In.setBackground(Color.WHITE);
                    
                if (!L7_New_Sku.getText().equals(L7_First_Sku.getText())) {
                    L7_New_Sku.setBackground(Color.WHITE);
                    
                if ((!L7_First_Sku.getText().equals("0") && L7_New_Sku.getText().equals("0")) || (L7_First_Sku.getText().equals("0") && !L7_New_Sku.getText().equals("0"))
                    || (!L7_First_Sku.getText().equals("0") && !L7_New_Sku.getText().equals("0"))) {
                    
                    IASdao.Field1 = L7_First_Sku.getText();
                    IASdao.Field2 = L7_Qty_Out.getText();
                    IASdao.Field3 = L7_First_Desc.getText();
                    IASdao.Field4 = L7_First_Attr.getText();
                    IASdao.Field5 = L7_First_Size.getText();
                    IASdao.Field6 = L7_Orig_Retail.getText();
                    IASdao.Field7 = L7_Reason_DropDown.getSelectedItem().toString();
                    IASdao.Field8 = L7_Desc_Damage.getText();
                    IASdao.Field9 = L7_New_Sku.getText();
                    IASdao.Field10 = L7_New_Desc.getText();
                    IASdao.Field11 = L7_New_Attr.getText();
                    IASdao.Field12 = L7_New_Size.getText();
                    IASdao.Field13 = L7_Qty_In.getText();
                    IASdao.rline = L7.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L7.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L7_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L7_Done_ChkBox.setSelected(false);
                        L7_New_Sku.requestFocus();
                        L7_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L7_Done_ChkBox.setSelected(false);
                        L7_Qty_In.requestFocus();
                        L7_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L7_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L7_Done_ChkBox.setSelected(false);
                        L7_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L7_Done_ChkBox.setSelected(false);
                        L7_Desc_Damage.requestFocus();
                        L7_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n\n" 
                                + "NOTE: This is only available for use with the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L7_Done_ChkBox.setSelected(false);
                        L7_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L7_Done_ChkBox.setSelected(false);
                        L7_Qty_Out.requestFocus();
                        L7_Qty_Out.setBackground(Color.yellow);
                    } 
                    } else {
                        L7_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L7_Done_ChkBox.setSelected(false);
                        L7_First_Sku.requestFocus();
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
                L7_First_Attr.setEnabled(true);
                L7_First_Size.setEnabled(true);
                L7_Orig_Retail.setEnabled(true);
                L7_Reason_DropDown.setEnabled(true);
                L7_Desc_Damage.setEnabled(true);
                L7_New_Sku.setEnabled(true);
                L7_New_Desc.setEnabled(true);
                L7_New_Attr.setEnabled(true);
                L7_New_Size.setEnabled(true);
                L7_Qty_In.setEnabled(true);
            }
            manager.focusNextComponent();
    }//GEN-LAST:event_L7_Done_ChkBoxKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L7_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L7_Done_ChkBoxActionPerformed
           rChkLn7();
        if (L7_Done_ChkBox.isSelected() == true) {
           String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L7_First_Sku.getText();
                IASdao.lnFlg = 7;
                /*
                if (L7_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine7();
                    L7_Qty_Out.setText("");
                    L7_First_Desc.setText("");
                    L7_First_Attr.setText("");
                    L7_First_Size.setText("");
                    L7_Orig_Retail.setText("");
                    L7_Reason_DropDown.setSelectedItem("");
                    L7_Desc_Damage.setText("");
                    L7_New_Sku.setText("");
                    L7_New_Desc.setText("");
                    L7_New_Attr.setText("");
                    L7_New_Size.setText("");
                    L7_Qty_In.setText("");
                    L7_Done_ChkBox.setSelected(false);
                    L7_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (L7_First_Sku.getText().matches("[0-9]+") && L7_First_Sku.getText().length() < 7 || (L7_First_Sku.getText().equals("0") || L7_First_Sku.getText().equals(""))) {
                
                if (IASdao.skuRslt.equals("good")|| (L7_First_Sku.getText().equals("") || (L7_First_Sku.getText().equals("0")))) {
                    L7_First_Sku.setBackground(Color.WHITE);
                    L7_First_Desc.setText(IASdao.firstSkuDesc);
                    L7_First_Attr.setText(IASdao.firstSkuAttr);
                    L7_First_Size.setText(IASdao.firstSkuSize);
                    L7_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L7_First_Sku.getText().equals("") || (L7_First_Sku.getText().equals("0"))) {
                    L7_First_Sku.setText("0");
                    L7_Qty_Out.setText("0");
                    L7_First_Desc.setText("");
                    L7_First_Attr.setText("");
                    L7_First_Size.setText("");
                    L7_Orig_Retail.setText("0.00");
                    L7_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL7_Cost();
                    IASdao.firstDCS = getfDcs7();
                    IASdao.firstSkuVendor = getfVen7();
                    IASdao.firstSkuVPNum = getfVpn7();
                }
                    
                if (L7_Qty_Out.getText().matches("[1-9]+") && L7_Qty_Out.getText().length() < 2 || L7_Qty_Out.getText().matches("0") && L7_First_Sku.getText().matches("0")) {                      
                    L7_Qty_Out.setBackground(Color.WHITE);
                    
                if (!L7_Reason_DropDown.getSelectedItem().equals("")&& !L7_First_Sku.getText().equals("0") || L7_Reason_DropDown.getSelectedItem().equals("") && L7_First_Sku.getText().matches("0")) {
                    
                if (L7_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L7_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L7_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if (IASdao.skuRslt2.equals("good") && L7_New_Sku.getText().matches("[0-9]+") && L7_New_Sku.getText().length() < 7 || L7_New_Sku.getText().equals("0") || L7_New_Sku.getText().equals("")) {
                        L7_New_Sku.setBackground(Color.WHITE);
                        L7_New_Desc.setText(IASdao.secondSkuDesc);
                        L7_New_Attr.setText(IASdao.secondSkuAttr);
                        L7_New_Size.setText(IASdao.secondSkuSize);
                        
                    if (L7_New_Sku.getText().equals("") || L7_New_Sku.getText().equals("0")) {                                                
                        L7_New_Sku.setText("0");
                        L7_New_Desc.setText("");
                        L7_New_Attr.setText("");
                        L7_New_Size.setText("");
                        L7_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL7_Cost();
                        IASdao.secondDCS = getsDcs7();
                        IASdao.secondSkuVendor = getsVen7();
                        IASdao.secondSkuVPNum = getsVpn7();
                    }
                
                if (L7_Qty_In.getText().matches("[1-9]+") && L7_Qty_In.getText().length() < 2 || L7_Qty_In.getText().matches("0") && L7_New_Sku.getText().matches("0")) {
                    
                if (!L7_New_Sku.getText().equals(L7_First_Sku.getText())) {
                    
                if ((!L7_First_Sku.getText().equals("0") && L7_New_Sku.getText().equals("0")) || (L7_First_Sku.getText().equals("0") && !L7_New_Sku.getText().equals("0"))
                    || (!L7_First_Sku.getText().equals("0") && !L7_New_Sku.getText().equals("0"))) {
    
                    IASdao.Field1 = L7_First_Sku.getText();
                    IASdao.Field2 = L7_Qty_Out.getText();
                    IASdao.Field3 = L7_First_Desc.getText();
                    IASdao.Field4 = L7_First_Attr.getText();
                    IASdao.Field5 = L7_First_Size.getText();
                    IASdao.Field6 = L7_Orig_Retail.getText();
                    IASdao.Field7 = L7_Reason_DropDown.getSelectedItem().toString();                    
                    IASdao.Field8 = L7_Desc_Damage.getText();                    
                    IASdao.Field9 = L7_New_Sku.getText();
                    IASdao.Field10 = L7_New_Desc.getText();
                    IASdao.Field11 = L7_New_Attr.getText();
                    IASdao.Field12 = L7_New_Size.getText();
                    IASdao.Field13 = L7_Qty_In.getText();
                    IASdao.rline = L7.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L7.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L7_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L7_Done_ChkBox.setSelected(false);
                        L7_New_Sku.requestFocus();
                        L7_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L7_Done_ChkBox.setSelected(false);
                        L7_Qty_In.requestFocus();                        
                        L7_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L7_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L7_Done_ChkBox.setSelected(false);
                        L7_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L7_Done_ChkBox.setSelected(false);
                        L7_Desc_Damage.requestFocus();
                        L7_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n\n"
                                + "NOTE: This is only available for use with the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L7_Done_ChkBox.setSelected(false);
                        L7_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L7_Done_ChkBox.setSelected(false);
                        L7_Qty_Out.requestFocus();
                        L7_Qty_Out.requestFocus();
                    } 
                    } else {
                        L7_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L7_Done_ChkBox.setSelected(false);
                        L7_First_Sku.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L7_Done_ChkBox.setSelected(false);
                    L7_First_Sku.requestFocus();
                    L7_First_Sku.setBackground(Color.yellow);
                }
             //   }
            manager.focusNextComponent();
        } else {
            L7_First_Sku.setEnabled(true);
            L7_Qty_Out.setEnabled(true);
            L7_First_Desc.setEnabled(true);
            L7_First_Attr.setEnabled(true);
            L7_First_Size.setEnabled(true);
            L7_Orig_Retail.setEnabled(true);
            L7_Reason_DropDown.setEnabled(true);
            L7_Desc_Damage.setEnabled(true);
            L7_New_Sku.setEnabled(true);
            L7_New_Desc.setEnabled(true);
            L7_New_Attr.setEnabled(true);
            L7_New_Size.setEnabled(true);
            L7_Qty_In.setEnabled(true);
        }      
    }//GEN-LAST:event_L7_Done_ChkBoxActionPerformed

    // sku validation for the first sku input field
    private void L8_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_First_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.firstSkuInput = L8_First_Sku.getText();
            if (L8_First_Sku.getText().matches("[0-9]+") && L8_First_Sku.getText().length() < 7 || (L8_First_Sku.getText().equals("") || (L8_First_Sku.getText().equals("0")))) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L8_First_Sku.setBackground(Color.WHITE);
                L8_First_Desc.setText(IASdao.firstSkuDesc);
                L8_First_Attr.setText(IASdao.firstSkuAttr);
                L8_First_Size.setText(IASdao.firstSkuSize);
                L8_Orig_Retail.setText(IASdao.firstSkuPr);
                L8_Qty_Out.requestFocus();
                L8_Qty_Out.setText("");
            } else if (L8_First_Sku.getText().equals("") || (L8_First_Sku.getText().equals("0"))) {
                L8_First_Sku.setText("0");
                L8_Qty_Out.setText("0");
                L8_First_Desc.setText("");
                L8_First_Attr.setText("");
                L8_First_Size.setText("");
                L8_Orig_Retail.setText("0.00");
                L8_Reason_DropDown.setSelectedItem("");
                L8_Desc_Damage.requestFocus();
             //   IASdao.firstSkuCost = getL8_Cost();  
            } else {
                L8_First_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                L8_First_Sku.requestFocus();                 
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 numerical values", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L8_First_Sku.requestFocus();
                L8_First_Sku.setBackground(Color.yellow);
            }
        }
    }//GEN-LAST:event_L8_First_SkuKeyPressed

    // sku validation for the second sku input field
    private void L8_New_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_New_SkuKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.secondSkuInput = L8_New_Sku.getText();
            if (!L8_New_Sku.getText().equals(L8_First_Sku.getText())) {
                
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L8_New_Sku.getText().matches("[0-9]+") && L8_New_Sku.getText().length() < 7 ) {
                    L8_New_Sku.setBackground(Color.WHITE);
                    L8_New_Desc.setText(IASdao.secondSkuDesc);
                    L8_New_Attr.setText(IASdao.secondSkuAttr);
                    L8_New_Size.setText(IASdao.secondSkuSize);
                    L8_Qty_In.requestFocus();
                    L8_Qty_In.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 numerical values", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L8_New_Sku.requestFocus();
                    L8_New_Sku.setBackground(Color.yellow);
                 }
            } else if (L8_New_Sku.getText().equals("") || L8_New_Sku.getText().equals("0")) {
                L8_New_Sku.setText("0");
                L8_New_Desc.setText("");
                L8_New_Attr.setText("");
                L8_New_Size.setText("");
                L8_Qty_In.setText("0");
                L8_Done_ChkBox.requestFocus();
            } else {
                L8_Done_ChkBox.setSelected(false);
                L8_New_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                L8_New_Sku.requestFocus();
            }            
        } else {
            JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
            L8_New_Sku.requestFocus();
            L8_New_Sku.setBackground(Color.yellow);
        }
       }
    }//GEN-LAST:event_L8_New_SkuKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L8_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Done_ChkBoxKeyPressed
            L8_Done_ChkBox.setSelected(true);
            rChkLn8();
            if (L8_Done_ChkBox.isSelected() == true) {
                String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L8_First_Sku.getText();
                IASdao.lnFlg = 8;
                /*
                if (L8_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine8();
                    L8_Qty_Out.setText("");
                    L8_First_Desc.setText("");
                    L8_First_Attr.setText("");
                    L8_First_Size.setText("");
                    L8_Orig_Retail.setText("");
                    L8_Reason_DropDown.setSelectedItem("");
                    L8_Desc_Damage.setText("");
                    L8_New_Sku.setText("");
                    L8_New_Desc.setText("");
                    L8_New_Attr.setText("");
                    L8_New_Size.setText("");
                    L8_Qty_In.setText("");
                    L8_Done_ChkBox.setSelected(false);
                    L8_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (L8_First_Sku.getText().matches("[0-9]+") && L8_First_Sku.getText().length() < 7 || (L8_First_Sku.getText().equals("0") || L8_First_Sku.getText().equals(""))) {
                
                if (IASdao.skuRslt.equals("good") || (L8_First_Sku.getText().equals("") || (L8_First_Sku.getText().equals("0")))) {
                    L8_First_Sku.setBackground(Color.WHITE);
                    L8_First_Desc.setText(IASdao.firstSkuDesc);
                    L8_First_Attr.setText(IASdao.firstSkuAttr);
                    L8_First_Size.setText(IASdao.firstSkuSize);
                    L8_Orig_Retail.setText(IASdao.firstSkuPr);
                 if (L8_First_Sku.getText().equals("") || (L8_First_Sku.getText().equals("0"))) {
                    L8_First_Sku.setText("0");
                    L8_Qty_Out.setText("0");
                    L8_First_Desc.setText("");
                    L8_First_Attr.setText("");
                    L8_First_Size.setText("");
                    L8_Orig_Retail.setText("0.00");
                    L8_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL8_Cost();
                    IASdao.firstDCS = getfDcs8();
                    IASdao.firstSkuVendor = getfVen8();
                    IASdao.firstSkuVPNum = getfVpn8();
                }
                    
                if (L8_Qty_Out.getText().matches("[1-9]+") && L8_Qty_Out.getText().length() < 2 || L8_Qty_Out.getText().matches("0") && L8_First_Sku.getText().matches("0")) {
                    L8_Qty_Out.setBackground(Color.WHITE);
                    
                if (!L8_Reason_DropDown.getSelectedItem().equals("")&& !L8_First_Sku.getText().equals("0") || L8_Reason_DropDown.getSelectedItem().equals("") && L8_First_Sku.getText().matches("0")) {
                    
                if (L8_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L8_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L8_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if (IASdao.skuRslt2.equals("good") && L8_New_Sku.getText().matches("[0-9]+") && L8_New_Sku.getText().length() < 7 || L8_New_Sku.getText().equals("0") || L8_New_Sku.getText().equals("")) {
                        L8_New_Sku.setBackground(Color.WHITE);
                        L8_New_Desc.setText(IASdao.secondSkuDesc);
                        L8_New_Attr.setText(IASdao.secondSkuAttr);
                        L8_New_Size.setText(IASdao.secondSkuSize);
                         
                    if (L8_New_Sku.getText().equals("") || L8_New_Sku.getText().equals("0")) {                                                
                        L8_New_Sku.setText("0");
                        L8_New_Desc.setText("");
                        L8_New_Attr.setText("");
                        L8_New_Size.setText("");
                        L8_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL8_Cost();
                        IASdao.secondDCS = getsDcs8();
                        IASdao.secondSkuVendor = getsVen8();
                        IASdao.secondSkuVPNum = getsVpn8();
                    }
                
                if (L8_Qty_In.getText().matches("[1-9]+") && L8_Qty_In.getText().length() < 2 || L8_Qty_In.getText().matches("0")&& L8_New_Sku.getText().matches("0")) {
                    
                if (!L8_New_Sku.getText().equals(L8_First_Sku.getText())) {
                    L8_New_Sku.setBackground(Color.WHITE);
                    
                if ((!L8_First_Sku.getText().equals("0") && L8_New_Sku.getText().equals("0")) || (L8_First_Sku.getText().equals("0") && !L8_New_Sku.getText().equals("0"))
                    || (!L8_First_Sku.getText().equals("0") && !L8_New_Sku.getText().equals("0"))) {
    
                    IASdao.Field1 = L8_First_Sku.getText();
                    IASdao.Field2 = L8_Qty_Out.getText();
                    IASdao.Field3 = L8_First_Desc.getText();
                    IASdao.Field4 = L8_First_Attr.getText();
                    IASdao.Field5 = L8_First_Size.getText();
                    IASdao.Field6 = L8_Orig_Retail.getText();
                    IASdao.Field7 = L8_Reason_DropDown.getSelectedItem().toString();                    
                    IASdao.Field8 = L8_Desc_Damage.getText();
                    IASdao.Field9 = L8_New_Sku.getText();
                    IASdao.Field10 = L8_New_Desc.getText();
                    IASdao.Field11 = L8_New_Attr.getText();
                    IASdao.Field12 = L8_New_Size.getText();
                    IASdao.Field13 = L8_Qty_In.getText();
                    IASdao.rline = L8.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L8.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L8_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L8_Done_ChkBox.setSelected(false);
                        L8_New_Sku.requestFocus();
                        L8_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L8_Done_ChkBox.setSelected(false);
                        L8_Qty_In.requestFocus();
                        L8_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L8_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L8_Done_ChkBox.setSelected(false);
                        L8_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L8_Done_ChkBox.setSelected(false);
                        L8_Desc_Damage.requestFocus();
                        L8_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n\n"
                                + "NOTE: This is only available for use with the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L8_Done_ChkBox.setSelected(false);
                        L8_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L8_Done_ChkBox.setSelected(false);
                        L8_Qty_Out.requestFocus();
                        L8_Qty_Out.setBackground(Color.yellow);
                    } 
                    } else {
                        L8_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L8_Done_ChkBox.setSelected(false);
                        L8_First_Sku.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L8_Done_ChkBox.setSelected(false);
                    L8_First_Sku.requestFocus();
                    L8_First_Sku.setBackground(Color.yellow);
                }
            //    }
            } else {
                L8_First_Sku.setEnabled(true);
                L8_Qty_Out.setEnabled(true);
                L8_First_Desc.setEnabled(true);
                L8_First_Attr.setEnabled(true);
                L8_First_Size.setEnabled(true);
                L8_Orig_Retail.setEnabled(true);
                L8_Reason_DropDown.setEnabled(true);
                L8_Desc_Damage.setEnabled(true);
                L8_New_Sku.setEnabled(true);
                L8_New_Desc.setEnabled(true);
                L8_New_Attr.setEnabled(true);
                L8_New_Size.setEnabled(true);
                L8_Qty_In.setEnabled(true);
            }
            manager.focusNextComponent();
    }//GEN-LAST:event_L8_Done_ChkBoxKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L8_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L8_Done_ChkBoxActionPerformed
            rChkLn8();
        if (L8_Done_ChkBox.isSelected() == true) {
            String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L8_First_Sku.getText();
                IASdao.lnFlg = 8;
                /*
                if (L8_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine8();
                    L8_Qty_Out.setText("");
                    L8_First_Desc.setText("");
                    L8_First_Attr.setText("");
                    L8_First_Size.setText("");
                    L8_Orig_Retail.setText("");
                    L8_Reason_DropDown.setSelectedItem("");
                    L8_Desc_Damage.setText("");
                    L8_New_Sku.setText("");
                    L8_New_Desc.setText("");
                    L8_New_Attr.setText("");
                    L8_New_Size.setText("");
                    L8_Qty_In.setText("");
                    L8_Done_ChkBox.setSelected(false);
                    L8_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (L8_First_Sku.getText().matches("[0-9]+") && L8_First_Sku.getText().length() < 7 || (L8_First_Sku.getText().equals("0") || L8_First_Sku.getText().equals(""))) {
                
                if (IASdao.skuRslt.equals("good") || (L8_First_Sku.getText().equals("") || (L8_First_Sku.getText().equals("0")))) {
                    L8_First_Sku.setBackground(Color.WHITE);
                    L8_First_Desc.setText(IASdao.firstSkuDesc);
                    L8_First_Attr.setText(IASdao.firstSkuAttr);
                    L8_First_Size.setText(IASdao.firstSkuSize);
                    L8_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L8_First_Sku.getText().equals("") || (L8_First_Sku.getText().equals("0"))) {
                    L8_First_Sku.setText("0");
                    L8_Qty_Out.setText("0");
                    L8_First_Desc.setText("");
                    L8_First_Attr.setText("");
                    L8_First_Size.setText("");
                    L8_Orig_Retail.setText("0.00");
                    L8_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL8_Cost();
                    IASdao.firstDCS = getfDcs8();
                    IASdao.firstSkuVendor = getfVen8();
                    IASdao.firstSkuVPNum = getfVpn8();
                }    
                    
                if (L8_Qty_Out.getText().matches("[1-9]+") && L8_Qty_Out.getText().length() < 2 || L8_Qty_Out.getText().matches("0") && L8_First_Sku.getText().matches("0")) {                      
                    L8_Qty_Out.setBackground(Color.WHITE);
                    
                if (!L8_Reason_DropDown.getSelectedItem().equals("")&& !L8_First_Sku.getText().equals("0") || L8_Reason_DropDown.getSelectedItem().equals("") && L8_First_Sku.getText().matches("0")) {
                    
                if (L8_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L8_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L8_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if (IASdao.skuRslt2.equals("good") && L8_New_Sku.getText().matches("[0-9]+") && L8_New_Sku.getText().length() < 7 || L8_New_Sku.getText().equals("0") || L8_New_Sku.getText().equals("")) {
                        L8_New_Sku.setBackground(Color.WHITE);
                        L8_New_Desc.setText(IASdao.secondSkuDesc);
                        L8_New_Attr.setText(IASdao.secondSkuAttr);
                        L8_New_Size.setText(IASdao.secondSkuSize);
                         
                    if (L8_New_Sku.getText().equals("") || L8_New_Sku.getText().equals("0")) {                                                
                        L8_New_Sku.setText("0");
                        L8_New_Desc.setText("");
                        L8_New_Attr.setText("");
                        L8_New_Size.setText("");
                        L8_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL8_Cost();
                        IASdao.secondDCS = getsDcs8();
                        IASdao.secondSkuVendor = getsVen8();
                        IASdao.secondSkuVPNum = getsVpn8();
                    }
                
                if (L8_Qty_In.getText().matches("[1-9]+") && L8_Qty_In.getText().length() < 2 || L8_Qty_In.getText().matches("0") && L8_New_Sku.getText().matches("0")) {
                    
                if (!L8_New_Sku.getText().equals(L8_First_Sku.getText())) {
                
                if ((!L8_First_Sku.getText().equals("0") && L8_New_Sku.getText().equals("0")) || (L8_First_Sku.getText().equals("0") && !L8_New_Sku.getText().equals("0"))
                    || (!L8_First_Sku.getText().equals("0") && !L8_New_Sku.getText().equals("0"))) {
    
                    IASdao.Field1 = L8_First_Sku.getText();
                    IASdao.Field2 = L8_Qty_Out.getText();
                    IASdao.Field3 = L8_First_Desc.getText();
                    IASdao.Field4 = L8_First_Attr.getText();
                    IASdao.Field5 = L8_First_Size.getText();
                    IASdao.Field6 = L8_Orig_Retail.getText();
                    IASdao.Field7 = L8_Reason_DropDown.getSelectedItem().toString();                    
                    IASdao.Field8 = L8_Desc_Damage.getText();                    
                    IASdao.Field9 = L8_New_Sku.getText();
                    IASdao.Field10 = L8_New_Desc.getText();
                    IASdao.Field11 = L8_New_Attr.getText();
                    IASdao.Field12 = L8_New_Size.getText();
                    IASdao.Field13 = L8_Qty_In.getText();
                    IASdao.rline = L8.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L8.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L8_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L8_Done_ChkBox.setSelected(false);
                        L8_New_Sku.requestFocus();
                        L8_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L8_Done_ChkBox.setSelected(false);
                        L8_Qty_In.requestFocus();                        
                        L8_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L8_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L8_Done_ChkBox.setSelected(false);
                        L8_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L8_Done_ChkBox.setSelected(false);
                        L8_Desc_Damage.requestFocus();
                        L8_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n\n" 
                                + "NOTE: This is only available for use with the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L8_Done_ChkBox.setSelected(false);
                        L8_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L8_Done_ChkBox.setSelected(false);
                        L8_Qty_Out.requestFocus();
                        L8_Qty_Out.setBackground(Color.yellow);
                    } 
                    } else {
                        L8_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L8_Done_ChkBox.setSelected(false);
                        L8_First_Sku.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L8_Done_ChkBox.setSelected(false);
                    L8_First_Sku.requestFocus();
                    L8_First_Sku.setBackground(Color.yellow);
                }
             //   }
                manager.focusNextComponent();
        } else {
            L8_First_Sku.setEnabled(true);
            L8_Qty_Out.setEnabled(true);
            L8_First_Desc.setEnabled(true);
            L8_First_Attr.setEnabled(true);
            L8_First_Size.setEnabled(true);
            L8_Orig_Retail.setEnabled(true);
            L8_Reason_DropDown.setEnabled(true);
            L8_Desc_Damage.setEnabled(true);
            L8_New_Sku.setEnabled(true);
            L8_New_Desc.setEnabled(true);
            L8_New_Attr.setEnabled(true);
            L8_New_Size.setEnabled(true);
            L8_Qty_In.setEnabled(true);
        }
    }//GEN-LAST:event_L8_Done_ChkBoxActionPerformed

    // sku validation for the first sku input field
    private void L9_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_First_SkuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.firstSkuInput = L9_First_Sku.getText();
            if (L9_First_Sku.getText().matches("[0-9]+") && L9_First_Sku.getText().length() < 7 || (L9_First_Sku.getText().equals("") || (L9_First_Sku.getText().equals("0")))) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L9_First_Sku.setBackground(Color.WHITE);
                L9_First_Desc.setText(IASdao.firstSkuDesc);
                L9_First_Attr.setText(IASdao.firstSkuAttr);
                L9_First_Size.setText(IASdao.firstSkuSize);
                L9_Orig_Retail.setText(IASdao.firstSkuPr);
                L9_Qty_Out.requestFocus();
                L9_Qty_Out.setText("");
            } else if (L9_First_Sku.getText().equals("") || (L9_First_Sku.getText().equals("0"))) {
                L9_First_Sku.setText("0");
                L9_Qty_Out.setText("0");
                L9_First_Desc.setText("");
                L9_First_Attr.setText("");
                L9_First_Size.setText("");
                L9_Orig_Retail.setText("0.00");
                L9_Reason_DropDown.setSelectedItem("");
                L9_Desc_Damage.requestFocus();
             //  IASdao.firstSkuCost = getL9_Cost();
            } else {
                L9_First_Sku.requestFocus();
                L9_First_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 numerical values", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L9_First_Sku.requestFocus();
                L9_First_Sku.setBackground(Color.yellow);
            }
        }
    }//GEN-LAST:event_L9_First_SkuKeyPressed

    // sku validation for the second sku input field
    private void L9_New_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_New_SkuKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            IASdao.secondSkuInput = L9_New_Sku.getText();
            if (!L9_New_Sku.getText().equals(L9_First_Sku.getText())) {
                
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L9_New_Sku.getText().matches("[0-9]+") && L9_New_Sku.getText().length() < 7 ) {
                    L9_New_Sku.setBackground(Color.WHITE);
                    L9_New_Desc.setText(IASdao.secondSkuDesc);
                    L9_New_Attr.setText(IASdao.secondSkuAttr);
                    L9_New_Size.setText(IASdao.secondSkuSize);
                    L9_Qty_In.requestFocus();
                    L9_Qty_In.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 numerical values", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L9_New_Sku.requestFocus();
                    L9_New_Sku.setBackground(Color.yellow);
                }
            } else if (L9_New_Sku.getText().equals("") || L9_New_Sku.getText().equals("0")) {
                L9_New_Sku.setText("0");
                L9_New_Desc.setText("");
                L9_New_Attr.setText("");
                L9_New_Size.setText("");
                L9_Qty_In.setText("0");
                L9_Qty_In.requestFocus();
            } else {
                L9_New_Sku.requestFocus();
                L9_New_Sku.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
            }            
        } else {
                JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                L9_New_Sku.requestFocus();
                L9_New_Sku.setBackground(Color.yellow);
            }
         }
    }//GEN-LAST:event_L9_New_SkuKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L9_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Done_ChkBoxKeyPressed
            L9_Done_ChkBox.setSelected(true);
            rChkLn9();
            if (L9_Done_ChkBox.isSelected() == true) {
                String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L9_First_Sku.getText();
                IASdao.lnFlg = 9;
                /*
                if (L9_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine9();
                    L9_Qty_Out.setText("");
                    L9_First_Desc.setText("");
                    L9_First_Attr.setText("");
                    L9_First_Size.setText("");
                    L9_Orig_Retail.setText("");
                    L9_Reason_DropDown.setSelectedItem("");
                    L9_Desc_Damage.setText("");
                    L9_New_Sku.setText("");
                    L9_New_Desc.setText("");
                    L9_New_Attr.setText("");
                    L9_New_Size.setText("");
                    L9_Qty_In.setText("");
                    L9_Done_ChkBox.setSelected(false);
                    L9_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (L9_First_Sku.getText().matches("[0-9]+") && L9_First_Sku.getText().length() < 7 || (L9_First_Sku.getText().equals("0") || L9_First_Sku.getText().equals(""))) {
                
                if (IASdao.skuRslt.equals("good") || (L9_First_Sku.getText().equals("") || (L9_First_Sku.getText().equals("0")))) {
                    L9_First_Sku.setBackground(Color.WHITE);
                    L9_First_Desc.setText(IASdao.firstSkuDesc);
                    L9_First_Attr.setText(IASdao.firstSkuAttr);
                    L9_First_Size.setText(IASdao.firstSkuSize);
                    L9_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L9_First_Sku.getText().equals("") || (L9_First_Sku.getText().equals("0"))) {
                    L9_First_Sku.setText("0");
                    L9_Qty_Out.setText("0");
                    L9_First_Desc.setText("");
                    L9_First_Attr.setText("");
                    L9_First_Size.setText("");
                    L9_Orig_Retail.setText("0.00");
                    L9_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL9_Cost();
                    IASdao.firstDCS = getfDcs9();
                    IASdao.firstSkuVendor = getfVen9();
                    IASdao.firstSkuVPNum = getfVpn9();
                }
                    
                if (L9_Qty_Out.getText().matches("[1-9]+") && L9_Qty_Out.getText().length() < 2 || L9_Qty_Out.getText().matches("0") && L9_First_Sku.getText().matches("0")) {                      
                    L9_Qty_Out.setBackground(Color.WHITE);
                    
                if (!L9_Reason_DropDown.getSelectedItem().equals("") && !L9_First_Sku.getText().equals("0") || L9_Reason_DropDown.getSelectedItem().equals("") && L9_First_Sku.getText().matches("0")) {
                                        
                if (L9_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L9_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L9_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                    if (IASdao.skuRslt2.equals("good") && L9_New_Sku.getText().matches("[0-9]+") && L9_New_Sku.getText().length() < 7 || L9_New_Sku.getText().equals("0") || L9_New_Sku.getText().equals("")) {
                        L9_New_Sku.setBackground(Color.WHITE);
                        L9_New_Desc.setText(IASdao.secondSkuDesc);
                        L9_New_Attr.setText(IASdao.secondSkuAttr);
                        L9_New_Size.setText(IASdao.secondSkuSize);
                        
                    if (L9_New_Sku.getText().equals("") || L9_New_Sku.getText().equals("0")) {                                                
                        L9_New_Sku.setText("0");
                        L9_New_Desc.setText("");
                        L9_New_Attr.setText("");
                        L9_New_Size.setText("");
                        L9_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL9_Cost();
                        IASdao.secondDCS = getsDcs9();
                        IASdao.secondSkuVendor = getsVen9();
                        IASdao.secondSkuVPNum = getsVpn9();
                    }
                
                if (L9_Qty_In.getText().matches("[1-9]+") && L9_Qty_In.getText().length() < 2 || L9_Qty_In.getText().matches("0")&& L9_New_Sku.getText().matches("0")) {
                    L9_Qty_In.setBackground(Color.WHITE);
                    
                if (!L9_New_Sku.getText().equals(L9_First_Sku.getText())) {
                    L9_New_Sku.setBackground(Color.WHITE);
                    
                if ((!L9_First_Sku.getText().equals("0") && L9_New_Sku.getText().equals("0")) || (L9_First_Sku.getText().equals("0") && !L9_New_Sku.getText().equals("0"))
                    || (!L9_First_Sku.getText().equals("0") && !L9_New_Sku.getText().equals("0"))) {
    
                    IASdao.Field1 = L9_First_Sku.getText();
                    IASdao.Field2 = L9_Qty_Out.getText();
                    IASdao.Field3 = L9_First_Desc.getText();
                    IASdao.Field4 = L9_First_Attr.getText();
                    IASdao.Field5 = L9_First_Size.getText();
                    IASdao.Field6 = L9_Orig_Retail.getText();
                    IASdao.Field7 = L9_Reason_DropDown.getSelectedItem().toString();
                    IASdao.Field8 = L9_Desc_Damage.getText();
                    IASdao.Field9 = L9_New_Sku.getText();
                    IASdao.Field10 = L9_New_Desc.getText();
                    IASdao.Field11 = L9_New_Attr.getText();
                    IASdao.Field12 = L9_New_Size.getText();
                    IASdao.Field13 = L9_Qty_In.getText();
                    IASdao.rline = L9.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L9.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L9_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L9_Done_ChkBox.setSelected(false);
                        L9_New_Sku.requestFocus();
                        L9_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L9_Done_ChkBox.setSelected(false);
                        L9_Qty_In.requestFocus();
                        L9_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L9_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L9_Done_ChkBox.setSelected(false);
                        L9_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L9_Done_ChkBox.setSelected(false);
                        L9_Desc_Damage.requestFocus();
                        L9_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n\n"
                                + "NOTE: This is only available for use with the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L9_Done_ChkBox.setSelected(false);
                        L9_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L9_Done_ChkBox.setSelected(false);
                        L9_Qty_Out.requestFocus();
                        L9_Qty_Out.setBackground(Color.yellow);
                    } 
                    } else {                        
                        L9_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L9_Done_ChkBox.setSelected(false);
                        L9_First_Sku.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L9_Done_ChkBox.setSelected(false);
                    L9_First_Sku.requestFocus();
                    L9_First_Sku.setBackground(Color.yellow);
                }
             //   }
            } else {
                L9_First_Sku.setEnabled(true);
                L9_Qty_Out.setEnabled(true);
                L9_First_Desc.setEnabled(true);
                L9_First_Attr.setEnabled(true);
                L9_First_Size.setEnabled(true);
                L9_Orig_Retail.setEnabled(true);
                L9_Reason_DropDown.setEnabled(true);
                L9_Desc_Damage.setEnabled(true);
                L9_New_Sku.setEnabled(true);
                L9_New_Desc.setEnabled(true);
                L9_New_Attr.setEnabled(true);
                L9_New_Size.setEnabled(true);
                L9_Qty_In.setEnabled(true);
            }
            manager.focusNextComponent();
    }//GEN-LAST:event_L9_Done_ChkBoxKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L9_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L9_Done_ChkBoxActionPerformed
           rChkLn9();
        if (L9_Done_ChkBox.isSelected() == true ) {
            String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L9_First_Sku.getText();
                IASdao.lnFlg = 9;
                /*
                if (L9_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine9();
                    L9_Qty_Out.setText("");
                    L9_First_Desc.setText("");
                    L9_First_Attr.setText("");
                    L9_First_Size.setText("");
                    L9_Orig_Retail.setText("");
                    L9_Reason_DropDown.setSelectedItem("");
                    L9_Desc_Damage.setText("");
                    L9_New_Sku.setText("");
                    L9_New_Desc.setText("");
                    L9_New_Attr.setText("");
                    L9_New_Size.setText("");
                    L9_Qty_In.setText("");
                    L9_Done_ChkBox.setSelected(false);
                    L9_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (L9_First_Sku.getText().matches("[0-9]+") && L9_First_Sku.getText().length() < 7 || (L9_First_Sku.getText().equals("0") || L9_First_Sku.getText().equals(""))) {
                
                if (IASdao.skuRslt.equals("good") || (L9_First_Sku.getText().equals("") || (L9_First_Sku.getText().equals("0")))) {
                    L9_First_Sku.setBackground(Color.WHITE);
                    L9_First_Desc.setText(IASdao.firstSkuDesc);
                    L9_First_Attr.setText(IASdao.firstSkuAttr);
                    L9_First_Size.setText(IASdao.firstSkuSize);
                    L9_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L9_First_Sku.getText().equals("") || (L9_First_Sku.getText().equals("0"))) {
                    L9_First_Sku.setText("0");
                    L9_Qty_Out.setText("0");
                    L9_First_Desc.setText("");
                    L9_First_Attr.setText("");
                    L9_First_Size.setText("");
                    L9_Orig_Retail.setText("0.00");
                    L9_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL9_Cost();
                    IASdao.firstDCS = getfDcs9();
                    IASdao.firstSkuVendor = getfVen9();
                    IASdao.firstSkuVPNum = getfVpn9();
                }
                    
                if (L9_Qty_Out.getText().matches("[1-9]+") && L9_Qty_Out.getText().length() < 2 || L9_Qty_Out.getText().matches("0") && L9_First_Sku.getText().matches("0")) {                      
                    L9_Qty_Out.setBackground(Color.WHITE);
                    
                if (!L9_Reason_DropDown.getSelectedItem().equals("")&& !L9_First_Sku.getText().equals("0") || L9_Reason_DropDown.getSelectedItem().equals("") && L9_First_Sku.getText().matches("0")) {
                    
                if (L9_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L9_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L9_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if (IASdao.skuRslt2.equals("good") && L9_New_Sku.getText().matches("[0-9]+") && L9_New_Sku.getText().length() < 7 || L9_New_Sku.getText().equals("0") || L9_New_Sku.getText().equals("")) {
                        L9_New_Sku.setBackground(Color.WHITE);
                        L9_New_Desc.setText(IASdao.secondSkuDesc);
                        L9_New_Attr.setText(IASdao.secondSkuAttr);
                        L9_New_Size.setText(IASdao.secondSkuSize);
                        
                    if (L9_New_Sku.getText().equals("") || L9_New_Sku.getText().equals("0")) {                                                
                        L9_New_Sku.setText("0");
                        L9_New_Desc.setText("");
                        L9_New_Attr.setText("");
                        L9_New_Size.setText("");
                        L9_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL9_Cost();
                        IASdao.secondDCS = getsDcs9();
                        IASdao.secondSkuVendor = getsVen9();
                        IASdao.secondSkuVPNum = getsVpn9();
                    }
                
                if (L9_Qty_In.getText().matches("[1-9]+") && L9_Qty_In.getText().length() < 2 || L9_Qty_In.getText().matches("0")&& L9_New_Sku.getText().matches("0")) {
                    L9_Qty_In.setBackground(Color.WHITE);
                    
                if (!L9_New_Sku.getText().equals(L9_First_Sku.getText())) {
                    L9_New_Sku.setBackground(Color.WHITE);
                    
                if ((!L9_First_Sku.getText().equals("0") && L9_New_Sku.getText().equals("0")) || (L9_First_Sku.getText().equals("0") && !L9_New_Sku.getText().equals("0"))
                    || (!L9_First_Sku.getText().equals("0") && !L9_New_Sku.getText().equals("0"))) {
    
                    IASdao.Field1 = L9_First_Sku.getText();
                    IASdao.Field2 = L9_Qty_Out.getText();
                    IASdao.Field3 = L9_First_Desc.getText();
                    IASdao.Field4 = L9_First_Attr.getText();
                    IASdao.Field5 = L9_First_Size.getText();
                    IASdao.Field6 = L9_Orig_Retail.getText();
                    IASdao.Field7 = L9_Reason_DropDown.getSelectedItem().toString();
                    IASdao.Field8 = L9_Desc_Damage.getText();
                    IASdao.Field9 = L9_New_Sku.getText();
                    IASdao.Field10 = L9_New_Desc.getText();
                    IASdao.Field11 = L9_New_Attr.getText();
                    IASdao.Field12 = L9_New_Size.getText();
                    IASdao.Field13 = L9_Qty_In.getText();
                    IASdao.rline = L9.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L9.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L9_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L9_Done_ChkBox.setSelected(false);                        
                        L9_New_Sku.requestFocus();
                        L9_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L9_Done_ChkBox.setSelected(false);
                        L9_Qty_In.requestFocus();                        
                        L9_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L9_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L9_Done_ChkBox.setSelected(false);
                        L9_New_Sku.requestFocus();                    
                        L9_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L9_Done_ChkBox.setSelected(false);
                        L9_Desc_Damage.requestFocus();
                        L9_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n\n"
                                + "NOTE: This is only available for use with the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L9_Done_ChkBox.setSelected(false);
                        L9_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L9_Done_ChkBox.setSelected(false);
                        L9_Qty_Out.requestFocus();
                        L9_Qty_Out.setBackground(Color.yellow);
                    } 
                    } else {
                        L9_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L9_Done_ChkBox.setSelected(false);
                        L9_First_Sku.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L9_Done_ChkBox.setSelected(false);
                    L9_First_Sku.requestFocus();
                    L9_First_Sku.setBackground(Color.yellow);
                }
             //   }
       } else {
            L9_First_Sku.setEnabled(true);
            L9_Qty_Out.setEnabled(true);
            L9_First_Desc.setEnabled(true);
            L9_First_Attr.setEnabled(true);
            L9_First_Size.setEnabled(true);
            L9_Orig_Retail.setEnabled(true);
            L9_Reason_DropDown.setEnabled(true);
            L9_Desc_Damage.setEnabled(true);
            L9_New_Sku.setEnabled(true);
            L9_New_Desc.setEnabled(true);
            L9_New_Attr.setEnabled(true);
            L9_New_Size.setEnabled(true);
            L9_Qty_In.setEnabled(true);
        }
    }//GEN-LAST:event_L9_Done_ChkBoxActionPerformed

    // sku validation for the first sku input field
    private void L10_First_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_First_SkuKeyPressed
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.firstSkuInput = L10_First_Sku.getText();
            if (L10_First_Sku.getText().matches("[0-9]+") && L10_First_Sku.getText().length() < 7 || (L10_First_Sku.getText().equals("") || (L10_First_Sku.getText().equals("0")))) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L10_First_Sku.setBackground(Color.WHITE);
                L10_First_Desc.setText(IASdao.firstSkuDesc);
                L10_First_Attr.setText(IASdao.firstSkuAttr);
                L10_First_Size.setText(IASdao.firstSkuSize);
                L10_Orig_Retail.setText(IASdao.firstSkuPr);
                L10_Qty_Out.requestFocus();
                L10_Qty_Out.setText("");
            } else if (L10_First_Sku.getText().equals("") || (L10_First_Sku.getText().equals("0"))) {
                L10_First_Sku.setText("0");
                L10_Qty_Out.setText("0");
                L10_First_Desc.setText("");
                L10_First_Attr.setText("");
                L10_First_Size.setText("");
                L10_Orig_Retail.setText("0.00");
                L10_Reason_DropDown.setSelectedItem("");
                L10_Desc_Damage.requestFocus();
             //   IASdao.firstSkuCost = getL10_Cost();
            } else {
                L10_First_Sku.setBackground(Color.yellow);
                L10_First_Sku.requestFocus();                    
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only enter up to 6 numerical values", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                L10_First_Sku.requestFocus();
                L10_First_Sku.setBackground(Color.yellow);
            }
        }
    }//GEN-LAST:event_L10_First_SkuKeyPressed

    // sku validation for the second sku input field
    private void L10_New_SkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_New_SkuKeyPressed
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            IASdao.secondSkuInput = L10_New_Sku.getText();
            if (!L10_New_Sku.getText().equals(L10_First_Sku.getText())) {
                                
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L10_New_Sku.getText().matches("[0-9]+") && L10_New_Sku.getText().length() < 7 ) {
                    L10_New_Sku.setBackground(Color.WHITE);
                    L10_New_Desc.setText(IASdao.secondSkuDesc);
                    L10_New_Attr.setText(IASdao.secondSkuAttr);
                    L10_New_Size.setText(IASdao.secondSkuSize);
                    L10_Qty_In.requestFocus();
                    L10_Qty_In.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 numerical values", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L10_New_Sku.requestFocus();
                    L10_New_Sku.setBackground(Color.yellow);
                 }                
            } else if (L10_New_Sku.getText().equals("") || L10_New_Sku.getText().equals("0")) {
                L10_New_Sku.setText("0");
                L10_New_Desc.setText("");
                L10_New_Attr.setText("");
                L10_New_Size.setText("");
                L10_Qty_In.setText("0");
                L10_Done_ChkBox.requestFocus();
            } else {                
                L10_New_Sku.setBackground(Color.yellow);
                L10_New_Sku.requestFocus();
                JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
            }            
      } else {
          JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
          L10_New_Sku.requestFocus();
          L10_New_Sku.setBackground(Color.yellow);
         }
      }
    }//GEN-LAST:event_L10_New_SkuKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L10_Done_ChkBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Done_ChkBoxKeyPressed
            L10_Done_ChkBox.setSelected(true);
            rChkLn10();
            if (L10_Done_ChkBox.isSelected() == true) {
                String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L10_First_Sku.getText();
                IASdao.lnFlg = 10;
                /*
                if (L10_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine10();
                    L10_Qty_Out.setText("");
                    L10_First_Desc.setText("");
                    L10_First_Attr.setText("");
                    L10_First_Size.setText("");
                    L10_Orig_Retail.setText("");
                    L10_Reason_DropDown.setSelectedItem("");
                    L10_Desc_Damage.setText("");
                    L10_New_Sku.setText("");
                    L10_New_Desc.setText("");
                    L10_New_Attr.setText("");
                    L10_New_Size.setText("");
                    L10_Qty_In.setText("");
                    L10_Done_ChkBox.setSelected(false);
                    L10_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (L10_First_Sku.getText().matches("[0-9]+") && L10_First_Sku.getText().length() < 7 || (L10_First_Sku.getText().equals("0") || L10_First_Sku.getText().equals(""))) {
                
                if (IASdao.skuRslt.equals("good") || (L10_First_Sku.getText().equals("") || (L10_First_Sku.getText().equals("0")))) {
                    L10_First_Sku.setBackground(Color.WHITE);
                    L10_First_Desc.setText(IASdao.firstSkuDesc);
                    L10_First_Attr.setText(IASdao.firstSkuAttr);
                    L10_First_Size.setText(IASdao.firstSkuSize);
                    L10_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L10_First_Sku.getText().equals("") || (L10_First_Sku.getText().equals("0"))) {
                    L10_First_Sku.setText("0");
                    L10_Qty_Out.setText("0");
                    L10_First_Desc.setText("");
                    L10_First_Attr.setText("");
                    L10_First_Size.setText("");
                    L10_Orig_Retail.setText("0.00");
                    L10_Reason_DropDown.setSelectedItem("");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL10_Cost();
                    IASdao.firstDCS = getfDcs10();
                    IASdao.firstSkuVendor = getfVen10();
                    IASdao.firstSkuVPNum = getfVpn10();
                }
                
                if (L10_Qty_Out.getText().matches("[1-9]+") && L10_Qty_Out.getText().length() < 2 || L10_Qty_Out.getText().matches("0") && L10_First_Sku.getText().matches("0")) {                      
                    L10_Qty_Out.setBackground(Color.WHITE);
                    
                if (!L10_Reason_DropDown.getSelectedItem().equals("") && !L10_First_Sku.getText().equals("0") || L10_Reason_DropDown.getSelectedItem().equals("") && L10_First_Sku.getText().matches("0")) {
                    
                if (L10_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L10_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L10_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if (IASdao.skuRslt2.equals("good") && L10_New_Sku.getText().matches("[0-9]+") && L10_New_Sku.getText().length() < 7 || L10_New_Sku.getText().equals("0") || L10_New_Sku.getText().equals("")) {
                        L10_New_Sku.setBackground(Color.WHITE);
                        L10_New_Desc.setText(IASdao.secondSkuDesc);
                        L10_New_Attr.setText(IASdao.secondSkuAttr);
                        L10_New_Size.setText(IASdao.secondSkuSize);
                        
                    if (L10_New_Sku.getText().equals("") || L10_New_Sku.getText().equals("0")) {                                                
                        L10_New_Sku.setText("0");
                        L10_New_Desc.setText("");
                        L10_New_Attr.setText("");
                        L10_New_Size.setText("");
                        L10_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL10_Cost();
                        IASdao.secondDCS = getsDcs10();
                        IASdao.secondSkuVendor = getsVen10();
                        IASdao.secondSkuVPNum = getsVpn10();
                    }
                
                if (L10_Qty_In.getText().matches("[1-9]+") && L10_Qty_In.getText().length() < 2 || L10_Qty_In.getText().matches("0") && L10_New_Sku.getText().matches("0")) {
                    L10_Qty_In.setBackground(Color.WHITE);
                    
                if (!L10_New_Sku.getText().equals(L10_First_Sku.getText())) {
                    L10_New_Sku.setBackground(Color.WHITE);
                    
                if ((!L10_First_Sku.getText().equals("0") && L10_New_Sku.getText().equals("0")) || (L10_First_Sku.getText().equals("0") && !L10_New_Sku.getText().equals("0"))
                    || (!L10_First_Sku.getText().equals("0") && !L10_New_Sku.getText().equals("0"))) {
    
                    IASdao.Field1 = L10_First_Sku.getText();
                    IASdao.Field2 = L10_Qty_Out.getText();
                    IASdao.Field3 = L10_First_Desc.getText();
                    IASdao.Field4 = L10_First_Attr.getText();
                    IASdao.Field5 = L10_First_Size.getText();
                    IASdao.Field6 = L10_Orig_Retail.getText();
                    IASdao.Field7 = L10_Reason_DropDown.getSelectedItem().toString();                    
                    IASdao.Field8 = L10_Desc_Damage.getText();
                    IASdao.Field9 = L10_New_Sku.getText();
                    IASdao.Field10 = L10_New_Desc.getText();
                    IASdao.Field11 = L10_New_Attr.getText();
                    IASdao.Field12 = L10_New_Size.getText();
                    IASdao.Field13 = L10_Qty_In.getText();
                    IASdao.rline = L10.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L10.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L10_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L10_Done_ChkBox.setSelected(false);
                        L10_New_Sku.requestFocus();
                        L10_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L10_Done_ChkBox.setSelected(false);
                        L10_Qty_In.requestFocus();
                        L10_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L10_Done_ChkBox.setSelected(false);
                        L10_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L10_Done_ChkBox.setSelected(false);
                        L10_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L10_Done_ChkBox.setSelected(false);
                        L10_Desc_Damage.requestFocus();
                        L10_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove first sku from inventory" + "\n\n" 
                                + "NOTE: This is only available for use with the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L10_Done_ChkBox.setSelected(false);
                        L10_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L10_Done_ChkBox.setSelected(false);
                        L10_Qty_Out.requestFocus();
                        L10_Qty_Out.setBackground(Color.yellow);
                    } 
                    } else {
                        L10_Done_ChkBox.setSelected(false);
                        L10_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);
                        L10_Done_ChkBox.setSelected(false);
                        L10_First_Sku.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "You can only enter up to 6 digits", "Only Numbers Error", JOptionPane.ERROR_MESSAGE);
                    L10_Done_ChkBox.setSelected(false);
                    L10_First_Sku.requestFocus();
                    L10_First_Sku.setBackground(Color.yellow);
                }
             //   }
            } else {
                L10_First_Sku.setEnabled(true);
                L10_Qty_Out.setEnabled(true);
                L10_First_Desc.setEnabled(true);
                L10_First_Attr.setEnabled(true);
                L10_First_Size.setEnabled(true);
                L10_Orig_Retail.setEnabled(true);
                L10_Reason_DropDown.setEnabled(true);
                L10_Desc_Damage.setEnabled(true);
                L10_New_Sku.setEnabled(true);
                L10_New_Desc.setEnabled(true);
                L10_New_Attr.setEnabled(true);
                L10_New_Size.setEnabled(true);
                L10_Qty_In.setEnabled(true);
            }
    }//GEN-LAST:event_L10_Done_ChkBoxKeyPressed

    // Validates all the necessary fields have the correct data and are not empty
    // if the user deletes the data in the first sku field it deletes that line from the temp table and form window
    private void L10_Done_ChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L10_Done_ChkBoxActionPerformed
                rChkLn10();
                if (L10_Done_ChkBox.isSelected() == true) {
                   String[] args = null;
                GtDates.main(args);
                IASdao.firstSkuInput = L10_First_Sku.getText();
                IASdao.lnFlg = 10;
                /*
                if (L10_First_Sku.getText().equals("")) {
                try {
                    IASdao.ClearLine10();
                    L10_Qty_Out.setText("");
                    L10_First_Desc.setText("");
                    L10_First_Attr.setText("");
                    L10_First_Size.setText("");
                    L10_Orig_Retail.setText("");
                    L10_Reason_DropDown.setSelectedItem("");
                    L10_Desc_Damage.setText("");
                    L10_New_Sku.setText("");
                    L10_New_Desc.setText("");
                    L10_New_Attr.setText("");
                    L10_New_Size.setText("");
                    L10_Qty_In.setText("");
                    L10_Done_ChkBox.setSelected(false);
                    L10_Timestamp.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { */
                try {
                    IASdao.main(args);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (L10_First_Sku.getText().matches("[0-9]+") && L10_First_Sku.getText().length() < 7  || (L10_First_Sku.getText().equals("0") || L10_First_Sku.getText().equals(""))) {
                
                if (IASdao.skuRslt.equals("good") || (L10_First_Sku.getText().equals("") || (L10_First_Sku.getText().equals("0")))) {
                    L10_First_Sku.setBackground(Color.WHITE);
                    L10_First_Desc.setText(IASdao.firstSkuDesc);
                    L10_First_Attr.setText(IASdao.firstSkuAttr);
                    L10_First_Size.setText(IASdao.firstSkuSize);
                    L10_Orig_Retail.setText(IASdao.firstSkuPr);
                if (L10_First_Sku.getText().equals("") || (L10_First_Sku.getText().equals("0"))) {
                    L10_First_Sku.setText("0");
                    L10_Qty_Out.setText("0");
                    L10_First_Desc.setText("");
                    L10_First_Attr.setText("");
                    L10_First_Size.setText("");
                    L10_Orig_Retail.setText("0.00");
                    // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                    IASdao.firstSkuCost = getL10_Cost();
                    IASdao.firstDCS = getfDcs10();
                    IASdao.firstSkuVendor = getfVen10();
                    IASdao.firstSkuVPNum = getfVpn10();
                }
                    
                if (L10_Qty_Out.getText().matches("[1-9]+") && L10_Qty_Out.getText().length() < 2 || L10_Qty_Out.getText().matches("0") && L10_First_Sku.getText().matches("0")) {
                    L10_Qty_Out.setBackground(Color.WHITE);
                    
                if (!L10_Reason_DropDown.getSelectedItem().equals("") && !L10_First_Sku.getText().equals("0") || L10_Reason_DropDown.getSelectedItem().equals("") && L10_First_Sku.getText().matches("0")) {                      
                    
                if (L10_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                    L10_Desc_Damage.setBackground(Color.WHITE);
                    
                    IASdao.secondSkuInput = L10_New_Sku.getText();
                    try {
                    IASdao.skuCheck2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    if (IASdao.skuRslt2.equals("good") && L10_New_Sku.getText().matches("[0-9]+") && L10_New_Sku.getText().length() < 7 || L10_New_Sku.getText().equals("0") || L10_New_Sku.getText().equals("")) {
                        L10_New_Sku.setBackground(Color.WHITE);
                        L10_New_Desc.setText(IASdao.secondSkuDesc);
                        L10_New_Attr.setText(IASdao.secondSkuAttr);
                        L10_New_Size.setText(IASdao.secondSkuSize);
                         
                    if (L10_New_Sku.getText().equals("") || L10_New_Sku.getText().equals("0")) {                                                
                        L10_New_Sku.setText("0");
                        L10_New_Desc.setText("");
                        L10_New_Attr.setText("");
                        L10_New_Size.setText("");
                        L10_Qty_In.setText("0");
                        // this assigns a value of 0.00 to ensure it writes to the database when the sku is 0
                        IASdao.secondSkuCost = getL10_Cost();
                        IASdao.secondDCS = getsDcs10();
                        IASdao.secondSkuVendor = getsVen10();
                        IASdao.secondSkuVPNum = getsVpn10();
                    }
                
                if (L10_Qty_In.getText().matches("[1-9]+") && L10_Qty_In.getText().length() < 2 || L10_Qty_In.getText().matches("0") && L10_New_Sku.getText().matches("0")) {
                    L10_Qty_In.setBackground(Color.WHITE);
                    
                if (!L10_New_Sku.getText().equals(L10_First_Sku.getText())) {
                    L10_New_Sku.setBackground(Color.WHITE);
                    
                if ((!L10_First_Sku.getText().equals("0") && L10_New_Sku.getText().equals("0")) || (L10_First_Sku.getText().equals("0") && !L10_New_Sku.getText().equals("0"))
                    || (!L10_First_Sku.getText().equals("0") && !L10_New_Sku.getText().equals("0"))) {
    
                    IASdao.Field1 = L10_First_Sku.getText();
                    IASdao.Field2 = L10_Qty_Out.getText();
                    IASdao.Field3 = L10_First_Desc.getText();
                    IASdao.Field4 = L10_First_Attr.getText();
                    IASdao.Field5 = L10_First_Size.getText();
                    IASdao.Field6 = L10_Orig_Retail.getText();
                    IASdao.Field7 = L10_Reason_DropDown.getSelectedItem().toString();
                    IASdao.Field8 = L10_Desc_Damage.getText();
                    IASdao.Field9 = L10_New_Sku.getText();
                    IASdao.Field10 = L10_New_Desc.getText();
                    IASdao.Field11 = L10_New_Attr.getText();
                    IASdao.Field12 = L10_New_Size.getText();
                    IASdao.Field13 = L10_Qty_In.getText();
                    IASdao.rline = L10.getText().replace(")", "");

                    IASdao.CmtLn();
                    
                    jPanel6.scrollRectToVisible(L10.getBounds(null));
                    
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter at least one sku", "At least one Sku Error", JOptionPane.ERROR_MESSAGE);
                        L10_Done_ChkBox.setSelected(false);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate Sku, Please enter different sku", "Duplicate Sku Error", JOptionPane.ERROR_MESSAGE);
                        L10_Done_ChkBox.setSelected(false);
                        L10_New_Sku.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
                        L10_Done_ChkBox.setSelected(false);
                        L10_Qty_In.requestFocus();
                        L10_Qty_In.setBackground(Color.yellow);
                    }
                    } else {
                        L10_Done_ChkBox.setSelected(false);
                        L10_New_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "New Sku Error", JOptionPane.ERROR_MESSAGE);
                        L10_New_Sku.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank" + "\n\n" + "NOTE: You must input a description of damage for the first sku"
                                + "\n\n" + " or input an explanation for the new sku when using qty in only adjustments", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
                        L10_Done_ChkBox.setSelected(false);
                        L10_Desc_Damage.requestFocus();
                        L10_Desc_Damage.setBackground(Color.yellow);
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please choose a Reason to remove from inventory" + "\n\n"
                                + "NOTE: This is only available for use with the first sku", "Reason Error", JOptionPane.ERROR_MESSAGE);
                        L10_Done_ChkBox.setSelected(false);
                        L10_Reason_DropDown.requestFocus();
                    }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
                        L10_Done_ChkBox.setSelected(false);
                        L10_Qty_Out.requestFocus();
                        L10_Qty_Out.setBackground(Color.yellow);
                    } 
                    } else {
                        L10_Done_ChkBox.setSelected(false);
                        L10_First_Sku.setBackground(Color.yellow);
                        JOptionPane.showMessageDialog(frame, "Please Enter A Valid SKU", "First Sku Error", JOptionPane.ERROR_MESSAGE);                        
                        L10_First_Sku.requestFocus();
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
                   L10_First_Attr.setEnabled(true);
                   L10_First_Size.setEnabled(true);
                   L10_Orig_Retail.setEnabled(true);
                   L10_Reason_DropDown.setEnabled(true);
                   L10_Desc_Damage.setEnabled(true);
                   L10_New_Sku.setEnabled(true);
                   L10_New_Desc.setEnabled(true);
                   L10_New_Attr.setEnabled(true);
                   L10_New_Size.setEnabled(true);
                   L10_Qty_In.setEnabled(true);
                }           
    }//GEN-LAST:event_L10_Done_ChkBoxActionPerformed
    // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L1_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Qty_OutKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L1_Qty_Out.getText().matches("[1-9]+") && L1_Qty_Out.getText().length() < 2 || L1_Qty_Out.getText().matches("0") && L1_First_Sku.getText().matches("0")) {
                    L1_Qty_Out.setBackground(Color.WHITE);
                    L1_Reason_DropDown.requestFocus();            
            } else {
                    JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);  
            }   
        }
    }//GEN-LAST:event_L1_Qty_OutKeyPressed
   // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L2_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Qty_OutKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L2_Qty_Out.getText().matches("[1-9]+") && L2_Qty_Out.getText().length() < 2 || L2_Qty_Out.getText().matches("0") && L2_First_Sku.getText().matches("0")) {
                L2_Qty_Out.setBackground(Color.WHITE);
                L2_Reason_DropDown.requestFocus();            
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
            }
        }   
    }//GEN-LAST:event_L2_Qty_OutKeyPressed

    private void L1_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Reason_DropDownKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        L1_Desc_Damage.requestFocus();
       }
    }//GEN-LAST:event_L1_Reason_DropDownKeyPressed

    private void L1_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Desc_DamageKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (L1_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
            L1_Desc_Damage.setBackground(Color.WHITE);
            L1_New_Sku.requestFocus();
        } else {
              JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        }          
    }
    }//GEN-LAST:event_L1_Desc_DamageKeyPressed

    private void L2_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Reason_DropDownKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        L2_Desc_Damage.requestFocus();
       }
    }//GEN-LAST:event_L2_Reason_DropDownKeyPressed

    private void L2_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Desc_DamageKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (L2_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
            L2_Desc_Damage.setBackground(Color.WHITE);
            L2_New_Sku.requestFocus();
        } else {
              JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        }
        }
    }//GEN-LAST:event_L2_Desc_DamageKeyPressed
    // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L2_Qty_InKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L2_Qty_InKeyPressed
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (L2_Qty_In.getText().matches("[1-9]+") && L2_Qty_In.getText().length() < 2 || L2_Qty_In.getText().matches("0") && L2_New_Sku.getText().matches("0")) {
             L2_Done_ChkBox.requestFocus();
             L2_Qty_In.setBackground(Color.WHITE);
      } else {
         JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);  
      }    
      }
    }//GEN-LAST:event_L2_Qty_InKeyPressed
 // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L3_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Qty_OutKeyPressed
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (L3_Qty_Out.getText().matches("[1-9]+") && L3_Qty_Out.getText().length() < 2 || L1_Qty_Out.getText().equals("0")) {
             L3_Qty_In.setBackground(Color.WHITE);
             L3_Reason_DropDown.requestFocus();
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);
            }
        }  
    }//GEN-LAST:event_L3_Qty_OutKeyPressed

    private void L3_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Reason_DropDownKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        L3_Desc_Damage.requestFocus();
       }
    }//GEN-LAST:event_L3_Reason_DropDownKeyPressed

    private void L3_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Desc_DamageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L3_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                L3_Desc_Damage.setBackground(Color.WHITE);
                L3_New_Sku.requestFocus();
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
            }        
        }
    }//GEN-LAST:event_L3_Desc_DamageKeyPressed
 // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L3_Qty_InKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L3_Qty_InKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L3_Qty_In.getText().matches("[1-9]+") && L3_Qty_In.getText().length() < 2 || L3_Qty_In.getText().equals("0") && L3_New_Sku.getText().matches("0")) {
                L3_Qty_In.setBackground(Color.WHITE);
                L3_Done_ChkBox.requestFocus();
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);  
            }    
        }
    }//GEN-LAST:event_L3_Qty_InKeyPressed
 // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L4_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Qty_OutKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L4_Qty_Out.getText().matches("[1-9]+") && L4_Qty_Out.getText().length() < 2 || L4_Qty_Out.getText().equals("0") && L4_First_Sku.getText().matches("0")) {
                L4_Qty_Out.setBackground(Color.WHITE);
                L4_Reason_DropDown.requestFocus();
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);   
            }
        }
    }//GEN-LAST:event_L4_Qty_OutKeyPressed

    private void L4_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Reason_DropDownKeyPressed
     if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        L4_Desc_Damage.requestFocus();
       }
    }//GEN-LAST:event_L4_Reason_DropDownKeyPressed

    private void L4_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Desc_DamageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L4_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                L4_Desc_Damage.setBackground(Color.WHITE);
                L4_New_Sku.requestFocus();
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
            }       
        }
    }//GEN-LAST:event_L4_Desc_DamageKeyPressed
 // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L4_Qty_InKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L4_Qty_InKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L4_Qty_In.getText().matches("[1-9]+") && L4_Qty_In.getText().length() < 2 || L4_Qty_In.getText().equals("0") && L4_New_Sku.getText().matches("0")) {
                L4_Qty_In.setBackground(Color.WHITE);
                L4_Done_ChkBox.requestFocus();
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);  
            }    
        }
    }//GEN-LAST:event_L4_Qty_InKeyPressed
 // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L5_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Qty_OutKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L5_Qty_Out.getText().matches("[1-9]+") && L5_Qty_Out.getText().length() < 2 || L5_Qty_Out.getText().equals("0") && L5_First_Sku.getText().matches("0")) {
                L5_Qty_Out.setBackground(Color.WHITE);
                L5_Reason_DropDown.requestFocus();
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE); 
            }
        }
    }//GEN-LAST:event_L5_Qty_OutKeyPressed

    private void L5_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Reason_DropDownKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        L5_Desc_Damage.requestFocus();
       }
    }//GEN-LAST:event_L5_Reason_DropDownKeyPressed

    private void L5_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Desc_DamageKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {           
            if (L5_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
                L5_Desc_Damage.setBackground(Color.WHITE);
                L5_New_Sku.requestFocus();
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
            }        
        }
    }//GEN-LAST:event_L5_Desc_DamageKeyPressed
 // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L5_Qty_InKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L5_Qty_InKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L5_Qty_In.getText().matches("[1-9]+") && L5_Qty_In.getText().length() < 2 || L5_Qty_In.getText().equals("0") && L5_New_Sku.getText().matches("0")) {
                L5_Qty_In.setBackground(Color.WHITE);
                L5_Done_ChkBox.requestFocus();
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);   
            }    
        }
    }//GEN-LAST:event_L5_Qty_InKeyPressed
 // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L6_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Qty_OutKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (L6_Qty_Out.getText().matches("[1-9]+") && L6_Qty_Out.getText().length() < 2 || L6_Qty_Out.getText().equals("0") && L6_First_Sku.getText().matches("0")) {
            L6_Qty_Out.setBackground(Color.WHITE); 
            L6_Reason_DropDown.requestFocus();
      } else {
         JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);   
      }
         }
    }//GEN-LAST:event_L6_Qty_OutKeyPressed

    private void L6_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Reason_DropDownKeyPressed
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        L6_Desc_Damage.requestFocus();
       }
    }//GEN-LAST:event_L6_Reason_DropDownKeyPressed

    private void L6_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Desc_DamageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L6_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
            L6_Desc_Damage.setBackground(Color.WHITE);
            L6_New_Sku.requestFocus();
         } else {
              JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
         }        
       }
    }//GEN-LAST:event_L6_Desc_DamageKeyPressed
 // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L6_Qty_InKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L6_Qty_InKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (L6_Qty_In.getText().matches("[1-9]+") && L6_Qty_In.getText().length() < 2 || L6_Qty_In.getText().equals("0") && L6_New_Sku.getText().matches("0")) {
            L6_Qty_In.setBackground(Color.WHITE);
            L6_Done_ChkBox.requestFocus();
      } else {
         JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);   
      }    
      }
    }//GEN-LAST:event_L6_Qty_InKeyPressed
 // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L7_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Qty_OutKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (L7_Qty_Out.getText().matches("[1-9]+") && L7_Qty_Out.getText().length() < 2 || L7_Qty_Out.getText().equals("0") && L7_First_Sku.getText().matches("0")) {
             L7_Qty_Out.setBackground(Color.WHITE);
             L7_Reason_DropDown.requestFocus();
         } else {
            JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);  
         }
        }
    }//GEN-LAST:event_L7_Qty_OutKeyPressed

    private void L7_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Reason_DropDownKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        L7_Desc_Damage.requestFocus();
       }
    }//GEN-LAST:event_L7_Reason_DropDownKeyPressed

    private void L7_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Desc_DamageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L7_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
            L7_Desc_Damage.setBackground(Color.WHITE);
            L7_New_Sku.requestFocus();
        } else {
              JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        }        
       }
    }//GEN-LAST:event_L7_Desc_DamageKeyPressed
 // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L7_Qty_InKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L7_Qty_InKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
       if (L7_Qty_In.getText().matches("[1-9]+") && L7_Qty_In.getText().length() < 2 || L7_Qty_In.getText().equals("0") && L7_New_Sku.getText().matches("0")) {
            L7_Qty_In.setBackground(Color.WHITE);
            L7_Done_ChkBox.requestFocus();        
      } else {
         JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);
      }    
      }
    }//GEN-LAST:event_L7_Qty_InKeyPressed
 // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L8_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Qty_OutKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (L8_Qty_Out.getText().matches("[1-9]+") && L8_Qty_Out.getText().length() < 2 || L8_Qty_Out.getText().equals("0") && L8_First_Sku.getText().matches("0")) {
            L8_Qty_Out.setBackground(Color.WHITE);
            L8_Reason_DropDown.requestFocus();
         } else {
                JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);   
         }
        }
    }//GEN-LAST:event_L8_Qty_OutKeyPressed

    private void L8_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Reason_DropDownKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        L8_Desc_Damage.requestFocus();
       }
    }//GEN-LAST:event_L8_Reason_DropDownKeyPressed

    private void L8_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Desc_DamageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L8_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
            L8_Desc_Damage.setBackground(Color.WHITE);
            L8_New_Sku.requestFocus();
        } else {
              JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        }        
       }
    }//GEN-LAST:event_L8_Desc_DamageKeyPressed
 // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L8_Qty_InKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L8_Qty_InKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
       if (L8_Qty_In.getText().matches("[1-9]+") && L8_Qty_In.getText().length() < 2 || L8_Qty_In.getText().equals("0") && L8_New_Sku.getText().matches("0")) {
            L8_Qty_In.setBackground(Color.WHITE);
            L8_Done_ChkBox.requestFocus();        
      } else {
         JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);   
      }    
      }
    }//GEN-LAST:event_L8_Qty_InKeyPressed
 // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L9_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Qty_OutKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (L9_Qty_Out.getText().matches("[1-9]+") && L9_Qty_Out.getText().length() < 2 ) {
            L9_Qty_Out.setBackground(Color.WHITE);
            L9_Reason_DropDown.requestFocus();
         } else {
            JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);  
         }
        }
    }//GEN-LAST:event_L9_Qty_OutKeyPressed

    private void L9_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Reason_DropDownKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        L9_Desc_Damage.requestFocus();
       }
    }//GEN-LAST:event_L9_Reason_DropDownKeyPressed

    private void L9_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Desc_DamageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L9_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
            L9_Desc_Damage.setBackground(Color.WHITE);
            L9_New_Sku.requestFocus();
        } else {
              JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        }        
       }
    }//GEN-LAST:event_L9_Desc_DamageKeyPressed
 // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L9_Qty_InKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L9_Qty_InKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (L9_Qty_In.getText().matches("[1-9]+") && L9_Qty_In.getText().length() < 2 || L9_Qty_In.getText().equals("0") && L9_New_Sku.getText().matches("0")) {
             L9_Qty_In.setBackground(Color.WHITE);
             L9_Done_ChkBox.requestFocus();        
        } else {
         JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);   
        }    
    }
    }//GEN-LAST:event_L9_Qty_InKeyPressed
 // Check if input is number between 1-9 and not other characters (a, !, /, etc)
    private void L10_Qty_OutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Qty_OutKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (L10_Qty_Out.getText().matches("[1-9]+") && L10_Qty_Out.getText().length() < 2 || L10_Qty_Out.getText().equals("0") && L10_First_Sku.getText().matches("0")) {
             L10_Qty_Out.setBackground(Color.WHITE);
             L10_Reason_DropDown.requestFocus();
        } else {
            JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty Out Error", JOptionPane.ERROR_MESSAGE);   
        }
    }
    }//GEN-LAST:event_L10_Qty_OutKeyPressed

    private void L10_Reason_DropDownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Reason_DropDownKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        L10_Desc_Damage.requestFocus();
       }
    }//GEN-LAST:event_L10_Reason_DropDownKeyPressed

    private void L10_Desc_DamageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Desc_DamageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (L10_Desc_Damage.getText().matches("[a-zA-Z ]+")) {
            L10_Desc_Damage.setBackground(Color.WHITE);
            L10_New_Sku.requestFocus();
        } else {
              JOptionPane.showMessageDialog(frame, "Wrong input, you can only input letters in this field and cannot be blank", "Desc / Explanation Cannot be blank Error", JOptionPane.ERROR_MESSAGE);
        }
       }
    }//GEN-LAST:event_L10_Desc_DamageKeyPressed
 // Check if input is number between 1-9 and not other characters (a, !, /, etc)
 // inserts 0 if left blank because second sku and qty is optional
    private void L10_Qty_InKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L10_Qty_InKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (L10_Qty_In.getText().matches("[1-9]+") && L10_Qty_In.getText().length() < 2 || L10_Qty_In.getText().equals("0") && L10_New_Sku.getText().matches("0")) {
            L10_Qty_In.setBackground(Color.WHITE);
            L10_Done_ChkBox.requestFocus();        
        } else {
         JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);  
        }
    }
    }//GEN-LAST:event_L10_Qty_InKeyPressed
    // removes data from existing forms header data table and inserts it into compeleted header table
    // saves all line data in database table archive
    private void RdyforExportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdyforExportBtnActionPerformed
    int response = JOptionPane.showConfirmDialog(null, "Do you want to Confirm?", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.NO_OPTION) {
      System.out.println("No button clicked");      
    } else if (response == JOptionPane.YES_OPTION) {
      System.out.println("Yes button clicked");	
        try {
                IASdao.LineArchive();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        try {
                IASdao.FormReadyForExport();
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

    private void L1_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L1_First_SkuFocusLost
        IASdao.firstSkuInput = L1_First_Sku.getText();
            if (L1_First_Sku.getText().matches("[0-9]+") && L1_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L1_First_Sku.setBackground(Color.WHITE);
                L1_First_Desc.setText(IASdao.firstSkuDesc);
                L1_First_Attr.setText(IASdao.firstSkuAttr);
                L1_First_Size.setText(IASdao.firstSkuSize);
                L1_Orig_Retail.setText(IASdao.firstSkuPr);
            }
            }
    }//GEN-LAST:event_L1_First_SkuFocusLost

    private void L1_New_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L1_New_SkuFocusLost
        IASdao.secondSkuInput = L1_New_Sku.getText();
        if (!L1_New_Sku.getText().equals(L1_First_Sku.getText())) {
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L1_New_Sku.getText().matches("[0-9]+") && L1_New_Sku.getText().length() < 7 ) {
                    L1_New_Sku.setBackground(Color.WHITE);
                    L1_New_Desc.setText(IASdao.secondSkuDesc);
                    L1_New_Attr.setText(IASdao.secondSkuAttr);
                    L1_New_Size.setText(IASdao.secondSkuSize);    
                }
            }
        }
    }//GEN-LAST:event_L1_New_SkuFocusLost

    private void L2_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L2_First_SkuFocusLost
        IASdao.firstSkuInput = L2_First_Sku.getText();
            if (L2_First_Sku.getText().matches("[0-9]+") && L2_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L2_First_Sku.setBackground(Color.WHITE);
                L2_First_Desc.setText(IASdao.firstSkuDesc);
                L2_First_Attr.setText(IASdao.firstSkuAttr);
                L2_First_Size.setText(IASdao.firstSkuSize);
                L2_Orig_Retail.setText(IASdao.firstSkuPr);    
            }
            }
    }//GEN-LAST:event_L2_First_SkuFocusLost

    private void L2_New_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L2_New_SkuFocusLost
        IASdao.secondSkuInput = L2_New_Sku.getText();
            if (!L2_New_Sku.getText().equals(L2_First_Sku.getText())) {
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L2_New_Sku.getText().matches("[0-9]+") && L2_New_Sku.getText().length() < 7 ) {
                    L2_New_Sku.setBackground(Color.WHITE);
                    L2_New_Desc.setText(IASdao.secondSkuDesc);
                    L2_New_Attr.setText(IASdao.secondSkuAttr);
                    L2_New_Size.setText(IASdao.secondSkuSize);
                }
            }
            }
    }//GEN-LAST:event_L2_New_SkuFocusLost

    private void L3_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L3_First_SkuFocusLost
        IASdao.firstSkuInput = L3_First_Sku.getText();
            if (L3_First_Sku.getText().matches("[0-9]+") && L3_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L3_First_Sku.setBackground(Color.WHITE);
                L3_First_Desc.setText(IASdao.firstSkuDesc);
                L3_First_Attr.setText(IASdao.firstSkuAttr);
                L3_First_Size.setText(IASdao.firstSkuSize);
                L3_Orig_Retail.setText(IASdao.firstSkuPr);
            }
            }
    }//GEN-LAST:event_L3_First_SkuFocusLost

    private void L3_New_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L3_New_SkuFocusLost
        IASdao.secondSkuInput = L3_New_Sku.getText();
            if (!L3_New_Sku.getText().equals(L3_First_Sku.getText())) {
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L3_New_Sku.getText().matches("[0-9]+") && L3_New_Sku.getText().length() < 7 ) {
                    L3_New_Sku.setBackground(Color.WHITE);
                    L3_New_Desc.setText(IASdao.secondSkuDesc);
                    L3_New_Attr.setText(IASdao.secondSkuAttr);
                    L3_New_Size.setText(IASdao.secondSkuSize);           
            }
            }
            }
    }//GEN-LAST:event_L3_New_SkuFocusLost

    private void L4_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L4_First_SkuFocusLost
        IASdao.firstSkuInput = L4_First_Sku.getText();
            if (L4_First_Sku.getText().matches("[0-9]+") && L4_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L4_First_Sku.setBackground(Color.WHITE);
                L4_First_Desc.setText(IASdao.firstSkuDesc);
                L4_First_Attr.setText(IASdao.firstSkuAttr);
                L4_First_Size.setText(IASdao.firstSkuSize);
                L4_Orig_Retail.setText(IASdao.firstSkuPr);       
            }
            }
    }//GEN-LAST:event_L4_First_SkuFocusLost

    private void L4_New_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L4_New_SkuFocusLost
        IASdao.secondSkuInput = L4_New_Sku.getText();
            if (!L4_New_Sku.getText().equals(L4_First_Sku.getText())) {
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L4_New_Sku.getText().matches("[0-9]+") && L4_New_Sku.getText().length() < 7 ) {
                    L4_New_Sku.setBackground(Color.WHITE);
                    L4_New_Desc.setText(IASdao.secondSkuDesc);
                    L4_New_Attr.setText(IASdao.secondSkuAttr);
                    L4_New_Size.setText(IASdao.secondSkuSize);     
            }
            }
            }
    }//GEN-LAST:event_L4_New_SkuFocusLost

    private void L5_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L5_First_SkuFocusLost
        IASdao.firstSkuInput = L5_First_Sku.getText();
            if (L5_First_Sku.getText().matches("[0-9]+") && L5_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L5_First_Sku.setBackground(Color.WHITE);
                L5_First_Desc.setText(IASdao.firstSkuDesc);
                L5_First_Attr.setText(IASdao.firstSkuAttr);
                L5_First_Size.setText(IASdao.firstSkuSize);
                L5_Orig_Retail.setText(IASdao.firstSkuPr);
            }
            }
    }//GEN-LAST:event_L5_First_SkuFocusLost

    private void L5_New_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L5_New_SkuFocusLost
        IASdao.secondSkuInput = L5_New_Sku.getText();
            if (!L5_New_Sku.getText().equals(L5_First_Sku.getText())) {
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L5_New_Sku.getText().matches("[0-9]+") && L5_New_Sku.getText().length() < 7 ) {
                    L5_New_Sku.setBackground(Color.WHITE);
                    L5_New_Desc.setText(IASdao.secondSkuDesc);
                    L5_New_Attr.setText(IASdao.secondSkuAttr);
                    L5_New_Size.setText(IASdao.secondSkuSize);    
            }
            }
            }
    }//GEN-LAST:event_L5_New_SkuFocusLost

    private void L6_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L6_First_SkuFocusLost
        IASdao.firstSkuInput = L6_First_Sku.getText();
            if (L6_First_Sku.getText().matches("[0-9]+") && L6_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L6_First_Sku.setBackground(Color.WHITE);
                L6_First_Desc.setText(IASdao.firstSkuDesc);
                L6_First_Attr.setText(IASdao.firstSkuAttr);
                L6_First_Size.setText(IASdao.firstSkuSize);
                L6_Orig_Retail.setText(IASdao.firstSkuPr);
            }
            }
    }//GEN-LAST:event_L6_First_SkuFocusLost

    private void L6_New_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L6_New_SkuFocusLost
        IASdao.secondSkuInput = L6_New_Sku.getText();
            if (!L6_New_Sku.getText().equals(L6_First_Sku.getText())) {
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L6_New_Sku.getText().matches("[0-9]+") && L6_New_Sku.getText().length() < 7 ) {
                    L6_New_Sku.setBackground(Color.WHITE);
                    L6_New_Desc.setText(IASdao.secondSkuDesc);
                    L6_New_Attr.setText(IASdao.secondSkuAttr);
                    L6_New_Size.setText(IASdao.secondSkuSize);            
            }
            }
            }
    }//GEN-LAST:event_L6_New_SkuFocusLost

    private void L7_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L7_First_SkuFocusLost
        IASdao.firstSkuInput = L7_First_Sku.getText();
            if (L7_First_Sku.getText().matches("[0-9]+") && L7_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L7_First_Sku.setBackground(Color.WHITE);
                L7_First_Desc.setText(IASdao.firstSkuDesc);
                L7_First_Attr.setText(IASdao.firstSkuAttr);
                L7_First_Size.setText(IASdao.firstSkuSize);
                L7_Orig_Retail.setText(IASdao.firstSkuPr);
            }
            }
    }//GEN-LAST:event_L7_First_SkuFocusLost

    private void L7_New_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L7_New_SkuFocusLost
        IASdao.secondSkuInput = L7_New_Sku.getText();
            if (!L7_New_Sku.getText().equals(L7_First_Sku.getText())) {
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L7_New_Sku.getText().matches("[0-9]+") && L7_New_Sku.getText().length() < 7 ) {
                    L7_New_Sku.setBackground(Color.WHITE);
                    L7_New_Desc.setText(IASdao.secondSkuDesc);
                    L7_New_Attr.setText(IASdao.secondSkuAttr);
                    L7_New_Size.setText(IASdao.secondSkuSize);        
            }
            }
            }
    }//GEN-LAST:event_L7_New_SkuFocusLost

    private void L8_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L8_First_SkuFocusLost
        IASdao.firstSkuInput = L8_First_Sku.getText();
            if (L8_First_Sku.getText().matches("[0-9]+") && L8_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L8_First_Sku.setBackground(Color.WHITE);
                L8_First_Desc.setText(IASdao.firstSkuDesc);
                L8_First_Attr.setText(IASdao.firstSkuAttr);
                L8_First_Size.setText(IASdao.firstSkuSize);
                L8_Orig_Retail.setText(IASdao.firstSkuPr);     
            }
            }
    }//GEN-LAST:event_L8_First_SkuFocusLost

    private void L8_New_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L8_New_SkuFocusLost
        IASdao.secondSkuInput = L8_New_Sku.getText();
            if (!L8_New_Sku.getText().equals(L8_First_Sku.getText())) {
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L8_New_Sku.getText().matches("[0-9]+") && L8_New_Sku.getText().length() < 7 ) {
                    L8_New_Sku.setBackground(Color.WHITE);
                    L8_New_Desc.setText(IASdao.secondSkuDesc);
                    L8_New_Attr.setText(IASdao.secondSkuAttr);
                    L8_New_Size.setText(IASdao.secondSkuSize);           
            }
            }
            }
    }//GEN-LAST:event_L8_New_SkuFocusLost

    private void L9_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L9_First_SkuFocusLost
        IASdao.firstSkuInput = L9_First_Sku.getText();
            if (L9_First_Sku.getText().matches("[0-9]+") && L9_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L9_First_Sku.setBackground(Color.WHITE);
                L9_First_Desc.setText(IASdao.firstSkuDesc);
                L9_First_Attr.setText(IASdao.firstSkuAttr);
                L9_First_Size.setText(IASdao.firstSkuSize);
                L9_Orig_Retail.setText(IASdao.firstSkuPr);
            }
            }
    }//GEN-LAST:event_L9_First_SkuFocusLost

    private void L9_New_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L9_New_SkuFocusLost
        IASdao.secondSkuInput = L9_New_Sku.getText();
            if (!L9_New_Sku.getText().equals(L9_First_Sku.getText())) {
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L9_New_Sku.getText().matches("[0-9]+") && L9_New_Sku.getText().length() < 7 ) {
                    L9_New_Sku.setBackground(Color.WHITE);
                    L9_New_Desc.setText(IASdao.secondSkuDesc);
                    L9_New_Attr.setText(IASdao.secondSkuAttr);
                    L9_New_Size.setText(IASdao.secondSkuSize);          
            }
            }
            }
    }//GEN-LAST:event_L9_New_SkuFocusLost

    private void L10_First_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L10_First_SkuFocusLost
        IASdao.firstSkuInput = L10_First_Sku.getText();
            if (L10_First_Sku.getText().matches("[0-9]+") && L10_First_Sku.getText().length() < 7 ) {
            String[] args = null;
            try {
                IASdao.main(args);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt.equals("good")) {
                L10_First_Sku.setBackground(Color.WHITE);
                L10_First_Desc.setText(IASdao.firstSkuDesc);
                L10_First_Attr.setText(IASdao.firstSkuAttr);
                L10_First_Size.setText(IASdao.firstSkuSize);
                L10_Orig_Retail.setText(IASdao.firstSkuPr);      
            }
            }
    }//GEN-LAST:event_L10_First_SkuFocusLost

    private void L10_New_SkuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L10_New_SkuFocusLost
        IASdao.secondSkuInput = L10_New_Sku.getText();
            if (!L10_New_Sku.getText().equals(L10_First_Sku.getText())) {
            // String[] args = null;
            try {
                IASdao.skuCheck2();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (IASdao.skuRslt2.equals("good")) {
                if (L10_New_Sku.getText().matches("[0-9]+") && L10_New_Sku.getText().length() < 7 ) {
                    L10_New_Sku.setBackground(Color.WHITE);
                    L10_New_Desc.setText(IASdao.secondSkuDesc);
                    L10_New_Attr.setText(IASdao.secondSkuAttr);
                    L10_New_Size.setText(IASdao.secondSkuSize);         
            }
            }
            }
    }//GEN-LAST:event_L10_New_SkuFocusLost

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
                         IASdao.ClearLine1();
                         L1_First_Sku.setText("");
                         L1_Qty_Out.setText("");
                         L1_First_Desc.setText("");
                         L1_First_Attr.setText("");
                         L1_First_Size.setText("");
                         L1_Orig_Retail.setText("");
                         L1_Reason_DropDown.setSelectedItem("");
                         L1_Desc_Damage.setText("");
                         L1_New_Sku.setText("");
                         L1_New_Desc.setText("");
                         L1_New_Attr.setText("");
                         L1_New_Size.setText("");
                         L1_Qty_In.setText("");
                         L1_Done_ChkBox.setSelected(false);
                         L1_Timestamp.setText("");
                         L1_First_Sku.setEnabled(true);
                         L1_Qty_Out.setEnabled(true);
                         L1_First_Desc.setEnabled(true);
                         L1_First_Attr.setEnabled(true);
                         L1_First_Size.setEnabled(true);
                         L1_Orig_Retail.setEnabled(true);
                         L1_Reason_DropDown.setEnabled(true);
                         L1_Desc_Damage.setEnabled(true);
                         L1_New_Sku.setEnabled(true);
                         L1_New_Desc.setEnabled(true);
                         L1_New_Attr.setEnabled(true);
                         L1_New_Size.setEnabled(true);
                         L1_Qty_In.setEnabled(true);
                         CLine_textfield.setText("");
                     } catch (SQLException ex) {
                         Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                     }    break;
                 case "2":
                     try {
                         IASdao.ClearLine2();
                         L2_First_Sku.setText("");
                         L2_Qty_Out.setText("");
                         L2_First_Desc.setText("");
                         L2_First_Attr.setText("");
                         L2_First_Size.setText("");
                         L2_Orig_Retail.setText("");
                         L2_Reason_DropDown.setSelectedItem("");
                         L2_Desc_Damage.setText("");
                         L2_New_Sku.setText("");
                         L2_New_Desc.setText("");
                         L2_New_Attr.setText("");
                         L2_New_Size.setText("");
                         L2_Qty_In.setText("");
                         L2_Done_ChkBox.setSelected(false);
                         L2_Timestamp.setText("");
                         L2_First_Sku.setEnabled(true);
                         L2_Qty_Out.setEnabled(true);
                         L2_First_Desc.setEnabled(true);
                         L2_First_Attr.setEnabled(true);
                         L2_First_Size.setEnabled(true);
                         L2_Orig_Retail.setEnabled(true);
                         L2_Reason_DropDown.setEnabled(true);
                         L2_Desc_Damage.setEnabled(true);
                         L2_New_Sku.setEnabled(true);
                         L2_New_Desc.setEnabled(true);
                         L2_New_Attr.setEnabled(true);
                         L2_New_Size.setEnabled(true);
                         L2_Qty_In.setEnabled(true);
                         CLine_textfield.setText("");
                     } catch (SQLException ex) {
                         Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                     }    break;
                 case "3":
                     try {
                         IASdao.ClearLine3();
                         L3_First_Sku.setText("");
                         L3_Qty_Out.setText("");
                         L3_First_Desc.setText("");
                         L3_First_Attr.setText("");
                         L3_First_Size.setText("");
                         L3_Orig_Retail.setText("");
                         L3_Reason_DropDown.setSelectedItem("");
                         L3_Desc_Damage.setText("");
                         L3_New_Sku.setText("");
                         L3_New_Desc.setText("");
                         L3_New_Attr.setText("");
                         L3_New_Size.setText("");
                         L3_Qty_In.setText("");
                         L3_Done_ChkBox.setSelected(false);
                         L3_Timestamp.setText("");
                         L3_First_Sku.setEnabled(true);
                         L3_Qty_Out.setEnabled(true);
                         L3_First_Desc.setEnabled(true);
                         L3_First_Attr.setEnabled(true);
                         L3_First_Size.setEnabled(true);
                         L3_Orig_Retail.setEnabled(true);
                         L3_Reason_DropDown.setEnabled(true);
                         L3_Desc_Damage.setEnabled(true);
                         L3_New_Sku.setEnabled(true);
                         L3_New_Desc.setEnabled(true);
                         L3_New_Attr.setEnabled(true);
                         L3_New_Size.setEnabled(true);
                         L3_Qty_In.setEnabled(true);
                         CLine_textfield.setText("");
                     } catch (SQLException ex) {
                         Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                     }    break;
                 case "4":
                     try {
                         IASdao.ClearLine4();
                         L4_First_Sku.setText("");
                         L4_Qty_Out.setText("");
                         L4_First_Desc.setText("");
                         L4_First_Attr.setText("");
                         L4_First_Size.setText("");
                         L4_Orig_Retail.setText("");
                         L4_Reason_DropDown.setSelectedItem("");
                         L4_Desc_Damage.setText("");
                         L4_New_Sku.setText("");
                         L4_New_Desc.setText("");
                         L4_New_Attr.setText("");
                         L4_New_Size.setText("");
                         L4_Qty_In.setText("");
                         L4_Done_ChkBox.setSelected(false);
                         L4_Timestamp.setText("");
                         L4_First_Sku.setEnabled(true);
                         L4_Qty_Out.setEnabled(true);
                         L4_First_Desc.setEnabled(true);
                         L4_First_Attr.setEnabled(true);
                         L4_First_Size.setEnabled(true);
                         L4_Orig_Retail.setEnabled(true);
                         L4_Reason_DropDown.setEnabled(true);
                         L4_Desc_Damage.setEnabled(true);
                         L4_New_Sku.setEnabled(true);
                         L4_New_Desc.setEnabled(true);
                         L4_New_Attr.setEnabled(true);
                         L4_New_Size.setEnabled(true);
                         L4_Qty_In.setEnabled(true);
                         CLine_textfield.setText("");
                     } catch (SQLException ex) {
                         Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                     }    break;
                 case "5":
                     try {
                         IASdao.ClearLine5();
                         L5_First_Sku.setText("");
                         L5_Qty_Out.setText("");
                         L5_First_Desc.setText("");
                         L5_First_Attr.setText("");
                         L5_First_Size.setText("");
                         L5_Orig_Retail.setText("");
                         L5_Reason_DropDown.setSelectedItem("");
                         L5_Desc_Damage.setText("");
                         L5_New_Sku.setText("");
                         L5_New_Desc.setText("");
                         L5_New_Attr.setText("");
                         L5_New_Size.setText("");
                         L5_Qty_In.setText("");
                         L5_Done_ChkBox.setSelected(false);
                         L5_Timestamp.setText("");
                         L5_First_Sku.setEnabled(true);
                         L5_Qty_Out.setEnabled(true);
                         L5_First_Desc.setEnabled(true);
                         L5_First_Attr.setEnabled(true);
                         L5_First_Size.setEnabled(true);
                         L5_Orig_Retail.setEnabled(true);
                         L5_Reason_DropDown.setEnabled(true);
                         L5_Desc_Damage.setEnabled(true);
                         L5_New_Sku.setEnabled(true);
                         L5_New_Desc.setEnabled(true);
                         L5_New_Attr.setEnabled(true);
                         L5_New_Size.setEnabled(true);
                         L5_Qty_In.setEnabled(true);
                         CLine_textfield.setText("");
                     } catch (SQLException ex) {
                         Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                     }    break;
                 case "6":
                     try {
                         IASdao.ClearLine6();
                         L6_First_Sku.setText("");
                         L6_Qty_Out.setText("");
                         L6_First_Desc.setText("");
                         L6_First_Attr.setText("");
                         L6_First_Size.setText("");
                         L6_Orig_Retail.setText("");
                         L6_Reason_DropDown.setSelectedItem("");
                         L6_Desc_Damage.setText("");
                         L6_New_Sku.setText("");
                         L6_New_Desc.setText("");
                         L6_New_Attr.setText("");
                         L6_New_Size.setText("");
                         L6_Qty_In.setText("");
                         L6_Done_ChkBox.setSelected(false);
                         L6_Timestamp.setText("");
                         L6_First_Sku.setEnabled(true);
                         L6_Qty_Out.setEnabled(true);
                         L6_First_Desc.setEnabled(true);
                         L6_First_Attr.setEnabled(true);
                         L6_First_Size.setEnabled(true);
                         L6_Orig_Retail.setEnabled(true);
                         L6_Reason_DropDown.setEnabled(true);
                         L6_Desc_Damage.setEnabled(true);
                         L6_New_Sku.setEnabled(true);
                         L6_New_Desc.setEnabled(true);
                         L6_New_Attr.setEnabled(true);
                         L6_New_Size.setEnabled(true);
                         L6_Qty_In.setEnabled(true);
                         CLine_textfield.setText("");
                     } catch (SQLException ex) {
                         Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                     }    break;
                 case "7":
                     try {
                         IASdao.ClearLine7();
                         L7_First_Sku.setText("");
                         L7_Qty_Out.setText("");
                         L7_First_Desc.setText("");
                         L7_First_Attr.setText("");
                         L7_First_Size.setText("");
                         L7_Orig_Retail.setText("");
                         L7_Reason_DropDown.setSelectedItem("");
                         L7_Desc_Damage.setText("");
                         L7_New_Sku.setText("");
                         L7_New_Desc.setText("");
                         L7_New_Attr.setText("");
                         L7_New_Size.setText("");
                         L7_Qty_In.setText("");
                         L7_Done_ChkBox.setSelected(false);
                         L7_Timestamp.setText("");
                         L7_First_Sku.setEnabled(true);
                         L7_Qty_Out.setEnabled(true);
                         L7_First_Desc.setEnabled(true);
                         L7_First_Attr.setEnabled(true);
                         L7_First_Size.setEnabled(true);
                         L7_Orig_Retail.setEnabled(true);
                         L7_Reason_DropDown.setEnabled(true);
                         L7_Desc_Damage.setEnabled(true);
                         L7_New_Sku.setEnabled(true);
                         L7_New_Desc.setEnabled(true);
                         L7_New_Attr.setEnabled(true);
                         L7_New_Size.setEnabled(true);
                         L7_Qty_In.setEnabled(true);
                         CLine_textfield.setText("");
                     } catch (SQLException ex) {
                         Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                     }    break;
                 case "8":
                     try {
                         IASdao.ClearLine8();
                         L8_First_Sku.setText("");
                         L8_Qty_Out.setText("");
                         L8_First_Desc.setText("");
                         L8_First_Attr.setText("");
                         L8_First_Size.setText("");
                         L8_Orig_Retail.setText("");
                         L8_Reason_DropDown.setSelectedItem("");
                         L8_Desc_Damage.setText("");
                         L8_New_Sku.setText("");
                         L8_New_Desc.setText("");
                         L8_New_Attr.setText("");
                         L8_New_Size.setText("");
                         L8_Qty_In.setText("");
                         L8_Done_ChkBox.setSelected(false);
                         L8_Timestamp.setText("");
                         L8_First_Sku.setEnabled(true);
                         L8_Qty_Out.setEnabled(true);
                         L8_First_Desc.setEnabled(true);
                         L8_First_Attr.setEnabled(true);
                         L8_First_Size.setEnabled(true);
                         L8_Orig_Retail.setEnabled(true);
                         L8_Reason_DropDown.setEnabled(true);
                         L8_Desc_Damage.setEnabled(true);
                         L8_New_Sku.setEnabled(true);
                         L8_New_Desc.setEnabled(true);
                         L8_New_Attr.setEnabled(true);
                         L8_New_Size.setEnabled(true);
                         L8_Qty_In.setEnabled(true);
                         CLine_textfield.setText("");
                     } catch (SQLException ex) {
                         Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                     }    break;
                 case "9":
                     try {
                         IASdao.ClearLine9();
                         L9_First_Sku.setText("");
                         L9_Qty_Out.setText("");
                         L9_First_Desc.setText("");
                         L9_First_Attr.setText("");
                         L9_First_Size.setText("");
                         L9_Orig_Retail.setText("");
                         L9_Reason_DropDown.setSelectedItem("");
                         L9_Desc_Damage.setText("");
                         L9_New_Sku.setText("");
                         L9_New_Desc.setText("");
                         L9_New_Attr.setText("");
                         L9_New_Size.setText("");
                         L9_Qty_In.setText("");
                         L9_Done_ChkBox.setSelected(false);
                         L9_Timestamp.setText("");
                         L9_First_Sku.setEnabled(true);
                         L9_Qty_Out.setEnabled(true);
                         L9_First_Desc.setEnabled(true);
                         L9_First_Attr.setEnabled(true);
                         L9_First_Size.setEnabled(true);
                         L9_Orig_Retail.setEnabled(true);
                         L9_Reason_DropDown.setEnabled(true);
                         L9_Desc_Damage.setEnabled(true);
                         L9_New_Sku.setEnabled(true);
                         L9_New_Desc.setEnabled(true);
                         L9_New_Attr.setEnabled(true);
                         L9_New_Size.setEnabled(true);
                         L9_Qty_In.setEnabled(true);
                         CLine_textfield.setText("");
                     } catch (SQLException ex) {
                         Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                     }    break;
                 case "10":
                     try {
                        IASdao.ClearLine10();
                        L10_First_Sku.setText("");
                        L10_Qty_Out.setText("");
                        L10_First_Desc.setText("");
                        L10_First_Attr.setText("");
                        L10_First_Size.setText("");
                        L10_Orig_Retail.setText("");
                        L10_Reason_DropDown.setSelectedItem("");
                        L10_Desc_Damage.setText("");
                        L10_New_Sku.setText("");
                        L10_New_Desc.setText("");
                        L10_New_Attr.setText("");
                        L10_New_Size.setText("");
                        L10_Qty_In.setText("");
                        L10_Done_ChkBox.setSelected(false);
                        L10_Timestamp.setText("");
                        L10_First_Sku.setEnabled(true);
                        L10_Qty_Out.setEnabled(true);
                        L10_First_Desc.setEnabled(true);
                        L10_First_Attr.setEnabled(true);
                        L10_First_Size.setEnabled(true);
                        L10_Orig_Retail.setEnabled(true);
                        L10_Reason_DropDown.setEnabled(true);
                        L10_Desc_Damage.setEnabled(true);
                        L10_New_Sku.setEnabled(true);
                        L10_New_Desc.setEnabled(true);
                        L10_New_Attr.setEnabled(true);
                        L10_New_Size.setEnabled(true);
                        L10_Qty_In.setEnabled(true);
                        CLine_textfield.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }    break;
                default:
                     CLine_textfield.setText("");
                     JOptionPane.showMessageDialog(frame, "You can only enter lines 1-10\nPlease try again", "Clear Line Error", JOptionPane.ERROR_MESSAGE);
                     break;
             }
        } else if (response == JOptionPane.CLOSED_OPTION) {
            System.out.println("JOptionPane closed");
        }
    }//GEN-LAST:event_CLine_ButtonActionPerformed

    private void L1_Qty_InKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_L1_Qty_InKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (L1_Qty_In.getText().matches("[1-9]+") && L1_Qty_In.getText().length() < 2 || L1_Qty_In.getText().matches("0") && L1_New_Sku.getText().matches("0")) {
             L1_Done_ChkBox.requestFocus();
             L1_Qty_In.setBackground(Color.WHITE);
      } else {
         JOptionPane.showMessageDialog(frame, "Wrong input, Qty must be a number between 1-9", "Qty In Error", JOptionPane.ERROR_MESSAGE);  
      }    
      }
    }//GEN-LAST:event_L1_Qty_InKeyPressed

    private void L1_Desc_DamageFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L1_Desc_DamageFocusGained
        jPanel6.scrollRectToVisible(L1_Timestamp.getBounds(null));
    }//GEN-LAST:event_L1_Desc_DamageFocusGained

    private void L2_Desc_DamageFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L2_Desc_DamageFocusGained
        jPanel6.scrollRectToVisible(L2_Timestamp.getBounds(null));
    }//GEN-LAST:event_L2_Desc_DamageFocusGained

    private void L3_Desc_DamageFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L3_Desc_DamageFocusGained
        jPanel6.scrollRectToVisible(L3_Timestamp.getBounds(null));
    }//GEN-LAST:event_L3_Desc_DamageFocusGained

    private void L4_Desc_DamageFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L4_Desc_DamageFocusGained
        jPanel6.scrollRectToVisible(L4_Timestamp.getBounds(null));
    }//GEN-LAST:event_L4_Desc_DamageFocusGained

    private void L5_Desc_DamageFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L5_Desc_DamageFocusGained
        jPanel6.scrollRectToVisible(L5_Timestamp.getBounds(null));
    }//GEN-LAST:event_L5_Desc_DamageFocusGained

    private void L6_Desc_DamageFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L6_Desc_DamageFocusGained
        jPanel6.scrollRectToVisible(L6_Timestamp.getBounds(null));
    }//GEN-LAST:event_L6_Desc_DamageFocusGained

    private void L7_Desc_DamageFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L7_Desc_DamageFocusGained
        jPanel6.scrollRectToVisible(L7_Timestamp.getBounds(null));
    }//GEN-LAST:event_L7_Desc_DamageFocusGained

    private void L8_Desc_DamageFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L8_Desc_DamageFocusGained
        jPanel6.scrollRectToVisible(L8_Timestamp.getBounds(null));
    }//GEN-LAST:event_L8_Desc_DamageFocusGained

    private void L9_Desc_DamageFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L9_Desc_DamageFocusGained
        jPanel6.scrollRectToVisible(L9_Timestamp.getBounds(null));
    }//GEN-LAST:event_L9_Desc_DamageFocusGained

    private void L10_Desc_DamageFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_L10_Desc_DamageFocusGained
        jPanel6.scrollRectToVisible(L10_Timestamp.getBounds(null));
    }//GEN-LAST:event_L10_Desc_DamageFocusGained

    private void Delete_Form_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_Form_BtnActionPerformed
        Toolkit.getDefaultToolkit().beep();
        int response = JOptionPane.showConfirmDialog(null, "Do you want to Delete Form?", "Confirm Delete",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            System.out.println("No button clicked");
        } else if (response == JOptionPane.YES_OPTION) {
            try {
                IASdao.DeleteForm();
            } catch (SQLException ex) {
                Logger.getLogger(EBAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(frame, "Form Has Been Deleted");
            InvAdj.Rpnt();
            this.dispose();
        }
    }//GEN-LAST:event_Delete_Form_BtnActionPerformed

    private void IC_Print_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IC_Print_BtnActionPerformed
        String[] args = null;
        PrintForms.formType = ("IAS");
        PrintForms.main(args);
    }//GEN-LAST:event_IC_Print_BtnActionPerformed
    
    // this makes the "Submit", "Approve" and "Ready For Export" buttons appear only after checkbox is checked and sku is not null to prevent accidental submission of data to archive
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
    }
    
    public static void rChkLn5() {
        if (L5_Done_ChkBox.isSelected()== true) {
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
         if (L5_Done_ChkBox.isSelected()== true) {
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
    }
    
    // ** not currently working as intended and not currently implemented **
    protected static void clearIfNew() {
                    L1_First_Sku.removeAll();
                    L1_Qty_Out.removeAll();
                    L1_First_Desc.setText("");
                    L1_First_Attr.setText("");
                    L1_First_Size.setText("");
                    L1_Orig_Retail.setText("");
                    L1_Reason_DropDown.setSelectedItem("");
                    L1_Desc_Damage.setText("");
                    L1_New_Sku.setText("");
                    L1_New_Desc.setText("");
                    L1_New_Attr.setText("");
                    L1_New_Size.setText("");
                    L1_Qty_In.setText("");
                    L1_Done_ChkBox.setSelected(false);
                    L1_Timestamp.setText("");
                    
                    L2_Qty_Out.setText("");
                    L2_First_Desc.setText("");
                    L2_First_Attr.setText("");
                    L2_First_Size.setText("");
                    L2_Orig_Retail.setText("");
                    L2_Reason_DropDown.setSelectedItem("");
                    L2_Desc_Damage.setText("");
                    L2_New_Sku.setText("");
                    L2_New_Desc.setText("");
                    L2_New_Attr.setText("");
                    L2_New_Size.setText("");
                    L2_Qty_In.setText("");
                    L2_Done_ChkBox.setSelected(false);
                    L2_Timestamp.setText("");
                    
                    L3_Qty_Out.setText("");
                    L3_First_Desc.setText("");
                    L3_First_Attr.setText("");
                    L3_First_Size.setText("");
                    L3_Orig_Retail.setText("");
                    L3_Reason_DropDown.setSelectedItem("");
                    L3_Desc_Damage.setText("");
                    L3_New_Sku.setText("");
                    L3_New_Desc.setText("");
                    L3_New_Attr.setText("");
                    L3_New_Size.setText("");
                    L3_Qty_In.setText("");
                    L3_Done_ChkBox.setSelected(false);
                    L3_Timestamp.setText("");
                    
                    L4_Qty_Out.setText("");
                    L4_First_Desc.setText("");
                    L4_First_Attr.setText("");
                    L4_First_Size.setText("");
                    L4_Orig_Retail.setText("");
                    L4_Reason_DropDown.setSelectedItem("");
                    L4_Desc_Damage.setText("");
                    L4_New_Sku.setText("");
                    L4_New_Desc.setText("");
                    L4_New_Attr.setText("");
                    L4_New_Size.setText("");
                    L4_Qty_In.setText("");
                    L4_Done_ChkBox.setSelected(false);
                    L4_Timestamp.setText("");
                    
                    L5_Qty_Out.setText("");
                    L5_First_Desc.setText("");
                    L5_First_Attr.setText("");
                    L5_First_Size.setText("");
                    L5_Orig_Retail.setText("");
                    L5_Reason_DropDown.setSelectedItem("");
                    L5_Desc_Damage.setText("");
                    L5_New_Sku.setText("");
                    L5_New_Desc.setText("");
                    L5_New_Attr.setText("");
                    L5_New_Size.setText("");
                    L5_Qty_In.setText("");
                    L5_Done_ChkBox.setSelected(false);
                    L5_Timestamp.setText("");
                    
                    L6_Qty_Out.setText("");
                    L6_First_Desc.setText("");
                    L6_First_Attr.setText("");
                    L6_First_Size.setText("");
                    L6_Orig_Retail.setText("");
                    L6_Reason_DropDown.setSelectedItem("");
                    L6_Desc_Damage.setText("");
                    L6_New_Sku.setText("");
                    L6_New_Desc.setText("");
                    L6_New_Attr.setText("");
                    L6_New_Size.setText("");
                    L6_Qty_In.setText("");
                    L6_Done_ChkBox.setSelected(false);
                    L6_Timestamp.setText("");
                    
                    L7_Qty_Out.setText("");
                    L7_First_Desc.setText("");
                    L7_First_Attr.setText("");
                    L7_First_Size.setText("");
                    L7_Orig_Retail.setText("");
                    L7_Reason_DropDown.setSelectedItem("");
                    L7_Desc_Damage.setText("");
                    L7_New_Sku.setText("");
                    L7_New_Desc.setText("");
                    L7_New_Attr.setText("");
                    L7_New_Size.setText("");
                    L7_Qty_In.setText("");
                    L7_Done_ChkBox.setSelected(false);
                    L7_Timestamp.setText("");
                    
                    L8_Qty_Out.setText("");
                    L8_First_Desc.setText("");
                    L8_First_Attr.setText("");
                    L8_First_Size.setText("");
                    L8_Orig_Retail.setText("");
                    L8_Reason_DropDown.setSelectedItem("");
                    L8_Desc_Damage.setText("");
                    L8_New_Sku.setText("");
                    L8_New_Desc.setText("");
                    L8_New_Attr.setText("");
                    L8_New_Size.setText("");
                    L8_Qty_In.setText("");
                    L8_Done_ChkBox.setSelected(false);
                    L8_Timestamp.setText("");
                    
                    L9_Qty_Out.setText("");
                    L9_First_Desc.setText("");
                    L9_First_Attr.setText("");
                    L9_First_Size.setText("");
                    L9_Orig_Retail.setText("");
                    L9_Reason_DropDown.setSelectedItem("");
                    L9_Desc_Damage.setText("");
                    L9_New_Sku.setText("");
                    L9_New_Desc.setText("");
                    L9_New_Attr.setText("");
                    L9_New_Size.setText("");
                    L9_Qty_In.setText("");
                    L9_Done_ChkBox.setSelected(false);
                    L9_Timestamp.setText("");
                    
                    L10_Qty_Out.setText("");
                    L10_First_Desc.setText("");
                    L10_First_Attr.setText("");
                    L10_First_Size.setText("");
                    L10_Orig_Retail.setText("");
                    L10_Reason_DropDown.setSelectedItem("");
                    L10_Desc_Damage.setText("");
                    L10_New_Sku.setText("");
                    L10_New_Desc.setText("");
                    L10_New_Attr.setText("");
                    L10_New_Size.setText("");
                    L10_Qty_In.setText("");
                    L10_Done_ChkBox.setSelected(false);
                    L10_Timestamp.setText("");
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
            java.util.logging.Logger.getLogger(IAS2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new IAS2().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(IAS2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton ApproveBtn;
    private javax.swing.JTextField Attr2;
    private javax.swing.JButton CLine_Button;
    private javax.swing.JTextField CLine_textfield;
    private javax.swing.JButton Close_Btn;
    public static javax.swing.JLabel Date_Label;
    private javax.swing.JButton Delete_Form_Btn;
    private javax.swing.JLabel Form_Name_Label;
    private javax.swing.JLabel Form_Name_Tag;
    private javax.swing.JButton IC_Print_Btn;
    public static javax.swing.JLabel L1;
    public static javax.swing.JLabel L10;
    public static javax.swing.JTextField L10_Desc_Damage;
    public static javax.swing.JCheckBox L10_Done_ChkBox;
    public static javax.swing.JTextField L10_First_Attr;
    public static javax.swing.JTextField L10_First_Desc;
    public static javax.swing.JTextField L10_First_Size;
    public static javax.swing.JTextField L10_First_Sku;
    public static javax.swing.JTextField L10_New_Attr;
    public static javax.swing.JTextField L10_New_Desc;
    public static javax.swing.JTextField L10_New_Size;
    public static javax.swing.JTextField L10_New_Sku;
    public static javax.swing.JTextField L10_Orig_Retail;
    public static javax.swing.JTextField L10_Qty_In;
    public static javax.swing.JTextField L10_Qty_Out;
    public static javax.swing.JComboBox L10_Reason_DropDown;
    public static javax.swing.JLabel L10_Timestamp;
    protected static javax.swing.JTextField L1_Desc_Damage;
    protected static javax.swing.JCheckBox L1_Done_ChkBox;
    protected static javax.swing.JTextField L1_First_Attr;
    protected static javax.swing.JTextField L1_First_Desc;
    protected static javax.swing.JTextField L1_First_Size;
    protected static javax.swing.JTextField L1_First_Sku;
    protected static javax.swing.JTextField L1_New_Attr;
    protected static javax.swing.JTextField L1_New_Desc;
    protected static javax.swing.JTextField L1_New_Size;
    protected static javax.swing.JTextField L1_New_Sku;
    protected static javax.swing.JTextField L1_Orig_Retail;
    protected static javax.swing.JTextField L1_Qty_In;
    protected static javax.swing.JTextField L1_Qty_Out;
    protected static javax.swing.JComboBox L1_Reason_DropDown;
    protected static javax.swing.JLabel L1_Timestamp;
    public static javax.swing.JLabel L2;
    public static javax.swing.JTextField L2_Desc_Damage;
    public static javax.swing.JCheckBox L2_Done_ChkBox;
    public static javax.swing.JTextField L2_First_Attr;
    public static javax.swing.JTextField L2_First_Desc;
    public static javax.swing.JTextField L2_First_Size;
    public static javax.swing.JTextField L2_First_Sku;
    public static javax.swing.JTextField L2_New_Attr;
    public static javax.swing.JTextField L2_New_Desc;
    public static javax.swing.JTextField L2_New_Size;
    public static javax.swing.JTextField L2_New_Sku;
    public static javax.swing.JTextField L2_Orig_Retail;
    public static javax.swing.JTextField L2_Qty_In;
    public static javax.swing.JTextField L2_Qty_Out;
    public static javax.swing.JComboBox L2_Reason_DropDown;
    public static javax.swing.JLabel L2_Timestamp;
    public static javax.swing.JLabel L3;
    public static javax.swing.JTextField L3_Desc_Damage;
    public static javax.swing.JCheckBox L3_Done_ChkBox;
    public static javax.swing.JTextField L3_First_Attr;
    public static javax.swing.JTextField L3_First_Desc;
    public static javax.swing.JTextField L3_First_Size;
    public static javax.swing.JTextField L3_First_Sku;
    public static javax.swing.JTextField L3_New_Attr;
    public static javax.swing.JTextField L3_New_Desc;
    public static javax.swing.JTextField L3_New_Size;
    public static javax.swing.JTextField L3_New_Sku;
    public static javax.swing.JTextField L3_Orig_Retail;
    public static javax.swing.JTextField L3_Qty_In;
    public static javax.swing.JTextField L3_Qty_Out;
    public static javax.swing.JComboBox L3_Reason_DropDown;
    public static javax.swing.JLabel L3_Timestamp;
    public static javax.swing.JLabel L4;
    public static javax.swing.JTextField L4_Desc_Damage;
    public static javax.swing.JCheckBox L4_Done_ChkBox;
    public static javax.swing.JTextField L4_First_Attr;
    public static javax.swing.JTextField L4_First_Desc;
    public static javax.swing.JTextField L4_First_Size;
    public static javax.swing.JTextField L4_First_Sku;
    public static javax.swing.JTextField L4_New_Attr;
    public static javax.swing.JTextField L4_New_Desc;
    public static javax.swing.JTextField L4_New_Size;
    public static javax.swing.JTextField L4_New_Sku;
    public static javax.swing.JTextField L4_Orig_Retail;
    public static javax.swing.JTextField L4_Qty_In;
    public static javax.swing.JTextField L4_Qty_Out;
    public static javax.swing.JComboBox L4_Reason_DropDown;
    public static javax.swing.JLabel L4_Timestamp;
    public static javax.swing.JLabel L5;
    public static javax.swing.JTextField L5_Desc_Damage;
    public static javax.swing.JCheckBox L5_Done_ChkBox;
    public static javax.swing.JTextField L5_First_Attr;
    public static javax.swing.JTextField L5_First_Desc;
    public static javax.swing.JTextField L5_First_Size;
    public static javax.swing.JTextField L5_First_Sku;
    public static javax.swing.JTextField L5_New_Attr;
    public static javax.swing.JTextField L5_New_Desc;
    public static javax.swing.JTextField L5_New_Size;
    public static javax.swing.JTextField L5_New_Sku;
    public static javax.swing.JTextField L5_Orig_Retail;
    public static javax.swing.JTextField L5_Qty_In;
    public static javax.swing.JTextField L5_Qty_Out;
    public static javax.swing.JComboBox L5_Reason_DropDown;
    public static javax.swing.JLabel L5_Timestamp;
    public static javax.swing.JLabel L6;
    public static javax.swing.JTextField L6_Desc_Damage;
    public static javax.swing.JCheckBox L6_Done_ChkBox;
    public static javax.swing.JTextField L6_First_Attr;
    public static javax.swing.JTextField L6_First_Desc;
    public static javax.swing.JTextField L6_First_Size;
    public static javax.swing.JTextField L6_First_Sku;
    public static javax.swing.JTextField L6_New_Attr;
    public static javax.swing.JTextField L6_New_Desc;
    public static javax.swing.JTextField L6_New_Size;
    public static javax.swing.JTextField L6_New_Sku;
    public static javax.swing.JTextField L6_Orig_Retail;
    public static javax.swing.JTextField L6_Qty_In;
    public static javax.swing.JTextField L6_Qty_Out;
    public static javax.swing.JComboBox L6_Reason_DropDown;
    public static javax.swing.JLabel L6_Timestamp;
    public static javax.swing.JLabel L7;
    public static javax.swing.JTextField L7_Desc_Damage;
    public static javax.swing.JCheckBox L7_Done_ChkBox;
    public static javax.swing.JTextField L7_First_Attr;
    public static javax.swing.JTextField L7_First_Desc;
    public static javax.swing.JTextField L7_First_Size;
    public static javax.swing.JTextField L7_First_Sku;
    public static javax.swing.JTextField L7_New_Attr;
    public static javax.swing.JTextField L7_New_Desc;
    public static javax.swing.JTextField L7_New_Size;
    public static javax.swing.JTextField L7_New_Sku;
    public static javax.swing.JTextField L7_Orig_Retail;
    public static javax.swing.JTextField L7_Qty_In;
    public static javax.swing.JTextField L7_Qty_Out;
    public static javax.swing.JComboBox L7_Reason_DropDown;
    public static javax.swing.JLabel L7_Timestamp;
    public static javax.swing.JLabel L8;
    public static javax.swing.JTextField L8_Desc_Damage;
    public static javax.swing.JCheckBox L8_Done_ChkBox;
    public static javax.swing.JTextField L8_First_Attr;
    public static javax.swing.JTextField L8_First_Desc;
    public static javax.swing.JTextField L8_First_Size;
    public static javax.swing.JTextField L8_First_Sku;
    public static javax.swing.JTextField L8_New_Attr;
    public static javax.swing.JTextField L8_New_Desc;
    public static javax.swing.JTextField L8_New_Size;
    public static javax.swing.JTextField L8_New_Sku;
    public static javax.swing.JTextField L8_Orig_Retail;
    public static javax.swing.JTextField L8_Qty_In;
    public static javax.swing.JTextField L8_Qty_Out;
    public static javax.swing.JComboBox L8_Reason_DropDown;
    public static javax.swing.JLabel L8_Timestamp;
    public static javax.swing.JLabel L9;
    public static javax.swing.JTextField L9_Desc_Damage;
    public static javax.swing.JCheckBox L9_Done_ChkBox;
    public static javax.swing.JTextField L9_First_Attr;
    public static javax.swing.JTextField L9_First_Desc;
    public static javax.swing.JTextField L9_First_Size;
    public static javax.swing.JTextField L9_First_Sku;
    public static javax.swing.JTextField L9_New_Attr;
    public static javax.swing.JTextField L9_New_Desc;
    public static javax.swing.JTextField L9_New_Size;
    public static javax.swing.JTextField L9_New_Sku;
    public static javax.swing.JTextField L9_Orig_Retail;
    public static javax.swing.JTextField L9_Qty_In;
    public static javax.swing.JTextField L9_Qty_Out;
    public static javax.swing.JComboBox L9_Reason_DropDown;
    public static javax.swing.JLabel L9_Timestamp;
    public static javax.swing.JButton RdyforExportBtn;
    private javax.swing.JLabel Store_Letter_Label;
    private javax.swing.JLabel Store_Location_Label;
    public static javax.swing.JLabel Store_Logged_In_Label;
    private javax.swing.JLabel Store_Num_Label;
    private javax.swing.JLabel Test_Label;
    private javax.swing.JLabel VersionLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField118;
    private javax.swing.JTextField jTextField119;
    private javax.swing.JTextField jTextField121;
    private javax.swing.JTextField jTextField122;
    private javax.swing.JTextField jTextField123;
    private javax.swing.JTextField jTextField124;
    private javax.swing.JTextField jTextField125;
    private javax.swing.JTextField jTextField126;
    private javax.swing.JTextField jTextField127;
    private javax.swing.JTextField jTextField128;
    private javax.swing.JTextField jTextField129;
    private javax.swing.JTextField jTextField131;
    private javax.swing.JTextField jTextField191;
    public static javax.swing.JButton subBtn;
    public static javax.swing.JLabel tdteLabel;
    // End of variables declaration//GEN-END:variables

    /*
    public static void RProExp() throws ClassNotFoundException, SQLException, IOException {
        try (Statement s1 = DBConnect.connection.createStatement()) {
            String selTable01 = "SELECT SKU As s, Qty As q, Description1 As d1, Attribute As atr, Size As sze, Orig_Retail As orgR, "
                    + "Reason As r, Desc_Damage As dmg, New_SKU As nwSk, "
                    + "Description2 As d2, Qty_In As qI FROM " + frmNm + " WHERE Sku IS NOT NULL";
            s1.execute(selTable01);
            try (ResultSet rs01 = s1.getResultSet()) {
                fWriter = new FileWriter("exports/" + frmNm.replace(":", "_") + ".csv", false);
                writer = new BufferedWriter(fWriter);
                String header = "store_code,item_number,quantity,description,attribute,size,original_retail,adj_reason,damage,new_sku,description2,qty_in";
                writer.write(header);
                writer.newLine();
                while (rs01.next()) {
                    String qty = rs01.getString("q");
                    String sku = rs01.getString("s");
                    String desc1 = rs01.getString("d1");
                    String attr = rs01.getString("atr");
                    String size = rs01.getString("sze");
                    String orgR = rs01.getString("orgR");
                    String reas = rs01.getString("r");
                    String descdmg = rs01.getString("dmg");
                    String nwsku = rs01.getString("nwSk");
                    String desc2 = rs01.getString("d2");
                    String qtyI = rs01.getString("qI");
                    //System.out.println(frmNm.split("_")[1] + qty + "," + sku + "," + desc1 + "," + reas + "," + descdmg + "," + orgR + "," + nwsku + "," + desc2 + "," + qtyI);
                    String line = frmNm.split("_")[1] + "," + sku + "," + qty + "," + desc1 + "," + attr + "," + size + "," + orgR + "," + reas + "," + descdmg + "," + nwsku + "," + desc2 + "," + qtyI;
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
        writer.close();
        GtForm();
        IASdao.drTable();
    }

    public static void UsrExport() throws SQLException, IOException {
        try (Statement s1 = DBConnect.connection.createStatement()) {
            String selTable01 = "SELECT SKU As s, Qty As q, Description1 As d1, Attribute As atr, Size As sze, "
                    + "Orig_Retail As orgR, Reason As r, Desc_Damage As dmg, New_SKU As nwSk, "
                    + "Description2 As d2, Qty_In As qI FROM " + frmNm + " WHERE Sku IS NOT NULL";
            s1.execute(selTable01);
            try (ResultSet rs01 = s1.getResultSet()) {
                fWriter = new FileWriter("Stores/" + frmNm.split("_")[1] + "/" + frmNm.replace(":", "_") + ".csv", false);
                writer = new BufferedWriter(fWriter);
                String header = "store_code,item_number,quantity,description,attribute,size,original_retail,adj_reason,damage,new_sku,description2,qty_in";
                writer.write(header);
                writer.newLine();
                while (rs01.next()) {
                    String sku = rs01.getString("s");
                    String qty = rs01.getString("q");
                    String desc1 = rs01.getString("d1");
                    String attr = rs01.getString("atr");
                    String size = rs01.getString("sze");
                    String orgR = rs01.getString("orgR");
                    String reas = rs01.getString("r");
                    String descdmg = rs01.getString("dmg");
                    String nwsku = rs01.getString("nwSk");
                    String desc2 = rs01.getString("d2");
                    String attr2 = rs01.getString("atr2");
                    String size2 = rs01.getString("sze2");
                    String qtyI = rs01.getString("qI");
                    //System.out.println(frmNm.split("_")[1] + qty + "," + sku + "," + desc1 + "," + reas + "," + descdmg + "," + orgR + "," + nwsku + "," + desc2 + "," + qtyI);
                    String line = frmNm.split("_")[1] + ","+sku+","+qty+","+desc1+","+attr+","+size+","+orgR+","+reas+","+descdmg+","+nwsku+","+desc2+","+attr2+","+size2+","+qtyI;
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
        writer.close();
    } */
     
    //  gets list of exported forms to display in the program
    public static void GtForm() throws IOException {
        Desktop.getDesktop().open(new File("exports/" + frmNm.replace(":", "_") + ".csv"));
    }
    // gets reasons from database
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

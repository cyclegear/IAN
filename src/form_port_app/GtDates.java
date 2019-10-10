/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form_port_app;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author SSwisher & Wstevens
 */

// this class gets the current date for timestamp for form creation
public class GtDates {

    public static String tdate;
    public static String ldate;
    public static String fdate;

    public static void main(String[] args) {
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //"yyyy/MMM/dd HH:mm:ss"
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd"); //"yyyy/MMM/dd HH:mm:ss"
        SimpleDateFormat formatter3 = new SimpleDateFormat("MM_dd_yy_HH_mm_ss"); //"yyyy/MMM/dd HH:mm:ss"
        String dateLine = formatter.format(currentDate.getTime());
        String dateNow = formatter2.format(currentDate.getTime());        
        String dateForm = formatter3.format(currentDate.getTime());
        //System.out.println("Now the date is :=>  " + dateNow);
        ldate = dateLine;
        tdate = dateNow;
        fdate = dateForm;
        
    }
}

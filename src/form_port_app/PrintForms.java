/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form_port_app;


import java.awt.Component;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

/**
 *
 * @author WStevens
 */
public class PrintForms extends javax.swing.JFrame {
    
    private Component frame;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    protected static String formType;
    protected static String formHeader = null;
    
    
    /**
     * Creates new form PrintForms
     * @throws java.sql.SQLException
     */
    public PrintForms() throws SQLException {
        initComponents();
        conn = DBConnect.connection;
        PrintFormTable.clearSelection();
        switch (formType) {
            case "IAS":
                getIAS();
                break;
            case "NEBAS":
                getNEBAS();
                break;
            case "EBAS":
                getEBAS();
                break;
        }        
    }
    
    private void getIAS() throws SQLException {
      //  PrintFormTable.clearSelection();
        Form_Name_Label.setText("Form Name: " + IASdao.StFrmNm);
        formHeader = IASdao.StFrmNm;
        
        String getIASdata = "SELECT Sku, Qty, Description1, Reason, Desc_Damage, New_Sku, Description2, Qty_In FROM "+ IASdao.StFrmNm +" WHERE Sku IS NOT NULL";
        pst = conn.prepareStatement(getIASdata);
        rs = pst.executeQuery();
        PrintFormTable.setModel(DbUtils.resultSetToTableModel(rs));
        
        PrintFormTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        PrintFormTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        PrintFormTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        PrintFormTable.getColumnModel().getColumn(2).setPreferredWidth(160);
        PrintFormTable.getColumnModel().getColumn(3).setPreferredWidth(80);
        PrintFormTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        PrintFormTable.getColumnModel().getColumn(5).setPreferredWidth(60);
        PrintFormTable.getColumnModel().getColumn(6).setPreferredWidth(160);
        PrintFormTable.getColumnModel().getColumn(7).setPreferredWidth(30);
    }
    
    private void getNEBAS() throws SQLException {
      //  PrintFormTable.clearSelection();
        Form_Name_Label.setText("Form Name: " + NEBASdao.StFrmNm);
        formHeader = NEBASdao.StFrmNm;
        String getNEBASdata = "SELECT Sku, Qty, Description1, Orig_Sku, Description2, New_Used, Reason, Desc_Damage FROM "+ NEBASdao.StFrmNm +" WHERE Sku IS NOT NULL";
        pst = conn.prepareStatement(getNEBASdata);
        rs = pst.executeQuery();
        PrintFormTable.setModel(DbUtils.resultSetToTableModel(rs));
        
        PrintFormTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        PrintFormTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        PrintFormTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        PrintFormTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        PrintFormTable.getColumnModel().getColumn(3).setPreferredWidth(60);
        PrintFormTable.getColumnModel().getColumn(4).setPreferredWidth(170);
        PrintFormTable.getColumnModel().getColumn(5).setPreferredWidth(30);
        PrintFormTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        PrintFormTable.getColumnModel().getColumn(7).setPreferredWidth(120);
    }
    
    private void getEBAS() throws SQLException {
     //  PrintFormTable.clearSelection();
        Form_Name_Label.setText("Form Name: " + EBASdao.StFrmNm);
        formHeader = EBASdao.StFrmNm;
        String getEBASdata = "SELECT Sku, Qty, Description1, Orig_Sku, Description2, New_Used, Reason, Desc_Damage FROM " + EBASdao.StFrmNm + " WHERE Sku IS NOT NULL";
        pst = conn.prepareStatement(getEBASdata);
        rs = pst.executeQuery();
        PrintFormTable.setModel(DbUtils.resultSetToTableModel(rs));
        
        PrintFormTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        PrintFormTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        PrintFormTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        PrintFormTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        PrintFormTable.getColumnModel().getColumn(3).setPreferredWidth(60);
        PrintFormTable.getColumnModel().getColumn(4).setPreferredWidth(170);
        PrintFormTable.getColumnModel().getColumn(5).setPreferredWidth(30);
        PrintFormTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        PrintFormTable.getColumnModel().getColumn(7).setPreferredWidth(120);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        PrintFormTable = new javax.swing.JTable();
        CloseButton = new javax.swing.JButton();
        PrintButton = new javax.swing.JButton();
        Form_Name_Label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        PrintFormTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(PrintFormTable);

        CloseButton.setBackground(new java.awt.Color(255, 0, 0));
        CloseButton.setText("Close");
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });

        PrintButton.setBackground(new java.awt.Color(255, 255, 0));
        PrintButton.setText("Print");
        PrintButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintButtonActionPerformed(evt);
            }
        });

        Form_Name_Label.setText("Form Name: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(253, 253, 253)
                .addComponent(Form_Name_Label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(PrintButton)
                        .addGap(120, 120, 120)
                        .addComponent(CloseButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Form_Name_Label)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 66, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PrintButton)
                    .addComponent(CloseButton))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void PrintButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintButtonActionPerformed
    PrinterJob job = PrinterJob.getPrinterJob();
    PageFormat pf = job.defaultPage();
    Paper paper = pf.getPaper();
  //  double margin = 20.;
    paper.setImageableArea(10, 10, 570, 820);
    
    System.out.println("Printing Form");
    JOptionPane.showMessageDialog(frame, "Form is Printing\nPlease Wait");
    /*
    paper.setImageableArea(margin, 
        paper.getImageableY(),
        paper.getWidth() - 2* margin, paper.getImageableHeight());
    */ 
    pf.setPaper(paper);
    
        MessageFormat header = new MessageFormat("Form Name: "+ formHeader);
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        pf.setOrientation(PageFormat.LANDSCAPE);
        
    job.setPrintable(PrintFormTable.getPrintable(JTable.PrintMode.NORMAL, header, footer),
            
        job.validatePage(pf));
        try {
            job.print();
            
            /*
            MessageFormat header = new MessageFormat("Form Name: "+ formHeader);
            MessageFormat footer = new MessageFormat("Page{0,number,integer}");
            
            try {
            PrintFormTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            
            } catch (java.awt.print.PrinterException e) {
            System.err.format("Failed to Print", e.getMessage());
            }  */
        } catch (PrinterException ex) {
            Logger.getLogger(PrintForms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_PrintButtonActionPerformed

    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_CloseButtonActionPerformed

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
            java.util.logging.Logger.getLogger(PrintForms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new PrintForms().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(PrintForms.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButton;
    private javax.swing.JLabel Form_Name_Label;
    private javax.swing.JButton PrintButton;
    private javax.swing.JTable PrintFormTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

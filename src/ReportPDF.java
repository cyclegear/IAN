/*

import java.io.IOException;
import java.util.HashMap;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

// Java Program To Call Jasper Report

public class ReportPDF {
  public static void main(String[] args) throws JRException, IOException {
   
    JasperReport jasperReport = JasperCompileManager.compileReport("UnprocessedTest.jrxml");
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,new HashMap(), new JREmptyDataSource());
    JasperExportManager.exportReportToPdfFile(jasperPrint, "Unprocessed.pdf");
  }
} */
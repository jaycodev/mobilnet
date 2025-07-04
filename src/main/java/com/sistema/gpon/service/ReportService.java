package com.sistema.gpon.service;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class ReportService {

    @Autowired
    private DataSource dataSource;

    public byte[] generarReportParametrs(String reportName, int idRegistro) throws JRException, SQLException {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/reports/" + reportName + ".jrxml");

            if (inputStream == null) {
                System.err.println("⚠️ No se encontró el archivo JRXML en la ruta: /reports/" + reportName + ".jrxml");
                return null;
            }

            InputStream logoStream = this.getClass().getResourceAsStream("/static/assets/img/contrato.jpg");

            if (logoStream == null) {
                System.err.println("⚠️ No se encontró la imagen en la ruta: /static/assets/img/contrato.jpg");
                return null;
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", idRegistro);
            parametros.put("LOGO", logoStream);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

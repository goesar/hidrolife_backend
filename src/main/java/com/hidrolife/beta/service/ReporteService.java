package com.hidrolife.beta.service;

import com.hidrolife.beta.model.Actividad;
import com.hidrolife.beta.model.Cultivo;
import com.hidrolife.beta.model.LecturaSensor;
import com.hidrolife.beta.model.Usuario;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {

    private Cell header(String texto) {
        return new Cell()
                .add(new Paragraph(texto).setBold().setFontColor(ColorConstants.WHITE))
                .setBackgroundColor(new DeviceRgb(0, 153, 168))
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(5);
    }

    private Cell cell(String texto) {
        return new Cell()
                .add(new Paragraph(texto))
                .setTextAlignment(TextAlignment.CENTER);
    }

    public byte[] generarPDF(String tabla, List<?> datos) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf);

        doc.setMargins(20, 20, 20, 20);

        // Formato de fecha estándar
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // --- HEADER (Logo pequeño + Título centrado) ---
        try {
            InputStream logoStream = getClass().getResourceAsStream("/static/img/hidrolife-logo.png");
            ImageData imageData = ImageDataFactory.create(logoStream.readAllBytes());
            Image logo = new Image(imageData);

            logo.scaleToFit(80, 80);        // LOGO REALMENTE PEQUEÑO
            logo.setMarginLeft(5);

            Paragraph titulo = new Paragraph("Reporte de " + tabla)
                    .setFontSize(22)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);

            // 👉 CLAVE: la tabla usa columnas proporcionales AL TAMAÑO DEL LOGO
            Table header = new Table(new float[]{20, 400, 50});
            header.useAllAvailableWidth();

            // COLUMNA 1 - LOGO
            header.addCell(new Cell()
                    .add(logo)
                    .setBorder(null)
                    .setVerticalAlignment(com.itextpdf.layout.properties.VerticalAlignment.MIDDLE)
                    .setTextAlignment(TextAlignment.LEFT)
            );

            // COLUMNA 2 - TÍTULO EXACTAMENTE CENTRADO
            header.addCell(new Cell()
                    .add(titulo)
                    .setBorder(null)
                    .setVerticalAlignment(com.itextpdf.layout.properties.VerticalAlignment.MIDDLE)
                    .setTextAlignment(TextAlignment.CENTER)
            );

            // COLUMNA 3 - VACÍA para balancear
            header.addCell(new Cell()
                    .setBorder(null)
            );

            doc.add(header);
            doc.add(new Paragraph("").setMarginBottom(5));  // Tabla queda más cerca
        } catch (IOException e) {
            System.out.println("No se pudo cargar el logo: " + e.getMessage());
        }

        // ---- TABLAS ----
        switch (tabla) {

            // ---------------- ACTIVIDADES ------------------
            case "Actividades" -> {
                Table table = new Table(6);

                table.addHeaderCell(header("ID"));
                table.addHeaderCell(header("Actividades"));
                table.addHeaderCell(header("Fecha"));
                table.addHeaderCell(header("Usuario"));
                table.addHeaderCell(header("Descripción"));
                table.addHeaderCell(header("Cultivo"));

                for (Object o : datos) {
                    Actividad a = (Actividad) o;

                    table.addCell(cell(String.valueOf(a.getIdActividad())));
                    table.addCell(cell(a.getActividades()));
                    table.addCell(cell(a.getFecha().format(fmt)));
                    table.addCell(cell(a.getUsuario()));
                    table.addCell(cell(a.getDescripcion()));
                    table.addCell(cell(a.getCultivo().getNombre()));
                }

                doc.add(table);
                break;
            }

            // ---------------- CULTIVOS ------------------
            case "Cultivos" -> {
                Table table = new Table(new float[]{40, 250, 60, 60, 70, 150});
                table.setWidth(UnitValue.createPercentValue(100));

                table.addHeaderCell(header("ID"));
                table.addHeaderCell(header("Nombre"));
                table.addHeaderCell(header("Plantas"));
                table.addHeaderCell(header("pH ideal"));
                table.addHeaderCell(header("TDS ideal"));
                table.addHeaderCell(header("Fecha"));

                for (Object o : datos) {
                    Cultivo c = (Cultivo) o;

                    table.addCell(cell(String.valueOf(c.getIdCultivo())));
                    table.addCell(cell(c.getNombre()));
                    table.addCell(cell(String.valueOf(c.getNumeroPlantas())));
                    table.addCell(cell(String.valueOf(c.getPhIdeal())));
                    table.addCell(cell(String.valueOf(c.getTdsIdeal())));
                    table.addCell(cell(c.getFecha().format(fmt)));
                }

                doc.add(table);
                break;
            }

            // ---------------- USUARIOS ------------------
            case "Usuarios" -> {
                Table table = new Table(4);
                table.setWidth(UnitValue.createPercentValue(100));
                table.addHeaderCell(header("ID"));
                table.addHeaderCell(header("Nombre"));
                table.addHeaderCell(header("Correo"));
                table.addHeaderCell(header("Telefono"));

                for (Object o : datos) {
                    Usuario u = (Usuario) o;

                    table.addCell(cell(String.valueOf(u.getIdUsuario())));
                    table.addCell(cell(u.getNombre()));
                    table.addCell(cell(u.getEmail()));
                    table.addCell(cell(u.getTelefono()));
                }

                doc.add(table);
                break;
            }

            // ---------------- LECTURAS ------------------
            case "Lecturas" -> {

                Table table = new Table(new float[]{40, 250, 60, 60, 70, 150});
                table.setWidth(UnitValue.createPercentValue(100));
                table.addHeaderCell(header("ID"));
                table.addHeaderCell(header("Humedad"));
                table.addHeaderCell(header("Temp"));
                table.addHeaderCell(header("pH"));
                table.addHeaderCell(header("TDS"));
                table.addHeaderCell(header("Fecha"));

                for (Object o : datos) {
                    LecturaSensor l = (LecturaSensor) o;

                    table.addCell(cell(String.valueOf(l.getIdLectura())));
                    table.addCell(cell(String.valueOf(l.getHumedad())));
                    table.addCell(cell(String.valueOf(l.getTemperatura())));
                    table.addCell(cell(String.valueOf(l.getPh())));
                    table.addCell(cell(String.valueOf(l.getTds())));
                    table.addCell(cell(l.getFechaYHora().format(fmt)));
                }

                doc.add(table);
                break;
            }
        }

        doc.close();
        return baos.toByteArray();
    }
}

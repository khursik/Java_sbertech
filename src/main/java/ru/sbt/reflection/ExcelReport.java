package ru.sbt.reflection;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

//возможно было бы лучше следовать принципу SRP и в Report хранить только данные
//а метод writeTo вынести в интрефейс ReportWriter
public class ExcelReport implements Report {
    private final Object[][] data;
    private final String sheetName;

    public ExcelReport(Object[][] data, String sheetName) {
        this.data = data;
        this.sheetName = sheetName;
    }

    @Override
    public void writeTo(OutputStream os) {
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
        HSSFSheet sheet = workbook.createSheet(sheetName);
        for (int i = 0; i < data.length; i++) {
            Object[] dataRow = data[i];
            Row row = sheet.createRow(i);
            for (int j = 0; j < dataRow.length; j++) {
                addToRow(dataRow[j], row.createCell(j));
            }
        }
            workbook.write(os);
            System.out.println("Excel file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addToRow(Object o, Cell cell) {
        if (o instanceof Double) {
            cell.setCellValue((Double) o);
        }
        else if (o instanceof Date) {
            cell.setCellValue((Date) o);
        }
        else if (o instanceof Boolean) {
            cell.setCellValue((Boolean) o);
        }
        else if (o instanceof LocalDateTime) {
            cell.setCellValue((LocalDateTime) o);
        }
        else if (o instanceof LocalDate) {
            cell.setCellValue((LocalDate) o);
        }
        else if (o instanceof Calendar) {
            cell.setCellValue((Calendar) o);
        }
        else if (o instanceof RichTextString) {
            cell.setCellValue((RichTextString) o);
        }
        else {
            cell.setCellValue(o.toString());
        }
    }


}

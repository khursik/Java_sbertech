package ru.sbt.reflection;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.io.OutputStream;
//возможно было бы лучше следовать принципу SRP и в Report хранить только данные
//а метод writeTo вынести в интрефейс ReportWriter
public class ExcelReport implements Report {
    private final String[][] data;
    private final String sheetName;

    public ExcelReport(String[][] data, String sheetName) {
        this.data = data;
        this.sheetName = sheetName;
    }

    @Override
    public void writeTo(OutputStream os) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);
        for (int i = 0; i < data.length; i++) {
            String[] dataRow = data[i];
            Row row = sheet.createRow(i);
            for (int j = 0; j < dataRow.length; j++) {
                row.createCell(j).setCellValue(dataRow[j]);
            }
        }
        try {
            workbook.write(os);
            workbook.close();
            System.out.println("Excel file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

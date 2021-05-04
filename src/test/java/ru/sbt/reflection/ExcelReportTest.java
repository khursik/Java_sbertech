package ru.sbt.reflection;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Iterator;
import java.util.List;

class ExcelReportTest {
    final static String EXPECTED_RESULT =
            "carId, brand, modelName, maxVelocity, power, ownerId;" +
                    "1, Mercedes, GLE, 370, 389, 1;" +
                    "1, Mercedes, GLE, 370, 389, 2;" +
                    "2, BMW, X7, 372, 389, 2;" +
                    "3, Volvo, S-class, 373, 389, 3;" +
                    "3, Volvo, S-class, 373, 389, 2;" +
                    "3, Volvo, S-class, 373, 389, 1;" +
                    "4, Lamborghini, Gallardo, 374, 389, 1;";

    @Test
    void testWriteTo() throws FileNotFoundException {
        List<Car> cars = List.of(
                new Car(1, "Mercedes", "GLE", 370, 389, 1),
                new Car(1, "Mercedes", "GLE", 370, 389, 2),
                new Car(2, "BMW", "X7", 372, 389, 2),
                new Car(3, "Volvo", "S-class", 373, 389, 3),
                new Car(3, "Volvo", "S-class", 373, 389, 2),
                new Car(3, "Volvo", "S-class", 373, 389, 1),
                new Car(4, "Lamborghini", "Gallardo", 374, 389, 1)
        );
        File file = new File("output.xls");
        ReportGenerator<Car> generator = new ExcelReportGenerator<>(Car.class);
        generator.generate(cars).writeTo(new FileOutputStream(file));
        FileInputStream file2 = new FileInputStream(new File("output.xls"));
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();
        HSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> ri = sheet.rowIterator();
        while (ri.hasNext()) {
            HSSFRow row = (HSSFRow) ri.next();
            Iterator<Cell> ci = row.cellIterator();
            while (ci.hasNext()) {
                HSSFCell cell = (HSSFCell) ci.next();
                if (!ci.hasNext()) {
                    stringBuilder.append(cell + ";");
                    break;
                }
                stringBuilder.append(cell + ", ");
            }
        }
        Assertions.assertEquals(EXPECTED_RESULT, stringBuilder.toString());
    }

    @Test
    void testWriteToWhenWorkbookIsNull() throws FileNotFoundException {
        List<Car> cars = List.of();
        File file = new File("output.xls");
        ReportGenerator<Car> generator = new ExcelReportGenerator<>(Car.class);
        generator.generate(cars).writeTo(new FileOutputStream(file));

        FileInputStream file2 = new FileInputStream(new File("output.xls"));
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(workbook);
    }
}
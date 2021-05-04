package ru.sbt.reflection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ReportGeneratorTest {

    @Test
    void testGenerateFromNullEntities() {
        ReportGenerator<Car> generator = new ExcelReportGenerator<>(Car.class);
        Assertions.assertNull(generator.generate(null));
    }

    @Test
    void testGenerateFromEmptyEntities() {
        ReportGenerator<Car> generator = new ExcelReportGenerator<>(Car.class);
        Assertions.assertNotNull(generator.generate(new ArrayList<>()));
    }
}
package ru.sbt.reflection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ReportGeneratorTest {

    @Test
    void testGenerate() throws IOException {
        List<Car> cars = List.of(
                new Car(1, "Mercedes", "GLE", 370, 389, 1),
                new Car(2, "BMW", "X7", 372, 389, 2),
                new Car(3, "Volvo", "S-class", 373, 389, 2),
                new Car(4, "Lamborghini", "Gallardo", 374, 389, 1)
        );
        File file = new File("output.xls");
        if (!file.exists()) {
            file.createNewFile();
        }
        ReportGenerator<Car> generator = new ReportGeneratorImpl<>();
        generator.generate(cars).writeTo(new FileOutputStream(file));
    }

    @Test
    void testGenerateFromNullEntities() {
        ReportGenerator<Car> generator = new ReportGeneratorImpl<>();
        Assertions.assertNull(generator.generate(null));
    }

    @Test
    void testGenerateFromEmptyEntities() {
        ReportGenerator<Car> generator = new ReportGeneratorImpl<>();
        Assertions.assertNotNull(generator.generate(new ArrayList<>()));
    }
}
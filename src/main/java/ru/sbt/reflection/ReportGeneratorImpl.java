package ru.sbt.reflection;

import java.lang.reflect.Field;
import java.util.List;

public class ReportGeneratorImpl<T> implements ReportGenerator<T> {

    @Override
    public Report generate(List<T> entities) {
        if (entities == null) {
            return null;
        }
        if (entities.isEmpty()) {
            return new ExcelReport(new String[0][0], "EmptySheet");
        }
        T first = entities.get(0);
        Class<?> type = first.getClass();
        final String className = type.getName();
        Field[] fields = type.getDeclaredFields();
        final String[][] data = new String[entities.size() + 1][fields.length];
        for (int i = 0; i < fields.length; i++) {
            data[0][i] = fields[i].getName();
            fields[i].setAccessible(true);
        }
        try {
            for (int i = 0; i < entities.size(); i++) {
                for (int j = 0; j < fields.length; j++) {
                    data[i + 1][j] = fields[j].get(entities.get(i)).toString();
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ExcelReport(data, className);
    }
}

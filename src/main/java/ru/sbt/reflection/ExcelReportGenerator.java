package ru.sbt.reflection;

import java.lang.reflect.Field;
import java.util.List;

public class ExcelReportGenerator<T> implements ReportGenerator<T> {

    private final Field[] fields;
    private final String className;
    private final String[] headers;

    public ExcelReportGenerator(Class<? extends T> elementType) {
        fields = elementType.getDeclaredFields();
        className = elementType.getName();
        headers = getHeaders();
    }

    @Override
    public Report generate(List<T> entities) {
        if (entities == null) {
            return null;
        }

        final Object[][] data = new Object[entities.size() + 1][fields.length];
        data[0] = headers;
        try {
            getValues(entities, data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ExcelReport(data, className);
    }

    private void getValues(List<T> entities, Object[][] data) throws IllegalAccessException {
        for (int i = 0; i < entities.size(); i++) {
            for (int j = 0; j < fields.length; j++) {
                data[i + 1][j] = fields[j].get(entities.get(i));
            }
        }
    }

    private String[] getHeaders() {
        String[] data = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            data[i] = fields[i].getName();
            fields[i].setAccessible(true);
        }
        return data;
    }
}

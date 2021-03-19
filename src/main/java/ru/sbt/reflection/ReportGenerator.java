package ru.sbt.reflection;

import java.util.List;

public interface ReportGenerator<T> {
    Report generate(List<T> entities);
}

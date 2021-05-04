package ru.sbt.reflection;

import java.util.List;

interface ReportGenerator<T> {
    Report generate(List<T> entities);
}

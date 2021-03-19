package ru.sbt.reflection;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

interface Report {
    default byte[] asBytes() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        writeTo(stream);
        return stream.toByteArray();
    }

    void writeTo(OutputStream os);
}

package ru.sbt;

public interface Dispatcher extends Runnable {
    void notifyAvailable(Taxi taxi);

    void run();
}

package ru.sbt;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        Dispatcher dispatcher = new DispatcherImpl(3, 10);
        Thread dispatcherThread = new Thread(dispatcher);
        dispatcherThread.start();
    }
}

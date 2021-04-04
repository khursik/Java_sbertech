package ru.sbt;

import java.util.Random;

public class TaxiImpl implements Taxi {
    private final Dispatcher dispatcher;
    private volatile Order order;
    private final Random random = new Random();

    public TaxiImpl(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        while (true) {
            if (order != null) {
                processOrder();
                dispatcher.notifyAvailable(this);
                Thread.yield();
            }
        }
    }

    @Override
    public void placeOrder(Order order) {
        this.order = order;
    }

    private void processOrder() {
        System.out.println("Processing order " + order + " by " + this);
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End processing order " + order + " by " + this);
        order = null;
    }
}

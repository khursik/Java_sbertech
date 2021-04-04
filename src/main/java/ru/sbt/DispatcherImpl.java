package ru.sbt;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class DispatcherImpl implements Dispatcher {
    final private Queue<Taxi> taxis;
    final int messageCount;
    final private Random random = new Random();

    public DispatcherImpl(int taxiCount, int messageCount) {
        this.messageCount = messageCount;
        this.taxis = new LinkedList<>();
        for (int i = 0; i < taxiCount; i++) {
            Taxi taxi = new TaxiImpl(this);
            Thread taxiThread = new Thread(taxi);
            taxiThread.start();
            taxis.add(taxi);
        }
    }

    @Override
    synchronized public void notifyAvailable(Taxi taxi) {
        taxis.add(taxi);
    }

    @Override
    public void run() {
        //генерация сообщений
        for (int i = 0; i < messageCount; i++) {
            try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Order order = new Order();
            System.out.println("Got order " + order + ", looking for taxi...");
            while (taxis.isEmpty()) {
                Thread.yield();
            }
            Taxi taxi = taxis.poll();
            System.out.println("Found taxi " + taxi);
            taxi.placeOrder(order);
        }
    }
}


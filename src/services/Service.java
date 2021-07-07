package services;

import java.util.ArrayList;

public class Service {

    public static final int CONSUMER_REQUEST_TYPE = 1;
    public static final int PRODUCER_REQUEST_TYPE = 2;

    public ArrayList<Integer> buffer;
    public int itemCount = 0;

    public Semaphore mutex;
    public Semaphore items;

    public Service() {
        this.buffer = new ArrayList<>();
        this.mutex = new Semaphore(1);
        this.items = new Semaphore(0);
    }

    public void run(int id, int requestType) {
        if (requestType == CONSUMER_REQUEST_TYPE) {
            Consumer consumer = new Consumer(this, id);
            consumer.start();
        } else if (requestType == PRODUCER_REQUEST_TYPE) {
            Producer producer = new Producer(this, id);
            producer.start();
        }
    }

}
package services;

public class Semaphore {

    public Semaphore() {
        count = 0;
    }

    public Semaphore(int i) {
        count = i;
    }

    public synchronized void down() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }

        count--;
    }

    public synchronized void up() {
        count++;
        notify();
    }

    protected int count;
}
package services;

public class Consumer extends Thread {

    public Service service;
    public int consumerId;

    public Consumer(Service service, int consumerId) {
        this.service = service;
        this.consumerId = consumerId;
    }

    public void run() {
        while (true) {
            this.service.items.down();
            this.service.mutex.down();

            int item = this.service.buffer.get(0);
            this.service.buffer.remove(0);
            this.service.itemCount--;

            System.out.println("consumidor #" + this.consumerId + ": consumindo item #" + item);

            this.service.mutex.up();

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
package services;

public class Producer extends Thread {

    public Service service;
    public int producerId;
    public int count;

    public Producer(Service service, int producerId) {
        this.service = service;
        this.producerId = producerId;
        this.count = 0;
    }

    public void run() {
        while (true) {
            this.service.mutex.down();
            this.service.items.up();
            this.count++;

            this.service.buffer.add(this.count);
            this.service.itemCount++;

            System.out.println("produtor #" + this.producerId + ": produzindo item #" + this.count);

            this.service.mutex.up();

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
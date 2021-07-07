package rmi;

import services.Service;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server extends java.rmi.server.UnicastRemoteObject
        implements RemoteMethodInterface {

    public int id = 0;

    public static Service service;

    public static int producerId = 1;
    public static int consumerId = 1;

    public Registry registry;

    public Server() throws RemoteException {
        service = new Service();

        System.out.println("this address = 127.0.0.1, port = 3232");
        registry = LocateRegistry.createRegistry(3232);
        registry.rebind("rmiServer", this);
    }

    public void produce() throws RemoteException {
        service.run(producerId++, Service.PRODUCER_REQUEST_TYPE);
    }

    public void consume() throws RemoteException {
        service.run(consumerId++, Service.CONSUMER_REQUEST_TYPE);
    }

    public static void main(String[] args) {
        try{
            Server server = new Server();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

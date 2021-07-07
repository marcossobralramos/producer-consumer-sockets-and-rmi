package rmi;

import services.Service;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        RemoteMethodInterface rmiServer;
        Registry registry;

        Scanner scanner = new Scanner(System.in);

        try {
            registry = LocateRegistry.getRegistry("127.0.0.1", 3232);

            System.out.println("Digite 1 para consumo e 2 para produção: ");

            int requestType = Integer.parseInt(scanner.nextLine());

            rmiServer = (RemoteMethodInterface) (registry.lookup("rmiServer"));

            if (requestType == Service.PRODUCER_REQUEST_TYPE) {
                rmiServer.produce();
            } else if (requestType == Service.CONSUMER_REQUEST_TYPE) {
                rmiServer.consume();
            }
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

    }
}

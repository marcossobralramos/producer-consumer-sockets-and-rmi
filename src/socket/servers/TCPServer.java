package socket.servers;

import services.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer implements Runnable {

    public int id = 0;

    public static Service service;
    public Socket client;

    public static int producerId = 1;
    public static int consumerId = 1;

    public TCPServer(Socket client) {

        this.client = client;
        service = new Service();
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Porta 12345 aberta!");

            System.out.println("Aguardando conexão do cliente...");

            while (true) {
                Socket client = serverSocket.accept();

                TCPServer tcpServer = new TCPServer(client);
                Thread thread = new Thread(tcpServer);
                thread.start();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        System.out.println("Novo cliente conectado...");

        try {
            Scanner scanner = new Scanner(this.client.getInputStream());

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();

                int typeRequest = Integer.parseInt(data);

                if (this.id == 0) {
                    this.id = typeRequest == Service.CONSUMER_REQUEST_TYPE ? consumerId++ : producerId++;
                }

                service.run(id, typeRequest);
            }

            scanner.close();

            this.client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

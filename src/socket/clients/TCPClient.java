package socket.clients;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public record TCPClient(Socket client) implements Runnable {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 12345);

        TCPClient tcpClient = new TCPClient(socket);
        Thread thread = new Thread(tcpClient);
        thread.start();
    }

    public void run() {
        try {
            System.out.println("O cliente conectou ao servidor");

            Scanner scanner = new Scanner(System.in);

            PrintStream output = new PrintStream(this.client.getOutputStream());

            System.out.println("Digite 1 para consumo e 2 para produção: ");

            while (scanner.hasNextLine()) {
                output.println(scanner.nextLine());
            }

            output.close();
            scanner.close();
            this.client.close();
            System.out.println("Fim do cliente!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

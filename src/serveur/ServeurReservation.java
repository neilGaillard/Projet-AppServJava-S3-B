package serveur;

import java.io.IOException;
import java.net.ServerSocket;

public class ServeurReservation implements Runnable{
    private static final int portReservation = 3000;

    private final ServerSocket listenSocket;

    public ServeurReservation() throws IOException {
        listenSocket = new ServerSocket(portReservation);
    }

    public static int getPortReservation() {
        return portReservation;
    }

    @Override
    public void run() {
        try {
            System.err.println("Lancement du serveur reservation au port " + ServeurReservation.portReservation);
            while (true)
                new Thread(new ServiceReservation(listenSocket.accept())).start();
        } catch (IOException e) {
            try {
                System.err.println("Arret du serveur reservation au port " + ServeurReservation.portReservation);
                this.listenSocket.close();
            } catch (IOException e1) {
                System.err.println("Arret du serveur reservation au port " + ServeurReservation.portReservation);
            }
            System.err.println("Arret du serveur reservation au port " + ServeurReservation.portReservation);
        }
    }


    protected void finalize() throws Throwable {
        try {
            this.listenSocket.close();
        } catch (IOException e1) {
        }
    }

}

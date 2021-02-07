package serveur;

import java.io.IOException;
import java.net.ServerSocket;

public class ServeurRetour implements Runnable{
    private static final int portRetour = 5000;

    private ServerSocket listenSocket;

    public ServeurRetour() throws IOException{
        listenSocket = new ServerSocket(portRetour);
    }

    public static int getPortRetour() {
        return portRetour;
    }

    @Override
    public void run() {
        try {
            System.err.println("Lancement du serveur au port " + ServeurRetour.portRetour);
            while (true)
                new Thread(new ServiceRetour(listenSocket.accept())).start();
        } catch (IOException e) {
            try {
                this.listenSocket.close();
            } catch (IOException e1) {

            }
            System.err.println("Arret du serveur au port " + ServeurRetour.portRetour);
        }
    }

    protected void finalize() throws Throwable {
        try {
            this.listenSocket.close();
        } catch (IOException e1) {
        }
    }
}
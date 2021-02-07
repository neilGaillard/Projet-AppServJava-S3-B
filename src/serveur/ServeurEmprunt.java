package serveur;

import java.io.IOException;
import java.net.ServerSocket;

public class ServeurEmprunt implements Runnable{
    private static final int portEmprunt = 4000;

    private ServerSocket listenSocket;

    public ServeurEmprunt() throws IOException {
        listenSocket = new ServerSocket(portEmprunt);
    }

    public static int getPortEmprunt() {
        return portEmprunt;
    }

    @Override
    public void run() {
        try {
            System.err.println("Lancement du serveur au port " + ServeurEmprunt.portEmprunt);
            while (true)
                new Thread(new ServiceEmprunt(listenSocket.accept())).start();
        } catch (IOException e) {
            try {
                this.listenSocket.close();
            } catch (IOException e1) {

            }
            System.err.println("Arret du serveur au port " + ServeurEmprunt.portEmprunt);
        }
    }

    protected void finalize() throws Throwable {
        try {
            this.listenSocket.close();
        } catch (IOException e1) {
        }
    }
}

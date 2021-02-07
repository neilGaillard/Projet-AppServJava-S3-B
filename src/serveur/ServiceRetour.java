package serveur;

import document.Document;
import document.ReservationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServiceRetour extends Service implements Runnable{

    public ServiceRetour(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            out.println("Entrez le numéro du document à retourner.");
            int noDocument = Integer.parseInt(in.readLine());

            Document d = getDocuments(noDocument);

            if (d != null)
                synchronized (d) {
                    out.println("Vous avez retourné le DVD " + (d.getNumero() + 1) + ".");
                    d.retour();
                }
            else
                out.println("Aucun document ne porte ce numéro.");

        } catch (IOException e) {
            System.err.println("Erreur dans le service Retour.");
        }
    }
}

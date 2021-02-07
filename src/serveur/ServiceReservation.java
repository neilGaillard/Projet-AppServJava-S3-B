package serveur;

import appli.Abonne;
import document.Document;
import document.ReservationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServiceReservation extends Service implements Runnable{

    public ServiceReservation(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            out.println("Entrez votre numéro");
            int nAbonne = Integer.parseInt(in.readLine());
            out.println("Entrez le numéro du document à réserver");
            int noDocument = Integer.parseInt(in.readLine());

            Abonne a = getAbonnes(nAbonne);
            Document d = getDocuments(noDocument);

            if (a != null) {
                if (d != null) {
                    synchronized (d) {

                        try {
                            d.reservationPour(Service.getAbonnes(nAbonne));
                            out.println("Bonjour " + a.getName() + ". Vous avez reservé le DVD " + (d.getNumero() + 1) + ".");
                        } catch (ReservationException e) {
                            out.println(e.getMessage());
                        }
                    }
                } else {
                    out.println("Aucun.e document ne porte ce numéro.");
                }
            }
            else {
                out.println("Aucun.e abonné ne porte ce numéro.");
            }

        } catch (IOException e) {
            System.err.println("Erreur dans le service reservation : " + e.getMessage());
        }
    }
}
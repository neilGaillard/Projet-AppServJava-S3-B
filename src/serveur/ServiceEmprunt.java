package serveur;

import appli.Abonne;
import document.Document;
import document.EmpruntException;
import document.ReservationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServiceEmprunt extends Service implements Runnable{

    public ServiceEmprunt(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            out.println("Entrez votre numéro");
            int nAbonne = Integer.parseInt(in.readLine());
            out.println("Entrez le numéro du document à emprunter");
            int noDocument = Integer.parseInt(in.readLine());

            Document d = getDocuments(noDocument);
            Abonne a = getAbonnes(nAbonne);

            if (a != null) {
                if (d != null) {
                    synchronized (documents) {
                        try {
                            d.empruntPar(Service.getAbonnes(nAbonne));
                            out.println("Bonjour " + a.getName() + ". Vous avez emprunté le DVD " + (d.getNumero() + 1 )+ ".");
                        } catch (EmpruntException e) {
                            out.println(e.getMessage());
                        }
                    }
                } else {
                    out.println("Ce document n'existe pas.");
                }
            }
            else {
                out.println("Aucun.e abonné ne porte ce numéro.");
            }
        } catch (IOException e) {
            System.err.println("Erreur dans le service Emprunt.");
        }
    }
}

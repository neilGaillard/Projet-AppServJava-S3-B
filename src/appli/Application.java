package appli;

import document.Document;
import serveur.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) throws Exception {
        boolean appli = true;

        List<Document> docs = initDoc();
        List<Abonne> abonnes = initAbo();

        ServiceEmprunt.setDocuments(docs);
        ServiceRetour.setDocuments(docs);
        ServiceReservation.setDocuments(docs);

        ServiceEmprunt.setAbonnes(abonnes);
        ServiceReservation.setAbonnes(abonnes);
        ServiceRetour.setAbonnes(abonnes);

        Thread reservation = new Thread(new ServeurReservation());
        reservation.start();
        System.err.println("ServeurReservation lance sur le port " + ServeurReservation.getPortReservation());

        Thread emprunt = new Thread(new ServeurEmprunt());
        emprunt.start();
        System.err.println("ServeurReservation lance sur le port " + ServeurEmprunt.getPortEmprunt());

        Thread retour = new Thread(new ServeurRetour());
        retour.start();
        System.err.println("ServeurReservation lance sur le port " + ServeurRetour.getPortRetour());
    }

    private static List<Abonne> initAbo(){
        List<Abonne> abonnes = new ArrayList<>();
        abonnes.add(new Abonne("Max", LocalDate.of(2010, 12, 12)));
        abonnes.add(new Abonne("Chloe", LocalDate.of(2011, 11, 13)));
        abonnes.add(new Abonne("Daniel", LocalDate.of(2007, 10, 14)));
        abonnes.add(new Abonne("Sean", LocalDate.of(1997, 9, 15)));
        abonnes.add(new Abonne("Kate", LocalDate.of(1995, 8, 16)));
        abonnes.add(new Abonne("Cassidy", LocalDate.of(1999, 7, 17)));
        return abonnes;
    }

    private static List<Document> initDoc() {
        List<Document> cours = new ArrayList<>();
        cours.add(new DVD("DVD 1", true));
        cours.add(new DVD("DVD 2", true));
        cours.add(new DVD("DVD 3", true));
        cours.add(new DVD("DVD 4", false));
        cours.add(new DVD("DVD 5", false));
        cours.add(new DVD("DVD 6", false));
        return cours;
    }
}
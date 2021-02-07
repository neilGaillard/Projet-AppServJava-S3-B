package document;

import appli.Abonne;

public interface Document {
    int getNumero();
    void reservationPour(Abonne ab) throws ReservationException;
    void empruntPar(Abonne ab) throws EmpruntException;
    // retour document ou annulation réservation
    void retour();
}
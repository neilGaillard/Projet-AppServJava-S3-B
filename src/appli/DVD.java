package appli;

import document.Document;
import document.EmpruntException;
import document.ReservationException;

import java.time.LocalDate;
import java.time.LocalTime;

public class DVD implements Document {

    private static final LocalTime tempsReservation = LocalTime.of(2,0,0,0);

    private static int NombreDVD = 0;

    private final int numero;
    private final String titre;
    private final boolean adulte;

    private boolean estPris;
    private LocalTime heureReservation;

    public DVD(String titre, boolean adulte){
        this.numero = DVD.NombreDVD;
        ++DVD.NombreDVD;
        this.titre = titre;
        this.adulte = adulte;

        this.estPris = false;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public void reservationPour(Abonne ab) throws ReservationException {
        if (this.estPris && this.heureReservation.plusHours(2).isBefore(LocalTime.now()))
            this.retour();

        if (this.estPris)
            throw new ReservationException("Ce DVD est déjà réservé, en attente d'etre emprunté. Nous vous notifierons " +
                    "quand le DVD sera disponible de nouveau.");
        else if (this.adulte && (LocalDate.now().getYear() - ab.getAnnee() < 18))
            throw new ReservationException("Vous n'etes pas assez agé.e.");
        else {
            this.estPris = true;
            heureReservation = LocalTime.now();
        }
    }

    @Override
    public void empruntPar(Abonne ab) throws EmpruntException {
        if (this.estPris)
            throw new EmpruntException("Ce DVD est déjà emprunté");
        else if (this.adulte && (LocalDate.now().getYear() - ab.getAnnee() < 18))
            throw new EmpruntException("Vous n'etes pas assez agé.e pour emprunté ce DVD.");
        else
            this.estPris = true;
    }

    @Override
    public void retour() {
        if (this.estPris) {
            this.estPris = false;
            this.heureReservation = null;
        }
    }

    @Override
    public String toString() {
        return "DVD{" +
                "numero=" + numero +
                ", titre='" + titre + '\'' +
                ", adulte=" + adulte +
                '}';
    }
}

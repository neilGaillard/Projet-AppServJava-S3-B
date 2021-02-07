package appli;

import java.time.LocalDate;

public class Abonne {

    private static int NbAbonnes = 0;

    private final int id;
    private String nom;
    private final LocalDate dateNaissance;

    public Abonne(String nom, LocalDate dateNaissance){
        this.id = Abonne.NbAbonnes;
        ++Abonne.NbAbonnes;

        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return nom;
    }

    public int getAnnee(){
        return this.dateNaissance.getYear();
    }

    @Override
    public String toString() {
        return "Abonne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateNaissance=" + dateNaissance +
                '}';
    }
}

package serveur;

import appli.Abonne;
import document.Document;

import java.net.Socket;
import java.util.List;

public abstract class Service implements Runnable{

    // **** ressources partagées : les documents *****************
    protected static List<Document> documents;
    protected static List<Abonne> abonnes;

    public static void setDocuments(List<Document> documents) {
        Service.documents = documents;
    }

    public static void setAbonnes(List<Abonne> abonnes) {
        Service.abonnes = abonnes;
    }

    protected static Document getDocuments(int nbDoc) {
        for (Document d : documents)
            if (d.getNumero() == nbDoc)
                return d;
        return null;
    }

    protected static Abonne getAbonnes(int nbAbonnes) {
        for (Abonne a : abonnes)
            if (a.getId() == nbAbonnes)
                return a;
        return null;
    }

    protected final Socket client;

    public Service(Socket socket){
        this.client = socket;
    }

    @Override
    public void run(){

    }

    protected void finalize() throws Throwable {
        client.close();
    }
}

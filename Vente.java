import java.util.Date;
import java.util.Hashtable;

public class Vente {
    Hashtable<Integer, Integer> Ticket;
    private Double Prix_tot;
    Date Date_vente;
    private int ID_vente;

    public Double getPrix_tot() {
        return Prix_tot;
    }

    public void setPrix_tot(Double prix_tot) {
        Prix_tot = prix_tot;
    }

    public int getID_vente() {
        return ID_vente;
    }

    public void setID_vente(int iD_vente) {
        ID_vente = iD_vente;
    }

    public Vente(Date date_vente, int iD_vente) {
        this.Date_vente = date_vente;
        this.ID_vente = iD_vente;
        Ticket = new Hashtable<Integer, Integer>();
    }

    public void AjouterProduit(int id, int nbr){
    }

}
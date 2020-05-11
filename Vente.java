import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

public class Vente {
    Hashtable<String, Integer> Ticket;
    private Double Prix_tot;
    Date Date_vente;
    private String ID_vente;

    public Double getPrix_tot() {
        return Prix_tot;
    }

    public void setPrix_tot(Double prix_tot) {
        Prix_tot = prix_tot;
    }

    public String getID_vente() {
        return ID_vente;
    }

    public void setID_vente(String iD_vente) {
        ID_vente = iD_vente;
    }

    public Vente(Date date_vente, String iD_vente) {
        this.Date_vente = date_vente;
        this.ID_vente = iD_vente;
        Ticket = new Hashtable<String, Integer>();
    }

    public void AjouterProduitTicket(String id, int nbr){
        Ticket.put(id, nbr);
    }

    public void AfficheTicket(){
        System.out.println("la liste des film:");
        Enumeration e = Ticket.keys();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            int nbr = (int) Ticket.get(key);
            
        }
    }

}
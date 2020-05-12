import java.sql.Date;

public class Vente {
    private String Client_ID;
    private String Vente_ID;
    private String Product_ID;
    Date Date_Vente;
    private int qte;

    public Vente(String client_ID, String vente_ID, String product_ID, Date new_date, int qte) {
        Client_ID = client_ID;
        Vente_ID= vente_ID;
        Product_ID = product_ID;
        Date_Vente = new_date;
        this.qte = qte;
    }

    public String getClient_ID() {
        return Client_ID;
    }

    public void setClient_ID(String client_ID) {
        Client_ID = client_ID;
    }

    public String getVente_ID() {
        return Vente_ID;
    }

    public void setVente_ID(String vente_ID) {
        Vente_ID = vente_ID;
    }

    public String getProduct_ID() {
        return Product_ID;
    }

    public void setProduct_ID(String product_ID) {
        Product_ID = product_ID;
    }

    public Date getDate_Vente() {
        return Date_Vente;
    }

    public void setDate_Vente(Date date_Vente) {
        Date_Vente = date_Vente;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void AfficherUneVente(){
        System.out.println(">>> vente: ");
        System.out.println("ID Vente: "+Vente_ID+" | ID Client: "+Client_ID+ " | ID produit: "+Product_ID);
        System.out.println("> "+qte+" piece(s) | Date: "+Date_Vente);
    }

}
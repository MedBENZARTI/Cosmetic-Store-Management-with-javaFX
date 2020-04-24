
public class Produit {
    private int ID_produit;
    private String Nom_produit;
    private float Prix_achat;
    private float Prix_vente;
    private String Type_produit;
    private String Marque;
    private int qte;

    public int getID_produit() {
        return ID_produit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setID_produit(int iD_produit) {
        this.ID_produit = iD_produit;
    }

    public String getNom_produit() {
        return Nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.Nom_produit = nom_produit;
    }

    public float getPrix_achat() {
        return Prix_achat;
    }

    public void setPrix_achat(float prix_achat) {
        this.Prix_achat = prix_achat;
    }

    public float getPrix_vente() {
        return Prix_vente;
    }

    public void setPrix_vente(float prix_vente) {
        this.Prix_vente = prix_vente;
    }

    public String getType_produit() {
        return Type_produit;
    }

    public void setType_produit(String type_produit) {
        this.Type_produit = type_produit;
    }

    public String getMarque() {
        return Marque;
    }

    public void setMarque(String marque) {
        this.Marque = marque;
    }

    public Produit(int iD_produit, String nom_produit, float prix_achat, float prix_vente, String type_produit,
            String marque, int qte) {
        this.ID_produit = iD_produit;
        this.Nom_produit = nom_produit;
        this.Prix_achat = prix_achat;
        this.Prix_vente = prix_vente;
        this.Type_produit = type_produit;
        this.Marque = marque;
        this.qte = qte;
    }

    public void AfficheDetailsProduit() {
        System.out.println("Produit: " + this.Nom_produit);
        System.out.println("ID: " + this.ID_produit);
        System.out.println("Marque: " + this.Marque);
        System.out.println("Type: " + this.Type_produit);
        System.out.println("Prix d'achat: " + this.Prix_achat + " dt.");
        System.out.println("Prix de vente: " + this.Prix_vente + " dt.");
        System.out.println("Quantitée: " + this.qte);
    }

    public void AfficheProduitTicket() {
        System.out.println("Produit: " + this.Nom_produit + " | Code: " + this.ID_produit + " | Prix: "
                + this.Prix_vente + " dt.");
    }

    public void Remise(float r) {
        this.Prix_vente = r * this.Prix_vente;
    }
}
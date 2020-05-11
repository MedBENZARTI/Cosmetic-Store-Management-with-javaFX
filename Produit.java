import java.util.UUID;

public class Produit {
    private String ID_produit;
    private String Nom_produit;
    private Double Prix_achat;
    private Double Prix_vente;
    private String Categ_produit;
    private String Marque;
    private int qte;

    public String getID_produit() {
        return ID_produit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setID_produit(String iD_produit) {
        this.ID_produit = iD_produit;
    }

    public String getNom_produit() {
        return Nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.Nom_produit = nom_produit;
    }

    public Double getPrix_achat() {
        return Prix_achat;
    }

    public void setPrix_achat(Double prix_achat) {
        this.Prix_achat = prix_achat;
    }

    public Double getPrix_vente() {
        return Prix_vente;
    }

    public void setPrix_vente(Double prix_vente) {
        this.Prix_vente = prix_vente;
    }

    public String getCateg_produit() {
        return Categ_produit;
    }

    public void setCateg_produit(String Categ_produit) {
        this.Categ_produit = Categ_produit;
    }

    public String getMarque() {
        return Marque;
    }

    public void setMarque(String marque) {
        this.Marque = marque;
    }

    public Produit(String ID_produit, String nom_produit, Double prix_achat, Double prix_vente, String Categ_produit,
            String marque, int qte) {
        // String uuid= UUID.randomUUID().toString();
        // this.ID_produit=uuid.substring(0, uuid.indexOf('-'));
        this.ID_produit=ID_produit;
        this.Nom_produit = nom_produit;
        this.Prix_achat = prix_achat;
        this.Prix_vente = prix_vente;
        this.Categ_produit = Categ_produit;
        this.Marque = marque;
        this.qte = qte;
    }

    public void AfficheDetailsProduit() {
        System.out.println("Produit: " + this.Nom_produit);
        System.out.println("ID: " + this.ID_produit);
        System.out.println("Marque: " + this.Marque);
        System.out.println("Categorie: " + this.Categ_produit);
        System.out.println("Prix d'achat: " + this.Prix_achat + " dt.");
        System.out.println("Prix de vente: " + this.Prix_vente + " dt.");
        System.out.println("Quantitée: " + this.qte);
    }

    public void AfficheDetailsProduitDeCategorie() {
        System.out.println("Produit: " + this.Nom_produit);
        System.out.println("ID: " + this.ID_produit);
        System.out.println("Marque: " + this.Marque);
        System.out.println("Prix d'achat: " + this.Prix_achat + " dt.");
        System.out.println("Prix de vente: " + this.Prix_vente + " dt.");
        System.out.println("Quantitée: " + this.qte);
    }

    public void AfficheProduitTicket() {
        System.out.println("- Produit: " + this.Nom_produit + " | Code: " + this.ID_produit + " | Prix: "
                + this.Prix_vente + " dt.");
    }

    public void Remise(Double r) {
        this.Prix_vente = (1-r) * this.Prix_vente;
    }

    public void AddProduct(int nbr) {
        this.qte += nbr;
    }

    public void SellProduct(int nbr) {
        this.qte -= nbr;
    }
}
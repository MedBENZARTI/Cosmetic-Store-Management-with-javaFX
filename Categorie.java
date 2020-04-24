import java.util.ArrayList;

/**
 * Categorie
 */
public class Categorie {

    private ArrayList Tab_prod;
    private String Nom_Cat;
    private int ID_cat;

    public Categorie(String nom, int ID) {
        Tab_prod = new ArrayList<>();
        this.Nom_Cat = nom;
        this.ID_cat = ID;
    }

    public String getNom_Cat() {
        return Nom_Cat;
    }

    public void setNom_Cat(String nom_Cat) {
        Nom_Cat = nom_Cat;
    }

    public int getID_cat() {
        return ID_cat;
    }

    public void setID_cat(int iD_cat) {
        ID_cat = iD_cat;
    }

    public Produit FindProductID(int id) {
        boolean find = false;
        for (int i = 0; i < Tab_prod.size(); i++) {
            Produit p = (Produit) Tab_prod.get(i);
            if (p.getID_produit() == id) {
                find = true;
                return (Produit) Tab_prod.get(i);
            }
        }
        if (!find) {
            System.out.println("Ther's no product with this ID");
        }
        return null;
    }

    public void FindProductMarque(String marque) {
        for (int i = 0; i < Tab_prod.size(); i++) {
            Produit p = (Produit) Tab_prod.get(i);
            if (p.getType_produit().equals(marque)) {
                p.AfficheProduitTicket();
            }
        }
        System.out.println("Ther's no product of this brand");
    }

    public void AjouterProduit(Produit p) {
        Tab_prod.add(p);
    }

    public void AfiicheCategorie() {
        for (int i = 0; i < Tab_prod.size(); i++) {
            Produit p = (Produit) Tab_prod.get(i);
            p.AfficheProduitTicket();
        }
    }

}

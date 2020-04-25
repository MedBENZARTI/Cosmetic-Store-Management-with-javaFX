import java.util.ArrayList;

/**
 * Categorie
 */
public class Categorie {

    private ArrayList<Produit> Tab_prod;
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

    public Produit FindProductID(int id) { // return the product for a given ID
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

    public void FindProductMarque(String marque) { // list all the products for the given brand
        boolean find = false;
        for (int i = 0; i < Tab_prod.size(); i++) {
            Produit p = (Produit) Tab_prod.get(i);
            if (p.getMarque().equals(marque)) {
                p.AfficheProduitTicket();
            }
        }
        if (!find) {
            System.out.println("Ther's no product for this brand");
        }
    }

    public void AjouterProduit(Produit p) { // adds the given product to the category
        if (p.getCateg_produit().equals(this.Nom_Cat)) {

        } else {

        }
        Tab_prod.add(p);
    }

    public void AfficheCategorie() { // prints the details aboute the category
        System.out.println(">>> The category " + this.Nom_Cat + ", ID: " + this.ID_cat + " contains:");
        for (int i = 0; i < Tab_prod.size(); i++) {
            Produit p = (Produit) Tab_prod.get(i);
            p.AfficheProduitTicket();
        }
    }

    public void AfficheCategorieDetails() { // prints more details aboute every product in the category
        System.out.println(">>> The category " + this.Nom_Cat + ", ID: " + this.ID_cat + " contains:");
        for (int i = 0; i < Tab_prod.size(); i++) {
            Produit p = Tab_prod.get(i);
            p.AfficheDetailsProduit();
        }
    }

    public void SupprimerProduit(int ID) { // deletes a product
        Produit p = FindProductID(ID);
        boolean operation = Tab_prod.remove(p);
        // System.out.println("operation is " + operation);
        if (operation == true) {
            System.out.println("The product is successfully deleted");
        }

    }

    public void RemiseCategory(Double r) {
        for (int i = 0; i < Tab_prod.size(); i++) {
            Produit p = (Produit) Tab_prod.get(i);
            p.Remise(r);;
        }
    }
}

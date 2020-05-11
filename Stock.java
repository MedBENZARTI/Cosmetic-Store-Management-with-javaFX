import java.util.ArrayList;

public class Stock {
    private ArrayList<Produit> stock;
    private String nom_magasin;
    private ArrayList<Categorie> categ;

    public Stock(String nom) {
        this.setNom_magasin(nom);
        stock = new ArrayList<Produit>();
        categ = new ArrayList<Categorie>();

    }

    public String getNom_magasin() {
        return nom_magasin;
    }

    public void setNom_magasin(String nom_magasin) {
        this.nom_magasin = nom_magasin;
    }

    public void AjouterProduit(String id,String nom_produit, Double prix_achat, Double prix_vente, String Categ_produit,
            String marque, int qte) {
        Produit p = new Produit(id,nom_produit, prix_achat, prix_vente, Categ_produit, marque, qte);
        ArrayList<String> Liste_categories = new ArrayList<String>();
        for (int i = 0; i < categ.size(); i++) {
            Categorie c = (Categorie) categ.get(i);
            Liste_categories.add(c.getNom_Cat());
        }
        if (Liste_categories.contains(Categ_produit)) {
            for (int i = 0; i < categ.size(); i++) {
                Categorie c = (Categorie) categ.get(i);
                if (c.getNom_Cat().equals(Categ_produit)) {
                    c.AjouterProduit(p);
                }
            }
        } else {
            Categorie NewCateg = new Categorie(Categ_produit);
            categ.add(NewCateg);
            NewCateg.AjouterProduit(p);
        }

        stock.add(p);
    }

    public Produit ChercherProduitID(String id) {
        for (int i = 0; i < stock.size(); i++) {
            Produit p = (Produit) stock.get(i);
            if (p.getID_produit().equals(id)) {
                return (Produit) stock.get(i);
            }
        }
        return null;
    }

    public Categorie AffecterCategorie(Produit p) {
        for (int i = 0; i < categ.size(); i++) {
            Categorie C = (Categorie) categ.get(i);
            if (C.getNom_Cat().equals(p.getCateg_produit())) {
                return (Categorie) categ.get(i);
            }
        }
        return null;
    }

    public void SupprimerProduit(String id) { // deletes a the product of a given id
        Produit p = ChercherProduitID(id); // gives the product of that ID
        Categorie C = AffecterCategorie(p); // gives the categ of that product
        if (p != null) {
            stock.remove(p);
            C.SupprimerProduit(id);
            System.out.println("The product is successfully deleted");
        } else {
            System.out.println("Ther's no product with this ID");
        }
    }

    public void AfficheStock() {
        System.out.println("The products of " + this.nom_magasin + ":");
        for (int i = 0; i < stock.size(); i++) {
            Produit p = stock.get(i);
            p.AfficheProduitTicket();
        }
    }

    public void AfficheStockDetails() {
        System.out.println("The products of " + this.nom_magasin + ":");
        for (int i = 0; i < stock.size(); i++) {
            Produit p = stock.get(i);
            p.AfficheDetailsProduit();
        }
    }

    public void AfficheStockCategories() {
        System.out.println("Affichage selon categorie: ");
        for (int i = 0; i < categ.size(); i++) {
            Categorie C = (Categorie) categ.get(i);
            C.AfficheCategorie();
        }
    }

    public Produit DonnerProduitNum(int n) {
        return (Produit) stock.get(n);
    }

    public void SellProduct(Produit p, int qte) {
        if (p.getQte() != 0) {
            if (p.getQte() < qte) {
                if (p.getQte() == 1) {
                    System.out.println("Operatin failed !! We still only have one piece");
                } else {
                    System.out.println("Operatin failed !! We still only have " + p.getQte() + " pieces");
                }
            } else {
                p.SellProduct(qte);
            }
        } else {
            System.out.println("Sorry we don't have any piece of that product");
        }
    }

}
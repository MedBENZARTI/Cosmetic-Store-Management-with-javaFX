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

    public void AjouterProduit(String nom_produit, Double prix_achat, Double prix_vente, String Categ_produit,String marque, int qte) {
        Produit p = new Produit(nom_produit, prix_achat, prix_vente, Categ_produit, marque, qte);
        ArrayList <String> Liste_categories = new ArrayList<String>();
        for (int i = 0; i < categ.size(); i++) {
            Categorie c = (Categorie) categ.get(i);
            Liste_categories.add(c.getNom_Cat());
        }
        if(Liste_categories.contains(Categ_produit)){
            for (int i = 0; i < categ.size(); i++) {
                Categorie c = (Categorie) categ.get(i);
                if (c.getNom_Cat().equals(Categ_produit)) {
                    c.AjouterProduit(p);
                }
            }
        }
        else{
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

    public void SupprimerProduit(String id) {
        Produit p = ChercherProduitID(id);
        Categorie C = AffecterCategorie(p);
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

    public void AfficheStockCategories() {
        System.out.println("Affichage selon categorie: ");
        for (int i = 0; i < categ.size(); i++) {
            Categorie C = (Categorie) categ.get(i);
            C.AfficheCategorie();
        }
    }

    public void OrganiserStock() {
        boolean test = false;
        System.out.println();
        for (int i = 0; i < stock.size(); i++) {
            Produit p = (Produit) stock.get(i);
            if (categ.size() == 0) {
                Categorie new_cat = new Categorie(p.getCateg_produit());
                new_cat.AjouterProduit(p);
                categ.add(new_cat);
                System.out.println("c");
            } else {
                int j = 0;
                while (j < categ.size() || test == false) {
                    Categorie C = (Categorie) categ.get(j);
                    if (C.getNom_Cat().equals(p.getCateg_produit())) {
                        C.AjouterProduit(p);
                        test = true;
                    }
                    j++;
                }
                if (test == true) {
                    test = false;
                } else {
                    Categorie an_iterator_cat = new Categorie(p.getCateg_produit());
                    an_iterator_cat.AjouterProduit(p);
                    categ.add(an_iterator_cat);
                }
            }
        }
    }

    public Produit DonnerProduitNum(int n) {
        return (Produit) stock.get(n);
    }

    public void OrganiserStock2() {
        Categorie first_categ = new Categorie(stock.get(0).getCateg_produit());
        first_categ.AjouterProduit((Produit) stock.get(0));
        categ.add(first_categ);
        for (int i = 1; i < stock.size(); i++) {
            Produit p = (Produit) stock.get(i);
            int j = 0;
            boolean test = false;
            while (j < categ.size() || test == false) {
                Categorie iterator_cat = categ.get(j);
                if (p.getCateg_produit().equals(iterator_cat.getNom_Cat())) {
                    iterator_cat.AjouterProduit(p);
                } else {
                    Categorie new_one = new Categorie(p.getCateg_produit());
                    new_one.AjouterProduit(p);
                    categ.add(new_one);
                }
            }
        }
    }
}
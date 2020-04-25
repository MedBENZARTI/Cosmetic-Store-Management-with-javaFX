
public class Prog {

    public static void main(String[] args) {
        Produit p1 = new Produit(11, "javel", 10.0, 12.0, "clean", "judy", 5);
        Produit p2 = new Produit(12, "shampoo", 100.0, 120.0, "clean", "judy", 5);
        Produit p3 = new Produit(12, "deo", 50.0, 52.0, "clean", "judy", 5);

        p1.AfficheProduitTicket();
        p2.AfficheProduitTicket();
        p3.AfficheProduitTicket();

        Categorie c = new Categorie("c1", 123);
        c.AjouterProduit(p1);
        c.AjouterProduit(p2);
        c.AjouterProduit(p3);
        c.AfficheCategorie();
        System.out.println("-----------------------------------------------");
        // Produit p = c.FindProductID(11);
        // p.AfficheProduitTicket();
        // c.FindProductID(21);

        c.SupprimerProduit(11);
        System.out.println("-----------------------------------------------");
        c.AfficheCategorieDetails();
        System.out.println("-----------------------------------------------");
        c.RemiseCategory(0.11);
        c.AfficheCategorie();
    }

}
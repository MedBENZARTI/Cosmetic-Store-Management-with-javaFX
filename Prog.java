import java.util.UUID;

public class Prog {

    public static void main(String[] args) {

        System.out.println();
        Stock stock = new Stock("hammam lif");
        stock.AjouterProduit("p1", 10.0, 12.0, "c1", "judy", 5);
        stock.AjouterProduit("p2", 100.0, 120.0, "c2", "judy", 5);
        stock.AjouterProduit("p3", 50.0, 52.0, "c1", "judy", 5);
        stock.AjouterProduit("p4", 10.0, 12.0, "c3", "judy", 5);
        stock.AjouterProduit("p5", 10.0, 12.0, "c2", "judy", 5);


        stock.AfficheStock();
        // stock.ensemble();
        System.out.println("-----------------------------------------------");
        stock.AfficheStockCategories();
        System.out.println("-----------------------------------------------");
        Produit p = stock.DonnerProduitNum(1) ;
        // stock.SupprimerProduit(p.getID_produit());
        // stock.AfficheStockCategories();
        // System.out.println("-----------------------------------------------");
        // stock.AfficheStock();
        
        }

    }

// Produit p1 = new Produit(11, "javel", 10.0, 12.0, "c", "judy", 5);
// Produit p2 = new Produit(12, "shampoo", 100.0, 120.0, "c", "judy", 5);
// Produit p3 = new Produit(12, "deo", 50.0, 52.0, "c", "judy", 5);

// p1.AfficheProduitTicket();
// p2.AfficheProduitTicket();
// p3.AfficheProduitTicket();

// Categorie c = new Categorie("c1", 123);
// c.AjouterProduit(p1);
// c.AjouterProduit(p2);
// c.AjouterProduit(p3);
// c.AfficheCategorie();
// System.out.println("-----------------------------------------------");
// // Produit p = c.FindProductID(11);
// // p.AfficheProduitTicket();
// // c.FindProductID(21);

// c.SupprimerProduit(11);
// System.out.println("-----------------------------------------------");
// c.AfficheCategorieDetails();
// System.out.println("-----------------------------------------------");
// p1.AfficheProduitTicket();
import java.sql.SQLException;
import java.util.UUID;

public class Prog {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connect c = new Connect();
        // c.AjouterProduit("p1", 12.0, 13.0, "c1", "m1", 10);
        // c.AjouterProduit("p2", 13.0, 15.0, "c2", "m2", 10);
        // c.AjouterProduit("p3", 14.0, 15.0, "c1", "m3", 10);
        // c.AjouterProduit("p4", 15.0, 18.0, "c4", "m4", 10);
        // c.AjouterProduit("p5", 16.0, 19.0, "c1", "m5", 10);
        c.AfficherStock();
        c.AfficherStockSelonCategorie("c1");
        // Produit p = c.LookForProdct("p4");
        c.AjouterQteAuPriduit("p1", 50);
        c.AfficherDetailsStock();

        
        // System.out.println();
        // Stock stock = new Stock("hammam lif");
        // stock.AjouterProduit("p1", 10.0, 12.0, "c1", "judy", 5);
        // stock.AjouterProduit("p2", 100.0, 120.0, "c2", "judy", 5);
        // stock.AjouterProduit("p3", 50.0, 52.0, "c1", "judy", 5);
        // stock.AjouterProduit("p4", 10.0, 12.0, "c3", "judy", 5);
        // stock.AjouterProduit("p5", 10.0, 12.0, "c2", "judy", 5);

        // stock.AfficheStockDetails();
        // System.out.println("-----------------------------------------------");
        // stock.AfficheStockCategories();
        // System.out.println("-----------------------------------------------");
        // Produit p = stock.DonnerProduitNum(1) ;
        // stock.SellProduct(p,2);
        // stock.AfficheStockDetails();
        // System.out.println("-----------------------------------------------");
        // stock.AfficheStockCategories();
        // stock.SellProduct(p,4);
        // stock.SellProduct(p,3);
        // stock.SellProduct(p,1);
        // stock.AfficheStockDetails();
        // System.out.println("-----------------------------------------------");

    }

}

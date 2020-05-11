import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

public class Connect {
    public Connection C; // tunnel de la connexion
    public Statement stmt; // transporteur des requêtes
    public PreparedStatement pstmt;
    public ResultSet res; // résultat de la requete
    public Produit p;

    public Connect() throws SQLException, ClassNotFoundException {
        try {
            // driver de la connexion
            Class.forName("com.mysql.jdbc.Driver");
            // établir une connexion( url de la base, login, passsword)
            C = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
            // transporteur des requêtes
            stmt = C.createStatement();
            System.out.println("success");
        } catch (Exception e) {
            System.out.println("erreur:Driver introuvable");
            e.printStackTrace();
        }
    }

    public ArrayList<Produit> AllProducts() {
        String req = "select * from Produit";
        ArrayList<Produit> list_product = new ArrayList<Produit>();
        try {
            res = stmt.executeQuery(req);
            while (res.next()) {
                Produit p = new Produit(res.getString(1), res.getString(2), res.getDouble(3), res.getDouble(4),
                        res.getString(5), res.getString(6), res.getInt(7));
                list_product.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_product;
    }

    public void AfficherDetailsStock() {
        System.out.println("Les produits de ce stock sont: ");
        ArrayList<Produit> a = AllProducts();
        for (int i = 0; i < a.size(); i++) {
            Produit p = (Produit) a.get(i);
            p.AfficheDetailsProduit();
        }
    }

    public void AfficherStock() {
        System.out.println("Les produits de ce stock sont: ");
        ArrayList<Produit> a = AllProducts();
        for (int i = 0; i < a.size(); i++) {
            Produit p = (Produit) a.get(i);
            p.AfficheProduitTicket();
        }
    }

    public ArrayList<Produit> ProductsOfCategory(String categ) {
        String req = "select * from Produit where Categ_produit = '" + categ + "'";
        ArrayList<Produit> list_product = new ArrayList<Produit>();
        try {
            res = stmt.executeQuery(req);
            while (res.next()) {
                Produit p = new Produit(res.getString(1), res.getString(2), res.getDouble(3), res.getDouble(4),
                        res.getString(5), res.getString(6), res.getInt(7));
                list_product.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_product;
    }

    public void AfficherDetailsSelonCategorie(String categ) {
        System.out.println("Les produits de la categorie " + categ + " sont:");
        ArrayList<Produit> a = ProductsOfCategory(categ);
        for (int i = 0; i < a.size(); i++) {
            Produit p = (Produit) a.get(i);
            p.AfficheDetailsProduitDeCategorie();
        }
    }

    public void AfficherStockSelonCategorie(String categ) {
        System.out.println("Les produits de la categorie " + categ + " sont:");
        ArrayList<Produit> a = ProductsOfCategory(categ);
        for (int i = 0; i < a.size(); i++) {
            Produit p = (Produit) a.get(i);
            p.AfficheProduitTicket();
        }
    }

    public void AjouterProduit(String nom_produit, Double prix_achat, Double prix_vente, String Categ_produit,
            String marque, int qte) {
        String uuid = UUID.randomUUID().toString();
        String ID_produit = uuid.substring(0, uuid.indexOf('-'));
        Produit p = new Produit(ID_produit, nom_produit, prix_achat, prix_vente, Categ_produit, marque, qte);
        String req = "insert into Produit values ('";
        req = req + p.getID_produit() + "','";
        req = req + p.getNom_produit() + "','";
        req = req + p.getPrix_achat() + "','";
        req = req + p.getPrix_vente() + "','";
        req = req + p.getCateg_produit() + "','";
        req = req + p.getMarque() + "','";
        req = req + p.getQte() + "')";
        System.out.println(req);
        try {
            int res = stmt.executeUpdate(req);
            System.out.println("Produit ajouté avec succes");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void VendreUnProduit(String name, int nbr) {
        try {
            pstmt = C.prepareStatement("SELECT * FROM produit WHERE Nom_produit = ?");
            pstmt.setString(1, name);
            res = pstmt.executeQuery();
            while (res.next()) {
                p = new Produit(res.getString("ID_produit"), res.getString("Nom_produit"), res.getDouble("prix_achat"),
                        res.getDouble("Prix_vente"), res.getString("Categ_produit"), res.getString("Marque"),
                        res.getInt("qte"));
            }
            if (p.getQte() > nbr) {
                PreparedStatement pstmt2 = C.prepareStatement("UPDATE produit SET qte = ? WHERE Nom_produit = ? ");
                int newQte = p.getQte() - nbr;
                pstmt2.setInt(1, newQte);
                pstmt2.setString(2, name);
                pstmt2.executeUpdate();
                p.setQte(newQte);
            } else {
                System.out.println("impo");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    } 
    
    public void AjouterQteAuPriduit(String name, int nbr){
        VendreUnProduit(name, -nbr);
    }

    public Produit LookForProdct(String name) {
        String req = "select * from Produit where Nom_produit ='" + name + "'";
        System.out.println(req);

        try {

            res = stmt.executeQuery(req);
            if (res.next()) {
                Produit p = new Produit(res.getString(1), res.getString(2), res.getDouble(3), res.getDouble(4),
                        res.getString(5), res.getString(6), res.getInt(7));
                return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

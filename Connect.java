import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.UUID;
import java.sql.*;

public class Connect {
    public Connection C; // tunnel de la connexion
    public Statement stmt; // transporteur des requêtes
    public PreparedStatement pstmt;
    public ResultSet res; // résultat de la requete
    Produit p;
    Vente v;
    Client c;

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

    private String newID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, uuid.indexOf('-'));
    }

    public void AjouterProduit(String nom_produit, Double prix_achat, Double prix_vente, String Categ_produit,
            String marque, int qte) {
        Produit p = new Produit(newID(), nom_produit, prix_achat, prix_vente, Categ_produit, marque, qte);
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

    public void VendreUnProduit(String client_ID, String name, int nbr) {
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

            AjouterVente(client_ID, p.getID_produit(), nbr);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    // public Produit LookForProdct(String name) {
    // String req = "select * from Produit where Nom_produit ='" + name + "'";
    // System.out.println(req);

    // try {

    // res = stmt.executeQuery(req);
    // if (res.next()) {
    // Produit p = new Produit(res.getString(1), res.getString(2), res.getDouble(3),
    // res.getDouble(4),
    // res.getString(5), res.getString(6), res.getInt(7));
    // return p;
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return null;
    // }

    public void RemiseCateg(String categ, Double remise) {

        try {
            pstmt = C.prepareStatement("update Produit set Prix_vente = Prix_vente * ? where Categ_produit = ? ");
            remise = remise / 100;
            pstmt.setDouble(1, 1 - remise);
            pstmt.setString(2, categ);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /////////////////////////////// VENTE ////////////////////////////////
    //////////////////////////////////////////////////////////////////////

    public void AjouterVente(String client_ID, String product_ID, int qte) {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        v = new Vente(client_ID, newID(), product_ID, date, qte);

        try {
            pstmt = C.prepareStatement("insert into Vente values (?,?,?,?,?)");
            pstmt.setString(1, v.getClient_ID());
            pstmt.setString(2, v.getVente_ID());
            pstmt.setString(3, v.getProduct_ID());
            pstmt.setDate(4, v.getDate_Vente());
            pstmt.setInt(5, v.getQte());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void AfficherTousLesVentes() {
        try {
            pstmt = C.prepareStatement("select * from Vente");
            res = pstmt.executeQuery();

            while (res.next()) {
                v = new Vente(res.getString(1), res.getString(2), res.getString(3), res.getDate(4), res.getInt(5));
                v.AfficherUneVente();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void VenteDeMr(String nom, String prenom) {
        try {
            pstmt = C.prepareStatement(
                    "select Client_ID,Vente_ID,Product_ID,Date_Vente,Vente.qte from Produit join Vente join Client where ID_produit=Product_ID and ID_client=Client_ID and nom = ? and prenom = ?");
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            while (res.next()) {
                v = new Vente(res.getString(1), res.getString(2), res.getString(3), res.getDate(4), res.getInt(5));
                v.AfficherUneVente();

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /////////////////////////////// CLIENT ////////////////////////////////
    //////////////////////////////////////////////////////////////////////

    public void AjouterClient(String nom, String prenom) {
        c = new Client(newID(), nom, prenom);

        try {
            pstmt = C.prepareStatement("insert into Client values (?,?,?)");
            pstmt.setString(1, c.getID_client());
            pstmt.setString(2, c.getNom());
            pstmt.setString(3, c.getPrenom());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void AfficherTousLeClients() {
        try {
            pstmt = C.prepareStatement("select * from Client");
            res = pstmt.executeQuery();

            while (res.next()) {
                c = new Client(res.getString(1), res.getString(2), res.getString(3));
                c.AfficherClient();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

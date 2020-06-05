package Classes;

import java.sql.SQLException;

import Connect.Connector;

// import Interfaces.*;

public class Prog {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    Product p = new Product("bfh5ri9", "p1", 12.0, 13.0, "c1", "m1", 10);
    Connector c = new Connector();
    c.addPorduct(p);
}

}
package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Connector {
    public Connection C; // tunnel de la connexion
    public Statement stmt; // transporteur des requêtes
    public PreparedStatement pStatement;
    public ResultSet res; // résultat de la requete
    Product p;
    Sale s;
    Client c;

    public Connector() {
        try {
            // driver de la connexion
            Class.forName("com.mysql.jdbc.Driver");
            // établir une connexion( url de la base, login, passsword)
            C = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "root");
            // transporteur des requêtes
            stmt = C.createStatement();
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String newID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, uuid.indexOf('-'));
    }

    public ObservableList<Product> AllProducts() {
        String req = "select * from Product";
        ObservableList<Product> list_product = FXCollections.observableArrayList();
        try {
            res = stmt.executeQuery(req);
            while (res.next()) {
                Product p = new Product(res.getString(1), res.getString(2), res.getDouble(3), res.getDouble(4),
                        res.getString(5), res.getString(6), res.getInt(7));
                list_product.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_product;
    }

    public ObservableList<Sale> AllSales() {
        String req = "select * from sale";
        ObservableList<Sale> list_sale = FXCollections.observableArrayList();
        try {
            res = stmt.executeQuery(req);
            while (res.next()) {
                Sale s = new Sale(res.getString(1), res.getString(2), res.getString(3), res.getTimestamp(5), res.getInt(6),
                        res.getDouble(7), res.getString(4));
                list_sale.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_sale;
    }

    public ObservableList<Category> AllCategories() {
        String req = "select * from category";
        ObservableList<Category> list_categ = FXCollections.observableArrayList();
        try {
            res = stmt.executeQuery(req);
            while (res.next()) {
                Category c = new Category(res.getString(1), res.getString(2));
                list_categ.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_categ;
    }

    public ObservableList<Client> AllClients() {
        String req = "select * from client";
        ObservableList<Client> list_client = FXCollections.observableArrayList();
        try {
            res = stmt.executeQuery(req);
            while (res.next()) {
                Client c = new Client(res.getString(1), res.getString(2), res.getString(3), res.getInt(4));
                list_client.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_client;
    }

    public ObservableList<Employee> Allemployee() {
        String req = "select * from employee";
        ObservableList<Employee> list_employee = FXCollections.observableArrayList();
        try {
            res = stmt.executeQuery(req);
            while (res.next()) {
                Employee e = new Employee(res.getString(1), res.getString(2), res.getString(3), res.getInt(4),
                        res.getString(5), res.getDouble(6));
                list_employee.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_employee;
    }

    public void addPorduct(Product p) {
        try {
            pStatement = C.prepareStatement("insert into product values (?,?,?,?,?,?,?)");
            pStatement.setString(1, p.getProductID());
            pStatement.setString(2, p.getProductName());
            pStatement.setDouble(3, p.getBuyingPrice());
            pStatement.setDouble(4, p.getSellingPrice());
            pStatement.setString(5, p.getProductCat());
            pStatement.setString(6, p.getMark());
            pStatement.setInt(7, p.getQte());

            int r = pStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addSale(Sale s) {
        try {
            pStatement = C.prepareStatement("insert into sale values (?,?,?,?,?,?,?)");
            pStatement.setString(1, s.getClientID());
            pStatement.setString(2, s.getSaleID());
            pStatement.setString(3, s.getProductID());
            pStatement.setString(4, s.getEmployeeID());
            pStatement.setTimestamp(5, s.getSaleDate());
            pStatement.setInt(6, s.getQte());
            pStatement.setDouble(7, s.getTotalePrice());

            int r = pStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addCategory(Category cat) {
        try {
            pStatement = C.prepareStatement("insert into category values (?,?)");
            pStatement.setString(2, cat.getCatID());
            pStatement.setString(1, cat.getCatName());
            int r = pStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addClient(Client c) {
        try {
            pStatement = C.prepareStatement("insert into client values (?,?,?,?)");
            pStatement.setString(1, c.getID());
            pStatement.setString(2, c.getFirstName());
            pStatement.setString(3, c.getLastName());
            pStatement.setInt(4, c.getPhoneNbr());
            int r = pStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addemployee(Employee e) {
        try {
            pStatement = C.prepareStatement("insert into employee values (?,?,?,?,?,?)");
            pStatement.setString(1, e.getID());
            pStatement.setString(2, e.getFirstName());
            pStatement.setString(3, e.getLastName());
            pStatement.setInt(4, e.getPhoneNbr());
            pStatement.setString(5, e.getGrade());
            pStatement.setDouble(6, e.getSalary());
            int r = pStatement.executeUpdate();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void deleteProduct(Product p) {
        try {
            pStatement = C.prepareStatement("Delete from product where ProductID = ? ");
            pStatement.setString(1, p.getProductID());
            int r = pStatement.executeUpdate();
            System.out.println("Prod deleted");
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void deleteCategory(Category c) {
        try {
            pStatement = C.prepareStatement("Delete from category where CatID = ? ");
            pStatement.setString(1, c.getCatID());
            int r = pStatement.executeUpdate();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void deleteClient(Client c) {
        try {
            pStatement = C.prepareStatement("Delete from client where ClientID = ? ");
            pStatement.setString(1, c.getID());
            int r = pStatement.executeUpdate();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void deleteEmployee(Employee e) {
        try {
            pStatement = C.prepareStatement("Delete from employee where ID = ? ");
            pStatement.setString(1, e.getID());
            int r = pStatement.executeUpdate();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void deleteSale(Sale s) {
        try {
            pStatement = C.prepareStatement("Delete from sale where SaleID = ? ");
            pStatement.setString(1, s.getSaleID());
            int r = pStatement.executeUpdate();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void makeDiscount(String id, Double newvalue) {
        try {
            pStatement = C.prepareStatement("update product set SellingPrice = ? where ProductID = ? ");
            pStatement.setDouble(1, newvalue);
            pStatement.setString(2, id);
            int r = pStatement.executeUpdate();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }

    public Product getProdByID(String id) {
        String req = "select * from Product where ProductID = '" + id + "'";
        ObservableList<Product> list_product = FXCollections.observableArrayList();
        try {
            res = stmt.executeQuery(req);
            while (res.next()) {
                Product p = new Product(res.getString(1), res.getString(2), res.getDouble(3), res.getDouble(4),
                        res.getString(5), res.getString(6), res.getInt(7));
                list_product.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_product.get(0);
    }
    public Employee getEmployeeByID(String id) {
        String req = "select * from employee where ID = '" + id + "'";
        ObservableList<Employee> list_employee = FXCollections.observableArrayList();
        try {
            res = stmt.executeQuery(req);
            while (res.next()) {
                Employee emp = new Employee(res.getString(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5), res.getDouble(6));
                list_employee.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_employee.get(0);
    }
    public Client getClientByID(String id) {
        String req = "select * from client where ClientID = '" + id + "'";
        ObservableList<Client> list_client = FXCollections.observableArrayList();
        try {
            res = stmt.executeQuery(req);
            while (res.next()) {
                Client c = new Client(res.getString(1), res.getString(2), res.getString(3), res.getInt(4));
                list_client.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_client.get(0);
    }

    public Product getProdByIDandPrice(String id, Double p) {
        String req = "select * from Product where ProductID = '" + id + "' and SellingPrice = " + p.toString();
        ObservableList<Product> list_product = FXCollections.observableArrayList();
        try {
            res = stmt.executeQuery(req);
            while (res.next()) {
                Product pr = new Product(res.getString(1), res.getString(2), res.getDouble(3), res.getDouble(4),
                        res.getString(5), res.getString(6), res.getInt(7));
                list_product.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_product.get(0);
    }

    public ObservableList<Product> Filtrewithname(String nom, Double mn, Double Mx) {
        ObservableList<Product> list_product = FXCollections.observableArrayList();
        try {
            pStatement = C
                    .prepareStatement("select * from product where ProductName = ? and SellingPrice between ? and ?");
            pStatement.setString(1, nom);
            pStatement.setDouble(2, mn);
            pStatement.setDouble(3, Mx);
            res = pStatement.executeQuery();
            while (res.next()) {
                Product pr = new Product(res.getString(1), res.getString(2), res.getDouble(3), res.getDouble(4),
                        res.getString(5), res.getString(6), res.getInt(7));
                list_product.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list_product;
    }

    public ObservableList<Product> Filtrewithoutname(Double mn, Double Mx) {
        ObservableList<Product> list_product = FXCollections.observableArrayList();
        try {
            pStatement = C.prepareStatement("select * from product where SellingPrice between ? and ?");
            pStatement.setDouble(1, mn);
            pStatement.setDouble(2, Mx);
            res = pStatement.executeQuery();
            while (res.next()) {
                Product pr = new Product(res.getString(1), res.getString(2), res.getDouble(3), res.getDouble(4),
                        res.getString(5), res.getString(6), res.getInt(7));
                list_product.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list_product;
    }

    // public ObservableList<Product> Filtreonlyprice(String nom, Double mn, Double Mx, String Categ) {
    //     ObservableList<Product> list_product = FXCollections.observableArrayList();
    //     try {
    //         pStatement = C.prepareStatement(
    //                 "select * from product where ProductName = ? and ProductCat = ? and SellingPrice between ? and ?");
    //         res = pStatement.executeQuery();
    //         while (res.next()) {
    //             Product pr = new Product(res.getString(1), res.getString(2), res.getDouble(3), res.getDouble(4),
    //                     res.getString(5), res.getString(6), res.getInt(7));
    //             list_product.add(pr);
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }

    //     return list_product;
    // }

    public ObservableList<Product> getProductByCategory(String c) {
        ObservableList<Product> list_product = FXCollections.observableArrayList();
        try {
            pStatement = C.prepareStatement("select * from product where ProductCat = ?");
            pStatement.setString(1, c);
            res = pStatement.executeQuery();
            while (res.next()) {
                Product pr = new Product(res.getString(1), res.getString(2), res.getDouble(3), res.getDouble(4),
                        res.getString(5), res.getString(6), res.getInt(7));
                list_product.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_product;
    }

    public ArrayList<String> getCategoriesNames() {
        ArrayList<String> Categs = new ArrayList<>();
        try {
            pStatement = C.prepareStatement("select CatName from category");
            res = pStatement.executeQuery();
            while (res.next()) {
                Categs.add(res.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Categs;
    }

    public ObservableList<String> employeeToString() {
        ObservableList<String> employee = FXCollections.observableArrayList();
        try {
            pStatement = C.prepareStatement("select ID, FirstName , LastName,Grade from employee");
            res = pStatement.executeQuery();
            while (res.next()) {
                String emp = res.getString(1) + " | "+res.getString(2) +" "+ res.getString(3) + " | " + res.getString(4);
                employee.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    public ObservableList<String> clientToString() {
        ObservableList<String> client = FXCollections.observableArrayList();
        try {
            pStatement = C.prepareStatement("select ClientID, FirstName , LastName from client");
            res = pStatement.executeQuery();
            while (res.next()) {
                String cl = res.getString(1) + " | " + res.getString(2)+" "+res.getString(3);
                client.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

    public ObservableList<String> prodToString() {
        ObservableList<String> prod = FXCollections.observableArrayList();
        try {
            pStatement = C.prepareStatement("select ProductID , ProductName , Mark from product");
            res = pStatement.executeQuery();
            while (res.next()) {
                String pr = res.getString(1) + " | " + res.getString(2) + " | " + res.getString(3);
                prod.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prod;
    }

    public ObservableList<Sale> SalesByEmployee(String id) {
        String req = "select * from sale where employeeID = '"+id+"'";
        ObservableList<Sale> list_sale = FXCollections.observableArrayList();
        try {
            res = stmt.executeQuery(req);
            while (res.next()) {
                Sale s = new Sale(res.getString(1), res.getString(2), res.getString(4), res.getTimestamp(5), res.getInt(6),
                        res.getDouble(7), res.getString(3));
                list_sale.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_sale;
    }
    public ObservableList<Sale> SalesByClient(String id) {
        String req = "select * from sale where ClientID = '"+id+"'";
        ObservableList<Sale> list_sale = FXCollections.observableArrayList();
        try {
            res = stmt.executeQuery(req);
            while (res.next()) {
                Sale s = new Sale(res.getString(1), res.getString(2), res.getString(4), res.getTimestamp(5), res.getInt(6),
                        res.getDouble(7), res.getString(3));
                list_sale.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_sale;
    }

    public ObservableList<Sale> salesByDate(String date1,String date2,String id) {
        String req = "select * from sale where SaleDate between '"+date1+"' and '"+date2+"' and ClientID = '"+id+"'";
        System.out.println(req);
        ObservableList<Sale> list_sale = FXCollections.observableArrayList();
        try {
            res = stmt.executeQuery(req);
            while (res.next()) {
                Sale s = new Sale(res.getString(1), res.getString(2), res.getString(4), res.getTimestamp(5), res.getInt(6),
                        res.getDouble(7), res.getString(3));
                list_sale.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_sale;
    }

    public ObservableList<Sale> salesByDateForEmployee(String date1,String date2,String id) {
        String req = "select * from sale where SaleDate between '"+date1+"' and '"+date2+"' and ProductID = '"+id+"'";
        System.out.println(req);
        ObservableList<Sale> list_sale = FXCollections.observableArrayList();
        try {
            res = stmt.executeQuery(req);
            while (res.next()) {
                Sale s = new Sale(res.getString(1), res.getString(2), res.getString(4), res.getTimestamp(5), res.getInt(6),
                        res.getDouble(7), res.getString(3));
                list_sale.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_sale;
    }
}
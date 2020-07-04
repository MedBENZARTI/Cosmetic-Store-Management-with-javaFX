package Interfaces;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import Classes.Client;
import Classes.Employee;
import Classes.Product;
import Classes.Sale;
import Connect.Connector;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

class VenteAlertboxes {
    private Scene s;
    Product p;
    Connector con = new Connector();
    ChoiceBox<String> choice;

    public VenteAlertboxes() {

    }

    // ------THE ADD BUTTON-------------
    // ---------------------------------
    public void ajouter(TableView<Sale> tablesales, TableView<Client> tableclient, TableView<Product> tabprod) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Ajouter");
        window.setMinWidth(250);
        VBox v = new VBox();
        v.getChildren().add(addTitle("Nouvelle vente"));

        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setHgap(10);
        g.setVgap(8);

        // employee label
        Label l1 = new Label("Employé");
        GridPane.setConstraints(l1, 0, 0);
        // employee input
        ChoiceBox<String> employee = new ChoiceBox<>(con.employeeToString());
        GridPane.setConstraints(employee, 1, 0);

        // client label
        Label l2 = new Label("Client");
        GridPane.setConstraints(l2, 0, 1);
        // client input
        ChoiceBox<String> choice = new ChoiceBox<>(con.clientToString());
        Button addclient = new Button("Ajouter");
        addclient.setOnAction(e -> {
            ClientAlertboxes cl = new ClientAlertboxes();
            cl.ajouter(tableclient, choice);
        });
        HBox clientpart = new HBox(choice, addclient);
        clientpart.setSpacing(8);
        GridPane.setConstraints(clientpart, 1, 1);

        // product label
        Label l3 = new Label("Produit");
        GridPane.setConstraints(l3, 0, 2);
        // product input
        ChoiceBox<String> product = new ChoiceBox<>(con.prodToString());
        GridPane.setConstraints(product, 1, 2);

        // qte label
        Label l4 = new Label("Quantité");
        GridPane.setConstraints(l4, 0, 3);
        // qte input
        TextField qte = new TextField();
        qte.setPromptText("3");
        GridPane.setConstraints(qte, 1, 3);

        g.getChildren().addAll(l1, employee, l2, clientpart, l3, product, l4, qte);

        // Buttons
        /////////
        Button leftb = new Button("Annuler");
        leftb.setOnAction(e -> window.close());
        Button rightb = new Button("Confirmer");
        rightb.setOnAction(e -> {
            Employee emp = con.getEmployeeByID(IDfromData(employee.getValue()));
            Client cli = con.getClientByID(IDfromData(choice.getValue()));
            Product prod = con.getProdByID(IDfromData(product.getValue()));
            int qtee = Integer.parseInt(qte.getText());
            if (qtee > prod.getQte()) {
                Alert al = new Alert(AlertType.WARNING);
                al.setTitle("WARNING");
                al.setHeaderText("quantité insuffisante");
                al.setContentText("Il ne reste que " + prod.getQte() + " pièce(s)");
                al.show();
            } else {
                Timestamp t = new Timestamp(newdate());
                Sale s = new Sale(cli.getID(), newID(), prod.getProductID(), t, qtee, qtee * prod.getSellingPrice(),
                        emp.getID());
                con.addSale(s);
                tablesales.getItems().add(s);
                Product newproduct = new Product(prod.getProductID(), prod.getProductName(), prod.getBuyingPrice(),
                        prod.getSellingPrice(), prod.getProductCat(), prod.getMark(), prod.getQte());
                newproduct.setQte(prod.getQte() - qtee);
                con.deleteProduct(prod);
                con.addPorduct(newproduct);
                tabprod.getItems().clear();
                tabprod.setItems(con.AllProducts());
                window.close();
            }

        });

        HBox b = new HBox(leftb, rightb);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.setSpacing(20);
        b.setAlignment(Pos.CENTER_RIGHT);
        v.getChildren().addAll(g, b);
        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(20, 10, 10, 10));
        v.setSpacing(15);

        Scene s = new Scene(v);
        window.setScene(s);
        window.show();

    }

    private String newID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, uuid.indexOf('-'));
    }

    public Text addTitle(String title) {
        Text t = new Text(title);
        t.setFont(Font.font("Arial", 18));
        return t;
    }

    public ChoiceBox<String> returnchoice() {
        return this.choice;
    }

    public void puttchoice(ChoiceBox<String> ch) {
        this.choice = ch;
    }

    public String IDfromData(String data) {
        return data.substring(0, data.indexOf(' '));
    }

    public Long newdate() {
        Date date = new Date();
        return date.getTime();
    }

    public String dateFromTimestamp(Timestamp time) {
        String date = time.toString();
        return date.substring(0, date.indexOf(' '));
    }

    public String dateToFullDate(String date) {
        return date + " 00:00:00";
    }

    public void details(Sale sale) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Details");
        window.setMinWidth(250);
        VBox v = new VBox(addTitle("Détails sur cette vente"));

        // the filtre
        ////////////

        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setHgap(10);
        g.setVgap(8);

        // client label
        Label l0 = new Label("Client");
        GridPane.setConstraints(l0, 0, 0);
        // datemin input
        TextField client = new TextField(con.getClientByID(sale.getClientID()).getFirstName() + " "
                + con.getClientByID(sale.getClientID()).getLastName());
        client.setDisable(true);
        GridPane.setConstraints(client, 1, 0);

        // Product label
        Label l1 = new Label("Produit ");
        GridPane.setConstraints(l1, 0, 1);
        Product p = con.getProdByID(sale.getEmployeeID());
        // Product input
        TextField product = new TextField(p.getProductName() + " | " + p.getMark());
        product.setDisable(true);
        GridPane.setConstraints(product, 1, 1);

        // Employee label
        Label l2 = new Label("Employé ");
        GridPane.setConstraints(l2, 0, 2);
        Employee em = con.getEmployeeByID(sale.getProductID());
        // Employee input
        TextField employee = new TextField(em.getFirstName() + " " + em.getLastName());
        employee.setDisable(true);
        GridPane.setConstraints(employee, 1, 2);
        // add to gridpane
        g.getChildren().addAll(l0, client, l1, product, l2, employee);
        Button retour = new Button("Retour");
        retour.setOnAction(e -> window.close());

        HBox b = new HBox(retour);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.setSpacing(20);
        b.setAlignment(Pos.CENTER_RIGHT);

        v.getChildren().addAll(g, b);
        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(20, 10, 10, 10));
        v.setSpacing(15);
        Scene s = new Scene(v);
        window.setScene(s);
        window.show();
    }

    public void details1(Sale sale) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Details");
        window.setMinWidth(250);
        VBox v = new VBox(addTitle("Détails sur cette vente"));

        // the filtre
        ////////////

        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setHgap(10);
        g.setVgap(8);

        // client label
        Label l0 = new Label("Client");
        GridPane.setConstraints(l0, 0, 0);
        // datemin input
        TextField client = new TextField(con.getClientByID(sale.getClientID()).getFirstName() + " "
                + con.getClientByID(sale.getClientID()).getLastName());
        client.setDisable(true);
        GridPane.setConstraints(client, 1, 0);

        // Product label
        Label l1 = new Label("Produit ");
        GridPane.setConstraints(l1, 0, 1);
        Product p = con.getProdByID(sale.getProductID());
        // Product input
        TextField product = new TextField(p.getProductName() + " | " + p.getMark());
        product.setDisable(true);
        GridPane.setConstraints(product, 1, 1);

        // Employee label
        Label l2 = new Label("Employé ");
        GridPane.setConstraints(l2, 0, 2);
        Employee em = con.getEmployeeByID(sale.getEmployeeID());
        // Employee input
        TextField employee = new TextField(em.getFirstName() + " " + em.getLastName());
        employee.setDisable(true);
        GridPane.setConstraints(employee, 1, 2);
        // add to gridpane
        g.getChildren().addAll(l0, client, l1, product, l2, employee);
        Button retour = new Button("Retour");
        retour.setOnAction(e -> window.close());

        HBox b = new HBox(retour);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.setSpacing(20);
        b.setAlignment(Pos.CENTER_RIGHT);

        v.getChildren().addAll(g, b);
        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(20, 10, 10, 10));
        v.setSpacing(15);
        Scene s = new Scene(v);
        window.setScene(s);
        window.show();
    }
}

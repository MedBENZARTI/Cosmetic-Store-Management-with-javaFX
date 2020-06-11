package Interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Classes.Client;
import Classes.Product;
import Classes.Sale;
import Connect.Connector;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

class ClientAlertboxes {
    private Scene s;
    Product p;
    Connector con = new Connector();

    public ClientAlertboxes() {
    }

    public void ajouter(TableView<Client> table, ChoiceBox<String> choice) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Ajouter");
        window.setMinWidth(250);
        VBox v = new VBox();
        v.getChildren().add(addTitle("Nouveau Client"));

        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setHgap(10);
        g.setVgap(8);

        // firstname label
        Label l1 = new Label("Prénom");
        GridPane.setConstraints(l1, 0, 0);
        // firstname input
        TextField firstname = new TextField();
        firstname.setPromptText("Mohamed");
        GridPane.setConstraints(firstname, 1, 0);

        // lastname label
        Label l2 = new Label("Nom");
        GridPane.setConstraints(l2, 0, 1);
        // bprice input
        TextField lastname = new TextField();
        lastname.setPromptText("BENZARTI");
        GridPane.setConstraints(lastname, 1, 1);

        // phoneNbr label
        Label l3 = new Label("Numéro de tel");
        GridPane.setConstraints(l3, 0, 2);
        // phoneNbr input
        TextField phoneNbr = new TextField();
        phoneNbr.setPromptText("54 xxx xxx");
        GridPane.setConstraints(phoneNbr, 1, 2);

        // add to gridpane
        g.getChildren().addAll(l1, firstname, l2, lastname, l3, phoneNbr);

        //// add buttons
        Button leftb = new Button("Annuler");
        leftb.setOnAction(e -> window.close());
        Button rightb = new Button("Confirmer");
        rightb.setOnAction(e -> {
            Client c = new Client(newID(), firstname.getText(), lastname.getText(),
                    Integer.parseInt(phoneNbr.getText()));
            table.getItems().add(c);
            con.addClient(c);
            choice.setItems(con.clientToString());
            window.close();
        });

        HBox b = new HBox(leftb, rightb);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.setSpacing(20);
        b.setAlignment(Pos.CENTER_RIGHT);

        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(20, 10, 10, 10));
        v.setSpacing(15);
        v.getChildren().addAll(g, b);

        Scene s = new Scene(v);
        window.setScene(s);
        window.show();
    }

    public void refresh(Client old, Client neew, TableView<Client> tab, Connector c) {
        tab.getItems().remove(old);
        tab.getItems().add(neew);
        c.deleteClient(old);
        c.addClient(neew);
    }

    // ------THE MODIFY BUTTON-------------
    // ------------------------------------
    public void modifier(Client selectedclient, TableView<Client> table) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Modifier");
        window.setMinWidth(250);

        VBox v = new VBox(addTitle("Modifier client"));

        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setHgap(10);
        g.setVgap(8);

        // id label
        Label l0 = new Label("ID");
        GridPane.setConstraints(l0, 0, 0);
        // id input
        TextField id = new TextField(selectedclient.getID());
        id.setDisable(true);
        GridPane.setConstraints(id, 1, 0);

        // firstname label
        Label l1 = new Label("Prénom");
        GridPane.setConstraints(l1, 0, 1);
        // firstname input
        TextField firstname = new TextField(selectedclient.getFirstName());
        firstname.setPromptText("Mohamed");
        GridPane.setConstraints(firstname, 1, 1);

        // lastname label
        Label l2 = new Label("Nom");
        GridPane.setConstraints(l2, 0, 2);
        // bprice input
        TextField lastname = new TextField(selectedclient.getLastName());
        lastname.setPromptText("BENZARTI");
        GridPane.setConstraints(lastname, 1, 2);

        // phoneNbr label
        Label l3 = new Label("Numéro de tel");
        GridPane.setConstraints(l3, 0, 3);
        // phoneNbr input
        TextField phoneNbr = new TextField(String.valueOf(selectedclient.getPhoneNbr()));
        phoneNbr.setPromptText("54 xxx xxx");
        GridPane.setConstraints(phoneNbr, 1, 3);

        // add to gridpane
        g.getChildren().addAll(l0, id, l1, firstname, l2, lastname, l3, phoneNbr);

        //// add buttons
        Button leftb = new Button("Annuler");
        leftb.setOnAction(e -> window.close());
        Button rightb = new Button("Confirmer");
        rightb.setOnAction(e -> {
            Client neew = new Client(newID(), firstname.getText(), lastname.getText(),
                    Integer.parseInt(phoneNbr.getText()));
            refresh(selectedclient, neew, table, con);
            window.close();
        });

        HBox b = new HBox(leftb, rightb);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.setSpacing(20);
        b.setAlignment(Pos.CENTER_RIGHT);

        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(20, 10, 10, 10));
        v.setSpacing(15);
        v.getChildren().addAll(g, b);

        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(20, 10, 10, 10));
        v.setSpacing(15);
        Scene s = new Scene(v);
        window.setScene(s);
        window.show();
    }

    // ------THE DETAILS BUTTON---------
    // ---------------------------------
    public void consulter(Client selectedClient) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Consulter");
        window.setMinWidth(250);

        VBox v = new VBox(addTitle("Les Ventes selon une date"));

        // the filtre
        ////////////

        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setHgap(10);
        g.setVgap(8);

        // date label
        Label l0 = new Label("Date entre: ");
        GridPane.setConstraints(l0, 0, 0);
        // datemin input
        TextField datemin = new TextField();
        datemin.setPromptText("aaaa-mm-jj");
        GridPane.setConstraints(datemin, 1, 0);

        Label l1 = new Label("et ");
        GridPane.setConstraints(l1, 2, 0);

        // datemax input
        TextField datemax = new TextField();
        datemax.setPromptText("aaaa-mm-jj");
        GridPane.setConstraints(datemax, 3, 0);
        Button find = new Button("Chercher");
        GridPane.setConstraints(find, 4, 0);

        // add to gridpane
        g.getChildren().addAll(l0, datemin, l1, datemax, find);
        //// the table
        /////////////
        TableView<Sale> tab = new TableView<>();
        TableColumn<Sale, String> saleColumn = new TableColumn<>("Vente ID");
        saleColumn.setPrefWidth(120);
        saleColumn.setCellValueFactory(new PropertyValueFactory<>("SaleID"));

        TableColumn<Sale, String> clienColumn = new TableColumn<>("Client ID");
        clienColumn.setPrefWidth(120);
        clienColumn.setCellValueFactory(new PropertyValueFactory<>("ClientID"));

        TableColumn<Sale, String> produitColumn = new TableColumn<>("Produit ID");
        produitColumn.setPrefWidth(150);
        produitColumn.setCellValueFactory(new PropertyValueFactory<>("ProductID"));

        TableColumn<Sale, String> employeeColumn = new TableColumn<>("Employee ID");
        employeeColumn.setPrefWidth(150);
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));

        TableColumn<Sale, String> dateColumn = new TableColumn<>("Date du vente");
        dateColumn.setPrefWidth(120);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("SaleDate"));

        TableColumn<Sale, String> qteColumn = new TableColumn<>("Quantité");
        qteColumn.setPrefWidth(120);
        qteColumn.setCellValueFactory(new PropertyValueFactory<>("Qte"));

        TableColumn<Sale, String> priceColumn = new TableColumn<>("Prix total");
        priceColumn.setPrefWidth(120);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("TotalePrice"));

        tab.setItems(null);
        tab.getColumns().addAll(saleColumn, clienColumn, produitColumn, employeeColumn, dateColumn, qteColumn,
                priceColumn);

        find.setOnAction(e -> {
            String date1, date2;
            if (datemin.getText().isEmpty()) {
                date1 = dateToFullDate("2000-01-01");
            } else {
                date1 = dateToFullDate(datemin.getText());
            }
            if (datemax.getText().isEmpty()) {
                date2 = dateToFullDate("2100-01-01 23:59:59");
            } else {
                date2 = datemax.getText()+" 23:59:59";
            }
            tab.setItems(con.salesByDate(date1, date2,selectedClient.getID()));
        });
        Button ok = new Button("Ok");
        ok.setOnAction(e -> window.close());
        Button detail = new Button("Détails");
        detail.setOnAction(e->{
            VenteAlertboxes vab = new VenteAlertboxes();
            vab.details(tab.getSelectionModel().getSelectedItem());
        });
        HBox b = new HBox(detail,ok);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.setSpacing(20);
        b.setAlignment(Pos.CENTER_RIGHT);

        v.getChildren().addAll(g, tab, b);
        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(20, 10, 10, 10));
        v.setSpacing(15);
        Scene s = new Scene(v);
        window.setScene(s);
        window.show();
    }

    public Text addTitle(String title) {
        Text t = new Text(title);
        t.setFont(Font.font("Arial", 18));
        return t;
    }

    private String newID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, uuid.indexOf('-'));
    }

    public Scene getS() {
        return s;
    }

    public void setS(Scene s) {
        this.s = s;
    }

    public String dateToFullDate(String date) {
        return date + " 00:00:00";
    }
}

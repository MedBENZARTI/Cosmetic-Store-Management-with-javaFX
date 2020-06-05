package Interfaces;

import java.util.UUID;
import Classes.Product;
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

class EmployeeAlertboxes {
    private Scene s;
    Product p;

    public EmployeeAlertboxes() {
    }

    public Product ajouter(TableView <Product> table) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Ajouter");
        window.setMinWidth(250);
        VBox v = new VBox();
        v.getChildren().add(addTitle("Nouveau Produit"));

        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setHgap(10);
        g.setVgap(8);

        // Name label
        Label l1 = new Label("Nom");
        g.setConstraints(l1, 0, 0);
        // Name input
        TextField name = new TextField();
        name.setPromptText("Savons déodorants");
        g.setConstraints(name, 1, 0);

        // bprice label
        Label l2 = new Label("Prix d'achat");
        g.setConstraints(l2, 0, 1);
        // bprice input
        TextField bprice = new TextField();
        bprice.setPromptText("12.0");
        g.setConstraints(bprice, 1, 1);

        // sprice label
        Label l3 = new Label("Prix de Vente");
        g.setConstraints(l3, 0, 2);
        // sprice input
        TextField sprice = new TextField();
        sprice.setPromptText("16.0");
        g.setConstraints(sprice, 1, 2);

        // category label
        Label l4 = new Label("Catégorie");
        g.setConstraints(l4, 0, 3);
        // category input
        TextField category = new TextField();
        category.setPromptText("Produits d’hygiène");
        g.setConstraints(category, 1, 3);

        // mark label
        Label l5 = new Label("Marque");
        g.setConstraints(l5, 0, 4);
        // mark input
        TextField mark = new TextField();
        mark.setPromptText("ARVEA");
        g.setConstraints(mark, 1, 4);

        // Qte label
        Label l6 = new Label("Quantité");
        g.setConstraints(l6, 0, 5);
        // Qte input
        TextField Qte = new TextField();
        Qte.setPromptText("10");
        g.setConstraints(Qte, 1, 5);

        // add to gridpane
        g.getChildren().addAll(l1, name, l2, bprice, l3, sprice, l4, category, l5, mark, l6, Qte);

        //// add buttons
        Button leftb = new Button("Annuler");
        leftb.setOnAction(e -> window.close());
        Button rightb = new Button("Confirmer");
        rightb.setOnAction(e -> {

            String a1 = name.getText();
            Double b1 = Double.parseDouble(bprice.getText());
            Double c1 = Double.parseDouble(sprice.getText());
            String d1 = category.getText();
            String e1 = mark.getText();
            int f1 = Integer.parseInt(Qte.getText());
            p = new Product(newID(), a1, b1, c1, d1, e1, f1);
            Connector c = new Connector();
            c.addPorduct(p);
            table.getItems().add(p);
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
        return p;
    }

    public Product getprod(){
        return this.p;
    }

    public void modifier() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Modifier");
        window.setMinWidth(250);

        VBox v = new VBox(addTitle("Modifier Produit"), addContentmod(),
                addButsmodifier("Annuler", "Confirmer", window));
        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(20, 10, 10, 10));
        v.setSpacing(15);
        Scene s = new Scene(v);
        window.setScene(s);
        window.show();
    }

    public void remise() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Remise");
        window.setMinWidth(250);

        VBox v = new VBox(addTitle("Remise"), addContentrem(), addButsremise("Annuler", "Confirmer", window));
        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(20, 10, 10, 10));
        v.setSpacing(15);
        Scene s = new Scene(v);
        window.setScene(s);
        window.show();
    }

    public void chercher() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Chercher");
        window.setMinWidth(250);

        VBox v = new VBox(addTitle("Chercher des produits"), addFiltre(), addContentcher(),
                addButschercher("Retour", "Chercher", window));
        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(20, 10, 10, 10));
        v.setSpacing(15);
        Scene s = new Scene(v);
        window.setScene(s);
        window.show();
    }

    public Scene getS() {
        return s;
    }

    public void setS(Scene s) {
        this.s = s;
    }

    public HBox addButsmodifier(String left, String right, Stage window) {
        Button leftb = new Button(left);
        leftb.setOnAction(e -> window.close());
        Button rightb = new Button(right);
        HBox b = new HBox(leftb, rightb);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.setSpacing(20);
        b.setAlignment(Pos.CENTER_RIGHT);
        return b;
    }

    public HBox addButsremise(String left, String right, Stage window) {
        Button leftb = new Button(left);
        leftb.setOnAction(e -> window.close());
        Button rightb = new Button(right);
        HBox b = new HBox(leftb, rightb);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.setSpacing(20);
        b.setAlignment(Pos.CENTER_RIGHT);
        return b;
    }

    public HBox addButschercher(String left, String right, Stage window) {
        Button leftb = new Button(left);
        leftb.setOnAction(e -> window.close());
        Button rightb = new Button(right);
        HBox b = new HBox(leftb, rightb);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.setSpacing(20);
        b.setAlignment(Pos.CENTER_RIGHT);
        return b;
    }

    public GridPane addFiltre() {
        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setHgap(10);
        g.setVgap(8);

        // Name label
        Label l0 = new Label("Nom");
        g.setConstraints(l0, 0, 0);
        // Name input
        TextField name = new TextField();
        name.setPromptText("avons déodorants");
        g.setConstraints(name, 1, 0);

        // minprice label
        Label l1 = new Label("Prix min");
        g.setConstraints(l1, 0, 1);
        // minprice input
        TextField minprice = new TextField();
        minprice.setPromptText("10.0");
        g.setConstraints(minprice, 1, 1);

        // maxprice label
        Label l2 = new Label("Prix Max");
        g.setConstraints(l2, 0, 2);
        // maxprice input
        TextField Maxprice = new TextField();
        Maxprice.setPromptText("100.0");
        g.setConstraints(Maxprice, 1, 2);

        // maxprice label
        Label l3 = new Label("Catégorie");
        g.setConstraints(l3, 0, 3);
        // maxprice input
        TextField categ = new TextField();
        categ.setPromptText("ARVEA");
        g.setConstraints(categ, 1, 3);

        // add to gridpane
        g.getChildren().addAll(l0, name, l1, minprice, l2, Maxprice, l3, categ);

        return g;
    }

    public Text addTitle(String title) {
        Text t = new Text(title);
        t.setFont(Font.font("Arial", 18));
        return t;
    }

    public GridPane addContentmod() {
        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setHgap(10);
        g.setVgap(8);

        // id label
        Label l0 = new Label("ID");
        g.setConstraints(l0, 0, 0);
        // id input
        TextField id = new TextField();
        id.setPromptText("123e4567");
        g.setConstraints(id, 1, 0);

        // Name label
        Label l1 = new Label("Nom");
        g.setConstraints(l1, 0, 1);
        // Name input
        TextField name = new TextField();
        name.setPromptText("Savons déodorants");
        g.setConstraints(name, 1, 1);

        // bprice label
        Label l2 = new Label("Prix d'achat");
        g.setConstraints(l2, 0, 2);
        // bprice input
        TextField bprice = new TextField();
        bprice.setPromptText("12.0");
        g.setConstraints(bprice, 1, 2);

        // sprice label
        Label l3 = new Label("Prix de Vente");
        g.setConstraints(l3, 0, 3);
        // sprice input
        TextField sprice = new TextField();
        sprice.setPromptText("16.0");
        g.setConstraints(sprice, 1, 3);

        // category label
        Label l4 = new Label("Catégorie");
        g.setConstraints(l4, 0, 4);
        // category input
        TextField category = new TextField();
        category.setPromptText("Produits d’hygiène");
        g.setConstraints(category, 1, 4);

        // mark label
        Label l5 = new Label("Marque");
        g.setConstraints(l5, 0, 5);
        // mark input
        TextField mark = new TextField();
        mark.setPromptText("ARVEA");
        g.setConstraints(mark, 1, 5);

        // Qte label
        Label l6 = new Label("Quantité");
        g.setConstraints(l6, 0, 6);
        // Qte input
        TextField Qte = new TextField();
        Qte.setPromptText("10");
        g.setConstraints(Qte, 1, 6);

        // add to gridpane
        g.getChildren().addAll(l0, id, l1, name, l2, bprice, l3, sprice, l4, category, l5, mark, l6, Qte);

        return g;
    }

    public GridPane addContentrem() {
        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setHgap(10);
        g.setVgap(8);

        // Name label
        Label l0 = new Label("ID");
        g.setConstraints(l0, 0, 0);
        // Name input
        TextField id = new TextField();
        id.setPromptText("123e4567");
        g.setConstraints(id, 1, 0);

        // Name label
        Label l1 = new Label("Prix de Vente");
        g.setConstraints(l1, 0, 1);
        // Name input
        TextField price = new TextField();
        price.setPromptText("16.0");
        g.setConstraints(price, 1, 1);

        // bprice label
        Label l2 = new Label("Taux de remise (%)");
        g.setConstraints(l2, 0, 2);
        // bprice input
        ChoiceBox<Integer> remise = new ChoiceBox<>();
        remise.getItems().addAll(10, 20, 25, 30, 40, 50, 80);
        g.setConstraints(remise, 1, 2);

        // add to gridpane
        g.getChildren().addAll(l0, id, l1, price, l2, remise);

        return g;
    }

    public TableView<Product> makeTable(ObservableList<Product> lista) {
        TableColumn<Product, String> idColumn = new TableColumn<>("ID");
        idColumn.setPrefWidth(120);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ProductID"));

        TableColumn<Product, String> nameColumn = new TableColumn<>("Nom");
        nameColumn.setPrefWidth(120);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("ProductName"));

        TableColumn<Product, String> bpriceColumn = new TableColumn<>("Prix d'Achat");
        bpriceColumn.setPrefWidth(150);
        bpriceColumn.setCellValueFactory(new PropertyValueFactory<>("BuyingPrice"));

        TableColumn<Product, String> spriceColumn = new TableColumn<>("Prix de Vente");
        spriceColumn.setPrefWidth(150);
        spriceColumn.setCellValueFactory(new PropertyValueFactory<>("SellingPrice"));

        TableColumn<Product, String> catColumn = new TableColumn<>("Categorie");
        catColumn.setPrefWidth(120);
        catColumn.setCellValueFactory(new PropertyValueFactory<>("ProductCat"));

        TableColumn<Product, String> markColumn = new TableColumn<>("Marque");
        markColumn.setPrefWidth(120);
        markColumn.setCellValueFactory(new PropertyValueFactory<>("Mark"));

        TableColumn<Product, String> qteColumn = new TableColumn<>("Quantité");
        qteColumn.setPrefWidth(120);
        qteColumn.setCellValueFactory(new PropertyValueFactory<>("Qte"));

        TableView<Product> table = new TableView<>();
        table.setItems(lista);
        table.getColumns().addAll(idColumn, nameColumn, bpriceColumn, spriceColumn, catColumn, markColumn, qteColumn);
        return table;
    }

    public VBox addContentcher() {
        VBox v = new VBox(makeTable(null));
        return v;
    }

    ////////// connexion with DB
    private String newID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, uuid.indexOf('-'));
    }

}

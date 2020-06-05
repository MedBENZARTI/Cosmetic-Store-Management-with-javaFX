package Interfaces;

import java.util.UUID;

import Classes.Category;
import Classes.Product;
import Connect.Connector;
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

class ProductAlertboxes {
    private Scene s;
    Product p;

    public ProductAlertboxes() {
    }

    // ------THE ADD BUTTON-------------
    // ---------------------------------
    public void ajouter(TableView<Product> table,TableView<Category> tablecateg) {
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
        GridPane.setConstraints(l1, 0, 0);
        // Name input
        TextField name = new TextField();
        name.setPromptText("Savons déodorants");
        GridPane.setConstraints(name, 1, 0);

        // bprice label
        Label l2 = new Label("Prix d'achat");
        GridPane.setConstraints(l2, 0, 1);
        // bprice input
        TextField bprice = new TextField();
        bprice.setPromptText("12.0");
        GridPane.setConstraints(bprice, 1, 1);

        // sprice label
        Label l3 = new Label("Prix de Vente");
        GridPane.setConstraints(l3, 0, 2);
        // sprice input
        TextField sprice = new TextField();
        sprice.setPromptText("16.0");
        GridPane.setConstraints(sprice, 1, 2);

        // category label
        Label l4 = new Label("Catégorie");
        GridPane.setConstraints(l4, 0, 3);
        // category input
        TextField category = new TextField();
        category.setPromptText("Produits d’hygiène");
        GridPane.setConstraints(category, 1, 3);

        // mark label
        Label l5 = new Label("Marque");
        GridPane.setConstraints(l5, 0, 4);
        // mark input
        TextField mark = new TextField();
        mark.setPromptText("ARVEA");
        GridPane.setConstraints(mark, 1, 4);

        // Qte label
        Label l6 = new Label("Quantité");
        GridPane.setConstraints(l6, 0, 5);
        // Qte input
        TextField Qte = new TextField();
        Qte.setPromptText("10");
        GridPane.setConstraints(Qte, 1, 5);

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
            Category cat =new Category(p.getProductCat(), newID());
            c.addCategory(cat);
            tablecateg.getItems().add(cat);
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
    }

    public Product getprod() {
        return this.p;
    }

    // ------THE MODIFY BUTTON-------------
    // ------------------------------------
    public void modifier(TableView<Product> table, Product SelectedProd) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Modifier");
        window.setMinWidth(250);

        VBox modifylayout = new VBox(addTitle("Modifier Produit"));

        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setHgap(10);
        g.setVgap(8);

        // id label
        Label l0 = new Label("ID");
        GridPane.setConstraints(l0, 0, 0);
        // id input
        TextField id = new TextField(SelectedProd.getProductID());
        id.setDisable(true);
        GridPane.setConstraints(id, 1, 0);

        // Name label
        Label l1 = new Label("Nom");
        GridPane.setConstraints(l1, 0, 1);
        // Name input
        TextField name = new TextField();
        name.setText(SelectedProd.getProductName());
        GridPane.setConstraints(name, 1, 1);

        // bprice label
        Label l2 = new Label("Prix d'achat");
        GridPane.setConstraints(l2, 0, 2);
        // bprice input
        TextField bprice = new TextField();
        bprice.setText(SelectedProd.getBuyingPrice().toString());
        GridPane.setConstraints(bprice, 1, 2);

        // sprice label
        Label l3 = new Label("Prix de Vente");
        GridPane.setConstraints(l3, 0, 3);
        // sprice input
        TextField sprice = new TextField();
        sprice.setText(SelectedProd.getSellingPrice().toString());
        GridPane.setConstraints(sprice, 1, 3);

        // category label
        Label l4 = new Label("Catégorie");
        GridPane.setConstraints(l4, 0, 4);
        // category input
        TextField category = new TextField();
        category.setText(SelectedProd.getProductCat());
        GridPane.setConstraints(category, 1, 4);

        // mark label
        Label l5 = new Label("Marque");
        GridPane.setConstraints(l5, 0, 5);
        // mark input
        TextField mark = new TextField();
        mark.setText(SelectedProd.getMark());
        GridPane.setConstraints(mark, 1, 5);

        // Qte label
        Label l6 = new Label("Quantité");
        GridPane.setConstraints(l6, 0, 6);
        // Qte input
        TextField Qte = new TextField();
        Qte.setText(String.valueOf(SelectedProd.getQte()));
        GridPane.setConstraints(Qte, 1, 6);

        // add to gridpane
        g.getChildren().addAll(l0, id, l1, name, l2, bprice, l3, sprice, l4, category, l5, mark, l6, Qte);

        //// making the buttons
        Button annuler = new Button("Annuler");
        annuler.setOnAction(e -> window.close());
        Button confirmer = new Button("Confirmer");
        confirmer.setOnAction(e -> {
            String a1 = name.getText();
            Double b1 = Double.parseDouble(bprice.getText());
            Double c1 = Double.parseDouble(sprice.getText());
            String d1 = category.getText();
            String e1 = mark.getText();
            int f1 = Integer.parseInt(Qte.getText());
            p = new Product(id.getText(), a1, b1, c1, d1, e1, f1);
            Connector c = new Connector();
            c.deleteProduct(SelectedProd);
            c.addPorduct(p);
            table.getItems().remove(SelectedProd);
            table.getItems().add(p);
            window.close();
        });
        HBox b = new HBox(annuler, confirmer);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.setSpacing(20);
        b.setAlignment(Pos.CENTER_RIGHT);

        /// making the layout
        modifylayout.getChildren().addAll(g, b);
        //// making the scene
        modifylayout.setAlignment(Pos.CENTER);
        modifylayout.setPadding(new Insets(20, 10, 10, 10));
        modifylayout.setSpacing(15);
        Scene s = new Scene(modifylayout);
        window.setScene(s);
        window.show();
    }

    // ------THE DISCOUNT BUTTON-----------
    // ------------------------------------
    public void remise(Product SelectedProd, TableView<Product> table) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Remise");
        window.setMinWidth(250);

        VBox discountlayout = new VBox(addTitle("Remise"));
        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setHgap(10);
        g.setVgap(8);

        // Name label
        Label l0 = new Label("ID");
        GridPane.setConstraints(l0, 0, 0);
        // Name input
        TextField id = new TextField();
        id.setText(SelectedProd.getProductID());
        id.setDisable(true);
        ;
        GridPane.setConstraints(id, 1, 0);

        // sellinprice label
        Label l1 = new Label("Prix de Vente");
        GridPane.setConstraints(l1, 0, 1);
        // sellinprice input
        TextField price = new TextField();
        // price.setPromptText("16.0");
        price.setText(SelectedProd.getSellingPrice().toString());
        price.setDisable(true);
        GridPane.setConstraints(price, 1, 1);

        // discount label
        Label l2 = new Label("Taux de remise (%)");
        GridPane.setConstraints(l2, 0, 2);
        // discount input
        ChoiceBox<Integer> remise = new ChoiceBox<>();
        remise.getItems().addAll(10, 20, 25, 30, 40, 50, 80);
        Button test = new Button("Test");
        test.setMinWidth(60);
        TextField result = new TextField(SelectedProd.getSellingPrice().toString());
        result.setDisable(true);
        HBox actions = new HBox(remise, test, result);
        actions.setSpacing(8);
        test.setOnAction(e -> {
            Double preview = discount(Double.parseDouble(price.getText()),
                    Double.parseDouble(remise.getValue().toString()));
            result.setText(preview.toString());
        });
        GridPane.setConstraints(actions, 1, 2);

        // add to gridpane
        g.getChildren().addAll(l0, id, l1, price, l2, actions);

        Button annuler = new Button("Annuler");
        annuler.setOnAction(e -> window.close());
        Button confirmer = new Button("Confirmer");
        confirmer.setOnAction(e -> {
            Double resultprice = Double.parseDouble(result.getText());
            Connector con = new Connector();
            table.getItems().remove(SelectedProd);
            con.makeDiscount(id.getText(), resultprice);
            Product newprod = con.getProdByID(id.getText());
            table.getItems().add(newprod);
            window.close();
        });
        HBox b = new HBox(annuler, confirmer);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.setSpacing(20);
        b.setAlignment(Pos.CENTER_RIGHT);
        /// making the layout
        discountlayout.getChildren().addAll(g, b);
        discountlayout.setAlignment(Pos.CENTER);
        discountlayout.setPadding(new Insets(20, 10, 10, 10));
        discountlayout.setSpacing(15);
        Scene s = new Scene(discountlayout);
        window.setScene(s);
        window.show();
    }

    public Double discount(Double d, Double r) {
        Double res = d * (1 - (r / 100));
        return res;
    }

    // ------THE FIND BUTTON-----------
    // --------------------------------
    public void chercher() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Chercher");
        window.setMinWidth(250);

        VBox v = new VBox(addTitle("Chercher des produits"));
        //the filtre
        ////////////

        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setHgap(10);
        g.setVgap(8);

        // Name label
        Label l0 = new Label("Nom");
        GridPane.setConstraints(l0, 0, 0);
        // Name input
        TextField name = new TextField();
        name.setPromptText("Savons déodorants");
        GridPane.setConstraints(name, 1, 0);

        // minprice label
        Label l1 = new Label("Prix min");
        GridPane.setConstraints(l1, 0, 1);
        // minprice input
        TextField minprice = new TextField();
        minprice.setPromptText("10.0");
        GridPane.setConstraints(minprice, 1, 1);

        // maxprice label
        Label l2 = new Label("Prix Max");
        GridPane.setConstraints(l2, 0, 2);
        // maxprice input
        TextField Maxprice = new TextField();
        Maxprice.setPromptText("100.0");
        GridPane.setConstraints(Maxprice, 1, 2);

        // add to gridpane
        g.getChildren().addAll(l0, name, l1, minprice, l2, Maxprice);

        //making the table
        /////////////////
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

        TableView<Product> tab = new TableView<>();
        tab.setItems(null);
        tab.getColumns().addAll(idColumn, nameColumn, bpriceColumn, spriceColumn, catColumn, markColumn, qteColumn);

        //the buttons
        /////////////
        Button retour = new Button("Retour");
        retour.setOnAction(e -> window.close());
        Button chercher = new Button("Chercher");
        chercher.setOnAction(e->{
            Connector con = new Connector();
            Double minpriceprod;
            Double maxpriceprod;
            if (minprice.getText().isEmpty()) {
                minpriceprod = 0.0;
            } else {
                minpriceprod =  Double.parseDouble(minprice.getText());
            }

            if (Maxprice.getText().isEmpty()) {
                maxpriceprod = 999999999.0;
            } else {
                maxpriceprod =  Double.parseDouble(Maxprice.getText());
            }

            if (name.getText().isEmpty()) {
                tab.setItems(con.Filtrewithoutname(minpriceprod, maxpriceprod));
            } else {
                tab.setItems(con.Filtrewithname(name.getText(), minpriceprod, maxpriceprod));
            }
            

        });
        HBox b = new HBox(retour, chercher);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.setSpacing(20);
        b.setAlignment(Pos.CENTER_RIGHT);

        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(20, 10, 10, 10));
        v.setSpacing(15);
        v.getChildren().addAll(g,tab,b);
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

    public Text addTitle(String title) {
        Text t = new Text(title);
        t.setFont(Font.font("Arial", 18));
        return t;
    }

    private String newID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, uuid.indexOf('-'));
    }

}

package Interfaces;

import java.util.UUID;

import Classes.Category;
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

class CategoryAlertboxes {
    private Scene s;
    Category c;
    Connector con = new Connector();

    public CategoryAlertboxes() {
    }

    // ------THE ADD BUTTON-------------
    // ---------------------------------
    public void ajouter(TableView<Category> table) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Ajouter");
        window.setMinWidth(250);
        VBox v = new VBox();
        v.getChildren().add(addTitle("Nouvelle Categorie"));

        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setHgap(10);
        g.setVgap(8);

        // Name label
        Label l1 = new Label("Nom");
        GridPane.setConstraints(l1, 0, 0);
        // Name input
        TextField name = new TextField();
        name.setPromptText("Produits d’hygiène");
        GridPane.setConstraints(name, 1, 0);

        // add to gridpane
        g.getChildren().addAll(l1, name);

        //// add buttons
        Button leftb = new Button("Annuler");
        leftb.setOnAction(e -> window.close());
        Button rightb = new Button("Confirmer");
        rightb.setOnAction(e -> {

            String a1 = name.getText();
            c = new Category(a1, newID());
            con.addCategory(c);
            table.getItems().add(c);
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

    public Category getcateg() {
        return this.c;
    }

    // ------THE DETAILS BUTTON---------
    // ---------------------------------
    public void consulter(TableView<Category> table) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Consulter");
        window.setMinWidth(250);

        VBox v = new VBox(addTitle("Les produits de cette catégorie"));

        // making the table
        ///////////////////
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
        tab.setItems(con.getProductByCategory(table.getSelectionModel().getSelectedItem().getCatName()));
        tab.getColumns().addAll(idColumn, nameColumn, bpriceColumn, spriceColumn, catColumn, markColumn, qteColumn);

        Button ok = new Button("Ok");
        ok.setOnAction(e -> window.close());
        HBox b = new HBox(ok);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.setSpacing(20);
        b.setAlignment(Pos.CENTER_RIGHT);

        v.getChildren().addAll(tab, b);
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

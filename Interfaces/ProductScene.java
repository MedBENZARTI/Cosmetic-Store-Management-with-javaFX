package Interfaces;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Classes.Category;
import Classes.Product;
import Connect.Connector;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ProductScene {
    BorderPane border;
    Connector con;
    TableView<Product> table;
    ProductAlertboxes p ;

    public ProductScene(TableView<Category> t) throws Exception {
        con = new Connector();
        this.border = new BorderPane();
        table = new TableView<>();
        p = new ProductAlertboxes();
        // Top part
        HBox Top = new HBox();

        Top.setPadding(new Insets(15, 12, 15, 12));
        Top.setSpacing(350);
        Top.setStyle("-fx-background-color: #67AB9F;");

        FileInputStream input = new FileInputStream("Icons/exit.png");
        Image exitimage = new Image(input);
        ImageView exitimageView = new ImageView(exitimage);

        Text market = new Text("\n  TEK UP \nCosmetics");

        market.setFont(Font.font("Courgette", FontWeight.BOLD, 22));
        Text frame = new Text("\nProduit");
        frame.setFont(Font.font("Copperplate Gothic Bold", FontWeight.BOLD, 34));

        VBox logout = new VBox();
        logout.setSpacing(5);
        Text adrs = new Text("malek@tekup.de");
        adrs.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Button exitb = new Button("", exitimageView);
        exitb.setStyle("-fx-background-color: #67AB9F; ");
        exitb.setOnAction(e -> System.out.println("ouuh ouuh !!"));
        logout.getChildren().addAll(adrs, exitb);
        Top.getChildren().addAll(market, frame, logout);

        // Left part

        // Center part
        VBox Center = new VBox();

        // Table creation
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

        table.setItems(con.AllProducts());
        table.getColumns().addAll(idColumn, nameColumn, bpriceColumn, spriceColumn, catColumn, markColumn, qteColumn);

        // Add buttons
        HBox Buttons = new HBox();

        Button ajouter = new Button("Ajouter");
        ajouter.setOnAction(e -> {
            p.ajouter(table,t);

        });
        Button modifier = new Button("Modifier");
        modifier.setOnAction(e -> {
            Product prod = table.getSelectionModel().getSelectedItem();
            p.modifier(table,prod);
        });

        Button remise = new Button("Remise");
        remise.setOnAction(e -> {
            p.remise(table.getSelectionModel().getSelectedItem(),table);
        });

        Button chercher = new Button("Chercher");
        chercher.setOnAction(e -> {
            p.chercher();
        });

        Button supprimer = new Button("Supprimer");
        supprimer.setOnAction(e -> {
            SupprimerButton();
        });
        Buttons.getChildren().addAll(ajouter, modifier, remise, chercher, supprimer);
        Buttons.setPadding(new Insets(10, 20, 10, 20));
        Buttons.setSpacing(30);
        ajouter.setPrefSize(160, 50);
        modifier.setPrefSize(160, 50);
        remise.setPrefSize(160, 50);
        chercher.setPrefSize(160, 50);
        supprimer.setPrefSize(160, 50);

        // Collecting center part
        Center.getChildren().addAll(table, Buttons);

        this.border.setTop(Top);
        this.border.setCenter(Center);
    }

    public void addleft(VBox v) {
        this.border.setLeft(v);
    }

    public void SupprimerButton(){
        Product ProdSelected;
        ProdSelected = table.getSelectionModel().getSelectedItem();
        table.getItems().remove(ProdSelected);
        con.deleteProduct(ProdSelected);
    }

    public BorderPane getborder() {
        return this.border;
    }

    public void setborder(BorderPane b) {
        this.border = b;
    }
}
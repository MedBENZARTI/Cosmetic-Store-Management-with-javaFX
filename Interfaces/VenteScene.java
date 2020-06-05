package Interfaces;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Classes.Product;
import Classes.Sale;
import Connect.Connector;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class VenteScene {
    private Scene scene;
    Connector con;
    TableView<Sale> table;
    private Button b ;
    BorderPane border;

    public VenteScene() throws Exception {
        con=new Connector();
        this.border = new BorderPane();
        table = new TableView<>();
        //Top part
        HBox Top = new HBox();

        Top.setPadding(new Insets(15, 12, 15, 12));
        Top.setSpacing(350);
        Top.setStyle("-fx-background-color: #67AB9F;");

        FileInputStream input = new FileInputStream("Icons/exit.png");
        Image exitimage = new Image(input);
        ImageView exitimageView = new ImageView(exitimage);

        Text market = new Text("\n  TEK UP \nCosmetics");

        market.setFont(Font.font("Courgette", FontWeight.BOLD, 22));
        Text frame = new Text("\nVente");
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

        //Left part

        //Center part
        VBox Center = new VBox();

        ObservableList <Product> productlist;
        
        //Table creation

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
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("SellinEmployeeIDgPrice"));

        TableColumn<Sale, String> dateColumn = new TableColumn<>("Date du vente");
        dateColumn.setPrefWidth(120);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("SaleDate"));

        TableColumn<Sale, String> qteColumn = new TableColumn<>("Quantité");
        qteColumn.setPrefWidth(120);
        qteColumn.setCellValueFactory(new PropertyValueFactory<>("Qte"));

        TableColumn<Sale, String> priceColumn = new TableColumn<>("Prix total");
        priceColumn.setPrefWidth(120);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("TotalePrice"));

        table.setItems(con.AllSales());
        table.getColumns().addAll(saleColumn, clienColumn, produitColumn, employeeColumn, dateColumn, qteColumn, priceColumn);

        //Add buttons
        HBox Buttons = new HBox();
        Button ajouter = new Button("Ajouter");
        
        // ajouter.setOnAction(e -> {
        //     ProductAlertboxes p = new ProductAlertboxes();
        //     // Product prod = p.ajouter();

        // });
        Button consulter = new Button("Consulter");
        // consulter.setOnAction(e -> {
        //     ProductAlertboxes p = new ProductAlertboxes();
        //     p.modifier();
        // });

        Button supprimer = new Button("Supprimer");
        
        Buttons.getChildren().addAll(ajouter,consulter, supprimer);
        Buttons.setPadding(new Insets(10, 20, 10, 20));
        Buttons.setSpacing(30);
        Buttons.setAlignment(Pos.CENTER);
        ajouter.setPrefSize(160, 50);
        consulter.setPrefSize(160, 50);
        supprimer.setPrefSize(160, 50);

        //Collecting center part
        Center.getChildren().addAll(table,Buttons);
        
        this.border.setTop(Top);

        this.border.setCenter(Center);
        // scene = new Scene(border, 1100, 600);

    }

    public void addproducts(Product p){
        ObservableList<Product> l = con.AllProducts();
        l.add(p);
        
    }
    public void deleteButtonClicked(){
        ObservableList <Sale> saleSelected, allSales;
        allSales = table.getItems();
        saleSelected= table.getSelectionModel().getSelectedItems();
    }

    public void addleft(VBox v){
        this.border.setLeft(v);
    }

    public BorderPane getborder() {
        return this.border;
    }

    public void setborder(BorderPane b) {
        this.border = b;

    }
    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public Button getb() {
        return b;
    }

    public void setb(Button but) {
        this.b = but;
    }
}
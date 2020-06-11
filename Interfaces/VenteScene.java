package Interfaces;

import java.io.FileInputStream;

import Classes.Client;
import Classes.Product;
import Classes.Sale;
import Connect.Connector;
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
import javafx.stage.Stage;

public class VenteScene {
    BorderPane border;
    TableView<Sale> table;
    TableView<Client> tabclient;



    public TableView<Client> gettabclient(){
        return tabclient;
    }
    public void settableclient(TableView<Client> tab){
        this.tabclient = tab;
    }

    public VenteScene(Connector con,TableView<Product> tabprod,Scene sc, Stage st ) throws Exception {
        con=new Connector();
        this.border = new BorderPane();
        VenteAlertboxes v = new VenteAlertboxes();
        table = new TableView<>();
        //Top part
        HBox Top = new HBox();

        Top.setPadding(new Insets(15, 12, 15, 12));
        Top.setSpacing(358);
        Top.setId("pane1");
        Top.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        FileInputStream input = new FileInputStream("Icons/exit.png");
        Image exitimage = new Image(input);
        ImageView exitimageView = new ImageView(exitimage);

        Text market = new Text("\n  TEK UP \nCosmetics");

        market.setFont(Font.font("Courgette", FontWeight.BOLD, 22));
        Text frame = new Text("\nVente");
        frame.setFont(Font.font("Copperplate Gothic Bold", FontWeight.BOLD, 34));

        VBox logout = new VBox();
        logout.setSpacing(5);
        Text adrs = new Text("Utilisateur: mohamed");
        adrs.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Button exitb = new Button("", exitimageView);
        exitb.setStyle("-fx-background-color: #67AB9F00; ");
        exitb.setOnAction(e -> st.setScene(sc));
        logout.getChildren().addAll(adrs, exitb);
        Top.getChildren().addAll(market, frame, logout);

        //Left part

        //Center part
        VBox Center = new VBox();
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
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));

        TableColumn<Sale, String> dateColumn = new TableColumn<>("Date du vente");
        dateColumn.setPrefWidth(160);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("SaleDate"));

        TableColumn<Sale, String> qteColumn = new TableColumn<>("Quantité");
        qteColumn.setPrefWidth(80);
        qteColumn.setCellValueFactory(new PropertyValueFactory<>("Qte"));

        TableColumn<Sale, String> priceColumn = new TableColumn<>("Prix total");
        priceColumn.setPrefWidth(120);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("TotalePrice"));

        table.setItems(con.AllSales());
        table.getColumns().addAll(saleColumn, clienColumn, produitColumn, employeeColumn, dateColumn, qteColumn, priceColumn);

        //Add buttons
        HBox Buttons = new HBox();
        Button ajouter = new Button("Ajouter");
        
        ajouter.setOnAction(e -> {
          v.ajouter(table,tabclient,tabprod);

        });
        Button consulter = new Button("Consulter");
        consulter.setOnAction(e->v.details1(table.getSelectionModel().getSelectedItem()));

        Button supprimer = new Button("Supprimer");
        supprimer.setOnAction(e->{
            Connector cc = new Connector();
            Sale SelectedSale = table.getSelectionModel().getSelectedItem();
            int nbr = SelectedSale.getQte();
            Product beforDelete = cc.getProdByID(SelectedSale.getProductID());
            beforDelete.AfficheDetailsProduct();
            Product afterDelete = new Product(beforDelete.getProductID(), beforDelete.getProductName(),beforDelete.getBuyingPrice(),beforDelete.getSellingPrice(), beforDelete.getProductCat(),beforDelete.getMark(),beforDelete.getQte()+nbr);
            cc.deleteSale(SelectedSale);
            cc.deleteProduct(beforDelete);
            cc.addPorduct(afterDelete);
            tabprod.getItems().clear();
            tabprod.setItems(cc.AllProducts());
            table.getItems().remove(SelectedSale);
        });
        
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


}
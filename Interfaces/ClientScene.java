package Interfaces;

import java.io.FileInputStream;

import Classes.Client;
import Connect.Connector;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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

public class ClientScene {
    BorderPane border;
    TableView<Client> table;
    ClientAlertboxes cl;
    ChoiceBox<String>choice;

    public ChoiceBox<String> getchoice(){
        return this.choice;
    }

    public void setchoice (ChoiceBox<String>choix){
        this.choice = choix;
    }


    public ClientScene(Connector con,Scene sc, Stage st) throws Exception {
        this.border = new BorderPane();
        table = new TableView<>();
        cl = new ClientAlertboxes();
        // Top part
        HBox Top = new HBox();

        Top.setPadding(new Insets(15, 12, 15, 12));
        Top.setSpacing(353);
        Top.setId("pane1");
        Top.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        FileInputStream input = new FileInputStream("Icons/exit.png");
        Image exitimage = new Image(input);
        ImageView exitimageView = new ImageView(exitimage);

        Text market = new Text("\n  TEK UP \nCosmetics");

        market.setFont(Font.font("Courgette", FontWeight.BOLD, 22));
        Text frame = new Text("\nClient");
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

        // Left part

        // Center part
        VBox Center = new VBox();

        // Table creation
        TableColumn<Client, String> idColumn = new TableColumn<>("ID");
        idColumn.setPrefWidth(220);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Client, String> fnameColumn = new TableColumn<>("Prénom");
        fnameColumn.setPrefWidth(220);
        fnameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));

        TableColumn<Client, String> lnameColumn = new TableColumn<>("Nom");
        lnameColumn.setPrefWidth(220);
        lnameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));

        TableColumn<Client, String> phnnbr = new TableColumn<>("Numéro de tel");
        phnnbr.setPrefWidth(220);
        phnnbr.setCellValueFactory(new PropertyValueFactory<>("PhoneNbr"));


        table.setItems(con.AllClients());
        table.getColumns().addAll(idColumn, fnameColumn, lnameColumn, phnnbr);

        // Add buttons
        HBox Buttons = new HBox();

        Button ajouter = new Button("Ajouter");
        ajouter.setOnAction(e -> {
            cl.ajouter(table,choice);
        });

        Button modifier = new Button("Modifier");
        modifier.setOnAction(e -> {
            cl.modifier(table.getSelectionModel().getSelectedItem(), table);
        });

        Button vente = new Button("Ventes");
        vente.setOnAction(e -> {
            cl.consulter(table.getSelectionModel().getSelectedItem());
        });

        Button supprimer = new Button("Supprimer");
        supprimer.setOnAction(e->{
            Client selectedclient = table.getSelectionModel().getSelectedItem();
            con.deleteClient(selectedclient);
            table.getItems().clear();
            table.setItems(con.AllClients());
        });

        Buttons.getChildren().addAll(ajouter, modifier, vente, supprimer);
        Buttons.setPadding(new Insets(10, 20, 10, 20));
        Buttons.setSpacing(30);
        Buttons.setAlignment(Pos.CENTER);

        ajouter.setPrefSize(160, 50);
        modifier.setPrefSize(160, 50);
        vente.setPrefSize(160, 50);
        supprimer.setPrefSize(160, 50);

        // Collecting center part
        Center.getChildren().addAll(table, Buttons);
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

    public TableView<Client> getClientTable(){
        return this.table;
    }
}
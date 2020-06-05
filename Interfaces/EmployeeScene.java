package Interfaces;

import java.io.FileInputStream;

import Classes.Employee;
import Classes.Product;
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

public class EmployeeScene {
    BorderPane border;
    Connector con;
    TableView<Employee> table;
    Button b;

    public EmployeeScene() throws Exception {
        con = new Connector();
        this.border = new BorderPane();
        table = new TableView<>();
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
        Text frame = new Text("\nEmployee");
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
        TableColumn<Employee, String> idColumn = new TableColumn<>("ID");
        idColumn.setPrefWidth(120);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Employee, String> fnameColumn = new TableColumn<>("Nom");
        fnameColumn.setPrefWidth(120);
        fnameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));

        TableColumn<Employee, String> lnameColumn = new TableColumn<>("Prix d'Achat");
        lnameColumn.setPrefWidth(150);
        lnameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));

        TableColumn<Employee, String> phnnbr = new TableColumn<>("Prix de Vente");
        phnnbr.setPrefWidth(150);
        phnnbr.setCellValueFactory(new PropertyValueFactory<>("PhoneNbr"));

        TableColumn<Employee, String> gradeColumn = new TableColumn<>("Grade");
        gradeColumn.setPrefWidth(150);
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("Grade"));

        TableColumn<Employee, String> salaryColumn = new TableColumn<>("Salaire");
        salaryColumn.setPrefWidth(150);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("Salary"));

        table.setItems(con.Allemployee());
        table.getColumns().addAll(idColumn, fnameColumn, lnameColumn, phnnbr, gradeColumn, salaryColumn);

        // Add buttons
        HBox Buttons = new HBox();
        Button ajouter = new Button("Ajouter");

        ajouter.setOnAction(e -> {

        });
        Button modifier = new Button("Modifier");
        modifier.setOnAction(e -> {
            ProductAlertboxes p = new ProductAlertboxes();
    
        });
        Button chercher = new Button("Chercher");
        chercher.setOnAction(e -> {
            ProductAlertboxes p = new ProductAlertboxes();
            // p.chercher();
        });
        Button supprimer = new Button("Supprimer");

        Buttons.getChildren().addAll(ajouter, modifier, chercher, supprimer);
        Buttons.setPadding(new Insets(10, 20, 10, 20));
        Buttons.setSpacing(30);
        Buttons.setAlignment(Pos.CENTER);
        ajouter.setPrefSize(160, 50);
        modifier.setPrefSize(160, 50);
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

    public BorderPane getborder() {
        return this.border;
    }

    public void setborder(BorderPane b) {
        this.border = b;
    }
}
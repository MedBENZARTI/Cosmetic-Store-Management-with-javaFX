package Interfaces;

import java.io.FileInputStream;

import Classes.Category;
import Connect.Connector;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class CategoryScene {
    BorderPane border;
    Connector con;
    TableView<Category> table;

    public CategoryScene() throws Exception {
        con = new Connector();
        this.border = new BorderPane();
        CategoryAlertboxes cat = new CategoryAlertboxes();
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
        Text frame = new Text("\nCategorie");
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
        TableColumn<Category, String> idColumn = new TableColumn<>("ID");
        idColumn.setPrefWidth(450);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("CatID"));

        TableColumn<Category, String> nameColumn = new TableColumn<>("Nom");
        nameColumn.setPrefWidth(450);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("CatName"));

        table.setItems(con.AllCategories());
        table.getColumns().addAll(nameColumn,idColumn);
        table.setCenterShape(true);


        // Add buttons
        HBox Buttons = new HBox();
        Button ajouter = new Button("Ajouter");

        ajouter.setOnAction(e -> {
            cat.ajouter(table);

        });
        Button consulter = new Button("Consulter");
        consulter.setOnAction(e -> {
            cat.consulter(table);
        });

        Button supprimer = new Button("Supprimer");
        supprimer.setOnAction(e->{
            table.getItems().remove(table.getSelectionModel().getSelectedItem());
            con.deleteCategory(table.getSelectionModel().getSelectedItem());
        });

        Buttons.getChildren().addAll(ajouter, consulter, supprimer);
        Buttons.setPadding(new Insets(10, 20, 10, 20));
        Buttons.setSpacing(30);
        Buttons.setAlignment(Pos.CENTER);
        ajouter.setPrefSize(160, 50);
        consulter.setPrefSize(160, 50);
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
    public TableView<Category> getTable() {
        return this.table;
    }
}
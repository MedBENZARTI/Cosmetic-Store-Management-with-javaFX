package Interfaces;

import java.io.FileInputStream;

import Classes.Employee;
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

public class EmployeeScene {
    BorderPane border;
    TableView<Employee> table;
    EmployeeAlertboxes emp;

    public EmployeeScene(Connector con,Scene sc, Stage st) throws Exception {
        this.border = new BorderPane();
        table = new TableView<>();
        emp= new EmployeeAlertboxes();
        // Top part
        HBox Top = new HBox();

        Top.setPadding(new Insets(15, 12, 15, 12));
        Top.setSpacing(334);
        Top.setId("pane1");
        Top.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        FileInputStream input = new FileInputStream("Icons/exit.png");
        Image exitimage = new Image(input);
        ImageView exitimageView = new ImageView(exitimage);

        Text market = new Text("\n  TEK UP \nCosmetics");

        market.setFont(Font.font("Courgette", FontWeight.BOLD, 22));
        Text frame = new Text("\nEmployé");
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
        TableColumn<Employee, String> idColumn = new TableColumn<>("ID");
        idColumn.setPrefWidth(120);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Employee, String> fnameColumn = new TableColumn<>("Prénom");
        fnameColumn.setPrefWidth(120);
        fnameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));

        TableColumn<Employee, String> lnameColumn = new TableColumn<>("Nom");
        lnameColumn.setPrefWidth(150);
        lnameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));

        TableColumn<Employee, String> phnnbr = new TableColumn<>("Numro de tel");
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
            emp.ajouter(table);
        });

        Button modifier = new Button("Modifier");
        modifier.setOnAction(e -> {
            emp.modifier(table, table.getSelectionModel().getSelectedItem());
        });

        Button consulter = new Button("Consulter");
        consulter.setOnAction(e -> {
            emp.consulter(table.getSelectionModel().getSelectedItem());
        });

        Button supprimer = new Button("Supprimer");
        supprimer.setOnAction(e->{
            con.deleteEmployee(table.getSelectionModel().getSelectedItem());
            table.getItems().remove(table.getSelectionModel().getSelectedItem());
        });

        Buttons.getChildren().addAll(ajouter, modifier, consulter, supprimer);
        Buttons.setPadding(new Insets(10, 20, 10, 20));
        Buttons.setSpacing(30);
        Buttons.setAlignment(Pos.CENTER);
        ajouter.setPrefSize(160, 50);
        modifier.setPrefSize(160, 50);
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
}
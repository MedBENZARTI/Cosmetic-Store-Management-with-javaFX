package Interfaces;

import java.util.UUID;

import Classes.Employee;
import Classes.Sale;
import Connect.Connector;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    Employee emp;
    Connector con = new Connector();
    public EmployeeAlertboxes() {
    }

    public void ajouter(TableView<Employee> table) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Ajouter");
        window.setMinWidth(250);
        VBox v = new VBox();
        v.getChildren().add(addTitle("Nouveau Employé"));

        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setHgap(10);
        g.setVgap(8);

        // firstname label
        Label l1 = new Label("Prénom");
        GridPane.setConstraints(l1, 0, 0);
        // firstname input
        TextField firstname = new TextField();
        firstname.setPromptText("Mohamed");
        GridPane.setConstraints(firstname, 1, 0);

        // lastname label
        Label l2 = new Label("Nom");
        GridPane.setConstraints(l2, 0, 1);
        // bprice input
        TextField lastname = new TextField();
        lastname.setPromptText("BENZARTI");
        GridPane.setConstraints(lastname, 1, 1);

        // phoneNbr label
        Label l3 = new Label("Numéro de tel");
        GridPane.setConstraints(l3, 0, 2);
        // phoneNbr input
        TextField phoneNbr = new TextField();
        phoneNbr.setPromptText("54 xxx xxx");
        GridPane.setConstraints(phoneNbr, 1, 2);

        // grade label
        Label l4 = new Label("Grade");
        GridPane.setConstraints(l4, 0, 3);
        // grade input
        TextField grade = new TextField();
        grade.setPromptText("Vendeur");
        GridPane.setConstraints(grade, 1, 3);

        // salary label
        Label l5 = new Label("Salaire");
        GridPane.setConstraints(l5, 0, 4);
        // salary input
        TextField salary = new TextField();
        salary.setPromptText("700 DT");
        GridPane.setConstraints(salary, 1, 4);

        // add to gridpane
        g.getChildren().addAll(l1, firstname, l2, lastname, l3, phoneNbr, l4, grade, l5, salary);

        //// add buttons
        Button annuler = new Button("Annuler");
        annuler.setOnAction(e -> window.close());
        Button confirmer = new Button("Confirmer");
        confirmer.setOnAction(e -> {
            emp = new Employee(newID(), firstname.getText(), lastname.getText(), Integer.parseInt(phoneNbr.getText()),
                    grade.getText(), Double.parseDouble(salary.getText()));
            Connector c = new Connector();
            c.addemployee(emp);
            table.getItems().add(emp);
            window.close();
        });

        HBox b = new HBox(annuler, confirmer);
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

    // ------THE MODIFY BUTTON-------------
    // ------------------------------------
    public void modifier(TableView<Employee> table, Employee SelectedEmp) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Modifier");
        window.setMinWidth(250);

        VBox v = new VBox(addTitle("Modifier Employé"));

        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setHgap(10);
        g.setVgap(8);

        // id label
        Label l0 = new Label("ID");
        GridPane.setConstraints(l0, 0, 0);
        // id input
        TextField id = new TextField(SelectedEmp.getID());
        id.setDisable(true);
        GridPane.setConstraints(id, 1, 0);

        // firstname label
        Label l1 = new Label("Prénom");
        GridPane.setConstraints(l1, 0, 1);
        // firstname input
        TextField firstname = new TextField(SelectedEmp.getFirstName());
        GridPane.setConstraints(firstname, 1, 1);

        // lastname label
        Label l2 = new Label("Nom");
        GridPane.setConstraints(l2, 0, 2);
        // lastname input
        TextField lastname = new TextField(SelectedEmp.getLastName());
        GridPane.setConstraints(lastname, 1, 2);

        // phoneNbr label
        Label l3 = new Label("Numéro de tel");
        GridPane.setConstraints(l3, 0, 3);
        // phoneNbr input
        TextField phoneNbr = new TextField(String.valueOf(SelectedEmp.getPhoneNbr()));
        GridPane.setConstraints(phoneNbr, 1, 3);

        // grade label
        Label l4 = new Label("Grade");
        GridPane.setConstraints(l4, 0, 4);
        // grade input
        TextField grade = new TextField(SelectedEmp.getGrade());
        GridPane.setConstraints(grade, 1, 4);

        // salary label
        Label l5 = new Label("Salaire");
        GridPane.setConstraints(l5, 0, 5);
        // salary input
        TextField salary = new TextField(SelectedEmp.getSalary().toString());
        GridPane.setConstraints(salary, 1, 5);

        // add to gridpane
        g.getChildren().addAll(l0, id, l1, firstname, l2, lastname, l3, phoneNbr, l4, grade, l5, salary);

        Button annuler = new Button("Annuler");
        annuler.setOnAction(e -> window.close());
        Button modifier = new Button("Modifier");
        modifier.setOnAction(e -> {
            Employee em = new Employee(id.getText(), firstname.getText(), lastname.getText(),
                    Integer.parseInt(phoneNbr.getText()), grade.getText(), Double.parseDouble(salary.getText()));
            Connector c = new Connector();
            c.deleteEmployee(SelectedEmp);
            c.addemployee(em);
            table.getItems().remove(SelectedEmp);
            table.getItems().add(em);
            window.close();
        });
        HBox b = new HBox(annuler, modifier);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.setSpacing(20);
        b.setAlignment(Pos.CENTER_RIGHT);

        v.getChildren().addAll(g, b);
        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(20, 10, 10, 10));
        v.setSpacing(15);
        Scene s = new Scene(v);
        window.setScene(s);
        window.show();
    }

    // ------THE DETAILS BUTTON---------
    // ---------------------------------
   // ------THE DETAILS BUTTON---------
    // ---------------------------------
    public void consulter(Employee selectedEmployee) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Consulter");
        window.setMinWidth(250);

        VBox v = new VBox(addTitle("Les Ventes selon une date"));

        // the filtre
        ////////////

        GridPane g = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setHgap(10);
        g.setVgap(8);

        // date label
        Label l0 = new Label("Date entre: ");
        GridPane.setConstraints(l0, 0, 0);
        // datemin input
        TextField datemin = new TextField();
        datemin.setPromptText("aaaa-mm-jj");
        GridPane.setConstraints(datemin, 1, 0);

        Label l1 = new Label("et ");
        GridPane.setConstraints(l1, 2, 0);

        // datemax input
        TextField datemax = new TextField();
        datemax.setPromptText("aaaa-mm-jj");
        GridPane.setConstraints(datemax, 3, 0);
        Button find = new Button("Chercher");
        GridPane.setConstraints(find, 4, 0);

        // add to gridpane
        g.getChildren().addAll(l0, datemin, l1, datemax, find);
        //// the table
        /////////////
        TableView<Sale> tab = new TableView<>();
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
        dateColumn.setPrefWidth(120);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("SaleDate"));

        TableColumn<Sale, String> qteColumn = new TableColumn<>("Quantité");
        qteColumn.setPrefWidth(120);
        qteColumn.setCellValueFactory(new PropertyValueFactory<>("Qte"));

        TableColumn<Sale, String> priceColumn = new TableColumn<>("Prix total");
        priceColumn.setPrefWidth(120);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("TotalePrice"));

        tab.setItems(null);
        tab.getColumns().addAll(saleColumn, clienColumn, produitColumn, employeeColumn, dateColumn, qteColumn,
                priceColumn);

        find.setOnAction(e -> {
            String date1, date2;
            if (datemin.getText().isEmpty()) {
                date1 = dateToFullDate("2000-01-01");
            } else {
                date1 = dateToFullDate(datemin.getText());
            }
            if (datemax.getText().isEmpty()) {
                date2 = "2100-01-01 23:59:59";
            } else {
                date2 = datemax.getText()+" 23:59:59";
            }
            tab.setItems(con.salesByDateForEmployee(date1, date2,selectedEmployee.getID()));
        });
        Button ok = new Button("Ok");
        ok.setOnAction(e -> window.close());
        Button detail = new Button("Détails");
        detail.setOnAction(e->{
            VenteAlertboxes vab = new VenteAlertboxes();
            vab.details(tab.getSelectionModel().getSelectedItem());
        });
        HBox b = new HBox(detail,ok);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.setSpacing(20);
        b.setAlignment(Pos.CENTER_RIGHT);

        v.getChildren().addAll(g, tab, b);
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

    public String dateToFullDate(String date) {
        return date + " 00:00:00";
    }
}

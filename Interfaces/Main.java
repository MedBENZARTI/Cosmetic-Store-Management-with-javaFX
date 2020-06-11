package Interfaces;

import java.io.FileInputStream;

import Classes.Category;
import Classes.Product;
import Connect.Connector;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    Stage Window;
    String user;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Connector con = new Connector();
        Window = primaryStage;
        Window.setTitle("TEK-UP COSMETICS");

        VBox log = new VBox();
        log.setId("pane");
        Scene login = new Scene(log, 1100, 733);

        // login box
        ///////////
        Text market = new Text("\n  TEK UP                \nCOSMETICS               ");
        VBox bigbox = new VBox(market);
        market.setFont(Font.font("Courgette", FontWeight.BOLD, 28));

        TextField username = new TextField();
        username.setPromptText("Nom d'utilisateur");
        username.setStyle("-fx-color: red;");
        username.setPrefSize(350, 50);
        username.setFont(Font.font("Arial", 20));

        PasswordField passwd = new PasswordField();
        passwd.setPromptText("Mot de passe");
        passwd.setFont(Font.font("Arial", 22));

        passwd.setMinSize(350, 50);
        passwd.setId("p");

        Button entrer = new Button("Connection");
        CheckBox remember = new CheckBox("Se souvenir de Moi");
        CategoryScene cab = new CategoryScene(con, login, Window);
        TableView<Category> t = cab.getTable();
        ProductScene pb = new ProductScene(con, t, login, Window);
        TableView<Product> tp = pb.gettable();
        VenteAlertboxes vv = new VenteAlertboxes();
        ClientScene clb = new ClientScene(con, login, Window);
        VenteScene vb = new VenteScene(con, tp, login, Window);
        vb.settableclient(clb.getClientTable());
        clb.setchoice(vv.returnchoice());
        EmployeeScene eb = new EmployeeScene(con, login, Window);

        BorderPane prodb = pb.getborder();
        BorderPane venteb = vb.getborder();
        BorderPane employeb = eb.getborder();
        BorderPane categb = cab.getborder();
        BorderPane clientb = clb.getborder();
        Scene prodscene = new Scene(prodb, 1100, 600);

        Scene ventescene = new Scene(venteb, 1100, 600);
        Scene eployescene = new Scene(employeb, 1100, 600);
        Scene categscene = new Scene(categb, 1100, 600);
        Scene clientscene = new Scene(clientb, 1100, 600);


        entrer.setOnAction(e -> {
            String u_n = username.getText();
            String pwd = passwd.getText();
            if (!u_n.isEmpty() && !pwd.isEmpty()) {
                if ((u_n.equals("mohamed") && pwd.equals("2050")) || (u_n.equals("malek") && pwd.equals("9030"))) {
                    Window.setScene(prodscene);
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Alerte");
                    alert.setHeaderText("Erreur de connection");
                    alert.setContentText("Nom d'utilisateur ou mot de passe incorrects");
                    alert.show();
                }
            }
            if (!remember.isArmed()) {
                username.clear();
                passwd.clear();
                remember.disarm();
            }
        });

        ///// LOGIN Scene
        ////////////////
        
        HBox buttom = new HBox(remember, entrer);
        buttom.setPadding(new Insets(0, 10, 0, 5));
        buttom.setSpacing(85);

        VBox box = new VBox(username, passwd, buttom);
        bigbox.setAlignment(Pos.CENTER_RIGHT);
        bigbox.setPadding(new Insets(200, 100, 100, 700));
        bigbox.setSpacing(20);
        box.setSpacing(20);

        bigbox.getChildren().add(box);
        login.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        log.getChildren().add(bigbox);

        // left part

        prodb.setLeft(addleftt(prodscene, ventescene, eployescene, categscene, clientscene));
        venteb.setLeft(addleftt(prodscene, ventescene, eployescene, categscene, clientscene));
        employeb.setLeft(addleftt(prodscene, ventescene, eployescene, categscene, clientscene));
        categb.setLeft(addleftt(prodscene, ventescene, eployescene, categscene, clientscene));
        clientb.setLeft(addleftt(prodscene, ventescene, eployescene, categscene, clientscene));

        // Window.setScene(prodscene);
        Window.setScene(login);
        Window.show();
    }

    public VBox addleftt(Scene prodscene, Scene ventescene, Scene eployescene, Scene categscene, Scene clientscene)
            throws Exception {
        VBox Left = new VBox();
        Left.setId("pane2");
        Left.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        Left.setPadding(new Insets(0));
        Left.setSpacing(0);
        Left.setStyle("-fx-background-color: #FF999900;");

        FileInputStream input1 = new FileInputStream("Icons/cosmetics-products.png");
        Image image1 = new Image(input1);
        ImageView imageView1 = new ImageView(image1);

        FileInputStream input2 = new FileInputStream("Icons/categ.png");
        Image image2 = new Image(input2);
        ImageView imageView2 = new ImageView(image2);

        FileInputStream input3 = new FileInputStream("Icons/sale.png");
        Image image3 = new Image(input3);
        ImageView imageView3 = new ImageView(image3);

        FileInputStream input4 = new FileInputStream("Icons/customer.png");
        Image image4 = new Image(input4);
        ImageView imageView4 = new ImageView(image4);

        FileInputStream input5 = new FileInputStream("Icons/employee.png");
        Image image5 = new Image(input5);
        ImageView imageView5 = new ImageView(image5);

        Button Products = new Button("  Produits", imageView1);
        Products.setStyle("-fx-background-color: #FF999900; ");

        Button Category = new Button("  Categories", imageView2);
        Category.setStyle("-fx-background-color: #FF999900; ");

        Button Sales = new Button("  Ventes", imageView3);
        Sales.setStyle("-fx-background-color: #FF999900; ");

        Button Clients = new Button("  Clients", imageView4);
        Clients.setStyle("-fx-background-color: #FF999900; ");
        Button Employee = new Button("  Employee", imageView5);
        Employee.setStyle("-fx-background-color: #FF999900; ");

        Products.setMaxWidth(Double.MAX_VALUE);
        Category.setMaxWidth(Double.MAX_VALUE);
        Sales.setMaxWidth(Double.MAX_VALUE);
        Clients.setMaxWidth(Double.MAX_VALUE);
        Employee.setMaxWidth(Double.MAX_VALUE);

        Products.setOnAction(e -> Window.setScene(prodscene));
        Products.setOnMouseEntered(e -> Products.setStyle("-fx-background-color:  #e6e6e6"));
        Products.setOnMouseExited(e -> Products.setStyle("-fx-background-color: #FF999900; "));
        Sales.setOnAction(e -> Window.setScene(ventescene));
        Sales.setOnMouseEntered(e -> Sales.setStyle("-fx-background-color:  #e6e6e6"));
        Sales.setOnMouseExited(e -> Sales.setStyle("-fx-background-color: #FF999900; "));
        Category.setOnAction(e -> Window.setScene(categscene));
        Category.setOnMouseEntered(e -> Category.setStyle("-fx-background-color:  #e6e6e6"));
        Category.setOnMouseExited(e -> Category.setStyle("-fx-background-color: #FF999900; "));
        Clients.setOnAction(e -> Window.setScene(clientscene));
        Clients.setOnMouseEntered(e -> Clients.setStyle("-fx-background-color:  #e6e6e6"));
        Clients.setOnMouseExited(e -> Clients.setStyle("-fx-background-color: #FF999900; "));
        Employee.setOnAction(e -> Window.setScene(eployescene));
        Employee.setOnMouseEntered(e -> Employee.setStyle("-fx-background-color:  #e6e6e6"));
        Employee.setOnMouseExited(e -> Employee.setStyle("-fx-background-color: #FF999900; "));

        Left.getChildren().addAll(Products, Category, Sales, Clients, Employee);

        return Left;
    }
    public void setUser(String u) {
        this.user = u;
    }

    public String getUser() {
        return user;
    }

}

package Interfaces;

import java.io.FileInputStream;
import Classes.Category;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    Stage Window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // left part
        
        VenteScene vb = new VenteScene();
        EmployeeScene eb = new EmployeeScene();
        CategoryScene cab = new CategoryScene();
        TableView<Category> t = cab.getTable();
        ProductScene pb = new ProductScene(t);
        ClientScene clb = new ClientScene();
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

        prodb.setLeft(addleftt(prodscene, ventescene, eployescene, categscene, clientscene));
        venteb.setLeft(addleftt(prodscene, ventescene, eployescene, categscene, clientscene));
        employeb.setLeft(addleftt(prodscene, ventescene, eployescene, categscene, clientscene));
        categb.setLeft(addleftt(prodscene, ventescene, eployescene, categscene, clientscene));
        clientb.setLeft(addleftt(prodscene, ventescene, eployescene, categscene, clientscene));

        Window = primaryStage;
        Window.setTitle("new");
        Window.setScene(prodscene);
        Window.show();
    }

    public VBox addleftt(Scene prodscene, Scene ventescene, Scene eployescene, Scene categscene, Scene clientscene)
            throws Exception {
        VBox Left = new VBox();

        Left.setPadding(new Insets(0));
        Left.setSpacing(0);
        Left.setStyle("-fx-background-color: #FF9999;");

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
        Products.setStyle("-fx-background-color: #FF9999; ");

        Button Category = new Button("  Categories", imageView2);
        Category.setStyle("-fx-background-color: #FF9999; ");

        Button Sales = new Button("  Ventes", imageView3);
        Sales.setStyle("-fx-background-color: #FF9999; ");

        Button Clients = new Button("  Clients", imageView4);
        Clients.setStyle("-fx-background-color: #FF9999; ");
        Button Employee = new Button("  Employee", imageView5);
        Employee.setStyle("-fx-background-color: #FF9999; ");

        Products.setMaxWidth(Double.MAX_VALUE);
        Category.setMaxWidth(Double.MAX_VALUE);
        Sales.setMaxWidth(Double.MAX_VALUE);
        Clients.setMaxWidth(Double.MAX_VALUE);
        Employee.setMaxWidth(Double.MAX_VALUE);

        Products.setOnAction(e -> Window.setScene(prodscene));
        Sales.setOnAction(e -> Window.setScene(ventescene));
        Category.setOnAction(e -> Window.setScene(categscene));
        Clients.setOnAction(e -> Window.setScene(clientscene));
        Employee.setOnAction(e -> Window.setScene(eployescene));

        Left.getChildren().addAll(Products, Category, Sales, Clients, Employee);

        return Left;
    }

}

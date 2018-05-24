import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.stage.*;
import java.lang.*;
import java.util.ArrayList;
import java.io.File;
import java.io.*;
import java.lang.*;
import java.util.*;

public class Main extends Application{
    Scene scene;
    Stage window;
    VBox layout;
    Button button1;
    Button button2;
    Button button3;
    ArrayList<String> grupes;
    Map<String, ArrayList<String>> studentai;
    Map<String, ArrayList<String>> datos;
    Scanner sc;
    Scanner sc2;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        window = primaryStage;
        window.setTitle("Lankomumas");
        layout = new VBox();
        button1 = new Button("Studentu ivedimas");
        button2 = new Button("Lankomumo ivedimas");
        button3 = new Button("Studentu sarasas");
        grupes = new ArrayList<>(5);
        studentai = new HashMap<String, ArrayList<String>>();
        datos = new HashMap<String, ArrayList<String>>();
        try{
            sc = new Scanner(new File("dBaze.txt"));
        }
        catch (Exception e){
            System.out.println("Nepavyko");
        }
        try{
            sc2 = new Scanner(new File("dBaze2.txt"));
        }
        catch (Exception e){
            System.out.println("Nepavyko");
        }
        Failas.Nuskaitymas(grupes,studentai,datos,sc);
        Failas.Nuskaitymas(datos,sc2);
        layout.setPadding(new Insets(10, 20, 20, 20));
        layout.setSpacing(25);
        layout.getChildren().addAll(button1,button2,button3);
        button1.setOnAction(event ->
        {
            StudentuIvedimas.display(grupes, studentai,datos);

        });
        button2.setOnAction(event -> LankomumoIvedimas.display(grupes,studentai,datos));
        button3.setOnAction(event -> StudentuSarasas.display(datos,studentai,grupes));

        scene = new Scene(layout, 500, 200);
        window.setScene(scene);
        window.show();

    }

}

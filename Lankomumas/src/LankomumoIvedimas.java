import javafx.event.ActionEvent;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.control.ComboBox;

import javax.crypto.NullCipher;
import javax.lang.model.type.NullType;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class LankomumoIvedimas {
    static boolean patikrinimas = false;
    public static void display(ArrayList<String> grupes, Map<String, ArrayList<String>> studentai,Map<String, ArrayList<String>> datos) {
        Stage window = new Stage();
        window.setTitle("LankomumoIvedimas");
        window.initModality(Modality.APPLICATION_MODAL);
        Label label2 = new Label("Pasirinkite studenta arba visa grupe");
        Label label3 = new Label("Iveskite metus");
        Label label4 = new Label("Iveskite menesi");
        Label label5 = new Label("Iveskite diena");
        TextField metai = new TextField();
        metai.setMaxSize(50, 5);
        TextField men = new TextField();
        men.setMaxSize(50, 5);
        TextField diena = new TextField();
        diena.setMaxSize(50, 5);
        Button button = new Button("Ivesti");
        ComboBox<String> comboBox = new ComboBox<>();

        for (String x : grupes) {

            comboBox.getItems().add(x);

            if (studentai.get(x) != null) {
                ArrayList<String> list = studentai.get(x);
                System.out.println(x + "\n");
                for (String y : list) {
                    comboBox.getItems().add(y);
                }
            }
        }
        button.setOnAction(event -> {

            if(!Patikrinimas(metai,men,diena))
            {
                System.out.println("Klaida");
            }
            else
            {
                if(grupes.contains(comboBox.getValue()))
                {
                    ArrayList<String> list = studentai.get(comboBox.getValue());
                    for(String x : list)
                    {
                        ArrayList<String> list2 = datos.get(x);
                        list2.add(metai.getText()+" "+men.getText()+" "+diena.getText());
                        datos.put(x, list2);
                    }
                    System.out.println("Ivesta");
                    patikrinimas = true;
                }
                else{
                    if(datos.containsKey(comboBox.getValue()))
                    {
                        ArrayList<String> list = datos.get(comboBox.getValue());
                        list.add(metai.getText()+" "+men.getText()+" "+diena.getText());
                        datos.put(comboBox.getValue(), list);
                        System.out.println("Ivesta");
                        patikrinimas = true;
                    }
                }

            }
        } );
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(label2,comboBox,label3,metai,label4,men,label5,diena,button);
        Scene scene = new Scene(layout, 400, 500);
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(event -> {
            if(patikrinimas)
            {
                try {
                    Failas.Irasymas(datos,studentai,grupes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }
        static boolean Patikrinimas(TextField metai, TextField men, TextField diena) {
        int nr = 0;
        boolean check = true;
        try {
            nr = Integer.parseInt(metai.getText());
        } catch (NumberFormatException e) {
            check = false;
        }
        if (nr < 1990 || nr > 2018)
            check = false;
        nr = 0;
        try {
            nr = Integer.parseInt(men.getText());
        } catch (NumberFormatException e) {
            check = false;
        }
        if(nr > 12 || nr < 1)
        {
            check = false;
        }
        nr = 0;
        try {
            nr = Integer.parseInt(diena.getText());
        } catch (NumberFormatException e) {
            check = false;
        }
        if(nr < 1 || nr > 31)
        {
            check = false;
        }
        return check;
    }
}

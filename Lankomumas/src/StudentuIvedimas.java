import javafx.event.ActionEvent;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.control.ComboBox;

import javax.lang.model.type.NullType;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentuIvedimas {
    private static ArrayList<String> list9 = new ArrayList<>(5);
    static boolean patikrinimas = false;
    public static void display(ArrayList<String> list2, Map<String, ArrayList<String>> list3,  Map<String, ArrayList<String>> datos) {
        Button button2 = new Button("testas");
        Stage window = new Stage();
        window.setTitle("Studentu Ivedimas");
        window.initModality(Modality.APPLICATION_MODAL);
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setEditable(true);
        Label zinute = new Label("Pasirinkite grupe arba Iveskite nauja");
        for (String x : list2) {
            list9.add(x);
            comboBox.getItems().add(x);
        }
        TextField vardas = new TextField();
        TextField pavarde = new TextField();
        Label zinute2 = new Label("Iveskite studento varda");
        Label zinute3 = new Label("Iveskite studento pavarde");
        Button button = new Button("Ivesti i sistema");

        comboBox.setOnAction((ActionEvent event) ->
        {
            String s = comboBox.getValue();
            if (list2.contains(s)==false && s != null) {
                comboBox.getItems().add(s);
                list9.add(s);
                list2.add(s);
            }
        });

        button.setOnAction(event -> {
            ArrayList<String> list4;
            ArrayList<String> list5 = new ArrayList<>();
            datos.put(vardas.getText() + " " + pavarde.getText(),list5);
            if (list3.containsKey(comboBox.getValue())) {
                list4 = list3.get(comboBox.getValue());
                if(list4.contains(vardas.getText() + " " + pavarde.getText()) == false)
                {
                    list4.add(vardas.getText() + " " + pavarde.getText());
                    patikrinimas = true;
                    list3.put(comboBox.getValue(), list4);
                }


            } else {
                list4 = new ArrayList<String>();
                list4.add(vardas.getText() + " " + pavarde.getText());
                list3.put(comboBox.getValue(), list4);
                patikrinimas = true;
            }
        });
        button2.setOnAction(event ->
        {
            ArrayList<String> list6;
            list6 = list3.get(comboBox.getValue());
            for (String x : list6) {
                System.out.println(x + "\n");
            }
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(zinute, comboBox, zinute2, vardas, zinute3, pavarde, button, button2);
        Scene scene = new Scene(layout, 400, 500);
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(event -> {
            if(patikrinimas)
            {
                try {
                    Failas.Irasymas(list2, list3);
                } catch (IOException e) {
                    System.out.println("Klaida");
                }
            }
            else{

            }

        });
    }

}

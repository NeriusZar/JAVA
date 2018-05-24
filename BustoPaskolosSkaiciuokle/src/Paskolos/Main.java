package Paskolos;

import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.stage.*;
import java.lang.*;


public class Main extends Application {
    Button button;
    Button button2;

    Scene scene2;
    Scene scene;
    Stage window;
    Stage window2;
    ListView<String> listView;
    VBox layout;
    VBox layout2;
    Formatter x = null;
    boolean a;
    boolean b;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Busto paskolos skaiciuokle");
        try {
            x = new Formatter("ataskaita.txt");
        } catch (Exception e) {
        }
        button = new Button();
        button.setText("Skaiciuoti");
        TextField text1 = new TextField();
        Label label1 = new Label("Suma");
        TextField text2 = new TextField();
        Label label2 = new Label("Metai");
        TextField text3 = new TextField();
        Label label3 = new Label("Menesiai");
        TextField text4 = new TextField();
        Label label4 = new Label("Procentas");
        TextField text5 = new TextField();
        Label label5 = new Label("Menesis nuo kurio norite matyti ataskaita");
        TextField text6 = new TextField();
        Label label6 = new Label("Menesis iki kurio norite matyti ataskaita");
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        Label label7 = new Label("Paskolos grazinimo budas");
        choiceBox.getItems().addAll("Anuitetinis", "Linijinis");
        choiceBox.setValue("Anuitetinis");

        button.setOnAction(event -> Skaiciavimas(text1, text2, text3, text4, choiceBox, text5, text6,b));

        layout = new VBox();
        layout.setPadding(new Insets(10, 20, 20, 20));
        layout.setSpacing(10);
        layout.getChildren().addAll(label1, text1, label2, text2, label3, text3, label4, text4, label5, text5, label6, text6, label7, choiceBox, button);
        scene = new Scene(layout, 500, 500);
        layout2 = new VBox(10);
        layout2.setPadding(new Insets(10, 20, 20, 20));
        scene2 = new Scene(layout2, 600, 300);
        window.setScene(scene);
        window.show();

    }


    private double isDouble(TextField text, boolean a) {
        try {
            double num = Double.parseDouble(text.getText());
            a = true;
            return num;
        } catch (NumberFormatException e) {
            a = false;
            AlertBox.display();
            return 0;
        }

    }

    private int isInt(TextField text, boolean a) {
        try {
            int num = Integer.parseInt(text.getText());
            a = true;
            return num;
        } catch (NumberFormatException e) {
            a = false;
            AlertBox.display();
            return 0;
        }

    }

    private void Skaiciavimas(TextField text1, TextField text2, TextField text3, TextField text4, ChoiceBox<String> choiceBox, TextField text5, TextField text6, boolean b) {
        if (Tikrinimas(text1, text2, text3, text4, text5, text6)) {
            try {
                x = new Formatter("ataskaita.txt");
            } catch (Exception e) {
            }
            window2 = new Stage();
            Paskolos a = new Paskolos(isDouble(text1,b), isInt(text2,b), isInt(text3,b), isDouble(text4,b), isInt(text5,b), isInt(text6,b));
            listView = new ListView<>();
            listView.getItems().add("Menesis: Menesine imoka: Dar nesumoketa: ");
            window2.initModality(Modality.APPLICATION_MODAL);
            button2 = new Button("Uzdaryti");
            if (choiceBox.getValue() == "Anuitetinis") {
                Anuitetinis objektas = new Anuitetinis(a.suma, a.metai, a.menesiai, a.procentas, a.men1, a.men2);


                objektas.Priskirimas();
                for (int i = 0; i < a.Periodusk2(); i++) {
                    objektas.grazinti *= 100;
                    objektas.grazinti = Math.round(objektas.grazinti);
                    objektas.grazinti /= 100;
                    objektas.liko *= 100;
                    objektas.liko = Math.round(objektas.liko);
                    objektas.liko /= 100;
                    if (i >= objektas.men1 - 1 && i < objektas.men2) {
                        listView.getItems().add((i + 1) + "             " + objektas.grazinti + "                 " + objektas.liko);
                        x.format("%.2f %.2f\n", objektas.grazinti, objektas.liko);

                    }

                    objektas.Sumazinimas();
                }
                layout2.getChildren().add(listView);
            }
            if (choiceBox.getValue() == "Linijinis") {
                Linijinis objektas2 = new Linijinis(a.suma, a.metai, a.menesiai, a.procentas, a.men1, a.men2);
                objektas2.Priskirimas();
                for (int i = 0; i < a.Periodusk2(); i++) {
                    objektas2.grazinti *= 100;
                    objektas2.grazinti = Math.round(objektas2.grazinti);
                    objektas2.grazinti /= 100;
                    objektas2.liko *= 100;
                    objektas2.liko = Math.round(objektas2.liko);
                    objektas2.liko /= 100;
                    if (i >= objektas2.men1 - 1 && i < objektas2.men2) {
                        listView.getItems().add((i + 1) + "             " + objektas2.grazinti + "                 " + objektas2.liko);
                        x.format("%.2f %.2f", objektas2.grazinti, objektas2.liko);
                        x.format("/n");

                    }
                    objektas2.Pakeitimas();
                }
                layout2.getChildren().add(listView);

            }
            x.close();
            layout2.getChildren().add(button2);
            button2.setOnAction(event -> {
                        layout2.getChildren().removeAll(button2, listView);
                        window2.close();
                    }
            );
            window2.setOnCloseRequest(event -> {
                        layout2.getChildren().removeAll(button2, listView);
                        window2.close();
                    }
            );
            window2.setScene(scene2);
            window2.showAndWait();
        } else {

        }


    }

    public boolean Tikrinimas(TextField text1, TextField text2, TextField text3, TextField text4, TextField text5, TextField text6) {

        double suma = isDouble(text1, a);
        int metai = isInt(text2, a);
        int menesiai = isInt(text3, a);
        double procentas = isDouble(text4, a);
        int men1 = isInt(text5, a);
        int men2 = isInt(text6, a);

        if (suma < 0 || metai < 0 || menesiai < 0 || procentas < 0 || men1 > men2 || men1 < 0 || men2 < 0) {
            AlertBox.display();
            return false;
            } else return true;
        }



}
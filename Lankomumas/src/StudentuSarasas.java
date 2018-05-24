import com.itextpdf.text.pdf.PdfWriter;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import com.itextpdf.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.List;


public class StudentuSarasas {
    public static void display(Map<String, ArrayList<String>> datos,Map<String, ArrayList<String>> studentai, ArrayList<String> grupes)
    {
        Button button = new Button("Sukurti sarasa pdf faile");
        Stage window = new Stage();
        window.setTitle("Studentu Sarasas");
        window.initModality(Modality.APPLICATION_MODAL);
        Label label1 = new Label("Pasirinkite data");
        ComboBox<String> comboBox = new ComboBox<>();
        for(String x: grupes)
        {
            ArrayList<String> list = null;
            if(x != null) {
                list = studentai.get(x);
                if(list != null)
                {
                    for (String i : list) {
                        ArrayList<String> list2 = datos.get(i);
                        for (String j : list2) {
                            if (!comboBox.getItems().contains(j)) {
                                comboBox.getItems().add(j);
                            }
                        }
                    }
                }

            }
        }
        ListView listView = new ListView();

        comboBox.setOnAction(event -> {
           listView.getItems().remove(0,listView.getItems().size());
            for(String x: grupes)
            {
                ArrayList<String> list = studentai.get(x);
                if(list != null)
                {
                    for(String i: list)
                    {
                        ArrayList<String> list2 = datos.get(i);
                        if(list2.contains(comboBox.getValue()))
                        {
                            listView.getItems().add(i);
                        }
                    }
                }

            }
           // listView.refresh();
        });
        button.setOnAction(event -> {
            ArrayList<String> list = new ArrayList<>();
            for(int x = 0; x < listView.getItems().size(); x++)
                list.add(listView.getItems().get(x).toString());

            PDF.SukurtiPdf(comboBox.getValue(),list);
        });
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(label1,comboBox,listView,button);
        Scene scene = new Scene(layout, 400, 500);
        window.setScene(scene);
        window.show();
    }
}

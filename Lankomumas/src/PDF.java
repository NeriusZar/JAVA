import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.text.html.ListView;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class PDF {
    static PdfPTable table = new PdfPTable(1);
    static Paragraph title = new Paragraph();
    public static void SukurtiPdf(String data, ArrayList<String> list)
    {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("sarasas.pdf"));
            document.open();
            title.clear();
            table.deleteBodyRows();
            title.setAlignment(Element.ALIGN_LEFT);
            title.add("Data : "+data);
            document.add(title);
            for(String x : list)
            {
                table.addCell(x);
            }
            table.setSpacingBefore(20);
            document.add(table);


            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}

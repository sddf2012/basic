package pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author liu peng bo
 * date: 2021/3/11 14:03
 */
public class Pdf {
    public static void generatePdf() throws FileNotFoundException {
        Document document = new Document();
        OutputStream os = new FileOutputStream("");
        PdfWriter writer;
        try {
            writer = PdfWriter.getInstance(document, os);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


    }
}

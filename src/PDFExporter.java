/* OPTIONAL: PDF export using iText 5 or 7.
   If you want PDF export, download iText jar (e.g., itextpdf-5.5.13.3.jar)
   and place it on your classpath, or convert project to Maven and add dependency.
*/
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;

public class PDFExporter {
    public static void exportStudentsToPDF(List<Student> students, String outFilename) throws Exception {
        // Sample code for iText 5:
        // com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        // com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(Paths.get("data","exports", outFilename).toFile()));
        // document.open();
        // document.add(new com.itextpdf.text.Paragraph("Student Report"));
        // for (Student s : students) {
        //    document.add(new com.itextpdf.text.Paragraph(s.toCSV()));
        // }
        // document.close();
        //
        // Above code requires iText jar. Uncomment and add iText dependency to use.
        throw new UnsupportedOperationException("PDF export requires iText library. See README for instructions.");
    }
}

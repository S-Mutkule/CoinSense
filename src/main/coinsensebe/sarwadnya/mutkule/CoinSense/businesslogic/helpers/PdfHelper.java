package sarwadnya.mutkule.CoinSense.businesslogic.helpers;


import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Component
public class PdfHelper {
    public static byte[] ExportToPdf(List<String[]> data, List<String> headers, String user){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        try{
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.BLUE);
            Paragraph title = new Paragraph("Monthly Coinsense Expense Report For : " + user);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            PdfPTable table = new PdfPTable(headers.size());
            table.setWidthPercentage(100);
            int col = table.getNumberOfColumns();
            table.setWidths(new float[] {2, 3, 3});
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE);
            for(String header : headers){
                PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
                cell.setBackgroundColor(Color.GRAY);
                cell.setPadding(5);
                table.addCell(cell);
            }
            for(String[] str : data){
                for(String s : str){
                    table.addCell(new Phrase(s));
                }
            }
            document.add(table);
        } catch(Exception ex){
            throw new RuntimeException("Error in generating PDF from Expense Data", ex);
        } finally {
            document.close();
        }
        return outputStream.toByteArray();
    }
}

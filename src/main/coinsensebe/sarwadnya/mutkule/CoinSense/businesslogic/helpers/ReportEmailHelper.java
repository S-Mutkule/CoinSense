package sarwadnya.mutkule.CoinSense.businesslogic.helpers;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReportEmailHelper {
    @Autowired
    private JavaMailSender javaMailSender;

    public void SendEmailWithPdfAttachment(String to ,String subject, String text, byte[] pdfData,
                                           String filename){
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom("sarwadnyamutkule99@gmail.com");
            mimeMessageHelper.setText(text);
            mimeMessageHelper.setSubject(subject);
            ByteArrayResource byteArrayResource = new ByteArrayResource(pdfData);
            mimeMessageHelper.addAttachment(filename, byteArrayResource, "application/pdf");
            javaMailSender.send(mimeMessage);
        } catch(Exception ex){
            log.error("Error in sending email with pdf attachment", ex);
        }
    }
}

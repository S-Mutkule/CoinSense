package sarwadnya.mutkule.CoinSenseApp.businesslogic.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.cache.MemoryCache;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.encryption.EncryptionInterface;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.mappers.MongoUserMapper;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.repository.MongoRepo;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.dbentity.User;

import java.util.Properties;
import java.util.UUID;

@Component
public class AccountHelper {

    @Autowired
    private EncryptionInterface encryptionInterface;
    @Autowired
    private TokenHelper tokenHelper;
    @Autowired
    private MongoRepo mongoRepo;
    @Autowired
    private MemoryCache memoryCache;
    @Autowired
    private MongoUserMapper mongoUserMapper;

    public void sendEmailForPasswordReset(String email){
        String tokenString = generateToken();
        tokenHelper.persistToken(tokenString, 100, email);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText("this is the reset link : http://localhost:8080/changePassword" +
                "?token=" + tokenString +"&username="+email);
        simpleMailMessage.setSubject("CoinSense Password Reset");
        simpleMailMessage.setFrom("sarwadnyacoding@gmail.com");
        getJavaMailSender().send(simpleMailMessage);
    }

    public boolean checkTokenValidity(String token, String user){
        return tokenHelper.checkTokenExistsForUser(token, user);
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("sarwadnyacoding@gmail.com");
        mailSender.setPassword(System.getenv("GMAIL_APP_PASSWORD"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    private String generateToken(){
        return UUID.randomUUID().toString();
    }

    public void changePassword(String username, String newPassword){
        User user = memoryCache.getUsers().get(username);
        mongoRepo.deleteByusername(username);
        user.setPassword(encryptionInterface.bCryptPasswordEncoder().encode(newPassword));
        mongoRepo.save(mongoUserMapper.mapUserToMongoUser(user));
    }
}

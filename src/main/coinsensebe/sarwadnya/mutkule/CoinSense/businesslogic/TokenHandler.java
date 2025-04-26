package sarwadnya.mutkule.CoinSense.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSense.businesslogic.repository.MongoRepo;
import sarwadnya.mutkule.CoinSense.businesslogic.repository.MongoTokenRepo;
import sarwadnya.mutkule.CoinSense.models.PasswordResetToken;

import java.time.LocalDateTime;


@Component
public class TokenHandler {
    @Autowired
    private MongoTokenRepo mongoTokenRepo;
    @Autowired
    private MongoRepo mongoRepo;

    public void persistToken(String token, int expiryMinutes, String user){
        if(mongoTokenRepo.existsByuser(user)){
            mongoTokenRepo.deleteByuser(user);
        }
        mongoTokenRepo.save(generateTokenObject(token, expiryMinutes, user));
    }

    public boolean checkTokenExistsForUser(String token, String user){
        PasswordResetToken passwordResetToken = mongoTokenRepo.findByuser(user);
        return passwordResetToken!=null && token.equals(passwordResetToken.getToken());
    }

    private PasswordResetToken generateTokenObject(String token, int expiryMinutes, String user){
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setExpiresAfter(LocalDateTime.now().plusMinutes(expiryMinutes));
        passwordResetToken.setUser(user);
        return passwordResetToken;
    }
}

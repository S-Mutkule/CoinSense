package sarwadnya.mutkule.CoinSenseApp.businesslogic.encryption;

import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncryptionInterface {


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Transactional
    public String encrypt(String string){
        return bCryptPasswordEncoder().encode(string);
    }
    public boolean checkPassword(String p1, String p2){
        return bCryptPasswordEncoder().matches(p1, p2);
    }
}

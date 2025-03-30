package sarwadnya.mutkule.CoinSense.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSense.businesslogic.repository.SQLRepository;
import sarwadnya.mutkule.CoinSense.models.dbentity.User;

@Component
public class LoginHelper {
    @Autowired
    private SQLRepository repository;
    public boolean CheckUserExists(String username){
        User user = repository.findByusername(username);
        return user != null;
    }
}

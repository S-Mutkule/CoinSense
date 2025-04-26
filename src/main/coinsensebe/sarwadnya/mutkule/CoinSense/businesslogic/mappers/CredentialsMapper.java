package sarwadnya.mutkule.CoinSense.businesslogic.mappers;

import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSense.businesslogic.models.UserCredentials;
import sarwadnya.mutkule.CoinSense.businesslogic.models.dbentity.User;

@Component
public class CredentialsMapper {
    public User MapCredsToUser(UserCredentials userCredentials){
        return new User(userCredentials.getUsername(), userCredentials.getName(), userCredentials.getPassword());
    }
}

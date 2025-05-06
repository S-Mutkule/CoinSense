package sarwadnya.mutkule.CoinSenseApp.businesslogic.mappers;

import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.UserCredentials;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.dbentity.User;

@Component
public class CredentialsMapper {
    public User MapCredsToUser(UserCredentials userCredentials){
        return new User(userCredentials.getUsername(), userCredentials.getName(), userCredentials.getPassword());
    }
}

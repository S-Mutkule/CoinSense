package sarwadnya.mutkule.CoinSense.businesslogic.mappers;

import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSense.models.dbentity.MongoUser;
import sarwadnya.mutkule.CoinSense.models.dbentity.User;

@Component
public class MongoUserMapper {
    public User mapMongoObjToUser(MongoUser mongoUser){
        return new User(mongoUser.getUsername(), mongoUser.getName(), mongoUser.getPassword());
    }
}

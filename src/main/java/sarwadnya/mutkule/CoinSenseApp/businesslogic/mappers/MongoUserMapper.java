package sarwadnya.mutkule.CoinSenseApp.businesslogic.mappers;

import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.dbentity.MongoUser;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.dbentity.User;

@Component
public class MongoUserMapper {
    public User mapMongoObjToUser(MongoUser mongoUser){
        return new User(mongoUser.getUsername(), mongoUser.getName(), mongoUser.getPassword());
    }
    public MongoUser mapUserToMongoUser(User user){
        return new MongoUser(user.getUsername(), user.getName(), user.getPassword());
    }
}

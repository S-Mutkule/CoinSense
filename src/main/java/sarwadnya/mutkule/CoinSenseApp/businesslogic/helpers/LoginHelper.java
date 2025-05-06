package sarwadnya.mutkule.CoinSenseApp.businesslogic.helpers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.cache.MemoryCache;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.encryption.EncryptionInterface;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.mappers.MongoUserMapper;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.repository.MongoRepo;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.dbentity.MongoUser;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.dbentity.User;

@Component
@Slf4j
public class LoginHelper {
    @Autowired
    private EncryptionInterface encryptionInterface;
    @Autowired
    private MongoRepo mongoRepo;
    @Autowired
    private MemoryCache memoryCache;
    @Autowired
    private MongoUserMapper mongoUserMapper;

    public boolean CheckUserExists(String username){
        MongoUser userMongodb = mongoRepo.findByusername(username);
        boolean userFound = userMongodb!=null && !userMongodb.username.isEmpty();
        if(userFound){
            memoryCache.getUsers().put(username, mongoUserMapper.mapMongoObjToUser(userMongodb));
        }
        return userFound;
    }
    public boolean matchPassword(User user){
        MongoUser userDB = mongoRepo.findByusername(user.getUsername());
        return encryptionInterface.checkPassword(user.getPassword(), userDB.getPassword());
    }
}

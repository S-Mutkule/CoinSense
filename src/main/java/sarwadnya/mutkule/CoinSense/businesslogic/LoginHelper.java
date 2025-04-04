package sarwadnya.mutkule.CoinSense.businesslogic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSense.businesslogic.encryption.EncryptionInterface;
import sarwadnya.mutkule.CoinSense.businesslogic.repository.MongoRepo;
import sarwadnya.mutkule.CoinSense.businesslogic.repository.SQLRepository;
import sarwadnya.mutkule.CoinSense.models.dbentity.MongoUser;
import sarwadnya.mutkule.CoinSense.models.dbentity.User;

import java.util.List;

@Component
@Slf4j
public class LoginHelper {

    @Autowired
    private SQLRepository repository;
    @Autowired
    private EncryptionInterface encryptionInterface;
    @Autowired
    private MongoRepo mongoRepo;
    public boolean CheckUserExists(String username){
        MongoUser usermon = mongoRepo.findByusername(username);
        List<MongoUser> users = mongoRepo.findAll();
        for(MongoUser user : users){
            System.out.println(user.username + " " + user.password);
        }
        return usermon!=null && !usermon.username.isEmpty();
    }
    public boolean matchPassword(User user){
        MongoUser userDB = mongoRepo.findByusername(user.username);
        return encryptionInterface.checkPassword(user.password, userDB.password);
    }
}

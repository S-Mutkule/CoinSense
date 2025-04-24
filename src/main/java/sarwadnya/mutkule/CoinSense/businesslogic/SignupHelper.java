package sarwadnya.mutkule.CoinSense.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSense.businesslogic.encryption.EncryptionInterface;
import sarwadnya.mutkule.CoinSense.businesslogic.repository.MongoRepo;
import sarwadnya.mutkule.CoinSense.businesslogic.repository.SQLRepository;
import sarwadnya.mutkule.CoinSense.models.dbentity.MongoUser;
import sarwadnya.mutkule.CoinSense.models.dbentity.User;
@Component
public class SignupHelper {
   @Autowired
   private SQLRepository sqlRepository;
   @Autowired
   private MongoRepo mongoRepo;
   @Autowired
   private LoginHelper loginHelper;
   @Autowired
   private EncryptionInterface encryptionInterface;

   public boolean signupUser(User userTBI){
      try {
         MongoUser user_mongo = mongoRepo.save(new MongoUser(userTBI.getUsername(), userTBI.getName(), encryptionInterface.bCryptPasswordEncoder().encode(userTBI.getPassword())));
         return !user_mongo.username.isEmpty();
      } catch(Exception ex){
         return false;
      }
   }
}

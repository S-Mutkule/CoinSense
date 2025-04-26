package sarwadnya.mutkule.CoinSense.businesslogic.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSense.businesslogic.encryption.EncryptionInterface;
import sarwadnya.mutkule.CoinSense.businesslogic.repository.MongoRepo;
import sarwadnya.mutkule.CoinSense.businesslogic.models.dbentity.MongoUser;
import sarwadnya.mutkule.CoinSense.businesslogic.models.dbentity.User;
@Component
public class SignupHelper {
   @Autowired
   private MongoRepo mongoRepo;
   @Autowired
   private EncryptionInterface encryptionInterface;

   public boolean signupUser(User userTBI){
      try {
         MongoUser u = new MongoUser(userTBI.getUsername(), userTBI.getName(), encryptionInterface.bCryptPasswordEncoder().encode(userTBI.getPassword()));
         MongoUser user_mongo = mongoRepo.save(u);
         return !user_mongo.username.isEmpty();
      } catch(Exception ex){
         System.out.println("Errror" + ex);
         return false;
      }
   }
}

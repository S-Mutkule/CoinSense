package sarwadnya.mutkule.CoinSense.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

   public boolean signupUser(User userTBI){
      try {
         MongoUser user_mongo = mongoRepo.save(new MongoUser(userTBI.username, userTBI.name, userTBI.password));

         return !user_mongo.username.isEmpty();
      } catch(Exception ex){
         return false;
      }
   }

   public void changePassword(){

   }
}

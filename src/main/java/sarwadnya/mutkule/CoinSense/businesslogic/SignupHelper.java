package sarwadnya.mutkule.CoinSense.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSense.businesslogic.repository.SQLRepository;
import sarwadnya.mutkule.CoinSense.models.dbentity.User;
@Component
public class SignupHelper {
   @Autowired
   private SQLRepository sqlRepository;

   @Autowired
   private LoginHelper loginHelper;

   public boolean signupUser(User userTBI){
       User userInserted = sqlRepository.save(userTBI);
       return !userInserted.username.isEmpty();
   }
}

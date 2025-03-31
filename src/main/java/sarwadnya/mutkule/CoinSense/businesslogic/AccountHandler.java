package sarwadnya.mutkule.CoinSense.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSense.businesslogic.encryption.EncryptionInterface;
import sarwadnya.mutkule.CoinSense.models.dbentity.User;

@Component
public class AccountHandler {
    @Autowired
    private LoginHelper loginHelper;
    @Autowired
    private SignupHelper signupHelper;
    @Autowired
    private EncryptionInterface encryptionInterface;

    public boolean checkUserExists(User user){
        return loginHelper.CheckUserExists(user.username);
    }

    public boolean insertUserInDB(User user){
        user.password = encryptionInterface.bCryptPasswordEncoder().encode(user.password);
        return signupHelper.signupUser(user);
    }

    public boolean checkPassword(User user){
        return loginHelper.matchPassword(user);
    }
}

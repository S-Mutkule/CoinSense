package sarwadnya.mutkule.CoinSense.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSense.models.dbentity.User;

@Component
public class AccountHandler {
    @Autowired
    private LoginHelper loginHelper;
    @Autowired
    private SignupHelper signupHelper;

    public boolean checkUserExists(String username){
        return loginHelper.CheckUserExists(username);
    }

    public boolean insertUserinDB(User user){
        return signupHelper.signupUser(user);
    }

}

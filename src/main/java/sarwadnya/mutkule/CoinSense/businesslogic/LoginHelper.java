package sarwadnya.mutkule.CoinSense.businesslogic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSense.businesslogic.repository.SQLRepository;
import sarwadnya.mutkule.CoinSense.models.dbentity.User;

import java.util.List;

@Component
@Slf4j
public class LoginHelper {

    @Autowired
    private SQLRepository repository;
    public boolean CheckUserExists(String username){
        System.out.println("Getting all the records");
        List<User> users = (List<User>) repository.findAll();
        for(User user : users){
            System.out.println(user.username);
        }
        User user = repository.findByusername(username);
        return user != null;
    }
}

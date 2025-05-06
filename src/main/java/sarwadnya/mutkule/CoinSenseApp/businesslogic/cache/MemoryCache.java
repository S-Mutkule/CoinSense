package sarwadnya.mutkule.CoinSenseApp.businesslogic.cache;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.dbentity.User;

import java.util.HashMap;

@Component
@Scope("singleton")
public class MemoryCache {
    @Getter @Setter
    private HashMap<String, User> users = new HashMap<>();
}

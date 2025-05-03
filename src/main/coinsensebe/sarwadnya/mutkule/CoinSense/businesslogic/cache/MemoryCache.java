package sarwadnya.mutkule.CoinSense.businesslogic.cache;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSense.businesslogic.models.dbentity.User;

import java.util.HashMap;

@Component
@Scope("singleton")
public class MemoryCache {
    @Getter @Setter
    private HashMap<String, User> users = new HashMap<>();
}

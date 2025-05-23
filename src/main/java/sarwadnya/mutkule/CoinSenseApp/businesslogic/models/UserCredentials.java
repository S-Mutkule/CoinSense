package sarwadnya.mutkule.CoinSenseApp.businesslogic.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials {
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String name;
}

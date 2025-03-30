package sarwadnya.mutkule.CoinSense.models;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class SignupPage {

    @Getter @Setter
    private String name;
    @Id @Getter @Setter
    private String username;
    @Getter @Setter
    private String password;
}

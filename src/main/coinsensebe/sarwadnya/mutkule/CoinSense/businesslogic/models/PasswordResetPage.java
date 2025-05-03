package sarwadnya.mutkule.CoinSense.businesslogic.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetPage {
    @Getter
    @Setter
    public String emailID;
}

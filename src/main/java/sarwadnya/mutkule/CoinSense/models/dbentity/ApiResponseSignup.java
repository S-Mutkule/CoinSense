package sarwadnya.mutkule.CoinSense.models.dbentity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseSignup {
    @Getter @Setter
    private String username;
    @Getter @Setter
    private int status;

}

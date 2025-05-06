package sarwadnya.mutkule.CoinSenseApp.businesslogic.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseLogin {
    @Getter
    @Setter
    private String body;
    @Getter
    @Setter
    private int status;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String user;
}

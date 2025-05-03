package sarwadnya.mutkule.CoinSense.businesslogic.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordPage {
    @Getter
    @Setter
    private String newPassword;
    @Getter
    @Setter
    private String confirmNewPassword;
    @Getter
    @Setter
    private String username;
}

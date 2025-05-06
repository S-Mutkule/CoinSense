package sarwadnya.mutkule.CoinSenseApp.businesslogic.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Document("Tokens")
public class PasswordResetToken {
    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    @Id
    private String user;
    @Getter
    @Setter
    private LocalDateTime expiresAfter;
}

package sarwadnya.mutkule.CoinSense.businesslogic.models.dbentity;

import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("USERS")
@NoArgsConstructor
@AllArgsConstructor
public class MongoUser {

    @Id
    @Indexed(unique = true)
    @Setter
    @Getter
    public String username;

    @Setter
    @Getter
    public String name;
    @Setter
    @Getter
    public String password;
}

package sarwadnya.mutkule.CoinSense.models.dbentity;

import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("USERS")
@NoArgsConstructor
@AllArgsConstructor
public class MongoUser {

    @Id
    @Indexed(unique = true)
    public String username;

    public String name;

    public String password;
}

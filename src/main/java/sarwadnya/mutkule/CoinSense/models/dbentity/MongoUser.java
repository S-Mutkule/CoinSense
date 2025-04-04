package sarwadnya.mutkule.CoinSense.models.dbentity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("EXPENSES")
@NoArgsConstructor
@AllArgsConstructor
public class MongoUser {

    @Id
    public String username;

    public String name;

    public String password;
}

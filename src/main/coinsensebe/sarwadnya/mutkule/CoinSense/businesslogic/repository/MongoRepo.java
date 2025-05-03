package sarwadnya.mutkule.CoinSense.businesslogic.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sarwadnya.mutkule.CoinSense.businesslogic.models.dbentity.MongoUser;

public interface MongoRepo extends MongoRepository<MongoUser, String> {
    MongoUser findByusername(String username);
    void deleteByusername(String username);
}

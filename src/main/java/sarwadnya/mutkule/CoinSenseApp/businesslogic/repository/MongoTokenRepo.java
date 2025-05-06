package sarwadnya.mutkule.CoinSenseApp.businesslogic.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.PasswordResetToken;

public interface MongoTokenRepo extends MongoRepository<PasswordResetToken, Long> {
    public PasswordResetToken findByuser(String user);
    public boolean existsByuser(String user);
    public void deleteByuser(String user);
}

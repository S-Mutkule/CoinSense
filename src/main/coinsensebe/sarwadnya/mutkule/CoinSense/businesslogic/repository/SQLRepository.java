package sarwadnya.mutkule.CoinSense.businesslogic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sarwadnya.mutkule.CoinSense.businesslogic.models.dbentity.User;

@Repository
public interface SQLRepository extends CrudRepository<User, Long> {
    User findByusername(String username);
}

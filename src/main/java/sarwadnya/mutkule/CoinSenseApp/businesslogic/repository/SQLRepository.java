package sarwadnya.mutkule.CoinSenseApp.businesslogic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.dbentity.User;

@Repository
public interface SQLRepository extends CrudRepository<User, Long> {
    User findByusername(String username);
}

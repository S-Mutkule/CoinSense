package sarwadnya.mutkule.CoinSense.businesslogic.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import sarwadnya.mutkule.CoinSense.businesslogic.models.dbentity.Expense;

public interface MongoExpenseRepository extends MongoRepository<Expense, Long> {
    public Expense findByusername(String username);
    public void deleteByusername(String username);
}

package sarwadnya.mutkule.CoinSenseApp.businesslogic.models.dbentity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document("EXPENSES")
public class Expense {
    @Id
    private String username;

    private List<ExpenseObj> expensesList;
}


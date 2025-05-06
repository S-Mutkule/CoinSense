package sarwadnya.mutkule.CoinSenseApp.businesslogic.models.dbentity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseObj{
    private String dateOfExpense;

    private String expenseType;

    private int amount;
}

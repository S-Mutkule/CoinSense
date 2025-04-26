package sarwadnya.mutkule.CoinSense.models.dbentity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import sarwadnya.mutkule.CoinSense.enums.ExpenseTypeEnum;

import java.util.HashMap;
import java.util.Map;


@Document("EXPENSE")
public class Expense {
    @Id
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private Map<ExpenseTypeEnum, Integer> expensesList;

    public Expense(){
        expensesList = new HashMap<>();
        expensesList.putAll(Map.of(
                ExpenseTypeEnum.HOUSING,0,
                ExpenseTypeEnum.UTILITY, 0,
                ExpenseTypeEnum.TRANSPORT, 0,
                ExpenseTypeEnum.GROCERIES, 0,
                ExpenseTypeEnum.DINEOUT, 0
        ));
        expensesList.putAll(Map.of(
                ExpenseTypeEnum.ENTERTAINMENT, 0,
                ExpenseTypeEnum.HEALTH_FITNESS, 0,
                ExpenseTypeEnum.EDUCATION, 0,
                ExpenseTypeEnum.CLOTHING, 0,
                ExpenseTypeEnum.SAVINGS, 0,
                ExpenseTypeEnum.INVESTMENT, 0
        ));
    }
}

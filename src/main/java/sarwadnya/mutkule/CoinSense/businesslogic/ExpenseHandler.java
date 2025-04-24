package sarwadnya.mutkule.CoinSense.businesslogic;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSense.businesslogic.enums.ExpenseTypeEnum;

import java.util.HashMap;
import java.util.Map;

@Component
public class ExpenseHandler {

        @Getter
        @Setter
        private Map<ExpenseTypeEnum, Integer> summary;

        @Getter
        @Setter
        private int totalExpenditure;

        public ExpenseHandler(){
            summary = new HashMap<>();
            totalExpenditure = 0;
        }

        public void AddExpense(ExpenseTypeEnum expenseType, int amount){
                summary.put(expenseType, summary.get(expenseType) + amount);
                totalExpenditure += amount;
        }

        public int GetTotalExpense(){
                return totalExpenditure;
        }
}

package sarwadnya.mutkule.CoinSense.businesslogic;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ExpenseHandler {

        @Getter
        @Setter
        private Map<SummaryItemsEnum, Integer> summary;

        public ExpenseHandler(){
            summary = new HashMap<>();
        }
}

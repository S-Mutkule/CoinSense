package sarwadnya.mutkule.CoinSense.businesslogic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sarwadnya.mutkule.CoinSense.businesslogic.helpers.ExpenseHelper;
import sarwadnya.mutkule.CoinSense.businesslogic.models.UserCredentials;
import sarwadnya.mutkule.CoinSense.businesslogic.models.dbentity.Expense;

@Controller
public class ExpenseController {
    @Autowired
    private ExpenseHelper expenseHelper;

    @PostMapping("/expenses")
    public ResponseEntity<String> HandleExpenses(@RequestBody Expense expense){
        if(expenseHelper.PersistExpenseToDB(expense)) {
            System.out.println("Data Persistence Complete");
            return ResponseEntity.ok("Data Persistence Success!!");
        }
        return ResponseEntity.ok("Error in persisting data");
    }
    @PostMapping("/fetchExpenses")
    public ResponseEntity<Expense> FetchAllExpenses(@RequestBody UserCredentials userCredentials){
        Expense expense = expenseHelper.GetAllExpensesForUser(userCredentials.getUsername());
        return ResponseEntity.ok(expense);
    }
}

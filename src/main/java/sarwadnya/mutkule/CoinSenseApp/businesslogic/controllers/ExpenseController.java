package sarwadnya.mutkule.CoinSenseApp.businesslogic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.helpers.ExpenseHelper;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.UserCredentials;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.dbentity.Expense;

@Controller
public class ExpenseController {
    @Autowired
    private ExpenseHelper expenseHelper;

    @CrossOrigin(origins = {
            "http://localhost:3000",
            "https://coinsense-li5k.onrender.com"
    })
    @PostMapping("/expenses")
    public ResponseEntity<String> HandleExpenses(@RequestBody Expense expense){
        if(expenseHelper.PersistExpenseToDB(expense)) {
            System.out.println("Data Persistence Complete");
            return ResponseEntity.ok("Data Persistence Success!!");
        }
        return ResponseEntity.ok("Error in persisting data");
    }
    @CrossOrigin(origins = {
            "http://localhost:3000",
            "https://coinsense-li5k.onrender.com"
    })
    @PostMapping("/fetchExpenses")
    public ResponseEntity<Expense> FetchAllExpenses(@RequestBody UserCredentials userCredentials) {
        Expense expense = expenseHelper.GetAllExpensesForUser(userCredentials.getUsername());
        return ResponseEntity.ok(expense);
    }
}

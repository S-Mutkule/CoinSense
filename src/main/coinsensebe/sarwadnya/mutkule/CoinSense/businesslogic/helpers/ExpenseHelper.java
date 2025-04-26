package sarwadnya.mutkule.CoinSense.businesslogic.helpers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSense.businesslogic.models.dbentity.Expense;
import sarwadnya.mutkule.CoinSense.businesslogic.models.dbentity.ExpenseObj;
import sarwadnya.mutkule.CoinSense.businesslogic.repository.MongoExpenseRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExpenseHelper {
    /* Steps to be followed
    * first check if any entry is already present with the username received
    * if so then fetch it change the object, delete the entry from the DB and persist the new object
    * if not just persist.
    * TODO : add functionality to update the object in the DB itself using the repository class. Will be done in Version 4
    * */
    @Autowired
    private MongoExpenseRepository mongoExpenseRepository;
    public boolean PersistExpenseToDB(Expense expense){
        try {
            Expense fetchedDbExpenseObj = mongoExpenseRepository.findByusername(expense.getUsername());
            if (fetchedDbExpenseObj == null) {
                mongoExpenseRepository.save(expense);
            } else{
                List<ExpenseObj> expenseObjs = expense.getExpensesList();
                expenseObjs.addAll(fetchedDbExpenseObj.getExpensesList());
                fetchedDbExpenseObj.setExpensesList(expenseObjs);
                mongoExpenseRepository.deleteByusername(expense.getUsername());
                mongoExpenseRepository.save(fetchedDbExpenseObj);
            }
            return true;
        } catch(Exception ex){
            System.out.println("exception occurred in method PersistExpenseToDB with the meesage " + ex.getMessage());
            return false;
        }
    }
    public Expense GetAllExpensesForUser(String username){
        Expense fetchedExpenses = mongoExpenseRepository.findByusername(username);
        if(fetchedExpenses == null){
            return new Expense();
        } else{
            return fetchedExpenses;
        }
    }
}

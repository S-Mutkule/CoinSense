package sarwadnya.mutkule.CoinSense.businesslogic.helpers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sarwadnya.mutkule.CoinSense.businesslogic.models.dbentity.Expense;
import sarwadnya.mutkule.CoinSense.businesslogic.models.dbentity.ExpenseObj;
import sarwadnya.mutkule.CoinSense.businesslogic.models.dbentity.MongoUser;
import sarwadnya.mutkule.CoinSense.businesslogic.repository.MongoExpenseRepository;
import sarwadnya.mutkule.CoinSense.businesslogic.repository.MongoRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
    @Autowired
    private PdfHelper pdfHelper;
    @Autowired
    private MongoRepo mongoRepo;
    @Autowired
    private ReportEmailHelper reportEmailHelper;
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
    @Scheduled(cron = "0 0 0 1  * ?")
    public void monthlyReport() {
        List<MongoUser> mongoUserList = mongoRepo.findAll();
        for(MongoUser user : mongoUserList){
            if(!SendExpenseReportToUser(user.getUsername()))
                log.error("Mail Sending Failed for User : " + user.getUsername());
        }
        System.out.println("Executing monthly job: " + LocalDateTime.now());

    }
    public boolean SendExpenseReportToUser(String username){
        boolean bool = false;
        try{
            Expense fetchedExpenses = mongoExpenseRepository.findByusername(username);
            MongoUser user = mongoRepo.findByusername(username);
            List<String[]> expenseList = fetchedExpenses.getExpensesList().stream()
                    .map(expense -> new String[]{
                            expense.getDateOfExpense(),
                            expense.getExpenseType(),
                            String.valueOf(expense.getAmount())
                    }).toList();
            String[] headers = {"Date", "Category", "Amount"};
            byte[] arr = pdfHelper.ExportToPdf(expenseList, Arrays.stream(headers).toList(), user.getName());
            reportEmailHelper.SendEmailWithPdfAttachment(username, "Monthly Expense Report", "", arr, "Expense");
            return arr.length > 0;
        } catch(Exception ex){
            log.error("Exception while sending the report to the user", ex);
            return false;
        }
    }
}

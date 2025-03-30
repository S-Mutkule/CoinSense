package sarwadnya.mutkule.CoinSense.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sarwadnya.mutkule.CoinSense.businesslogic.AccountHandler;
import sarwadnya.mutkule.CoinSense.models.LoginPage;
import sarwadnya.mutkule.CoinSense.models.SignupPage;
import sarwadnya.mutkule.CoinSense.models.dbentity.User;

import java.util.Map;

@Controller
@Slf4j
public class ExpenseTrackerController {
    @Autowired
    AccountHandler accountHandler;

    @GetMapping("/expenseTracker/home")
    public String ExpenseTracker(){
        return "expensetracker";
    }

    @GetMapping("/loginPage")
    public String GetLoginPage(Model model){
        LoginPage loginPage = new LoginPage();
        model.addAttribute("loginPage", loginPage);
        return "loginpage";
    }

    @PostMapping("/loginPage/login")
    public String LogUserIn(@RequestParam Map<String, String> map){
        if(accountHandler.checkUserExists(map.get("username")))
            return "";
        else
            return "usernotfound";
    }

    @GetMapping("/signup")
    public String SignupPage(Model model){
        SignupPage signupPage = new SignupPage();
        model.addAttribute("signupPage", signupPage);
        return "signup";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> Signup(@RequestParam Map<String, String> map){
        User user = new User(map.get("name"), map.get("username"), map.get("password"));
        if(accountHandler.insertUserinDB(user))
            return new ResponseEntity<>(map.get("username") + " has been inserted", HttpStatusCode.valueOf(200));
        else
            return new ResponseEntity<>("error in user insertion", HttpStatusCode.valueOf(500));
    }

}

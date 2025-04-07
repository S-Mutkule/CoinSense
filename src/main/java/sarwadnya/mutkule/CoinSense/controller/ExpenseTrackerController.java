package sarwadnya.mutkule.CoinSense.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sarwadnya.mutkule.CoinSense.businesslogic.AccountHandler;
import sarwadnya.mutkule.CoinSense.businesslogic.LoginHelper;
import sarwadnya.mutkule.CoinSense.businesslogic.LoginResponseEnum;
import sarwadnya.mutkule.CoinSense.models.ChangePasswordPage;
import sarwadnya.mutkule.CoinSense.models.LoginPage;
import sarwadnya.mutkule.CoinSense.models.PasswordResetPage;
import sarwadnya.mutkule.CoinSense.models.SignupPage;
import sarwadnya.mutkule.CoinSense.models.dbentity.User;

import java.util.Map;

@Controller
@Slf4j
public class ExpenseTrackerController {
    @Autowired
    private AccountHandler accountHandler;
    @Autowired
    private LoginHelper loginHelper;

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
        User user = getUserObject(map);
        LoginResponseEnum loginResponse = accountHandler.login(user);
        switch (loginResponse){
            case SUCCESS -> {
                return "welcomepage";
            }
            case INVALIDUSERNAME -> {
                return "usernotfound";
            }
            case INVALIDPASSWORD ->  {
                return "incorrectpasswordpage";
            }
            default -> {
                return "";
            }
        }
    }

    @GetMapping("/signup")
    public String SignupPage(Model model){
        SignupPage signupPage = new SignupPage();
        model.addAttribute("signupPage", signupPage);
        return "signup";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> Signup(@RequestParam Map<String, String> map){
        User user = getUserObject(map);
        if(accountHandler.insertUserInDB(user))
            return new ResponseEntity<>(map.get("username") + " has been inserted",
                    HttpStatusCode.valueOf(200));
        else
            return new ResponseEntity<>("error in user insertion",
                    HttpStatusCode.valueOf(500));
    }

    @GetMapping("/resetpassword")
    public String ResetPasswordPage(Model model){
        PasswordResetPage passwordResetPage = new PasswordResetPage();
        model.addAttribute("resetpassword", passwordResetPage);
        return "resetpassword";
    }

    @PostMapping("/resetpassword/sendlink")
    public ResponseEntity<String> ResetPassword(@RequestParam Map<String, String> map){
        String user = map.get("username");
        if(loginHelper.CheckUserExists(user)) {
            accountHandler.sendEmailForPasswordReset(user);
            return new ResponseEntity<>("Link sent. Please check your email",
                    HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>("User not found, please signup",
                HttpStatusCode.valueOf(200));
    }

    @GetMapping("/changePassword")
    public String changePassword(@RequestParam String token, @RequestParam String username,
                                 Model model){
        if(accountHandler.checkTokenValidity(token, username)) {
            ChangePasswordPage changePasswordPage = new ChangePasswordPage();
            changePasswordPage.setUsername(username);
            model.addAttribute("changepasswordpage", changePasswordPage);
            return "changepasswordpage";
        }
        return "invalidtokenpage";
    }
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassowrd(@RequestParam Map<String, String> map){
        accountHandler.changePassword(map.get("username"), map.get("newPassword"));
        return new ResponseEntity<>("Working on your request", HttpStatusCode.valueOf(200));
    }
    private User getUserObject(Map<String, String> map){
        return new User(map.get("username"), map.get("name"), map.get("password"));
    }
}

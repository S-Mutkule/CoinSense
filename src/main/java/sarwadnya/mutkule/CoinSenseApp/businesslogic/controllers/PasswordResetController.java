package sarwadnya.mutkule.CoinSenseApp.businesslogic.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.helpers.AccountHelper;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.helpers.LoginHelper;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.ApiResponseLogin;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.ChangePasswordPage;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.PasswordResetPage;

import java.util.Map;

@Controller
@Slf4j
public class PasswordResetController {
    @Autowired
    private AccountHelper accountHelper;
    @Autowired
    private LoginHelper loginHelper;

    @CrossOrigin(origins = {
            "http://localhost:3000",
            "https://coinsense-li5k.onrender.com"
    })
    @GetMapping("/expenseTracker/home")
    public String ExpenseTracker(){
        return "expensetracker";
    }

    @CrossOrigin(origins = {
            "http://localhost:3000",
            "https://coinsense-li5k.onrender.com"
    })
    @GetMapping("/resetpassword")
    public String ResetPasswordPage(Model model){
        PasswordResetPage passwordResetPage = new PasswordResetPage();
        model.addAttribute("resetpassword", passwordResetPage);
        return "resetpassword";
    }

    @CrossOrigin(origins = {
            "http://localhost:3000",
            "https://coinsense-li5k.onrender.com"
    })
    @PostMapping("/resetpassword/sendlink")
    public ResponseEntity<String> ResetPassword(@RequestBody PasswordResetPage passwordResetPage){
        String user = passwordResetPage.getEmailID();
        if(loginHelper.CheckUserExists(user)) {
            accountHelper.sendEmailForPasswordReset(user);
            return new ResponseEntity<>("Link sent. Please check your email",
                    HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>("User not found, please signup",
                HttpStatusCode.valueOf(200));
    }

    @GetMapping("/changePassword")
    public ResponseEntity<ApiResponseLogin> changePassword(@RequestParam String token, @RequestParam String username,
                                                           Model model){
        if(accountHelper.checkTokenValidity(token, username)) {
            ChangePasswordPage changePasswordPage = new ChangePasswordPage();
            changePasswordPage.setUsername(username);
            model.addAttribute("changepasswordpage", changePasswordPage);
            return new ResponseEntity<ApiResponseLogin>(new ApiResponseLogin("Redirecting to new password entry page", 200, username, ""), HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<ApiResponseLogin>(new ApiResponseLogin("Invalid Token", 401, username, ""), HttpStatusCode.valueOf(401));
    }
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassowrd(@RequestParam Map<String, String> map){
        accountHelper.changePassword(map.get("username"), map.get("newPassword"));
        return new ResponseEntity<>("Working on your request", HttpStatusCode.valueOf(200));
    }
}

/*
* @GetMapping("/loginPage")
    public String GetLoginPage(Model model){
        UserCredentials userCredentials = new UserCredentials();
        model.addAttribute("loginPage", userCredentials);
        return "loginpage";
    }

    @GetMapping("/signup")
    public String SignupPage(Model model){
        UserCredentials userCredentials = new UserCredentials();
        model.addAttribute("signupPage", userCredentials);
        return "signup";
    }
* */

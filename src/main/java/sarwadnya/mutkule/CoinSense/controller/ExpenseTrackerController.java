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
import sarwadnya.mutkule.CoinSense.models.*;
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

    @PostMapping("/signup")
    public ResponseEntity<String> Signup(@RequestBody UserCredentials signupPage){
        User user = new User();
        user.setUsername(signupPage.getUsername());
        user.setPassword(signupPage.getPassword());
        user.setName(signupPage.getName());
        if(accountHandler.insertUserInDB(user))
            return new ResponseEntity<>(signupPage.getUsername() + " has been inserted",
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
    public ResponseEntity<String> ResetPassword(@RequestBody PasswordResetPage passwordResetPage){
        String user = passwordResetPage.getEmailID();
        if(loginHelper.CheckUserExists(user)) {
            accountHandler.sendEmailForPasswordReset(user);
            return new ResponseEntity<>("Link sent. Please check your email",
                    HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>("User not found, please signup",
                HttpStatusCode.valueOf(200));
    }

    @GetMapping("/changePassword")
    public ResponseEntity<ApiResponseLogin> changePassword(@RequestParam String token, @RequestParam String username,
                                 Model model){
        if(accountHandler.checkTokenValidity(token, username)) {
            ChangePasswordPage changePasswordPage = new ChangePasswordPage();
            changePasswordPage.setUsername(username);
            model.addAttribute("changepasswordpage", changePasswordPage);
            return new ResponseEntity<ApiResponseLogin>(new ApiResponseLogin("Redirecting to new password entry page", 200, username), HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<ApiResponseLogin>(new ApiResponseLogin("Invalid Token", 401, username), HttpStatusCode.valueOf(401));
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

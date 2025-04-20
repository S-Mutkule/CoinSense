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
import sarwadnya.mutkule.CoinSense.models.*;
import sarwadnya.mutkule.CoinSense.models.dbentity.User;

import java.net.http.HttpResponse;
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
    public ResponseEntity<ApiResponseLogin> LogUserIn(@RequestBody LoginPage loginPage){
        User user = new User();
        user.setUsername(loginPage.getUsername());
        user.setPassword(loginPage.getPassword());
        user.setName(loginPage.getName());
        LoginResponseEnum loginResponse = accountHandler.login(user);
        switch (loginResponse){
            case SUCCESS -> {
                return ResponseEntity.ok(
                        new ApiResponseLogin("logged in", 200)
                );
            }
            case INVALIDUSERNAME -> {
                return ResponseEntity.ok(
                        new ApiResponseLogin("wrong username", 401)
                );
            }
            case INVALIDPASSWORD ->  {
                return ResponseEntity.ok(
                        new ApiResponseLogin("wrong password", 401)
                );
            }
            default -> {
                return ResponseEntity.ok(null);
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
    public ResponseEntity<String> Signup(@RequestBody SignupPage signupPage){
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

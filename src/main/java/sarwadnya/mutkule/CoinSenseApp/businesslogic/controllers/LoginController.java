package sarwadnya.mutkule.CoinSenseApp.businesslogic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.helpers.LoginHelper;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.cache.MemoryCache;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.mappers.CredentialsMapper;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.ApiResponseLogin;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.UserCredentials;
import sarwadnya.mutkule.CoinSenseApp.businesslogic.models.dbentity.User;


@Controller
public class LoginController {

    @Autowired
    CredentialsMapper credentialsMapper;
    @Autowired
    LoginHelper loginHelper;
    @Autowired
    MemoryCache memoryCache;

    @CrossOrigin(origins = {
            "http://localhost:3000",
            "https://coinsense-li5k.onrender.com"
    })
    @PostMapping("/loginPage/login")
    public ResponseEntity<ApiResponseLogin> LogUserIn(@RequestBody UserCredentials userCredentials){
        User userClient = credentialsMapper.MapCredsToUser(userCredentials);
        boolean userExists = loginHelper.CheckUserExists(userClient.getUsername());
        if(!userExists){
            return new ResponseEntity<ApiResponseLogin>(new ApiResponseLogin("Wrong Username", 401, "", ""), HttpStatusCode.valueOf(200));
        } else{
            User user = memoryCache.getUsers().get(userClient.getUsername());
            if(loginHelper.matchPassword(userClient)){
                return new ResponseEntity<ApiResponseLogin>(new ApiResponseLogin("SUCCESS", 200, user.getName(), user.getUsername()), HttpStatusCode.valueOf(200));
            } else{
                return new ResponseEntity<ApiResponseLogin>(new ApiResponseLogin("INCORRECT PASSWORD", 401, "", ""), HttpStatusCode.valueOf(401));
            }
        }
    }
}

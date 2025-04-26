package sarwadnya.mutkule.CoinSense.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sarwadnya.mutkule.CoinSense.businesslogic.SignupHelper;
import sarwadnya.mutkule.CoinSense.businesslogic.mappers.CredentialsMapper;
import sarwadnya.mutkule.CoinSense.models.UserCredentials;
import sarwadnya.mutkule.CoinSense.models.dbentity.ApiResponseSignup;
import sarwadnya.mutkule.CoinSense.models.dbentity.User;

@Controller
public class SignupController {

    @Autowired
    private CredentialsMapper credentialsMapper;

    @Autowired
    private SignupHelper signupHelper;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseSignup> Signup(@RequestBody UserCredentials userCredentials){
        User user = credentialsMapper.MapCredsToUser(userCredentials);
        if(signupHelper.signupUser(user))
            return new ResponseEntity<>(new ApiResponseSignup(userCredentials.getUsername(), 200),
                    HttpStatusCode.valueOf(200));
        else
            return new ResponseEntity<>(new ApiResponseSignup("", 500),
                    HttpStatusCode.valueOf(200));
    }
}

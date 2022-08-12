package com.se.democognitoauthentication;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.ListUsersRequest;
import com.amazonaws.services.cognitoidp.model.UserType;
import com.se.democognitoauthentication.model.AuthenticationRequest;
import com.se.democognitoauthentication.model.UserSignUpRequest;
import com.se.democognitoauthentication.security.SpringSecurityUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoCognitoAuthenticationApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoCognitoAuthenticationApplication.class, args);
    }

    private static String USER_NAME= "evad4";
    private static  String MAIL = "eugene.s@it-dimension.com";
    private static  String PASSWORD = "qwe123456";

    @Override
    public void run(String... args) throws Exception {


        try {
            DemoHelper demoHelper = new DemoHelper();


            AWSCognitoIdentityProvider awsCognitoIdentityProvider = demoHelper.getAmazonCognitoIdentityClient();


            UserSignUpRequest signUpRequest = new UserSignUpRequest();
            signUpRequest.setUsername(USER_NAME);
            signUpRequest.setAgreementFlag("");
            signUpRequest.setEmail(MAIL);
            signUpRequest.setLastname("Last name ");
            signUpRequest.setPassword(PASSWORD);
            signUpRequest.setName("NAME");
            signUpRequest.setAgreementFlag("aggreement flag");
            signUpRequest.setPhoneNumber("+380999999999");
            signUpRequest.setCompanyName("CompanyName");
            signUpRequest.setBrokerID("brokerID");
            signUpRequest.setCompanyPosition("CompanyPosition");


            // create USer
            UserType userType =  demoHelper.signUp(signUpRequest);

            AuthenticationRequest authenticationRequest = new AuthenticationRequest();
            authenticationRequest.setPassword(PASSWORD);
            authenticationRequest.setUsername(MAIL);


            SpringSecurityUser springSecurityUser =  demoHelper.authenticate(authenticationRequest);

            int a = 0;
        }
        catch (Exception ex) {
         ex.printStackTrace();
            int a = 10;
        }
    }
}

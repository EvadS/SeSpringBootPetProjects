package com.se.democognitoauthentication;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;
import com.se.democognitoauthentication.config.CognitoConfig;
import com.se.democognitoauthentication.exception.CognitoException;
import com.se.democognitoauthentication.model.AuthenticationRequest;
import com.se.democognitoauthentication.model.UserResponse;
import com.se.democognitoauthentication.model.UserSignUpRequest;
import com.se.democognitoauthentication.security.SpringSecurityUser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoHelper {

    /**
     * New password key
     */
    private static final String NEW_PASS_WORD = "NEW_PASSWORD";
    /**
     * New password required challenge key
     */
    private static final String NEW_PASS_WORD_REQUIRED = "NEW_PASSWORD_REQUIRED";
    /**
     * Password key
     */
    private static final String PASS_WORD = "PASSWORD";
    /**
     * Username key
     */
    private static final String USERNAME = "USERNAME";
    private static final Logger logger = LoggerFactory.getLogger(DemoHelper.class);
    private CognitoConfig cognitoConfig = new CognitoConfig();

    public AWSCognitoIdentityProvider getAmazonCognitoIdentityClient() {
        ClasspathPropertiesFileCredentialsProvider propertiesFileCredentialsProvider =
                new ClasspathPropertiesFileCredentialsProvider();

        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(propertiesFileCredentialsProvider)
                .withRegion(cognitoConfig.getRegion())
                .build();

    }

    public UserType signUp(UserSignUpRequest signUpRequest) {
        try {
            AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();

            AdminCreateUserRequest cognitoRequest = new AdminCreateUserRequest()
                    .withUserPoolId(cognitoConfig.getUserPoolId())
                    .withUsername(signUpRequest.getUsername())
                    .withUserAttributes(
                            new AttributeType()
                                    .withName("email")
                                    .withValue(signUpRequest.getEmail()),
                            new AttributeType()
                                    .withName("name")
                                    .withValue(signUpRequest.getName()),
                            new AttributeType()
                                    .withName("family_name")
                                    .withValue(signUpRequest.getLastname()),
                            new AttributeType()
                                    .withName("phone_number")
                                    .withValue(signUpRequest.getPhoneNumber()),
//                            new AttributeType()
//                                    .withName("custom:brokerID")
//                                    .withValue(signUpRequest.getBrokerID()),
//                            new AttributeType()
//                                    .withName("custom:companyPosition")
//                                    .withValue(signUpRequest.getCompanyPosition()),
                            new AttributeType()
                                    .withName("email_verified")
                                    .withValue("true"))
                    .withTemporaryPassword(signUpRequest.getPassword())
                    .withMessageAction("SUPPRESS")
                    .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL)
                    .withForceAliasCreation(Boolean.FALSE);

            AdminCreateUserResult createUserResult = cognitoClient.adminCreateUser(cognitoRequest);
            UserType cognitoUser = createUserResult.getUser();

            return cognitoUser;
        } catch (com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException e) {
            int a = 1;
            e.printStackTrace();

        } catch (Exception e) {
            int a = 1;
            e.printStackTrace();
        }
        return null;
    }


    public SpringSecurityUser authenticate(AuthenticationRequest authenticationRequest) {

        AuthenticationResultType authenticationResult = null;
        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();


        try {

            final Map<String, String> authParams = new HashMap<>();
            authParams.put(USERNAME, authenticationRequest.getUsername());
            authParams.put(PASS_WORD, authenticationRequest.getPassword());

            final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest();
            authRequest.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                    .withClientId(cognitoConfig.getClientId())
                    .withUserPoolId(cognitoConfig.getUserPoolId())
                    .withAuthParameters(authParams);

            AdminInitiateAuthResult result = cognitoClient.adminInitiateAuth(authRequest);


            //Has a Challenge
            if (StringUtils.isNotBlank(result.getChallengeName())) {

                //If the challenge is required new Password validates if it has the new password variable.
                if (NEW_PASS_WORD_REQUIRED.equals(result.getChallengeName())) {

                    if (null == authenticationRequest.getNewPassword()) {
                        throw new CognitoException("User must provide a new password", CognitoException.USER_MUST_CHANGE_PASS_WORD_EXCEPTION_CODE, result.getChallengeName());
                    } else {
                        //we still need the username

                        final Map<String, String> challengeResponses = new HashMap<>();
                        challengeResponses.put(USERNAME, authenticationRequest.getUsername());
                        challengeResponses.put(PASS_WORD, authenticationRequest.getPassword());

                        //add the new password to the params map
                        challengeResponses.put(NEW_PASS_WORD, authenticationRequest.getNewPassword());

                        //populate the challenge response
                        final AdminRespondToAuthChallengeRequest request = new AdminRespondToAuthChallengeRequest();
                        request.withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
                                .withChallengeResponses(challengeResponses)
                                .withClientId(cognitoConfig.getClientId())
                                .withUserPoolId(cognitoConfig.getUserPoolId())
                                .withSession(result.getSession());

                        AdminRespondToAuthChallengeResult resultChallenge = cognitoClient.adminRespondToAuthChallenge(request);
                        authenticationResult = resultChallenge.getAuthenticationResult();

                    }
                } else {
                    //has another challenge
                    throw new CognitoException(result.getChallengeName(), CognitoException.USER_MUST_DO_ANOTHER_CHALLENGE, result.getChallengeName());
                }

            } else {
                //Doesn't have a challenge
                authenticationResult = result.getAuthenticationResult();
            }

            //AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN, ROLE_EMPLOYEE, ROLE_MANAGER")
            SpringSecurityUser userAuthenticated = new SpringSecurityUser(authenticationRequest.getUsername(), authenticationRequest.getPassword(), null, null, null);
            userAuthenticated.setAccessToken(authenticationResult.getAccessToken());
            userAuthenticated.setExpiresIn(authenticationResult.getExpiresIn());
            userAuthenticated.setTokenType(authenticationResult.getTokenType());
            userAuthenticated.setRefreshToken(authenticationResult.getRefreshToken());
            userAuthenticated.setIdToken(authenticationResult.getIdToken());


            System.out.println(String.format("User successfully authenticated userInfo: username %s", authenticationRequest.getUsername()));


            return userAuthenticated;
        } catch (com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException e) {
            logger.error(e.getMessage(), e);
            throw new CognitoException(e.getMessage(), e.getErrorCode(), e.getMessage() + e.getErrorCode());
        } catch (CognitoException cognitoException) {
            throw cognitoException;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CognitoException(e.getMessage(), CognitoException.GENERIC_EXCEPTION_CODE, e.getMessage());
        }

    }


    public UserResponse getUserInfo(String accessToken) {
        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();

        try {

            if (StringUtils.isBlank(accessToken)){
                throw new CognitoException("User must provide an access token",CognitoException.INVALID_ACCESS_TOKEN_EXCEPTION, "User must provide an access token");
            }

            GetUserRequest userRequest = new GetUserRequest()
                    .withAccessToken(accessToken);

            GetUserResult userResult = cognitoClient.getUser(userRequest);

            List<AttributeType> userAttributes = userResult.getUserAttributes();
            UserResponse userResponse = getUserAttributesData(userAttributes, userResult.getUsername());

            return userResponse;


        }catch (com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException e){
            logger.error(e.getMessage(), e);
            throw new CognitoException(e.getMessage(), e.getErrorCode(), e.getMessage() + e.getErrorCode());
        }catch(Exception e) {
            logger.error(e.getMessage(), e);
            throw new CognitoException(e.getMessage(), CognitoException.GENERIC_EXCEPTION_CODE,e.getMessage());
        }

    }

    private UserResponse getUserAttributesData(List<AttributeType> userAttributes, String username) {
        UserResponse userResponse = new UserResponse();

        userResponse.setUsername(username);

        for(AttributeType attribute : userAttributes) {
            if(attribute.getName().equals("email")) {
                userResponse.setEmail(attribute.getValue());
            }else if(attribute.getName().equals("phone_number")) {
                userResponse.setPhoneNumber(attribute.getValue());
            }else if(attribute.getName().equals("name")) {
                userResponse.setName(attribute.getValue());
            }else if(attribute.getName().equals("family_name")) {
                userResponse.setLastname(attribute.getValue());
            }else if(attribute.getName().equals("custom:companyPosition")) {
                userResponse.setCompanyPosition(attribute.getValue());
            }
        }

        return userResponse;
    }


}

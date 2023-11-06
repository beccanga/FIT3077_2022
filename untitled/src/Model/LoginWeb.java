package Model;


import User.Admin;
import User.Customer;
import User.HealthCareWorker;
import User.UserManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
/**
 * LoginWeb class that represents the web service class for login system
 */
public class LoginWeb  extends RESTApi{

    private static final String rootUrl = "https://fit3077.com/api/v2";
    /**
     * instance of LoginWeb
     */
    private static LoginWeb instance;
    String usersUrl = rootUrl + "/user";
    ObjectNode currentUserNode = null;

    private LoginWeb(){
        super();
    }

    /**
     * getInstance() method
     * @return instance of LoginWeb
     */
    public static LoginWeb getInstance(){
        if(instance==null){
            instance = new LoginWeb();
        }
        return instance;

    }

    /**
     * login() method
     * @param userID user ID
     * @param password user password
     * @return boolean value of login
     */
    public boolean login(String userID, String password) throws Exception {
        String jsonString = "{" +
                "\"userName\":\"" + userID + "\"," +
                "\"password\":\"" + password + "\"" +
                "}";
        String usersLoginUrl = usersUrl + "/login"+ "?jwt=true";
        try {
            ObjectNode jsonNode = new ObjectMapper().readValue(post(usersLoginUrl, jsonString).body(), ObjectNode.class);
            jsonString = "{\"jwt\":\"" + jsonNode.get("jwt").textValue() + "\"}";
        }catch (Exception e){
            return false;
        }
        String usersVerifyTokenUrl = usersUrl + "/verify-token";
        if (post(usersVerifyTokenUrl,jsonString).statusCode()==200){
            loadUser(userID);
            return true;
        }
        return false;
    }

    /**
     * loadUser() method
     * @param userName user name
     * @return role of user using system
     */
    public boolean[] loadUser(String userName) throws IOException, InterruptedException {
        String usersLoginUrl = usersUrl;
        boolean[] role ={false,false,false};
        ObjectNode [] jsonNode = new ObjectMapper().readValue(get(usersLoginUrl).body(), ObjectNode[].class);
        for(int i =0; i < jsonNode.length; i++){
            if (jsonNode[i].get("userName").textValue().equals(userName)) {
                currentUserNode = jsonNode[i];
                if (jsonNode[i].get("isCustomer").asBoolean()){
                    role[0] = true;
                }
                if (jsonNode[i].get("isHealthcareWorker").asBoolean()){
                    role[1] = true;
                }
                if (jsonNode[i].get("isReceptionist").asBoolean()){
                    role[2] = true;
                }
                return role;
            }

        }
        return role;
    }

    /**
     * setCurrentUser() method
     * @param choice input choice
     * @return boolean value of choice made
     */
    public boolean setCurrentUser(int choice) {
        switch (choice) {
            case 0: {
                UserManager.getInstance().setCurrentUserName(new Customer(currentUserNode.get("id").textValue(),
                        currentUserNode.get("givenName").textValue(),
                        currentUserNode.get("familyName").textValue(),
                        currentUserNode.get("userName").textValue(),
                        currentUserNode.get("phoneNumber").asInt()));
                return true;
            }
            case 1: {
                UserManager.getInstance().setCurrentUserName(new HealthCareWorker(currentUserNode.get("id").textValue(),
                        currentUserNode.get("givenName").textValue(),
                        currentUserNode.get("familyName").textValue(),
                        currentUserNode.get("userName").textValue(),
                        currentUserNode.get("phoneNumber").asInt()));
                return true;

            }

            case 2: {
                UserManager.getInstance().setCurrentUserName(new Admin(currentUserNode.get("id").textValue(),
                        currentUserNode.get("givenName").textValue(),
                        currentUserNode.get("familyName").textValue(),
                        currentUserNode.get("userName").textValue(),
                        currentUserNode.get("phoneNumber").asInt()));
                return true;
            }
            default : return false;


        }
    }
}



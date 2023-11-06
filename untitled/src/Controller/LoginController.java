package Controller;

import View.View;
import Model.LoginWeb;

/**
 * LoginController class that represents the controller for login
 */
public class LoginController {
    /**
     * login() method
     * gets username and password and validates the login
     */
    public void login(View view) throws Exception {
        boolean validator = true;
        do {
            validator=true;
            view.printPrompt("Enter UserName");
            String us =  view.inputString();
            view.printPrompt("Enter Password");
            String pw =  view.inputString();


            if (LoginWeb.getInstance().login(us, pw)) {
                view.printPrompt("Successfully Login");
                loginAs(us,view);

            } else {
                view.printPrompt("Failed Login");
                validator=false;
            }
        }while(!validator);

    }

    /**
     * loginAs() method
     * provide roles for the user when login
     * @param us user
     */
    public void loginAs(String us, View view) throws Exception {
        LoginWeb loginWeb = LoginWeb.getInstance();

        boolean [] role =  loginWeb.loadUser(us);
        String [] character = {"Customer","Health Care Worker","Admin"};
        view.printPrompt("Login As:");
        for(int i = 0; i < role.length;i++){
            if(role[i]){
                view.printPrompt("Press "+i+" to login as "+character[i]);
            }
        }
        int loginChoice = view.inputInt();
        if(loginWeb.setCurrentUser(loginChoice)){
            MainMenuController.getInstance().mainMenu(view);
        }
    }

}

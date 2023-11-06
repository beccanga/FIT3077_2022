package Controller;

import User.Admin;
import User.UserManager;
import Enum.UserTag;
import View.View;

import java.util.Scanner;

/**
 * MainMenuController class that represents the controller for main menu
 */
public class MainMenuController {
    public MainMenuController() {
    }

    /**
     * instance of MainMenuController
     */
    private static MainMenuController instance ;

    /**
     * getInstance() method
     * @return instance of MainMenuController
     */
    public static MainMenuController getInstance(){
        if (instance == null){
            instance = new MainMenuController();
        }
        return instance;
    }

    /**
     * choice() method
     * to allow user to choose actions to perform
     * @return integer of choice from user
     */
    public int choice(View view){
        view.printPrompt("1. Search for Testing Site");
        view.printPrompt("2. Perform Onsite Booking");
        view.printPrompt("3: Perform Onsite Testing(Admin and HealthcareWorker only");
        view.printPrompt("4: Perform Home Testing");
        view.printPrompt("5: Admin Panel");
        int choice = view.inputInt();
        return choice;
    }

    /**
     * mainMenu() method
     * contains many different controllers that depends on the choice made by user
     */
    public void mainMenu(View view) throws Exception {
        switch(choice(view)){
            case 1 :{
                if (UserManager.getInstance().getCurrentUser().hasCapability(UserTag.IS_CUSTOMER)) {
                    TestingSiteController.getInstance().control(view);
                }
                else{
                    view.printPrompt("You have no access");
                }
            } break;
            case 2:
                if (UserManager.getInstance().getCurrentUser().hasCapability(UserTag.IS_ADMIN)) {
                    OnsiteBookController.getInstance().subMenu(view);
                }
                else if (UserManager.getInstance().getCurrentUser().hasCapability(UserTag.IS_CUSTOMER)) {
                    OnsiteBookController.getInstance().subCustMenu(view);
                }break;
            case 3:{
                if (UserManager.getInstance().getCurrentUser().hasCapability(UserTag.IS_ADMIN)||UserManager.getInstance().getCurrentUser().hasCapability(UserTag.IS_HEALTH_CARE_WORKER)){
                    OnsiteTestingController.getInstance().placeTest(view);
                }
            }break;
            case 4:{
                HomeBookingController.getInstance().placeBooking(view);
            }break;
            case 5:{
                if (UserManager.getInstance().getCurrentUser().hasCapability(UserTag.IS_ADMIN)||UserManager.getInstance().getCurrentUser().hasCapability(UserTag.IS_HEALTH_CARE_WORKER))
                    AdminPanelController.getInstance().subMenu(view);
            }break;
                default:{
                view.printPrompt("Wrong key");
            }
        }


    }

}

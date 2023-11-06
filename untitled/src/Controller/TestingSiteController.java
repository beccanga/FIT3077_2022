package Controller;

import Model.TestingSiteWeb;
import View.View;

/**
 * TestingSiteController class represents the controller to search for the testing sites
 */
public class TestingSiteController {
    /**
     * instance of TestingSiteController
     */
    private static TestingSiteController instance ;
    private TestingSiteController(){}

    /**
     * getInstance() method
     * @return instance of TestingSiteController
     */
    public static TestingSiteController getInstance(){
        if(instance==null){
            instance = new TestingSiteController();
        }
        return instance;
    }

    /**
     * searchMenu() method
     * allows user to get the list of testing sites
     * @return integer value of choice
     */
    public int searchMenu(View view){
        view.printPrompt("1: List all Testing Site");
        view.printPrompt("2: Find Testing by Suburb");
        int choice = view.inputInt();
        return choice;
    }

    /**
     * control() method
     * shows the list of testing sites
     */
    public void control(View view) throws Exception {
        TestingSiteWeb.getInstance().loadList();
        int choice = searchMenu(view);
        switch (choice){
            case 1:{
                TestingSiteWeb.getInstance().showList();
            }break;
            case 2:{
                findSuburb(view);
            }break;
        }
    }

    /**
     * findSuburb() method to find suburb by giving a name
     */
    public void findSuburb(View view) {
        view.printPrompt("Enter Suburb name");
        String suburb = view.inputString();
        TestingSiteWeb.getInstance().findBySuburb(suburb);
    }


}

package Controller;

import BookAndTest.*;
import Model.OnsiteBookWeb;
import Model.TestingSiteWeb;
import TestingSitePkg.TestingSite;
import TestingSitePkg.TestingSiteManager;
import User.Customer;
import User.UserManager;
import View.View;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * OnsiteBookController class represents the controller to perform onsite booking
 */
public class OnsiteBookController {
    /**
     * instance of OnsiteBookController
     */
    private static OnsiteBookController instance;
    private OnsiteBookController(){}

    /**
     * getInstance() method
     * @return instance of OnsiteBookController
     */
    public static OnsiteBookController getInstance(){
        if(instance==null){
            instance = new OnsiteBookController();
        }
        return instance;
    }

    /**
     * subMenu() method
     * lets user choose between 2 actions
     */
    public void subMenu(View view) throws Exception {
        view.printPrompt("Choose the action:");
        view.printPrompt("1: Place Booking");
        view.printPrompt("2: Check Booking");
        view.printPrompt("3: Modify Booking by Phone");
        int choice = view.inputInt();
        switch(choice){
            case 1:{
                placeBooking(view);
            }break;
            case 2:{
                checkBooking(view);
            }break;
            case 3:{
                modifyBookingbyPhone(view);
            }
            default:
                subMenu(view);
        }
    }

    /**
     * placebooking() method
     * allows user to place booking
     */
    public void placeBooking(View view) throws Exception{
        String custID;
        String testSiteId;
        Customer customer;
        TestingSite testingSite;
        do {
            view.printPrompt("Please Enter Customer ID");
            custID = view.inputString();
            customer = OnsiteBookWeb.getInstance().validateCust(custID);
            if (customer == null) {
                view.printPrompt("Please enter valid CustomerID");
            }
        }while(customer == null);
        do {
            view.printPrompt("Please Enter Testing ID");
            testSiteId = view.inputString();
            testingSite = OnsiteBookWeb.getInstance().validateTestSite(testSiteId);
            if (testingSite == null){
                view.printPrompt("Please enter valid TestSite ID");
            }
        }while(testingSite == null);
        view.printPrompt("Please Enter Notes");
        String notes = view.inputString();
        Booking postBooking = new Booking(customer,testingSite,notes);
        Booking completeBooking = OnsiteBookWeb.getInstance().uploadDetail(postBooking);

        int testChoice = 0;
        view.printPrompt("Select Test");
        view.printPrompt("1: PCR");
        view.printPrompt("2: RAT");
        testChoice = view.inputInt();
        CovidTest ct = null;
        view.printPrompt("Enter notes for the Covid test");
        String testNotes = view.inputString();
        if (testChoice == 1 ){
            ct = new PCRTest(customer, UserManager.getInstance().getCurrentUser(),
                    "PENDING","PENDING",testNotes);
        }
        else if(testChoice ==2 ){
            ct = new RATTest(customer,UserManager.getInstance().getCurrentUser(),
                    "PENDING","PENDING",testNotes);
        }
        completeBooking.setTest(ct);
        OnsiteBookWeb.getInstance().uploadTest(completeBooking);
        view.printPrompt("Booking Pin:"+completeBooking.getSmsPin());
        subMenu(view);
    }

    /**
     * checkBooking() method
     * allows user to check their booking
     */
    public Booking checkBooking(View view) throws Exception {
        BookingManager bm = OnsiteBookWeb.getInstance().loadBooking();
        Booking pin = null;
        do {
            view.printPrompt("Enter Your SMS Pin code");
            int smsPin = view.inputInt();
            pin = bm.findPin(smsPin);
            if (pin != null) {
                view.printPrompt(pin.toString());
            }
        }while(pin == null);
        return pin;
    }

    /**
     * subCustMenu method act as a sub menu to allow user to perform modification to their booking
     * @param view to display information
     * @throws Exception
     */
    public void subCustMenu(View view) throws Exception {
        view.printPrompt("1: Modify Booking");
        view.printPrompt("2: Cancel Booking");
        int choice = view.inputInt();
        modifyBookingMenu(view,choice);
    }

    /**
     * modifyBookingMenu method lets user to make the necessary modifications they require to the booking
     * @param view to display information
     * @param sel selection from user
     * @throws Exception
     */
    public void modifyBookingMenu(View view,int sel) throws Exception {
        view.printPrompt("1: Check current booking");
        view.printPrompt("2: Enter Booking id");
        int choice = view.inputInt();

        switch (choice){
            case 1:{
                modifyBooking(OnsiteBookWeb.getInstance().userBooking(UserManager.getInstance().getCurrentUser().getId(),null),view,sel);
            } break;
            case 2:{
                modifyByBookingID(view,sel);
            }
        }
    }

    /**
     * modifyByBookingID method to let user modify booking by ID
     * @param view to display information to user
     * @param sel selection made by user
     * @throws Exception
     */
    public void modifyByBookingID(View view,int sel) throws Exception {
        view.printPrompt("Enter Booking id");
        String bookingid = view.inputString();
        modifyBooking(OnsiteBookWeb.getInstance().userBooking(null,bookingid),view,sel);
    }

    /**
     * modifyBooking method lets user to modify their booking
     * @param book booking information
     * @param view to display information to user
     * @param sel selection made by user
     * @throws Exception
     */
    public void modifyBooking(Booking book,View view,int sel) throws Exception {
        if (sel == 1){
            view.printPrompt("Select to modify:");
            view.printPrompt("1: Date of Perform: " + book.getTest().getDatePerformed());
            view.printPrompt("2: Testing Site: " + book.getTestingSite().getName());
            int choice = view.inputInt();
            switch(choice){
                case 1:{
                    changeDate(book,view);
                }break;
                case 2:{
                    changeTestingSite(book,view);
                }break;
                default:{

                }
        }
        }
        else if (sel == 2){
            view.printPrompt("1: Confirm to cancel");
            view.printPrompt("2: Return to home");
            int choice = view.inputInt();
            switch(choice){
                case 1:
                    OnsiteBookWeb.getInstance().cancelBooking(book);
            }
        }
    }

    /**
     * changeDate method allows user to change the date of their booking
     * @param book booking information
     * @param view to display information to user
     * @throws Exception
     */
    public void changeDate(Booking book,View view) throws Exception {
        Instant instant = Instant.parse(book.getTest().getDatePerformed());
        if (instant.isBefore(Instant.now())){
            view.printPrompt("This is a lapsed Booking");
            subCustMenu(view);
        }
        if (book.getTest().getStatus().equals("CANCELLED")){
            view.printPrompt("This is a canceled Booking");
            subCustMenu(view);
        }
        view.printPrompt("Change Date to:");
        view.printPrompt("1: "+instant.plus(1, ChronoUnit.DAYS));
        view.printPrompt("2: "+instant.plus(2, ChronoUnit.DAYS));
        view.printPrompt("3: "+instant.plus(3, ChronoUnit.DAYS));
        int days = view.inputInt();
        book.getTest().setDatePerformed(instant.plus(days,ChronoUnit.DAYS).toString());
        OnsiteBookWeb.getInstance().modifyDate(book);

    }

    /**
     * changeTestingSite method allows user to modify their booking by changing the testing site
     * @param book booking information
     * @param view to display information to user
     * @throws Exception
     */
    public void changeTestingSite(Booking book, View view) throws Exception {
        if(TestingSiteManager.getInstance().getTestingSites().size()==0){
            TestingSiteWeb.getInstance().loadList();
        }
        ArrayList<TestingSite> ts = TestingSiteManager.getInstance().getTestingSites();

        view.printPrompt("Select Testing Site");
        for(int i = 0; i< ts.size();i++){
            view.printPrompt(i+" : "+ ts.get(i).getName());
        }
        int choice = view.inputInt();

        book.setTestingSite(ts.get(choice));

        OnsiteBookWeb.getInstance().modifyTestSite(book);
    }


    /**
     * modifyBookingbyPhone method allows user to make booking modifications through phone
     * @param view to display information to user
     * @throws Exception
     */
    public void modifyBookingbyPhone(View view) throws Exception{
        view.printPrompt("Modify by:");
        view.printPrompt("1: Booking Pin");
        view.printPrompt("2: Booking Id");
        int choice = view.inputInt();
        Booking book = null;
        switch (choice){
            case 1:{
                 book = checkBooking(view);
                 modifyBooking(book,view,1);
            }break;
            case 2:
                modifyByBookingID(view,1);
    }
}

}
package Controller;
import BookAndTest.Booking;
import BookAndTest.CovidTest;
import BookAndTest.PCRTest;
import BookAndTest.RATTest;
import User.User;
import User.UserManager;
import Model.TestingWeb;
import Enum.COVIDTestTag;
import View.View;

import java.util.Scanner;

/**
 * OnsiteTestingController class represents the controller to perform onsite testing
 */
public class OnsiteTestingController {
    /**
     * instance of OnsiteTestingController
     */
    private static OnsiteTestingController instance;
    private OnsiteTestingController(){}

    /**
     * getInstance() method
     * @return instance of OnsiteTestingController
     */
    public static OnsiteTestingController getInstance(){
        if(instance==null){
            instance = new OnsiteTestingController();
        }
        return instance;
    }

    /**
     * placeTest() method
     * allows healthcare worker or admin to choose the covid test type and upload result of covid test
     */
    public void placeTest(View view) throws Exception {
        String type="";
        int choice = 0;
        do{
        view.printPrompt("Press");
        view.printPrompt("1: To select Polymerase Chain Reaction test (PCR)");
        view.printPrompt("2: To select Rapid Antigen test (RAT)");
        choice = view.inputInt();
        if(choice == 1){
            type = "PCR";
        }
        else if (choice==2){
            type = "RAT";
        }
        }while(choice!= 1 && choice!=2 );

        User adminID = UserManager.getInstance().getCurrentUser();
        Booking booking= null;
        do {
            view.printPrompt("Enter Booking Id");
            String bookingID = view.inputString();
            booking = TestingWeb.getInstance().getBooking(bookingID);
        }while(booking == null);
        String result = "";
        choice= 0;
        do{
        view.printPrompt("Select Result:");
        view.printPrompt("1: POSITIVE");
        view.printPrompt("2: NEGATIVE");
        choice = view.inputInt();
        if(choice == 1){
            result = "POSITIVE";
        }
        else if (choice==2){
            result = "NEGATIVE";
        }
        }while(choice!= 1 && choice!=2 );
        view.printPrompt("Enter Status");
        String status = view.inputString();
        view.printPrompt("Enter Notes");
        String notes = view.inputString();
        CovidTest ct = null;
        if (type.equals("PCR")){
            if (booking.getTest().hasCapability(COVIDTestTag.IS_RAPID_ANTIGEN_TEST)){
                CovidTest old = booking.getTest();
                booking.setTest(new PCRTest(old.getId(),old.getCustomer(),adminID,result,status,notes,old.getDatePerformed()));
            }
            else{
                booking.getTest().setHealthCareWorker(adminID);
                booking.getTest().setNotes(notes);
                booking.getTest().setStatus(status);
                booking.getTest().setResult(result);
            }
        }
        else if(type.equals("RAT")){
            if (booking.getTest().hasCapability(COVIDTestTag.IS_RT_PCR)){
                CovidTest old = booking.getTest();
                booking.setTest(new RATTest(old.getId(),old.getCustomer(),adminID,result,status,notes,old.getDatePerformed()));
            }
            else{
                booking.getTest().setHealthCareWorker(adminID);
                booking.getTest().setNotes(notes);
                booking.getTest().setStatus(status);
                booking.getTest().setResult(result);
            }        }
        if (TestingWeb.getInstance().updateRes(booking)){
            view.printPrompt("Upload successfully");
        }
        else{
            view.printPrompt("Failed upload");
        }
    }

}

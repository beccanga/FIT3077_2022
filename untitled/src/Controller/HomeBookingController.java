package Controller;

import BookAndTest.Booking;
import Enum.COVIDTestTag;
import Model.HomeBookingWeb;
import Model.TestingWeb;
import View.View;

/**
 * HomeBookingController class that represents the controller for home booking system
 */
public class HomeBookingController {

    /**
     * instance of HomeBookingController
     */
    private static HomeBookingController instance;
    private HomeBookingController(){}

    /**
     * getInstance() method
     * @return instance of HomeBookingController
     */
    public static HomeBookingController getInstance(){
        if (instance == null ){
            instance = new HomeBookingController();
        }
        return instance;
    }

    /**
     * placebooking() method
     * allows user to place booking
     */
    public void placeBooking(View view) throws Exception {
        view.printPrompt("Please enter Booking id:");
        String bookingId = view.inputString();
        TestingWeb testingWeb = TestingWeb.getInstance();
        Booking booking = testingWeb.getBooking(bookingId);
        if (booking.getTest().hasCapability(COVIDTestTag.IS_RT_PCR)){
            view.printPrompt("Please go to center to perform PCR");
        }
        else if (booking.getTest().hasCapability(COVIDTestTag.IS_RAPID_ANTIGEN_TEST)){
            view.printPrompt("Do you have a test kit \n 1: Yes \n2: NO");
            int choice = view.inputInt();
            if(choice == 1){
                view.printPrompt("Book for home supervision");
            }
            else if(choice == 2){
                HomeBookingWeb.getInstance().updateQrCode(booking);
                view.printPrompt(booking.getAdditionalInfo().toString());
            }
        }

    }
}
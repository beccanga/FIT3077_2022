package Controller;

import BookAndTest.Booking;
import BookAndTest.BookingManager;
import Model.AdminPanelWeb;
import Model.OnsiteBookWeb;
import TestingSitePkg.TestingSite;
import View.View;

import java.util.ArrayList;
/**
 * AdminPanelController class that represents the controller for home booking system
 */
public class AdminPanelController {

    /**
     * instance of AdminPanelController
     */
    private static AdminPanelController instance;
    private AdminPanelController(){}

    /**
     * getInstance() method
     * @return instance of AdminPanelController
     */
    public static AdminPanelController getInstance(){
        if(instance == null){
            instance = new AdminPanelController();
        }
        return instance;
    }

    /**
     * subMenu method to act as a sub menu for different functionalities
     * @param view stores all the information to be displayed
     * @throws Exception
     */
    public void subMenu(View view) throws Exception {
        view.printPrompt("1: Modify Booking");
        view.printPrompt("2: View all Booking");
        view.printPrompt("3: Delete Booking");
        view.printPrompt("4: Notification");
        view.printPrompt("5: Cancel Booking");
        int choice = view.inputInt();

        switch (choice){
            case 1:{
                modifyBooking(view);
            }break;
            case 2:{
                viewBooking(view);
            }break;
            case 3:{
                deleteBooking(view);
            }break;
            case 4:{
                notifyBooking(view);
            }break;
            case 5:{
                cancelBooking(view);
            }
        }
    }

    /**
     * cancelBooking method to allow current user to cancel booking if they havent performed the covid test
     * @param view to display the information to user
     * @throws Exception
     */
    public void cancelBooking(View view) throws Exception {
        OnsiteBookController.getInstance().modifyByBookingID(view,2);
    }

    /**
     * notifyBooking method to allow current user to receive the notification of their booking status
     * @param view to display the information to user
     * @throws Exception
     */
    public void notifyBooking(View view) throws Exception {
        TestingSite workingLocation = AdminPanelWeb.getInstance().getCurrentUserWorkingLocation();
        view.printPrompt("You work in "+workingLocation.getName());
        BookingManager bm = OnsiteBookWeb.getInstance().loadBooking();
        ArrayList<Booking> bookings = bm.getBookings();
        for (int i = 0 ; i < bookings.size();i++){
            if(bookings.get(i).getTest()!=null && bookings.get(i).getTest().getStatus().equals("CANCELLED")
                    && bookings.get(i).getTestingSite().getId().equals(workingLocation.getId())
                        ){
                view.printPrompt(bookings.get(i).toString());
            }
        }
    }

    /**
     * deleteBooking method to allow current user to delete booking if they havent performed the covid test
     * @param view to display the information to user
     * @throws Exception
     */
    public void deleteBooking(View view) throws Exception {
        view.printPrompt("Enter Booking id to Delete");
        String id = view.inputString();
        Booking book = AdminPanelWeb.getInstance().getBooking(id);
        view.printPrompt(book.toString());
        view.printPrompt("");
        view.printPrompt("Press 1 to confirm Delete");
        int choice = view.inputInt();
        if (choice==1) {
            AdminPanelWeb.getInstance().deleteBooking(book);
        }
    }

    /**
     * modifyBooking method to allow current user to modify booking
     * @param view to display information to user
     * @throws Exception
     */
    public void modifyBooking(View view) throws Exception {
        OnsiteBookController.getInstance().modifyByBookingID(view,1);
    }

    /**
     * viewBooking to allow current user to view booking
     * @param view
     * @throws Exception
     */
    public void viewBooking(View view) throws Exception {
        BookingManager bm = OnsiteBookWeb.getInstance().loadBooking();
        bm.viewBooking(view);

    }


}

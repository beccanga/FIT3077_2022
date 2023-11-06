package BookAndTest;

import View.View;

import java.util.ArrayList;

/**
 * Booking manager class
 */
public class BookingManager {
    /**
     * bookings ArrayList
     */
    private ArrayList<Booking> bookings;

    /**
     * Booking Manager constructor
     */
    public BookingManager(){
        this.bookings = new ArrayList<Booking>();
    }

    /**
     * add() method that adds the booking
     * @param book booking
     */
    public void add(Booking book){
        this.bookings.add(book);
    }

    /**
     * findPin() method that finds the sms pin from booking
     * @param smsPin sms pin
     * @return booking sms pin
     */
    public Booking findPin(int smsPin){
        for(int i = 0 ; i < bookings.size();i++){
            if(bookings.get(i).getSmsPin()==smsPin){
                return bookings.get(i);
            }
        }
        return null;
    }

    public void viewBooking(View view) {
        for(int i = 0 ; i < bookings.size();i++){
            view.printPrompt(bookings.get(i).toString());
            System.out.println("");
        }
}

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
}

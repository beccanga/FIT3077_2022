package BookAndTest;

import TestingSitePkg.TestingSite;
import User.Customer;

import java.time.Instant;
import java.util.Map;
/**
 * Booking class
 */

public class Booking {
    private String bookingId;
    private Customer customer;
    private TestingSite testingSite;
    private String notes;
    private String date;
    private int smsPin;
    private CovidTest test;
    private AdditionalInfo additionalInfo;

    /**
     * Constructor to store the value for post
     * @param customer customer
     * @param testingSite testing site
     * @param notes notes
     */
    public Booking(Customer customer, TestingSite testingSite, String notes){
        this.customer=customer;
        this.notes=notes;
        this.testingSite=testingSite;
        this.date = Instant.now().toString();

    }
    /**
     * Constructor for storing the response value of post
     * @param bookingId booking ID
     * @param customer customer
     * @param testingSite testing site
     * @param notes notes
     * @param date date
     * @param smsPin smsPin
     */
    public Booking(String bookingId, Customer customer, TestingSite testingSite, String notes, String date, int smsPin){
        this.bookingId = bookingId;
        this.customer=customer;
        this.notes=notes;
        this.testingSite=testingSite;
        this.date = date;
        this.smsPin = smsPin;
    }

    public Booking(String bookingId, Customer customer, TestingSite testingSite, String notes, String date, int smsPin, CovidTest test) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.testingSite = testingSite;
        this.notes = notes;
        this.date = date;
        this.smsPin = smsPin;
        this.test = test;
    }
    /**
     * Getter for additional information
     * @return additional information object
     */

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }
    /**
     * Setter of additional info
     * @param additionalInfo additional information
     */
    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    /**
     * Getter for Covid Test
     * @return test
     */
    public CovidTest getTest() {
        return test;
    }

    /**
     * Setter for Covid test
     * @param test test
     */
    public void setTest(CovidTest test) {
        this.test = test;
    }

    /**
     * Getter for Customer
     * @return customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Setter for Customer
     * @param customer customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Setter for Customer ID
     * @param customer customer
     */
    public void setCustomerId(Customer customer) {
        this.customer = customer;
    }

    /**
     * Getter for Booking ID
     * @return booking ID
     */
    public String getBookingId() {
        return bookingId;
    }

    /**
     * Setter for Booking ID
     * @param bookingId booking ID
     */
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Getter for testing site
     * @return testing site
     */
    public TestingSite getTestingSite() {
        return testingSite;
    }

    /**
     * Setter for testing site
     * @param testingSite testing site
     */
    public void setTestingSite(TestingSite testingSite) {
        this.testingSite = testingSite;
    }

    /**
     * Getter for notes
     * @return string of notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Setter for notes
     * @param notes notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Getter for date
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter for date
     * @param date date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter for sms pin
     * @return integer of sms pin
     */
    public int getSmsPin() {
        return smsPin;
    }

    /**
     * Setter of sms pin
     * @param smsPin sms pin
     */
    public void setSmsPin(int smsPin) {
        this.smsPin = smsPin;
    }

    /**
     * toString() method
     * @return string of description of all the information
     */
    @Override
    public String toString() {
        if (getTest() != null) {
            return "bookingId=" + bookingId + "\n" +
                    "customer id=" + customer.getId() + "\n" +
                    "customer given name=" + customer.getGivenName() + "\n" +
                    "customer family name=" + customer.getFamilyName() + "\n" +
                    "testingSite id=" + testingSite.getId() + "\n" +
                    "testingSite address=" + testingSite.getAddress().toString() + "\n" +
                    "testingSite name=" + testingSite.getName() + "\n" +
                    "testingSite phone no=" + testingSite.getPhoneNumber() + "\n" +
                    "notes='" + notes + "\n" +
                    "date='" + date + "\n" + getTest().toString();
        }
        else{
            return "bookingId=" + bookingId + "\n" +
                    "customer id=" + customer.getId() + "\n" +
                    "customer given name=" + customer.getGivenName() + "\n" +
                    "customer family name=" + customer.getFamilyName() + "\n" +
                    "testingSite id=" + testingSite.getId() + "\n" +
                    "testingSite address=" + testingSite.getAddress().toString() + "\n" +
                    "testingSite name=" + testingSite.getName() + "\n" +
                    "testingSite phone no=" + testingSite.getPhoneNumber() + "\n" +
                    "notes='" + notes + "\n" +
                    "date='" + date + "\n" + "No Test Registered";
        }
    }
}

package User;

import BookAndTest.Booking;
import Enum.Capabilities;
import Enum.Capable;

/**
 * Abstract User class
 */
public abstract class User implements Capable {
    /**
     * Implements Capable interface
     */
    private String id;
    private String givenName;
    private String familyName;
    private String userName;
    private int phoneNumber;
    private Booking booking;
    private Capabilities capabilities = new Capabilities();

    /**
     * User constructor
     * @param id User ID
     * @param givenName User given name
     * @param familyName User family name
     * @param userName User Username
     * @param phoneNumber User phone number
     */
    public User(String id, String givenName, String familyName, String userName, int phoneNumber) {
        this.id = id;
        this.givenName = givenName;
        this.familyName = familyName;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    /**
     * User constructor
     * @param id User ID
     * @param givenName User given name
     * @param familyName User family name
     * @param userName User Username
     * @param phoneNumber User phone number
     * @param booking User booking
     */
    public User(String id, String givenName, String familyName, String userName, int phoneNumber, Booking booking) {
        this.id = id;
        this.givenName = givenName;
        this.familyName = familyName;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.booking = booking;
    }

    /**
     * hasCapability() method
     * @param capability capability
     * @return capabilities
     */
    @Override
    public boolean hasCapability(Enum<?> capability) { return capabilities.hasCapability(capability);
    }

    /**
     * addCapability() method
     * @param capability capability
     */
    @Override
    public void addCapability(Enum<?> capability) {
        capabilities.addCapability(capability);
    }

    /**
     * removeCapability() method
     * @param capability capability
     */
    @Override
    public void removeCapability(Enum<?> capability) {
        capabilities.removeCapability(capability);
    }

    /**
     * Getter for ID
     * @return string of user id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for ID
     * @param id user id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for given name
     * @return string of user given name
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Setter for given name
     * @param givenName user given name
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * Getter for family name
     * @return string of user family name
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Setter for family name
     * @param familyName user family name
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * Getter for username
     * @return string of username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for username
     * @param userName user username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for phone number
     * @return integer of phone number
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setter for phone number
     * @param phoneNumber user phone number
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter for user booking
     * @return booking
     */
    public Booking getBooking() {
        return booking;
    }

    /**
     * Setter for booking
     * @param booking user booking
     */
    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}

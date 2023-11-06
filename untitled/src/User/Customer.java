package User;
import Enum.UserTag;

/**
 * This Customer class represents the user with the customer role
 */
public class Customer extends User{
    /**
     * The Customer constructor
     * @param id Customer ID
     * @param givenName Customer given name
     * @param familyName Customer family name
     * @param userName Customer Username
     * @param phoneNumber Customer phone number
     */
    public Customer(String id, String givenName, String familyName, String userName, int phoneNumber) {
        super(id, givenName, familyName, userName, phoneNumber);
        this.addCapability(UserTag.IS_CUSTOMER); //contains the enum user tag IS_CUSTOMER
    }


}

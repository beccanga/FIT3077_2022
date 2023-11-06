package User;
import Enum.UserTag;

/**
 * This Admin class represents the user with the admin role
 */
public class Admin extends User{
    /**
     * The Admin class constructor
     * @param id Admin ID
     * @param givenName Admin given name
     * @param familyName Admin family name
     * @param userName Admin Username
     * @param phoneNumber Admin phone number
     */
    public Admin(String id, String givenName, String familyName, String userName, int phoneNumber) {
        super(id, givenName, familyName, userName, phoneNumber);
        this.addCapability(UserTag.IS_ADMIN); //contains the enum user tag IS_ADMIN
    }
}

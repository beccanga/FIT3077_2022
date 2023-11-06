package User;
import BookAndTest.Booking;
import Enum.UserTag;

/**
 * This HealthCareWorker class represents the user with the health care worker role
 */
public class HealthCareWorker extends User{
    /**
     * The HealthCareWorker constructor
     * @param id HealthCareWorker ID
     * @param givenName HealthCareWorker given name
     * @param familyName HealthCareWorker family name
     * @param userName HealthCareWorker Username
     * @param phoneNumber HealthCareWorker phone number
     */
    public HealthCareWorker(String id, String givenName, String familyName, String userName, int phoneNumber) {
        super(id, givenName, familyName, userName, phoneNumber);
        this.addCapability(UserTag.IS_HEALTH_CARE_WORKER); //contains the enum user tag IS_HEALTH_CARE_WORKER
    }

    public HealthCareWorker(String id, String givenName, String familyName, String userName, int phoneNumber, Booking booking) {
        super(id, givenName, familyName, userName, phoneNumber, booking);
        this.addCapability(UserTag.IS_HEALTH_CARE_WORKER);

    }
}

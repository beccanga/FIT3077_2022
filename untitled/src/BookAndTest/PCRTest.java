package BookAndTest;
import Enum.COVIDTestTag;
import User.Customer;
import User.User;

/**
 * PCRTest class that represents the covid test type PCR
 */
public class PCRTest extends CovidTest{

    /**
     * PCRTest Constructor
     * inherits the constructor from CovidTest class
     * @param id id
     * @param customer customer
     * @param healthCareWorker healthcare worker
     * @param result result
     * @param status status
     * @param notes notes
     * @param datePerformed date of test performed
     */
    public PCRTest(String id, Customer customer, User healthCareWorker, String result, String status, String notes, String datePerformed) {
        super(id, customer, healthCareWorker, result, status, notes, datePerformed);
        this.addCapability(COVIDTestTag.IS_RT_PCR); //contains the enum covid test tag IS_RT_PCR

    }

    /**
     * PCRTest Constructor
     * @param customer customer
     * @param healthCareWorker healthcare worker
     * @param result result
     * @param status status
     * @param notes notes
     */
    public PCRTest(Customer customer, User healthCareWorker, String result, String status, String notes) {
        super(customer, healthCareWorker, result, status, notes);
        this.addCapability(COVIDTestTag.IS_RT_PCR); //contains the enum covid test tag IS_RT_PCR
    }

}

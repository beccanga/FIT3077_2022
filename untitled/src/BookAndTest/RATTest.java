package BookAndTest;

import Enum.COVIDTestTag;
import User.Customer;
import User.User;

/**
 * RATTest class that represents the covid test type RAT
 */
public class RATTest extends CovidTest{
    /**
     * RATTest constructor
     * @param id id
     * @param customer customer
     * @param healthCareWorker healthcare worker
     * @param result result
     * @param status status
     * @param notes notes
     * @param datePerformed date of test performed
     */
    public RATTest(String id, Customer customer, User healthCareWorker, String result, String status, String notes, String datePerformed) {
        super(id, customer, healthCareWorker, result, status, notes, datePerformed);
        this.addCapability(COVIDTestTag.IS_RAPID_ANTIGEN_TEST); //contains the enum covid test tag IS_RAPID_ANTIGEN_TEST
    }

    /**
     * RATTest constructor
     * @param customer customer
     * @param healthCareWorker healthcare worker
     * @param result result
     * @param status status
     * @param notes notes
     */
    public RATTest(Customer customer, User healthCareWorker, String result, String status, String notes) {
        super(customer, healthCareWorker, result, status, notes);
        this.addCapability(COVIDTestTag.IS_RAPID_ANTIGEN_TEST); //contains the enum covid test tag IS_RAPID_ANTIGEN_TEST
    }
}

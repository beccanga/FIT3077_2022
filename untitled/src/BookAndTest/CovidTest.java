package BookAndTest;

import Enum.Capabilities;
import Enum.Capable;
import User.Customer;
import User.User;

/**
 * CovidTest abstract class
 */
public abstract class CovidTest implements Capable {
    private String id;
    private Customer customer;
    private User healthCareWorker;
    private String result;
    private String status;
    private String notes;
    private String datePerformed;
    private Capabilities capabilities = new Capabilities();

    /**
     * CovidTest constructor
     * @param id id
     * @param customer customer
     * @param healthCareWorker healthcare worker
     * @param result result
     * @param status status
     * @param notes notes
     * @param datePerformed date the test was performed
     */
    public CovidTest(String id,Customer customer, User healthCareWorker, String result, String status, String notes,String datePerformed) {
        this.id = id;
        this.customer = customer;
        this.healthCareWorker = healthCareWorker;
        this.result = result;
        this.status = status;
        this.notes = notes;
        this.datePerformed = datePerformed;
    }

    /**
     * CovidTest constructor
     * @param customer customer
     * @param healthCareWorker healthcare worker
     * @param result results
     * @param status status
     * @param notes notes
     */
    public CovidTest(Customer customer, User healthCareWorker, String result, String status, String notes) {
        this.customer = customer;
        this.healthCareWorker = healthCareWorker;
        this.result = result;
        this.status = status;
        this.notes = notes;
    }

    /**
     * Getter of date when the test was performed
     * @return string of date the test was performed
     */
    public String getDatePerformed() {
        return datePerformed;
    }

    /**
     * Setter of the date of when the test was performed
     * @param datePerformed date of test performed
     */
    public void setDatePerformed(String datePerformed) {
        this.datePerformed = datePerformed;
    }

    /**
     * Getter of test ID
     * @return string of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter of test ID
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter of customer
     * @return Customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Setter of customer
     * @param customer customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Getter of healthcare worker
     * @return User healthare worker
     */
    public User getHealthCareWorker() {
        return healthCareWorker;
    }

    /**
     * Setter of healthcare worker
     * @param healthCareWorker healthcare worker
     */
    public void setHealthCareWorker(User healthCareWorker) {
        this.healthCareWorker = healthCareWorker;
    }

    /**
     * Getter of result
     * @return string of result
     */
    public String getResult() {
        return result;
    }

    /**
     * Setter of result
     * @param result result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Getter of status
     * @return string of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter of status
     * @param status status of test
     */
    public void setStatus(String status) {
        this.status = status;
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
     * hasCapability() method
     * @param capability capability
     * @return boolean of whether capability is present
     */
    @Override
    public boolean hasCapability(Enum<?> capability) {
        return capabilities.hasCapability(capability);
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
     * toString() method
     * @return description of Covid Test and other information such as id, result, status, notes, dates performed
     */
    @Override
    public String toString() {
        return "CovidTest{" +
                "id='" + id + '\'' +
                ", result='" + result + '\'' +
                ", status='" + status + '\'' +
                ", notes='" + notes + '\'' +
                ", datePerformed='" + datePerformed + '\'' +
                '}';
    }
}

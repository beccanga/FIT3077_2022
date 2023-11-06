package TestingSitePkg;

/**
 * TestingSite class that represents the testing sites available
 */
public class TestingSite {
    private String id;
    private String name;
    private String description;
    private String website;
    private int phoneNumber;
    private Address address;

    /**
     * TestingSite constructor
     * @param id testing site id
     * @param name testing site name
     * @param description testing site description
     * @param website testing site website
     * @param phoneNumber phone number
     * @param address address
     */
    public TestingSite(String id, String name, String description, String website, int phoneNumber, Address address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Getter of testing site ID
     * @return string value of testing site id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter of testing site ID
     * @param id testing site id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter of testing site name
     * @return string value of testing site name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of testing site name
     * @param name testing site name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of testing site description
     * @return string value of testing site description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter of testing site description
     * @param description testing site description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter of testing site website
     * @return string value of testing site
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Setter of testing site website
     * @param website testing site website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Getter of testing site phone number
     * @return integer value of phone number
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setter of testing site phone number
     * @param phoneNumber testing site phone number
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter of address
     * @return address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Setter of address
     * @param address address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * toString() method
     * @return string description of testing site
     */
    @Override
    public String toString() {
        return
                "ID= " + id + "\n" +
                "Name= " + name + "\n" +
                "Description= " + description + "\n" +
                "Website= " + website + "\n" +
                "PhoneNumber= " + phoneNumber +"\n"+
                "Address= " + address.toString() + "\n" +
                "\n";
    }
}

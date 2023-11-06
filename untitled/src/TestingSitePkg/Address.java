package TestingSitePkg;

/**
 * Class that represents Address
 */
public class Address {
    private double latitude;
    private double longitude;
    private int unitNumber;
    private String street;
    private String street2;
    private String suburb;
    private String state;
    private int postcode;

    /**
     * Address constructor
     * @param latitude latitude
     * @param longitude longitude
     * @param unitNumber unit number
     * @param street street
     * @param street2 street 2
     * @param suburb suburb
     * @param state state
     * @param postcode postcode
     */
    public Address(double latitude, double longitude, int unitNumber, String street, String street2, String suburb, String state, int postcode) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.unitNumber = unitNumber;
        this.street = street;
        this.street2 = street2;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
    }

    /**
     * Getter for latitude
     * @return float value of latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Setter for latitude
     * @param latitude latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Getter of longitude
     * @return float value of longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Setter of longitude
     * @param longitude longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Getter of unit number
     * @return integer value of unit number
     */
    public int getUnitNumber() {
        return unitNumber;
    }

    /**
     * Setter of unit number
     * @param unitNumber unit number
     */
    public void setUnitNumber(int unitNumber) {
        this.unitNumber = unitNumber;
    }

    /**
     * Getter of street
     * @return string value of street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Setter of street
     * @param street street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Getter of street2
     * @return string value street2
     */
    public String getStreet2() {
        return street2;
    }

    /**
     * Setter of street2
     * @param street2 street 2
     */
    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    /**
     * Getter of suburb
     * @return string value of suburb
     */
    public String getSuburb() {
        return suburb;
    }

    /**
     * Setter of suburb
     * @param suburb suburb
     */
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    /**
     * Getter of state
     * @return string value of state
     */
    public String getState() {
        return state;
    }

    /**
     * Setter of state
     * @param state state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Getter of postcode
     * @return integer value of postcode
     */
    public int getPostcode() {
        return postcode;
    }

    /**
     * Setter of postcode
     * @param postcode postcode
     */
    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    /**
     * toString() method
     * @return a description string of address
     */
    @Override
    public String toString() {
        if (street2!=null){
        return ", unitNumber=" + unitNumber +
                ", street='" + street + '\'' +
                ", street2='" + street2 + '\'' +
                ", suburb='" + suburb + '\'' +
                ", state='" + state + '\'' +
                ", postcode='" + postcode + '\'';
        }
        else{
            return ", unitNumber=" + unitNumber +
                    ", street='" + street + '\'' +
                    ", suburb='" + suburb + '\'' +
                    ", state='" + state + '\'' +
                    ", postcode='" + postcode + '\'';
        }
    }
}

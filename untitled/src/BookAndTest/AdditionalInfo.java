package BookAndTest;

/**
 * AdditionalInfo class to represent additional info
 */
public class AdditionalInfo {
    private String id;
    private String dateCreated;
    private String fileName;
    private String URL;
    private boolean isRedeemed;

    /**
     * AdditionalInfo constructor
     * @param id id
     * @param dateCreated date created
     * @param fileName file name
     * @param URL url
     * @param isRedeemed is redeemed
     */
    public AdditionalInfo(String id, String dateCreated, String fileName, String URL, boolean isRedeemed) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.fileName = fileName;
        this.URL = URL;
        this.isRedeemed = isRedeemed;
    }

    /**
     * Getter for id
     * @return string value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for id
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for date created
     * @return string value of date created
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * Setter for date created
     * @param dateCreated date created
     */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Getter for file name
     * @return string value of file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Setter for file name
     * @param fileName file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Getter for URL
     * @return string value of url
     */
    public String getURL() {
        return URL;
    }

    /**
     * Setter for URL
     * @param URL url
     */
    public void setURL(String URL) {
        this.URL = URL;
    }

    /**
     * isRedeemed() method
     * @return boolean value of whether is redeemed
     */
    public boolean isRedeemed() {
        return isRedeemed;
    }

    /**
     * instantiates isRedeemed() method
     * @param redeemed redeemed
     */
    public void setRedeemed(boolean redeemed) {
        isRedeemed = redeemed;
    }

    /**
     * toString() method
     * @return string description of additional information
     */
    @Override
    public String toString() {
        return "AdditionalInfo{" +
                "id='" + id + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", fileName='" + fileName + '\'' +
                ", URL='" + URL + '\'' +
                ", isRedeemed=" + isRedeemed +
                '}';
    }
}

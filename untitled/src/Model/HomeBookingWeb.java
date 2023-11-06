package Model;

import BookAndTest.AdditionalInfo;
import BookAndTest.Booking;

import java.net.http.HttpResponse;
import java.time.Instant;

/**
 * HomeBookingWeb class that represents the web service class for home booking system
 */
public class HomeBookingWeb extends RESTApi {
    /**
     * instance of HomeBookingWeb
     */
    private static HomeBookingWeb instance;
    private static final String rootUrl = "https://fit3077.com/api/v2";

    private HomeBookingWeb() {
    }

    /**
     * getInstance() method
     * @return instance of HomeBookingWeb
     */
    public static HomeBookingWeb getInstance(){
        if(instance == null ){
            instance = new HomeBookingWeb();
        }
        return instance;
    }

    /**
     * updateQrCode() method that updates the QR code for user
     * @param booking booking details
     */
    public void updateQrCode(Booking booking) throws Exception {
        String url = rootUrl+"/booking/"+booking.getBookingId();
        AdditionalInfo ai = new AdditionalInfo(getAlphaNumericString(25),Instant.now().toString(),
                getAlphaNumericString(10),"www."+getAlphaNumericString(8)+".com",false);
        String additionalInfo= "{" +
                "\"id\":\"" + ai.getId() + "\"," +
                "\"dateCreated\":\"" + ai.getDateCreated() + "\"," +
                "\"fileName\":\"" + ai.getFileName()+ "\"," +
                "\"URL\":\"" + ai.getURL()+ "\"," +
                "\"IsRedeemed\":\"" +String.valueOf(ai.isRedeemed()) + "\"" +
                "}";
        String jsonString = "{" +
                "\"additionalInfo\":" + additionalInfo  +
                "}";
        HttpResponse<String> response = patch(url,jsonString);
        if (response.statusCode() == 200 ){
            booking.setAdditionalInfo(ai);
            System.out.println(ai);
        }
        else{
            System.out.println("Error Occur");
        }
    }

    /**
     * getAlphaNumericString() method
     * @param n integer value of n
     * @return string value of alphanumeric string
     */
    public String getAlphaNumericString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length()
                    * Math.random());

            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }
}

package Model;

import BookAndTest.Booking;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import Enum.COVIDTestTag;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.Instant;

/**
 * TestingWeb class that represents the web service class for testing system
 */
public class TestingWeb extends RESTApi {
    private static final String rootUrl = "https://fit3077.com/api/v2";
    /**
     * instance of TestingWeb
     */
    private static TestingWeb instance;

    private TestingWeb() {
    }

    /**
     * getInstance() method
     * @return instance of TestingWeb
     */
    public static TestingWeb getInstance() {
        if (instance == null) {
            instance = new TestingWeb();
        }
        return instance;
    }

    /**
     * getBooking() method
     * @param bookingId booking ID
     * @return booking
     */
    public Booking getBooking(String bookingId) throws Exception {
        String bookUrl = rootUrl+"/booking/"+bookingId+"?fields=covidTests";
        HttpResponse <String>response = get(bookUrl);
        if (response.statusCode()==200){
        ObjectNode jsonNode = new ObjectMapper().readValue(response.body(),ObjectNode.class);
        return createBooking(jsonNode);
        }
        else{
            return null;
        }
    }

    /**
     * upload() method
     * @param booking booking
     * @return boolean value of upload testing status
     */
    public boolean upload(Booking booking) throws IOException, InterruptedException {
        String type = "";
        if(booking.getTest().hasCapability(COVIDTestTag.IS_RAPID_ANTIGEN_TEST)){
            type = "RAT";
        }
        else if(booking.getTest().hasCapability(COVIDTestTag.IS_RT_PCR)){
            type = "PCR";

        }
        String jsonString = "{" +
                "\"type\":\"" + type + "\"," +
                "\"patientId\":\"" + booking.getCustomer().getId() + "\"," +
                "\"administererId\":\"" + booking.getTest().getHealthCareWorker().getId() + "\"," +
                "\"bookingId\":\"" + booking.getBookingId() + "\"," +
                "\"result\":\"" + booking.getTest().getResult() + "\"," +
                "\"status\":\"" + booking.getTest().getStatus() + "\"," +
                "\"notes\":\"" + booking.getTest().getNotes() + "\"," +
                "\"additionalInfo\":" + "{}"  +
                "}";
        HttpResponse<String> response = post(rootUrl+"/covid-test",jsonString);
        System.out.println(response.body());
        if (response.statusCode() == 201){
            ObjectNode jsonNodes = new ObjectMapper().readValue(response.body(),ObjectNode.class);
            booking.getTest().setId(jsonNodes.get("id").textValue());
            System.out.println(jsonNodes.get("id").textValue());
            return true;
        }
        return false;
    }

    /**
     * uploadRes() method
     * uploads the test result
     * @param booking booking
     * @return boolean value of upload status
     */
    public boolean updateRes(Booking booking) throws Exception {
        String type = "";
        if(booking.getTest().hasCapability(COVIDTestTag.IS_RAPID_ANTIGEN_TEST)){
            type = "RAT";
        }
        else if(booking.getTest().hasCapability(COVIDTestTag.IS_RT_PCR)){
            type = "PCR";

        }
        String jsonString = "{" +
                "\"type\":\"" + type + "\"," +
                "\"patientId\":\"" + booking.getCustomer().getId() + "\"," +
                "\"administererId\":\"" + booking.getTest().getHealthCareWorker().getId() + "\"," +
                "\"bookingId\":\"" + booking.getBookingId() + "\"," +
                "\"result\":\"" + booking.getTest().getResult() + "\"," +
                "\"status\":\"" + booking.getTest().getStatus() + "\"," +
                "\"notes\":\"" + booking.getTest().getNotes() + "\"," +
                "\"datesOfResults\":\"" + Instant.now().toString()+ "\""+
                "}";
        System.out.println(booking.getTest());
        HttpResponse<String> response = patch(rootUrl+"/covid-test/"+booking.getTest().getId(),jsonString);
        System.out.println(response.body());
        if (response.statusCode() == 200){
            return true;
        }
        return false;
    }
}

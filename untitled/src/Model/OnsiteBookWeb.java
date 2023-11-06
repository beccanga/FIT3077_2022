package Model;

import BookAndTest.Booking;
import BookAndTest.BookingManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.net.http.HttpResponse;
import java.time.Instant;

/**
 * OnsiteBookWeb class that represents the web service class for on site booking system
 */
public class OnsiteBookWeb extends RESTApi{
    private static final String rootUrl = "https://fit3077.com/api/v2";
    /**
     * instance of OnsiteBookWeb
     */
    private static OnsiteBookWeb instance;
    String bookingUrl = rootUrl + "/booking";

    private OnsiteBookWeb(){}

    /**
     * getInstance() method
     * @return instance of OnsiteBookWeb
     */
    public static OnsiteBookWeb getInstance(){
        if(instance==null){
            instance = new OnsiteBookWeb();
        }
        return instance;
    }

    /**
     * uploadDetail() method that uploads the details of the booking
     * @param booking booking
     * @return booking created
     */
    public Booking uploadDetail(Booking booking) throws Exception{
        String jsonString = "{" +
                "\"customerId\":\"" + booking.getCustomer().getId() + "\"," +
                "\"testingSiteId\":\"" + booking.getTestingSite().getId() + "\"," +
                "\"startTime\":\"" + booking.getDate() + "\"," +
                "\"notes\":\"" + booking.getNotes() + "\"," +
                "\"additionalInfo\":" + "{}"  +
                "}";
        HttpResponse<String> response = post(bookingUrl,jsonString);
        if(response.statusCode()!=201){
            return null;
        }
        ObjectNode jsonNode = new ObjectMapper().readValue(response.body(),ObjectNode.class);
        return createBooking(jsonNode);
    }

    /**
     * loadBooking() method
     * @return booking manager
     */
    public BookingManager loadBooking() throws Exception{
        String bookUrl = rootUrl + "/booking?fields=covidTests";
        BookingManager bm = new BookingManager();
        HttpResponse<String> response = get(bookUrl);
        ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);
        for (ObjectNode i:jsonNodes){
            bm.add(createBooking(i));
        }
        return bm;
    }

    /**
     * uploadTest() method
     * @param booking booking
     */
    public void uploadTest(Booking booking) throws Exception {
        boolean boo =TestingWeb.getInstance().upload(booking);
        String testUrl = rootUrl+"/covid-test/"+booking.getTest().getId();
        String date = Instant.now().plusSeconds(172800).toString(); //two days
        String jsonString = "{" +
                "\"id\":\"" + booking.getTest().getId() + "\"," +
                "\"patientId\":\"" + booking.getCustomer().getId() + "\"," +
                "\"administererId\":\"" + booking.getTest().getHealthCareWorker().getId() + "\"," +
                "\"bookingId\":\"" + booking.getBookingId() + "\"," +
                "\"datePerformed\":\"" + date + "\"" +
                "}";
        HttpResponse<String> response=patch(testUrl,jsonString);
        if (response.statusCode()==200){
            booking.getTest().setDatePerformed(date);
            System.out.println("success");
        }
    }

    /**
     * userBooking method returns the booking from the user
     * @param userid user id
     * @param bookingid booking id
     * @return booking information
     * @throws Exception
     */
    public Booking userBooking(String userid,String bookingid) throws Exception {
        String url = null;
        Booking booking = null;
        if(bookingid ==null) {
            url = "https://fit3077.com/api/v2/user/" + userid + "?fields=bookings.covidTests";
            HttpResponse<String> response = get(url);
            ObjectNode node = new ObjectMapper().readValue(response.body(),ObjectNode.class);
            JsonNode bookingNode = node.get("bookings").get(0);
            booking = createBooking((ObjectNode) bookingNode);
        }
        else{
            url = "https://fit3077.com/api/v2/booking/"+bookingid+"?fields=covidTests";
            HttpResponse<String> response = get(url);
            ObjectNode node = new ObjectMapper().readValue(response.body(),ObjectNode.class);
            booking = createBooking((ObjectNode) node);
        }

        return booking;
    }

    /**
     * modifyDate method modifies the date of the booking
     * @param book booking information
     * @throws Exception
     */
    public void modifyDate(Booking book) throws Exception {
        String url = "https://fit3077.com/api/v2/covid-test/"+ book.getTest().getId();
        String jsonString = "{" +
                "\"datePerformed\":\"" + book.getTest().getDatePerformed() + "\"" +
                "}";
        patch(url,jsonString);
    }

    /**
     * modifyTestSite method modifies the test site of the booking
     * @param book booking information
     * @throws Exception
     */
    public void modifyTestSite(Booking book) throws Exception {
        String url = "https://fit3077.com/api/v2/booking/"+ book.getBookingId();
        String jsonString = "{" +
                "\"testingSiteId\":\"" + book.getTestingSite().getId() + "\"" +
                "}";
        patch(url,jsonString);
    }

    /**
     * cancelBooking method cancels the booking of user
     * @param book booking information
     * @throws Exception
     */
    public void cancelBooking(Booking book) throws Exception {
        String testurl = "https://fit3077.com/api/v2/covid-test/"+ book.getTest().getId();
        String bookurl = "https://fit3077.com/api/v2/booking/"+ book.getBookingId();

        String jsonString = "{" +
                "\"status\":\"" + "CANCELLED" + "\"" +
                "}";
        patch(testurl,jsonString);
        patch(bookurl,jsonString);
    }
}

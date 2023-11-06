package Model;


import BookAndTest.Booking;
import TestingSitePkg.TestingSite;
import User.UserManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.net.http.HttpResponse;
/**
 * AdminPanelWeb class that represents the web service class for admin panel system
 */
public class AdminPanelWeb extends RESTApi{
    /**
     * instance of AdminPanelWeb
     */
    private static AdminPanelWeb instance;
    private AdminPanelWeb(){}

    /**
     * getInstance() method
     * @return instance of AdminPanelWeb
     */
    public static AdminPanelWeb getInstance(){
        if(instance == null) {
            instance = new AdminPanelWeb();
        }
        return instance;
    }

    /**
     * getBooking method gets the booking information
     * @param id booking id
     * @return
     * @throws Exception
     */
    public Booking getBooking(String id) throws Exception{
        String bookurl = "https://fit3077.com/api/v2/booking/"+id+"?fields=covidTests";
        HttpResponse<String> response = get(bookurl);
        ObjectNode node = new ObjectMapper().readValue(response.body(),ObjectNode.class);
        Booking book = createBooking(node);
        return book;
    }

    /**
     * deleteBooking method deletes the booking
     * @param book
     * @throws Exception
     */
    public void deleteBooking(Booking book) throws Exception {
        String bookurl = "https://fit3077.com/api/v2/booking/"+book.getBookingId();
        String testurl = "https://fit3077.com/api/v2/covid-test/"+book.getTest().getId();
        delete(testurl);
        delete(bookurl);
    }

    /**
     * getCurrentUserWorkingLocation method gets the current user's working location
     * @return testing site
     * @throws Exception
     */
    public TestingSite getCurrentUserWorkingLocation() throws Exception{
        String userId = UserManager.getInstance().getCurrentUser().getId();
        String url =  "https://fit3077.com/api/v2/user/"+ userId +"?fields=testsAdministered";

        HttpResponse<String> response = get(url);

        ObjectNode userNode = new ObjectMapper().readValue(response.body(),ObjectNode.class);


        return createTestingSite((ObjectNode) userNode.get("testsAdministered").get(0).get("booking").get("testingSite"));
    }
}

package Model;

import BookAndTest.Booking;
import BookAndTest.CovidTest;
import BookAndTest.PCRTest;
import BookAndTest.RATTest;
import TestingSitePkg.Address;
import TestingSitePkg.TestingSite;
import User.Admin;
import User.Customer;
import User.HealthCareWorker;
import User.User;
import User.UserManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * RESTApi class represents the api interface
 */
public class RESTApi {
    private static final String myApiKey = "";

    public RESTApi() {
        super();
    }

    /**
     * get() method to retrieve the information from API resource
     * @param url url
     * @return HttpResponse
     */
    public HttpResponse<String> get(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;

    }

    /**
     * post() method used to create API resource
     * @param url url
     * @param JsonString json string
     * @return HttpResponse
     */
    public HttpResponse<String> post(String url,String JsonString) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)) // Return a JWT so we can use it in Part 5 later.
                .setHeader("Authorization", myApiKey)
                .header("Content-Type","application/json") // This header needs to be set when sending a JSON request body.
                .POST(HttpRequest.BodyPublishers.ofString(JsonString))
                .build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        return response;
    }

    /**
     * patch() method used to modifies API resource
     * @param url url
     * @param jsonString json string
     * @return HttpResponse
     */
    public HttpResponse<String> patch(String url, String jsonString) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .method("PATCH",HttpRequest.BodyPublishers.ofString(jsonString))
                .setHeader("Authorization", myApiKey)
                .header("Content-Type","application/json")
                .build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        return response;
    }
    public HttpResponse<String> delete(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url))
                .setHeader("Authorization", myApiKey)
                .DELETE()
                .build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        return response;
    }


    /**
     * createAddress() method
     * @param addressNode address node
     * @return address created
     */
    public Address createAddress(JsonNode addressNode){
        return new Address(addressNode.get("latitude").asDouble(),addressNode.get("longitude").asDouble(),
                addressNode.get("unitNumber").asInt(),addressNode.get("street").textValue(),addressNode.get("street2").textValue(),
                addressNode.get("suburb").textValue(),addressNode.get("state").textValue(),addressNode.get("postcode").asInt());
    }

    /**
     * createCustomer() method
     * @param jsonNode json node
     * @return customer created
     */
    public Customer createCustomer(ObjectNode jsonNode){
        return new Customer(jsonNode.get("id").textValue(),
                jsonNode.get("givenName").textValue(),
                jsonNode.get("familyName").textValue(),
                jsonNode.get("userName").textValue(),
                jsonNode.get("phoneNumber").asInt());
    }

    /**
     * createHealthCareWorker() method
     * @param jsonNode json node
     * @return healthcare worker created
     */
    public HealthCareWorker createHealthCareWorker(ObjectNode jsonNode){
        return new HealthCareWorker(jsonNode.get("id").textValue(),
                jsonNode.get("givenName").textValue(),
                jsonNode.get("familyName").textValue(),
                jsonNode.get("userName").textValue(),
                jsonNode.get("phoneNumber").asInt());
    }

    /**
     * createAdmin() method
     * @param jsonNode json node
     * @return admin created
     */
    public Admin createAdmin(ObjectNode jsonNode){
        return new Admin(jsonNode.get("id").textValue(),
                jsonNode.get("givenName").textValue(),
                jsonNode.get("familyName").textValue(),
                jsonNode.get("userName").textValue(),
                jsonNode.get("phoneNumber").asInt());
    }

    /**
     * createTestingSite() method
     * @param jsonNodes json nodes
     * @return testing site created
     */
    public TestingSite createTestingSite(ObjectNode jsonNodes){
        Address address = createAddress(jsonNodes.get("address"));
        return new TestingSite(jsonNodes.get("id").textValue(),
                jsonNodes.get("name").textValue(),
                jsonNodes.get("description").textValue(),
                jsonNodes.get("websiteUrl").textValue(),
                jsonNodes.get("phoneNumber").asInt(),
                address);
    }

    /**
     * createBooking() method
     * @param jsonNode json node
     * @return booking
     */
    public Booking createBooking(ObjectNode jsonNode){
        Customer customer=null;
        try {
            customer = createCustomer((ObjectNode) jsonNode.get("customer"));
        }catch (Exception ex){
            customer = (Customer)UserManager.getInstance().getCurrentUser();
        }
        TestingSite testingSite = createTestingSite((ObjectNode) jsonNode.get("testingSite"));
        Booking booking = new Booking(jsonNode.get("id").textValue(),customer, testingSite,jsonNode.get("notes").textValue(),
                jsonNode.get("startTime").textValue(),jsonNode.get("smsPin").asInt());
        if(jsonNode.get("covidTests")==null){
            return booking;
        }

        if(jsonNode.get("covidTests").get(0)!=null){
            CovidTest test = createTest((ObjectNode)jsonNode.get("covidTests").get(0));
            booking.setTest(test);
        }
        return booking;

    }

    /**
     * validateCust() method to validate customer information
     * @param custId customer ID
     * @return customer
     */
    public Customer validateCust(String custId) throws Exception{
        String custUrl = "https://fit3077.com/api/v1/user/"+custId;
        HttpResponse<String> response = get(custUrl);
        if(response.statusCode()!=200){
            return null;
        }
        ObjectNode jsonNode = new ObjectMapper().readValue(response.body(), ObjectNode.class);
        Customer cust = createCustomer(jsonNode);
        return cust;

    }

    /**
     * validateTestSite() method to validate testing site information
     * @param testId test ID
     * @return testing site
     */
    public TestingSite validateTestSite(String testId) throws Exception{
        String testUrl = "https://fit3077.com/api/v1/testing-site/"+testId;
        HttpResponse<String> response = get(testUrl);
        if(response.statusCode()!=200){
            return null;
        }
        ObjectNode jsonNodes = new ObjectMapper().readValue(response.body(),ObjectNode.class);
        TestingSite ts = createTestingSite(jsonNodes);
        return ts;
    }

    /**
     * createTest() method
     * @param jsonNodes json nodes
     * @return covid test created
     */
    public CovidTest createTest(ObjectNode jsonNodes) {
        User admin = null;

        String id = jsonNodes.get("id").textValue();
        Customer cust = createCustomer((ObjectNode) jsonNodes.get("patient"));
        if (jsonNodes.get("administerer").get("isHealthcareWorker").asBoolean()) {
            admin = createHealthCareWorker((ObjectNode) jsonNodes.get("administerer"));
        }
        else{
            admin = createAdmin((ObjectNode) jsonNodes.get("administerer"));
        }
        String result = jsonNodes.get("result").textValue();
        String status = jsonNodes.get("status").textValue();
        String notes = jsonNodes.get("notes").textValue();
        String datePerformed = jsonNodes.get("datePerformed").textValue();
        if (jsonNodes.get("type").textValue().equals("RAT")){
            return new RATTest(id,cust,admin,result,status,notes,datePerformed);
        }
        else{
            return new PCRTest(id,cust,admin,result,status,notes,datePerformed);
        }
    }

}


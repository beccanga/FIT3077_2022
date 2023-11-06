package Model;


import TestingSitePkg.Address;
import TestingSitePkg.TestingSite;
import TestingSitePkg.TestingSiteManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

/**
 * TestingSiteWeb class that represents the web service class for testing site system
 */
public class TestingSiteWeb extends RESTApi{
    private static final String rootUrl = "https://fit3077.com/api/v2";
    private String url = rootUrl + "/testing-site";
    /**
     * instance of TestingSiteWeb
     */
    private static TestingSiteWeb instance;

    private TestingSiteWeb(){}

    /**
     * getInstance() method
     * @return instance of TestingSiteWeb
     */
    public static TestingSiteWeb getInstance(){
        if(instance == null){
            instance = new TestingSiteWeb();
        }
        return instance;
    }

    /**
     * showList() method that retrieves the list of testing site from testing site manager
     */
    public void showList() throws Exception {
        TestingSiteManager.getInstance().printArr();
    }

    /**
     * loadList() method that loads the list of testing sites
     * @throws Exception
     */
    public void loadList() throws Exception {
        ObjectNode [] jsonNode = new ObjectMapper().readValue(get(url).body(), ObjectNode[].class);
        for(int i = 0 ; i < jsonNode.length;i++){
            JsonNode addressNode = jsonNode[i].get("address");
            ObjectNode testNode = jsonNode[i];
            TestingSite ts = createTestingSite(testNode);
            TestingSiteManager.getInstance().add(ts);
        }
    }

    /**
     * findBySuburb() method finds the testing site by suburb
     * @param suburb suburb
     */
    public void findBySuburb(String suburb){
        ArrayList<TestingSite> arr = TestingSiteManager.getInstance().findBySuburb(suburb);
        for (int i = 0 ;i < arr.size();i++){
            System.out.println(arr.get(i));
        }
    }

    /**
     * createAdress() method
     * @param addressNode address node
     * @return address created
     */
    public Address createAddress(JsonNode addressNode){
        return new Address(addressNode.get("latitude").asDouble(),addressNode.get("longitude").asDouble(),
                addressNode.get("unitNumber").asInt(),addressNode.get("street").textValue(),addressNode.get("street2").textValue(),
                addressNode.get("suburb").textValue(),addressNode.get("state").textValue(),addressNode.get("postcode").asInt());

    }

    /**
     * createTestingSite() method
     * @param jsonNode json node
     * @return testing site created
     */
    public TestingSite createTestingSite(ObjectNode jsonNode){
        return new TestingSite(jsonNode.get("id").textValue(),
            jsonNode.get("name").textValue(),
            jsonNode.get("description").textValue(),
            jsonNode.get("websiteUrl").textValue(),
            jsonNode.get("phoneNumber").asInt(),
            createAddress(jsonNode.get("address")));
    }


}

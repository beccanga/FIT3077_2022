package TestingSitePkg;

import java.util.ArrayList;

/**
 * TestingSiteManager class that represents the Testing Site Manager
 */
public class TestingSiteManager {
    private ArrayList<TestingSite> testingSites;
    private static TestingSiteManager instance;

    /**
     * TestingSiteManager constructor
     */
    public TestingSiteManager() {
        this.testingSites = new ArrayList<TestingSite>();
    }

    /**
     * getInstance() method of TestingSiteManager
     * @return instance of TestingSiteManager
     */
    public static TestingSiteManager getInstance(){
        if(instance==null){
            instance = new TestingSiteManager();
        }
        return instance;
    }

    /**
     * add() method to add the testing site
     * @param ts testing sites
     */
    public void add(TestingSite ts){
        testingSites.add(ts);
    }

    /**
     * printArr() method
     * that prints the contents of the array list
     */
    public void printArr(){
        for(int i =0 ; i< testingSites.size();i++){
            System.out.println(testingSites.get(i));
        }
    }
    public ArrayList<TestingSite> findBySuburb(String suburb){
        ArrayList<TestingSite> arr = new ArrayList<TestingSite>();
        for(int i =0 ; i< testingSites.size();i++){
            if(suburb.toLowerCase().equals(testingSites.get(i).getAddress().getSuburb().toLowerCase())){
                arr.add(testingSites.get(i));
            }
        }
        return arr;
    }

    public ArrayList<TestingSite> getTestingSites() {
        return testingSites;
    }
}

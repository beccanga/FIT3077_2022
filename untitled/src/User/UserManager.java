package User;

/**
 * User manager class to manage the users
 */
public class UserManager {
    private static UserManager instance;
    private User currentUser;
    private UserManager(){
    }

    /**
     * getInstance method
     * @return instance of UserManager
     */
    public static UserManager getInstance(){
        if (instance==null){
            instance = new UserManager();
        }
        return instance;
    }

    /**
     * getCurrentUser method
     * @return currentUser
     */
    public User getCurrentUser(){
        return this.currentUser;
    }

    /**
     * setCurrentUserName method
     * instantiates the current user
     * @param currentUser current user
     */
    public void setCurrentUserName(User currentUser){
        this.currentUser=currentUser;
    }
    }




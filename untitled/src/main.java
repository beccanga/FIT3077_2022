import Controller.LoginController;
import View.View;

public class main {
    public static void main(String[] args) throws Exception {
        LoginController lc = new LoginController();
        lc.login(new View());
    }
}

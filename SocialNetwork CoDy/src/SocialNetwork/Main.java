package SocialNetwork;

import Controller.RegisterPageController;
import View.RegisterPageView;

public class Main {
    public static void main(String[] args) {
        //new HomePage(new User(),new Database() );
      new RegisterPageController(new RegisterPageView());
        //new Modify(new User(),new Database());
    }
}

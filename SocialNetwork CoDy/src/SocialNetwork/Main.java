package SocialNetwork;

import Controller.RegisterPageController;
import View.RegisterPageView;

public class Main {
    public static void main(String[] args) {
      new RegisterPageController(new RegisterPageView());
    }
}

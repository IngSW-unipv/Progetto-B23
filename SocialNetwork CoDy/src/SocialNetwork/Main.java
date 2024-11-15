package SocialNetwork;

import Controller.RegisterPageController;
import View.RegisterPageView;

/**
 * Classe principale del progetto.
 * @author Corrado
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
      new RegisterPageController(new RegisterPageView());
    }
}

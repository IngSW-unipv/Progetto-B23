package Controller;

import View.AlertView;
import View.HomePageView;
import View.JTextArea;
import model.User;
import model.Post;
import model.HomePage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * La classe HomePageController rappresenta il controller per la homepage
 * Questa classe fornisce metodi per gestire la homepage
 *
 * @author Corrado
 * @version 1.1
 */
public class HomePageController {
    private final User user;
    private final HomePageView homePageView;
    private final HomePage homePageModel;
    private final String hint = "A cosa stai pensando?";

    /**
     * Costruttore della classe HomePageController
     * @param user Utente che ha aperto la homepage
     * @param homePageView Vista della homepage
     */
    public HomePageController(User user, HomePageView homePageView) {
        this.user = user;
        this.homePageView = homePageView;
        this.homePageModel = new HomePage(user);

        homePageView.getStampaNome().setText("Ciao, " + user.stampaNome());

        this.homePageView.getPostButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handlePostAction();
            }
        });

        generateTimeline();
    }

    /**
     * Funzione che gestisce l'azione di pubblicazione di un post
     */
    private void handlePostAction() {
        JTextArea postInput = homePageView.getPostInput();
        String postText = postInput.getText().trim();

        if (postText.isEmpty() || postText.equals(hint)) {
            new AlertView("Il post è vuoto", homePageView.getFrame());
            return;
        }

        if (homePageModel.pubblicaPost(postText)) {
            new AlertView("Postato con successo", homePageView.getFrame());
            postInput.setText("");
            homePageView.addPostToTimeline(user, new Post(postText, user));
        }
    }

    /**
     * Funzione che genera la timeline dell'utente
     */
    private void generateTimeline() {
        ArrayList<Post> posts = homePageModel.caricaTimeline();
        for (Post post : posts) {
            homePageView.addPostToTimeline(user, post);
        }
    }
}
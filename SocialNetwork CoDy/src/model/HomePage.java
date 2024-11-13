package model;

import database.PostDAO;
import java.util.ArrayList;

/**
 * La classe HomePageModel gestisce la logica di business per la homepage dell'utente.
 *
 * @author Corrado
 * @version 1.1
 */
public class HomePage {
    private final User user;

    /**
     * Costruttore della classe HomePageModel.
     *
     * @param user l'utente corrente
     */
    public HomePage(User user) {
        this.user = user;
    }

    /**
     * Carica la timeline dell'utente.
     *
     * @return una lista di post dell'utente.
     */
    public ArrayList<Post> caricaTimeline() {
        PostDAO postDAO = new PostDAO();
        return postDAO.generateTimeline(user);
    }

    /**
     * Pubblica un nuovo post per l'utente.
     *
     * @param testo Il contenuto del post.
     * @return true se il post è stato pubblicato correttamente, false altrimenti.
     */
    public boolean pubblicaPost(String testo) {
        Post post = new Post(testo, user);
        PostDAO postDAO = new PostDAO(post);
        return postDAO.posted();
    }
}

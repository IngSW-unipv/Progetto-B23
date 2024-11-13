package Controller;

import View.HomePageView;
import View.PostTab;
import View.RegisterPageView;
import View.TabView;
import database.LikeDAO;
import database.PostDAO;
import database.UserDAO;
import model.Post;
import model.User;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


/**
 * La classe TabController rappresenta il controller per la gestione delle tab.
 * Questa classe fornisce metodi per gestire le diverse visualizzazioni delle tab basate su azioni utente
 * come visualizzare utenti, post, like o effettuare il logout.
 *
 * @author Dylan
 * @version 1.0
 */
public class TabController {
    public TabView tabView;

    /**
     * Costruttore per la classe TabController.
     * La tab viene scelta con uno switch case.
     *
     * @param view nome della tab che si vuole creare
     * @param user utente che ha richiesto la creazione della tab
     */
    public TabController(String view, User user) {
        this.tabView = new TabView();

        tabView.getTitle().setText(view);
        tabView.getFrame().addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                tabView.getFrame().dispose();
                new HomePageController(user, new HomePageView(user));
            }
        });

        tabView.getHome().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabView.getFrame().dispose();
                new HomePageController(user, new HomePageView(user));
            }
        });

        switch (view){
            case "Amici":
                ArrayList<User> users= new UserDAO().readAllUsers(user);
                for(User u : users){
                    tabView.getPanel().add(Box.createVerticalStrut(7));
                    tabView.getPanel().add(new FriendsController(user, u).getFriendsView());
                }
                break;
            case "Posts":
                ArrayList<Post> posts = new PostDAO().readUserPosts(user);
                for(Post p : posts){
                    tabView.getPanel().add(Box.createVerticalStrut(7));
                    tabView.getPanel().add(new PostTab(user, p, tabView.getFrame(), true));
                }
                break;
            case "Like":
                ArrayList<Post> likes = new LikeDAO().ReadUserLikes(user);
                for(Post p : likes){
                    tabView.getPanel().add(Box.createVerticalStrut(7));
                    tabView.getPanel().add(new PostTab(user,p,tabView.getFrame(),true));
                }
                break;

            case "Logout":
                new RegisterPageController(new RegisterPageView());
                break;

        }
    }
}

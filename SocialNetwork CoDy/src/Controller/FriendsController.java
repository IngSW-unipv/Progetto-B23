package Controller;

import View.FriendsView;
import database.FriendDAO;
import model.User;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * La classe FriendsController gestisce le interazioni dell'utente con la vista degli amici.
 * Fornisce funzionalità per aggiungere e rimuovere amici.
 *
 * @author Corrado
 * @version 1.0
 */
public class FriendsController {
    public FriendsView friendsView;


/**
 * Costruttore della classe FriendsController.
 * Inizializza la vista degli amici per un determinato utente e imposta i listener per le azioni di aggiunta e rimozione di amici.
 *
 * @param utentePrincipale L'utente principale che sta visualizzando la lista degli amici.
 * @param u L'utente amico da gestire nella vista.
 */
    public FriendsController(User utentePrincipale, User u) {
        this.friendsView = new FriendsView(u);

        friendsView.getRimuoviLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (new FriendDAO(utentePrincipale, u).isRemoved()) {
                    utentePrincipale.removeFriend(u);
                    friendsView.getRimuoviLabel().setVisible(false);
                    friendsView.getAddFriendButton().setVisible(true);
                }
            }


        });

        if(utentePrincipale.isFriend(u)){
            friendsView.getAddFriendButton().setVisible(false);
            friendsView.getRimuoviLabel().setVisible(true);
        }else {
            friendsView.getAddFriendButton().setVisible(true);
            friendsView.getRimuoviLabel().setVisible(false);
        }

        friendsView.getAddFriendButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (new FriendDAO(utentePrincipale, u).isAdded()) {
                    utentePrincipale.addFriend(u);
                    friendsView.getAddFriendButton().setVisible(false);
                    friendsView.getRimuoviLabel().setVisible(true);
                }
            }
        });

    }


    /**
     * Ritorna la vista degli amici associata a questo controller.
     *
     * @return la vista degli amici.
     */
    public Component getFriendsView() {
        return friendsView;
    }
}

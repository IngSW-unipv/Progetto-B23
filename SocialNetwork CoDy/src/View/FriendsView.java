package View;

import model.User;

import javax.swing.*;
import java.awt.*;

/**
 * La classe FriendsView rappresenta la vista degli utenti
 * Questa classe fornisce metodi per gestire la vista degli utenti
 *
 * @author Corrado
 * @version 1.0
 */
public class FriendsView extends JPanel {
    public JLabel rimuovi;
    public JButton addFriend;

    public JFrame frame;

    /**
     * Costruttore della classe FriendsView
     * Inizializza la vista degli utenti
     * @param u l'utente di cui si vuole visualizzare la vista
     */
    public FriendsView(User u){
        setLayout((new BorderLayout()));
        setBackground(ColorGUIView.white);
        setBorder(BorderFactory.createEmptyBorder(10,15,10,15));

        JLabel author= new JLabel(u.stampaNome(), 20, ColorGUIView.black,Font.BOLD);
        add(author,BorderLayout.WEST);

        JPanel right =new JPanel(new FlowLayout(FlowLayout.RIGHT));
        right.setBackground(null);

        addFriend = new JButton("Follow",35,16);
        addFriend.setPreferredSize(new Dimension(81,37));
        addFriend.setVisible(false);

        right.add(addFriend);

        rimuovi = new JLabel("Unfollow",16, ColorGUIView.red,Font.BOLD );
        rimuovi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rimuovi.setVisible(false);
        rimuovi.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        right.add(rimuovi);

        add(right);

        Dimension dimension = new Dimension(500,67);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
    }

    /**
     * Restituisce il bottone per aggiungere/rimuovere un amico
     * @return il bottone per aggiungere un amico
     */
    public JButton getAddFriendButton() {
        return addFriend;
    }

    /**
     * Restituisce l'etichetta per rimuovere un amico
     * @return l'etichetta per rimuovere un amico
     */
    public JLabel getRimuoviLabel() {
        return rimuovi;
    }

    /**
     * Restituisce il frame a cui e' associato il pannello
     * @return il frame a cui e' associato il pannello
     */
    public JFrame getFrame() {
        return frame;
    }
}

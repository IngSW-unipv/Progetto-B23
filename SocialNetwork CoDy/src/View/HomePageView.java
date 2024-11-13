package View;

import model.User;
import javax.swing.*;
import java.awt.*;

/**
 * La classe HomePageView rappresenta la vista della homepage dell'utente.
 *
 * Questa classe fornisce metodi per creare e gestire la vista della homepage dell'utente.
 *
 * @author Corrado
 * @version 1.1
 */
public class HomePageView {
    private final JFrame frame;
    private final JPanel postPanel;
    private final JTextArea postIn;
    private final JButton postBtn;
    private final JLabel stampaNome;



    /**
     * Costruisce la vista della homepage per l'utente specificato.
     *
     * @param user l'utente per il quale viene costruita la homepage
     */
    public HomePageView(User user) {
        frame = new JFrame();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setResizable(false);

        JPanel sidebar = new JPanel();
        sidebar.setBackground(ColorGUIView.side);
        sidebar.setPreferredSize(new Dimension(100, 0));
        sidebar.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel filler = new JPanel();
        filler.setPreferredSize(new Dimension(300, 120));
        filler.setBackground(ColorGUIView.side);
        ProfileButton profilo = new ProfileButton("Modifica","profile", user, frame);

        sidebar.add(filler);
        sidebar.add(profilo);
        sidebar.add(new SideButton("Posts", "post", user, frame));
        sidebar.add(new SideButton("Amici", "people", user, frame));
        sidebar.add(new SideButton("Like","Ilike",user,frame));
        sidebar.add(new SideButtonLogout("Logout", "Logout", user, frame));
        frame.getContentPane().add(sidebar, BorderLayout.WEST);

        postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.setBackground(null);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(ColorGUIView.side);
        Dimension dimension = new Dimension(500, 159);
        header.setPreferredSize(dimension);
        header.setMinimumSize(dimension);
        header.setMaximumSize(dimension);
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JPanel north = new JPanel(new BorderLayout());
        north.setBackground(null);
        stampaNome=new JLabel(" ", 20, ColorGUIView.white, Font.BOLD);
        north.add(stampaNome, BorderLayout.WEST);
        header.add(north, BorderLayout.NORTH);

        postIn = new JTextArea("A cosa stai pensando?", 18, 20);
        header.add(new JScrollPane(postIn), BorderLayout.CENTER);

        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.setBackground(null);

        postBtn = new JButton("Post", 35, 16);
        postBtn.setPreferredSize(new Dimension(81, 37));
        south.add(postBtn);

        header.add(south, BorderLayout.SOUTH);
        postPanel.add(header);

        frame.getContentPane().add(new JScrollPane(postPanel), BorderLayout.CENTER);
        frame.getContentPane().add(Box.createHorizontalStrut(182), BorderLayout.EAST);

        frame.setVisible(true);
        frame.requestFocus();
    }

    /**
     * Metodo che aggiunge un post alla timeline dell'utente
     * @param user l'utente che ha creato il post
     * @param post il post da aggiungere
     */
    public void addPostToTimeline(User user, model.Post post) {
        postPanel.add(Box.createVerticalStrut(7));
        postPanel.add(new PostTab(user, post, frame, true));
    }

    /**
     * Restituisce il frame della homepage.
     *
     * @return il frame della homepage
     */
    public JFrame getFrame() {

        return frame;
    }

    /**
     * Restituisce il pannello contenente la timeline dell'utente
     *
     * @return il pannello contenente la timeline dell'utente
     */
    public JTextArea getPostInput() {

        return postIn;
    }

    /**
     * Restituisce il pulsante per inviare un post.
     *
     * @return il pulsante per inviare un post
     */
    public JButton getPostButton() {

        return postBtn;
    }

    /**
     * Restituisce l'etichetta che contiene il nome dell'utente corrente
     *
     * @return l'etichetta che contiene il nome dell'utente corrente
     */
    public JLabel getStampaNome() {
        return stampaNome;
    }
}
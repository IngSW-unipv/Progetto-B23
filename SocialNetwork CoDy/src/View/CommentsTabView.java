package View;

import model.User;
import javax.swing.*;
import java.awt.*;

/**
 * La classe CommentsTabView rappresenta la vista della tab per la gestione dei commenti
 * Questa classe fornisce metodi per gestire la vista della tab per la gestione dei commenti
 *
 * @author Corrado
 * @version 1.0
 */
public class CommentsTabView {
    private final JTextArea commentIn;
    private final JButton commentBtn;
    private final JPanel panel;
    private final JFrame frame;

    /**
     * Costruttore della classe CommentsTabView.
     * Crea l'interfaccia utente per la gestione dei commenti, incluso il frame, il pannello e i componenti
     * per l'inserimento e la pubblicazione dei commenti.
     *
     * @param user l'utente attualmente connesso
     * @param post il post a cui i commenti si riferiscono
     */
    public CommentsTabView(User user, model.Post post){

        frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(null);

        panel.add(new PostTab(user, post, frame, false));


        panel.add(Box.createVerticalStrut(7));

        JPanel nuovoCommento = new JPanel(new BorderLayout());
        nuovoCommento.setBackground(ColorGUIView.side);

        Dimension dimension= new Dimension(500,58);
        nuovoCommento.setPreferredSize(dimension);
        nuovoCommento.setMaximumSize(dimension);
        nuovoCommento.setMinimumSize(dimension);
        nuovoCommento.setBorder(BorderFactory.createEmptyBorder(10,10,10,15));

        commentIn = new JTextArea("Scrivi un commento", 18,5);
        nuovoCommento.add(new JScrollPane(commentIn), BorderLayout.CENTER);

        commentBtn = new JButton("Invia", 35,16);
        commentBtn.setPreferredSize(new Dimension(81,37));


        nuovoCommento.add(commentBtn,BorderLayout.EAST);

        panel.add(nuovoCommento);
        panel.add(Box.createVerticalStrut(10));

        frame.getContentPane().add(new JScrollPane(panel));

        frame.setVisible(true);
        frame.requestFocus();
    }

    /**
     * Restituisce il frame della vista
     *
     * @return  frame
     */
    public JFrame getFrame() { return frame; }

    /**
     * Restituisce il campo di testo per l'inserimento dei commenti.
     *
     * @return commentIn, la JTextArea per l'inserimento dei commenti
     */
    public JTextArea getCommentIn() { return commentIn; }

    /**
     * Restituisce il bottone per la pubblicazione dei commenti.
     *
     * @return commentBtn, il bottone per la pubblicazione dei commenti
     */
    public JButton getCommentBtn() { return commentBtn; }

    /**
     * Restituisce il pannello contenente i commenti
     *
     * @return panel, il pannello contenente i commenti
     */
    public JPanel getPanel() { return panel; }

}


package View;

/**
 * La classe JFrame rappresenta la finestra principale dell'applicazione.
 * Questa classe fornisce metodi per gestire la finestra principale dell'applicazione.
 *
 * @author Dylan
 * @version 1.0
 */
public class JFrame extends javax.swing.JFrame{

    /**
     * Costruttore di JFrame che imposta il titolo e le dimensioni della finestra
     */
    public JFrame(){

        super("SocialCoDy");
        getContentPane().setBackground(ColorGUIView.background);
        setSize(900,625);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }
}

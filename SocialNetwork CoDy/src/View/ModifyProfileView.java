package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * La classe ModifyProfileView rappresenta la view per la modifica del profilo.
 *
 * Questa classe fornisce metodi per creare i campi di input per la modifica del profilo,
 * il pulsante per confermare le modifiche e il label per cambiare la password.
 *
 * @author Corrado
 * @version 1.0
 */
public class ModifyProfileView {
    private final JFrame frame;
    private final JTextField nome;
    private final JTextField cognome;
    private final JTextField email;
    private final JButton submit;
    private final JLabel cambiaPswLabel;

    /**
     * Costruttore per la view di modifica del profilo.
     * <p>
     * Inizializza i campi di input con i dati dell'utente e crea il pulsante per confermare le modifiche.
     * </p>
     */
    public ModifyProfileView() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(72, 84, 149, 84));

        JLabel titleLabel = new JLabel("             Modifica il tuo profilo", 40, ColorGUIView.white, Font.BOLD);
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(4, 1, 10, 10));
        center.setBackground(null);
        center.setBorder(BorderFactory.createEmptyBorder(50, 150, 17, 150));

        nome = new JTextField("Nome");
        center.add(nome);

        cognome = new JTextField("Cognome");
        center.add(cognome);

        email = new JTextField("Email");
        center.add(email);

        submit = new JButton("Conferma", 45, 20);
        center.add(submit);
        panel.add(center, BorderLayout.CENTER);

        cambiaPswLabel = new JLabel("Cambia Password", 20, Color.lightGray, Font.BOLD);
        cambiaPswLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cambiaPswLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(cambiaPswLabel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.requestFocus();
    }

    /**
     * Restituisce il frame della view
     *
     * @return frame della view
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Restituisce il campo di testo per l'inserimento del nome utente.
     *
     * @return campo di testo per l'inserimento del nome utente
     */
    public JTextField getNomeField() {
        return nome;
    }

/**
 * Restituisce il campo di testo per l'inserimento del cognome utente.
 *
 * @return campo di testo per l'inserimento del cognome utente
 */
    public JTextField getCognomeField() {
        return cognome;
    }

    /**
     * Restituisce il campo di testo per l'inserimento dell'email utente.
     *
     * @return campo di testo per l'inserimento dell'email utente
     */
    public JTextField getEmailField() {
        return email;
    }

    /**
     * Restituisce il pulsante per confermare le modifiche.
     *
     * @return pulsante per confermare le modifiche
     */
    public JButton getSubmitButton() {
        return submit;
    }

    /**
     * Restituisce il label per cambiare la password.
     *
     * @return label per cambiare la password
     */
    public JLabel getCambiaPswLabel() {
        return cambiaPswLabel;
    }

    /**
     * Aggiunge un listener al pulsante per confermare le modifiche.
     * @param listener listener da aggiungere
     */
    public void addSubmitListener(MouseListener listener) {
        submit.addMouseListener(listener);
    }

/**
 * Aggiunge un listener al label per cambiare la password.
 *
 * @param listener listener da aggiungere
 */
public void addCambiaPswListener(MouseListener listener) {
    cambiaPswLabel.addMouseListener(listener);
    }
}

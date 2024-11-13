package View;

import javax.swing.*;
import java.awt.*;

/**
 * La classe RegisterPageView rappresenta la vista della pagina di registrazione.
 * Questa classe fornisce l'interfaccia utente per consentire agli utenti di registrarsi
 * al social network inserendo i loro dati personali.
 *
 * @author dylan
 * @version 1.0
 */
public class RegisterPageView {
    private final JFrame frame;
    private final JTextField nome, cognome, email, password, confermaPassword;
    private final JButton creaAcc;
    private final JLabel login;


    /**
     * Costruttore della classe RegisterPageView.
     *
     * Crea la finestra di dialogo per la registrazione.
     *
     */
    public RegisterPageView() {
        frame = new JFrame();
        frame.setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(53,84,76,84));
        panel.add(new JLabel("           Benvenuto", 65, ColorGUIView.white, Font.BOLD), BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(6,1,10,10));
        center.setBackground(null);
        center.setBorder(BorderFactory.createEmptyBorder(22,150,17,150));

        nome = new JTextField("Nome");
        cognome = new JTextField("Cognome");
        email = new JTextField("Email");
        password = new JTextField("Password");
        confermaPassword = new JTextField("Conferma Password");

        center.add(nome);
        center.add(cognome);
        center.add(email);
        center.add(password);
        center.add(confermaPassword);

        creaAcc = new JButton("Crea Account", 45,20);
        center.add(creaAcc);
        panel.add(center, BorderLayout.CENTER);

        login = new JLabel("Hai già un account? Login", 20, ColorGUIView.testoSuggerimento, Font.BOLD);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.setHorizontalAlignment(JLabel.CENTER);
        panel.add(login, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.requestFocus();
    }

    /**
     * Restituisce il frame della RegisterPageView.
     *
     * @return il frame della RegisterPageView
     */
    public JFrame getFrame() {
        return frame;
    }


    /**
     * Restituisce il campo di testo per l'inserimento del nome utente.
     *
     * @return il campo di testo per l'inserimento del nome utente
     */
    public JTextField getNomeField() {
        return nome;
    }

    /**
     * Restituisce il campo di testo per l'inserimento del cognome utente.
     *
     * @return il campo di testo per l'inserimento del cognome utente
     */
    public JTextField getCognomeField() {
        return cognome;
    }

    /**
     * Restituisce il campo di testo per l'inserimento dell'email utente.
     *
     * @return il campo di testo per l'inserimento dell'email utente
     */
    public JTextField getEmailField() {
        return email;
    }

    /**
     * Restituisce il campo di testo per l'inserimento della password utente.
     *
     * @return il campo di testo per l'inserimento della password utente
     */
    public JTextField getPasswordField() {
        return password;
    }

    /**
     * Restituisce il campo di testo per l'inserimento della conferma della password.
     *
     * @return il campo di testo per l'inserimento della conferma della password
     */
    public JTextField getConfermaPasswordField() {
        return confermaPassword;
    }

/**
 * Restituisce il pulsante per la creazione di un account.
 *
 * @return il pulsante per la creazione di un account
 */
    public JButton getCreaAccButton() {
        return creaAcc;
    }

    /**
     * Restituisce l'etichetta per il login.
     *
     * @return l'etichetta per il login
     */
    public JLabel getLoginLabel() {
        return login;
    }
}


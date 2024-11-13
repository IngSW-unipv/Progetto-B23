package View;

import javax.swing.*;
import java.awt.*;

/**
 * La classe LoginView rappresenta la finestra di login che permette di
 * effettuare l'accesso al social network.
 *
 * @author Dylan
 * @version 1.2
 */
public class LoginView {
    private JTextField email;
    private JTextField password;
    private JFrame frame;
    private JButton loginButton;
    private JLabel registerLabel;

    /**
     * Costruttore che crea la finestra di login e la visualizza a schermo
     */
    public LoginView() {
        frame = new JFrame();
        frame.setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(115, 0, 182, 0));

        JLabel title = new JLabel("Bentornato", 50, ColorGUIView.white, Font.BOLD);
        title.setHorizontalAlignment(JLabel.CENTER);
        panel.add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(3, 1, 10, 10));
        center.setBackground(null);
        center.setBorder(BorderFactory.createEmptyBorder(34, 200, 17, 200));

        email = new JTextField("Email");
        center.add(email);
        password = new JTextField("Password");
        center.add(password);
        loginButton = new JButton("Login", 45, 20);

        center.add(loginButton);
        panel.add(center, BorderLayout.CENTER);

        registerLabel = new JLabel("Non hai un account? Registrati", 20, ColorGUIView.testoSuggerimento, Font.BOLD);
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(registerLabel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.requestFocus();
    }

    /**
     * Restituisce il campo di testo per l'email.
     *
     * @return il campo di testo per l'email
     */
    public JTextField getEmailField() {
        return email;
    }

    /**
     * Restituisce il campo di testo per la password.
     *
     * @return il campo di testo per la password
     */
    public JTextField getPasswordField() {
        return password;
    }

    /**
     * Restituisce la finestra principale del login.
     *
     * @return la finestra principale del login
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Restituisce il bottone di login.
     *
     * @return il bottone di login
     */
    public JButton getLoginButton() {
        return loginButton;
    }


    /**
     * Restituisce l'etichetta per la registrazione.
     *
     * @return l'etichetta per la registrazione
     */
    public JLabel getRegisterLabel() {
        return registerLabel;
    }

}

